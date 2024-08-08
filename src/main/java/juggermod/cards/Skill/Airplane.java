package juggermod.cards.Skill;

import basemod.abstracts.CustomCard;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static juggermod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;


public class Airplane extends CustomCard{
	public static final String ID = "ExampleMod_Airplane";
	private static final String NAME = "起飞";
	private static final String IMG_PATH = "ModExampleResources/img/cards/013234.png";
	private static final int COST = 3;
	private static final String DESCRIPTION = "抽三张牌回复三点生命";
	private static final CardType TYPE = CardType.SKILL;
	private static final CardColor COLOR = EXAMPLE_GREEN;
	private static final CardRarity RARITY = CardRarity.COMMON;






	private static final int DRAW_AMT= 3;


    public Airplane() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, CardTarget.SELF);

		this.magicNumber = this.baseMagicNumber = DRAW_AMT;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, this.magicNumber));
        int secondMagic = 3;
        AbstractDungeon.actionManager.addToBottom(new HealAction(AbstractDungeon.player, AbstractDungeon.player, secondMagic));
	}


	@Override
	public AbstractCard makeCopy() {
		return new Airplane();
	}

	@Override
	public void upgrade() {

	}
	@Override
	public boolean canUpgrade() {
		return false;
	}
}

