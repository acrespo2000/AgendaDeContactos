/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Andrés
 */
public class AgendaDeContactos extends javax.swing.JFrame {

    private final ArrayList<Contacto> contactos;
    private int indice=0;
    private Estados estado;
    
    private int boton;
    
    public AgendaDeContactos() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/principal/logo-google-contacts.png")).getImage());
        contactos=leerFicheroContactos();
    }

    
    private void actualizaDespuesDeCambiar(){
        indice--;
            Contacto c;
            if(indice<0){
                indice=0;
                c= contactos.get(0);
                lblNumeroContacto.setText(String.valueOf(0));
            }else{

                c= contactos.get(indice);
                lblNumeroContacto.setText(String.valueOf(indice));
            }
    }
    
    private void actualizar(int indice){
        Contacto c=contactos.get(indice);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        posicion(indice);
        lblNumeroContacto.setText(String.valueOf(indice));
        jTextNombre.setText(c.getNombre());
        jTextApellido1.setText(c.getApellido1());
        jTextApellido2.setText(c.getApellido2());
        jTextTelefono.setText(String.valueOf(c.getTelefono()));
        jTextNif.setText(c.getDni());
        jTextFecha.setText(sdf.format(c.getNacimiento()));
        jTextTipo.setText(String.valueOf(c.getTipo()));
    }
    
    private void posicion(int indice){
        if(indice<=0){
           jButtonAnterior.setEnabled(false);
           jButtonInicio.setEnabled(false);
           jButtonFinal.setEnabled(true);
           jButtonSiguiente.setEnabled(true);
        }else if(indice==contactos.size()-1){
           jButtonAnterior.setEnabled(true);
           jButtonInicio.setEnabled(true);
           jButtonFinal.setEnabled(false);
           jButtonSiguiente.setEnabled(false);
        }else{
           jButtonAnterior.setEnabled(true);
           jButtonInicio.setEnabled(true);
           jButtonFinal.setEnabled(true);
           jButtonSiguiente.setEnabled(true);
        }
    }
    
    private void CambiarEstado(Estados estado){
        switch(estado){
            case NAVEGACION:
                btnAñadir.setEnabled(true);
                btnEditar.setEnabled(true);
                btnEliminar.setEnabled(true);
                btnAceptar.setEnabled(false);
                btnCancelar.setEnabled(false);
                jButtonFinal.setEnabled(true);
                jButtonSiguiente.setEnabled(true);
                jButtonAnterior.setEnabled(true);
                jButtonInicio.setEnabled(true);
                cmbTipo.setEnabled(false);
                jTextNombre.setEditable(false);
                jTextApellido1.setEditable(false);
                jTextApellido2.setEditable(false);
                jTextTelefono.setEditable(false);
                jTextNif.setEditable(false);
                jTextFecha.setEditable(false);
                jTextTipo.setEditable(false);
                break;
            case ADICION:
                btnAñadir.setEnabled(false);
                btnEditar.setEnabled(false);
                btnEliminar.setEnabled(false);
                btnAceptar.setEnabled(false);
                btnCancelar.setEnabled(false);
                jButtonFinal.setEnabled(false);
                jButtonSiguiente.setEnabled(false);
                jButtonAnterior.setEnabled(false);
                jButtonInicio.setEnabled(false);
                cmbTipo.setEnabled(false);
                jTextNombre.setEditable(false);
                jTextApellido1.setEditable(false);
                jTextApellido2.setEditable(false);
                jTextTelefono.setEditable(false);
                jTextNif.setEditable(false);
                jTextFecha.setEditable(false);
                jTextTipo.setEditable(false);

                break;
            case ELIMINACION:
                btnAñadir.setEnabled(false);
                btnEditar.setEnabled(false);
                btnEliminar.setEnabled(false);
                btnAceptar.setEnabled(true);
                btnCancelar.setEnabled(true);
                jButtonFinal.setEnabled(false);
                jButtonSiguiente.setEnabled(false);
                jButtonAnterior.setEnabled(false);
                jButtonInicio.setEnabled(false);
                cmbTipo.setEnabled(false);
                jTextNombre.setEditable(false);
                jTextApellido1.setEditable(false);
                jTextApellido2.setEditable(false);
                jTextTelefono.setEditable(false);
                jTextNif.setEditable(false);
                jTextFecha.setEditable(false);
                jTextTipo.setEditable(false);
                break;
            case EDICION:
                btnAñadir.setEnabled(false);
                btnEditar.setEnabled(false);
                btnEliminar.setEnabled(false);
                btnAceptar.setEnabled(false);
                btnCancelar.setEnabled(false);
                jButtonFinal.setEnabled(false);
                jButtonSiguiente.setEnabled(false);
                jButtonAnterior.setEnabled(false);
                jButtonInicio.setEnabled(false);
                cmbTipo.setEnabled(false);
                jTextNombre.setEditable(false);
                jTextApellido1.setEditable(false);
                jTextApellido2.setEditable(false);
                jTextTelefono.setEditable(false);
                jTextNif.setEditable(false);
                jTextFecha.setEditable(false);
                jTextTipo.setEditable(false);
                break;
        }
    }

    private ArrayList<Contacto> leerFicheroContactos(){
        ArrayList<Contacto> lista = new ArrayList<>();
        
        Scanner lector;
        try {
            lector = new Scanner(new FileReader("contactos.txt"));
            while(lector.hasNextLine()){
                String registro = lector.nextLine();
                Contacto nuevo = new Contacto(registro);
                lista.add(nuevo);
            }
            lector.close();
        } catch (IOException ex) {
            System.out.println("Error, al leer/usar el fichero");
        } catch (ParseException ex) {
            System.out.println("Error al parsear los datos");
        }
        return lista;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNumeroContacto = new javax.swing.JLabel();
        jButtonAnterior = new javax.swing.JButton();
        jButtonInicio = new javax.swing.JButton();
        jButtonSiguiente = new javax.swing.JButton();
        jButtonFinal = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextNif = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextTelefono = new javax.swing.JTextField();
        jTextFecha = new javax.swing.JTextField();
        jTextTipo = new javax.swing.JTextField();
        jTextNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextApellido2 = new javax.swing.JTextField();
        jTextApellido1 = new javax.swing.JTextField();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnAñadir = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        cmbTipo = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Agenda de contactos");
        setIconImages(null);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        lblNumeroContacto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNumeroContacto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNumeroContacto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButtonAnterior.setText("<");
        jButtonAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnteriorActionPerformed(evt);
            }
        });

        jButtonInicio.setText("|<");
        jButtonInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInicioActionPerformed(evt);
            }
        });

        jButtonSiguiente.setText(">");
        jButtonSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSiguienteActionPerformed(evt);
            }
        });

        jButtonFinal.setText(">|");
        jButtonFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFinalActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Nombre:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Telefono:");

        jTextNif.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Tipo:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Fecha de nacimiento:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("NIF:");

        jTextTelefono.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTextFecha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTextTipo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTextNombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Primer apellido:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Segundo apellido:");

        jTextApellido2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTextApellido1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnEditar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnAñadir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAñadir.setText("Añadir");
        btnAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirActionPerformed(evt);
            }
        });

        btnAceptar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        cmbTipo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AMIGO", "FAMILIAR", "ENEMIGO", "TRABAJO" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonInicio)
                                .addGap(91, 91, 91)
                                .addComponent(jButtonAnterior)
                                .addGap(85, 85, 85)
                                .addComponent(lblNumeroContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(105, 105, 105)
                                .addComponent(jButtonSiguiente)
                                .addGap(73, 73, 73)
                                .addComponent(jButtonFinal))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAñadir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 81, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                            .addComponent(jTextNif, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                            .addComponent(jTextFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                            .addComponent(jTextTipo, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                            .addComponent(jTextNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                            .addComponent(jTextApellido2)
                            .addComponent(jTextApellido1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(345, Short.MAX_VALUE))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonAnterior, jButtonInicio});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel2, jLabel7});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextApellido1)
                            .addComponent(btnEliminar))
                        .addGap(3, 3, 3)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextApellido2)
                        .addComponent(btnAñadir)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(jTextTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextNif, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(btnAceptar))
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbTipo)
                    .addComponent(btnCancelar)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNumeroContacto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSiguiente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3, jLabel7});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnteriorActionPerformed
        indice--;
        Contacto c;
        if(indice<0){
            indice=0;
            c= contactos.get(0);
            lblNumeroContacto.setText(String.valueOf(0));
        }else{
            
            c= contactos.get(indice);
            lblNumeroContacto.setText(String.valueOf(indice));
        }
        
        actualizar(indice);
        posicion(indice);
    }//GEN-LAST:event_jButtonAnteriorActionPerformed

    private void jButtonInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInicioActionPerformed
        indice=0;
        actualizar(indice);
        posicion(indice);
    }//GEN-LAST:event_jButtonInicioActionPerformed

    private void jButtonSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSiguienteActionPerformed
        indice++;
        Contacto c;
        if(indice>contactos.size()-1){
            indice=contactos.size()-1;
            c= contactos.get(indice);
            lblNumeroContacto.setText(String.valueOf(indice));
        }else{
            c= contactos.get(indice);
            lblNumeroContacto.setText(String.valueOf(indice));
        }

        actualizar(indice);
        posicion(indice);
    }//GEN-LAST:event_jButtonSiguienteActionPerformed

    private void jButtonFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFinalActionPerformed
        indice=contactos.size()-1;
        actualizar(indice);
        posicion(indice);
    }//GEN-LAST:event_jButtonFinalActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Contacto c= contactos.get(indice);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        posicion(indice);
        lblNumeroContacto.setText(String.valueOf(indice));
        jTextNombre.setText(c.getNombre());
        jTextApellido1.setText(c.getApellido1());
        jTextApellido2.setText(c.getApellido2());
        jTextTelefono.setText(String.valueOf(c.getTelefono()));
        jTextNif.setText(c.getDni());
        jTextFecha.setText(sdf.format(c.getNacimiento()));
        jTextTipo.setText(String.valueOf(c.getTipo()));
        
        posicion(0);
        CambiarEstado(estado.NAVEGACION);

    }//GEN-LAST:event_formWindowOpened

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        CambiarEstado(estado.EDICION);
        Contacto c= contactos.get(indice);
        Editar  captura = new Editar(this, true, c);
        captura.setVisible(true);
    }//GEN-LAST:event_btnEditarActionPerformed
   
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        CambiarEstado(estado.ELIMINACION);
        boton=2;
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirActionPerformed
        CambiarEstado(estado.ADICION);
        jTextNombre.setText("");
        jTextApellido1.setText("");
        jTextApellido2.setText("");
        jTextTelefono.setText("");
        jTextNif.setText("");
        jTextFecha.setText("");
        jTextTipo.setText("");
        lblNumeroContacto.setText(String.valueOf(contactos.size()));
        indice=contactos.size();
        Añadir  captura = new Añadir(this, true);
        captura.setVisible(true);
    }//GEN-LAST:event_btnAñadirActionPerformed

    public void recibeDatosCapturados(Contacto datos){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Contacto c= new Contacto();
        indice=contactos.size();
        contactos.add(datos);
        try {
            File f3= new File("contactos.txt");
            BufferedWriter fichero = new BufferedWriter(new FileWriter(f3));
            //Escribimos los datos recogidos
            for(Contacto d: contactos){
                fichero.write(d.getNombre()+"_"+d.getApellido1()+"_"+d.getApellido2()+"_"+d.getTelefono()+"_"+d.getTipo()+"_"+d.getDni()+"_"+sdf.format(d.getNacimiento()));
                fichero.newLine();//SALTO DE LINEA
            }
            fichero.close(); 
        } catch (FileNotFoundException fNFE) {
            System.out.println("No se encuentra el fichero");
        }catch(IOException io){
            System.out.println("Error de E/S");
            System.out.println("Variables involucradas: fichero, (clase BufferesWriter), ruta(clase File.)"); 
        }
        
        indice=0;   //volvemos al inicio
        leerFicheroContactos(); //leer el fichero otra vez para actualizar
        actualizar(indice);
        CambiarEstado(estado.NAVEGACION);
    }
    
    public void datosEditados(Contacto datos){
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            File f3= new File("contactos.txt");
            BufferedWriter fichero = new BufferedWriter(new FileWriter(f3));
            //Escribimos los datos recogidos
            for(Contacto d: contactos){
                fichero.write(d.getNombre()+"_"+d.getApellido1()+"_"+d.getApellido2()+"_"+d.getTelefono()+"_"+d.getTipo()+"_"+d.getDni()+"_"+sdf.format(d.getNacimiento()));
                fichero.newLine();//SALTO DE LINEA
            }
            fichero.close(); 
        } catch (FileNotFoundException fNFE) {
            System.out.println("No se encuentra el fichero");
        }catch(IOException io){
            System.out.println("Error de E/S");
            System.out.println("Variables involucradas: fichero, (clase BufferesWriter), ruta(clase File.)"); 
        }
        
        indice=0;   //volvemos al inicio
        leerFicheroContactos(); //leer el fichero otra vez para actualizar
        actualizar(indice);
        CambiarEstado(estado.NAVEGACION);
    }
    
    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        if(boton==2){
            indice=Integer.parseInt(lblNumeroContacto.getText());
            contactos.remove(indice);
            actualizaDespuesDeCambiar();
            actualizar(indice);
            posicion(indice);  
        }
        CambiarEstado(estado.NAVEGACION);
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        CambiarEstado(estado.NAVEGACION);
        indice=0;
        actualizar(indice);
        posicion(indice);
        
    }//GEN-LAST:event_btnCancelarActionPerformed

    public void cancelar(){
        CambiarEstado(estado.NAVEGACION);
        indice=0;
        actualizar(indice);
        posicion(indice);
    }
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
            java.util.logging.Logger.getLogger(AgendaDeContactos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgendaDeContactos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgendaDeContactos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgendaDeContactos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AgendaDeContactos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnAñadir;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JButton jButtonAnterior;
    private javax.swing.JButton jButtonFinal;
    private javax.swing.JButton jButtonInicio;
    private javax.swing.JButton jButtonSiguiente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField jTextApellido1;
    private javax.swing.JTextField jTextApellido2;
    private javax.swing.JTextField jTextFecha;
    private javax.swing.JTextField jTextNif;
    private javax.swing.JTextField jTextNombre;
    private javax.swing.JTextField jTextTelefono;
    private javax.swing.JTextField jTextTipo;
    private javax.swing.JLabel lblNumeroContacto;
    // End of variables declaration//GEN-END:variables
}
