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

import com.sg.vttpminiproject.models.TransferMarkt;


@Repository
public class TransferMarktRepository {

    @Autowired
	// always rmb to set REDIS_PASSWORD on cli
    @Qualifier("redislab")
	private RedisTemplate<String, String> redisTemplate;

    public void save(TransferMarkt table) {
		redisTemplate.opsForValue().set(table.getClubName(), table.toJson().toString());
		redisTemplate.expire(table.getClubName(), Duration.ofMinutes(5));
	}

	public void save(List<TransferMarkt> table) {
		Map<String, String> map = new HashMap<String,String>();
		for (TransferMarkt t: table)
			map.put(t.getId(), t.toJson().toString());
		redisTemplate.opsForValue().multiSet(map);

		for (String id: map.keySet())
			redisTemplate.expire(id, Duration.ofMinutes(5));
	}

	public Optional<TransferMarkt> get(String id) {
		if (!redisTemplate.hasKey(id))
			return Optional.empty();
		String data = redisTemplate.opsForValue().get(id);
		return Optional.of(TransferMarkt.create(data));
	}
}
