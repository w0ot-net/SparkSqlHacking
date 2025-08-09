package org.json4s.scalap.scalasig;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.collection.Iterator;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Md\u0001B\u000f\u001f\u0001\u001eB\u0001\"\u0010\u0001\u0003\u0016\u0004%\tA\u0010\u0005\t\u0005\u0002\u0011\t\u0012)A\u0005\u007f!)1\t\u0001C\u0001\t\"9\u0001\n\u0001b\u0001\n\u0003q\u0004BB%\u0001A\u0003%q\b\u0003\u0004K\u0001\u0001\u0006Ia\u0013\u0005\u00073\u0002\u0001\u000b\u0011\u0002.\t\u000b\u0001\u0004A\u0011A1\t\u000b\u0015\u0004A\u0011\u00014\t\u000b%\u0004A\u0011\u00016\t\u000f5\u0004\u0011\u0011!C\u0001]\"9\u0001\u000fAI\u0001\n\u0003\t\bb\u0002?\u0001\u0003\u0003%\t% \u0005\t\u0003\u001b\u0001\u0011\u0011!C\u0001}!I\u0011q\u0002\u0001\u0002\u0002\u0013\u0005\u0011\u0011\u0003\u0005\n\u0003/\u0001\u0011\u0011!C!\u00033A\u0011\"a\t\u0001\u0003\u0003%\t!!\n\t\u0013\u0005%\u0002!!A\u0005B\u0005-\u0002\"CA\u0018\u0001\u0005\u0005I\u0011IA\u0019\u0011%\t\u0019\u0004AA\u0001\n\u0003\n)\u0004C\u0005\u00028\u0001\t\t\u0011\"\u0011\u0002:\u001dI\u0011Q\b\u0010\u0002\u0002#\u0005\u0011q\b\u0004\t;y\t\t\u0011#\u0001\u0002B!11i\u0006C\u0001\u00033B\u0011\"a\r\u0018\u0003\u0003%)%!\u000e\t\u0011\u0015<\u0012\u0011!CA\u00037B\u0011\"a\u0018\u0018\u0003\u0003%\t)!\u0019\t\u0013\u0005%t#!A\u0005\n\u0005-$\u0001D\"p]N$\u0018M\u001c;Q_>d'BA\u0010!\u0003!\u00198-\u00197bg&<'BA\u0011#\u0003\u0019\u00198-\u00197ba*\u00111\u0005J\u0001\u0007UN|g\u000eN:\u000b\u0003\u0015\n1a\u001c:h\u0007\u0001\u0019B\u0001\u0001\u0015/cA\u0011\u0011\u0006L\u0007\u0002U)\t1&A\u0003tG\u0006d\u0017-\u0003\u0002.U\t1\u0011I\\=SK\u001a\u0004\"!K\u0018\n\u0005AR#a\u0002)s_\u0012,8\r\u001e\t\u0003eir!a\r\u001d\u000f\u0005Q:T\"A\u001b\u000b\u0005Y2\u0013A\u0002\u001fs_>$h(C\u0001,\u0013\tI$&A\u0004qC\u000e\\\u0017mZ3\n\u0005mb$\u0001D*fe&\fG.\u001b>bE2,'BA\u001d+\u0003\raWM\\\u000b\u0002\u007fA\u0011\u0011\u0006Q\u0005\u0003\u0003*\u00121!\u00138u\u0003\u0011aWM\u001c\u0011\u0002\rqJg.\u001b;?)\t)u\t\u0005\u0002G\u00015\ta\u0004C\u0003>\u0007\u0001\u0007q(\u0001\u0003tSj,\u0017!B:ju\u0016\u0004\u0013A\u00022vM\u001a,'\u000fE\u0002M#Nk\u0011!\u0014\u0006\u0003\u001d>\u000bq!\\;uC\ndWM\u0003\u0002QU\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005Ik%aC!se\u0006L()\u001e4gKJ\u0004B!\u000b+F-&\u0011QK\u000b\u0002\n\rVt7\r^5p]F\u0002\"!K,\n\u0005aS#aA!os\u00061a/\u00197vKN\u00042!K.^\u0013\ta&FA\u0003BeJ\f\u0017\u0010E\u0002*=ZK!a\u0018\u0016\u0003\r=\u0003H/[8o\u0003\u0019I7OR;mYV\t!\r\u0005\u0002*G&\u0011AM\u000b\u0002\b\u0005>|G.Z1o\u0003\u0015\t\u0007\u000f\u001d7z)\t1v\rC\u0003i\u0013\u0001\u0007q(A\u0003j]\u0012,\u00070A\u0002bI\u0012$\"!R6\t\u000b1T\u0001\u0019A*\u0002\u0003\u0019\fAaY8qsR\u0011Qi\u001c\u0005\b{-\u0001\n\u00111\u0001@\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012A\u001d\u0016\u0003\u007fM\\\u0013\u0001\u001e\t\u0003kjl\u0011A\u001e\u0006\u0003ob\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005eT\u0013AC1o]>$\u0018\r^5p]&\u00111P\u001e\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001\u007f!\ry\u0018\u0011B\u0007\u0003\u0003\u0003QA!a\u0001\u0002\u0006\u0005!A.\u00198h\u0015\t\t9!\u0001\u0003kCZ\f\u0017\u0002BA\u0006\u0003\u0003\u0011aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRL\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0004-\u0006M\u0001\u0002CA\u000b\u001f\u0005\u0005\t\u0019A \u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\tY\u0002E\u0003\u0002\u001e\u0005}a+D\u0001P\u0013\r\t\tc\u0014\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000eF\u0002c\u0003OA\u0001\"!\u0006\u0012\u0003\u0003\u0005\rAV\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002\u007f\u0003[A\u0001\"!\u0006\u0013\u0003\u0003\u0005\raP\u0001\tQ\u0006\u001c\bnQ8eKR\tq(\u0001\u0005u_N#(/\u001b8h)\u0005q\u0018AB3rk\u0006d7\u000fF\u0002c\u0003wA\u0001\"!\u0006\u0016\u0003\u0003\u0005\rAV\u0001\r\u0007>t7\u000f^1oiB{w\u000e\u001c\t\u0003\r^\u0019RaFA\"\u0003\u001f\u0002b!!\u0012\u0002L}*UBAA$\u0015\r\tIEK\u0001\beVtG/[7f\u0013\u0011\ti%a\u0012\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\u0005\u0003\u0002R\u0005]SBAA*\u0015\u0011\t)&!\u0002\u0002\u0005%|\u0017bA\u001e\u0002TQ\u0011\u0011q\b\u000b\u0004\u000b\u0006u\u0003\"B\u001f\u001b\u0001\u0004y\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u0003G\n)\u0007E\u0002*=~B\u0001\"a\u001a\u001c\u0003\u0003\u0005\r!R\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA7!\ry\u0018qN\u0005\u0005\u0003c\n\tA\u0001\u0004PE*,7\r\u001e"
)
public class ConstantPool implements Product, Serializable {
   private final int len;
   private final int size;
   private final ArrayBuffer buffer;
   private final Option[] values;

   public static Option unapply(final ConstantPool x$0) {
      return ConstantPool$.MODULE$.unapply(x$0);
   }

   public static Function1 andThen(final Function1 g) {
      return ConstantPool$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return ConstantPool$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int len() {
      return this.len;
   }

   public int size() {
      return this.size;
   }

   public boolean isFull() {
      return this.buffer.length() >= this.size();
   }

   public Object apply(final int index) {
      int i = index - 1;
      return this.values[i].getOrElse(() -> {
         Object value = ((Function1)this.buffer.apply(i)).apply(this);
         this.buffer.update(i, (Object)null);
         this.values[i] = new Some(value);
         return value;
      });
   }

   public ConstantPool add(final Function1 f) {
      this.buffer.$plus$eq(f);
      return this;
   }

   public ConstantPool copy(final int len) {
      return new ConstantPool(len);
   }

   public int copy$default$1() {
      return this.len();
   }

   public String productPrefix() {
      return "ConstantPool";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToInteger(this.len());
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
      return x$1 instanceof ConstantPool;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "len";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.len());
      return Statics.finalizeHash(var1, 1);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label49: {
            boolean var2;
            if (x$1 instanceof ConstantPool) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               ConstantPool var4 = (ConstantPool)x$1;
               if (this.len() == var4.len() && var4.canEqual(this)) {
                  break label49;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public ConstantPool(final int len) {
      this.len = len;
      Product.$init$(this);
      this.size = len - 1;
      this.buffer = new ArrayBuffer();
      this.values = (Option[])scala.Array..MODULE$.fill(this.size(), () -> scala.None..MODULE$, scala.reflect.ClassTag..MODULE$.apply(Option.class));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
