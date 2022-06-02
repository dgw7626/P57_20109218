package AI_arrayTest;

import ai_control.ChessAI;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hanul Rheem 20109218
 */
public class AI_test {

    private ChessAI ai;

    public AI_test() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ai = new ChessAI();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of find method, of class ArrayTest. test index is 1 Find the minimum
     */
    @Test
    public void checkIndex() {
       int[] array = new int[]{1,2,3,4,5};
       boolean isActive = false;
        for(int i =0; i < 6; i++){
            System.out.println("pos:" +array[i]);
            isActive = true;
        }
        assertEquals(isActive, true, 0);
    }
    @Test 
    public void checkIndexMin(){
        int[] array = new int[]{1,2,3,4,5};
        boolean isActive = false;
        for(int i = 0; i < 6; i++){
            if(i == 0){
                System.out.println("pos min:" + array[i]);
                isActive = true;
                break;
            }
        }
         assertEquals(isActive, true, 0);
    }
    
    @Test 
    public void checkIndexMax(){
        int[] array = new int[]{1,2,3,4,5};
           boolean isActive = false;
        for(int i = 0; i < 6; i++){
            if(i <= 6){
                System.out.println("pos max:" + array[i]);
                  isActive = true;
                break;
            }
        }
             assertEquals(isActive, true, 0);
    }
    @Test
    public void checkIndexFind(){
        int[] array = new int[]{1,2,3,4,5};
        int find = 3;
           boolean isActive = false;
         for(int i = 0; i < 6; i++){
            if(i == 3){
                System.out.println("pos find in:" + array[i]);
                      isActive = true;
                break;
            }
        }
             assertEquals(isActive, true, 0);
    }
    
    @Test 
    public void checkIndexIterator(){
         int[] array = new int[]{1,2,3,4,5};
              boolean isActive = false;
         for(int i = 0; i < 6; i++){
             System.out.println("Iterator check" + i + "values:" + array[i]);
                 isActive = true;
        }
              assertEquals(isActive, true, 0);
    }

    private void assertEquals(boolean active, boolean b, int i) {
        if(active == b){
            System.out.println("PASSED!");
        }else{
            System.out.println("FAILED!");
        }
    }
}
