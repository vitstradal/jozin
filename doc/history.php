<?php require("header.inc"); ?>
<h1>History</h1> 

<p>Someday I found <code>guavad</code>, a disassembler for java byte
code (it does similar things like <code>javap&nbsp;-c</code>).  I used
it on a class file, and found that it was possible to reconstruct the
original java code.  First I did it by hand on some small routines,
but I soon realized that it was a rather stupid task.  So I wrote a
small <a href="dasm_to_java.perl"><code>perl</code> script</a> that
did this process automatically.  At the end of the next day I had my
first working decompiler.</p>

<p>Now while the <code>perl</code> script is working, it is not easy
to use.  You have to decompile the code first with a disassembler, cut
out the code of a single method, and run the perl script on it.  I
decided to get the bytecode directly out of the class files and do
this all automatically.  I decided to write it in <code>java</code>
now, because it suited best.</p>

<p>Just for the records: the java code is now more than 50 times
bigger than the original perl script and is still growing.</p>
<?php require("footer.inc"); ?>

