package com.ucodeacademy.day_01_simple_request_and_validation;

import org.hamcrest.MatcherAssert;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class HamcrestAssertion {

    @Test
    public void simpleHamcrestAssertion(){
        MatcherAssert.assertThat(10+10, Matchers.is(20));
        MatcherAssert.assertThat(5 + 4, Matchers.not(10));

        MatcherAssert.assertThat(10, Matchers.is(10));

        Assert.assertTrue(5 > 2);
        Assert.assertEquals(5 ,50);

    }
}
