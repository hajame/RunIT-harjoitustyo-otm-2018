# OTM-harjoitustyö: RunIT

Harjoitustyö kurssille Ohjelmistotekniikan menetelmät.

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/hajame/otm-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/hajame/otm-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

## Komentorivitoiminnot

### Ohjelman suorittaminen

Ohjelman voi suorittaa kansiossa _runIT_ komennolla

```
mvn compile exec:java -Dexec.mainClass=runit.domain.Main
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
