package juggermod.cards.Power;

import basemod.abstracts.CustomCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static juggermod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class DaJiYi extends CustomCard {
	public static final String ID = "ExampleMod_S";
	private static final int COST = 2;
	private static final int HEAL_AMOUNT = 5;

	public DaJiYi() {
		super(ID, "大记忆回复术", "ModExampleResources/img/cards/006234.png", COST, "回合结束回复5点并持续两回合.", CardType.SKILL, EXAMPLE_GREEN, CardRarity.UNCOMMON, CardTarget.SELF);
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeBaseCost(1);

		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		// Apply a temporary power to the player that heals at the end of the turn
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new EndOfTurnHealingPower(p, HEAL_AMOUNT), HEAL_AMOUNT));
	}

	private static class EndOfTurnHealingPower extends AbstractPower {
		public static final String POWER_ID = "EndOfTurnHealingPower";
		private final AbstractPlayer player;
		private final int healAmount;
		private int turnsRemaining;

		public EndOfTurnHealingPower(AbstractPlayer player, int healAmount) {
			this.name = "回复";
			this.ID = POWER_ID;
			this.owner = player;
			this.player = player;
			this.healAmount = healAmount;
			this.turnsRemaining = 2; // Set the duration to 2 turns
			this.updateDescription();
			this.loadRegion("regen"); // Ensure region name matches the power image
		}

		@Override
		public void atEndOfTurn(boolean isPlayer) {
			if (isPlayer) {
				AbstractDungeon.actionManager.addToBottom(new HealAction(player, player, healAmount));
				turnsRemaining--;
				if (turnsRemaining <= 0) {
					AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(player, player, POWER_ID));
				}
			}
		}

		@Override
		public void updateDescription() {
			this.description = "回合结束后回复 " + this.healAmount + " 点生命值，持续 " + this.turnsRemaining + " 回合";
		}
	}}
