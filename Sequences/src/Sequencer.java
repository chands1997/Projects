/**
 * Created by Shivangi Chand on 2/4/2017.
 */

import java.util.*;

public class Sequencer {

    public static void main(String[] args) {

        Scanner read = new Scanner(System.in);
        String dna = "";

        System.out.println("Input lowercase DNA fragments one line at a time. End with a blank line.");

        String input;

        while (read.hasNextLine()){


            input = read.nextLine();
            input = input.toLowerCase();
            int check = 0;

            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == 'a' || input.charAt(i) == 't' || input.charAt(i) == 'g' || input.charAt(i) == 'c') {
                    check = 1;
                } else {
                    System.out.println("DNA is invalid");
                    check = 0;
                    return;
                }
            }
            if (check == 1) {
                if (dna.equals("")) {
                    dna = dna + input;
                } else {
                    for (int j = 0; j < dna.length(); j++) {
                        String str = dna.substring(j, dna.length());
                        if (dna.endsWith(str) == input.startsWith(str)) {
                            input = input.replaceFirst(str, "");
                            break;
                        }
                    }
                    dna = dna.concat(input);
                }
            }
        }
        System.out.println("Input DNA: " + dna);

        String gene;

        if (!(dna.contains("atg"))) {
            System.out.println("DNA does not contain a gene start codon");
            return;
        } else {
            int pos = dna.indexOf("atg");
            int k;
            int check = 0;
            String codon = dna.substring(pos, dna.length());
            if (!(codon.contains("tag") || codon.contains("taa") || codon.contains("tga"))) {
                System.out.println("DNA does not contain a gene end codon");
                return;
            }
            for (k = pos; k < dna.length()-2; k +=3) {
                String str = dna.substring(k, k + 3);
                if (str.equals("tag") || str.equals("taa") || str.equals("tga")) {
                    check = 1;
                    break;
                }
            }
            if (check == 1) {
                System.out.println("Start codon position: " + pos);
                System.out.println("End codon position: " + k);
                System.out.println("Gene: " + dna.substring(pos,k) );
                gene = dna.substring(pos,k);
            }
            else{
                System.out.println("DNA does not contain a gene end codon");
                return;
            }
        }

        if(dna.length() < 30){
            System.out.println("\nThe gene is not long enough to continue.");
        }
        else{
            System.out.println("\nAnalysis Results ");
            char eyeColor = gene.charAt(20);
            String eyeColorResult = "" ;
            switch(eyeColor){
                case 'a':
                    eyeColorResult += "blue";
                    break;
                case 't':
                    eyeColorResult += "green";
                    break;
                case 'g':
                    eyeColorResult += "brown";
                    break;
                case 'c':
                    eyeColorResult += "brown";
                    break;

            }
            System.out.println("\nEye color: " + eyeColorResult);

            char hairColor = gene.charAt(18);
            String hairColorResult = ""  ;
            switch(hairColor){
                case 'a':
                    hairColorResult += "black";
                    break;
                case 't':
                    hairColorResult += "blond";
                    break;
                case 'g':
                    hairColorResult += "red";
                    break;
                case 'c':
                    hairColorResult += "brown";
                    break;

            }
            System.out.println("Hair color: " + hairColorResult);

            char tongue = gene.charAt(8);
            String tongueResult = "" ;
            switch(tongue){
                case 'a':
                    tongueResult += "yes";
                    break;
                case 't':
                    tongueResult += "no";
                    break;
                case 'g':
                    tongueResult += "no";
                    break;
                case 'c':
                    tongueResult += "no";
                    break;

            }
            System.out.println("Can roll tongue? " + tongueResult);
        }
    }
}

