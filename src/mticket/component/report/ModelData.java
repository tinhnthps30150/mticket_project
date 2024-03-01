package mticket.component.report;

/**
 *
 * @author RAVEN
 */
public class ModelData {

    public ModelData(String name, double count) {
        this.name = name;
        this.count = count;
    }

    public ModelData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

   

    private String name;
    private double count;
}
