package org.apache.spark.sql;

import org.apache.spark.sql.catalyst.analysis.CannotReplaceMissingTableException;
import org.apache.spark.sql.catalyst.analysis.TableAlreadyExistsException;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005Q4q\u0001C\u0005\u0011\u0002G\u0005!\u0003C\u0003+\u0001\u0019\u00051\u0006C\u0003=\u0001\u0019\u00051\u0006C\u0003D\u0001\u0019\u00051\u0006C\u0003E\u0001\u0019\u0005Q\tC\u0003X\u0001\u0019\u0005\u0001\fC\u0003k\u0001\u0019\u00051\u000eC\u0003o\u0001\u0019\u0005qNA\tDe\u0016\fG/\u001a+bE2,wK]5uKJT!AC\u0006\u0002\u0007M\fHN\u0003\u0002\r\u001b\u0005)1\u000f]1sW*\u0011abD\u0001\u0007CB\f7\r[3\u000b\u0003A\t1a\u001c:h\u0007\u0001)\"aE\u0011\u0014\u0007\u0001!\"\u0004\u0005\u0002\u001615\taCC\u0001\u0018\u0003\u0015\u00198-\u00197b\u0013\tIbC\u0001\u0004B]f\u0014VM\u001a\t\u00047qqR\"A\u0005\n\u0005uI!AE,sSR,7i\u001c8gS\u001elU\r\u001e5pIN\u00042a\u0007\u0001 !\t\u0001\u0013\u0005\u0004\u0001\u0005\u000b\t\u0002!\u0019A\u0012\u0003\u0003Q\u000b\"\u0001J\u0014\u0011\u0005U)\u0013B\u0001\u0014\u0017\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!\u0006\u0015\n\u0005%2\"aA!os\u000611M]3bi\u0016$\u0012\u0001\f\t\u0003+5J!A\f\f\u0003\tUs\u0017\u000e\u001e\u0015\u0004\u0003AZ\u0004cA\u000b2g%\u0011!G\u0006\u0002\u0007i\"\u0014xn^:\u0011\u0005QJT\"A\u001b\u000b\u0005Y:\u0014\u0001C1oC2L8/[:\u000b\u0005aJ\u0011\u0001C2bi\u0006d\u0017p\u001d;\n\u0005i*$a\u0007+bE2,\u0017\t\u001c:fC\u0012LX\t_5tiN,\u0005pY3qi&|gnI\u00014\u0003\u001d\u0011X\r\u001d7bG\u0016D3A\u0001 C!\r)\u0012g\u0010\t\u0003i\u0001K!!Q\u001b\u0003E\r\u000bgN\\8u%\u0016\u0004H.Y2f\u001b&\u001c8/\u001b8h)\u0006\u0014G.Z#yG\u0016\u0004H/[8oG\u0005y\u0014aD2sK\u0006$Xm\u0014:SKBd\u0017mY3\u0002\u001bA\f'\u000f^5uS>tW\r\u001a\"z)\rqbi\u0013\u0005\u0006\u000f\u0012\u0001\r\u0001S\u0001\u0007G>dW/\u001c8\u0011\u0005mI\u0015B\u0001&\n\u0005\u0019\u0019u\u000e\\;n]\")A\n\u0002a\u0001\u001b\u000691m\u001c7v[:\u001c\bcA\u000bO\u0011&\u0011qJ\u0006\u0002\u000byI,\u0007/Z1uK\u0012t\u0004F\u0001\u0003R!\t\u0011V+D\u0001T\u0015\t!f#\u0001\u0006b]:|G/\u0019;j_:L!AV*\u0003\u000fY\f'/\u0019:hg\u0006I1\r\\;ti\u0016\u0014()\u001f\u000b\u0004=e3\u0007\"\u0002.\u0006\u0001\u0004Y\u0016aB2pY:\u000bW.\u001a\t\u00039\u000et!!X1\u0011\u0005y3R\"A0\u000b\u0005\u0001\f\u0012A\u0002\u001fs_>$h(\u0003\u0002c-\u00051\u0001K]3eK\u001aL!\u0001Z3\u0003\rM#(/\u001b8h\u0015\t\u0011g\u0003C\u0003h\u000b\u0001\u0007\u0001.\u0001\u0005d_2t\u0015-\\3t!\r)bj\u0017\u0015\u0003\u000bE\u000bQ!^:j]\u001e$\"A\b7\t\u000b54\u0001\u0019A.\u0002\u0011A\u0014xN^5eKJ\fQ\u0002^1cY\u0016\u0004&o\u001c9feRLHc\u0001\u0010qe\")\u0011o\u0002a\u00017\u0006A\u0001O]8qKJ$\u0018\u0010C\u0003t\u000f\u0001\u00071,A\u0003wC2,X\r"
)
public interface CreateTableWriter extends WriteConfigMethods {
   // $FF: synthetic method
   static CreateTableWriter partitionedBy$(final CreateTableWriter $this, final Column column, final Column... columns) {
      return $this.partitionedBy(column, columns);
   }

   default CreateTableWriter partitionedBy(final Column column, final Column... columns) {
      return this.partitionedBy(column, (Seq).MODULE$.wrapRefArray(columns));
   }

   // $FF: synthetic method
   static CreateTableWriter clusterBy$(final CreateTableWriter $this, final String colName, final String... colNames) {
      return $this.clusterBy(colName, colNames);
   }

   default CreateTableWriter clusterBy(final String colName, final String... colNames) {
      return this.clusterBy(colName, (Seq).MODULE$.wrapRefArray((Object[])colNames));
   }

   void create() throws TableAlreadyExistsException;

   void replace() throws CannotReplaceMissingTableException;

   void createOrReplace();

   CreateTableWriter partitionedBy(final Column column, final Seq columns);

   CreateTableWriter clusterBy(final String colName, final Seq colNames);

   CreateTableWriter using(final String provider);

   CreateTableWriter tableProperty(final String property, final String value);
}
