package top.itart.agile.common.bean;

import java.util.ArrayList;
import java.util.List;

import top.itart.agile.common.util.StringUtils;


public class MessageImp implements Message {

    private Object data;
    private boolean success = true;
    private List<String> infos = new ArrayList<String>();
    private String separator = "\n";

    public Object getData() {
        return data;
    }
    
    @SuppressWarnings("unchecked")
    public <T> T getData(Class<T> claze) {
        return null == data ? null : (T) data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getInfo() {
        return StringUtils.join(infos, separator);
    }

    public void setInfo(List<String> infos) {
        this.infos = infos;
    }

    @Override
    public void addInfo(String info) {
        infos.add(info);
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }


}
