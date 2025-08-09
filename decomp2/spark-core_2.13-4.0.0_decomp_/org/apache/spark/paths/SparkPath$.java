package org.apache.spark.paths;

import java.io.Serializable;
import java.net.URI;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.Path;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class SparkPath$ implements Serializable {
   public static final SparkPath$ MODULE$ = new SparkPath$();

   public SparkPath fromPathString(final String str) {
      return this.fromPath(new Path(str));
   }

   public SparkPath fromPath(final Path path) {
      return this.fromUri(path.toUri());
   }

   public SparkPath fromFileStatus(final FileStatus fs) {
      return this.fromPath(fs.getPath());
   }

   public SparkPath fromUrlString(final String str) {
      return this.apply(str);
   }

   public SparkPath fromUri(final URI uri) {
      return this.fromUrlString(uri.toString());
   }

   public SparkPath apply(final String underlying) {
      return new SparkPath(underlying);
   }

   public Option unapply(final SparkPath x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.underlying$access$0()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkPath$.class);
   }

   private SparkPath$() {
   }
}
