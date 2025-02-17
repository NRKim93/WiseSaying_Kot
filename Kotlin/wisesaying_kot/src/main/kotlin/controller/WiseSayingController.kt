package com.nrkimprogect.thegym.controller

import com.nrkimprogect.thegym.dto.WiseSayingDto
import com.nrkimprogect.thegym.service.WiseSayingService
import org.springframework.stereotype.Controller


@Controller
class WiseSayingController ( private val service: WiseSayingService ) {
    
    fun addWiseSaying() {
        print(" 명언 : ")
        var wiseSaying = readLine()?.trim() ?: ""
        print(" 작가 : ")
        var author = readLine()?.trim() ?: ""

        when {
            wiseSaying.isBlank() && author.isNotBlank() -> {
                println("명언을 입력해 주세요.")
            }

            wiseSaying.isNotBlank() && author.isBlank() -> {
                println("작가를 입력해 주세요.")
            }

            wiseSaying.isBlank() && author.isBlank() -> {
                println("명언과 작가를 둘 다 입력해 주세요.")
            }

            else -> {
                val dto = WiseSayingDto(author, wiseSaying)
                val rs= service.addWiseSaying(dto)
                println("${rs.id}번 명언이 등록되었습니다. ")
            }

        }

    }

    fun showWiseSayingList() {
        println("번호 / 작가 / 명언")
        println("--------------------------------------------")
        val rs = service.showWiseSayingList()

        rs.forEach() {aa ->
            println("${aa.id} / ${aa.author} / ${aa.wiseSaying} ")
        }
    }

    fun deleteWiseSaying() {
        print("? id = ")
        var id = readLine()?.toLongOrNull() ?: 0L
        var selRs = service.showWiseSaying(id)

        when(selRs) {
            "404" -> {
                println("${id}번 명언이 존재하지 않습니다. ")
            }
        }

        var delRs = service.deleteById(id)

        when (delRs) {
            "200" -> {
                println("${id}번 명언이 삭제되었습니다. ")
            }
            
            "500" -> {
                println("삭제중 에러 발생")
            }
        }
    }

    fun editWiseSaying() {
        print("? id = ")
        var id = readLine()?.toLongOrNull() ?: 0L
        var selRs = service.showWiseSaying(id)
        val selContent = service.showWiseSayingDto(id)

        when(selRs) {
            "200" -> {
                print(" 명언 (기존) :  ${selContent?.wiseSaying}")
                print(" 명언 : ")
                var wiseSaying = readLine()?.trim() ?: ""

                print(" 작가 (기존) :  ${selContent?.author}")
                print(" 작가 : ")
                var author = readLine()?.trim() ?: ""

                val dto = WiseSayingDto(author, wiseSaying,id);
                service.editWiseSaying(dto)

                println("${id}번 명언의 내용이 편집 되었습니다.")
            }

            "404" -> {
                println("${id}번 명언이 존재하지 않습니다. ")
            }
        }



    }
}