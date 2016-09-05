package com.pauu.bankqueue;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainClass {
	public static void main(String[] args) {
		for(int i=1;i<=5;i++){
			ServiceWindow commonWindow = new ServiceWindow();
			commonWindow.setWindowId(i);
			commonWindow.start();
		}
		ServiceWindow expressWindow = new ServiceWindow();
		expressWindow.setWindowId(6);
		expressWindow.setType(CustomerType.EXPRESS);
		expressWindow.start();
		
		ServiceWindow vipWindow = new ServiceWindow();
		vipWindow.setWindowId(7);
		vipWindow.setType(CustomerType.VIP);
		vipWindow.start();
		//普通客户
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(
				new Runnable() {
					@Override
					public void run() {
						Integer number = NumberMachine.getInstance().getCommonManager().generateNewNumber();
						System.out.println(number+"号普通客户正在等待服务！");
					}
				}, 
				0, 
				Constants.COMMON_CUSTOMER_TIME_INTERVAL, 
				TimeUnit.SECONDS
		);
		//快速客户
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(
				new Runnable() {
					@Override
					public void run() {
						Integer number = NumberMachine.getInstance().getExpressManager().generateNewNumber();
						System.out.println(number+"号快速客户正在等待服务！");
					}
				}, 
				0, 
				Constants.COMMON_CUSTOMER_TIME_INTERVAL*2, 
				TimeUnit.SECONDS
		);
		//vip客户
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(
				new Runnable() {
					@Override
					public void run() {
						Integer number = NumberMachine.getInstance().getVipManager().generateNewNumber();
						System.out.println(number+"号vip客户正在等待服务！");
					}
				}, 
				0, 
				Constants.COMMON_CUSTOMER_TIME_INTERVAL*6, 
				TimeUnit.SECONDS
		);
	}
}
