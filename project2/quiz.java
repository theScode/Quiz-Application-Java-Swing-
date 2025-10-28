import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class quiz extends JFrame implements ActionListener 
{
    String[] questions = {
        "Which language is used for web apps?",
        "Which company developed Java?",
        "Which keyword is used to inherit a class in Java?",
        "Which data structure uses FIFO?",
        "What does JVM stand for?"
    };

    String[][] options = {
        {"Python", "JavaScript", "C++", "C"},
        {"Microsoft", "Sun Microsystems", "Apple", "Google"},
        {"this", "super", "extends", "implements"},
        {"Stack", "Queue", "Array", "Tree"},
        {"Java Virtual Machine", "Java Variable Model", "Just Virtual Machine", "Joint Visual Model"}
    };
    int[] answers = {2, 2, 3, 2, 1};
    /*
    index = 0 -> first question
    correct -> This keeps count of how many correct answers the user has given
    */
    int index=0,correct=0;
    JFrame frame;
    JLabel questionLabel;
    JRadioButton[] choices = new JRadioButton[4];
    ButtonGroup group;
    JButton nextButton;
    JLabel resultLabel;
    
    quiz()
    {
        frame=new JFrame("QUIZ APPLICATION");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        
        questionLabel=new JLabel("",SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(questionLabel, BorderLayout.NORTH);
        
        JPanel optionPanel=new JPanel();
        optionPanel.setLayout(new GridLayout(4,1));
        group=new ButtonGroup();
        
        for(int i=0;i<4;i++)
        {
            choices[i]=new JRadioButton();
            choices[i].setSelected(false);
            group.add(choices[i]);
            optionPanel.add(choices[i]);
        }
        frame.add(optionPanel, BorderLayout.CENTER);
        nextButton = new JButton("Next");
        nextButton.addActionListener(this);
        frame.add(nextButton, BorderLayout.SOUTH);
        loadQuestion();
        frame.setVisible(true);
    }
    
    public void loadQuestion() //it displays next question and its answers
    {
        if(index<questions.length)
        {
            questionLabel.setText("Q"+(index+1)+". "+questions[index]);
            for(int i=0;i<4;i++)
            {
                choices[i].setText(options[index][i]);
                choices[i].setSelected(false);
            }
        }
        else
            showResult();
    }
    
    public void showResult()
    {
        frame.getContentPane().removeAll();
        frame.setLayout(new GridLayout(2, 1));

        resultLabel = new JLabel("Your Score: " + correct + "/" + questions.length, SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(resultLabel);
        JButton restartButton = new JButton("Restart Quiz");
        restartButton.setFont(new Font("Arial", Font.PLAIN, 16));
        restartButton.addActionListener(e -> {
//        index = 0;
//        correct = 0;
//        frame.getContentPane().removeAll();
        frame.dispose();
        new quiz(); 
        
        frame.revalidate();
        frame.repaint();
    });
    frame.add(restartButton);
        frame.revalidate();
        frame.repaint();
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        for(int i=0;i<4;i++)
        {
            if(choices[i].isSelected())
            {
                if(i+1==answers[index])
                {
                    correct++;
                    break;
                }
                    
            }
            
        }
        index++;
        loadQuestion();
    }
}
/*
Each row = one question
Each column = the four options for that question
*/