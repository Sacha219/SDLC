package model;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class AgeModel {

    public interface ModelListener {
        void onModelChanged();
    }

    private final List<ModelListener> listeners = new ArrayList<>();

    private int day;
    private int month;
    private int year;
    private boolean hasData = false;

    private int years;
    private int months;
    private int days;
    private long totalDays;
    private long totalMinutes;
    private long cupsOfCoffee;
    private long seriesEpisodes;
    private long scrollKilometers;

    private static final double COFFEE_CUPS_PER_DAY = 2.0;
    private static final double SERIES_PER_DAY = 1.5;
    private static final double SCROLL_KM_PER_DAY = 0.3;

    public void addListener(ModelListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (ModelListener l : listeners) {
            l.onModelChanged();
        }
    }

    public void setData(int day, int month, int year) throws IllegalArgumentException {
        if (month < 1 || month > 12 || day < 1 || day > 31
                || year < 1900 || year > LocalDate.now().getYear()) {
            throw new IllegalArgumentException(
                    "Введены некорректные данные даты!");
        }
        LocalDate birthDate;
        try {
            birthDate = LocalDate.of(year, month, day);
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    "Указанной даты не существует!");
        }
        if (birthDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Дата рождения не может быть в будущем!");
        }

        this.day = day;
        this.month = month;
        this.year = year;
        this.hasData = true;

        calculateAge(birthDate);
        notifyListeners();
    }

    private void calculateAge(LocalDate birthDate) {
        LocalDate today = LocalDate.now();

        Period period = Period.between(birthDate, today);
        this.years  = period.getYears();
        this.months = period.getYears() * 12 + period.getMonths();
        this.days   = (int) ChronoUnit.DAYS.between(birthDate, today);

        this.totalMinutes = days * 24L * 60L;

        this.cupsOfCoffee     = (long) (days * COFFEE_CUPS_PER_DAY);
        this.seriesEpisodes   = (long) (days * SERIES_PER_DAY);
        this.scrollKilometers = (long) (days * SCROLL_KM_PER_DAY);
    }

    public boolean hasData() { return hasData; }
    public int getDay() { return day; }
    public int getMonth() { return month; }
    public int getYear() { return year; }
    public int getYears() { return years; }
    public int getMonths() { return months; }
    public int getDays() { return days; }
    public long getTotalDays() { return totalDays; }
    public long getTotalMinutes() { return totalMinutes; }
    public long getCupsOfCoffee() { return cupsOfCoffee; }
    public long getSeriesEpisodes() { return seriesEpisodes; }
    public long getScrollKilometers() { return scrollKilometers; }
}