package juggermod.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyRelic extends CustomRelic {
    // 遗物ID
    public static final Logger logger = LogManager.getLogger(MyRelic.class);
    public static final String ID = "MyRelic"; // 确保 ID 是唯一且正确的
    private static final String IMG_PATH = "ModExampleResources/img/relics/MyRelic.png"; // 确保路径正确
    private static final AbstractRelic.RelicTier RELIC_TIER = AbstractRelic.RelicTier.STARTER;
    private static final AbstractRelic.LandingSound LANDING_SOUND = AbstractRelic.LandingSound.FLAT;

    // 定义描述字符串数组
    public static final String[] DESCRIPTIONS = new String[] {
            "这是遗物的主要描述。",
            "这是第二行描述。",
            "这是一种额外的描述信息，可以根据需求设置。",
    };

    public MyRelic() {
        super(ID, loadTexture(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    private static Texture loadTexture(String path) {
        Texture texture = ImageMaster.loadImage(path);
        if (texture == null) {
            logger.error("Failed to load image at path: " + path);
        } else {
            logger.info("Successfully loaded image at path: " + path);
        }
        return texture;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStart() {
        super.atBattleStart();
        this.addToBot(new DrawCardAction(1));
    }

    @Override
    public AbstractRelic makeCopy() {
        return new MyRelic();
    }
}
