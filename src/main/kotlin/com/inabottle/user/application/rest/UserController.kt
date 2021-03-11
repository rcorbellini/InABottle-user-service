package com.inabottle.user.application.rest

import com.inabottle.user.domain.model.TreasureHuntDTO
import com.inabottle.user.domain.model.TreasureHuntDetail
import com.inabottle.user.domain.model.User
import com.inabottle.user.domain.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import java.util.*

@RestController
class UserController(private val userService: UserService) {

    @PostMapping("/user", produces = [MediaType.TEXT_EVENT_STREAM_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody user: User): Mono<User> = userService.save(user)

    @GetMapping("/user/{email}", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun findByEMail(@PathVariable("email") email: String): Mono<User> = userService.findByEmail(email)


    @PostMapping("/user/{userId}/treasure", produces = [MediaType.TEXT_EVENT_STREAM_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun addTreasure(@PathVariable("userId") userId : UUID,  @RequestBody treasureHuntDTO: TreasureHuntDTO): Mono<User> = userService.addTreasure(TreasureHuntDetail(id= treasureHuntDTO.id, userCreateId = userId))

}