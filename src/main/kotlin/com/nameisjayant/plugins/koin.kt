package com.nameisjayant.plugins

import com.nameisjayant.di.appModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin


fun Application.configureKoin(){
    install(Koin){
        modules(appModule)
    }
}