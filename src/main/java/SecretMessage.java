import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * By using CharacterStream, please discover the secret message from the given
 * "secret.txt" file considering the following instructions:
 *
 * - Take into consideration only capital letters from the input text.
 * - Consider capital X as space between words.
 * - The secret message needs to be read from a file.
 */

public class SecretMessage {
    public static void main(String[] args) throws IOException {

//      File reader:
        FileReader inputStream = new FileReader("src/main/resources/secret.txt");

//      Storing the file's contents in a String:
        StringBuilder inputStringBuilder = new StringBuilder();
        try {
            int chars;
            while ((chars = inputStream.read()) != -1) { /*HOW COME "inputStream.read()" CAN BE DEFINED AS AN "int"...?*/
                inputStringBuilder.append((char)chars);
            }
        } finally {
                inputStream.close();
        }
        String initialMessage = inputStringBuilder.toString();

//      Finding the hidden message according to the given rules:
        StringBuilder parseStringBuilder = new StringBuilder();
        try {
            Pattern pattern = Pattern.compile("[A-Z]+");
            Matcher matcher = pattern.matcher(initialMessage);
            while (matcher.find()) {
                for (int i = 0; i <= matcher.groupCount(); i++) {
                    parseStringBuilder.append(matcher.group(i));
                }
            }
        } catch (PatternSyntaxException e) {}
        String hiddenMessage = parseStringBuilder.toString().replace("X", " ");

//      Displaying the hidden message:
        System.out.println(hiddenMessage);

    }
}
