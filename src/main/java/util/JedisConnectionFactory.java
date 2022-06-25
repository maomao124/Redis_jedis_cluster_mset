package util;

import redis.clients.jedis.*;

/**
 * Project name(项目名称)：Redis_jedis_cluster_mset
 * Package(包名): util
 * Class(类名): JedisConnectionFactory
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/6/25
 * Time(创建时间)： 11:10
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class JedisConnectionFactory
{
    /*private static JedisPool jedisPool;
    private static final JedisCluster jedisCluster;

    static
    {
        // 配置连接池
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(8);
        poolConfig.setMaxIdle(8);
        poolConfig.setMinIdle(0);
        poolConfig.setMaxWaitMillis(1000);
        jedisCluster = new JedisCluster(new HostAndPort("127.0.0.1", 7001),poolConfig);
    }

    public static Jedis getJedis()
    {
        return jedisPool.getResource();
    }

    public static JedisCluster getJedisCluster()
    {
        return jedisCluster;
    }*/
}
