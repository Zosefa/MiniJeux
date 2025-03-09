plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.minijeux"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.minijeux"
        minSdk = 21
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
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")

    // Composants Material Design pour un meilleur UI
    implementation("com.google.android.material:material:1.10.0")

    // RecyclerView pour afficher la liste des jeux
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    // Glide pour charger et afficher les images facilement
    implementation("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.16.0")

    // Gestion des contraintes pour le layout
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // JUnit pour les tests unitaires
    testImplementation("junit:junit:4.13.2")

    // Test UI (Instrumented tests)
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}