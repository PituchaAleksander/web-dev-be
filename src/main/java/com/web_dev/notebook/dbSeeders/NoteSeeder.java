package com.web_dev.notebook.dbSeeders;

import com.web_dev.notebook.note.domain.Note;
import com.web_dev.notebook.note.domain.NoteStatus;
import com.web_dev.notebook.note.domain.repository.NoteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class NoteSeeder implements ISeeder {

    NoteRepository noteRepository;

    @Override
    public void seed() {
        List<Note> books = new ArrayList<>();

        books.add(new Note("Lalka", "Bolesław Prus", NoteStatus.TODO, ""));
        books.add(new Note("Dziady", "Adam Mickiewicz", NoteStatus.COMPLETED, ""));
        books.add(new Note("Pan Tadeusz", "Adam Mickiewicz", NoteStatus.IN_PROGRESS, ""));
        books.add(new Note("Kamizelka", "Bolesław Prus", NoteStatus.COMPLETED, ""));

        for (var book : books) {
            noteRepository.save(book);
        }
    }

    @Override
    public void resetDb() {
        log.info("Reseting all db.");
        noteRepository.deleteAll();
        log.info("End reseting db.");

    }
}
