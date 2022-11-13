package com.ikrima.practice.dicoding.storyappdicoding.utilities.helper

import com.google.gson.Gson
import com.ikrima.practice.dicoding.storyappdicoding.data.responses.ApiGeneralErrorResponses
import retrofit2.Response

object HelperErrorResponses {

    fun getGeneralMsgError(response: Any) : Any {

        val test = response as Response<*>
        val data = test.errorBody()?.charStream()
        val gson = Gson()

        return gson.fromJson(data, ApiGeneralErrorResponses::class.java)

    }
}