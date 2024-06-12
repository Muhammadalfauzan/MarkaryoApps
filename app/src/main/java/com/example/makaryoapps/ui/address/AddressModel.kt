package com.example.makaryoapps.ui.address

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddressModel (val labelAlamat : String , val name: String, val complateAddress : String): Parcelable