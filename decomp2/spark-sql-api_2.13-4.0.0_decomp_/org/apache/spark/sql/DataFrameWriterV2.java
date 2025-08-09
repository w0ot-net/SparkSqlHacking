package org.apache.spark.sql;

import org.apache.spark.annotation.Experimental;
import org.apache.spark.sql.catalyst.analysis.NoSuchTableException;
import scala.collection.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.ScalaRunTime.;

@Experimental
@ScalaSignature(
   bytes = "\u0006\u0005\u0005ec!B\b\u0011\u0003\u0003I\u0002\"\u0002\u0019\u0001\t\u0003\t\u0004\"B\u001a\u0001\r\u0003\"\u0004\"B\"\u0001\t\u0003\"\u0005\"B\"\u0001\t\u0003b\u0005\"B\"\u0001\t\u0003\u0012\u0006\"B\"\u0001\r\u0003B\u0006\"B.\u0001\r\u0003b\u0006\"B.\u0001\r\u0003\"\u0007\"B7\u0001\r\u0003r\u0007\"\u0002:\u0001\r\u0003\u001a\bbBA\u0006\u0001\u0019\u0005\u0013Q\u0002\u0005\b\u00037\u0001a\u0011AA\u000f\u0011\u001d\ty\u0004\u0001D\u0001\u0003\u0003Bq!!\u0013\u0001\r\u0003\tiBA\tECR\fgI]1nK^\u0013\u0018\u000e^3s-JR!!\u0005\n\u0002\u0007M\fHN\u0003\u0002\u0014)\u0005)1\u000f]1sW*\u0011QCF\u0001\u0007CB\f7\r[3\u000b\u0003]\t1a\u001c:h\u0007\u0001)\"AG\u0014\u0014\u0007\u0001Y\u0012\u0005\u0005\u0002\u001d?5\tQDC\u0001\u001f\u0003\u0015\u00198-\u00197b\u0013\t\u0001SD\u0001\u0004B]f\u0014VM\u001a\t\u0004E\r*S\"\u0001\t\n\u0005\u0011\u0002\"!E\"sK\u0006$X\rV1cY\u0016<&/\u001b;feB\u0011ae\n\u0007\u0001\t\u0015A\u0003A1\u0001*\u0005\u0005!\u0016C\u0001\u0016.!\ta2&\u0003\u0002-;\t9aj\u001c;iS:<\u0007C\u0001\u000f/\u0013\tySDA\u0002B]f\fa\u0001P5oSRtD#\u0001\u001a\u0011\u0007\t\u0002Q%A\u0003vg&tw\r\u0006\u00026m5\t\u0001\u0001C\u00038\u0005\u0001\u0007\u0001(\u0001\u0005qe>4\u0018\u000eZ3s!\tI\u0004I\u0004\u0002;}A\u00111(H\u0007\u0002y)\u0011Q\bG\u0001\u0007yI|w\u000e\u001e \n\u0005}j\u0012A\u0002)sK\u0012,g-\u0003\u0002B\u0005\n11\u000b\u001e:j]\u001eT!aP\u000f\u0002\r=\u0004H/[8o)\r)Ti\u0012\u0005\u0006\r\u000e\u0001\r\u0001O\u0001\u0004W\u0016L\b\"\u0002%\u0004\u0001\u0004I\u0015!\u0002<bYV,\u0007C\u0001\u000fK\u0013\tYUDA\u0004C_>dW-\u00198\u0015\u0007Uje\nC\u0003G\t\u0001\u0007\u0001\bC\u0003I\t\u0001\u0007q\n\u0005\u0002\u001d!&\u0011\u0011+\b\u0002\u0005\u0019>tw\rF\u00026'RCQAR\u0003A\u0002aBQ\u0001S\u0003A\u0002U\u0003\"\u0001\b,\n\u0005]k\"A\u0002#pk\ndW\rF\u000263jCQA\u0012\u0004A\u0002aBQ\u0001\u0013\u0004A\u0002a\nqa\u001c9uS>t7\u000f\u0006\u00026;\")1l\u0002a\u0001=B!qL\u0019\u001d9\u001b\u0005\u0001'BA1\u001e\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003G\u0002\u00141!T1q)\t)T\rC\u0003\\\u0011\u0001\u0007a\r\u0005\u0003hYbBT\"\u00015\u000b\u0005%T\u0017\u0001B;uS2T\u0011a[\u0001\u0005U\u00064\u0018-\u0003\u0002dQ\u0006iA/\u00192mKB\u0013x\u000e]3sif$2!N8r\u0011\u0015\u0001\u0018\u00021\u00019\u0003!\u0001(o\u001c9feRL\b\"\u0002%\n\u0001\u0004A\u0014!\u00049beRLG/[8oK\u0012\u0014\u0015\u0010F\u00026ifDQ!\u001e\u0006A\u0002Y\faaY8mk6t\u0007C\u0001\u0012x\u0013\tA\bC\u0001\u0004D_2,XN\u001c\u0005\u0006u*\u0001\ra_\u0001\bG>dW/\u001c8t!\raBP^\u0005\u0003{v\u0011!\u0002\u0010:fa\u0016\fG/\u001a3?Q\tQq\u0010\u0005\u0003\u0002\u0002\u0005\u001dQBAA\u0002\u0015\r\t)!H\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA\u0005\u0003\u0007\u0011qA^1sCJ<7/A\u0005dYV\u001cH/\u001a:CsR)Q'a\u0004\u0002\u0014!1\u0011\u0011C\u0006A\u0002a\nqaY8m\u001d\u0006lW\rC\u0004\u0002\u0016-\u0001\r!a\u0006\u0002\u0011\r|GNT1nKN\u00042\u0001\b?9Q\tYq0\u0001\u0004baB,g\u000e\u001a\u000b\u0003\u0003?\u00012\u0001HA\u0011\u0013\r\t\u0019#\b\u0002\u0005+:LG\u000fK\u0003\r\u0003O\ti\u0004E\u0003\u001d\u0003S\ti#C\u0002\u0002,u\u0011a\u0001\u001e5s_^\u001c\b\u0003BA\u0018\u0003si!!!\r\u000b\t\u0005M\u0012QG\u0001\tC:\fG._:jg*\u0019\u0011q\u0007\t\u0002\u0011\r\fG/\u00197zgRLA!a\u000f\u00022\t!bj\\*vG\"$\u0016M\u00197f\u000bb\u001cW\r\u001d;j_:\u001c#!!\f\u0002\u0013=4XM]<sSR,G\u0003BA\u0010\u0003\u0007Ba!!\u0012\u000e\u0001\u00041\u0018!C2p]\u0012LG/[8oQ\u0015i\u0011qEA\u001f\u0003Myg/\u001a:xe&$X\rU1si&$\u0018n\u001c8tQ\u0015q\u0011qEA\u001fQ\r\u0001\u0011q\n\t\u0005\u0003#\n)&\u0004\u0002\u0002T)\u0019\u0011Q\u0001\n\n\t\u0005]\u00131\u000b\u0002\r\u000bb\u0004XM]5nK:$\u0018\r\u001c"
)
public abstract class DataFrameWriterV2 implements CreateTableWriter {
   public DataFrameWriterV2 partitionedBy(final Column column, final Column... columns) {
      return this.partitionedBy(column, (Seq).MODULE$.wrapRefArray(columns));
   }

   public DataFrameWriterV2 clusterBy(final String colName, final String... colNames) {
      return this.clusterBy(colName, (Seq).MODULE$.wrapRefArray((Object[])colNames));
   }

   public abstract DataFrameWriterV2 using(final String provider);

   public DataFrameWriterV2 option(final String key, final boolean value) {
      return this.option(key, Boolean.toString(value));
   }

   public DataFrameWriterV2 option(final String key, final long value) {
      return this.option(key, Long.toString(value));
   }

   public DataFrameWriterV2 option(final String key, final double value) {
      return this.option(key, Double.toString(value));
   }

   public abstract DataFrameWriterV2 option(final String key, final String value);

   public abstract DataFrameWriterV2 options(final Map options);

   public abstract DataFrameWriterV2 options(final java.util.Map options);

   public abstract DataFrameWriterV2 tableProperty(final String property, final String value);

   public abstract DataFrameWriterV2 partitionedBy(final Column column, final Seq columns);

   public abstract DataFrameWriterV2 clusterBy(final String colName, final Seq colNames);

   public abstract void append() throws NoSuchTableException;

   public abstract void overwrite(final Column condition) throws NoSuchTableException;

   public abstract void overwritePartitions() throws NoSuchTableException;

   public DataFrameWriterV2() {
      WriteConfigMethods.$init$(this);
   }
}
