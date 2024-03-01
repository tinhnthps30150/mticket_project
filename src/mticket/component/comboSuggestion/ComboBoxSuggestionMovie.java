package mticket.component.comboSuggestion;

import javax.swing.JComboBox;

public class ComboBoxSuggestionMovie<E> extends JComboBox<E> {

    public ComboBoxSuggestionMovie() {
        setUI(new ComboSuggestionMovieUI());
    }
}
