/* Main Copyright (C) vitas
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; see the file COPYING.  If not, write to
 * the Free Software Foundation, 675 Mass Ave, Cambridge, MA 02139, USA.
 *
 * $Id: Main.java.in,v 1.4.2.3 2002/05/28 17:34:14 hoenicke Exp $
 */

package jode;
import jode.obfuscator.*;
import jode.decompiler.*;
import jode.obfuscator.modules.*;
import jode.bytecode.ClassInfo;
import jode.bytecode.SearchPath;
import jode.GlobalOptions;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.util.Enumeration;
import java.util.Vector;

import gnu.getopt.LongOpt;
import gnu.getopt.Getopt;

import java.lang.reflect.Modifier;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Collection;
import java.util.Random;

public class Jozin {
    public static boolean swapOrder   = false;

    public static final int OPTION_STRONGOVERLOAD  = 0x0001;
    public static final int OPTION_PRESERVESERIAL  = 0x0002;
    public static int options = OPTION_PRESERVESERIAL;

    private static final LongOpt[] longOptions = new LongOpt[] {
	new LongOpt("cp", LongOpt.REQUIRED_ARGUMENT, null, 'c'),
	new LongOpt("src", LongOpt.REQUIRED_ARGUMENT, null, 's'),
	new LongOpt("load", LongOpt.REQUIRED_ARGUMENT, null, 'l'),
	new LongOpt("classpath", LongOpt.REQUIRED_ARGUMENT, null, 'c'),
	new LongOpt("output", LongOpt.REQUIRED_ARGUMENT, null, 'o'),
	new LongOpt("help", LongOpt.NO_ARGUMENT, null, 'h'),
	new LongOpt("version", LongOpt.NO_ARGUMENT, null, 'V'),
	new LongOpt("verbose", LongOpt.OPTIONAL_ARGUMENT, null, 'v'),
	new LongOpt("debug", LongOpt.OPTIONAL_ARGUMENT, null, 'D'),
    };

    public static final String[] stripNames = {
	"unreach", "inner", "lvt", "lnt", "source"
    };
    public static final int STRIP_UNREACH    = 0x0001;
    public static final int STRIP_INNERINFO  = 0x0002;
    public static final int STRIP_LVT        = 0x0004;
    public static final int STRIP_LNT        = 0x0008;
    public static final int STRIP_SOURCE     = 0x0010;
    public static int stripping = 0;
    /**
     * A random pool used to destroy order of method identifiers and
     * classes in packages.  <br>
     *
     * A pseudo random is enough, no need to generate the seed
     * securely.  This makes obfuscating errors reproducable.
     */
    public static Random rand = new Random(123456);

    private static ClassBundle bundle;

    public static void usage() {
	PrintWriter err = GlobalOptions.err;
        err.println("usage:java  jode.Jozin [opts] in.jar");
	err.println("  -h, --help           show this information.");
	err.println("  -V, --version        output version information and exit.");
	err.println("  -v, --verbose        be verbose (multiple times means more verbose).");
	err.println("                       The directories should be separated by ','.");
	err.println("  -o, --output <dir>     write decompiled files to disk into directory destdir.");
	err.println("  -s, --src <dir>      write decompiled sources directory.");
	err.println("  -D, --debug=...      use --debug=help for more information.");
    }


    public static ClassBundle getClassBundle() {
	return bundle;
    }


    public static void main(String[] params) {
	if (params.length == 0) {
	    usage();
	    return;
	}
	String cp = null;
        String output = "out.jar";
        String dir = null;
        //String load = "jode.*";
        String load = "*";
        String revtable = "out.tbl";
        String src_dir = null;

        Renamer renamer = new JozinRenamer();
        ConstantAnalyzer analyzer = new ConstantAnalyzer();
        //post = new LocalOptimizer, new RemovePopAnalyzer

	//GlobalOptions.err.println(GlobalOptions.copyright);
	bundle = new ClassBundle();
	boolean errorInParams = false;
	Getopt g = new Getopt("jode.obfuscator.Main", params, "hVvc:d:D:s:l:",
			      longOptions, true);
	for (int opt = g.getopt(); opt != -1; opt = g.getopt()) {
	    switch(opt) {
	    case 0:
		break;
	    case 'h':
		usage();
		errorInParams = true;
		break;
	    case 'V':
		GlobalOptions.err.println(GlobalOptions.version);
		break;
	    case 's':
		src_dir = g.getOptarg();
		break;
	    case 'l':
		load = g.getOptarg();
		break;
	    case 'r':
		revtable = g.getOptarg();
		break;
	    case 'c':
		cp = g.getOptarg();
		break;
	    case 'o':
		output = g.getOptarg();
		break;
	    case 'x':
		dir = g.getOptarg();
		break;
	    case 'v': {
		String arg = g.getOptarg();
		if (arg == null)
		    GlobalOptions.verboseLevel++;
		else {
		    try {
			GlobalOptions.verboseLevel = Integer.parseInt(arg);
		    } catch (NumberFormatException ex) {
			GlobalOptions.err.println
			    ("jode.obfuscator.Main: Argument `"
			     +arg+"' to --verbose must be numeric:");
			errorInParams = true;
		    }
		}
		break;
	    }
	    case 'D': {
		String arg = g.getOptarg();
		if (arg == null)
		    arg = "help";
		errorInParams |= !GlobalOptions.setDebugging(arg);
		break;
	    }
	    default:
		errorInParams = true;
		break;
	    }
	}

        if (g.getOptind() != params.length - 1) {
            System.err.println("You must specify exactly one jar.");
            return;
        }

        cp = params[g.getOptind()];

	if (errorInParams)
	    return;

	// Command Line overwrites script options:
	if (cp != null)
	    bundle.setOption("classpath", Collections.singleton(cp));
	if (output != null)
	    bundle.setOption("dest", Collections.singleton(output));

	if (load != null)
	    bundle.setOption("load", Collections.singleton(load));

	if (revtable != null)
	    bundle.setOption("revtable", Collections.singleton(revtable));

	if (renamer != null)
	    bundle.setOption("renamer", Collections.singleton(renamer));

	if (analyzer != null)
	    bundle.setOption("analyzer", Collections.singleton(analyzer));

	Collection post = new LinkedList();
        post.add(new LocalOptimizer());
        post.add(new RemovePopAnalyzer());
        bundle.setOption("post", post);

        Collection strip = new LinkedList();
        strip.add("unreach");
        strip.add("lvt");
        strip.add("inner");
        bundle.setOption("strip", strip);

        Collection preserve = new LinkedList();
        WildCard wc = new WildCard();
        wc.setOption("value",  Collections.singleton("org.myorg.ApplicationClass.main.*"));
        preserve.add(wc);
        bundle.setOption("preserve", preserve);

        jode.obfuscator.Main.setClassBundle(bundle);

        bundle.run();


        String classPath = System.getProperty("java.class.path")
	    .replace(File.pathSeparatorChar, Decompiler.altPathSeparatorChar);
	String bootClassPath = System.getProperty("sun.boot.class.path");
	if (bootClassPath != null)
	    classPath += Decompiler.altPathSeparatorChar
		+ bootClassPath.replace(File.pathSeparatorChar, Decompiler.altPathSeparatorChar);

        if( src_dir != null ) {
	     GlobalOptions.err.println("writing src:" + src_dir);
             try {
	            int importPackageLimit = ImportHandler.DEFAULT_PACKAGE_LIMIT;
                    int importClassLimit = ImportHandler.DEFAULT_CLASS_LIMIT;;

		    ClassInfo.setClassPath(output + Decompiler.altPathSeparatorChar + classPath);

	            ImportHandler imports = new ImportHandler(importPackageLimit, importClassLimit);
		    Enumeration eenum = new ZipFile(output).entries();
		    while (eenum.hasMoreElements()) {
			String entry = ((ZipEntry) eenum.nextElement()).getName();
			if (entry.endsWith(".class")) {
			    entry = entry.substring(0, entry.length() - 6).replace('/', '.');
			    jode.decompiler.Main.decompileClass(entry, null, src_dir, null, imports);
			}
		    }
		    //ClassInfo.setClassPath(classPath);
	     } catch (IOException ex) {
		GlobalOptions.err.println("Can't read zip file " + output + ".");
		//ex.printStackTrace(GlobalOptions.err);
	    }
        }
    }
}

