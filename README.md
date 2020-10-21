# LZ77
It has a simple command line interface.
It supports only ISO-8859-1 format text i.e. Characters with ASCII values (0-255).

# Compression:
Compile the LZ_Main.java<br>
  javac LZ_Main.java<br>
Run the command for compression.<br>
  java LZ77Main -c input.txt output.lz77<br><br>
  
input.txt: The contents file which has the text we want to compress.<br>
output.lz77: The file will be created and the compressed data is stored here.<br>

# Decompression:
Compile the LZ_Main.java<br>
  javac LZ_Main.java<br>
Run the command for decompression.<br>
  java LZ77Main -d output.lz77 output.txt<br><br>

output.lz77: This contains the compressed data that needs to be decompressed.<br>
output.txt: The decompressed text will be stored here.<br>

# Note:
  The file extensions need not be same. They don't matter.
# Working:
On a random article taken from wikipedia (contents.txt) of 5798 bytes, The compressed file was of 3339 bytes.
