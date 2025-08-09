package org.apache.spark.ml.util;

import java.io.IOException;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005)3q\u0001B\u0003\u0011\u0002\u0007\u0005\u0001\u0003C\u0003\u0018\u0001\u0011\u0005\u0001\u0004C\u0003\u001d\u0001\u0019\u0005Q\u0004C\u0003,\u0001\u0011\u0005AF\u0001\u0006N\u0019^\u0013\u0018\u000e^1cY\u0016T!AB\u0004\u0002\tU$\u0018\u000e\u001c\u0006\u0003\u0011%\t!!\u001c7\u000b\u0005)Y\u0011!B:qCJ\\'B\u0001\u0007\u000e\u0003\u0019\t\u0007/Y2iK*\ta\"A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u0001#A\u0011!#F\u0007\u0002')\tA#A\u0003tG\u0006d\u0017-\u0003\u0002\u0017'\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$C#A\r\u0011\u0005IQ\u0012BA\u000e\u0014\u0005\u0011)f.\u001b;\u0002\u000b]\u0014\u0018\u000e^3\u0016\u0003y\u0001\"a\b\u0011\u000e\u0003\u0015I!!I\u0003\u0003\u00115cuK]5uKJD3AA\u0012*!\t!s%D\u0001&\u0015\t1\u0013\"\u0001\u0006b]:|G/\u0019;j_:L!\u0001K\u0013\u0003\u000bMKgnY3\"\u0003)\nQ!\r\u00187]A\nAa]1wKR\u0011\u0011$\f\u0005\u0006]\r\u0001\raL\u0001\u0005a\u0006$\b\u000e\u0005\u00021o9\u0011\u0011'\u000e\t\u0003eMi\u0011a\r\u0006\u0003i=\ta\u0001\u0010:p_Rt\u0014B\u0001\u001c\u0014\u0003\u0019\u0001&/\u001a3fM&\u0011\u0001(\u000f\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005Y\u001a\u0002fA\u0002$S!\u001a1\u0001P$\u0011\u0007Iit(\u0003\u0002?'\t1A\u000f\u001b:poN\u0004\"\u0001Q#\u000e\u0003\u0005S!AQ\"\u0002\u0005%|'\"\u0001#\u0002\t)\fg/Y\u0005\u0003\r\u0006\u00131\"S(Fq\u000e,\u0007\u000f^5p]\u0006\n\u0001*\u0001 JM\u0002\"\b.\u001a\u0011j]B,H\u000f\t9bi\"\u0004\u0013\r\u001c:fC\u0012L\b%\u001a=jgR\u001c\bEY;uA=4XM]<sSR,\u0007%[:!]>$\b%\u001a8bE2,GM\f\u0015\u0004\u0001\rJ\u0003"
)
public interface MLWritable {
   MLWriter write();

   // $FF: synthetic method
   static void save$(final MLWritable $this, final String path) {
      $this.save(path);
   }

   default void save(final String path) throws IOException {
      this.write().save(path);
   }

   static void $init$(final MLWritable $this) {
   }
}
