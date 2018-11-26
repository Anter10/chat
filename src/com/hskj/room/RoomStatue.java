package com.hskj.room;

/**
 * 房间状态
 * @author guoyouchao
 * 
 */
public enum RoomStatue {
	Empty("空置",0), 
	Playing("正在进行",1), 
	Closed("关闭状态",2), 
	Enough("已满",3),
	Open("打开状态",4);
	
	private String info;  
    private int Statue;
    
    /**
	 * 得到当前房间的信息描述
	 * @return
	 */
	private RoomStatue(String info,int Statue) {
		this.info = info;
		this.Statue = Statue;
	}
	
	/**
	 * 得到当前房间的信息描述
	 * @return
	 */
	public String getInfo() {
		return this.info;
	}
	
	/**
	 * 得到当前房间的状态
	 * @return
	 */
	public int getStatue() {
		return this.Statue;
	}
}
