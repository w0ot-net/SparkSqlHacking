package org.apache.spark.util;

import java.io.File;
import javax.tools.JavaFileObject;
import scala.Option;
import scala.collection.immutable.Seq;

public final class SparkTestUtils$ implements SparkTestUtils {
   public static final SparkTestUtils$ MODULE$ = new SparkTestUtils$();
   private static JavaFileObject.Kind org$apache$spark$util$SparkTestUtils$$SOURCE;

   static {
      SparkTestUtils.$init$(MODULE$);
   }

   public File createCompiledClass(final String className, final File destDir, final SparkTestUtils.JavaSourceFromString sourceFile, final Seq classpathUrls) {
      return SparkTestUtils.createCompiledClass$(this, className, destDir, sourceFile, classpathUrls);
   }

   public File createCompiledClass(final String className, final File destDir, final String toStringValue, final String baseClass, final Seq classpathUrls, final Seq implementsClasses, final String extraCodeBody, final Option packageName) {
      return SparkTestUtils.createCompiledClass$(this, className, destDir, toStringValue, baseClass, classpathUrls, implementsClasses, extraCodeBody, packageName);
   }

   public String createCompiledClass$default$3() {
      return SparkTestUtils.createCompiledClass$default$3$(this);
   }

   public String createCompiledClass$default$4() {
      return SparkTestUtils.createCompiledClass$default$4$(this);
   }

   public Seq createCompiledClass$default$5() {
      return SparkTestUtils.createCompiledClass$default$5$(this);
   }

   public Seq createCompiledClass$default$6() {
      return SparkTestUtils.createCompiledClass$default$6$(this);
   }

   public String createCompiledClass$default$7() {
      return SparkTestUtils.createCompiledClass$default$7$(this);
   }

   public Option createCompiledClass$default$8() {
      return SparkTestUtils.createCompiledClass$default$8$(this);
   }

   public JavaFileObject.Kind org$apache$spark$util$SparkTestUtils$$SOURCE() {
      return org$apache$spark$util$SparkTestUtils$$SOURCE;
   }

   public final void org$apache$spark$util$SparkTestUtils$_setter_$org$apache$spark$util$SparkTestUtils$$SOURCE_$eq(final JavaFileObject.Kind x$1) {
      org$apache$spark$util$SparkTestUtils$$SOURCE = x$1;
   }

   private SparkTestUtils$() {
   }
}
