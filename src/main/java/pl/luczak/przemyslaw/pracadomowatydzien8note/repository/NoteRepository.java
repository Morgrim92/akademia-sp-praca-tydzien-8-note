package pl.luczak.przemyslaw.pracadomowatydzien8note.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.luczak.przemyslaw.pracadomowatydzien8note.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}
