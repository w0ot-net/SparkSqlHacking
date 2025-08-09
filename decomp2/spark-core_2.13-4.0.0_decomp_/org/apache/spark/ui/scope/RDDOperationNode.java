package org.apache.spark.ui.scope;

import java.io.Serializable;
import scala.Enumeration;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-g!B\u0013'\u0001*\u0002\u0004\u0002C$\u0001\u0005+\u0007I\u0011\u0001%\t\u00111\u0003!\u0011#Q\u0001\n%C\u0001\"\u0014\u0001\u0003\u0016\u0004%\tA\u0014\u0005\t/\u0002\u0011\t\u0012)A\u0005\u001f\"A\u0001\f\u0001BK\u0002\u0013\u0005\u0011\f\u0003\u0005^\u0001\tE\t\u0015!\u0003[\u0011!q\u0006A!f\u0001\n\u0003I\u0006\u0002C0\u0001\u0005#\u0005\u000b\u0011\u0002.\t\u0011\u0001\u0004!Q3A\u0005\u00029C\u0001\"\u0019\u0001\u0003\u0012\u0003\u0006Ia\u0014\u0005\tE\u0002\u0011)\u001a!C\u0001G\"Aq\u000e\u0001B\tB\u0003%A\rC\u0003q\u0001\u0011\u0005\u0011\u000fC\u0004{\u0001\u0005\u0005I\u0011A>\t\u0013\u0005\u0015\u0001!%A\u0005\u0002\u0005\u001d\u0001\"CA\u000f\u0001E\u0005I\u0011AA\u0010\u0011%\t\u0019\u0003AI\u0001\n\u0003\t)\u0003C\u0005\u0002*\u0001\t\n\u0011\"\u0001\u0002&!I\u00111\u0006\u0001\u0012\u0002\u0013\u0005\u0011q\u0004\u0005\n\u0003[\u0001\u0011\u0013!C\u0001\u0003_A\u0011\"a\r\u0001\u0003\u0003%\t%!\u000e\t\u0011\u0005\u0015\u0003!!A\u0005\u0002!C\u0011\"a\u0012\u0001\u0003\u0003%\t!!\u0013\t\u0013\u0005U\u0003!!A\u0005B\u0005]\u0003\"CA3\u0001\u0005\u0005I\u0011AA4\u0011%\tY\u0007AA\u0001\n\u0003\ni\u0007C\u0005\u0002r\u0001\t\t\u0011\"\u0011\u0002t!I\u0011Q\u000f\u0001\u0002\u0002\u0013\u0005\u0013q\u000f\u0005\n\u0003s\u0002\u0011\u0011!C!\u0003w:!\"a '\u0003\u0003E\tAKAA\r%)c%!A\t\u0002)\n\u0019\t\u0003\u0004q?\u0011\u0005\u00111\u0014\u0005\n\u0003kz\u0012\u0011!C#\u0003oB\u0011\"!( \u0003\u0003%\t)a(\t\u0013\u00055v$!A\u0005\u0002\u0006=\u0006\"CAa?\u0005\u0005I\u0011BAb\u0005A\u0011F\tR(qKJ\fG/[8o\u001d>$WM\u0003\u0002(Q\u0005)1oY8qK*\u0011\u0011FK\u0001\u0003k&T!a\u000b\u0017\u0002\u000bM\u0004\u0018M]6\u000b\u00055r\u0013AB1qC\u000eDWMC\u00010\u0003\ry'oZ\n\u0005\u0001E:$\b\u0005\u00023k5\t1GC\u00015\u0003\u0015\u00198-\u00197b\u0013\t14G\u0001\u0004B]f\u0014VM\u001a\t\u0003eaJ!!O\u001a\u0003\u000fA\u0013x\u000eZ;diB\u00111\b\u0012\b\u0003y\ts!!P!\u000e\u0003yR!a\u0010!\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011\u0001N\u0005\u0003\u0007N\nq\u0001]1dW\u0006<W-\u0003\u0002F\r\na1+\u001a:jC2L'0\u00192mK*\u00111iM\u0001\u0003S\u0012,\u0012!\u0013\t\u0003e)K!aS\u001a\u0003\u0007%sG/A\u0002jI\u0002\nAA\\1nKV\tq\n\u0005\u0002Q):\u0011\u0011K\u0015\t\u0003{MJ!aU\u001a\u0002\rA\u0013X\rZ3g\u0013\t)fK\u0001\u0004TiJLgn\u001a\u0006\u0003'N\nQA\\1nK\u0002\naaY1dQ\u0016$W#\u0001.\u0011\u0005IZ\u0016B\u0001/4\u0005\u001d\u0011un\u001c7fC:\fqaY1dQ\u0016$\u0007%A\u0004cCJ\u0014\u0018.\u001a:\u0002\u0011\t\f'O]5fe\u0002\n\u0001bY1mYNLG/Z\u0001\nG\u0006dGn]5uK\u0002\n\u0001d\\;uaV$H)\u001a;fe6Lg.[:uS\u000edUM^3m+\u0005!\u0007CA3l\u001d\t1\u0017.D\u0001h\u0015\tA'&A\u0002sI\u0012L!A[4\u0002%\u0011+G/\u001a:nS:L7\u000f^5d\u0019\u00164X\r\\\u0005\u0003Y6\u0014QAV1mk\u0016L!A\\\u001a\u0003\u0017\u0015sW/\\3sCRLwN\\\u0001\u001a_V$\b/\u001e;EKR,'/\\5oSN$\u0018n\u0019'fm\u0016d\u0007%\u0001\u0004=S:LGO\u0010\u000b\beR,ho\u001e=z!\t\u0019\b!D\u0001'\u0011\u00159U\u00021\u0001J\u0011\u0015iU\u00021\u0001P\u0011\u0015AV\u00021\u0001[\u0011\u0015qV\u00021\u0001[\u0011\u0015\u0001W\u00021\u0001P\u0011\u0015\u0011W\u00021\u0001e\u0003\u0011\u0019w\u000e]=\u0015\u0013IdXP`@\u0002\u0002\u0005\r\u0001bB$\u000f!\u0003\u0005\r!\u0013\u0005\b\u001b:\u0001\n\u00111\u0001P\u0011\u001dAf\u0002%AA\u0002iCqA\u0018\b\u0011\u0002\u0003\u0007!\fC\u0004a\u001dA\u0005\t\u0019A(\t\u000f\tt\u0001\u0013!a\u0001I\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nTCAA\u0005U\rI\u00151B\u0016\u0003\u0003\u001b\u0001B!a\u0004\u0002\u001a5\u0011\u0011\u0011\u0003\u0006\u0005\u0003'\t)\"A\u0005v]\u000eDWmY6fI*\u0019\u0011qC\u001a\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002\u001c\u0005E!!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012TCAA\u0011U\ry\u00151B\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134+\t\t9CK\u0002[\u0003\u0017\tabY8qs\u0012\"WMZ1vYR$C'\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001b\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%mU\u0011\u0011\u0011\u0007\u0016\u0004I\u0006-\u0011!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u00028A!\u0011\u0011HA\"\u001b\t\tYD\u0003\u0003\u0002>\u0005}\u0012\u0001\u00027b]\u001eT!!!\u0011\u0002\t)\fg/Y\u0005\u0004+\u0006m\u0012\u0001\u00049s_\u0012,8\r^!sSRL\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0003\u0017\n\t\u0006E\u00023\u0003\u001bJ1!a\u00144\u0005\r\te.\u001f\u0005\t\u0003':\u0012\u0011!a\u0001\u0013\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!!\u0017\u0011\r\u0005m\u0013\u0011MA&\u001b\t\tiFC\u0002\u0002`M\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\t\u0019'!\u0018\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u00045\u0006%\u0004\"CA*3\u0005\u0005\t\u0019AA&\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\u0005]\u0012q\u000e\u0005\t\u0003'R\u0012\u0011!a\u0001\u0013\u0006A\u0001.Y:i\u0007>$W\rF\u0001J\u0003!!xn\u0015;sS:<GCAA\u001c\u0003\u0019)\u0017/^1mgR\u0019!,! \t\u0013\u0005MS$!AA\u0002\u0005-\u0013\u0001\u0005*E\t>\u0003XM]1uS>tgj\u001c3f!\t\u0019xdE\u0003 \u0003\u000b\u000b\t\nE\u0006\u0002\b\u00065\u0015j\u0014.[\u001f\u0012\u0014XBAAE\u0015\r\tYiM\u0001\beVtG/[7f\u0013\u0011\ty)!#\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>tg\u0007\u0005\u0003\u0002\u0014\u0006eUBAAK\u0015\u0011\t9*a\u0010\u0002\u0005%|\u0017bA#\u0002\u0016R\u0011\u0011\u0011Q\u0001\u0006CB\u0004H.\u001f\u000b\u000ee\u0006\u0005\u00161UAS\u0003O\u000bI+a+\t\u000b\u001d\u0013\u0003\u0019A%\t\u000b5\u0013\u0003\u0019A(\t\u000ba\u0013\u0003\u0019\u0001.\t\u000by\u0013\u0003\u0019\u0001.\t\u000b\u0001\u0014\u0003\u0019A(\t\u000b\t\u0014\u0003\u0019\u00013\u0002\u000fUt\u0017\r\u001d9msR!\u0011\u0011WA_!\u0015\u0011\u00141WA\\\u0013\r\t)l\r\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0013I\nI,S([5>#\u0017bAA^g\t1A+\u001e9mKZB\u0001\"a0$\u0003\u0003\u0005\rA]\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAAc!\u0011\tI$a2\n\t\u0005%\u00171\b\u0002\u0007\u001f\nTWm\u0019;"
)
public class RDDOperationNode implements Product, Serializable {
   private final int id;
   private final String name;
   private final boolean cached;
   private final boolean barrier;
   private final String callsite;
   private final Enumeration.Value outputDeterministicLevel;

   public static Option unapply(final RDDOperationNode x$0) {
      return RDDOperationNode$.MODULE$.unapply(x$0);
   }

   public static RDDOperationNode apply(final int id, final String name, final boolean cached, final boolean barrier, final String callsite, final Enumeration.Value outputDeterministicLevel) {
      return RDDOperationNode$.MODULE$.apply(id, name, cached, barrier, callsite, outputDeterministicLevel);
   }

   public static Function1 tupled() {
      return RDDOperationNode$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return RDDOperationNode$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int id() {
      return this.id;
   }

   public String name() {
      return this.name;
   }

   public boolean cached() {
      return this.cached;
   }

   public boolean barrier() {
      return this.barrier;
   }

   public String callsite() {
      return this.callsite;
   }

   public Enumeration.Value outputDeterministicLevel() {
      return this.outputDeterministicLevel;
   }

   public RDDOperationNode copy(final int id, final String name, final boolean cached, final boolean barrier, final String callsite, final Enumeration.Value outputDeterministicLevel) {
      return new RDDOperationNode(id, name, cached, barrier, callsite, outputDeterministicLevel);
   }

   public int copy$default$1() {
      return this.id();
   }

   public String copy$default$2() {
      return this.name();
   }

   public boolean copy$default$3() {
      return this.cached();
   }

   public boolean copy$default$4() {
      return this.barrier();
   }

   public String copy$default$5() {
      return this.callsite();
   }

   public Enumeration.Value copy$default$6() {
      return this.outputDeterministicLevel();
   }

   public String productPrefix() {
      return "RDDOperationNode";
   }

   public int productArity() {
      return 6;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.id());
         }
         case 1 -> {
            return this.name();
         }
         case 2 -> {
            return BoxesRunTime.boxToBoolean(this.cached());
         }
         case 3 -> {
            return BoxesRunTime.boxToBoolean(this.barrier());
         }
         case 4 -> {
            return this.callsite();
         }
         case 5 -> {
            return this.outputDeterministicLevel();
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
      return x$1 instanceof RDDOperationNode;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "id";
         }
         case 1 -> {
            return "name";
         }
         case 2 -> {
            return "cached";
         }
         case 3 -> {
            return "barrier";
         }
         case 4 -> {
            return "callsite";
         }
         case 5 -> {
            return "outputDeterministicLevel";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.id());
      var1 = Statics.mix(var1, Statics.anyHash(this.name()));
      var1 = Statics.mix(var1, this.cached() ? 1231 : 1237);
      var1 = Statics.mix(var1, this.barrier() ? 1231 : 1237);
      var1 = Statics.mix(var1, Statics.anyHash(this.callsite()));
      var1 = Statics.mix(var1, Statics.anyHash(this.outputDeterministicLevel()));
      return Statics.finalizeHash(var1, 6);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10;
      if (this != x$1) {
         label75: {
            if (x$1 instanceof RDDOperationNode) {
               RDDOperationNode var4 = (RDDOperationNode)x$1;
               if (this.id() == var4.id() && this.cached() == var4.cached() && this.barrier() == var4.barrier()) {
                  label68: {
                     String var10000 = this.name();
                     String var5 = var4.name();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label68;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label68;
                     }

                     var10000 = this.callsite();
                     String var6 = var4.callsite();
                     if (var10000 == null) {
                        if (var6 != null) {
                           break label68;
                        }
                     } else if (!var10000.equals(var6)) {
                        break label68;
                     }

                     Enumeration.Value var9 = this.outputDeterministicLevel();
                     Enumeration.Value var7 = var4.outputDeterministicLevel();
                     if (var9 == null) {
                        if (var7 != null) {
                           break label68;
                        }
                     } else if (!var9.equals(var7)) {
                        break label68;
                     }

                     if (var4.canEqual(this)) {
                        break label75;
                     }
                  }
               }
            }

            var10 = false;
            return var10;
         }
      }

      var10 = true;
      return var10;
   }

   public RDDOperationNode(final int id, final String name, final boolean cached, final boolean barrier, final String callsite, final Enumeration.Value outputDeterministicLevel) {
      this.id = id;
      this.name = name;
      this.cached = cached;
      this.barrier = barrier;
      this.callsite = callsite;
      this.outputDeterministicLevel = outputDeterministicLevel;
      Product.$init$(this);
   }
}
