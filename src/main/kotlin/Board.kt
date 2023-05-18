import javax.swing.Timer

data class Board(val boardWidth: Int = 300,
                 val boardHeight: Int = 300,
                 val dotSize: Int = 10,
                 val allDots: Int = 900,
                 val randomPosition: Int = 29,
                 val delay: Int = 140,
                 var inProgress: Boolean = true,
                 var timer: Timer? = null) {
    val x = IntArray(allDots)
    val y = IntArray(allDots)
}
