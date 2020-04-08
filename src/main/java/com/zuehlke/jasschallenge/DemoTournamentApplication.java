package com.zuehlke.jasschallenge;

import com.zuehlke.jasschallenge.client.RemoteGame;
import com.zuehlke.jasschallenge.client.game.Player;
import com.zuehlke.jasschallenge.client.game.strategy.RandomJassStrategy;
import com.zuehlke.jasschallenge.client.game.strategy.StrongestOrWeakestStrategy;
import com.zuehlke.jasschallenge.messages.type.SessionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Starts one or multiple bots in tournament mode.
 * <p>
 * Add your own com.zuehlke.jasschallenge.client.strategy to compete in the Jass Challenge Tournament!
 *
 * <br><br>
 * To start from CLI use
 * <pre>
 *     gradlew run [websocketUrl]
 * </pre>
 */
public class DemoTournamentApplication {
    private static final String SERVER_URL = "ws://127.0.0.1:3000";
    private static final List<Player> players = new ArrayList<>();
    private static final Logger log = LoggerFactory.getLogger(DemoTournamentApplication.class);

    public static void main(String[] args) throws Exception {
        String websocketUrl = parseWebsocketUrlOrDefault(args);


        players.add(new Player("StrongestOrWeakest", new StrongestOrWeakestStrategy()));
        players.add(new Player("StrongestOrWeakest", new StrongestOrWeakestStrategy()));
        players.add(new Player("RandomJass", new RandomJassStrategy()));
        players.add(new Player("RandomJass", new RandomJassStrategy()));


        System.out.println("Connecting... Server socket URL: " + websocketUrl);
        players.forEach(player -> {
            startGame(websocketUrl, player);
        });
    }

    private static String parseWebsocketUrlOrDefault(String[] args) {
        if (args.length > 0) {
            System.out.println("Arguments: " + Arrays.toString(args));
            return args[0];
        }
        return SERVER_URL;
    }

    private static void startGame(String targetUrl, Player myLocalPlayer) {
        RemoteGame remoteGame = new RemoteGame(targetUrl, myLocalPlayer, SessionType.TOURNAMENT, "tournament");
        remoteGame.start();
    }
}
