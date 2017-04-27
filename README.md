# LZ77
It has a simple command line interface.
It supports only ISO-8859-1 format text i.e. Characters with ASCII values (0-255).

# Compression:
Compile the LZ_Main.java
  javac LZ_Main.java
Run the command for compression.
  java LZ77Main -c input.txt output.lz77
  
input.txt: The contents file which has the text we want to compress.
output.lz77: The file will be created and the compressed data is stored here.

# Decompression:
Compile the LZ_Main.java
  javac LZ_Main.java
Run the command for decompression.
  java LZ77Main -d output.lz77 output.txt

output.lz77: The contains the compressed data that needs to be decompressed.
output.txt: The decompressed text will be stored here.

# Note:
  The file extensions need not be same. They don't matter.
  
# Working:
On a random article taken from wikipedia (contents.txt) of 5798 bytes, The compressed file was of 3339 bytes.
