import sys

def class_boundary(args):
    return '{} - {}'.format(lower_bound(args[0]), upper_bound(args[1]))

def cumulative_frequency(args):
    def c_f(v, i, values):
        return  v + sum(values[:i])

    def f_f(calcs):
        return ', '.join(['{}'.format(p) for p in calcs])

    return frequency(args, c_f, f_f)

def lower_bound(str_lb):
    return int(str_lb) - 0.5

def frequency(args, calc_func, format_func):
    values = [float(v) for v in args]
    total = sum(values)
    calcs = [calc_func(values[i], i, values) for i in range(len(values))]
    results = format_func(calcs)
    return '{} : total {}'.format(results, total)

def midpoint(args):
    return (lower_bound(args[0]) + upper_bound(args[1])) / 2

def relative_frequency(args):
    def c_f(v, i, values):
        return  v / sum(values)

    def f_f(percents):
        return ', '.join(['{0:.2f}%'.format(p * 100) for p in percents])

    return frequency(args, c_f, f_f)

def upper_bound(str_ub):
    return int(str_ub) + 0.5


stat_functions = {
    'cb': class_boundary,
    'class-boundary': class_boundary,
    'cf': cumulative_frequency,
    'cumulative-frequency': cumulative_frequency,
    'rf': relative_frequency,
    'relative-frequency': relative_frequency,
    'mp': midpoint,
    'midpoint': midpoint,
}

if __name__ == '__main__':
    action = sys.argv[1]
    print(stat_functions[action](sys.argv[2:]))
