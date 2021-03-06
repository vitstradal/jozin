<?php require("header.inc"); ?>
<h1>Blue Sky</h1>

<p>This section contains features that I think would be great to have,
but are also very hard to implement.  The name of the section is
inspired, by <a
href="http://www.mozilla.org/blue-sky/">Mozilla</a>.</p>

<p>Currently this are all my own ideas.  But if you send me an idea
for an interesting feature, I will add it to this list.</p>

<h2><i>Out</i>line inlined methods</h2>

<p>If java gets called with `<code>-O</code>' switch, it inlines methods,
that are private, final, or static and contain no loops.  When
decompiling this it sometimes produces really ugly code.  The right
way to solve this would be to <i>out</i>line the code again.  This is
possible but requires to find the inlined method.  </p>

<p>The name `outline' was suggested by <a
href="http://www.informatik.uni-oldenburg.de/~mw">Michael</a>.
</p>

<h2>Better names of local variables</h2>

<p>The local variable naming is very stupid.  Even with pretty it just
names the variable after the type with a unifying number appended. A
method containing very much objects of the same type looks very
ugly. </p>

<p>My plan is looking at the assignments.  If we have locals in
assignments
<pre>
int l_1 = array.length
String l_2 = object.getName()
</pre>
we could name them "length" and "name".  If we
have assignments:
<pre>
MenuItem local_1 = new MenuItem("Open");
MenuItem local_2 = new MenuItem("Save");
</pre>
good names would be <code>miOpen</code> and <code>miSave</code>.  </p>

<p>It is currently possible to assign a <i>(hint name,type)</i> pair
to a local.  If the type matches, the local will be named after
<i>hint name</i>.  This could be extended by giving them several
weighted hints and constructing the name in an intelligent way. </p>

<h2>Better deobfuscation features</h2>
<p>First there should be a good Renamer: Methods that simply
return a field value should be renamed to get<i>FieldName</i>.
Fields should be named after their type, maybe also by assignments
(see section about local variable names).</p>

<p>The deobfuscator should detect inner and anonymous variables,
synthetic methods and so on, and rename them accordingly.</p>

<h2>Handling of Class.forName in obfuscator</h2>
<p>The obfuscator should detect Class.forName constructs (and
similarly for methods and fields) and if their parameters are constant
it should change the parameter according to the rename function. </p>

<h2>Merging javadoc comments</h2>
<p>It would be nice if the decompiler could merge the javadoc comments
into the class file.  More and more people use javadoc to comment the
public api of their java classes.  It shouldn't be too difficult to
copy them back into the java code. </p>

<p>This doesn't need to be built into the decompiler.  A script that takes
the javadoc pages and the decompiled code can easily merge them.</p>
<?php require("footer.inc"); ?>
