package ru.kpfu.itis.water.managers

import ru.kpfu.itis.water.api.PostLoginForm
import ru.kpfu.itis.water.api.RestAPI
import rx.Observable

/**
 * Created by Melnikov Semyon on 03.06.18.
 * Higher School ITIS KFU
 */

class LoginManager(private val api: RestAPI = RestAPI()) {
    fun logIn(login: String, password: String): Observable<PostLoginForm> {
        return Observable.create {
            subscriber ->
            val callResponse = api.login(login, password)
            val response = callResponse.execute()

            if (response.isSuccessful) {
                val responseBody = response.body()
                val postLoginForm = PostLoginForm(
                        userId = responseBody.userId
                )
                subscriber.onNext(postLoginForm)
                subscriber.onCompleted()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}