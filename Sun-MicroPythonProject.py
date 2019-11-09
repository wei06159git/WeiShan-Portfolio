# Project Author: Wei-Shan Sun
# Original Project: Using temperature sensor to detect the temperature and display the color.
# From blue to red color, it represents from low temperature to high temperature. Ex.if the color is toward red, it means the temp is high.
# In addition, it will display the message said "hot", "cold", or smiling face.
# Press A button to display the temp degree

from microbit import *
import music

# initialize a list "tune". Melody from Rachmaninoff's Symphony #2 movement3
tune=["C4:6", "E4:6", "G", "B","C5:6", "A4:9", "B3:6", "D4:6", "F4:6", "A4:6",
      "B", "G4:9", "A3:6", "C4:6", "E", "G", "A", "F4:10", "E4:5", "D4:8", "E4:5", "C4:16"]

#This function is to display the message and play a tune to notify if it is hot, okay or cold.
def degNColor(degC):
    if degC > 30:
        display.scroll("HOT!")
        redVal = degC*4
        blueVal = 0
        greenVal = 255 -(degC*4)
        pin8.write_analog(redVal)
        pin12.write_analog(greenVal)
        pin16.write_analog(blueVal)
        music.play(tune)
    elif degC < 30 and degC >15:
        display.show(Image.HAPPY)
        redVal = 85-degC
        blueVal = 85+degC
        greenVal = 85
        pin8.write_analog(redVal)
        pin12.write_analog(greenVal)
        pin16.write_analog(blueVal)
    elif degC <15:
        display.scroll("COLD!")
        redVal = 0
        blueVal = 255 - degC*2
        greenVal =degC*2
        pin8.write_analog(redVal)
        pin12.write_analog(greenVal)
        pin16.write_analog(blueVal)
        music.play(tune)
        
while True:

    rawTemp = pin1.read_analog()
    degC = (((rawTemp*3.3)/(1023))-0.5)*100

    degNColor(degC)
    
    sleep(2000)

    #Press A button to display temp
    if button_a.was_pressed():
        degC = int(degC)
        display.scroll(str(degC))
        sleep(1000)



