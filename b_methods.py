#list method
    #A list method can perform a useful operation on a list such as adding or removing elements, sorting, reversing, etc.

#Can you locate list methods in the online Python documentation?
print('Use this link to python documentation about list method: https://docs.python.org/3/tutorial/datastructures.html')

###Challenge 1###

# Sort short_names in reverse alphabetic order.

# Sample output with input: 'Jan Sam Ann Joe Tod'
# ['Tod', 'Sam', 'Joe', 'Jan', 'Ann']

# ''' Your solution goes here '''
user_input = input('please enter name: ')
short_names = user_input.split()
short_names.reverse()
print(short_names)