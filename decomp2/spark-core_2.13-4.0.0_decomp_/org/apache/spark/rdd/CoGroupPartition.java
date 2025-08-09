package org.apache.spark.rdd;

import org.apache.spark.Partition;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000553Q\u0001C\u0005\u0001\u0017EA\u0001\"\u000b\u0001\u0003\u0006\u0004%\tE\u000b\u0005\t]\u0001\u0011\t\u0011)A\u0005W!Aq\u0006\u0001BC\u0002\u0013\u0005\u0001\u0007\u0003\u0005<\u0001\t\u0005\t\u0015!\u00032\u0011\u0015a\u0004\u0001\"\u0001>\u0011\u0015\t\u0005\u0001\"\u0011C\u0011\u0015\u0019\u0005\u0001\"\u0011E\u0005A\u0019un\u0012:pkB\u0004\u0016M\u001d;ji&|gN\u0003\u0002\u000b\u0017\u0005\u0019!\u000f\u001a3\u000b\u00051i\u0011!B:qCJ\\'B\u0001\b\u0010\u0003\u0019\t\u0007/Y2iK*\t\u0001#A\u0002pe\u001e\u001cB\u0001\u0001\n\u00199A\u00111CF\u0007\u0002))\tQ#A\u0003tG\u0006d\u0017-\u0003\u0002\u0018)\t1\u0011I\\=SK\u001a\u0004\"!\u0007\u000e\u000e\u0003-I!aG\u0006\u0003\u0013A\u000b'\u000f^5uS>t\u0007CA\u000f'\u001d\tqBE\u0004\u0002 G5\t\u0001E\u0003\u0002\"E\u00051AH]8piz\u001a\u0001!C\u0001\u0016\u0013\t)C#A\u0004qC\u000e\\\u0017mZ3\n\u0005\u001dB#\u0001D*fe&\fG.\u001b>bE2,'BA\u0013\u0015\u0003\u0015Ig\u000eZ3y+\u0005Y\u0003CA\n-\u0013\tiCCA\u0002J]R\fa!\u001b8eKb\u0004\u0013A\u00038beJ|w\u000fR3qgV\t\u0011\u0007E\u0002\u0014eQJ!a\r\u000b\u0003\u000b\u0005\u0013(/Y=\u0011\u0007M)t'\u0003\u00027)\t1q\n\u001d;j_:\u0004\"\u0001O\u001d\u000e\u0003%I!AO\u0005\u0003+9\u000b'O]8x\u0007><%o\\;q'Bd\u0017\u000e\u001e#fa\u0006Ya.\u0019:s_^$U\r]:!\u0003\u0019a\u0014N\\5u}Q\u0019ah\u0010!\u0011\u0005a\u0002\u0001\"B\u0015\u0006\u0001\u0004Y\u0003\"B\u0018\u0006\u0001\u0004\t\u0014\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003-\na!Z9vC2\u001cHCA#I!\t\u0019b)\u0003\u0002H)\t9!i\\8mK\u0006t\u0007\"B%\b\u0001\u0004Q\u0015!B8uQ\u0016\u0014\bCA\nL\u0013\taECA\u0002B]f\u0004"
)
public class CoGroupPartition implements Partition {
   private final int index;
   private final Option[] narrowDeps;

   // $FF: synthetic method
   public boolean org$apache$spark$Partition$$super$equals(final Object x$1) {
      return super.equals(x$1);
   }

   public int index() {
      return this.index;
   }

   public Option[] narrowDeps() {
      return this.narrowDeps;
   }

   public int hashCode() {
      return this.index();
   }

   public boolean equals(final Object other) {
      return Partition.equals$(this, other);
   }

   public CoGroupPartition(final int index, final Option[] narrowDeps) {
      this.index = index;
      this.narrowDeps = narrowDeps;
      Partition.$init$(this);
   }
}
