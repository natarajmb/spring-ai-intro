package dev.tanzu.demo.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class PromptChatController {

    @Value("classpath:prompts/joke.st")
    private Resource userPrompt;

    private final ChatClient chatClient;
    
    PromptChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping(value = "/chat/joke")
    String chat(
        @RequestParam(value = "topic", defaultValue = "food") String topic,
        @RequestParam(value = "lang", defaultValue = "English") String language
    ) {

        // Prompt template enable us to safely inject user input into prompt.
        // Text in {} is replaced by the value of the variable with the same name.
        
        return chatClient
            .prompt()
            .user( u -> u
                .text(userPrompt)
                .param("topic", topic)
                .param("language", language))
            .call()
            .content();

    }

}
