package com.hskj.room;

public enum RoomType {
    NewPlayer("新手房",0), 
    HighPlayer("高手房",1),
	MaxPlayer("至尊房",2);
	/**
	 * 房间类型描述
	 */
	private String info;
	/**
	 * 房间类型值
	 */
	private int type;
	
	/**
	 * 构造函数
	 * @param info
	 * @param type
	 */
	private RoomType(String info,int type) {
		this.info = info;
		this.type = type;
	}
	
	/**
	 * 得到房间类型的描述
	 * @return
	 */
	public String getInfo() {
		return this.info;
	}
	/**
	 * 得到房间的类型
	 * @return
	 */
	public int getType() {
		return this.type;
	}
}
