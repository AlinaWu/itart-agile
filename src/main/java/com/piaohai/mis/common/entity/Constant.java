package com.piaohai.mis.common.entity;

public class Constant {
	//用户信息在session中对应的ID
	public final static String SESSION_USER_ID="____1C402A287CCC4A28913FA88F81917839__nmj";
	//驗證碼在session中对应的ID
	public final static String SESSION_CODE_ID="____1C402A287CCC4A28913FA88F81917839_nmj";
	//项目路径
	public final static String path;
	//上传文件存放的文件夹名称
	public final static String uploadFolderName="upload";
	//xml数据库操作类型-新增
	public final static String STATEMENT_TYPE_INSERT="INSERT";
	//xml数据库操作类型-更新
	public final static String STATEMENT_TYPE_UPDATE="UPDATE";
	static{
		path = Thread.currentThread().getContextClassLoader().getResource("").getPath().replace("WEB-INF/classes/", "");
	}
	//doc对象模型
	public final static String DOC_MODEL="docModel";
	//doc频道模型
	public final static String CHAN_MODEL="chanModel";
}
