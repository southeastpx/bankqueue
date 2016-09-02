package com.pauu.bankqueue;
/**
 * 号码机器（普通、快速、会员）
 * @author peng.xing
 *
 */
public class NumberMachine {
	private NumberManager commonManager = new NumberManager();//生成普通号码的管理器
	private NumberManager expressManager = new NumberManager();//生成快速号码的管理器
	private NumberManager vipManager = new NumberManager();//生成会员号码的管理器
	public NumberManager getCommonManager() {
		return commonManager;
	}
	public NumberManager getExpressManager() {
		return expressManager;
	}
	public NumberManager getVipManager() {
		return vipManager;
	}
	//确保单例
	private NumberMachine(){}
	private static NumberMachine instance = new NumberMachine();
	public static NumberMachine getInstance(){
		return instance;
	}
}
