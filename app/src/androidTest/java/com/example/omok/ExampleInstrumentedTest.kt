package com.example.omok

import android.view.View
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OmokGameTest {
    private lateinit var omokpan: Array<IntArray>
    private var currentPlayer: Int = 1
    private lateinit var startOmokText: String

    @Before
    fun setup() {
        omokpan = Array(19) { IntArray(19) { 0 } }
        startOmokText = "Start Omok Game"
    }

    @RunWith(AndroidJUnit4::class)
    class OmokGameTest {
        private lateinit var mainActivity: MainActivity
        private lateinit var omokpan: Array<IntArray>
        private var currentPlayer: Int = 1
        private lateinit var startOmokText: String

        @Before
        fun setup() {
            // MainActivity 인스턴스 생성
            mainActivity = MainActivity()

            // 테스트를 위한 초기 상태 설정
            omokpan = Array(19) { IntArray(19) { 0 } }
            startOmokText = "Start Omok Game"
        }

        @Test
        fun testPlaceDol() {
            // 빈 위치에 돌 놓기
            mainActivity.placeDol(5, 5)
            assertEquals(currentPlayer, mainActivity.omokpan[5][5])

            // 이미 돌이 있는 위치에 돌 놓기
            mainActivity.placeDol(5, 5)
            assertEquals("이미 돌이 있는 위치입니다.", mainActivity.startOmokText)
        }

        @Test
        fun testCheckWin() {
            // 가로 5개 연결
            for (i in 0 until 5) {
                mainActivity.omokpan[5][i] = currentPlayer
            }
            assertTrue(mainActivity.checkWin(5, 0))

            // 세로 5개 연결
            for (i in 0 until 5) {
                mainActivity.omokpan[i][5] = currentPlayer
            }
            assertTrue(mainActivity.checkWin(0, 5))

            // 대각선 5개 연결
            for (i in 0 until 5) {
                mainActivity.omokpan[i][i] = currentPlayer
            }
            assertTrue(mainActivity.checkWin(0, 0))
        }

        @Test
        fun testSwitchPlayer() {
            // 초기 플레이어 확인
            assertEquals(1, currentPlayer)

            // 플레이어 전환
            mainActivity.switchPlayer()
            assertEquals(2, currentPlayer)

            // 다시 플레이어 전환
            mainActivity.switchPlayer()
            assertEquals(1, currentPlayer)
        }

        @Test
        fun testUpdateBoard() {
            // 플레이어 1의 돌 표시
            mainActivity.currentPlayer = 1
            mainActivity.updateBoard()
            assertTrue(mainActivity.blackDol.visibility == View.VISIBLE)
            assertTrue(mainActivity.whiteDol.visibility == View.INVISIBLE)

            // 플레이어 2의 돌 표시
            mainActivity.currentPlayer = 2
            mainActivity.updateBoard()
            assertTrue(mainActivity.blackDol.visibility == View.INVISIBLE)
            assertTrue(mainActivity.whiteDol.visibility == View.VISIBLE)
        }
    }
}



