package com.inabottle.user.application.rest

import com.inabottle.user.domain.model.User
import com.inabottle.user.domain.service.AuthGoogleService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class LoginController(private val authService: AuthGoogleService) {

    @PostMapping("/auth/google", produces = [MediaType.TEXT_EVENT_STREAM_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.OK)
    fun googleAuth(@RequestBody token: String): Mono<User>  = authService.authByToken(token)
}