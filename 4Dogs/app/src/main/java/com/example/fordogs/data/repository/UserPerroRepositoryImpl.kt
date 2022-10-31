package com.example.fordogs.data.repository

import com.example.fordogs.data.Resource
import com.example.fordogs.data.local.dao.UserPerroDao
import com.example.fordogs.data.local.entity.userPerro

class UserPerroRepositoryImpl (
    private val userPerroDao: UserPerroDao,
    //API
) : UserPerroRepository {
    override suspend fun getUserPerroInfo(): Resource<userPerro> {
        return try {
            val localInfo = userPerroDao.getInfoForUserPerro()
            Resource.Succes(localInfo)
        } catch (ex: Exception){
            Resource.Error(message = "No se han encontrado datos")
        }
    }

    override suspend fun setUserPerroInfo(data: userPerro): Resource<String> {
        return try {
            userPerroDao.insertAll(data)
            Resource.Succes(data = "Guardado con exito")
        } catch (ex: Exception){
            Resource.Error(message = "Error al guardar datos")
        }
    }

    override suspend fun updateUserPerroInfo(data: userPerro): Resource<String> {
        return try {
            userPerroDao.update(data)
            Resource.Succes(data = "Guardado con exito")
        } catch (ex: Exception){
            Resource.Error(message = "Error al guardar datos")
        }
    }

}