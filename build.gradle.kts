plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kover)
}

dependencies {
    kover(project(":shared"))
}

subprojects {
    val GROUP: String by project
    val LIBRARY_VERSION: String by project

    group = GROUP
    version = LIBRARY_VERSION
}

koverReport {
    filters {
        excludes {
            classes(
                "**Platform*",
                "*.LemonSqueezy$*",
                "*.LemonSqueezyKt",
                "*.LemonSqueezyApi",
                "com.snacks.**.request.*",
                "com.snacks.**.response.*",
                "com.snacks.**.data.*",
                "com.snacks.**.internal.ktor.internal.*",
                "com.snacks.**.**.ktor.internal.*",
                //"com.snacks.**.*\$*$*", // Lambda functions like - LemonSqueezyLicenseApi$activeLicense$activationResult$1
                "*.BuildConfig",
                "*.BuildKonfig", // BuildKonfig generated
                "*.ComposableSingletons*", // Jetpack Compose generated
                "*.*\$*Preview\$*", // Jetpack Compose Preview functions
                "*.di.*", // Koin
                "*.ui.preview.*", // Jetpack Compose Preview providers
                "*.*Test", // Test files
                "*.*Test*", // Test cases
                "*.*Mock", // mockative @Mock generated
                "*.test.*", // Test util package
                "*.*\$\$serializer", // Kotlinx serializer)
                "**.*\$Lambda$*.*", // Lambda functions
                "**.*\$inlined$*", // Inlined functions
                "**.*2\$1" // transactionWithResult
            )
        }
        includes {
            classes("com.snacks.*")
        }
    }

    verify {
        rule {
            bound {
                minValue = 100
            }
        }
    }
}