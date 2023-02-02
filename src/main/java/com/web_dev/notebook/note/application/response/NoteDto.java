package com.web_dev.notebook.note.application.response;

import com.web_dev.notebook.generics.MongoDto;
import com.web_dev.notebook.note.domain.NoteStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class NoteDto implements MongoDto {
    private String id;

    private String title;

    private String content;

    private NoteStatus status;

}