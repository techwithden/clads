package com.decagonhq.clads.data.remote

import com.decagonhq.clads.data.domain.GenericResponseClass
import com.decagonhq.clads.data.domain.images.UserGalleryImage
import com.decagonhq.clads.data.domain.images.UserProfileImage
import com.decagonhq.clads.data.domain.login.UserRole
import com.decagonhq.clads.data.domain.profile.UserProfile
import com.decagonhq.clads.data.remote.login.LoginCredentialsDTO
import com.decagonhq.clads.data.remote.profile.UserProfileDTO
import com.decagonhq.clads.data.remote.registration.UserRegistrationDTO
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part

interface ApiService {

    /*Register User */
    @POST("artisans/register")
    suspend fun registerUser(@Body user: UserRegistrationDTO): GenericResponseClass<UserProfile>

    /*Email Login*/
    @POST("login")
    suspend fun login(
        @Body loginCredentials: LoginCredentialsDTO
    ): GenericResponseClass<String>

    /*Google Login*/
    @POST("login/google")
    suspend fun googleLogin(
        @Body userRole: UserRole
    ): GenericResponseClass<String>

    /*Upload Profile Picture*/
    @Multipart
    @POST("upload")
    suspend fun uploadImage(@Part image: MultipartBody.Part): GenericResponseClass<UserProfileImage>

    @GET("download")
    fun getUploadedImage(): GenericResponseClass<UserProfileImage>

    /*Get User Profile*/
    @GET("me/profile")
    suspend fun getUserProfile(): GenericResponseClass<UserProfile>

    /*Update User Profile*/
    @PUT("me/profile")
    suspend fun updateUserProfile(@Body userProfile: UserProfileDTO): GenericResponseClass<UserProfile>

    @POST("upload")
    suspend fun uploadGallery(@Body requestBody: RequestBody): GenericResponseClass<UserGalleryImage>

    @GET("download/images")
    fun getGalleryImages(): GenericResponseClass<List<UserGalleryImage>>

}
