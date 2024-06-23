import java.io.IOException;
import java.util.Scanner;

public class ChatBot {

    private Scanner scanner;

    public ChatBot() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Hello! I'm your Java ChatBot. How can I assist you today?");
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            }
            processCommand(input);
        }
    }

    private void processCommand(String input) {
        if (input.startsWith("open application")) {
            String appName = input.replace("open application", "").trim();
            openApplication(appName);
        } else if (input.startsWith("search")) {
            String query = input.replace("search", "").trim();
            searchWeb(query);
        } else {
            System.out.println("Sorry, I didn't understand that command.");
        }
    }

    private void openApplication(String appName) {
        String command;
        try {
            switch (appName.toLowerCase()) {
                case "notepad":
                    command = "notepad";
                    break;
                case "calculator":
                    command = "calc";
                    break;
                default:
                    System.out.println("Application not recognized.");
                    return;
            }
            Runtime.getRuntime().exec(command);
            System.out.println("Opening " + appName + "...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void searchWeb(String query) {
        try {
            String url = "https://www.google.com/search?q=" + query.replace(" ", "+");
            Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", url});
            System.out.println("Searching for: " + query);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ChatBot chatBot = new ChatBot();
        chatBot.start();
    }
}
