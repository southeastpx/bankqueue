package com.pauu.bankqueue;

import java.util.Random;
import java.util.concurrent.Executors;

public class ServiceWindow {
	private CustomerType type = CustomerType.COMMON;
	private int windowId = 1;
	
	public void setType(CustomerType type) {
		this.type = type;
	}

	public void setWindowId(int windowId) {
		this.windowId = windowId;
	}

	public void start(){
		Executors.newSingleThreadExecutor().execute(new Runnable() {
			@Override
			public void run() {
				while(true){
					switch(type){
						case COMMON:
						commonService();
							break;
						case EXPRESS:
							expressService();
							break;
						case VIP:
							vipService();
							break;
					}
				}
			}
		});
	}

	private void commonService() {
		String windowName = "第"+windowId+"号"+type+"窗口";
		System.out.println("正在获取任务！");
		Integer number = NumberMachine.getInstance().getCommonManager().fetchServiceNumber();
		if(number!=null){
			long beginTime = System.currentTimeMillis();
			int maxRandom = Constants.MAX_SERVICE_TIME-Constants.MIN_SERVICE_TIME;
			int serviceTime = Constants.MIN_SERVICE_TIME+new Random().nextInt(maxRandom)+1;
			try {
				Thread.sleep(serviceTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			long endTime = System.currentTimeMillis();
			System.out.println(windowName+"为第"+number+"个"+type+"客户完成服务，耗时"+(endTime-beginTime));
		}else{
			System.out.println("没有取到任务,先休息一秒钟!");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void expressService() {
		String windowName = "第"+windowId+"号"+type+"窗口";
		System.out.println("正在获取任务！");
		Integer number = NumberMachine.getInstance().getExpressManager().fetchServiceNumber();
		if(number!=null){
			long beginTime = System.currentTimeMillis();
			int serviceTime = Constants.MIN_SERVICE_TIME;
			try {
				Thread.sleep(serviceTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			long endTime = System.currentTimeMillis();
			System.out.println(windowName+"为第"+number+"个"+type+"客户完成服务，耗时"+(endTime-beginTime));
		}else{
			System.out.println(windowName+"没有取到任务!");
			commonService();
		}
	}
	
	private void vipService() {
		String windowName = "第"+windowId+"号"+type+"窗口";
		System.out.println("正在获取任务！");
		Integer number = NumberMachine.getInstance().getVipManager().fetchServiceNumber();
		if(number!=null){
			long beginTime = System.currentTimeMillis();
			int maxRandom = Constants.MAX_SERVICE_TIME-Constants.MIN_SERVICE_TIME;
			int serviceTime = Constants.MIN_SERVICE_TIME+new Random().nextInt(maxRandom)+1;
			try {
				Thread.sleep(serviceTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			long endTime = System.currentTimeMillis();
			System.out.println(windowName+"为第"+number+"个"+type+"客户完成服务，耗时"+(endTime-beginTime));
		}else{
			System.out.println(windowName+"没有取到任务!");
			commonService();
		}
	}
}
