package com.ikrima.practice.dicoding.storyappdicoding.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ikrima.practice.dicoding.storyappdicoding.data.responses.ApiGeneralErrorResponses
import com.ikrima.practice.dicoding.storyappdicoding.data.responses.LoginUserResponses
import com.ikrima.practice.dicoding.storyappdicoding.data.retrofit.StoryAppApiServices
import com.ikrima.practice.dicoding.storyappdicoding.utilities.helper.HelperErrorResponses
import com.ikrima.practice.dicoding.storyappdicoding.utilities.helper.ResultWrapper
import com.ikrima.practice.dicoding.storyappdicoding.utilities.sharedpreference.Constant
import com.ikrima.practice.dicoding.storyappdicoding.utilities.sharedpreference.PreferencesHelper
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection
import kotlin.coroutines.CoroutineContext

class LoginViewModel : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main

    private lateinit var service : StoryAppApiServices
    private lateinit var sharedPref : PreferencesHelper

    private val _responseLoginUser = MutableLiveData<ResultWrapper<Any>>()
    val responseLoginUser : LiveData<ResultWrapper<Any>> = _responseLoginUser

    init {
        _responseLoginUser.value = ResultWrapper.default()
    }

    fun setService(service : StoryAppApiServices) {
        this.service = service
    }

    fun setSharedPref(sharedPref : PreferencesHelper) {
        this.sharedPref = sharedPref
    }



    /*
    * Save some data of user
    * */
    private fun saveSessionUser(token: String?, userId: String?, name: String?) {
        sharedPref.putValueString(Constant.prefUserToken, token?:"")
        sharedPref.putValueString(Constant.prefUserId, userId?:"")
        sharedPref.putValueString(Constant.prefAccountName, name?:"")
        sharedPref.putValueBoolean(Constant.prefIsLogin, true)
    }

    fun loginUser(email : String, password : String) {
        _responseLoginUser.value = ResultWrapper.loading()
        launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    service.loginUser(email, password)
                }

                result.enqueue(object : Callback<LoginUserResponses> {
                    override fun onResponse(
                        call: Call<LoginUserResponses>,
                        response: Response<LoginUserResponses>
                    ) {
                        if (response.isSuccessful) {
                            if (response.code() == HttpURLConnection.HTTP_OK) {
                                response.body().let {
                                    if (it != null) {
                                        val responseApi = it.loginResult
                                        responseApi.let { loginResponse ->
                                            saveSessionUser(loginResponse.token, loginResponse.userId, loginResponse.name)
                                        }
                                        _responseLoginUser.value = ResultWrapper.success(responseApi)
                                    } else {
                                        _responseLoginUser.value = ResultWrapper.empty("")
                                    }
                                }
                            }
                            Log.e("successLogin", response.body().toString())

                        } else {
                            val helperError = HelperErrorResponses.getGeneralMsgError(response)
                            val responseError = helperError as ApiGeneralErrorResponses

                            _responseLoginUser.value = ResultWrapper.fail(title = response.code().toString(), message = responseError.message)

                            Log.e("codeFailedLogin", response.code().toString())
                            Log.e("failedLogin", response.message())
                        }
                    }

                    override fun onFailure(call: Call<LoginUserResponses>, t: Throwable) {
                        Log.e("failureLogin", "onResponse: ${t.localizedMessage}")
                    }

                })
            } catch (e : Throwable) {
                Log.e("errorLoginUser", "Msg : ${e.localizedMessage}")
            }
        }
    }

}