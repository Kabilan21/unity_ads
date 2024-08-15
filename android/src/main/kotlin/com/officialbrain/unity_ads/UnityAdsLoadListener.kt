package com.officialbrain.unity_ads

import android.util.Log
import com.unity3d.ads.IUnityAdsLoadListener
import com.unity3d.ads.UnityAds.UnityAdsLoadError

class UnityAdsLoadListener(private val placementChannelManager: PlacementChannelManager) :
    IUnityAdsLoadListener {
    override fun onUnityAdsAdLoaded(placementId: String?) {
        placementChannelManager.invokeMethod(UnityAdsConstants.LOAD_COMPLETE_METHOD, placementId!!)
    }

    override fun onUnityAdsFailedToLoad(
        placementId: String?,
        error: UnityAdsLoadError,
        message: String?
    ) {
        placementChannelManager.invokeMethod(
            UnityAdsConstants.LOAD_FAILED_METHOD,
            placementId!!, convertError(error), message
        )
    }

    private fun convertError(error: UnityAdsLoadError): String? {
        return when (error) {
            UnityAdsLoadError.INITIALIZE_FAILED -> "initializeFailed"
            UnityAdsLoadError.INTERNAL_ERROR -> "internalError"
            UnityAdsLoadError.INVALID_ARGUMENT -> "invalidArgument"
            UnityAdsLoadError.NO_FILL -> "noFill"
            UnityAdsLoadError.TIMEOUT -> "timeout"
            else -> ""
        }
    }
}