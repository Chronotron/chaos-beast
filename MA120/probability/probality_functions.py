from fractions import Fraction

import math


class Probability(Fraction, object):

    def probability(self):
        return 1.0 - self.relative_probability()

    def relative_probability(self):
        return float(self.numerator) / float(self.denominator)


def probability(success, total):
    return Probability(success, total).probability()


def relative_probability(success, total):
    return Probability(success, total).relative_probability()


def combination(objects, selections):
    return math.factorial(objects) / (math.factorial(objects - selections) * math.factorial(selections))


def permutation(objects, selections):
    return math.factorial(objects) / math.factorial(objects - selections)


def add_probability(probabilities):
    fraction = sum(probabilities)
    return _from_fraction(fraction)


def subtract_probability(left, right):
    left_right = left - right
    return _from_fraction(left_right)


def _from_fraction(fraction):
    return Probability(fraction.numerator, fraction.denominator)
