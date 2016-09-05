package com.pauu.bankqueue;

import java.util.ArrayList;
import java.util.List;
/**
 * 号码管理器
 * @author peng.xing
 *
 */
public class NumberManager {
	private int lastNumber = 1;
	private List<Integer> queueNumber = new ArrayList<Integer>();
	//生成号码给客户
	public synchronized Integer generateNewNumber(){
		queueNumber.add(lastNumber);
		return lastNumber++;
	}
	//排队窗口取号
	public synchronized Integer fetchServiceNumber(){
		return queueNumber.remove(0);
	}
}
