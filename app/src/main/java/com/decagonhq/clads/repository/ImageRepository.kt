package com.decagonhq.clads.repository

import com.decagonhq.clads.data.domain.GenericResponseClass
import com.decagonhq.clads.data.domain.images.UserGalleryImage
import com.decagonhq.clads.data.domain.images.UserProfileImage
import com.decagonhq.clads.util.Resource
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface ImageRepository {

    suspend fun uploadMediaImage(image: MultipartBody.Part): Flow<Resource<UserProfileImage>>
    suspend fun getUserImage(): Flow<Resource<UserProfileImage>>
    suspend fun getGalleryImage(): Flow<Resource<List<UserGalleryImage>>>
    suspend fun uploadGallery(requestBody: RequestBody): Flow<Resource<List<UserGalleryImage>>>
}
