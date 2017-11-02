package net.rgielen.maven.web;

import org.junit.Test;

import static org.junit.Assert.*;

public class HelloSayerTest {

    @Test
    public void sayHelloReturnsProperGreeting() throws Exception {
        assertEquals("Hello!", new HelloSayer().sayHello());
    }
}