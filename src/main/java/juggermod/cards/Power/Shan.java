package juggermod.cards.Power;

import basemod.abstracts.CustomCard;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static juggermod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Shan extends CustomCard {

	public static final String ID = "ExampleMod_S3";
	private static final String NAME = "善";
	private static final String IMG_PATH = "ModExampleResources/img/cards/014234.png";
	private static final int COST = 1;
	private static final String DESCRIPTION = "本回合每使用一张技能牌回复1点能量.";
	private static final CardType TYPE = CardType.POWER;
	private static final CardColor COLOR = EXAMPLE_GREEN;
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.SELF;


	public Shan() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
		this.exhaust = true;// Amount of Strength to gain
	}


	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();

		}
	}


	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		// Apply a temporary power to the player that regains energy for every 2 skill cards played this turn
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SkillEnergyPower(p), 1));
	}

	private static class SkillEnergyPower extends AbstractPower {
		public static final String POWER_ID = "SkillEnergyPower";
		private int skillCounter;


		public SkillEnergyPower(AbstractPlayer player) {
			this.name = "技能赋能";
			this.ID = POWER_ID;
			this.owner = player;
            this.skillCounter = 0;
			this.updateDescription();
			this.loadRegion("dexterity"); // Ensure region name matches the power image
		}

		@Override
		public void onAfterUseCard(AbstractCard card, UseCardAction action) {
			if (card.type == AbstractCard.CardType.ATTACK) {
				AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.GainEnergyAction(1));
			}
		}

		@Override
		public void atEndOfTurn(boolean isPlayer) {
			this.skillCounter = 0; // Reset counter at the end of the turn
			this.updateDescription();
		}

		@Override
		public void updateDescription() {
			this.description = " 本回合每使用一张技能牌回复1点能量";
		}


    }
}