import java.awt.EventQueue
import javax.swing.JFrame

class RunSnake: JFrame() {
    init {
        initUI()
    }

    private fun initUI() {
        add(SnakeGame())

        title = EConstants.GAME_NAME.message
        isResizable = false

        pack()

        setLocationRelativeTo(null)
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            EventQueue.invokeLater {
                val ex = RunSnake()
                ex.isVisible = true
            }
        }
    }
}