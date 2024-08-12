package juggermod.cards.relic;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RemoveRelicAction extends AbstractGameAction {
    private final String relicId;

    public RemoveRelicAction(String relicId) {
        this.relicId = relicId;
    }

    @Override
    public void update() {
        AbstractDungeon.player.relics.removeIf(relic -> relic.relicId.equals(this.relicId));
        this.isDone = true;
    }
}
