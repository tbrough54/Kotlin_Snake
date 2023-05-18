import java.awt.Image
import javax.swing.ImageIcon

data class GameIcons(val bodyFileName: String = "dot.png",
                     val headFileName: String = "head.png",
                     val appleFileName: String = "apple.png") {
    var body = ImageIcon("/Users/thomasbrough/GitRepos/Kotlin_Snake/src/main/resources/$bodyFileName").image
    var head = ImageIcon("/Users/thomasbrough/GitRepos/Kotlin_Snake/src/main/resources/$headFileName").image
    var apple = ImageIcon("/Users/thomasbrough/GitRepos/Kotlin_Snake/src/main/resources/$appleFileName").image
}
