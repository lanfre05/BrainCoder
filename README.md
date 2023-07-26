# Introduction

Here is a simple and funny desktop application that acts as an Integrated Development Environment for *BrainFuck*.

> BrainFuck (often abbreviated in BF) it's an esoteric general-purpose programming language. It is not intended for practical use, but to challenge and amuse programmers. Here it is its simple semantic:

">"	increment the data pointer by one (to point to the next cell to the right).

"<"	decrement the data pointer by one (to point to the next cell to the left).

"+"	increment the byte at the data pointer by one.

"-"	decrement the byte at the data pointer by one.

"."	output the byte at the data pointer.

","	accept one byte of input, storing its value in the byte at the data pointer.

"["	if the byte at the data pointer is zero, then instead of moving the instruction pointer forward to the next command, jump it forward to the command after the matching ] command.

"]"	if the byte at the data pointer is nonzero, then instead of moving the instruction pointer forward to the next command, jump it back to the command after the matching [ command.


# Guide

The graphic user interface of this application is quite simply to understand and use.

 . On the navbar you can find the options:
- FILE section to write a new blank file (New), open an existing file (Open), save the current file (Save), exit the application (Exit).
> Note: the application consider only files with *.bf* (BrainFuck Source Code) and *.txt* (Text File).
- EDIT section to adjust the font size.
  
. Under the text area you can find the *output area* where are displayed:
- COMPILE & EXECUTE buttons
- the area arranged to display the program OUTPUT


