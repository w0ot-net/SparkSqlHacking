package org.apache.spark.util.collection;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y3a!\u0003\u0006\u0002\u00029!\u0002\"\u0002\u000f\u0001\t\u0003q\u0002\"B\u0018\u0001\t\u0003\u0001\u0004\"B\u0019\u0001\r#\u0011\u0004\"B\u0019\u0001\t\u0003Q\u0004\"B \u0001\r\u0003\u0001\u0005\"B%\u0001\r\u0003Q\u0005\"B*\u0001\r\u0003!\u0006\"B.\u0001\r\u0003a&AD*peR$\u0015\r^1G_Jl\u0017\r\u001e\u0006\u0003\u00171\t!bY8mY\u0016\u001cG/[8o\u0015\tia\"\u0001\u0003vi&d'BA\b\u0011\u0003\u0015\u0019\b/\u0019:l\u0015\t\t\"#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002'\u0005\u0019qN]4\u0016\u0007U\u0019Sf\u0005\u0002\u0001-A\u0011qCG\u0007\u00021)\t\u0011$A\u0003tG\u0006d\u0017-\u0003\u0002\u001c1\t1\u0011I\\=SK\u001a\fa\u0001P5oSRt4\u0001\u0001\u000b\u0002?A!\u0001\u0005A\u0011-\u001b\u0005Q\u0001C\u0001\u0012$\u0019\u0001!Q\u0001\n\u0001C\u0002\u0015\u0012\u0011aS\t\u0003M%\u0002\"aF\u0014\n\u0005!B\"a\u0002(pi\"Lgn\u001a\t\u0003/)J!a\u000b\r\u0003\u0007\u0005s\u0017\u0010\u0005\u0002#[\u0011)a\u0006\u0001b\u0001K\t1!)\u001e4gKJ\faA\\3x\u0017\u0016LH#A\u0011\u0002\r\u001d,GoS3z)\r\t3'\u000e\u0005\u0006i\r\u0001\r\u0001L\u0001\u0005I\u0006$\u0018\rC\u00037\u0007\u0001\u0007q'A\u0002q_N\u0004\"a\u0006\u001d\n\u0005eB\"aA%oiR!\u0011e\u000f\u001f>\u0011\u0015!D\u00011\u0001-\u0011\u00151D\u00011\u00018\u0011\u0015qD\u00011\u0001\"\u0003\u0015\u0011X-^:f\u0003\u0011\u0019x/\u00199\u0015\t\u0005#Ui\u0012\t\u0003/\tK!a\u0011\r\u0003\tUs\u0017\u000e\u001e\u0005\u0006i\u0015\u0001\r\u0001\f\u0005\u0006\r\u0016\u0001\raN\u0001\u0005a>\u001c\b\u0007C\u0003I\u000b\u0001\u0007q'\u0001\u0003q_N\f\u0014aC2paf,E.Z7f]R$R!Q&N\u001fFCQ\u0001\u0014\u0004A\u00021\n1a\u001d:d\u0011\u0015qe\u00011\u00018\u0003\u0019\u0019(o\u0019)pg\")\u0001K\u0002a\u0001Y\u0005\u0019Am\u001d;\t\u000bI3\u0001\u0019A\u001c\u0002\r\u0011\u001cH\u000fU8t\u0003%\u0019w\u000e]=SC:<W\r\u0006\u0004B+Z;\u0006,\u0017\u0005\u0006\u0019\u001e\u0001\r\u0001\f\u0005\u0006\u001d\u001e\u0001\ra\u000e\u0005\u0006!\u001e\u0001\r\u0001\f\u0005\u0006%\u001e\u0001\ra\u000e\u0005\u00065\u001e\u0001\raN\u0001\u0007Y\u0016tw\r\u001e5\u0002\u0011\u0005dGn\\2bi\u0016$\"\u0001L/\t\u000biC\u0001\u0019A\u001c"
)
public abstract class SortDataFormat {
   public Object newKey() {
      return null;
   }

   public abstract Object getKey(final Object data, final int pos);

   public Object getKey(final Object data, final int pos, final Object reuse) {
      return this.getKey(data, pos);
   }

   public abstract void swap(final Object data, final int pos0, final int pos1);

   public abstract void copyElement(final Object src, final int srcPos, final Object dst, final int dstPos);

   public abstract void copyRange(final Object src, final int srcPos, final Object dst, final int dstPos, final int length);

   public abstract Object allocate(final int length);
}
