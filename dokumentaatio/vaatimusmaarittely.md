# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen avulla käyttäjien on mahdollista seurata oman kuntonsa kehitystä kirjaamalla juoksu- tai kävelylenkkeihin liittyvää tietoa. Sovellusta on mahdollista käyttää useamman rekisteröityneen käyttäjän, joilla on kaikilla oma yksilöllinen lenkkihistoriansa. 

Ohjelma tarjoaa mahdollisuuden tarkastella yksittäisen lenkin tietoja. Lisäksi ohjelma esittää käyttäjälle koottua tietoa kunnon kehittymisestä näyttämällä kaikkien lenkkien

- kokonaismäärän
- pituuden
- keston 
- keskinopeuden
- ja keskipituuden.

## Käyttäjät

Sovelluksella on yksi käyttäjärooli, **normaali käyttäjä**. Sovelluksella voi olla useita käyttäjiä, jotka kirjautuvat sovellukseen salasanalla.

## Käyttöliittymäluonnos

![Hahmotelma käyttöliittymästä](https://github.com/hajame/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/UIMockUp2.png)

Ohjelma avautuu kirjautumisnäkymään, josta voidaan siirtyä uuden käyttäjän luomiseen tai kotinäkymään. Kotinäkymässä käyttäjä voi tarkastella ja lisätä lenkkejä, poistua tai siirtyä tilastonäkymään, joka kertoo tilastotietoa käyttäjän harjoittelusta. 

## Perusversion tarjoama toiminnallisuus

### Ennen kirjautumista

- käyttäjä voi luoda itselleen käyttätunnuksen  
    - tunnuksen täytyy olla uniikki ja vähintään 3 merkkiä pitkä
    - jos tunnus on varattu, eli tietokannassa on jo sama tunnus, käyttäjä saa siitä ilmoituksen 
- käyttäjä voi kirjautua järjestelmään

### Kirjautumisen jälkeen

- harjoitusnäkymässä käyttäjä näkee oman lenkkihistoriansa aikajärjestyksessä
    - tuorein tapahtuma on listan ensimmäisenä
- käyttäjä voi kirjata uuden lenkin
    - lenkki näkyy vain sen luoneelle käyttäjälle
    - lenkin tietoihin kirjataan
        - päivämäärä ja aika  
        - lenkin kesto
        - lenkin pituus
- käyttäjä voi poistaa yksittäisiä lenkkimerkintöjä
- käyttäjä voi siirtyä tilastonäkymään, jossa näkyy kaikkien lenkkien
    - kokonaismäärä
    - pituus
    - kesto 
    - keskinopeus
    - keskipituus
- käyttäjä voi siityä takaisin harjoitusnkymään    
- käyttäjä voi kirjautua ulos järjestelmästä

## Jatkokehitysideoita

Perusversion jälkeen järjestelmää täydennetään (jos aika sallii) seuraavilla toiminnallisuuksilla

- lenkkitietojen muokkaaminen
- halutun aikavälin (viikko / kuukausi) tilastot
- käyttäjien yhteyteen salasana, joka vaaditaan kirjautuessa
- lenkin tietoihin voi halutessaan syöttää lisätiedon esim.
    - huippunopeuden
    - paikkatiedon
    - säätiedon
- lenkkitietojen vienti taulukkomuotoon
    - mahdollisesti suoraan Google Sheetsiin API:n avulla
- lämpötilatiedon lisääminen paikkatiedon ja ajan perusteella
- pääkäyttäjä, joka voi näkee muiden käyttäjien lenkkitiedot ja voi muokata niitä
- käyttäjätunnuksen poistaminen
    - lenkkitiedot poistuvat samalla
- tavoitteet
    - käyttäjä voi asettaa itselleen juoksutavoitteen
        - esim 100 km / kk
    - tavoite näytetään kirjautumisen jälkeisessä näkymässä

