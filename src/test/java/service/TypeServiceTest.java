package service;

import dao.TypeDao;
import dao.TypeDaoInterface;
import models.ReimbursementType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TypeServiceTest {
    TypeService typeService;
    TypeDaoInterface typeDao = Mockito.mock(TypeDao.class);

    @BeforeEach
    void setUp() {
        typeService = new TypeService(typeDao);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createType() {
        //arrange objects and variables

        //setup mocks
        Mockito.when(typeDao.createType("LODGING")).thenReturn(true);

        //call methods
        Boolean created = typeDao.createType("LODGING");

        //asserts
        assertTrue(created);
    }

    @Test
    void getType() {
        ReimbursementType type = new ReimbursementType("FOOD");

        Mockito.when(typeDao.getType(1)).thenReturn(type);

        ReimbursementType actualType = typeDao.getType(1);

        assertEquals(type, actualType);

    }

    @Test
    void getAllTypes() {
        List<ReimbursementType> types = new ArrayList<>();
        ReimbursementType type1 = new ReimbursementType("LODGING");
        ReimbursementType type2 = new ReimbursementType("FOOD");
        types.add(type1);
        types.add(type2);

        Mockito.when(typeDao.getAllTypes()).thenReturn(types);

        List<ReimbursementType> actualTypes = typeDao.getAllTypes();

        assertEquals(types, actualTypes);
    }

    @Test
    void deleteType() {
        Mockito.when(typeDao.deleteType(1)).thenReturn(true);

        Boolean deleted = typeDao.deleteType(1);

        assertTrue(deleted);
    }

    @Test
    void updateType() {
        ReimbursementType type = new ReimbursementType("FOOD");
        ReimbursementType updatedType = new ReimbursementType("LODGING");

        Mockito.when(typeDao.updateType(updatedType)).thenReturn(updatedType);

        ReimbursementType actualType = typeDao.updateType(updatedType);

        assertEquals(updatedType, actualType);
    }
}