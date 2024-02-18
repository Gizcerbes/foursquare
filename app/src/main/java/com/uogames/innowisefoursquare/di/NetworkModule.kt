package com.uogames.innowisefoursquare.di

import android.content.Context
import com.squareup.picasso.LruCache
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import com.uogames.innowisefoursquare.BuildConfig
import com.uogames.innowisefoursquare.Config
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.bearerAuth
import io.ktor.serialization.gson.gson
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.io.File
import javax.inject.Named
import javax.inject.Singleton


private const val CACHE_FOLDER = "okhttp"

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

	private val BASE_URL_V3 = "https://api.foursquare.com/v3/places/"
	private val BASE_URL_V2 = "https://api.foursquare.com/v2/"

	@Singleton
	@Provides
	fun provideOkHttp(@ApplicationContext context: Context): OkHttpClient = OkHttpClient()
		.newBuilder()
		.cache(Cache(File(context.cacheDir, CACHE_FOLDER), 1000 * 1024 * 1024))
		.addInterceptor(Interceptor() { chain ->
			chain.request().newBuilder().apply {
				//addHeader("Authorization", BuildConfig.API_KEY)
			}.build().let { chain.proceed(it) }
		}
		).build()


	@Singleton
	@Named("place_api")
	@Provides
	fun provideSearchKtorClient(okHttp: OkHttpClient): HttpClient = HttpClient(OkHttp) {
		engine { preconfigured = okHttp }
		install(DefaultRequest) {
			this.headers["Authorization"] = BuildConfig.API_KEY
			url(BASE_URL_V3)
		}
		install(ContentNegotiation) { gson() }
		if (BuildConfig.DEBUG) install(Logging) {
			logger = Logger.SIMPLE
			level = LogLevel.ALL
		}
	}

	@Singleton
	@Named("user_api")
	@Provides
	fun provideUserKtorClient(okHttp: OkHttpClient): HttpClient = HttpClient(OkHttp) {
		engine { preconfigured = okHttp }
		install(DefaultRequest) {
			Config.accessToken.value?.let { bearerAuth(it) }
			url(BASE_URL_V2)
		}
		install(ContentNegotiation) { gson() }
		if (BuildConfig.DEBUG) install(Logging) {
			logger = Logger.SIMPLE
			level = LogLevel.ALL
		}
	}

	@Singleton
	@Provides
	fun picasso(@ApplicationContext context: Context, okHttp: OkHttpClient): Picasso = Picasso
		.Builder(context)
		.downloader(OkHttp3Downloader(okHttp))
		.memoryCache(LruCache(context))
		.loggingEnabled(BuildConfig.DEBUG)
		.build()

}