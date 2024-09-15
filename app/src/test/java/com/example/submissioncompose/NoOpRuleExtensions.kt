package com.example.submissioncompose

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class NoOpTestRule : TestRule {
    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                // No operation - can be used to avoid issues with certain system properties
                base.evaluate()
            }
        }
    }
}
