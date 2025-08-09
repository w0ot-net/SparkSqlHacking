package org.apache.ivy.util.cli;

import java.util.HashMap;
import java.util.Map;

public class CommandLine {
   private final Map optionValues = new HashMap();
   private String[] leftOverArgs;

   void addOptionValues(String option, String[] values) {
      this.optionValues.put(option, values);
   }

   void setLeftOverArgs(String[] args) {
      this.leftOverArgs = args;
   }

   public boolean hasOption(String option) {
      return this.optionValues.containsKey(option);
   }

   public String getOptionValue(String option) {
      String[] values = this.getOptionValues(option);
      return values != null && values.length != 0 ? values[0] : null;
   }

   public String getOptionValue(String option, String defaultValue) {
      String value = this.getOptionValue(option);
      return value == null ? defaultValue : value;
   }

   public String[] getOptionValues(String option) {
      return (String[])this.optionValues.get(option);
   }

   public String[] getLeftOverArgs() {
      return this.leftOverArgs;
   }
}
