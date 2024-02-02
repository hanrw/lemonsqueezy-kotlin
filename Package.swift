// swift-tools-version:5.3
import PackageDescription

// BEGIN KMMBRIDGE VARIABLES BLOCK (do not edit)
let remoteKotlinUrl = "https://maven.pkg.github.com/tddworks/lemonsqueezy-kotlin/com/snacks/lemonsqueezy/shared-kmmbridge/0.1/shared-kmmbridge-0.1.zip"
let remoteKotlinChecksum = "d92d578a02645d9456490d20ba3f3ce6eedf20c42b1c615ab69f3dc761f24031"
let packageName = "Shared"
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