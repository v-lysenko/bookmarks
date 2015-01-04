package lt.mexahuk.bookmarks

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @RequestMapping("/")
    String index() {
        "Greetings from Spring Boot! Now is " + new Date()
    }

}