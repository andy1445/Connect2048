import twitter4j.examples.stream.PrintSampleStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by Zoe on 10/15/16.
 */

public class GameWindowView extends JFrame implements ActionListener{
    static String[] twitterFeed = {"","","","","","","","","",""};
    private GameWindow controller;
    private ArrayList<JButton> horizontal = new ArrayList<>(), vertical = new ArrayList<>();
    ArrayList<JButton> gravityButtons = new ArrayList<>();
    private JLabel twitter = new JLabel(twitterFeed[0]);
    private JPanel gamePanel = new JPanel(new GridLayout(6, 7));
    private JPanel leftPanel = new JPanel(new BorderLayout());
    Container contentPane = this.getContentPane();
    char[][] board;
    JLabel[][] labels;

    public GameWindowView(GameWindow controller){
        this.controller = controller;
        this.board = controller.getModel().getBoard();
        addElements();
        try {
            PrintSampleStream.twitterInitializer();
        } catch (Exception e) {
            twitter.setText("Error");
            e.printStackTrace();
        }
    }

    public void update(char[][] board) {
        this.board = board;

        try {
            twitter.setText(PrintSampleStream.getTweetStorage());
        } catch (Exception e) {
            twitter.setText("Error");
            e.printStackTrace();
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                labels[i][j].setText("  " + board[i][j] + "  ");
            }
        }

        this.pack();
    }


    private void addElements() {
        contentPane.setLayout(new FlowLayout());

        this.board = controller.getModel().getBoard();
        labels = new JLabel[board.length][board[0].length];

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                labels[i][j] = new JLabel("  " + board[i][j] + "  ");
                labels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                gamePanel.add(labels[i][j]);
            }
        }

        JPanel panel = new JPanel(new GridLayout(0, board.length + 2));
        JPanel panel2 = new JPanel(new GridLayout(board[0].length - 1, 0));
        JPanel panel3 = new JPanel(new BorderLayout());

        for (int i = 0; i < board[0].length; i++){
            JButton button = new JButton(" " + i + " ");
            horizontal.add(button);
            button.addActionListener(this); //this same as new GameWindowView(new GameWindow())
            panel.add(horizontal.get(i));
        }
        for (int i = 0; i < board.length; i++) {
            JButton button = new JButton(" " + i + " ");
            vertical.add(button);
            button.addActionListener(this);
            panel2.add(vertical.get(i));
        }
        if(controller.getModel().getGravity() == 0 || controller.getModel().getGravity() == 2){
            for(int i = 0; i < board.length; i++){
                vertical.get(i).setVisible(true);
            }
        } else {
            for(int i = 0; i < board[0].length; i++){
                horizontal.get(i).setVisible(true);
            }
        }
        gravityButtons.add(new JButton("<-"));
        gravityButtons.add(new JButton("->"));
        gravityButtons.add(new JButton("\\/"));
        gravityButtons.add(new JButton(("/\\")));

        for(int i = 0; i < gravityButtons.size(); i++){
            gravityButtons.get(i).addActionListener(this);
        }

        panel3.add(gravityButtons.get(0), BorderLayout.WEST);
        panel3.add(gravityButtons.get(1), BorderLayout.EAST);
        panel3.add(gravityButtons.get(2), BorderLayout.SOUTH);
        panel3.add(gravityButtons.get(3), BorderLayout.NORTH);
        panel3.add(twitter, BorderLayout.CENTER);

        leftPanel.add(gamePanel, BorderLayout.CENTER);
        leftPanel.add(panel, BorderLayout.NORTH);
        leftPanel.add(panel2, BorderLayout.EAST);
        leftPanel.add(panel3, BorderLayout.SOUTH);


        contentPane.add(leftPanel);

        this.pack();
        this.setVisible(true);


    }

    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton)e.getSource();

        if(gravityButtons.contains(source)){
            switch(gravityButtons.indexOf(source)){
                case 0: controller.getModel().setGravity(1); break;
                case 1: controller.getModel().setGravity(3); break;
                case 2: controller.getModel().setGravity(2); break;
                case 3: controller.getModel().setGravity(0); break;
            }
        }

        else {
            switch (controller.getModel().getGravity()) {
                case 0: controller.getModel().play(board.length - 1, horizontal.indexOf(source));
                    break;
                case 2: controller.getModel().play(0, horizontal.indexOf(source));
                    break;
                case 1: controller.getModel().play(vertical.indexOf(source), board[0].length - 1);
                    break;
                case 3: controller.getModel().play(vertical.indexOf(source), 0);
            }
        }
    }
}