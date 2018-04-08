package app.my.drivingtest.bean;

import java.io.Serializable;

/**
 * Created by FancyMenG on 2018/3/27.
 * 每条选项
 */

public class SelectBean implements Serializable {
    private String name ="选ze ";
    private boolean isSelected ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    @Override
    public String toString() {
        return "SelectBean{" +
                "name='" + name + '\'' +
                ", isSelected=" + isSelected +
                '}';
    }
}
