import java.awt.Image
import javax.swing.ImageIcon

data class GameIcons(val bodyFileName: String = "dot.png",
                     val headFileName: String = "head.png",
                     val appleFileName: String = "apple.png") {
    var body: Image = ImageIcon("src/main/resources/$bodyFileName").image
    var head: Image = ImageIcon("src/main/resources/$headFileName").image
    var apple: Image = ImageIcon("src/main/resources/$appleFileName").image
}
