package org.apache.spark.sql.catalyst.encoders;

import java.io.Serializable;
import scala.Function0;
import scala.runtime.ModuleSerializationProxy;

public final class JavaSerializationCodec$ implements Function0, Serializable {
   public static final JavaSerializationCodec$ MODULE$ = new JavaSerializationCodec$();

   static {
      Function0.$init$(MODULE$);
   }

   public boolean apply$mcZ$sp() {
      return Function0.apply$mcZ$sp$(this);
   }

   public byte apply$mcB$sp() {
      return Function0.apply$mcB$sp$(this);
   }

   public char apply$mcC$sp() {
      return Function0.apply$mcC$sp$(this);
   }

   public double apply$mcD$sp() {
      return Function0.apply$mcD$sp$(this);
   }

   public float apply$mcF$sp() {
      return Function0.apply$mcF$sp$(this);
   }

   public int apply$mcI$sp() {
      return Function0.apply$mcI$sp$(this);
   }

   public long apply$mcJ$sp() {
      return Function0.apply$mcJ$sp$(this);
   }

   public short apply$mcS$sp() {
      return Function0.apply$mcS$sp$(this);
   }

   public void apply$mcV$sp() {
      Function0.apply$mcV$sp$(this);
   }

   public String toString() {
      return Function0.toString$(this);
   }

   public Codec apply() {
      return new JavaSerializationCodec();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JavaSerializationCodec$.class);
   }

   private JavaSerializationCodec$() {
   }
}
