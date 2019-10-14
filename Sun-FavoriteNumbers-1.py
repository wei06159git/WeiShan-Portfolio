#Read file and print the content
#Search a person's favorite number
#Coder: Wei-Shan Sun
with open("Sun-favoriteNumbers.txt") as file_ob:
        lines = file_ob.readlines()

for values in lines:
    print(values, end=' ')

name = input('Who\'s favorite number do you want to know?\n---> ')
# Find one's favorite number
for i in range(len(lines)):
    if name in lines[i]:
        dummy = lines[i].split('\t\t\t')
        number = dummy[1]
        number = number.rstrip('\n')
        print(name + '\'s favorite number is: ' + number)
