/*
 * MIT License
 * Copyright (c) 2026 Message App
 * See LICENSE file in root directory for full license text.
 */

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class ComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

            extensions.configure<LibraryExtension> {
                buildFeatures { compose = true }
            }

            dependencies {
                val bom = platform("androidx.compose:compose-bom:2024.06.00")
                add("implementation",            bom)
                add("androidTestImplementation", bom)
                add("implementation",        "androidx.compose.ui:ui")
                add("implementation",        "androidx.compose.material3:material3")
                add("implementation",        "androidx.compose.ui:ui-tooling-preview")
                add("debugImplementation",   "androidx.compose.ui:ui-tooling")
                add("debugImplementation",   "androidx.compose.ui:ui-test-manifest")
                add("androidTestImplementation", "androidx.compose.ui:ui-test-junit4")
            }
        }
    }
}