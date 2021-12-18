package dao;

import models.ReimbursementType;

import java.util.List;

public interface TypeDaoInterface {
    boolean createType(String type);
    ReimbursementType getType(int typeId);
    List<ReimbursementType> getAllTypes();
    boolean deleteType(int typeId);
    ReimbursementType updateType(ReimbursementType updatedType);

    ReimbursementType getTypeByName(String type);
}
