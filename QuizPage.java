import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizPage {

    int score = 0;
    int q = 0;

    String questions[] = {
            "Which language is used for Java?",
            "Who developed Java?",
            "Which keyword is used for class?"
    };

    String options[][] = {
            {"Python","Java","C++","HTML"},
            {"James Gosling","Elon Musk","Bill Gates","Mark"},
            {"function","define","class","new"}
    };

    int answers[] = {1,0,2};

    JLabel questionLabel;
    JRadioButton op1,op2,op3,op4;
    ButtonGroup bg;
    JButton nextBtn;

    public QuizPage(){

        JFrame frame = new JFrame("AI Smart Quiz");
        frame.setSize(500,400);
        frame.setLayout(null);

        questionLabel = new JLabel();
        questionLabel.setBounds(50,50,400,30);
        frame.add(questionLabel);

        op1 = new JRadioButton();
        op1.setBounds(50,100,200,30);
        frame.add(op1);

        op2 = new JRadioButton();
        op2.setBounds(50,140,200,30);
        frame.add(op2);

        op3 = new JRadioButton();
        op3.setBounds(50,180,200,30);
        frame.add(op3);

        op4 = new JRadioButton();
        op4.setBounds(50,220,200,30);
        frame.add(op4);

        bg = new ButtonGroup();
        bg.add(op1); bg.add(op2); bg.add(op3); bg.add(op4);

        nextBtn = new JButton("Next");
        nextBtn.setBounds(200,270,100,30);
        frame.add(nextBtn);

        loadQuestion();

        nextBtn.addActionListener(e -> checkAnswer());

        frame.setVisible(true);
    }

    void loadQuestion(){
        if(q<questions.length){
            questionLabel.setText(questions[q]);
            op1.setText(options[q][0]);
            op2.setText(options[q][1]);
            op3.setText(options[q][2]);
            op4.setText(options[q][3]);
        } else {
            String message;

            if(score==3){
                message="Excellent! You are Genius 😎";
            } else if(score==2){
                message="Good! Keep practicing 👍";
            } else {
                message="Need improvement. Study more 📚";
            }

            JOptionPane.showMessageDialog(null,"Quiz finished! Score: "+score+"\n"+message);

            try{
                java.sql.Connection con = DBConnection.getConnection();
                String query="INSERT INTO scores(username,score) VALUES(?,?)";
                java.sql.PreparedStatement pst=con.prepareStatement(query);

                pst.setString(1,"student");   // we will improve later
                pst.setInt(2,score);
                pst.executeUpdate();

                new Leaderboard();


            }catch(Exception e){
                e.printStackTrace();
            }

        }
    }

    void checkAnswer(){
        int selected = -1;

        if(op1.isSelected()) selected=0;
        if(op2.isSelected()) selected=1;
        if(op3.isSelected()) selected=2;
        if(op4.isSelected()) selected=3;

        if(selected==answers[q]) score++;

        q++;
        bg.clearSelection();
        loadQuestion();
    }
}
