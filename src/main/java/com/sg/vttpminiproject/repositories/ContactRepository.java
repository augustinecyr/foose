package com.sg.vttpminiproject.repositories;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void save(Contact contact) {
        redisTemplate.opsForValue().set(contact.getEmail(), contact.toString());
        redisTemplate.expire(contact.getEmail(), Duration.ofMinutes(5));
    }

    public void save(List<Contact> contacts) {
        Map<String, String> map = new HashMap<>();
        for (Contact c : contacts)
            map.put(c.getEmail(), c.toString());
        redisTemplate.opsForValue().multiSet(map);

        for (String email : map.keySet())
            redisTemplate.expire(email, Duration.ofMinutes(5));
    }

}
