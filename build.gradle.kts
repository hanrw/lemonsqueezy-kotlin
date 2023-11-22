plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kover)
}

dependencies {
    kover(project(":shared"))
}

koverReport {

    filters {
        excludes {
            classes(
                "com.snacks.**.internal.ktor.*",
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