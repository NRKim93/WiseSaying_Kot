package com.nrkimprogect.thegym.domain.repository

import com.nrkimprogect.thegym.domain.dto.WiseSayingDto
import com.nrkimprogect.thegym.domain.entity.WiseSaying
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WiseSayingRepository : JpaRepository<WiseSaying, Long> {

    fun findAllByOrderByIdDesc() : List<WiseSayingDto>
}