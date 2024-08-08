package juggermod.cards.Skill;

import basemod.abstracts.CustomCard;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

import com.megacrit.cardcrawl.monsters.AbstractMonster;

import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;

import static juggermod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;


public class GuangTou extends CustomCard{
	public static final String ID = "ExampleMod_GuangTou";
	private static final String NAME = "哇，光头";
	private static final String IMG_PATH = "ModExampleResources/img/cards/123456.png";
	private static final int COST = 5;
	private static final String DESCRIPTION = "亮瞎敌人使得自身无法选中";
	private static final CardType TYPE = CardType.SKILL;
	private static final CardColor COLOR = EXAMPLE_GREEN;
	private static final CardRarity RARITY = CardRarity.RARE;






	private static final int DRAW_AMT= 1;



	public GuangTou() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, CardTarget.SELF);

		this.magicNumber = this.baseMagicNumber = DRAW_AMT;

	}
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, this.magicNumber), this.magicNumber));
	}


	@Override
	public AbstractCard makeCopy() {
		return new GuangTou();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBaseCost(5);


	}
	}
}

