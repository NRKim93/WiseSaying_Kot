package com.nrkimprogect.thegym.common.exception

class WiseSayingNotFoundException(id: Long) : RuntimeException("${id}번 명언을 찾을 수 없습니다.") {
}
