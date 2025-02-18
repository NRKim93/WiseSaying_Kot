package com.nrkimprogect.thegym.domain.dto


data class WiseSayingDto(
    val author: String,
//  val wiseSaying : String,  // 2025-02-17 코드리뷰 반영으로 인한 변수명 변경
    val content : String,

    //  2025-02-17 코드리뷰 반영으로 인한 초기화 방식 val id: Long? = null 에서 val id: Long = 0 변경
    //  id: Long? = null 로 진행하게 되면 불필요한 null체크가 진행될 수 있음. 그리고 이미 entity에서 자동채번 하므로
    //  entity에서 자동채번된 id 값이 적용될 것이기 때문에 val id : Long = 0으로 해도 무방
    val id: Long = 0

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true // 동일 객체면 true
        if (other !is WiseSayingDto) return false // 타입이 다르면 false

        return id == other.id // id 값 비교하여 동일 여부 판단
    }

    override fun hashCode(): Int {
        return id.hashCode() // ID 값만 해쉬 코드로 이용
    }

}
/*
    *  equals() && hashCode()를 오버라이드하면 id 값이 같은경우 결국은 같은 객체이기 때문에
    *  비교 대상을 명확히 할 수 있어, 불필요한 비교를 막을 수 있다.
    *
    *  Set 혹은 Map 같은 형태의 자료구조에서 중복을 방지할 수 있음
*/
