Week 4 – Navigointi Jetpack Composessa (ToDo App)
Projektin kuvaus

Tässä projektissa laajensin Week3 ToDo -sovellusta lisäämällä Jetpack Compose Navigation -navigoinnin. Sovellus käyttää single-activity-arkkitehtuuria, jossa kaikki näkymät ovat Composable-funktioita ja navigointi hoidetaan NavControllerin avulla.

Sovelluksessa on kolme pääsivua:

HomeScreen – tehtävälista

CalendarScreen – tehtävät kalenterimaisessa näkymässä

SettingsScreen – asetukset (dummy + teeman vaihto)

Tehtävien lisäys ja muokkaus tehdään AlertDialogilla, ei erillisillä navigointisivuilla.

Navigointi Jetpack Composessa

Jetpack Compose Navigation mahdollistaa näkymien välisen siirtymisen ilman useita activityjä. Kaikki näkymät sijaitsevat saman activityn sisällä ja navigointi tapahtuu reittien (routes) avulla.

Navigoinnissa käytetään:

NavController → ohjaa siirtymiä

NavHost → määrittelee kaikki navigoitavat näkymät

route → yksilöllinen tunniste jokaiselle screenille

Reitit (Routes)

Reitit on määritelty vakioina:

const val ROUTE_HOME = "home"
const val ROUTE_CALENDAR = "calendar"
const val ROUTE_SETTINGS = "settings"

NavHost ja NavController

NavHost on määritelty MainActivityn setContent-lohkoon.

val navController = rememberNavController()

NavHost(
    navController = navController,
    startDestination = ROUTE_HOME
) {
    composable(ROUTE_HOME) {
        HomeScreen(navController, viewModel)
    }
    composable(ROUTE_CALENDAR) {
        CalendarScreen(navController, viewModel)
    }
    composable(ROUTE_SETTINGS) {
        SettingsScreen()
    }
}

Navigaatiorakenne (Home ↔ Calendar)

Navigointi HomeScreenin ja CalendarScreenin välillä on toteutettu käyttöliittymän painikkeilla.

Vaihtoehtoina käytetty:

TopAppBar toimintopainike

tai BottomNavigation kahdella tabilla

Navigointi:

navController.navigate(ROUTE_CALENDAR)


Takaisin:

navController.popBackStack()


Järjestelmän back-nappi toimii automaattisesti.

MVVM + jaettu ViewModel

Sovellus käyttää MVVM-arkkitehtuuria.

TaskViewModel sisältää tehtävätilan

Sama ViewModel jaetaan kaikille screeneille

ViewModel ei luoda uudelleen navigoinnin aikana

ViewModel luodaan NavHost-tasolla:

val viewModel: TaskViewModel = viewModel()


Molemmat näkymät (Home ja Calendar):

lukevat tilan ViewModelista

käyttävät samoja funktioita:

addTask

updateTask

removeTask

toggleDone

Kun tehtävää muokataan toisessa näkymässä, muutos näkyy heti toisessa.

CalendarScreen – kalenterimainen näkymä

CalendarScreen näyttää tehtävät kalenterimaisesti ryhmiteltynä päivämäärän mukaan.

Toteutus:

tehtävät ryhmitellään dueDate-kentän perusteella

jokaiselle päivälle näytetään otsikko

otsikon alle listataan päivän tehtävät

Esimerkki:

2026-02-08
- Osta maito
- Tee raportti

2026-02-09
- Treeni


Kalenterin kautta käyttäjä voi:

nähdä tehtävät päivittäin

avata editointidialogin

poistaa tehtävän

AlertDialog – addTask ja editTask

Tehtävien lisäys ja muokkaus toteutetaan AlertDialogilla, ei navigointisivuilla.

addTask

Avautuu + painikkeesta.

Sisältää:

title-kenttä

description-kenttä (valinnainen)

dueDate-kenttä

Napin toiminnot:

Tallenna → viewModel.addTask(...)

Peruuta → dialog sulkeutuu

editTask

Avautuu kun käyttäjä painaa tehtävää listassa tai kalenterissa.

Kentät ovat valmiiksi täytetty.

Napin toiminnot:

Tallenna → viewModel.updateTask(...)

Poista → viewModel.removeTask(...)

Peruuta → ei muutoksia

Lisäys ja editointi eivät ole omia navigointikohteita — ne ovat UI-dialogeja.

Riippuvuudet

Navigation Compose lisätty:

implementation("androidx.navigation:navigation-compose:<versio>")

Palautus

Palautukseen sisältyy:

GitHub

Repo sisältää projektin

Git tag: week4

git tag week4
git push origin week4
