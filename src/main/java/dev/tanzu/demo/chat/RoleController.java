package dev.tanzu.demo.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class RoleController {

    @Value("classpath:prompts/role.st")
    private Resource systemPrompt;

    private final ChatClient chatClient;    
    RoleController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping(value = "/chat/fruit")
    String fruitQuestion(
        @RequestParam (
            name = "question",
            defaultValue = "What is a colour of a banana?"
        ) String question ) {

        // Ask a question about a fruit
        return chatClient.prompt().system(systemPrompt).user(question).call().content();

    }

    @GetMapping(value = "/chat/veggie")
    String veggieQuestion(
        @RequestParam (
            name = "question",
            defaultValue = "What is a colour of a carrot?"
        ) String question ) {

        // Ask a question about a vegetable
        return chatClient.prompt().system(systemPrompt).user(question).call().content();

    }

}
