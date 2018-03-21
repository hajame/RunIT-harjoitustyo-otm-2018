package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }

    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void saldoEiMuutuJosRahaaEiTarpeeksi() {
        kortti.otaRahaa(500);
        kortti.otaRahaa(1000);
        
        
        assertEquals("saldo: 5.0", kortti.toString());
    }
    
    @Test
    public void palauttaaTruejosRahatRiittavat() {
        assertTrue(kortti.otaRahaa(500));
    }
    
    @Test
    public void palauttaaFalsejosRahatEivätRiita() {
        assertFalse(kortti.otaRahaa(1500));
    }

    @Test
    public void antaaOikeanSaldon() {
        assertEquals(1000, kortti.saldo());
    }

    @Test
    public void  lisaaOikeanMaaran() {
        kortti.lataaRahaa(100);
        assertEquals(1100, kortti.saldo());
    }
    
}
