package com.example.jackiechan.dbutils;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by jackiechan on 15/12/6.
 */
@Table(name = "person")//代表这个类在数据库中对应哪一张表
public class Person {
    @Column(name = "name", isId = true)//isID 意思为是否是主键,如果是 那么这个的值必须是唯一的
    private String name;//姓名
    @Column(name = "gender")
    private String gender;//性别
    @Column(name = "age")
    private int age;// 年龄

    @Column(name = "xuexiao")
    private String xuexiao;


    public String getXuexiao() {
        return xuexiao;
    }

    public void setXuexiao(String xuexiao) {
        this.xuexiao = xuexiao;
    }

    public Person() {//无参的构造必须保留,内部反射调用
    }

    public Person(String name, String gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
