package com.ktc.todyinfo.main;

import androidx.annotation.IntDef;

import static com.ktc.todyinfo.main.MainConstant.GUANGZHOU;
import static com.ktc.todyinfo.main.MainConstant.HANGZHOU;
import static com.ktc.todyinfo.main.MainConstant.SHANGHAI;
import static com.ktc.todyinfo.main.MainConstant.SHENZHEN;

@IntDef({SHANGHAI, HANGZHOU, SHENZHEN, GUANGZHOU})
public @interface MainConstant {
    int SHANGHAI = 0;
    int HANGZHOU = 1;
    int SHENZHEN = 2;
    int GUANGZHOU = 3;
}
