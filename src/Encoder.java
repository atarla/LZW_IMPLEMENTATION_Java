
/**
 * Created by Anusha Tarla 800970209 on 3/9/2017.
 */
import java.io.*;
import java.net.URL;
import java.util.*;

public class Encoder {
    public void encode(String args[]){
        //----------------------------------------Get File Name ---------------------------------------------------//
        URL url = getClass().getResource(args[0]);
        long b =1; //2
        int n = Integer.parseInt(args[1]);//bitlength passed as a command-line argument
        int tableSize = 256;              //dictionary size
        long max_table_size = b<<n;       // calculate MAX_TABLE_SIZE=2^(bit_length)

        //---------------create hashmap(Table or Dictionary) for mapping numbers to ascii codes--------------------//
        Map<String,Integer> table = new HashMap<String,Integer>();
        for(int i=0;i<256;i++){
            table.put("" + (char)i, i);
        }
        //-------initialize STRING with NULL--------//
        String input_string = "";
        List<Integer> result = new ArrayList<Integer>(); //storing the result in an arraylist
        File file = new File(url.getPath()); //Get file from the given path
        //--------------------------Process file and Encode character by character-------//
        System.out.println("FileName is "+file);    //print Filename
        try {
            InputStream input = new FileInputStream(file);  //Process Input from file.

            int content;
            while ((content = input.read()) != -1) {    //While there's still input..
                // convert to char and display it
                char symbol = (char)content;            //Process each character
                //System.out.println(input_string+symbol);
                if(table.containsKey(input_string+symbol)){ //if match found for string+symbol in dictionary/table.
                    input_string = input_string+symbol;         //add it to string//STRING = STRING + SYMBOL
                }
                else {
                    result.add(table.get(input_string));    //output the code for string
                    if (table.size()<max_table_size){       //if table is not full

                        table.put(input_string+symbol, tableSize++);    //String + Symbol now has a code
                        input_string = ""+symbol;
                    }
                }
            }
            //---------------------------------add to list------------------------------------//
            if (!input_string.equals(""))
                result.add(table.get(input_string));
            System.out.println(result);

            //-----------------write to file------------------------------------------------------------------//
              String filename = String.valueOf(file);
              String new_filename = filename.substring(0, filename.lastIndexOf('.'));

            //---------------------Add elements from the list to File- 'file_name.lzw'------------------------//

            PrintWriter writer = new PrintWriter(new_filename+".lzw","UTF-16BE");
            int size = result.size();   //get size of list

            for (int j=0;j<size;j++) {
                  writer.write(result.get(j));
            }
            writer.close();
            //-----------------------------------------end of loop--------------------------------------------//
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }



    }
    public static void main(String args[]){
        System.out.println("Your first argument is: "+args[0]);
        System.out.println("Your second argument is: "+args[1]);
        //-----create new class object and call encoder.-----------------------------//
        Encoder obj = new Encoder();
        obj.encode(args);

    }
}
