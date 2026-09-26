package view;

import controller.AgeController;
import model.AgeModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainView extends JFrame implements AgeModel.ModelListener {

    private final AgeController controller;
    private final AgeModel model;

    private static final Color BG_COLOR      = new Color(0xE3F2FD); // нежно-голубой
    private static final Color PANEL_COLOR   = new Color(0xFFFFFF); // белая карточка
    private static final Color TITLE_COLOR   = new Color(0x0D47A1); // тёмно-синий
    private static final Color TEXT_COLOR    = new Color(0x1A237E); // почти чёрно-синий
    private static final Color ACCENT_COLOR  = new Color(0x1976D2); // акцент (синий)

    private static final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 18);
    private static final Font FONT_LABEL = new Font("Segoe UI Semibold", Font.PLAIN, 16);
    private static final Font FONT_VALUE = new Font("Segoe UI", Font.BOLD, 16);
    private static final Font FONT_BTN   = new Font("Segoe UI", Font.BOLD, 16);

    private final JLabel lblBirthDate = new JLabel("Дата рождения не задана");
    private final JLabel lblYears   = new JLabel("Возраст: -");
    private final JLabel lblMonths  = new JLabel("Месяцев: -");
    private final JLabel lblDays    = new JLabel("Дней: -");
    private final JLabel lblMinutes = new JLabel("Минут: -");
    private final JLabel lblCoffee  = new JLabel("Чашек кофе: -");
    private final JLabel lblSeries  = new JLabel("Серий сериала: -");
    private final JLabel lblScroll  = new JLabel("Км ленты соцсетей: -");

    public MainView(AgeController controller, AgeModel model) {
        this.controller = controller;
        this.model = model;
        this.model.addListener(this);

        initView();
    }

    private void initView() {
        setTitle("Возраст в неожиданных единицах");
        setSize(560, 560);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout(15, 15));
        root.setBackground(BG_COLOR);
        root.setBorder(new EmptyBorder(20, 25, 20, 25));
        setContentPane(root);

        JLabel title = new JLabel("Возраст в неожиданных единицах", SwingConstants.CENTER);
        title.setFont(FONT_TITLE);
        title.setForeground(TITLE_COLOR);
        title.setBorder(new EmptyBorder(0, 0, 10, 0));
        root.add(title, BorderLayout.NORTH);

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(PANEL_COLOR);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1, true),
                new EmptyBorder(20, 25, 20, 25)));

        lblBirthDate.setFont(FONT_VALUE);
        lblBirthDate.setForeground(ACCENT_COLOR);
        lblBirthDate.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(lblBirthDate);
        card.add(Box.createVerticalStrut(10));

        JSeparator sep = new JSeparator();
        sep.setForeground(ACCENT_COLOR);
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        card.add(sep);
        card.add(Box.createVerticalStrut(12));

        JLabel[] labels = {lblYears, lblMonths, lblDays, lblMinutes,
                lblCoffee, lblSeries, lblScroll};
        for (JLabel l : labels) {
            l.setFont(FONT_LABEL);
            l.setForeground(TEXT_COLOR);
            l.setAlignmentX(Component.LEFT_ALIGNMENT);
            card.add(l);
            card.add(Box.createVerticalStrut(8));
        }

        JPanel centerWrap = new JPanel(new BorderLayout());
        centerWrap.setBackground(BG_COLOR);
        centerWrap.add(card, BorderLayout.CENTER);
        root.add(centerWrap, BorderLayout.CENTER);

        JButton btnInput = new JButton("Ввести данные");
        btnInput.setFont(FONT_BTN);
        btnInput.setBackground(ACCENT_COLOR);
        btnInput.setForeground(Color.WHITE);
        btnInput.setFocusPainted(false);
        btnInput.setBorder(BorderFactory.createEmptyBorder(12, 30, 12, 30));
        btnInput.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnInput.addActionListener(e -> controller.openInputDialog(this));

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottom.setBackground(BG_COLOR);
        bottom.add(btnInput);
        root.add(bottom, BorderLayout.SOUTH);
    }

    @Override
    public void onModelChanged() {
        if (!model.hasData()) return;

        lblBirthDate.setText(String.format("Дата рождения: %02d.%02d.%04d",
                model.getDay(), model.getMonth(), model.getYear()));
        lblYears.setText("Возраст: " + model.getYears() + " лет");
        lblMonths.setText("Месяцев: " + model.getMonths());
        lblDays.setText("Дней: " + model.getDays());
        lblMinutes.setText("Минут: " + model.getTotalMinutes());
        lblCoffee.setText("Чашек кофе: " + model.getCupsOfCoffee());
        lblSeries.setText("Серий сериала: " + model.getSeriesEpisodes());
        lblScroll.setText("Км ленты соцсетей: " + model.getScrollKilometers());
    }
}