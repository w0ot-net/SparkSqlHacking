package org.apache.spark.util.logging;

import java.io.File;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.Some;
import scala.collection.ArrayOps.;
import scala.collection.immutable.Seq;
import scala.runtime.BoxesRunTime;

public final class RollingFileAppender$ {
   public static final RollingFileAppender$ MODULE$ = new RollingFileAppender$();
   private static final int DEFAULT_BUFFER_SIZE = 8192;
   private static final String GZIP_LOG_SUFFIX = ".gz";

   public int $lessinit$greater$default$5() {
      return this.DEFAULT_BUFFER_SIZE();
   }

   public boolean $lessinit$greater$default$6() {
      return false;
   }

   public int DEFAULT_BUFFER_SIZE() {
      return DEFAULT_BUFFER_SIZE;
   }

   public String GZIP_LOG_SUFFIX() {
      return GZIP_LOG_SUFFIX;
   }

   public Seq getSortedRolledOverFiles(final String directory, final String activeFileName) {
      File[] rolledOverFiles = (File[]).MODULE$.sorted$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])(new File(directory)).getAbsoluteFile().listFiles()), (filex) -> BoxesRunTime.boxToBoolean($anonfun$getSortedRolledOverFiles$1(activeFileName, filex)))), scala.math.Ordering..MODULE$.ordered(scala.Predef..MODULE$.$conforms()));
      File file = (new File(directory, activeFileName)).getAbsoluteFile();
      Option activeFile = (Option)(file.exists() ? new Some(file) : scala.None..MODULE$);
      return org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(.MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.sortBy$extension(scala.Predef..MODULE$.refArrayOps((Object[])rolledOverFiles), (x$1) -> scala.collection.StringOps..MODULE$.stripSuffix$extension(scala.Predef..MODULE$.augmentString(x$1.getName()), MODULE$.GZIP_LOG_SUFFIX()), scala.math.Ordering.String..MODULE$)), activeFile, scala.reflect.ClassTag..MODULE$.apply(File.class))).toImmutableArraySeq();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getSortedRolledOverFiles$1(final String activeFileName$1, final File file) {
      boolean var10000;
      label25: {
         String fileName = file.getName();
         if (fileName.startsWith(activeFileName$1)) {
            if (fileName == null) {
               if (activeFileName$1 != null) {
                  break label25;
               }
            } else if (!fileName.equals(activeFileName$1)) {
               break label25;
            }
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   private RollingFileAppender$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
