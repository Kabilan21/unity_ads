package com.officialbrain.unity_ads
import com.unity3d.ads.IUnityAdsShowListener
import com.unity3d.ads.UnityAds.UnityAdsShowCompletionState
import com.unity3d.ads.UnityAds.UnityAdsShowError

class UnityAdsShowListener(private val placementChannelManager: PlacementChannelManager) :
    IUnityAdsShowListener {

    override fun onUnityAdsShowFailure(
        placementId: String?,
        error: UnityAdsShowError,
        message: String?
    ) {
        placementChannelManager.invokeMethod(
            UnityAdsConstants.SHOW_FAILED_METHOD,
            placementId!!, convertError(error), message
        )
    }

    override fun onUnityAdsShowStart(placementId: String?) {
        placementChannelManager.invokeMethod(UnityAdsConstants.SHOW_START_METHOD, placementId!!)
    }

    override fun onUnityAdsShowClick(placementId: String?) {
        placementChannelManager.invokeMethod(UnityAdsConstants.SHOW_CLICK_METHOD, placementId!!)
    }

    override fun onUnityAdsShowComplete(placementId: String?, state: UnityAdsShowCompletionState) {
        if (state == UnityAdsShowCompletionState.SKIPPED) {
            placementChannelManager.invokeMethod(
                UnityAdsConstants.SHOW_SKIPPED_METHOD,
                placementId!!
            )
        } else if (state == UnityAdsShowCompletionState.COMPLETED) {
            placementChannelManager.invokeMethod(
                UnityAdsConstants.SHOW_COMPLETE_METHOD,
                placementId!!
            )
        }
    }

    private fun convertError(error: UnityAdsShowError): String? {
        return when (error) {
            UnityAdsShowError.NOT_INITIALIZED -> "notInitialized"
            UnityAdsShowError.NOT_READY -> "notReady"
            UnityAdsShowError.VIDEO_PLAYER_ERROR -> "videoPlayerError"
            UnityAdsShowError.INVALID_ARGUMENT -> "invalidArgument"
            UnityAdsShowError.NO_CONNECTION -> "noConnection"
            UnityAdsShowError.ALREADY_SHOWING -> "alreadyShowing"
            UnityAdsShowError.INTERNAL_ERROR -> "internalError"
            UnityAdsShowError.TIMEOUT -> "timeout"
            else -> ""
        }
    }
}