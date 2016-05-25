package cn.internship.service;

import java.util.List;

import cn.internship.entity.EquipmentHistory;

public interface EquipmentHistoryService {

	public List<EquipmentHistory> getHistoryByEuipmentId(Integer equipmentId);
	
	public List<EquipmentHistory> getHistoryByEuipmentIdIsDelete(Integer equipmentId);
	
	public List<EquipmentHistory> getAllHistoryByEuipmentId();
	
	public List<EquipmentHistory> getAllHistoryByEuipmentIdIsDelete();
	
	public void add(EquipmentHistory equipmentHistory);
	
}