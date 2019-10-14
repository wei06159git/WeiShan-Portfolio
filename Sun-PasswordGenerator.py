# Generate different kinds of password
#Coder: Wei-Shan Sun

import random

#dictionary comes from this site "http://svnweb.freebsd.org/csrg/share/dict/words?view=co&content-type=text/plain"
#Dictionary source credited to: stackoverflow.com/questions/18834636/random-word-generator-python
import urllib.request

#Create a traditional password using lowercase, UPPERCASE & numbers
print("Let's generate a traditional password that has 8 - 15 characters!!!")
length = random.randint(8,15)
count = 0
mylist = ['Lower', 'Upper', 'Number']

trapassword = ''

while count < length:
    dummy = random.choice(mylist)
    if dummy == 'Lower':
        lowerchar = random.randint(97,122)
        trapassword = trapassword + chr(lowerchar)
    elif dummy == 'Upper':
        upperchar = random.randint(65,90)
        trapassword = trapassword + chr(upperchar)
    elif dummy == 'Number':
        num = random.randint(48,57)
        trapassword = trapassword + chr(num)
    count +=1

print('Generated traditional password is: ',trapassword)
print('\n')

#Create a password followed by XKCD model
print("Let's use 4 words concatenated together to create a password!!!")

web = "http://svnweb.freebsd.org/csrg/share/dict/words?view=co&content-type=text/plain"
dictionary = urllib.request.urlopen(web)
dictdummy = dictionary.read().decode()
ddict = dictdummy.splitlines()
    
wordNum = 0
xkcdPass = ''
print('The 4 choices of words are:\n')
while wordNum < 4:
    dummy = random.choice(ddict)
    print(dummy,'\n')
    xkcdPass = xkcdPass + dummy
    wordNum +=1

print("A password followed by XKCD model is : ", xkcdPass )
print('\n')

#Advanced password from XKCD password
print('Let''s generate advanced XKCD password by replacing w/ numbers, uppercase and special characters!')
    
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
print(xkcdPass1, ' is the advanced password!!!')


#How many guesses will it take us to find the matching password which has 4 lower case letters

#declare a password
correctpass = 'root'

isTrue = False
guess = 0

while isTrue == False:
    guess+=1
    guesspass = ''
    fourLoop = 0
    while fourLoop < 4:
        lowerchar = random.randint(97,122)
        guesspass = guesspass + chr(lowerchar)
        fourLoop +=1
    if guesspass == correctpass:
        print(guesspass)
        isTrue = True
        break

print('\n')
print('It takes', guess, ' times for computer to guess the correct password')
print('This is not a good algorithm!!!')

#Optimized the above algorithm
isTrue = False
guess1 =0
guesspass = ''
#how many guess for 'r'
while isTrue == False:
    guess1+=1
    lowerchar = random.randint(97,122)
    if lowerchar == 114:
        guesspass = guesspass + chr(lowerchar)
        isTrue = True

isTrue = False
#how many guesses for 'o'
while isTrue == False:
    guess1+=1
    lowerchar = random.randint(97,122)
    if lowerchar == 111:
        guesspass = guesspass + chr(lowerchar)
        isTrue = True

isTrue = False
while isTrue == False:
    guess1+=1
    lowerchar = random.randint(97,122)
    if lowerchar == 111:
        guesspass = guesspass + chr(lowerchar)
        isTrue = True

#how many guesses for 't'
isTrue = False
while isTrue == False:
    guess1+=1
    lowerchar = random.randint(97,122)
    if lowerchar == 116:
        guesspass = guesspass + chr(lowerchar)
        isTrue = True

print('\n')
print('It takes', guess1, ' times for computer to guess the correct password')
print('This is a better algorithm!!!')    

