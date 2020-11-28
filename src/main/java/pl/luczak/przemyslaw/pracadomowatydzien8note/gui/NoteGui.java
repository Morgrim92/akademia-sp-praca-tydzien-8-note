package pl.luczak.przemyslaw.pracadomowatydzien8note.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.luczak.przemyslaw.pracadomowatydzien8note.controller.NoteController;
import pl.luczak.przemyslaw.pracadomowatydzien8note.model.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Route
public class NoteGui extends VerticalLayout {

    private final NoteController noteController;

    @Autowired
    public NoteGui(NoteController noteController) {
        this.noteController = noteController;

        Grid<Note> noteGrid = new Grid<>(Note.class);
        add(noteGrid);

        Button addNoteButton = new Button("Add Note");
        Button editNoteButton = new Button("Edit Note");
        Button saveChangesButton = new Button("Save");
        TextArea titleNoteArea = new TextArea("TITLE");
        TextArea messageNoteArea = new TextArea("MESSAGE");
        TextArea newTitleNoteArea = new TextArea("NEW TITLE");
        TextArea newMessageNoteArea = new TextArea("NEW MESSAGE");

        add(titleNoteArea);
        add(messageNoteArea);
        add(addNoteButton);
        add(editNoteButton);

        addNoteButton.addClickListener(buttonClickEvent -> {
            String title = titleNoteArea.getValue();
            String message = messageNoteArea.getValue();
            if (!title.isEmpty() && !message.isEmpty()) {
                Note note = new Note(title, message);
                noteController.save(note);
            }
            List<Note> notes = noteController.findAll();
            noteGrid.setItems(notes);
        });

        editNoteButton.addClickListener(buttonClickEvent -> {
            Set<Note> selectedItems = noteGrid.getSelectionModel().getSelectedItems();
            if (!selectedItems.isEmpty()) {
                add(newTitleNoteArea);
                add(newMessageNoteArea);
                add(saveChangesButton);
            }
        });

        saveChangesButton.addClickListener(buttonClickEvent -> {
           String newTitle = newTitleNoteArea.getValue();
           String newMessage = newMessageNoteArea.getValue();
            if (!newTitle.isEmpty() && !newMessage.isEmpty()) {
                Set<Note> selectedItems = noteGrid.getSelectionModel().getSelectedItems();
                Optional<Note> note = selectedItems.stream().findAny();
                note.get().setName(newTitle);
                note.get().setMessage(newMessage);
                noteController.update(note.get());
            }
            noteGrid.setItems(new ArrayList<>());
            List<Note> notes = noteController.findAll();
            noteGrid.setItems(notes);
        });

    }
}
