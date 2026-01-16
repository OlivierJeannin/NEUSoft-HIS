package his.logic.perma_data;

/**
 * 药品
 */
public class Medication extends PaidObject {
    public int amount;  // 数量

    public Medication(String ID, String name, String price, int amount) {
        super(ID, name, price);
        this.amount = amount;
    }

}
