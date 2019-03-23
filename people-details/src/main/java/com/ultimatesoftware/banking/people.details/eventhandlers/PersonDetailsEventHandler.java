package com.ultimatesoftware.banking.people.details.eventhandlers;

import com.ultimatesoftware.banking.api.operations.AxonEventHandler;
import com.ultimatesoftware.banking.api.repository.Repository;
import com.ultimatesoftware.banking.people.details.models.PersonDetails;
import com.ultimatesoftware.banking.people.events.DetailsUpdatedEvent;
import com.ultimatesoftware.banking.people.events.PersonCreatedEvent;
import org.axonframework.axonserver.connector.AxonServerConfiguration;
import org.axonframework.eventhandling.EventHandler;
import org.bson.types.ObjectId;

import javax.inject.Singleton;

@Singleton
public class PersonDetailsEventHandler extends AxonEventHandler {
    private Repository<PersonDetails> mongoRepository;

    public PersonDetailsEventHandler(Repository<PersonDetails> mongoRepository, AxonServerConfiguration axonServerConfiguration) {
        super(axonServerConfiguration);
        this.mongoRepository = mongoRepository;
    }

    @EventHandler
    public void on(PersonCreatedEvent event) {
        mongoRepository.add(new PersonDetails(new ObjectId(event.getId()), event.getFirstName(), event.getLastName()));
    }

    @EventHandler
    public void on(DetailsUpdatedEvent event) {
        mongoRepository.replaceOne(event.getId(), new PersonDetails(new ObjectId(event.getId()), event.getFirstName(), event.getLastName()));
    }
}