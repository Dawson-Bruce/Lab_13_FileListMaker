import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class Main {
    static Scanner in = new Scanner(System.in);
    static ArrayList<String> myArrList = new ArrayList<>();
    static boolean isDirty = false;
    static JFileChooser chooser = new JFileChooser();
    static File selectedFile;
    static String rec = "";

    public static void main(String[] args) {
        String command = "";
        boolean done = false;
        do {
            System.out.println("\n---------------------------------\n");
            display();
            System.out.println("\nA - Add an item to the list\nD - Delete an item from the list\nI - Insert an item into the list\nM - Move an item\nO - Open a list file from disk\nS - Save the current list file to disk\nC - Clear all elements from the current list\nV - View the list\nQ - Quit the program\n");
            command = SafeInput.getRegExString(in, "Please select an option", "[AaDdIiVvQqMmOoSsCc]");

            try {
                if (command.equalsIgnoreCase("q")) {
                    if (isDirty) {
                        if (SafeInput.getYNConfirm(in, "The current file isn't saved, do you want to save it?")) {
                            saveList();
                        }
                    }

                    done = SafeInput.getYNConfirm(in, "Are you sure you want to quit?");
                } else if (command.equalsIgnoreCase("a")) {
                    addItem();
                } else if (command.equalsIgnoreCase("d")) {
                    deleteItem();
                } else if (command.equalsIgnoreCase("i")) {
                    insertItem();
                } else if (command.equalsIgnoreCase("v")) {
                    viewList();
                } else if (command.equalsIgnoreCase("m")) {
                    moveItem();
                } else if (command.equalsIgnoreCase("o")) {
                    openList();
                } else if (command.equalsIgnoreCase("s")) {
                    saveList();
                } else if (command.equalsIgnoreCase("c")) {
                    clearList();
                }
            }
            catch (FileNotFoundException e) {
                System.out.println("File not found!!!");
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } while(!done);
    }

    private static void display() {
        System.out.println(myArrList);
    }

    private static void displayNumbered() {
        for (int i = 0; i < myArrList.size(); i++) {
            System.out.println("[" + i + "] - " + myArrList.get(i));
        }
    }

    private static void viewList() {
        display();
    }

    private static void insertItem() {
        displayNumbered();
        int index = SafeInput.getRangedInt(in, "Please enter the position that you want to insert to", 0, myArrList.size());
        String value = SafeInput.getNonZeroLenString(in, "Please enter the desired value");
        myArrList.add(index, value);
        isDirty = true;
    }

    private static void deleteItem() {
        displayNumbered();
        int index = SafeInput.getRangedInt(in, "Please enter the position that you want to delete", 0, myArrList.size()-1);
        myArrList.remove(index);
        isDirty = true;
    }

    private static void addItem() {
        String value = SafeInput.getNonZeroLenString(in, "Please enter the desired value");
        myArrList.add(value);
        isDirty = true;
    }

    private static void moveItem() {
        displayNumbered();
        int index = SafeInput.getRangedInt(in, "Please enter the position of the value that you want to move", 0, myArrList.size()-1);
        String value = myArrList.get(index);
        myArrList.remove(index);
        displayNumbered();
        int newPos = SafeInput.getRangedInt(in, "Please enter the position that you want to move the value to", 0, myArrList.size());
        myArrList.add(newPos, value);
        isDirty = true;
    }

    private static void clearList() {
        myArrList = new ArrayList<>();
        isDirty = true;
    }

    private static void openList() throws FileNotFoundException, IOException {
           if (isDirty) {
               if (SafeInput.getYNConfirm(in, "The current list isn't saved, do you want to save it?")) {
                   saveList();
               }
           }

               chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

               if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                   selectedFile = chooser.getSelectedFile();
                   Path file = selectedFile.toPath();
                   InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                   BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                   clearList();

                   while (reader.ready()) {
                       rec = reader.readLine();
                       myArrList.add(rec);
                   }
                   reader.close();
                   isDirty = false;
               } else
               {
                   System.out.println("No file selected!");
               }
    }

    private static void saveList() throws FileNotFoundException, IOException {
        String fileName = SafeInput.getNonZeroLenString(in, "Please enter the file name");
        Path file = Paths.get(new File(System.getProperty("user.dir")).getPath() + "\\src\\" + fileName + ".txt");

        OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

        for(String entry : myArrList)
        {
            writer.write(entry, 0, entry.length());
            writer.newLine();
        }
        writer.close();
        isDirty = false;
        System.out.println("Data file written!");
    }
}