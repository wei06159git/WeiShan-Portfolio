# Pwassword Manager
# Coder: Wei-Shan Sun

import sys
import random

#dictionary comes from this site "http://svnweb.freebsd.org/csrg/share/dict/words?view=co&content-type=text/plain"
#Dictionary source credited to: stackoverflow.com/questions/18834636/random-word-generator-python
import urllib.request

import stdiomask # input password with *

Userdictionary = {'gmail': 'wejkh32j548vn', 'amazon': '1238fb834n'} # Dictionary to store user's passwords
menuLoop = True #Looping the menu until the users quit 
masterpassword = 'ILoveCat<3(*+*)' # master password for encrypting or decrypting password

#Create a password followed by XKCD model
def PassGenerate():
   
    web = "http://svnweb.freebsd.org/csrg/share/dict/words?view=co&content-type=text/plain"
    dictionary = urllib.request.urlopen(web)
    dictdummy = dictionary.read().decode()
    ddict = dictdummy.splitlines()
    
    wordNum1 = 0
    xkcdPass1 = ''

    while wordNum1 < 4:
        dummy = random.choice(ddict)
        if wordNum1 == 0:
            dummy = dummy.capitalize()
        elif wordNum1 == 1:
            xkcdPass1 = xkcdPass1 + '@#$%'
        elif wordNum1 == 2:
            xkcdPass1 = xkcdPass1 + '168'
        xkcdPass1 = xkcdPass1 + dummy
        wordNum1 +=1

    return xkcdPass1

def encrypt(password):
    cyphertext = ''
    shift = 3
    for value in password:
        number = ord(value)
        number = number + shift
        dummy = chr(number)
        cyphertext = cyphertext +dummy
    return cyphertext
def decrypt(key):
    plain=''
    shift = 3
    for value in key:
        number = ord(value)
        number = number - shift
        dummy = chr(number)
        plain = plain +dummy
    return plain

#Login --- only allow three times password entries
def login():
    dummy = True
    count = 0
    while dummy == True:
        masterUser = stdiomask.getpass(prompt = 'Enter your Master Password: ')
        if masterUser != masterpassword:
            if count < 2:
                print('The password you enter is not correct! Please try again!')
                count +=1
            elif count ==2:
                print("Cannot Login, GoodBye!!!")
                sys.exit()
        else:
            print('Login Successfully!!!')
            dummy = False

login()

# User Menu 
print('Welcome to Password Manager!')

while menuLoop == True:
    print('-----------Menu-------------')
    print('(A) Store New Password to a new account')
    print('(B) Retrieve password from the existed account')
    print('(C) Generate a random passwords')
    print('(D) Quit')

    response = input('What would you like to do? Please enter A,B,C or D: ')
    if response == 'A':
        newPass = input('What is the password you want to store: ')
        accountName = input('What is the account name you want to store the password to: ')
        password = encrypt(newPass)
        Userdictionary.update({accountName:password})
        print('Successfully store a new password to ', accountName)

    elif response == 'B':
        accountName=input('Which account do you want to retrieve the password from: ')
        key = Userdictionary[accountName]
        password = decrypt(key)
        print('The password for ', accountName, ' is ', password)

    elif response == 'C':
        print('Generate a random passwords')
        generatedpass = PassGenerate()
        print('Here is the generated password: ', generatedpass)
    elif response == 'D':
        menuLoop = False

print('Thank you for using Password Manager!!!')




