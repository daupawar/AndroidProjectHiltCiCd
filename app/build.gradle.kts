plugins {
    id(Plugins.androidApplication)
//    id(Plugins.google)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinAndroidExtensions)
    id(Plugins.kapt)
//    id(Plugins.crashlytics)
    id(Plugins.hilt)
}

android {

    compileSdkVersion(AndroidSdk.compileSDK)

    defaultConfig {
        applicationId = "com.example.androidtemplatedsl"
        minSdkVersion(AndroidSdk.minSDK)
        targetSdkVersion(AndroidSdk.targetSDK)
        versionCode = Versions.playStoreCode
        multiDexEnabled = true
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    buildFeatures {
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    flavorDimensions("version")

    productFlavors {
        create("dev") {
            dimension("version")
            versionName =
                "${Versions.versionMajor}.${Versions.versionMinor}.${Versions.versionPatch}.dev${Versions.devCode}"
            applicationIdSuffix = ".dev"
        }
        create("staging") {
            dimension("version")
            versionName =
                "${Versions.versionMajor}.${Versions.versionMinor}.${Versions.versionPatch}.stage${Versions.stageCode}"
            applicationIdSuffix = ".stage"
        }
        create("beta") {
            dimension("version")
            versionName =
                "${Versions.versionMajor}.${Versions.versionMinor}.${Versions.versionPatch}.beta${Versions.betaCode}"
            applicationIdSuffix = ".beta"
        }
        create("production") {
            dimension("version")
            versionName =
                "${Versions.versionMajor}.${Versions.versionMinor}.${Versions.versionPatch}"
        }
    }
}

dependencies {
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.appCompat)
    implementation(Libraries.ktxCore)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.multidex)

    //Test
    testImplementation(TestLibraries.junit4)
//    testImplementation(TestLibraries.mockitoCore)
//    testImplementation(TestLibraries.mockitoInline)
    testImplementation(TestLibraries.mockitoKotlin)
    testImplementation(TestLibraries.okHttp)
    testImplementation(TestLibraries.core)
    testImplementation(TestLibraries.kotlinTest)
//    testImplementation("com.google.truth:truth:1.0.1")
    androidTestImplementation(TestLibraries.testRunner)
    androidTestImplementation(TestLibraries.espresso)

    api(Libraries.coroutineCore)
    testImplementation(TestLibraries.coroutineTest)

    //Hilt
    implementation(Libraries.hilt)
    implementation(Libraries.hiltLifecycle)
    kapt(Libraries.hiltCompilerAndroid)
    kapt(Libraries.hiltCompiler)

    //Retrofit
    implementation(Libraries.gson)
    implementation(Libraries.retrofit)
    implementation(Libraries.retrofitGsonConverter)
    implementation(Libraries.retrofitAdapter)
    implementation(Libraries.okLoggingInterceptor)

    //Room
    implementation(Libraries.room)
    implementation(Libraries.roomKtx)
    kapt(Libraries.roomCompiler)
    androidTestImplementation(TestLibraries.roomTesting)

    //Lifecycle
    implementation(Libraries.lifecycleViewModel)
    implementation(Libraries.lifecycleRuntime)
    implementation(Libraries.lifecycleExtension)
    implementation(Libraries.lifecycleLivedata)

    //RX
    implementation(Libraries.rxkotlin)
    implementation(Libraries.rxAndroid)

    implementation(Libraries.googleMaterial)
    implementation(Libraries.timber)

//    //firebase
//    implementation(Libraries.firebaseAnalytics)
//    implementation(Libraries.firebaseCrashlytics)
//    implementation(Libraries.firebaseMessaging)

    implementation(Libraries.securityPreferences)
    implementation(Libraries.navigationFragment)
    implementation(Libraries.navigationUi)
}
