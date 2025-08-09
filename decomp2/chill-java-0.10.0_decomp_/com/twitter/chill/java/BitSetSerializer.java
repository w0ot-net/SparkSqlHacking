package com.twitter.chill.java;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.twitter.chill.IKryoRegistrar;
import com.twitter.chill.SingleRegistrar;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.BitSet;

public class BitSetSerializer extends Serializer implements Serializable {
   private static final Field wordsField;
   private static final Constructor bitSetConstructor;
   private static final Method recalculateWordsInUseMethod;

   public static IKryoRegistrar registrar() {
      return new SingleRegistrar(BitSet.class, new BitSetSerializer());
   }

   public void write(Kryo var1, Output var2, BitSet var3) {
      Object var4 = null;

      try {
         var7 = (long[])wordsField.get(var3);
      } catch (IllegalAccessException var6) {
         throw new KryoException("Error while accessing field 'words' of bitSet", var6);
      }

      var2.writeInt(var7.length, true);

      for(int var5 = 0; var5 < var7.length; ++var5) {
         var2.writeLong(var7[var5]);
      }

   }

   public BitSet read(Kryo var1, Input var2, Class var3) {
      int var4 = var2.readInt(true);
      long[] var5 = new long[var4];

      for(int var6 = 0; var6 < var4; ++var6) {
         var5[var6] = var2.readLong();
      }

      Object var15 = null;

      try {
         var16 = (BitSet)bitSetConstructor.newInstance(var5);
      } catch (InstantiationException var11) {
         throw new KryoException("Exception thrown while creating new instance BitSetConstructor", var11);
      } catch (IllegalAccessException var12) {
         throw new KryoException("Exception thrown while creating new instance of BitSetConstructor", var12);
      } catch (InvocationTargetException var13) {
         throw new KryoException("Exception thrown while creating new instance of BitSetConstructor", var13);
      } catch (IllegalArgumentException var14) {
         throw new KryoException("Exception thrown while creating new instance of BitSetConstructor", var14);
      }

      try {
         recalculateWordsInUseMethod.invoke(var16);
         return var16;
      } catch (InvocationTargetException var8) {
         throw new KryoException("Exception thrown while invoking recalculateWordsInUseMethod", var8);
      } catch (IllegalAccessException var9) {
         throw new KryoException("Exception thrown while invoking recalculateWordsInUseMethod", var9);
      } catch (IllegalArgumentException var10) {
         throw new KryoException("Exception thrown while invoking recalculateWordsInUseMethod", var10);
      }
   }

   static {
      try {
         wordsField = BitSet.class.getDeclaredField("words");
         wordsField.setAccessible(true);
      } catch (NoSuchFieldException var3) {
         throw new KryoException("Error while getting field 'words' of bitSet", var3);
      }

      try {
         bitSetConstructor = BitSet.class.getDeclaredConstructor(long[].class);
         bitSetConstructor.setAccessible(true);
      } catch (NoSuchMethodException var2) {
         throw new KryoException("Unable to get BitSet(long[]) constructor", var2);
      }

      try {
         recalculateWordsInUseMethod = BitSet.class.getDeclaredMethod("recalculateWordsInUse");
         recalculateWordsInUseMethod.setAccessible(true);
      } catch (NoSuchMethodException var1) {
         throw new KryoException("Unable to get BitSet.recalculateWordsInUse() method", var1);
      }
   }
}
