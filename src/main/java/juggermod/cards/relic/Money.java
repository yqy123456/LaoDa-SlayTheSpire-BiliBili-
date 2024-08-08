package juggermod.cards.relic;

import basemod.abstracts.CustomRelic;
import cn.candy.config.RelicConfig;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 这个遗物会在开始的时候赋予房间所有怪物3点血量

 **/
@SuppressWarnings("unused")
public class Money extends CustomRelic {
    /**
     * 遗物ID 随便写 但是需要和json文件名称一致
     * 比如我这里最终是 CANDY_MOD_Money 就需要最后json文件内有 CANDY_MOD_Money 的遗物信息
     */
    public static final String ID = RelicConfig.RELIC_PRE_NAME + "Money";
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
        if (AbstractDungeon.player.energy.energyMaster <= 6) {
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
        }
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
                AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
    }



    @Override
    public AbstractRelic makeCopy() {
        return new Money();
    }
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
}
