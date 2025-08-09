package org.apache.ivy.core.event;

import java.util.ArrayList;
import java.util.List;
import org.apache.ivy.plugins.matcher.ExactPatternMatcher;
import org.apache.ivy.plugins.matcher.Matcher;
import org.apache.ivy.plugins.matcher.PatternMatcher;
import org.apache.ivy.util.StringUtils;
import org.apache.ivy.util.filter.AndFilter;
import org.apache.ivy.util.filter.Filter;
import org.apache.ivy.util.filter.NoFilter;
import org.apache.ivy.util.filter.NotFilter;
import org.apache.ivy.util.filter.OrFilter;

public class IvyEventFilter implements Filter {
   private static final String NOT = "NOT ";
   private static final String OR = " OR ";
   private static final String AND = " AND ";
   private PatternMatcher matcher;
   private Filter nameFilter;
   private Filter attFilter;

   public IvyEventFilter(String event, String filterExpression, PatternMatcher matcher) {
      this.matcher = (PatternMatcher)(matcher == null ? ExactPatternMatcher.INSTANCE : matcher);
      if (event == null) {
         this.nameFilter = NoFilter.instance();
      } else {
         final Matcher eventNameMatcher = this.matcher.getMatcher(event);
         this.nameFilter = new Filter() {
            public boolean accept(IvyEvent e) {
               return eventNameMatcher.matches(e.getName());
            }
         };
      }

      if (StringUtils.isNullOrEmpty(filterExpression)) {
         this.attFilter = NoFilter.instance();
      } else {
         this.attFilter = this.parseExpression(filterExpression);
      }

   }

   private Filter parseExpression(String filterExpression) {
      filterExpression = filterExpression.trim();
      int index = filterExpression.indexOf(" AND ");
      if (index != -1) {
         return new AndFilter(this.parseExpression(filterExpression.substring(0, index)), this.parseExpression(filterExpression.substring(index + " AND ".length())));
      } else {
         index = filterExpression.indexOf(" OR ");
         if (index != -1) {
            return new OrFilter(this.parseExpression(filterExpression.substring(0, index)), this.parseExpression(filterExpression.substring(index + " OR ".length())));
         } else if (filterExpression.startsWith("NOT ")) {
            return new NotFilter(this.parseExpression(filterExpression.substring("NOT ".length())));
         } else {
            index = filterExpression.indexOf("=");
            if (index == -1) {
               throw new IllegalArgumentException("bad filter expression: " + filterExpression + ": no equal sign found");
            } else {
               final String attname = filterExpression.substring(0, index).trim();
               final List<Matcher> matchers = new ArrayList();

               for(String value : StringUtils.splitToArray(filterExpression.substring(index + 1))) {
                  matchers.add(this.matcher.getMatcher(value));
               }

               return new Filter() {
                  public boolean accept(IvyEvent e) {
                     String val = (String)e.getAttributes().get(attname);
                     if (val == null) {
                        return false;
                     } else {
                        for(Matcher matcher : matchers) {
                           if (matcher.matches(val)) {
                              return true;
                           }
                        }

                        return false;
                     }
                  }
               };
            }
         }
      }
   }

   public boolean accept(IvyEvent e) {
      return this.nameFilter.accept(e) && this.attFilter.accept(e);
   }
}
