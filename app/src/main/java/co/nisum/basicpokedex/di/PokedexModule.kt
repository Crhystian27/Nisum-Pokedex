package co.nisum.basicpokedex.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import co.nisum.basicpokedex.BuildConfig
import co.nisum.basicpokedex.data.PokedexRepository
import co.nisum.basicpokedex.data.local.PokedexDataBase
import co.nisum.basicpokedex.data.local.dao.PokemonListDao
import co.nisum.basicpokedex.data.remote.PokedexApiInterface
import co.nisum.basicpokedex.data.repository.dataSourceImpl.PokemonLocalDataSourceImpl
import co.nisum.basicpokedex.data.repository.dataSourceImpl.PokemonRemoteDataSourceImpl
import co.nisum.basicpokedex.domain.usescases.*
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
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PokedexModule {

    @Provides
    @Singleton
    fun provideGetEvolutionUseCase(
        repository: PokedexRepository
    )= GetEvolutionUseCase(repository)

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
    fun provideGetLocationUseCase(
        repository: PokedexRepository
    )= GetLocationListUseCase(repository)

    @Provides
    @Singleton
    fun providePokemonListDao(db: PokedexDataBase) = db.pokemonListDao


    @Provides
    @Singleton
    fun providePokedexDataBase(application: Application) =
        Room.databaseBuilder(application,
            PokedexDataBase::class.java, BuildConfig.DATA_BASE_NAME)
            .build()


    @Provides
    @Singleton
    fun providePokemonLocalDataSource(
        pokemonListDao: PokemonListDao)= PokemonLocalDataSourceImpl(pokemonListDao)

    @Provides
    @Singleton
    fun providePokemonRemoteDataSource(pokedexApiInterface: PokedexApiInterface) = PokemonRemoteDataSourceImpl(pokedexApiInterface)


    @Provides
    @Singleton
    fun providePokedexRepository(
        @ApplicationContext context: Context,
        pokemonLocalDataSource: PokemonLocalDataSourceImpl,
        pokemonRemoteDataSource: PokemonRemoteDataSourceImpl
    ) = PokedexRepository(context,pokemonLocalDataSource,pokemonRemoteDataSource)


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