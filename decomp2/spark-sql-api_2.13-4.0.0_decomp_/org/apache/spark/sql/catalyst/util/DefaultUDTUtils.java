package org.apache.spark.sql.catalyst.util;

import org.apache.spark.sql.types.UserDefinedType;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A:a\u0001B\u0003\t\u0002%\tbAB\n\u0006\u0011\u0003IA\u0003C\u0003\u001f\u0003\u0011\u0005\u0001\u0005C\u0003\"\u0003\u0011\u0005#%A\bEK\u001a\fW\u000f\u001c;V\tR+F/\u001b7t\u0015\t1q!\u0001\u0003vi&d'B\u0001\u0005\n\u0003!\u0019\u0017\r^1msN$(B\u0001\u0006\f\u0003\r\u0019\u0018\u000f\u001c\u0006\u0003\u00195\tQa\u001d9be.T!AD\b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0001\u0012aA8sOB\u0011!#A\u0007\u0002\u000b\tyA)\u001a4bk2$X\u000b\u0012+Vi&d7oE\u0002\u0002+m\u0001\"AF\r\u000e\u0003]Q\u0011\u0001G\u0001\u0006g\u000e\fG.Y\u0005\u00035]\u0011a!\u00118z%\u00164\u0007C\u0001\n\u001d\u0013\tiRA\u0001\u0005V\tR+F/\u001b7t\u0003\u0019a\u0014N\\5u}\r\u0001A#A\t\u0002\u000bQ|'k\\<\u0015\u0007\r2\u0003\u0006\u0005\u0002\u0017I%\u0011Qe\u0006\u0002\u0004\u0003:L\b\"B\u0014\u0004\u0001\u0004\u0019\u0013!\u0002<bYV,\u0007\"B\u0015\u0004\u0001\u0004Q\u0013aA;eiB\u00191FL\u0012\u000e\u00031R!!L\u0005\u0002\u000bQL\b/Z:\n\u0005=b#aD+tKJ$UMZ5oK\u0012$\u0016\u0010]3"
)
public final class DefaultUDTUtils {
   public static Object toRow(final Object value, final UserDefinedType udt) {
      return DefaultUDTUtils$.MODULE$.toRow(value, udt);
   }
}
