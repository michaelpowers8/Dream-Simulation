from random import randint
from os import system
from scipy.stats import binom
from decimal import Decimal
from pandas import DataFrame
from gc import disable,enable

def probability_n_or_more_pearls(n: int):
    return 1-binom.cdf(n-1, 262, 20/423)

def probability_n_or_more_blaze_rods(n: int):
    return 1-binom.cdf(n-1,305,1/2)

max_ender_pearls = 0
max_blaze_rods = 0
number_of_trials = 0
lowest_achieved_probability = 1
best_trial_number = 0
best_pearl_trial = 0
best_rod_trial = 0
total_ender_pearls = 0
total_blaze_rods = 0

first = False
second = False
third = False
fourth = False

most_pearls_ever = 0
most_pearls_ever_rods = 0
most_rods_ever = 0
most_rods_ever_pearls = 0
trial_results = []

while True:
    number_of_ender_pearls = 0
    number_of_blaze_rods = 0
    number_of_trials += 1

    for trading in range(262):
        if randint(1, 423) <= 20:
            number_of_ender_pearls += 1

    for blazes in range(305):
        if randint(1,2) == 2:
            number_of_blaze_rods += 1
    disable()
    trial_results.append([number_of_ender_pearls,number_of_blaze_rods])
    enable()
    total_ender_pearls += number_of_ender_pearls
    total_blaze_rods += number_of_blaze_rods
    probability_current_trial = probability_n_or_more_pearls(number_of_ender_pearls)*probability_n_or_more_blaze_rods(number_of_blaze_rods)

    if (
        (number_of_blaze_rods > max_blaze_rods)or
        (number_of_ender_pearls > max_ender_pearls)or
        (number_of_trials % 10000 < 1.0)or
        (probability_current_trial < lowest_achieved_probability)
    ):
        first = False
        second = False
        third = False
        fourth = False

        if (
            probability_current_trial < lowest_achieved_probability
        ):
            max_ender_pearls = number_of_ender_pearls
            max_blaze_rods = number_of_blaze_rods
            best_trial_number = number_of_trials
            lowest_achieved_probability = probability_current_trial
            first = True

        if number_of_blaze_rods > most_rods_ever:
            most_rods_ever = number_of_blaze_rods
            best_rod_trial = number_of_trials
            most_rods_ever_pearls = number_of_ender_pearls
            second = True

        if number_of_ender_pearls > most_pearls_ever:
            most_pearls_ever = number_of_ender_pearls
            most_pearls_ever_rods = number_of_blaze_rods
            best_pearl_trial = number_of_trials
            third = True

        if number_of_trials % 10000 == 0.0:
            disable()
            DataFrame(trial_results,columns=['Ender_Pearls','Blaze_Rods']).to_html('Dream_Simulation_Trial_Results.html')
            DataFrame(trial_results,columns=['Ender_Pearls','Blaze_Rods']).to_csv('Dream_Simulation_Trial_Results.csv')
            enable()
            fourth = True

        if first or second or third or fourth:
            system('cls')
            print(
                f"Theoretical:            (12 , 152.5)\n"
                f"Goal:                   (42 , 211)\n"
                f"Average:                ({total_ender_pearls/number_of_trials} , {total_blaze_rods/number_of_trials})\n"
                f"Best Complete Run:      ({max_ender_pearls} , {max_blaze_rods} , {best_trial_number:,}) {'%.6E' % Decimal(lowest_achieved_probability)}\n"
                f"Most Ender Pearls Ever: ({most_pearls_ever} , {most_pearls_ever_rods} , {best_pearl_trial:,})\n"
                f"Most Blaze Rods Ever:   ({most_rods_ever_pearls} , {most_rods_ever} , {best_rod_trial:,})\n"
                f"Trial Number:           ({number_of_trials:,})\n"
            )
