package juggermod.cards.Skill;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import static juggermod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Wow extends CustomCard {
	public static final String ID = "ExampleMod_Wow";
	private static final String NAME = "曼波";
	private static final String IMG_PATH = "ModExampleResources/img/cards/Wow.png";
	private static final int COST = 2;
	private static final String DESCRIPTION = "随机对全体敌方造成两层脆弱或者两层虚弱";
	private static final CardType TYPE = CardType.SKILL;
	private static final CardColor COLOR = EXAMPLE_GREEN;
	private static final CardRarity RARITY = CardRarity.RARE;



	public Wow() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, CardTarget.ALL_ENEMY);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
			if (AbstractDungeon.cardRandomRng.randomBoolean()) {
				addToBot(new ApplyPowerAction(monster, p, new VulnerablePower(monster, 2, false), 2, true, AbstractGameAction.AttackEffect.NONE));
			} else {
				addToBot(new ApplyPowerAction(monster, p, new WeakPower(monster, 2, false), 2, true, AbstractGameAction.AttackEffect.NONE));
			}
		}
	}

	@Override
	public AbstractCard makeCopy() {
		return new Wow();
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeBaseCost(8);
		}
	}


}
