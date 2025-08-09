package io.vertx.core.cli;

import io.vertx.core.cli.converters.Converter;
import java.util.Objects;
import java.util.Set;

public class TypedOption extends Option {
   protected Class type;
   protected boolean parsedAsList;
   protected String listSeparator = ",";
   protected Converter converter;

   public TypedOption() {
   }

   public TypedOption(TypedOption option) {
      super((Option)option);
      this.type = option.getType();
      this.converter = option.getConverter();
      this.parsedAsList = option.isParsedAsList();
      this.listSeparator = option.getListSeparator();
   }

   public TypedOption setMultiValued(boolean acceptMultipleValues) {
      super.setMultiValued(acceptMultipleValues);
      return this;
   }

   public TypedOption setSingleValued(boolean acceptSingleValue) {
      super.setSingleValued(acceptSingleValue);
      return this;
   }

   public TypedOption setArgName(String argName) {
      super.setArgName(argName);
      return this;
   }

   public TypedOption setDefaultValue(String defaultValue) {
      super.setDefaultValue(defaultValue);
      return this;
   }

   public TypedOption setDescription(String description) {
      super.setDescription(description);
      return this;
   }

   public TypedOption setFlag(boolean flag) {
      super.setFlag(flag);
      return this;
   }

   public TypedOption setHidden(boolean hidden) {
      super.setHidden(hidden);
      return this;
   }

   public TypedOption setLongName(String longName) {
      super.setLongName(longName);
      return this;
   }

   public TypedOption setRequired(boolean required) {
      super.setRequired(required);
      return this;
   }

   public TypedOption setShortName(String shortName) {
      super.setShortName(shortName);
      return this;
   }

   public Class getType() {
      return this.type;
   }

   public TypedOption setType(Class type) {
      this.type = type;
      if (type != null && this.getChoices().isEmpty() && type.isEnum()) {
         this.setChoicesFromEnumType();
      }

      return this;
   }

   public boolean isParsedAsList() {
      return this.parsedAsList;
   }

   public TypedOption setParsedAsList(boolean isList) {
      this.parsedAsList = isList;
      return this;
   }

   public String getListSeparator() {
      return this.listSeparator;
   }

   public TypedOption setListSeparator(String listSeparator) {
      Objects.requireNonNull(listSeparator);
      this.parsedAsList = true;
      this.listSeparator = listSeparator;
      return this;
   }

   public Converter getConverter() {
      return this.converter;
   }

   public TypedOption setConverter(Converter converter) {
      this.converter = converter;
      return this;
   }

   public void ensureValidity() {
      super.ensureValidity();
      if (this.type == null) {
         throw new IllegalArgumentException("Type must not be null");
      }
   }

   public TypedOption setChoices(Set choices) {
      super.setChoices(choices);
      return this;
   }

   public TypedOption addChoice(String choice) {
      super.addChoice(choice);
      return this;
   }

   private void setChoicesFromEnumType() {
      Object[] constants = this.type.getEnumConstants();

      for(Object c : constants) {
         this.addChoice(c.toString());
      }

   }
}
