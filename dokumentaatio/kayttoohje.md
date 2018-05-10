# Käyttöohje

Lataa tiedosto [runIT-1.0-SNAPSHOT.jar](https://github.com/hajame/otm-harjoitustyo/releases/tag/viikko6)

Sovellus olettaa, että käyttäjä on asentanut Java v1.8:n ja SQLite 3:n.

## Konfigurointi

Sovellus olettaa, että sen käynnistyshakemistossa on konfiguraatiotiedosto _config.properties_, joka määrittelee harjoitustietokannan nimen. Tiedoston sisällön tulee olla alla olevassa muodossa. 

```
databaseFile=database.db
```
Jos et halua itse tehdä tiedostoa, ohjelma luo automaattisesti _config.properties_-tiedoston, jossa tietokannan nimi on _database.db_.

Voit koska vain korvata tietokannan nimen _database.db_ haluamallasi nimellä. Tällöin ohjelma luo uuden tietokannan konfiguroidulla nimellä. Voit ylläpitää useita harjoitustietokantoja ja tuoda tietokannan toiselta tietokoneelta.

## Ohjelman käynnistäminen

Ohjelma käynnistetään työpöydän pikakuvakkeesta, tai komentorivillä komennolla

```
java -jar runIT-1.0-SNAPSHOT.jar
```

## Kirjautuminen

Sovellus käynnistyy kirjautumisnäkymään:

![Kirjautumisnäkymä](https://github.com/hajame/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/loginScreen.png)

Kirjautuminen onnistuu kirjoittamalla olemassaoleva käyttäjätunnus ja salasana syötekenttiin ja painamalla _login_. Onnistuneen kirjautumisen jälkeen siirrytään harjoitukset-näkymään (exercises).

Mikäli haluat kokeilla ohjelmaa luomatta uutta käyttäjää, voit kirjautua tunnuksilla: 
- Username: _test_ Password: _pass_

## Uuden käyttäjän luominen

Kirjautumisnäkymästä on mahdollista siirtyä uuden käyttäjän luomisnäkymään panikkeella _create new user_.

Uusi käyttäjä luodaan syöttämällä tiedot syötekenttiin ja painamalla _create_.


![Uusi käyttäjä -näkymä](https://github.com/hajame/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/newUserSceen.png)

Jos käyttäjän luominen onnistui, palataan kirjautumisnäkymään.

## Harjoitukset

Onnistuneen kirjautumisen myötä siirrytään käyttäjien harjoitukset listaavaan harjoitukset-näkymään.

![Harjoitukset-näkymä](https://github.com/hajame/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/exerciseView.png)

### Harjoitusten lisääminen ja poistaminen

Näkymä mahdollistaa olemassaolevien harjoitusten poistamisen painikkeella _delete_ sekä uusien harjoitusten luomisen. 

Ohjelma hakee automaattisesti järjestelmän päivämäärän ja kellonajan sekä oletusharjoituksen tiedot oikeassa formaatissa. Voit muokata syötekentien tietoja. Harjoitus lisätään painamalla _add_.

## Yhteenveto-näkymä (summary)
Klikkaamalla _summary_, siirrytään yhteenveto-näkymään. Yhteenvetonäkymässä saat tilastotietoa harjoituksistasi.

![Yhteenveto-näkymä](https://github.com/hajame/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/summaryView.png)

Klikkaamalla _exercises_, siiryt takaisin harjoitukset-näkymään.

## Ohjelmasta poistuminen

Klikkaamalla näkymän oikean ylänurkan painiketta _logout_, kirjaudut ulos sovelluksesta ja sovellus palaa takaisin kirjaantumisnäkymään. Voit myös poistua sulkemalla ikkunan, jolloin käyttäjä kirjataan ulos automaattisesti.







