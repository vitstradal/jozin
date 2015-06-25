/* ListRenamer Copyright (C) vitas
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
 */

package jode.obfuscator.modules;
import jode.obfuscator.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.io.*;
import java.lang.UnsupportedOperationException;

public class JozinRenamer implements Renamer, OptionHandler {
    String nouns[] = {
      "ahoj", "penize", "kolo", "autobus", "kocicka", "pejsek", "manicka", "kaficko", "chleba", "strom", "cesta", "slunicko", "beruska",
      "zelenina", "ovoce", "brioska", "lampa", "maslo"
    };

    String javaKeywords[] = {
	"abstract", "default", "if", "private", "throw", "boolean",
	"do", "implements", "protected", "throws", "break", "double",
	"import", "public", "transient", "byte", "else", "instanceof",
	"return", "try", "case", "extends", "int", "short", "void",
	"catch", "final", "interface", "static", "volatile", "char",
	"finally", "long", "super", "while", "class", "float",
	"native", "switch", "const", "for", "new", "synchronized",
	"continue", "goto", "package", "this", "strictfp", "null",
	"true", "false"
    };
    ArrayList<String>[] dict;
    ArrayList<String> adjs;


    public JozinRenamer() {
      dict = new ArrayList[10];
      dict[0] = read_dict("dict/pack");
      dict[1] = read_dict("dict/class");
      dict[2] = read_dict("dict/field");
      dict[3] = read_dict("dict/meth");
      dict[4] = read_dict("dict/loc");
      adjs = read_dict("dict/adjs");

    }
    private ArrayList<String> read_dict(String file ) {
        ArrayList<String> d = new ArrayList<String>();
        try {
        BufferedReader br = new BufferedReader(new FileReader(file));
          String line;
          while ((line = br.readLine()) != null) {
             line.replace("\n", "");
             d.add(line);
          }
          br.close();
        } catch(IOException ex) {
          System.err.println(ex);
        }
        return  d;
    }

    public void setOption(String option, Collection values) {
    }

    static int counts[] = {0,0,0,0,0};
    public Iterator generateNames(Identifier ident) {
	final int identType;

        // mesto
	if (ident instanceof PackageIdentifier)
	    identType = 0;
        
        // ovoce
	else if (ident instanceof ClassIdentifier)
	    identType = 1;

        //  podst.
	else if (ident instanceof FieldIdentifier)
	    identType = 2;

        // sloveso
	else if (ident instanceof MethodIdentifier)
	    identType = 3;

        // tripismene zacatecni jako  Class
	else if (ident instanceof LocalIdentifier) {
	    identType = 4;
            counts[identType] = 0;
	} else {
            System.err.println("bad agrument:" + ident.getClass().getName());
	    throw new IllegalArgumentException(ident.getClass().getName());

        }
	return new Iterator() {
	    char[] name = null;

	    public boolean hasNext() {
		return true;
	    }
	    public Object next() {
                ArrayList<String> ddict = dict[identType];
                int count = counts[identType]++;
                int n = ddict.size();
                int idx = count % n;
                int epoch = count / n;

                String ret =  ddict.get(perm(idx, n));
                if( epoch > 0 ) {
                  if( epoch - 1 < adjs.size() ) {
                     ret =  adjs.get(perm(epoch - 1, adjs.size())) + "_" + ret;
                   }
                   else {
                     ret += epoch;
                   }
                }
                return ret;
	    }

	    public void remove() {
		throw new UnsupportedOperationException();
	    }
	};
    }

    int perm(int i, int n)
    {
      int off = 0;
      int p = 1021;
      if( n == 0 ) {
        return i;
      }
      int idx = (i*p+off)%n;
      return idx;
    }
}

