import com.intellij.openapi.ui.DialogWrapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CommitPanel {
    private JPanel mainPanel;
    private JComboBox changeType;
    private JComboBox changeScope;
    private JTextField shortDescription;
    private JTextArea longDescription;
    private JTextField breakingChanges;
    private JButton scopeConf;
    private JButton typeConf;
    private JTextField closedIssues;

    CommitPanel(DialogWrapper dialog) {

        Items types = new Items("urlTypeConf");
        ArrayList<Item> allTypes = types.getScopes();
        for (Item type : allTypes) {
            changeType.addItem(type);
        }

        Items scopes = new Items("urlScopeConf");
        ArrayList<Item> allScopes = scopes.getScopes();
        allScopes.add(0, new Item("", "prazdny scope"));
        for (Item scope : allScopes) {
            changeScope.addItem(scope);
        }

        scopeConf.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ConfPanel.main(new String[]{"ScopeConf"});
            }
        });

        typeConf.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ConfPanel.main(new String[]{"TypeConf"});
            }
        });
    }

    JPanel getMainPanel() {
        return mainPanel;
    }

    String getChangeType() {
        Item type = (Item) changeType.getSelectedItem();
        if (type == null) return "";
        return type.getLabel();
    }

    String getChangeScope() {
        Item scope = (Item) changeScope.getSelectedItem();
        return scope.getLabel();
    }

    String getShortDescription() {
        return shortDescription.getText().trim();
    }

    String getLongDescription() {
        return longDescription.getText().trim();
    }

    String getBreakingChanges() {
        return breakingChanges.getText().trim();
    }

    String getClosedIssues() {
        return closedIssues.getText().trim();
    }
}
