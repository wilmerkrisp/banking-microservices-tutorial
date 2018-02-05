package com.ultimatesoftware.banking.eventsourcing.handlers;

import org.axonframework.commandhandling.AggregateAnnotationCommandHandler;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.eventsourcing.EventSourcingRepository;

public abstract class CommandHandler<T> {
    protected AggregateAnnotationCommandHandler<T> handler;
    protected CommandBus commandBus;

    public CommandHandler(EventSourcingRepository repository, CommandBus commandBus, Class<T> type) {
         handler = new AggregateAnnotationCommandHandler<T>(type, repository);
         this.commandBus = commandBus;
    }
    public void subscribe() {
        handler.subscribe(commandBus);
    }
}
