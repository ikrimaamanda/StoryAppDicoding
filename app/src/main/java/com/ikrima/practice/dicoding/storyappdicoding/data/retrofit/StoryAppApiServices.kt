package com.ikrima.practice.dicoding.storyappdicoding.data.retrofit

import com.ikrima.practice.dicoding.storyappdicoding.data.remote.UrlEndPoint
import com.ikrima.practice.dicoding.storyappdicoding.data.responses.LoginUserResponses
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface StoryAppApiServices {

    @FormUrlEncoded
    @POST(UrlEndPoint.login)
    fun loginUser(@Field("email") email : String,
                  @Field("password") password : String
    ) : Call<LoginUserResponses>

}