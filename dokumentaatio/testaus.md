# Testausdokumentti

Ohjelmaa on testattu sekä automatisoiduin yksikkö- ja integraatiotestein JUnitilla sekä manuaalisesti tapahtunein järjestelmätason testein.

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Automatisoitujen testien ytimen moudostavat sovelluslogiikkaa, eli pakkauksien [runit.domain](https://github.com/hajame/otm-harjoitustyo/tree/master/runIT/src/main/java/runit/domain) ja [runit.dao](https://github.com/hajame/otm-harjoitustyo/tree/master/runIT/src/main/java/runit/dao) luokkia testaavat integraatiotestit (pakkauksessa [test.integrated](https://github.com/hajame/otm-harjoitustyo/tree/master/runIT/src/test/java/test/integrated)), joiden määrittelevät testitapaukset simuloivat käyttöliittymän [Logic](https://github.com/hajame/otm-harjoitustyo/blob/master/runIT/src/main/java/runit/domain/Logic.java)-olin avulla suorittamia toiminnallisuuksia.

Integraatiotestit käyttävät datan pysyväistallennukseen DAO-rajapintojen keskusmuistitoteutuksia [ExerciseDao](https://github.com/hajame/otm-harjoitustyo/blob/master/runIT/src/main/java/runit/dao/ExerciseDao.java) ja [UserDao](https://github.com/hajame/otm-harjoitustyo/blob/master/runIT/src/main/java/runit/dao/UserDao.java).

Sovelluslogiikkakerroksen luokille [Logic](https://github.com/hajame/otm-harjoitustyo/blob/master/runIT/src/main/java/runit/domain/Logic.java), [User](https://github.com/hajame/otm-harjoitustyo/blob/master/runIT/src/main/java/runit/domain/User.java), [Exercise](https://github.com/hajame/otm-harjoitustyo/blob/master/runIT/src/main/java/runit/domain/Exercise.java) ja [Statistics](https://github.com/hajame/otm-harjoitustyo/blob/master/runIT/src/main/java/runit/domain/Statistics.java) on tehty muutama yksikkötesti kattamaan tapaukset, joita integraatiotestit eivät kata pakkauksessa [test.domain](https://github.com/hajame/otm-harjoitustyo/tree/master/runIT/src/test/java/test/domain).

### DAO-luokat

Molempien DAO-luokkien toiminnallisuus on testattu luomalla testeissä tilapäinen testitietokanta, joka poistetaan testien lopuksi.

### Testauskattavuus

Käyttöliittymäkerrosta lukuunottamatta sovelluksen testauksen rivikattavuus on 94% ja haarautumakattavuus 93%.

![testikattavuus](https://github.com/hajame/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/TestikattavuusRunIT.png)

Testaamatta jäivät tilanteet, joissa tietokantatoiminnot eivät onnistu tietokannan virheen, esimerkiksi korruptoitumisen takia. Jäi myös testaamatta tilanne , jossa ExerciseDao ei löydä pyydettyä harjoitusta findOne-metodilla.

## Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti.

### Asennus ja konfigurointi

Sovellus on haettu ja sitä on testattu [käyttöohjeen](https://github.com/hajame/otm-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md) kuvaamalla tavalla sekä Windows- että Linux-ympäristöön. Asennustestaus tehtiin sekä siten, että sovelluksen käynnistyshakemistossa on ollut käyttöohjeen kuvauksen mukainen _config.properties_-tiedosto, ja ilman tiedostoa, jolloin ohjelma luo itse tiedoston.

### Toiminnallisuudet

Kaikki [vaatimusmäärittelyn](https://github.com/hajame/otm-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md#perusversion-tarjoama-toiminnallisuus) ja käyttöohjeen listaamat toiminnallisuudet on käyty läpi. Kaikkien toiminnallisuuksien yhteydessä on syötekentät yritetty täyttää myös virheellisillä arvoilla kuten tyhjillä.

## Sovellukseen jääneet laatuongelmat

Sovellus ei anna tällä hetkellä järkeviä virheilmoituksia, seuraavissa tilanteissa
- harjoituksen luonnissa jokin kenttä on syötetty väärässä formaatissa
