package com.chen.demo.commons;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

public class EncryptUtil {

    private static final char[] data = new char[] {
            'u', 'v', 'w', 'x', 'y', 'z',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    public static String md5(String str){
        return DigestUtils.md5Hex(str);
    }

    public static String gen(int len){
        return RandomStringUtils.random(len, data);
    }
}
