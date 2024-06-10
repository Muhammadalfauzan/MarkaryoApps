package com.example.makaryoapps.ui.chat

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatModel(
    val image: Int, val name: String, val lastChat: String, val jam: String) : Parcelable
