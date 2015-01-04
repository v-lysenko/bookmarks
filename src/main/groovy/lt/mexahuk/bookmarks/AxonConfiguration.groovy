package lt.mexahuk.bookmarks

import org.axonframework.commandhandling.CommandBus
import org.axonframework.commandhandling.SimpleCommandBus
import org.axonframework.commandhandling.annotation.AnnotationCommandHandlerBeanPostProcessor
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.commandhandling.gateway.DefaultCommandGateway
import org.axonframework.commandhandling.interceptors.LoggingInterceptor
import org.axonframework.eventhandling.EventBus
import org.axonframework.eventhandling.SimpleEventBus
import org.axonframework.eventhandling.annotation.AnnotationEventListenerBeanPostProcessor
import org.axonframework.eventstore.EventStore
import org.axonframework.eventstore.fs.FileSystemEventStore
import org.axonframework.eventstore.fs.SimpleEventFileResolver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class AxonConfiguration {

    @Bean
    CommandBus createCommanBus() {
        SimpleCommandBus commandBus = new SimpleCommandBus()
        commandBus.setHandlerInterceptors([new LoggingInterceptor()])
        return commandBus
    }

    @Bean
    CommandGateway createCommanGateway(CommandBus commandBus) {
        new DefaultCommandGateway(commandBus)
    }

    @Bean
    EventStore createEventStore() {
        new FileSystemEventStore(new SimpleEventFileResolver(new File("./.events")))
    }

    @Bean
    EventBus createEventBus() {
        new SimpleEventBus()
    }

    @Bean
    AnnotationEventListenerBeanPostProcessor createEventListenerPostProcessor(EventBus eventBus) {
        AnnotationEventListenerBeanPostProcessor postProcessor = new AnnotationEventListenerBeanPostProcessor();
        postProcessor.setEventBus(eventBus)
        return postProcessor
    }

    @Bean
    AnnotationCommandHandlerBeanPostProcessor createCommandHandlerPostProcessor(CommandBus commandBus) {
        AnnotationCommandHandlerBeanPostProcessor postProcessor = new AnnotationCommandHandlerBeanPostProcessor()
        postProcessor.setCommandBus(commandBus)
        return postProcessor
    }

}
