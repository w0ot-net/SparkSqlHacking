package com.google.thirdparty.publicsuffix;

import java.util.Deque;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.annotations.VisibleForTesting;
import org.sparkproject.guava.base.Joiner;
import org.sparkproject.guava.collect.ImmutableMap;
import org.sparkproject.guava.collect.Queues;

@GwtCompatible
final class TrieParser {
   private static final Joiner DIRECT_JOINER = Joiner.on("");

   static ImmutableMap parseTrie(CharSequence... encodedChunks) {
      String encoded = DIRECT_JOINER.join((Object[])encodedChunks);
      return parseFullString(encoded);
   }

   @VisibleForTesting
   static ImmutableMap parseFullString(String encoded) {
      ImmutableMap.Builder<String, PublicSuffixType> builder = ImmutableMap.builder();
      int encodedLen = encoded.length();

      for(int idx = 0; idx < encodedLen; idx += doParseTrieToBuilder(Queues.newArrayDeque(), encoded, idx, builder)) {
      }

      return builder.buildOrThrow();
   }

   private static int doParseTrieToBuilder(Deque stack, CharSequence encoded, int start, ImmutableMap.Builder builder) {
      int encodedLen = encoded.length();
      int idx = start;

      char c;
      for(c = 0; idx < encodedLen; ++idx) {
         c = encoded.charAt(idx);
         if (c == '&' || c == '?' || c == '!' || c == ':' || c == ',') {
            break;
         }
      }

      stack.push(reverse(encoded.subSequence(start, idx)));
      if (c == '!' || c == '?' || c == ':' || c == ',') {
         String domain = DIRECT_JOINER.join((Iterable)stack);
         if (domain.length() > 0) {
            builder.put(domain, PublicSuffixType.fromCode(c));
         }
      }

      ++idx;
      if (c != '?' && c != ',') {
         while(idx < encodedLen) {
            idx += doParseTrieToBuilder(stack, encoded, idx, builder);
            if (encoded.charAt(idx) == '?' || encoded.charAt(idx) == ',') {
               ++idx;
               break;
            }
         }
      }

      stack.pop();
      return idx - start;
   }

   private static CharSequence reverse(CharSequence s) {
      return (new StringBuilder(s)).reverse();
   }
}
