package com.nrkimprogect.thegym.service

import com.nrkimprogect.thegym.dto.WiseSayingDto
import com.nrkimprogect.thegym.entity.WiseSaying
import com.nrkimprogect.thegym.repository.WiseSayingRepository
import org.springframework.stereotype.Service

@Service
class WiseSayingService (
    private val repository : WiseSayingRepository
) {
    fun addWiseSaying(dto: WiseSayingDto) : WiseSayingDto {
        val ws = WiseSaying(author = dto.author, wiseSaying = dto.wiseSaying)
        repository.save(ws)

        return WiseSayingDto(
            author =  ws.author,
            wiseSaying = ws.wiseSaying,
            id = ws.id
        )
    }

    fun showWiseSayingList() : List<WiseSayingDto> {
        return repository.findAllByOrderByIdDesc().map { wiseSayings ->
            WiseSayingDto(
                author = wiseSayings.author,
                wiseSaying = wiseSayings.wiseSaying,
                id = wiseSayings.id
            )
        }
    }

    fun showWiseSayingDto(id:Long) : WiseSayingDto? {
        val wiseSayingContent = repository.findById(id).orElse(null) ?: return null

        return WiseSayingDto(
            author = wiseSayingContent.author,
            wiseSaying = wiseSayingContent.wiseSaying,
            id = wiseSayingContent.id
        )
    }

    fun showWiseSaying(id:Long) : String {
        repository.findById(id).orElse(null) ?: return "404"

        return "200"
    }

    fun deleteById(id: Long) : String {
            repository.deleteById(id)

            val ver = showWiseSaying(id)
            if(ver == "200") {
                //  삭제 처리 후 삭제 대상 id 통해 다시 조회 했는데 나온다? error
                return "500"
            } else {
                //  삭제 처리 후 삭제 대상  id 통해 다시 조회 했는데 안나온다? 삭제됨
                return "200"
            }
    }

    fun editWiseSaying(dto: WiseSayingDto) : WiseSayingDto? {
        val existingWs = repository.findById(dto.id ?: return null).orElse(null) ?: return null  // 기존 데이터 조회

        val updateWs = existingWs.copy(
            author = dto.author,
            wiseSaying = dto.wiseSaying
        )
        repository.save(updateWs)

        return WiseSayingDto(
            author =  updateWs.author,
            wiseSaying = updateWs.wiseSaying,
            id = updateWs.id
        )
    }
}