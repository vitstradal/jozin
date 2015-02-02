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
import java.lang.UnsupportedOperationException;

public class ListRenamer implements Renamer, OptionHandler {
    String nouns[] = {
      "ahoj", "penize", "kolo", "autobus", "kocicka", "pejsek", "manicka", "kaficko", "chleba", "strom", "cesta", "slunicko", "beruska",
      "zelenina", "ovoce", "brioska"
    };

    String adjs[] = {
      "red", "quick", "small", "green", "full"
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


    public ListRenamer() {
    }

    public void setOption(String option, Collection values) {
    }

    static int counts[] = {0,0,0,0,0};
    public Iterator generateNames(Identifier ident) {
	int identType;

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
	else if (ident instanceof LocalIdentifier)
	    identType = 4;
	else
	    throw new IllegalArgumentException(ident.getClass().getName());
	return new Iterator() {
	    char[] name = null;

	    public boolean hasNext() {
		return true;
	    }
	    public Object next() {
                int count = counts[identType]++;
                int idx = count % nouns.length;
                int epoch = count / nouns.length;

                String ret =  nouns[idx];
                if( epoch > 0 ) {
                  ret += "" + epoch;
                }
                return ret;
	    }

	    public void remove() {
		throw new UnsupportedOperationException();
	    }
	};
    }
}

