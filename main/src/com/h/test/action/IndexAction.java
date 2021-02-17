package com.h.test.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.h.test.vo.Votest;

import online.morn.actionlike.work.instance.ActionLike;
import online.morn.actionlike.work.test.Tester;
import online.morn.actionlike.work.utils.Json;

/**
 * 首页
 * @author Morn4ever 2015-10-27
 */
public class IndexAction extends ActionLike{
	
	public IndexAction(){
		Tester.print("测试调用IndexAction构造方法");
	}
	
	//逻辑型
	private boolean boolean1; 
	private Boolean boolean2;
	
	//字符型
	private char char1;
	private Character char2;
	
	//整数型
	private byte byte1;
	private Byte byte2;
	
	private short short1;
	private Short short2;
	
	private int int1;
	private Integer int2;
	
	private long long1;
	private Long long2;
	
	//小数型
	private float float1;
	private Float float2;
	
	private double double1;
	private Double double2;
	
	private String string1;
	
	private String dataLink;
	
	
	public String index() {
		Tester.print("程序执行到 index");
		
		
		Votest votest = new Votest();
		votest.setId(100);
		votest.setName("leiliang10");
		votest.setPass("0101001");
		votest.setMoney(1);
		votest.setTime(new Timestamp(System.currentTimeMillis()));
		votest.setDel(false);
		List<String> keyList = new ArrayList<String>();
		keyList.add("1111");
		keyList.add("2222");
		votest.setKeyList(keyList);
		
		Map<String,String> userHash = new HashMap<String,String>();
		userHash.put("100","50");
		userHash.put("userKK","my name is 姜海诺");
		
		votest.setUserHash(userHash);
		char a = 1;
		String b = "11111";
		float c = 10;
		double d = 1.1;
		
		List<Object> list = new ArrayList<Object>();
		list.add("1111");
		list.add("2222");
		list.add(votest);
		
		super.getHttpPro().sendJson(new ArrayList());
		
		
		Map<String, String> map = Json.toMap("{\"k1\":\"1000\",\"k2\":1000,\"k3\":1000}");
		
		Tester.print("map size:" + map.size());
		for(String key : map.keySet()){
			Tester.print("key:" + key);
			Tester.print("value:" + map.get(key));
		}
		
		List<String> aList = Json.toList("[100,\"100\",'200',500,'']");
		Tester.print("list size:" + aList.size());
		for(String value : aList){
			Tester.print("value:" + value);
		}
		
		
		/*Tester.print("boolean1:" + this.boolean1);
		Tester.print("boolean2:" + this.boolean2);
		Tester.print("char1:" + this.char1);
		Tester.print("char2:" + this.char2);
		Tester.print("byte1:" + this.byte1);
		Tester.print("byte2:" + this.byte2);
		Tester.print("short1:" + this.short1);
		
		Tester.print("short2:" + this.short2);
		Tester.print("int1:" + this.int1);
		Tester.print("int2:" + this.int2);
		Tester.print("long1:" + this.long1);
		Tester.print("long2:" + this.long2);
		Tester.print("float1:" + this.float1);
		Tester.print("float2:" + this.float2);
		Tester.print("double1:" + this.double1);
		Tester.print("double2:" + this.double2);
		Tester.print("string1:" + this.string1);
		Tester.print("dataLink:" + this.dataLink);*/
		return "break";
	}
	
	public void login(){
		Tester.print("程序执行到 login");
	}
	
	public void register(){
		Tester.print("程序执行到 register");
	}
	
	/**
	 * get set方法
	 * @param boolean1
	 */
	public void setBoolean1(boolean boolean1) {
		this.boolean1 = boolean1;
	}

	public void setBoolean2(Boolean boolean2) {
		this.boolean2 = boolean2;
	}

	public void setChar1(char char1) {
		this.char1 = char1;
	}

	public void setChar2(Character char2) {
		this.char2 = char2;
	}

	public void setByte1(byte byte1) {
		this.byte1 = byte1;
	}

	public void setByte2(Byte byte2) {
		this.byte2 = byte2;
	}

	public void setShort1(short short1) {
		this.short1 = short1;
	}

	public void setShort2(Short short2) {
		this.short2 = short2;
	}

	public void setInt1(int int1) {
		this.int1 = int1;
	}

	public void setInt2(Integer int2) {
		this.int2 = int2;
	}

	public void setLong1(long long1) {
		this.long1 = long1;
	}

	public void setLong2(Long long2) {
		this.long2 = long2;
	}

	public void setFloat1(float float1) {
		this.float1 = float1;
	}

	public void setFloat2(Float float2) {
		this.float2 = float2;
	}

	public void setDouble1(double double1) {
		this.double1 = double1;
	}

	public void setDouble2(Double double2) {
		this.double2 = double2;
	}

	public void setString1(String string1) {
		this.string1 = string1;
	}

	public void setDataLink(String dataLink) {
		this.dataLink = dataLink;
	}
}
