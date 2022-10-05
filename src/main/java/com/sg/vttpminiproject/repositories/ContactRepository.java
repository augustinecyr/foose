package com.sg.vttpminiproject.repositories;

import java.time.Duration;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.sg.vttpminiproject.models.Contact;

@Repository
public class ContactRepository {
    @Autowired
    // always rmb to set REDIS_PASSWORD on cli
    @Qualifier("redislab")
    private RedisTemplate<String, String> redisTemplate;

    public void save(Contact entry) {
        redisTemplate.opsForValue().set(entry.getEmail(), entry.toJson().toString());
        redisTemplate.expire(entry.getEmail(), Duration.ofHours(10));
    }

    public Optional<Contact> get(String email) {
		if (!redisTemplate.hasKey(email))
			return Optional.empty();
		String data = redisTemplate.opsForValue().get(email);
		return Optional.of(Contact.create(data));
	}


}
