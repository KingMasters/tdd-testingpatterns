package com.designpattern.collectionparameter;

import java.util.List;

class Calculator {
    int sum(List<Integer> numbers) {
        int total = 0;
        for (int n : numbers) {
            total += n;
        }
        return total;
    }
}

