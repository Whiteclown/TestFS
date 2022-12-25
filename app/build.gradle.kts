plugins {
    id(Dependencies.Plugins.ANDROID_APPLICATION)
    id(Dependencies.Plugins.KOTLIN_ANDROID)
    kotlin(Dependencies.Plugins.KAPT)
    kotlin(Dependencies.Plugins.SERIALIZATION) version Dependencies.Version.SERIALIZATION
    id(Dependencies.Plugins.HILT)
    `android-kotlin-convention`
}

android {

    defaultConfig {
        applicationId = "com.example.testfs"
        versionCode = 1
        versionName = "1.0"
    }
    namespace = "com.example.testfs"
}

dependencies {
    kapt(Dependencies.Hilt.COMPILER)
    implementation(Dependencies.Hilt.ANDROID)

    implementation(Dependencies.Core.CORE)
    implementation(Dependencies.Core.APPCOMPAT)
    implementation(Dependencies.Core.MATERIAL)

    implementation(Dependencies.Navigation.UI)
    implementation(Dependencies.Navigation.FRAGMENT)
    implementation(Dependencies.Navigation.DYNAMIC_FEATURES)

    implementation(Dependencies.Network.RETROFIT)
    implementation(Dependencies.Network.OKHTTP)
    implementation(Dependencies.Network.OKHTTP_LOGGING_INTERCEPTOR)
    implementation(Dependencies.Network.KOTLINX_SERIALIZATION)
    implementation(Dependencies.Network.KOTLINX_CONVERTER)

    implementation(Dependencies.Room.RUNTIME)
    annotationProcessor(Dependencies.Room.ANNOTATION_COMPILER)
    kapt(Dependencies.Room.KAPT_COMPILER)
    implementation(Dependencies.Room.KTX)

    implementation(project(Modules.Core.CORE))
    implementation(project(Modules.Features.HOME))
    implementation(project(Modules.Features.HISTORY))
    implementation(project(Modules.Shared.REMOTE))
}