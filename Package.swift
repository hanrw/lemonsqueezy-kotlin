// swift-tools-version:5.3
import PackageDescription

// BEGIN KMMBRIDGE VARIABLES BLOCK (do not edit)
let remoteKotlinUrl = "https://maven.pkg.github.com/tddworks/lemonsqueezy-kotlin/com/snacks/lemonsqueezy/shared-kmmbridge/0.1.4/shared-kmmbridge-0.1.4.zip"
let remoteKotlinChecksum = "620b841ef814a38858c2d38c501b2c16ea966be9ef469823bea87ac7ee70b85c"
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