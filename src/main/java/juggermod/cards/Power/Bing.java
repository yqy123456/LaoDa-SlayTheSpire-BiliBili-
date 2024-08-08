package juggermod.cards.Power;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static juggermod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Bing extends CustomCard {

	public static final String ID = "ExampleMod_S6";
	private static final String NAME = "冰";
	private static final String IMG_PATH = "ModExampleResources/img/cards/007234.png";
	private static final int COST = 2;
	private static final String DESCRIPTION = "回合结束时保留所有手牌";
	private static final CardType TYPE = CardType.POWER;
	private static final CardColor COLOR = EXAMPLE_GREEN;
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.SELF;

    public Bing() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
		this.exhaust = true;// Amount of Strength to gain
	}


	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeBaseCost(1);

		}
		// Define upgrade logic here
	}


	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RetainAndDrawPower(p), 1));
	}

	private static class RetainAndDrawPower extends AbstractPower {
		public static final String POWER_ID = "RetainAndDrawPower";
		private final AbstractPlayer player;

		public RetainAndDrawPower(AbstractPlayer player) {
			this.name = "woc,冰";
			this.ID = POWER_ID;
			this.owner = player;
			this.player = player;
			this.updateDescription();
			this.loadRegion("dexterity"); // Ensure region name matches the power image
		}

		@Override
		public void atEndOfTurn(boolean isPlayer) {
			if (isPlayer) {
				for (AbstractCard card : player.hand.group) {
					card.retain = true;
				}
			}
		}


		@Override
		public void atStartOfTurn() {
			//AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DrawCardAction(player, 1));
		}

		@Override
		public void updateDescription() {
			this.description = "回合结束时保留所有手牌。回合开始时抽一张牌。";
		}
	}
}