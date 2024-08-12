package juggermod.cards.Power;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static juggermod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class ZhanDouShuang extends CustomCard {

	public static final String ID = "ExampleMod_S5";
	private static final String NAME = "战斗，爽";
	private static final String IMG_PATH = "ModExampleResources/img/cards/008234.png";
	private static final int COST = 1;
	private static final String DESCRIPTION = "每使用两张攻击牌，抽一张牌";
	private static final CardType TYPE = CardType.POWER;
	private static final CardColor COLOR = EXAMPLE_GREEN;
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.SELF;

	public ZhanDouShuang() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
		this.exhaust = true;// Amount of Strength to gain
	}


	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();

			this.upgradeBaseCost(0); // Reduce cost to 0 when upgraded
		}

	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DrawOnAttackPower(p), 1));
	}

	private static class DrawOnAttackPower extends AbstractPower {
		public static final String POWER_ID = "DrawOnAttackPower";
		private final AbstractPlayer player;
		private int skillCounter=0;

		public DrawOnAttackPower(AbstractPlayer player) {
			this.name = "爽";
			this.ID = POWER_ID;
			this.owner = player;
			this.player = player;
			this.updateDescription();
			this.loadRegion("strength"); // Ensure region name matches the power image
		}

		@Override
		public void onUseCard(AbstractCard card, UseCardAction action) {

			if (card.type == CardType.SKILL) {
				this.skillCounter++;
				if (this.skillCounter % 2 == 0) {
					AbstractDungeon.actionManager.addToBottom(new DrawCardAction(player, 1));
				}
				this.updateDescription();
			}
		}

		@Override
		public void updateDescription() {
			this.description = "每使用两张攻击牌，抽一张牌。";
		}
	}

}