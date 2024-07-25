
import com.uns.controlador.LoginController;
import com.uns.modelo.Conexion;
import java.sql.Connection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application{

    public static Conexion con = Conexion.getInstance();
    public static Connection conexion = con.getConnection();
    
    public static void main(String[] args) {
 
        launch(args);
      
    }

    @Override
    public void start(Stage primaryStage) {
        
        try {
            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/uns/vista/login.fxml"));
            VBox root = loader.load();
            
            // Obtener el controlador asociado
            LoginController controller = loader.getController();
            
            // Crear la escena
            Scene scene = new Scene(root, 600, 400);
             scene.getStylesheets().add(getClass().getResource("/com/uns/res/css/login.css").toExternalForm());
            
            // Agregar la hoja de estilos CSS
          // Establecer la escena en el escenario principal
            primaryStage.setScene(scene);
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/uns/res/img/box.png")));
            primaryStage.setTitle("Tienda Online");
            primaryStage.show();
            
            primaryStage.setOnCloseRequest(e->{
            System.exit(0);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
          if(conexion!=null){
            System.out.println("Conexion correcta");
        }else{
            System.out.println("Error de conexion : ");
        }
    }
    
}
