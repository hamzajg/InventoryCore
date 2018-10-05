package com.hamzajg.inventory.core.domain.repository

import com.hamzajg.inventory.core.domain.model.User
import spock.lang.Specification

class UserRepositoryTest extends Specification {
    UserRepository userRepository = new InMemoryUserRepository()
    def 'should be able to save and load user'(){
        given:
        UUID uuid = UUID.randomUUID()
        and:
        User someUser = new User(uuid)
        and:
        someUser.activate()
        and:
        someUser.changeNicknameTo("Hamza")
        when:
        userRepository.save(someUser)
        and:
        User loadedUser = userRepository.find(uuid)
        then:
        loadedUser.isActivated()
        loadedUser.getNickname() == "Hamza"
    }
}
