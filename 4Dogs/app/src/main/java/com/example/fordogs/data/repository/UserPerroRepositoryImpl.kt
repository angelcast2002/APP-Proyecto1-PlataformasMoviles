package com.example.fordogs.data.repository

import com.example.fordogs.data.Resource
import com.example.fordogs.data.local.dao.UserPerroDao
import com.example.fordogs.data.local.entity.userPerro
import com.example.fordogs.data.repository.UserPerroRepoConstants.Companion.ERROR_GET_USER_PERRO_INFO
import com.example.fordogs.data.repository.UserPerroRepoConstants.Companion.ERROR_LOG_OUT_USER_PERRO_INFO
import com.example.fordogs.data.repository.UserPerroRepoConstants.Companion.ERROR_SET_USER_PERRO_INFO
import com.example.fordogs.data.repository.UserPerroRepoConstants.Companion.ERROR_UPDATE_USER_PERRO_INFO
import com.example.fordogs.data.repository.UserPerroRepoConstants.Companion.SUCCES_LOG_OUT_USER_PERRO_INFO
import com.example.fordogs.data.repository.UserPerroRepoConstants.Companion.SUCCES_SET_USER_PERRO_INFO
import com.example.fordogs.data.repository.UserPerroRepoConstants.Companion.SUCCES_UPDATE_USER_PERRO_INFO

class UserPerroRepositoryImpl (
    private val userPerroDao: UserPerroDao,
    //API
) : UserPerroRepository {
    override suspend fun getUserPerroInfo(): Resource<userPerro> {
        return try {
            val localInfo = userPerroDao.getInfoForUserPerro()
            if (localInfo.id.isEmpty()) {
                return Resource.Error(message = ERROR_GET_USER_PERRO_INFO)
            } else (
                Resource.Succes(localInfo)
            )

        } catch (ex: Exception){
            Resource.Error(message = ERROR_GET_USER_PERRO_INFO)
        }
    }

    override suspend fun setUserPerroInfo(data: userPerro): Resource<String> {
        return try {
            userPerroDao.insertAll(data)
            Resource.Succes(data = SUCCES_SET_USER_PERRO_INFO)
        } catch (ex: Exception){
            Resource.Error(message = ERROR_SET_USER_PERRO_INFO)
        }
    }

    override suspend fun updateUserPerroInfo(data: userPerro): Resource<String> {
        return try {
            userPerroDao.update(data)
            Resource.Succes(data = SUCCES_UPDATE_USER_PERRO_INFO)
        } catch (ex: Exception){
            Resource.Error(message = ERROR_UPDATE_USER_PERRO_INFO)
        }
    }

    override suspend fun logOut(data: userPerro): Resource<String> {
        return try {
            userPerroDao.delete(data)
            Resource.Succes(SUCCES_LOG_OUT_USER_PERRO_INFO)
        } catch (ex: Exception) {
            Resource.Error(ERROR_LOG_OUT_USER_PERRO_INFO)
        }
    }

}