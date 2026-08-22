import java.util.ArrayList;

public class Wordle {
    //terminal color codes, start with the color code and
    // then end with ANSI_END to stop the colouring
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_END = "\u001B[0m";

    private enum LetterStatus {INVALID,VALID,VALID_BUT_WRONG_POS};
    private ArrayList<Character> remaningLetters = new ArrayList<>();
    LetterStatus[] status = new LetterStatus[5];
    private String chosenWord = "enter";
    private String userGuess;
    Wordle(String UserGuess1){
        userGuess =UserGuess1;
        if(userGuess.length()!=5) throw new RuntimeException("Choose a 5 letter word!");


    }

    public void checker(){
        //
        for(int i=0;i<chosenWord.length();i++){
            if(chosenWord.charAt(i)== userGuess.charAt(i)){
                status[i]= LetterStatus.VALID;
            }
            else {
                status[i]=LetterStatus.INVALID;
                remaningLetters.add(chosenWord.charAt(i));
            }
        }

        for(int i =0; i<chosenWord.length();i++){
            if(!status[i].equals(LetterStatus.VALID)) {
                if(remaningLetters.contains(userGuess.charAt(i)))
                {
                    status[i]=LetterStatus.VALID_BUT_WRONG_POS;
                }

            }
        }
        print();
    }

    private void print() {
        for (int i = 0; i < 5; i++) {
            if (status[i].equals(LetterStatus.VALID)) {
                IO.print(ANSI_GREEN + userGuess.charAt(i) + ANSI_END);
            } else if (status[i].equals(LetterStatus.VALID_BUT_WRONG_POS)) {
                IO.print(ANSI_YELLOW + userGuess.charAt(i) + ANSI_END);
            } else IO.print(userGuess.charAt(i));
        }
    }
}
