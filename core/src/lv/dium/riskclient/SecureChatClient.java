package lv.dium.riskclient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

public final class SecureChatClient {

    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "9965"));

    static Channel ch;
    static EventLoopGroup group;

    public static void start() {
        try {
            // Configure SSL.
            final SslContext sslCtx = SslContextBuilder.forClient()
                    .trustManager(InsecureTrustManagerFactory.INSTANCE).build();

            group = new NioEventLoopGroup();

            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new SecureChatClientInitializer(sslCtx));

            // Start the connection attempt.
            ch = b.connect(HOST, PORT).sync().channel();
        }
        catch (Exception e){
            System.out.println("MP fail: " + e.getMessage());
        }
    }

    public static void send(String message){
        try {
            // Read commands from the stdin.
            ChannelFuture lastWriteFuture = null;

            // Sends the received line to the server.
            lastWriteFuture = ch.writeAndFlush(message + "\r\n");

            // If user typed the 'bye' command, wait until the server closes
            // the connection.
            if ("bye".equals(message.toLowerCase())) {
                ch.closeFuture().sync();
            }


            // Wait until all messages are flushed before closing the channel.
            if (lastWriteFuture != null) {
                lastWriteFuture.sync();
            }
        }
        catch (Exception e){
            System.out.println("MP fail: " + e.getMessage());
        }
    }
}