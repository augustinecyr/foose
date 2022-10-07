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

	public void save(TransferMarkt tables) {
		redisTemplate.opsForValue().set(tables.getClubName(), tables.toJson().toString());
		redisTemplate.expire(tables.getClubName(), Duration.ofMinutes(5));
	}

	// saves the payload and arrange by clubname
	public void save(List<TransferMarkt> tables) {
		Map<String, String> map = new HashMap<String, String>();
		for (TransferMarkt t : tables)
			map.put(t.getClubName(), t.toJson().toString());
		redisTemplate.opsForValue().multiSet(map);

		for (String id : map.keySet())
			redisTemplate.expire(id, Duration.ofMinutes(5));
	}

	// input clubname e.g Manchester United, Arsenal to get the json
	public Optional<TransferMarkt> get(String clubName) {
		if (!redisTemplate.hasKey(clubName))
			return Optional.empty();
		String data = redisTemplate.opsForValue().get(clubName);
		return Optional.of(TransferMarkt.create(data));
	}
}
