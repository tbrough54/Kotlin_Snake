import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.JPanel
import javax.swing.Timer

class SnakeGame: JPanel(), ActionListener {

    private val board = Board()
    private val icons = GameIcons()
    private val snake = Snake()
    private val appleLocation = AppleLocation()

    init {
        addKeyListener(TAdapter())
        background = Color.BLACK
        isFocusable = true
        preferredSize = Dimension(board.boardWidth, board.boardHeight)
        initGame()
    }

    private fun initGame() {
        snake.snakeSize = 3

        for (z in 0 until snake.snakeSize) {
            board.x[z] = 50 - z * 10
            board.y[z] = 50
        }

        locateApple()

        board.timer = Timer(board.delay, this)
        board.timer!!.start()
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        displaySnake(g)
    }

    private fun displaySnake(g: Graphics) {
        if (board.inProgress) {
            g.drawImage(icons.apple, appleLocation.xCoord, appleLocation.yCoord, this)

            for (snakeIndex in 0 until snake.snakeSize) {
                if (0 == snakeIndex) {
                    g.drawImage(icons.head, board.x[snakeIndex], board.y[snakeIndex], this)
                } else {
                    g.drawImage(icons.body, board.x[snakeIndex], board.y[snakeIndex], this)
                }
            }

            Toolkit.getDefaultToolkit().sync()
        } else {
            gameOver(g)
        }
    }

    private fun gameOver(g: Graphics) {
        val rh = RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON)

        rh[RenderingHints.KEY_RENDERING] = RenderingHints.VALUE_RENDER_QUALITY

        (g as Graphics2D).setRenderingHints(rh)

        g.color = Color.WHITE
        g.font = Font(EConstants.GAME_FONT.message, Font.BOLD, 15)
        g.drawString(EConstants.GAME_OVER.message,
            (board.boardWidth - getFontMetrics(g.font).stringWidth(EConstants.GAME_OVER.message)) / 2,
            board.boardHeight / 2)
    }

    private fun checkIfCollectPoint() {
        if (board.x[0] == appleLocation.xCoord && board.y[0] == appleLocation.yCoord) {
            snake.snakeSize++
            locateApple()
        }
    }

    private fun move() {
        for (length in snake.snakeSize downTo 1) {
            board.x[length] = board.x[length - 1]
            board.y[length] = board.y[length - 1]
        }

        if (snake.left) board.x[0] -= board.dotSize
        if (snake.right) board.x[0] += board.dotSize
        if (snake.up) board.y[0] -= board.dotSize
        if (snake.down) board.y[0] += board.dotSize
    }

    private fun checkCollisions() {
        for (length in snake.snakeSize downTo 1) {
            if (length > 4 && board.x[0] == board.x[length] && board.y[0] == board.y[length]) board.inProgress=false
        }

        if (board.y[0] >= board.boardHeight) board.inProgress = false
        if (board.y[0] < 0 ) board.inProgress = false
        if (board.x[0] >= board.boardWidth) board.inProgress = false
        if (board.x[0] < 0 ) board.inProgress = false

        if (!board.inProgress) board.timer!!.stop()
    }

    private fun locateApple() {
        for (i in 0..1) {
            when (i) {
                0 -> appleLocation.xCoord = (Math.random() * board.randomPosition).toInt() * board.dotSize
                1 ->appleLocation.yCoord = (Math.random() * board.randomPosition).toInt() * board.dotSize
            }
        }
    }

    override fun actionPerformed(e: ActionEvent) {
        if (board.inProgress) {
            checkIfCollectPoint()
            checkCollisions()
            move()
        }

        repaint()
    }

    private inner class TAdapter: KeyAdapter() {
        override fun keyPressed(e: KeyEvent?) {
            val key = e!!.keyCode

            if ((key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) && !snake.right) {
                snake.left = true
                snake.up = false
                snake.down = false
            }

            if ((key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) && !snake.left) {
                snake.right = true
                snake.up = false
                snake.down = false
            }

            if ((key == KeyEvent.VK_UP || key == KeyEvent.VK_W) && !snake.down) {
                snake.up = true
                snake.right = false
                snake.left = false
            }

            if ((key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) && !snake.up) {
                snake.down = true
                snake.right = false
                snake.left = false
            }
        }
    }

}