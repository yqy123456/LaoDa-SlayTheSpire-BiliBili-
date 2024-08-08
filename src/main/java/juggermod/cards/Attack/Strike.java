package juggermod.cards.Attack;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;


import static juggermod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;


public class Strike extends CustomCard {
	public static final String ID = "ExampleMod_Strike";
	private static final String NAME = "肘击";
	private static final String IMG_PATH = "ModExampleResources/img/cards/shoulder1.png";
	private static final int COST = 1;
	private static final String DESCRIPTION = "肘击造成4次 !D! 点伤害并且洗入弃牌堆两张牌";
	private static final CardType TYPE = CardType.ATTACK;
	private static final CardColor COLOR = EXAMPLE_GREEN;
	private static final CardRarity RARITY = CardRarity.BASIC;
	private static final CardTarget TARGET = CardTarget.ENEMY;

	public Strike() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
		this.damage = this.baseDamage = 1;
		this.tags.add(CardTags.STARTER_STRIKE);
		this.tags.add(CardTags.STRIKE);
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeDamage(2);
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		int num =0;
		while(true){
			num+=1;
			if(num>=5){
				break;
			}
		AbstractDungeon.actionManager.addToBottom(
				new DamageAction(
						m,
						new DamageInfo(
								p,
								this.damage,
								DamageInfo.DamageType.NORMAL
						)
				)
			);

		} // 创建当前卡牌的副本
		AbstractCard cardToDiscard = this.makeStatEquivalentCopy();
		AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(cardToDiscard, 2) );
	}
	public AbstractCard makeCopy() {
		return new Strike();
	}
}