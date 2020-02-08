package com.liuwei.demo.factory.staticfactory;

import com.liuwei.demo.bean.American;
import com.liuwei.demo.bean.Chinese;
import com.liuwei.demo.bean.Person;

public class PersonFactory {

    public static Person getPerson(String nationality) {
        if ("CN".equals(nationality)) {
            return new Chinese();
        }
        else {
            return new American();
        }
    }
}
