package controller;

import model.AgeModel;
import view.InputDialog;

import javax.swing.*;

public class AgeController {

    private final AgeModel model;
    private InputDialog inputDialog;

    public AgeController(AgeModel model) {
        this.model = model;
    }

    public void openInputDialog(JFrame parent) {
        if (inputDialog == null || !inputDialog.isDisplayable()) {
            inputDialog = new InputDialog(parent, this);
        }
        // Восстановление последних данных
        inputDialog.setValues(model.getDay(), model.getMonth(), model.getYear());
        inputDialog.setVisible(true);
    }

    public void processInput(String dayStr, String monthStr, String yearStr) {
        try {
            int d = Integer.parseInt(dayStr.trim());
            int m = Integer.parseInt(monthStr.trim());
            int y = Integer.parseInt(yearStr.trim());

            // Передаём в модель — она сама валидирует и уведомит View
            model.setData(d, m, y);

            if (inputDialog != null) inputDialog.dispose();
        } catch (NumberFormatException e) {
            showError("Ошибка: Пожалуйста, введите целые числа!");
        } catch (IllegalArgumentException e) {
            showError("Ошибка: " + e.getMessage());
        }
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Ошибка ввода",
                JOptionPane.ERROR_MESSAGE);
    }
}