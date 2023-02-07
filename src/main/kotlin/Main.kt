import kotlin.system.exitProcess

fun main() {
    startGame()
}

fun startGame() {
    handlingState(DiceState.InitialState)
    val readInput = readLine()
    when (val countOfThrowing = convertingInput(readInput)) {
        null -> handlingState(DiceState.Error)
        else -> diceThrowing(countOfThrowing)
    }
    handlingState(DiceState.FinishState)
}

fun handlingState(state: DiceState) {
    val output = when (state) {
        is DiceState.InitialState -> "Введите количество бросков кубика:"
        is DiceState.ThrowingState -> "Бросание кубика..."
        is DiceState.ResultState -> "Бросок №${state.throwNumber}. Результат: ${
            getDiceString(state.getThrowingResult())
        }"
        is DiceState.FinishState -> "Конец"
        is DiceState.Error -> "Некорретно введено количество бросков"
    }
    println(output).also {
        if (state is DiceState.Error) {
            exitProcess(400)
        }
    }
}

fun convertingInput(input: String?): Int? {
    var result: Int? = null
    input?.let { str ->
        result = try {
            str.toInt()
        } catch (e: java.lang.Exception) {
            null
        }
    }
    result?.let {
        if (it <= 0) result = null
    }
    return result
}

fun diceThrowing(countOfThrowing: Int) {
    var i = 1
    while (i <= countOfThrowing) {
        handlingState(DiceState.ThrowingState)
        Thread.sleep(500)
        handlingState(DiceState.ResultState(i))
        Thread.sleep(500)
        i++
    }
}

fun getDiceString(dice: Int): String {
    return when (dice) {
        1 -> "⚀"
        2 -> "⚁"
        3 -> "⚂"
        4 -> "⚃"
        5 -> "⚄"
        6 -> "⚅"
        else -> throw java.lang.RuntimeException("Error")
    }
}