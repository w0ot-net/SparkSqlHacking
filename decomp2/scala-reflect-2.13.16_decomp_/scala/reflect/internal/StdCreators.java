package scala.reflect.internal;

import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Mirror;
import scala.reflect.api.TreeCreator;
import scala.reflect.api.TypeCreator;
import scala.runtime.AbstractFunction2;
import scala.runtime.ScalaRunTime;
import scala.runtime.Statics;
import scala.util.hashing.MurmurHash3.;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0015c!\u0003\u001b6!\u0003\r\t\u0001\u0010B\u001f\u0011\u0015\t\u0005\u0001\"\u0001C\r\u00111\u0005\u0001Q$\t\u0011u\u0013!Q3A\u0005\u0002yC\u0001b\u0019\u0002\u0003\u0012\u0003\u0006Ia\u0018\u0005\tI\n\u0011)\u001a!C\u0001K\"A1N\u0001B\tB\u0003%a\rC\u0003m\u0005\u0011\u0005Q\u000eC\u0003r\u0005\u0011\u0005!\u000fC\u0005\u0002\u0018\t\t\t\u0011\"\u0001\u0002\u001a!I\u0011q\u0004\u0002\u0012\u0002\u0013\u0005\u0011\u0011\u0005\u0005\n\u0003o\u0011\u0011\u0013!C\u0001\u0003sA\u0011\"!\u0010\u0003\u0003\u0003%\t%a\u0010\t\u0013\u0005E#!!A\u0005\u0002\u0005M\u0003\"CA.\u0005\u0005\u0005I\u0011AA/\u0011%\tIGAA\u0001\n\u0003\nY\u0007C\u0005\u0002z\t\t\t\u0011\"\u0001\u0002|!I\u0011Q\u0011\u0002\u0002\u0002\u0013\u0005\u0013q\u0011\u0005\n\u0003\u0017\u0013\u0011\u0011!C!\u0003\u001bC\u0011\"a$\u0003\u0003\u0003%\t%!%\t\u0013\u0005M%!!A\u0005B\u0005Uu!CAM\u0001\u0005\u0005\t\u0012AAN\r!1\u0005!!A\t\u0002\u0005u\u0005B\u00027\u0017\t\u0003\t)\fC\u0005\u0002\u0010Z\t\t\u0011\"\u0012\u0002\u0012\"A\u0011OFA\u0001\n\u0003\u000b9\fC\u0005\u0002>Z\t\t\u0011\"!\u0002@\u001a1\u0011\u0011\u001b\u0001A\u0003'D\u0001\"X\u000e\u0003\u0016\u0004%\tA\u0018\u0005\tGn\u0011\t\u0012)A\u0005?\"Q\u00111\\\u000e\u0003\u0016\u0004%\t!!8\t\u0015\u0005%8D!E!\u0002\u0013\ty\u000e\u0003\u0004m7\u0011\u0005\u00111\u001e\u0005\u0007cn!\t!a=\t\u0013\u0005]1$!A\u0005\u0002\t-\u0001\"CA\u00107E\u0005I\u0011AA\u0011\u0011%\t9dGI\u0001\n\u0003\u0011\t\u0002C\u0005\u0002>m\t\t\u0011\"\u0011\u0002@!I\u0011\u0011K\u000e\u0002\u0002\u0013\u0005\u00111\u000b\u0005\n\u00037Z\u0012\u0011!C\u0001\u0005+A\u0011\"!\u001b\u001c\u0003\u0003%\t%a\u001b\t\u0013\u0005e4$!A\u0005\u0002\te\u0001\"CAC7\u0005\u0005I\u0011\tB\u000f\u0011%\tYiGA\u0001\n\u0003\ni\tC\u0005\u0002\u0010n\t\t\u0011\"\u0011\u0002\u0012\"I\u00111S\u000e\u0002\u0002\u0013\u0005#\u0011E\u0004\n\u0005K\u0001\u0011\u0011!E\u0001\u0005O1\u0011\"!5\u0001\u0003\u0003E\tA!\u000b\t\r1|C\u0011\u0001B\u0017\u0011%\tyiLA\u0001\n\u000b\n\t\n\u0003\u0005r_\u0005\u0005I\u0011\u0011B\u0018\u0011%\tilLA\u0001\n\u0003\u0013)DA\u0006Ti\u0012\u001c%/Z1u_J\u001c(B\u0001\u001c8\u0003!Ig\u000e^3s]\u0006d'B\u0001\u001d:\u0003\u001d\u0011XM\u001a7fGRT\u0011AO\u0001\u0006g\u000e\fG.Y\u0002\u0001'\t\u0001Q\b\u0005\u0002?\u007f5\t\u0011(\u0003\u0002As\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$C#A\"\u0011\u0005y\"\u0015BA#:\u0005\u0011)f.\u001b;\u0003-\u0019K\u00070\u001a3NSJ\u0014xN\u001d+sK\u0016\u001c%/Z1u_J\u001cBA\u0001%O#B\u0011\u0011\nT\u0007\u0002\u0015*\u00111jN\u0001\u0004CBL\u0017BA'K\u0005-!&/Z3De\u0016\fGo\u001c:\u0011\u0005yz\u0015B\u0001):\u0005\u001d\u0001&o\u001c3vGR\u0004\"A\u0015.\u000f\u0005MCfB\u0001+X\u001b\u0005)&B\u0001,<\u0003\u0019a$o\\8u}%\t!(\u0003\u0002Zs\u00059\u0001/Y2lC\u001e,\u0017BA.]\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tI\u0016(\u0001\u0004nSJ\u0014xN]\u000b\u0002?B\u0019\u0011\n\u00192\n\u0005\u0005T%AB'jeJ|'/D\u0001\u0001\u0003\u001di\u0017N\u001d:pe\u0002\nA\u0001\u001e:fKV\ta\r\u0005\u0002cO&\u0011\u0001.\u001b\u0002\u0005)J,W-\u0003\u0002kk\t)AK]3fg\u0006)AO]3fA\u00051A(\u001b8jiz\"2A\\8q!\t\u0011'\u0001C\u0003^\u000f\u0001\u0007q\fC\u0003e\u000f\u0001\u0007a-A\u0003baBd\u00170\u0006\u0002toR\u0019A/!\u0005\u0011\u0007U\fi\u0001\u0005\u0002wo2\u0001A!\u0002=\t\u0005\u0004I(!A+\u0012\u0005il\bC\u0001 |\u0013\ta\u0018HA\u0004O_RD\u0017N\\4\u0013\u000by\f\t!a\u0002\u0007\t}\u0014\u0001! \u0002\ryI,g-\u001b8f[\u0016tGO\u0010\t\u0004\u0013\u0006\r\u0011bAA\u0003\u0015\nAQK\\5wKJ\u001cX\rE\u0002?\u0003\u0013I1!a\u0003:\u0005%\u0019\u0016N\\4mKR|g.C\u0002i\u0003\u001fI!A\u001b&\t\u000f\u0005M\u0001\u00021\u0001\u0002\u0016\u0005\tQ\u000eE\u0002JAV\fAaY8qsR)a.a\u0007\u0002\u001e!9Q,\u0003I\u0001\u0002\u0004y\u0006b\u00023\n!\u0003\u0005\rAZ\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\t\u0019CK\u0002`\u0003KY#!a\n\u0011\t\u0005%\u00121G\u0007\u0003\u0003WQA!!\f\u00020\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003cI\u0014AC1o]>$\u0018\r^5p]&!\u0011QGA\u0016\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\t\tYDK\u0002g\u0003K\tQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAA!!\u0011\t\u0019%!\u0014\u000e\u0005\u0005\u0015#\u0002BA$\u0003\u0013\nA\u0001\\1oO*\u0011\u00111J\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002P\u0005\u0015#AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u0002VA\u0019a(a\u0016\n\u0007\u0005e\u0013HA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002`\u0005\u0015\u0004c\u0001 \u0002b%\u0019\u00111M\u001d\u0003\u0007\u0005s\u0017\u0010C\u0005\u0002h9\t\t\u00111\u0001\u0002V\u0005\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!!\u001c\u0011\r\u0005=\u0014QOA0\u001b\t\t\tHC\u0002\u0002te\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\t9(!\u001d\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003{\n\u0019\tE\u0002?\u0003\u007fJ1!!!:\u0005\u001d\u0011un\u001c7fC:D\u0011\"a\u001a\u0011\u0003\u0003\u0005\r!a\u0018\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003\u0003\nI\tC\u0005\u0002hE\t\t\u00111\u0001\u0002V\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002V\u0005AAo\\*ue&tw\r\u0006\u0002\u0002B\u00051Q-];bYN$B!! \u0002\u0018\"I\u0011q\r\u000b\u0002\u0002\u0003\u0007\u0011qL\u0001\u0017\r&DX\rZ'jeJ|'\u000f\u0016:fK\u000e\u0013X-\u0019;peB\u0011!MF\n\u0006-\u0005}\u00151\u0016\t\b\u0003C\u000b9k\u00184o\u001b\t\t\u0019KC\u0002\u0002&f\nqA];oi&lW-\u0003\u0003\u0002*\u0006\r&!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oeA!\u0011QVAZ\u001b\t\tyK\u0003\u0003\u00022\u0006%\u0013AA5p\u0013\rY\u0016q\u0016\u000b\u0003\u00037#RA\\A]\u0003wCQ!X\rA\u0002}CQ\u0001Z\rA\u0002\u0019\fq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002B\u00065\u0007#\u0002 \u0002D\u0006\u001d\u0017bAAcs\t1q\n\u001d;j_:\u0004RAPAe?\u001aL1!a3:\u0005\u0019!V\u000f\u001d7fe!A\u0011q\u001a\u000e\u0002\u0002\u0003\u0007a.A\u0002yIA\u0012aCR5yK\u0012l\u0015N\u001d:peRK\b/Z\"sK\u0006$xN]\n\u00067\u0005Ug*\u0015\t\u0004\u0013\u0006]\u0017bAAm\u0015\nYA+\u001f9f\u0007J,\u0017\r^8s\u0003\r!\b/Z\u000b\u0003\u0003?\u00042AYAq\u0013\u0011\t\u0019/!:\u0003\tQK\b/Z\u0005\u0004\u0003O,$!\u0002+za\u0016\u001c\u0018\u0001\u0002;qK\u0002\"b!!<\u0002p\u0006E\bC\u00012\u001c\u0011\u0015i\u0006\u00051\u0001`\u0011\u001d\tY\u000e\ta\u0001\u0003?,B!!>\u0002|R!\u0011q\u001fB\u0004!\u0011\tIPa\u0001\u0011\u0007Y\fY\u0010\u0002\u0004yC\t\u0007\u0011Q`\t\u0004u\u0006}(C\u0002B\u0001\u0003\u0003\t9AB\u0003\u00007\u0001\ty0\u0003\u0003\u0002d\n\u0015\u0011bAAt\u0015\"9\u00111C\u0011A\u0002\t%\u0001\u0003B%a\u0003s$b!!<\u0003\u000e\t=\u0001bB/#!\u0003\u0005\ra\u0018\u0005\n\u00037\u0014\u0003\u0013!a\u0001\u0003?,\"Aa\u0005+\t\u0005}\u0017Q\u0005\u000b\u0005\u0003?\u00129\u0002C\u0005\u0002h\u001d\n\t\u00111\u0001\u0002VQ!\u0011Q\u0010B\u000e\u0011%\t9'KA\u0001\u0002\u0004\ty\u0006\u0006\u0003\u0002B\t}\u0001\"CA4U\u0005\u0005\t\u0019AA+)\u0011\tiHa\t\t\u0013\u0005\u001dT&!AA\u0002\u0005}\u0013A\u0006$jq\u0016$W*\u001b:s_J$\u0016\u0010]3De\u0016\fGo\u001c:\u0011\u0005\t|3#B\u0018\u0003,\u0005-\u0006#CAQ\u0003O{\u0016q\\Aw)\t\u00119\u0003\u0006\u0004\u0002n\nE\"1\u0007\u0005\u0006;J\u0002\ra\u0018\u0005\b\u00037\u0014\u0004\u0019AAp)\u0011\u00119Da\u000f\u0011\u000by\n\u0019M!\u000f\u0011\ry\nImXAp\u0011%\tymMA\u0001\u0002\u0004\ti\u000f\u0005\u0003\u0003@\t\u0005S\"A\u001b\n\u0007\t\rSGA\u0006Ts6\u0014w\u000e\u001c+bE2,\u0007"
)
public interface StdCreators {
   FixedMirrorTreeCreator$ FixedMirrorTreeCreator();

   FixedMirrorTypeCreator$ FixedMirrorTypeCreator();

   static void $init$(final StdCreators $this) {
   }

   public class FixedMirrorTreeCreator extends TreeCreator implements Product {
      private final Mirror mirror;
      private final Trees.Tree tree;
      // $FF: synthetic field
      public final SymbolTable $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Mirror mirror() {
         return this.mirror;
      }

      public Trees.Tree tree() {
         return this.tree;
      }

      public scala.reflect.api.Trees.TreeApi apply(final Mirror m) {
         if (m == this.mirror()) {
            return this.tree();
         } else {
            throw new IllegalArgumentException((new StringBuilder(53)).append("Expr defined in ").append(this.mirror()).append(" cannot be migrated to other mirrors.").toString());
         }
      }

      public FixedMirrorTreeCreator copy(final Mirror mirror, final Trees.Tree tree) {
         return this.scala$reflect$internal$StdCreators$FixedMirrorTreeCreator$$$outer().new FixedMirrorTreeCreator(mirror, tree);
      }

      public Mirror copy$default$1() {
         return this.mirror();
      }

      public Trees.Tree copy$default$2() {
         return this.tree();
      }

      public String productPrefix() {
         return "FixedMirrorTreeCreator";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.mirror();
            case 1:
               return this.tree();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return new ScalaRunTime..anon.1(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof FixedMirrorTreeCreator;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "mirror";
            case 1:
               return "tree";
            default:
               return (String)Statics.ioobe(x$1);
         }
      }

      public int hashCode() {
         return .MODULE$.productHash(this, -889275714, false);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         if (this != x$1) {
            if (x$1 instanceof FixedMirrorTreeCreator && ((FixedMirrorTreeCreator)x$1).scala$reflect$internal$StdCreators$FixedMirrorTreeCreator$$$outer() == this.scala$reflect$internal$StdCreators$FixedMirrorTreeCreator$$$outer()) {
               FixedMirrorTreeCreator var2 = (FixedMirrorTreeCreator)x$1;
               Mirror var10000 = this.mirror();
               Mirror var3 = var2.mirror();
               if (var10000 == null) {
                  if (var3 != null) {
                     return false;
                  }
               } else if (!var10000.equals(var3)) {
                  return false;
               }

               Trees.Tree var5 = this.tree();
               Trees.Tree var4 = var2.tree();
               if (var5 == null) {
                  if (var4 != null) {
                     return false;
                  }
               } else if (!var5.equals(var4)) {
                  return false;
               }

               if (var2.canEqual(this)) {
                  return true;
               }
            }

            return false;
         } else {
            return true;
         }
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$StdCreators$FixedMirrorTreeCreator$$$outer() {
         return this.$outer;
      }

      public FixedMirrorTreeCreator(final Mirror mirror, final Trees.Tree tree) {
         this.mirror = mirror;
         this.tree = tree;
         if (StdCreators.this == null) {
            throw null;
         } else {
            this.$outer = StdCreators.this;
            super();
         }
      }
   }

   public class FixedMirrorTreeCreator$ extends AbstractFunction2 implements Serializable {
      // $FF: synthetic field
      private final SymbolTable $outer;

      public final String toString() {
         return "FixedMirrorTreeCreator";
      }

      public FixedMirrorTreeCreator apply(final Mirror mirror, final Trees.Tree tree) {
         return this.$outer.new FixedMirrorTreeCreator(mirror, tree);
      }

      public Option unapply(final FixedMirrorTreeCreator x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.mirror(), x$0.tree())));
      }

      public FixedMirrorTreeCreator$() {
         if (StdCreators.this == null) {
            throw null;
         } else {
            this.$outer = StdCreators.this;
            super();
         }
      }
   }

   public class FixedMirrorTypeCreator extends TypeCreator implements Product {
      private final Mirror mirror;
      private final Types.Type tpe;
      // $FF: synthetic field
      public final SymbolTable $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Mirror mirror() {
         return this.mirror;
      }

      public Types.Type tpe() {
         return this.tpe;
      }

      public scala.reflect.api.Types.TypeApi apply(final Mirror m) {
         if (m == this.mirror()) {
            return this.tpe();
         } else {
            throw new IllegalArgumentException((new StringBuilder(57)).append("Type tag defined in ").append(this.mirror()).append(" cannot be migrated to other mirrors.").toString());
         }
      }

      public FixedMirrorTypeCreator copy(final Mirror mirror, final Types.Type tpe) {
         return this.scala$reflect$internal$StdCreators$FixedMirrorTypeCreator$$$outer().new FixedMirrorTypeCreator(mirror, tpe);
      }

      public Mirror copy$default$1() {
         return this.mirror();
      }

      public Types.Type copy$default$2() {
         return this.tpe();
      }

      public String productPrefix() {
         return "FixedMirrorTypeCreator";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.mirror();
            case 1:
               return this.tpe();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return new ScalaRunTime..anon.1(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof FixedMirrorTypeCreator;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "mirror";
            case 1:
               return "tpe";
            default:
               return (String)Statics.ioobe(x$1);
         }
      }

      public int hashCode() {
         return .MODULE$.productHash(this, -889275714, false);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         if (this != x$1) {
            if (x$1 instanceof FixedMirrorTypeCreator && ((FixedMirrorTypeCreator)x$1).scala$reflect$internal$StdCreators$FixedMirrorTypeCreator$$$outer() == this.scala$reflect$internal$StdCreators$FixedMirrorTypeCreator$$$outer()) {
               FixedMirrorTypeCreator var2 = (FixedMirrorTypeCreator)x$1;
               Mirror var10000 = this.mirror();
               Mirror var3 = var2.mirror();
               if (var10000 == null) {
                  if (var3 != null) {
                     return false;
                  }
               } else if (!var10000.equals(var3)) {
                  return false;
               }

               Types.Type var5 = this.tpe();
               Types.Type var4 = var2.tpe();
               if (var5 == null) {
                  if (var4 != null) {
                     return false;
                  }
               } else if (!var5.equals(var4)) {
                  return false;
               }

               if (var2.canEqual(this)) {
                  return true;
               }
            }

            return false;
         } else {
            return true;
         }
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$StdCreators$FixedMirrorTypeCreator$$$outer() {
         return this.$outer;
      }

      public FixedMirrorTypeCreator(final Mirror mirror, final Types.Type tpe) {
         this.mirror = mirror;
         this.tpe = tpe;
         if (StdCreators.this == null) {
            throw null;
         } else {
            this.$outer = StdCreators.this;
            super();
         }
      }
   }

   public class FixedMirrorTypeCreator$ extends AbstractFunction2 implements Serializable {
      // $FF: synthetic field
      private final SymbolTable $outer;

      public final String toString() {
         return "FixedMirrorTypeCreator";
      }

      public FixedMirrorTypeCreator apply(final Mirror mirror, final Types.Type tpe) {
         return this.$outer.new FixedMirrorTypeCreator(mirror, tpe);
      }

      public Option unapply(final FixedMirrorTypeCreator x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.mirror(), x$0.tpe())));
      }

      public FixedMirrorTypeCreator$() {
         if (StdCreators.this == null) {
            throw null;
         } else {
            this.$outer = StdCreators.this;
            super();
         }
      }
   }
}
