package org.apache.spark.streaming.util;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class FileBasedWriteAheadLogSegment$ extends AbstractFunction3 implements Serializable {
   public static final FileBasedWriteAheadLogSegment$ MODULE$ = new FileBasedWriteAheadLogSegment$();

   public final String toString() {
      return "FileBasedWriteAheadLogSegment";
   }

   public FileBasedWriteAheadLogSegment apply(final String path, final long offset, final int length) {
      return new FileBasedWriteAheadLogSegment(path, offset, length);
   }

   public Option unapply(final FileBasedWriteAheadLogSegment x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.path(), BoxesRunTime.boxToLong(x$0.offset()), BoxesRunTime.boxToInteger(x$0.length()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(FileBasedWriteAheadLogSegment$.class);
   }

   private FileBasedWriteAheadLogSegment$() {
   }
}
