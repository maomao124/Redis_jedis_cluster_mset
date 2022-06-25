import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
import util.ClusterSlotHashUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Project name(项目名称)：Redis_jedis_cluster_mset
 * Package(包名): PACKAGE_NAME
 * Class(类名): JedisClusterTest
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/6/25
 * Time(创建时间)： 11:17
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class JedisClusterTest
{
    private JedisCluster jedisCluster;

    @BeforeEach
    void setUp()
    {
        // 配置连接池
        /*JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(8);
        poolConfig.setMaxIdle(8);
        poolConfig.setMinIdle(0);
        poolConfig.setMaxWaitMillis(1000);*/
        HashSet<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("127.0.0.1", 7201));
        nodes.add(new HostAndPort("127.0.0.1", 7202));
        nodes.add(new HostAndPort("127.0.0.1", 7203));
        nodes.add(new HostAndPort("127.0.0.1", 7301));
        nodes.add(new HostAndPort("127.0.0.1", 7302));
        nodes.add(new HostAndPort("127.0.0.1", 7303));
        nodes.add(new HostAndPort("127.0.0.1", 7304));
        nodes.add(new HostAndPort("127.0.0.1", 7305));
        nodes.add(new HostAndPort("127.0.0.1", 7306));
        jedisCluster = new JedisCluster(nodes);
    }

    /**
     * 添加失败，插槽不一样
     * Keys must belong to same hashslot
     */
    @Test
    void testMSet()
    {
        jedisCluster.mset("name", "Jack", "age", "21", "sex", "male");
    }

    /**
     * 串行slot
     * m次网络耗时 + N次命令 耗时 m = key的slot个数
     */
    @Test
    void testMSet2()
    {
        Map<String, String> map = new HashMap<>(3);
        map.put("name", "Jack");
        map.put("age", "21");
        map.put("sex", "Male");

        Map<Integer, List<Map.Entry<String, String>>> result = map.entrySet()
                .stream()
                .collect(Collectors.groupingBy(
                        entry -> ClusterSlotHashUtil.calculateSlot(entry.getKey()))
                );
        for (List<Map.Entry<String, String>> list : result.values())
        {
            String[] arr = new String[list.size() * 2];
            int j = 0;
            for (int i = 0; i < list.size(); i++)
            {
                j = i << 2;
                Map.Entry<String, String> e = list.get(0);
                arr[j] = e.getKey();
                arr[j + 1] = e.getValue();
            }
            jedisCluster.mset(arr);
        }
    }

    @AfterEach
    void tearDown()
    {
        if (jedisCluster != null)
        {
            jedisCluster.close();
        }
    }
}
