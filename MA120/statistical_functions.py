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
        return _midpoint([self.lower, self.upper])

    pass


# endregion

# region Functions

def cheb_theorem(args):
    deviations = float(args[0])
    return 1.0 - 1.0 / (deviations ** 2)


def class_boundary(args):
    return '{} - {}'.format(lower_bound(args[0]), upper_bound(args[1]))


def cumulative_frequency(args):
    def c_f(v, i, values):
        return v + sum(values[:i])

    def f_f(calcs):
        return ', '.join(['{}'.format(p) for p in calcs])

    return frequency(args, c_f, f_f)


def empirical_rule(args):
    mean_val = float(args[0])
    sd = float(args[1])
    group1 = '68% {} - {}'.format(mean_val - sd, mean_val + sd)
    group2 = '95% {} - {}'.format(mean_val - sd * 2, mean_val + sd * 2)
    group3 = '99.7% {} - {}'.format(mean_val - sd * 3, mean_val + sd * 3)
    return ', '.join([group1, group2, group3])


def percentile(args):
    return _find_fractile(float(args[0]), 100, float(args[1]))


def quartile(args):
    return _find_fractile(float(args[0]), 4, float(args[1]))


def percentile_value(args):
    return _percentile_value(float(args[0]), float(args[1]))


def grouped_standard_deviation(args):
    return FrequencyChart(args).standard_deviation()


def grouped_mean(args):
    return FrequencyChart(args).mean()


def isclose(a, b, rel_tol=1e-09, abs_tol=0.0):
    return abs(a - b) <= max(rel_tol * max(abs(a), abs(b)), abs_tol)


def lower_bound(str_lb):
    return _lower_bound(float(str_lb))


def frequency(args, calc_func, format_func):
    values = [float(v) for v in args]
    total = sum(values)
    calcs = [calc_func(values[i], i, values) for i in range(len(values))]
    results = format_func(calcs)
    return '{} : total {}'.format(results, total)


def mean(args):
    return _mean(_convert_args(args))


def median(args):
    values = _convert_args(args)
    length = len(values)
    odd = length % 2 != 0
    upper_median = (length / 2)
    if odd:
        return values[upper_median]
    else:
        lower_median = upper_median - 1
        low_val = values[lower_median]
        hi_val = values[upper_median]
        return _mean([low_val, hi_val])


def median_class(args):
    return "Group {}".format(_median_class(args) + 1)


def median_value(args):
    values = _convert_args(args, False)
    group = _median_class(args)
    return _median_value(sum(values), sum(values[:group]), values[group])


def midpoint(args):
    values = _convert_args(args, False)
    return _midpoint(values)


def midrange(args):
    values = _convert_args(args)
    low = values[0]
    hi = values[-1]
    return _mean([low, hi])


def mode(args):
    values = _convert_args(args)
    count_map = {}
    for val in values:
        key = str(val)
        if key not in count_map:
            count_map[key] = 1
        else:
            count_map[key] += 1

    mappings = []
    for key in count_map:
        mappings.append({'val': float(key), 'count': count_map[key]})

    mappings = sorted(mappings, key=lambda m: m['count'], reverse=True)
    modes = []
    mappings_length = len(mappings)
    mode_count = 0
    for i in range(mappings_length):
        map = mappings[i]
        modes.append(map)
        mode_count = map['count']
        if i + 1 >= mappings_length or mode_count != mappings[i + 1]['count']:
            break

    modal_type = 'Single'
    if len(modes) == 2:
        modal_type = "bimodal"
    elif len(modes) == mappings_length:
        modal_type = "no mode"
    elif len(modes) > 2:
        modal_type = "multimodal"

    modes_desc = ", ".join('val: {}'.format(m['val']) for m in modes)
    return 'TYPE: {}, COUNT: {} :: {}'.format(modal_type, mode_count, modes_desc)


def pop_standard_deviation_comp(args):
    values = _convert_args(args)
    return _pop_standard_deviation_comp(values)


def relative_frequency(args):
    def c_f(v, i, values):
        return v / sum(values)

    def f_f(percents):
        return ', '.join(['{0:.2f}%'.format(p * 100) for p in percents])

    return frequency(args, c_f, f_f)


def trimmed_mean(args):
    percent = float(args[0])
    values = _convert_args(args[1:])
    trim = int(round((percent * len(values)) / 100))
    return _mean(values[trim:-trim])


def upper_bound(str_ub):
    return _upper_bound(float(str_ub)) + 0.5


def standard_deviation_def(args):
    pass


def standard_deviation_comp(args):
    values = _convert_args(args)
    return _standard_deviation_comp(values)


def stat_range(args):
    values = _convert_args(args)
    return _stat_range(values)


def variance(args):
    values = _convert_args(args)
    return _variance(values)


def variance_pop(args):
    values = _convert_args(args)
    return _variance_pop(values)


def z_score(args):
    return _z_score(float(args[0]), float(args[1]), float(args[2]))


def z_score_pop(args):
    pass


# endregion

# region Helper Functions

def _convert_args(args, sort=True):
    values = [float(v) for v in args]
    if not sort:
        return values
    return sorted(values)


def _lower_bound(value):
    return value - 0.5


def _mean(values):
    return sum(values) / len(values)


def _median_class(args):
    values = _convert_args(args, False)
    median_point = sum(values) / 2
    counter = 0.0
    for i in range(len(values)):
        counter += values[i]
        if counter >= median_point:
            return i
    return -1


def _median_value(sum_of_all_classes, sum_of_preceding_classes, frequency_of_median_class):
    nx = (sum_of_all_classes + 1) / 2
    mx = sum_of_preceding_classes + 1
    return (nx - mx) / frequency_of_median_class


def _midpoint(values):
    return (_lower_bound(values[0]) + _upper_bound(values[1])) / 2


def _parse_freqeuncy_group(raw):
    return FrequencyGroup(raw)


def _percentile_value(valuesLess, totalValues, parts=100):
    return valuesLess / totalValues * parts


def _find_fractile(subscript, parts, samplesize):
    return math.ceil((subscript / parts) * samplesize)


def _power_sum(values, power=2):
    values = [math.pow(v, power) for v in values]
    return sum(values)


def _stat_range(values):
    values = sorted(values)
    low = values[0]
    hi = values[-1]
    return hi - low


def _standard_deviation_comp(values):
    val_range = _stat_range(values)

    def top_left():
        return val_range * sum(math.pow(x, 2) for x in values)

    def top_right():
        return math.pow(sum(values), 2)

    def bottom():
        return val_range * (val_range - 1)

    left_and_right = top_left() - top_right()

    return math.sqrt(left_and_right / bottom())


def _pop_standard_deviation_comp(values):
    val_range = _stat_range(values)
    mu = _mu(values)
    sum_x_power_2 = _power_sum(values)
    left_side = sum_x_power_2 / val_range
    right_side = math.pow(mu, 2)
    return math.sqrt(left_side - right_side)


def _mu(values):
    val_range = _stat_range(values)
    sum_x = sum(values)
    return sum_x / val_range


def _upper_bound(value):
    return value + 0.5


def _variance(values):
    return math.pow(_standard_deviation_comp(values), 2)


def _variance_pop(values):
    return math.pow(_pop_standard_deviation_comp(values), 2)


def _z_score(value, mean_value, sd):
    return (value - mean_value) / sd

# endregion
