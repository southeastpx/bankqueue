package com.pauu.bankqueue;

public enum CustomerType {
	COMMON,EXPRESS,VIP;
	public String toString(){
		switch(this){
			case COMMON:
				return "ÆÕÍ¨";
			case EXPRESS:
				return "¿ìËÙ";
			case VIP:
				return name();
		}
		return null;
	}
}
