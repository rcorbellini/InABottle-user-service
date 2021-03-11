package com.inabottle.user.infrastructure.repositories

import com.inabottle.user.domain.model.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.util.*

@Repository
interface UserRepository : ReactiveMongoRepository<User, UUID> {

    fun findByEmail(email: String): Mono<User>
}
