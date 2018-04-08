package app.my.drivingtest.bean;

import java.io.Serializable;

/**
 * Created by FancyMenG on 2018/3/28.
 */

public class SubmitBean implements Serializable {
    private String type;
    private int index;
    private int selectNum;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getSelectNum() {
        return selectNum;
    }

    public void setSelectNum(int selectNum) {
        this.selectNum = selectNum;
    }
}
