package jack.utils;

import java.io.InputStream;
import java.util.Properties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtils {

	//��jedis����
	private static JedisPool pool = null;
	static {
		//�������ӳ�
		JedisPoolConfig config = new JedisPoolConfig();
		//�����ļ���ֱ�Ӷ�ȡ
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
	
	//����jedis����
	public static Jedis getJedis() {
		return pool.getResource();
	}
}
