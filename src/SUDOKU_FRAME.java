
import static java.awt.Color.*;
import java.util.HashMap;
import java.util.Stack;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author PRATIMA
 */
public class SUDOKU_FRAME extends javax.swing.JFrame {
    
    private String number;  //cannot take int because we write text in button
    private boolean globalVar = false;
    private Stack <HashMap<JButton, String>> st = new Stack<>();
    
    private String solvedBoard[][] ={
        {"2","9","8","5","1","6","7","3","4"},
        {"4","1","3","2","7","8","5","6","9"},
        {"7","5","6","3","4","9","1","2","8"},
        {"8","2","1","4","3","5","6","9","7"},
        {"5","3","4","6","9","7","2","8","1"},
        {"9","6","7","1","8","2","3","4","5"},
        {"1","4","2","8","5","3","9","7","6"},
        {"3","7","5","9","6","4","8","1","2"},
        {"6","8","9","7","2","1","4","5","3"},
    };

    /**
     * Creates new form SUDOKU_FRAME
     */
    public SUDOKU_FRAME() {
        initComponents();
        number = "1";
    }
    
    private void undo() {
        if(st.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please enter a value first before using the undo functionality", "Sudoku game", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            
            HashMap<JButton, String> deleted = st.pop(); //deleted is holding a pair
//            HashMap.Entry<JButton, String> entry = deleted.entrySet().stream().findFirst().get();
            HashMap.Entry<JButton, String> entry = deleted.entrySet().stream().findFirst().get();
            JButton deletedBTN = entry.getKey();
            boolean flag = false;
            
            Stack<HashMap<JButton,String>> tempSt = new Stack<>();
            
            while(!st.isEmpty()) {
                
            HashMap<JButton, String> temp = st.pop(); //deleted is holding a pair
            tempSt.push(temp);
            
            HashMap.Entry<JButton, String> entry1 = temp.entrySet().stream().findFirst().get(); //accessing the first key value pair of hashmap
            JButton comparedBTN = entry1.getKey();
            String value = entry1.getValue();
            
            if(deletedBTN == comparedBTN) {
                flag = true;
                comparedBTN.setText(value);
                break;
            }
            }
            
            if(flag == false){
                deletedBTN.setText("");
            }
            
            while(!tempSt.isEmpty()) {
            HashMap<JButton, String> temp = tempSt.pop();
            st.push(temp);
            }
            
        }
    }
    
    public void chooseNumber(JButton BTN){
        SELECTIONBTN1.setBackground(black);
        SELECTIONBTN2.setBackground(black);
        SELECTIONBTN3.setBackground(black);
        SELECTIONBTN4.setBackground(black);
        SELECTIONBTN5.setBackground(black);
        SELECTIONBTN6.setBackground(black);
        SELECTIONBTN7.setBackground(black);
        SELECTIONBTN8.setBackground(black);
        SELECTIONBTN9.setBackground(black);
        
        BTN.setBackground(blue);
    }
    
    public void seeSolution(){
        JButton predefinedBTNS[] = {R1C4, R1C7, R1C8, R1C9, R2C1, R2C5, R2C6, R2C7, R2C9, R3C1, R3C3, R3C5, R3C8, R4C5, R4C6, R4C8,
                                    R5C2, R5C3, R5C7, R5C8, R6C2, R6C4, R6C5, R7C2, R7C5, R7C7, R7C9, R8C1, R8C4, R8C5, R8C9, R9C1,
                                    R9C2, R9C3, R9C6};
        
        JButton BTNS[][] = {
            {R1C1,R1C2,R1C3,R1C4,R1C5,R1C6,R1C7,R1C8,R1C9},
            {R2C1,R2C2,R2C3,R2C4,R2C5,R2C6,R2C7,R2C8,R2C9},
            {R3C1,R3C2,R3C3,R3C4,R3C5,R3C6,R3C7,R3C8,R3C9},
            {R4C1,R4C2,R4C3,R4C4,R4C5,R4C6,R4C7,R4C8,R4C9},
            {R5C1,R5C2,R5C3,R5C4,R5C5,R5C6,R5C7,R5C8,R5C9},
            {R6C1,R6C2,R6C3,R6C4,R6C5,R6C6,R6C7,R6C8,R6C9},
            {R7C1,R7C2,R7C3,R7C4,R7C5,R7C6,R7C7,R7C8,R7C9},
            {R8C1,R8C2,R8C3,R8C4,R8C5,R8C6,R8C7,R8C8,R8C9},
            {R9C1,R9C2,R9C3,R9C4,R9C5,R9C6,R9C7,R9C8,R9C9},
        };
        
        for(int i=0;i<9;i++) {
            for(int j=0;j<9;j++) {
                
                boolean flag = true;
                
                for(int k=0;k<predefinedBTNS.length;k++) {
                    if(predefinedBTNS[k] == BTNS[i][j]) {
                        flag = false;
                        break;
                    }
                }
                
                if(flag == true) {
                    
                    if(globalVar == false) {
                    BTNS[i][j].setText(solvedBoard[i][j]);
                    BTNS[i][j].setBackground(black);
                    BTNS[i][j].setForeground(white);
                }
                    else{
                    BTNS[i][j].setText("");
                    BTNS[i][j].setBackground(white);
                    BTNS[i][j].setForeground(black);  
                    }
                }
            }
        }
        
        if(globalVar == false) {
            
            globalVar = true;
            SOLUTIONBTN.setText("HIDE SOLUTION");
        }
        else{
            
            globalVar = false;
            SOLUTIONBTN.setText("SOLUTION");
        }
        }
    
    private void checkMoves() {
        JButton BTNS[][] = {
            {R1C1,R1C2,R1C3,R1C4,R1C5,R1C6,R1C7,R1C8,R1C9},
            {R2C1,R2C2,R2C3,R2C4,R2C5,R2C6,R2C7,R2C8,R2C9},
            {R3C1,R3C2,R3C3,R3C4,R3C5,R3C6,R3C7,R3C8,R3C9},
            {R4C1,R4C2,R4C3,R4C4,R4C5,R4C6,R4C7,R4C8,R4C9},
            {R5C1,R5C2,R5C3,R5C4,R5C5,R5C6,R5C7,R5C8,R5C9},
            {R6C1,R6C2,R6C3,R6C4,R6C5,R6C6,R6C7,R6C8,R6C9},
            {R7C1,R7C2,R7C3,R7C4,R7C5,R7C6,R7C7,R7C8,R7C9},
            {R8C1,R8C2,R8C3,R8C4,R8C5,R8C6,R8C7,R8C8,R8C9},
            {R9C1,R9C2,R9C3,R9C4,R9C5,R9C6,R9C7,R9C8,R9C9},
        };
        
        for(int i=0;i<9;i++) {
            for(int j=0;j<9;j++) {
                if(BTNS[i][j].getText()!= solvedBoard[i][j] && BTNS[i][j].getText()!= "") {
                    BTNS[i][j].setBackground(red);
                }
            }
        }
    }
    
    public void undoOperation() {
        
    }
    
    public void reset() {
        JButton predefinedBTNS[] = {R1C4, R1C7, R1C8, R1C9, R2C1, R2C5, R2C6, R2C7, R2C9, R3C1, R3C3, R3C5, R3C8, R4C5, R4C6, R4C8,
                                    R5C2, R5C3, R5C7, R5C8, R6C2, R6C4, R6C5, R7C2, R7C5, R7C7, R7C9, R8C1, R8C4, R8C5, R8C9, R9C1,
                                    R9C2, R9C3, R9C6};
        
        JButton BTNS[][] = {
            {R1C1,R1C2,R1C3,R1C4,R1C5,R1C6,R1C7,R1C8,R1C9},
            {R2C1,R2C2,R2C3,R2C4,R2C5,R2C6,R2C7,R2C8,R2C9},
            {R3C1,R3C2,R3C3,R3C4,R3C5,R3C6,R3C7,R3C8,R3C9},
            {R4C1,R4C2,R4C3,R4C4,R4C5,R4C6,R4C7,R4C8,R4C9},
            {R5C1,R5C2,R5C3,R5C4,R5C5,R5C6,R5C7,R5C8,R5C9},
            {R6C1,R6C2,R6C3,R6C4,R6C5,R6C6,R6C7,R6C8,R6C9},
            {R7C1,R7C2,R7C3,R7C4,R7C5,R7C6,R7C7,R7C8,R7C9},
            {R8C1,R8C2,R8C3,R8C4,R8C5,R8C6,R8C7,R8C8,R8C9},
            {R9C1,R9C2,R9C3,R9C4,R9C5,R9C6,R9C7,R9C8,R9C9},
        };
        
        for(int i=0;i<9;i++) {
            for(int j=0;j<9;j++) {
                
                boolean flag = true;
                
                for(int k=0;k<predefinedBTNS.length;k++) {
                    if(predefinedBTNS[k] == BTNS[i][j]) {
                        flag = false;
                        break;
                    }
                }
                
                if(flag == true) {
                    BTNS[i][j].setText("");
                    BTNS[i][j].setBackground(white);
                }
            }
        }
        
        st.clear();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        R1C1 = new javax.swing.JButton();
        R1C2 = new javax.swing.JButton();
        R1C3 = new javax.swing.JButton();
        R2C1 = new javax.swing.JButton();
        R2C2 = new javax.swing.JButton();
        R2C3 = new javax.swing.JButton();
        R3C1 = new javax.swing.JButton();
        R3C3 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        R3C2 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        R1C4 = new javax.swing.JButton();
        R1C5 = new javax.swing.JButton();
        R1C6 = new javax.swing.JButton();
        R2C4 = new javax.swing.JButton();
        R2C5 = new javax.swing.JButton();
        R2C6 = new javax.swing.JButton();
        R3C5 = new javax.swing.JButton();
        R3C4 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        R3C6 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        R1C7 = new javax.swing.JButton();
        R1C8 = new javax.swing.JButton();
        R1C9 = new javax.swing.JButton();
        R2C7 = new javax.swing.JButton();
        R2C8 = new javax.swing.JButton();
        R2C9 = new javax.swing.JButton();
        R3C8 = new javax.swing.JButton();
        R3C9 = new javax.swing.JButton();
        R3C7 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        R4C1 = new javax.swing.JButton();
        R4C2 = new javax.swing.JButton();
        R4C3 = new javax.swing.JButton();
        R5C1 = new javax.swing.JButton();
        R5C2 = new javax.swing.JButton();
        R5C3 = new javax.swing.JButton();
        R6C1 = new javax.swing.JButton();
        R6C3 = new javax.swing.JButton();
        R6C2 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        R4C4 = new javax.swing.JButton();
        R4C5 = new javax.swing.JButton();
        R4C6 = new javax.swing.JButton();
        R5C4 = new javax.swing.JButton();
        R5C5 = new javax.swing.JButton();
        R5C6 = new javax.swing.JButton();
        R6C5 = new javax.swing.JButton();
        R6C4 = new javax.swing.JButton();
        jButton59 = new javax.swing.JButton();
        R6C6 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        R4C7 = new javax.swing.JButton();
        R4C8 = new javax.swing.JButton();
        R4C9 = new javax.swing.JButton();
        R5C7 = new javax.swing.JButton();
        R5C8 = new javax.swing.JButton();
        R5C9 = new javax.swing.JButton();
        R6C7 = new javax.swing.JButton();
        R6C8 = new javax.swing.JButton();
        R6C9 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        R7C1 = new javax.swing.JButton();
        R7C2 = new javax.swing.JButton();
        R7C3 = new javax.swing.JButton();
        R8C1 = new javax.swing.JButton();
        R8C2 = new javax.swing.JButton();
        R8C3 = new javax.swing.JButton();
        R9C2 = new javax.swing.JButton();
        R9C3 = new javax.swing.JButton();
        R9C1 = new javax.swing.JButton();
        jButton108 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        R7C4 = new javax.swing.JButton();
        R7C5 = new javax.swing.JButton();
        R7C6 = new javax.swing.JButton();
        R8C4 = new javax.swing.JButton();
        R8C5 = new javax.swing.JButton();
        R8C6 = new javax.swing.JButton();
        R9C6 = new javax.swing.JButton();
        R9C4 = new javax.swing.JButton();
        R9C5 = new javax.swing.JButton();
        jButton120 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        R7C7 = new javax.swing.JButton();
        R7C8 = new javax.swing.JButton();
        R7C9 = new javax.swing.JButton();
        R8C7 = new javax.swing.JButton();
        R8C8 = new javax.swing.JButton();
        R8C9 = new javax.swing.JButton();
        R9C7 = new javax.swing.JButton();
        R9C8 = new javax.swing.JButton();
        R9C9 = new javax.swing.JButton();
        SELECTIONBTN1 = new javax.swing.JButton();
        SELECTIONBTN2 = new javax.swing.JButton();
        SELECTIONBTN3 = new javax.swing.JButton();
        SELECTIONBTN4 = new javax.swing.JButton();
        SELECTIONBTN5 = new javax.swing.JButton();
        SELECTIONBTN8 = new javax.swing.JButton();
        SELECTIONBTN9 = new javax.swing.JButton();
        SELECTIONBTN6 = new javax.swing.JButton();
        SELECTIONBTN7 = new javax.swing.JButton();
        RESETBTN = new javax.swing.JButton();
        UNDOBTN = new javax.swing.JButton();
        SOLUTIONBTN = new javax.swing.JButton();
        CHECKBTN = new javax.swing.JButton();
        EXITBTN = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SUDOKU GAME");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SUDOKU PUZZLE GAME");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setPreferredSize(new java.awt.Dimension(126, 126));

        R1C1.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R1C1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R1C1ActionPerformed(evt);
            }
        });

        R1C2.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R1C2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R1C2ActionPerformed(evt);
            }
        });

        R1C3.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R1C3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R1C3ActionPerformed(evt);
            }
        });

        R2C1.setBackground(new java.awt.Color(153, 153, 255));
        R2C1.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R2C1.setText("4");
        R2C1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R2C1ActionPerformed(evt);
            }
        });

        R2C2.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R2C2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R2C2ActionPerformed(evt);
            }
        });

        R2C3.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R2C3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R2C3ActionPerformed(evt);
            }
        });

        R3C1.setBackground(new java.awt.Color(153, 153, 255));
        R3C1.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R3C1.setText("7");
        R3C1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R3C1ActionPerformed(evt);
            }
        });

        R3C3.setBackground(new java.awt.Color(153, 153, 255));
        R3C3.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R3C3.setText("6");
        R3C3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R3C3ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N

        R3C2.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R3C2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R3C2ActionPerformed(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(R1C1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R1C2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R1C3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(R2C1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R2C2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R2C3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(R3C1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(47, 47, 47)
                        .addComponent(R3C3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(R3C2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(R1C3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R1C2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R1C1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(R2C3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R2C2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R2C1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(R3C3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R3C1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(98, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(R3C2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap()))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setPreferredSize(new java.awt.Dimension(126, 126));

        R1C4.setBackground(new java.awt.Color(153, 153, 255));
        R1C4.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R1C4.setText("5");
        R1C4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R1C4ActionPerformed(evt);
            }
        });

        R1C5.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R1C5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R1C5ActionPerformed(evt);
            }
        });

        R1C6.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R1C6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R1C6ActionPerformed(evt);
            }
        });

        R2C4.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R2C4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R2C4ActionPerformed(evt);
            }
        });

        R2C5.setBackground(new java.awt.Color(153, 153, 255));
        R2C5.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R2C5.setText("7");
        R2C5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R2C5ActionPerformed(evt);
            }
        });

        R2C6.setBackground(new java.awt.Color(153, 153, 255));
        R2C6.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R2C6.setText("8");
        R2C6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R2C6ActionPerformed(evt);
            }
        });

        R3C5.setBackground(new java.awt.Color(153, 153, 255));
        R3C5.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R3C5.setText("4");
        R3C5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R3C5ActionPerformed(evt);
            }
        });

        R3C4.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R3C4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R3C4ActionPerformed(evt);
            }
        });

        jButton23.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N

        R3C6.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R3C6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R3C6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(R1C4, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R1C5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R1C6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(R2C4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R2C5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R2C6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(R3C5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(41, 41, 41)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(R3C4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(R3C6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(R1C6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R1C5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R1C4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(R2C6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R2C5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R2C4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(R3C5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addContainerGap(98, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(R3C6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(R3C4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap()))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setPreferredSize(new java.awt.Dimension(126, 126));

        R1C7.setBackground(new java.awt.Color(153, 153, 255));
        R1C7.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R1C7.setText("7");
        R1C7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R1C7ActionPerformed(evt);
            }
        });

        R1C8.setBackground(new java.awt.Color(153, 153, 255));
        R1C8.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R1C8.setText("3");

        R1C9.setBackground(new java.awt.Color(153, 153, 255));
        R1C9.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R1C9.setText("4");
        R1C9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R1C9ActionPerformed(evt);
            }
        });

        R2C7.setBackground(new java.awt.Color(153, 153, 255));
        R2C7.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R2C7.setText("5");
        R2C7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R2C7ActionPerformed(evt);
            }
        });

        R2C8.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R2C8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R2C8ActionPerformed(evt);
            }
        });

        R2C9.setBackground(new java.awt.Color(153, 153, 255));
        R2C9.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R2C9.setText("9");
        R2C9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R2C9ActionPerformed(evt);
            }
        });

        R3C8.setBackground(new java.awt.Color(153, 153, 255));
        R3C8.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R3C8.setText("2");
        R3C8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R3C8ActionPerformed(evt);
            }
        });

        R3C9.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R3C9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R3C9ActionPerformed(evt);
            }
        });

        R3C7.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R3C7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R3C7ActionPerformed(evt);
            }
        });

        jButton35.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(R1C7, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R1C8, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R1C9, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(R2C7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R2C8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R2C9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(R3C8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R3C9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(R3C7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGap(47, 47, 47)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(R1C9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R1C8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R1C7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(R2C9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R2C8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R2C7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(R3C9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R3C8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addContainerGap(98, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(R3C7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap()))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setPreferredSize(new java.awt.Dimension(126, 126));

        R4C1.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R4C1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R4C1ActionPerformed(evt);
            }
        });

        R4C2.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R4C2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R4C2ActionPerformed(evt);
            }
        });

        R4C3.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R4C3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R4C3ActionPerformed(evt);
            }
        });

        R5C1.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R5C1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R5C1ActionPerformed(evt);
            }
        });

        R5C2.setBackground(new java.awt.Color(153, 153, 255));
        R5C2.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R5C2.setText("3");
        R5C2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R5C2ActionPerformed(evt);
            }
        });

        R5C3.setBackground(new java.awt.Color(153, 153, 255));
        R5C3.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R5C3.setText("4");
        R5C3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R5C3ActionPerformed(evt);
            }
        });

        R6C1.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R6C1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R6C1ActionPerformed(evt);
            }
        });

        R6C3.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R6C3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R6C3ActionPerformed(evt);
            }
        });

        R6C2.setBackground(new java.awt.Color(153, 153, 255));
        R6C2.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R6C2.setText("6");
        R6C2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R6C2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(R4C1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R4C2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R4C3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(R5C1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R5C2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R5C3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(R6C1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(47, 47, 47)
                        .addComponent(R6C3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(47, 47, 47)
                    .addComponent(R6C2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGap(47, 47, 47)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(R4C3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R4C2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R4C1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(R5C3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R5C2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R5C1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(R6C3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R6C1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addContainerGap(98, Short.MAX_VALUE)
                    .addComponent(R6C2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setPreferredSize(new java.awt.Dimension(126, 126));

        R4C4.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R4C4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R4C4ActionPerformed(evt);
            }
        });

        R4C5.setBackground(new java.awt.Color(153, 153, 255));
        R4C5.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R4C5.setText("3");
        R4C5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R4C5ActionPerformed(evt);
            }
        });

        R4C6.setBackground(new java.awt.Color(153, 153, 255));
        R4C6.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R4C6.setText("5");
        R4C6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R4C6ActionPerformed(evt);
            }
        });

        R5C4.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R5C4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R5C4ActionPerformed(evt);
            }
        });

        R5C5.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R5C5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R5C5ActionPerformed(evt);
            }
        });

        R5C6.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R5C6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R5C6ActionPerformed(evt);
            }
        });

        R6C5.setBackground(new java.awt.Color(153, 153, 255));
        R6C5.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R6C5.setText("8");
        R6C5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R6C5ActionPerformed(evt);
            }
        });

        R6C4.setBackground(new java.awt.Color(153, 153, 255));
        R6C4.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R6C4.setText("1");
        R6C4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R6C4ActionPerformed(evt);
            }
        });

        jButton59.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N

        R6C6.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R6C6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R6C6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(R4C4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R4C5, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R4C6, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(R5C4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R5C5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R5C6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(R6C5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(41, 41, 41)))
                .addContainerGap())
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(R6C4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton59, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(R6C6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(R4C6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R4C5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R4C4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(R5C6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R5C5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R5C4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(R6C5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                    .addContainerGap(98, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(R6C6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton59, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(R6C4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap()))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.setPreferredSize(new java.awt.Dimension(126, 126));

        R4C7.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R4C7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R4C7ActionPerformed(evt);
            }
        });

        R4C8.setBackground(new java.awt.Color(153, 153, 255));
        R4C8.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R4C8.setText("9");
        R4C8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R4C8ActionPerformed(evt);
            }
        });

        R4C9.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R4C9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R4C9ActionPerformed(evt);
            }
        });

        R5C7.setBackground(new java.awt.Color(153, 153, 255));
        R5C7.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R5C7.setText("2");
        R5C7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R5C7ActionPerformed(evt);
            }
        });

        R5C8.setBackground(new java.awt.Color(153, 153, 255));
        R5C8.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R5C8.setText("8");
        R5C8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R5C8ActionPerformed(evt);
            }
        });

        R5C9.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R5C9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R5C9ActionPerformed(evt);
            }
        });

        R6C7.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R6C7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R6C7ActionPerformed(evt);
            }
        });

        R6C8.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R6C8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R6C8ActionPerformed(evt);
            }
        });

        R6C9.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R6C9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R6C9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(R4C7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R4C8, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R4C9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(R5C7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R5C8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R5C9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(R6C7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(R6C8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(R6C9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(R4C9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R4C8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R4C7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(R5C9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R5C8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R5C7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                    .addContainerGap(98, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(R6C9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(R6C8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(R6C7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap()))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel10.setPreferredSize(new java.awt.Dimension(126, 126));

        R7C1.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R7C1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R7C1ActionPerformed(evt);
            }
        });

        R7C2.setBackground(new java.awt.Color(153, 153, 255));
        R7C2.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R7C2.setText("4");
        R7C2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R7C2ActionPerformed(evt);
            }
        });

        R7C3.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R7C3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R7C3ActionPerformed(evt);
            }
        });

        R8C1.setBackground(new java.awt.Color(153, 153, 255));
        R8C1.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R8C1.setText("3");
        R8C1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R8C1ActionPerformed(evt);
            }
        });

        R8C2.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R8C2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R8C2ActionPerformed(evt);
            }
        });

        R8C3.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R8C3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R8C3ActionPerformed(evt);
            }
        });

        R9C2.setBackground(new java.awt.Color(153, 153, 255));
        R9C2.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R9C2.setText("8");
        R9C2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R9C2ActionPerformed(evt);
            }
        });

        R9C3.setBackground(new java.awt.Color(153, 153, 255));
        R9C3.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R9C3.setText("9");
        R9C3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R9C3ActionPerformed(evt);
            }
        });

        R9C1.setBackground(new java.awt.Color(153, 153, 255));
        R9C1.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R9C1.setText("6");
        R9C1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R9C1ActionPerformed(evt);
            }
        });

        jButton108.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(R7C1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R7C2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R7C3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(R8C1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R8C2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R8C3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(R9C2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R9C3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(R9C1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGap(47, 47, 47)
                    .addComponent(jButton108, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(R7C3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R7C2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R7C1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(R8C3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R8C2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R8C1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(R9C3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R9C2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                    .addContainerGap(98, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton108, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(R9C1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap()))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel11.setPreferredSize(new java.awt.Dimension(126, 126));

        R7C4.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R7C4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R7C4ActionPerformed(evt);
            }
        });

        R7C5.setBackground(new java.awt.Color(153, 153, 255));
        R7C5.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R7C5.setText("5");
        R7C5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R7C5ActionPerformed(evt);
            }
        });

        R7C6.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R7C6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R7C6ActionPerformed(evt);
            }
        });

        R8C4.setBackground(new java.awt.Color(153, 153, 255));
        R8C4.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R8C4.setText("9");
        R8C4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R8C4ActionPerformed(evt);
            }
        });

        R8C5.setBackground(new java.awt.Color(153, 153, 255));
        R8C5.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R8C5.setText("6");
        R8C5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R8C5ActionPerformed(evt);
            }
        });

        R8C6.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R8C6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R8C6ActionPerformed(evt);
            }
        });

        R9C6.setBackground(new java.awt.Color(153, 153, 255));
        R9C6.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R9C6.setText("1");
        R9C6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R9C6ActionPerformed(evt);
            }
        });

        R9C4.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R9C4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R9C4ActionPerformed(evt);
            }
        });

        R9C5.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R9C5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R9C5ActionPerformed(evt);
            }
        });

        jButton120.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(R7C4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R7C5, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R7C6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(R8C4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R8C5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R8C6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(R9C6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(R9C4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(R9C5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton120, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(R7C6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R7C5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R7C4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(R8C6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R8C5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R8C4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(R9C6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                    .addContainerGap(98, Short.MAX_VALUE)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton120, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(R9C5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(R9C4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap()))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel12.setPreferredSize(new java.awt.Dimension(126, 126));

        R7C7.setBackground(new java.awt.Color(153, 153, 255));
        R7C7.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R7C7.setText("9");
        R7C7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R7C7ActionPerformed(evt);
            }
        });

        R7C8.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R7C8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R7C8ActionPerformed(evt);
            }
        });

        R7C9.setBackground(new java.awt.Color(153, 153, 255));
        R7C9.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R7C9.setText("6");
        R7C9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R7C9ActionPerformed(evt);
            }
        });

        R8C7.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R8C7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R8C7ActionPerformed(evt);
            }
        });

        R8C8.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R8C8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R8C8ActionPerformed(evt);
            }
        });

        R8C9.setBackground(new java.awt.Color(153, 153, 255));
        R8C9.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R8C9.setText("2");
        R8C9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R8C9ActionPerformed(evt);
            }
        });

        R9C7.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R9C7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R9C7ActionPerformed(evt);
            }
        });

        R9C8.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R9C8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R9C8ActionPerformed(evt);
            }
        });

        R9C9.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        R9C9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R9C9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(R7C7, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R7C8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R7C9, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(R8C7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R8C8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R8C9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(R9C7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(R9C8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(R9C9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(R7C9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R7C8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R7C7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(R8C9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R8C8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R8C7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                    .addContainerGap(98, Short.MAX_VALUE)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(R9C9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(R9C8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(R9C7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap()))
        );

        SELECTIONBTN1.setBackground(new java.awt.Color(0, 0, 204));
        SELECTIONBTN1.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        SELECTIONBTN1.setForeground(new java.awt.Color(255, 255, 255));
        SELECTIONBTN1.setText("1");
        SELECTIONBTN1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SELECTIONBTN1ActionPerformed(evt);
            }
        });

        SELECTIONBTN2.setBackground(new java.awt.Color(0, 0, 0));
        SELECTIONBTN2.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        SELECTIONBTN2.setForeground(new java.awt.Color(255, 255, 255));
        SELECTIONBTN2.setText("2");
        SELECTIONBTN2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SELECTIONBTN2ActionPerformed(evt);
            }
        });

        SELECTIONBTN3.setBackground(new java.awt.Color(0, 0, 0));
        SELECTIONBTN3.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        SELECTIONBTN3.setForeground(new java.awt.Color(255, 255, 255));
        SELECTIONBTN3.setText("3");
        SELECTIONBTN3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SELECTIONBTN3ActionPerformed(evt);
            }
        });

        SELECTIONBTN4.setBackground(new java.awt.Color(0, 0, 0));
        SELECTIONBTN4.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        SELECTIONBTN4.setForeground(new java.awt.Color(255, 255, 255));
        SELECTIONBTN4.setText("4");
        SELECTIONBTN4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SELECTIONBTN4ActionPerformed(evt);
            }
        });

        SELECTIONBTN5.setBackground(new java.awt.Color(0, 0, 0));
        SELECTIONBTN5.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        SELECTIONBTN5.setForeground(new java.awt.Color(255, 255, 255));
        SELECTIONBTN5.setText("5");
        SELECTIONBTN5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SELECTIONBTN5ActionPerformed(evt);
            }
        });

        SELECTIONBTN8.setBackground(new java.awt.Color(0, 0, 0));
        SELECTIONBTN8.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        SELECTIONBTN8.setForeground(new java.awt.Color(255, 255, 255));
        SELECTIONBTN8.setText("8");
        SELECTIONBTN8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SELECTIONBTN8ActionPerformed(evt);
            }
        });

        SELECTIONBTN9.setBackground(new java.awt.Color(0, 0, 0));
        SELECTIONBTN9.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        SELECTIONBTN9.setForeground(new java.awt.Color(255, 255, 255));
        SELECTIONBTN9.setText("9");
        SELECTIONBTN9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SELECTIONBTN9ActionPerformed(evt);
            }
        });

        SELECTIONBTN6.setBackground(new java.awt.Color(0, 0, 0));
        SELECTIONBTN6.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        SELECTIONBTN6.setForeground(new java.awt.Color(255, 255, 255));
        SELECTIONBTN6.setText("6");
        SELECTIONBTN6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SELECTIONBTN6ActionPerformed(evt);
            }
        });

        SELECTIONBTN7.setBackground(new java.awt.Color(0, 0, 0));
        SELECTIONBTN7.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        SELECTIONBTN7.setForeground(new java.awt.Color(255, 255, 255));
        SELECTIONBTN7.setText("7");
        SELECTIONBTN7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SELECTIONBTN7ActionPerformed(evt);
            }
        });

        RESETBTN.setBackground(new java.awt.Color(102, 255, 102));
        RESETBTN.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        RESETBTN.setText("RESET");
        RESETBTN.setPreferredSize(new java.awt.Dimension(90, 40));
        RESETBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RESETBTNActionPerformed(evt);
            }
        });

        UNDOBTN.setBackground(new java.awt.Color(204, 255, 255));
        UNDOBTN.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        UNDOBTN.setText("UNDO");
        UNDOBTN.setPreferredSize(new java.awt.Dimension(90, 40));
        UNDOBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UNDOBTNActionPerformed(evt);
            }
        });

        SOLUTIONBTN.setBackground(new java.awt.Color(255, 255, 153));
        SOLUTIONBTN.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        SOLUTIONBTN.setText("SOLUTION");
        SOLUTIONBTN.setPreferredSize(new java.awt.Dimension(90, 40));
        SOLUTIONBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SOLUTIONBTNActionPerformed(evt);
            }
        });

        CHECKBTN.setBackground(new java.awt.Color(204, 153, 255));
        CHECKBTN.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        CHECKBTN.setText("CHECK MOVES");
        CHECKBTN.setPreferredSize(new java.awt.Dimension(90, 40));
        CHECKBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CHECKBTNActionPerformed(evt);
            }
        });

        EXITBTN.setBackground(new java.awt.Color(255, 51, 51));
        EXITBTN.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        EXITBTN.setText("EXIT");
        EXITBTN.setPreferredSize(new java.awt.Dimension(90, 40));
        EXITBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EXITBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(RESETBTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(37, 37, 37)
                                        .addComponent(UNDOBTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(CHECKBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(SOLUTIONBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(EXITBTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SELECTIONBTN1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SELECTIONBTN2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SELECTIONBTN3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SELECTIONBTN4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SELECTIONBTN5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SELECTIONBTN8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SELECTIONBTN9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SELECTIONBTN6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SELECTIONBTN7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(SELECTIONBTN1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SELECTIONBTN2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SELECTIONBTN3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SELECTIONBTN4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SELECTIONBTN5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SELECTIONBTN6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SELECTIONBTN7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SELECTIONBTN8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SELECTIONBTN9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RESETBTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EXITBTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UNDOBTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SOLUTIONBTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CHECKBTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void SELECTIONBTN7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SELECTIONBTN7ActionPerformed
        // TODO add your handling code here:
        number = "7";
        chooseNumber(SELECTIONBTN7);
    }//GEN-LAST:event_SELECTIONBTN7ActionPerformed

    private void R1C7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R1C7ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "This place has already been allocated", "Sudoku game", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_R1C7ActionPerformed

    private void R3C8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R3C8ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "This place has already been allocated", "Sudoku game", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_R3C8ActionPerformed

    private void R6C4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R6C4ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "This place has already been allocated", "Sudoku game", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_R6C4ActionPerformed

    private void SOLUTIONBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SOLUTIONBTNActionPerformed
        // TODO add your handling code here:
        seeSolution();
    }//GEN-LAST:event_SOLUTIONBTNActionPerformed

    private void UNDOBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UNDOBTNActionPerformed
        // TODO add your handling code here:
        undo();
    }//GEN-LAST:event_UNDOBTNActionPerformed

    private void R8C3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R8C3ActionPerformed
        // TODO add your handling code here:
        R8C3.setBackground(white);
        R8C3.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R8C3,number);
        
        st.push(hm);
    }//GEN-LAST:event_R8C3ActionPerformed

    private void SELECTIONBTN1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SELECTIONBTN1ActionPerformed
        // TODO add your handling code here:
        number = "1";
        chooseNumber(SELECTIONBTN1);
    }//GEN-LAST:event_SELECTIONBTN1ActionPerformed

    private void SELECTIONBTN2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SELECTIONBTN2ActionPerformed
        // TODO add your handling code here:
        number = "2";
        chooseNumber(SELECTIONBTN2);
    }//GEN-LAST:event_SELECTIONBTN2ActionPerformed

    private void SELECTIONBTN3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SELECTIONBTN3ActionPerformed
        // TODO add your handling code here:
        number = "3";
        chooseNumber(SELECTIONBTN3);
    }//GEN-LAST:event_SELECTIONBTN3ActionPerformed

    private void SELECTIONBTN4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SELECTIONBTN4ActionPerformed
        // TODO add your handling code here:
        number = "4";
        chooseNumber(SELECTIONBTN4);
    }//GEN-LAST:event_SELECTIONBTN4ActionPerformed

    private void SELECTIONBTN5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SELECTIONBTN5ActionPerformed
        // TODO add your handling code here:
        number = "5";
        chooseNumber(SELECTIONBTN5);
    }//GEN-LAST:event_SELECTIONBTN5ActionPerformed

    private void SELECTIONBTN6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SELECTIONBTN6ActionPerformed
        // TODO add your handling code here:
        number = "6";
        chooseNumber(SELECTIONBTN6);
    }//GEN-LAST:event_SELECTIONBTN6ActionPerformed

    private void SELECTIONBTN8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SELECTIONBTN8ActionPerformed
        // TODO add your handling code here:
        number = "8";
        chooseNumber(SELECTIONBTN8);
    }//GEN-LAST:event_SELECTIONBTN8ActionPerformed

    private void SELECTIONBTN9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SELECTIONBTN9ActionPerformed
        // TODO add your handling code here:
        number = "9";
        chooseNumber(SELECTIONBTN9);
    }//GEN-LAST:event_SELECTIONBTN9ActionPerformed

    private void R1C1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R1C1ActionPerformed
        // TODO add your handling code here:
        R1C1.setBackground(white);
        R1C1.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R1C1,number);
        
        st.push(hm);
    }//GEN-LAST:event_R1C1ActionPerformed

    private void R1C2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R1C2ActionPerformed
        // TODO add your handling code here:
        R1C2.setBackground(white);
        R1C2.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R1C2,number);
        
        st.push(hm);
    }//GEN-LAST:event_R1C2ActionPerformed

    private void R1C3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R1C3ActionPerformed
        // TODO add your handling code here:
        R1C3.setBackground(white);
        R1C3.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R1C3,number);
        
        st.push(hm);
    }//GEN-LAST:event_R1C3ActionPerformed

    private void R1C5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R1C5ActionPerformed
        // TODO add your handling code here:
        R1C5.setBackground(white);
        R1C5.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R1C5,number);
        
        st.push(hm);
    }//GEN-LAST:event_R1C5ActionPerformed

    private void R1C6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R1C6ActionPerformed
        // TODO add your handling code here:
        R1C6.setBackground(white);
        R1C6.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R1C6,number);
        
        st.push(hm);
    }//GEN-LAST:event_R1C6ActionPerformed

    private void R2C2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R2C2ActionPerformed
        // TODO add your handling code here:
        R2C2.setBackground(white);
        R2C2.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R2C2,number);
        
        st.push(hm);
    }//GEN-LAST:event_R2C2ActionPerformed

    private void R2C3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R2C3ActionPerformed
        // TODO add your handling code here:
        R2C3.setBackground(white);
        R2C3.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R2C3,number);
        
        st.push(hm);
    }//GEN-LAST:event_R2C3ActionPerformed

    private void R2C4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R2C4ActionPerformed
        // TODO add your handling code here:
        R2C4.setBackground(white);
        R2C4.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R2C4,number);
        
        st.push(hm);
    }//GEN-LAST:event_R2C4ActionPerformed

    private void R2C8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R2C8ActionPerformed
        // TODO add your handling code here:
        R2C8.setBackground(white);
        R2C8.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R2C8,number);
        
        st.push(hm);
    }//GEN-LAST:event_R2C8ActionPerformed

    private void R3C2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R3C2ActionPerformed
        // TODO add your handling code here:
        R3C2.setBackground(white);
        R3C2.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R3C2,number);
        
        st.push(hm);
    }//GEN-LAST:event_R3C2ActionPerformed

    private void R3C4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R3C4ActionPerformed
        // TODO add your handling code here:
        R3C4.setBackground(white);
        R3C4.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R3C4,number);
        
        st.push(hm);
    }//GEN-LAST:event_R3C4ActionPerformed

    private void R3C6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R3C6ActionPerformed
        // TODO add your handling code here:
        R3C6.setBackground(white);
        R3C6.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R3C6,number);
        
        st.push(hm);
    }//GEN-LAST:event_R3C6ActionPerformed

    private void R3C7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R3C7ActionPerformed
        // TODO add your handling code here:
        R3C7.setBackground(white);
        R3C7.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R3C7,number);
        
        st.push(hm);
    }//GEN-LAST:event_R3C7ActionPerformed

    private void R3C9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R3C9ActionPerformed
        // TODO add your handling code here:
        R3C9.setBackground(white);
        R3C9.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R3C9,number);
        
        st.push(hm);
    }//GEN-LAST:event_R3C9ActionPerformed

    private void R4C1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R4C1ActionPerformed
        // TODO add your handling code here:
        R4C1.setBackground(white);
        R4C1.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R4C1,number);
        
        st.push(hm);
    }//GEN-LAST:event_R4C1ActionPerformed

    private void R4C2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R4C2ActionPerformed
        // TODO add your handling code here:
        R4C2.setBackground(white);
        R4C2.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R4C2,number);
        
        st.push(hm);
    }//GEN-LAST:event_R4C2ActionPerformed

    private void R4C3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R4C3ActionPerformed
        // TODO add your handling code here:
        R4C3.setBackground(white);
        R4C3.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R4C3,number);
        
        st.push(hm);
    }//GEN-LAST:event_R4C3ActionPerformed

    private void R4C4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R4C4ActionPerformed
        // TODO add your handling code here:
        R4C4.setBackground(white);
        R4C4.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R4C4,number);
        
        st.push(hm);
    }//GEN-LAST:event_R4C4ActionPerformed

    private void R4C7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R4C7ActionPerformed
        // TODO add your handling code here:
        R4C7.setBackground(white);
        R4C7.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R4C7,number);
        
        st.push(hm);
    }//GEN-LAST:event_R4C7ActionPerformed

    private void R4C9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R4C9ActionPerformed
        // TODO add your handling code here:
        R4C9.setBackground(white);
        R4C9.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R4C9,number);
        
        st.push(hm);
    }//GEN-LAST:event_R4C9ActionPerformed

    private void R5C1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R5C1ActionPerformed
        // TODO add your handling code here:
        R5C1.setBackground(white);
        R5C1.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R5C1,number);
        
        st.push(hm);
    }//GEN-LAST:event_R5C1ActionPerformed

    private void R5C4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R5C4ActionPerformed
        // TODO add your handling code here:
        R5C4.setBackground(white);
        R5C4.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R5C4,number);
        
        st.push(hm);
    }//GEN-LAST:event_R5C4ActionPerformed

    private void R5C5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R5C5ActionPerformed
        // TODO add your handling code here:
        R5C5.setBackground(white);
        R5C5.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R5C5,number);
        
        st.push(hm);
    }//GEN-LAST:event_R5C5ActionPerformed

    private void R5C6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R5C6ActionPerformed
        // TODO add your handling code here:
        R5C6.setBackground(white);
        R5C6.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R5C6,number);
        
        st.push(hm);
    }//GEN-LAST:event_R5C6ActionPerformed

    private void R5C9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R5C9ActionPerformed
        // TODO add your handling code here:
        R5C9.setBackground(white);
        R5C9.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R5C9,number);
        
        st.push(hm);
    }//GEN-LAST:event_R5C9ActionPerformed

    private void R6C1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R6C1ActionPerformed
        // TODO add your handling code here:
        R6C1.setBackground(white);
        R6C1.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R6C1,number);
        
        st.push(hm);
    }//GEN-LAST:event_R6C1ActionPerformed

    private void R6C3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R6C3ActionPerformed
        // TODO add your handling code here:
        R6C3.setBackground(white);
        R6C3.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R6C3,number);
        
        st.push(hm);
    }//GEN-LAST:event_R6C3ActionPerformed

    private void R6C6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R6C6ActionPerformed
        // TODO add your handling code here:
        R6C6.setBackground(white);
        R6C6.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R6C6,number);
        
        st.push(hm);
    }//GEN-LAST:event_R6C6ActionPerformed

    private void R6C7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R6C7ActionPerformed
        // TODO add your handling code here:
        R6C7.setBackground(white);
        R6C7.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R6C7,number);
        
        st.push(hm);
    }//GEN-LAST:event_R6C7ActionPerformed

    private void R6C8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R6C8ActionPerformed
        // TODO add your handling code here:
        R6C8.setBackground(white);
        R6C8.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R6C8,number);
        
        st.push(hm);
    }//GEN-LAST:event_R6C8ActionPerformed

    private void R6C9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R6C9ActionPerformed
        // TODO add your handling code here:
        R6C9.setBackground(white);
        R6C9.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R6C9,number);
        
        st.push(hm);
    }//GEN-LAST:event_R6C9ActionPerformed

    private void R7C1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R7C1ActionPerformed
        // TODO add your handling code here:
        R7C1.setBackground(white);
        R7C1.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R7C1,number);
        
        st.push(hm);
    }//GEN-LAST:event_R7C1ActionPerformed

    private void R7C3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R7C3ActionPerformed
        // TODO add your handling code here:
        R7C3.setBackground(white);
        R7C3.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R7C3,number);
        
        st.push(hm);
    }//GEN-LAST:event_R7C3ActionPerformed

    private void R7C4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R7C4ActionPerformed
        // TODO add your handling code here:
        R7C4.setBackground(white);
        R7C4.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R7C4,number);
        
        st.push(hm);
    }//GEN-LAST:event_R7C4ActionPerformed

    private void R7C6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R7C6ActionPerformed
        // TODO add your handling code here:
        R7C6.setBackground(white);
        R7C6.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R7C6,number);
        
        st.push(hm);
    }//GEN-LAST:event_R7C6ActionPerformed

    private void R7C8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R7C8ActionPerformed
        // TODO add your handling code here:
        R7C8.setBackground(white);
        R7C8.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R7C8,number);
        
        st.push(hm);
    }//GEN-LAST:event_R7C8ActionPerformed

    private void R8C2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R8C2ActionPerformed
        // TODO add your handling code here:
        R8C2.setBackground(white);
        R8C2.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R8C2,number);
        
        st.push(hm);
    }//GEN-LAST:event_R8C2ActionPerformed

    private void R8C6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R8C6ActionPerformed
        // TODO add your handling code here:
        R8C6.setBackground(white);
        R8C6.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R8C6,number);
        
        st.push(hm);
    }//GEN-LAST:event_R8C6ActionPerformed

    private void R8C7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R8C7ActionPerformed
        // TODO add your handling code here:
        R8C7.setBackground(white);
        R8C7.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R8C7,number);
        
        st.push(hm);
    }//GEN-LAST:event_R8C7ActionPerformed

    private void R8C8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R8C8ActionPerformed
        // TODO add your handling code here:
        R8C8.setBackground(white);
        R8C8.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R8C8,number);
        
        st.push(hm);
    }//GEN-LAST:event_R8C8ActionPerformed

    private void R9C4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R9C4ActionPerformed
        // TODO add your handling code here:
        R9C4.setBackground(white);
        R9C4.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R9C4,number);
        
        st.push(hm);
    }//GEN-LAST:event_R9C4ActionPerformed

    private void R9C5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R9C5ActionPerformed
        // TODO add your handling code here:
        R9C5.setBackground(white);
        R9C5.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R9C5,number);
        
        st.push(hm);
    }//GEN-LAST:event_R9C5ActionPerformed

    private void R9C7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R9C7ActionPerformed
        // TODO add your handling code here:
        R9C7.setBackground(white);
        R9C7.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R9C7,number);
        
        st.push(hm);
    }//GEN-LAST:event_R9C7ActionPerformed

    private void R9C8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R9C8ActionPerformed
        // TODO add your handling code here:
        R9C8.setBackground(white);
        R9C8.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R9C8,number);
        
        st.push(hm);
    }//GEN-LAST:event_R9C8ActionPerformed

    private void R9C9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R9C9ActionPerformed
        // TODO add your handling code here:
        R9C9.setBackground(white);
        R9C9.setText(number);
        HashMap<JButton, String> hm = new HashMap<>();
        hm.put(R9C9,number);
        
        st.push(hm);
    }//GEN-LAST:event_R9C9ActionPerformed

    private void R1C4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R1C4ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "This place has already been allocated", "Sudoku game", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_R1C4ActionPerformed

    private void R1C9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R1C9ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "This place has already been allocated", "Sudoku game", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_R1C9ActionPerformed

    private void R2C1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R2C1ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "This place has already been allocated", "Sudoku game", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_R2C1ActionPerformed

    private void R2C5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R2C5ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "This place has already been allocated", "Sudoku game", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_R2C5ActionPerformed

    private void R2C6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R2C6ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "This place has already been allocated", "Sudoku game", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_R2C6ActionPerformed

    private void R2C7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R2C7ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "This place has already been allocated", "Sudoku game", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_R2C7ActionPerformed

    private void R2C9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R2C9ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "This place has already been allocated", "Sudoku game", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_R2C9ActionPerformed

    private void R3C1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R3C1ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "This place has already been allocated", "Sudoku game", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_R3C1ActionPerformed

    private void R3C3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R3C3ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "This place has already been allocated", "Sudoku game", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_R3C3ActionPerformed

    private void R3C5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R3C5ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "This place has already been allocated", "Sudoku game", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_R3C5ActionPerformed

    private void R4C5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R4C5ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "This place has already been allocated", "Sudoku game", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_R4C5ActionPerformed

    private void R4C6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R4C6ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "This place has already been allocated", "Sudoku game", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_R4C6ActionPerformed

    private void R4C8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R4C8ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "This place has already been allocated", "Sudoku game", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_R4C8ActionPerformed

    private void R5C2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R5C2ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "This place has already been allocated", "Sudoku game", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_R5C2ActionPerformed

    private void R5C3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R5C3ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "This place has already been allocated", "Sudoku game", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_R5C3ActionPerformed

    private void R5C7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R5C7ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "This place has already been allocated", "Sudoku game", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_R5C7ActionPerformed

    private void R5C8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R5C8ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "This place has already been allocated", "Sudoku game", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_R5C8ActionPerformed

    private void R6C2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R6C2ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "This place has already been allocated", "Sudoku game", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_R6C2ActionPerformed

    private void R6C5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R6C5ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "This place has already been allocated", "Sudoku game", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_R6C5ActionPerformed

    private void R7C2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R7C2ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "This place has already been allocated", "Sudoku game", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_R7C2ActionPerformed

    private void R7C5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R7C5ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "This place has already been allocated", "Sudoku game", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_R7C5ActionPerformed

    private void R7C7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R7C7ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "This place has already been allocated", "Sudoku game", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_R7C7ActionPerformed

    private void R7C9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R7C9ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "This place has already been allocated", "Sudoku game", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_R7C9ActionPerformed

    private void R8C1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R8C1ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "This place has already been allocated", "Sudoku game", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_R8C1ActionPerformed

    private void R8C4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R8C4ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "This place has already been allocated", "Sudoku game", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_R8C4ActionPerformed

    private void R8C5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R8C5ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "This place has already been allocated", "Sudoku game", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_R8C5ActionPerformed

    private void R8C9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R8C9ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "This place has already been allocated", "Sudoku game", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_R8C9ActionPerformed

    private void R9C1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R9C1ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "This place has already been allocated", "Sudoku game", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_R9C1ActionPerformed

    private void R9C2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R9C2ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "This place has already been allocated", "Sudoku game", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_R9C2ActionPerformed

    private void R9C3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R9C3ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "This place has already been allocated", "Sudoku game", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_R9C3ActionPerformed

    private void R9C6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R9C6ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "This place has already been allocated", "Sudoku game", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_R9C6ActionPerformed

    private void EXITBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EXITBTNActionPerformed
        // TODO add your handling code here:
        if(JOptionPane.showConfirmDialog(this,"Confirm if you want to Exit","Sudoku game",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION)
        {
            System.exit(0);
        }
    }//GEN-LAST:event_EXITBTNActionPerformed

    private void CHECKBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CHECKBTNActionPerformed
        // TODO add your handling code here:
        checkMoves();
    }//GEN-LAST:event_CHECKBTNActionPerformed

    private void RESETBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RESETBTNActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_RESETBTNActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SUDOKU_FRAME.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SUDOKU_FRAME.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SUDOKU_FRAME.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SUDOKU_FRAME.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SUDOKU_FRAME().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CHECKBTN;
    private javax.swing.JButton EXITBTN;
    private javax.swing.JButton R1C1;
    private javax.swing.JButton R1C2;
    private javax.swing.JButton R1C3;
    private javax.swing.JButton R1C4;
    private javax.swing.JButton R1C5;
    private javax.swing.JButton R1C6;
    private javax.swing.JButton R1C7;
    private javax.swing.JButton R1C8;
    private javax.swing.JButton R1C9;
    private javax.swing.JButton R2C1;
    private javax.swing.JButton R2C2;
    private javax.swing.JButton R2C3;
    private javax.swing.JButton R2C4;
    private javax.swing.JButton R2C5;
    private javax.swing.JButton R2C6;
    private javax.swing.JButton R2C7;
    private javax.swing.JButton R2C8;
    private javax.swing.JButton R2C9;
    private javax.swing.JButton R3C1;
    private javax.swing.JButton R3C2;
    private javax.swing.JButton R3C3;
    private javax.swing.JButton R3C4;
    private javax.swing.JButton R3C5;
    private javax.swing.JButton R3C6;
    private javax.swing.JButton R3C7;
    private javax.swing.JButton R3C8;
    private javax.swing.JButton R3C9;
    private javax.swing.JButton R4C1;
    private javax.swing.JButton R4C2;
    private javax.swing.JButton R4C3;
    private javax.swing.JButton R4C4;
    private javax.swing.JButton R4C5;
    private javax.swing.JButton R4C6;
    private javax.swing.JButton R4C7;
    private javax.swing.JButton R4C8;
    private javax.swing.JButton R4C9;
    private javax.swing.JButton R5C1;
    private javax.swing.JButton R5C2;
    private javax.swing.JButton R5C3;
    private javax.swing.JButton R5C4;
    private javax.swing.JButton R5C5;
    private javax.swing.JButton R5C6;
    private javax.swing.JButton R5C7;
    private javax.swing.JButton R5C8;
    private javax.swing.JButton R5C9;
    private javax.swing.JButton R6C1;
    private javax.swing.JButton R6C2;
    private javax.swing.JButton R6C3;
    private javax.swing.JButton R6C4;
    private javax.swing.JButton R6C5;
    private javax.swing.JButton R6C6;
    private javax.swing.JButton R6C7;
    private javax.swing.JButton R6C8;
    private javax.swing.JButton R6C9;
    private javax.swing.JButton R7C1;
    private javax.swing.JButton R7C2;
    private javax.swing.JButton R7C3;
    private javax.swing.JButton R7C4;
    private javax.swing.JButton R7C5;
    private javax.swing.JButton R7C6;
    private javax.swing.JButton R7C7;
    private javax.swing.JButton R7C8;
    private javax.swing.JButton R7C9;
    private javax.swing.JButton R8C1;
    private javax.swing.JButton R8C2;
    private javax.swing.JButton R8C3;
    private javax.swing.JButton R8C4;
    private javax.swing.JButton R8C5;
    private javax.swing.JButton R8C6;
    private javax.swing.JButton R8C7;
    private javax.swing.JButton R8C8;
    private javax.swing.JButton R8C9;
    private javax.swing.JButton R9C1;
    private javax.swing.JButton R9C2;
    private javax.swing.JButton R9C3;
    private javax.swing.JButton R9C4;
    private javax.swing.JButton R9C5;
    private javax.swing.JButton R9C6;
    private javax.swing.JButton R9C7;
    private javax.swing.JButton R9C8;
    private javax.swing.JButton R9C9;
    private javax.swing.JButton RESETBTN;
    private javax.swing.JButton SELECTIONBTN1;
    private javax.swing.JButton SELECTIONBTN2;
    private javax.swing.JButton SELECTIONBTN3;
    private javax.swing.JButton SELECTIONBTN4;
    private javax.swing.JButton SELECTIONBTN5;
    private javax.swing.JButton SELECTIONBTN6;
    private javax.swing.JButton SELECTIONBTN7;
    private javax.swing.JButton SELECTIONBTN8;
    private javax.swing.JButton SELECTIONBTN9;
    private javax.swing.JButton SOLUTIONBTN;
    private javax.swing.JButton UNDOBTN;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton108;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton120;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton59;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    // End of variables declaration//GEN-END:variables
}
