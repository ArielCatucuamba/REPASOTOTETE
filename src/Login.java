import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

//1 debo poner extends JFrame
public class Login extends JFrame{
    private JButton validarDatosButton;
    private JTextField usu;
    private JTextField con;
    private JPanel panel1;

    //2 creo el metodo constructor
    public Login(){
        //No olvides darle diseño ya que este es el que se llama desde el main
        setTitle("Logueo de ususarios");
        setSize(500,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel1);

        validarDatosButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Logueo();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    //4 creo mi metodo para loguearme
    public void Logueo() throws SQLException {
        //Primero traigo el texto de mis JtextField y los guardo en una variable String
        String usuario=usu.getText();
        String contraseña=con.getText();
        //2 creo mi objeto Connection y llamo a la funcion Connection que cree
        Connection conecta=conexion();
        //3 Creo mi consulta SQL (recuerda que aqui van los nombres tal cual de las columnas y se pueden usar ?
        String sql="select * from USUARIO where username=? and password=?";
        //4 creo mi objeto PreparedStatement (preparo a mi consulta)
        PreparedStatement pstmt=conecta.prepareStatement(sql);
        //como tengo ? debo especificar que va en cada ? - > para ello uso pstmt.set
        pstmt.setString(1,usuario);
        pstmt.setString(2,contraseña);
        //5 Creo mi objeto de ResultSet (este se usa en los select) ->me guarda los registros que coincidan con la consulta
        // al pstmt le pongo el execute
        ResultSet rs = pstmt.executeQuery();
        //6 Ahora debo recorrer el resultSet - puede ser con un if o un while
        if (rs.next()){
            //Pongo un mensaje de ventana y le digo que si son correctas le envie a otra parte
            JOptionPane.showMessageDialog(null,"-------Credenciales correctas-------");
            Menu m1=new Menu();
            m1.Iniciar();
            dispose();
        }else {
            JOptionPane.showMessageDialog(null,"Credenciales incorrectas!!!!!!");
            usu.setText("");
            con.setText("");
        }
        //7 cerrar todo
        conecta.close();
        pstmt.close();
        rs.close();
    }


    //3 creo el metodo Connection
    public Connection conexion() throws SQLException {
        //Creo 3 variables de tipo String para el url user y pass
        String url="jdbc:mysql://localhost:3306/sistema_hospitalario";
        String user="root";
        String password="123456";
        //ahora tengo que retorna para usarlo dentro de otra funcion
        return DriverManager.getConnection(url,user,password);
    }

    public void Iniciar(){
        setVisible(true);
        setTitle("Menu");
        setSize(500,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
