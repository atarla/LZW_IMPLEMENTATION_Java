/**
 * Created by Anusha Tarla on 3/10/2017.
 */

import java.io.*;
import java.net.URL;
import java.util.*;


public class Decoder {
    public void decode(String args[]) throws IOException {

        long b =1;//=2
        int n = Integer.parseInt(args[1]);////bit-length passed as a command-line argument
        int tableSize = 256;
        long max_table_size = b<<n;     //MAX_TABLE_SIZE=2^(bit_length)
        //----------------------------------------Get File Name ---------------------------------------------------//
        URL url = getClass().getResource(args[0]);//

        //---------------create hashmap(Table or Dictionary) for mapping numbers to ascii codes--------------------//
        Map<Integer,String> table_d = new HashMap<Integer,String>();
        for(int i=0;i<256;i++){
            table_d.put(i, "" + (char)i);
        }
        File file = new File(url.getPath());//get file

        //read codes from file.
        InputStreamReader inputStream = new InputStreamReader(new FileInputStream(file), "UTF-16BE");//to read the data in bytes
        BufferedReader input = new BufferedReader(inputStream);

        //-----initialize TABLE[0 to 255] = code for individual characters-----------------------------//
        ArrayList<Integer> list = new ArrayList<Integer>();
        //-----------------initialize code----------------------------//
        int code = 0;

        //---------------save contents of file to an array for processing------------------------------//
        while ((code = input.read()) != -1){
            list.add(code);
            System.out.println("Code Now"+code);
        }
        String input_string = "" + (char)(int)list.remove(0);; //STRING = TABLE[CODE]
        StringBuilder result = new StringBuilder(input_string);///////String buffer to store result

        for (int k:list)//while there are still codes to receive
        {
            String new_string;
            if (table_d.containsKey(k))
                new_string = table_d.get(k);
            else if (k == tableSize)
                new_string = input_string + input_string.charAt(0);
            else
                throw new IllegalArgumentException("Bad compressed k: " + k);

            result.append(new_string);  //Add new_string to output

            // Add w+entry[0] to the dictionary.
            table_d.put(tableSize++, input_string + new_string.charAt(0));

            input_string = new_string;
        }//STRING = TABLE[CODE]

        //------------------------------write to file - File_decoded.txt--------//
        String filename = String.valueOf(file);
        String new_filename = filename.substring(0, filename.lastIndexOf('.'));
        System.out.print(new_filename);
        BufferedWriter bw = new BufferedWriter(new FileWriter(new_filename+ "_decoded.txt"));

        String str_result = String.valueOf(result);
        bw.write(str_result);
        bw.close();

    }
    public static void main(String args[]) throws IOException {
        System.out.println("Your first argument is: "+args[0]);
        System.out.println("Your second argument is: "+args[1]);
        //----------------------create new object and call Decoder function------------//
        Decoder obj = new Decoder();
        obj.decode(args);

    }
}
