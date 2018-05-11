# Arkkitehtuurikuvaus

## Rakenne
Ohjelman rakenne noudattelee kolmitasaoista kerrosarkkitehtuuria, ja koodin pakkausrakenne on seuraava:
![Package Overview of runIT](https://github.com/hajame/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/packageOverview.jpg)

Pakkaus _runit.ui_ sisältää JavaFX:llä toteutetun käyttöliittymän, _runit.domain_ sovelluslogiikan ja _runit.dao_ tietojen pysyväistallennuksesta vastaavan koodin.

## Käyttöliittymä

Käyttöliittymä sisältää neljä erillistä näkymää
- kirjautuminen
- uuden käyttäjän luominen
- harjoituslista
- yhteenvetonäkymä

Jokainen näistä on toteutettu omana [Scene](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html)-oliona. Näkymistä yksi kerrallaan on näkyvänä eli sijoitettuna sovelluksen [stageen](https://docs.oracle.com/javase/8/javafx/api/javafx/stage/Stage.html). Käyttöliittymä on rakennettu ohjelmallisesti luokassa [runit.ui.GUI](https://github.com/hajame/otm-harjoitustyo/blob/master/runIT/src/main/java/runit/ui/GUI.java).

Käyttöliittymä on pyritty eristämään täysin sovelluslogiikasta, se ainoastaan kutsuu sopivin parametrein sovelluslogiikan toteuttavan olion _Logic_ metodeja.

Kun sovelluksen harjoituslistan tilanne muuttuu, eli uusi käyttäjä kirjautuu, harjoitus luodaan tai poistetaan, kutsutaan sovelluksen metodia [redrawExerciseList](https://github.com/hajame/otm-harjoitustyo/blob/master/runIT/src/main/java/runit/ui/GUI.java#L60) joka renderöi harjoituslistan uudelleen sovelluslogiikalta saamansa päivitetyn listan perusteella.

## Sovelluslogiikka

Sovelluksen loogisen datamallin  muodostavat luokat [User](https://github.com/hajame/otm-harjoitustyo/blob/master/runIT/src/main/java/runit/domain/User.java) ja [Exercise](https://github.com/hajame/otm-harjoitustyo/blob/master/runIT/src/main/java/runit/domain/Exercise.java), jotka kuvaavat käyttäjiä ja heidän harjoituksiaan. Luokka [Statistics](https://github.com/hajame/otm-harjoitustyo/blob/master/runIT/src/main/java/runit/domain/Statistics.java) laskee harjoituksiin liittyvää tilastotietoa, jonka se säilöö yhteen keskiarvoarvoja edustavaan Exercise-olioon.

![User-Exercise Relation](https://github.com/hajame/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/UserExerciseRelation.jpg)

Toiminnalisista kokonaisuuksista vastaa luokan [Logic](https://github.com/hajame/otm-harjoitustyo/blob/master/runIT/src/main/java/runit/domain/Logic.java) ainoa olio. Luokka tarjoaa metodit käyttölittymän kaikille toiminnoille. Näitä ovat esim.

- String loginUser(String username, String password)
- void addExercise(Exercise exercise)
- void deleteExercise(Exercise exercise)
- List<Exercise> getHistory()
- Statistics getStatistics()
  
_Logic_ pääsee käsiksi käyttäjiin ja harjoituksiin tietojen tallennuksesta vastaavan pakkauksessa runit.dao sijaitsevien Dao-rajapinnan toteuttavien UserDao ja ExerciseDao -luokkien kautta. Luokkien toteutukset injektoidaan sovelluslogiikalle konstruktorikutsun yhteydessä.

__Ohjelman osien suhdetta kuvaava pakkaus/luokkakaavio:__

![Class-PackageUML of runIT](https://github.com/hajame/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/runITclasspackage.jpg)

## Tietojen pysyväistallennus
Pakkauksen runit.dao luokat UserDao ja ExerciseDao huolehtivat tietojen tallettamisesta SQL-tietokantaan. Luokat noudattavat Data Access Object -suunnittelumallia.

Sovelluslogiikan testauksessa hyödynnetäänkin tätä siten, että testeissä käytetään tiedostoon tallentavien DAO-olioiden sijaan keskusmuistiin tallentavia toteutuksia.

### Tiedostot

Sovelluksen juureen sijoitettu [konfiguraatiotiedosto](https://github.com/hajame/otm-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md#konfigurointi) [config.properties](https://github.com/hajame/otm-harjoitustyo/blob/master/runIT/config.properties) määrittelee tietokantatideoston nimen.

Käyttäjät ja harjoitukset tallennetaan SQLite3-järjestelmän kontrolloiman SQL-tietokannan User ja Exercise -tietokantatauluihin.

![Tietokantakaavio](https://github.com/hajame/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/TietokantakaavioRunIT.jpg)

## Päätoiminnallisuudet

Kuvataan seuraavaksi sovelluksen toimintalogiikka muutaman päätoiminnallisuuden osalta sekvenssikaaviona.

### Käyttäjän kirjautuminen
Kun kirjautumisnäkymässä on syötekenttään kirjoitettu käyttäjätunnus ja klikataan painiketta loginButton etenee sovelluksen kontrolli seuraavasti:

![Käyttäjän kirjautuminen](https://github.com/hajame/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/loginSequence.jpg)

Painikkeen painamiseen reagoiva tapahtumankäsittelijä kutsuu sovelluslogiikan Logic metodia loginUser antaen parametriksi kirjautuneen käyttäjätunnuksen ja salasanan. Sovelluslogiikka selvittää UserDao:n avulla onko käyttäjätunnus olemassa. Jos on, eli kirjautuminen onnistuu, on seurauksena se että käyttöliittymä vaihtaa näkymäksi runitScenen, eli sovelluksen varsinaisen päänäkymän ja renderöi näkymään kirjautuneen käyttäjän Exerciset eli juoksuharjoitukset.

### Uuden käyttäjän luominen

### Harjoituksen lisääminen

### Yhteenvetonäkymään siirtyminen
