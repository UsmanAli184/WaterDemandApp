package com.example.waterdemandapp.model.repositories

import com.example.waterdemandapp.model.utils.CloudinaryUploadHelper
import javax.inject.Inject

class StorageRepository @Inject constructor() {
    fun uploadFile(filePath:String,onComplete: (Boolean,String?) -> Unit){
        CloudinaryUploadHelper().uploadFile(filePath,onComplete)
    }
}