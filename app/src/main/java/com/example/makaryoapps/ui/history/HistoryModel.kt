package com.example.makaryoapps.ui.history

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class HistoryModel (
   val imageBuilder: Int, val nameBuilder: String,val ratting :String,val statusOrder:String,val date: String,
) : Parcelable