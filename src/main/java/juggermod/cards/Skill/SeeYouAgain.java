package juggermod.cards.Skill;

import basemod.abstracts.CustomCard;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;

import com.megacrit.cardcrawl.cards.AbstractCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import juggermod.powers.DoubleEffectPower;

import static juggermod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;


public class SeeYouAgain extends CustomCard {
	public static final String ID = "ExampleMod_SeeYouAgain";
	private static final String NAME = "See You 'Again'";
	private static final String IMG_PATH = "ModExampleResources/img/cards/003234.png";
	private static final int COST = 1;
	private static final String DESCRIPTION = "你的下一张牌(不包括能力牌)会被打出去两次";
	private static final CardType TYPE = CardType.SKILL;
	private static final CardColor COLOR = EXAMPLE_GREEN;
	private static final CardRarity RARITY = CardRarity.BASIC;
	private static final CardTarget TARGET = CardTarget.ENEMY;

	public SeeYouAgain() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
		this.damage = this.baseDamage = 2;
		this.magicNumber=this.baseMagicNumber=2;
		this.tags.add(CardTags.STARTER_STRIKE);
		this.tags.add(CardTags.STRIKE);
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DoubleEffectPower(p, 1), 1));
	}
	@Override
	public AbstractCard makeCopy() {
		return new SeeYouAgain();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeMagicNumber(1);
		}
	}
}
