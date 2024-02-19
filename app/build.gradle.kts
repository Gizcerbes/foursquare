import Dependency.androidX
import Dependency.appauth
import Dependency.comGoogle
import Dependency.daggerHilt
import Dependency.foursquare
import Dependency.ktor
import Dependency.okHttp
import Dependency.picasso
import Dependency.room

plugins {
	id("com.android.application")
	id("org.jetbrains.kotlin.android")
	id("com.google.dagger.hilt.android")
	//id("com.google.gms.google-services")

	id("kotlin-kapt")
}

android {
	namespace = "com.uogames.innowisefoursquare"
	compileSdk = 34

	defaultConfig {
		applicationId = "com.uogames.innowisefoursquare"
		minSdk = 26
		targetSdk = 34
		versionCode = 2
		versionName = "1.1"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"


		buildConfigField("String", "CLIENT_ID", Environments.API_CLIENT_ID)
		buildConfigField("String", "CLIENT_SECRET", Environments.API_CLIENT_SECRET)
		buildConfigField("String", "API_KEY", Environments.API_KEY)

		kapt {
			arguments {
				arg("room.schemaLocation", "$projectDir/schemas")
			}
		}
		manifestPlaceholders["appAuthRedirectScheme"] = "com.uogames.oauth"

	}

	signingConfigs {
		create("sign") {
			storeFile = file(Environments.STORE_FILE)
			storePassword = Environments.STORE_PASSWORD
			keyAlias = Environments.KEY_ALIAS
			keyPassword = Environments.KEY_PASSWORD
		}

	}

	buildTypes {
		release {
			isMinifyEnabled = true
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
			signingConfig = signingConfigs.getByName("sign")
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
		buildConfig = true
	}
}

tasks.register("version") {
	println(android.defaultConfig.versionName)
}

dependencies {

	androidX()
	comGoogle()
	okHttp()
	picasso()
	room()
	daggerHilt()
	ktor()
	appauth()

	testImplementation("junit:junit:4.13.2")
	androidTestImplementation("androidx.test.ext:junit:1.1.5")
	androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}