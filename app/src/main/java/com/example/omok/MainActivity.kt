package nextstep.omok

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.example.omok.R

class MainActivity : AppCompatActivity() {
    private val board = Array(8) { IntArray(8) { 0 } } // 0: 빈 셀, 1: 검은 돌, 2: 흰 돌
    private var currentPlayer = 1 // 1: 검은 돌, 2: 흰 돌
    private var isGameStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val boardLayout = findViewById<TableLayout>(R.id.board)
        val startGameText = findViewById<TextView>(R.id.start_game_text)

        boardLayout
            .children
            .filterIsInstance<TableRow>()
            .forEachIndexed { rowIndex, tableRow ->
                tableRow.children
                    .filterIsInstance<ImageView>()
                    .forEachIndexed { colIndex, imageView ->
                        imageView.setOnClickListener {
                            if (!isGameStarted) {
                                startGameText.visibility = View.GONE
                                isGameStarted = true
                            }
                            placePiece(rowIndex, colIndex)
                        }
                    }
            }
    }

    private fun placePiece(row: Int, col: Int) {
        if (board[row][col] == 0) {
            board[row][col] = currentPlayer
            updateBoard(row, col)
            checkWin(row, col)
            switchPlayer()
        } else {
            val alreadyPlacedTextView = findViewById<TextView>(R.id.intheDol)
            alreadyPlacedTextView.visibility = View.VISIBLE
        }
    }

    private fun updateBoard(row: Int, col: Int) {
        val boardLayout = findViewById<TableLayout>(R.id.board)
        val imageView = (boardLayout.getChildAt(row) as TableRow).getChildAt(col) as ImageView
        imageView.setImageResource(if (currentPlayer == 1) R.drawable.blackdol else R.drawable.whitedol)
    }

    public inline fun <reified T : View> findViewById(id: Int): T? {
        return super.findViewById(id)
    }

    override fun <T : View> findViewById(id: Int): T? {
        return super.findViewById(id)
    }

    private fun switchPlayer() {
        currentPlayer = if (currentPlayer == 1) 2 else 1
    }

    private fun checkWin(row: Int, col: Int) {
        val player = board[row][col]
        if (player == 0) return

        // 가로 방향 검사
        var count = 1
        for (i in col + 1 until 8) {
            if (board[row][i] == player) count++ else break
        }
        for (i in col - 1 downTo 0) {
            if (board[row][i] == player) count++ else break
        }
        if (count >= 5) {
            showWinnerMessage(player)
            return
        }

        // 세로 방향 검사
        count = 1
        for (i in row + 1 until 8) {
            if (board[i][col] == player) count++ else break
        }
        for (i in row - 1 downTo 0) {
            if (board[i][col] == player) count++ else break
        }
        if (count >= 5) {
            showWinnerMessage(player)
            return
        }

        // 대각선 방향 검사
        count = 1
        var i = row + 1
        var j = col + 1
        while (i < 8 && j < 8) {
            if (board[i][j] == player) count++ else break
            i++
            j++
        }
        i = row - 1
        j = col - 1
        while (i >= 0 && j >= 0) {
            if (board[i][j] == player) count++ else break
            i--
            j--
        }
        if (count >= 5) {
            showWinnerMessage(player)
            return
        }

        // 대각선 방향 검사
        count = 1
        i = row + 1
        j = col - 1
        while (i < 8 && j >= 0) {
            if (board[i][j] == player) count++ else break
            i++
            j--
        }
        i = row - 1
        j = col + 1
        while (i >= 0 && j < 8) {
            if (board[i][j] == player) count++ else break
            i--
            j++
        }
        if (count >= 5) {
            showWinnerMessage(player)
        }
    }

    private fun showWinnerMessage(player: Int) {
        val winnerText = if (player == 1) "검은 돌" else "흰 돌"
        val winnerTextView = findViewById<TextView>(R.id.winnerText)
        winnerTextView.visibility = View.VISIBLE
        winnerTextView.text = "$winnerText 플레이어가 승리했습니다!"
        isGameStarted = false
    }
}







