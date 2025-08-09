package javolution.xml.stream;

import java.util.Iterator;
import javolution.text.CharArray;

public interface NamespaceContext {
   CharArray getNamespaceURI(CharSequence var1);

   CharArray getPrefix(CharSequence var1);

   Iterator getPrefixes(CharSequence var1);
}
