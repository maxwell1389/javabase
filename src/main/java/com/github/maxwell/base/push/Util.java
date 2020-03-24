package com.github.maxwell.base.push;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Util {
	
	public static String join(String sep, Collection<String> strs) {
		if (strs == null || strs.isEmpty()) {
			return "";
		}
		boolean first = true;
		StringBuilder sb = new StringBuilder();
		for (String s : strs) {
			if (first) {
				sb.append(s);
				first = false;
			} else {
				sb.append(sep).append(s);
			}
		}
		return sb.toString();
	}
	
	public static List<String> splitToList(String str, String sep) {
		if (str == null || str.isEmpty()) {
			return Collections.emptyList();
		}
		return Arrays.asList(str.split(sep));
	}
	
	// 生成一个消息用于客户端接收多个push服务的时候识别同一消息
	public static String genMsgId() {
		String timeFactor = String.valueOf(System.currentTimeMillis() % 100000000L);
		String randFactor = String.valueOf(Double.valueOf(Math.random()*10000).intValue());
		StringBuilder sb = new StringBuilder();
		sb.append(timeFactor);
		int len = randFactor.length();
		while(len++ < 4) {
			sb.append('0');
		}
		sb.append(randFactor);
		return sb.toString();
	}

}
