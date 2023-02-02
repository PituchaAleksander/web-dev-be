package com.web_dev.notebook.note.application.request;

import com.web_dev.notebook.note.domain.NoteStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class NoteUpdateStatusRequest {
    private NoteStatus status;
    private String id;
}
