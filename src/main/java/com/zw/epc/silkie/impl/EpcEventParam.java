/**
 * Copyright (c) 2014 by pw186.com.
 * All right reserved.
 */
package com.zw.epc.silkie.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zw.epc.silkie.BaseParam;
import com.zw.epc.silkie.ModeLock;
import com.zw.epc.silkie.field.DataField;

/**
 * 事件参数，改接口实例将作为参数注入Event中执行
 *
 */
public class EpcEventParam extends BaseParam {
	
	private int seqSeries;// unsigned short
	private long seqNo; // unsigned int
	private int tid;
	
	
	private HashMap<String, Object> map;
	private long timestamp;
	private ModeLock modeLock;
	
	public EpcEventParam() {
	    timestamp = System.currentTimeMillis();
		map = new HashMap<String, Object>();
	}
	
	protected Map<String, Object> getMap() {
		return map;
	}

	/**
	 * 参数类生成的时间
	 */
	public long getCreatedMillis() {
	    return timestamp;
	}
	
/////////////////////////////////////////////////// FtdField Ops ///////////////	
	
	static final String FIELD_PARAM_NAME = "EpcEventParam.field.list";
	
	/**
	 * 获得所有的Field
	 */
	public DataField[] getFields() {
		List<DataField> list = getFieldList();
		return list == null? null : (DataField[])list.toArray();
	}
	
	/**
	 * 以List的形式返回域
	 */
	@SuppressWarnings("unchecked")
	public List<DataField> getFieldList() {
		return (List<DataField>)getObjValue(FIELD_PARAM_NAME);
	}
	
	/**
	 *  存入所有的域
	 */
	public void setFields(DataField[] fields) {
		if (fields == null) return ;
		List<DataField> list = new ArrayList<DataField>();
		for (int i = 0; i < fields.length; i++) 
			list.add(fields[i]);
	
		setValue(FIELD_PARAM_NAME, list);
	}
	
	/**
	 *  存入所有的域
	 */
	public void setFields(List<DataField> fields) {
		if (fields == null) return ;
		setValue(FIELD_PARAM_NAME, fields);
	}
	
	/**
	 * 追加一个field
	 */
	public void addField(DataField f) {
		if (f == null) return;
		List<DataField> list = getFieldList();
		if( list == null) {
			list = new ArrayList<DataField>();
			setFields(list);
		}
		list.add(f);
	}
	
	/**
	 * 追加一组field
	 */
	public void addAllField(List<DataField> flist) {
		if (flist == null) return;
		List<DataField> list = getFieldList();
		if( list == null) {
			list = new ArrayList<DataField>();
			setFields(list);
		}
		list.addAll(flist);
	}
	
	/**
	 * 高效的获取方法
	 * @param index 第几个域
	 * @return null 没有或越界
	 */
	public DataField getField(int index) {
		List<DataField> list = getFieldList();
		if (list == null)
			return null;
		
		if (index < 0 || index >= list.size())
			return null;
		
		return list.get(index);
	}
	
	/**
	 * 查找第一个pid对应的域
	 */
	public DataField queryFirst(int pid) {
		List<DataField> list = getFieldList();
		if (list == null)
			return null;
		for (DataField f : list) {
			if (f.id() == pid)
				return f;
		}
		return null;
	}
	
	/**
	 * 查找所有pid对应的域
	 */
	public List<DataField> query(int pid) {
		List<DataField> rtn = new ArrayList<DataField>(4);
		List<DataField> list = getFieldList();
		if (list == null)
			return null;
		
		for (DataField f : list) {
			if (f.id() == pid)
				rtn.add(f);
		}
		
		return rtn;
	}
	
	/**
	 * 把几个sgFtdFields数组连接成一个新的数组
	 * @param sgFtdFields[]
	 * @return
	 */
	public static DataField[] assembleDataField(DataField[] ...fields ){
		if(fields == null || fields.length==0)
			return null;
		
		int lentgh = 0;
		for(int i=0 ; i<fields.length; i++){
			lentgh += fields[i].length;
		}
		
		DataField[] result = new DataField[lentgh];
		int pos = 0;
		for(int i=0 ; i<fields.length; i++){
			System.arraycopy(fields[i], 0, result, pos, fields[i].length);
			
			lentgh += fields[i].length;
			pos += fields[i].length;
		}
		
		return result;
	}

	public void setSeqSeries(int seqSeries) {
		this.seqSeries = seqSeries;
	}

	public int getSeqSeries() {
		return seqSeries;
	}

	public void setSeqNo(long seqNo) {
		this.seqNo = seqNo;
	}

	public long getSeqNo() {
		return seqNo;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getTid() {
		return tid;
	}

	public ModeLock getModeLock() {
		return modeLock;
	}

	public void setModeLock(ModeLock modeLock) {
		this.modeLock = modeLock;
	}
	
}
