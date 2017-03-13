# LZW_Algos
LZW implementation in Java

By Anusha V. Tarla 
ID: 800970209

Contains to Java Source code files. One for Encoding and one for decoding.
* Encoder.java
* Decoder.java

Encoder takes filename and bitlength as input and generates .lzw - a compressed file,
as output.

Decoder takes compressed file and bit-length as input and generates decoded file as output in the same directory.

DataStructures Used:
*Hashmap for Dictionary/Table (mapping codes to ASCII characters).
*Arraylists: for convenient processing of file data.

The programs take input data from file and compare each code/character in a dictionary structure.
If a match is found the character/code is replaced with code/character and appended to result.

 If the resulting string does not exist in the
dictionary, it means it has found a new string: it outputs the code corresponding to the string without
the newest symbol, adds the string concatenated with the newest symbol (i.e., the new string) to the
dictionary with its code (which is the previous largest code value incremented by one), and resets the
current string to the newest symbol. Thus the next time the LZW algorithm encounters a repeated string
in the input sequence, it will be encoded with a single number. The algorithm continues to process
symbols from the input sequence, building new strings until the sequence is exhausted, and it then
outputs the code for the remaining string.

Running Instructions:

javac Encoder.java

java Encoder <filename> <bitlength>

javac Decoder.java

java Decoder <filename> <bitlength>


