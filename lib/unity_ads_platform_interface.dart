import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'unity_ads_method_channel.dart';

abstract class UnityAdsPlatform extends PlatformInterface {
  /// Constructs a UnityAdsPlatform.
  UnityAdsPlatform() : super(token: _token);

  static final Object _token = Object();

  static UnityAdsPlatform _instance = MethodChannelUnityAds();

  /// The default instance of [UnityAdsPlatform] to use.
  ///
  /// Defaults to [MethodChannelUnityAds].
  static UnityAdsPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [UnityAdsPlatform] when
  /// they register themselves.
  static set instance(UnityAdsPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
