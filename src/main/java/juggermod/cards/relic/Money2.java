//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package juggermod.cards.relic;

import com.badlogic.gdx.graphics.Texture;


import juggermod.cards.relic.abstracts.ClickableRelic;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import juggermod.util.TextureLoader;



public class Money2 extends ClickableRelic {
    public static final String ID = cn.candy.config.RelicConfig.RELIC_PRE_NAME + "Money2";
    private static final Texture IMG = TextureLoader.getTexture("ModExampleResources/img/relics/Basketball.png");
    private static final Texture OUTLINE = TextureLoader.getTexture("ModExampleResources/img/relics/outline/Basketball.png");


    public Money2() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.FLAT);
    }


    @Override
    public void atTurnStart() {

        this.flash();
        if (AbstractDungeon.player.energy.energyMaster <= 6) {
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
        }

    }

    @Override
    public void onRightClick() {
        AbstractDungeon.player.energy.energyMaster = 3;
        AbstractDungeon.player.decreaseMaxHealth(10);
        AbstractDungeon.actionManager.addToBottom(new RemoveRelicAction(this.relicId));
    }

    @Override
    public void onVictory() {

    }

    @Override
    public AbstractRelic makeCopy() {
        return new Money2();
    }
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
    
}
