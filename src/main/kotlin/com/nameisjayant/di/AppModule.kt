package com.nameisjayant.di

import com.nameisjayant.data.repository.UserRepositoryImp
import com.nameisjayant.db.DatabaseConnection
import org.koin.dsl.bind
import org.koin.dsl.module


val appModule = module {
    single { DatabaseConnection() }
    factory { UserRepositoryImp(get()) } bind UserRepositoryImp::class
}