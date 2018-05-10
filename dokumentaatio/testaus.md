# Testausdokumentti

Ohjelmaa on testattu sekä automatisoiduin yksikkö- ja integraatiotestein JUnitilla sekä manuaalisesti tapahtunein järjestelmätason testein.

## Yksikkö- ja integraatiotestaus

### sovelluslogiikka

Automatisoitujen testien ytimen moudostavat sovelluslogiikkaa, eli pakkauksien [runit.domain](https://github.com/hajame/otm-harjoitustyo/tree/master/runIT/src/main/java/runit/domain) ja [runit.dao](https://github.com/hajame/otm-harjoitustyo/tree/master/runIT/src/main/java/runit/dao) luokkia testaavat integraatiotestit (pakkauksessa [test.integrated](https://github.com/hajame/otm-harjoitustyo/tree/master/runIT/src/test/java/test/integrated)), joiden määrittelevät testitapaukset simuloivat käyttöliittymän [Logic](https://github.com/hajame/otm-harjoitustyo/blob/master/runIT/src/main/java/runit/domain/Logic.java)-olin avulla suorittamia toiminnallisuuksia.

Integraatiotestit käyttävät datan pysyväistallennukseen DAO-rajapintojen keskusmuistitoteutuksia [ExerciseDao](https://github.com/hajame/otm-harjoitustyo/blob/master/runIT/src/main/java/runit/dao/ExerciseDao.java) ja [UserDao](https://github.com/hajame/otm-harjoitustyo/blob/master/runIT/src/main/java/runit/dao/UserDao.java).

Sovelluslogiikkakerroksen luokille [Logic](https://github.com/hajame/otm-harjoitustyo/blob/master/runIT/src/main/java/runit/domain/Logic.java), [User](https://github.com/hajame/otm-harjoitustyo/blob/master/runIT/src/main/java/runit/domain/User.java), [Exercise](https://github.com/hajame/otm-harjoitustyo/blob/master/runIT/src/main/java/runit/domain/Exercise.java) ja [Statistics](https://github.com/hajame/otm-harjoitustyo/blob/master/runIT/src/main/java/runit/domain/Statistics.java) on tehty muutama yksikkötesti kattamaan tapaukset, joita integraatiotestit eivät kata pakkauksessa [test.domain](https://github.com/hajame/otm-harjoitustyo/tree/master/runIT/src/test/java/test/domain).

### DAO-luokat

Molempien DAO-luokkien toiminnallisuus on testattu luomalla testeissä tilapäinen tietokanta, joka poistetaan testien lopuksi.
