package org.json4s;

import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala..less.colon.less.;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ed\u0001B\u0010!\u0001\u0016B\u0001\u0002\u0010\u0001\u0003\u0016\u0004%\t!\u0010\u0005\t\u0019\u0002\u0011\t\u0012)A\u0005}!)Q\n\u0001C\u0001\u001d\u0016!\u0011\u000b\u0001\u0001S\u0011\u0015\u0001\u0007\u0001\"\u0001b\u0011\u0015\u0011\u0007\u0001\"\u0011d\u0011\u0015I\u0007\u0001\"\u0011k\u0011\u001dq\u0007!!A\u0005\u0002=Dq!\u001d\u0001\u0012\u0002\u0013\u0005!\u000fC\u0004~\u0001\u0005\u0005I\u0011\t@\t\u0013\u00055\u0001!!A\u0005\u0002\u0005=\u0001\"CA\t\u0001\u0005\u0005I\u0011AA\n\u0011%\tI\u0002AA\u0001\n\u0003\nY\u0002C\u0005\u0002*\u0001\t\t\u0011\"\u0001\u0002,!I\u0011q\u0006\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0007\u0005\n\u0003k\u0001\u0011\u0011!C!\u0003o9q!!\u000f!\u0011\u0003\u000bYD\u0002\u0004 A!\u0005\u0015Q\b\u0005\u0007\u001bJ!\t!!\u0012\t\u000f\u0005\u001d#\u0003\"\u0001\u0002J!I\u0011q\t\n\u0002\u0002\u0013\u0005\u0015Q\u000b\u0005\n\u00033\u0012\u0012\u0011!CA\u00037Bq! \n\u0002\u0002\u0013\u0005c\u0010C\u0005\u0002\u000eI\t\t\u0011\"\u0001\u0002\u0010!I\u0011\u0011\u0003\n\u0002\u0002\u0013\u0005\u0011q\r\u0005\n\u00033\u0011\u0012\u0011!C!\u00037A\u0011\"!\u000b\u0013\u0003\u0003%\t!a\u001b\t\u000f%\u0014\u0012\u0011!C!U\"I\u0011Q\u0007\n\u0002\u0002\u0013\u0005\u0013q\u0007\u0005\n\u0003_\u0012\u0012\u0011!C\u0005\u0003c\u0012qAS(cU\u0016\u001cGO\u0003\u0002\"E\u00051!n]8oiMT\u0011aI\u0001\u0004_J<7\u0001A\n\u0005\u0001\u0019R\u0003\u0007\u0005\u0002(Q5\t\u0001%\u0003\u0002*A\t1!JV1mk\u0016\u0004\"a\u000b\u0018\u000e\u00031R\u0011!L\u0001\u0006g\u000e\fG.Y\u0005\u0003_1\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00022s9\u0011!g\u000e\b\u0003gYj\u0011\u0001\u000e\u0006\u0003k\u0011\na\u0001\u0010:p_Rt\u0014\"A\u0017\n\u0005ab\u0013a\u00029bG.\fw-Z\u0005\u0003um\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001\u000f\u0017\u0002\u0007=\u0014'.F\u0001?!\r\tt(Q\u0005\u0003\u0001n\u0012A\u0001T5tiB\u0011!)\u0013\b\u0003\u0007\u001es!\u0001\u0012$\u000f\u0005M*\u0015\"A\u0012\n\u0005\u0005\u0012\u0013B\u0001%!\u0003\u001dQ5o\u001c8B'RK!AS&\u0003\r)3\u0015.\u001a7e\u0015\tA\u0005%\u0001\u0003pE*\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002P!B\u0011q\u0005\u0001\u0005\u0006y\r\u0001\rA\u0010\u0002\u0007-\u0006dW/Z:\u0011\tM;&,\u0018\b\u0003)V\u0003\"a\r\u0017\n\u0005Yc\u0013A\u0002)sK\u0012,g-\u0003\u0002Y3\n\u0019Q*\u00199\u000b\u0005Yc\u0003CA*\\\u0013\ta\u0016L\u0001\u0004TiJLgn\u001a\t\u0003WyK!a\u0018\u0017\u0003\u0007\u0005s\u00170\u0001\u0004wC2,Xm]\u000b\u0002%\u00061Q-];bYN$\"\u0001Z4\u0011\u0005-*\u0017B\u00014-\u0005\u001d\u0011un\u001c7fC:DQ\u0001\u001b\u0004A\u0002u\u000bA\u0001\u001e5bi\u0006A\u0001.Y:i\u0007>$W\rF\u0001l!\tYC.\u0003\u0002nY\t\u0019\u0011J\u001c;\u0002\t\r|\u0007/\u001f\u000b\u0003\u001fBDq\u0001\u0010\u0005\u0011\u0002\u0003\u0007a(\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003MT#A\u0010;,\u0003U\u0004\"A^>\u000e\u0003]T!\u0001_=\u0002\u0013Ut7\r[3dW\u0016$'B\u0001>-\u0003)\tgN\\8uCRLwN\\\u0005\u0003y^\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\tq\u0010\u0005\u0003\u0002\u0002\u0005-QBAA\u0002\u0015\u0011\t)!a\u0002\u0002\t1\fgn\u001a\u0006\u0003\u0003\u0013\tAA[1wC&\u0019A,a\u0001\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003-\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000fF\u0002^\u0003+A\u0001\"a\u0006\r\u0003\u0003\u0005\ra[\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005u\u0001#BA\u0010\u0003KiVBAA\u0011\u0015\r\t\u0019\u0003L\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA\u0014\u0003C\u0011\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR\u0019A-!\f\t\u0011\u0005]a\"!AA\u0002u\u000b!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0019q0a\r\t\u0011\u0005]q\"!AA\u0002-\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002\u007f\u00069!j\u00142kK\u000e$\bCA\u0014\u0013'\u0015\u0011\u0012q\b\u00161!\rY\u0013\u0011I\u0005\u0004\u0003\u0007b#AB!osJ+g\r\u0006\u0002\u0002<\u0005)\u0011\r\u001d9msR\u0019q*a\u0013\t\u000f\u00055C\u00031\u0001\u0002P\u0005\u0011am\u001d\t\u0005W\u0005E\u0013)C\u0002\u0002T1\u0012!\u0002\u0010:fa\u0016\fG/\u001a3?)\ry\u0015q\u000b\u0005\u0006yU\u0001\rAP\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\ti&a\u0019\u0011\t-\nyFP\u0005\u0004\u0003Cb#AB(qi&|g\u000e\u0003\u0005\u0002fY\t\t\u00111\u0001P\u0003\rAH\u0005\r\u000b\u0004;\u0006%\u0004\u0002CA\f3\u0005\u0005\t\u0019A6\u0015\u0007\u0011\fi\u0007\u0003\u0005\u0002\u0018m\t\t\u00111\u0001^\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t\u0019\b\u0005\u0003\u0002\u0002\u0005U\u0014\u0002BA<\u0003\u0007\u0011aa\u00142kK\u000e$\b"
)
public class JObject extends JValue {
   private final List obj;

   public static Option unapply(final JObject x$0) {
      return JObject$.MODULE$.unapply(x$0);
   }

   public List obj() {
      return this.obj;
   }

   public Map values() {
      return this.obj().iterator().map((x0$1) -> {
         if (x0$1 != null) {
            String n = (String)x0$1._1();
            JValue v = (JValue)x0$1._2();
            Tuple2 var1 = new Tuple2(n, v.values());
            return var1;
         } else {
            throw new MatchError(x0$1);
         }
      }).toMap(.MODULE$.refl());
   }

   public boolean equals(final Object that) {
      boolean var2;
      if (that instanceof JObject) {
         boolean var6;
         label21: {
            label20: {
               JObject var4 = (JObject)that;
               Set var10000 = this.obj().toSet();
               Set var5 = var4.obj().toSet();
               if (var10000 == null) {
                  if (var5 == null) {
                     break label20;
                  }
               } else if (var10000.equals(var5)) {
                  break label20;
               }

               var6 = false;
               break label21;
            }

            var6 = true;
         }

         var2 = var6;
      } else {
         var2 = false;
      }

      return var2;
   }

   public int hashCode() {
      return this.obj().toSet().hashCode();
   }

   public JObject copy(final List obj) {
      return new JObject(obj);
   }

   public List copy$default$1() {
      return this.obj();
   }

   public String productPrefix() {
      return "JObject";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.obj();
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof JObject;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "obj";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public JObject(final List obj) {
      this.obj = obj;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
