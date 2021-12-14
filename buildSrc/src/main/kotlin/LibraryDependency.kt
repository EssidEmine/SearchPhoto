import projectconfig.BuildConfig.Versions

object LibraryDependency {

    // Kotlin
    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.KOTLIN_VERSION}"
    const val kotlinCoroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.COROUTINES}"
    const val kotlinCoroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.COROUTINES}"

    // Android

    const val coreKtx = "androidx.core:core-ktx:${Version.CORE_KTX}"
    const val appcompat = "androidx.appcompat:appcompat:${Version.APPCOMPAT}"
    const val design = "com.google.android.material:material:${Version.DESIGN}"
    const val constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Version.CONSTRAINTLAYOUT}"
    const val roomRuntime = "androidx.room:room-runtime:${Version.ROOM}"
    const val roomKtx = "androidx.room:room-ktx:${Version.ROOM}"
    const val roomCompiler = "androidx.room:room-compiler:${Version.ROOM}"
    const val daggerHiltPlugin =
        "com.google.dagger:hilt-android-gradle-plugin:${Version.DAGGER_HILT}"
    const val daggerHilt = "com.google.dagger:hilt-android:${Version.DAGGER_HILT}"
    const val daggerHiltCompiler = "com.google.dagger:hilt-android-compiler:${Version.DAGGER_HILT}"
    const val activityKtx = "androidx.activity:activity-ktx:${Version.ACTIVITY_KTX}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Version.RECYCLERVIEW}"
    const val picasso = "com.squareup.picasso:picasso:${Version.PICASSO}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Version.FRAGMENT_KTX}"

    object Network {
        const val retrofit2 = "com.squareup.retrofit2:retrofit:${Version.RETROFIT_2}"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Version.RETROFIT_2}"
    }

    object Test {
        const val junit = "junit:junit:${Version.JUNIT}"
        const val junitParams = "pl.pragmatists:JUnitParams:${Version.JUNIT_PARAMS}"

        const val junitExt = "androidx.test.ext:junit:${Version.JUNIT_EXT}"
        const val espresso = "androidx.test.espresso:espresso-core:${Version.ESPRESSO_CORE}"
        const val assertjCore = "org.assertj:assertj-core:${Version.ASSERTJ}"
        const val mockk = "io.mockk:mockk:${Version.MOCKK}"
        const val mockkAndroid = "io.mockk:mockk-android:${Version.MOCKK}"
        const val archCore = "androidx.arch.core:core-common:${Version.ARCHCORE}"
        const val archCoreTesting = "androidx.arch.core:core-testing:${Version.ARCHCORE}"
        const val kotlinCoroutinesTest =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.COROUTINES}"
    }

    object LifeCycle {
        const val process = "androidx.lifecycle:lifecycle-process:${Version.LIFECYCLE}"
        const val annotations = "androidx.lifecycle:lifecycle-compiler:${Version.LIFECYCLE}"
        const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.LIFECYCLE}"
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.LIFECYCLE}"
    }

    private object Version {
        const val RETROFIT_2 = "2.9.0"
        const val COROUTINES = "1.4.2"
        const val CORE_KTX = "1.6.0"
        const val DAGGER_HILT = "2.38.1"

        //Android
        const val APPCOMPAT = "1.3.0"
        const val DESIGN = "1.4.0"
        const val CONSTRAINTLAYOUT = "2.0.4"
        const val ROOM = "2.3.0"
        const val LIFECYCLE = "2.3.1"
        const val ACTIVITY_KTX = "1.2.3"
        const val RECYCLERVIEW = "1.1.0"
        const val PICASSO = "2.71828"
        const val FRAGMENT_KTX = "1.2.5"


        //Test
        const val JUNIT = "4.13.2"
        const val JUNIT_PARAMS = "1.1.1"
        const val JUNIT_EXT = "1.1.3"
        const val ESPRESSO_CORE = "3.4.0"
        const val ASSERTJ = "3.19.0"
        const val MOCKK = "1.11.0"
        const val ARCHCORE = "2.1.0"
    }

}