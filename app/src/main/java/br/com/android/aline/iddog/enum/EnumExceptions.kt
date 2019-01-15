package br.com.android.aline.iddog.enum

enum class EnumExceptions constructor(val code : Int) {
    INVALID_EMAIL(400),
    NOT_AUTHORIZED(401),
    INTERNAL_SERVER_ERROR(500),
    FORBIDDEN(403)


}