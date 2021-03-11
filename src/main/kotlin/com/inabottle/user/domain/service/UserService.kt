package com.inabottle.user.domain.service

import com.inabottle.user.domain.model.TreasureHuntDetail
import com.inabottle.user.domain.model.TreasureHuntHistory
import com.inabottle.user.domain.model.User
import com.inabottle.user.infrastructure.repositories.UserRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserService(private val userRepository: UserRepository) {

    fun addTreasure(treasureHuntDetail: TreasureHuntDetail): Mono<User> {
        val userFounded = userRepository.findById(treasureHuntDetail.userCreateId)

        return userFounded.flatMap { user ->
            user.treasureHuntHistory.add(0, TreasureHuntHistory(id = treasureHuntDetail.id))
            userRepository.save(user)
        }


    }

    fun save(user: User): Mono<User> = userRepository.save(user)

    fun findByEmail(email: String): Mono<User> = userRepository.findByEmail(email)
}
