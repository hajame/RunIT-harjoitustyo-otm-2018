# Käyttöohje

Lataa tiedosto [runIT-1.0-SNAPSHOT.jar](https://github.com/hajame/otm-harjoitustyo/releases/tag/viikko6)

Sovellus olettaa, että käyttäjä on asentanut Java v1.8:n ja SQLite 3:n.

## Ohjelman käynnistäminen

Ohjelma käynnistetään työpöydän pikakuvakkeesta, tai komentorivillä komennolla

```
java -jar runIT-1.0-SNAPSHOT.jar
```

## Kirjautuminen

Sovellus käynnistyy kirjautumisnäkymään:

![Kirjautumisnäkymä](https://github.com/hajame/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/loginScreen.png)

Kirjautuminen onnistuu kirjoittamalla olemassaoleva käyttäjätunnus ja salasana syötekenttiin ja painamalla _login_. Onnistuneen kirjautumisen jälkeen siirrytään harjoitukset-näkymään (exercises)

## Uuden käyttäjän luominen

Kirjautumisnäkymästä on mahdollista siirtyä uuden käyttäjän luomisnäkymään panikkeella _create new user_.

Uusi käyttäjä luodaan syöttämällä tiedot syötekenttiin ja painamalla _create_


![Uusi käyttäjä -näkymä](https://github.com/hajame/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/newUserSceen.png)

Jos käyttäjän luominen onnistuu, palataan kirjautumisnäkymään.

## Harjoitukset

Onnistuneen kirjautumisen myötä siirrytään käyttäjien harjoitukset listaavaan harjoitukset-näkymään.

![Harjoitukset-näkymä](https://github.com/hajame/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/exerciseView.png)

### Harjoitusten lisääminen ja poistaminen

Näkymä mahdollistaa olemassaolevien harjoitusten poistamisen painikkeella _delete_ sekä uusien harjoitusten luomisen kirjoittamalla syötekenttään esimerkin mukaiset tiedot ja painamalla _add_. Kenttiin on syötetty valmiiksi tiedot ohjelman vaatimassa formaatissa.

Klikkaamalla näkymän oikean ylänurkan painiketta _logout_, käyttäjä kirjautuu ulos sovelluksesta ja sovellus palaa takaisin kirjaantumisnäkymään.

Klikkaamalla _summary_, käyttäjä siirtyy yhteenveto-näkymään.

## Yhteenveto-näkymä (summary)

Yhteenvetonäkymässä käyttäjä saa tilastotietoa harjoituksistaan

![Yhteenveto-näkymä](https://github.com/hajame/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/summaryView.png)

Klikkaamalla _exercises_, käyttäjä siirtyy takaisin harjoitukset-näkymään.
Klikkaamalla painiketta _logout_, käyttäjä kirjautuu ulos sovelluksesta ja sovellus palaa takaisin kirjaantumisnäkymään.









