import minesweeper.domain.BoardSize
import minesweeper.domain.LengthNumber
import minesweeper.domain.MineNumber
import minesweeper.domain.MinesweeperBoard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class MinesweeperBoardGameTest {
    @Test
    @DisplayName("사용자가 입력한 높이, 너비 크기의 배열을 만들기")
    fun checkCreateMinesweeperBoard() {
        val boardSize = BoardSize(LengthNumber(10), LengthNumber(7))
        val mineCount = MineNumber(10, boardSize)
        val mineSweeperBoard = MinesweeperBoard(boardSize, mineCount)

        val boardHeight = mineSweeperBoard.minesweeperBoard.size
        val boardWidth = mineSweeperBoard.minesweeperBoard[0].size

        assertAll(
            { assertThat(boardSize.height.length).isEqualTo(boardHeight) },
            { assertThat(boardSize.width.length).isEqualTo(boardWidth) }
        )
    }
}
