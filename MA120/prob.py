import sys
from probability import probality_functions
from probability.probality_functions import Probability


def add_probability(args):
    probabilities = _convert_args_to_probabilities(args)
    pr = probality_functions.add_probability(probabilities)
    return '{} - {}'.format(pr, pr.relative_probability())


def combination(args):
    return probality_functions.combination(int(args[0]), int(args[1]))


def permutation(args):
    return probality_functions.permutation(int(args[0]), int(args[1]))


def probability(args):
    return probality_functions.probability(int(args[0]), int(args[1]))


def relative_probability(args):
    return probality_functions.relative_probability(int(args[0]), int(args[1]))


def subtract_probability(args):
    left = _convert_arg_to_probability(args[0])
    right = _convert_arg_to_probability(args[1])
    pr = probality_functions.subtract_probability(left, right)
    return '{} - {}'.format(pr, pr.relative_probability())


def _convert_args_to_probabilities(args):
    probabilities = []
    for arg in args:
        prob =_convert_arg_to_probability(arg)
        probabilities.append(prob)
    return probabilities


def _convert_arg_to_probability(arg):
    n, d = arg.split('/')
    return Probability(int(n), int(d))


probability_functions = {
    'combination': combination,
    'permutation': permutation,
    'addp': add_probability,
    'add-probability': add_probability,
    'probability': probability,
    'relative-probability': relative_probability,
    'subp': subtract_probability,
    'subtract-probability': subtract_probability,
}

if __name__ == '__main__':
    action = sys.argv[1]
    print(probability_functions[action](sys.argv[2:]))
