import sys

import math


def class_boundary(args):
    return '{} - {}'.format(lower_bound(args[0]), upper_bound(args[1]))

def cumulative_frequency(args):
    def c_f(v, i, values):
        return  v + sum(values[:i])

    def f_f(calcs):
        return ', '.join(['{}'.format(p) for p in calcs])

    return frequency(args, c_f, f_f)

def isclose(a, b, rel_tol=1e-09, abs_tol=0.0):
    return abs(a-b) <= max(rel_tol * max(abs(a), abs(b)), abs_tol)

def lower_bound(str_lb):
    return int(str_lb) - 0.5

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
    return (lower_bound(args[0]) + upper_bound(args[1])) / 2

def midrange(args):
    values = _convert_args(args)
    low = values[0]
    hi = values[-1]
    return _mean([low, hi])

def mode(args):
    values = _convert_args(args)
    count_map = {}
    for val in values:
        if val not in count_map:
            count_map[val] = 1
        else:
            count_map[val] += 1

    mappings = []
    for key in count_map:
        mappings.append({'val': key, 'count': count_map[key]})

    mappings = sorted(mappings, key=lambda m : m['count'], reverse=True)
    modes = []
    mappings_length = len(mappings)
    mode_count = 0
    for i in range(mappings_length):
        map = mappings[i]
        modes.append(map)
        mode_count = map['count']
        if i >= mappings_length or mode_count != mappings[i + 1]['count']:
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

def relative_frequency(args):
    def c_f(v, i, values):
        return  v / sum(values)

    def f_f(percents):
        return ', '.join(['{0:.2f}%'.format(p * 100) for p in percents])

    return frequency(args, c_f, f_f)

def trimmed_mean(args):
    percent = float(args[0])
    values = _convert_args(args[1:])
    trim = int(round((percent * len(values)) / 100))
    return _mean(values[trim:-trim])

def upper_bound(str_ub):
    return int(str_ub) + 0.5

def standard_deviation_def(args):
    pass

def standard_deviation_comp(args):
    values = _convert_args(args)
    return _standard_deviation_comp(values)

def stat_range(args):
    values = _convert_args(args)
    return _stat_range(values)

def _convert_args(args, sort=True):
    values = [float(v) for v in args]
    if not sort:
        return values
    return sorted(values)

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
    sum_x = sum(values)
    sum_x_power_2 = _power_sum(values)
    denominator = val_range * (val_range - 1)
    left_side = val_range * sum_x_power_2
    right_side = math.pow(sum_x, 2)
    return math.sqrt((left_side - right_side) / denominator)

stat_functions = {
    'cb': class_boundary,
    'class-boundary': class_boundary,
    'cf': cumulative_frequency,
    'cumulative-frequency': cumulative_frequency,
    'mean': mean,
    'median': median,
    'median-class': median_class,
    'median-value': median_value,
    'mode': mode,
    'mp': midpoint,
    'midpoint': midpoint,
    'midrange': midrange,
    'range': stat_range,
    'rf': relative_frequency,
    'relative-frequency': relative_frequency,
    'sdc': standard_deviation_comp,
    'standard-deviation-comp': standard_deviation_comp,
    'trm': trimmed_mean,
    'trimmed-mean': trimmed_mean,
}

if __name__ == '__main__':
    action = sys.argv[1]
    print(stat_functions[action](sys.argv[2:]))
