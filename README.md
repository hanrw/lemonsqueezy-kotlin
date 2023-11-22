[![CI](https://dl.circleci.com/status-badge/img/gh/hanrw/onegai-sensei/tree/main.svg?style=svg&circle-token=a36cda1c2ffa25bf74b960c56fd844e1d005b20d)](https://github.com/hanrw/lemonsqueezy-kotlin/workflows/CI/badge.svg?branch=main)
[![codecov](https://codecov.io/gh/hanrw/onegai-sensei/branch/main/graph/badge.svg?token=4I0kaLhkrK)](https://codecov.io/gh/hanrw/lemonsqueezy-kotlin)

This is a Kotlin Multiplatform project targeting iOS.

* `/shared` is for the code that will be shared between all targets in the project.
  The most important subfolder is `commonMain`. If preferred, you can add code to the platform-specific folders here too.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…


## Lemonsqueezy api

```kotlin
val api = LemonSqueezyApi(token = "YOUR_TOKEN")
val response = api.activeLicense("YOUR_LICENSE_KEY", "YOUR_INSTANCE_NAME")
```


