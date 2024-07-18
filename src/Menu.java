import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//1 primero pongo extends Jframe
public class Menu extends JFrame{
    private JButton registrarPacientesButton;
    private JPanel panel1;
    private JButton bsucarPacientesButton;
    private JButton actualizarInformacionDeUnButton;
    private JButton eliminarAUnPacienteButton;

    //2 Creo el metodo constructor
    public Menu(){
        //aqui en el metodo contructor es muy importante
        //traser los elementos del gui
        setContentPane(panel1);
        registrarPacientesButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Registro r1=new Registro();
                r1.Iniciar();
                dispose();
            }
        });
        bsucarPacientesButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Busqueda m1=new Busqueda();
                m1.Iniciar();
                dispose();
            }
        });
        actualizarInformacionDeUnButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Actualizacion a1=new Actualizacion();
                a1.Iniciar();
                dispose();
            }
        });
        eliminarAUnPacienteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ELIMINAR e1=new ELIMINAR();
                e1.Iniciar();
                dispose();
            }
        });
    }


    //3Creo el metodo inicair para que pueda ser llamado desde otras ventanas
    public void Iniciar(){
        setVisible(true);
        setTitle("Menu");
        setSize(500,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
