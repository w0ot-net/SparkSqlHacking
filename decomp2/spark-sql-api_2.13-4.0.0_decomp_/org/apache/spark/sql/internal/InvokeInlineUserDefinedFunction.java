package org.apache.spark.sql.internal;

import java.io.Serializable;
import org.apache.spark.sql.catalyst.trees.Origin;
import scala.Function1;
import scala.Option;
import scala.PartialFunction;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ug!\u0002\u0014(\u0001&\n\u0004\u0002\u0003'\u0001\u0005+\u0007I\u0011A'\t\u0011E\u0003!\u0011#Q\u0001\n9C\u0001B\u0015\u0001\u0003\u0016\u0004%\ta\u0015\u0005\t/\u0002\u0011\t\u0012)A\u0005)\"A\u0001\f\u0001BK\u0002\u0013\u0005\u0011\f\u0003\u0005^\u0001\tE\t\u0015!\u0003[\u0011!q\u0006A!f\u0001\n\u0003z\u0006\u0002\u00035\u0001\u0005#\u0005\u000b\u0011\u00021\t\u000b%\u0004A\u0011\u00016\t\rA\u0004A\u0011I\u0014r\u0011\u0015Q\u0003\u0001\"\u0011s\u0011\u0019Y\b\u0001\"\u0011(y\"I\u00111\u0001\u0001\u0002\u0002\u0013\u0005\u0011Q\u0001\u0005\n\u0003\u001f\u0001\u0011\u0013!C\u0001\u0003#A\u0011\"a\n\u0001#\u0003%\t!!\u000b\t\u0013\u00055\u0002!%A\u0005\u0002\u0005=\u0002\"CA\u001a\u0001E\u0005I\u0011AA\u001b\u0011%\tI\u0004AA\u0001\n\u0003\nY\u0004C\u0005\u0002L\u0001\t\t\u0011\"\u0001\u0002N!I\u0011Q\u000b\u0001\u0002\u0002\u0013\u0005\u0011q\u000b\u0005\n\u0003G\u0002\u0011\u0011!C!\u0003KB\u0011\"a\u001d\u0001\u0003\u0003%\t!!\u001e\t\u0013\u0005e\u0004!!A\u0005B\u0005m\u0004\"CA@\u0001\u0005\u0005I\u0011IAA\u0011%\t\u0019\tAA\u0001\n\u0003\n)\tC\u0005\u0002\b\u0002\t\t\u0011\"\u0011\u0002\n\u001eQ\u0011QR\u0014\u0002\u0002#\u0005\u0011&a$\u0007\u0013\u0019:\u0013\u0011!E\u0001S\u0005E\u0005BB5\u001d\t\u0003\tI\u000bC\u0005\u0002\u0004r\t\t\u0011\"\u0012\u0002\u0006\"I\u00111\u0016\u000f\u0002\u0002\u0013\u0005\u0015Q\u0016\u0005\n\u0003oc\u0012\u0013!C\u0001\u0003_A\u0011\"!/\u001d#\u0003%\t!!\u000e\t\u0013\u0005mF$!A\u0005\u0002\u0006u\u0006\"CAh9E\u0005I\u0011AA\u0018\u0011%\t\t\u000eHI\u0001\n\u0003\t)\u0004C\u0005\u0002Tr\t\t\u0011\"\u0003\u0002V\ny\u0012J\u001c<pW\u0016Le\u000e\\5oKV\u001bXM\u001d#fM&tW\r\u001a$v]\u000e$\u0018n\u001c8\u000b\u0005!J\u0013\u0001C5oi\u0016\u0014h.\u00197\u000b\u0005)Z\u0013aA:rY*\u0011A&L\u0001\u0006gB\f'o\u001b\u0006\u0003]=\na!\u00199bG\",'\"\u0001\u0019\u0002\u0007=\u0014xmE\u0003\u0001eabt\b\u0005\u00024m5\tAGC\u00016\u0003\u0015\u00198-\u00197b\u0013\t9DG\u0001\u0004B]f\u0014VM\u001a\t\u0003sij\u0011aJ\u0005\u0003w\u001d\u0012!bQ8mk6tgj\u001c3f!\t\u0019T(\u0003\u0002?i\t9\u0001K]8ek\u000e$\bC\u0001!J\u001d\t\tuI\u0004\u0002C\r6\t1I\u0003\u0002E\u000b\u00061AH]8piz\u001a\u0001!C\u00016\u0013\tAE'A\u0004qC\u000e\\\u0017mZ3\n\u0005)[%\u0001D*fe&\fG.\u001b>bE2,'B\u0001%5\u0003!1WO\\2uS>tW#\u0001(\u0011\u0005ez\u0015B\u0001)(\u0005])6/\u001a:EK\u001aLg.\u001a3Gk:\u001cG/[8o\u0019&\\W-A\u0005gk:\u001cG/[8oA\u0005I\u0011M]4v[\u0016tGo]\u000b\u0002)B\u0019\u0001)\u0016\u001d\n\u0005Y[%aA*fc\u0006Q\u0011M]4v[\u0016tGo\u001d\u0011\u0002\u0015%\u001cH)[:uS:\u001cG/F\u0001[!\t\u00194,\u0003\u0002]i\t9!i\\8mK\u0006t\u0017aC5t\t&\u001cH/\u001b8di\u0002\naa\u001c:jO&tW#\u00011\u0011\u0005\u00054W\"\u00012\u000b\u0005\r$\u0017!\u0002;sK\u0016\u001c(BA3*\u0003!\u0019\u0017\r^1msN$\u0018BA4c\u0005\u0019y%/[4j]\u00069qN]5hS:\u0004\u0013A\u0002\u001fj]&$h\bF\u0003lY6tw\u000e\u0005\u0002:\u0001!)A*\u0003a\u0001\u001d\")!+\u0003a\u0001)\"9\u0001,\u0003I\u0001\u0002\u0004Q\u0006b\u00020\n!\u0003\u0005\r\u0001Y\u0001\n]>\u0014X.\u00197ju\u0016$\u0012a[\u000b\u0002gB\u0011A\u000f\u001f\b\u0003kZ\u0004\"A\u0011\u001b\n\u0005]$\u0014A\u0002)sK\u0012,g-\u0003\u0002zu\n11\u000b\u001e:j]\u001eT!a\u001e\u001b\u0002\u0011\rD\u0017\u000e\u001c3sK:,\u0012! \t\u0004\u0001Vs\bCA\u001d\u0000\u0013\r\t\ta\n\u0002\u000f\u0007>dW/\u001c8O_\u0012,G*[6f\u0003\u0011\u0019w\u000e]=\u0015\u0013-\f9!!\u0003\u0002\f\u00055\u0001b\u0002'\u000e!\u0003\u0005\rA\u0014\u0005\b%6\u0001\n\u00111\u0001U\u0011\u001dAV\u0002%AA\u0002iCqAX\u0007\u0011\u0002\u0003\u0007\u0001-\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\u0005M!f\u0001(\u0002\u0016-\u0012\u0011q\u0003\t\u0005\u00033\t\u0019#\u0004\u0002\u0002\u001c)!\u0011QDA\u0010\u0003%)hn\u00195fG.,GMC\u0002\u0002\"Q\n!\"\u00198o_R\fG/[8o\u0013\u0011\t)#a\u0007\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0005\u0005-\"f\u0001+\u0002\u0016\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\u001aTCAA\u0019U\rQ\u0016QC\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00135+\t\t9DK\u0002a\u0003+\tQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAA\u001f!\u0011\ty$!\u0013\u000e\u0005\u0005\u0005#\u0002BA\"\u0003\u000b\nA\u0001\\1oO*\u0011\u0011qI\u0001\u0005U\u00064\u0018-C\u0002z\u0003\u0003\nA\u0002\u001d:pIV\u001cG/\u0011:jif,\"!a\u0014\u0011\u0007M\n\t&C\u0002\u0002TQ\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!!\u0017\u0002`A\u00191'a\u0017\n\u0007\u0005uCGA\u0002B]fD\u0011\"!\u0019\u0015\u0003\u0003\u0005\r!a\u0014\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\t9\u0007\u0005\u0004\u0002j\u0005=\u0014\u0011L\u0007\u0003\u0003WR1!!\u001c5\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003c\nYG\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGc\u0001.\u0002x!I\u0011\u0011\r\f\u0002\u0002\u0003\u0007\u0011\u0011L\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0002>\u0005u\u0004\"CA1/\u0005\u0005\t\u0019AA(\u0003!A\u0017m\u001d5D_\u0012,GCAA(\u0003!!xn\u0015;sS:<GCAA\u001f\u0003\u0019)\u0017/^1mgR\u0019!,a#\t\u0013\u0005\u0005$$!AA\u0002\u0005e\u0013aH%om>\\W-\u00138mS:,Wk]3s\t\u00164\u0017N\\3e\rVt7\r^5p]B\u0011\u0011\bH\n\u00069\u0005M\u0015q\u0014\t\n\u0003+\u000bYJ\u0014+[A.l!!a&\u000b\u0007\u0005eE'A\u0004sk:$\u0018.\\3\n\t\u0005u\u0015q\u0013\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:$\u0004\u0003BAQ\u0003Ok!!a)\u000b\t\u0005\u0015\u0016QI\u0001\u0003S>L1ASAR)\t\ty)A\u0003baBd\u0017\u0010F\u0005l\u0003_\u000b\t,a-\u00026\")Aj\ba\u0001\u001d\")!k\ba\u0001)\"9\u0001l\bI\u0001\u0002\u0004Q\u0006b\u00020 !\u0003\u0005\r\u0001Y\u0001\u0010CB\u0004H.\u001f\u0013eK\u001a\fW\u000f\u001c;%g\u0005y\u0011\r\u001d9ms\u0012\"WMZ1vYR$C'A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005}\u00161\u001a\t\u0006g\u0005\u0005\u0017QY\u0005\u0004\u0003\u0007$$AB(qi&|g\u000eE\u00044\u0003\u000ftEK\u00171\n\u0007\u0005%GG\u0001\u0004UkBdW\r\u000e\u0005\t\u0003\u001b\u0014\u0013\u0011!a\u0001W\u0006\u0019\u0001\u0010\n\u0019\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00134\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%i\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011q\u001b\t\u0005\u0003\u007f\tI.\u0003\u0003\u0002\\\u0006\u0005#AB(cU\u0016\u001cG\u000f"
)
public class InvokeInlineUserDefinedFunction implements ColumnNode, Product, Serializable {
   private final UserDefinedFunctionLike function;
   private final Seq arguments;
   private final boolean isDistinct;
   private final Origin origin;
   private ColumnNode normalized;
   private volatile boolean bitmap$0;

   public static Origin $lessinit$greater$default$4() {
      return InvokeInlineUserDefinedFunction$.MODULE$.$lessinit$greater$default$4();
   }

   public static boolean $lessinit$greater$default$3() {
      return InvokeInlineUserDefinedFunction$.MODULE$.$lessinit$greater$default$3();
   }

   public static Option unapply(final InvokeInlineUserDefinedFunction x$0) {
      return InvokeInlineUserDefinedFunction$.MODULE$.unapply(x$0);
   }

   public static Origin apply$default$4() {
      return InvokeInlineUserDefinedFunction$.MODULE$.apply$default$4();
   }

   public static boolean apply$default$3() {
      return InvokeInlineUserDefinedFunction$.MODULE$.apply$default$3();
   }

   public static InvokeInlineUserDefinedFunction apply(final UserDefinedFunctionLike function, final Seq arguments, final boolean isDistinct, final Origin origin) {
      return InvokeInlineUserDefinedFunction$.MODULE$.apply(function, arguments, isDistinct, origin);
   }

   public static Function1 tupled() {
      return InvokeInlineUserDefinedFunction$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return InvokeInlineUserDefinedFunction$.MODULE$.curried();
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

   private ColumnNode normalized$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.normalized = ColumnNode.normalized$(this);
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.normalized;
   }

   public ColumnNode normalized() {
      return !this.bitmap$0 ? this.normalized$lzycompute() : this.normalized;
   }

   public UserDefinedFunctionLike function() {
      return this.function;
   }

   public Seq arguments() {
      return this.arguments;
   }

   public boolean isDistinct() {
      return this.isDistinct;
   }

   public Origin origin() {
      return this.origin;
   }

   public InvokeInlineUserDefinedFunction normalize() {
      Seq x$1 = ColumnNode$.MODULE$.normalize(this.arguments());
      Origin x$2 = ColumnNode$.MODULE$.NO_ORIGIN();
      UserDefinedFunctionLike x$3 = this.copy$default$1();
      boolean x$4 = this.copy$default$3();
      return this.copy(x$3, x$1, x$4, x$2);
   }

   public String sql() {
      String var10000 = this.function().name();
      return var10000 + ColumnNode$.MODULE$.argumentsToSql(this.arguments());
   }

   public Seq children() {
      return this.arguments();
   }

   public InvokeInlineUserDefinedFunction copy(final UserDefinedFunctionLike function, final Seq arguments, final boolean isDistinct, final Origin origin) {
      return new InvokeInlineUserDefinedFunction(function, arguments, isDistinct, origin);
   }

   public UserDefinedFunctionLike copy$default$1() {
      return this.function();
   }

   public Seq copy$default$2() {
      return this.arguments();
   }

   public boolean copy$default$3() {
      return this.isDistinct();
   }

   public Origin copy$default$4() {
      return this.origin();
   }

   public String productPrefix() {
      return "InvokeInlineUserDefinedFunction";
   }

   public int productArity() {
      return 4;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.function();
         }
         case 1 -> {
            return this.arguments();
         }
         case 2 -> {
            return BoxesRunTime.boxToBoolean(this.isDistinct());
         }
         case 3 -> {
            return this.origin();
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
      return x$1 instanceof InvokeInlineUserDefinedFunction;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "function";
         }
         case 1 -> {
            return "arguments";
         }
         case 2 -> {
            return "isDistinct";
         }
         case 3 -> {
            return "origin";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.function()));
      var1 = Statics.mix(var1, Statics.anyHash(this.arguments()));
      var1 = Statics.mix(var1, this.isDistinct() ? 1231 : 1237);
      var1 = Statics.mix(var1, Statics.anyHash(this.origin()));
      return Statics.finalizeHash(var1, 4);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10;
      if (this != x$1) {
         label67: {
            if (x$1 instanceof InvokeInlineUserDefinedFunction) {
               InvokeInlineUserDefinedFunction var4 = (InvokeInlineUserDefinedFunction)x$1;
               if (this.isDistinct() == var4.isDistinct()) {
                  label60: {
                     UserDefinedFunctionLike var10000 = this.function();
                     UserDefinedFunctionLike var5 = var4.function();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label60;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label60;
                     }

                     Seq var8 = this.arguments();
                     Seq var6 = var4.arguments();
                     if (var8 == null) {
                        if (var6 != null) {
                           break label60;
                        }
                     } else if (!var8.equals(var6)) {
                        break label60;
                     }

                     Origin var9 = this.origin();
                     Origin var7 = var4.origin();
                     if (var9 == null) {
                        if (var7 != null) {
                           break label60;
                        }
                     } else if (!var9.equals(var7)) {
                        break label60;
                     }

                     if (var4.canEqual(this)) {
                        break label67;
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

   public InvokeInlineUserDefinedFunction(final UserDefinedFunctionLike function, final Seq arguments, final boolean isDistinct, final Origin origin) {
      this.function = function;
      this.arguments = arguments;
      this.isDistinct = isDistinct;
      this.origin = origin;
      ColumnNodeLike.$init$(this);
      ColumnNode.$init$(this);
      Product.$init$(this);
   }
}
