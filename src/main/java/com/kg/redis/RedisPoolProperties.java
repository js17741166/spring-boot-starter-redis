package com.kg.redis;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;



	/*<bean id="jedisPoolConfig" class="redis.clients.jedis.JedisPoolConfig">
<property name="maxIdle" value="300" /> <!-- 最大能够保持idel状态的对象数  -->
<property name="maxTotal" value="60000" /> <!-- 最大分配的对象数 -->
<property name="testOnBorrow" value="true" /> <!-- 当调用borrow Object方法时，是否进行有效性检查 -->
</bean>

<bean id="jedisPool" class="redis.clients.jedis.JedisPool">
<constructor-arg index="0" ref="jedisPoolConfig" />
<constructor-arg index="1" value="${redis.host}" />
<constructor-arg index="2" value="${redis.port}" type="int" />
</bean>
*/
/**
 *
 * <bean id="jedisPoolConfig" class="redis.clients.jedis.JedisPoolConfig">
 <property name="maxIdle" value="300" /> <!-- 最大能够保持idel状态的对象数  -->
 <property name="maxTotal" value="60000" /> <!-- 最大分配的对象数 -->
 <property name="testOnBorrow" value="true" /> <!-- 当调用borrow Object方法时，是否进行有效性检查 -->
 </bean>
 * Redis 配置
 */
@ConfigurationProperties(prefix = "redis")
public class RedisPoolProperties {

    @Value("${redis.maxIdle}")
    private Integer maxIdle ;
    @Value("${redis.maxTotal}")
    private Integer maxTotal;
    @Value("${redis.testOnborrow}")
    private Boolean testOnborrow;


    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private Integer port;
    @Value("${redis.password}")
    private String password;


    public RedisPoolProperties() {

        this.jedisPoolConfig = new JedisPoolConfig();
    }

    protected JedisPoolConfig jedisPoolConfig;
    protected JedisPool jedisPool;


    public JedisPoolConfig getJedisPoolConfig() {
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setTestOnBorrow(testOnborrow);
        return jedisPoolConfig;
    }

    public void setJedisPoolConfig(JedisPoolConfig jedisPoolConfig) {
        this.jedisPoolConfig = jedisPoolConfig;
    }

    public Integer getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
    }

    public Boolean getTestOnborrow() {
        return testOnborrow;
    }

    public void setTestOnborrow(Boolean testOnborrow) {
        this.testOnborrow = testOnborrow;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public JedisPool getJedisPool() {
        if (this.password == null || this.password.trim().length() <= 0){
            jedisPool = new JedisPool(this.jedisPoolConfig, this.host, this.port, 6000);
        }else{
            jedisPool = new JedisPool(this.jedisPoolConfig,this.host, this.port, 6000,
                    this.password);
        }
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
