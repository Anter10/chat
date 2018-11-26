package com.hskj.room;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.hskj.player.Player;
import com.hskj.player.PlayerManager;
import com.hskj.tool.Param;

public class RoomManager {
	   private static Map<String,Room> rooms = new HashMap<String, Room>();
	   
	   /**
	    * 创建房间
	    * @return
	    */
	   public static Room createRoom() {
		   Room room = new Room();
		   room.setRoomid(RoomManager.createRoomId());
		   return room;
	   };
	   
	   /**
	    * 创建房间的ID
	    * @return
	    */
	   public static int createRoomId() {
		   return Integer.valueOf(Param.getYZM(6));
	   }
	   
	   /**
	    * 通过ID得到房间
	    */
       public static Room getRoomById(String id) {
    	   if(rooms.containsKey(id)) {
    		   return rooms.get(id);
    	   }
    	   return null;
       }
       
       /**
        * 给所有房间广播消息
        * @param msg
        */
       public static void sendMessageToAllRoomPlayer(String msg) {
    	    Set <String>sets = rooms.keySet();
	    	for (String key : sets) { 
	    		 Room room = RoomManager.getRoomById(key);
	    		 if(room != null){
	    			 room.sendMessageToAllRoomPlayer(msg);
	    		 }
			 }
       }
       
       /**
        * 给某个房间发送消息
        * @param roomid
        * @param msg
        */
       public static void sendMessageToRoom(String roomid, String msg) {
    	   Room room = RoomManager.getRoomById(roomid);
    	   if(room != null) {
    		   room.sendMessageToAllRoomPlayer(msg); 
    	   }
       }
       
       
       /**
        * 通过房间ID删除某个房间
        * @param roomid
        * @return
        */
       public static boolean removeRoomById(String roomid) {
    	   if(rooms.containsKey(roomid)) {
    		   rooms.remove(roomid);
    		   return true;
    	   }
    	   return false;
       }
}
