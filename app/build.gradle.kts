plugins {
    id("message.android.application")
    id("message.android.compose")
    id("message.android.hilt")
}

android {
    namespace  = "com.message.app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.message.app"
        minSdk        = 23
        targetSdk     = 35
        versionCode   = 1
        versionName   = "1.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose     = true
        buildConfig = true
    }
}

dependencies {
    implementation(project(":core-common"))
    implementation(project(":core-data"))
    implementation(project(":core-telephony"))
    implementation(project(":core-permission"))
    implementation(project(":core-sms"))
    implementation(project(":core-mms"))
    implementation(project(":core-rcs"))

    val composeBom = platform(libs.compose.bom)
    implementation(composeBom)
    implementation(libs.bundles.compose)
    implementation(libs.activity.compose)
    implementation(libs.navigation.compose)
    implementation(libs.bundles.lifecycle)
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    ksp(libs.hilt.compiler)
    implementation(libs.work.runtime.ktx)
    implementation(libs.hilt.work)
    ksp(libs.hilt.compiler.ext)
    implementation(libs.bundles.coroutines)
    implementation(libs.datastore.preferences)
    implementation(libs.coil.compose)
    implementation(libs.splashscreen)
    coreLibraryDesugaring(libs.desugar.jdk)

    testImplementation(libs.bundles.testing.unit)
    androidTestImplementation(libs.bundles.testing.android)
}