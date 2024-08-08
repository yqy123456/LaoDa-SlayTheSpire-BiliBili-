package juggermod.cards.actions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
//import luShi.powers.ExtraordinaryPower;

public class DiscoveryDrawAction extends AbstractGameAction {
    private boolean retrieveCard = false;

    private boolean optional;

    private int numberOfCards;

    private AbstractPlayer player;

    public DiscoveryDrawAction(int numberOfCards, boolean optional) {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.player = AbstractDungeon.player;
        this.numberOfCards = numberOfCards;
        this.amount = numberOfCards;
        this.optional = optional;
    }

    public DiscoveryDrawAction(int numberOfCards) {
        this(numberOfCards, false);
    }

    public void update() {
        if (this.player.drawPile.isEmpty()) {
            this.isDone = true;
            return;
        }
        ArrayList<AbstractCard> generatedCards = generateCardChoices();
        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractDungeon.cardRewardScreen.customCombatOpen(generatedCards, CardRewardScreen.TEXT[1], true);
            tickDuration();
        } else {
            if (!this.retrieveCard) {
                if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
                    AbstractCard disCard = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
                    AbstractCard disCard2 = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
                    if (AbstractDungeon.player.hasPower("MasterRealityPower")) {
                        disCard.upgrade();
                        disCard2.upgrade();
                    }
                    disCard.current_x = -1000.0F * Settings.xScale;
                    disCard2.current_x = -1000.0F * Settings.xScale + AbstractCard.IMG_HEIGHT_S;
                    if (this.amount == 1) {
                        if (AbstractDungeon.player.hand.size() < BaseMod.MAX_HAND_SIZE) {
                            AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                        } else {
                            AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                        }
                        disCard2 = null;
                    }
                    AbstractDungeon.cardRewardScreen.discoveryCard = null;
                }
                this.retrieveCard = true;
            }
            tickDuration();
        }
    }

    private ArrayList<AbstractCard> generateCardChoices() {
        ArrayList<AbstractCard> derp = new ArrayList();
        int difCardSize = getDifCardSize();
        while (derp.size() != difCardSize && derp.size() < 3) {
            boolean dupe = false;
            AbstractCard tmp = this.player.drawPile.getRandomCard(true);
            Iterator<AbstractCard> var6 = derp.iterator();
            while (var6.hasNext()) {
                AbstractCard c = var6.next();
                if (c.cardID.equals(tmp.cardID)) {
                    dupe = true;
                    break;
                }
            }
            if (!dupe) {
                AbstractCard card = tmp.makeStatEquivalentCopy();
                /*if (AbstractDungeon.player.hasPower(ExtraordinaryPower.POWER_ID)) {
                    card.cost = 1;
                    card.costForTurn = card.cost;
                    card.isCostModified = true;
                    card.freeToPlayOnce = false;
                }*/
                derp.add(card);
            }
        }
        return derp;
    }

    public int getDifCardSize() {
        Iterator<AbstractCard> var6 = this.player.drawPile.group.iterator();
        Set<String> set = new HashSet<>();
        for (Iterator<AbstractCard> it = var6; it.hasNext(); ) {
            AbstractCard c = var6.next();
            set.add(c.cardID);
        }
        return set.size();
    }

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("BetterToHandAction")).TEXT;
}
