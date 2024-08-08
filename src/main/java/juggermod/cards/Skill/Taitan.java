package juggermod.cards.Skill;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static juggermod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;


public class Taitan extends CustomCard{
	public static final String ID = "ExampleMod_Chicken";
	private static final String NAME = "只因";
	private static final String IMG_PATH = "ModExampleResources/img/cards/ji.png";
	private static final int COST = 4;
	private static final String DESCRIPTION = "damn,能够复原你的能量";
	private static final CardType TYPE = CardType.SKILL;
	private static final CardColor COLOR = EXAMPLE_GREEN;
	private static final CardRarity RARITY = CardRarity.COMMON;

	private static final int DRAW_AMT= 3;

	public Taitan() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, CardTarget.SELF);

		this.magicNumber = this.baseMagicNumber = DRAW_AMT;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		int energyToRestore = p.energy.energyMaster - p.energy.energy;
		AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(energyToRestore));
	}


	@Override
	public AbstractCard makeCopy() {
		return new Taitan();
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeBaseCost(3);
	}
}}

