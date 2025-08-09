package org.apache.spark.storage;

import java.io.File;
import java.lang.invoke.SerializedLambda;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00053Q!\u0003\u0006\u0001\u0019IA\u0001\"\u0007\u0001\u0003\u0006\u0004%\ta\u0007\u0005\tI\u0001\u0011\t\u0011)A\u00059!AQ\u0005\u0001BC\u0002\u0013\u0005a\u0005\u0003\u0005+\u0001\t\u0005\t\u0015!\u0003(\u0011!Y\u0003A!b\u0001\n\u00031\u0003\u0002\u0003\u0017\u0001\u0005\u0003\u0005\u000b\u0011B\u0014\t\u000b5\u0002A\u0011\u0001\u0018\t\u000bQ\u0002A\u0011I\u001b\u0003\u0017\u0019KG.Z*fO6,g\u000e\u001e\u0006\u0003\u00171\tqa\u001d;pe\u0006<WM\u0003\u0002\u000e\u001d\u0005)1\u000f]1sW*\u0011q\u0002E\u0001\u0007CB\f7\r[3\u000b\u0003E\t1a\u001c:h'\t\u00011\u0003\u0005\u0002\u0015/5\tQCC\u0001\u0017\u0003\u0015\u00198-\u00197b\u0013\tARC\u0001\u0004B]f\u0014VMZ\u0001\u0005M&dWm\u0001\u0001\u0016\u0003q\u0001\"!\b\u0012\u000e\u0003yQ!a\b\u0011\u0002\u0005%|'\"A\u0011\u0002\t)\fg/Y\u0005\u0003Gy\u0011AAR5mK\u0006)a-\u001b7fA\u00051qN\u001a4tKR,\u0012a\n\t\u0003)!J!!K\u000b\u0003\t1{gnZ\u0001\b_\u001a47/\u001a;!\u0003\u0019aWM\\4uQ\u00069A.\u001a8hi\"\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u00030cI\u001a\u0004C\u0001\u0019\u0001\u001b\u0005Q\u0001\"B\r\b\u0001\u0004a\u0002\"B\u0013\b\u0001\u00049\u0003\"B\u0016\b\u0001\u00049\u0013\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003Y\u0002\"a\u000e \u000f\u0005ab\u0004CA\u001d\u0016\u001b\u0005Q$BA\u001e\u001b\u0003\u0019a$o\\8u}%\u0011Q(F\u0001\u0007!J,G-\u001a4\n\u0005}\u0002%AB*ue&twM\u0003\u0002>+\u0001"
)
public class FileSegment {
   private final File file;
   private final long offset;
   private final long length;

   public File file() {
      return this.file;
   }

   public long offset() {
      return this.offset;
   }

   public long length() {
      return this.length;
   }

   public String toString() {
      return .MODULE$.format$extension(scala.Predef..MODULE$.augmentString("(name=%s, offset=%d, length=%d)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this.file().getName(), BoxesRunTime.boxToLong(this.offset()), BoxesRunTime.boxToLong(this.length())}));
   }

   public FileSegment(final File file, final long offset, final long length) {
      this.file = file;
      this.offset = offset;
      this.length = length;
      scala.Predef..MODULE$.require(offset >= 0L, () -> "File segment offset cannot be negative (got " + this.offset() + ")");
      scala.Predef..MODULE$.require(length >= 0L, () -> "File segment length cannot be negative (got " + this.length() + ")");
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
