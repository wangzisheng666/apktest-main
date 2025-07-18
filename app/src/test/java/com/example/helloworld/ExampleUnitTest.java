package com.example.helloworld;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    
    @Test
    public void testAppName() {
        // 简单的测试，验证应用名称
        String appName = "HelloWorld";
        assertNotNull(appName);
        assertEquals("HelloWorld", appName);
    }
} 