package juggermod.cards.Skill;

import basemod.abstracts.CustomCard;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import juggermod.cards.actions.DiscoveryDrawAction;

import static juggermod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;


public class Banana3 extends CustomCard{
	public static final String ID = "ExampleMod_Banana3";
	private static final String NAME = "Banana Lead";
	private static final String IMG_PATH = "ModExampleResources/img/cards/Banana.png";
	private static final int COST = 4;
	private static final String DESCRIPTION = "你手牌中的所有技能牌本回合费用减少5♂";
	private static final CardType TYPE = CardType.SKILL;
	private static final CardColor COLOR = EXAMPLE_GREEN;
	private static final CardRarity RARITY = CardRarity.COMMON;











	public Banana3() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, CardTarget.SELF);

		this.baseMagicNumber = this.magicNumber = 1;
		this.exhaust = true;
	}
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		for (int i = 0; i < this.magicNumber; i++)
			addToBot(new DiscoveryDrawAction(1));
	}

	@Override
	public AbstractCard makeCopy() {
		return new Banana3();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(1);
			initializeDescription();
		}
	}

}
