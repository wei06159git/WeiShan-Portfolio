# String Method
# Coder: Wei-Shan Sun
#list method
    #A list method can perform a useful operation on a list such as adding or removing elements, sorting, reversing, etc.

# Sort short_names in reverse alphabetic order.

# Sample output with input: 'Jan Sam Ann Joe Tod'
# ['Tod', 'Sam', 'Joe', 'Jan', 'Ann']

# ''' Your solution goes here '''
user_input = input('please enter name: ')
short_names = user_input.split()
short_names.reverse()
print(short_names)
