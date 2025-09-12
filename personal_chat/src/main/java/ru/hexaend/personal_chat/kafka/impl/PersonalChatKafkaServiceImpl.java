package ru.hexaend.personal_chat.kafka.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.hexaend.personal_chat.dto.kafka.PersonalChatCreated;
import ru.hexaend.personal_chat.dto.kafka.PersonalChatDeleted;
import ru.hexaend.personal_chat.entity.PersonalChat;
import ru.hexaend.personal_chat.kafka.PersonalChatKafkaService;
import ru.hexaend.personal_chat.mapper.PersonalChatResponseMapper;

@Service
public class PersonalChatKafkaServiceImpl implements PersonalChatKafkaService {
    private final KafkaTemplate<Object, Object> kafkaTemplate;
    private final PersonalChatResponseMapper personalChatResponseMapper;

    @Value("${kafka.topic}")
    private String topic;

    public PersonalChatKafkaServiceImpl(KafkaTemplate<Object, Object> kafkaTemplate, PersonalChatResponseMapper personalChatResponseMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.personalChatResponseMapper = personalChatResponseMapper;
    }

    @Override
    public void personalChatCreated(PersonalChat personalChat) {
        PersonalChatCreated personalChatCreated = personalChatResponseMapper.toKafkaCreatedDto(personalChat);
        kafkaTemplate.send(topic, personalChatCreated);
    }

    @Override
    public void personalChatDeleted(PersonalChat personalChat) {
        PersonalChatDeleted personalChatDeleted = personalChatResponseMapper.toKafkaDeletedDto(personalChat);
        kafkaTemplate.send(topic, personalChatDeleted);

    }
}
