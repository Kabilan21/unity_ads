package com.officialbrain.unity_ads

import android.app.Activity
import android.content.Context
import android.util.Log
import com.unity3d.ads.UnityAds
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result


class UnityAdsPlugin : FlutterPlugin, MethodCallHandler, ActivityAware {
    private lateinit var channel: MethodChannel
    private lateinit var placementChannelManager: PlacementChannelManager;
    private lateinit var activity: Activity;
    private lateinit var context: Context;

    override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        context = flutterPluginBinding.applicationContext;
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, UnityAdsConstants.CHANNEL)
        channel.setMethodCallHandler(this)
        val binaryMessenger = flutterPluginBinding.binaryMessenger
        placementChannelManager = PlacementChannelManager(binaryMessenger);
    }

    override fun onMethodCall(call: MethodCall, result: Result) {
        when (call.method) {
            UnityAdsConstants.INIT_METHOD -> result.success(initialize(call))
            UnityAdsConstants.LOAD_METHOD -> result.success(load(call))
            UnityAdsConstants.SHOW_VIDEO_METHOD -> result.success(show(call))
        }
    }


    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }

    private fun initialize(call: MethodCall): Boolean {
        val gameId = call.argument<String>(UnityAdsConstants.GAME_ID_PARAMETER);
        val testMode = call.argument<Boolean>(UnityAdsConstants.TEST_MODE_PARAMETER);
        UnityAds.initialize(
            context,
            gameId,
            testMode ?: false,
            UnityAdsInitializationListener(channel)
        );
        return true;
    }

    private fun load(call: MethodCall): Boolean {
        val placementId = call.argument<String>(UnityAdsConstants.PLACEMENT_ID_PARAMETER);
        try {
            UnityAds.load(placementId, UnityAdsLoadListener(placementChannelManager))
            return true
        } catch (ex: Exception) {
            placementChannelManager.invokeMethod(
                UnityAdsConstants.LOAD_FAILED_METHOD,
                placementId!!,
                "unknown",
                ex.message
            )
        }
        return false
    }

    private fun show(call: MethodCall): Boolean {
        val placementId = call.argument<String>(UnityAdsConstants.PLACEMENT_ID_PARAMETER);
        try {
            UnityAds.show(activity, placementId, UnityAdsShowListener(placementChannelManager));
            return true
        } catch (ex: Exception) {
            placementChannelManager.invokeMethod(
                UnityAdsConstants.LOAD_FAILED_METHOD,
                placementId!!,
                "unknown",
                ex.message
            )
        }
        return false
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        this.activity = binding.activity;
    }

    override fun onDetachedFromActivityForConfigChanges() {
    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
    }

    override fun onDetachedFromActivity() {
    }
}
