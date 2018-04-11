import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class test01 {
	@Test
	public void test(){
		//host链接云主机的外网地址
		Jedis jedis = new Jedis("117.50.2.84",6379);
		String name = jedis.get("name1");
		jedis.set("name3", "12");
		System.out.println(name);
		jedis.close();
	}
	@Test
	public void test02Shared(){
		List<JedisShardInfo> infos = new ArrayList<JedisShardInfo>();
		
		JedisShardInfo info1 = new JedisShardInfo("106.75.123.226",6379);
		JedisShardInfo info2 = new JedisShardInfo("106.75.123.226",6380);
		JedisShardInfo info3 = new JedisShardInfo("106.75.123.226",6381);
		
		infos.add(info1);
		infos.add(info2);
		infos.add(info3);
		ShardedJedis jedis = new ShardedJedis(infos);
		String name = jedis.get("name2");
		System.out.println(name);
		jedis.set("n1", "67");
		jedis.close();
	}
	public void test03Pool(){
		List<JedisShardInfo> infos = new ArrayList<JedisShardInfo>();
		JedisShardInfo info1 = new JedisShardInfo("");
		JedisShardInfo info2 = new JedisShardInfo("");
		JedisShardInfo info3 = new JedisShardInfo("");
		
		infos.add(info1);
		infos.add(info2);
		infos.add(info3);
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(200);
		ShardedJedisPool pool = new ShardedJedisPool(config, infos);
		ShardedJedis jedis = pool.getResource();
		jedis.set("city","beijing");
		String name = jedis.get("name");
		System.out.println(name);
		pool.returnResource(jedis);
		pool.close();
	}
}
