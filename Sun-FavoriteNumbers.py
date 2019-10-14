# prompt user to enter fav numbers and then write into the file
# Coder: Wei-Shan Sun
dummy = True
while dummy == True:
    username = input("\nEnter your name: ")
    nums = input('What is your favorite number? ')

    with open("Sun-favoriteNumbers.txt", 'a') as file_ob:
        file_ob.write(username + '\t\t\t' + nums + '\n') 

    response = input('\nDo you want to continue? enter y (yes) or n (no) ')
    if response == 'y':
        dummy = True
    else:
        dummy = False
