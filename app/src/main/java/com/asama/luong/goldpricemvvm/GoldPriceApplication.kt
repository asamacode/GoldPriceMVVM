package com.asama.luong.goldpricemvvm

import android.app.Application
import com.asama.luong.goldpricemvvm.data.db.AppDatabase
import com.asama.luong.goldpricemvvm.data.network.*
import com.asama.luong.goldpricemvvm.data.respository.MetalsRepository
import com.asama.luong.goldpricemvvm.data.respository.MetalsRepositoryImpl
import com.asama.luong.goldpricemvvm.ui.base.ViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class GoldPriceApplication : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@GoldPriceApplication))

        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { instance<AppDatabase>().metalsPriceDao() }

        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { APIGoldPriceService(instance()) }
        bind<MetalsPriceDataSource>() with singleton { MetalsPriceDataSourceImpl(instance()) }
        bind<MetalsRepository>() with singleton { MetalsRepositoryImpl(instance(), instance()) }
        bind() from singleton { ViewModelFactory(instance()) }
    }
}