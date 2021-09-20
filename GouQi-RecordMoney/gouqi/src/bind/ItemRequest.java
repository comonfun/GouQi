package bind;

public class ItemRequest {
    private final String type;
    private final Double num;
    private final String date;
    private final String backup;

    public ItemRequest(String type, Double num, String date, String backup) {
        this.type = type;
        this.num = num;
        this.date = date;
        this.backup = backup;
    }

    public String getType() {
        return type;
    }

    public Double getNum() {
        return num;
    }

    public String getDate() {
        return date;
    }

    public String getBackup() {
        return backup;
    }
}
