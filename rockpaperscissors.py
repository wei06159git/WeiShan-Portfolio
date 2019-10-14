# Scissor-Paper-Rock Game
# Human vs. Computer
# Coder: Wei-Shan Sun
import random

mylist=['Rock', 'Paper', 'Scissors']

print("Let's play Rock-Paper-Scissors Game!!!")
print("There are 3 rounds\nLet's begin!!!")
print("======================================")

playerscore = 0
computerscore = 0
count = 0

while count < 3:
    print("Round ", count+1, " !!!")
    player1 =  input("Rock, Paper, Scissors: ")
    computer = random.choice(mylist)
    print("Computer's choice is: ", computer)
    if player1 == "Rock":
        if computer == "Paper":
            print ("computer wins!!!")
            computerscore = computerscore +1
        elif computer == "Scissors":
            print ("player1 win!!!")
            playerscore = playerscore+1
        elif computer == "Rock":
            print ("Tie!!! No one gets point!!!")
        else:
            print("Invalid input")
    elif player1 == "Paper":
        if computer == "Scissors":
            print("computer wins!!!")
            computerscore = computerscore +1
        elif computer == "Rock":
            print("player1 wins!!!")
            playerscore = playerscore+1
        elif computer == "Paper":
            print("Tie!!! No one gets point!!!")
        else:
            print("Invalid input")
    elif player1 == "Scissors":
        if computer == "Rock":
            print("computer wins!!!")
            computerscore = computerscore +1
        elif computer == "Paper":
            print("player1 wins!!!")
            playerscore = playerscore+1
        elif computer == "Scissors":
            print("Tie!!! No one gets point!!!")
        else:
            print("Invalid input")
    else:
        print("Invalid input")
    count= count +1
    print("============================")
    
print("============================")
print("Your score is:", playerscore)
print("Computer's score is: ", computerscore)

if playerscore > computerscore:
    print("You win!!!")
elif playerscore < computerscore:
    print("computer wins!!!")
else:
    print("Tie!!!")
