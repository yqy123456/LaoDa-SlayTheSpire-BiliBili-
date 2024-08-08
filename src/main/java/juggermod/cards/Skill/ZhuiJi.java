package juggermod.cards.Skill;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

import com.megacrit.cardcrawl.monsters.AbstractMonster;

import com.megacrit.cardcrawl.powers.PoisonPower;

import static juggermod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;


public class ZhuiJi extends CustomCard{
	public static final String ID = "ExampleMod_ZhuiJi";
	private static final String NAME = "坠机的代价";
	private static final String IMG_PATH = "ModExampleResources/img/cards/009234.png";
	private static final int COST = 10;
	private static final String DESCRIPTION = "立即撞飞精英怪物或者给予boss十层中毒";
	private static final CardType TYPE = CardType.SKILL;
	private static final CardColor COLOR = EXAMPLE_GREEN;
	private static final CardRarity RARITY = CardRarity.RARE;






	private static final int DRAW_AMT= 10;



	public ZhuiJi() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, CardTarget.SELF);

		this.magicNumber = this.baseMagicNumber = DRAW_AMT;
		this.exhaust=true;
	}
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (m.type != AbstractMonster.EnemyType.BOSS && m.type != AbstractMonster.EnemyType.ELITE) {
			addToTop(new InstantKillAction(m));
		} else {
			addToBot(new ApplyPowerAction(m, p, new PoisonPower(m, p, this.magicNumber), this.magicNumber, AbstractGameAction.AttackEffect.POISON));
		}
	}



	@Override
	public AbstractCard makeCopy() {
		return new ZhuiJi();
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeBaseCost(9);
		}

	}

}

