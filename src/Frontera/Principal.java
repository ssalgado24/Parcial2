/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontera;

import Control.Validacion;
import Entidad.*;
import DAO.*;
import static java.lang.Math.random;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Samuel
 */
public class Principal extends javax.swing.JFrame {
   TablaHD historico=new TablaHD();
   TablaSensor sens= new TablaSensor();
   TablaTS tablasens= new TablaTS();
   Date date=new Date();
   Validacion val=new Validacion();
   TablaHDDAO daoHD= new TablaHDDAO();
   TablaSensorDAO daoS= new TablaSensorDAO();
   TablaTSDAO daoTS= new TablaTSDAO();
   ArrayList<TablaHD> listaH= new ArrayList<>();
   double avg;
    /**
     * Creates new form Principal
     */
   
    public Principal() {
        initComponents();
        inicializacion();
    }
    public void inicializacion(){
        tablasens.setTipo("HUMEDAD");
        tablasens.setNombre("Sensor de humedad");
        tablasens.setMinimo(100);
        tablasens.setMaximo(850);
        tablasens.setPromedio("Si");
        tablasens.setNumeroHoras(2);
        
        
        sens.setTipo(tablasens.getTipo());
        sens.setUbicacion("Sector I");
        sens.setIdsensor(50);
        
        System.out.println("Sensor");
        System.out.println(sens.getIdsensor() + " " + sens.getUbicacion() + " " + sens.getTipo());
        System.out.println("\n Tipo de Sensor");
        System.out.println(tablasens.getTipo()+ " " + tablasens.getNombre() + " (" + tablasens.getMinimo() + " - " + tablasens.getMaximo()+")");
        
    }
    
    public ArrayList<TablaHD> crearLista (){     
        listaH.clear();
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String url="jdbc:derby://localhost:1527/BaseParcial";
            String login="admin_APP";
            String password="samuel";
            Connection con= DriverManager.getConnection(url,login , password);
            String query="SELECT * FROM ADMIN_APP.HISTORICO ORDER BY id DESC";
            Statement st=con.createStatement();
            ResultSet rs= st.executeQuery(query);
            TablaHD hd;
            while (rs.next()){
                hd= new TablaHD(rs.getInt("IDSENSOR"),rs.getDouble("VALUE"),rs.getString("DATE"), rs.getInt("ID"));
                listaH.add(hd); 
            }                
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return listaH;
        
    }  
  
    public void showDatos(){
        DefaultTableModel model= (DefaultTableModel) jTable1.getModel();   
        Object[] row= new Object[4];  
        
        int rowCount = model.getRowCount();
            for(int i = 0; i < rowCount; i++){
                model.removeRow(0);
            } 
        
        for(int i=0;i<5;i++){
            row[0]=listaH.get(i).getId();
            row[1]=listaH.get(i).getIdsensor();
            row[2]=listaH.get(i).getValue();
            row[3]=listaH.get(i).getDate();
            model.addRow(row);           
        }
    }
    public void showDatos2(){
        DefaultTableModel model= (DefaultTableModel) jTable3.getModel();   
        Object[] row= new Object[4];  
        
        int rowCount = model.getRowCount();
            for(int i = 0; i < rowCount; i++){
                model.removeRow(0);
            } 
        
        for(int i=0;i<1;i++){
            row[0]=listaH.get(i).getId();
            row[1]=listaH.get(i).getIdsensor();
            row[2]=listaH.get(i).getValue();
            row[3]=listaH.get(i).getDate();
            model.addRow(row);           
        }
        String valorNo=row[2].toString();
        double valNo= Double.parseDouble(valorNo);
        if(valNo<150){
            JOptionPane.showMessageDialog(null, "Inferior al mínimo permitido \n Promedio: "+valNo);
        }else if(valNo>850){
            JOptionPane.showMessageDialog(null, "Superior al máximo permitido \n Promedio: "+valNo);
        }else{
            JOptionPane.showMessageDialog(null, "Está entre el mínimo y el máximo permitido\n Promedio: "+valNo);
        }    
        
        
    }
    
    public Double crearListaParcial (){     
        listaH.clear();
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String url="jdbc:derby://localhost:1527/BaseParcial";
            String login="admin_APP";
            String password="samuel";
            Connection con= DriverManager.getConnection(url,login , password);
            String query="SELECT AVG(VALUE) AS VALOR_PROMEDIO FROM ADMIN_APP.HISTORICO WHERE DATE BETWEEN '2020-12-09 04:20:00.611' AND CURRENT_TIMESTAMP";
            Statement st=con.createStatement();
            ResultSet rs= st.executeQuery(query);
            TablaHD hd;
            while (rs.next()){
                avg=rs.getDouble("VALOR_PROMEDIO"); 
            }                
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        if(avg<150){
            JOptionPane.showMessageDialog(null, "Inferior al mínimo permitido \n Promedio: "+avg);
        }else if(avg>850){
            JOptionPane.showMessageDialog(null, "Superior al máximo permitido \n Promedio: "+avg);
        }else{
            JOptionPane.showMessageDialog(null, "Está entre el mínimo y el máximo permitido\n Promedio: "+avg);
        }  
        return avg;
        
    }
    public void validarPromedio(TablaTS tablasens){
        String prom=tablasens.getPromedio();
        System.out.println(prom);
        if(prom.equals("No")){
            crearLista();
            showDatos2();
        }else if(prom.equals("Si")){
            crearListaParcial();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        enviarDatoB = new javax.swing.JButton();
        mostrarDatosB = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        panelPrincipal = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        enviarDatoB.setText("Enviar Dato");
        enviarDatoB.setFocusable(false);
        enviarDatoB.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        enviarDatoB.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        enviarDatoB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarDatoBActionPerformed(evt);
            }
        });
        jToolBar1.add(enviarDatoB);

        mostrarDatosB.setText("Mostrar Datos");
        mostrarDatosB.setFocusable(false);
        mostrarDatosB.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mostrarDatosB.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        mostrarDatosB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarDatosBActionPerformed(evt);
            }
        });
        jToolBar1.add(mostrarDatosB);

        jButton1.setText("Procesamiento de datos");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "ID Sensor", "Valor_Humedad", "Fecha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "ID", "ID Sensor", "Valor Humedad", "Fecha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mostrarDatosBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrarDatosBActionPerformed
        // TODO add your handling code here:
        jTable1.setVisible(true);
        crearLista();
        showDatos();
    }//GEN-LAST:event_mostrarDatosBActionPerformed

    private void enviarDatoBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarDatoBActionPerformed
        // TODO add your handling code here:
        historico.setIdsensor(sens.getIdsensor());
        historico.setValue(Math.random()*950);
        historico.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(date));
        if(val.verificar(historico.getValue())==true){
            System.out.println(historico.getId()+ " " +historico.getIdsensor()+ " " +historico.getValue()+ " " +historico.getDate());
            daoHD.crear(historico);                    
        }  
        jTable1.setVisible(false);
    }//GEN-LAST:event_enviarDatoBActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jTable3.setVisible(true);
        validarPromedio(tablasens);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton enviarDatoB;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton mostrarDatosB;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
