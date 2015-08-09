package com.zuehlke.jasschallenge.client.websocket.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.zuehlke.jasschallenge.client.websocket.RemoteGameHandler;
import com.zuehlke.jasschallenge.client.websocket.messages.responses.Response;

import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BroadCastGameFinished.class, name = "BROADCAST_GAME_FINISHED"),
        @JsonSubTypes.Type(value = BroadCastStich.class, name = "BROADCAST_STICH"),
        @JsonSubTypes.Type(value = BroadCastTeams.class, name = "BROADCAST_TEAMS"),
        @JsonSubTypes.Type(value = BroadCastTrumpf.class, name = "BROADCAST_TRUMPF"),
        @JsonSubTypes.Type(value = BroadCastWinnerTeam.class, name = "BROADCAST_WINNER_TEAM"),
        @JsonSubTypes.Type(value = RequestCard.class, name = "REQUEST_CARD"),
        @JsonSubTypes.Type(value = RequestTrumpf.class, name = "REQUEST_TRUMPF"),
        @JsonSubTypes.Type(value = RejectCard.class, name = "REJECT_CARD"),
        @JsonSubTypes.Type(value = PlayerJoined.class, name = "BROADCAST_SESSION_JOINED"),
        @JsonSubTypes.Type(value = DealCard.class, name = "DEAL_CARDS"),
        @JsonSubTypes.Type(value = RequestPlayerName.class, name = "REQUEST_PLAYER_NAME"),
        @JsonSubTypes.Type(value = PlayedCards.class, name = "PLAYED_CARDS"),
        @JsonSubTypes.Type(value = RequestSessionChoice.class, name = "REQUEST_SESSION_CHOICE"),
})
public interface Message {
    Optional<Response> dispatch(RemoteGameHandler handler);
}