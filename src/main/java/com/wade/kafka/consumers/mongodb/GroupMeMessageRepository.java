package com.wade.kafka.consumers.mongodb;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * GroupMeMessageRepository
 */
public interface GroupMeMessageRepository extends MongoRepository<GroupMeMessage, String> {

    public List<GroupMeMessage> findByName(String name);
}