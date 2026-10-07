package yugioh.listener;

/*
 Desacopla la lógica del duelo de la interfaz: Duel avisa de lo que pasa
 y la ventana decide cómo mostrarlo.
 */
public interface BattleListener {
    //Se jugó una ronda: cartas usadas (con su modo) y quién ganó ("Jugador", "Máquina" o "Empate").
    void onTurn(String playerCard, String aiCard, String winner);
    //Cambió el marcador de rondas ganadas.
    void onScoreChanged(int playerScore, int aiScore);
    //El duelo terminó; winner es "Jugador", "Máquina" o "Empate".
    void onDuelEnded(String winner);
}