import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Actualizacion extends JFrame{
    private JButton actualizarRegistroButton;
    private JTextField cedula;
    private JTextField campo;
    private JTextField valor;
    private JPanel panel1;

    public Actualizacion(){
        setContentPane(panel1);
        actualizarRegistroButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Actualizar();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }


    public void Actualizar() throws SQLException {
        String Cedula=cedula.getText();
        String Campo=campo.getText();
        String Valor=valor.getText();

        //6 creo mi objeto Connection
        Connection conecta=conexion();

        //7 creo mi consulta
        String sql="update PACIENTE set "+Campo+"=? where cedula=?";
        //8 creo mi preparedStatement
        PreparedStatement pstmt=conecta.prepareStatement(sql);
        //como tengo ? , debo especificar que va ahi
        pstmt.setString(1,Valor);
        pstmt.setString(2,Cedula);

        int a=pstmt.executeUpdate();
        if (a>0){
            JOptionPane.showMessageDialog(null,"Registro actualizado con exito");
        }else {
            JOptionPane.showMessageDialog(null,"Registro no actualizado!!!!");
        }

        conecta.close();
        pstmt.close();
    }




    public void Iniciar(){
        setVisible(true);
        setTitle("Busqueda de pacientes");
        setSize(500,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    //3 creo el metodo Connection
    public Connection conexion() throws SQLException {
        //Creo 3 variables para almacenar el url,user y pass
        String url="jdbc:mysql://localhost:3306/sistema_hospitalario";
        String user="root";
        String password="123456";
        return DriverManager.getConnection(url,user,password);
    }
}
