package com.example.dms_idea.dao;

import com.example.dms_idea.pojo.Dormitory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DormitoryMapper {

    List<Dormitory> getDormitoryList(String prop, String order, Map<String, Object> map);

    void addDormitory(int bedNumber, int buildingId, int floorNumber, String name, int unitNumber);

    Integer ifDormitoryNameHaveInTheSameUnitAndFloor(String name, int unitNumber, int floorNumber, int buildingId);

    Map<String, Integer> getMaxUnitAndFloor(String id);

    Map<String, Integer> getMaxUnitAndFloor(Integer id);

    Dormitory getDormitoryById(int id);

    Dormitory getDormitoryById(String id);

    void updateDormitory(Dormitory dormitory);

    void deleteDormitory(String id);

    List<String> checkDormitoryName(Map<String, Integer> map);

    void addStudentNumber(int id, int num);

    List<Dormitory> getDormitoryListByBuildingIdUnitFloor(int id, int unit, int floor);

    List<Integer> getDormitoryUnitHasDor(int buildingId);

    List<Integer> getDormitoryFlootHasDor(int buildingId, Integer unit);

    List<Integer> getDormitoryUnitHasStu(int buildingId);

    List<Integer> getDormitoryFlootHasStu(int buildingId, Integer unit);

    List<Dormitory> getDormitoryListByBuildingIdUnitFloorHasStu(int id, int unit, int floor);
}
