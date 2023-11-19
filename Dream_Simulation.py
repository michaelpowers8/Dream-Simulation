import streamlit as st
import random

def dream_minecraft_simulation():
    max_ender_pearls = 0
    max_blaze_rods = 0
    number_of_trials = 0
    best_trial_number = 0
    best_pearl_trial = 0
    best_rod_trial = 0

    first = False
    second = False
    third = False
    fourth = False

    number_of_ender_pearls = 0
    number_of_blaze_rods = 0

    most_pearls_ever = 0
    most_pearls_ever_rods = 0
    most_rods_ever = 0
    most_rods_ever_pearls = 0

    while True:
        number_of_ender_pearls = 0
        number_of_blaze_rods = 0
        number_of_trials += 1

        for _ in range(262):
            if random.randint(1, 423) <= 20:
                number_of_ender_pearls += 1

        for _ in range(305):
            if random.random() >= 0.5:
                number_of_blaze_rods += 1

        if (
            (number_of_blaze_rods > max_blaze_rods)
            or (number_of_ender_pearls > max_ender_pearls)
            or (number_of_trials % 10000 < 1.0)
        ):
            first = False
            second = False
            third = False
            fourth = False

            if (
                (number_of_blaze_rods >= max_blaze_rods and number_of_ender_pearls > max_ender_pearls)
                or (number_of_blaze_rods > max_blaze_rods and number_of_ender_pearls >= max_ender_pearls)
            ):
                max_ender_pearls = number_of_ender_pearls
                max_blaze_rods = number_of_blaze_rods
                best_trial_number = number_of_trials
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

            if number_of_trials % 100000 == 0.0:
                fourth = True

            if first or second or third or fourth:
                output_text = (
                    f"Theoretical:            (12 , 152.5)\n"
                    f"Goal:                   (42 , 211)\n"
                    f"Best Complete Run:      ({max_ender_pearls} , {max_blaze_rods} , {best_trial_number})\n"
                    f"Most Ender Pearls Ever: ({most_pearls_ever} , {most_pearls_ever_rods} , {best_pearl_trial})\n"
                    f"Most Blaze Rods Ever:   ({most_rods_ever_pearls} , {most_rods_ever} , {best_rod_trial})\n"
                    f"Trial Number:           ({number_of_trials})\n"
                )
                st.text(output_text)

                # Writing the output to a file (optional)
                with open("Dream_Simulation_Output.txt", "w") as f:
                    f.write(output_text)

# Streamlit app
st.title("Dream Minecraft Simulation")
dream_minecraft_simulation()
