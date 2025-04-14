package com.pro.dailysale.util.exception


import com.fasterxml.jackson.annotation.JsonFormat

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum class ErrorCode(
    val httpCode: Int,
    val msg: String
) {
    CODE_200(200, "Success"),
    CODE_404(404, "존재하지 않는 데이터"),
    CODE_403(403, "인증 정보가 없습니다."),

    // 구독 관련
    CODE_4000(404, "유효하지 않은 이메일 타입"),
    CODE_4001(404, "이미 구독중인 이메일입니다.")

    ;

    companion object {
        fun getErrorCodeByCode(httpCode: Int): ErrorCode {
            return values().first { it.httpCode == httpCode }
        }
    }
}