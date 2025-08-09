package org.jline.reader;

import java.util.regex.Pattern;
import org.jline.utils.AttributedString;

public interface Highlighter {
   AttributedString highlight(LineReader var1, String var2);

   default void refresh(LineReader reader) {
   }

   void setErrorPattern(Pattern var1);

   void setErrorIndex(int var1);
}
