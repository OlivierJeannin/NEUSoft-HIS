package his.logic.perma_data;

import java.io.Serializable;

public class RegisterLevel implements Serializable {
    public String name;  // 级别名称
    public int fee;  // 挂号费

    public RegisterLevel(String name, int fee) {
        this.name = name;
        this.fee = fee;
    }

}
