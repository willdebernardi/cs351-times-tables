# Times Tables
This project is a visualization tool for a circular times table modulo operartion.
By: Will DeBernardi
For: CS 351-002 Project 1

## Usage
The program will first open with a blank circle without any lines drawn. From here, you can click the "Start" button in the upper left corner to begin the visualization, enter a specific number of points with the corresponding text field on the bottom, or click one of the buttons under the "Favorites" section to view a selection of my favorite images. The visualization can be started from any of the favorite locations.

Once the visualization is running, you can click the "Stop" button to pause the visualization at any time. To interact with the animation, you can alter the the frame update speed with the text field in the upper-right. The default value is 200,000,000 nanoseconds, or 200 milliseconds. This is best adjusted my changing the first few numbers. Additionally, you can move the "Increment size" slider" to adjust the increment value in real time. In addition to the number of points, you can type in a specified multiplier to jump to that specific section of the visualization. 

Finally, the colors of the lines can be changed using one of the 10 available buttons on the left side of the window.

## Bugs
- The increment slider is not effective retroactively (i.e reducing the increment size after an increase)
- At certain numbers of points, some of (usually just one) the favorites may not display correctly.
- Due to the implementation of color changing, the color will not apply if the visualization is paused, and only will once the visualization is resumed.
