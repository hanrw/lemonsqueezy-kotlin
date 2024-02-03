// swift-tools-version:5.3
import PackageDescription

// BEGIN KMMBRIDGE VARIABLES BLOCK (do not edit)
let remoteKotlinUrl = "https://maven.pkg.github.com/tddworks/lemonsqueezy-kotlin/com/snacks/lemonsqueezy/shared-kmmbridge/0.1.5/shared-kmmbridge-0.1.5.zip"
let remoteKotlinChecksum = "5d1c6558473bf2cfdbd2b29df6fe907506333338581cdb6ad8e9c07ba6a71e7a"
let packageName = "Lemonsqueezy.Sdk"
// END KMMBRIDGE BLOCK

let package = Package(
    name: packageName,
    platforms: [
        .iOS(.v13)
    ],
    products: [
        .library(
            name: packageName,
            targets: [packageName]
        ),
    ],
    targets: [
        .binaryTarget(
            name: packageName,
            url: remoteKotlinUrl,
            checksum: remoteKotlinChecksum
        )
        ,
    ]
)