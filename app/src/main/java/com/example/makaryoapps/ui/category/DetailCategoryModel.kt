package com.example.makaryoapps.ui.category

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailCategoryModel (val imageCrafts : Int, val nameCrafts : String, val ratting: Float, val skill1 : String, val imgStatus : Int, val status : String):
    Parcelable