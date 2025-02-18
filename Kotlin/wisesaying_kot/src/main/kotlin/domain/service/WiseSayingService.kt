package com.nrkimprogect.thegym.domain.service

import com.nrkimprogect.thegym.common.exception.DeleteFailedException
import com.nrkimprogect.thegym.common.exception.WiseSayingNotFoundException
import com.nrkimprogect.thegym.domain.dto.WiseSayingDto
import com.nrkimprogect.thegym.domain.entity.WiseSaying
import com.nrkimprogect.thegym.domain.repository.WiseSayingRepository
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.stereotype.Service

@Service
class WiseSayingService (
    private val repository : WiseSayingRepository
) {
    fun addWiseSaying(dto: WiseSayingDto) : WiseSaying {
        val ws = WiseSaying(author = dto.author, content = dto.content)
        return repository.save(ws);
    }

    fun showWiseSayingList() : List<WiseSayingDto> {
        return repository.findAllByOrderByIdDesc().map { wiseSayings ->
            WiseSayingDto(
                author = wiseSayings.author,
                content = wiseSayings.content,
                id = wiseSayings.id
            )
        }
    }

    fun showWiseSayingDto(id:Long) : WiseSayingDto {
        val wiseSayingContent = repository.findById(id)
            .orElseThrow { NoSuchElementException("No wise saying found with id: $id") } // null을 지양해야 하기 때문에 error

        return WiseSayingDto(
            author = wiseSayingContent.author,
            content = wiseSayingContent.content,
        )
    }

    fun showWiseSaying(id:Long) : String {
        repository.findById(id).orElseThrow { WiseSayingNotFoundException(id) }
        return "200"
    }

    fun deleteById(id: Long) : String {
        repository.findById(id).orElseThrow { WiseSayingNotFoundException(id) } // 삭제 대상 명언 있는지 Select

        repository.deleteById(id) //  삭제

        return try {
            showWiseSaying(id)
            throw DeleteFailedException(id)

        } catch (ex : WiseSayingNotFoundException) {
            "200"
        }
    }

    fun editWiseSaying(dto: WiseSayingDto) : WiseSayingDto? {
        val existingWs = repository.findById(dto.id ?: return null)
            .orElseThrow { NoSuchElementException("No wise saying found with id: $id") } // null을 지양해야 하기 때문에 error

        val updateWs = existingWs.copy(
            author = dto.author,
            content = dto.content
        )
        repository.save(updateWs)

        return WiseSayingDto(
            author =  updateWs.author,
            content = updateWs.content,
        )
    }
}