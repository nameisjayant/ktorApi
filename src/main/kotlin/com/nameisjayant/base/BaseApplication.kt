package com.nameisjayant.base

import com.nameisjayant.data.repository.UserRepositoryImp
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BaseApplication : KoinComponent {

    val userRepository by inject<UserRepositoryImp>()

}