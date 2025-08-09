package org.apache.spark.sql;

import org.apache.spark.annotation.Stable;
import org.apache.spark.sql.internal.ColumnNode;
import scala.reflect.ScalaSignature;

@Stable
@ScalaSignature(
   bytes = "\u0006\u0005A3AAB\u0004\u0001!!Ia\u0003\u0001B\u0001B\u0003%q#\b\u0005\n=\u0001\u0011)\u0019!C\u0001\u000f}A\u0001\"\r\u0001\u0003\u0002\u0003\u0006I\u0001\t\u0005\u0006e\u0001!\ta\r\u0005\u0006u\u0001!\te\u000f\u0002\f)f\u0004X\rZ\"pYVlgN\u0003\u0002\t\u0013\u0005\u00191/\u001d7\u000b\u0005)Y\u0011!B:qCJ\\'B\u0001\u0007\u000e\u0003\u0019\t\u0007/Y2iK*\ta\"A\u0002pe\u001e\u001c\u0001!F\u0002\u0012m\u0015\u001a\"\u0001\u0001\n\u0011\u0005M!R\"A\u0004\n\u0005U9!AB\"pYVlg.\u0001\u0003o_\u0012,\u0007C\u0001\r\u001c\u001b\u0005I\"B\u0001\u000e\b\u0003!Ig\u000e^3s]\u0006d\u0017B\u0001\u000f\u001a\u0005)\u0019u\u000e\\;n]:{G-Z\u0005\u0003-Q\tq!\u001a8d_\u0012,'/F\u0001!!\r\u0019\u0012eI\u0005\u0003E\u001d\u0011q!\u00128d_\u0012,'\u000f\u0005\u0002%K1\u0001A!\u0002\u0014\u0001\u0005\u00049#!A+\u0012\u0005!r\u0003CA\u0015-\u001b\u0005Q#\"A\u0016\u0002\u000bM\u001c\u0017\r\\1\n\u00055R#a\u0002(pi\"Lgn\u001a\t\u0003S=J!\u0001\r\u0016\u0003\u0007\u0005s\u00170\u0001\u0005f]\u000e|G-\u001a:!\u0003\u0019a\u0014N\\5u}Q\u0019A\u0007O\u001d\u0011\tM\u0001Qg\t\t\u0003IY\"aa\u000e\u0001\t\u0006\u00049#!\u0001+\t\u000bY!\u0001\u0019A\f\t\u000by!\u0001\u0019\u0001\u0011\u0002\t9\fW.\u001a\u000b\u0003iqBQ!P\u0003A\u0002y\nQ!\u00197jCN\u0004\"a\u0010$\u000f\u0005\u0001#\u0005CA!+\u001b\u0005\u0011%BA\"\u0010\u0003\u0019a$o\\8u}%\u0011QIK\u0001\u0007!J,G-\u001a4\n\u0005\u001dC%AB*ue&twM\u0003\u0002FU!\u0012\u0001A\u0013\t\u0003\u0017:k\u0011\u0001\u0014\u0006\u0003\u001b&\t!\"\u00198o_R\fG/[8o\u0013\tyEJ\u0001\u0004Ti\u0006\u0014G.\u001a"
)
public class TypedColumn extends Column {
   private final Encoder encoder;

   public Encoder encoder() {
      return this.encoder;
   }

   public TypedColumn name(final String alias) {
      return new TypedColumn(super.name(alias).node(), this.encoder());
   }

   public TypedColumn(final ColumnNode node, final Encoder encoder) {
      super(node);
      this.encoder = encoder;
   }
}
