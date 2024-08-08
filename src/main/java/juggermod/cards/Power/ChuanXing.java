package juggermod.cards.Power;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static juggermod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class ChuanXing extends CustomCard {

	public static final String ID = "ExampleMod_S7";
	private static final String NAME = "你从未体验过的船新版本";
	private static final String IMG_PATH = "ModExampleResources/img/cards/005234.png";
	private static final int COST = 1;
	private static final String DESCRIPTION = "每回合开始时递增你的费用并且额外抽一张牌";
	private static final CardType TYPE = CardType.POWER;
	private static final CardColor COLOR = EXAMPLE_GREEN;
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.SELF;

    public ChuanXing() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
		this.exhaust = true;// Amount of Strength to gain
	}


	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeBaseCost(0);
		}
		// Define upgrade logic here
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		// Apply a temporary power to the player that increases energy at the start of each turn
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StartOfTurnEnergyPower(p), 1));
	}

	private static class StartOfTurnEnergyPower extends AbstractPower {
		public static final String POWER_ID = "StartOfTurnEnergyPower";
        private int turnCounter;

		public StartOfTurnEnergyPower(AbstractPlayer player) {
			this.name = "回合开始获得能量";
			this.ID = POWER_ID;
			this.owner = player;
            this.turnCounter = 0;
			this.updateDescription();
			this.loadRegion("focus"); // Ensure region name matches the power image
		}

		@Override
		public void atStartOfTurn() {
			this.turnCounter++;
			AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.turnCounter));
			this.updateDescription();
		}

		@Override
		public void updateDescription() {
			this.description = "回合开始时得到递增" + this.turnCounter + " 能量";
		}
	}

}