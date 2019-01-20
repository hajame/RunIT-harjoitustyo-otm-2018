# runIT

> Ohjelma on harjoitustyö Helsingin Yliopiston kurssille Ohjelmistotekniikan menetelmät (OTM).

runIT:n avulla voi pitää kirjaa juoksuharjoituksistaan. Sovellus tarjoaa käyttäjälle tilastotietoa harjoituksista yhteenvetonäkymässä. Sovellusta voi käyttää eri käyttäjätileillä, joilla on kaikilla omat harjoituksensa.

Ohjelma tarjoaa mahdollisuuden tarkastella yksittäisen lenkin tietoja. Lisäksi ohjelma esittää käyttäjälle tilastotietoa kaikista hänen juoksemistaan lenkeistä.

## Dokumentaatio

- __[Vaatimusmäärittely](https://github.com/hajame/otm-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)__
- [Arkkitehtuurikuvaus](https://github.com/hajame/otm-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)
- [Testausdokumentti](https://github.com/hajame/otm-harjoitustyo/blob/master/dokumentaatio/testaus.md)
- [Käyttöohje](https://github.com/hajame/otm-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)
- [Työaikakirjanpito](https://github.com/hajame/otm-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)



## Releaset 
[Loppupalautus: runIT](https://github.com/hajame/otm-harjoitustyo/releases/tag/v1.0)

## Komentorivitoiminnot

### Ohjelman suorittaminen

Ohjelman voi suorittaa NetBeansin vihreällä napilla, tai terminaalissa kansiossa _runIT_:

```
mvn compile exec:java -Dexec.mainClass=runit.ui.GUI
```

### Suoritettavan jarin generointi

```
mvn package
```
generoi hakemistoon _target_ suoritettavan jar-tiedoston runIT-1.0-SNAPSHOT.jar

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
