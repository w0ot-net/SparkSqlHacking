package org.apache.spark.mllib.linalg;

import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015g\u0001\u0002\u000f\u001e\u0001\"B\u0001b\u0010\u0001\u0003\u0016\u0004%\t\u0001\u0011\u0005\t\u0019\u0002\u0011\t\u0012)A\u0005\u0003\"AQ\n\u0001BK\u0002\u0013\u0005a\n\u0003\u0005T\u0001\tE\t\u0015!\u0003P\u0011!!\u0006A!f\u0001\n\u0003)\u0006\u0002C-\u0001\u0005#\u0005\u000b\u0011\u0002,\t\u000bi\u0003A\u0011A.\t\u000f\u0001\u0004\u0011\u0011!C\u0001C\"91\u000eAI\u0001\n\u0003a\u0007b\u0002>\u0001#\u0003%\ta\u001f\u0005\n\u0003\u0003\u0001\u0011\u0013!C\u0001\u0003\u0007A\u0011\"!\u0004\u0001\u0003\u0003%\t%a\u0004\t\u0013\u0005\u0005\u0002!!A\u0005\u0002\u0005\r\u0002\"CA\u0016\u0001\u0005\u0005I\u0011AA\u0017\u0011%\t\u0019\u0004AA\u0001\n\u0003\n)\u0004C\u0005\u0002D\u0001\t\t\u0011\"\u0001\u0002F!I\u0011q\n\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u000b\u0005\n\u0003+\u0002\u0011\u0011!C!\u0003/B\u0011\"!\u0017\u0001\u0003\u0003%\t%a\u0017\t\u0013\u0005u\u0003!!A\u0005B\u0005}s!CA:;\u0005\u0005\t\u0012AA;\r!aR$!A\t\u0002\u0005]\u0004B\u0002.\u0017\t\u0003\t\u0019\tC\u0005\u0002ZY\t\t\u0011\"\u0012\u0002\\!I\u0011Q\u0011\f\u0002\u0002\u0013\u0005\u0015q\u0011\u0005\n\u000373\u0012\u0011!CA\u0003;C\u0011\"a/\u0017\u0003\u0003%I!!0\u00035MKgnZ;mCJ4\u0016\r\\;f\t\u0016\u001cw.\u001c9pg&$\u0018n\u001c8\u000b\u0005yy\u0012A\u00027j]\u0006dwM\u0003\u0002!C\u0005)Q\u000e\u001c7jE*\u0011!eI\u0001\u0006gB\f'o\u001b\u0006\u0003I\u0015\na!\u00199bG\",'\"\u0001\u0014\u0002\u0007=\u0014xm\u0001\u0001\u0016\u0007%\u001auk\u0005\u0003\u0001UA\u001a\u0004CA\u0016/\u001b\u0005a#\"A\u0017\u0002\u000bM\u001c\u0017\r\\1\n\u0005=b#AB!osJ+g\r\u0005\u0002,c%\u0011!\u0007\f\u0002\b!J|G-^2u!\t!DH\u0004\u00026u9\u0011a'O\u0007\u0002o)\u0011\u0001hJ\u0001\u0007yI|w\u000e\u001e \n\u00035J!a\u000f\u0017\u0002\u000fA\f7m[1hK&\u0011QH\u0010\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003w1\n\u0011!V\u000b\u0002\u0003B\u0011!i\u0011\u0007\u0001\t\u0015!\u0005A1\u0001F\u0005\u0015)F+\u001f9f#\t1\u0015\n\u0005\u0002,\u000f&\u0011\u0001\n\f\u0002\b\u001d>$\b.\u001b8h!\tY#*\u0003\u0002LY\t\u0019\u0011I\\=\u0002\u0005U\u0003\u0013!A:\u0016\u0003=\u0003\"\u0001U)\u000e\u0003uI!AU\u000f\u0003\rY+7\r^8s\u0003\t\u0019\b%A\u0001W+\u00051\u0006C\u0001\"X\t\u0015A\u0006A1\u0001F\u0005\u00151F+\u001f9f\u0003\t1\u0006%\u0001\u0004=S:LGO\u0010\u000b\u00059vsv\f\u0005\u0003Q\u0001\u00053\u0006\"B \b\u0001\u0004\t\u0005\"B'\b\u0001\u0004y\u0005\"\u0002+\b\u0001\u00041\u0016\u0001B2paf,2AY3h)\u0011\u0019\u0007.\u001b6\u0011\tA\u0003AM\u001a\t\u0003\u0005\u0016$Q\u0001\u0012\u0005C\u0002\u0015\u0003\"AQ4\u0005\u000baC!\u0019A#\t\u000f}B\u0001\u0013!a\u0001I\"9Q\n\u0003I\u0001\u0002\u0004y\u0005b\u0002+\t!\u0003\u0005\rAZ\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\ri\u00070_\u000b\u0002]*\u0012\u0011i\\\u0016\u0002aB\u0011\u0011O^\u0007\u0002e*\u00111\u000f^\u0001\nk:\u001c\u0007.Z2lK\u0012T!!\u001e\u0017\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002xe\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0005\u000b\u0011K!\u0019A#\u0005\u000baK!\u0019A#\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0019AP`@\u0016\u0003uT#aT8\u0005\u000b\u0011S!\u0019A#\u0005\u000baS!\u0019A#\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%gU1\u0011QAA\u0005\u0003\u0017)\"!a\u0002+\u0005Y{G!\u0002#\f\u0005\u0004)E!\u0002-\f\u0005\u0004)\u0015!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u0002\u0012A!\u00111CA\u000f\u001b\t\t)B\u0003\u0003\u0002\u0018\u0005e\u0011\u0001\u00027b]\u001eT!!a\u0007\u0002\t)\fg/Y\u0005\u0005\u0003?\t)B\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0003\u0003K\u00012aKA\u0014\u0013\r\tI\u0003\f\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0004\u0013\u0006=\u0002\"CA\u0019\u001d\u0005\u0005\t\u0019AA\u0013\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011q\u0007\t\u0006\u0003s\ty$S\u0007\u0003\u0003wQ1!!\u0010-\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003\u0003\nYD\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA$\u0003\u001b\u00022aKA%\u0013\r\tY\u0005\f\u0002\b\u0005>|G.Z1o\u0011!\t\t\u0004EA\u0001\u0002\u0004I\u0015A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!!\u0005\u0002T!I\u0011\u0011G\t\u0002\u0002\u0003\u0007\u0011QE\u0001\tQ\u0006\u001c\bnQ8eKR\u0011\u0011QE\u0001\ti>\u001cFO]5oOR\u0011\u0011\u0011C\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005\u001d\u0013\u0011\r\u0005\t\u0003c!\u0012\u0011!a\u0001\u0013\"*\u0001!!\u001a\u0002pA!\u0011qMA6\u001b\t\tIG\u0003\u0002vC%!\u0011QNA5\u0005\u0015\u0019\u0016N\\2fC\t\t\t(A\u00032]Ar\u0003'\u0001\u000eTS:<W\u000f\\1s-\u0006dW/\u001a#fG>l\u0007o\\:ji&|g\u000e\u0005\u0002Q-M!aCKA=!\u0011\tY(!!\u000e\u0005\u0005u$\u0002BA@\u00033\t!![8\n\u0007u\ni\b\u0006\u0002\u0002v\u0005)\u0011\r\u001d9msV1\u0011\u0011RAH\u0003'#\u0002\"a#\u0002\u0016\u0006]\u0015\u0011\u0014\t\u0007!\u0002\ti)!%\u0011\u0007\t\u000by\tB\u0003E3\t\u0007Q\tE\u0002C\u0003'#Q\u0001W\rC\u0002\u0015CaaP\rA\u0002\u00055\u0005\"B'\u001a\u0001\u0004y\u0005B\u0002+\u001a\u0001\u0004\t\t*A\u0004v]\u0006\u0004\b\u000f\\=\u0016\r\u0005}\u0015qVAZ)\u0011\t\t+!.\u0011\u000b-\n\u0019+a*\n\u0007\u0005\u0015FF\u0001\u0004PaRLwN\u001c\t\tW\u0005%\u0016QV(\u00022&\u0019\u00111\u0016\u0017\u0003\rQ+\b\u000f\\34!\r\u0011\u0015q\u0016\u0003\u0006\tj\u0011\r!\u0012\t\u0004\u0005\u0006MF!\u0002-\u001b\u0005\u0004)\u0005\"CA\\5\u0005\u0005\t\u0019AA]\u0003\rAH\u0005\r\t\u0007!\u0002\ti+!-\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005}\u0006\u0003BA\n\u0003\u0003LA!a1\u0002\u0016\t1qJ\u00196fGR\u0004"
)
public class SingularValueDecomposition implements Product, Serializable {
   private final Object U;
   private final Vector s;
   private final Object V;

   public static Option unapply(final SingularValueDecomposition x$0) {
      return SingularValueDecomposition$.MODULE$.unapply(x$0);
   }

   public static SingularValueDecomposition apply(final Object U, final Vector s, final Object V) {
      return SingularValueDecomposition$.MODULE$.apply(U, s, V);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Object U() {
      return this.U;
   }

   public Vector s() {
      return this.s;
   }

   public Object V() {
      return this.V;
   }

   public SingularValueDecomposition copy(final Object U, final Vector s, final Object V) {
      return new SingularValueDecomposition(U, s, V);
   }

   public Object copy$default$1() {
      return this.U();
   }

   public Vector copy$default$2() {
      return this.s();
   }

   public Object copy$default$3() {
      return this.V();
   }

   public String productPrefix() {
      return "SingularValueDecomposition";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.U();
         }
         case 1 -> {
            return this.s();
         }
         case 2 -> {
            return this.V();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof SingularValueDecomposition;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "U";
         }
         case 1 -> {
            return "s";
         }
         case 2 -> {
            return "V";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label53: {
            if (x$1 instanceof SingularValueDecomposition) {
               SingularValueDecomposition var4 = (SingularValueDecomposition)x$1;
               if (BoxesRunTime.equals(this.U(), var4.U())) {
                  label46: {
                     Vector var10000 = this.s();
                     Vector var5 = var4.s();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label46;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label46;
                     }

                     if (BoxesRunTime.equals(this.V(), var4.V()) && var4.canEqual(this)) {
                        break label53;
                     }
                  }
               }
            }

            var6 = false;
            return var6;
         }
      }

      var6 = true;
      return var6;
   }

   public SingularValueDecomposition(final Object U, final Vector s, final Object V) {
      this.U = U;
      this.s = s;
      this.V = V;
      Product.$init$(this);
   }
}
