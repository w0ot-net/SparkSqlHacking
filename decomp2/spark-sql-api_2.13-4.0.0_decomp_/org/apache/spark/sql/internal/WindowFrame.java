package org.apache.spark.sql.internal;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.PartialFunction;
import scala.Product;
import scala.Some;
import scala.collection.Iterator;
import scala.collection.immutable.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0005f!\u0002+V\u0001^{\u0006\u0002\u0003>\u0001\u0005+\u0007I\u0011A>\t\u0013\t-\u0004A!E!\u0002\u0013a\bB\u0003B,\u0001\tU\r\u0011\"\u0001\u0003n!Q!q\u000e\u0001\u0003\u0012\u0003\u0006IA!\u0017\t\u0015\tu\u0003A!f\u0001\n\u0003\u0011i\u0007\u0003\u0006\u0003r\u0001\u0011\t\u0012)A\u0005\u00053Bq!!\u0005\u0001\t\u0003\u0011\u0019\b\u0003\u0005\u0002^\u0001!\t%\u0016B>\u0011\u001dA\u0006\u0001\"\u0011V\u00033A\u0001\"!\u000e\u0001\t\u0003*\u0016q\u0007\u0005\n\u0003;\u0003\u0011\u0011!C\u0001\u0005{B\u0011\"a)\u0001#\u0003%\tA!\"\t\u0013\t%\u0005!%A\u0005\u0002\t-\u0005\"\u0003BH\u0001E\u0005I\u0011\u0001BF\u0011%\tY\fAA\u0001\n\u0003\nI\u0007C\u0005\u0002>\u0002\t\t\u0011\"\u0001\u0002@\"I\u0011q\u0019\u0001\u0002\u0002\u0013\u0005!\u0011\u0013\u0005\n\u0003+\u0004\u0011\u0011!C!\u0003/D\u0011\"!:\u0001\u0003\u0003%\tA!&\t\u0013\u0005E\b!!A\u0005B\te\u0005\"CA|\u0001\u0005\u0005I\u0011IA}\u0011%\tY\u0010AA\u0001\n\u0003\ni\u0010C\u0005\u0002\u0000\u0002\t\t\u0011\"\u0011\u0003\u001e\u001e1a0\u0016E\u0001/~4q\u0001V+\t\u0002]\u000b\t\u0001C\u0004\u0002\u0012e!\t!a\u0005\u0007\u000f\u0005U\u0011$!\t\u0002\u0018!I\u0001l\u0007BC\u0002\u0013\u0005\u0013\u0011\u0004\u0005\u000b\u0003WY\"\u0011!Q\u0001\n\u0005m\u0001bBA\t7\u0011\u0005\u0011Q\u0006\u0005\t\u0003kYB\u0011I+\u00028\u001d9\u0011\u0011K\r\t\u0002\u0005=caBA%3!\u0005\u00111\n\u0005\b\u0003#\tC\u0011AA'\u000f\u001d\t\u0019&\u0007E\u0001\u0003\u000f2q!!\u0011\u001a\u0011\u0003\t\u0019\u0005C\u0004\u0002\u0012\u0011\"\t!!\u0012\u0007\u000f\u0005U\u0013$!\t\u0002X!9\u0011\u0011\u0003\u0014\u0005\u0002\u0005e\u0003\u0002CA/M\u0011\u0005S+!\u0017\t\u0011\u0005Ub\u0005\"\u0011V\u0003o9qA!\u0002\u001a\u0011\u0003\t9GB\u0004\u0002beA\t!a\u0019\t\u000f\u0005E1\u0006\"\u0001\u0002f!9\u0001l\u000bC!+\u0006%ta\u0002B\u00043!\u0005\u00111\u0011\u0004\b\u0003{J\u0002\u0012AA@\u0011\u001d\t\tb\fC\u0001\u0003\u0003Cq\u0001W\u0018\u0005BU\u000bIgB\u0004\u0003\neA\t!a\u001f\u0007\u000f\u0005U\u0014\u0004#\u0001\u0002x!9\u0011\u0011C\u001a\u0005\u0002\u0005e\u0004b\u0002-4\t\u0003*\u0016\u0011\u000e\u0004\u0007\u0003\u000bK\u0002)a\"\t\u0015\u0005%eG!f\u0001\n\u0003\tY\t\u0003\u0006\u0002\u0014Z\u0012\t\u0012)A\u0005\u0003\u001bCq!!\u00057\t\u0003\t)\n\u0003\u0005\u0002^Y\"\t%VAN\u0011\u001dAf\u0007\"\u0011V\u00033A\u0001\"!\u000e7\t\u0003*\u0016q\u0007\u0005\n\u0003;3\u0014\u0011!C\u0001\u0003?C\u0011\"a)7#\u0003%\t!!*\t\u0013\u0005mf'!A\u0005B\u0005%\u0004\"CA_m\u0005\u0005I\u0011AA`\u0011%\t9MNA\u0001\n\u0003\tI\rC\u0005\u0002VZ\n\t\u0011\"\u0011\u0002X\"I\u0011Q\u001d\u001c\u0002\u0002\u0013\u0005\u0011q\u001d\u0005\n\u0003c4\u0014\u0011!C!\u0003gD\u0011\"a>7\u0003\u0003%\t%!?\t\u0013\u0005mh'!A\u0005B\u0005u\b\"CA\u0000m\u0005\u0005I\u0011\tB\u0001\u000f%\u0011Y!GA\u0001\u0012\u0003\u0011iAB\u0005\u0002\u0006f\t\t\u0011#\u0001\u0003\u0010!9\u0011\u0011C%\u0005\u0002\tu\u0001\"CA~\u0013\u0006\u0005IQIA\u007f\u0011%\u0011y\"SA\u0001\n\u0003\u0013\t\u0003C\u0005\u0003&%\u000b\t\u0011\"!\u0003(!I!1G%\u0002\u0002\u0013%!Q\u0007\u0005\b\u0003\u0013KB\u0011\u0001B\u001f\u0011\u001d\tI)\u0007C\u0001\u0005\u0007B\u0011Ba\b\u001a\u0003\u0003%\tIa\u0014\t\u0013\t\u0015\u0012$!A\u0005\u0002\n}\u0003\"\u0003B\u001a3\u0005\u0005I\u0011\u0002B\u001b\u0005-9\u0016N\u001c3po\u001a\u0013\u0018-\\3\u000b\u0005Y;\u0016\u0001C5oi\u0016\u0014h.\u00197\u000b\u0005aK\u0016aA:rY*\u0011!lW\u0001\u0006gB\f'o\u001b\u0006\u00039v\u000ba!\u00199bG\",'\"\u00010\u0002\u0007=\u0014xmE\u0003\u0001A\u001aTW\u000e\u0005\u0002bI6\t!MC\u0001d\u0003\u0015\u00198-\u00197b\u0013\t)'M\u0001\u0004B]f\u0014VM\u001a\t\u0003O\"l\u0011!V\u0005\u0003SV\u0013abQ8mk6tgj\u001c3f\u0019&\\W\r\u0005\u0002bW&\u0011AN\u0019\u0002\b!J|G-^2u!\tqwO\u0004\u0002pk:\u0011\u0001\u000f^\u0007\u0002c*\u0011!o]\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\t1-\u0003\u0002wE\u00069\u0001/Y2lC\u001e,\u0017B\u0001=z\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t1(-A\u0005ge\u0006lW\rV=qKV\tA\u0010\u0005\u0002~79\u0011q\rG\u0001\f/&tGm\\<Ge\u0006lW\r\u0005\u0002h3M!\u0011\u0004YA\u0002!\u0011\t)!a\u0004\u000e\u0005\u0005\u001d!\u0002BA\u0005\u0003\u0017\t!![8\u000b\u0005\u00055\u0011\u0001\u00026bm\u0006L1\u0001_A\u0004\u0003\u0019a\u0014N\\5u}Q\tqPA\u0005Ge\u0006lW\rV=qKN\u00191\u0004\u00194\u0016\u0005\u0005m\u0001\u0003BA\u000f\u0003KqA!a\b\u0002\"A\u0011\u0001OY\u0005\u0004\u0003G\u0011\u0017A\u0002)sK\u0012,g-\u0003\u0003\u0002(\u0005%\"AB*ue&twMC\u0002\u0002$\t\fAa]9mAQ!\u0011qFA\u001a!\r\t\tdG\u0007\u00023!1\u0001L\ba\u0001\u00037\t\u0001b\u00195jY\u0012\u0014XM\\\u000b\u0003\u0003s\u0001BA\\A\u001eM&\u0019\u0011QH=\u0003\u0007M+\u0017/K\u0002\u001cI\u0005\u0012QAU1oO\u0016\u001c2\u0001JA\u0018)\t\t9\u0005E\u0002\u00022\u0011\u00121AU8x'\r\t\u0013q\u0006\u000b\u0003\u0003\u001f\u00022!!\r\"\u0003\r\u0011vn^\u0001\u0006%\u0006tw-\u001a\u0002\u000e\rJ\fW.\u001a\"pk:$\u0017M]=\u0014\u0007\u0019\u0002g\r\u0006\u0002\u0002\\A\u0019\u0011\u0011\u0007\u0014\u0002\u00139|'/\\1mSj,\u0017&\u0002\u0014,g=2$AC\"veJ,g\u000e\u001e*poN\u00191&a\u0017\u0015\u0005\u0005\u001d\u0004cAA\u0019WU\u0011\u00111\u000e\t\u0005\u0003[\n\u0019(\u0004\u0002\u0002p)!\u0011\u0011OA\u0006\u0003\u0011a\u0017M\\4\n\t\u0005\u001d\u0012q\u000e\u0002\u0013+:\u0014w.\u001e8eK\u00124u\u000e\u001c7po&twmE\u00024\u00037\"\"!a\u001f\u0011\u0007\u0005E2G\u0001\nV]\n|WO\u001c3fIB\u0013XmY3eS:<7cA\u0018\u0002\\Q\u0011\u00111\u0011\t\u0004\u0003cy#!\u0002,bYV,7#\u0002\u001c\u0002\\)l\u0017!\u0002<bYV,WCAAG!\r9\u0017qR\u0005\u0004\u0003#+&AC\"pYVlgNT8eK\u00061a/\u00197vK\u0002\"B!a&\u0002\u001aB\u0019\u0011\u0011\u0007\u001c\t\u000f\u0005%\u0015\b1\u0001\u0002\u000eR\u0011\u0011qS\u0001\u0005G>\u0004\u0018\u0010\u0006\u0003\u0002\u0018\u0006\u0005\u0006\"CAE{A\u0005\t\u0019AAG\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\"!a*+\t\u00055\u0015\u0011V\u0016\u0003\u0003W\u0003B!!,\u000286\u0011\u0011q\u0016\u0006\u0005\u0003c\u000b\u0019,A\u0005v]\u000eDWmY6fI*\u0019\u0011Q\u00172\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002:\u0006=&!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006i\u0001O]8ek\u000e$\bK]3gSb\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\"!!1\u0011\u0007\u0005\f\u0019-C\u0002\u0002F\n\u00141!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!a3\u0002RB\u0019\u0011-!4\n\u0007\u0005='MA\u0002B]fD\u0011\"a5B\u0003\u0003\u0005\r!!1\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\tI\u000e\u0005\u0004\u0002\\\u0006\u0005\u00181Z\u0007\u0003\u0003;T1!a8c\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003G\fiN\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BAu\u0003_\u00042!YAv\u0013\r\tiO\u0019\u0002\b\u0005>|G.Z1o\u0011%\t\u0019nQA\u0001\u0002\u0004\tY-\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA6\u0003kD\u0011\"a5E\u0003\u0003\u0005\r!!1\u0002\u0011!\f7\u000f[\"pI\u0016$\"!!1\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!a\u001b\u0002\r\u0015\fX/\u00197t)\u0011\tIOa\u0001\t\u0013\u0005Mw)!AA\u0002\u0005-\u0017AC\"veJ,g\u000e\u001e*po\u0006\u0011RK\u001c2pk:$W\r\u001a)sK\u000e,G-\u001b8h\u0003I)fNY8v]\u0012,GMR8mY><\u0018N\\4\u0002\u000bY\u000bG.^3\u0011\u0007\u0005E\u0012jE\u0003J\u0005#\t\u0019\u0001\u0005\u0005\u0003\u0014\te\u0011QRAL\u001b\t\u0011)BC\u0002\u0003\u0018\t\fqA];oi&lW-\u0003\u0003\u0003\u001c\tU!!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8ocQ\u0011!QB\u0001\u0006CB\u0004H.\u001f\u000b\u0005\u0003/\u0013\u0019\u0003C\u0004\u0002\n2\u0003\r!!$\u0002\u000fUt\u0017\r\u001d9msR!!\u0011\u0006B\u0018!\u0015\t'1FAG\u0013\r\u0011iC\u0019\u0002\u0007\u001fB$\u0018n\u001c8\t\u0013\tER*!AA\u0002\u0005]\u0015a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011!q\u0007\t\u0005\u0003[\u0012I$\u0003\u0003\u0003<\u0005=$AB(cU\u0016\u001cG\u000f\u0006\u0003\u0002\u0018\n}\u0002b\u0002B!\u001f\u0002\u0007\u0011\u0011Y\u0001\u0002SR!\u0011q\u0013B#\u0011\u001d\u00119\u0005\u0015a\u0001\u0005\u0013\n\u0011\u0001\u001c\t\u0004C\n-\u0013b\u0001B'E\n!Aj\u001c8h)!\u0011\tFa\u0015\u0003V\tm\u0003CA4\u0001\u0011\u0015Q\u0018\u000b1\u0001}\u0011\u001d\u00119&\u0015a\u0001\u00053\nQ\u0001\\8xKJ\u0004\"! \u0014\t\u000f\tu\u0013\u000b1\u0001\u0003Z\u0005)Q\u000f\u001d9feR!!\u0011\rB5!\u0015\t'1\u0006B2!!\t'Q\r?\u0003Z\te\u0013b\u0001B4E\n1A+\u001e9mKNB\u0011B!\rS\u0003\u0003\u0005\rA!\u0015\u0002\u0015\u0019\u0014\u0018-\\3UsB,\u0007%\u0006\u0002\u0003Z\u00051An\\<fe\u0002\na!\u001e9qKJ\u0004C\u0003\u0003B)\u0005k\u00129H!\u001f\t\u000bi<\u0001\u0019\u0001?\t\u000f\t]s\u00011\u0001\u0003Z!9!QL\u0004A\u0002\teCC\u0001B))!\u0011\tFa \u0003\u0002\n\r\u0005b\u0002>\f!\u0003\u0005\r\u0001 \u0005\n\u0005/Z\u0001\u0013!a\u0001\u00053B\u0011B!\u0018\f!\u0003\u0005\rA!\u0017\u0016\u0005\t\u001d%f\u0001?\u0002*\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012TC\u0001BGU\u0011\u0011I&!+\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%gQ!\u00111\u001aBJ\u0011%\t\u0019.EA\u0001\u0002\u0004\t\t\r\u0006\u0003\u0002j\n]\u0005\"CAj'\u0005\u0005\t\u0019AAf)\u0011\tYGa'\t\u0013\u0005MG#!AA\u0002\u0005\u0005G\u0003BAu\u0005?C\u0011\"a5\u0018\u0003\u0003\u0005\r!a3"
)
public class WindowFrame implements ColumnNodeLike, Product, Serializable {
   private final FrameType frameType;
   private final FrameBoundary lower;
   private final FrameBoundary upper;

   public static Option unapply(final WindowFrame x$0) {
      return WindowFrame$.MODULE$.unapply(x$0);
   }

   public static WindowFrame apply(final FrameType frameType, final FrameBoundary lower, final FrameBoundary upper) {
      return WindowFrame$.MODULE$.apply(frameType, lower, upper);
   }

   public static Value value(final long l) {
      return WindowFrame$.MODULE$.value(l);
   }

   public static Value value(final int i) {
      return WindowFrame$.MODULE$.value(i);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public void foreach(final Function1 f) {
      ColumnNodeLike.foreach$(this, f);
   }

   public Seq collect(final PartialFunction pf) {
      return ColumnNodeLike.collect$(this, pf);
   }

   public FrameType frameType() {
      return this.frameType;
   }

   public FrameBoundary lower() {
      return this.lower;
   }

   public FrameBoundary upper() {
      return this.upper;
   }

   public WindowFrame normalize() {
      FrameBoundary x$1 = this.lower().normalize();
      FrameBoundary x$2 = this.upper().normalize();
      FrameType x$3 = this.copy$default$1();
      return this.copy(x$3, x$1, x$2);
   }

   public String sql() {
      String var10000 = this.frameType().sql();
      return var10000 + " BETWEEN " + this.lower().sql() + " AND " + this.upper().sql();
   }

   public Seq children() {
      return new .colon.colon(this.frameType(), new .colon.colon(this.lower(), new .colon.colon(this.upper(), scala.collection.immutable.Nil..MODULE$)));
   }

   public WindowFrame copy(final FrameType frameType, final FrameBoundary lower, final FrameBoundary upper) {
      return new WindowFrame(frameType, lower, upper);
   }

   public FrameType copy$default$1() {
      return this.frameType();
   }

   public FrameBoundary copy$default$2() {
      return this.lower();
   }

   public FrameBoundary copy$default$3() {
      return this.upper();
   }

   public String productPrefix() {
      return "WindowFrame";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.frameType();
         }
         case 1 -> {
            return this.lower();
         }
         case 2 -> {
            return this.upper();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof WindowFrame;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "frameType";
         }
         case 1 -> {
            return "lower";
         }
         case 2 -> {
            return "upper";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10;
      if (this != x$1) {
         label63: {
            if (x$1 instanceof WindowFrame) {
               label56: {
                  WindowFrame var4 = (WindowFrame)x$1;
                  FrameType var10000 = this.frameType();
                  FrameType var5 = var4.frameType();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label56;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label56;
                  }

                  FrameBoundary var8 = this.lower();
                  FrameBoundary var6 = var4.lower();
                  if (var8 == null) {
                     if (var6 != null) {
                        break label56;
                     }
                  } else if (!var8.equals(var6)) {
                     break label56;
                  }

                  var8 = this.upper();
                  FrameBoundary var7 = var4.upper();
                  if (var8 == null) {
                     if (var7 != null) {
                        break label56;
                     }
                  } else if (!var8.equals(var7)) {
                     break label56;
                  }

                  if (var4.canEqual(this)) {
                     break label63;
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

   public WindowFrame(final FrameType frameType, final FrameBoundary lower, final FrameBoundary upper) {
      this.frameType = frameType;
      this.lower = lower;
      this.upper = upper;
      ColumnNodeLike.$init$(this);
      Product.$init$(this);
   }

   public abstract static class FrameType implements ColumnNodeLike {
      private final String sql;

      public ColumnNodeLike normalize() {
         return ColumnNodeLike.normalize$(this);
      }

      public void foreach(final Function1 f) {
         ColumnNodeLike.foreach$(this, f);
      }

      public Seq collect(final PartialFunction pf) {
         return ColumnNodeLike.collect$(this, pf);
      }

      public String sql() {
         return this.sql;
      }

      public Seq children() {
         return (Seq)scala.package..MODULE$.Seq().empty();
      }

      public FrameType(final String sql) {
         this.sql = sql;
         ColumnNodeLike.$init$(this);
      }
   }

   public static class Row$ extends FrameType {
      public static final Row$ MODULE$ = new Row$();

      public Row$() {
         super("ROWS");
      }
   }

   public static class Range$ extends FrameType {
      public static final Range$ MODULE$ = new Range$();

      public Range$() {
         super("RANGE");
      }
   }

   public abstract static class FrameBoundary implements ColumnNodeLike {
      public void foreach(final Function1 f) {
         ColumnNodeLike.foreach$(this, f);
      }

      public Seq collect(final PartialFunction pf) {
         return ColumnNodeLike.collect$(this, pf);
      }

      public FrameBoundary normalize() {
         return this;
      }

      public Seq children() {
         return (Seq)scala.package..MODULE$.Seq().empty();
      }

      public FrameBoundary() {
         ColumnNodeLike.$init$(this);
      }
   }

   public static class CurrentRow$ extends FrameBoundary {
      public static final CurrentRow$ MODULE$ = new CurrentRow$();

      public String sql() {
         return "CURRENT ROW";
      }
   }

   public static class UnboundedPreceding$ extends FrameBoundary {
      public static final UnboundedPreceding$ MODULE$ = new UnboundedPreceding$();

      public String sql() {
         return "UNBOUNDED PRECEDING";
      }
   }

   public static class UnboundedFollowing$ extends FrameBoundary {
      public static final UnboundedFollowing$ MODULE$ = new UnboundedFollowing$();

      public String sql() {
         return "UNBOUNDED FOLLOWING";
      }
   }

   public static class Value extends FrameBoundary implements Product, Serializable {
      private final ColumnNode value;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public ColumnNode value() {
         return this.value;
      }

      public Value normalize() {
         return this.copy(this.value().normalize());
      }

      public String sql() {
         return this.value().sql();
      }

      public Seq children() {
         return new .colon.colon(this.value(), scala.collection.immutable.Nil..MODULE$);
      }

      public Value copy(final ColumnNode value) {
         return new Value(value);
      }

      public ColumnNode copy$default$1() {
         return this.value();
      }

      public String productPrefix() {
         return "Value";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return this.value();
            }
            default -> {
               return Statics.ioobe(x$1);
            }
         }
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Value;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "value";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var6;
         if (this != x$1) {
            label47: {
               if (x$1 instanceof Value) {
                  label40: {
                     Value var4 = (Value)x$1;
                     ColumnNode var10000 = this.value();
                     ColumnNode var5 = var4.value();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label40;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label40;
                     }

                     if (var4.canEqual(this)) {
                        break label47;
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

      public Value(final ColumnNode value) {
         this.value = value;
         Product.$init$(this);
      }
   }

   public static class Value$ extends AbstractFunction1 implements Serializable {
      public static final Value$ MODULE$ = new Value$();

      public final String toString() {
         return "Value";
      }

      public Value apply(final ColumnNode value) {
         return new Value(value);
      }

      public Option unapply(final Value x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.value()));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Value$.class);
      }
   }
}
