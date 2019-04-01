package com.dionisiofilho.sicoob.application.bases

abstract class BasePresenterCallback<C> {

    abstract fun onSuccess(response: C)
    abstract fun onError(throwable: Throwable)
}