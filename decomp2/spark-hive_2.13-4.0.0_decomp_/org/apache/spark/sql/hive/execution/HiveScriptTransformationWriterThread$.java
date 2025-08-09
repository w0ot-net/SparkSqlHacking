package org.apache.spark.sql.hive.execution;

import java.io.OutputStream;
import java.io.Serializable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.serde2.AbstractSerDe;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.spark.TaskContext;
import org.apache.spark.sql.execution.ScriptTransformationIOSchema;
import org.apache.spark.util.CircularBuffer;
import scala.Option;
import scala.Some;
import scala.Tuple10;
import scala.None.;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction10;
import scala.runtime.ModuleSerializationProxy;

public final class HiveScriptTransformationWriterThread$ extends AbstractFunction10 implements Serializable {
   public static final HiveScriptTransformationWriterThread$ MODULE$ = new HiveScriptTransformationWriterThread$();

   public final String toString() {
      return "HiveScriptTransformationWriterThread";
   }

   public HiveScriptTransformationWriterThread apply(final Iterator iter, final Seq inputSchema, final AbstractSerDe inputSerde, final StructObjectInspector inputSoi, final ScriptTransformationIOSchema ioSchema, final OutputStream outputStream, final Process proc, final CircularBuffer stderrBuffer, final TaskContext taskContext, final Configuration conf) {
      return new HiveScriptTransformationWriterThread(iter, inputSchema, inputSerde, inputSoi, ioSchema, outputStream, proc, stderrBuffer, taskContext, conf);
   }

   public Option unapply(final HiveScriptTransformationWriterThread x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple10(x$0.iter(), x$0.inputSchema(), x$0.inputSerde(), x$0.inputSoi(), x$0.ioSchema(), x$0.outputStream(), x$0.proc(), x$0.stderrBuffer(), x$0.taskContext(), x$0.conf())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(HiveScriptTransformationWriterThread$.class);
   }

   private HiveScriptTransformationWriterThread$() {
   }
}
