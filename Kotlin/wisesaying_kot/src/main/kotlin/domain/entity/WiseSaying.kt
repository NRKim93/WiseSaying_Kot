package com.nrkimprogect.thegym.domain.entity

import com.nrkimprogect.thegym.common.entity.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "wise_sayings")
data class WiseSaying (
    val author: String,
    // val wiseSaying: String // 2025-02-17 코드리뷰 반영으로 인한 변수명 변경
    val content: String
) : BaseEntity() // 2025-02-17 코드리뷰 반영 : BaseEntity에 id 박아두고, Persistable 구현
