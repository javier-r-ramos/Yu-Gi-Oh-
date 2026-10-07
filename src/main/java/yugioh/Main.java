package yugioh;

import java.util.ArrayList;
import java.util.List;

import yugioh.api.YgoApiClient;
import yugioh.listener.BattleListener;
import yugioh.logic.Duel;
import yugioh.model.Card;

public class Main {
    public static void main(String[] args) throws Exception {
        YgoApiClient api = new YgoApiClient();
        List<Card> player = new ArrayList<>();
        List<Card> ai = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            player.add(api.getRandomMonster());
            ai.add(api.getRandomMonster());
        }

        Duel duel = new Duel(player, ai, new BattleListener() {
            public void onTurn(String p, String a, String w) {
                System.out.println(p + " vs " + a + " -> " + w);
            }
            public void onScoreChanged(int p, int a) {
                System.out.println("Marcador: " + p + " - " + a);
            }
            public void onDuelEnded(String w) {
                System.out.println("GANADOR: " + w);
            }
        });

        while (!duel.isFinished()) {
            duel.playRound(duel.getPlayerHand().get(0), true);
        }
    }
}