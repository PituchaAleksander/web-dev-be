package com.web_dev.notebook.note.domain.service;

import com.web_dev.notebook.exceptions.NotFoundException;
import com.web_dev.notebook.note.application.request.NoteRequest;
import com.web_dev.notebook.note.application.request.NoteUpdateStatusRequest;
import com.web_dev.notebook.note.application.response.NoteDto;
import com.web_dev.notebook.note.domain.Note;
import com.web_dev.notebook.note.domain.NoteStatus;
import com.web_dev.notebook.note.domain.repository.NoteRepository;
import com.web_dev.notebook.securityJwt.domain.services.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class NoteService {

    final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }


    public List<NoteDto> getNotes(String userUUID){
        return noteRepository.findAllByUserUUID(userUUID).stream()
                .filter(note -> note.getStatus() != NoteStatus.COMPLETED).map(Note::toDto).toList();
    }

    public List<NoteDto> getCompletedNotes(String userUUID){
        return noteRepository.findAllByUserUUIDAndStatus(userUUID, NoteStatus.COMPLETED).stream().map(Note::toDto).toList();
    }

    public NoteDto addNote(NoteRequest request, String userUUID){
        Note note = new Note();
        note.setTitle(request.getTitle());
        note.setContent(request.getContent());
        note.setStatus(NoteStatus.TODO);
        note.setUserUUID(userUUID);

        return noteRepository.save(note).toDto();
    }

    public NoteDto updateNote(String id, NoteRequest request, UserDetailsImpl user){
        Optional<Note> noteOptionale = noteRepository.findById(id);
        if(noteOptionale.isEmpty()) {
            throw new NotFoundException("Note", user.getEmail());
        } else {
            Note note = noteOptionale.get();
            if(note.getUserUUID().equals(user.getUserUUID())){
                note.setTitle(request.getTitle());
                note.setContent(request.getContent());

                return noteRepository.save(note).toDto();
            } else {
                throw new NotFoundException("Note", user.getEmail());
            }
        }
    }

    public NoteDto setNoteStatus(NoteUpdateStatusRequest request, UserDetailsImpl user){
        Optional<Note> noteOptionale = noteRepository.findById(request.getId());
        if(noteOptionale.isEmpty()) {
            throw new NotFoundException("Note", user.getEmail());
        } else {
            Note note = noteOptionale.get();
            if(note.getUserUUID().equals(user.getUserUUID())){
                note.setStatus(request.getStatus());
                return noteRepository.save(note).toDto();
            } else {
                throw new NotFoundException("Note", user.getEmail());
            }
        }
    }

    public void deleteNote(String id){
        Optional<Note> noteOptionale = noteRepository.findById(id);
        if(noteOptionale.isEmpty()) {
            throw new NotFoundException("Note", "");
        } else {
            noteRepository.deleteById(noteOptionale.get().getId());
        }
    }

    public void deleteNote(String id, UserDetailsImpl user){
        Optional<Note> noteOptionale = noteRepository.findById(id);
        if(noteOptionale.isEmpty()) {
            throw new NotFoundException("Note", user.getEmail());
        } else {
            Note note = noteOptionale.get();
            if(note.getUserUUID().equals(user.getUserUUID())){
                noteRepository.deleteById(id);
            } else {
                throw new NotFoundException("Note", user.getEmail());
            }
        }
    }
}
