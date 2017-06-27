import sys

import math


# region Classes

class FrequencyChart(object):
    def __init__(self, args):
        self.groups = [FrequencyGroup(r) for r in args]

    def mean(self):
        def top_func(group):
            return group.frequency * group.midpoint()

        top = sum(top_func(g) for g in self.groups)
        bot = sum(g.frequency for g in self.groups)
        return top / bot

    def total(self):
        return sum(g.frequency for g in self.groups)

    def standard_deviation(self):
        def top_left(self):
            return self.total() * (sum(g.frequency * g.midpoint() ** 2 for g in self.groups))

        def top_right(self):
            return sum(g.frequency * g.midpoint() for g in self.groups) ** 2

        left_right = top_left(self) - top_right(self)
        bottom = self.total() * (self.total() - 1)
        result = math.sqrt(left_right / bottom)
        return result


class FrequencyGroup(object):
    def __init__(self, raw):
        rawVals = raw.split(',')
        self.frequency = float(rawVals[0])
        self.lower = float(rawVals[1])
        self.upper = float(rawVals[2])

    def midpoint(self):
        return midpoint([self.lower, self.upper])

    pass


# endregion

# region Functions

def lower_bound(value):
    return value - 0.5


def mean(values):
    return sum(values) / len(values)


def median_class(values):
    median_point = sum(values) / 2
    counter = 0.0
    for i in range(len(values)):
        counter += values[i]
        if counter >= median_point:
            return i
    return -1


def median_value(sum_of_all_classes, sum_of_preceding_classes, frequency_of_median_class):
    nx = (sum_of_all_classes + 1) / 2
    mx = sum_of_preceding_classes + 1
    return (nx - mx) / frequency_of_median_class


def midpoint(values):
    return (lower_bound(values[0]) + upper_bound(values[1])) / 2


def parse_freqeuncy_group(raw):
    return FrequencyGroup(raw)


def percentile_value(valuesLess, totalValues, parts=100):
    return valuesLess / totalValues * parts


def find_fractile(subscript, parts, samplesize):
    return math.ceil((subscript / parts) * samplesize)


def power_sum(values, power=2):
    values = [math.pow(v, power) for v in values]
    return sum(values)


def stat_range(values):
    values = sorted(values)
    low = values[0]
    hi = values[-1]
    return hi - low


def standard_deviation_comp(values):
    val_range = stat_range(values)

    def top_left():
        return val_range * sum(math.pow(x, 2) for x in values)

    def top_right():
        return math.pow(sum(values), 2)

    def bottom():
        return val_range * (val_range - 1)

    left_and_right = top_left() - top_right()

    return math.sqrt(left_and_right / bottom())


def pop_standard_deviation_comp(values):
    val_range = stat_range(values)
    _mu = mu(values)
    sum_x_power_2 = power_sum(values)
    left_side = sum_x_power_2 / val_range
    right_side = math.pow(_mu, 2)
    return math.sqrt(left_side - right_side)


def mu(values):
    val_range = _stat_range(values)
    sum_x = sum(values)
    return sum_x / val_range


def upper_bound(value):
    return value + 0.5


def variance(values):
    return math.pow(standard_deviation_comp(values), 2)


def variance_pop(values):
    return math.pow(pop_standard_deviation_comp(values), 2)


def z_score(value, mean_value, sd):
    return (value - mean_value) / sd

# endregion
