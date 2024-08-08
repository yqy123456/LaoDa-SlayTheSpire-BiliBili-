package juggermod.characters;

import java.util.ArrayList;

import basemod.abstracts.CustomPlayer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;

import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;


import com.megacrit.cardcrawl.screens.CharSelectInfo;

import juggermod.ExampleMod;
import juggermod.cards.Attack.Strike;
import juggermod.cards.Skill.Defence;

import juggermod.cards.relic.Money;
import juggermod.cards.relic.Money2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyCharacter extends CustomPlayer {
    public static final Logger logger = LogManager.getLogger(ExampleMod.class);
    public static final int ENERGY_PER_TURN = 4; // 每回合获得费用（能量）数
    public static final String MY_CHARACTER_SHOULDER_2 = "ModExampleResources/img/char/shoulder.png"; // 篝火姿势
    public static final String MY_CHARACTER_SHOULDER_1 = "ModExampleResources/img/char/shoulder1.png"; // 另一个篝火姿势
    public static final String MY_CHARACTER_CORPSE = "ModExampleResources/img/char/corpse.png"; // 尸体

    // 人物死亡图像

    // 战斗界面左下角能量图标的每个图层
    private static final String[] ORB_TEXTURES = new String[]{
            "ModExampleResources/img/UI/orb/layer5.png",
            "ModExampleResources/img/UI/orb/layer4.png",
            "ModExampleResources/img/UI/orb/layer3.png",
            "ModExampleResources/img/UI/orb/layer2.png",
            "ModExampleResources/img/UI/orb/layer1.png",
            "ModExampleResources/img/UI/orb/layer6.png",
            "ModExampleResources/img/UI/orb/layer5d.png",
            "ModExampleResources/img/UI/orb/layer4d.png",
            "ModExampleResources/img/UI/orb/layer3d.png",
            "ModExampleResources/img/UI/orb/layer2d.png",
            "ModExampleResources/img/UI/orb/layer1d.png"
    };
    // 每个图层的旋转速度
    private static final float[] LAYER_SPEED = new float[]{-40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F};

    public MyCharacter(String name) {
        super(name, PlayerColorEnum.MY_CHARACTER, ORB_TEXTURES, "ModExampleResources/img/UI/orb/vfx.png", LAYER_SPEED, null, null);
        logger.info("MyCharacter initialized with name: " + name);

        initializeClass(
                "ModExampleResources/img/char/shoulderz.png",
                MY_CHARACTER_SHOULDER_2,
                MY_CHARACTER_SHOULDER_1,
                MY_CHARACTER_CORPSE,
                this.getLoadout(),
                0.0F, 0.0F,
                200.0F, 220.0F,
                new EnergyManager(ENERGY_PER_TURN)
        );

        logger.info("Character class initialized");
    }

    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        for(int x = 0; x<5; x++) {
            retVal.add(Strike.ID);
        }
        for(int x = 0; x<5; x++) {
            retVal.add(Defence.ID);

            //retVal.add("ExampleMod_S2");
        }
        retVal.add("ExampleMod_SeeYouAgain");
        //retVal.add("ExampleMod_S4");
        //retVal.add("ExampleMod_S5");
        //retVal.add("ExampleMod_S6");
        return retVal;
    }

    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(Money2.ID);
        return retVal;
    }

    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(
                "Laoda", // 人物名字
                "Man", // 人物介绍
                75, // 当前血量
                75, // 最大血量
                0, // 初始充能球栏位
                24, // 初始携带金币
                5, // 每回合抽牌数量
                this, // 别动
                this.getStartingRelics(), // 初始遗物
                this.getStartingDeck(), // 初始卡组
                false // 别动
        );
    }

    @Override
    public String getTitle(PlayerClass playerClass) {

        return "Laoda";
    }

    @Override
    public AbstractCard.CardColor getCardColor() {

        return PlayerColorEnum.EXAMPLE_GREEN;
    }

    @Override
    public Color getCardRenderColor() {

        return ExampleMod.MY_COLOR;
    }

    @Override
    public AbstractCard getStartCardForEvent()
    {
        return new Strike();
    }

    @Override
    public Color getCardTrailColor() {
        return ExampleMod.MY_COLOR;
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 5;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);

    }
    @Override
    public ArrayList<CutscenePanel> getCutscenePanels() {
        ArrayList<CutscenePanel> panels = new ArrayList<>();
        // 有两个参数的，第二个参数表示出现图片时播放的音效
        panels.add(new CutscenePanel("ModExampleResources/img/char/Victory1.png", "ATTACK_MAGIC_FAST_1"));
        panels.add(new CutscenePanel("ModExampleResources/img/char/Victory2.png"));
        panels.add(new CutscenePanel("ModExampleResources/img/char/Victory3.png"));
        return panels;
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_HEAVY";
    }

    @Override
    public String getLocalizedCharacterName() {
        return "Laoda";
    }

    @Override
    public AbstractPlayer newInstance() {
        return new MyCharacter("Laoda");
    }

    @Override
    public String getSpireHeartText() {
        return "oil";
    }

    @Override
    public Color getSlashAttackColor() {
        return ExampleMod.MY_COLOR;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL, AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL};
    }


    @Override
    public String getVampireText() {
        return "hi";
    }

    public static class PlayerColorEnum {
        @SpireEnum
        public static AbstractPlayer.PlayerClass MY_CHARACTER;

        @SpireEnum
        public static AbstractCard.CardColor EXAMPLE_GREEN;
    }

    public static class PlayerLibraryEnum {
        @SpireEnum
        public static CardLibrary.LibraryType EXAMPLE_GREEN;
    }
}

