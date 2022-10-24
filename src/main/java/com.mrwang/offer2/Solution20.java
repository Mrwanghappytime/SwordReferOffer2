package com.mrwang.offer2;

import com.sun.org.apache.regexp.internal.RE;

/**
 *  表示数值的字符串 包含. E e +/-小数部分可以没有整数，小数部分一定为整数, 使用状态机，在各状态转换，看最终状态是否为终止状态
 */
public class Solution20 {
    public static void main(String[] args) {
        String[] strings = new String[]{"+100", "5e2", "-123", "3.1416", "-1E-16", "12e", "1a3.14", "1.2.3", "+-5", "12e+5.4", "+.1e-1"};
        for (String str : strings) {
            System.out.println(str + ",isNumber" + isNumber(str));
        }
    }

    public static boolean isNumber(String str) {
        State state = State.BEGIN;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '+' || str.charAt(i) == '-') {
                if (state != State.BEGIN && state != State.E_BEGIN) {
                    return false;
                }
                state = state == State.E_BEGIN ? State.E_FUHAO : State.FUHAO;
            } else if (str.charAt(i) <= '9' && str.charAt(i) >= '0') {
                if (state == State.E_FUHAO || state == State.E_BEGIN || state == State.E_NORMAL) {
                    state = State.E_NORMAL;
                } else if (state == State.XIAOSHU_BEGIN || state == State.XIAOSHU_NORMAL) {
                    state = State.XIAOSHU_NORMAL;
                } else if (state == State.FUHAO || state == State.NORMAL || state == State.BEGIN) {
                    state = State.NORMAL;
                }
            } else if (str.charAt(i) == '.') {
                if (state != State.BEGIN && state != State.NORMAL && state != State.FUHAO) {
                    return false;
                }
                state = State.XIAOSHU_BEGIN;
            } else if (str.charAt(i) == 'e' || str.charAt(i) == 'E') {
                if (state != State.NORMAL && state != State.XIAOSHU_NORMAL) {
                    return false;
                }
                state = State.E_BEGIN;
            } else {
                return false;
            }
        }

        return state == State.NORMAL || state == State.E_NORMAL || state == State.XIAOSHU_NORMAL;

    }

    public enum State {
        BEGIN,
        NORMAL,
        FAIL,
        XIAOSHU_BEGIN,
        E_BEGIN,
        FUHAO,
        E_FUHAO,
        E_NORMAL,
        XIAOSHU_NORMAL,
        ;
    }
}
