import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//1 pongo extends JFrame
public class Registro extends JFrame{
    private JButton registrarNuevosPacientesButton;
    private JButton buscarPacientesButton;
    private JTextField cedula;
    private JTextField historial;
    private JTextField nombre;
    private JTextField apellido;
    private JTextField telefono;
    private JTextField edad;
    private JTextField descripcion;
    private JPanel panel1;

    //2 creo el metodo contructor
    public Registro(){
        setContentPane(panel1);

        registrarNuevosPacientesButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Insertar();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    // 5 creo un metodo de registro osea insertar datos y guardarlos en la base de daros
    public void Insertar() throws SQLException {
        //traigo la informacion de los JTextField y los guardo en variables
        String ced=cedula.getText();
        String his=historial.getText();
        String nom=nombre.getText();
        String ape=apellido.getText();
        String tel=telefono.getText();
        String eda=edad.getText();
        String des=descripcion.getText();

        //6 creo mi objeto de tipo Connection
        Connection conecta=conexion();
        //7 creo mi consulta en este caso seria con ?
        // los ? se usar para poner los datos que se ingresen en mi Jtextfield
        String sql="insert into PACIENTE(cedula,n_historial_clinico,nombre,apellido,telefono,edad,descripcion_enfermedad) " +
                "values(?,?,?,?,?,?,?)";
        //8 creo el objeto PreparedStatement (prepara la consulta)
        PreparedStatement pstmt=conecta.prepareStatement(sql);

        //como estoy ocupando ? debo especificar que va en cada ?
        //no olvidarse que debe ser del mismo tipo que la pase de dartos
        pstmt.setString(1,ced);
        pstmt.setInt(2,Integer.parseInt(his));
        pstmt.setString(3,nom);
        pstmt.setString(4,ape);
        pstmt.setString(5,tel);
        pstmt.setInt(6,Integer.parseInt(eda));
        pstmt.setString(7,des);

        //9 Ahora debo usar el executeUpdate y guardarlo en una variable (el executeQuerry solo se usa en el select)
        int ariel=pstmt.executeUpdate();
        if (ariel>0){
            JOptionPane.showMessageDialog(null,"------Registro ingresado con exito------");
            cedula.setText("");
            historial.setText("");
            nombre.setText("");
            apellido.setText("");
            telefono.setText("");
            edad.setText("");
            descripcion.setText("");
        }else {
            JOptionPane.showMessageDialog(null,"ERRROR - Intentelo de nuevo!!!!");
            cedula.setText("");
            historial.setText("");
            nombre.setText("");
            apellido.setText("");
            telefono.setText("");
            edad.setText("");
            descripcion.setText("");
        }

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
