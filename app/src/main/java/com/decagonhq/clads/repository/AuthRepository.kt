package com.decagonhq.clads.repository

import com.decagonhq.clads.data.domain.login.EmailLoginSuccessResponse
import com.decagonhq.clads.data.domain.login.LoginCredentials
import com.decagonhq.clads.data.domain.profileimage.UserProfileImage
import com.decagonhq.clads.data.domain.profileimage.UserProfileImageResponse
import com.decagonhq.clads.data.domain.registration.UserRegSuccessResponse
import com.decagonhq.clads.data.domain.registration.UserRegistration
import com.decagonhq.clads.util.Resource
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface AuthRepository {
    suspend fun registerUser(user: UserRegistration): Flow<Resource<UserRegSuccessResponse>>
    suspend fun loginUser(loginCredentials: LoginCredentials): Flow<Resource<EmailLoginSuccessResponse>>
    suspend fun userProfileImage(userProfileImage: MultipartBody.Part): Flow<Resource<UserProfileImageResponse>>

}
