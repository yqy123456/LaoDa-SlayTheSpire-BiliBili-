package juggermod.cards.Skill;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


import static juggermod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;


public class ShengCang extends CustomCard{
	public static final String ID = "ExampleMod_ShengCang";
	private static final String NAME = "升舱的钱我来出";
	private static final String IMG_PATH = "ModExampleResources/img/cards/010234.png";
	private static final int COST = 0;
	private static final String DESCRIPTION = "消耗十点生命抽三张牌";
	private static final CardType TYPE = CardType.SKILL;
	private static final CardColor COLOR = EXAMPLE_GREEN;
	private static final CardRarity RARITY = CardRarity.RARE;






	private static final int DRAW_AMT= 3;
	private static final int HP_COST = 3;


	public ShengCang() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, CardTarget.SELF);

		this.magicNumber = this.baseMagicNumber = DRAW_AMT;
	}
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {

		if (AbstractDungeon.player.currentHealth > HP_COST) {
			AbstractDungeon.actionManager.addToBottom(new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player, HP_COST));
			AbstractDungeon.actionManager.addToBottom(new DrawCardAction(3));
		}
	}


	@Override
	public AbstractCard makeCopy() {
		return new ShengCang();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeBaseCost(0);
		}
	}
}

