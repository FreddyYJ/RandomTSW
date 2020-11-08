# RandomTrainSimWorld
#### This project is abandoned, move to RandomTrainSimWorld2.
It will pick random route and locomotive from Train Sim World. It can help to select routes, locomotives, or weathers.
Select randomly route, locomotive, weather, or all.\
Or you can see which route/locomotive DLC includes.
* Javadocs: https://freddyyj.github.io/RandomTrainSimWorld/

# How to install
1. Install JDK 13 or up: https://www.oracle.com/java/technologies/javase-downloads.html
2. Install JavaFX 11 or up: https://gluonhq.com/products/javafx/
3. Run command: ```java -jar (File name).jar```
* Currently Maven Jlink is not released. If Jlink plugin is released, I will update for withput JDK and JavaFX installation.

# Changelog
v0.11.2
* Add new routes 'Hauptstrecke München-Augsburg', 'Rhein-Ruhr Osten', 'Oakville Subdivision', and 'West Somerset Railway' for TSW2
* Add new locomotives 'DB BR 185.2', '146.2', '423', '182', '143', '155', '766.2 DBpbzfa', '403 ICE3', '425', and 'MRCE BR 185.5' at Hauptstrecke München-Augsburg
* Add new locomotives 'DB BR 185.2', '146.2', '422', '182', '143', '155', and 'MRCE BR 185.5' at Rhein-Ruhr Osten
* Add new locomotives 'CN GP9RM' and 'CN GP38-2' at Oakville Subdivision
* Add new locomotives 'BR Class 08 BLU', 'BR Class 20 RF', 'GRN Class 09', 'GRN Class 47', 'GRN Class 33', and 'MAR Class 52 'Western'' at West Somerset Railway
* Add new locomotive 'DB BR 204' at Main-Spessart Bahn

v0.11.1
* Add new routes 'Isle of Wight (TSW2)'
* Add new locomotive 'Isle of Wight Class 483' at Isle of Wight (TSW2)'

v0.11.0
* Add new routes 'Main-Spessart Bahn (TSW2)' and 'Rapid Transit (TSW2)'
* Add new locomotives 'DB BR 185.2', 'DB BR 146.2', 'DB BR 766.2 DBpbzfa' and 'DB BR 143' at Schnellfahrstrecke Köln (TSW2)
* Add new locomotives 'DB BR 185.2', 'DB BR 146.2', 'DB BR 766.2 DBpbzfa', 'DB BR 182', 'DB BR 143' and 'DB BR 155' at HauptStrecke Rhein-Ruhr (TSW2)
* Add new locomotives 'DB BR 182' at Ruhr-Sieg Nord (TSW2)
* Change locomotive name 'DB BR 767.2 DABpbzfa' to 'DB BR 766.2 DBpbzfa' at Ruhr-Sieg Nord (TSW2)
* Change locomotive name 'DB BR 1442 'Talent 2'' to 'S-Bahn BR 1442 'Talent 2'' at Rapid Transit

v0.10.2
* Update Javadocs
* Change gui.Main.controller to private
* Fix issue at random loco after random all/TSW2/TSW2020

v0.10.1
* Fix issue when random pick for 'Tees Valley Line (TSW2)', 'Long Island RailRoad (TSW2)' and 'Ruhr-Sieg Nord (TSW2)'

v0.10.0
* Add new routes 'Tees Valley Line (TSW2)', 'Long Island RailRoad (TSW2)' and 'Ruhr-Sieg Nord (TSW2)'
* Add new locomotives 'BR Class 101 BLG', 'BR Class 37/5 RF', 'BR Class 08 BLU', 'BR Class 31/1 BLU' and 'BR Class 20 RF' at Tees Valley Line (TSW2)
* Add new locomotives 'M7 LIRR' and 'M3 LIRR' at Long Island RailRoad (TSW2)
* Add new locomotives 'DB BR 767.2 DABpbzfa', DB BR 185.2', 'DB BR 143' and 'DB BR 155' at Ruhr-Sieg Nord (TSW2)

v0.9.2
* Update to allow custom Random class for custom random algorithm
* Add javadocs under docs directory
* Javadocs link to OpenJFX is not work correctly. It's about OpenJFX javadocs, I'll work to fix this issue.
* Add TSW icon

v0.9.1
* Add buttons 'Pick TSW2020' and 'Pick TSW2' to pick all from each game
* Update fx:id of TSW2 routes and locos to ends with 'Tsw2'

v0.9.0: Logic Update
* Almost of sequences and data flows are changed
* Now uncheck everything and pick random will throw exception

v0.8.0: TSW2 Update
* Add new Routes for Train Sim World 2: Sand Patch Grade, Schnellfahrstrecke Köln, London Underground Bakerloo, Haupstrecke Rhein-Ruhr, East Coastway, Peninsula Corridor
* New TSW2 routes have (TSW2) after route name
* Add new locos at Sand Patch Grade (TSW2): GP38-2, SD40-2, AC4400CW
* Add new locos at Schnellfahrstrecke Köln (TSW2): BR 442, BR 406 ICE3M
* Add new locos at London Underground Bakerloo (TSW2): 1972 Mk.II Tube Stock
* Add new locos at Haupstrecke Rhein-Ruhr (TSW2): BR 425, BR 422
* Add new locos at East Coastway (TSW2): BR Class 377/4, BR Class 66
* Add new locos at Peninsula Corridor (TSW2): F40PH-2CAT, Gallery Cab Car, GP38-2, MP36PH-3C, Bi-Level Cab Car, MP15DC
* Change logic at onRandomRoute, onRandomLoco function in gui.MainController

v0.7.0
* Add new loco 'BR Class 20' at Tees Valley Line and West Somerset Railway
* Now Warning alert is popped up when closing window
* Add shortcut for saving(Ctrl+S)
* Update to change locomotive list after 'Pick Route' or 'Random All', Now you can 'Pick Locomotive' right after

v0.6.3
* Add new loco 'M3 LIRR' in Long Island Railroad

v0.6.2
* Add new loco 'DB BR 204' in Main-Spessart Bahn

v0.6.1
* Fix 3 'DB BR 146.2' in HauptStrecke Rhein-Ruhr

v0.6.0
* Add new DLC 'HauptStrecke Rhein-Ruhr'
* Add new route 'HauptStrecke Rhein-Ruhr'
* Add new loco 'DB BR 425' in HauptStrecke Rhein-Ruhr
* Add 7 locos 'DB BR 185.2/146.2/422/182/143/155/766.2 DBpbzfa' in HauptStrecke Rhein-Ruhr, so 8 locos in this route
* Now you don't have to delete save file to update, new route and locos will be added in your save file automaically

v0.5.3
* Add new DLC 'MP15DC Diesel Switcher'
* Add new loco 'MP15DC CT' in Peninsula Corridor

v0.5.2
* Add new DLC 'Oakville Subdivision'
* Add new route 'Oakville Subdivision'
* Add new loco 'EMD GP38-2 CN' in Oakville Subdivision
* Add new loco 'EMD GP9RM CN' in Oakville Subdivision
* Update Java version to 13

v0.5.1
* Change 'MP36PH-3C CT' to 'MP36PH-3C CT "Baby Bullet"'

v0.5.0
* Add new DLC 'MP36PH-3C Baby Bullet'
* Add new loco 'MP36PH-3C CT' in Peninsula Corridor
* Add new loco(cab car) 'Bi-Level Cab Car' in Peninsula Corridor

v0.4.0
* Add menubar for more functions
* Save/Load save files manually
* You can now save/load multiple save files
* Config file at documents directory(Delete before config file: randomtsw.json in your document!)
* Resolve error when after start program and click locos
* Resolve error at loading invalid config
* Update JavaFX version to 13

v0.3.1
* Remove version warning for JavaFX
* Remove standard output when closing program

v0.3.0
* Add East Coastway Routes
* Add BR Class 377/4, 66, 47/4, 45/1, 31/1, 37/5, 40 in East Coastway route
* Now you can uncheck routes, locomotives, weathers that you don't want to pick
* Now it auto-save your current check when closing it
* Now it load auto-saved file when starting it
* Change locomotive name 'BR Class 66' to 'BR Class 66 EWS/DBS'

v0.2.1
* Add BR Class 31 to Tees Valley Line and West Somerset Railway

v0.2.0
* Add GUI
* Pick random all, random locos, random routes, or random weather
* Checkboxs don't work

v0.1.0
* Init version
