package com.hskj.room;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.hskj.player.Player;
import com.hskj.player.PlayerManager;

/**
 * 房间类
 * @author guoyouchao
 *
 */

public class Room {
    
	/**
	 * 房间的玩家字典
	 */
	private static Map players = new HashMap<String, Player>();
	
   /**
    * 房间的ID
    */
   private int roomid;

   /**
    * 房间状态 默认状态为空
    */
   private RoomStatue Statue = RoomStatue.Empty;
   
   /**
    * 房间玩家数量
    */
   private RoomPlayerNumber RPN = RoomPlayerNumber.SingleRoom;
   
   /**
    * 加入房间
    * @param player 加入的玩家
    * @return
    */
   public boolean joinRoom(Player player) {
	   if(players.size() == RPN.getNumber()) {
		  return false;
	   }
	   
	   if(players.containsKey(player.getId())) {
		  return false;
	   }
	   
	   return true;
   }
   
   /**
    * 离开房间
    * @param player 离开的玩家
    * @return
    */
   public boolean outRoom(Player player) {
	   if(players.containsKey(player.getId())) {
		  if(players.remove(player.getId(), player)) {
			  return true;
		  }else {
			  return false;
		  }
	   }else {
		  return false;
	   }
   }

   /**
    * 得到房间的所有玩家
    * @return
    */
	public Map getPlayers() {
		return players;
	}
	
	/**
	 * 设置房间的所有玩家
	 * @param players
	 */
	public void setPlayers(Map players) {
		this.players = players;
	}
	
	
	/**
	 * 得到房间的ID
	 * @return
	 */
	public int getRoomid() {
		return roomid;
	}
	
	/**
	 * 设置房间的ID
	 * @param roomid
	 */
	public void setRoomid(int roomid) {
		this.roomid = roomid;
	}
	
	/**
	 * 得到房间的状态
	 * @return
	 */
	public RoomStatue getStatue() {
		return Statue;
	}
	
	/**
	 * 设置房间的状态
	 * @param statue
	 */
	public void setStatue(RoomStatue statue) {
		Statue = statue;
	}
	
	/**
	 * 得到房间的人数类型
	 * @return
	 */
	public RoomPlayerNumber getRPN() {
		return RPN;
	}

	/**
	 * 设置房间的人数类型
	 * @param rPN
	 */
	public void setRPN(RoomPlayerNumber rPN) {
		RPN = rPN;
	}
	
	   /**
     * 广播房间消息
     * @param msg
     */
    public static void sendMessageToAllRoomPlayer(String msg) {
 	        Set <String> sets = players.keySet();
	    	for (String key : sets) { 
	    		 Player pl = (Player) players.get(key);
	    		 if(pl.isActive() == true){
	    			pl.getChc().write(msg);
	    			pl.getChc().flush();
	    		 }
			 }
    }
    
    /**
     * 打印房间信息
     */
    public void printInfo() {
    	String info = "";
    	info += "房间ID = "+this.getRoomid();
    	info += "房间玩家个数 = "+this.players.size();
    	String [] msgs = new String[this.players.size()];
    	int i = 0;
    	Set <String> sets = players.keySet();
	    for(String key : sets) { 
	        Player pl = (Player) players.get(key);
	    	msgs[i] = pl.toString();  
	    	i ++;
		}
	    info += "玩家的信息="+msgs.toString();
    	System.out.print("房间信息 = "+info);
    }
    
    
   
 
   
   
}
