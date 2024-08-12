package juggermod;
import basemod.BaseMod;


import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

import com.megacrit.cardcrawl.helpers.CardHelper;

import com.megacrit.cardcrawl.core.CardCrawlGame;

import juggermod.cards.Attack.ChuanDi;
import juggermod.cards.Attack.Strike;
import juggermod.cards.Power.ZhanDouShuang;
import juggermod.cards.Skill.*;
import juggermod.cards.Attack.*;
import juggermod.cards.Power.*;
import juggermod.cards.Curse.*;
import juggermod.cards.relic.Money;
import juggermod.cards.relic.Money2;
import juggermod.characters.MyCharacter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static juggermod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;
import static juggermod.characters.MyCharacter.PlayerColorEnum.MY_CHARACTER;


@SpireInitializer
public class ExampleMod implements  EditCardsSubscriber, EditCharactersSubscriber,EditRelicsSubscriber{
    private static final String MY_CHARACTER_BUTTON = "ModExampleResources/img/char/Character_Button.png";
    public static final Logger logger = LogManager.getLogger(ExampleMod.class);
    private static final String MY_CHARACTER_PORTRAIT = "ModExampleResources/img/char/Portrait.png";
    private static final String BG_ATTACK_512 = "ModExampleResources/img/512/bg_attack_512.png";
    private static final String BG_POWER_512 = "ModExampleResources/img/512/bg_power_512.png";
    private static final String BG_SKILL_512 = "ModExampleResources/img/512/bg_skill_512.png";
    private static final String SMALL_ORB = "ModExampleResources/img/char/small_orb.png";
    private static final String BG_ATTACK_1024 = "ModExampleResources/img/1024/bg_attack.png";
    private static final String BG_POWER_1024 = "ModExampleResources/img/1024/bg_power.png";
    private static final String BG_SKILL_1024 = "ModExampleResources/img/1024/bg_skill.png";
    private static final String BIG_ORB = "ModExampleResources/img/char/card_orb.png";
    private static final String ENERGY_ORB = "ModExampleResources/img/char/cost_orb.png";

    public static final Color MY_COLOR = CardHelper.getColor(186.0f, 108.0f, 31.0f);
    //public static final String BRITTLE_ROCK_RELIC = "ModExampleResources/img/relics/KeyBoard.png";
    //public static Texture getBrittleRockTexture() { return new Texture(BRITTLE_ROCK_RELIC); }
    public ExampleMod() {
        logger.info("Initialized ExampleMod");
        BaseMod.subscribe(this);
        BaseMod.addColor(EXAMPLE_GREEN, MY_COLOR, MY_COLOR, MY_COLOR, MY_COLOR, MY_COLOR, MY_COLOR, MY_COLOR,BG_ATTACK_512,BG_SKILL_512,BG_POWER_512,ENERGY_ORB,BG_ATTACK_1024,BG_SKILL_1024,BG_POWER_1024,BIG_ORB,SMALL_ORB);

// 省略其他

}

    public static void initialize() {
        logger.info("Initializing ExampleMod");
        new ExampleMod();

    }




    @Override
    public void receiveEditCards() {

        BaseMod.addCard(new Strike());
        BaseMod.addCard(new Banana());
        BaseMod.addCard(new Defence());
        BaseMod.addCard(new Fury());
        BaseMod.addCard(new Earthquake());
        BaseMod.addCard(new Guanxing());
        BaseMod.addCard(new DaJiYi());
        BaseMod.addCard(new NaiShuai());
        BaseMod.addCard(new Shan());
        BaseMod.addCard(new JKL());
        BaseMod.addCard(new ZhanDouShuang());
        BaseMod.addCard(new Bing());
        BaseMod.addCard(new Curse());
        BaseMod.addCard(new Curse2());
        BaseMod.addCard(new Curse3());
        BaseMod.addCard(new ShengCang());
        BaseMod.addCard(new Airplane());
        BaseMod.addCard(new Tiger());
        BaseMod.addCard(new ChuanDi());
        BaseMod.addCard(new Ji());
        BaseMod.addCard(new MaBaoGuo());
        BaseMod.addCard(new SeeYouAgain());
        BaseMod.addCard(new ZhuiJi());
        BaseMod.addCard(new GuangTou());
        BaseMod.addCard(new Random());
        BaseMod.addCard(new Taitan());
        BaseMod.addCard(new Banana2());
        BaseMod.addCard(new ChuanXing());
        BaseMod.addCard(new Banana3());




    }


    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new MyCharacter(CardCrawlGame.playerName), MY_CHARACTER_BUTTON, MY_CHARACTER_PORTRAIT, MY_CHARACTER);
    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelic(new Money2(), RelicType.SHARED);
        BaseMod.addRelic(new Money(), RelicType.SHARED);
    }





}

