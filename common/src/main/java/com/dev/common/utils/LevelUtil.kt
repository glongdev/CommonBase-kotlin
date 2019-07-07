package com.lzt.common.utils

/**
 *
 * @author guolong
 * @since 2019/6/16
 */
object LevelUtil {

    const val LEVEL_1_WORD_COUNT = 0
    const val LEVEL_2_WORD_COUNT = 15
    const val LEVEL_3_WORD_COUNT = 30
    const val LEVEL_4_WORD_COUNT = 50
    const val LEVEL_5_WORD_COUNT = 70
    const val LEVEL_6_WORD_COUNT = 80
    const val LEVEL_7_WORD_COUNT = 100
    const val LEVEL_8_WORD_COUNT = 140
    const val LEVEL_9_WORD_COUNT = 160
    const val LEVEL_10_WORD_COUNT = 180

    const val LEVEL_1_UNCOMMON_WORD_COUNT = 0
    const val LEVEL_2_UNCOMMON_WORD_COUNT = 0
    const val LEVEL_3_UNCOMMON_WORD_COUNT = 0
    const val LEVEL_4_UNCOMMON_WORD_COUNT = 1
    const val LEVEL_5_UNCOMMON_WORD_COUNT = 3
    const val LEVEL_6_UNCOMMON_WORD_COUNT = 6
    const val LEVEL_7_UNCOMMON_WORD_COUNT = 15
    const val LEVEL_8_UNCOMMON_WORD_COUNT = 25
    const val LEVEL_9_UNCOMMON_WORD_COUNT = 40
    const val LEVEL_10_UNCOMMON_WORD_COUNT = 70

    const val LEVEL_1_LABLE = "地球人"
    const val LEVEL_2_LABLE = "月球人"
    const val LEVEL_3_LABLE = "水星人"
    const val LEVEL_4_LABLE = "金星人"
    const val LEVEL_5_LABLE = "木星人"
    const val LEVEL_6_LABLE = "土星人"
    const val LEVEL_7_LABLE = "天王星人"
    const val LEVEL_8_LABLE = "海王星人"
    const val LEVEL_9_LABLE = "冥王星人"
    const val LEVEL_10_LABLE = "火星人"

    /**
     * 地球人
     */
    fun isLevel1(unCommonCount: Int): Boolean {
        return true
    }

    /**
     * 月球人        15个汉字
     */
    fun isLevel2(unCommonCount: Int): Boolean {
        return unCommonCount >= LEVEL_2_UNCOMMON_WORD_COUNT
    }

    /**
     * 水星人         30个汉字
     */
    fun isLevel3(unCommonCount: Int): Boolean {
        return unCommonCount >= LEVEL_3_UNCOMMON_WORD_COUNT
    }

    /**
     * 金星人         50个汉字+1个生僻字
     */
    fun isLevel4(unCommonCount: Int): Boolean {
        return unCommonCount >= LEVEL_4_UNCOMMON_WORD_COUNT
    }

    /**
     * 木星            70个汉字+3个生僻字
     */
    fun isLevel5(unCommonCount: Int): Boolean {
        return unCommonCount >= LEVEL_5_UNCOMMON_WORD_COUNT
    }

    /**
     * 土星人         80个汉字+6个生僻字
     */
    fun isLevel6(unCommonCount: Int): Boolean {
        return unCommonCount >= LEVEL_6_UNCOMMON_WORD_COUNT
    }

    /**
     * 天王星人      100个汉字+15个生僻字
     */
    fun isLevel7(unCommonCount: Int): Boolean {
        return unCommonCount >= LEVEL_7_UNCOMMON_WORD_COUNT
    }

    /**
     * 海王星人      140个汉字+25个生僻字
     */
    fun isLevel8(unCommonCount: Int): Boolean {
        return unCommonCount >= LEVEL_8_UNCOMMON_WORD_COUNT
    }

    /**
     * 冥王星人      160个汉字+40个生僻字
     */
    fun isLevel9(unCommonCount: Int): Boolean {
        return unCommonCount >= LEVEL_9_UNCOMMON_WORD_COUNT
    }

    /**
     * 火星人         180个汉字+70个生僻字
     */
    fun isLevel10(unCommonCount: Int): Boolean {
        return unCommonCount >= LEVEL_10_UNCOMMON_WORD_COUNT
    }
}