// swift-tools-version:5.3
import PackageDescription

// BEGIN KMMBRIDGE VARIABLES BLOCK (do not edit)
let remoteKotlinUrl = "https://maven.pkg.github.com/tddworks/lemonsqueezy-kotlin/lemonsqueezy-kotlin/shared-kmmbridge/unspecified/shared-kmmbridge-unspecified.zip"
let remoteKotlinChecksum = "4e182dd461e42b07100fe05706df08d80dadd12b06892b7a9072b41c3a190d69"
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