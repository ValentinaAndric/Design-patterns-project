## Opis zahteva za projekat iz predmeta Dizajnerski zadaci za 2019/2020
Korišćenjem *Java Swing* implementirati desktop aplikaciju za rad sa 2D grafikom. Aplikacija mora podržavati funkcionalnosti koje su rađene u projektnom zadatku na predmetu Objektno orijentisane informacione tehnologije.
Izmene/Dodatne funkcionalnosti:
1. crtanje oblika različitim bojama (boja ivice i boja unutrašnjosti) za šta je potrebno koristiti *JColorChooser* klasu,
2. unutrašnjost oblika krug sa rupom treba da bude transparentna (*java.awt.Graphics2D*, *java.awt.Shape*, *java.awt.geom.Area*, *java.awt.geom.Ellipse2D*),
3. nazivi klasa, metoda i promenljivih moraju biti na engleskom jeziku,
4. aplikacija mora biti realizovan u skladu sa MVC arhitektonskim obrascem,
5. dodavanje, brisanje i modifikacija šestougla (*hexagon*) koristeći Adapter obrazac za hexagon.jar,
6. poništavanje izvršenih komandi (*undo* funkcionalnost) – *Command* i *Prototype* obrazac, ponovno izvršenje poništenih komandi (*redo* funkcionalnost) – *Command* i *Prototype* obrazac, *undo* i *redo* dugme treba da budu dostupni samo kada je dostupna i funkcionalnost,
7. generisanje i prikaz loga izvršenih komandi u okviru aplikacije,
8. zapis u tekstualnu datoteku loga izvršenih komandi na eksterni memorijski medijum, zapis kompletnog crteža (*Serialization*) na eksterni memorijski medijum, - *Strategy* obrazac,
9. učitavanje tekstualne datoteke koja sadrži log izvršenih komandi i na osnovu sadržaja, kreiranje odgovarajućeg crteža, komandu po komandu u interakciji sa korisnikom, učitavanje kompletnog crteža,
10. promenu pozicije oblika po Z osi, *To Front* (pozicija po pozicija), *To Back* (pozicija po pozicija), *Bring To Front* (na najvišu poziciju), *Bring To Back* (na najnižu poziciju),
11. prikazati trenutno aktivne boje za crtanje ivice i popunjavanje oblika, klikom na boju, otvara se dijalog sa mogućnošću promene trenutno aktivne boje,
12. omogućiti selekciju više oblika,
13. dugme za brisanje treba da bude dostupno samo u slučaju da postoje selektovani objekti – *Observer* obrazac,
14. dugme za modifikaciju treba da bude dostupan samo u slučaju kada je selektovan samo jedan oblik – *Observer* obrazac.

### Dodatna objašnjenja
Predmetni projekat **nije predviđen kao grupni rad**, tako da svaki student treba projekat da uradi u potpunosti samostalno. Jedan projekat može da brani samo jedan student. **U slučaju da projekat ili neki njegov deo bude prepoznat kao preuzet iz drugog projekta za posledicu će imati neuspešnu odbranu.**

Tokom rada na projektu obavezno je korišćenje *GitHub Classroom*-a, što podrazumeva da je **obavezno da svaki student u svoj repozitorijum postavi verziju aplikacije sa kojom je položio predmet Objektno orijentisane informacione tehnologije kao početnu tačku** i potom se očekuje da kako uspešno uradite ili pokušate da uradite neki deo zadatka, to i *commit*-ujete u vaš repozitorijum.  Takođe, obavezno je unošenje i poruke koja objašnjava promene nastele u konkretnom *commit*-u. Dakle, neophodno je da tok izrade projekta bude vidljiv kroz *commit*-e. Projekti koji ne ispune ovaj uslov (npr. samo jedan ili par *commit*-a, svi *commit*-i u kratkom vremenskom periodu jedan za drugim) **neće biti prihvaćeni za odbranu**.

Kod MVC obrasca, kao što je pokazivano na vežbama, model je klasa u kojoj je lista oblika koji se iscrtavaju, a *view* je *JPanel* u kojem se ti oblici iscrtavaju. Takođe, na vežbama je pokazan jedan način kako se *model*, *view* i *controller* mogu međusobno referencirati, i koja klasa bi trebalo da ima referencu na koju od klasa iz ovog obrasca. Rešenje u kojem MVC klase kreirate koristeći *Singleton* dizajnerski obrazac, te koristite globalne promenljive za referenciranje instanci klasa **nije prihvatljivo**.

### Pojašnjenja funkcionisanja aplikacije:

- sve aktivnosti korisnika vezano za crtanje treba da budu sačuvane u log-u, to podrazumeva i logovanje selekcije, kao i *To Front*, *To Back*, *Bring To Front* i *Bring To Back* funkcionalnosti, *undo*  i *redo*.

- selektovani oblik, nakon modifikacije, treba da ostane selektovan.

- ukoliko se nakon *undo* funkcionalnosti izvrši neka nova funkcionalnost (nacrta novi oblik, modifikuje postojeći…), *redo* funkcionalnost ne bi trebalo da bude dostupna.
