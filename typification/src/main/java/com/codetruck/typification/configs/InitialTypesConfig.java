package com.codetruck.typification.configs;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.UUID;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.codetruck.typification.models.TypeModel;
import com.codetruck.typification.services.TypeService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class InitialTypesConfig implements ApplicationRunner {

	final TypeService typeService;
	
	public InitialTypesConfig(TypeService typeService) {
		this.typeService = typeService;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		if (this.typeService.countParentTypes() != 0) {
			return;
		}
		
		log.info("Initial types not found, registering initial ones...");
		
		final UUID randomUUID = UUID.randomUUID();
		
		var typeServiceInformation = new TypeModel();
		typeServiceInformation.setActive(Boolean.TRUE);
		typeServiceInformation.setAsk("Contact motivation?");
		typeServiceInformation.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
		typeServiceInformation.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
		typeServiceInformation.setDescription("Service Information");
		typeServiceInformation.setUserIdCreated(randomUUID); // TODO: usar um user verdadeiro integrando o UserService
		typeServiceInformation.setUserIdLastUpdated(randomUUID);
		
		var typeServiceRequest = new TypeModel();
		typeServiceRequest.setActive(Boolean.TRUE);
		typeServiceRequest.setAsk("Contact motivation?");
		typeServiceRequest.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
		typeServiceRequest.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
		typeServiceRequest.setDescription("Service Request");
		typeServiceRequest.setUserIdCreated(randomUUID); // TODO: usar um user verdadeiro integrando o UserService
		typeServiceRequest.setUserIdLastUpdated(randomUUID);
		
		var typeServiceCancellation = new TypeModel();
		typeServiceCancellation.setActive(Boolean.TRUE);
		typeServiceCancellation.setAsk("Contact motivation?");
		typeServiceCancellation.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
		typeServiceCancellation.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
		typeServiceCancellation.setDescription("Service Cancellation");
		typeServiceCancellation.setUserIdCreated(randomUUID); // TODO: usar um user verdadeiro integrando o UserService
		typeServiceCancellation.setUserIdLastUpdated(randomUUID);
		
		this.typeService.saveAll(Arrays.asList(typeServiceInformation, typeServiceRequest, typeServiceCancellation));
	}

}
