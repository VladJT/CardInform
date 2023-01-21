package jt.projects.cardinform.di

import dagger.Module
import dagger.Provides
import jt.projects.cardinform.BuildConfig
import jt.projects.cardinform.repos.remote.CardAPI
import jt.projects.cardinform.repos.remote.CardRepoRetrofitImpl
import jt.projects.cardinform.repos.remote.ICardRemoteRepository
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

    @Singleton
    @Provides
    fun cardRemoteRepo(
        api: CardAPI
    ): ICardRemoteRepository =
        CardRepoRetrofitImpl(api)

}