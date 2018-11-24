package no.uib.ii.inf102.f18.mandatory2;

/**
 * @author You
 */
public final class Date {
    final Month month;
    final int year;
    final int day;

    public Date(Month month, int year, int day) {
        this.month = month;
        this.year = year;
        this.day = day;
    }

    public int hashCode(){
        return String.format("%d%d%d",month.ordinal(), day, year).hashCode();
    }

    public boolean equals(Object obj){
        if(this == obj) return true;
        if(!(obj instanceof Date)) return false;
        
        Date other = (Date) obj;
        return (other.day == this.day) && (other.month.equals(this.month)) && (other.year == this.year);
    }
}
