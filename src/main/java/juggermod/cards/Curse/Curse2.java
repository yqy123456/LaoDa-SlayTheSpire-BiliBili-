package juggermod.cards.Curse;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static juggermod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Curse2 extends CustomCard {
	public static final String ID = "ExampleMod_SC2";
	private static final String NAME = "Strong Curse1";
	private static final String IMG_PATH = "img/cards/indomitable_will.png";
	private static final int COST = 1;
	private static final String DESCRIPTION = "七二抽一";
	private static final CardType TYPE = CardType.CURSE;
	private static final CardColor COLOR = EXAMPLE_GREEN;
	private static final CardRarity RARITY = CardRarity.CURSE;
	private static final CardTarget TARGET = CardTarget.SELF;

	private static final int DISCARDS = 2; // 弃掉的牌数
	private static final int DRAW = 1; // 抽的牌数

	public Curse2() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
		this.baseMagicNumber = this.magicNumber = 2; // Amount of Strength to gain
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeMagicNumber(1); // Increase Strength gain by 1 on upgrade
			this.exhaust = true;
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		int handSize = AbstractDungeon.player.hand.size();

		if (handSize >= DISCARDS) {
			// 如果手牌中有足够的牌，执行弃牌和抽牌
			AbstractDungeon.actionManager.addToBottom(new DiscardAction(p, p, DISCARDS, true));
			AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, DRAW));
		} else {
			// 如果手牌中牌数不足，直接抽牌
			AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, DRAW));
		}
	}


	@Override
	public AbstractCard makeCopy() {
		return new Curse2();
	}
}
