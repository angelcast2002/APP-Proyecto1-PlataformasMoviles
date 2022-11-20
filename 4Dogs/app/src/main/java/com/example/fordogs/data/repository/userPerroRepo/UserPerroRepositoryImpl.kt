package com.example.fordogs.data.repository.userPerroRepo

import com.example.fordogs.data.Resource
import com.example.fordogs.data.local.dao.userPerroInfo.UserPerroDao
import com.example.fordogs.data.local.entity.UserPerro
import com.example.fordogs.data.repository.Firestore.FirestoreRepository
import com.example.fordogs.data.repository.userPerroRepo.UserPerroRepoConstants.Companion.ERROR_GET_USER_PERRO_INFO
import com.example.fordogs.data.repository.userPerroRepo.UserPerroRepoConstants.Companion.ERROR_LOG_OUT_USER_PERRO_INFO
import com.example.fordogs.data.repository.userPerroRepo.UserPerroRepoConstants.Companion.ERROR_SET_USER_PERRO_INFO
import com.example.fordogs.data.repository.userPerroRepo.UserPerroRepoConstants.Companion.ERROR_UPDATE_USER_PERRO_INFO
import com.example.fordogs.data.repository.userPerroRepo.UserPerroRepoConstants.Companion.SUCCES_LOG_OUT_USER_PERRO_INFO
import com.example.fordogs.data.repository.userPerroRepo.UserPerroRepoConstants.Companion.SUCCES_SET_USER_PERRO_INFO
import com.example.fordogs.data.repository.userPerroRepo.UserPerroRepoConstants.Companion.SUCCES_UPDATE_USER_PERRO_INFO

class UserPerroRepositoryImpl (
    private val userPerroDao: UserPerroDao,
    private val firestoreRepository: FirestoreRepository
) : UserPerroRepository {
    override suspend fun getUserPerroInfo(): Resource<UserPerro> {
        return try {

            val localInfo = userPerroDao.getInfoForUserPerro()
            if (localInfo.id.isEmpty()) {
                val remoteInfo = firestoreRepository.getUserPerroInfo().data
                if (remoteInfo != null) {
                    userPerroDao.update(remoteInfo)
                    Resource.Success(data = remoteInfo)
                } else {
                    Resource.Error(message = ERROR_GET_USER_PERRO_INFO)
                }

            } else (
                Resource.Success(localInfo)
            )

        } catch (ex: Exception){
            Resource.Error(message = ERROR_GET_USER_PERRO_INFO)
        }
    }

    override suspend fun setUserPerroInfo(data: UserPerro): Resource<String> {
        return try {
            if(data.nombre == ""){
                Resource.Error(message = ERROR_SET_USER_PERRO_INFO)
            } else {
                userPerroDao.insertAll(data)
                Resource.Success(data = SUCCES_SET_USER_PERRO_INFO)
            }


        } catch (ex: Exception){
            Resource.Error(message = ERROR_SET_USER_PERRO_INFO)
        }

    }

    override suspend fun updateUserPerroInfo(data: UserPerro): Resource<String> {
        try{
            firestoreRepository.setUserPerroInfo(data)
        }catch (_: Exception){


        }
        return try {
            userPerroDao.update(data)
            Resource.Success(data = SUCCES_UPDATE_USER_PERRO_INFO)
        } catch (ex: Exception){
            Resource.Error(message = ERROR_UPDATE_USER_PERRO_INFO)
        }

    }

    override suspend fun logOut(data: UserPerro): Resource<String> {
        return try {
            userPerroDao.delete(data)
            Resource.Success(SUCCES_LOG_OUT_USER_PERRO_INFO)
        } catch (ex: Exception) {
            Resource.Error(ERROR_LOG_OUT_USER_PERRO_INFO)
        }
    }

}