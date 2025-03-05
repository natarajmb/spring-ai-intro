package dev.tanzu.demo.feedback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.document.DocumentReader;
import org.springframework.ai.reader.JsonReader;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
class LoadController {

    private final Logger logger = LoggerFactory.getLogger(LoadController.class);
    private final VectorStore vectorStore;

    @Value("classpath:data/feedback.json")
    private Resource jsonEntries;

    LoadController(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @GetMapping("/feedback/load/json")
    public String loadJson() throws IOException {

        logger.info("Loading entries from {}", jsonEntries.getURL());

        // read JSON data into a document per entry
        DocumentReader reader = new JsonReader(
                jsonEntries,
                "timestamp",
                "name",
                "email",
                "score",
                "feedback"
        );
        
        // get the list of documents
        List<Document> documents = reader.get();

        // add the list to the vector store
        logger.info("Adding {} documents to the vector store {}", documents.size(), vectorStore.getName());
        this.vectorStore.add(documents);

        return "vector store loaded with %s documents.".formatted(documents.size());

    }

}
