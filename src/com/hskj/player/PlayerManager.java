package com.hskj.player;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.hskj.tool.Param;

public class PlayerManager {
	  
		/**
		 * 当前缓存中存在的玩家数据
		 */
	   private static Map players = new HashMap<String, Player>();
	   
	   /**
	    * 创建玩家
	    * @return
	    */
       public static Player createPlayer() {
    	   String playerid =  PlayerManager.createPlayerId();
    	   Player player = new Player();
    	   player.setId(playerid);
    	   players.put(player.getId(), player);
    	   return player;
       }
       
       /**
        * 创建玩家的ID
        */
       public static String createPlayerId() {
    	   return Param.getYZM(6) + Param.getCurentServerTime() + Param.getYZM(6);
       }
       
       /**
        * 通过ID得到玩家
        */
       public static Player getPlayerById(String playerid) {
    	    if(players.containsKey(playerid)) {
    	    	Player player = (Player) players.get(playerid);
;    	    	return player;
    	    }
    	    return null;
       }
       
       /**
        * 得到所有的玩家
        */
       public static Map<String, Map> getPlayers(){
    	   return players;
       }
       
     /**
      * 所有玩家广播
      * @param msg
      */
      public static void sendMessageToAllPlayer(String msg) {
    	  Set <String>sets = PlayerManager.getPlayers().keySet();
	    	for (String key : sets) { 
	    		 Player pl = (Player) PlayerManager.getPlayers().get(key);
	    		 if(pl.isActive() == true){
	    			pl.getChc().write(msg);
	    			pl.getChc().flush();
	    		 }
			 }
      }
      
      
      /**
       * 删除某个玩家
       * @param playerid
       * @return
       */
       public static boolean removePlayerById(String playerid) {
	   	   if(players.containsKey(playerid)) {
	   		players.remove(playerid);
	   		   return true;
	   	   }
	   	   return false;
      }
       
      
       
      
}
