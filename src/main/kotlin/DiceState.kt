import kotlin.random.Random

sealed class DiceState {
    object InitialState: DiceState()
    object ThrowingState: DiceState()
    data class ResultState(
        val throwNumber: Int
    ) : DiceState() {
        fun getThrowingResult(): Int{
            return Random.nextInt(1, 7)
        }
    }
    object FinishState: DiceState()
    object Error: DiceState()
}