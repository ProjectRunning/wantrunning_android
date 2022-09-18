package com.openrun.core_network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val wantRunningAppDispatchers: WantRunningAppDispatchers)

enum class WantRunningAppDispatchers {
    IO
}