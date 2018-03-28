import javafx.application.Application; 
import javafx.event.EventHandler;
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage; 
import javafx.scene.shape.LineTo; 
import javafx.scene.shape.MoveTo; 
import javafx.scene.shape.Path; 
         
public class ComplexShape extends Application { 

    //hola
    
    double m_nX = 0;
    double m_nY = 0;
    double m_nMouseX = 0;
    double m_nMouseY = 0;
    Group circle=new Group();
        
    @Override 
    public void start(Stage stage) { 
    
    

      Path path = new Path(); 
       
      
    // aqui se crean las lineas 
    MoveTo moveTo=new MoveTo(200, 200); //este es el punto inical para comenzar a dibujar la linea
    LineTo line4 = new LineTo(200, 150);
    LineTo line1 = new LineTo(200, 100);  //segundos punto
    LineTo line2 = new LineTo(150, 150);  //tercer punto
    LineTo line3 = new LineTo(200, 150);  //cuarto punto


    path.setStrokeWidth(4); //grisor de la linea


    //Adding all the elements to the path 
    path.getElements().add(moveTo); 
    path.getElements().addAll(line4,line1, line2, line3);        

        
   
    
//    en esta parte se crean los circulos para modificar el numero  

        Circle c1 = new Circle();
        c1.setCenterX(moveTo.getX());
        c1.setCenterY(moveTo.getY());
        c1.setRadius(5.0);
        c1.setCache(true);
        c1.setStroke(Color.RED);
        c1.setStrokeWidth(3);

        Circle c2 = new Circle();
        c2.setCenterX(line1.getX());
        c2.setCenterY(line1.getY());
        c2.setRadius(5.0);
        c2.setCache(true);
        c2.setStroke(Color.RED);
        c2.setStrokeWidth(3);

        Circle c3 = new Circle();
        c3.setCenterX(line2.getX());
        c3.setCenterY(line2.getY());
        c3.setRadius(5.0);
        c3.setStroke(Color.RED);
        c3.setStrokeWidth(3);

        Circle c4 = new Circle();
        c4.setCenterX(line3.getX());
        c4.setCenterY(line3.getY());
        c4.setRadius(5.0);
        c4.setCache(true);
        c4.setStroke(Color.RED);
        c4.setStrokeWidth(3);


            
    //se crea el grupo para generar el numero
    Group root = new Group(path);
    circle.getChildren().addAll(c1,c2,c3,c4);
    root.getChildren().addAll(circle);
    
    
    //opcion modificar
    
    circle.setVisible(true);
    
    //se toma la posicion del circulo al cl1ckearlo
    c1.setOnMousePressed(pressMouse(c1));
    //se ejecuta la accion de mover
    c1.setOnMouseDragged(dragMouseInicial(c1, moveTo));
  
    c2.setOnMousePressed(pressMouse(c2));
    c2.setOnMouseDragged(dragMouse(c2, line1));
    c3.setOnMousePressed(pressMouse(c3));
    c3.setOnMouseDragged(dragMouse(c3, line2));
    c4.setOnMousePressed(pressMouse(c4));
    c4.setOnMouseDragged(dragMouseFinal(c4, line3, line4));

            
      
    //se crea la escena 
    Scene scene = new Scene(root, 600, 300);  

    //titulo
    stage.setTitle("Drawing an arc through a path");

    //se agrega la escena a la ventana
    stage.setScene(scene);

    //se hace correr la ventana
    stage.show();         
   } 
   public static void main(String args[]){ 
      launch(args); 
   } 
   
   
   //se usa para obtener la posicion del circulo o punto de movimiento
   EventHandler<MouseEvent> pressMouse(Circle c) {
        EventHandler<MouseEvent> mousePressHandler = new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    // get the current mouse coordinates according to the scene.
                    m_nMouseX = event.getSceneX();
                    m_nMouseY = event.getSceneY();

                    // get the current coordinates of the draggable node.
                    m_nX = c.getLayoutX();
                    m_nY = c.getLayoutY();
                }
            }
        };
        return mousePressHandler;
    }
   
   //dragMouse y dragMouseInicial son lo mismo pero uno es para el moveto que es el punto inicial y el otro para el lineto que son el resto de puntos
   EventHandler<MouseEvent> dragMouseInicial(Circle c, MoveTo l) {
        EventHandler<MouseEvent> dragHandler = new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    // find the delta coordinates by subtracting the new mouse
                    // coordinates with the old.
                    double deltaX = event.getSceneX() - m_nMouseX;
                    double deltaY = event.getSceneY() - m_nMouseY;

                    // add the delta coordinates to the node coordinates.
                    m_nX += deltaX;
                    m_nY += deltaY;

                    // set the layout for the draggable node.
                    c.setLayoutX(m_nX);
                    c.setLayoutY(m_nY);
                    l.setX(event.getSceneX());
                    l.setY(event.getSceneY());

                    // get the latest mouse coordinate.
                    m_nMouseX = event.getSceneX();
                    m_nMouseY = event.getSceneY();
                }
            }
        };
        return dragHandler;
    }
   
   EventHandler<MouseEvent> dragMouse(Circle c, LineTo l) {
        EventHandler<MouseEvent> dragHandler = new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    // find the delta coordinates by subtracting the new mouse
                    // coordinates with the old.
                    double deltaX = event.getSceneX() - m_nMouseX;
                    double deltaY = event.getSceneY() - m_nMouseY;

                    // add the delta coordinates to the node coordinates.
                    m_nX += deltaX;
                    m_nY += deltaY;

                    // set the layout for the draggable node.
                    c.setLayoutX(m_nX);
                    c.setLayoutY(m_nY);
                    l.setX(event.getSceneX());
                    l.setY(event.getSceneY());

                    // get the latest mouse coordinate.
                    m_nMouseX = event.getSceneX();
                    m_nMouseY = event.getSceneY();
                }
            }
        };
        return dragHandler;
    }
   
   EventHandler<MouseEvent> dragMouseFinal(Circle c, LineTo l1, LineTo l2) {
        EventHandler<MouseEvent> dragHandler = new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    // find the delta coordinates by subtracting the new mouse
                    // coordinates with the old.
                    double deltaX = event.getSceneX() - m_nMouseX;
                    double deltaY = event.getSceneY() - m_nMouseY;

                    // add the delta coordinates to the node coordinates.
                    m_nX += deltaX;
                    m_nY += deltaY;

                    // set the layout for the draggable node.
                    c.setLayoutX(m_nX);
                    c.setLayoutY(m_nY);
                    l1.setX(event.getSceneX());
                    l1.setY(event.getSceneY());
                    l2.setX(event.getSceneX());
                    l2.setY(event.getSceneY());

                    // get the latest mouse coordinate.
                    m_nMouseX = event.getSceneX();
                    m_nMouseY = event.getSceneY();
                }
            }
        };
        return dragHandler;
    }
}  