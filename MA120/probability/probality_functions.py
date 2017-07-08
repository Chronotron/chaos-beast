from fractions import Fraction

import math


class Probability(Fraction, object):
    def probability(self):
        return 1 - self.relative_probability()

    def relative_probability(self):
        return self.numerator / self.denominator


def combination(objects, selections):
    return math.factorial(objects) / (math.factorial(objects - selections) * math.factorial(selections))


def mean():
    pass


def probability(success, total):
    return Probability(success, total).probability()


def permutation(objects, selections):
    return math.factorial(objects) / math.factorial(objects - selections)


def relative_probability(success, total):
    return Probability(success, total).relative_probability()


def standard_deviation():
    pass
