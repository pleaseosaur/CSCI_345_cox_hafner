/*
 * Author: Peter Hafner and Andrew Cox
 * Date: 16 May 2023
 * Purpose: Main
 */

// imports


import javax.swing.*;
import java.awt.*;

public class Game {

    private JFrame frame;
    private JPanel panel;
    private JButton moveButton, takeRoleButton, rehearseButton, actButton;
    private JLabel playerLabel;
    private JTextArea playerStatsArea;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Game().GUI());
    }

    private void GUI() {
        frame = new JFrame("Deadwood"); // Create and set up the window.
        panel = new JPanel(); // Create a panel to hold all other components
        panel.setLayout(new BorderLayout()); // Use BorderLayout for panel

        JPanel buttonPanel = new JPanel(); // Create a separate panel for the buttons
        moveButton = new JButton("Move"); // Create buttons
        takeRoleButton = new JButton("Take Role");
        rehearseButton = new JButton("Rehearse");
        actButton = new JButton("Act");

        buttonPanel.add(moveButton); // Add the buttons to the button panel
        buttonPanel.add(takeRoleButton);
        buttonPanel.add(rehearseButton);
        buttonPanel.add(actButton);

        JPanel statsPanel = new JPanel(); // Create a panel for player stats
        statsPanel.setLayout(new BorderLayout()); // Use BorderLayout for panel
        playerLabel = new JLabel("Current Player: "); // Create a label to display player stats
        playerStatsArea = new JTextArea(); // Create a text area to display player stats
        statsPanel.add(playerLabel, BorderLayout.NORTH); // Add the stats label to the stats panel
        statsPanel.add(playerStatsArea, BorderLayout.CENTER); // Add the text area to the stats panel

        JLayeredPane layeredPane = new JLayeredPane(); // Create the JLayeredPane to hold the board, cards, tokens, etc.

        ImageIcon board = new ImageIcon(getClass().getResource("images/board.jpg")); // Create the board image
        board.setImage(board.getImage().getScaledInstance(1200, 900, Image.SCALE_DEFAULT));
        JLabel boardLabel = new JLabel(board); // Add the board image to a label
        boardLabel.setBounds(0, 0, board.getIconWidth(), board.getIconHeight()); // Set the size of the board label

        // testing - dummy player icon
        ImageIcon playerIcon = new ImageIcon("resources/images/players/red-1.png"); // TODO - images aren't loading
        JLabel playerLabel = new JLabel(playerIcon);
        playerLabel.setBounds(200, 200, 100, 150);

        // testing - dummy card
        JLabel card = new JLabel("Card");
        card.setBounds(200, 200, 100, 150);
        card.setBackground(Color.BLUE);
        card.setOpaque(true);

        // testing - layering
        layeredPane.add(boardLabel, Integer.valueOf(1));
        layeredPane.add(playerLabel, Integer.valueOf(2));
        layeredPane.add(card, Integer.valueOf(3));

        panel.add(buttonPanel, BorderLayout.NORTH); // Add the button panel to the north of the main panel
        panel.add(layeredPane, BorderLayout.WEST); // Add the layered pane to the west of the main panel
        panel.add(statsPanel, BorderLayout.EAST); // Add the stats panel to the east of the main panel

        frame.add(panel); // Add the panel to the frame
        frame.setSize(1200, 900); // Set the size of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Player selection dialog
        Integer[] choices = { 2, 3, 4, 5, 6, 7, 8 }; // Number of players choices
        JComboBox<Integer> numPlayers = new JComboBox<>(choices); // Create a combo box for the number of players

        Object[] message = {
                "Please select the number of players:", numPlayers
        };

        Object[] options = { "Start Game", "Cancel" }; // Buttons for the dialog

        int option = JOptionPane.showOptionDialog(null, message, "Game Setup", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (option == 0) { // 'Start Game' selected
            Integer input = (Integer) numPlayers.getSelectedItem(); // Get the number of players from the combo box
            System.out.println("Starting game with " + input + " players!");
            // game logic
        } else { // 'Cancel' selected or dialog closed
            System.out.println("Game Setup Cancelled!");
            // exit
        }
    }
}


