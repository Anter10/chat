package websocket;
 
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class UserIdAndAddress {
    private String ip;
    private String uid;
    private ChannelHandlerContext ctx;
    
    
    
	public ChannelHandlerContext getCtx() {
		return ctx;
	}

	public void setCtx(ChannelHandlerContext ctx) {
		 this.ctx = ctx;
	}

	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public void sendData(String data) {
		this.ctx.channel().writeAndFlush(new TextWebSocketFrame(data));
	}
    
    
}
