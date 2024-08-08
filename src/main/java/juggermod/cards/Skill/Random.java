package juggermod.cards.Skill;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


import static juggermod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Random extends CustomCard {
	public static final String ID = "ExampleMod_Random";
	private static final String NAME = "随机";
	private static final String IMG_PATH = "ModExampleResources/img/cards/zhuge.png";
	private static final int COST = 0;
	private static final String DESCRIPTION = "随机给两张技能牌";
	private static final CardType TYPE = CardType.SKILL;
	private static final CardColor COLOR = EXAMPLE_GREEN;
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.SELF;


	public Random() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
		this.magicNumber=1;
	}


	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		for (int i = 0; i < 2; i++) {
			AbstractCard card = getRandomExampleGreenCard();
			AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(card, 1));
		}
	}

	private AbstractCard getRandomExampleGreenCard() {

		return AbstractDungeon.returnTrulyRandomCardInCombat(CardType.SKILL).makeCopy();
	}

	@Override
	public void upgrade() {
		this.upgradeName();//升级名称。必带。


		this.upgradeBaseCost(0);//升级后的费用。注意括号内的值即为费用，与上方不同！！！！
		this.upgradeMagicNumber(-1);
		this.isEthereal = false;//虚无属性。
		this.exhaust = false;//消耗属性。
		this.isInnate = true;//固有属性。
		this.rawDescription = "查看抽牌堆选择1张牌弃掉，抽取牌库顶上两张牌,固有"; // 修改卡牌描述
		initializeDescription(); // 更新卡牌描述
		// Logic for card upgrade
	}
	public AbstractCard makeCopy() {
		return new Random();
	}
}


