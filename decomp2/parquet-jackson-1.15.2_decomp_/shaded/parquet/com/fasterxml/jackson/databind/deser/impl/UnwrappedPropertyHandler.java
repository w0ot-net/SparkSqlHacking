package shaded.parquet.com.fasterxml.jackson.databind.deser.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import shaded.parquet.com.fasterxml.jackson.core.JsonParser;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationContext;
import shaded.parquet.com.fasterxml.jackson.databind.JsonDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import shaded.parquet.com.fasterxml.jackson.databind.util.NameTransformer;
import shaded.parquet.com.fasterxml.jackson.databind.util.TokenBuffer;

public class UnwrappedPropertyHandler {
   protected final List _properties;

   public UnwrappedPropertyHandler() {
      this._properties = new ArrayList();
   }

   protected UnwrappedPropertyHandler(List props) {
      this._properties = props;
   }

   public void addProperty(SettableBeanProperty property) {
      this._properties.add(property);
   }

   public UnwrappedPropertyHandler renameAll(NameTransformer transformer) {
      ArrayList<SettableBeanProperty> newProps = new ArrayList(this._properties.size());

      for(SettableBeanProperty prop : this._properties) {
         String newName = transformer.transform(prop.getName());
         prop = prop.withSimpleName(newName);
         JsonDeserializer<?> deser = prop.getValueDeserializer();
         if (deser != null) {
            JsonDeserializer<Object> newDeser = deser.unwrappingDeserializer(transformer);
            if (newDeser != deser) {
               prop = prop.withValueDeserializer(newDeser);
            }
         }

         newProps.add(prop);
      }

      return new UnwrappedPropertyHandler(newProps);
   }

   public Object processUnwrapped(JsonParser originalParser, DeserializationContext ctxt, Object bean, TokenBuffer buffered) throws IOException {
      int i = 0;

      for(int len = this._properties.size(); i < len; ++i) {
         SettableBeanProperty prop = (SettableBeanProperty)this._properties.get(i);
         JsonParser p = buffered.asParser(originalParser.streamReadConstraints());
         p.nextToken();
         prop.deserializeAndSet(p, ctxt, bean);
      }

      return bean;
   }
}
