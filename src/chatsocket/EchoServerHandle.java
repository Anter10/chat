package chatsocket;


import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;

import com.hskj.player.Player;
import com.hskj.player.PlayerManager;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

@Sharable
public class EchoServerHandle extends ChannelInboundHandlerAdapter {
	    public static int count = 0;
	    public final AttributeKey<Player> playerid = AttributeKey.newInstance("id");
	    public EchoServerHandle() {
	    	EchoServerHandle.count = EchoServerHandle.count + 1;
	    	System.out.print("当前的连接数 = "+EchoServerHandle.count);
	    }
	    @Override
	    public void channelRead(ChannelHandlerContext ctx, Object msg) {
	    	PlayerManager.sendMessageToAllPlayer(msg.toString()); 
	    }

	    @Override
	    public void channelReadComplete(ChannelHandlerContext ctx) {
	    	
	    }

	    @Override
	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
	        // Close the connection when an exception is raised.
	    	System.out.print("socket异常");  
	        cause.printStackTrace();
	        ctx.close();
	    }
	    
	    @Override
	    public void channelActive(ChannelHandlerContext ctx)
	        throws Exception { 
	    	Player player = PlayerManager.createPlayer();
	    	ctx.channel().attr(playerid).set(player);
	    	player.setChc(ctx);
	    	player.setIp(ctx.channel().remoteAddress().toString());
	    	player.printInfo();
	        System.out.println("\n id = "+ctx.channel().attr(playerid).get().getId()+" 连接的ID = "+ctx.hashCode());
	    }
	    
	    @Override
	    public void channelInactive(io.netty.channel.ChannelHandlerContext ctx) throws java.lang.Exception{
	    	System.out.print("客服端断开连接"+ctx.hashCode());  
	    }
	     
}
