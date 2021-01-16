package mradziewicz.model;

import java.util.Objects;

public class Course extends Publication {
    private double hours;
    private String lastUpdate;
    private double rating;
    public static final String COURSE_TYPE = "Kursy";

    public Course(String title, String author, String publisher, double hours, String lastUpdate, double rating) {
        super(title, author, publisher);
        this.hours = hours;
        this.lastUpdate = lastUpdate;
        this.rating = rating;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toCsv() {
        return COURSE_TYPE + ";" + getTitle() + ";" + getAuthor() + ";" + getPublisher() + ";" + hours + ";"
                + lastUpdate + ";" + rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        if (!super.equals(o)) return false;
        Course course = (Course) o;
        return Double.compare(course.hours, hours) == 0 &&
                Double.compare(course.rating, rating) == 0 &&
                Objects.equals(lastUpdate, course.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hours, lastUpdate, rating);
    }

    @Override
    public String toString() {
        return super.toString() +
                "; " + hours +
                "; " + lastUpdate +
                "; " + rating;
    }
}
