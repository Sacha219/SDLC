package by.bsuir.age;

import controller.AgeController;
import model.AgeModel;
import view.MainView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AgeModel model = new AgeModel();
            AgeController controller = new AgeController(model);
            MainView view = new MainView(controller, model);
            view.setVisible(true);
        });
    }
}