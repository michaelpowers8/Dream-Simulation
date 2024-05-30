# Dream Minecraft Simulation

## Overview

This Java program simulates a scenario in Minecraft where a player engages in activities to collect Ender Pearls and Blaze Rods. The program conducts a large number of trials to estimate the probabilities of obtaining certain numbers of these items under specific conditions. The acquired data is recorded and analyzed to understand the probabilities and outcomes of these trials.

## Purpose

The primary objective of this simulation is to:
1. Determine the probability of obtaining a specified number of Ender Pearls and Blaze Rods within a given number of attempts.
2. Record the results of each trial to analyze the distribution and frequency of outcomes.
3. Identify the best trial results in terms of maximum items collected and their corresponding probabilities.

## How the Program Works

### Initialization

1. **Initialize Variables:** The program sets up several variables to track the number of trials, best results, total items collected, and probabilities.
2. **File Setup:** Two files are created to store the results of individual trials:
   - `Dream_Simulation_Individual_Trials.csv`
   - `Dream_Simulation_Individual_Trials.txt`

### Simulation Loop

1. **Run Trials:** The program enters an infinite loop where it repeatedly conducts trials.
   - For each trial, the player attempts to collect Ender Pearls by trading and Blaze Rods by fighting blazes.
   - The number of Ender Pearls collected is determined by simulating 262 trades, each with a 20/423 chance of success.
   - The number of Blaze Rods collected is determined by simulating 305 encounters, each with a 50% chance of success.

2. **Calculate Probabilities:** After each trial, the probabilities of obtaining the collected number of Ender Pearls and Blaze Rods are calculated using methods from the `Probability_Testing` class. The combined probability is also calculated.

3. **Record Results:** The results of each trial, including the number of items collected and their probabilities, are stored in lists.

4. **Update Best Results:** The program checks if the current trial results are the best so far in various categories (e.g., most items collected, lowest probability). If so, it updates the relevant best results.

5. **Periodic Saving:** Every 1,000,000 trials, the program saves the accumulated trial results to the CSV and text files.

### Output

The program periodically prints the following information to the console:
- Theoretical values for comparison.
- The goal values to be achieved.
- The average number of items collected per trial.
- The best complete run in terms of maximum items collected.
- The probabilities of achieving the best run.
- The most Ender Pearls and Blaze Rods collected in a single trial.
- The total number of trials conducted.
- The elapsed time since the start of the simulation.

## Data and Analysis

### Files

1. **CSV File:** The `Dream_Simulation_Individual_Trials.csv` file contains the results of individual trials in a comma-separated format. Each row represents a trial with the following columns:
   - Number of Ender Pearls collected
   - Number of Blaze Rods collected
   - Probability of obtaining the collected number of Ender Pearls
   - Probability of obtaining the collected number of Blaze Rods
   - Combined probability of obtaining both results

2. **Text File:** The `Dream_Simulation_Individual_Trials.txt` file contains the same data as the CSV file but in a plain text format.

### Analysis

The data collected from the simulation can be used to analyze:
- The distribution and frequency of different outcomes.
- The likelihood of achieving specific goals in terms of item collection.
- How the actual results compare to theoretical expectations.

By examining the probabilities and outcomes recorded, readers can gain insights into the randomness and variability of the item collection process in Minecraft under the simulated conditions. This information can help in understanding the game's mechanics and planning strategies for efficient item collection.

## Conclusion

The Dream Minecraft Simulation provides a comprehensive analysis of item collection probabilities in Minecraft through extensive simulation trials. The recorded data offers valuable insights into the likelihood of various outcomes and helps in understanding the game's stochastic nature.
