package com.td.todolist240317.domain.todo.todo.service;

import com.td.todolist240317.domain.todo.todo.entity.Hashtag;
import com.td.todolist240317.domain.todo.todo.entity.Keyword;
import com.td.todolist240317.domain.todo.todo.entity.Todo;
import com.td.todolist240317.domain.todo.todo.repository.HashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HashtagService {

    private final KeywordService keywordService;
    private final HashtagRepository hashtagRepository;

    public void addHashtags(Todo todo, String keywordContentsStr) {
        List<String> keywordContents = Arrays.stream(keywordContentsStr.split("#"))
                .map(String::trim)
                .filter(s -> s.length() > 0)
                .collect(Collectors.toList());

        keywordContents.forEach(keywordContent ->
                saveHashtag(todo, keywordContent));
    }

    private Hashtag saveHashtag(Todo todo, String keywordContent) {
        Keyword keyword = keywordService.save(keywordContent);

        Optional<Hashtag> opHashtag = hashtagRepository.findByTodoIdAndKeyword(todo.getId(), keyword);

        if (opHashtag.isPresent()) {
            return opHashtag.get();
        }

        Hashtag hashtag = Hashtag.builder()
                .todo(todo)
                .keyword(keyword)
                .build();

        hashtagRepository.save(hashtag);

        return hashtag;
    }
}