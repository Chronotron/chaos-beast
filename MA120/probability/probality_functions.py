from fractions import Fraction

import math


class Probability(Fraction, object):
    def probability(self):
        return 1 - self.relative_probability()

    def relative_probability(self):
        return self.numerator / self.denominator


def combination(objects, selections):
    return math.factorial(objects) / (math.factorial(objects - selections) * math.factorial(selections))

def expected_value(cost=0.0, prize=0.0, success=0.0):
    failure = 1.0 - success
    failureCost = cost * failure
    netProfit = prize - cost
    successCost = netProfit * success
    return  failureCost + successCost


def independent_probability(chance, trials):
    currentChance = chance
    for i in range(trials - 1):
        currentChance = currentChance * chance

    return currentChance

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
