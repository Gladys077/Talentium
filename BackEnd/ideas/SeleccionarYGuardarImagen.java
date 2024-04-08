package org.example;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Clock;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SeleccionarYGuardarImagen {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/fotos";
        String usuario = "root";
        String contraseña = "1234";

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();

        try {
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO productos (nombre, foto) VALUES (?, ?)");

            String nombre = "foto1.jpg";
            ps.setString(1, nombre);

            /**
             * <p>convirtiendo la imagen desde el front con un FileInputStream para guardarla en la bbdd</p>
             * */
            FileInputStream fis = new FileInputStream(file);
            ps.setBinaryStream(2, fis, (int) file.length());
            ps.executeUpdate();

            ps.close();
            conexion.close();

            System.out.println("La imagen se ha guardado correctamente en la base de datos.");

            // Mostrar la imagen desde la base de datos
            mostrarImagenDesdeBD(url, usuario, contraseña);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void mostrarImagenDesdeBD(String url, String usuario, String contraseña) {
        try {
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
            PreparedStatement ps = conexion.prepareStatement("SELECT foto FROM productos WHERE id = ?");
            ps.setInt(1, 11); // Reemplaza con el ID de la imagen que deseas obtener

           ImageIcon imagen = null;


            ResultSet rs = ps.executeQuery();
            /**
             * <p>convirtiendo la imagen desde la bbdd para exponerla en un label</p>
             * */
            if (rs.next()) {
                InputStream is = rs.getBinaryStream("foto");

                 imagen = new ImageIcon(ImageIO.read(is));
                Image image = imagen.getImage();

            }

            ps.close();
            conexion.close();

            // Mostrar la imagen en una ventana
            if (imagen != null) {
                JLabel label = new JLabel(imagen);
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(label);
                frame.pack();
                frame.setVisible(true);
                System.out.println("La imagen se ha cargado correctamente desde la base de datos y se ha mostrado en una ventana.");
            } else {
                System.out.println("No se encontró ninguna imagen en la base de datos con el ID especificado.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
