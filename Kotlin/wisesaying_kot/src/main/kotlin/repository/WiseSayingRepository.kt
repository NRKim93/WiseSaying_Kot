package com.nrkimprogect.thegym.repository

import com.nrkimprogect.thegym.dto.WiseSayingDto
import com.nrkimprogect.thegym.entity.WiseSaying
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WiseSayingRepository : JpaRepository<WiseSaying, Long> {

    fun findAllByOrderByIdDesc() : List<WiseSayingDto>
}