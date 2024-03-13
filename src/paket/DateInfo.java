package paket;
import java.io.Serializable;

public class DateInfo implements Serializable{
    private int startYear;
    private int startMonth;
    private int endMonth;

    public DateInfo(int startYear, int startMonth, int endMonth) {
        this.startYear = startYear;
        this.startMonth = startMonth;
        this.endMonth = endMonth;
    }

    public int getStartYear() {
        return startYear;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public int getEndMonth() {
        return endMonth;
    }

    @Override
    public String toString() {
        return "DateInfo{" +
                "startYear=" + startYear +
                ", startMonth=" + startMonth +
                ", endMonth=" + endMonth +
                '}';
    }

    public int getEndYear(){
        if(endMonth > startMonth)
            return startYear;
        else
            return startYear + 1;
    }
}
