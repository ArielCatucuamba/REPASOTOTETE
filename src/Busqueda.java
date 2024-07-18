import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

//1 pongo extends JFrame
public class Busqueda extends JFrame{
    private JButton verificarCedulaDelPacienteButton;
    private JButton regresarARegistroDeButton;
    private JButton reubicarseEnLogueoDeButton;
    private JTextField cedu;
    private JPanel panel1;

    //2 creo el metodo constructor
    public Busqueda(){
        setContentPane(panel1);
        verificarCedulaDelPacienteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    consultar();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        regresarARegistroDeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Registro r2=new Registro();
                r2.Iniciar();
                dispose();
            }
        });
        reubicarseEnLogueoDeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Login l1=new Login();
                l1.Iniciar();
                dispose();
            }
        });
    }

    //4 creo mi metodo para hacer la consulta
    public void consultar() throws SQLException {
        //5 traigo el JtextField de mi cedula
        String ced=cedu.getText();

        //6 creo mi objeto Connection
        Connection conecta=conexion();

        //7 creo mi consulta
        String sql="select * from PACIENTE where cedula=?";
        //8 creo mi preparedStatement
        PreparedStatement pstmt=conecta.prepareStatement(sql);
        //como tengo ? , debo especificar que va ahi
        pstmt.setString(1,ced);

        //9 Creo mi resultset
        ResultSet rs=pstmt.executeQuery();
        //10 recorro a mi resultSet
        if (rs.next()){
            JOptionPane.showMessageDialog(null,"Paciente encontrado");
            //uso getString que es una funcion del ResutlSet por eso pongo rs. (es para traer la info de la base de datos)
            String c =rs.getString("cedula");
            String h = rs.getString("n_historial_clinico");
            String n=rs.getString("nombre");
            String a =rs.getString("apellido");
            String t=rs.getString("telefono");
            String e=rs.getString("edad");
            String d=rs.getString("descripcion_enfermedad");


            JOptionPane.showMessageDialog(null,"Cedula: "+c+" | "+"Historial clinico: "+h+" | "+
                    "Nombre: "+n+" | "+"Apellido: "+a+" | "+"Telefono: "+t+" | "+"Edad: "+e+" | "+
                    "Descripcion: "+d+" | ");
        }else {
            JOptionPane.showMessageDialog(null,"No se a encontrado el paciente");
        }



    }



    //creo el metodo iniciar
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
