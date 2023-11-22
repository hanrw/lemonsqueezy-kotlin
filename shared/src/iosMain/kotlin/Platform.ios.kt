import io.ktor.client.engine.*
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    override val engine: HttpClientEngineFactory<HttpClientEngineConfig>
        get() = TODO()
}

actual fun getPlatform(): Platform = IOSPlatform()