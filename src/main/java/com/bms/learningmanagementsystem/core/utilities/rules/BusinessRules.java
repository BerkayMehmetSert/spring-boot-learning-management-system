package com.bms.learningmanagementsystem.core.utilities.rules;

import com.bms.learningmanagementsystem.core.model.BaseEntity;

import java.util.function.Supplier;

public class BusinessRules {
    public static void run(Runnable... logics) {
        for (var logic : logics) {
            logic.run();
        }
    }

    @SafeVarargs
    public static <T extends BaseEntity> T run(Supplier<T>... logics) {
        for (var logic : logics) {
            var result = logic.get();
            if (result != null) {
                return result;
            }
        }
        return null;
    }
}