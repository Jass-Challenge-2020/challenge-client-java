package com.zuehlke.jasschallenge.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zuehlke.jasschallenge.client.websocket.GameHandler;
import com.zuehlke.jasschallenge.messages.responses.Response;
import com.zuehlke.jasschallenge.messages.type.RemoteTeam;

import java.util.List;
import java.util.Optional;

public class BroadCastTeams implements Message {
    private final List<RemoteTeam> remoteTeams;

    public BroadCastTeams(@JsonProperty(value = "data", required = true) List<RemoteTeam> remoteTeams) {
        this.remoteTeams = remoteTeams;
    }

    @Override
    public Optional<Response> dispatch(GameHandler handler) {
        handler.onBroadCastTeams(remoteTeams);
        return Optional.empty();
    }
}
