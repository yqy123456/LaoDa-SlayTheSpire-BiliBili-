package juggermod.cards.Skill;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static juggermod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;


public class Banana2 extends CustomCard{
	public static final String ID = "ExampleMod_Banana2";
	private static final String NAME = "dark♂香蕉2.0";
	private static final String IMG_PATH = "ModExampleResources/img/cards/Banana.png";
	private static final int COST = 4;
	private static final String DESCRIPTION = "你手牌中的所有技能牌本回合费用减少5♂";
	private static final CardType TYPE = CardType.SKILL;
	private static final CardColor COLOR = EXAMPLE_GREEN;
	private static final CardRarity RARITY = CardRarity.COMMON;






	private static final int DRAW_AMT= 1;




	public Banana2() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, CardTarget.SELF);

		this.magicNumber = this.baseMagicNumber = DRAW_AMT;
	}
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {

		for (AbstractCard c : p.hand.group) {
			if (c.type.equals(CardType.SKILL)){
				c.setCostForTurn(-5);
			}
		}
	}

	@Override
	public AbstractCard makeCopy() {
		return new Banana2();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeBaseCost(3);

			this.initializeDescription();
		}
	}
}
