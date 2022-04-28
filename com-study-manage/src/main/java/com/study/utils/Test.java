package com.study.utils;

import java.text.ParseException;


/**
 * @author chenzhangzhong
 * @date 2021/12/08
 * @description 测试类
 */
public class Test {
    public static void main(String[] args) throws ParseException {
        String code = UniqueCodeUtils.idToCode(1525143L);
        System.out.println(code);
        Long toId = UniqueCodeUtils.codeToId(code);
        System.out.println(toId);
    }
}
