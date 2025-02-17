plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    id("kotlin-parcelize") // Parcelize desteği için gerekli eklenti



}

android {
    namespace = "com.courage.newsapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.courage.newsapp"
        minSdk = 26
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.paging.common.android)
    implementation(libs.androidx.room.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation (libs.androidx.core.splashscreen)
    implementation ("androidx.compose.material3:material3:1.1.1") // Material 3 kütüphanesi

    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.30.1")
    implementation ("androidx.datastore:datastore-preferences:1.1.2")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation ("com.google.dagger:hilt-android:2.51.1")
    // Jetpack Compose için Hilt ViewModel Desteği
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
    kapt ("com.google.dagger:hilt-compiler:2.51.1")

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0") // JSON dönüşümü için

    implementation ("com.squareup.okhttp3:okhttp:4.10.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.10.0") // Loglama için
    implementation ("com.google.code.gson:gson:2.10.1")
    implementation ("io.coil-kt:coil:2.5.0")
    implementation ("io.coil-kt:coil-compose:2.5.0")
    implementation ("com.github.bumptech.glide:glide:4.16.0")

    // Paging 3
    implementation ("androidx.paging:paging-runtime:3.2.1")

    // Paging 3 Compose entegrasyonu
    implementation ("androidx.paging:paging-compose:3.2.1")

    // Temel Material Icons
    implementation ("androidx.compose.material:material-icons-core:1.5.4")

    // Genişletilmiş Material Icons (daha fazla ikon seçeneği)
    implementation ("androidx.compose.material:material-icons-extended:1.5.4")

    // Room kütüphanesi
    implementation ("androidx.room:room-runtime:2.6.0")
    kapt ("androidx.room:room-compiler:2.6.0") // Annotation processing için kapt
    implementation ("androidx.room:room-ktx:2.6.0") // Kotlin extensions ve Coroutine desteği

}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}