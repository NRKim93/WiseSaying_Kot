package com.nrkimprogect.thegym.common.exception

class DeleteFailedException(id: Long) : RuntimeException("${id}번 명언 삭제에 실패했습니다."){
}