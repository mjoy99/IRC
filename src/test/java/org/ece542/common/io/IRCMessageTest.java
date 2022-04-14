package org.ece542.common.io;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class IRCMessageTest {

    @BeforeEach
    void setUp() {



    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getPost() throws UnsupportedEncodingException {
/*
        byte[] flag = {0};
        byte[] temp = "test".getBytes(StandardCharsets.UTF_8);
        byte[] c = new byte[1 + temp.length];
        System.arraycopy(flag, 0, c, 0, 1);
        System.arraycopy(temp, 0, c, 1, temp.length);
        IRCMessage testmes = new IRCMessage(c);

        assertEquals("test",testmes.getPost());*/
    }
}