package javolution.xml.sax;

import javolution.text.CharArray;

public interface Attributes {
   int getLength();

   CharArray getURI(int var1);

   CharArray getLocalName(int var1);

   CharArray getQName(int var1);

   CharArray getType(int var1);

   CharArray getValue(int var1);

   int getIndex(CharSequence var1, CharSequence var2);

   int getIndex(CharSequence var1);

   CharArray getType(CharSequence var1, CharSequence var2);

   CharArray getType(CharSequence var1);

   CharArray getValue(CharSequence var1, CharSequence var2);

   CharArray getValue(CharSequence var1);
}
