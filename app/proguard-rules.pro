# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.

# Preserve line number information and source files for readable stack traces
-keepattributes SourceFile,LineNumberTable

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

# ==============================================================================
# RETROFIT & GSON & OKHTTP RULES (Networking & Deserializzazione CDN/Supabase)
# ==============================================================================

# Attributi necessari per Gson e Retrofit (generics, riflessione e annotazioni)
-keepattributes Signature
-keepattributes *Annotation*
-keepattributes InnerClasses,EnclosingMethod
-keepattributes RuntimeVisibleAnnotations,RuntimeVisibleParameterAnnotations

# Regole generali Retrofit
-keep class retrofit2.** { *; }
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
-dontwarn retrofit2.**

# Regole generali Gson
-keep class com.google.gson.** { *; }
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}
-keepclassmembers enum * { *; }

# Regole generali OkHttp
-keep class okhttp3.** { *; }
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**

# ==============================================================================
# INTERFACCE API & MODELLI DATI (Supabase, CDN LavoraMi, DTOs)
# ==============================================================================

# Mantieni tutte le classi e interfacce del pacchetto Supabase
-keep class com.andreafilice.lavorami.supabase.** { *; }
-keep interface com.andreafilice.lavorami.supabase.** { *; }

# Interfaccia Retrofit per le chiamate alla CDN
-keep interface com.andreafilice.lavorami.APIWorks { *; }

# Modelli DTO e Descriptors usati per la deserializzazione JSON (CDN & Supabase)
-keep class com.andreafilice.lavorami.EventDescriptor { *; }
-keep class com.andreafilice.lavorami.VariablesDescriptor { *; }
-keep class com.andreafilice.lavorami.RequirementsDescriptor { *; }
-keep class com.andreafilice.lavorami.MetroStatusDescriptor { *; }
-keep class com.andreafilice.lavorami.*Descriptor { *; }

# Altri modelli/classi usati in app
-keep class com.andreafilice.lavorami.AccountManagement { *; }
-keep class com.andreafilice.lavorami.ChangeUsername { *; }
-keep class com.andreafilice.lavorami.DatabaseDataPreferences { *; }
-keep class com.andreafilice.lavorami.LinesActivity { *; }
-keep class com.andreafilice.lavorami.LinesDetailActivity { *; }
-keep class com.andreafilice.lavorami.MainActivity { *; }
-keep class com.andreafilice.lavorami.SettingsActivity { *; }
-keep class com.andreafilice.lavorami.RetrofitManager { *; }

