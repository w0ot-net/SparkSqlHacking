package spire.math.prime;

import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.math.Ordered;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import spire.math.SafeLong;

@ScalaSignature(
   bytes = "\u0006\u0005\tMt!B(Q\u0011\u00039f!B-Q\u0011\u0003Q\u0006\"B1\u0002\t\u0003\u0011g\u0001B2\u0002\u0001\u0012D\u0001\"_\u0002\u0003\u0016\u0004%\tA\u001f\u0005\t\u007f\u000e\u0011\t\u0012)A\u0005w\"I\u0011\u0011A\u0002\u0003\u0012\u0004%\tA\u001f\u0005\u000b\u0003\u0007\u0019!\u00111A\u0005\u0002\u0005\u0015\u0001\"CA\t\u0007\tE\t\u0015)\u0003|\u0011\u0019\t7\u0001\"\u0001\u0002\u0014!9\u0011\u0011D\u0002\u0005\u0002\u0005m\u0001\"CA\u0014\u0007\u0005\u0005I\u0011AA\u0015\u0011%\tycAI\u0001\n\u0003\t\t\u0004C\u0005\u0002H\r\t\n\u0011\"\u0001\u00022!I\u0011\u0011J\u0002\u0002\u0002\u0013\u0005\u00131\n\u0005\n\u0003;\u001a\u0011\u0011!C\u0001\u0003?B\u0011\"!\u0019\u0004\u0003\u0003%\t!a\u0019\t\u0013\u000554!!A\u0005B\u0005=\u0004\"CA?\u0007\u0005\u0005I\u0011AA@\u0011%\tIiAA\u0001\n\u0003\nY\tC\u0005\u0002\u0010\u000e\t\t\u0011\"\u0011\u0002\u0012\"I\u00111S\u0002\u0002\u0002\u0013\u0005\u0013Q\u0013\u0005\n\u0003/\u001b\u0011\u0011!C!\u00033;\u0011\"!(\u0002\u0003\u0003E\t!a(\u0007\u0011\r\f\u0011\u0011!E\u0001\u0003CCa!\u0019\r\u0005\u0002\u0005e\u0006\"CAJ1\u0005\u0005IQIAK\u0011%\tY\fGA\u0001\n\u0003\u000bi\fC\u0005\u0002Db\t\t\u0011\"!\u0002F\"I\u0011q\u001b\r\u0002\u0002\u0013%\u0011\u0011\u001c\u0004\u0007\u0003C\f\u0001)a9\t\u0013et\"Q3A\u0005\u0002\u0005}\u0003\"C@\u001f\u0005#\u0005\u000b\u0011BA\u000f\u0011%\t)O\bBI\u0002\u0013\u0005!\u0010\u0003\u0006\u0002hz\u0011\t\u0019!C\u0001\u0003SD\u0011\"!<\u001f\u0005#\u0005\u000b\u0015B>\t\r\u0005tB\u0011AAx\u0011%\t9CHA\u0001\n\u0003\t9\u0010C\u0005\u00020y\t\n\u0011\"\u0001\u0002~\"I\u0011q\t\u0010\u0012\u0002\u0013\u0005\u0011\u0011\u0007\u0005\n\u0003\u0013r\u0012\u0011!C!\u0003\u0017B\u0011\"!\u0018\u001f\u0003\u0003%\t!a\u0018\t\u0013\u0005\u0005d$!A\u0005\u0002\t\u0005\u0001\"CA7=\u0005\u0005I\u0011IA8\u0011%\tiHHA\u0001\n\u0003\u0011)\u0001C\u0005\u0002\nz\t\t\u0011\"\u0011\u0003\n!I\u0011q\u0012\u0010\u0002\u0002\u0013\u0005\u0013\u0011\u0013\u0005\n\u0003's\u0012\u0011!C!\u0003+C\u0011\"a&\u001f\u0003\u0003%\tE!\u0004\b\u0013\tE\u0011!!A\t\u0002\tMa!CAq\u0003\u0005\u0005\t\u0012\u0001B\u000b\u0011\u0019\t'\u0007\"\u0001\u0003\u001a!I\u00111\u0013\u001a\u0002\u0002\u0013\u0015\u0013Q\u0013\u0005\n\u0003w\u0013\u0014\u0011!CA\u00057A\u0011\"a13\u0003\u0003%\tI!\t\t\u0013\u0005]''!A\u0005\n\u0005egA\u0002B\u0015\u0003\u0001\u0013Y\u0003\u0003\u0006\u0003.a\u0012\t\u001a!C\u0001\u0005_A!Ba\u000e9\u0005\u0003\u0007I\u0011\u0001B\u001d\u0011)\u0011i\u0004\u000fB\tB\u0003&!\u0011\u0007\u0005\u0007Cb\"\tAa\u0010\t\u0013\u0005\u001d\u0002(!A\u0005\u0002\t\u0015\u0003\"CA\u0018qE\u0005I\u0011\u0001B%\u0011%\tI\u0005OA\u0001\n\u0003\nY\u0005C\u0005\u0002^a\n\t\u0011\"\u0001\u0002`!I\u0011\u0011\r\u001d\u0002\u0002\u0013\u0005!Q\n\u0005\n\u0003[B\u0014\u0011!C!\u0003_B\u0011\"! 9\u0003\u0003%\tA!\u0015\t\u0013\u0005%\u0005(!A\u0005B\tU\u0003\"CAHq\u0005\u0005I\u0011IAI\u0011%\t\u0019\nOA\u0001\n\u0003\n)\nC\u0005\u0002\u0018b\n\t\u0011\"\u0011\u0003Z\u001d9!QL\u0001\t\u0002\t}ca\u0002B\u0015\u0003!\u0005!\u0011\r\u0005\u0007C&#\tAa\u0019\t\u000f\t\u0015\u0014\n\"\u0001\u0003h!I\u00111X%\u0002\u0002\u0013\u0005%\u0011\u000e\u0005\n\u0003\u0007L\u0015\u0011!CA\u0005[B\u0011\"a6J\u0003\u0003%I!!7\u0002\u0013MKWM^3Vi&d'BA)S\u0003\u0015\u0001(/[7f\u0015\t\u0019F+\u0001\u0003nCRD'\"A+\u0002\u000bM\u0004\u0018N]3\u0004\u0001A\u0011\u0001,A\u0007\u0002!\nI1+[3wKV#\u0018\u000e\\\n\u0003\u0003m\u0003\"\u0001X0\u000e\u0003uS\u0011AX\u0001\u0006g\u000e\fG.Y\u0005\u0003Av\u0013a!\u00118z%\u00164\u0017A\u0002\u001fj]&$h\bF\u0001X\u0005\u00191\u0015m\u0019;peN)1aW3tmB\u0019aM\\9\u000f\u0005\u001ddgB\u00015l\u001b\u0005I'B\u00016W\u0003\u0019a$o\\8u}%\ta,\u0003\u0002n;\u00069\u0001/Y2lC\u001e,\u0017BA8q\u0005\u001dy%\u000fZ3sK\u0012T!!\\/\u0011\u0005I\u001cQ\"A\u0001\u0011\u0005q#\u0018BA;^\u0005\u001d\u0001&o\u001c3vGR\u0004\"AZ<\n\u0005a\u0004(\u0001D*fe&\fG.\u001b>bE2,\u0017!\u00019\u0016\u0003m\u0004\"\u0001`?\u000e\u0003IK!A *\u0003\u0011M\u000bg-\u001a'p]\u001e\f!\u0001\u001d\u0011\u0002\t9,\u0007\u0010^\u0001\t]\u0016DHo\u0018\u0013fcR!\u0011qAA\u0007!\ra\u0016\u0011B\u0005\u0004\u0003\u0017i&\u0001B+oSRD\u0001\"a\u0004\b\u0003\u0003\u0005\ra_\u0001\u0004q\u0012\n\u0014!\u00028fqR\u0004C#B9\u0002\u0016\u0005]\u0001\"B=\n\u0001\u0004Y\bBBA\u0001\u0013\u0001\u000710A\u0004d_6\u0004\u0018M]3\u0015\t\u0005u\u00111\u0005\t\u00049\u0006}\u0011bAA\u0011;\n\u0019\u0011J\u001c;\t\r\u0005\u0015\"\u00021\u0001r\u0003\u0011!\b.\u0019;\u0002\t\r|\u0007/\u001f\u000b\u0006c\u0006-\u0012Q\u0006\u0005\bs.\u0001\n\u00111\u0001|\u0011!\t\ta\u0003I\u0001\u0002\u0004Y\u0018AD2paf$C-\u001a4bk2$H%M\u000b\u0003\u0003gQ3a_A\u001bW\t\t9\u0004\u0005\u0003\u0002:\u0005\rSBAA\u001e\u0015\u0011\ti$a\u0010\u0002\u0013Ut7\r[3dW\u0016$'bAA!;\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005\u0015\u00131\b\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u00055\u0003\u0003BA(\u00033j!!!\u0015\u000b\t\u0005M\u0013QK\u0001\u0005Y\u0006twM\u0003\u0002\u0002X\u0005!!.\u0019<b\u0013\u0011\tY&!\u0015\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\t\ti\"\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005\u0015\u00141\u000e\t\u00049\u0006\u001d\u0014bAA5;\n\u0019\u0011I\\=\t\u0013\u0005=\u0001#!AA\u0002\u0005u\u0011a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005E\u0004CBA:\u0003s\n)'\u0004\u0002\u0002v)\u0019\u0011qO/\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002|\u0005U$\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!!!\u0002\bB\u0019A,a!\n\u0007\u0005\u0015ULA\u0004C_>dW-\u00198\t\u0013\u0005=!#!AA\u0002\u0005\u0015\u0014A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!!\u0014\u0002\u000e\"I\u0011qB\n\u0002\u0002\u0003\u0007\u0011QD\u0001\tQ\u0006\u001c\bnQ8eKR\u0011\u0011QD\u0001\ti>\u001cFO]5oOR\u0011\u0011QJ\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005\u0005\u00151\u0014\u0005\n\u0003\u001f1\u0012\u0011!a\u0001\u0003K\naAR1di>\u0014\bC\u0001:\u0019'\u0015A\u00121UAX!\u001d\t)+a+|wFl!!a*\u000b\u0007\u0005%V,A\u0004sk:$\u0018.\\3\n\t\u00055\u0016q\u0015\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u0014\u0004\u0003BAY\u0003ok!!a-\u000b\t\u0005U\u0016QK\u0001\u0003S>L1\u0001_AZ)\t\ty*A\u0003baBd\u0017\u0010F\u0003r\u0003\u007f\u000b\t\rC\u0003z7\u0001\u00071\u0010\u0003\u0004\u0002\u0002m\u0001\ra_\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t9-a5\u0011\u000bq\u000bI-!4\n\u0007\u0005-WL\u0001\u0004PaRLwN\u001c\t\u00069\u0006=7p_\u0005\u0004\u0003#l&A\u0002+va2,'\u0007\u0003\u0005\u0002Vr\t\t\u00111\u0001r\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u00037\u0004B!a\u0014\u0002^&!\u0011q\\A)\u0005\u0019y%M[3di\nQa)Y:u\r\u0006\u001cGo\u001c:\u0014\tyY6O^\u0001\u0002[\u0006)Qn\u0018\u0013fcR!\u0011qAAv\u0011!\tyAIA\u0001\u0002\u0004Y\u0018AA7!)\u0019\t\t0a=\u0002vB\u0011!O\b\u0005\u0007s\u0012\u0002\r!!\b\t\r\u0005\u0015H\u00051\u0001|)\u0019\t\t0!?\u0002|\"A\u00110\nI\u0001\u0002\u0004\ti\u0002\u0003\u0005\u0002f\u0016\u0002\n\u00111\u0001|+\t\tyP\u000b\u0003\u0002\u001e\u0005UB\u0003BA3\u0005\u0007A\u0011\"a\u0004+\u0003\u0003\u0005\r!!\b\u0015\t\u0005\u0005%q\u0001\u0005\n\u0003\u001fa\u0013\u0011!a\u0001\u0003K\"B!!\u0014\u0003\f!I\u0011qB\u0017\u0002\u0002\u0003\u0007\u0011Q\u0004\u000b\u0005\u0003\u0003\u0013y\u0001C\u0005\u0002\u0010A\n\t\u00111\u0001\u0002f\u0005Qa)Y:u\r\u0006\u001cGo\u001c:\u0011\u0005I\u00144#\u0002\u001a\u0003\u0018\u0005=\u0006#CAS\u0003W\u000bib_Ay)\t\u0011\u0019\u0002\u0006\u0004\u0002r\nu!q\u0004\u0005\u0007sV\u0002\r!!\b\t\r\u0005\u0015X\u00071\u0001|)\u0011\u0011\u0019Ca\n\u0011\u000bq\u000bIM!\n\u0011\rq\u000by-!\b|\u0011%\t)NNA\u0001\u0002\u0004\t\tPA\u0006GCN$h)Y2u_J\u001c8\u0003\u0002\u001d\\gZ\f1!\u0019:s+\t\u0011\t\u0004E\u0003]\u0005g\t\t0C\u0002\u00036u\u0013Q!\u0011:sCf\fq!\u0019:s?\u0012*\u0017\u000f\u0006\u0003\u0002\b\tm\u0002\"CA\bu\u0005\u0005\t\u0019\u0001B\u0019\u0003\u0011\t'O\u001d\u0011\u0015\t\t\u0005#1\t\t\u0003ebBqA!\f=\u0001\u0004\u0011\t\u0004\u0006\u0003\u0003B\t\u001d\u0003\"\u0003B\u0017{A\u0005\t\u0019\u0001B\u0019+\t\u0011YE\u000b\u0003\u00032\u0005UB\u0003BA3\u0005\u001fB\u0011\"a\u0004B\u0003\u0003\u0005\r!!\b\u0015\t\u0005\u0005%1\u000b\u0005\n\u0003\u001f\u0019\u0015\u0011!a\u0001\u0003K\"B!!\u0014\u0003X!I\u0011q\u0002#\u0002\u0002\u0003\u0007\u0011Q\u0004\u000b\u0005\u0003\u0003\u0013Y\u0006C\u0005\u0002\u0010\u001d\u000b\t\u00111\u0001\u0002f\u0005Ya)Y:u\r\u0006\u001cGo\u001c:t!\t\u0011\u0018j\u0005\u0003J7\u0006=FC\u0001B0\u0003\u0015)W\u000e\u001d;z+\t\u0011\t\u0005\u0006\u0003\u0003B\t-\u0004b\u0002B\u0017\u0019\u0002\u0007!\u0011\u0007\u000b\u0005\u0005_\u0012\t\bE\u0003]\u0003\u0013\u0014\t\u0004C\u0005\u0002V6\u000b\t\u00111\u0001\u0003B\u0001"
)
public final class SieveUtil {
   public static class Factor implements Ordered, Product, Serializable {
      private final SafeLong p;
      private SafeLong next;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public boolean $less(final Object that) {
         return Ordered.$less$(this, that);
      }

      public boolean $greater(final Object that) {
         return Ordered.$greater$(this, that);
      }

      public boolean $less$eq(final Object that) {
         return Ordered.$less$eq$(this, that);
      }

      public boolean $greater$eq(final Object that) {
         return Ordered.$greater$eq$(this, that);
      }

      public int compareTo(final Object that) {
         return Ordered.compareTo$(this, that);
      }

      public SafeLong p() {
         return this.p;
      }

      public SafeLong next() {
         return this.next;
      }

      public void next_$eq(final SafeLong x$1) {
         this.next = x$1;
      }

      public int compare(final Factor that) {
         return -this.next().compare(that.next());
      }

      public Factor copy(final SafeLong p, final SafeLong next) {
         return new Factor(p, next);
      }

      public SafeLong copy$default$1() {
         return this.p();
      }

      public SafeLong copy$default$2() {
         return this.next();
      }

      public String productPrefix() {
         return "Factor";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.p();
               break;
            case 1:
               var10000 = this.next();
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
         return x$1 instanceof Factor;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "p";
               break;
            case 1:
               var10000 = "next";
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
         boolean var10000;
         if (this != x$1) {
            label51: {
               boolean var2;
               if (x$1 instanceof Factor) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  Factor var4 = (Factor)x$1;
                  if (BoxesRunTime.equalsNumNum(this.p(), var4.p()) && BoxesRunTime.equalsNumNum(this.next(), var4.next()) && var4.canEqual(this)) {
                     break label51;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public Factor(final SafeLong p, final SafeLong next) {
         this.p = p;
         this.next = next;
         super();
         Ordered.$init$(this);
         Product.$init$(this);
      }
   }

   public static class Factor$ extends AbstractFunction2 implements Serializable {
      public static final Factor$ MODULE$ = new Factor$();

      public final String toString() {
         return "Factor";
      }

      public Factor apply(final SafeLong p, final SafeLong next) {
         return new Factor(p, next);
      }

      public Option unapply(final Factor x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.p(), x$0.next())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Factor$.class);
      }
   }

   public static class FastFactor implements Product, Serializable {
      private final int p;
      private SafeLong m;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int p() {
         return this.p;
      }

      public SafeLong m() {
         return this.m;
      }

      public void m_$eq(final SafeLong x$1) {
         this.m = x$1;
      }

      public FastFactor copy(final int p, final SafeLong m) {
         return new FastFactor(p, m);
      }

      public int copy$default$1() {
         return this.p();
      }

      public SafeLong copy$default$2() {
         return this.m();
      }

      public String productPrefix() {
         return "FastFactor";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = BoxesRunTime.boxToInteger(this.p());
               break;
            case 1:
               var10000 = this.m();
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
         return x$1 instanceof FastFactor;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "p";
               break;
            case 1:
               var10000 = "m";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, this.p());
         var1 = Statics.mix(var1, Statics.anyHash(this.m()));
         return Statics.finalizeHash(var1, 2);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label51: {
               boolean var2;
               if (x$1 instanceof FastFactor) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  FastFactor var4 = (FastFactor)x$1;
                  if (this.p() == var4.p() && BoxesRunTime.equalsNumNum(this.m(), var4.m()) && var4.canEqual(this)) {
                     break label51;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public FastFactor(final int p, final SafeLong m) {
         this.p = p;
         this.m = m;
         super();
         Product.$init$(this);
      }
   }

   public static class FastFactor$ extends AbstractFunction2 implements Serializable {
      public static final FastFactor$ MODULE$ = new FastFactor$();

      public final String toString() {
         return "FastFactor";
      }

      public FastFactor apply(final int p, final SafeLong m) {
         return new FastFactor(p, m);
      }

      public Option unapply(final FastFactor x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToInteger(x$0.p()), x$0.m())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(FastFactor$.class);
      }
   }

   public static class FastFactors implements Product, Serializable {
      private FastFactor[] arr;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public FastFactor[] arr() {
         return this.arr;
      }

      public void arr_$eq(final FastFactor[] x$1) {
         this.arr = x$1;
      }

      public FastFactors copy(final FastFactor[] arr) {
         return new FastFactors(arr);
      }

      public FastFactor[] copy$default$1() {
         return this.arr();
      }

      public String productPrefix() {
         return "FastFactors";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.arr();
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
         return x$1 instanceof FastFactors;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "arr";
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
         boolean var10000;
         if (this != x$1) {
            label49: {
               boolean var2;
               if (x$1 instanceof FastFactors) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  FastFactors var4 = (FastFactors)x$1;
                  if (this.arr() == var4.arr() && var4.canEqual(this)) {
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

      public FastFactors(final FastFactor[] arr) {
         this.arr = arr;
         super();
         Product.$init$(this);
      }
   }

   public static class FastFactors$ implements Serializable {
      public static final FastFactors$ MODULE$ = new FastFactors$();

      public FastFactors empty() {
         return new FastFactors(new FastFactor[0]);
      }

      public FastFactors apply(final FastFactor[] arr) {
         return new FastFactors(arr);
      }

      public Option unapply(final FastFactors x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.arr()));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(FastFactors$.class);
      }
   }
}
