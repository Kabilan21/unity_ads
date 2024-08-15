import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'unity_ads_platform_interface.dart';

/// An implementation of [UnityAdsPlatform] that uses method channels.
class MethodChannelUnityAds extends UnityAdsPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('unity_ads');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
}
