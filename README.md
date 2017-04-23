# LZ77
The encode() function encodes the file(argument 1) and stores the compressed output in another file(argument 2).
The decode() function decodes the file(argument 2) and stores the decoded output in another file(argument 3).
Note: The original and decoded files have same content(That's how you say this is working.. ;-)
Note: The encoded file cannot be read normally. The decode function is required to read.
Note: The amount of compression depends on the contents of the file. The example shown in the repo compresses 5798 bytes to 3339 bytes.
Feel free to suggest changes.
