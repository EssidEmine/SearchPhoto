import projectconfig.AndroidConfig
import projectconfig.BuildConfig

plugins {
    id(Plugin.Id.ANDROID_APPLICATION)
    id(Plugin.Id.KOTLIN_ANDROID)
    id(Plugin.Id.DAGGER_HILT)
    kotlin(Plugin.Id.KAPT)
}

android {
    compileSdkVersion(AndroidConfig.COMPILE_SDK_VERSION)
    buildToolsVersion = BuildConfig.Versions.ANDROID_BUILD_TOOLS_VERSION

    defaultConfig {
        applicationId = "com.example.searchphoto"
        minSdkVersion(AndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(AndroidConfig.TARGET_SDK_VERSION)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()

    }
}

dependencies {


    implementation(LibraryDependency.kotlinStdlib)
    implementation(LibraryDependency.coreKtx)
    implementation(LibraryDependency.appcompat)
    implementation(LibraryDependency.kotlinCoroutines)
    implementation(LibraryDependency.kotlinCoroutinesAndroid)
    implementation(LibraryDependency.design)
    implementation(LibraryDependency.constraintlayout)
    implementation(LibraryDependency.activityKtx)
    implementation(LibraryDependency.fragmentKtx)
    implementation(LibraryDependency.recyclerview)
    implementation(LibraryDependency.picasso)
    implementation(LibraryDependency.Network.retrofit2)
    implementation(LibraryDependency.Test.junitParams)
    //room
    implementation(LibraryDependency.roomRuntime)
    implementation(LibraryDependency.roomKtx)
    kapt(LibraryDependency.roomCompiler)

    //navigation
    implementation(LibraryDependency.fragmentNavKtx)
    implementation(LibraryDependency.navigation)

    //hilt
    implementation(LibraryDependency.daggerHilt)
    kapt(LibraryDependency.daggerHiltCompiler)

    //Compose
    implementation("androidx.compose.ui:ui:1.2.0")
    // Integration with activities
    implementation("androidx.activity:activity-compose:1.5.1")
    // Compose Material Design
    implementation("androidx.compose.material:material:1.2.0")
    // Animations
    implementation("androidx.compose.animation:animation:1.2.0")
    // Tooling support (Previews, etc.)
    implementation("androidx.compose.ui:ui-tooling:1.2.0")
    // Integration with ViewModels
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
    //image loading
    implementation("io.coil-kt:coil-compose:2.0.0-rc01")
    //navigation
    implementation("androidx.navigation:navigation-compose:2.5.1")



    testImplementation(LibraryDependency.Test.junit)
    androidTestImplementation(LibraryDependency.Test.junitExt)
    androidTestImplementation(LibraryDependency.Test.espresso)
    testImplementation(LibraryDependency.Test.archCore)
    testImplementation(LibraryDependency.Test.archCoreTesting)
    testImplementation(LibraryDependency.Test.mockk)
    testImplementation(LibraryDependency.Test.mockkAndroid)
    testImplementation(LibraryDependency.Test.assertjCore)
    testImplementation(LibraryDependency.Test.kotlinCoroutinesTest)
    testImplementation(LibraryDependency.Test.kotlinCoroutinesTurbineTest)

    // region Projects implementation

    implementation(project(Modules.Repository.consultation))
    implementation(project(Modules.Repository.network))
    implementation(project(Modules.Model))

    // endregion

}