from statistics import statistical_functions


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
    return statistical_functions.find_fractile(float(args[0]), 100, float(args[1]))


def quartile(args):
    return statistical_functions.find_fractile(float(args[0]), 4, float(args[1]))


def percentile_value(args):
    return statistical_functions.percentile_value(float(args[0]), float(args[1]))


def grouped_standard_deviation(args):
    return FrequencyChart(args).standard_deviation()


def grouped_mean(args):
    return FrequencyChart(args).mean()


def isclose(a, b, rel_tol=1e-09, abs_tol=0.0):
    return abs(a - b) <= max(rel_tol * max(abs(a), abs(b)), abs_tol)


def lower_bound(str_lb):
    return statistical_functions.lower_bound(float(str_lb))


def frequency(args, calc_func, format_func):
    values = [float(v) for v in args]
    total = sum(values)
    calcs = [calc_func(values[i], i, values) for i in range(len(values))]
    results = format_func(calcs)
    return '{} : total {}'.format(results, total)


def mean(args):
    return statistical_functions.mean(_convert_args(args))


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
        return statistical_functions.mean([low_val, hi_val])


def median_class(args):
    return "Group {}".format(statistical_functions.median_class(_convert_args(args)) + 1)


def median_value(args):
    values = _convert_args(args, False)
    group = statistical_functions.median_class(_convert_args(args))
    return statistical_functions.median_value(sum(values), sum(values[:group]), values[group])


def midpoint(args):
    values = _convert_args(args, False)
    return statistical_functions.midpoint(values)


def midrange(args):
    values = _convert_args(args)
    low = values[0]
    hi = values[-1]
    return statistical_functions.mean([low, hi])


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
    return statistical_functions.pop_standard_deviation_comp(values)


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
    return statistical_functions.mean(values[trim:-trim])


def upper_bound(str_ub):
    return statistical_functions.upper_bound(float(str_ub)) + 0.5


def standard_deviation_def(args):
    pass


def standard_deviation_comp(args):
    values = _convert_args(args)
    return statistical_functions.standard_deviation_comp(values)


def stat_range(args):
    values = _convert_args(args)
    return statistical_functions.stat_range(values)


def variance(args):
    values = _convert_args(args)
    return statistical_functions.variance(values)


def variance_pop(args):
    values = _convert_args(args)
    return statistical_functions.variance_pop(values)


def z_score(args):
    return statistical_functions.z_score(float(args[0]), float(args[1]), float(args[2]))


def z_score_pop(args):
    pass


def _convert_args(args, sort=True):
    values = [float(v) for v in args]
    if not sort:
        return values
    return sorted(values)


# endregion

stat_functions = {
    'cb': class_boundary,
    'class-boundary': class_boundary,
    'cf': cumulative_frequency,
    'cheb-theorem': cheb_theorem,
    'cumulative-frequency': cumulative_frequency,
    'empirical-rule': empirical_rule,
    'gmean': grouped_mean,
    'grouped-mean': grouped_mean,
    'gsd': grouped_standard_deviation,
    'grouped-standard-deviation': grouped_standard_deviation,
    'mean': mean,
    'median': median,
    'median-class': median_class,
    'median-value': median_value,
    'mode': mode,
    'mp': midpoint,
    'midpoint': midpoint,
    'midrange': midrange,
    'percentile': percentile,
    'percentile-value': percentile_value,
    'psdc': pop_standard_deviation_comp,
    'pop-standard-deviation-comp': pop_standard_deviation_comp,
    'quartile': quartile,
    'range': stat_range,
    'rf': relative_frequency,
    'relative-frequency': relative_frequency,
    'sdc': standard_deviation_comp,
    'standard-deviation-comp': standard_deviation_comp,
    'trm': trimmed_mean,
    'trimmed-mean': trimmed_mean,
    'var': variance,
    'variance': variance,
    'varp': variance_pop,
    'variance-pop': variance_pop,
    'z-score': z_score,
}

if __name__ == '__main__':
    action = sys.argv[1]
    print(stat_functions[action](sys.argv[2:]))
