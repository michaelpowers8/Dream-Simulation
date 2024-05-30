import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
public class Dream_Minecraft_Simulation {
    public static void main(String[] args) {
        double number_of_trials,best_trial_number,best_pearl_trial,best_rod_trial,total_ender_pearls,total_blaze_rods;
        number_of_trials = best_trial_number = best_pearl_trial = best_rod_trial = total_ender_pearls = total_blaze_rods = 0;
        BigDecimal lowest_probability_achieved = new BigDecimal(1);
        BigDecimal current_combined_probability = new BigDecimal(1);
        BigDecimal current_ender_pearl_probability = new BigDecimal(1);
        BigDecimal current_blaze_rod_probability = new BigDecimal(1);
        boolean first,second,third,fourth;
        first = second = third = fourth = false;
        int number_of_ender_pearls,number_of_blaze_rods,max_ender_pearls,max_blaze_rods,most_pearls_ever,most_pearls_ever_rods,most_rods_ever,most_rods_ever_pearls;
        number_of_ender_pearls = number_of_blaze_rods = max_ender_pearls = max_blaze_rods = most_pearls_ever = most_pearls_ever_rods = most_rods_ever = most_rods_ever_pearls = 0;
        List<List<Integer>> trial_results = new ArrayList<>();
        List<List<BigDecimal>> trial_results_probabilities = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("#,###");
        Probability_Testing trial_probabilities = new Probability_Testing();
        initialize_files();
        double start = (double)System.nanoTime();
        double finish = (double)System.nanoTime();
        double elapsed = 0.0;
        while(true) {
            number_of_ender_pearls = 0;
            number_of_blaze_rods = 0;
            number_of_trials += 1;
            for (int trading = 0; trading < 262; trading++) {
                if ((int)((Math.random()*423)+1) <= 20) {
                    number_of_ender_pearls += 1;
                }
            }
            for (int blazes = 0; blazes < 305; blazes++) {
                if (Math.random() >= 0.5) {
                    number_of_blaze_rods += 1;
                }
            }
            current_ender_pearl_probability = trial_probabilities.probability_n_or_more_pearls(423,20,262,number_of_ender_pearls);
            current_blaze_rod_probability = trial_probabilities.probability_n_or_more_rods(305,number_of_blaze_rods);
            current_combined_probability = trial_probabilities.combined_probability(current_ender_pearl_probability,current_blaze_rod_probability);
            
            trial_results.add(Arrays.asList(number_of_ender_pearls,number_of_blaze_rods));
            trial_results_probabilities.add(Arrays.asList(current_ender_pearl_probability,current_blaze_rod_probability,current_combined_probability));
            
            total_ender_pearls += number_of_ender_pearls;
            total_blaze_rods += number_of_blaze_rods;
            if ((number_of_blaze_rods > max_blaze_rods) || (number_of_ender_pearls > max_ender_pearls) ||(number_of_trials % 1_000_000 < 1.0) || current_combined_probability.compareTo(lowest_probability_achieved) < 0) {
                first = false;
                second = false;
                third = false;
                fourth = false;
                if (current_combined_probability.compareTo(lowest_probability_achieved) < 0) {
                    max_ender_pearls = number_of_ender_pearls;
                    max_blaze_rods = number_of_blaze_rods;
                    best_trial_number = number_of_trials;
                    lowest_probability_achieved = current_combined_probability;
                    first = true;
                }
                if (number_of_blaze_rods > most_rods_ever) {
                    most_rods_ever = number_of_blaze_rods;
                    best_rod_trial = number_of_trials;
                    most_rods_ever_pearls = number_of_ender_pearls;
                    second = true;
                }
                if (number_of_ender_pearls > most_pearls_ever) {
                    most_pearls_ever = number_of_ender_pearls;
                    most_pearls_ever_rods = number_of_blaze_rods;
                    best_pearl_trial = number_of_trials;
                    third = true;
                }
                if (number_of_trials % 1_000_000 == 0.0) {
                    add_to_files(trial_results,trial_results_probabilities);
                    fourth = true;
                }
                if (first || second || third || fourth) {
                    finish = (double)System.nanoTime();
                    elapsed = (finish - start)/(1_000_000_000);
                    System.out.println("Theoretical:            (12 , 152.5)\n" +
                        "Goal:                   (42 , 211)" + "\n" +
                        "Average:                (" + total_ender_pearls/number_of_trials + " , " + total_blaze_rods/number_of_trials + ")" + "\n" +
                        "Best Complete Run:      (" + max_ender_pearls + " , " + max_blaze_rods + " , " + df.format(best_trial_number) + ")\n" + 
                        "Probability of Best Run: " + lowest_probability_achieved + "\n" +
                        "Most Ender Pearls Ever: (" + most_pearls_ever + " , " + most_pearls_ever_rods + " , " + df.format(best_pearl_trial) + ")\n" +
                        "Most Blaze Rods Ever:   (" + most_rods_ever_pearls + " , " + most_rods_ever + " , " + df.format(best_rod_trial) + ")\n" + 
                        "Trial Number:           (" + df.format(number_of_trials) + ")\n" + 
                        "Time Elapsed:  " + elapsed + " seconds.\n");
                }
            }
        }
    }

    public static void initialize_files(){
        try{
            new BufferedWriter(new FileWriter("Dream_Simulation_Individual_Trials.csv",false));
            new BufferedWriter(new FileWriter("Dream_Simulation_Individual_Trials.txt",false));
            BufferedWriter trial_writer_csv = new BufferedWriter(new FileWriter("Dream_Simulation_Individual_Trials.csv",true));
            trial_writer_csv.write("Ender_Pearls,Blaze_Rods,Probability_Ender_Pearls,Probability_Blaze Rods,Combined_Probability\n");
            trial_writer_csv.close();
            BufferedWriter trial_writer_text = new BufferedWriter(new FileWriter("Dream_Simulation_Individual_Trials.txt",true));
            trial_writer_text.write("Ender_Pearls,Blaze_Rods,Probability_Ender_Pearls,Probability_Blaze Rods,Combined_Probability\n");
            trial_writer_text.close();
        }
        catch(IOException e){e.printStackTrace();}
    }

    public static void add_to_files(List<List<Integer>> trial_results, List<List<BigDecimal>> trial_results_probabilities){
        try{
            BufferedWriter trial_writer_csv = new BufferedWriter(new FileWriter("Dream_Simulation_Individual_Trials.csv",true));
            BufferedWriter trial_writer_text = new BufferedWriter(new FileWriter("Dream_Simulation_Individual_Trials.txt",true));
            for(int i=trial_results.size()-1_000_000;i<trial_results.size();i++){
                trial_writer_csv.write(trial_results.get(i).get(0).toString() + " , " + trial_results.get(i).get(1).toString() + " , " + trial_results_probabilities.get(i).get(0).toString() + " , " + trial_results_probabilities.get(i).get(1).toString() + " , " + trial_results_probabilities.get(i).get(2).toString() + "\n");    
                trial_writer_text.write(trial_results.get(i).get(0).toString() + " , " + trial_results.get(i).get(1).toString() + " , " + trial_results_probabilities.get(i).get(0).toString() + " , " + trial_results_probabilities.get(i).get(1).toString() + " , " + trial_results_probabilities.get(i).get(2).toString() + "\n"); 
            }
            trial_writer_csv.close();
            trial_writer_text.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
