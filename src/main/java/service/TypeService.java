package service;

import dao.TypeDaoInterface;
import models.ReimbursementType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TypeService {
    TypeDaoInterface typeDao;

    public TypeService(TypeDaoInterface typeDao) {
        this.typeDao = typeDao;
    }

    //todo: status has 10 character limit
    public boolean createType(String type) {
        return typeDao.createType(type);
    }

    public ReimbursementType getTypeById(int typeId) {
        return typeDao.getType(typeId);
    }

    public ReimbursementType getTypeByName(String type){return typeDao.getTypeByName(type);}

    public List<ReimbursementType> getAllTypes() {
        return typeDao.getAllTypes();
    }

    public boolean deleteType(int typeId) {
        return typeDao.deleteType(typeId);
    }

    public ReimbursementType updateType(ReimbursementType updatedType) {
        return typeDao.updateType(updatedType);
    }
}
