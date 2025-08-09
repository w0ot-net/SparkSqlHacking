package org.apache.commons.text.translate;

import java.io.IOException;
import java.io.Writer;
import java.security.InvalidParameterException;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class LookupTranslator extends CharSequenceTranslator {
   private final Map lookupMap;
   private final BitSet prefixSet;
   private final int shortest;
   private final int longest;

   public LookupTranslator(Map lookupMap) {
      if (lookupMap == null) {
         throw new InvalidParameterException("lookupMap cannot be null");
      } else {
         this.lookupMap = new HashMap();
         this.prefixSet = new BitSet();
         int currentShortest = Integer.MAX_VALUE;
         int currentLongest = 0;

         for(Map.Entry pair : lookupMap.entrySet()) {
            this.lookupMap.put(((CharSequence)pair.getKey()).toString(), ((CharSequence)pair.getValue()).toString());
            this.prefixSet.set(((CharSequence)pair.getKey()).charAt(0));
            int sz = ((CharSequence)pair.getKey()).length();
            if (sz < currentShortest) {
               currentShortest = sz;
            }

            if (sz > currentLongest) {
               currentLongest = sz;
            }
         }

         this.shortest = currentShortest;
         this.longest = currentLongest;
      }
   }

   public int translate(CharSequence input, int index, Writer writer) throws IOException {
      if (this.prefixSet.get(input.charAt(index))) {
         int max = this.longest;
         if (index + this.longest > input.length()) {
            max = input.length() - index;
         }

         for(int i = max; i >= this.shortest; --i) {
            CharSequence subSeq = input.subSequence(index, index + i);
            String result = (String)this.lookupMap.get(subSeq.toString());
            if (result != null) {
               writer.write(result);
               return Character.codePointCount(subSeq, 0, subSeq.length());
            }
         }
      }

      return 0;
   }
}
