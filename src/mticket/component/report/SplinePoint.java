package mticket.component.report;

public class SplinePoint {

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public SplinePoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public SplinePoint() {
    }

    private double x;
    private double y;

    public SplinePoint copy() {
        return new SplinePoint(x, y);
    }

//    public static void main(String[] args) {
//         LocalDate now = LocalDate.now();
//        LocalDate firstDay = now.with(firstDayOfYear());
//        LocalDate lastDay = now.with(lastDayOfYear());
//        while (firstDay.isBefore(lastDay)) {
//             System.out.println(java.sql.Date.valueOf(firstDay));
//            firstDay = firstDay.plus(1, ChronoUnit.MONTHS);
//           
//        }
//       String monthString = new DateFormatSymbols().getMonths()[12 - 1];
//        System.out.println( reportDAO.getAmountMovieByIdAdMonth("MV001", monthString).size());
//    }
}
