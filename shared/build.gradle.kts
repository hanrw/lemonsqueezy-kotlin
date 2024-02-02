plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.multiplatform.swiftpackage)
    alias(libs.plugins.kover)
    alias(libs.plugins.touchlab.kmmbridge)
    `maven-publish`
}

kotlin {
    jvm()
    listOf(
        macosArm64(),
//        iosX64(),
//        iosArm64(),
//        iosSimulatorArm64()
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
            implementation(libs.ktor.client.mock)
        }

        jvmMain.dependencies {
            implementation(libs.ktor.client.cio)
        }

        jvmTest.dependencies {
            implementation(project.dependencies.platform(libs.junit.bom))
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


addGithubPackagesRepository() // <- Add the GitHub Packages repo

kmmbridge {
    /**
     * reference: https://kmmbridge.touchlab.co/docs/artifacts/MAVEN_REPO_ARTIFACTS#github-packages
     * In kmmbridge, notice mavenPublishArtifacts() tells the plugin to push KMMBridge artifacts to a Maven repo. You then need to define a repo. Rather than do everything manually, you can just call addGithubPackagesRepository(), which will add the correct repo given parameters that are passed in from GitHub Actions.
     */
    mavenPublishArtifacts() // <- Publish using a Maven repo
    spm()
//    spm {
//        swiftToolsVersion = "5.9"
//        platforms {
//            iOS { v("14") }
//            macOS { v("13") }
//            watchOS { v("7") }
//            tvOS { v("14") }
//        }
//    }
}

tasks {
    named<Test>("jvmTest") {
        useJUnitPlatform()
    }
}

