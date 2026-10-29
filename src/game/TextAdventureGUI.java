
package game;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class TextAdventureGUI extends JFrame {

    private JTextArea roomDescriptionArea;
    private JTextArea responseArea;
    private JTextField inputField;

    // Theme colors
    private final Color backgroundColor = new Color(30, 30, 35);
    private final Color inputBackground = new Color(45, 45, 52);
    private final Color textColor = new Color(230, 230, 230);
    private final Color responseColor = new Color(190, 220, 190);
    private final Color borderColor = new Color(90, 90, 100);
    private final Color titleColor = new Color(200, 200, 210);

    // Fonts
    private final Font gameFont =
            new Font("Arial", Font.PLAIN, 17);
    private final Font inputFont =
            new Font("Monospaced", Font.PLAIN, 17);
    private final Font borderFont =
            new Font("SansSerif", Font.BOLD, 13);

    public void createGUI(
            String roomDescription,
            String initialResponse,
            ActionListener commandListener) {

        setTitle("Text Adventure");
        setSize(700, 600);
        setMinimumSize(new Dimension(500, 400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(backgroundColor);
        setLayout(new BorderLayout(8, 8));

        // Top: Room description
        roomDescriptionArea = new JTextArea(roomDescription);
        roomDescriptionArea.setEditable(false);
        roomDescriptionArea.setLineWrap(true);
        roomDescriptionArea.setWrapStyleWord(true);
        roomDescriptionArea.setFont(gameFont);
        roomDescriptionArea.setForeground(textColor);
        roomDescriptionArea.setBackground(backgroundColor);
        roomDescriptionArea.setCaretColor(textColor);
        roomDescriptionArea.setMargin(new Insets(10, 12, 10, 12));

        JScrollPane roomScroll = new JScrollPane(roomDescriptionArea);
        roomScroll.setBorder(createSectionBorder("Room Description"));
        roomScroll.getViewport().setBackground(backgroundColor);
        roomScroll.setBackground(backgroundColor);

        // Middle: Game responses
        responseArea = new JTextArea(initialResponse);
        responseArea.setEditable(false);
        responseArea.setLineWrap(true);
        responseArea.setWrapStyleWord(true);
        responseArea.setFont(gameFont);
        responseArea.setForeground(responseColor);
        responseArea.setBackground(backgroundColor);
        responseArea.setCaretColor(responseColor);
        responseArea.setMargin(new Insets(10, 12, 10, 12));

        JScrollPane responseScroll = new JScrollPane(responseArea);
        responseScroll.setBorder(createSectionBorder("Responses"));
        responseScroll.getViewport().setBackground(backgroundColor);
        responseScroll.setBackground(backgroundColor);

        // Bottom: Player input
        inputField = new JTextField();
        inputField.setFont(inputFont);
        inputField.setForeground(Color.WHITE);
        inputField.setBackground(inputBackground);
        inputField.setCaretColor(Color.WHITE);
        inputField.setMargin(new Insets(8, 10, 8, 10));
        inputField.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(borderColor),
                        BorderFactory.createEmptyBorder(2, 4, 2, 4)
                )
        );

        inputField.addActionListener(e -> {
            String command = inputField.getText().trim();

            if (!command.isEmpty()) {
                if (commandListener != null) {
                    commandListener.actionPerformed(
                            new java.awt.event.ActionEvent(
                                    this,
                                    java.awt.event.ActionEvent.ACTION_PERFORMED,
                                    command
                            )
                    );
                }

                inputField.setText("");
            }
        });

        JPanel roomPanel = new JPanel(new BorderLayout());
        roomPanel.setBackground(backgroundColor);
        roomPanel.add(roomScroll, BorderLayout.CENTER);

        JPanel responsePanel = new JPanel(new BorderLayout());
        responsePanel.setBackground(backgroundColor);
        responsePanel.add(responseScroll, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        inputPanel.setBackground(backgroundColor);
        inputPanel.setBorder(createSectionBorder("Enter Command"));
        inputPanel.add(inputField, BorderLayout.CENTER);

        // Layout: description, responses, and input
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(backgroundColor);
        contentPanel.setBorder(
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
        );

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(4, 0, 4, 0);

        // Room description gets 35% of available height
        gbc.gridy = 0;
        gbc.weighty = 0.35;
        contentPanel.add(roomPanel, gbc);

        // Responses get 50% of available height
        gbc.gridy = 1;
        gbc.weighty = 0.50;
        contentPanel.add(responsePanel, gbc);

        // Input gets 15% of available height
        gbc.gridy = 2;
        gbc.weighty = 0.15;
        contentPanel.add(inputPanel, gbc);

        add(contentPanel, BorderLayout.CENTER);

        setVisible(true);
        inputField.requestFocusInWindow();
    }

    // Create a consistent styled border for each section
    private TitledBorder createSectionBorder(String title) {
        TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(borderColor),
                title
        );

        border.setTitleFont(borderFont);
        border.setTitleColor(titleColor);

        return border;
    }

    // Update the room description
    public void setRoomDescription(String description) {
        roomDescriptionArea.setText(description);
        roomDescriptionArea.setCaretPosition(0);
    }

    // Replace the response text
    public void setResponse(String response) {
        responseArea.setText(response);
        responseArea.setCaretPosition(responseArea.getDocument().getLength());
    }

    // Add a response while keeping previous responses
    public void appendResponse(String response) {
        responseArea.append(response + "\n");
        responseArea.setCaretPosition(responseArea.getDocument().getLength());
    }
}