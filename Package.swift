// swift-tools-version:5.3
import PackageDescription

// BEGIN KMMBRIDGE VARIABLES BLOCK (do not edit)
let remoteKotlinUrl = "https://maven.pkg.github.com/tddworks/lemonsqueezy-kotlin/com/tddworks/lemonsqueezy/shared-kmmbridge/0.1.6/shared-kmmbridge-0.1.6.zip"
let remoteKotlinChecksum = "23e06c2c206918813415239add623ac1b778320ae03acdd4dc683e60a11f6341"
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