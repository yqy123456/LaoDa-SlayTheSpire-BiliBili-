package juggermod.cards.Power;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;

import static juggermod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class NaiShuai extends CustomCard {
	public static final String ID = "ExampleMod_S2";
	private static final String NAME = "传奇耐摔王";
	private static final String IMG_PATH = "ModExampleResources/img/cards/009924.png";
	private static final int COST = 1;
	private static final String DESCRIPTION = "回合结束获得3点多重护甲.";
	private static final CardType TYPE = CardType.POWER;
	private static final CardColor COLOR = EXAMPLE_GREEN;
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.SELF;


	public NaiShuai() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
		this.baseMagicNumber = this.magicNumber = 2;
		this.exhaust = true;// Amount of Strength to gain
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeMagicNumber(5);
			this.rawDescription = "回合结束获得5点多重护甲";
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PlatedArmorPower(p,  3),3));



	}


	@Override
	public AbstractCard makeCopy() {
		return new NaiShuai();
	}
}
