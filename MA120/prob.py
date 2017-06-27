import sys
from probability import probality_functions

def probability(args):
    return probality_functions.probability(int(args[0]), int(args[1]))

probability_functions = {
    'probability': probability
}

if __name__ == '__main__':
    action = sys.argv[1]
    print(probability_functions[action](sys.argv[2:]))
