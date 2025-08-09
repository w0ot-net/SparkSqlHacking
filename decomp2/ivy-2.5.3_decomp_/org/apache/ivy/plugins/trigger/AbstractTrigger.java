package org.apache.ivy.plugins.trigger;

import org.apache.ivy.core.IvyContext;
import org.apache.ivy.core.event.IvyEventFilter;
import org.apache.ivy.plugins.matcher.PatternMatcher;
import org.apache.ivy.util.filter.Filter;

public abstract class AbstractTrigger implements Trigger {
   private Filter filter;
   private String event;
   private String expression;
   private String matcher = "exact";

   public Filter getEventFilter() {
      if (this.filter == null) {
         this.filter = this.createFilter();
      }

      return this.filter;
   }

   private Filter createFilter() {
      return new IvyEventFilter(this.getEvent(), this.getFilter(), this.getPatternMatcher());
   }

   private PatternMatcher getPatternMatcher() {
      return IvyContext.getContext().getSettings().getMatcher(this.matcher);
   }

   public String getEvent() {
      return this.event;
   }

   public void setEvent(String event) {
      this.event = event;
   }

   public String getFilter() {
      return this.expression;
   }

   public void setFilter(String filterExpression) {
      this.expression = filterExpression;
   }

   public String getMatcher() {
      return this.matcher;
   }

   public void setMatcher(String matcher) {
      this.matcher = matcher;
   }
}
