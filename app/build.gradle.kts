plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.rama.quotes"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.rama.quotes"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        release {
            buildConfigField(
                "String",
                "API_URL",
                "\"https://quotes-api.raul-marquez.dev/\""
            )
            buildConfigField("String", "AUTH_SECRET", "\"ht3fiOMSusOGVO55mA4gzIJ5VWOU0NlW\"")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            buildConfigField(
                "String",
                "API_URL",
                "\"http://10.0.2.2:1988/\""
            )

            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
            signingConfig = signingConfigs.getByName("debug")

        }

        create("local") {
            matchingFallbacks += "release"
            matchingFallbacks += "debug"

            buildConfigField(
                "String",
                "API_URL",
                "\"http://10.0.2.2:1988/\""
            )

            isMinifyEnabled = false
            isDebuggable = true
            applicationIdSuffix = ".local"
            versionNameSuffix = "-local"
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
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.ui.desktop)

    // Room
    implementation(libs.androidx.room.runtime)
    implementation (libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // Retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation(libs.logging.interceptor)

    // Kotlin Standard Library
    implementation (libs.kotlin.stdlib)

    // Coroutines for Retrofit
    implementation (libs.kotlinx.coroutines.core)
    implementation (libs.kotlinx.coroutines.android)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}