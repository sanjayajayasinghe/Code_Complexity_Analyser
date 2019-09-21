public class TimetableRow {
    int rows;

    String from;
    String to;
    String departureTime;
    String arraivalTime;
    String status;
    float cost;
    boolean lastStation;
    String date;
    private String travelID;


    public TimetableRow(int rows, String from, String to, String departureTime, String arraivalTime, String status, float cost, boolean lastStation, String date, String travelID){
        super();
        this.rows = rows;
        this.from = from;
        this.to = to;
        this.departureTime = departureTime;
        this.arraivalTime = arraivalTime;
        this.status = status;
        this.cost = cost;
        this.lastStation = lastStation;
        this.date = date;
        this.travelID = travelID;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArraivalTime() {
        return arraivalTime;
    }

    public void setArraivalTime(String arraivalTime) {
        this.arraivalTime = arraivalTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }


    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }


    public boolean isLastStation() {
        return lastStation;
    }

    public void setLastStation(boolean lastStation) {
        this.lastStation = lastStation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTravelID() {
        return travelID;
    }

    public void setTravelID(String travelID) {
        this.travelID = travelID;
    }

    @Override
    public String toString() {
        return "TimetableRow [rows=" + rows + ", from=" + from + ", to=" + to + ", departureTime=" + departureTime + ", arraivalTime=" + arraivalTime + ", status=" + status + ", cost=" + cost + ", lastStation=" + lastStation + ", date=" + date + ", travelID=" + travelID + "]";
    }


}
