package org.apache.spark.sql.catalyst.encoders;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import org.apache.spark.sql.errors.ExecutionErrors$;
import org.apache.spark.util.SparkClassUtils.;
import scala.Function0;
import scala.runtime.BoxedUnit;

public final class KryoSerializationCodec$ implements Function0 {
   public static final KryoSerializationCodec$ MODULE$ = new KryoSerializationCodec$();
   private static MethodHandle kryoCodecConstructor;
   private static volatile boolean bitmap$0;

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

   private MethodHandle kryoCodecConstructor$lzycompute() {
      synchronized(this){}

      try {
         if (!bitmap$0) {
            Class cls = .MODULE$.classForName("org.apache.spark.sql.catalyst.encoders.KryoSerializationCodecImpl", .MODULE$.classForName$default$2(), .MODULE$.classForName$default$3());
            kryoCodecConstructor = MethodHandles.lookup().findConstructor(cls, MethodType.methodType(BoxedUnit.TYPE));
            bitmap$0 = true;
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return kryoCodecConstructor;
   }

   private MethodHandle kryoCodecConstructor() {
      return !bitmap$0 ? this.kryoCodecConstructor$lzycompute() : kryoCodecConstructor;
   }

   public Codec apply() {
      try {
         return (Codec)this.kryoCodecConstructor().invoke();
      } catch (ClassNotFoundException var1) {
         throw ExecutionErrors$.MODULE$.cannotUseKryoSerialization();
      }
   }

   private KryoSerializationCodec$() {
   }
}
