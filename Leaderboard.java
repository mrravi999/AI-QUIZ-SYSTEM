import javax.swing.*;
import java.sql.*;

public class Leaderboard {

    public Leaderboard(){

        JFrame frame = new JFrame("Leaderboard");
        frame.setSize(400,400);

        JTextArea area = new JTextArea();
        frame.add(new JScrollPane(area));

        try{
            Connection con = DBConnection.getConnection();
            String query="SELECT * FROM scores ORDER BY score DESC";
            PreparedStatement pst=con.prepareStatement(query);
            ResultSet rs=pst.executeQuery();

            area.setText("---- Leaderboard ----\n\n");

            while(rs.next()){
                area.append("User: "+rs.getString("username")
                        +"   Score: "+rs.getInt("score")+"\n");
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        frame.setVisible(true);
    }
}
