package lt.mexahuk.bookmarks.model

import org.axonframework.commandhandling.CommandBus
import org.axonframework.commandhandling.annotation.AggregateAnnotationCommandHandler
import org.axonframework.eventhandling.EventBus
import org.axonframework.eventsourcing.EventSourcingRepository
import org.axonframework.eventstore.EventStore
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BookmarkConfiguration {

    @Bean
    AggregateAnnotationCommandHandler<Bookmark> bookmarkCommandHandler(CommandBus commandBus, EventStore eventStore, EventBus eventBus) {
        EventSourcingRepository<Bookmark> repository = new EventSourcingRepository<>(Bookmark.class, eventStore)
        repository.setEventBus(eventBus)
        new AggregateAnnotationCommandHandler<>(Bookmark.class, repository, commandBus);
    }
}
