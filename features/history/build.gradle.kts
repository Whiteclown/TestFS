plugins {
    id(Dependencies.Plugins.ANDROID_LIBRARY)
    id(Dependencies.Plugins.KOTLIN_ANDROID)
    kotlin(Dependencies.Plugins.KAPT)
    id(Dependencies.Plugins.HILT)
    `android-kotlin-convention`
}

android {
    namespace = "com.example.history"
}

dependencies {

    kapt(Dependencies.Hilt.COMPILER)
    implementation(Dependencies.Hilt.ANDROID)

    implementation(Dependencies.Core.CORE)
    implementation(Dependencies.Core.APPCOMPAT)
    implementation(Dependencies.Core.MATERIAL)

    implementation(Dependencies.Network.RETROFIT)

    implementation(Dependencies.Navigation.FRAGMENT)

    implementation(project(Modules.Core.CORE))
    implementation(project(Modules.Shared.REMOTE))
}