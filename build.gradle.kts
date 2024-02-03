plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kover)
}

dependencies {
    kover(project(":shared"))
}

val autoVersion = project.property(
    if (project.hasProperty("AUTO_VERSION")) {
        "AUTO_VERSION"
    } else {
        "LIBRARY_VERSION"
    }
) as String

subprojects {
    val GROUP: String by project
    group = GROUP
    version = autoVersion
}

koverReport {
    filters {
        excludes {
            classes(
                "**Platform*",
                "*.LemonSqueezy$*",
                "*.LemonSqueezyKt",
                "*.LemonSqueezyApi",
                "com.tddworks.**.request.*",
                "com.tddworks.**.response.*",
                "com.tddworks.**.data.*",
                "com.tddworks.**.internal.ktor.internal.*",
                "com.tddworks.**.**.ktor.internal.*",
                //"com.tddworks.**.*\$*$*", // Lambda functions like - LemonSqueezyLicenseApi$activeLicense$activationResult$1
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
            classes("com.tddworks.*")
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