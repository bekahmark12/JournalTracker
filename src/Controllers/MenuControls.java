package Controllers;

import prog.JournalEntry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MenuControls {
    ArrayList<JournalEntry> entryList = new ArrayList<>();

    public static String promptForString(String prompt) {
        BufferedReader buffy = new BufferedReader(new InputStreamReader(System.in));

        String input = null;
        boolean isValid = true;
        System.out.println(prompt);
        do {
            try {
                input = buffy.readLine();
            } catch (IOException ioe) {
                System.out.println("There was a technical hiccup. Please try again.");
            }
            isValid = (input != null && !input.trim().isEmpty());
        } while (!isValid);
        return input;
    }

    public static int promptForInt(String prompt, int min, int max) {
        int num = 0;
        boolean isValid = false;
        do {
            String input = promptForString(prompt);
            try {
                num = Integer.parseInt(input);
                isValid = (num >= min && num <= max);
            } catch (NumberFormatException nfe) {
            }
            if (!isValid) {
                System.out.println("Your input is invalid, please try again");
            }
        } while (!isValid);
        return num;
    }

    public static int promptForMenuSelection() {
        int choice = -1;
        StringBuilder sb = new StringBuilder();
        sb.append("Please choose one of the following: \n").append("1) Enter a journal entry \n").append("2) Search entries by date \n")
                .append("\n0) Exit").append("\nEnter the number of your selection: ");

        choice = promptForInt(sb.toString(), 0, 2);
        return choice;
    }

    public void run(){
        int userChoice;
        do {
            FileControls fileControls = new FileControls();
            userChoice = promptForMenuSelection();
            switch (userChoice) {
                case 1:
                        String date = promptForString("Please enter the desired date: ").toLowerCase();
                        if(compareDates(date)){
                            System.out.println("You have existing journal entries on this date.");
                        }
                        String entry = promptForString("Type your journal entry: ");
                        JournalEntry journalEntry = new JournalEntry(date, entry);
                        entryList.add(journalEntry);

                    break;
                case 2:
                        compareDates(promptForString("Enter the date you would like to search: ").toLowerCase());
                    break;
            }

            fileControls.writeToFile(entryList);
            fileControls.readFromFile();
        } while(userChoice != 0);
    }

    public boolean compareDates(String date){
        for(JournalEntry entry : entryList){
            String objectDate = entry.getDate().toLowerCase();
            if(date.equals(objectDate)){
                System.out.println(entry);
                return true;
            }
        }
        return false;
    }

}
