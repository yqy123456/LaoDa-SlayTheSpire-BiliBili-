package juggermod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;


public class DoubleEffectPower extends AbstractPower {
	public static final String POWER_ID = "See You 'Again'";

	public DoubleEffectPower(AbstractCreature owner, int amount) {
		this.name = "See You 'Again'";
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		this.updateDescription();
		this.loadRegion("strength");
	}

	@Override
	public void onUseCard(AbstractCard card, UseCardAction action) {
		if (!card.purgeOnUse && this.amount > 0 && (card.type == AbstractCard.CardType.ATTACK || card.type == AbstractCard.CardType.SKILL)) {
			flash();
			AbstractMonster m = null;
			if (action.target != null) {
				m = (AbstractMonster) action.target;
			}
			AbstractCard tmp = card.makeSameInstanceOf();

			if (m != null) {
				tmp.calculateCardDamage(m);
			}
			tmp.purgeOnUse = true;
			AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, card.energyOnUse, true, true), true);
			this.amount--;
			if (this.amount == 0) {
				addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
			}
		}
	}

	@Override
	public void updateDescription() {
		this.description = "下一张牌会释放 " + this.amount + " 次";
	}
}