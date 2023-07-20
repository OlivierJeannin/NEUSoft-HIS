package his;

import his.perma_data.Medication;
import his.perma_data.PaidObject;

/**
 * 医生开立的付费项目
 *
 * <p>包含医疗服务本身，以及其他与“费用”有关的附加信息。</p>
 */
public class PaidItem {
    PaidObject paidObject;  // 1个付费项目包含的1个医疗服务

    int amount;  // 数量
    boolean isPaid;  // 是否已缴费
    boolean isOperated;  // 是否已执行；如果是处方，是否已发药

    public PaidItem(PaidObject paidObject) {
        this.paidObject = paidObject;
        amount = 1;
        isPaid = false;
        isOperated = false;
    }

    public PaidItem(PaidObject paidObject, int amount) {
        this(paidObject);
        this.amount = amount;
    }

    /**
     * 获取付费项目的总价
     *
     * @return 表示总价的字符串
     */
    public String getCost() {
        return String.valueOf(Integer.parseInt(paidObject.price) * amount);
    }

    /**
     * 缴费
     */
    public void pay() {
        isPaid = true;
    }

    /**
     * 退费
     */
    public void unPay() {
        assert !isOperated;
        isPaid = false;
    }

    /**
     * 执行付费项目
     *
     * <p>若是处方，则发药。</p>
     */
    public void operate() {
        assert isPaid;
        isOperated = true;
    }

    /**
     * 退药
     *
     * @throws UnsupportedOperationException 非药品不支持该操作
     */
    public void unOperate() {
        if (this.paidObject instanceof Medication)
            isOperated = false;
        else throw new UnsupportedOperationException("非药品对象不支持该操作！");
    }

}
