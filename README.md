
# Pokedex - NISUM

- Consumo de [Poké API](https://pokeapi.co/) para crear un "Pokedex".
- Visualizar la lista de pokemons.
- Guardar la lista de pokemons en `Room`.
- Hacer busqueda en la lista de pokemons por nombre o numero.
- Ingresar al detalle de un pokemon en particular `Habilidades`, `Movimientos`, `Location`, `Evolucion`

La app esta implementada sobre  [`Clean architecture`](https://developer.android.com/topic/architecture) usando [`JitPack Android`](https://jitpack.io/docs/ANDROID/) y
[`Basic Continuous Integration`](https://source.android.com/docs/setup/contribute/dashboard)


## Diseño UX/UI

| | | |
|:-------------------------:|:-------------------------:|:-------------------------:|
|<img alt="Screen 1" src="https://github.com/Crhystian27/Nisum-Pokedex/blob/master/screenshots/1.png"> | <img alt="Screen 2" src="https://github.com/Crhystian27/Nisum-Pokedex/blob/master/screenshots/2.png"> | <img alt="Screen 3" src="https://github.com/Crhystian27/Nisum-Pokedex/blob/master/screenshots/3.png"> |
|<img alt="Screen 4" src="https://github.com/Crhystian27/Nisum-Pokedex/blob/master/screenshots/4.png">  |  <img alt="Screen 5" src="https://github.com/Crhystian27/Nisum-Pokedex/blob/master/screenshots/5.png">|<img alt="Screen 6" src="https://github.com/Crhystian27/Nisum-Pokedex/blob/master/screenshots/6.png">|


## Acciones consumidas

- Pokemon:  [https://pokeapi.co/api/v2/pokemon/{id_or_name}/](https://pokeapi.co/api/v2/pokemon/charmander)
- Pokemon Species:  [https://pokeapi.co/api/v2/pokemon-species/{id_or_name}/](https://pokeapi.co/api/v2/pokemon-species/charmander)
- Pokemon Location Areas:  [https://pokeapi.co/api/v2/pokemon/{id_or_name}/encounters](https://pokeapi.co/api/v2/pokemon/charmander/encounters)
- Abilities:  [https://pokeapi.co/api/v2/ability/{id_or_name}/](https://pokeapi.co/api/v2/ability/charmander/)
- Evolution Chains:  [https://pokeapi.co/api/v2/evolution-chain/{id_or_name}/](https://pokeapi.co/api/v2/evolution-chain/2/)
- Image Pokemon:  [https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/{id}.png/](https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/4.png)


## DATA SOURCE

**LOCAL**

```kotlin
interface PokemonLocalDataSource {

    fun getPokemonListDB(): Flow<List<PokemonListEntity>>
    
    suspend fun savePokemonListToDB(
                    context: Context, 
                    pokemonList: PokemonListResponse): List<PokemonListPresentation>

}
```

**DATA BASE**

```kotlin
@Database(
    entities = [PokemonListEntity::class],
    version = 1,
    exportSchema = false
)

abstract class PokedexDataBase : RoomDatabase() {

    abstract val pokemonListDao : PokemonListDao
}
```

**REMOTE**

```kotlin
interface PokemonRemoteDataSource {

    suspend fun getRemotePokemonList(limit: String,offset: String): Response<PokemonListResponse>
    suspend fun getRemotePokemon(number: String): Response<PokemonResponse>
    suspend fun getRemoteSpecies(species: String): Response<SpeciesResponse>
    suspend fun getRemoteEvolution(number: String): Response<EvolutionResponse>
    suspend fun getRemoteLocation(number: String): Response<List<LocationResponse>>

}
```

## GRADLE

**CONFIG GRADLE**

```groovy
defaultConfig {
     applicationId "co.nisum.basicpokedex"
     minSdk 23
     targetSdk 33
     versionCode 1
     versionName "1.0"

     testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"

     buildConfigField "String", "BASE_URL", "\"https://pokeapi.co\""
     buildConfigField "String", "BASE_URL_IMG", "\"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork\""
     buildConfigField "String", "DATA_BASE_NAME", "\"pokedex_db\""
    }
```

**DEPENDENCIES GRADLE**

```groovy
dependencies {

    /*Standar */
    implementation 'androidx.core:core-ktx:1.9.0'
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.8.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.5'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'

    /*RxJava*/
    implementation 'io.reactivex.rxjava2:rxjava:2.2.17'
    implementation 'io.reactivex.rxjava2:rxandroid:2.1.1'
    implementation 'io.reactivex.rxjava2:rxkotlin:2.4.0'

    /*Retrofit*/
    implementation "com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2"
    implementation "com.squareup.retrofit2:retrofit:2.9.0"
    implementation "com.squareup.retrofit2:converter-gson:2.9.0"
    implementation "com.squareup.retrofit2:adapter-rxjava2:2.9.0"

    /*Gson*/
    implementation 'com.google.code.gson:gson:2.9.0'

    // Navigation
    implementation "androidx.navigation:navigation-fragment-ktx:$navigation_version"
    implementation "androidx.navigation:navigation-ui-ktx:$navigation_version"

    // Room
    implementation "androidx.room:room-runtime:2.5.0"
    implementation "androidx.room:room-ktx:2.5.0"
    kapt "androidx.room:room-compiler:2.5.0"

    //Hilt
    implementation "com.google.dagger:hilt-android:$dagger_hilt_version"
    kapt "com.google.dagger:hilt-compiler:$dagger_hilt_version"

    //Coroutines
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlin_coroutines"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlin_coroutines"

    //Glide
    implementation 'com.github.bumptech.glide:glide:4.14.2'

    //Palette
    implementation 'androidx.palette:palette:1.0.0'

    implementation 'org.apache.commons:commons-lang3:3.6'
}
```    
