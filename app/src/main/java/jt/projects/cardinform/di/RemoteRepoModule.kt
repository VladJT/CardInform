package jt.projects.gbpopularlibs.di

import dagger.Module
import dagger.Provides
import jt.projects.cardinform.BuildConfig
import jt.projects.cardinform.repos.CardAPI
import jt.projects.cardinform.repos.CardRepoRetrofitImpl
import jt.projects.cardinform.repos.ICardRemoteRepository
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class RemoteRepoModule {
    @Singleton
    @Provides
    fun api(): CardAPI = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build().create(CardAPI::class.java)

    @Provides
    fun cardRemoteRepo(
        api: CardAPI
    ): ICardRemoteRepository =
        CardRepoRetrofitImpl(api)

}