package org.apache.ivy.plugins.version;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import org.apache.ivy.core.IvyContext;
import org.apache.ivy.core.IvyPatternHelper;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.plugins.matcher.Matcher;
import org.apache.ivy.plugins.matcher.PatternMatcher;

public class Match {
   private String revision;
   private String pattern;
   private String args;
   private String matcher;

   public String getArgs() {
      return this.args;
   }

   public void setArgs(String args) {
      this.args = args;
   }

   public String getMatcher() {
      return this.matcher;
   }

   public void setMatcher(String matcher) {
      this.matcher = matcher;
   }

   public String getPattern() {
      return this.pattern;
   }

   public void setPattern(String pattern) {
      this.pattern = pattern;
   }

   public String getRevision() {
      return this.revision;
   }

   public void setRevision(String revision) {
      this.revision = revision;
   }

   public Matcher getPatternMatcher(ModuleRevisionId askedMrid) {
      String revision = askedMrid.getRevision();
      List<String> args = split(this.getArgs());
      List<String> argValues = this.getRevisionArgs(revision);
      if (args.size() != argValues.size()) {
         return new NoMatchMatcher();
      } else {
         Map<String, String> variables = new HashMap();

         for(String arg : args) {
            variables.put(arg, argValues.get(args.indexOf(arg)));
         }

         String pattern = this.getPattern();
         pattern = IvyPatternHelper.substituteVariables(pattern, variables);
         PatternMatcher pMatcher = IvyContext.getContext().getSettings().getMatcher(this.matcher);
         return pMatcher.getMatcher(pattern);
      }
   }

   private List getRevisionArgs(String revision) {
      int bracketStartIndex = revision.indexOf(40);
      if (bracketStartIndex == -1) {
         return Collections.emptyList();
      } else {
         int bracketEndIndex = revision.indexOf(41);
         return bracketEndIndex <= bracketStartIndex + 1 ? Collections.emptyList() : split(revision.substring(bracketStartIndex + 1, bracketEndIndex));
      }
   }

   private static List split(String string) {
      if (string == null) {
         return Collections.emptyList();
      } else {
         StringTokenizer tokenizer = new StringTokenizer(string, ", ");
         List<String> tokens = new LinkedList();

         while(tokenizer.hasMoreTokens()) {
            tokens.add(tokenizer.nextToken());
         }

         return tokens;
      }
   }

   private static class NoMatchMatcher implements Matcher {
      private NoMatchMatcher() {
      }

      public boolean isExact() {
         return false;
      }

      public boolean matches(String str) {
         return false;
      }
   }
}
