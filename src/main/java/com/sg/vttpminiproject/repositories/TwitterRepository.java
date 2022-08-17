package com.sg.vttpminiproject.repositories;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.sg.vttpminiproject.models.Twitter;

@Repository
public class TwitterRepository {
    @Autowired
	// always rmb to set REDIS_PASSWORD on cli
    @Qualifier("redislab")
	private RedisTemplate<String, String> redisTemplate;

	public void save(Twitter tweet) {
		redisTemplate.opsForValue().set(tweet.getId(), tweet.toJson().toString());
		redisTemplate.expire(tweet.getId(), Duration.ofMinutes(5));
	}

	public void save(List<Twitter> tweets) {
		Map<String, String> map = new HashMap<>();
		for (Twitter twt: tweets)
			map.put(twt.getId(), twt.toJson().toString());
		redisTemplate.opsForValue().multiSet(map);

		for (String id: map.keySet())
			redisTemplate.expire(id, Duration.ofMinutes(5));
	}

	public Optional<Twitter> get(String id) {
		if (!redisTemplate.hasKey(id))
			return Optional.empty();
		String data = redisTemplate.opsForValue().get(id);
		return Optional.of(Twitter.create(data));
	}
}
