package his.logic.perma_data;

/**
 * 患者须付费才能得到的医疗服务
 */
// todo 子类中更详细的信息，等到转换到文件后再表示，现在暂时用类结构来表示不同医疗服务间的区别
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
