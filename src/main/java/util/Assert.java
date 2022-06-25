package util;

/**
 * Project name(项目名称)：Redis_jedis_cluster_mset
 * Package(包名): util
 * Class(类名): Assert
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/6/25
 * Time(创建时间)： 11:05
 * Version(版本): 1.0
 * Description(描述)： 断言
 */
public class Assert
{
    /**
     * Not null.
     *
     * @param obj the obj
     * @param msg the msg
     */
    public static void notNull(Object obj, String msg)
    {
        if (obj == null)
        {
            throw new RuntimeException(msg);
        }
    }

    /**
     * Has text.
     *
     * @param str the str
     * @param msg the msg
     */
    public static void hasText(String str, String msg)
    {
        if (str == null)
        {
            throw new RuntimeException(msg);
        }
        if (str.trim().isEmpty())
        {
            throw new RuntimeException(msg);
        }
    }
}
