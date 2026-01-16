package his.logic.perma_data;

/**
 * 处置、治疗
 */
public class Treatment extends PaidObject {
    public int amount;  // 数量

    public Treatment(String ID, String name, String price, int amount) {
        super(ID, name, price);
        this.amount = amount;
    }

}
