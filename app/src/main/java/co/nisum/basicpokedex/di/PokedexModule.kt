package co.nisum.basicpokedex.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import co.nisum.basicpokedex.BuildConfig
import co.nisum.basicpokedex.data.PokedexRepository
import co.nisum.basicpokedex.data.local.PokedexDataBase
import co.nisum.basicpokedex.data.local.dao.PokemonDao
import co.nisum.basicpokedex.data.remote.PokedexApiInterface
import co.nisum.basicpokedex.data.repository.dataSource.PokemonLocalDataSource
import co.nisum.basicpokedex.data.repository.dataSource.PokemonRemoteDataSource
import co.nisum.basicpokedex.data.repository.dataSourceImpl.PokemonLocalDataSourceImpl
import co.nisum.basicpokedex.data.repository.dataSourceImpl.PokemonRemoteDataSourceImpl
import co.nisum.basicpokedex.domain.interfaces.IPokedexRepository
import co.nisum.basicpokedex.domain.usescases.GetPokemonListUseCase
import co.nisum.basicpokedex.domain.usescases.GetPokemonUseCase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PokedexModule {


    @Provides
    @Singleton
    fun provideGetPokemonListUseCase(
        repository: PokedexRepository
    ) = GetPokemonListUseCase(repository)

    @Provides
    @Singleton
    fun provideGetPokemonUseCase(
        repository: PokedexRepository
    ) = GetPokemonUseCase(repository)

    @Provides
    @Singleton
    fun providePokemonListDao(db: PokedexDataBase) = db.pokemonListDao

    @Provides
    @Singleton
    fun providePokemonDao(db: PokedexDataBase) = db.pokemonDao


    @Provides
    @Singleton
    fun providePokedexDataBase(application: Application) =
        Room.databaseBuilder(application,
            PokedexDataBase::class.java, BuildConfig.DATA_BASE_NAME)
            .build()


    @Provides
    @Singleton
    fun providePokemonLocalDataSource(pokemonDao: PokemonDao)= PokemonLocalDataSourceImpl(pokemonDao)

    @Provides
    @Singleton
    fun providePokemonRemoteDataSource(pokedexApiInterface: PokedexApiInterface) = PokemonRemoteDataSourceImpl(pokedexApiInterface)


    @Provides
    @Singleton
    fun providePokedexRepository(
        pokemonLocalDataSource: PokemonLocalDataSourceImpl,
        pokemonRemoteDataSource: PokemonRemoteDataSourceImpl
    ) = PokedexRepository(pokemonLocalDataSource,pokemonRemoteDataSource)




    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

@Singleton
@Provides
fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
    OkHttpClient
        .Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()



    @Provides
    @Singleton
    fun providePokedexApi(
        okHttpClient: OkHttpClient): PokedexApiInterface =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(PokedexApiInterface::class.java)

}