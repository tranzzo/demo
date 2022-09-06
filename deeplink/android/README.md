# Create Deep Links to App Content

https://developer.android.com/training/app-links/deep-linking

# Verify Android App Links

https://developer.android.com/training/app-links/verify-site-associations

## Enable deeplink in debug build(Android 12)

https://user-images.githubusercontent.com/10816778/189054049-8085210a-1042-45d3-ae83-be2d97c6e3e2.mp4


:warning: You will not have a problem with `Android 12` on the production build type. Verification will be run automatically by this file `.well-known/assetlinks.json` that you have made on your site. \
See https://demo.tranzzo.com/.well-known/assetlinks.json as an example.


:warning: You will not have a problem with another android version less than 12

# Run example application and enjoy

All that you need you find in the example code. 

Interesting files: `AndroidManifest.xml, MainActivity.kt`

**AndroidManifest.xml**

```xml
...
<intent-filter android:autoVerify="true">
    <action android:name="android.intent.action.VIEW" />

    <category android:name="android.intent.category.DEFAULT" />
    <category android:name="android.intent.category.BROWSABLE" />

    <data
        android:host="demo.tranzzo.com"
        android:path="/deeplink"
        android:scheme="https" />
</intent-filter>
...
```

**Constansts**

```kotlin
private const val URL_LINK = "https://demo.tranzzo.com/app/deeplink/index.html"
private const val RESULT_LINK = "demo.tranzzo.com/deeplink"
```

**Processing the result**


```kotlin
override fun onNewIntent(intent: Intent?) {
    super.onNewIntent(intent)
    if (intent?.dataString.orEmpty().contains(RESULT_LINK)) {
        // do something
    }
}
```
