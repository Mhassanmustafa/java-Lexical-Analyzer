import java.io.*;
import java.net.URL;

public class Analyzer {
    public static void main(String[] args) {
        TransitionTable transitionTable = new TransitionTable();

        int count = 0;
        int state = 0;
        int lexc = 0;
        char lex[] = new char[100];
        URL path = ClassLoader.getSystemResource("test.txt");
  

        try {
            File f = new File(path.toURI());
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            int c = 0;
            int characterCount = 0;

            char characterArray[] = new char[((int) f.length())+1];
            while ((c = br.read()) != -1)
            {
                char character = (char) c;
                characterArray[characterCount] = character;
                characterCount++;
            }

            characterArray[characterCount++] = '\0';



           while (characterArray[count] != '\0'){

               int value = characterArray[count];
               if(transitionTable.Table[state][128]==1){

                   if(transitionTable.Table[state][129]!= 0 ){
                       lexc--;
                       count--;
                   }

                   lex[lexc] = '\0';
                  // System.out.println(lex);
                   transitionTable.generateTokens(lex,transitionTable.Table[state][130]);
                   lex = new char[100];
                   lexc = 0;
                   state = 0;
               }else {


                   state = transitionTable.Table[state][value];

                  // System.out.println("state"+state +"value"+value);
                   if(state == -1){
                       lexc = 0;
                       state = 0;
                       count++;
                   }else {
                       lex[lexc] = characterArray[count];
                       lexc++;
                       count++;
                   }
               }
           }
        }catch (Exception e){
            e.printStackTrace();

    }

    }
}
