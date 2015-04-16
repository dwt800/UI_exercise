import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by weitao on 4/16/15.
 */
public class UI_List extends JFrame{

    JButton button1;
    String infoOnComponent = "";
    JList favoriteMovies, favoriteColors;
    DefaultListModel defaultListModel = new DefaultListModel();
    JScrollPane jScrollPane;


    public static void main(String[] args) {
        new UI_List();
    }

    public UI_List(){
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("My Frame");
        JPanel thePanel = new JPanel();
        button1 = new JButton("Get Answer");
        ListenForButton listenForButton = new ListenForButton();
        button1.addActionListener(listenForButton);
        thePanel.add(button1);
        String[] movies = {"Matrix","Minority Report","Big"};
        favoriteMovies = new JList(movies);
        favoriteMovies.setFixedCellHeight(30);
        favoriteMovies.setFixedCellWidth(150);
        favoriteMovies.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        String[] colors = {"Black","Blue","White","Green","Orange","Gray"};
        for(String color:colors){
            defaultListModel.addElement(color);
        }
        defaultListModel.add(2,"Purple");
        favoriteColors = new JList(defaultListModel);
        favoriteColors.setVisibleRowCount(4);
        jScrollPane = new JScrollPane(favoriteColors,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        favoriteColors.setFixedCellHeight(30);
        favoriteColors.setFixedCellWidth(150);
        thePanel.add(favoriteMovies);
        thePanel.add(jScrollPane);
        this.add(thePanel);
        this.setVisible(true);

    }

    public class ListenForButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==button1){
                if(defaultListModel.contains("Black")) infoOnComponent += "Black is Here\n";
                if(!defaultListModel.isEmpty()) infoOnComponent += "Isn't Empty\n";
                infoOnComponent +="Elements in the List" + defaultListModel.size()+"\n";
                infoOnComponent +="Last Element" + defaultListModel.lastElement() +"\n";
                infoOnComponent +="First Element" + defaultListModel.firstElement() +"\n";
                infoOnComponent +="In Index 1" + defaultListModel.get(1) +"\n";
                defaultListModel.remove(0);
                defaultListModel.removeElement("Big");

                Object[] arrayOflist = defaultListModel.toArray();
                for (Object color:arrayOflist){
                    infoOnComponent += color+"\n";
                }

                JOptionPane.showMessageDialog(UI_List.this,infoOnComponent,"Information",JOptionPane.INFORMATION_MESSAGE);
                infoOnComponent="";
            }
        }
    }
}
