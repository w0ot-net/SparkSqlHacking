package org.apache.commons.cli;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Option implements Cloneable, Serializable {
   static final Option[] EMPTY_ARRAY = new Option[0];
   private static final long serialVersionUID = 1L;
   public static final int UNINITIALIZED = -1;
   public static final int UNLIMITED_VALUES = -2;
   private int argCount;
   private String argName;
   private transient Converter converter;
   private final transient DeprecatedAttributes deprecated;
   private String description;
   private String longOption;
   private final String option;
   private boolean optionalArg;
   private boolean required;
   private String since;
   private Class type;
   private List values;
   private char valueSeparator;

   public static Builder builder() {
      return builder((String)null);
   }

   public static Builder builder(String option) {
      return new Builder(option);
   }

   private Option(Builder builder) {
      this.argCount = -1;
      this.type = String.class;
      this.values = new ArrayList();
      this.argName = builder.argName;
      this.description = builder.description;
      this.longOption = builder.longOption;
      this.argCount = builder.argCount;
      this.option = builder.option;
      this.optionalArg = builder.optionalArg;
      this.deprecated = builder.deprecated;
      this.required = builder.required;
      this.since = builder.since;
      this.type = builder.type;
      this.valueSeparator = builder.valueSeparator;
      this.converter = builder.converter;
   }

   public Option(String option, boolean hasArg, String description) throws IllegalArgumentException {
      this(option, (String)null, hasArg, description);
   }

   public Option(String option, String description) throws IllegalArgumentException {
      this(option, (String)null, false, description);
   }

   public Option(String option, String longOption, boolean hasArg, String description) throws IllegalArgumentException {
      this.argCount = -1;
      this.type = String.class;
      this.values = new ArrayList();
      this.deprecated = null;
      this.option = OptionValidator.validate(option);
      this.longOption = longOption;
      if (hasArg) {
         this.argCount = 1;
      }

      this.description = description;
   }

   boolean acceptsArg() {
      return (this.hasArg() || this.hasArgs() || this.hasOptionalArg()) && (this.argCount <= 0 || this.values.size() < this.argCount);
   }

   private void add(String value) {
      if (!this.acceptsArg()) {
         throw new IllegalArgumentException("Cannot add value, list full.");
      } else {
         this.values.add(value);
      }
   }

   /** @deprecated */
   @Deprecated
   public boolean addValue(String value) {
      throw new UnsupportedOperationException("The addValue method is not intended for client use. Subclasses should use the processValue method instead.");
   }

   void clearValues() {
      this.values.clear();
   }

   public Object clone() {
      try {
         Option option = (Option)super.clone();
         option.values = new ArrayList(this.values);
         return option;
      } catch (CloneNotSupportedException e) {
         throw new UnsupportedOperationException(e.getMessage(), e);
      }
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof Option)) {
         return false;
      } else {
         Option other = (Option)obj;
         return Objects.equals(this.longOption, other.longOption) && Objects.equals(this.option, other.option);
      }
   }

   public String getArgName() {
      return this.argName;
   }

   public int getArgs() {
      return this.argCount;
   }

   public Converter getConverter() {
      return this.converter == null ? TypeHandler.getDefault().getConverter(this.type) : this.converter;
   }

   public DeprecatedAttributes getDeprecated() {
      return this.deprecated;
   }

   public String getDescription() {
      return this.description;
   }

   public int getId() {
      return this.getKey().charAt(0);
   }

   public String getKey() {
      return this.option == null ? this.longOption : this.option;
   }

   public String getLongOpt() {
      return this.longOption;
   }

   public String getOpt() {
      return this.option;
   }

   public String getSince() {
      return this.since;
   }

   public Object getType() {
      return this.type;
   }

   public String getValue() {
      return this.hasNoValues() ? null : (String)this.values.get(0);
   }

   public String getValue(int index) throws IndexOutOfBoundsException {
      return this.hasNoValues() ? null : (String)this.values.get(index);
   }

   public String getValue(String defaultValue) {
      String value = this.getValue();
      return value != null ? value : defaultValue;
   }

   public String[] getValues() {
      return this.hasNoValues() ? null : (String[])this.values.toArray(Util.EMPTY_STRING_ARRAY);
   }

   public char getValueSeparator() {
      return this.valueSeparator;
   }

   public List getValuesList() {
      return this.values;
   }

   public boolean hasArg() {
      return this.argCount > 0 || this.argCount == -2;
   }

   public boolean hasArgName() {
      return this.argName != null && !this.argName.isEmpty();
   }

   public boolean hasArgs() {
      return this.argCount > 1 || this.argCount == -2;
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.longOption, this.option});
   }

   public boolean hasLongOpt() {
      return this.longOption != null;
   }

   private boolean hasNoValues() {
      return this.values.isEmpty();
   }

   public boolean hasOptionalArg() {
      return this.optionalArg;
   }

   public boolean hasValueSeparator() {
      return this.valueSeparator > 0;
   }

   public boolean isDeprecated() {
      return this.deprecated != null;
   }

   public boolean isRequired() {
      return this.required;
   }

   void processValue(String value) {
      if (this.argCount == -1) {
         throw new IllegalArgumentException("NO_ARGS_ALLOWED");
      } else {
         String add = value;
         if (this.hasValueSeparator()) {
            char sep = this.getValueSeparator();

            for(int index = value.indexOf(sep); index != -1 && this.values.size() != this.argCount - 1; index = add.indexOf(sep)) {
               this.add(add.substring(0, index));
               add = add.substring(index + 1);
            }
         }

         this.add(add);
      }
   }

   boolean requiresArg() {
      if (this.optionalArg) {
         return false;
      } else {
         return this.argCount == -2 ? this.values.isEmpty() : this.acceptsArg();
      }
   }

   public void setArgName(String argName) {
      this.argName = argName;
   }

   public void setArgs(int num) {
      this.argCount = num;
   }

   public void setConverter(Converter converter) {
      this.converter = converter;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public void setLongOpt(String longOpt) {
      this.longOption = longOpt;
   }

   public void setOptionalArg(boolean optionalArg) {
      this.optionalArg = optionalArg;
   }

   public void setRequired(boolean required) {
      this.required = required;
   }

   public void setType(Class type) {
      this.type = Option.Builder.toType(type);
   }

   /** @deprecated */
   @Deprecated
   public void setType(Object type) {
      this.setType((Class)type);
   }

   public void setValueSeparator(char valueSeparator) {
      this.valueSeparator = valueSeparator;
   }

   String toDeprecatedString() {
      if (!this.isDeprecated()) {
         return "";
      } else {
         StringBuilder buf = (new StringBuilder()).append("Option '").append(this.option).append('\'');
         if (this.longOption != null) {
            buf.append('\'').append(this.longOption).append('\'');
         }

         buf.append(": ").append(this.deprecated);
         return buf.toString();
      }
   }

   public String toString() {
      StringBuilder buf = (new StringBuilder()).append("[ ");
      buf.append("Option ");
      buf.append(this.option);
      if (this.longOption != null) {
         buf.append(' ').append(this.longOption);
      }

      if (this.isDeprecated()) {
         buf.append(' ');
         buf.append(this.deprecated.toString());
      }

      if (this.hasArgs()) {
         buf.append("[ARG...]");
      } else if (this.hasArg()) {
         buf.append(" [ARG]");
      }

      return buf.append(" :: ").append(this.description).append(" :: ").append(this.type).append(" ]").toString();
   }

   public static final class Builder {
      private static final Class DEFAULT_TYPE = String.class;
      private int argCount;
      private String argName;
      private Converter converter;
      private DeprecatedAttributes deprecated;
      private String description;
      private String longOption;
      private String option;
      private boolean optionalArg;
      private boolean required;
      private String since;
      private Class type;
      private char valueSeparator;

      private static Class toType(Class type) {
         return type != null ? type : DEFAULT_TYPE;
      }

      private Builder(String option) throws IllegalArgumentException {
         this.argCount = -1;
         this.type = DEFAULT_TYPE;
         this.option(option);
      }

      public Builder argName(String argName) {
         this.argName = argName;
         return this;
      }

      public Option build() {
         if (this.option == null && this.longOption == null) {
            throw new IllegalArgumentException("Either opt or longOpt must be specified");
         } else {
            return new Option(this);
         }
      }

      public Builder converter(Converter converter) {
         this.converter = converter;
         return this;
      }

      public Builder deprecated() {
         return this.deprecated(DeprecatedAttributes.DEFAULT);
      }

      public Builder deprecated(DeprecatedAttributes deprecated) {
         this.deprecated = deprecated;
         return this;
      }

      public Builder desc(String description) {
         this.description = description;
         return this;
      }

      public Builder hasArg() {
         return this.hasArg(true);
      }

      public Builder hasArg(boolean hasArg) {
         this.argCount = hasArg ? 1 : -1;
         return this;
      }

      public Builder hasArgs() {
         this.argCount = -2;
         return this;
      }

      public Builder longOpt(String longOption) {
         this.longOption = longOption;
         return this;
      }

      public Builder numberOfArgs(int argCount) {
         this.argCount = argCount;
         return this;
      }

      public Builder option(String option) throws IllegalArgumentException {
         this.option = OptionValidator.validate(option);
         return this;
      }

      public Builder optionalArg(boolean optionalArg) {
         if (optionalArg && this.argCount == -1) {
            this.argCount = 1;
         }

         this.optionalArg = optionalArg;
         return this;
      }

      public Builder required() {
         return this.required(true);
      }

      public Builder required(boolean required) {
         this.required = required;
         return this;
      }

      public Builder since(String since) {
         this.since = since;
         return this;
      }

      public Builder type(Class type) {
         this.type = toType(type);
         return this;
      }

      public Builder valueSeparator() {
         return this.valueSeparator('=');
      }

      public Builder valueSeparator(char valueSeparator) {
         this.valueSeparator = valueSeparator;
         return this;
      }
   }
}
