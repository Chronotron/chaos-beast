import sys
from probability import probality_functions


def combination(args):
    return probality_functions.combination(int(args[0]), int(args[1]))


def permutation(args):
    return probality_functions.permutation(int(args[0]), int(args[1]))


def probability(args):
    return probality_functions.probability(int(args[0]), int(args[1]))


def relative_probability(args):
    return probality_functions.relative_probability(int(args[0]), int(args[1]))


probability_functions = {
    'combination': combination,
    'permutation': permutation,
    'probability': probability,
    'relative-probability': relative_probability,
}

if __name__ == '__main__':
    action = sys.argv[1]
    print(probability_functions[action](sys.argv[2:]))
