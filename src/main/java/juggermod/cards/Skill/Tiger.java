package juggermod.cards.Skill;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static juggermod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;


public class Tiger extends CustomCard{
	public static final String ID = "ExampleMod_Tiger";
	private static final String NAME = "虎视眈眈";
	private static final String IMG_PATH = "ModExampleResources/img/cards/011234.png";
	private static final int COST = 2;
	private static final String DESCRIPTION = "回复 !M! 点生命";
	private static final CardType TYPE = CardType.SKILL;
	private static final CardColor COLOR = EXAMPLE_GREEN;
	private static final CardRarity RARITY = CardRarity.UNCOMMON;






	private static final int DRAW_AMT=5;


	public Tiger() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, CardTarget.SELF);

		this.magicNumber = this.baseMagicNumber = DRAW_AMT;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {

		AbstractDungeon.actionManager.addToBottom(new HealAction(AbstractDungeon.player, AbstractDungeon.player, this.magicNumber));
	}


	@Override
	public AbstractCard makeCopy() {
		return new Tiger();
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeMagicNumber(5);



	}
}}

