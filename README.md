# OTM-harjoitustyö: runIT

Sovelluksen avulla käyttäjä voi pitää kirjaa juoksuharjoituksistaan. Sovellus tarjoaa käyttäjälle tilastotietoa harjoituksista yhteenvetonäkymässä. Sovellusta voi käyttää eri käyttäjätileillä, joilla on kaikilla omat harjoituksensa.

Ohjelma on harjoitustyö Helsingin Yliopiston kurssille Ohjelmistotekniikan menetelmät.

## Dokumentaatio

[Käyttöohje](https://github.com/hajame/otm-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/hajame/otm-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/hajame/otm-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Testausdokumentti](https://github.com/hajame/otm-harjoitustyo/blob/master/dokumentaatio/testaus.md)

[Työaikakirjanpito](https://github.com/hajame/otm-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)



## Releaset 
[runIT: Viikko7](https://github.com/hajame/otm-harjoitustyo/releases/tag/viikko7)

## Komentorivitoiminnot

### Ohjelman suorittaminen

Ohjelman voi suorittaa NetBeansin vihreällä napilla, tai terminaalissa kansiossa _runIT_:

```
mvn compile exec:java -Dexec.mainClass=runit.ui.GUI
```
Käynnissä olevan Dao-mallin toteutuksen vuoksi ohjelmaan ei voi vielä luoda uusia käyttäjiä tai harjoituksia. Voit kirjautua testikäyttäjänä tunnuksella _test_ ja salasanalla _pass_. Lisäksi voit tulostaa listan harjoituksista.

### Suoritettavan jarin generointi

```
mvn package
```

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn test jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Kansiossa _runIT_ komennolla 

```
mvn package
```
generoi hakemistoon target suoritettavan jar-tiedoston runIT-1.0-SNAPSHOT.jar

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/hajame/otm-harjoitustyo/blob/master/runIT/checkstyle.xml) määritellyt tarkistukset suoritetaan komennolla

```
mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_
