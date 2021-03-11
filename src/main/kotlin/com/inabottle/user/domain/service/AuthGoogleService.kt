package com.inabottle.user.domain.service

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.gson.GsonFactory
import com.inabottle.user.domain.model.User
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono


@Service
class AuthGoogleService(private val userService: UserService) {


    fun authByToken(idToken: String): Mono<User> {
        val email = emailFromToken(idToken)
        val userFounded = userService.findByEmail(email)
        return userFounded.switchIfEmpty( userService.save(User(
                email = email
        )))
    }

    fun emailFromToken(idTokenString: String): String {
        val transport = NetHttpTransport()
        val jsonFactory: JsonFactory = GsonFactory()

        val verifier = GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(listOf(WEB, ANDROID))
                .build()

        val idToken = GoogleIdToken.parse(verifier.jsonFactory, idTokenString)
        val tokenIsValid = (idToken != null) && verifier.verify(idToken)

        println(tokenIsValid)

        //if (!tokenIsValid) {
       //     throw Exception()
        //}

        return idToken.payload.email

    }

    companion object {
        const val WEB = "42533182132-0bcbiuopigprf3ebcfm3m55ie7svv6ma.apps.googleusercontent.com"
        const val ANDROID = "1011585668218-ml27hn7vsqtcegejtgl441lk8bi979qm.apps.googleusercontent.com"
    }
}

