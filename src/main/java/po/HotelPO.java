package po;

import helper.User;
import helper.UserType;

import java.util.ArrayList;

public class HotelPO extends User {
	
	private String name;
	private int level;
	private String city;
	private String address;
	private String district;
	private double score;	//酒店评分
	private String service;	//酒店设施服务
	private String introduction;	//酒店简介
	private String managerName;	//酒店管理人员的姓名
	private String managerTel;	//酒店管理人员的联系方式
	
	private ArrayList<String> enterpriseList;	//合作企业列表
	
	public HotelPO() {
		enterpriseList = new ArrayList<String>();
	}
	
	public HotelPO(String name, String address, String district, String city, int level, double score, String service,
	               String introduction, String managerName, String managerTel, ArrayList<String> enterpriseList) {
		this.name = name;
		this.address = address;
		this.district = district;
		this.city = city;
		this.level = level;
		this.score = score;
		this.introduction = introduction;
		this.managerName = managerName;
		this.managerTel = managerTel;
		this.enterpriseList = enterpriseList;
	}
	
	public HotelPO(String userID, String password, String name, String address, String district, String city,
	               int level, double score, String service, String introduction, String managerName,
	               String managerTel, ArrayList<String> enterpriseList) {
		super(userID, password, UserType.Hotel);
		this.name = name;
		this.address = address;
		this.district = district;
		this.city = city;
		this.level = level;
		this.score = score;
		this.service = service;
		this.introduction = introduction;
		this.managerName = managerName;
		this.managerTel = managerTel;
		this.enterpriseList = enterpriseList;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public String getDistrict() {
		return district;
	}
	
	public void setDistrict(String district) {
		this.district = district;
	}
	
	public double getScore() {
		return score;
	}
	
	public void setScore(double score) {
		this.score = score;
	}
	
	public String getIntroduction() {
		return introduction;
	}
	
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	public String getManagerName() {
		return managerName;
	}
	
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	
	public String getManagerTel() {
		return managerTel;
	}
	
	public void setManagerTel(String managerTel) {
		this.managerTel = managerTel;
	}
	
	public ArrayList<String> getEnterpriseList() {
		return enterpriseList;
	}
	
	public void setEnterpriseList(ArrayList<String> enterpriseList) {
		this.enterpriseList = enterpriseList;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getService() {
		return service;
	}
	
	public void setService(String service) {
		this.service = service;
	}
}