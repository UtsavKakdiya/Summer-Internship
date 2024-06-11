import org.jetbrains.kotlin.ir.backend.js.compile

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.summer_internship"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.summer_internship"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

//    configurations.all {
//        resolutionStrategy {
//            force("androidx.core:core:1.13.1")
//        }
//    }
}


dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
//    implementation("androidx.compose.material:material:1.6.0")
//    implementation("com.google.android.material:material:1.10.0")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
//    implementation("com.github.sephiroth74:RangeSeekBar:1.1.0")
    implementation("it.sephiroth.android.library.rangeseekbar:rangeseekbar:1.1.0"){
        exclude("com.android.support")
//        exclude group: 'com.android.support'
    }
    implementation ("com.github.Jay-Goo:RangeSeekBar:v3.0.0") {
        exclude("com.android.support")
    }

    implementation("com.github.lzyzsd:circleprogress:1.2.1") {
        exclude("com.android.support")
    }

    implementation("androidx.core:core-splashscreen:1.2.0-alpha01")
    implementation("androidx.viewpager2:viewpager2:1.1.0")
    implementation("androidx.fragment:fragment-ktx:1.3.6")
}