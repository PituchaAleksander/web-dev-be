package com.web_dev.notebook.note.domain;

import com.web_dev.notebook.note.application.response.NoteDto;
import com.web_dev.notebook.generics.MongoModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note implements MongoModel {

    @Id
    private String id;

    private String title;

    private String content;

    private NoteStatus status;

    private String userUUID;

    public Note(String title, String content, NoteStatus status, String userUUID) {
        this.title = title;
        this.content = content;
        this.status = status;
        this.userUUID = userUUID;
    }

    public NoteDto toDto(){
        return new NoteDto(this.id, this.title, this.content, this.status);
    }
}