package com.liuwei.demo.factory.instanctfactory;

import com.liuwei.demo.bean.Animal;
import com.liuwei.demo.bean.Dog;

/**
 * 实例工厂Bean
 */
public class AnimalFactory {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String name;

    public Animal getAnimal(String name) {
        return new Dog();
    }
}
