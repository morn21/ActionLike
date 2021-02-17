package com.h.test.vo;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class Votest {
	private int id;
	private String name;
	private String pass;
	private Timestamp time;
	private double money;
	private boolean isDel;
	private List<String> keyList;
	private Map<String,String> userHash;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public boolean isDel() {
		return isDel;
	}
	public void setDel(boolean isDel) {
		this.isDel = isDel;
	}
	public List<String> getKeyList() {
		return keyList;
	}
	public void setKeyList(List<String> keyList) {
		this.keyList = keyList;
	}
	public Map<String, String> getUserHash() {
		return userHash;
	}
	public void setUserHash(Map<String, String> userHash) {
		this.userHash = userHash;
	}
	
}
