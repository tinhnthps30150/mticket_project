package mticket.component.comboSuggestion;

import javax.swing.JComboBox;

public class ComboBoxSuggestionFood<E> extends JComboBox<E> {

    public ComboBoxSuggestionFood() {
        setUI(new ComboSuggestionFoodUI());
    }
}
