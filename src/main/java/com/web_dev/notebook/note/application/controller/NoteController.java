package com.web_dev.notebook.note.application.controller;

import com.web_dev.notebook.note.application.request.NoteRequest;
import com.web_dev.notebook.note.application.request.NoteUpdateStatusRequest;
import com.web_dev.notebook.note.application.response.NoteDto;
import com.web_dev.notebook.note.domain.service.NoteService;
import com.web_dev.notebook.securityJwt.domain.services.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/note")
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class NoteController {

    NoteService noteService;

    @GetMapping()
    public List<NoteDto> getNotes(){
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return noteService.getNotes(user.getUserUUID());
    }

    @GetMapping("completed")
    public List<NoteDto> getCompletedNotes(){
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return noteService.getCompletedNotes(user.getUserUUID());
    }

    @GetMapping("{userUUID}")
    public List<NoteDto> getNotesAdmin(@PathVariable String userUUID){
        return noteService.getNotes(userUUID);
    }

    @DeleteMapping("user/{id}")
    public HttpStatus deleteNoteAdmin(@PathVariable String id){
        noteService.deleteNote(id);

        return HttpStatus.OK;
    }

    @PostMapping()
    public NoteDto addNote(@RequestBody NoteRequest request){
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return noteService.addNote(request, user.getUserUUID());
    }

    @PutMapping("/status")
    public NoteDto setNoteStatus(@RequestBody NoteUpdateStatusRequest request){
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return noteService.setNoteStatus(request, user);
    }

    @PutMapping("{id}")
    public NoteDto updateNote(@PathVariable String id, @RequestBody NoteRequest request){
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return noteService.updateNote(id, request, user);
    }

    @DeleteMapping("{id}")
    public HttpStatus deleteNote(@PathVariable String id){
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        noteService.deleteNote(id, user);

        return HttpStatus.OK;
    }
}
