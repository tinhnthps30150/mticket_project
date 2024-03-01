package mticket.component.transitionForm;

import java.awt.Component;
import java.awt.Point;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

public class FormAnimate {

    public Component getForm() {
        return form;
    }

    public void setForm(Component form) {
        this.form = form;
    }

    public Point getOriginalLocation() {
        return originalLocation;
    }

    public void setOriginalLocation(Point originalLocation) {
        this.originalLocation = originalLocation;
    }

    public FormAnimate(Component form, Point originalLocation) {
        this.form = form;
        this.originalLocation = originalLocation;
    }

    public FormAnimate() {
    }

    private Component form;
    private Point originalLocation;

//    public static void main(String[] args) {
//        LocalDate now = LocalDate.of(2023, 2, 1);
//        LocalDate firstDay = now.with(firstDayOfMonth());
//        LocalDate lastDay = now.with(lastDayOfMonth());
//        do {
//            System.out.println(firstDay);
//            firstDay = firstDay.plus(1, ChronoUnit.DAYS);
//        } while (firstDay.isBefore(lastDay) || firstDay.equals(lastDay));
//    }
}
