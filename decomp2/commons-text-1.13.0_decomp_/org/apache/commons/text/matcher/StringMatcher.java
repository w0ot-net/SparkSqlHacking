package org.apache.commons.text.matcher;

import org.apache.commons.lang3.CharSequenceUtils;

public interface StringMatcher {
   default StringMatcher andThen(StringMatcher stringMatcher) {
      return StringMatcherFactory.INSTANCE.andMatcher(this, stringMatcher);
   }

   default int isMatch(char[] buffer, int pos) {
      return this.isMatch((char[])buffer, pos, 0, buffer.length);
   }

   int isMatch(char[] var1, int var2, int var3, int var4);

   default int isMatch(CharSequence buffer, int pos) {
      return this.isMatch((CharSequence)buffer, pos, 0, buffer.length());
   }

   default int isMatch(CharSequence buffer, int start, int bufferStart, int bufferEnd) {
      return this.isMatch(CharSequenceUtils.toCharArray(buffer), start, bufferEnd, bufferEnd);
   }

   default int size() {
      return 0;
   }
}
