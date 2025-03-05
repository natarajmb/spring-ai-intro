# spring-ai-intro

Gentle introduction to Spring AI.

## How to run

```shell
./mvnw spring-boot:run
```

## Chat Client API Examples

All chat examples are packaged in `chat` package, e.g.
* SimpleChatController
* PromptChatController
* RoleController


### SimpleChatController

`SimpleChatController` demonstrates a simple ChatClient API that connects to your LLM model.

```shell
http localhost:8080/chat/simple
```

```shell
http 'localhost:8080/chat/simple?query=tell-me-a-joke'
```

### PromptChatController

`PromptChatController` demonstrates simple prompt templating.

```shell
http 'localhost:8080/chat/joke'
```

```shell
http 'localhost:8080/chat/joke?topic=ai'
```

```shell
http 'localhost:8080/chat/joke?lang=marati'
```

### RoleController

`RoleController` demonstrates use of `user` and `system` prompts.


```shell
http localhost:8080/chat/fruit
```

```shell
http localhost:8080/chat/veggie
```
