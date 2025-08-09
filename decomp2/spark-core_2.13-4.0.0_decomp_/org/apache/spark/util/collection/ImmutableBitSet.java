package org.apache.spark.util.collection;

import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005!3A\u0001D\u0007\u00011!AQ\u0004\u0001BC\u0002\u0013\u0005a\u0004\u0003\u0005&\u0001\t\u0005\t\u0015!\u0003 \u0011!1\u0003A!b\u0001\n\u00039\u0003\u0002C\u0016\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0015\t\u000b1\u0002A\u0011A\u0017\t\u000bE\u0002A\u0011\t\u001a\t\u000bY\u0002A\u0011I\u001c\t\u000bi\u0002A\u0011I\u001e\t\u000by\u0002A\u0011I \t\u000b\u0005\u0003A\u0011\t\"\t\u000b\u0011\u0003A\u0011I#\u0003\u001f%kW.\u001e;bE2,')\u001b;TKRT!AD\b\u0002\u0015\r|G\u000e\\3di&|gN\u0003\u0002\u0011#\u0005!Q\u000f^5m\u0015\t\u00112#A\u0003ta\u0006\u00148N\u0003\u0002\u0015+\u00051\u0011\r]1dQ\u0016T\u0011AF\u0001\u0004_J<7\u0001A\n\u0003\u0001e\u0001\"AG\u000e\u000e\u00035I!\u0001H\u0007\u0003\r\tKGoU3u\u0003\u001dqW/\u001c\"jiN,\u0012a\b\t\u0003A\rj\u0011!\t\u0006\u0002E\u0005)1oY1mC&\u0011A%\t\u0002\u0004\u0013:$\u0018\u0001\u00038v[\nKGo\u001d\u0011\u0002\u0013\tLGo\u001d+p'\u0016$X#\u0001\u0015\u0011\u0007\u0001Js$\u0003\u0002+C\tQAH]3qK\u0006$X\r\u001a \u0002\u0015\tLGo\u001d+p'\u0016$\b%\u0001\u0004=S:LGO\u0010\u000b\u0004]=\u0002\u0004C\u0001\u000e\u0001\u0011\u0015iR\u00011\u0001 \u0011\u00151S\u00011\u0001)\u0003\u0015\u0019G.Z1s)\u0005\u0019\u0004C\u0001\u00115\u0013\t)\u0014E\u0001\u0003V]&$\u0018AC2mK\u0006\u0014XK\u001c;jYR\u00111\u0007\u000f\u0005\u0006s\u001d\u0001\raH\u0001\tE&$\u0018J\u001c3fq\u0006\u00191/\u001a;\u0015\u0005Mb\u0004\"B\u001f\t\u0001\u0004y\u0012!B5oI\u0016D\u0018\u0001C:fiVsG/\u001b7\u0015\u0005M\u0002\u0005\"B\u001d\n\u0001\u0004y\u0012!B;og\u0016$HCA\u001aD\u0011\u0015i$\u00021\u0001 \u0003\u0015)h.[8o)\t\u0019d\tC\u0003H\u0017\u0001\u0007\u0011$A\u0003pi\",'\u000f"
)
public class ImmutableBitSet extends BitSet {
   private final int numBits;
   private final Seq bitsToSet;

   public int numBits() {
      return this.numBits;
   }

   public Seq bitsToSet() {
      return this.bitsToSet;
   }

   public void clear() {
      throw new UnsupportedOperationException(ErrorMessage$.MODULE$.msg());
   }

   public void clearUntil(final int bitIndex) {
      throw new UnsupportedOperationException(ErrorMessage$.MODULE$.msg());
   }

   public void set(final int index) {
      throw new UnsupportedOperationException(ErrorMessage$.MODULE$.msg());
   }

   public void setUntil(final int bitIndex) {
      throw new UnsupportedOperationException(ErrorMessage$.MODULE$.msg());
   }

   public void unset(final int index) {
      throw new UnsupportedOperationException(ErrorMessage$.MODULE$.msg());
   }

   public void union(final BitSet other) {
      throw new UnsupportedOperationException(ErrorMessage$.MODULE$.msg());
   }

   public ImmutableBitSet(final int numBits, final Seq bitsToSet) {
      super(numBits);
      this.numBits = numBits;
      this.bitsToSet = bitsToSet;
      Iterator bitsIterator = bitsToSet.iterator();

      while(bitsIterator.hasNext()) {
         super.set(BoxesRunTime.unboxToInt(bitsIterator.next()));
      }

   }
}
