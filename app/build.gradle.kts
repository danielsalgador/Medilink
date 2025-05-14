import org.gradle.kotlin.dsl.implementation

val kotlin_version = "1.9.10" // Define la versión de Kotlin directamente aquí

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("kapt")
}

android {
    namespace = "com.example.proyectomedilink"
    compileSdk = 34 // ⚠️ Usa uno solo, no repitas `compileSdk`

    defaultConfig {
        applicationId = "com.example.proyectomedilink"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }

}

dependencies {
    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version") // Usando la variable de Kotlin

    // Jetpack Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Core Android y Jetpack
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)

    // Coil para imágenes
    implementation("io.coil-kt:coil-compose:2.4.0")

    // Retrofit + Gson + Logging
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.1")

    // Corrutinas
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.5.0")
    implementation("androidx.compose.material3:material3:1.1.1")
    implementation("io.coil-kt:coil-compose:2.3.0")


    // Lifecycle y ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

    // AppCompat y ConstraintLayout (opcional si solo usas Compose)
    implementation("androidx.appcompat:appcompat:1.6.1") // ✅ versión actualizada
    implementation("androidx.constraintlayout:constraintlayout:2.1.4") // ✅ versión actualizada

    // Room (base de datos local)
    implementation("androidx.room:room-runtime:2.5.0")
    kapt("androidx.room:room-compiler:2.5.0") // ⚠️ Cambiado de annotationProcessor a kapt
    implementation("androidx.room:room-ktx:2.5.0")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")

    // Ktor
    implementation("io.ktor:ktor-client-core:2.3.5")
    implementation("io.ktor:ktor-client-cio:2.3.5")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.5")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.5")
    implementation("io.ktor:ktor-client-logging:2.3.5")

    // Livedata + Compose runtime
    implementation ("androidx.compose.runtime:runtime-livedata:1.6.0")

    // Bibliotecas de fecha y hora
    implementation ("com.jakewharton.threetenabp:threetenabp:1.4.5")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")// Asegúrate de usar la última versión disponible

    // Dependencias adicionales

    implementation("com.google.dagger:hilt-android:2.44") // Hilt DI para inyección de dependencias
    kapt("com.google.dagger:hilt-compiler:2.44") // Compilador de Hilt
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.9.0") // Si usas RxJava



    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


}
