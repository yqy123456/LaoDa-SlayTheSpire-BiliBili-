package juggermod.cards.Skill;

import basemod.abstracts.CustomCard;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static juggermod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;


public class Banana extends CustomCard{
	public static final String ID = "ExampleMod_Banana";
	private static final String NAME = "dark♂香蕉";
	private static final String IMG_PATH = "ModExampleResources/img/cards/Banana.png";
	private static final int COST = 4;
	private static final String DESCRIPTION = "所有攻击牌本回合费用为0♂";
	private static final CardType TYPE = CardType.SKILL;
	private static final CardColor COLOR = EXAMPLE_GREEN;
	private static final CardRarity RARITY = CardRarity.UNCOMMON;





	private static final int COST_UPGRADE = 1;
	private static final int DRAW_AMT= 1;




	public Banana() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, AbstractCard.CardTarget.SELF);

		this.magicNumber = this.baseMagicNumber = DRAW_AMT;
	}
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		/*for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			if (!mo.hasPower("Slow") && !mo.hasPower("Slowed")) {
				AbstractDungeon.actionManager
						.addToBottom(new ApplyPowerAction(mo, p, new TemporarySlowPower(mo, 0),
								0, true, AbstractGameAction.AttackEffect.NONE));
			}
		}*/
		for (AbstractCard c : p.hand.group) {
			if (c.type.equals(CardType.ATTACK)){
				c.setCostForTurn(-9);
			}
		}
	}

	@Override
	public AbstractCard makeCopy() {
		return new Banana();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeBaseCost(COST_UPGRADE);

			this.initializeDescription();
		}
	}
}
