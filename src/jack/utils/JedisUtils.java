package jack.utils;

import java.io.InputStream;
import java.util.Properties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtils {

	//将jedis创建
	private static JedisPool pool = null;
	static {
		//建立连接池
		JedisPoolConfig config = new JedisPoolConfig();
		//配置文件，直接读取
		InputStream in = JedisUtils.class.getClassLoader().getResourceAsStream("redis.properties");
		Properties pro = new Properties();
		try {
			pro.load(in);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		config.setMaxIdle(Integer.parseInt(pro.getProperty("redis.maxIdle").toString()));
		config.setMinIdle(Integer.parseInt(pro.getProperty("redis.minIdle").toString()));
		config.setMaxTotal(Integer.parseInt(pro.getProperty("redis.maxTotal").toString()));
		
		pool = new JedisPool(config,
							pro.getProperty("redis.url").toString(),
							Integer.parseInt(pro.getProperty("redis.port").toString()));
							
	}
	
	//传回jedis对象
	public static Jedis getJedis() {
		return pool.getResource();
	}
}
