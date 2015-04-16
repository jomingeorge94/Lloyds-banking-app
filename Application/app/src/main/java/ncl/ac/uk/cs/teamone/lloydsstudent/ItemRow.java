package ncl.ac.uk.cs.teamone.lloydsstudent;

/**
 * Created by Jomin on 15/04/2015.
 */
public class ItemRow {
    String itemName;
    long icon;

    public ItemRow(String itemName, long icon) {
        super();
        this.itemName = itemName;
        this.icon = icon;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public long getIcon() {
        return icon;
    }
    public void setIcon(long icon) {
        this.icon = icon;
    }

}
