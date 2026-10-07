package yugioh;

import yugioh.api.YgoApiClient;
import yugioh.model.Card;

public class Main {
    public static void main(String[] args) throws Exception {
        YgoApiClient api = new YgoApiClient();
        for (int i = 0; i < 3; i++) {
            Card card = api.getRandomMonster();
            System.out.println(card);
        }
    }
}