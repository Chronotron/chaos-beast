from statistical_functions import *

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
