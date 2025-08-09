package com.ibm.icu.text;

import java.text.AttributedCharacterIterator;

public interface FormattedValue extends CharSequence {
   String toString();

   Appendable appendTo(Appendable var1);

   boolean nextPosition(ConstrainedFieldPosition var1);

   AttributedCharacterIterator toCharacterIterator();
}
