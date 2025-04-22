package org.example;

public class Village extends Card implements Action {


    @Override
    public void play(Game game, Player player) {
        System.out.println("+1 card\n+2 actions");
        player.draw(1);
        player.addActions(2);
    }

    @Override
    public Card copy() {
        return new Village();
    }
}
