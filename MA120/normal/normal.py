import math

from statistics.statistical_functions import z_score


class Normal():
    def __init__(self, mean=0.0, standard_d=0.0):
        self.mean = mean
        self.standard_d = standard_d

    def x(self, z=0.0):
        return self.mean + (z * self.standard_d)

    def z(self, x=0.0):
        return z_score(x, self.mean, self.standard_d)

    def z_sample(self, x=0.0, n=1):
        return z_score(x, self.mean, self.stand_d_sample(n))
        pass

    def stand_d_sample(self, n):
        return self.standard_d / math.sqrt(n)


class NormalTrial(Normal):
    def __init__(self, n=1.0, p=1.0):
        Normal.__init__(self, n * p, math.sqrt(n * p * (1.0 - p)))


# n - fixed number of trials
# x - the specific number of successes in n trials
# p - probability of success in one of n trials
# q - probability of failure in one of n trials


class Proportion():
    def __init__(self, proportion, fraction):
        self.proportion = proportion
        self.fraction = fraction

    def proportional_probability(self):
        return self.proportion * self.fraction