package com.pro.dailysale.util.exception


import com.fasterxml.jackson.annotation.JsonFormat

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum class ErrorCode(
    val httpCode: Int,
    val msg: String
) {
    // 여기에 열거형 상수 정의
    CODE_200(200, "Success"),
    CODE_404(404, "존재하지 않는 데이터")

    ;

    companion object {
        fun getErrorCodeByCode(httpCode: Int): ErrorCode {
            return values().first { it.httpCode == httpCode }
        }
    }
}