package com.example.springunittesting.unroll

import spock.lang.Specification
import spock.lang.Unroll


class SquareNumberTest extends Specification {

    @Unroll
    def "square of #number is #result"() {
        expect:
        number * number == result

        where:
        number | result
        1      | 1
        2      | 4
        3      | 9
        17     | 289
    }
}