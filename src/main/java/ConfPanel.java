import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.Arrays;
import java.util.Properties;

public class ConfPanel extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField URL;

    private Properties prop;
    private String conf;

    public ConfPanel(String _conf) {
        this.conf = _conf;
        prop = new Properties();
        try {
            InputStream input = new FileInputStream("config.properties");
            prop.load(input);
            String urlString = prop.getProperty(this.conf);
            URL.setText(urlString);
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        prop.setProperty(this.conf, URL.getText());
        try {
            OutputStream output = new FileOutputStream("config.properties");
            prop.store(output, null);
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        this.dispose();
    }

    public static void main(String[] args) {
        String conf = "";
        if (Arrays.asList(args).contains("ScopeConf"))
        {
            conf = "urlScopeConf";
        }
        else if (Arrays.asList(args).contains("TypeConf"))
        {
            conf = "urlTypeConf";
        }

        ConfPanel dialog = new ConfPanel(conf);
        dialog.setLocationRelativeTo(null);
        dialog.pack();

        dialog.setVisible(true);
    }
}
