package com.example.omok

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    //변수정의
    private lateinit var blackDol: TextView
    private lateinit var whiteDol: TextView
    private lateinit var startOmokText: TextView
    private lateinit var winnerText: TextView
    private lateinit var omokpan: Array<IntArray>
    //검은돌부터
    private var currentPlayer = 1 // 1: black, 2: white
    private val board = Array(19) { BooleanArray(19) { false } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        blackDol = findViewById(R.id.black)
        whiteDol = findViewById(R.id.white)
        startOmokText = findViewById(R.id.StartOmok)
        winnerText = findViewById(R.id.winnerText)

        omokpan = Array(19) { IntArray(19) { 0 } }

        startOmokText.text = "게임을 시작해주세요"
    }

    private fun updateBoard() {
        blackDol.visibility = if (currentPlayer == 1) View.VISIBLE else View.INVISIBLE
        whiteDol.visibility = if (currentPlayer == 2) View.VISIBLE else View.INVISIBLE
    }

    //이겼는지 확인
    private fun checkWin(x: Int, y: Int): Boolean {
        var count = 0

        // 가로 검사
        for (i in 0 until 19) {
            if (omokpan[x][i] == currentPlayer) {
                count++
                if (count >= 5) return true
            } else {
                count = 0
            }
        }

        // 세로 검사
        count = 0
        for (i in 0 until 19) {
            if (omokpan[i][y] == currentPlayer) {
                count++
                if (count >= 5) return true
            } else {
                count = 0
            }
        }

        // 대각선 검사
        count = 0
        var i = x
        var j = y
        while (i >= 0 && i < 19 && j >= 0 && j < 19) {
            if (omokpan[i][j] == currentPlayer) {
                count++
                if (count >= 5) return true
            } else {
                count = 0
            }
            i++
            j++
        }

        return false
    }

    private fun placeDol(x: Int, y: Int) {
        if (omokpan[x][y] == 0) {
            omokpan[x][y] = currentPlayer
            if (checkWin(x, y)) {
                val winnerName = if (currentPlayer == 1) "검은돌" else "흰돌"
                winnerText.text = getString(R.string.winner_text, winnerName)
            } else {
                switchPlayer()
            }
        } else {
            startOmokText.text = "이미 돌이 있는 위치입니다."
        }
    }

    private fun switchPlayer() {
        currentPlayer = 3 - currentPlayer // 1 -> 2, 2 -> 1
        updateBoard()
    }
}



