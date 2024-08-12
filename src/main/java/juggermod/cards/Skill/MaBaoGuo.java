package juggermod.cards.Skill;

import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static juggermod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class MaBaoGuo extends AbstractCard {
	public static final String ID = "ExampleMod_Ma";
	private static final String NAME = "Never gonna give you up";
	private static final String IMG_PATH = "ModExampleResources/img/cards/015534.png";
	private static final int COST = 5;
	private static final String DESCRIPTION = "防御 !B! 甲临时版本";
	private static final CardType TYPE = CardType.SKILL;
	private static final CardColor COLOR = EXAMPLE_GREEN;
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.SELF;

	public MaBaoGuo() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

		this.baseBlock = 30;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new com.megacrit.cardcrawl.actions.common.GainBlockAction(p, p, this.block));
	}

	@Override
	public void triggerOnEndOfTurnForPlayingCard() {
		addToBot(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
	}

	@Override
	public AbstractCard makeCopy() {
		return new MaBaoGuo();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(20);
		}
	}
}
