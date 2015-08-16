/**
 * Yossi Silberhaft 210028924 89-281-04
 * Daniel Blinick WN654676 89-281-04
 * Binyamin Greenberg 200220671 89-281-04
 * Steven Lapp 204785240 89-281-05
 */

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.GroupLayout;


/**
 * This class deals with the GUI which has the name FrontPage
 */
public class FrontPage extends JFrame {
    //Deceleration of variables
    String userInput;
    String result;
    ResultSet rs;
    ArrayList columnNames = new ArrayList();
    ArrayList data = new ArrayList();
    JTable table;
    JFrame frame;

    public FrontPage(){
        initComponents();
    }

    /**
     * This Button will deal with executing DML queries
     * It will also check whether the query is in the correct form.
     * @param a not used here
     */
    private void btnrunDMLActionPerformed(ActionEvent a) {
        //Clears the array list before the running of a query
        columnNames.clear();
        //Clears the array list before the running of a Query
        data.clear();
        //Gets the users input from the TextArea
        userInput = taInput.getText();

        //Checks that the query is in the correct form
        if(userInput.startsWith("SELECT") || userInput.startsWith("SHOW")){
            try{
                Statement stat = DBProject.connect.createStatement();
                //Runs the users query
                rs = stat.executeQuery(userInput);

                ResultSetMetaData md = rs.getMetaData();

                //Gets the number of columns
                int columns = md.getColumnCount();
                for(int i = 1; i <= columns; i++){
                    columnNames.add(md.getColumnName(i));
                }

                //  Get row data
                while (rs.next()) {
                    ArrayList row = new ArrayList(columns);
                    for (int i = 1; i <= columns; i++) {
                        row.add( rs.getObject(i) );
                    }
                    data.add( row );
                }

            }catch (SQLException e){
                if(e.toString().contains("syntax")){
                    JOptionPane.showMessageDialog(frame, "WRONG QUERY STRUCTURE");
                }
                else {
                    JOptionPane.showMessageDialog(frame, "LOGICAL ERROR");
                }
            }

            Vector columnNamesVector = new Vector();
            Vector dataVector = new Vector();
            for (int i = 0; i < data.size(); i++)
            {
                ArrayList subArray = (ArrayList)data.get(i);
                Vector subVector = new Vector();
                for (int j = 0; j < subArray.size(); j++)
                {
                    subVector.add(subArray.get(j));
                }
                dataVector.add(subVector);
            }

            for (int i = 0; i < columnNames.size(); i++ )
                columnNamesVector.add(columnNames.get(i));

            //  Create table with database data
            JTable tempTable = new JTable(dataVector, columnNamesVector) {
                public Class getColumnClass(int column) {
                    for (int row = 0; row < getRowCount(); row++) {
                        Object o = getValueAt(row, column);
                        if (o != null) {
                            return o.getClass();
                        }
                    }
                    return Object.class;
                }
            };

            table=tempTable;
            revalidate();
            scrlpMain.setViewportView(table);
            getContentPane().add(scrlpMain);
        }
        else {
            JOptionPane.showMessageDialog(frame, "WRONG QUERY STRUCTURE");
        }
    }


    /**
     * This button will deal with the running of a DDL query
     * It will do a check that will ensure that the query is correct
     * @param a not used here
     */
    private void btnRunDDLActionPerformed(ActionEvent a) {
        userInput = taInput.getText();
        if(userInput.startsWith("SELECT") || userInput.startsWith("SHOW")){
            result = "WRONG QUERY STRUCTURE";
            JOptionPane.showMessageDialog(frame, result);
            taInput.setText("");
        }
        else{
            //Try to run the DDL on the database
            try {
                Statement stat = DBProject.connect.createStatement();
                stat.executeUpdate(userInput);
                JOptionPane.showMessageDialog(frame, "Update completed");
            //Catch if there is a problem will the statement
            } catch (SQLException e) {
                if(e.toString().contains("syntax")){
                    JOptionPane.showMessageDialog(frame, "WRONG QUERY STRUCTURE");
                }
                else {
                    JOptionPane.showMessageDialog(frame, "LOGICAL ERROR");
                }
            }
        }
    }

    private void button1ActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void btnRunActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Yosef Silberhaft
        btnRunDDL = new JButton();
        scrlpMain = new JScrollPane();
        table1 = new JTable();
        label1 = new JLabel();
        btnrunDML = new JButton();
        scrollPane2 = new JScrollPane();
        taInput = new JTextArea();
        label2 = new JLabel();

        //======== this ========
        setTitle("Welcome");
        Container contentPane = getContentPane();

        //---- btnRunDDL ----
        btnRunDDL.setText("Run DDL");
        btnRunDDL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button1ActionPerformed(e);
                btnRunActionPerformed(e);
                btnRunDDLActionPerformed(e);
                btnRunDDLActionPerformed(e);
            }
        });

        //======== scrlpMain ========
        {
            scrlpMain.setViewportView(table1);
        }

        //---- label1 ----
        label1.setText("Enter Query:");

        //---- btnrunDML ----
        btnrunDML.setText("Run DML");
        btnrunDML.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button1ActionPerformed(e);
                btnRunActionPerformed(e);
                btnrunDMLActionPerformed(e);
                btnrunDMLActionPerformed(e);
            }
        });

        //======== scrollPane2 ========
        {

            //---- taInput ----
            taInput.setToolTipText("Enter Query Here");
            scrollPane2.setViewportView(taInput);
        }

        //---- label2 ----
        label2.setText("Created by: Yossi Silberhaft, Steven Lapp, Binyamin Greenberg, Daniel Blinick");
        label2.setFont(new Font("Segoe UI", Font.PLAIN, 10));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(label1)
                            .addGap(18, 18, 18)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 394, GroupLayout.PREFERRED_SIZE)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(btnRunDDL, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
                                    .addGap(56, 56, 56)
                                    .addComponent(btnrunDML, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))))
                        .addComponent(label2))
                    .addGap(46, 46, 46)
                    .addComponent(scrlpMain, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(13, 13, 13)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(scrlpMain, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
                            .addContainerGap())
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label1))
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(30, 30, 30)
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnRunDDL, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnrunDML, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
                                    .addGap(245, 247, Short.MAX_VALUE))
                                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 306, Short.MAX_VALUE)
                                    .addComponent(label2))))))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Yosef Silberhaft
    private JButton btnRunDDL;
    private JScrollPane scrlpMain;
    private JTable table1;
    private JLabel label1;
    private JButton btnrunDML;
    private JScrollPane scrollPane2;
    private JTextArea taInput;
    private JLabel label2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}