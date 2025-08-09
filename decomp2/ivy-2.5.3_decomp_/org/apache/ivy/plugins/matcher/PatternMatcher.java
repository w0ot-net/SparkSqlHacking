package org.apache.ivy.plugins.matcher;

public interface PatternMatcher {
   String EXACT = "exact";
   String REGEXP = "regexp";
   String GLOB = "glob";
   String EXACT_OR_REGEXP = "exactOrRegexp";
   String ANY_EXPRESSION = "*";

   Matcher getMatcher(String var1);

   String getName();
}
