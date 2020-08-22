package minesweeper

import minesweeper.domain.BoardSize
import minesweeper.domain.LengthNumber
import minesweeper.domain.Position
import minesweeper.domain.XPosition
import minesweeper.domain.YPosition
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PositionTest {
    @DisplayName("위치 입력값 유효성 확인, 오류사항")
    @ParameterizedTest
    @ValueSource(strings = ["인", "-1", "", "#", "1,2000", "3#3"])
    fun validationPosition(positionString: String) {
        assertThatThrownBy { Position.of(positionString) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @DisplayName("위치 입력값 유효성 확인")
    @Test
    fun validationPosition() {
        assertThat(Position.of("1,2"))
            .isInstanceOf(Position::class.java)
    }

    @DisplayName("지뢰판 범위 외의 위치값 요청 ")
    @Test
    fun validationPositionOutOfIndexes() {
        val boardSize = BoardSize(LengthNumber(5), LengthNumber(5))
        val yOutBoundIndexOutOfBounds = "6,2"
        val xOutBoundIndexOutOfBounds = "2,6"

        assertAll(
            {
                assertThatThrownBy { Position.of(xOutBoundIndexOutOfBounds, boardSize) }
                    .isInstanceOf(IllegalArgumentException::class.java)
            },
            {
                assertThatThrownBy { Position.of(yOutBoundIndexOutOfBounds, boardSize) }
                    .isInstanceOf(IllegalArgumentException::class.java)
            }
        )
    }

    @DisplayName("위치 입력값 equal 확인")
    @Test
    fun checkEqualPosition() {
        val position = Position.of("10,12")

        assertAll(
            { assertThat(position.x).isSameAs(XPosition.of(10)) },
            { assertThat(position.y).isSameAs(YPosition.of(12)) },
            { assertThat(position).isEqualTo(Position(XPosition.of(10), YPosition.of(12))) }
        )
    }
}
