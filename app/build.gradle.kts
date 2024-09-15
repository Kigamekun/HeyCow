plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.submissioncompose"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.submissioncompose"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("com.google.accompanist:accompanist-pager-indicators:0.24.12-rc")
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
    implementation("androidx.activity:activity-compose:1.9.1")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.compose.material3:material3-android:1.2.1")
    implementation("androidx.navigation:navigation-testing:2.7.7")
    implementation("androidx.compose.ui:ui-test-junit4-android:1.6.8")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("com.google.android.material:material:1.12.0")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    testImplementation("androidx.test:core:1.6.1")

    // AndroidX Test - JUnit
    androidTestImplementation("androidx.test.ext:junit:1.2.1")

    // AndroidX Test - Espresso
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    // AndroidX Test - Rules
    androidTestImplementation("androidx.test:rules:1.6.1")

    // AndroidX Test - Runner
    androidTestImplementation("androidx.test:runner:1.6.2")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.05.01"))
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    implementation("androidx.navigation:navigation-compose:2.7.7")
    androidTestImplementation("androidx.navigation:navigation-testing:2.7.7")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation("androidx.test.espresso:espresso-intents:3.6.1")
//    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.2.0")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
//    androidTestImplementation("androidx.test:runner:1.4.0")
//    androidTestImplementation("androidx.test:rules:1.4.0")


//    androidTestImplementation("androidx.navigation:navigation-testing:2.7.7")
//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.2.1")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
//    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
//    androidTestImplementation("androidx.compose.ui:ui-test-junit4")


}