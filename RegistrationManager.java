import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class RegistrationManager extends Frame implements ActionListener {

    private TextField nameField, emailField, phoneField;
    private CheckboxGroup genderGroup;
    private Checkbox maleCheckbox, femaleCheckbox;
    private Choice countryChoice;
    private TextArea displayArea;
    private Button submitButton, displayButton, saveButton;

    public RegistrationManager() {
        // Set up the frame
        setTitle("User Registration Manager");
        setSize(250, 400);
        setLayout(new BorderLayout());
        setVisible(true);

        // Create components
        nameField = new TextField(20);
        emailField = new TextField(20);
        phoneField = new TextField(20);
        
        genderGroup = new CheckboxGroup();
        maleCheckbox = new Checkbox("Male", genderGroup, true);
        femaleCheckbox = new Checkbox("Female", genderGroup, false);

        countryChoice = new Choice();
        countryChoice.add("Select Country");
        countryChoice.add("USA");
        countryChoice.add("Canada");
        countryChoice.add("UK");
        countryChoice.add("India");
        countryChoice.add("Australia");

        displayArea = new TextArea(10, 30);
        displayArea.setEditable(false);

        submitButton = new Button("Submit");
        displayButton = new Button("Display");
        saveButton = new Button("Save to File");

        submitButton.addActionListener(this);
        displayButton.addActionListener(this);
        saveButton.addActionListener(this);

        Panel inputPanel = new Panel();
        inputPanel.setLayout(new GridLayout(6, 2));
        inputPanel.add(new Label("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new Label("Email:"));
        inputPanel.add(emailField);
        inputPanel.add(new Label("Phone:"));
        inputPanel.add(phoneField);
        inputPanel.add(new Label("Gender:"));
        Panel genderPanel = new Panel();
        genderPanel.add(maleCheckbox);
        genderPanel.add(femaleCheckbox);
        inputPanel.add(genderPanel);
        inputPanel.add(new Label("Country:"));
        inputPanel.add(countryChoice);

        Panel buttonPanel = new Panel();
        buttonPanel.add(submitButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(saveButton);

        add(inputPanel, BorderLayout.NORTH);
        add(displayArea, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Submit")) {
            handleSubmit();
        } else if (command.equals("Display")) {
            handleDisplay();
        } else if (command.equals("Save to File")) {
            handleSaveToFile();
        }
    }

    private void handleSubmit() {
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String gender = genderGroup.getSelectedCheckbox().getLabel();
        String country = countryChoice.getSelectedItem();

        displayArea.append("Name: " + name + "\n");
        displayArea.append("Email: " + email + "\n");
        displayArea.append("Phone: " + phone + "\n");
        displayArea.append("Gender: " + gender + "\n");
        displayArea.append("Country: " + country + "\n");
        displayArea.append("----------------------\n");

        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        genderGroup.setSelectedCheckbox(maleCheckbox);
        countryChoice.select("Select Country");
    }

    private void handleDisplay() {
        System.out.println(displayArea.getText());
    }

    private void handleSaveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("registration_data.txt"))) {
            writer.write(displayArea.getText());
            writer.flush();
            displayArea.append("Data saved to registration_data.txt\n");
        } catch (IOException ex) {
            displayArea.append("Error saving data: " + ex.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        new RegistrationManager();
    }
}
