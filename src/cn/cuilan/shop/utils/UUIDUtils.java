package cn.cuilan.shop.utils;

import java.util.UUID;

/**
 * 生成随机字符串的工具类
 * 
 * @author 翠兰123
 * @date 2015年3月23日
 */
public class UUIDUtils {

	/**
	 * 获得随机的字符串，并替换掉其中的"-"
	 * 
	 * @return 返回一个字符串类型的UUID
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

}
