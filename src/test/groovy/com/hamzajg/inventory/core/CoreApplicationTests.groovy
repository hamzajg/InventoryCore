package com.hamzajg.inventory.core;

import spock.lang.Specification;

class FirstTest extends Specification {

    def 'fist test'() {
        given:
            System.out.println("First Test Given")
        when:
        System.out.println("First Test When")
        then:
            System.out.println("First Test Then")
    }
}
