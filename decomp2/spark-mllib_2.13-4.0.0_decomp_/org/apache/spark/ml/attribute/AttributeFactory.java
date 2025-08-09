package org.apache.spark.ml.attribute;

import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.NumericType;
import org.apache.spark.sql.types.StructField;
import scala.Predef.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q2\u0001\"\u0002\u0004\u0011\u0002\u0007\u0005a\u0001\u0005\u0005\u0006/\u0001!\t!\u0007\u0005\u0007;\u00011\tA\u0002\u0010\t\r5\u0002A\u0011\u0001\u0005/\u0011\u0015I\u0004\u0001\"\u0001;\u0005A\tE\u000f\u001e:jEV$XMR1di>\u0014\u0018P\u0003\u0002\b\u0011\u0005I\u0011\r\u001e;sS\n,H/\u001a\u0006\u0003\u0013)\t!!\u001c7\u000b\u0005-a\u0011!B:qCJ\\'BA\u0007\u000f\u0003\u0019\t\u0007/Y2iK*\tq\"A\u0002pe\u001e\u001c\"\u0001A\t\u0011\u0005I)R\"A\n\u000b\u0003Q\tQa]2bY\u0006L!AF\n\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uI\r\u0001A#\u0001\u000e\u0011\u0005IY\u0012B\u0001\u000f\u0014\u0005\u0011)f.\u001b;\u0002\u0019\u0019\u0014x.\\'fi\u0006$\u0017\r^1\u0015\u0005}\u0019\u0003C\u0001\u0011\"\u001b\u00051\u0011B\u0001\u0012\u0007\u0005%\tE\u000f\u001e:jEV$X\rC\u0003%\u0005\u0001\u0007Q%\u0001\u0005nKR\fG-\u0019;b!\t13&D\u0001(\u0015\tA\u0013&A\u0003usB,7O\u0003\u0002+\u0015\u0005\u00191/\u001d7\n\u00051:#\u0001C'fi\u0006$\u0017\r^1\u0002#\u0011,7m\u001c3f'R\u0014Xo\u0019;GS\u0016dG\rF\u0002 _QBQ\u0001M\u0002A\u0002E\nQAZ5fY\u0012\u0004\"A\n\u001a\n\u0005M:#aC*ueV\u001cGOR5fY\u0012DQ!N\u0002A\u0002Y\nA\u0002\u001d:fg\u0016\u0014h/\u001a(b[\u0016\u0004\"AE\u001c\n\u0005a\u001a\"a\u0002\"p_2,\u0017M\\\u0001\u0010MJ|Wn\u0015;sk\u000e$h)[3mIR\u0011qd\u000f\u0005\u0006a\u0011\u0001\r!\r"
)
public interface AttributeFactory {
   Attribute fromMetadata(final Metadata metadata);

   // $FF: synthetic method
   static Attribute decodeStructField$(final AttributeFactory $this, final StructField field, final boolean preserveName) {
      return $this.decodeStructField(field, preserveName);
   }

   default Attribute decodeStructField(final StructField field, final boolean preserveName) {
      .MODULE$.require(field.dataType() instanceof NumericType);
      Metadata metadata = field.metadata();
      String mlAttr = AttributeKeys$.MODULE$.ML_ATTR();
      if (metadata.contains(mlAttr)) {
         Attribute attr = this.fromMetadata(metadata.getMetadata(mlAttr));
         return preserveName ? attr : attr.withName(field.name());
      } else {
         return UnresolvedAttribute$.MODULE$;
      }
   }

   // $FF: synthetic method
   static Attribute fromStructField$(final AttributeFactory $this, final StructField field) {
      return $this.fromStructField(field);
   }

   default Attribute fromStructField(final StructField field) {
      return this.decodeStructField(field, false);
   }

   static void $init$(final AttributeFactory $this) {
   }
}
