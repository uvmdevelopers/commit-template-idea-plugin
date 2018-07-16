import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class Items {
    private ArrayList<Item> items;

    public Items(String conf)
    {
        Properties prop = new Properties();
        String urlString = "";
        try {
            InputStream input = new FileInputStream("config.properties");
            prop.load(input);
            urlString = prop.getProperty(conf);
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        items = new ArrayList<Item>();
        try{
            URL url = new URL(urlString);
            Scanner s = new Scanner(url.openStream());

            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] newItems = line.split(";");
                if (newItems.length > 1)
                {
                    items.add(new Item(newItems[0], newItems[1]));
                }
                else
                {
                    items.add(new Item(newItems[0], ""));
                }
            }
        }
        catch(java.net.MalformedURLException ex)
        {
            JOptionPane.showMessageDialog(null, "Chybna URL: "+ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch(java.io.IOException ex)
        {
            JOptionPane.showMessageDialog(null, "Nepodarilo najit soubor: "+ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<Item> getScopes()
    {
        return this.items;
    }
}
