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

import com.sg.vttpminiproject.models.User;

@Repository
public class UserRepository {
    @Autowired
    @Qualifier("redislab")

    private RedisTemplate<String, String> redisTemplate;

    public void save(User user) {
		redisTemplate.opsForValue().set(user.getUsername(), user.toJson().toString());
		redisTemplate.expire(user.getUsername(), Duration.ofMinutes(5));
	}

	public void save(List<User> users) {
		Map<String, String> map = new HashMap<>();
		for (User usr: users)
			map.put(usr.getUsername(), usr.toJson().toString());
		redisTemplate.opsForValue().multiSet(map);

		for (String id: map.keySet())
			redisTemplate.expire(id, Duration.ofMinutes(5));
	}

	public Optional<User> get(String username) {
		if (!redisTemplate.hasKey(username))
			return Optional.empty();
		String data = redisTemplate.opsForValue().get(username);
		return Optional.of(User.create(data));
	}
   
}
