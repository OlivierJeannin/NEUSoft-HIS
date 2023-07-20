package his.perma_data;

/**
 * 患者须付费才能得到的医疗服务
 */
public abstract class PaidObject {
    public String ID;  // 编号
    public String name;  // 名称
    public String price;  // 单价

    public PaidObject(String ID, String name, String price) {
        this.ID = ID;
        this.name = name;
        this.price = price;
    }

}
