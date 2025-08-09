package org.apache.spark.sql.catalyst.expressions;

import org.apache.spark.sql.types.StructType;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00193Aa\u0002\u0005\u0001+!I!\u0004\u0001B\u0001B\u0003%1\u0004\n\u0005\tK\u0001\u0011)\u0019!C!M!AQ\u0006\u0001B\u0001B\u0003%q\u0005C\u0003/\u0001\u0011\u0005q\u0006C\u0003/\u0001\u0011E1\u0007C\u00035\u0001\u0011\u0005SG\u0001\u000bHK:,'/[2S_^<\u0016\u000e\u001e5TG\",W.\u0019\u0006\u0003\u0013)\t1\"\u001a=qe\u0016\u001c8/[8og*\u00111\u0002D\u0001\tG\u0006$\u0018\r\\=ti*\u0011QBD\u0001\u0004gFd'BA\b\u0011\u0003\u0015\u0019\b/\u0019:l\u0015\t\t\"#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002'\u0005\u0019qN]4\u0004\u0001M\u0011\u0001A\u0006\t\u0003/ai\u0011\u0001C\u0005\u00033!\u0011!bR3oKJL7MU8x\u0003\u00191\u0018\r\\;fgB\u0019AdH\u0011\u000e\u0003uQ\u0011AH\u0001\u0006g\u000e\fG.Y\u0005\u0003Au\u0011Q!\u0011:sCf\u0004\"\u0001\b\u0012\n\u0005\rj\"aA!os&\u0011!\u0004G\u0001\u0007g\u000eDW-\\1\u0016\u0003\u001d\u0002\"\u0001K\u0016\u000e\u0003%R!A\u000b\u0007\u0002\u000bQL\b/Z:\n\u00051J#AC*ueV\u001cG\u000fV=qK\u000691o\u00195f[\u0006\u0004\u0013A\u0002\u001fj]&$h\bF\u00021cI\u0002\"a\u0006\u0001\t\u000bi!\u0001\u0019A\u000e\t\u000b\u0015\"\u0001\u0019A\u0014\u0015\u0003A\n!BZ5fY\u0012Le\u000eZ3y)\t1\u0014\b\u0005\u0002\u001do%\u0011\u0001(\b\u0002\u0004\u0013:$\b\"\u0002\u001e\u0007\u0001\u0004Y\u0014\u0001\u00028b[\u0016\u0004\"\u0001P\"\u000f\u0005u\n\u0005C\u0001 \u001e\u001b\u0005y$B\u0001!\u0015\u0003\u0019a$o\\8u}%\u0011!)H\u0001\u0007!J,G-\u001a4\n\u0005\u0011+%AB*ue&twM\u0003\u0002C;\u0001"
)
public class GenericRowWithSchema extends GenericRow {
   private final StructType schema;

   public StructType schema() {
      return this.schema;
   }

   public int fieldIndex(final String name) {
      return this.schema().fieldIndex(name);
   }

   public GenericRowWithSchema(final Object[] values, final StructType schema) {
      super(values);
      this.schema = schema;
   }

   public GenericRowWithSchema() {
      this((Object[])null, (StructType)null);
   }
}
