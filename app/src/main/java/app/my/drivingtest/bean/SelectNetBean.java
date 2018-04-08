package app.my.drivingtest.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by FancyMenG on 2018/3/29.
 */

public class SelectNetBean implements Serializable{

    private String type;//单选还是多选
    private String title="题目。。。。。。。。。。。"; //题目
    private List<SelectBean> testContent;  //内容
    private String answer; // 答案

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SelectBean> getTestContent() {
        return testContent;
    }

    public void setTestContent(List<SelectBean> testContent) {
        this.testContent = testContent;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


    @Override
    public String toString() {
        return "SelectNetBean{" +
                "type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", testContent=" + testContent +
                ", answer='" + answer + '\'' +
                '}';
    }
}
