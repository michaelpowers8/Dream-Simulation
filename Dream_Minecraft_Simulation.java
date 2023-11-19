import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class Dream_Minecraft_Simulation {
    public static void main(String[] args) {
        int max_ender_pearls = 0;
        int max_blaze_rods = 0;
        double number_of_trials = 0;
        double best_trial_number = 0;
        double best_pearl_trial = 0;
        double best_rod_trial = 0;

        boolean first = false;
        boolean second = false;
        boolean third = false;
        boolean fourth = false;
        
        int number_of_ender_pearls = 0;
        int number_of_blaze_rods = 0;
        
        int most_pearls_ever = 0;
        int most_pearls_ever_rods = 0;
        int most_rods_ever = 0;
        int most_rods_ever_pearls = 0;
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
            if ((number_of_blaze_rods > max_blaze_rods) || (number_of_ender_pearls > max_ender_pearls) ||(number_of_trials % 10000 < 1.0)) {
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
                if (number_of_trials % 100000 == 0.0) {
                    fourth = true;
                }
                /*if (first || second || third || fourth) {
                    System.out.println("Theoretical:            (12 , 152.5)\n" +
                                       "Goal:                   (42 , 211)" + "\n" +
                                       "Best Complete Run:      (" + max_ender_pearls + " , " + max_blaze_rods + " , " + best_trial_number + ")" + "\n" +
                                       "Most Ender Pearls Ever: (" + most_pearls_ever + " , " + most_pearls_ever_rods + " , " + best_pearl_trial + ")\n" +
                                       "Most Blaze Rods Ever:   (" + most_rods_ever_pearls + " , " + most_rods_ever + " , " + best_rod_trial + ")\n" + 
                                       "Trial Number:           (" + number_of_trials + ")\n");
                }*/
                try {
                    if (first || second || third || fourth) {
                        BufferedWriter writer = new BufferedWriter(new FileWriter("Dream Simulation Output.txt"));
                        writer.write("Theoretical:            (12 , 152.5)\n" +
                                     "Goal:                   (42 , 211)" + "\n" +
                                     "Best Complete Run:      (" + max_ender_pearls + " , " + max_blaze_rods + " , " + best_trial_number + ")" + "\n" +
                                     "Most Ender Pearls Ever: (" + most_pearls_ever + " , " + most_pearls_ever_rods + " , " + best_pearl_trial + ")\n" +
                                     "Most Blaze Rods Ever:   (" + most_rods_ever_pearls + " , " + most_rods_ever + " , " + best_rod_trial + ")\n" +
                                     "Trial Number:           (" + number_of_trials + ")\n");
        
                        // Close the writer
                        writer.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
