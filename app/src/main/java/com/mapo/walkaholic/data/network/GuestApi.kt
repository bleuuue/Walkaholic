package com.mapo.walkaholic.data.network

import com.mapo.walkaholic.data.model.request.LoginRequestBody
import com.mapo.walkaholic.data.model.request.SignupRequestBody
import com.mapo.walkaholic.data.model.response.AuthResponse
import com.mapo.walkaholic.data.model.response.GuideInformationResponse
import com.mapo.walkaholic.data.model.response.TermResponse
import retrofit2.http.*

interface GuestApi {
    @Headers(
        "Accept:application/json, text/plain, */*",
        "Content-Type:application/json;charset=UTF-8"
    )
    @POST("signup")
    suspend fun register(
        @Body userSignUpDto: SignupRequestBody
    ): AuthResponse

    @GET("global/guide")
    suspend fun getTutorialFilenames(): GuideInformationResponse

    @GET("auth/term")
    suspend fun getTerm(): TermResponse

    @Headers(
        "Accept:application/json, text/plain, */*",
        "Content-Type:application/json;charset=UTF-8"
    )
    @POST("login")
    suspend fun login(
        @Body userLogin: LoginRequestBody
    ): AuthResponse

    /*
    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("accessToken") accessToken: String,
        @Field("accessTokenExpiresAt") accessTokenExpiresAt: String,
        @Field("refreshToken") refreshToken: String,
        @Field("refreshTokenExpiresAt") refreshTokenExpiresAt : String
    ): AuthResponse
     */
}