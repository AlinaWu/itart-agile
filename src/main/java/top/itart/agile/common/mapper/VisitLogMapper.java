package top.itart.agile.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import top.itart.agile.common.model.VisitLog;

public interface VisitLogMapper {
	
     @Select("SELECT * FROM SYS_VISIT_LOG")
     public List<VisitLog> selectAll();
     
     @Update("rename column sys_user.id to user_id;")
     public void updateColumnName();
     
     @Insert("insert into SYS_VISIT_LOG(VISIT_ID, START_TIME, END_TIME, USER_NAME, URI, PARAMETER, HEADER) "
     		+ "values(#{visitId},#{startTime},#{endTime},#{userName},#{uri},#{parameter},#{header})")
     public void insert(VisitLog visitLog);
}
