package com.designpattern.pluggableobject;

//1️⃣ Önce davranışı temsil eden interface
interface DiscountPolicy {
    int apply(int amount);
}
