package io.jsonwebtoken.impl.lang;

import io.jsonwebtoken.lang.Assert;
import java.util.List;
import java.util.Set;

public class DefaultParameterBuilder implements ParameterBuilder {
   private String id;
   private String name;
   private boolean secret;
   private final Class type;
   private Converter converter;
   private Class collectionType;

   public DefaultParameterBuilder(Class type) {
      this.type = (Class)Assert.notNull(type, "Type cannot be null.");
   }

   public ParameterBuilder setId(String id) {
      this.id = id;
      return this;
   }

   public ParameterBuilder setName(String name) {
      this.name = name;
      return this;
   }

   public ParameterBuilder setSecret(boolean secret) {
      this.secret = secret;
      return this;
   }

   public ParameterBuilder list() {
      Class<?> clazz = List.class;
      this.collectionType = clazz;
      return this;
   }

   public ParameterBuilder set() {
      Class<?> clazz = Set.class;
      this.collectionType = clazz;
      return this;
   }

   public ParameterBuilder setConverter(Converter converter) {
      this.converter = converter;
      return this;
   }

   public Parameter build() {
      Assert.notNull(this.type, "Type must be set.");
      Converter conv = this.converter;
      if (conv == null) {
         conv = Converters.forType(this.type);
      }

      if (this.collectionType != null) {
         conv = List.class.isAssignableFrom(this.collectionType) ? Converters.forList(conv) : Converters.forSet(conv);
      }

      if (this.secret) {
         conv = new RedactedValueConverter(conv);
      }

      return new DefaultParameter(this.id, this.name, this.secret, this.type, this.collectionType, conv);
   }
}
