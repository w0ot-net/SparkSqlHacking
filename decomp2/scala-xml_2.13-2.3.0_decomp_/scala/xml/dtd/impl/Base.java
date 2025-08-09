package scala.xml.dtd.impl;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

/** @deprecated */
@ScalaSignature(
   bytes = "\u0006\u0005\t]bAB\"E\u0003\u00031E\nC\u0003R\u0001\u0011\u00051\u000bB\u0003W\u0001\t\u0005qKB\u0003^\u0001\u0005\u0005a\fC\u0003R\u0007\u0011\u0005q\fC\u0003a\u0007\u0019\u0005\u0011mB\u0003f\u0001!\u0005aMB\u0003h\u0001!\u0005\u0001\u000eC\u0003R\u000f\u0011\u0005\u0011\u000eC\u0003k\u000f\u0011\u00051\u000eC\u0003z\u000f\u0011\u0005!P\u0002\u0003h\u0001\u0001i\u0007\u0002\u00038\f\u0005\u000b\u0007I\u0011A8\t\u0011Q\\!\u0011!Q\u0001\nADQ!U\u0006\u0005\nUDq\u0001Y\u0006C\u0002\u0013\u0015\u0013\r\u0003\u0004x\u0017\u0001\u0006iAY\u0004\b\u0003\u001f\u0001\u0001\u0012AA\t\r\u001d\t\u0019\u0002\u0001E\u0001\u0003+Aa!\u0015\n\u0005\u0002\u0005]\u0001B\u00026\u0013\t\u0003\tI\u0002\u0003\u0004z%\u0011\u0005\u0011Q\u0004\u0004\u0007\u0003'\u0001\u0001!a\t\t\u001194\"Q1A\u0005\u0002=D\u0001\u0002\u001e\f\u0003\u0002\u0003\u0006I\u0001\u001d\u0005\u0007#Z!I!!\n\t\u000f\u00014\"\u0019!C#C\"1qO\u0006Q\u0001\u000e\t4a!!\u000b\u0001\u0001\u0006-\u0002BCA#9\tU\r\u0011\"\u0001\u0002H!I\u0011\u0011\n\u000f\u0003\u0012\u0003\u0006Ia\u001d\u0005\u0007#r!\t!a\u0013\t\u0011\u0001d\u0002R1A\u0005F\u0005D\u0011\"!\u0015\u001d\u0003\u0003%\t!a\u0015\t\u0013\u0005]C$%A\u0005\u0002\u0005e\u0003\"CA89\u0005\u0005I\u0011IA9\u0011%\t\u0019\tHA\u0001\n\u0003\t)\tC\u0005\u0002\u000er\t\t\u0011\"\u0001\u0002\u0010\"I\u00111\u0014\u000f\u0002\u0002\u0013\u0005\u0013Q\u0014\u0005\n\u0003Wc\u0012\u0011!C\u0001\u0003[C\u0011\"!-\u001d\u0003\u0003%\t%a-\t\u0013\u0005]F$!A\u0005B\u0005e\u0006\"CA^9\u0005\u0005I\u0011IA_\u0011%\ty\fHA\u0001\n\u0003\n\tmB\u0005\u0002F\u0002\t\t\u0011#\u0001\u0002H\u001aI\u0011\u0011\u0006\u0001\u0002\u0002#\u0005\u0011\u0011\u001a\u0005\u0007#6\"\t!!9\t\u0013\u0005mV&!A\u0005F\u0005u\u0006\u0002\u00036.\u0003\u0003%\t)a9\t\u0013\u0005\u001dX&!A\u0005\u0002\u0006%xaBA{\u0001!\u0005\u0015q\u001f\u0004\b\u0003s\u0004\u0001\u0012QA~\u0011\u0019\t6\u0007\"\u0001\u0002~\"A\u0001m\rEC\u0002\u0013\u0015\u0013\rC\u0004\u0002<N\"\t%a@\t\u0013\u0005=4'!A\u0005B\u0005E\u0004\"CABg\u0005\u0005I\u0011AAC\u0011%\tiiMA\u0001\n\u0003\u0011y\u0001C\u0005\u0002\u001cN\n\t\u0011\"\u0011\u0002\u001e\"I\u00111V\u001a\u0002\u0002\u0013\u0005!1\u0003\u0005\n\u0003o\u001b\u0014\u0011!C!\u0003s3aAa\u0006\u0001\u0001\te\u0001\"\u0003B\u000e{\t\u0005\t\u0015!\u0003t\u0011\u0019\tV\b\"\u0001\u0003\u001e!9\u0001-\u0010b\u0001\n\u000b\n\u0007BB<>A\u00035!\rC\u0004\u0002Fu\"\t!a\u0012\u0003\t\t\u000b7/\u001a\u0006\u0003\u000b\u001a\u000bA![7qY*\u0011q\tS\u0001\u0004IR$'BA%K\u0003\rAX\u000e\u001c\u0006\u0002\u0017\u0006)1oY1mCN\u0011\u0001!\u0014\t\u0003\u001d>k\u0011AS\u0005\u0003!*\u0013a!\u00118z%\u00164\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003Q\u0003\"!\u0016\u0001\u000e\u0003\u0011\u0013\u0001b\u0018:fO\u0016D\b\u000fV\t\u00031n\u0003\"AT-\n\u0005iS%a\u0002(pi\"Lgn\u001a\t\u00039\u000ei\u0011\u0001\u0001\u0002\u0007%\u0016<W\t\u001f9\u0014\u0005\riE#A.\u0002\u0015%\u001ch*\u001e7mC\ndW-F\u0001c!\tq5-\u0003\u0002e\u0015\n9!i\\8mK\u0006t\u0017aA!miB\u0011Al\u0002\u0002\u0004\u00032$8CA\u0004N)\u00051\u0017!B1qa2LHC\u00017y!\ta6b\u0005\u0002\f7\u0006\u0011!o]\u000b\u0002aB\u0019a*]:\n\u0005IT%A\u0003\u001fsKB,\u0017\r^3e}A\u0011ALA\u0001\u0004eN\u0004CC\u00017w\u0011\u0015qg\u00021\u0001q\u0003-I7OT;mY\u0006\u0014G.\u001a\u0011\t\u000b9L\u0001\u0019\u00019\u0002\u0015Ut\u0017\r\u001d9msN+\u0017\u000fF\u0002|\u0003\u0017\u00012A\u0014?\u007f\u0013\ti(J\u0001\u0003T_6,\u0007\u0003B@\u0002\u0006Mt1ATA\u0001\u0013\r\t\u0019AS\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\t9!!\u0003\u0003\u0007M+\u0017OC\u0002\u0002\u0004)Ca!!\u0004\u000b\u0001\u0004a\u0017!\u0001=\u0002\tM+\u0017/\u001e\t\u00039J\u0011AaU3rkN\u0011!#\u0014\u000b\u0003\u0003#!2aWA\u000e\u0011\u0015qG\u00031\u0001q)\rY\u0018q\u0004\u0005\b\u0003\u001b)\u0002\u0019AA\u0011!\tafc\u0005\u0002\u00177R!\u0011\u0011EA\u0014\u0011\u0015q\u0017\u00041\u0001q\u0005\u0011\u0019F/\u0019:\u0014\rqY\u0016QFA\u001a!\rq\u0015qF\u0005\u0004\u0003cQ%a\u0002)s_\u0012,8\r\u001e\t\u0005\u0003k\t\tE\u0004\u0003\u00028\u0005\u0005a\u0002BA\u001d\u0003\u007fi!!a\u000f\u000b\u0007\u0005u\"+\u0001\u0004=e>|GOP\u0005\u0002\u0017&!\u00111IA\u0005\u00051\u0019VM]5bY&T\u0018M\u00197f\u0003\u0005\u0011X#A:\u0002\u0005I\u0004C\u0003BA'\u0003\u001f\u0002\"\u0001\u0018\u000f\t\r\u0005\u0015s\u00041\u0001t\u0003\u0011\u0019w\u000e]=\u0015\t\u00055\u0013Q\u000b\u0005\t\u0003\u000b\n\u0003\u0013!a\u0001g\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nTCAA.U\r\u0019\u0018QL\u0016\u0003\u0003?\u0002B!!\u0019\u0002l5\u0011\u00111\r\u0006\u0005\u0003K\n9'A\u0005v]\u000eDWmY6fI*\u0019\u0011\u0011\u000e&\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002n\u0005\r$!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006i\u0001O]8ek\u000e$\bK]3gSb,\"!a\u001d\u0011\t\u0005U\u0014qP\u0007\u0003\u0003oRA!!\u001f\u0002|\u0005!A.\u00198h\u0015\t\ti(\u0001\u0003kCZ\f\u0017\u0002BAA\u0003o\u0012aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLXCAAD!\rq\u0015\u0011R\u0005\u0004\u0003\u0017S%aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BAI\u0003/\u00032ATAJ\u0013\r\t)J\u0013\u0002\u0004\u0003:L\b\"CAMK\u0005\u0005\t\u0019AAD\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011q\u0014\t\u0007\u0003C\u000b9+!%\u000e\u0005\u0005\r&bAAS\u0015\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005%\u00161\u0015\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000eF\u0002c\u0003_C\u0011\"!'(\u0003\u0003\u0005\r!!%\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003g\n)\fC\u0005\u0002\u001a\"\n\t\u00111\u0001\u0002\b\u0006A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002\b\u0006AAo\\*ue&tw\r\u0006\u0002\u0002t\u00051Q-];bYN$2AYAb\u0011%\tIjKA\u0001\u0002\u0004\t\t*\u0001\u0003Ti\u0006\u0014\bC\u0001/.'\u0015i\u00131ZAl!\u001d\ti-a5t\u0003\u001bj!!a4\u000b\u0007\u0005E'*A\u0004sk:$\u0018.\\3\n\t\u0005U\u0017q\u001a\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\f\u0004\u0003BAm\u0003?l!!a7\u000b\t\u0005u\u00171P\u0001\u0003S>LA!a\u0011\u0002\\R\u0011\u0011q\u0019\u000b\u0005\u0003\u001b\n)\u000f\u0003\u0004\u0002FA\u0002\ra]\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\tY/!=\u0011\t9\u000bio]\u0005\u0004\u0003_T%AB(qi&|g\u000eC\u0005\u0002tF\n\t\u00111\u0001\u0002N\u0005\u0019\u0001\u0010\n\u0019\u0002\u0007\u0015\u00038\u000f\u0005\u0002]g\t\u0019Q\t]:\u0014\rMZ\u0016QFA\u001a)\t\t9\u0010\u0006\u0002\u0003\u0002A!!1\u0001B\u0006\u001d\u0011\u0011)Aa\u0002\u0011\u0007\u0005e\"*C\u0002\u0003\n)\u000ba\u0001\u0015:fI\u00164\u0017\u0002BAA\u0005\u001bQ1A!\u0003K)\u0011\t\tJ!\u0005\t\u0013\u0005e\u0015(!AA\u0002\u0005\u001dEc\u00012\u0003\u0016!I\u0011\u0011T\u001e\u0002\u0002\u0003\u0007\u0011\u0011\u0013\u0002\u0005\u001b\u0016$\u0018m\u0005\u0002>7\u0006\u0011!/\r\u000b\u0005\u0005?\u0011\t\u0003\u0005\u0002]{!1!1D A\u0002MD3\u0002\u0001B\u0013\u0005W\u0011iC!\r\u00034A\u0019aJa\n\n\u0007\t%\"J\u0001\u0006eKB\u0014XmY1uK\u0012\fq!\\3tg\u0006<W-\t\u0002\u00030\u0005QB\u000b[5tA\rd\u0017m]:!o&dG\u000e\t2fAI,Wn\u001c<fI\u0006)1/\u001b8dK\u0006\u0012!QG\u0001\u0007e9\n\u0004G\f\u0019"
)
public abstract class Base {
   private volatile Alt$ Alt$module;
   private volatile Sequ$ Sequ$module;
   private volatile Star$ Star$module;
   private volatile Eps$ Eps$module;

   public Alt$ Alt() {
      if (this.Alt$module == null) {
         this.Alt$lzycompute$1();
      }

      return this.Alt$module;
   }

   public Sequ$ Sequ() {
      if (this.Sequ$module == null) {
         this.Sequ$lzycompute$1();
      }

      return this.Sequ$module;
   }

   public Star$ Star() {
      if (this.Star$module == null) {
         this.Star$lzycompute$1();
      }

      return this.Star$module;
   }

   public Eps$ Eps() {
      if (this.Eps$module == null) {
         this.Eps$lzycompute$1();
      }

      return this.Eps$module;
   }

   private final void Alt$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Alt$module == null) {
            this.Alt$module = new Alt$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void Sequ$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Sequ$module == null) {
            this.Sequ$module = new Sequ$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void Star$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Star$module == null) {
            this.Star$module = new Star$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void Eps$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Eps$module == null) {
            this.Eps$module = new Eps$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   public abstract class RegExp {
      // $FF: synthetic field
      public final Base $outer;

      public abstract boolean isNullable();

      // $FF: synthetic method
      public Base scala$xml$dtd$impl$Base$RegExp$$$outer() {
         return this.$outer;
      }

      public RegExp() {
         if (Base.this == null) {
            throw null;
         } else {
            this.$outer = Base.this;
            super();
         }
      }
   }

   public class Alt$ {
      // $FF: synthetic field
      private final Base $outer;

      public Alt apply(final Seq rs) {
         if (rs.size() < 2) {
            throw new SyntaxError("need at least 2 branches in Alt");
         } else {
            return this.$outer.new Alt(rs);
         }
      }

      public Some unapplySeq(final Alt x) {
         return new Some(x.rs());
      }

      public Alt$() {
         if (Base.this == null) {
            throw null;
         } else {
            this.$outer = Base.this;
            super();
         }
      }
   }

   public class Alt extends RegExp {
      private final Seq rs;
      private final boolean isNullable;

      public Seq rs() {
         return this.rs;
      }

      public final boolean isNullable() {
         return this.isNullable;
      }

      // $FF: synthetic method
      public Base scala$xml$dtd$impl$Base$Alt$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$isNullable$1(final RegExp x$1) {
         return x$1.isNullable();
      }

      public Alt(final Seq rs) {
         this.rs = rs;
         this.isNullable = rs.exists((x$1) -> BoxesRunTime.boxToBoolean($anonfun$isNullable$1(x$1)));
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class Sequ$ {
      // $FF: synthetic field
      private final Base $outer;

      public RegExp apply(final Seq rs) {
         return (RegExp)(rs.isEmpty() ? this.$outer.Eps() : this.$outer.new Sequ(rs));
      }

      public Some unapplySeq(final Sequ x) {
         return new Some(x.rs());
      }

      public Sequ$() {
         if (Base.this == null) {
            throw null;
         } else {
            this.$outer = Base.this;
            super();
         }
      }
   }

   public class Sequ extends RegExp {
      private final Seq rs;
      private final boolean isNullable;

      public Seq rs() {
         return this.rs;
      }

      public final boolean isNullable() {
         return this.isNullable;
      }

      // $FF: synthetic method
      public Base scala$xml$dtd$impl$Base$Sequ$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$isNullable$2(final RegExp x$2) {
         return x$2.isNullable();
      }

      public Sequ(final Seq rs) {
         this.rs = rs;
         this.isNullable = rs.forall((x$2) -> BoxesRunTime.boxToBoolean($anonfun$isNullable$2(x$2)));
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class Star extends RegExp implements Product, Serializable {
      private boolean isNullable;
      private final RegExp r;
      private volatile boolean bitmap$0;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public RegExp r() {
         return this.r;
      }

      private boolean isNullable$lzycompute() {
         synchronized(this){}

         try {
            if (!this.bitmap$0) {
               this.isNullable = true;
               this.bitmap$0 = true;
            }
         } catch (Throwable var3) {
            throw var3;
         }

         return this.isNullable;
      }

      public final boolean isNullable() {
         return !this.bitmap$0 ? this.isNullable$lzycompute() : this.isNullable;
      }

      public Star copy(final RegExp r) {
         return this.scala$xml$dtd$impl$Base$Star$$$outer().new Star(r);
      }

      public RegExp copy$default$1() {
         return this.r();
      }

      public String productPrefix() {
         return "Star";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.r();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Star;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "r";
            default:
               return (String)Statics.ioobe(x$1);
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
            label52: {
               if (x$1 instanceof Star && ((Star)x$1).scala$xml$dtd$impl$Base$Star$$$outer() == this.scala$xml$dtd$impl$Base$Star$$$outer()) {
                  label42: {
                     Star var4 = (Star)x$1;
                     RegExp var10000 = this.r();
                     RegExp var5 = var4.r();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label42;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label42;
                     }

                     if (var4.canEqual(this)) {
                        break label52;
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

      // $FF: synthetic method
      public Base scala$xml$dtd$impl$Base$Star$$$outer() {
         return this.$outer;
      }

      public Star(final RegExp r) {
         this.r = r;
         Product.$init$(this);
      }
   }

   public class Star$ extends AbstractFunction1 implements Serializable {
      // $FF: synthetic field
      private final Base $outer;

      public final String toString() {
         return "Star";
      }

      public Star apply(final RegExp r) {
         return this.$outer.new Star(r);
      }

      public Option unapply(final Star x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.r()));
      }

      public Star$() {
         if (Base.this == null) {
            throw null;
         } else {
            this.$outer = Base.this;
            super();
         }
      }
   }

   public class Eps$ extends RegExp implements Product, Serializable {
      private boolean isNullable;
      private volatile boolean bitmap$0;

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      private boolean isNullable$lzycompute() {
         synchronized(this){}

         try {
            if (!this.bitmap$0) {
               this.isNullable = true;
               this.bitmap$0 = true;
            }
         } catch (Throwable var3) {
            throw var3;
         }

         return this.isNullable;
      }

      public final boolean isNullable() {
         return !this.bitmap$0 ? this.isNullable$lzycompute() : this.isNullable;
      }

      public String toString() {
         return "Eps";
      }

      public String productPrefix() {
         return "Eps";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Eps$;
      }

      public int hashCode() {
         return 69896;
      }

      public Eps$() {
         Product.$init$(this);
      }
   }

   public class Meta extends RegExp {
      private final RegExp r1;
      private final boolean isNullable;

      public final boolean isNullable() {
         return this.isNullable;
      }

      public RegExp r() {
         return this.r1;
      }

      // $FF: synthetic method
      public Base scala$xml$dtd$impl$Base$Meta$$$outer() {
         return this.$outer;
      }

      public Meta(final RegExp r1) {
         this.r1 = r1;
         this.isNullable = r1.isNullable();
      }
   }
}
