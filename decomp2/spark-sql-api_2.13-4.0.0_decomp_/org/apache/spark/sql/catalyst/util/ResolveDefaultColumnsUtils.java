package org.apache.spark.sql.catalyst.util;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M2qa\u0002\u0005\u0011\u0002\u0007\u0005Q\u0003C\u0003\u001d\u0001\u0011\u0005Q\u0004C\u0004\"\u0001\t\u0007I\u0011\u0001\u0012\t\u000f-\u0002!\u0019!C\u0001E\u001d)A\u0006\u0003E\u0001[\u0019)q\u0001\u0003E\u0001_!)\u0011'\u0002C\u0001e\tQ\"+Z:pYZ,G)\u001a4bk2$8i\u001c7v[:\u001cX\u000b^5mg*\u0011\u0011BC\u0001\u0005kRLGN\u0003\u0002\f\u0019\u0005A1-\u0019;bYf\u001cHO\u0003\u0002\u000e\u001d\u0005\u00191/\u001d7\u000b\u0005=\u0001\u0012!B:qCJ\\'BA\t\u0013\u0003\u0019\t\u0007/Y2iK*\t1#A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u0001-A\u0011qCG\u0007\u00021)\t\u0011$A\u0003tG\u0006d\u0017-\u0003\u0002\u001c1\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$C#\u0001\u0010\u0011\u0005]y\u0012B\u0001\u0011\u0019\u0005\u0011)f.\u001b;\u0002G\r+&KU#O)~#UIR!V\u0019R{6i\u0014'V\u001b:{V*\u0012+B\t\u0006#\u0016iX&F3V\t1\u0005\u0005\u0002%S5\tQE\u0003\u0002'O\u0005!A.\u00198h\u0015\u0005A\u0013\u0001\u00026bm\u0006L!AK\u0013\u0003\rM#(/\u001b8h\u0003\t*\u0005,S*U'~#UIR!V\u0019R{6i\u0014'V\u001b:{V*\u0012+B\t\u0006#\u0016iX&F3\u0006Q\"+Z:pYZ,G)\u001a4bk2$8i\u001c7v[:\u001cX\u000b^5mgB\u0011a&B\u0007\u0002\u0011M\u0019QA\u0006\u0019\u0011\u00059\u0002\u0011A\u0002\u001fj]&$h\bF\u0001.\u0001"
)
public interface ResolveDefaultColumnsUtils {
   void org$apache$spark$sql$catalyst$util$ResolveDefaultColumnsUtils$_setter_$CURRENT_DEFAULT_COLUMN_METADATA_KEY_$eq(final String x$1);

   void org$apache$spark$sql$catalyst$util$ResolveDefaultColumnsUtils$_setter_$EXISTS_DEFAULT_COLUMN_METADATA_KEY_$eq(final String x$1);

   String CURRENT_DEFAULT_COLUMN_METADATA_KEY();

   String EXISTS_DEFAULT_COLUMN_METADATA_KEY();

   static void $init$(final ResolveDefaultColumnsUtils $this) {
      $this.org$apache$spark$sql$catalyst$util$ResolveDefaultColumnsUtils$_setter_$CURRENT_DEFAULT_COLUMN_METADATA_KEY_$eq("CURRENT_DEFAULT");
      $this.org$apache$spark$sql$catalyst$util$ResolveDefaultColumnsUtils$_setter_$EXISTS_DEFAULT_COLUMN_METADATA_KEY_$eq("EXISTS_DEFAULT");
   }
}
