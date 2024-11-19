package Views;

import persistencia.ClasesDao.ClienteDAO;
import modelo.Cliente;

/**
 * Ventana grafica que permite recuperar la contraseña de un cliente
 *
 * @author Marcos Ramon Caraballo, Angelina María Vialle,Valentin Rebechi,Ian
 * Caraballo
 * @version 27/10/2024
 */
public class ClRecuperarC extends javax.swing.JFrame {

    /**
     * Constructor por defecto de la ventana grafica. Se encarga de inicializar
     * y configurar los componentes de la interfaz gráfica de usuario. Tambien
     * de centrarla
     *
     */
    public ClRecuperarC() {
        initComponents();
        setLocationRelativeTo(null);

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
        jLCorreo = new javax.swing.JLabel();
        correo = new javax.swing.JTextField();
        enviar = new javax.swing.JButton();
        atras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Recuperar Contraseña");

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        jLCorreo.setForeground(new java.awt.Color(255, 255, 255));
        jLCorreo.setText("Correo");

        correo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                correoActionPerformed(evt);
            }
        });

        enviar.setText("Enviar");
        enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarActionPerformed(evt);
            }
        });

        atras.setText("Atras");
        atras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atrasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(enviar)
                        .addGap(28, 28, 28)
                        .addComponent(atras))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLCorreo)
                        .addGap(18, 18, 18)
                        .addComponent(correo, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLCorreo)
                    .addComponent(correo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enviar)
                    .addComponent(atras))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void correoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_correoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_correoActionPerformed
    /**
     * Método llamado cuando el usuario presionar el botón enviar. Revisa que el
     * campo de correo electronico no esté vacío, en caso de no estar vacio
     * llama el metodo recuperarContraseña de la clase ClienteDAO y muestra en
     * pantalla el resultado de llamar a ese metodo.
     *
     * @param   evt representa el evento que se genera al presionar el boton
     * enviar.
     */
    private void enviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarActionPerformed
        String correoIngreso = correo.getText();
        if (correoIngreso.isEmpty()) {

            javax.swing.JOptionPane.showMessageDialog(this,
                    "Por favor, ingrese su correo.",
                    "Advertencia",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente1 = new Cliente();

        String resultado = clienteDAO.recuperarContraseña(correoIngreso);

        if (!resultado.equals("")) {
            if(!resultado.equals("Correo no encontrado")){
            javax.swing.JOptionPane.showMessageDialog(this,
                    resultado,
                    "Recuperar Contraseña",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }else{
            javax.swing.JOptionPane.showMessageDialog(this,
                    resultado,
                    "Advertencia",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Ocurrio un error",
                    "Advertencia",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_enviarActionPerformed
    /**
     * Se encarga de generar una nueva ventana Inicio, hacerla visible y cerrar
     * la ventana actual.
     *
     * @param   evt el evento generado al presionar el boton "Atras".
     */
    private void atrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atrasActionPerformed
        Inicio inicio = new Inicio();
        inicio.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_atrasActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton atras;
    private javax.swing.JTextField correo;
    private javax.swing.JButton enviar;
    private javax.swing.JLabel jLCorreo;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
