package com.officialbrain.unity_ads

import com.unity3d.ads.IUnityAdsInitializationListener
import com.unity3d.ads.UnityAds.UnityAdsInitializationError
import io.flutter.plugin.common.MethodChannel


class UnityAdsInitializationListener(private val channel: MethodChannel) :
    IUnityAdsInitializationListener {
    override fun onInitializationComplete() {
        channel.invokeMethod(UnityAdsConstants.INIT_COMPLETE_METHOD, HashMap<String, String>())
    }


    override fun onInitializationFailed(error: UnityAdsInitializationError, message: String) {
        val arguments: MutableMap<String, String> = HashMap()
        arguments[UnityAdsConstants.ERROR_CODE_PARAMETER] = convertError(error)
        arguments[UnityAdsConstants.ERROR_MESSAGE_PARAMETER] = message
        channel.invokeMethod(UnityAdsConstants.INIT_FAILED_METHOD, arguments)
    }

    private fun convertError(error: UnityAdsInitializationError): String {
        return when (error) {
            UnityAdsInitializationError.INTERNAL_ERROR -> "internalError"
            UnityAdsInitializationError.INVALID_ARGUMENT -> "invalidArgument"
            UnityAdsInitializationError.AD_BLOCKER_DETECTED -> "adBlockerDetected"
            else -> ""
        }
    }
}