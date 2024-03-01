/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mticket.component.searchBar;

import mticket.utils.mticketUtils;

/**
 *
 * @author HuyTinh
 */
public class JTextFieldSearchOption extends TextFieldSearchOption {

    public JTextFieldSearchOption() {
        mticketUtils.setFont(this, "Kodchasan-Medium.ttf", 12f);
        addEventOptionSelected(new SearchOptinEvent() {
            @Override
            public void optionSelected(SearchOption option, int index) {
                setHint("Search by " + option.getName() + "...");
            }
        });
    }
}
