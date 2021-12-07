package dao;

import models.Reimbursement;
import models.ReimbursementType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.H2Util;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TypeDaoTest {
    TypeDaoInterface typeDao;

    @BeforeEach
    void setUp() {
        typeDao = new TypeDao(H2Util.url, H2Util.userName, H2Util.password);
        H2Util.createAll();
    }

    @AfterEach
    void tearDown() {
        H2Util.dropAll();

    }

    @Test
    void createType() {
        boolean created = typeDao.createType("LODGING");

        assertTrue(created);
    }

    @Test
    void getType() {
        ReimbursementType type = new ReimbursementType(1, "LODGING");
        typeDao.createType(type.getType());
        ReimbursementType type1 = typeDao.getType(1);

        assertEquals(type.toString(), type1.toString());
    }

    @Test
    void getAllTypes() {
        ReimbursementType type = new ReimbursementType(1, "LODGING");
        ReimbursementType type1 = new ReimbursementType(2, "FOOD");

        Boolean created = typeDao.createType(type.getType());
        created = typeDao.createType(type1.getType());
        List<ReimbursementType> types = typeDao.getAllTypes();

        assertEquals(types.size(), 2);
    }

    @Test
    void deleteType() {
        ReimbursementType type = new ReimbursementType(1, "LODGING");
        ReimbursementType type1 = new ReimbursementType(2, "FOOD");

        typeDao.createType(type.getType());
        typeDao.createType(type1.getType());
        boolean deleted = typeDao.deleteType(1);
        List<ReimbursementType> types = typeDao.getAllTypes();

        assertTrue(deleted);
        assertEquals(types.size(), 1);
    }

    @Test
    void updateType() {
        ReimbursementType type = new ReimbursementType(1, "LODGING");
        ReimbursementType updatedType = new ReimbursementType(1, "FOOD");

        typeDao.createType(type.getType());
        ReimbursementType resultType = typeDao.updateType(updatedType);

        assertEquals(updatedType.toString(), resultType.toString());
    }


}