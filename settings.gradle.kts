pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Actors"
include(":app")
include(":core:data")
include(":core:designsystem")
include(":core:testing")
include(":core:network")

include(":core:common")
include(":core:mylibrary")
