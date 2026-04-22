import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            ToDoAppGUI gui = new ToDoAppGUI();
            gui.setVisible(true);
        });
    }
}
