import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class App {
    static String FINAL_STRING = "";
    static boolean isNewline = true;
    public static void main(String[] args) throws IOException{    
        File output = new File("src/txtFiles/Output.txt");
        FileWriter fw = new FileWriter(output);


        try{
            File input = new File("src/txtFiles/TestCase.txt");
            Scanner reader = new Scanner(input);

            while (reader.hasNext()){
                String[] tokens = reader.nextLine().split(" ");
                for (String token : tokens) {
                    if(!token.equals("")){
                        checkToken(token);
                    }
                }
                FINAL_STRING += '\n';
                isNewline = true;
            }

            reader.close();
        }catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            e.printStackTrace();
        }

        fw.write(FINAL_STRING);
        fw.close();
    }

    public static void checkToken(String token) throws IOException{
        String finalOutput = "";
        char cs = 'a';
        
        for(int i = 0; i < token.length(); i++){
            switch(cs){
                // KEYWORD DFA
                case 'a':
                    switch(token.charAt(i)){
                        case ',': cs = 'a'; break;
                        case 'D': cs = 'b'; break;
                        case 'R': cs = 'l'; break;
                        case '$': cs = 'p'; break;
                        case 'F': cs = 'q'; break;
                        default: cs = 'a'; 
                    }
                    break;
                case 'b':
                    switch(token.charAt(i)){
                        case 'A': cs = 'c'; break;
                        case 'M': cs = 'g'; break;
                        default: cs = 'a'; 
                    }
                    break;
                case 'c':
                    switch(token.charAt(i)){
                        case 'D': cs = 'd'; break;
                        default: cs = 'a';
                    } 
                    break;
                case 'd':
                    switch(token.charAt(i)){
                        case 'D': cs = 'e'; break;
                        default: cs = 'a';
                    } 
                    break;
                case 'e':
                    switch(token.charAt(i)){
                        case 'I': cs = 'f'; break;
                        case 'U': cs = 'k'; break;
                        default: cs = 'a';
                    } 
                    break;
                case 'f':
                    switch(token.charAt(i)){
                        case 'U': cs = 'k'; break;
                        default: cs = 'a';
                    } 
                    break;
                case 'g':
                    switch(token.charAt(i)){
                        case 'U': cs = 'h'; break;
                        default: cs = 'a';
                    } 
                    break;
                case 'h':
                    switch(token.charAt(i)){
                        case 'L': cs = 'i'; break;
                        default: cs = 'a';
                    } 
                    break;
                case 'i':
                    switch(token.charAt(i)){
                        case 'T': cs = 'j'; break;
                        default: cs = 'a';
                    }
                    break;
                case 'j':
                    switch(token.charAt(i)){
                        case 'U': cs = 'k'; break;
                        case ',': cs = 'j'; break;
                        default: cs = 'a';
                    }
                    break;
                case 'k':
                    switch(token.charAt(i)){
                        case ',': cs = 'k'; break;
                        default: cs = 'D';
                    }
                break;
                // GPR DFA
                case 'l':
                    switch(token.charAt(i)){
                        case '3': cs = 'm'; break;
                        case '1': case '2': cs = 'n'; break;
                        case '0': case '4': case '5': case '6': case '7': case '8': case '9': cs = 'o'; break;
                        default: cs = 'a';
                    } 
                    break;
                case 'm':
                    switch(token.charAt(i)){
                        case '0': cs = 'o'; break;
                        case '1': cs = 'o'; break;
                        case ',': cs = 'm'; break;
                        default: cs = 'a';
                    }
                    break;
                case 'n':
                    switch(token.charAt(i)){
                        case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9': cs = 'o'; break;
                        case ',': cs = 'n'; break;
                        default: cs = 'a';
                    } 
                    break;
                case 'o':
                    switch(token.charAt(i)){
                        case ',': cs = 'o'; break;
                        default: cs = 'D';
                    }
                    break;
                // $ DFA
                case 'p':
                    switch(token.charAt(i)){
                        case 'F': cs = 'q'; break;
                        case '3': cs = 'm'; break;
                        case '1': case '2': cs = 'n'; break;
                        case '0': case '4': case '5': case '6': case '7': case '8': case '9': cs = 'o'; break;
                        default: cs = 'a';
                    } 
                    break;
                // F DFA
                case 'q':
                    switch(token.charAt(i)){
                        case '3': cs = 'r'; break;
                        case '1': case '2': cs = 's'; break;
                        case '0': case '4': case '5': case '6': case '7': case '8': case '9': cs = 't'; break;
                        default: cs = 'a';
                    } 
                    break;
                case 'r':
                    switch(token.charAt(i)){ 
                        case '0': cs = 't'; break;
                        case '1': cs = 't'; break;
                        case ',': cs = 'r'; break;
                        default: cs = 'a';
                    } 
                    break;
                case 's':
                    switch(token.charAt(i)){
                        case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9': cs = 't'; break;
                        case ',': cs = 's'; break;
                        default: cs = 'a';
                    }
                    break;
                case 't':
                    switch(token.charAt(i)){
                        case ',': cs = 't'; break;
                        default: cs = 'D';
                    }
                    break;
            }
        } 

        if(cs == 'k' || cs == 'j'){
            finalOutput += "KEYWORD";
        } else if (cs == 'm' || cs == 'n' || cs == 'o'){
            finalOutput += "GPR";
        } else if (cs == 'r' || cs == 's' || cs == 't'){
            finalOutput += "FPR";
        } else if (cs == 'a' || cs == 'D'){
            finalOutput += "ERROR";
        }

        if(isNewline){
            FINAL_STRING += finalOutput;
            isNewline = false;
        }else{
            FINAL_STRING += " " + finalOutput;
        }
        
    }
}
