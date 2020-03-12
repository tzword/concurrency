package com.tzword.concurrency.redis;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

/**
 * Created by tzwor on 2020/3/12.
 */
@Slf4j
public class RedisExample1 {
    private Jedis jedis = new Jedis("127.0.0.1",6379);

    public RedisExample1(Jedis jedis) {
        this.jedis = jedis;
    }

    public boolean isActionAllowed(String userId,String actionKey,int period,int maxCount){
        String key = String.format("hist:%s:%s",userId,actionKey);
        log.info(key);
        //毫秒时时间戳
        long nowTs = System.currentTimeMillis();
        System.out.println(nowTs);
        Pipeline pipe = jedis.pipelined();
        pipe.multi();
        //zadd(key，score，member):括号里面代表（第一个是每个都是相同的值，第二个是分数，第三个是用户id）
        pipe.zadd(key,nowTs,""+nowTs);
        //移除时间窗口之前的行为记录，上下的都是时间窗口内的
        pipe.zremrangeByScore(key,0,nowTs-period * 1000);
        //获取窗口内的行为数量
        Response<Long> count = pipe.zcard(key);
        //设置zset过期时间，避免冷用户占用内存，
        //过期时间应该等于时间窗口的长度，再多宽限1s
        pipe.expire(key,period + 1);
        //批量执行
        pipe.exec();
        pipe.close();
        //比较是否超标
        return count.get() <= maxCount;
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        RedisExample1 redisExample1 = new RedisExample1(jedis);
        for (int i = 0; i < 20; i++) {
            System.out.println(redisExample1.isActionAllowed("tzword","reply",60,5));
        }
        jedis.close();
    }
}
