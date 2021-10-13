package net.prasyb.miraimcchatbukkit.network;

public class ServerPacket {
    private ServerMessage message;

    public ServerMessage getMessage() {
        return message;
    }

    public void setMessage(ServerMessage message) {
        this.message = message;
    }
}
