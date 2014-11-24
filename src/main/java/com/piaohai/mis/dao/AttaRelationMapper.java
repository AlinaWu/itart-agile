package com.piaohai.mis.dao;

import com.piaohai.mis.model.AttaRelation;

public interface AttaRelationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ATTA_RELATION
     *
     * @mbggenerated Wed Sep 25 15:09:33 CST 2013
     */
    int deleteByPrimaryKey(String atreId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ATTA_RELATION
     *
     * @mbggenerated Wed Sep 25 15:09:33 CST 2013
     */
    int insert(AttaRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ATTA_RELATION
     *
     * @mbggenerated Wed Sep 25 15:09:33 CST 2013
     */
    int insertSelective(AttaRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ATTA_RELATION
     *
     * @mbggenerated Wed Sep 25 15:09:33 CST 2013
     */
    AttaRelation selectByPrimaryKey(String atreId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ATTA_RELATION
     *
     * @mbggenerated Wed Sep 25 15:09:33 CST 2013
     */
    int updateByPrimaryKeySelective(AttaRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ATTA_RELATION
     *
     * @mbggenerated Wed Sep 25 15:09:33 CST 2013
     */
    int updateByPrimaryKey(AttaRelation record);
    /**
	 *	根据附件id删除 
	 * @param attaId
	 * @return
	 */
	public int deleteByAttachId(String attaId);
}