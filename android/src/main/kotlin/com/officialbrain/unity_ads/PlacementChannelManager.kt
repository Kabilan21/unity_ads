package com.officialbrain.unity_ads

import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodChannel


class PlacementChannelManager constructor(private val binaryMessenger: BinaryMessenger) {
    private var placementChannels: MutableMap<String, MethodChannel> = HashMap()

    fun invokeMethod(methodName: String?, adUnitId: String) {
        invokeMethod(methodName, adUnitId, HashMap())
    }

    fun invokeMethod(
        methodName: String?,
        adUnitId: String,
        errorCode: String?,
        errorMessage: String?
    ) {
        val arguments: MutableMap<String?, String?> = HashMap()
        arguments[UnityAdsConstants.ERROR_CODE_PARAMETER] = errorCode
        arguments[UnityAdsConstants.ERROR_MESSAGE_PARAMETER] = errorMessage
        invokeMethod(methodName, adUnitId, arguments)
    }

    private fun invokeMethod(
        methodName: String?,
        placementId: String,
        arguments: MutableMap<String?, String?>
    ) {
        arguments[UnityAdsConstants.PLACEMENT_ID_PARAMETER] = placementId
        var channel = placementChannels[placementId]
        if (channel == null) {
            channel = MethodChannel(
                binaryMessenger!!,
                UnityAdsConstants.VIDEO_AD_CHANNEL + "_" + placementId
            )
            placementChannels!![placementId] = channel
        }
        channel.invokeMethod(methodName!!, arguments)
    }
}