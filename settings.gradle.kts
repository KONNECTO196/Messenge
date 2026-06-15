pluginManagement {
    includeBuild("build-logic")
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

rootProject.name = "Message"

include(":app")
include(":core-common")
include(":core-data")
include(":core-telephony")
include(":core-permission")
include(":core-sms")
include(":core-mms")
include(":core-rcs")
include(":core-security")
include(":core-testing")
include(":core-database")
include(":core-notification")
include(":core-contact")
include(":core-media")
include(":core-sync")
include(":core-ui")