package org.konradkrakowiak.weatherapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.konradkrakowiak.weatherapp.BuildConfig
import org.konradkrakowiak.weatherapp.common.network.HOST
import org.konradkrakowiak.weatherapp.common.network.SCHEME
import org.konradkrakowiak.weatherapp.common.network.WeatherApiClient
import org.konradkrakowiak.weatherapp.common.network.WeatherApiClientImpl
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    internal fun provideRetrofit(builder: Retrofit.Builder) = builder.build()

    @Provides
    internal fun provideRetrofitBuilder(
            httpUrl: HttpUrl,
            okHttpClient: OkHttpClient,
            converterFactory: GsonConverterFactory,
            callAdapterFactory: RxJava2CallAdapterFactory
    ) = Retrofit.Builder()
            .baseUrl(httpUrl)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)

    @Provides
    internal fun provideHttpUrlBuilder() = HttpUrl.Builder()

    @Provides
    internal fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    internal fun provideRxCallAdapterFactory() = RxJava2CallAdapterFactory.create()

    @Provides
    internal fun provideConverterFactory(gson: Gson) = GsonConverterFactory.create(gson)

    @Provides
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val result = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) result.level = HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return result
    }

    @Provides
    internal fun provideGson(gsonBuilder: GsonBuilder) = gsonBuilder.create()

    @Provides
    internal fun provideGsonBuilder() = GsonBuilder()

    @Provides
    internal fun provideHttpUrl() = HttpUrl.Builder().scheme(SCHEME).host(HOST).build()

    @Provides
    internal fun weatherApiClient(weatherApiClient: WeatherApiClientImpl): WeatherApiClient = weatherApiClient
}

private const val TIMEOUT = 15L