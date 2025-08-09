package org.jline.reader.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.jline.reader.Candidate;
import org.jline.reader.CompletingParsedLine;
import org.jline.reader.CompletionMatcher;
import org.jline.reader.LineReader;
import org.jline.utils.AttributedString;

public class CompletionMatcherImpl implements CompletionMatcher {
   protected Predicate exact;
   protected List matchers;
   private Map matching;
   private boolean caseInsensitive;

   protected void reset(boolean caseInsensitive) {
      this.caseInsensitive = caseInsensitive;
      this.exact = (s) -> false;
      this.matchers = new ArrayList();
      this.matching = null;
   }

   public void compile(Map options, boolean prefix, CompletingParsedLine line, boolean caseInsensitive, int errors, String originalGroupName) {
      this.reset(caseInsensitive);
      this.defaultMatchers(options, prefix, line, caseInsensitive, errors, originalGroupName);
   }

   public List matches(List candidates) {
      this.matching = Collections.emptyMap();
      Map<String, List<Candidate>> sortedCandidates = this.sort(candidates);

      for(Function matcher : this.matchers) {
         this.matching = (Map)matcher.apply(sortedCandidates);
         if (!this.matching.isEmpty()) {
            break;
         }
      }

      return (List)(!this.matching.isEmpty() ? (List)this.matching.entrySet().stream().flatMap((e) -> ((List)e.getValue()).stream()).distinct().collect(Collectors.toList()) : new ArrayList());
   }

   public Candidate exactMatch() {
      if (this.matching == null) {
         throw new IllegalStateException();
      } else {
         return (Candidate)this.matching.values().stream().flatMap(Collection::stream).filter(Candidate::complete).filter((c) -> this.exact.test(c.value())).findFirst().orElse((Object)null);
      }
   }

   public String getCommonPrefix() {
      if (this.matching == null) {
         throw new IllegalStateException();
      } else {
         String commonPrefix = null;

         for(String key : this.matching.keySet()) {
            commonPrefix = commonPrefix == null ? key : this.getCommonStart(commonPrefix, key, this.caseInsensitive);
         }

         return commonPrefix;
      }
   }

   protected void defaultMatchers(Map options, boolean prefix, CompletingParsedLine line, boolean caseInsensitive, int errors, String originalGroupName) {
      String wd = line.word();
      String wdi = caseInsensitive ? wd.toLowerCase() : wd;
      String wp = wdi.substring(0, line.wordCursor());
      if (prefix) {
         this.matchers = new ArrayList(Arrays.asList(this.simpleMatcher((s) -> (caseInsensitive ? s.toLowerCase() : s).startsWith(wp)), this.simpleMatcher((s) -> (caseInsensitive ? s.toLowerCase() : s).contains(wp))));
         if (LineReader.Option.COMPLETE_MATCHER_TYPO.isSet(options)) {
            this.matchers.add(this.typoMatcher(wp, errors, caseInsensitive, originalGroupName));
         }

         this.exact = (s) -> caseInsensitive ? s.equalsIgnoreCase(wp) : s.equals(wp);
      } else if (!LineReader.Option.EMPTY_WORD_OPTIONS.isSet(options) && wd.length() == 0) {
         this.matchers = new ArrayList(Collections.singletonList(this.simpleMatcher((s) -> !s.startsWith("-"))));
         this.exact = (s) -> caseInsensitive ? s.equalsIgnoreCase(wd) : s.equals(wd);
      } else {
         if (LineReader.Option.COMPLETE_IN_WORD.isSet(options)) {
            String ws = wdi.substring(line.wordCursor());
            Pattern p1 = Pattern.compile(Pattern.quote(wp) + ".*" + Pattern.quote(ws) + ".*");
            Pattern p2 = Pattern.compile(".*" + Pattern.quote(wp) + ".*" + Pattern.quote(ws) + ".*");
            this.matchers = new ArrayList(Arrays.asList(this.simpleMatcher((s) -> p1.matcher(caseInsensitive ? s.toLowerCase() : s).matches()), this.simpleMatcher((s) -> p2.matcher(caseInsensitive ? s.toLowerCase() : s).matches())));
         } else {
            this.matchers = new ArrayList(Arrays.asList(this.simpleMatcher((s) -> (caseInsensitive ? s.toLowerCase() : s).startsWith(wdi)), this.simpleMatcher((s) -> (caseInsensitive ? s.toLowerCase() : s).contains(wdi))));
         }

         if (LineReader.Option.COMPLETE_MATCHER_CAMELCASE.isSet(options)) {
            this.matchers.add(this.simpleMatcher((s) -> this.camelMatch(wd, 0, s, 0)));
         }

         if (LineReader.Option.COMPLETE_MATCHER_TYPO.isSet(options)) {
            this.matchers.add(this.typoMatcher(wdi, errors, caseInsensitive, originalGroupName));
         }

         this.exact = (s) -> caseInsensitive ? s.equalsIgnoreCase(wd) : s.equals(wd);
      }

   }

   protected Function simpleMatcher(Predicate predicate) {
      return (m) -> (Map)m.entrySet().stream().filter((e) -> predicate.test((String)e.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
   }

   protected Function typoMatcher(String word, int errors, boolean caseInsensitive, String originalGroupName) {
      return (m) -> {
         Map<String, List<Candidate>> map = (Map)m.entrySet().stream().filter((e) -> ReaderUtils.distance(word, caseInsensitive ? ((String)e.getKey()).toLowerCase() : (String)e.getKey()) < errors).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
         if (map.size() > 1) {
            ((List)map.computeIfAbsent(word, (w) -> new ArrayList())).add(new Candidate(word, word, originalGroupName, (String)null, (String)null, (String)null, false));
         }

         return map;
      };
   }

   protected boolean camelMatch(String word, int i, String candidate, int j) {
      if (word.length() <= i) {
         return true;
      } else if (candidate.length() <= j) {
         return false;
      } else {
         char c = word.charAt(i);
         if (c == candidate.charAt(j)) {
            return this.camelMatch(word, i + 1, candidate, j + 1);
         } else {
            for(int j1 = j; j1 < candidate.length(); ++j1) {
               if (Character.isUpperCase(candidate.charAt(j1)) && Character.toUpperCase(c) == candidate.charAt(j1) && this.camelMatch(word, i + 1, candidate, j1 + 1)) {
                  return true;
               }
            }

            return false;
         }
      }
   }

   private Map sort(List candidates) {
      Map<String, List<Candidate>> sortedCandidates = new HashMap();

      for(Candidate candidate : candidates) {
         ((List)sortedCandidates.computeIfAbsent(AttributedString.fromAnsi(candidate.value()).toString(), (s) -> new ArrayList())).add(candidate);
      }

      return sortedCandidates;
   }

   private String getCommonStart(String str1, String str2, boolean caseInsensitive) {
      int[] s1 = str1.codePoints().toArray();
      int[] s2 = str2.codePoints().toArray();

      int len;
      for(len = 0; len < Math.min(s1.length, s2.length); ++len) {
         int ch1 = s1[len];
         int ch2 = s2[len];
         if (ch1 != ch2 && caseInsensitive) {
            ch1 = Character.toUpperCase(ch1);
            ch2 = Character.toUpperCase(ch2);
            if (ch1 != ch2) {
               ch1 = Character.toLowerCase(ch1);
               ch2 = Character.toLowerCase(ch2);
            }
         }

         if (ch1 != ch2) {
            break;
         }
      }

      return new String(s1, 0, len);
   }
}
