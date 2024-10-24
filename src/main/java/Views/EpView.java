/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;
import persistencia.ClasesDao.ReservaDAO;
import modelo.Reserva;
import modelo.Empleado;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Rebechi
 */
public class EpView extends javax.swing.JFrame {
    private DefaultTableModel tabla;
    private ReservaDAO reserva;
    private ArrayList<Reserva> reservas;
   
    private Empleado empleado;
    /**
     * Creates new form EpView
     */
    public EpView(Empleado empleado) {
        initComponents();
         setSize(1000, 600);
                setResizable(false);
                setTitle("Sistema de Clientes");
                setLocationRelativeTo(null);
        tabla = (DefaultTableModel) tablaListadoMesasEmpleado.getModel();
        this.empleado=empleado;
                
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
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jToggleButton_empleado_salir = new javax.swing.JToggleButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaListadoMesasEmpleado = new javax.swing.JTable();
        btnRecepcionistaAsistencia = new javax.swing.JButton();
        jButtonMesasHoy = new javax.swing.JButton();
        jButtonMeseroHoraInicio = new javax.swing.JButton();
        jButtonMeseroHoraFin = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Logo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 90, 60));

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Restaurante Floripa");

        jToggleButton_empleado_salir.setText("Salir");
        jToggleButton_empleado_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton_empleado_salirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 271, Short.MAX_VALUE)
                .addComponent(jToggleButton_empleado_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jToggleButton_empleado_salir))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, 640, 60));

        jTabbedPane1.setBackground(new java.awt.Color(0, 102, 102));
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Empleado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jPanel4.setBackground(new java.awt.Color(0, 51, 51));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Mesa"));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Listado de Mesas ");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        tablaListadoMesasEmpleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numero", "Cliente", "Comentario", "Asistencia", "Hora Inicio", "Hora Fin"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaListadoMesasEmpleado);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 650, 220));

        btnRecepcionistaAsistencia.setText("Marcar asistencia");
        btnRecepcionistaAsistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecepcionistaAsistenciaActionPerformed(evt);
            }
        });
        jPanel4.add(btnRecepcionistaAsistencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, -1, -1));

        jButtonMesasHoy.setText("Mesas de hoy");
        jButtonMesasHoy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMesasHoyActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonMesasHoy, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jButtonMeseroHoraInicio.setText("Inicio");
        jButtonMeseroHoraInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMeseroHoraInicioActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonMeseroHoraInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jButtonMeseroHoraFin.setText("Fin");
        jPanel4.add(jButtonMeseroHoraFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        jLabel4.setText("Hora");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));
        jPanel4.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 80, -1));

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel4.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 80, -1));

        jTabbedPane1.addTab("Lista Mesas", jPanel4);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 820, 450));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton_empleado_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton_empleado_salirActionPerformed
        if(evt.getSource()== jToggleButton_empleado_salir){
          dispose();
          Inicio login = new Inicio();
          login.setVisible(true);
    }
    }//GEN-LAST:event_jToggleButton_empleado_salirActionPerformed

    private void btnRecepcionistaAsistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecepcionistaAsistenciaActionPerformed
      //falta revisar si el empleado es recepcionista
      //revisar en la base de datos? o con el objeto cliente?
        int filaSeleccionada = tablaListadoMesasEmpleado.getSelectedRow();
       if (filaSeleccionada != -1) {
           reservas=reserva.obtenerReservasDeHoy();
            String asistenciaActual = (String) tabla.getValueAt(filaSeleccionada, 3); // Asistencia actual

            // Cambiar el estado de "asistencia" en la base de datos
            int nuevoEstado = asistenciaActual.equals("Asistio") ? 0 : 1;
            boolean actualizado =reserva.cambiarAsistencia(filaSeleccionada, reservas);
            if(actualizado){
                javax.swing.JOptionPane.showMessageDialog(this, "Asistencia actualizada", "Asistencia", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }else{
                javax.swing.JOptionPane.showMessageDialog(this, "No se pudo actualizar la asistencia", "Advertencia", javax.swing.JOptionPane.WARNING_MESSAGE);
            }
       }
    }//GEN-LAST:event_btnRecepcionistaAsistenciaActionPerformed

    private void jButtonMesasHoyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMesasHoyActionPerformed
    tabla.setRowCount(0);
    Object[] o = new Object [4];
    reserva= new ReservaDAO();
    reservas = new ArrayList<Reserva>();
    reservas= reserva.obtenerReservasDeHoy();
    for(Reserva reserva1: reservas){   
        o[0]=reserva1.getMesa().getNumero();
        o[1]=reserva1.getCliente().getNombre();
        o[2]=reserva1.getComentario();
        if(reserva1.getAsistencia()){
            o[3]="Asistio";
        }else{
            o[3]="No asistio";
        }
        tabla.addRow(o);
    }
    }//GEN-LAST:event_jButtonMesasHoyActionPerformed

    private void jButtonMeseroHoraInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMeseroHoraInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonMeseroHoraInicioActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnRecepcionistaAsistencia;
    private javax.swing.JButton jButtonMesasHoy;
    private javax.swing.JButton jButtonMeseroHoraFin;
    private javax.swing.JButton jButtonMeseroHoraInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JToggleButton jToggleButton_empleado_salir;
    public javax.swing.JTable tablaListadoMesasEmpleado;
    // End of variables declaration//GEN-END:variables
}
