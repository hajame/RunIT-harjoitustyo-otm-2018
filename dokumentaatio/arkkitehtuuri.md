# Arkkitehtuurikuvaus


## Sovelluslogiikka
![Class-PackageUML of runIT](https://github.com/hajame/otm-harjoitustyo/blob/master/dokumentaatio/runITclasspackage.jpg)

## Tietojen pysyväistallennus
Pakkauksen runit.dao luokat UserDao ja ExerciseDao huolehtivat tietojen tallettamisesta SQLite-tietokantaan. Luokat noudattavat Data Access Object -suunnittelumallia.

Sovelluslogiikan testauksessa hyödynnetäänkin tätä siten, että testeissä käytetään tiedostoon tallentavien DAO-olioiden sijaan keskusmuistiin tallentavia toteutuksia.

### Päätoiminnallisuudet
#### Käyttäjän kirjautuminen
Kun kirjautumisnäkymässä on syötekenttään kirjoitettu käyttäjätunnus ja klikataan painiketta loginButton etenee sovelluksen kontrolli seuraavasti:
![Käyttäjän kirjautuminen](https://github.com/hajame/otm-harjoitustyo/blob/master/dokumentaatio/loginSequence.jpg)

Painikkeen painamiseen reagoiva tapahtumankäsittelijä kutsuu sovelluslogiikan Logic metodia loginUser antaen parametriksi kirjautuneen käyttäjätunnuksen ja salasanan. Sovelluslogiikka selvittää UserDao:n avulla onko käyttäjätunnus olemassa. Jos on, eli kirjautuminen onnistuu, on seurauksena se että käyttöliittymä vaihtaa näkymäksi runitScenen, eli sovelluksen varsinaisen päänäkymän ja renderöi näkymään kirjautuneen käyttäjän Exerciset eli juoksuharjoitukset.
