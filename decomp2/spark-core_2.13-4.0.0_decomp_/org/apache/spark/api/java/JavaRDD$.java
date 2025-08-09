package org.apache.spark.api.java;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import org.apache.spark.SparkContext;
import org.apache.spark.rdd.RDD;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag.;
import scala.runtime.ModuleSerializationProxy;

public final class JavaRDD$ implements Serializable {
   public static final JavaRDD$ MODULE$ = new JavaRDD$();

   public JavaRDD fromRDD(final RDD rdd, final ClassTag evidence$1) {
      return new JavaRDD(rdd, evidence$1);
   }

   public RDD toRDD(final JavaRDD rdd) {
      return rdd.rdd();
   }

   public JavaRDD readRDDFromFile(final JavaSparkContext sc, final String filename, final int parallelism) {
      return this.readRDDFromInputStream(sc.sc(), new FileInputStream(filename), parallelism);
   }

   public JavaRDD readRDDFromInputStream(final SparkContext sc, final InputStream in, final int parallelism) {
      DataInputStream din = new DataInputStream(in);

      JavaRDD var10000;
      try {
         ArrayBuffer objs = new ArrayBuffer();

         try {
            while(true) {
               int length = din.readInt();
               byte[] obj = new byte[length];
               din.readFully(obj);
               objs.$plus$eq(obj);
            }
         } catch (EOFException var12) {
            var10000 = this.fromRDD(sc.parallelize(objs.toSeq(), parallelism, .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE))), .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE)));
         }
      } finally {
         din.close();
      }

      return var10000;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JavaRDD$.class);
   }

   private JavaRDD$() {
   }
}
