import SwiftUI
import ComposeApp

@main
struct iOSApp: App {

    init() {
        HelperKt.InitKoin()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
