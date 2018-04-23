# OTM-harjoitustyö: runIT

Harjoitustyö kurssille Ohjelmistotekniikan menetelmät.

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/hajame/otm-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/hajame/otm-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

[Arkkitehtuurikuvaus](https://github.com/hajame/otm-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

## Releaset 
[runIT: Viikko5](https://github.com/hajame/otm-harjoitustyo/releases/tag/viikko5)

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

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/hajame/otm-harjoitustyo/blob/master/runIT/checkstyle.xml) määritellyt tarkistukset suoritetaan komennolla

```
mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_
