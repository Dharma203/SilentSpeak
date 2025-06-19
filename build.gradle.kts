// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
<<<<<<< Updated upstream
    alias(libs.plugins.kotlin.compose) apply false
=======
    alias(libs.plugins.kotlin.compose) apply false // Ensure compose is applied after kapt if necessary
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.google.gms.google.services) apply false
>>>>>>> Stashed changes
}