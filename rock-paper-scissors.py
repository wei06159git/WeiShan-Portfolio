# Scissor-Paper-Rock Game
# Human vs.Human
# Coder: Wei-Shan Sun
import stdiomask

player1 = stdiomask.getpass(prompt = "Rock, Paper, Scissors: ")
player2 = stdiomask.getpass(prompt = "Rock, Paper, Scissors: ")

if player1 == "Rock":
    if player2 == "Paper":
        print ("player2 wins!!!")
    elif player2 == "Scissors":
        print ("player1 win!!!")
    elif player2 == "Rock":
        print ("Tie!!!")
    else:
        print("Invalid input")
elif player1 == "Paper":
    if player2 == "Scissors":
        print("player2 wins!!!")
    elif player2 == "Rock":
        print("player1 wins!!!")
    elif player2 == "Paper":
        print("Tie!!!")
    else:
        print("Invalid input")
elif player1 == "Scissors":
    if player2 == "Rock":
        print("player2 wins!!!")
    elif player2 == "Paper":
        print("player1 wins!!!")
    elif player2 == "Scissors":
        print("Tie!!!")
    else:
        print("Invalid input")
else:
    print("Invalid input")

