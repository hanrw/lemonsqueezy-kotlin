class MacOSPlatform : Platform {
    override val name: String = "MacOS"
}

actual fun getPlatform(): Platform = MacOSPlatform()