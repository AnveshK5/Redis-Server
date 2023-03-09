package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.embedded.RedisServer;

import java.io.IOException;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(DemoApplication.class, args);
		RedisServer redisServer = new RedisServer(6379);
		redisServer.start();
		System.out.println(redisServer.ports());
		String redisEndpoint = "localhost";
		JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), redisEndpoint, 6379, 2000);
		Jedis jedis = jedisPool.getResource();
		String response = jedis.ping();
		if (redisServer.isActive()) {
			System.out.println("Redis server is running: " + response);
		} else {
			System.out.println("Port 6379 Not Active");
		}
//		redisServer.stop();

	}

}
