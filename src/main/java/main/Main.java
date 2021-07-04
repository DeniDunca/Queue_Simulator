package main;

import controller.ControllerQ;
import model.ModelQ;
import view.ViewQ;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args){
        ModelQ model = new ModelQ("output.txt");//obiect model
        ViewQ view = new ViewQ(model);//obiect view

        ControllerQ controller = new ControllerQ(model, view);//obiect controller care leaga model si view intre ele

        view.setVisible(true);

    }
}
