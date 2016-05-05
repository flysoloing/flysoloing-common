package com.flysoloing.common.security;

import org.junit.Before;
import org.junit.Test;

/**
 * @author laitao
 * @since 2016-05-05 16:39:06
 */
public class SHAUtilsTest {
    @Test
    public void testEncodeSHA() throws Exception {
        String string = "test";
        byte[] bytes = string.getBytes("UTF-8");
        SHAUtils.encodeSHA(bytes);
    }

    @Test
    public void testEncodeSHAHex() throws Exception {
        String string = "test";
        byte[] bytes = string.getBytes("UTF-8");
        SHAUtils.encodeSHAHex(bytes);
    }
}
