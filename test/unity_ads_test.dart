import 'package:flutter_test/flutter_test.dart';
import 'package:unity_ads/unity_ads_platform_interface.dart';
import 'package:unity_ads/unity_ads_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockUnityAdsPlatform
    with MockPlatformInterfaceMixin
    implements UnityAdsPlatform {
  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final UnityAdsPlatform initialPlatform = UnityAdsPlatform.instance;

  test('$MethodChannelUnityAds is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelUnityAds>());
  });

  test('getPlatformVersion', () async {
    MockUnityAdsPlatform fakePlatform = MockUnityAdsPlatform();
    UnityAdsPlatform.instance = fakePlatform;
  });
}
