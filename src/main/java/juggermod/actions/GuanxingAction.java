package juggermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class GuanxingAction extends AbstractGameAction {
    private final AbstractCard card;

    public GuanxingAction(AbstractCard card) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.card = card;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (!AbstractDungeon.isScreenUp) {
                AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.drawPile, 1, "Choose a card to discard", false);
            } else if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {


            }
            tickDuration();
        }
        else {
            AbstractDungeon.player.drawPile.moveToDiscardPile(AbstractDungeon.gridSelectScreen.selectedCards.get(0));
            AbstractDungeon.player.draw();
            AbstractDungeon.player.draw();// 移动到这里以确保在动作完成后才执行
            isDone = true; // 标记动作执行完毕
            tickDuration();
        }
    }
}