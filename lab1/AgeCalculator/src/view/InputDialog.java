package view;

import controller.AgeController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InputDialog extends JDialog {

    private static final Color BG_COLOR     = new Color(0xE3F2FD);
    private static final Color PANEL_COLOR  = Color.WHITE;
    private static final Color ACCENT_COLOR = new Color(0x1976D2);
    private static final Color TEXT_COLOR   = new Color(0x1A237E);

    private static final Font FONT_LABEL = new Font("Segoe UI Semibold", Font.PLAIN, 15);
    private static final Font FONT_FIELD = new Font("Segoe UI", Font.PLAIN, 15);
    private static final Font FONT_BTN   = new Font("Segoe UI", Font.BOLD, 15);

    private final JTextField txtDay   = new JTextField(8);
    private final JTextField txtMonth = new JTextField(8);
    private final JTextField txtYear  = new JTextField(8);

    public InputDialog(JFrame parent, AgeController controller) {
        super(parent, "Ввод даты рождения", true);
        setSize(420, 320);
        setLocationRelativeTo(parent);

        JPanel root = new JPanel(new BorderLayout(10, 10));
        root.setBackground(BG_COLOR);
        root.setBorder(new EmptyBorder(20, 20, 15, 20));
        setContentPane(root);

        JLabel title = new JLabel("Введите дату рождения", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 17));
        title.setForeground(new Color(0x0D47A1));
        title.setBorder(new EmptyBorder(0, 0, 10, 0));
        root.add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(3, 2, 10, 14));
        form.setBackground(PANEL_COLOR);
        form.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1, true),
                new EmptyBorder(20, 20, 20, 20)));

        form.add(makeLabel("День (1-31):"));
        form.add(styleField(txtDay));
        form.add(makeLabel("Месяц (1-12):"));
        form.add(styleField(txtMonth));
        form.add(makeLabel("Год (1900-" + java.time.LocalDate.now().getYear() + "):"));
        form.add(styleField(txtYear));

        root.add(form, BorderLayout.CENTER);

        JButton ok = new JButton("OK");
        ok.setFont(FONT_BTN);
        ok.setBackground(ACCENT_COLOR);
        ok.setForeground(Color.WHITE);
        ok.setFocusPainted(false);
        ok.setPreferredSize(new Dimension(120, 36));
        ok.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttons.setBackground(BG_COLOR);
        buttons.add(ok);
        root.add(buttons, BorderLayout.SOUTH);

        java.awt.event.ActionListener submit = e ->
                controller.processInput(txtDay.getText(), txtMonth.getText(), txtYear.getText());
        ok.addActionListener(submit);
        txtDay.addActionListener(submit);
        txtMonth.addActionListener(submit);
        txtYear.addActionListener(submit);
    }

    private JLabel makeLabel(String text) {
        JLabel l = new JLabel(text);
        l.setFont(FONT_LABEL);
        l.setForeground(TEXT_COLOR);
        return l;
    }

    private JTextField styleField(JTextField f) {
        f.setFont(FONT_FIELD);
        f.setForeground(TEXT_COLOR);
        f.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1, true),
                new EmptyBorder(4, 8, 4, 8)));
        return f;
    }

    public void setValues(int day, int month, int year) {
        if (year != 0) {
            txtDay.setText(String.valueOf(day));
            txtMonth.setText(String.valueOf(month));
            txtYear.setText(String.valueOf(year));
        }
    }
}