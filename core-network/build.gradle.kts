import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import projectconfig.BuildConfig.Versions

plugins {
    id(Plugin.Id.ANDROID_LIBRARY)
    id(Plugin.Id.KOTLIN_ANDROID)
    id(Plugin.Id.DAGGER_HILT)
    kotlin(Plugin.Id.KAPT)
}

android {
    compileSdkVersion(projectconfig.AndroidConfig.COMPILE_SDK_VERSION)
    buildToolsVersion(Versions.ANDROID_BUILD_TOOLS_VERSION)

    defaultConfig {
        minSdkVersion(projectconfig.AndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(projectconfig.AndroidConfig.TARGET_SDK_VERSION)
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


repositories {
    mavenCentral()
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
}

dependencies {

    implementation(LibraryDependency.kotlinStdlib)
    implementation(LibraryDependency.kotlinCoroutines)
    implementation(LibraryDependency.kotlinCoroutinesAndroid)

    implementation(LibraryDependency.Network.retrofit2)
    implementation(LibraryDependency.Network.gsonConverter)

    implementation(LibraryDependency.daggerHilt)
    kapt(LibraryDependency.daggerHiltCompiler)

    // region Projects implementation

    implementation(project(Modules.Model))

// endregion
}