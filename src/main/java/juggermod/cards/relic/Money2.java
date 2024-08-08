//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package juggermod.cards.relic;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;

import juggermod.util.TextureLoader;

public class Money2 extends CustomRelic {
    public static final String ID = cn.candy.config.RelicConfig.RELIC_PRE_NAME + "Money2";
    private static final Texture IMG = TextureLoader.getTexture("ModExampleResources/img/relics/Basketball.png");

    private static final Texture OUTLINE = TextureLoader.getTexture("ModExampleResources/img/relics/outline/Basketball.png");


    public Money2() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.FLAT);
    }

    public void atBattleStart() {
        this.flash();

    }

    public void onVictory() {
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
}
