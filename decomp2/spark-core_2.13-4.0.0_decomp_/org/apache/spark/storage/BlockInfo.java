package org.apache.spark.storage;

import scala.Predef.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u4Q!\u0007\u000e\u00015\tB\u0001\"\u000b\u0001\u0003\u0006\u0004%\ta\u000b\u0005\ta\u0001\u0011\t\u0011)A\u0005Y!A\u0011\u0007\u0001BC\u0002\u0013\u0005!\u0007\u0003\u0005?\u0001\t\u0005\t\u0015!\u00034\u0011!1\u0005A!b\u0001\n\u00039\u0005\u0002C&\u0001\u0005\u0003\u0005\u000b\u0011\u0002%\t\u000b1\u0003A\u0011A'\t\u000bY\u0003A\u0011A,\t\u000bm\u0003A\u0011\u0001/\t\r\t\u0004\u0001\u0015)\u0003Y\u0011\u0015\u0019\u0007\u0001\"\u0001e\u0011\u0015A\u0007\u0001\"\u0001j\u0011\u0019a\u0007\u0001)Q\u0005K\")Q\u000e\u0001C\u0001/\")a\u000e\u0001C\u0001_\"1!\u000f\u0001Q!\naCQa\u001d\u0001\u0005\nQ<a!\u001e\u000e\t\u0002i1hAB\r\u001b\u0011\u0003Qr\u000fC\u0003M'\u0011\u0005\u0001\u0010C\u0004z'\t\u0007I\u0011A,\t\ri\u001c\u0002\u0015!\u0003Y\u0011\u001dY8C1A\u0005\u0002]Ca\u0001`\n!\u0002\u0013A&!\u0003\"m_\u000e\\\u0017J\u001c4p\u0015\tYB$A\u0004ti>\u0014\u0018mZ3\u000b\u0005uq\u0012!B:qCJ\\'BA\u0010!\u0003\u0019\t\u0007/Y2iK*\t\u0011%A\u0002pe\u001e\u001c\"\u0001A\u0012\u0011\u0005\u0011:S\"A\u0013\u000b\u0003\u0019\nQa]2bY\u0006L!\u0001K\u0013\u0003\r\u0005s\u0017PU3g\u0003\u0015aWM^3m\u0007\u0001)\u0012\u0001\f\t\u0003[9j\u0011AG\u0005\u0003_i\u0011Ab\u0015;pe\u0006<W\rT3wK2\fa\u0001\\3wK2\u0004\u0013\u0001C2mCN\u001cH+Y4\u0016\u0003M\u0002$\u0001\u000e\u001f\u0011\u0007UB$(D\u00017\u0015\t9T%A\u0004sK\u001adWm\u0019;\n\u0005e2$\u0001C\"mCN\u001cH+Y4\u0011\u0005mbD\u0002\u0001\u0003\n{\u0011\t\t\u0011!A\u0003\u0002}\u00121a\u0018\u00132\u0003%\u0019G.Y:t)\u0006<\u0007%\u0005\u0002A\u0007B\u0011A%Q\u0005\u0003\u0005\u0016\u0012qAT8uQ&tw\r\u0005\u0002%\t&\u0011Q)\n\u0002\u0004\u0003:L\u0018A\u0003;fY2l\u0015m\u001d;feV\t\u0001\n\u0005\u0002%\u0013&\u0011!*\n\u0002\b\u0005>|G.Z1o\u0003-!X\r\u001c7NCN$XM\u001d\u0011\u0002\rqJg.\u001b;?)\u0011qu\nU+\u0011\u00055\u0002\u0001\"B\u0015\b\u0001\u0004a\u0003\"B\u0019\b\u0001\u0004\t\u0006G\u0001*U!\r)\u0004h\u0015\t\u0003wQ#\u0011\"\u0010)\u0002\u0002\u0003\u0005)\u0011A \t\u000b\u0019;\u0001\u0019\u0001%\u0002\tML'0Z\u000b\u00021B\u0011A%W\u0005\u00035\u0016\u0012A\u0001T8oO\u0006A1/\u001b>f?\u0012*\u0017\u000f\u0006\u0002^AB\u0011AEX\u0005\u0003?\u0016\u0012A!\u00168ji\")\u0011-\u0003a\u00011\u0006\t1/A\u0003`g&TX-A\u0006sK\u0006$WM]\"pk:$X#A3\u0011\u0005\u00112\u0017BA4&\u0005\rIe\u000e^\u0001\u0010e\u0016\fG-\u001a:D_VtGo\u0018\u0013fcR\u0011QL\u001b\u0005\u0006W2\u0001\r!Z\u0001\u0002G\u0006aqL]3bI\u0016\u00148i\\;oi\u0006QqO]5uKJ$\u0016m]6\u0002\u001d]\u0014\u0018\u000e^3s)\u0006\u001c8n\u0018\u0013fcR\u0011Q\f\u001d\u0005\u0006c>\u0001\r\u0001W\u0001\u0002i\u0006Yql\u001e:ji\u0016\u0014H+Y:l\u0003=\u0019\u0007.Z2l\u0013:4\u0018M]5b]R\u001cH#A/\u0002\u0013\tcwnY6J]\u001a|\u0007CA\u0017\u0014'\t\u00192\u0005F\u0001w\u0003%qujX,S\u0013R+%+\u0001\u0006O\u001f~;&+\u0013+F%\u0002\nqBT(O?R\u000b5kS0X%&#VIU\u0001\u0011\u001d>su\fV!T\u0017~;&+\u0013+F%\u0002\u0002"
)
public class BlockInfo {
   private final StorageLevel level;
   private final ClassTag classTag;
   private final boolean tellMaster;
   private long _size;
   private int _readerCount;
   private long _writerTask;

   public static long NON_TASK_WRITER() {
      return BlockInfo$.MODULE$.NON_TASK_WRITER();
   }

   public static long NO_WRITER() {
      return BlockInfo$.MODULE$.NO_WRITER();
   }

   public StorageLevel level() {
      return this.level;
   }

   public ClassTag classTag() {
      return this.classTag;
   }

   public boolean tellMaster() {
      return this.tellMaster;
   }

   public long size() {
      return this._size;
   }

   public void size_$eq(final long s) {
      this._size = s;
      this.checkInvariants();
   }

   public int readerCount() {
      return this._readerCount;
   }

   public void readerCount_$eq(final int c) {
      this._readerCount = c;
      this.checkInvariants();
   }

   public long writerTask() {
      return this._writerTask;
   }

   public void writerTask_$eq(final long t) {
      this._writerTask = t;
      this.checkInvariants();
   }

   private void checkInvariants() {
      .MODULE$.assert(this._readerCount >= 0);
      .MODULE$.assert(this._readerCount == 0 || this._writerTask == BlockInfo$.MODULE$.NO_WRITER());
   }

   public BlockInfo(final StorageLevel level, final ClassTag classTag, final boolean tellMaster) {
      this.level = level;
      this.classTag = classTag;
      this.tellMaster = tellMaster;
      this._size = 0L;
      this._readerCount = 0;
      this._writerTask = BlockInfo$.MODULE$.NO_WRITER();
      this.checkInvariants();
   }
}
