import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
// import java.math.BigDecimal;
public class Dream_Minecraft_Simulation {
    public static double number_of_trials = 0.0;
    public static double best_trial_number = 0.0;
    public static double best_pearl_trial = 0.0;
    public static double best_rod_trial = 0.0;
    public static double total_ender_pearls = 0.0;
    public static double total_blaze_rods = 0.0;
    public static double start = (double)System.nanoTime();
    public static double finish = (double)System.nanoTime();
    public static double elapsed = (double)System.nanoTime();

    public static int number_of_ender_pearls = 0;
    public static int number_of_blaze_rods = 0;
    public static int max_ender_pearls = 0;
    public static int max_blaze_rods = 0;
    public static int most_pearls_ever = 0;
    public static int most_pearls_ever_rods = 0;
    public static int most_rods_ever = 0;
    public static int most_rods_ever_pearls = 0;

    public static DecimalFormat df = new DecimalFormat("#,###");
    public static List<List<Integer>> trial_results = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println("Current working directory: " + System.getProperty("user.dir"));
        boolean first,second,third,fourth;
        first = second = third = fourth = false;
        initialize_files();
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
            
            trial_results.add(Arrays.asList(number_of_ender_pearls,number_of_blaze_rods));
            total_ender_pearls += number_of_ender_pearls;
            total_blaze_rods += number_of_blaze_rods;

            if ((number_of_blaze_rods > max_blaze_rods) || (number_of_ender_pearls > max_ender_pearls) ||(number_of_trials % 10_000_000 < 1.0)) {
                first = false;
                second = false;
                third = false;
                fourth = false;
                if ((number_of_blaze_rods >= max_blaze_rods && number_of_ender_pearls > max_ender_pearls) || (number_of_blaze_rods > max_blaze_rods && number_of_ender_pearls >= max_ender_pearls)) {
                    max_ender_pearls = number_of_ender_pearls;
                    max_blaze_rods = number_of_blaze_rods;
                    best_trial_number = number_of_trials;
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
                if (number_of_trials % 10_000_000 == 0.0) {
                    finish = (double)System.nanoTime();
                    elapsed = (finish - start)/(1_000_000_000);
                    add_to_files(trial_results);
                    fourth = true;
                }
                if (first || second || third || fourth) {
                    finish = (double)System.nanoTime();
                    elapsed = (finish - start)/(1_000_000_000);
                    System.out.println("Theoretical:            (12 , 152.5)\n" +
                        "Goal:                   (42 , 211)" + "\n" +
                        "Average:                (" + total_ender_pearls/number_of_trials + " , " + total_blaze_rods/number_of_trials + ")" + "\n" +
                        "Best Complete Run:      (" + max_ender_pearls + " , " + max_blaze_rods + " , " + df.format(best_trial_number) + ")\n" + 
                        /*"Probability of Best Run: " + lowest_probability_achieved + "\n" +*/
                        "Most Ender Pearls Ever: (" + most_pearls_ever + " , " + most_pearls_ever_rods + " , " + df.format(best_pearl_trial) + ")\n" +
                        "Most Blaze Rods Ever:   (" + most_rods_ever_pearls + " , " + most_rods_ever + " , " + df.format(best_rod_trial) + ")\n" + 
                        "Trial Number:           (" + df.format(number_of_trials) + ")\n" + 
                        "Time Elapsed:            " + elapsed + " seconds.\n");
                }
            }
        }
    }

    public static void initialize_files(){
        try{
            BufferedWriter x = new BufferedWriter(new FileWriter(new File("C:\\Users\\michael\\Documents\\Dream_Minecraft_Simulation\\Dream_Simulation_Individual_Trials.csv"),false));
            x.close();
            BufferedWriter y = new BufferedWriter(new FileWriter(new File("C:\\Users\\michael\\Documents\\Dream_Minecraft_Simulation\\Dream_Simulation_Individual_Trials.txt"),false));
            y.close();
            BufferedWriter trial_writer_csv = new BufferedWriter(new FileWriter(new File("C:\\Users\\michael\\Documents\\Dream_Minecraft_Simulation\\Dream_Simulation_Individual_Trials.csv"),true));
            trial_writer_csv.write("Ender_Pearls,Blaze_Rods\n");
            trial_writer_csv.close();
            BufferedWriter trial_writer_text = new BufferedWriter(new FileWriter(new File("C:\\Users\\michael\\Documents\\Dream_Minecraft_Simulation\\Dream_Simulation_Individual_Trials.txt"),true));
            trial_writer_text.write("Ender_Pearls,Blaze_Rods\n");
            trial_writer_text.close();
        }
        catch(IOException e){e.printStackTrace();}
    }

    public static void add_to_files(List<List<Integer>> trial_results, List<List<BigDecimal>> trial_results_probabilities){
        try{
            BufferedWriter trial_writer_csv = new BufferedWriter(new FileWriter("Dream_Simulation_Individual_Trials.csv",true));
            BufferedWriter trial_writer_text = new BufferedWriter(new FileWriter("Dream_Simulation_Individual_Trials.txt",true));
            for(int i=trial_results.size()-10_000_000;i<trial_results.size();i++){
                trial_writer_csv.write(trial_results.get(i).get(0).toString() + " , " + trial_results.get(i).get(1).toString() + " , " + trial_results_probabilities.get(i).get(0).toString() + " , " + trial_results_probabilities.get(i).get(1).toString() + " , " + trial_results_probabilities.get(i).get(2).toString() + "\n");    
                trial_writer_text.write(trial_results.get(i).get(0).toString() + " , " + trial_results.get(i).get(1).toString() + " , " + trial_results_probabilities.get(i).get(0).toString() + " , " + trial_results_probabilities.get(i).get(1).toString() + " , " + trial_results_probabilities.get(i).get(2).toString() + "\n"); 
            }
            trial_writer_csv.close();
            trial_writer_text.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void add_to_files(List<List<Integer>> trial_results){
        try{
            BufferedWriter trial_writer_csv = new BufferedWriter(new FileWriter(new File("C:\\Users\\michael\\Documents\\Dream_Minecraft_Simulation\\Dream_Simulation_Individual_Trials.csv"),true));
            BufferedWriter trial_writer_text = new BufferedWriter(new FileWriter(new File("C:\\Users\\michael\\Documents\\Dream_Minecraft_Simulation\\Dream_Simulation_Individual_Trials.txt"),true));
            for(int i=trial_results.size()-10_000_000;i<trial_results.size();i++){
                trial_writer_csv.write(trial_results.get(i).get(0).toString() + " , " + trial_results.get(i).get(1).toString() +"\n");    
                trial_writer_text.write(trial_results.get(i).get(0).toString() + " , " + trial_results.get(i).get(1).toString() + "\n"); 
            }
            trial_writer_csv.close();
            trial_writer_text.close();
            BufferedWriter x = new BufferedWriter(new FileWriter(new File("C:\\Users\\michael\\Documents\\Dream_Minecraft_Simulation\\Dream_Simulation_Summary.txt"),false));
            x.close();
            BufferedWriter summary_writer = new BufferedWriter(new FileWriter(new File("C:\\Users\\michael\\Documents\\Dream_Minecraft_Simulation\\Dream_Simulation_Summary.txt"),true));
            summary_writer.write("" +
            "Theoretical:            (12 , 152.5)\n" +
            "Goal:                   (42 , 211)" + "\n" +
            "Average:                (" + total_ender_pearls/number_of_trials + " , " + total_blaze_rods/number_of_trials + ")" + "\n" +
            "Best Complete Run:      (" + max_ender_pearls + " , " + max_blaze_rods + " , " + df.format(best_trial_number).toString() + ")\n" + 
            /*"Probability of Best Run: " + lowest_probability_achieved + "\n" +*/
            "Most Ender Pearls Ever: (" + most_pearls_ever + " , " + most_pearls_ever_rods + " , " + df.format(best_pearl_trial).toString() + ")\n" +
            "Most Blaze Rods Ever:   (" + most_rods_ever_pearls + " , " + most_rods_ever + " , " + df.format(best_rod_trial).toString() + ")\n" + 
            "Trial Number:           (" + df.format(number_of_trials) + ")\n" + 
            "Time Elapsed:            " + elapsed + " seconds.\n");
            summary_writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
