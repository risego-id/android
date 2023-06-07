package com.rg.capstone.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class User (
    val id: Int,
    val name: String,
    val email: String,
    val height: Int? = 0,
    val weight: Int? = 0,
    val gender: String? = "",
    val dob: String? = "",
    val age: Int? = 0,
): Parcelable