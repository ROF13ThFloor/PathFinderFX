package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
public class Main extends Application {
    static  int e1,e2;
    static boolean[][] matrix;
    static int max=0;
    static int n;
    static boolean[] canVisit;
    static  int counter;
    GridPane rootNode = new GridPane();
    Scene myScene = new Scene(rootNode, 1000,1000);
    Group gr=new Group();
    static int count=0;


    @Override
    public void start(Stage primaryStage) throws Exception{

        matrix = new boolean[22][22];
        int index = 1;
    counter=0;
        primaryStage.setTitle("Calculator");
        int i=0;
        matrix = new boolean[22][22];
        canVisit = new boolean[22];

        rootNode.setPadding(new Insets(15));
        rootNode.setHgap(5);
        rootNode.setVgap(5);
        rootNode.setAlignment(Pos.CENTER);

        Button aButton = new Button("Start");
        rootNode.add(aButton, 1, 2);
        TextField numbers=new TextField();
        rootNode.add(numbers,1,0);
        aButton.setOnAction(e->{
            n=Integer.valueOf(numbers.getText());
            rootNode.getChildren().clear();
            Button addbuButton=new Button("add data");

            rootNode.add(addbuButton, 2, 1);
            Button showbuButton=new Button("show pathes");
            rootNode.add(showbuButton, 1, 1);
            rootNode.add(new Label("the streetcorners that is opens "), 0, 0);
            TextField firstValue = new TextField();
            rootNode.add(firstValue, 1, 0);
            TextField secondValue = new TextField();
            rootNode.add(secondValue, 2,0);



            addbuButton.setOnAction(c->{
                 e1=Integer.valueOf(firstValue.getText());
                e2 = Integer.valueOf(secondValue.getText());
                System.out.printf("recives");

                    rootNode.getChildren().clear();
                    Button addbuButton2=new Button("add data");
                    rootNode.add(addbuButton2, 2, 1);
                    Button showbuButton2=new Button("show pathes");
                rootNode.add(showbuButton2, 1, 1);
                rootNode.add(new Label("the streetcorners that is opens "), 0, 0);
                TextField firstValue1 = new TextField();
                rootNode.add(firstValue1, 1, 0);
                TextField secondValue2 = new TextField();
                rootNode.add(secondValue2, 2,0);
                addbuButton2.setOnAction(c2->{
                    matrix[e1][e2] = matrix[e2][e1] = true;
                    e1 =Integer.valueOf(firstValue1.getText());
                    e2 =Integer.valueOf(secondValue2.getText());
                    System.out.println(e1 +"   " +e2+ "\n");
                    max = Math.max(Math.max(e1, e2), max);
                    rootNode.getChildren().clear();
                    rootNode.add(addbuButton2, 2, 1);
                    rootNode.add(new Label("the streetcorners that is opens "), 0, 0);
                    rootNode.add(showbuButton2, 1, 1);
                    rootNode.add(firstValue1, 1, 0);
                    rootNode.add(secondValue2, 2,0);
                    showbuButton2.setOnAction(e3->{
                        dfs(n, max);
                        rootNode.getChildren().clear();

                        generatePath("1", 1, n, 2, max);



                    });


                });


            });






        });

        primaryStage.setScene(myScene);

        primaryStage.show();




    }

     void generatePath(String path, int s, int e, int v, int max) {


        if (canVisit[s]) {

            if (s == e) {
                counter++;
                count=count+5;
                Text tx1=new Text();
                tx1.setY(400*count);
                tx1.setX(100*count);
                tx1.setText(path);


                DropShadow ds = new DropShadow();
                ds.setOffsetY(3.0);
                ds.setColor(Color.color(0.4, 0.4, 0.4));
                tx1.setEffect(ds);
                tx1.setFill(Color.RED);
                tx1.setFont(Font.font(null, FontWeight.BOLD, 32));
                rootNode.getChildren().add(tx1);
                System.out.println(path);

            }
            for (int i = 1; i <= max; i++)
                if ((v & (1 << i)) == 0 && matrix[s][i])
                    generatePath(path + " " + i, i, e, v | (1 << i), max);
        }
   }

    private void dfs(int n, int max) {
        
        canVisit[n] = true;
        for (int i = 1; i <= max; i++)
            if (matrix[n][i] && !canVisit[i])
                dfs(i, max);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
