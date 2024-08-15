package com.officialbrain.unity_ads

interface UnityAdsConstants {
    companion object {
        const val CHANNEL = "com.officialbrain.UnityAdsPlugin";
        const val VIDEO_AD_CHANNEL = "$CHANNEL/videoAd"
        const val PLACEMENT_ID_PARAMETER = "placementId"
        const val ERROR_CODE_PARAMETER = "errorCode"
        const val ERROR_MESSAGE_PARAMETER = "errorMessage"

        const val INIT_METHOD = "init"
        const val GAME_ID_PARAMETER = "gameId"
        const val TEST_MODE_PARAMETER = "testMode"
        const val INIT_COMPLETE_METHOD = "initComplete"
        const val INIT_FAILED_METHOD = "initFailed"
        const val IS_INITIALIZED_METHOD = "isInitialized"

        const val LOAD_METHOD = "load"
        const val LOAD_COMPLETE_METHOD = "loadComplete"
        const val LOAD_FAILED_METHOD = "loadFailed"

        const val SHOW_VIDEO_METHOD = "showVideo"
        const val SERVER_ID_PARAMETER = "serverId"
        const val SHOW_COMPLETE_METHOD = "showComplete"
        const val SHOW_FAILED_METHOD = "showFailed"
        const val SHOW_START_METHOD = "showStart"
        const val SHOW_SKIPPED_METHOD = "showSkipped"
        const val SHOW_CLICK_METHOD = "showClick"
    }
}
