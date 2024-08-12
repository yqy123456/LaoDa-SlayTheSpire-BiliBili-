package juggermod.cards.Skill;

import basemod.abstracts.CustomCard;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static juggermod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Victory extends CustomCard {
	public static final String ID = "ExampleMod_ZhuiJi";
	private static final String NAME = "坚持就是胜利";
	private static final String IMG_PATH = "ModExampleResources/img/cards/009234.png";
	private static final int COST = 10;
	private static final String DESCRIPTION = "每回合的费用重置为5点，五回合后获得胜利";
	private static final CardType TYPE = CardType.SKILL;
	private static final CardColor COLOR = EXAMPLE_GREEN;
	private static final CardRarity RARITY = CardRarity.RARE;


	public Victory() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, CardTarget.ENEMY); // 修改目标为ENEMY
		this.magicNumber=5;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new ApplyPowerAction(p, p, new VictoryPower(p, this.magicNumber), this.magicNumber));
	}

	@Override
	public AbstractCard makeCopy() {
		return new Victory();
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeBaseCost(9);
		}
	}
	public class VictoryPower extends AbstractPower {
		public static final String POWER_ID = "ExampleMod_VictoryPower";
		private static final String NAME = "Victory Power";
		private final String[] DESCRIPTIONS = new String[]{
				"Survive for ", " more turns to win."
		};
		private int turnsLeft;

		public VictoryPower(AbstractCreature owner, int turns) {
			this.name = NAME;
			this.ID = POWER_ID;
			this.owner = owner;
			this.amount = turns;
			this.turnsLeft = turns;
			this.updateDescription();
			this.loadRegion("poison");
		}

		@Override
		public void atEndOfTurn(boolean isPlayer) {
			if (isPlayer) {
				EnergyPanel.totalCount=5;
				this.turnsLeft--;
				if (this.turnsLeft <= 0) {
					for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
						addToBot(new InstantKillAction(m));
					}
					addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
				}
				this.updateDescription();
			}
		}

		@Override
		public void updateDescription() {
			this.description = DESCRIPTIONS[0] + this.turnsLeft + DESCRIPTIONS[1];
		}
	}

}
