package com.example.springboot.utils;
//yzy
import org.springframework.stereotype.Component;

@Component
public class StringUtil {
    public static boolean isAbsent(String string) {
        return "".equals(string) || null == string;
    }
}
