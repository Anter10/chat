package com.hskj.player;


import io.netty.channel.ChannelHandlerContext;


public class Player {
	
	
	/**
	 * 玩家的ID
	 */
    private String id;
   
    /**
     * 玩家登陆的IP
     */
    private String ip;
    
    /**
     * 玩家连接的具柄
     */
    private ChannelHandlerContext chc = null;
    
    
    
    /**
     * 得到玩家的ID
     * @return
     */
	public String getId() {
		return id;
	}

	/**
	 * 设置玩家的ID
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 得到玩家连接
	 * @return
	 */
	public ChannelHandlerContext getChc() {
		return chc;
	}

	/**
	 * 设置玩家连接
	 * @param chc
	 */
	public void setChc(ChannelHandlerContext chc) {
		this.chc = chc;
	}
    

	
	
    /**
     * 得到玩家的IP
     */
	public String getIp() {
		return ip;
	}
   
	/**
	 * 设置玩家的IP
	 * @param ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
	/**
	 * 当前用户是否活跃
	 */
	public boolean isActive() {
		return this.getChc().channel().isActive();
	}
	
	/**
	 * 打印玩家的信息
	 */
	public void printInfo() {
	    System.out.print("玩家的ID "+this.getId());
	    System.out.print("玩家的IP "+this.getIp());
	}
	
	@Override
	public String toString() {
		String info = "";
		info += "玩家的ID = "+this.getId();
		info += "玩家的IP = "+this.getIp();
		info += "玩家的信道 = "+this.getChc().hashCode();
		return info;
	}
    
    
    
}
