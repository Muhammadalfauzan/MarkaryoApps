package com.example.makaryoapps.ui.recomended

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecomendedModel (val imageRec: Int, val nameBuilder : String,
    val skill: String, val nilaiRatting: Float, val icRatting: Int, val distance : Float, val address: String) : Parcelable