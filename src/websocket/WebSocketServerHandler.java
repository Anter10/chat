package websocket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

 
import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
 
  public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {
      private static final Logger logger = Logger
            .getLogger(WebSocketServerHandler.class.getName());
 
      private WebSocketServerHandshaker handshaker;
      @Override
      public void channelRead0(ChannelHandlerContext ctx, Object msg)
             throws Exception {
         // 传统的HTTP接入
         if (msg instanceof FullHttpRequest) {
             handleHttpRequest(ctx, (FullHttpRequest) msg);
         }
         // WebSocket接入
         else if (msg instanceof WebSocketFrame) {
        	 String request = ((TextWebSocketFrame) msg).text();
//             handleWebSocketFrame(ctx, (WebSocketFrame) msg);
        	 Charset utf8 = Charset.forName("utf-8");
        	 ByteBuf bb = Unpooled.copiedBuffer("Helloc",utf8);
        	  
        	 ctx.write(new TextWebSocketFrame("服务器消息"+request.toUpperCase(Locale.US)));
         }
      }
  
      @Override
     public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    	  System.out.print("消息数据  "+(new TextWebSocketFrame(ctx.read().toString())));
          ctx.flush();
    }
 
     private void handleHttpRequest(ChannelHandlerContext ctx,
                                    FullHttpRequest req) throws Exception {
 
          // 如果HTTP解码失败，返回HHTP异常
          if (!req.decoderResult().isSuccess()
                  || (!"websocket".equals(req.headers().get("Upgrade")))) {
              sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HTTP_1_1,
            		  HttpResponseStatus.BAD_REQUEST));
             return;
          }
  
          // 构造握手响应返回，本机测试
          WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                  "ws://localhost:8088/websocket", null, false);
          handshaker = wsFactory.newHandshaker(req);
          
          if (handshaker == null) {
        	     
              WebSocketServerHandshakerFactory
                      .sendUnsupportedVersionResponse(ctx.channel());
          } else {
        	      HttpRequest mReq = (HttpRequest) req;
  			  String clientIP = mReq.headers().get("X-Forwarded-For");
  		      if (clientIP == null) {
				InetSocketAddress insocket = (InetSocketAddress) ctx.channel()
						.remoteAddress();
				clientIP = insocket.getAddress().getHostAddress();
			  }
        	      System.out.print(clientIP + " 开始连接了"+ctx.channel().isOpen());
//        	      UserIdAndAddress ud = new UserIdAndAddress();
//        	      ud.setIp(clientIP);
//         	  ud.setCtx(ctx);
//        	      TzebLogic.getLinks().put(clientIP, ud);
              handshaker.handshake(ctx.channel(), req);
         }
      }
  
      private void handleWebSocketFrame(ChannelHandlerContext ctx,
                                        WebSocketFrame frame) throws CloneNotSupportedException {
  
          // 判断是否是关闭链路的指令
          if (frame instanceof CloseWebSocketFrame) {
              handshaker.close(ctx.channel(),
                      (CloseWebSocketFrame) frame.retain());
              System.out.print("连接已经关闭了啊"+ctx.channel().remoteAddress());
              return;
          }
          // 判断是否是Ping消息
          if (frame instanceof PingWebSocketFrame) {
              ctx.channel().write(
                     new PongWebSocketFrame(frame.content().retain()));
              return;
          }
          // 本例程仅支持文本消息，不支持二进制消息
          if (!(frame instanceof TextWebSocketFrame)) {
             throw new UnsupportedOperationException(String.format(
                    "%s frame types not supported", frame.getClass().getName()));
         }
         
         // 返回应答消息
         String request = ((TextWebSocketFrame) frame).text();
         System.out.print("data  = "+request);
         ctx.channel().write(request);
         ctx.channel().flush();
      }
  
      private static void sendHttpResponse(ChannelHandlerContext ctx,
                                           FullHttpRequest req, FullHttpResponse res) {
          // 返回应答给客户端
          if (res.getStatus().code() != 200) {
              ByteBuf buf = Unpooled.copiedBuffer(res.getStatus().toString(),
                     CharsetUtil.UTF_8);
              res.content().writeBytes(buf);
              buf.release();
              HttpUtil.setContentLength(res, res.content().readableBytes());
          }
  
          // 如果是非``	`-Alive，关闭连接
         ChannelFuture f = ctx.channel().writeAndFlush(res);
          if (!HttpUtil.isKeepAlive(req) || res.status().code() != 200) {
              f.addListener(ChannelFutureListener.CLOSE);
          }
      }
      
      @Override
      public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
              throws Exception {
        
          System.out.print("关闭连接了2222"+ctx.channel().isOpen()+ctx.hashCode());
          ctx.close();
      }
      
      @Override
      public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
    	     InetSocketAddress insocket = (InetSocketAddress) ctx.channel()
					.remoteAddress();
		 String clientIP = insocket.getAddress().getHostAddress();
         //		 TzebLogic.removeUserByIp(clientIP);
//		  
      }
  }
  
  
  
  
  
  
  
  