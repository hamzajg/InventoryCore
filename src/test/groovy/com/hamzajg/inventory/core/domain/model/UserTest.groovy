package com.hamzajg.inventory.core.domain.model

import spock.lang.Specification

class UserTest extends Specification {

    User user = new User(UUID.randomUUID())

    def 'deactivated user cannot change nickname'() {
        given:
        user.deactivate()
        when:
        user.changeNicknameTo("Hamza")
        then:
        thrown(IllegalStateException)
    }

    def 'activated user can change nickname'() {
        given:
        user.activate()
        when:
        user.changeNicknameTo("Hamza")
        then:
        user.getNickname() == "Hamza"
    }

    def 'new user can be activated'() {

        when:
        user.activate()
        then:
        user.isActivated()
    }

    def 'activated user can be deactivated'() {

        given:
        user.activate()
        when:
        user.deactivate()
        then:
        user.isDeactivated()
    }

    def 'activated user cannot be activated'() {

        given:
        user.activate()
        when:
        user.activate()
        then:
        thrown(IllegalStateException)
    }

    def 'deactivated user cannot be deactivated'() {

        given:
        user.deactivate()
        when:
        user.deactivate()
        then:
        thrown(IllegalStateException)
    }
}
