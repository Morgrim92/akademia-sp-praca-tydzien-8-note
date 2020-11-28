package pl.luczak.przemyslaw.pracadomowatydzien8note.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.luczak.przemyslaw.pracadomowatydzien8note.model.Note;
import pl.luczak.przemyslaw.pracadomowatydzien8note.repository.NoteRepository;
import pl.luczak.przemyslaw.pracadomowatydzien8note.service.NoteService;

import java.util.List;

@Controller
public class NoteController {

    private final NoteRepository repository;
    private final NoteService noteService;

    @Autowired
    public NoteController(NoteRepository repository, NoteService noteService) {
        this.repository = repository;
        this.noteService = noteService;
    }

    public List<Note> findAll() {
        return repository.findAll();
    }

    public Note findById(Long id) {
        return repository.findById(id).get();
    }

    public void save(Note note) {
        repository.save(note);
    }

    public void update(Note note) {
        noteService.update(note);
    }
}
