package lt.mexahuk.bookmarks.model

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier

class AddBookmark {

    @TargetAggregateIdentifier
    UUID id
    String url
    String title
}
