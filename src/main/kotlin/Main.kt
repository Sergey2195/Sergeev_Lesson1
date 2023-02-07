fun main(){
    printState(DiceState.ResultState(1))
}

fun printState(state: DiceState){
    val output = when (state){
        is DiceState.InitialState -> "Введите количество бросков кубика:"
        is DiceState.ThrowingState -> "Бросание кубика..."
        is DiceState.ResultState -> "Бросок №${state.throwNumber}. Результат: ${
            getDiceString(state.getThrowingResult())}"
        is DiceState.FinishState -> "Конец"
    }
    println(output)
}

fun getDiceString(dice: Int):String{
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