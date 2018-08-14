# NSAA Seed Predictor
![Screenshot](pictures/screenshot.png?raw=true "Screenshot")
(at the time of this screenshot the football season hasn't started yet, so the fields are unfilled)

This program was written for the football coach at my local highschool in Scottsbluff, Nebraska. It is useful because it lets you try to predict what the seed placements for the finals will be so you know which team(s) you will need to prepare against.

To use press on file in the top left and then click download. The program will freeze while it is downloading the csv files (multi threading is not implemented)
To change the outcome of a game, find the game you want to change in the top table and double 
click on the winner column, make your edit and then press enter. The entered winner must be spelled exactly how it is 
spelled in the program, caps doesn't matter.


## Build/Run Instructions:
**Linux**:

```
git clone https://github.com/abueide/jTox.git
cd jTox
./gradlew build
./gradlew run
```

**Windows**
```
git clone https://github.com/abueide/jTox.git
cd jTox
gradlew.bat build
gradlew.bat run
```

