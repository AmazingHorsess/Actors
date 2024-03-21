@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    alias(libs.plugins.com.google.dagger.hilt.android)

}

android {
    namespace = "com.example.actors"
    compileSdk = 34
    android.testOptions.unitTests.isIncludeAndroidResources = true


    defaultConfig {
        applicationId = "com.example.actors"
        minSdk = 31
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    android.testOptions.unitTests.isIncludeAndroidResources = true

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
    packaging {


        resources {
            merges.add("META-INF/gradle/incremental.annotation.processors")
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation ("com.squareup.okhttp3:logging-interceptor:4.5.0")
    implementation ("com.pierfrancescosoffritti.androidyoutubeplayer:core:12.1.0")

    implementation("io.github.ilyapavlovskii:youtubeplayer-compose:2024.02.25")
    implementation("androidx.media3:media3-exoplayer:1.2.1")
    implementation("androidx.media3:media3-exoplayer-dash:1.2.1")
    implementation("androidx.media3:media3-ui:1.2.1")
    implementation(project(":core:data"))
    implementation(project(":core:designsystem"))
    androidTestImplementation(project(":core:testing"))
    val lifecycle_version = "2.7.0"


    // Saved state module for ViewModel
    implementation(libs.com.google.android.material)
    implementation(libs.androidx.constraintlayout.constraintlayout.compose)



    implementation(libs.kotlinx.serialization)
    implementation(libs.retrofit)
    implementation(libs.retrofit.kotlinx.serialization.converter)


    implementation(libs.androidx.lifecycle.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation( libs.androidx.lifecycle.viewmodel.savedstate)
    implementation(libs.androidx.lifecycle.viewmodel.compose)


    implementation(libs.androidx.palette.palette.ktx)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.serialization)
    implementation(libs.androidx.compose.runtime.runtime.livedata)
    implementation(libs.coil)
    implementation(libs.androidx.paging.paging.compose)
    implementation(libs.androidx.paging.paging.runtime.ktx)



    testImplementation("com.google.dagger:hilt-android-testing:2.46.1")
    // ...with Kotlin.
    kaptTest("com.google.dagger:hilt-android-compiler:2.46.1")


    // For instrumented tests.
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.46.1")
    // ...with Kotlin.
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.46.1")

    // Room
    implementation(libs.androidx.room.room.runtime)
    implementation(libs.androidx.room.room.ktx)
    kapt(libs.androidx.room.room.compiler)

    // hilt
    implementation(libs.androidx.hilt.hilt.navigation.compose)
    implementation(libs.com.google.dagger.hilt.android)
    kapt(libs.com.google.dagger.hilt.android.compiler)

    //Navigation
    implementation(libs.androidx.navigation.navigation.compose)
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.navigation.navigaton.testing)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}