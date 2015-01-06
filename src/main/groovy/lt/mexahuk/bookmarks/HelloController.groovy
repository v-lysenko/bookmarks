package lt.mexahuk.bookmarks
import lt.mexahuk.bookmarks.model.AddBookmark
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    private CommandGateway commandGateway

    @Autowired
    HelloController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway
    }

    @RequestMapping("/")
    String index() {
        "Greetings from Spring Boot! Now is " + new Date()
    }

    @RequestMapping("/bookmarks/new")
    String newBookmark(
//            @RequestParam("title") String title, @RequestParam("url") String url
    ) {
        String title = "I'm a bookmark"
        String url = "http://aaa.aaa"
        commandGateway.send(new AddBookmark(id: UUID.randomUUID(), title: title, url: url))
        "new bookmark registered"
    }

}