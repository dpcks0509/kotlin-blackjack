package blackjack.view

import blackjack.model.Calculator
import blackjack.model.GameResult
import blackjack.model.Participant
import blackjack.model.Participant.Dealer
import blackjack.model.Participant.Player

object ResultView {
    private const val MESSAGE_PARTICIPANT_GAME_SCORE = "%s 카드: %s - 결과: %d"
    private const val MESSAGE_TOTAL_REVENUE = "\n## 최종 수익"
    private const val MESSAGE_REVENUE = "%s: %s"

    fun outputGameScores(
        dealer: Dealer,
        players: List<Player>,
    ) {
        outputGameScore(dealer)
        players.forEach { player ->
            outputGameScore(player)
        }
    }

    fun outputGameResult(gameResult: GameResult) {
        println(MESSAGE_TOTAL_REVENUE)
        println(
            MESSAGE_REVENUE.format(
                gameResult.dealer.participantInformation.name,
                gameResult.calculateDealerRevenue(),
            ),
        )
        gameResult.playersRevenue.withIndex().map { (index, playerRevenue) ->
            println(
                MESSAGE_REVENUE.format(
                    gameResult.players[index].participantInformation.name,
                    playerRevenue,
                ),
            )
        }
    }

    private fun outputGameScore(participant: Participant) {
        println(
            MESSAGE_PARTICIPANT_GAME_SCORE.format(
                participant.participantInformation.name,
                participant.gameInformation.cards.joinToString(separator = ", "),
                Calculator.calculateScore(participant.gameInformation.cards),
            ),
        )
    }
}
