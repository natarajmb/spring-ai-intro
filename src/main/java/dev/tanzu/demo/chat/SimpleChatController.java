package dev.tanzu.demo.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
class SimpleChatController {

    private final ChatClient chatClient;

    SimpleChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping(value = "/chat/simple")
    String chat(@RequestParam(value = "query", defaultValue = "who are you") String query) {

        // Simple ChatClient API call to your LLM model
        return chatClient.prompt().user(query).call().content();

    }

}
