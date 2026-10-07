package yugioh.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import yugioh.listener.BattleListener;
import yugioh.model.Card;

/*
 Reglas del duelo: 3 cartas por bando, una carta por ronda y gana quien
 consiga primero 2 rondas. No conoce Swing; solo avisa a un BattleListener.
 */
public class Duel {
    public static final String PLAYER = "Jugador";
    public static final String AI = "Máquina";
    public static final String DRAW = "Empate";
    private static final int ROUNDS_TO_WIN = 2;

    private final List<Card> playerHand;
    private final List<Card> aiHand;
    private final BattleListener listener;
    private final Random random = new Random();
    private final boolean playerStarts;

    private int playerScore = 0;
    private int aiScore = 0;
    private boolean finished = false;

    public Duel(List<Card> playerHand, List<Card> aiHand, BattleListener listener) {
        if (playerHand.size() != 3 || aiHand.size() != 3) {
            throw new IllegalArgumentException("Cada jugador necesita 3 cartas");
        }
        this.playerHand = new ArrayList<>(playerHand);
        this.aiHand = new ArrayList<>(aiHand);
        this.listener = listener;
        this.playerStarts = random.nextBoolean();
    }

    /* Juega una ronda: el jugador elige carta y modo; la máquina elige ambos al azar. */
    public void playRound(Card playerCard, boolean playerAttacks) {
        if (finished) {
            throw new IllegalStateException("El duelo ya terminó");
        }
        if (!playerHand.remove(playerCard)) {
            throw new IllegalArgumentException("Esa carta ya no está disponible");
        }

        Card aiCard = aiHand.remove(random.nextInt(aiHand.size()));
        boolean aiAttacks = random.nextBoolean();

        int result = resolve(playerCard, playerAttacks, aiCard, aiAttacks);
        String winner = DRAW;
        if (result > 0) {
            playerScore++;
            winner = PLAYER;
        } else if (result < 0) {
            aiScore++;
            winner = AI;
        }

        listener.onTurn(describe(playerCard, playerAttacks),
                describe(aiCard, aiAttacks), winner);
        listener.onScoreChanged(playerScore, aiScore);

        if (playerScore == ROUNDS_TO_WIN || aiScore == ROUNDS_TO_WIN || playerHand.isEmpty()) {
            finished = true;
            listener.onDuelEnded(finalWinner());
        }
    }

    //Devuelve >0 si gana el jugador, <0 si gana la máquina y 0 si empatan.
    private int resolve(Card p, boolean pAttacks, Card a, boolean aAttacks) {
        if (pAttacks && aAttacks) return Integer.compare(p.getAtk(), a.getAtk());
        if (pAttacks) return Integer.compare(p.getAtk(), a.getDef());
        if (aAttacks) return Integer.compare(p.getDef(), a.getAtk());
        return 0;
    }

    private String describe(Card card, boolean attacks) {
        return card.getName() + (attacks ? " [ATAQUE " + card.getAtk() + "]"
                : " [DEFENSA " + card.getDef() + "]");
    }

    private String finalWinner() {
        if (playerScore > aiScore) return PLAYER;
        if (aiScore > playerScore) return AI;
        return DRAW;
    }

    public List<Card> getPlayerHand() { return playerHand; }
    public List<Card> getAiHand() { return aiHand; }
    public boolean isPlayerStarts() { return playerStarts; }
    public boolean isFinished() { return finished; }
}