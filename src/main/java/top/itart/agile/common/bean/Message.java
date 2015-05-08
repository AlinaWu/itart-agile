package top.itart.agile.common.bean;

import java.util.List;

/**
 * 
 * @ClassName:     Message.java  
 * @Description:   Result message 
 * @author         hymanz (www.itart.top)
 * @version        V1.0
 * @Date           Jan 15, 2015 11:33:39 AM
 */
public interface Message {

    public <T> T getData(Class<T> claze);

    public void setData(Object data);

    public boolean isSuccess();

    public void setSuccess(boolean success);

    public String getInfo();

    public void setInfo(List<String> infos);

    public void addInfo(String info);

    public void setSeparator(String separator);

}
