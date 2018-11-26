package com.hskj.room;

public enum RoomPlayerNumber {
	
    SingleRoom("单人房间",1),
	DoubleRoom("双人房间",2),
	ThirdRoom("三人房间",3),
	FourRoom("四人房间",4);
	
	/**
	 * 房间玩家数量描述
	 */
	private String info = "";
	/**
	 * 房间玩家数量
	 */
	private int number  = 0;
	
	private RoomPlayerNumber(String info, int number) {
		this.info = info;
		this.number = number;
	}
	
	public String getInfo() {
		return this.info;
	}
	
	public int getNumber() {
		return this.number;
	}
}
