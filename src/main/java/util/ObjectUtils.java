package util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 * Project name(项目名称)：Redis_jedis_cluster_mset
 * Package(包名): util
 * Class(类名): ObjectUtils
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/6/25
 * Time(创建时间)： 11:14
 * Version(版本): 1.0
 * Description(描述)： 无
 */


public class ObjectUtils
{
    /**
     * Is empty boolean.
     *
     * @param obj the obj
     * @return the boolean
     */
    public static boolean isEmpty(Object obj)
    {
        if (obj == null)
        {
            return true;
        }

        if (obj instanceof Optional)
        {
            return !((Optional<?>) obj).isPresent();
        }
        if (obj instanceof CharSequence)
        {
            return ((CharSequence) obj).length() == 0;
        }
        if (obj.getClass().isArray())
        {
            return Array.getLength(obj) == 0;
        }
        if (obj instanceof Collection)
        {
            return ((Collection<?>) obj).isEmpty();
        }
        if (obj instanceof Map)
        {
            return ((Map<?, ?>) obj).isEmpty();
        }
        return false;
    }
}
