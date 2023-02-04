package com.web_dev.notebook.note.domain.repository;

import com.web_dev.notebook.note.domain.Note;
import com.web_dev.notebook.note.domain.NoteStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface NoteRepository extends MongoRepository<Note, String> {
    List<Note> findAllByUserUUID(String uuid);
    List<Note> findAllByUserUUIDAndStatus(String uuid, NoteStatus status);
}
