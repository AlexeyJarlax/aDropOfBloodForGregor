plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.pavlovalexey.adropofbloodforgregor"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.pavlovalexey.adropofbloodforgregor"
        minSdk = 26
        targetSdk = 35
        versionCode = 7
        versionName = "1.07"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            // proguardRules
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.8"
    }
}

dependencies {
    // Базовые
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.activity.compose)

    // Compose BOM и Material3
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.generativeai)
    implementation(libs.material3)
    implementation(libs.androidx.material.icons.extended)

    // Предпросмотр
//    debugImplementation(libs.ui.tooling)
//    debugImplementation(libs.ui.test.manifest)
//    androidTestImplementation(libs.ui.test.junit4)

    // Dagger Hilt + KSP
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // Navigation
    implementation(libs.androidx.hilt.navigation.compose)

    // Coil для загрузки изображений
    implementation(libs.coil.compose)

    // YAML, okhttp, API
    implementation (libs.jackson.module.kotlin)
    implementation (libs.jackson.dataformat.yaml)
    implementation (libs.okhttp)
    implementation (libs.kotlinx.coroutines.android)

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.11.0")
    // 3) Тестовые
//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.1.5")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}