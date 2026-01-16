package his.logic;

import his.logic.perma_data.Medication;
import his.logic.perma_data.PaidObject;

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
//    todo 暂时不考虑 同一项目仅执行部分数量 的情况
//    todo 执行结果，如，检查结果、检验结果、处置结果

    /**
     * 创建 PaidItem 对象
     *
     * @param paidObject 项目对应的医疗服务
     * @param amount 项目中包含的医疗服务（主要是药品）的数量
     */
    public PaidItem(PaidObject paidObject, int amount) {
        this.paidObject = paidObject;
        this.amount = amount;
        isPaid = false;
        isOperated = false;
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
