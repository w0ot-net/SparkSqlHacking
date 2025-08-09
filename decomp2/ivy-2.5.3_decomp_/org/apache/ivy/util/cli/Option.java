package org.apache.ivy.util.cli;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Option {
   private String name;
   private String[] args;
   private String description;
   private boolean required;
   private boolean countArgs;
   private boolean deprecated;

   Option(String name, String[] args, String description, boolean required, boolean countArgs, boolean deprecated) {
      this.name = name;
      this.args = args;
      this.description = description;
      this.required = required;
      this.countArgs = countArgs;
      this.deprecated = deprecated;
      if (required) {
         throw new UnsupportedOperationException("required option not supported yet");
      }
   }

   public String getName() {
      return this.name;
   }

   public String[] getArgs() {
      return this.args;
   }

   public String getDescription() {
      return this.description;
   }

   public boolean isRequired() {
      return this.required;
   }

   public boolean isCountArgs() {
      return this.countArgs;
   }

   public boolean isDeprecated() {
      return this.deprecated;
   }

   String[] parse(ListIterator iterator) throws ParseException {
      if (this.isCountArgs()) {
         String[] values = new String[this.args.length];

         for(int i = 0; i < values.length; ++i) {
            if (!iterator.hasNext()) {
               this.missingArgument(i);
            }

            values[i] = (String)iterator.next();
            if (values[i].startsWith("-")) {
               this.missingArgument(i);
            }
         }

         return values;
      } else {
         List<String> values = new ArrayList();

         while(iterator.hasNext()) {
            String value = (String)iterator.next();
            if (value.startsWith("-")) {
               iterator.previous();
               break;
            }

            values.add(value);
         }

         return (String[])values.toArray(new String[values.size()]);
      }
   }

   private void missingArgument(int i) throws ParseException {
      if (i == 0) {
         throw new ParseException("no argument for: " + this.name);
      } else {
         throw new ParseException("missing argument for: " + this.name + ". Expected: " + this.getArgsSpec());
      }
   }

   public String getSpec() {
      return "-" + this.name + " " + this.getArgsSpec();
   }

   private String getArgsSpec() {
      if (this.args.length == 0) {
         return "";
      } else {
         StringBuilder sb = new StringBuilder();

         for(String arg : this.args) {
            sb.append("<").append(arg).append("> ");
         }

         return sb.toString();
      }
   }
}
