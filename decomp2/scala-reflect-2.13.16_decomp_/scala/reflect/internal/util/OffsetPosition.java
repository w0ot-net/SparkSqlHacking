package scala.reflect.internal.util;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E2A!\u0003\u0006\u0001'!A\u0001\u0004\u0001B\u0001B\u0003%\u0011\u0004\u0003\u0005\u001d\u0001\t\u0005\t\u0015!\u0003\u001e\u0011\u0015\t\u0003\u0001\"\u0001#\u0011\u00151\u0003\u0001\"\u0011(\u0011\u0015Y\u0003\u0001\"\u0011-\u0011\u0015i\u0003\u0001\"\u0011/\u0011\u0015y\u0003\u0001\"\u0011/\u0011\u0015\u0001\u0004\u0001\"\u0011/\u00059yeMZ:fiB{7/\u001b;j_:T!a\u0003\u0007\u0002\tU$\u0018\u000e\u001c\u0006\u0003\u001b9\t\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u0003\u001fA\tqA]3gY\u0016\u001cGOC\u0001\u0012\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019\"\u0001\u0001\u000b\u0011\u0005U1R\"\u0001\u0006\n\u0005]Q!a\u0004#fM&tW\r\u001a)pg&$\u0018n\u001c8\u0002\u0011M|WO]2f\u0013:\u0004\"!\u0006\u000e\n\u0005mQ!AC*pkJ\u001cWMR5mK\u00069\u0001o\\5oi&s\u0007C\u0001\u0010 \u001b\u0005\u0001\u0012B\u0001\u0011\u0011\u0005\rIe\u000e^\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007\r\"S\u0005\u0005\u0002\u0016\u0001!)\u0001d\u0001a\u00013!)Ad\u0001a\u0001;\u00059\u0011n\u001d*b]\u001e,W#\u0001\u0015\u0011\u0005yI\u0013B\u0001\u0016\u0011\u0005\u001d\u0011un\u001c7fC:\faa]8ve\u000e,W#A\r\u0002\u000bA|\u0017N\u001c;\u0016\u0003u\tQa\u001d;beR\f1!\u001a8e\u0001"
)
public class OffsetPosition extends DefinedPosition {
   private final SourceFile sourceIn;
   private final int pointIn;

   public boolean isRange() {
      return false;
   }

   public SourceFile source() {
      return this.sourceIn;
   }

   public int point() {
      return this.pointIn;
   }

   public int start() {
      return this.point();
   }

   public int end() {
      return this.point();
   }

   public OffsetPosition(final SourceFile sourceIn, final int pointIn) {
      this.sourceIn = sourceIn;
      this.pointIn = pointIn;
   }
}
