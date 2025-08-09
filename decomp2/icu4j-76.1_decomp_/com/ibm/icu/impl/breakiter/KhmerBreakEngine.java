package com.ibm.icu.impl.breakiter;

import com.ibm.icu.lang.UCharacter;
import com.ibm.icu.text.UnicodeSet;
import java.io.IOException;
import java.text.CharacterIterator;

public class KhmerBreakEngine extends DictionaryBreakEngine {
   private static final byte KHMER_LOOKAHEAD = 3;
   private static final byte KHMER_ROOT_COMBINE_THRESHOLD = 3;
   private static final byte KHMER_PREFIX_COMBINE_THRESHOLD = 3;
   private static final byte KHMER_MIN_WORD = 2;
   private static final byte KHMER_MIN_WORD_SPAN = 4;
   private DictionaryMatcher fDictionary;
   private UnicodeSet fEndWordSet;
   private UnicodeSet fBeginWordSet;
   private UnicodeSet fMarkSet;

   public KhmerBreakEngine() throws IOException {
      UnicodeSet khmerWordSet = new UnicodeSet("[[:Khmer:]&[:LineBreak=SA:]]");
      this.fMarkSet = new UnicodeSet("[[:Khmer:]&[:LineBreak=SA:]&[:M:]]");
      this.fMarkSet.add(32);
      this.fBeginWordSet = new UnicodeSet(6016, 6067);
      khmerWordSet.compact();
      this.fEndWordSet = new UnicodeSet(khmerWordSet);
      this.fEndWordSet.remove(6098);
      this.fMarkSet.compact();
      this.fEndWordSet.compact();
      this.fBeginWordSet.compact();
      khmerWordSet.freeze();
      this.fMarkSet.freeze();
      this.fEndWordSet.freeze();
      this.fBeginWordSet.freeze();
      this.setCharacters(khmerWordSet);
      this.fDictionary = DictionaryData.loadDictionaryFor("Khmr");
   }

   public boolean equals(Object obj) {
      return obj instanceof KhmerBreakEngine;
   }

   public int hashCode() {
      return this.getClass().hashCode();
   }

   public boolean handles(int c) {
      int script = UCharacter.getIntPropertyValue(c, 4106);
      return script == 23;
   }

   public int divideUpDictionaryRange(CharacterIterator fIter, int rangeStart, int rangeEnd, DictionaryBreakEngine.DequeI foundBreaks, boolean isPhraseBreaking) {
      if (rangeEnd - rangeStart < 4) {
         return 0;
      } else {
         int wordsFound = 0;
         DictionaryBreakEngine.PossibleWord[] words = new DictionaryBreakEngine.PossibleWord[3];

         for(int i = 0; i < 3; ++i) {
            words[i] = new DictionaryBreakEngine.PossibleWord();
         }

         fIter.setIndex(rangeStart);

         int current;
         while((current = fIter.getIndex()) < rangeEnd) {
            int wordLength = 0;
            int candidates = words[wordsFound % 3].candidates(fIter, this.fDictionary, rangeEnd);
            if (candidates == 1) {
               wordLength = words[wordsFound % 3].acceptMarked(fIter);
               ++wordsFound;
            } else if (candidates > 1) {
               boolean foundBest = false;
               if (fIter.getIndex() < rangeEnd) {
                  do {
                     if (words[(wordsFound + 1) % 3].candidates(fIter, this.fDictionary, rangeEnd) > 0) {
                        words[wordsFound % 3].markCurrent();
                        if (fIter.getIndex() >= rangeEnd) {
                           break;
                        }

                        do {
                           if (words[(wordsFound + 2) % 3].candidates(fIter, this.fDictionary, rangeEnd) > 0) {
                              words[wordsFound % 3].markCurrent();
                              foundBest = true;
                              break;
                           }
                        } while(words[(wordsFound + 1) % 3].backUp(fIter));
                     }
                  } while(words[wordsFound % 3].backUp(fIter) && !foundBest);
               }

               wordLength = words[wordsFound % 3].acceptMarked(fIter);
               ++wordsFound;
            }

            if (fIter.getIndex() < rangeEnd && wordLength < 3) {
               if (words[wordsFound % 3].candidates(fIter, this.fDictionary, rangeEnd) <= 0 && (wordLength == 0 || words[wordsFound % 3].longestPrefix() < 3)) {
                  int remaining = rangeEnd - (current + wordLength);
                  int pc = fIter.current();
                  int chars = 0;

                  while(true) {
                     fIter.next();
                     int uc = fIter.current();
                     ++chars;
                     --remaining;
                     if (remaining <= 0) {
                        break;
                     }

                     if (this.fEndWordSet.contains(pc) && this.fBeginWordSet.contains(uc)) {
                        int candidate = words[(wordsFound + 1) % 3].candidates(fIter, this.fDictionary, rangeEnd);
                        fIter.setIndex(current + wordLength + chars);
                        if (candidate > 0) {
                           break;
                        }
                     }

                     pc = uc;
                  }

                  if (wordLength <= 0) {
                     ++wordsFound;
                  }

                  wordLength += chars;
               } else {
                  fIter.setIndex(current + wordLength);
               }
            }

            int currPos;
            while((currPos = fIter.getIndex()) < rangeEnd && this.fMarkSet.contains(fIter.current())) {
               fIter.next();
               wordLength += fIter.getIndex() - currPos;
            }

            if (wordLength > 0) {
               foundBreaks.push(current + wordLength);
            }
         }

         if (foundBreaks.peek() >= rangeEnd) {
            foundBreaks.pop();
            --wordsFound;
         }

         return wordsFound;
      }
   }
}
