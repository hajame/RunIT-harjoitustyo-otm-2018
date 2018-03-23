/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.unicafe.Kassapaate;
import com.mycompany.unicafe.Maksukortti;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hajame
 */
public class KassapaateTest {

    Kassapaate paate;
    Maksukortti kortti;
    Maksukortti koyha;

    public KassapaateTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        paate = new Kassapaate();
        kortti = new Maksukortti(1000);
        koyha = new Maksukortti(100);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void alussaRahaaTuhat() {
        assertEquals(100000, paate.kassassaRahaa());
    }

    @Test
    public void alussaEdullisia0() {
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }

    @Test
    public void alussaMaukkaita0() {
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void EdullisenOstoKunRahaa() {
        assertEquals(60, paate.syoEdullisesti(300));
        assertEquals(100000 + 240, paate.kassassaRahaa());
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }

    @Test
    public void EdullisenOstoKunEiRahaa() {
        assertEquals(60, paate.syoEdullisesti(60));
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }

    @Test
    public void MaukkaanOstoKunRahaa() {
        assertEquals(100, paate.syoMaukkaasti(500));
        assertEquals(100000 + 400, paate.kassassaRahaa());
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void MaukkaanOstoKunEiRahaa() {
        assertEquals(60, paate.syoMaukkaasti(60));
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void EdullisenKorttiostoKunRahaa() {
        
        assertEquals(true, paate.syoEdullisesti(kortti));
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void EdullisenKorttiostoKunEiRahaa() {
        
        assertEquals(false, paate.syoEdullisesti(koyha));
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }

    @Test
    public void MaukkaanKorttiostoKunRahaa() {
        
        assertEquals(true, paate.syoMaukkaasti(kortti));
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void MaukkaanKorttiostoKunEiRahaa() {
        
        assertEquals(false, paate.syoMaukkaasti(koyha));
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void KortinLatausOnnistuu() {
        paate.lataaRahaaKortille(kortti, 100);
        assertEquals(1100, kortti.saldo());
        assertEquals(100100, paate.kassassaRahaa());
       
    }
    
    @Test
    public void KortinLatausNegatiivisellaEiOnnistu() {
                
        paate.lataaRahaaKortille(kortti, -10);
        
        assertEquals(1000, kortti.saldo());
        assertEquals(100000, paate.kassassaRahaa());
        
    }    

}
