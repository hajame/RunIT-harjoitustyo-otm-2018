# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen avulla käyttäjien on mahdollista seurata oman kuntonsa kehitystä kirjaamalla juoksu- tai kävelylenkkeihin liittyvää tietoa. Sovellusta on mahdollista käyttää useamman rekisteröityneen käyttäjän, joilla on kaikilla oma yksilöllinen lenkkihistoriansa. 

Ohjelma tarjoaa mahdollisuuden tarkastella yksittäisen lenkin tietoja. Lisäksi ohjelma esittää käyttäjälle koottua tietoa kunnon kehittymisestä näyttämällä tietyltä aikajaksolta lenkkien määrän, keskinopeuden, sekä matkojen keskipituuden.

## Käyttäjät

Alkuvaiheessa sovelluksella on ainoastaan yksi käyttäjärooli eli **normaali käyttäjä.** Myöhemmin sovellukseen saatetaan lisätä suuremmilla oikeuksilla varustettu **pääkäyttäjä**.

## Perusversion tarjoama toiminnallisuus

### Ennen kirjautumista

- käyttäjä voi luoda itselleen käyttätunnuksen  
    - tunnuksen täytyy olla uniikki  
    
- käyttäjä voi kirjautua järjestelmään

### Kirjautumisen jälkeen

- käyttäjä näkee oman lenkkihistoriansa viimeisemmät tapahtumat
- käyttäjä voi kirjata uuden lenkin
    - lenkki näkyy vain sen luoneelle käyttäjälle
    - lenkin tietoihin kirjataan
        - päivämäärä ja aika  
        - lenkin kesto
        - lenkin pituus
        - kuvaus / desc
- käyttäjä saa halutessaan näkyviin listan kaikista lenkeistä
    - listalta voi valita yksittäisen lenkin ja tarkastella sen tietoja
- käyttäjä voi siirtyä tilastonäkymään, jossa näkyy 
    - kaikkien lenkkien määrä, aikojen keskiarvo, ja keskipituus  
- käyttäjä voi kirjautua ulos järjestelmästä


## Jatkokehitysideoita

Perusversion jälkeen järjestelmää täydennetään (jos aika sallii) seuraavilla toiminnallisuuksilla

- lenkkitietojen poistaminen
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

