package no.uib.ii.inf102.f18.mandatory2;

/**
 * @author You
 */
public class Event {
    final Date date;
    final String title;
    String description;

    public Event(Date date, String title) {
        this.date = date;
        this.title = title;
    }

    public boolean equals(Object obj){
        if(this == obj) return true;
        if(!(obj instanceof Event)) return false;
        
        Event other = (Event) obj;
        return (other.date.equals(this.date)) && other.title.equals(this.title);
    }


    public int hashCode(){
        return String.format("%d%d%d%s",date.month.ordinal(), date.day, date.year, title).hashCode();
    }

}
