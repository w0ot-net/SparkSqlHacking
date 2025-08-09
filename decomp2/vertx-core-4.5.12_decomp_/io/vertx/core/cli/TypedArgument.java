package io.vertx.core.cli;

import io.vertx.core.cli.converters.Converter;

public class TypedArgument extends Argument {
   protected Class type;
   protected Converter converter;

   public TypedArgument(TypedArgument arg) {
      super((Argument)arg);
      this.type = arg.getType();
      this.converter = arg.getConverter();
   }

   public TypedArgument() {
   }

   public Class getType() {
      return this.type;
   }

   public TypedArgument setType(Class type) {
      this.type = type;
      return this;
   }

   public Converter getConverter() {
      return this.converter;
   }

   public TypedArgument setConverter(Converter converter) {
      this.converter = converter;
      return this;
   }

   public void ensureValidity() {
      super.ensureValidity();
      if (this.type == null) {
         throw new IllegalArgumentException("Type must not be null");
      }
   }

   public TypedArgument setArgName(String argName) {
      super.setArgName(argName);
      return this;
   }

   public TypedArgument setDefaultValue(String defaultValue) {
      super.setDefaultValue(defaultValue);
      return this;
   }

   public TypedArgument setDescription(String description) {
      super.setDescription(description);
      return this;
   }

   public TypedArgument setHidden(boolean hidden) {
      super.setHidden(hidden);
      return this;
   }

   public TypedArgument setIndex(int index) {
      super.setIndex(index);
      return this;
   }

   public TypedArgument setRequired(boolean required) {
      super.setRequired(required);
      return this;
   }

   public TypedArgument setMultiValued(boolean multiValued) {
      super.setMultiValued(multiValued);
      return this;
   }
}
