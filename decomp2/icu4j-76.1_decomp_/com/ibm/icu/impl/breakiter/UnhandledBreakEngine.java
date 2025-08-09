package com.ibm.icu.impl.breakiter;

import com.ibm.icu.impl.CharacterIteration;
import com.ibm.icu.lang.UCharacter;
import com.ibm.icu.text.UnicodeSet;
import java.text.CharacterIterator;

public final class UnhandledBreakEngine implements LanguageBreakEngine {
   volatile UnicodeSet fHandled = new UnicodeSet();

   public boolean handles(int c) {
      return this.fHandled.contains(c);
   }

   public int findBreaks(CharacterIterator text, int startPos, int endPos, DictionaryBreakEngine.DequeI foundBreaks, boolean isPhraseBreaking) {
      UnicodeSet uniset = this.fHandled;

      for(int c = CharacterIteration.current32(text); text.getIndex() < endPos && uniset.contains(c); c = CharacterIteration.current32(text)) {
         CharacterIteration.next32(text);
      }

      return 0;
   }

   public void handleChar(int c) {
      UnicodeSet originalSet = this.fHandled;
      if (!originalSet.contains(c)) {
         int script = UCharacter.getIntPropertyValue(c, 4106);
         UnicodeSet newSet = new UnicodeSet();
         newSet.applyIntPropertyValue(4106, script);
         newSet.addAll(originalSet);
         this.fHandled = newSet;
      }

   }
}
