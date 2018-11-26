package websocket;

import java.nio.charset.Charset;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.proxy.HttpProxyHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.codec.http.*;

public class ChatServerInitlizer extends ChannelInitializer<Channel> {
   private final ChannelGroup group;
   
   public ChatServerInitlizer(ChannelGroup group) {
	   this.group = group;
   }
   
   @Override
   public void initChannel(Channel ch) {
	   ChannelPipeline pipeline = ch.pipeline();
	   pipeline.addLast("codec",new HttpServerCodec());
	   pipeline.addLast("aggregator",new HttpObjectAggregator(64 * 1024));
	   pipeline.addLast("trunk",new ChunkedWriteHandler());
	   pipeline.addLast(new StringDecoder(Charset.forName("UTf-8")));
	   pipeline.addLast(new StringEncoder(Charset.forName("UTf-8")));
	   pipeline.addLast("handler",new WebSocketServerHandler());
	   
   }
}
