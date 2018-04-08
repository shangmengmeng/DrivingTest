package app.my.drivingtest.view;

import java.io.Serializable;

/**
 * Created by FancyMenG on 2018/3/30.
 */

public class JageDialogSelctedBean implements Serializable {
    private String selectStatus;
    private boolean isSelected;

    public String getSelectStatus() {
        return selectStatus;
    }

    public void setSelectStatus(String selectStatus) {
        this.selectStatus = selectStatus;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
