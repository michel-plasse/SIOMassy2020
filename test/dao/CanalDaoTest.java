/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import modele.Canal;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cissé-LENOVO
 */
public class CanalDaoTest {
    



    @Test
    public void testGetAll() throws Exception {
        System.out.println("getAll");
        List<Canal> expResult = new ArrayList<Canal>();
        expResult.add(new Canal(1, 1, "Canal_1", LocalDateTime.of(2019, Month.SEPTEMBER, 02, 0, 0, 0)));
        expResult.add(new Canal(2, 2, "Canal_2", LocalDateTime.of(2019, Month.SEPTEMBER, 02, 0, 0, 0)));
        expResult.add(new Canal(3, 3, "Canal_3", LocalDateTime.of(2019, Month.SEPTEMBER, 02, 0, 0, 0)));

        List<Canal> result = CanalDao.getAll();
        assertEquals(3, result.size());
    }
    
    
}
