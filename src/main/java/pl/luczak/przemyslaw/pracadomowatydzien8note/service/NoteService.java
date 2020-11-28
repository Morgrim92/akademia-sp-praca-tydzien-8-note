package pl.luczak.przemyslaw.pracadomowatydzien8note.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.luczak.przemyslaw.pracadomowatydzien8note.model.Note;
import pl.luczak.przemyslaw.pracadomowatydzien8note.repository.NoteRepository;

import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository repository;

    @Autowired
    public NoteService(NoteRepository repository) {
        this.repository = repository;
    }

    public void update(Note note) {
        Optional<Note> noteInDb = repository.findById(note.getId());
        if (noteInDb.isPresent()) {
            if (!note.getName().isEmpty() && !note.getMessage().isEmpty()) {
                noteInDb.get().setName(note.getName());
                noteInDb.get().setMessage(note.getMessage());
                repository.save(noteInDb.get());
            }
        }
    }
}
