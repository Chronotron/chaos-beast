from fractions import Fraction

import math


class Probability(Fraction, object):

    def probability(self):
        return 1 - self.relative_probability()

    def relative_probability(self):
        return self.numerator / self.denominator


def probability(success, total):
    return Probability(success, total).probability()


def relative_probability(success, total):
    return Probability(success, total).relative_probability()


def combination(objects, selections):
    return math.factorial(objects) / (math.factorial(objects - selections) * math.factorial(selections))


def permutation(objects, selections):
    return math.factorial(objects) / math.factorial(objects - selections)
