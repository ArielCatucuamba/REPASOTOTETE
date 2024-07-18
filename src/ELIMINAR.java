import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ELIMINAR extends JFrame{
    private JButton eliminarAlPacienteButton;
    private JTextField cedula;
    private JPanel panel1;

    public ELIMINAR(){
        setContentPane(panel1);
        eliminarAlPacienteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    eliminarU();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public void eliminarU() throws SQLException {
        String Cedula=cedula.getText();

        Connection conecta=conexion();

        String sql="delete from PACIENTE where cedula=?";
        PreparedStatement pstmt=conecta.prepareStatement(sql);
        pstmt.setString(1,Cedula);

        int UUU =pstmt.executeUpdate();
        if (UUU>0){
            JOptionPane.showMessageDialog(null,"Paciente eliminado");
        }

        conecta.close();
        pstmt.close();
    }





    //3 creo el metodo iniciar
    public void Iniciar(){
        setVisible(true);
        setTitle("Registro de usuarios");
        setSize(500,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    //4 como voy a hacer una consulta siempre necesitare primero una Conexion
    public Connection conexion() throws SQLException {
        //creo 3 variables (user,url,password)
        String url="jdbc:mysql://localhost:3306/sistema_hospitalario";
        String user="root";
        String password="123456";
        //retorno a la conexion para usarla despues
        return DriverManager.getConnection(url,user,password);
    }
}
