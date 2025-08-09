package org.apache.ivy.util.cli;

import java.util.ArrayList;
import java.util.List;

public class OptionBuilder {
   private final String name;
   private final List args = new ArrayList();
   private String description = "";
   private boolean required = false;
   private boolean countArgs = true;
   private boolean deprecated = false;

   public OptionBuilder(String name) {
      this.name = name;
   }

   public OptionBuilder required(boolean required) {
      this.required = required;
      return this;
   }

   public OptionBuilder description(String description) {
      this.description = description;
      return this;
   }

   public OptionBuilder arg(String argName) {
      this.args.add(argName);
      return this;
   }

   public OptionBuilder countArgs(boolean countArgs) {
      this.countArgs = countArgs;
      return this;
   }

   public OptionBuilder deprecated() {
      this.deprecated = true;
      return this;
   }

   public Option create() {
      return new Option(this.name, (String[])this.args.toArray(new String[this.args.size()]), this.description, this.required, this.countArgs, this.deprecated);
   }
}
