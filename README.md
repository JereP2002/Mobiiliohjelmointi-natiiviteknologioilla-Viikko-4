# Week 3 – MVVM Task Manager (Jetpack Compose)

Tämä projekti on osa kurssia *Mobiiliohjelmointi natiiviteknologioilla*.  
Sovellus on toteutettu Androidille käyttäen **Jetpack Composea** ja **MVVM-arkkitehtuuria**.

## Sovelluksen idea
Sovellus on yksinkertainen tehtävienhallintaohjelma, jossa käyttäjä voi:
- lisätä uusia tehtäviä
- merkitä tehtävän tehdyksi
- avata tehtävän tiedot dialogissa
- muokata tehtävän nimeä
- poistaa tehtävän

Muutokset näkyvät käyttöliittymässä heti.

---

## MVVM-arkkitehtuuri

Sovelluksessa on käytetty MVVM-mallia:

### Model
Model sisältää `Task`-dataluokan, joka kuvaa yksittäistä tehtävää.

### View
View-kerros koostuu Jetpack Compose -komponenteista:
- `HomeScreen` näyttää tehtävälistan
- `DetailDialog` avautuu tehtävää painettaessa

View ei sisällä sovelluslogiikkaa, vaan ainoastaan näyttää dataa ja välittää käyttäjän toiminnot ViewModelille.

### ViewModel
ViewModel (`TaskViewModel`) hallitsee sovelluksen tilaa ja logiikkaa.
Se sisältää:
- tehtävälistan tilan
- funktiot: addTask, toggleDone, updateTask ja removeTask

---

## StateFlow
Sovelluksessa tilan hallintaan on käytetty **StateFlowta**.
View kuuntelee ViewModelin tilaa `collectAsState()`-funktion avulla.

Kun tehtävälista muuttuu ViewModelissa, Compose UI päivittyy automaattisesti ilman erillistä päivityskutsua.

---

## Teknologiat
- Kotlin
- Android Studio
- Jetpack Compose
- ViewModel
- StateFlow

---

## Demo
- YouTube-demo: (lisää linkki tähän)
- APK: Release-versio palautettu erikseen
