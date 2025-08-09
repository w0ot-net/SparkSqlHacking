package shaded.parquet.com.fasterxml.jackson.databind.cfg;

import java.io.Serializable;
import shaded.parquet.com.fasterxml.jackson.databind.AbstractTypeResolver;
import shaded.parquet.com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import shaded.parquet.com.fasterxml.jackson.databind.deser.Deserializers;
import shaded.parquet.com.fasterxml.jackson.databind.deser.KeyDeserializers;
import shaded.parquet.com.fasterxml.jackson.databind.deser.ValueInstantiators;
import shaded.parquet.com.fasterxml.jackson.databind.deser.std.StdKeyDeserializers;
import shaded.parquet.com.fasterxml.jackson.databind.util.ArrayBuilders;
import shaded.parquet.com.fasterxml.jackson.databind.util.ArrayIterator;

public class DeserializerFactoryConfig implements Serializable {
   private static final long serialVersionUID = 1L;
   protected static final Deserializers[] NO_DESERIALIZERS = new Deserializers[0];
   protected static final BeanDeserializerModifier[] NO_MODIFIERS = new BeanDeserializerModifier[0];
   protected static final AbstractTypeResolver[] NO_ABSTRACT_TYPE_RESOLVERS = new AbstractTypeResolver[0];
   protected static final ValueInstantiators[] NO_VALUE_INSTANTIATORS = new ValueInstantiators[0];
   protected static final KeyDeserializers[] DEFAULT_KEY_DESERIALIZERS = new KeyDeserializers[]{new StdKeyDeserializers()};
   protected final Deserializers[] _additionalDeserializers;
   protected final KeyDeserializers[] _additionalKeyDeserializers;
   protected final BeanDeserializerModifier[] _modifiers;
   protected final AbstractTypeResolver[] _abstractTypeResolvers;
   protected final ValueInstantiators[] _valueInstantiators;

   public DeserializerFactoryConfig() {
      this((Deserializers[])null, (KeyDeserializers[])null, (BeanDeserializerModifier[])null, (AbstractTypeResolver[])null, (ValueInstantiators[])null);
   }

   protected DeserializerFactoryConfig(Deserializers[] allAdditionalDeserializers, KeyDeserializers[] allAdditionalKeyDeserializers, BeanDeserializerModifier[] modifiers, AbstractTypeResolver[] atr, ValueInstantiators[] vi) {
      this._additionalDeserializers = allAdditionalDeserializers == null ? NO_DESERIALIZERS : allAdditionalDeserializers;
      this._additionalKeyDeserializers = allAdditionalKeyDeserializers == null ? DEFAULT_KEY_DESERIALIZERS : allAdditionalKeyDeserializers;
      this._modifiers = modifiers == null ? NO_MODIFIERS : modifiers;
      this._abstractTypeResolvers = atr == null ? NO_ABSTRACT_TYPE_RESOLVERS : atr;
      this._valueInstantiators = vi == null ? NO_VALUE_INSTANTIATORS : vi;
   }

   public DeserializerFactoryConfig withAdditionalDeserializers(Deserializers additional) {
      if (additional == null) {
         throw new IllegalArgumentException("Cannot pass null Deserializers");
      } else {
         Deserializers[] all = (Deserializers[])ArrayBuilders.insertInListNoDup(this._additionalDeserializers, additional);
         return new DeserializerFactoryConfig(all, this._additionalKeyDeserializers, this._modifiers, this._abstractTypeResolvers, this._valueInstantiators);
      }
   }

   public DeserializerFactoryConfig withAdditionalKeyDeserializers(KeyDeserializers additional) {
      if (additional == null) {
         throw new IllegalArgumentException("Cannot pass null KeyDeserializers");
      } else {
         KeyDeserializers[] all = (KeyDeserializers[])ArrayBuilders.insertInListNoDup(this._additionalKeyDeserializers, additional);
         return new DeserializerFactoryConfig(this._additionalDeserializers, all, this._modifiers, this._abstractTypeResolvers, this._valueInstantiators);
      }
   }

   public DeserializerFactoryConfig withDeserializerModifier(BeanDeserializerModifier modifier) {
      if (modifier == null) {
         throw new IllegalArgumentException("Cannot pass null modifier");
      } else {
         BeanDeserializerModifier[] all = (BeanDeserializerModifier[])ArrayBuilders.insertInListNoDup(this._modifiers, modifier);
         return new DeserializerFactoryConfig(this._additionalDeserializers, this._additionalKeyDeserializers, all, this._abstractTypeResolvers, this._valueInstantiators);
      }
   }

   public DeserializerFactoryConfig withAbstractTypeResolver(AbstractTypeResolver resolver) {
      if (resolver == null) {
         throw new IllegalArgumentException("Cannot pass null resolver");
      } else {
         AbstractTypeResolver[] all = (AbstractTypeResolver[])ArrayBuilders.insertInListNoDup(this._abstractTypeResolvers, resolver);
         return new DeserializerFactoryConfig(this._additionalDeserializers, this._additionalKeyDeserializers, this._modifiers, all, this._valueInstantiators);
      }
   }

   public DeserializerFactoryConfig withValueInstantiators(ValueInstantiators instantiators) {
      if (instantiators == null) {
         throw new IllegalArgumentException("Cannot pass null resolver");
      } else {
         ValueInstantiators[] all = (ValueInstantiators[])ArrayBuilders.insertInListNoDup(this._valueInstantiators, instantiators);
         return new DeserializerFactoryConfig(this._additionalDeserializers, this._additionalKeyDeserializers, this._modifiers, this._abstractTypeResolvers, all);
      }
   }

   public boolean hasDeserializers() {
      return this._additionalDeserializers.length > 0;
   }

   public boolean hasKeyDeserializers() {
      return this._additionalKeyDeserializers.length > 0;
   }

   public boolean hasDeserializerModifiers() {
      return this._modifiers.length > 0;
   }

   public boolean hasAbstractTypeResolvers() {
      return this._abstractTypeResolvers.length > 0;
   }

   public boolean hasValueInstantiators() {
      return this._valueInstantiators.length > 0;
   }

   public Iterable deserializers() {
      return new ArrayIterator(this._additionalDeserializers);
   }

   public Iterable keyDeserializers() {
      return new ArrayIterator(this._additionalKeyDeserializers);
   }

   public Iterable deserializerModifiers() {
      return new ArrayIterator(this._modifiers);
   }

   public Iterable abstractTypeResolvers() {
      return new ArrayIterator(this._abstractTypeResolvers);
   }

   public Iterable valueInstantiators() {
      return new ArrayIterator(this._valueInstantiators);
   }
}
