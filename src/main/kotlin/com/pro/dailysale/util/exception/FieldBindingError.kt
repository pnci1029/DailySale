package com.pro.dailysale.util.exception

data class FieldBindingError (
    val field: String,
    val defaultMessage: String?,
    val rejectedValue: Any?,
)
