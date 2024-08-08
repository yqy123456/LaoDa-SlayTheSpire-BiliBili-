package juggermod.cards.Attack;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;

import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

import static juggermod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Earthquake extends CustomCard {
	public static final String ID = "ExampleMod_Earthquake";
	private static final String NAME = " 地震...Huh?";
	private static final String IMG_PATH = "ModExampleResources/img/cards/huh.png";
	private static final int COST = 1;



	private static final int VULNERABLE_AMT = 1;
	private static final int UPGRADE_PLUS_VULNERABLE = 1;
	private static final String DESCRIPTION = "造成 !D! 伤害并且全体虚弱";
	private static final CardType TYPE = CardType.ATTACK;
	private static final CardColor COLOR = EXAMPLE_GREEN;
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;

	public Earthquake() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
		this.damage = this.baseDamage = 3;
		this.tags.add(CardTags.STARTER_STRIKE);
		this.tags.add(CardTags.STRIKE);
		this.isMultiDamage = true;
		this.magicNumber = this.baseMagicNumber = VULNERABLE_AMT;


	}


	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeDamage(3);
			this.upgradeMagicNumber(UPGRADE_PLUS_VULNERABLE);
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new SFXAction("THUNDERCLAP", 0.05f));
		for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			if (mo.isDeadOrEscaped())
				continue;
			AbstractDungeon.actionManager.addToBottom(new VFXAction(new LightningEffect(mo.drawX, mo.drawY), 0.05f));
		}
		AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage,
				this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
		for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			AbstractDungeon.actionManager
					.addToBottom(new ApplyPowerAction(mo, p, new VulnerablePower(mo, this.magicNumber, false),
							this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
		}
	}
	public AbstractCard makeCopy() {
		return new Earthquake();
	}
}