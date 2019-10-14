#Encryption Project
#Coder: Wei-Shan Sun

# Part 1: Substitution Cipher
print('Part 1: ------------------------------')
ciphertext = 'BKFTAZDAOWE'
message = ''

for value in ciphertext:
    number = ord(value)
    number = number +14
    if number > 90:
        number =number - 90
        number = number + 65 -1
    dummy = chr(number)
    message = message +dummy

print('What is the message from this this ciphertext "BKFTAZDAOWE"')
print('The secret message is: ', message)

# Part 2: Caesar Cipher
print('part 2:--------------------------------')
plaintext = 'thequickbrownfoxjumpsoverthelazydog'
encrypt = ''

for value in plaintext:
    number = ord(value)
    number = number +13 #shift 13
    if number > 122:
        number =number - 122
        number = number + 97 -1
    dummy = chr(number)
    encrypt  = encrypt  +dummy
    
print('The original message is: ', plaintext)
print('After the encryption, it is: ', encrypt)

print('part 3:--------------------------------')
cipher = 'dzeevjfkrlezkvuwffksrcctcls'

#function to decrypt 
def decrypt(cipher, shift):
    message = ''
    for value in cipher:
        number = ord(value)
        number = number - shift # shift back 
        if number < 97:
            num = 97 - number
            number = 122 - num +1
        dummy = chr(number)
        message = message + dummy
    return message

print('The encrypted text: ', cipher)

for i in range(1,26):
    print('THE SHIFT IS: ', i)
    print('After decrypting the text, it is: ', decrypt(cipher,i))
    
#Part4: Scrambled Key
print('part 4:--------------------------------')
alphabet ='abcdefghijklmnopqrstuvwxyz'
key = 'mwgpbdzxrylacsokjfhtnueivq'
plaintext = 'of shoes and ships and sealing wax of cabbages and kings'
dictionary = {'a':'m', 'b':'w', 'c': 'g', 'd':'p', 'e':'b', 'f':'d', 'g': 'z', 'h':'x','i':'r','j':'y', 'k':'l','l':'a','m':'c', 'n':'s', 'o':'o', 'p':'k','q':'j','r':'f', 's':'h','t':'t','u':'n','v':'u', 'w':'e','x':'i','y':'v','z':'q', ' ':' '}
ciphertext =''
for value in plaintext:
    dummy = dictionary[value]
    ciphertext = ciphertext + dummy
print('original text: ', plaintext)
print('Encrypted text: ', ciphertext)

#Part 5: Scrambled Key -continued
print('part 5:--------------------------------')
ciphertext = 'hzqftcqumfqfzxcxcdqscqhzqfmqfzxcxcdquxhzqmllqzxfqaxdzh'
plain = ''
for value in ciphertext:
    for text, name in dictionary.items():
        if name == value:
            plain = plain + text
print('Encrypted text: ', ciphertext)       
print('original text: ', plain)

#Part6: Make my own key and to encrypt the secret message
print('part 6:--------------------------------')
dictionary = {'a':'s', 'b':'t', 'c': 'u', 'd':'v', 'e':'w', 'f':'x', 'g': 'y', 'h':'z','i':'a','j':'b', 'k':'c','l':'d','m':'e', 'n':'f', 'o':'g', 'p':'h','q':'i','r':'j', 's':'k','t':'l','u':'m','v':'n', 'w':'o','x':'p','y':'q','z':'r', ' ':' '}
def decryptText(cipher):
    plain = ''
    for value in cipher:
        for text, name in dictionary.items():
            if name == value:
                plain = plain + text
    return plain
cipher='a dgnw usl'
text = decryptText(cipher)
print('Here is the encrypted message: ', cipher)
print('After decrypting, it is: ', text)
    
