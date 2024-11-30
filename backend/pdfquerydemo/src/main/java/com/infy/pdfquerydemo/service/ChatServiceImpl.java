package com.infy.pdfquerydemo.service;

import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService<String, Document> {

    private static final String PROMPT_TEXT = """
        Tell about {query} from these relevant context {context}.
    """;

    @Autowired
    OllamaChatModel chatModel;

    @Override
    public String chat(String query, List<Document> relatedDocs) {
        String context = relatedDocs.stream().map(Document::getContent)
                .collect(Collectors.joining(System.lineSeparator()));

        PromptTemplate promptTemplate = new PromptTemplate(PROMPT_TEXT);
        Message message = promptTemplate.createMessage(Map.of("query", query, "context", context));
        Prompt prompt = new Prompt(List.of(message));
        return Optional.ofNullable(chatModel.call(prompt).getResult().getOutput().getContent()).orElse("");
    }
}
