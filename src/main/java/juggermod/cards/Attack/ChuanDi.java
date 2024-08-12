package juggermod.cards.Attack;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static juggermod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;


public class ChuanDi extends CustomCard{
	public static final String ID = "ExampleMod_ChuanDi";
	private static final String NAME = "传递";
	private static final String IMG_PATH = "ModExampleResources/img/cards/Banana.png";
	private static final int COST = 2;
	private static final String DESCRIPTION = "传递香蕉，香蕉伤害会从10开始上升";
	private static final CardType TYPE = CardType.ATTACK;
	private static final CardColor COLOR = EXAMPLE_GREEN;
	private static final CardRarity RARITY = CardRarity.COMMON;






	private static final int DRAW_AMT= 3;




	public ChuanDi() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, CardTarget.SELF);

		this.magicNumber = this.baseMagicNumber = DRAW_AMT;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		ArrayList<AbstractMonster> monsters = AbstractDungeon.getMonsters().monsters;

		// 如果没有敌人，退出方法
		if (monsters.isEmpty()) {
			return;
		}

		for (int i = 0; i < monsters.size(); i++) {
			AbstractMonster enemy = monsters.get(i);
			// 限制伤害值
			int damage = Math.min(i*5 + 10, 100); // 设置伤害的上限为100
			AbstractDungeon.actionManager.addToBottom(new DamageAction(enemy, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
		}
	}




	@Override
	public AbstractCard makeCopy() {
		return new ChuanDi();
	}

	@Override
	public void upgrade() {

	}
	@Override
	public boolean canUpgrade() {
		return false;
	}
}

