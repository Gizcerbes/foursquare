import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependency {

	private fun DependencyHandler.implementation(any: Any) = add("implementation", any)

	private fun DependencyHandler.testImplementation(any: Any) = add("testImplementation", any)

	private fun DependencyHandler.androidTestImplementation(any: Any) = add("androidTestImplementation", any)

	private fun DependencyHandler.kapt(any: Any) = add("kapt", any)

	private fun DependencyHandler.runtimeOnly(any: Any) = add("runtimeOnly", any)

	private fun DependencyHandler.compile(any: Any) = add("compile", any)

	fun DependencyHandler.androidX() {
		implementation("androidx.core:core-ktx:1.12.0")
		implementation("androidx.appcompat:appcompat:1.6.1")
		implementation("androidx.constraintlayout:constraintlayout:2.1.4")
		implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
		implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
		implementation("androidx.palette:palette-ktx:1.0.0")
		implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
		implementation("androidx.core:core-splashscreen:1.0.1")
		implementation("androidx.security:security-crypto:1.0.0")

	}

	fun DependencyHandler.comGoogle() {
		implementation("com.google.android.material:material:1.11.0")
	}

	fun DependencyHandler.okHttp() {
		implementation(platform("com.squareup.okhttp3:okhttp-bom:4.12.0"))
		implementation("com.squareup.okhttp3:okhttp")
		implementation("com.squareup.okhttp3:logging-interceptor")
	}

	fun DependencyHandler.picasso() {
		implementation("com.squareup.picasso:picasso:2.8")
	}

	fun DependencyHandler.room() {
		implementation("androidx.room:room-runtime:2.6.1")
		kapt("androidx.room:room-compiler:2.6.1")
		implementation("androidx.room:room-ktx:2.6.1")
		testImplementation("androidx.room:room-testing:2.6.1")
	}

	fun DependencyHandler.daggerHilt() {
		implementation("com.google.dagger:hilt-android:2.50")
		kapt("com.google.dagger:hilt-compiler:2.50")
	}

	fun DependencyHandler.ktor() {
		implementation("io.ktor:ktor-client-core:2.3.7")
		implementation("io.ktor:ktor-client-okhttp:2.3.7")
		implementation("io.ktor:ktor-client-content-negotiation:2.3.7")
		implementation("io.ktor:ktor-client-logging:2.3.7")
		implementation("io.ktor:ktor-serialization-gson:2.3.7")
	}

	fun DependencyHandler.foursquare() {
		implementation("com.foursquare:foursquare-android-oauth:1.1.1")
	}

	fun DependencyHandler.appauth() {
		implementation("net.openid:appauth:0.11.1")
	}



}