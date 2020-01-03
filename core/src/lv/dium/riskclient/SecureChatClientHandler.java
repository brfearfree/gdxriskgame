package lv.dium.riskclient;

import com.fasterxml.jackson.databind.MapperFeature;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Handles a client-side channel.
 */
public class SecureChatClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.err.println(msg);

        String t = msg.toLowerCase();

        // If this is server command
        if(t.substring(0,1).equals("=")){

            String command = t.substring(1,2);

            // login command
            if(command.equals("l")){
                String result = t.substring(2, 3);
                if(result.equals("g")) {
                    System.err.println("Login ok. Game in progress");
                    SecureChatClient.send("!gVasja");
                }
                else if(result.equals("s")) {
                    System.err.println("Login ok. Idle.");
                    SecureChatClient.send("!gRoma");
                }
                else if(result.equals("f")) {
                    System.err.println("Login failed.");
                }
                else{
                    System.err.println("Unknown response: [" + result + "], " + t);
                }
            }

            // greeting command
            else if(command.equals("g")){
                String result = t.substring(2, 3);
                String jsonPayload = "";
                if(result.equals("g")) {
                    jsonPayload = t.substring(3);
                }
                System.err.println("Greeting [" + result + "] " + jsonPayload);

                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
                GameRisk gameServerState = objectMapper.readValue(jsonPayload, GameRisk.class);
                ActorExample.loadGameFromServerState(gameServerState);
            }
            // greeting command
            else if(command.equals("f")){
                String result = t.substring(2, 3);
                String jsonPayload = "";
                if(result.equals("c")) {
                    System.err.println("Force [close connection]");
                }
                else{
                    System.err.println("Force [" + result + "]");
                }

            }

        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}