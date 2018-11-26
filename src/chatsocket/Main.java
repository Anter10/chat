package chatsocket;
 

import java.nio.charset.Charset;

import javax.net.ssl.SSLException;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

public class Main {
	static final boolean SSL = System.getProperty("ssl") != null;
    static final int PORT = Integer.parseInt(System.getProperty("port", "8001"));
    
    public Main() {
    	
    }
    
    public static void main(String [] args) throws Exception {
    	final SslContext sslCtx;
        if (SSL) {
            SelfSignedCertificate ssc = new SelfSignedCertificate();
            sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
        } else {
            sslCtx = null;
        }
    	EventLoopGroup bossGroup = new NioEventLoopGroup(2);
    	EventLoopGroup workerGroup = new NioEventLoopGroup(4);
    	 final EchoServerHandle serverHandler = new EchoServerHandle();
         try {
             ServerBootstrap b = new ServerBootstrap();
             b.group(bossGroup, workerGroup)
              .channel(NioServerSocketChannel.class)
              .option(ChannelOption.SO_BACKLOG, 100)
              .handler(new LoggingHandler(LogLevel.INFO))
              .childHandler(new ChannelInitializer<SocketChannel>() {
                  @Override
                  public void initChannel(SocketChannel ch) throws Exception {
                      ChannelPipeline p = ch.pipeline();
                      if (sslCtx != null) {
                          p.addLast(sslCtx.newHandler(ch.alloc()));
                      }
                       
                    
                      //3 设置字符串形式的解码
                      p.addLast(new StringDecoder(Charset.forName("UTf-8")));
                      p.addLast(new StringEncoder(Charset.forName("UTf-8")));
                      //p.addLast(new LoggingHandler(LogLevel.INFO));
                      p.addLast(serverHandler);
                  }
              });

             // Start the server.
             ChannelFuture f = b.bind(PORT).sync();

             // Wait until the server socket is closed.
             f.channel().closeFuture().sync();
         } finally {
             // Shut down all event loops to terminate all threads.
             bossGroup.shutdownGracefully();
             workerGroup.shutdownGracefully();
         }
    	
    }
    
    
    
    
}
