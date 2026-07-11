# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# Mapbox Common SDK keep rules
-keep class com.mapbox.common.** { *; }
-keep interface com.mapbox.common.** { *; }

# Specifically keep native callback classes
-keep class * extends com.mapbox.common.ResultCallback { *; }
-dontwarn com.mapbox.common.**

# Preserve native methods
-keepclasseswithmembernames class * {
    native <methods>;
}