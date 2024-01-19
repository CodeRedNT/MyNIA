package com.coderednt.mynia

class Play {
}

fun add(a: Double, b: Double) : Double {
    return a + b
}

fun highOrder(a: Double, b: Double, function: (Double, Double) -> Double) {
    println(function(a, b))
}