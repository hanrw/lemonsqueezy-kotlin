plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.multiplatform.swiftpackage)
}

kotlin {
    jvm()
    listOf(
        macosArm64(),
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            // put your Multiplatform dependencies here
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.bundles.ktor.client)
        }

        commonTest.dependencies {
            implementation(libs.junit)
            implementation(libs.ktor.client.mock)
        }

        jvmTest.dependencies {
            implementation(libs.bundles.jvm.test)
        }
    }
}

multiplatformSwiftPackage {
    swiftToolsVersion("5.9")
    targetPlatforms {
        iOS { v("14") }
        macOS { v("13") }
    }
}

