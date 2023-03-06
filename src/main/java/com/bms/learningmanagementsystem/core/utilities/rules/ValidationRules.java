package com.bms.learningmanagementsystem.core.utilities.rules;

public class ValidationRules {
    public static void run(Runnable... rules) {
        for (var rule : rules) {
            rule.run();
        }
    }
}
