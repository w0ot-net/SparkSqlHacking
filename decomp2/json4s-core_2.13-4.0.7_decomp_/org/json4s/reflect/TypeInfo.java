package org.json4s.reflect;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015f\u0001B\r\u001b\u0001\u0006B\u0001b\u000e\u0001\u0003\u0016\u0004%\t\u0001\u000f\u0005\t\r\u0002\u0011\t\u0012)A\u0005s!Aa\n\u0001BK\u0002\u0013\u0005q\n\u0003\u0005]\u0001\tE\t\u0015!\u0003Q\u0011\u0015i\u0006\u0001\"\u0001_\u0011\u001d9\u0007!!A\u0005\u0002!Dqa\u001b\u0001\u0012\u0002\u0013\u0005A\u000eC\u0004~\u0001E\u0005I\u0011\u0001@\t\u0013\u0005\u0005\u0001!!A\u0005B\u0005\r\u0001\"CA\u0006\u0001\u0005\u0005I\u0011AA\u0007\u0011%\t)\u0002AA\u0001\n\u0003\t9\u0002C\u0005\u0002\u001e\u0001\t\t\u0011\"\u0011\u0002 !I\u0011Q\u0006\u0001\u0002\u0002\u0013\u0005\u0011q\u0006\u0005\n\u0003s\u0001\u0011\u0011!C!\u0003wA\u0011\"a\u0010\u0001\u0003\u0003%\t%!\u0011\t\u0013\u0005\r\u0003!!A\u0005B\u0005\u0015\u0003\"CA$\u0001\u0005\u0005I\u0011IA%\u000f%\tiEGA\u0001\u0012\u0003\tyE\u0002\u0005\u001a5\u0005\u0005\t\u0012AA)\u0011\u0019i6\u0003\"\u0001\u0002r!I\u00111I\n\u0002\u0002\u0013\u0015\u0013Q\t\u0005\n\u0003g\u001a\u0012\u0011!CA\u0003kB\u0011\"a!\u0014\u0003\u0003%\t)!\"\t\u0013\u0005m5#!A\u0005\n\u0005u%\u0001\u0003+za\u0016LeNZ8\u000b\u0005ma\u0012a\u0002:fM2,7\r\u001e\u0006\u0003;y\taA[:p]R\u001a(\"A\u0010\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u0001\u0011\u0003f\u000b\t\u0003G\u0019j\u0011\u0001\n\u0006\u0002K\u0005)1oY1mC&\u0011q\u0005\n\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\rJ\u0013B\u0001\u0016%\u0005\u001d\u0001&o\u001c3vGR\u0004\"\u0001\f\u001b\u000f\u00055\u0012dB\u0001\u00182\u001b\u0005y#B\u0001\u0019!\u0003\u0019a$o\\8u}%\tQ%\u0003\u00024I\u00059\u0001/Y2lC\u001e,\u0017BA\u001b7\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t\u0019D%A\u0003dY\u0006T(0F\u0001:a\tQD\tE\u0002<\u007f\ts!\u0001P\u001f\u0011\u00059\"\u0013B\u0001 %\u0003\u0019\u0001&/\u001a3fM&\u0011\u0001)\u0011\u0002\u0006\u00072\f7o\u001d\u0006\u0003}\u0011\u0002\"a\u0011#\r\u0001\u0011IQIAA\u0001\u0002\u0003\u0015\ta\u0012\u0002\u0004?\u0012\n\u0014AB2mCjT\b%\u0005\u0002I\u0017B\u00111%S\u0005\u0003\u0015\u0012\u0012qAT8uQ&tw\r\u0005\u0002$\u0019&\u0011Q\n\n\u0002\u0004\u0003:L\u0018!\u00059be\u0006lW\r^3sSj,G\rV=qKV\t\u0001\u000bE\u0002$#NK!A\u0015\u0013\u0003\r=\u0003H/[8o!\t!&,D\u0001V\u0015\tYbK\u0003\u0002X1\u0006!A.\u00198h\u0015\u0005I\u0016\u0001\u00026bm\u0006L!aW+\u0003#A\u000b'/Y7fi\u0016\u0014\u0018N_3e)f\u0004X-\u0001\nqCJ\fW.\u001a;fe&TX\r\u001a+za\u0016\u0004\u0013A\u0002\u001fj]&$h\bF\u0002`C\u001a\u0004\"\u0001\u0019\u0001\u000e\u0003iAQaN\u0003A\u0002\t\u0004$aY3\u0011\u0007mzD\r\u0005\u0002DK\u0012IQ)YA\u0001\u0002\u0003\u0015\ta\u0012\u0005\u0006\u001d\u0016\u0001\r\u0001U\u0001\u0005G>\u0004\u0018\u0010F\u0002`S*Dqa\u000e\u0004\u0011\u0002\u0003\u0007!\rC\u0004O\rA\u0005\t\u0019\u0001)\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\tQ\u000e\r\u0002og*\u0012q\u000e\u001e\t\u0004aF\u0014X\"\u0001,\n\u0005\u00013\u0006CA\"t\t%)u!!A\u0001\u0002\u000b\u0005qiK\u0001v!\t180D\u0001x\u0015\tA\u00180A\u0005v]\u000eDWmY6fI*\u0011!\u0010J\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001?x\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\u0005y(F\u0001)u\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u0011Q\u0001\t\u0004a\u0006\u001d\u0011bAA\u0005-\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\"!a\u0004\u0011\u0007\r\n\t\"C\u0002\u0002\u0014\u0011\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$2aSA\r\u0011%\tYbCA\u0001\u0002\u0004\ty!A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003C\u0001R!a\t\u0002*-k!!!\n\u000b\u0007\u0005\u001dB%\u0001\u0006d_2dWm\u0019;j_:LA!a\u000b\u0002&\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\t\t$a\u000e\u0011\u0007\r\n\u0019$C\u0002\u00026\u0011\u0012qAQ8pY\u0016\fg\u000e\u0003\u0005\u0002\u001c5\t\t\u00111\u0001L\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\u0005\u0015\u0011Q\b\u0005\n\u00037q\u0011\u0011!a\u0001\u0003\u001f\t\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003\u001f\t\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003\u000b\ta!Z9vC2\u001cH\u0003BA\u0019\u0003\u0017B\u0001\"a\u0007\u0012\u0003\u0003\u0005\raS\u0001\t)f\u0004X-\u00138g_B\u0011\u0001mE\n\u0006'\u0005M\u0013q\r\t\t\u0003+\nY&a\u0018Q?6\u0011\u0011q\u000b\u0006\u0004\u00033\"\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003;\n9FA\tBEN$(/Y2u\rVt7\r^5p]J\u0002D!!\u0019\u0002fA!1hPA2!\r\u0019\u0015Q\r\u0003\n\u000bN\t\t\u0011!A\u0003\u0002\u001d\u0003B!!\u001b\u0002p5\u0011\u00111\u000e\u0006\u0004\u0003[B\u0016AA5p\u0013\r)\u00141\u000e\u000b\u0003\u0003\u001f\nQ!\u00199qYf$RaXA<\u0003\u0003Caa\u000e\fA\u0002\u0005e\u0004\u0007BA>\u0003\u007f\u0002BaO \u0002~A\u00191)a \u0005\u0015\u0015\u000b9(!A\u0001\u0002\u000b\u0005q\tC\u0003O-\u0001\u0007\u0001+A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005\u001d\u0015q\u0013\t\u0005GE\u000bI\t\u0005\u0004$\u0003\u0017\u000by\tU\u0005\u0004\u0003\u001b##A\u0002+va2,'\u0007\r\u0003\u0002\u0012\u0006U\u0005\u0003B\u001e@\u0003'\u00032aQAK\t%)u#!A\u0001\u0002\u000b\u0005q\t\u0003\u0005\u0002\u001a^\t\t\u00111\u0001`\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003?\u00032\u0001]AQ\u0013\r\t\u0019K\u0016\u0002\u0007\u001f\nTWm\u0019;"
)
public class TypeInfo implements Product, Serializable {
   private final Class clazz;
   private final Option parameterizedType;

   public static Option unapply(final TypeInfo x$0) {
      return TypeInfo$.MODULE$.unapply(x$0);
   }

   public static TypeInfo apply(final Class clazz, final Option parameterizedType) {
      return TypeInfo$.MODULE$.apply(clazz, parameterizedType);
   }

   public static Function1 tupled() {
      return TypeInfo$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return TypeInfo$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Class clazz() {
      return this.clazz;
   }

   public Option parameterizedType() {
      return this.parameterizedType;
   }

   public TypeInfo copy(final Class clazz, final Option parameterizedType) {
      return new TypeInfo(clazz, parameterizedType);
   }

   public Class copy$default$1() {
      return this.clazz();
   }

   public Option copy$default$2() {
      return this.parameterizedType();
   }

   public String productPrefix() {
      return "TypeInfo";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.clazz();
            break;
         case 1:
            var10000 = this.parameterizedType();
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof TypeInfo;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "clazz";
            break;
         case 1:
            var10000 = "parameterizedType";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var9;
      if (this != x$1) {
         label63: {
            boolean var2;
            if (x$1 instanceof TypeInfo) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label45: {
                  label54: {
                     TypeInfo var4 = (TypeInfo)x$1;
                     Class var10000 = this.clazz();
                     Class var5 = var4.clazz();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label54;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label54;
                     }

                     Option var7 = this.parameterizedType();
                     Option var6 = var4.parameterizedType();
                     if (var7 == null) {
                        if (var6 != null) {
                           break label54;
                        }
                     } else if (!var7.equals(var6)) {
                        break label54;
                     }

                     if (var4.canEqual(this)) {
                        var9 = true;
                        break label45;
                     }
                  }

                  var9 = false;
               }

               if (var9) {
                  break label63;
               }
            }

            var9 = false;
            return var9;
         }
      }

      var9 = true;
      return var9;
   }

   public TypeInfo(final Class clazz, final Option parameterizedType) {
      this.clazz = clazz;
      this.parameterizedType = parameterizedType;
      Product.$init$(this);
   }
}
