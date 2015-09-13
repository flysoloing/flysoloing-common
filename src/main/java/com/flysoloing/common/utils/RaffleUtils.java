package com.flysoloing.common.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 抽奖工具类<br>
 * User: laitao<br>
 * Date: 15-3-24<br>
 * Time: 下午6:57<br>
 */
public class RaffleUtils {

    /**
     * 按照传入的抽奖中奖率抽奖，使用的是伪随机算法
     * @param raffleRates 抽奖商品中奖概率列表
     * @return -1：无法抽奖；<br>其他：对应的商品序号
     */
    public static int raffle(List<BigDecimal> raffleRates) {
        // 如果抽奖率列表为空，则返回-1表示无法抽奖
        if (raffleRates == null || raffleRates.isEmpty()) {
            return -1;
        }

        // 当前参与抽奖奖品的数量
        int size = raffleRates.size();

        // 计算总概率
        BigDecimal sumRate = new BigDecimal("0");
        for (BigDecimal rate : raffleRates)
            sumRate = sumRate.add(rate);

        // 计算每个奖品在总概率基础下的概率，并重排序
        List<BigDecimal> sortRaffleRates = new ArrayList<BigDecimal>(size);
        BigDecimal tempSumRate = new BigDecimal("0");
        for (BigDecimal rate : raffleRates) {
            tempSumRate = tempSumRate.add(rate);
            BigDecimal r = tempSumRate.divide(sumRate, 10, BigDecimal.ROUND_HALF_UP);
            sortRaffleRates.add(r);
        }

        // 抽取随机值，并加入已排序的抽奖概率列表
        double nextDouble = Math.random();
        BigDecimal index = new BigDecimal(nextDouble);
        sortRaffleRates.add(index);
        Collections.sort(sortRaffleRates);

        // 返回随机值所在抽奖概率列表的索引
        return sortRaffleRates.indexOf(index);
    }

}
