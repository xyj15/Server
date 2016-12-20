package po;

import other.User;
import other.UserType;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 存储Hotel信息的PO类
 * @author CROFF
 * @version 2016-12-1
 */
public class HotelPO extends User implements Serializable {
	
	private String name;	//酒店名称
	private String city;	//酒店所在城市名
	private String district;	//酒店所属商圈
	private String address;	//酒店地址
	private int level;	//酒店星级
	private double score;	//酒店评分
	private String service;	//酒店设施服务
	private String introduction;	//酒店简介
	private String managerName;	//酒店管理人员的姓名
	private String managerTel;	//酒店管理人员的联系方式
	private ArrayList<String> enterpriseList;	//合作企业列表
	private static final long serialVersionUID = -6833877079313718314L;   //序列号
	
	/**
	 * 空构造方法
	 */
	public HotelPO() {
		enterpriseList = new ArrayList<String>();
		super.setUserType(UserType.Hotel);
	}
	
	/**
	 * 无用户名和密码的构造方法
	 * @param name 酒店名称
	 * @param city 酒店所在城市名
	 * @param district 酒店所属商圈
	 * @param address 酒店地址
	 * @param level 酒店星级
	 * @param score 酒店评分
	 * @param service 酒店设施服务
	 * @param introduction 酒店简介
	 * @param managerName 酒店工作人员姓名
	 * @param managerTel 酒店工作人员联系方式
	 * @param enterpriseList 合作企业列表
	 */
	public HotelPO(String name, String city, String district, String address, int level, double score, String service,
	               String introduction, String managerName, String managerTel, ArrayList<String> enterpriseList) {
		this.name = name;
		this.city = city;
		this.district = district;
		this.address = address;
		this.level = level;
		this.score = score;
		this.service = service;
		this.introduction = introduction;
		this.managerName = managerName;
		this.managerTel = managerTel;
		this.enterpriseList = enterpriseList;
		super.setUserType(UserType.Hotel);
	}
	
	/**
	 * 有用户名和密码的构造方法
	 * @param userID 酒店ID
	 * @param password 酒店登陆的密码
	 * @param name 酒店名称
	 * @param city 酒店所在城市名
	 * @param district 酒店所属商圈
	 * @param address 酒店地址
	 * @param level 酒店星级
	 * @param score 酒店评分
	 * @param service 酒店设施服务
	 * @param introduction 酒店简介
	 * @param managerName 酒店工作人员姓名
	 * @param managerTel 酒店工作人员联系方式
	 * @param enterpriseList 合作企业列表
	 */
	public HotelPO(String userID, String password, String name, String city, String district, String address,
	               int level, double score, String service, String introduction, String managerName,
	               String managerTel, ArrayList<String> enterpriseList, boolean isLogged) {
		super(userID, password, UserType.Hotel, isLogged);
		this.name = name;
		this.city = city;
		this.district = district;
		this.address = address;
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