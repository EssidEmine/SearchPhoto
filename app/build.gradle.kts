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
    buildToolsVersion(BuildConfig.Versions.ANDROID_BUILD_TOOLS_VERSION)

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
    implementation (LibraryDependency.fragmentKtx)
    implementation(LibraryDependency.recyclerview)
    implementation(LibraryDependency.picasso)
    implementation(LibraryDependency.Network.retrofit2)
    implementation(LibraryDependency.Test.junitParams)

    implementation(LibraryDependency.roomRuntime)
    implementation(LibraryDependency.roomKtx)
    kapt(LibraryDependency.roomCompiler)

    implementation (LibraryDependency.fragmentNavKtx)
    implementation(LibraryDependency.navigation)

    implementation(LibraryDependency.daggerHilt)
    kapt(LibraryDependency.daggerHiltCompiler)

    testImplementation(LibraryDependency.Test.junit)
    androidTestImplementation(LibraryDependency.Test.junitExt)
    androidTestImplementation(LibraryDependency.Test.espresso)
    testImplementation(LibraryDependency.Test.archCore)
    testImplementation(LibraryDependency.Test.archCoreTesting)
    testImplementation(LibraryDependency.Test.mockk)
    testImplementation(LibraryDependency.Test.mockkAndroid)
    testImplementation(LibraryDependency.Test.assertjCore)
    testImplementation(LibraryDependency.Test.kotlinCoroutinesTest)
    testImplementation (LibraryDependency.Test.kotlinCoroutinesTurbineTest)

    // region Projects implementation

    implementation(project(Modules.Repository.consultation))
    implementation(project(Modules.Repository.network))
    implementation(project(Modules.Model))

    // endregion

}