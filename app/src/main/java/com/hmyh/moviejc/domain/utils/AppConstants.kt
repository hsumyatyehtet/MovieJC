package com.hmyh.moviejc.domain.utils

/**
 * Created by P.T.H.W on 03/03/2023.
 */

val PHONE_NUMBER_REGEX = Regex("^(09|9)[0-9]{7,9}\$|^(09)[0-9]{8,10}\$")

const val TEST_SCRATCH_URL = "https://dav57wfojsbqk.cloudfront.net/"

const val UAT_WEB_GAME_CAMPAIGNS_ENDPOINT = "https://pocket-game-uat.onenex.dev/"

const val PREPROD_WEB_GAME_CAMPAIGNS_ENDPOINT = "https://pocket-game-preprod.onenex.dev/"

const val PROD_WEB_GAME_CAMPAIGNS_ENDPOINT ="https://pocket-game.pocket.com.mm/"

enum class SheetType(val value: String) {
    SheetState("sheet_state"),
    SheetTownship("sheet_township"),
    SheetGender("sheet_gender"),
}

//
const val GIFT_TYPE_COIN = "gift_coin"
const val GIFT_TYPE_REWARD = "gift_reward"
const val GIFT_TYPE_QUICK = "gift_quick_access"
const val GIFT_REWARD_TYPE_VOUCHER = "gift_reward_type_voucher"
const val GIFT_REWARD_TYPE_REWARD = "gift_reward_type_reward"
const val GIFT_SCREEN_TYPE="coin_landing_screen"

//
const val CHECK_PIN_STATUS_KEY = "check_pin_status_key"
const val FRAG_RESULT_RAFFLE_TICKET = "check_pin_status_key"

//
const val PUSHY_TOPIC_ALL = "all"
const val PUSHY_TOPIC_ANDROID = "android"


/** Server Date Format */
const val SERVER_DATE_FORMAT = "yyyy-MM-dd"
const val DMY_DATE_FORMAT = "dd-MM-yyyy"
const val SERVER_TIME_FORMAT = "HH:mm:ss"
const val SERVER_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss"
const val SERVER_TIME_ZONE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

/** App specific date style */
const val COIN_EXPIRE_DATE_STYLE = "dd MMM yyyy"
const val REWARD_EXPIRE_DATE_STYLE = "dd-MM-yyyy"
const val STAMP_REWARD_EXPIRE_DATE_STYLE = "MMMM dd, yyyy"


/** Session expire */
const val SESSION_EXPIRE_YES = "yes"
const val SESSION_EXPIRE_NO = "no"

/** Campaign Type */
enum class CampaignType(val value: String) {
    Stamp("stamp"),
    Quiz("quiz"),
    Survey("survey"),
    Progress("progress"),
    Game("game"),
    CampaignWelcome("campaign-welcome"),
}

const val GAME_TRY_ISSUED="GameTryIssued"

const val PAID_WITH_COIN_ICON="paid_with_coins_icon"
const val COIN_RECEIVED_ICON= "coins_received_icon"
const val REDEEM_REWARD_ICON="redeemed_reward_icon"