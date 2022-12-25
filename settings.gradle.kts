pluginManagement {

    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "TestFS"
include(
    ":app",
    ":core",
    ":features:home",
    ":features:history",
    ":shared:remote",
)
