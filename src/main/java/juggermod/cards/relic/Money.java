package juggermod.cards.relic;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static juggermod.cards.config.RelicConfig.RELIC_PRE_NAME;

/**
 * 这个遗物会在开始的时候赋予房间所有怪物3点血量

 **/
@SuppressWarnings("unused")
public class Money extends CustomRelic {
    /**
     * 遗物ID 随便写 但是需要和json文件名称一致
     * 比如我这里最终是 CANDY_MOD_Money 就需要最后json文件内有 CANDY_MOD_Money 的遗物信息
     */
    public static final String ID = RELIC_PRE_NAME + "Money";
    /**
     * 日志对象
     */
    private static final Logger log = LogManager.getLogger(Money.class);
    
    /**
     * 构造函数
     */
    public Money() {
        //图片使用内置的 使用破碎王冠 的图标
        //使用内置图标就不需要导入了 想自定义可以抄其他的mod或者看教程
        super(ID, "crown.png", RelicTier.COMMON, LandingSound.CLINK);
    }
    //crown.png
    /**ModExampleResources/img/cards/Basketball.png
     * 在战斗开始时触发
     * Untitled
     * crown.png
     */

    @Override
    public void atTurnStart() {
        this.flash();
        int energySpentThisTurn = 0; // 重置每回合消耗的能量
    }

    @Override
    public void onUseCard(final AbstractCard targetCard, final UseCardAction useCardAction) {
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, StrengthPower.POWER_ID));

        // 累加消耗的能量
        int energySpentThisTurn = targetCard.costForTurn;
        int strengthIncrease = energySpentThisTurn / 3;

        // 重新计算并应用新的力量加成
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
                AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, strengthIncrease), strengthIncrease));
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Money();
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
}