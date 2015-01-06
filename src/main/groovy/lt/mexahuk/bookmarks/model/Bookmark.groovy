package lt.mexahuk.bookmarks.model

import org.axonframework.commandhandling.annotation.CommandHandler
import org.axonframework.eventhandling.annotation.EventHandler
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot
import org.axonframework.eventsourcing.annotation.AggregateIdentifier

import static org.springframework.util.Assert.notNull

class Bookmark extends AbstractAnnotatedAggregateRoot<UUID> {

    @AggregateIdentifier
    UUID id
    String url
    String title

    Bookmark() {
    }

    @CommandHandler
    Bookmark(AddBookmark command) {

        command.with {
            notNull(url)
            apply(new BookmarkAdded(id: id, url: url, title: title));
        }
    }

    @EventHandler
    public void on(BookmarkAdded event) {
        id = event.id
        url = event.url
        title = event.title
    }
}
