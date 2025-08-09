package scala.collection;

import java.io.Serializable;
import scala.None$;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.collection.generic.IsSeq;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.ScalaRunTime$;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0015u!B A\u0011\u0003)e!B$A\u0011\u0003A\u0005\"B'\u0002\t\u0003qe!B(\u0002\u0003C\u0001\u0006\"B'\u0004\t\u0003\t\u0006\"\u0002+\u0004\r\u0003)f\u0001\u0002.\u0002\u0001nC\u0001b\u001b\u0004\u0003\u0016\u0004%\t!\u0016\u0005\tY\u001a\u0011\t\u0012)A\u0005-\")QJ\u0002C\u0001[\")AK\u0002C!+\"9\u0001OBA\u0001\n\u0003\t\bbB:\u0007#\u0003%\t\u0001\u001e\u0005\t\u007f\u001a\t\t\u0011\"\u0011\u0002\u0002!A\u00111\u0003\u0004\u0002\u0002\u0013\u0005Q\u000bC\u0005\u0002\u0016\u0019\t\t\u0011\"\u0001\u0002\u0018!I\u00111\u0005\u0004\u0002\u0002\u0013\u0005\u0013Q\u0005\u0005\n\u0003[1\u0011\u0011!C\u0001\u0003_A\u0011\"!\u000f\u0007\u0003\u0003%\t%a\u000f\t\u0013\u0005}b!!A\u0005B\u0005\u0005\u0003\"CA\"\r\u0005\u0005I\u0011IA#\u0011%\t9EBA\u0001\n\u0003\nIeB\u0005\u0002n\u0005\t\t\u0011#\u0001\u0002p\u0019A!,AA\u0001\u0012\u0003\t\t\b\u0003\u0004N/\u0011\u0005\u0011\u0011\u0012\u0005\n\u0003\u0007:\u0012\u0011!C#\u0003\u000bB\u0011\"a#\u0018\u0003\u0003%\t)!$\t\u0013\u0005Eu#!A\u0005\u0002\u0006M\u0005\"CAP/\u0005\u0005I\u0011BAQ\r\u0019\ti%\u0001!\u0002P!AA+\bBK\u0002\u0013\u0005Q\u000bC\u0005\u0002Ru\u0011\t\u0012)A\u0005-\"1Q*\bC\u0001\u0003'B\u0001\u0002]\u000f\u0002\u0002\u0013\u0005\u0011\u0011\f\u0005\bgv\t\n\u0011\"\u0001u\u0011!yX$!A\u0005B\u0005\u0005\u0001\u0002CA\n;\u0005\u0005I\u0011A+\t\u0013\u0005UQ$!A\u0005\u0002\u0005u\u0003\"CA\u0012;\u0005\u0005I\u0011IA\u0013\u0011%\ti#HA\u0001\n\u0003\t\t\u0007C\u0005\u0002:u\t\t\u0011\"\u0011\u0002f!I\u0011qH\u000f\u0002\u0002\u0013\u0005\u0013\u0011\t\u0005\n\u0003\u0007j\u0012\u0011!C!\u0003\u000bB\u0011\"a\u0012\u001e\u0003\u0003%\t%!\u001b\b\u0013\u0005%\u0016!!A\t\u0002\u0005-f!CA'\u0003\u0005\u0005\t\u0012AAW\u0011\u0019iU\u0006\"\u0001\u00022\"I\u00111I\u0017\u0002\u0002\u0013\u0015\u0013Q\t\u0005\n\u0003\u0017k\u0013\u0011!CA\u0003gC\u0011\"!%.\u0003\u0003%\t)a.\t\u0013\u0005}U&!A\u0005\n\u0005\u0005fABA^\u0003\t\ti\f\u0003\b\u0002HN\"\t\u0011!B\u0003\u0006\u0004%I!!3\t\u0017\u0005U8G!B\u0001B\u0003%\u00111\u001a\u0005\u0007\u001bN\"\t!a>\t\u0013\u0005}2'!A\u0005B\u0005\u0005\u0003\"CA$g\u0005\u0005I\u0011\tB\u0007\u000f%\u0011)#AA\u0001\u0012\u0003\u00119CB\u0005\u0002<\u0006\t\t\u0011#\u0001\u0003*!1QJ\u000fC\u0001\u0005WA\u0011B!\f;\u0003\u0003%)Aa\f\t\u0013\t\u0005#(!A\u0005\u0006\t\r\u0003b\u0002B,\u0003\u0011\r!\u0011L\u0001\n'\u0016\f'o\u00195j]\u001eT!!\u0011\"\u0002\u0015\r|G\u000e\\3di&|gNC\u0001D\u0003\u0015\u00198-\u00197b\u0007\u0001\u0001\"AR\u0001\u000e\u0003\u0001\u0013\u0011bU3be\u000eD\u0017N\\4\u0014\u0005\u0005I\u0005C\u0001&L\u001b\u0005\u0011\u0015B\u0001'C\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\"\u0012!\u0012\u0002\r'\u0016\f'o\u00195SKN,H\u000e^\n\u0003\u0007%#\u0012A\u0015\t\u0003'\u000ei\u0011!A\u0001\u000fS:\u001cXM\u001d;j_:\u0004v.\u001b8u+\u00051\u0006C\u0001&X\u0013\tA&IA\u0002J]RL3a\u0001\u0004\u001e\u0005\u00151u.\u001e8e'\u00111!\u000bX0\u0011\u0005)k\u0016B\u00010C\u0005\u001d\u0001&o\u001c3vGR\u0004\"\u0001\u00195\u000f\u0005\u00054gB\u00012f\u001b\u0005\u0019'B\u00013E\u0003\u0019a$o\\8u}%\t1)\u0003\u0002h\u0005\u00069\u0001/Y2lC\u001e,\u0017BA5k\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t9')\u0001\u0006g_VtG-\u00138eKb\f1BZ8v]\u0012Le\u000eZ3yAQ\u0011an\u001c\t\u0003'\u001aAQa[\u0005A\u0002Y\u000bAaY8qsR\u0011aN\u001d\u0005\bW.\u0001\n\u00111\u0001W\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012!\u001e\u0016\u0003-Z\\\u0013a\u001e\t\u0003qvl\u0011!\u001f\u0006\u0003un\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005q\u0014\u0015AC1o]>$\u0018\r^5p]&\u0011a0\u001f\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u0002\u0004A!\u0011QAA\b\u001b\t\t9A\u0003\u0003\u0002\n\u0005-\u0011\u0001\u00027b]\u001eT!!!\u0004\u0002\t)\fg/Y\u0005\u0005\u0003#\t9A\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\tI\"a\b\u0011\u0007)\u000bY\"C\u0002\u0002\u001e\t\u00131!\u00118z\u0011!\t\tcDA\u0001\u0002\u00041\u0016a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002(A)a)!\u000b\u0002\u001a%\u0019\u00111\u0006!\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003c\t9\u0004E\u0002K\u0003gI1!!\u000eC\u0005\u001d\u0011un\u001c7fC:D\u0011\"!\t\u0012\u0003\u0003\u0005\r!!\u0007\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003\u0007\ti\u0004\u0003\u0005\u0002\"I\t\t\u00111\u0001W\u0003!A\u0017m\u001d5D_\u0012,G#\u0001,\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!a\u0001\u0002\r\u0015\fX/\u00197t)\u0011\t\t$a\u0013\t\u0013\u0005\u0005R#!AA\u0002\u0005e!AD%og\u0016\u0014H/[8o!>Lg\u000e^\n\u0005;Icv,A\bj]N,'\u000f^5p]B{\u0017N\u001c;!)\u0011\t)&a\u0016\u0011\u0005Mk\u0002\"\u0002+!\u0001\u00041F\u0003BA+\u00037Bq\u0001V\u0011\u0011\u0002\u0003\u0007a\u000b\u0006\u0003\u0002\u001a\u0005}\u0003\u0002CA\u0011K\u0005\u0005\t\u0019\u0001,\u0015\t\u0005E\u00121\r\u0005\n\u0003C9\u0013\u0011!a\u0001\u00033!B!a\u0001\u0002h!A\u0011\u0011\u0005\u0015\u0002\u0002\u0003\u0007a\u000b\u0006\u0003\u00022\u0005-\u0004\"CA\u0011W\u0005\u0005\t\u0019AA\r\u0003\u00151u.\u001e8e!\t\u0019vcE\u0003\u0018\u0003g\ny\b\u0005\u0004\u0002v\u0005mdK\\\u0007\u0003\u0003oR1!!\u001fC\u0003\u001d\u0011XO\u001c;j[\u0016LA!! \u0002x\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u0019\u0011\t\u0005\u0005\u0015qQ\u0007\u0003\u0003\u0007SA!!\"\u0002\f\u0005\u0011\u0011n\\\u0005\u0004S\u0006\rECAA8\u0003\u0015\t\u0007\u000f\u001d7z)\rq\u0017q\u0012\u0005\u0006Wj\u0001\rAV\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t)*a'\u0011\t)\u000b9JV\u0005\u0004\u00033\u0013%AB(qi&|g\u000e\u0003\u0005\u0002\u001en\t\t\u00111\u0001o\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003G\u0003B!!\u0002\u0002&&!\u0011qUA\u0004\u0005\u0019y%M[3di\u0006q\u0011J\\:feRLwN\u001c)pS:$\bCA*.'\u0015i\u0013qVA@!\u001d\t)(a\u001fW\u0003+\"\"!a+\u0015\t\u0005U\u0013Q\u0017\u0005\u0006)B\u0002\rA\u0016\u000b\u0005\u0003+\u000bI\fC\u0005\u0002\u001eF\n\t\u00111\u0001\u0002V\tQ1+Z1sG\"LU\u000e\u001d7\u0016\r\u0005}\u0016Q`Al'\r\u0019\u0014\u0011\u0019\t\u0004\u0015\u0006\r\u0017bAAc\u0005\n1\u0011I\\=WC2\f1f]2bY\u0006$3m\u001c7mK\u000e$\u0018n\u001c8%'\u0016\f'o\u00195j]\u001e$3+Z1sG\"LU\u000e\u001d7%I\r|G\u000e\\\u000b\u0003\u0003\u0017\u0004D!!4\u0002rBIa)a4\u0002T\u0006\r\u0018q^\u0005\u0004\u0003#\u0004%AB*fc>\u00038\u000f\u0005\u0003\u0002V\u0006]G\u0002\u0001\u0003\b\u00033\u001c$\u0019AAn\u0005\u0005\t\u0015\u0003BAo\u00033\u00012ASAp\u0013\r\t\tO\u0011\u0002\b\u001d>$\b.\u001b8h!\u0011\t)/!;\u000f\u0007\u0019\u000b9/\u0003\u0002h\u0001&!\u00111^Aw\u0005%\te._\"p]N$(O\u0003\u0002h\u0001B!\u0011Q[Ay\t-\t\u00190NA\u0001\u0002\u0003\u0015\t!a7\u0003\u0007}#\u0013'\u0001\u0017tG\u0006d\u0017\rJ2pY2,7\r^5p]\u0012\u001aV-\u0019:dQ&tw\rJ*fCJ\u001c\u0007.S7qY\u0012\"3m\u001c7mAQ!\u0011\u0011 B\u0001!\u0019\u00196'a?\u0002TB!\u0011Q[A\u007f\t\u001d\typ\rb\u0001\u00037\u0014AAU3qe\"9!1\u0001\u001cA\u0002\t\u0015\u0011\u0001B2pY2\u0004DAa\u0002\u0003\fAIa)a4\u0002T\u0006\r(\u0011\u0002\t\u0005\u0003+\u0014Y\u0001\u0002\u0007\u0002t\n\u0005\u0011\u0011!A\u0001\u0006\u0003\tY\u000e\u0006\u0003\u00022\t=\u0001\"CA\u0011q\u0005\u0005\t\u0019AA\rQ-\u0019$1\u0003B\r\u00057\u0011yB!\t\u0011\u0007)\u0013)\"C\u0002\u0003\u0018\t\u0013!\u0002Z3qe\u0016\u001c\u0017\r^3e\u0003\u001diWm]:bO\u0016\f#A!\b\u0002IN+\u0017M]2iA5,G\u000f[8eg\u0002\n'/\u001a\u0011eK\u001aLg.\u001a3!I&\u0014Xm\u0019;ms\u0002zg\u000eI*fc>\u00038\u000fI1oI\u0002\"w\u000e\t8pi\u0002\u0012X-];je\u0016\u00043oY1mC:\u001aw\u000e\u001c7fGRLwN\u001c\u0018TK\u0006\u00148\r[5oO\u0002\ng.\u001f\u0011n_J,\u0017!B:j]\u000e,\u0017E\u0001B\u0012\u0003\u0019\u0011d&M\u001a/a\u0005Q1+Z1sG\"LU\u000e\u001d7\u0011\u0005MS4C\u0001\u001eJ)\t\u00119#\u0001\niCND7i\u001c3fI\u0015DH/\u001a8tS>tWC\u0002B\u0019\u0005w\u0011y\u0004\u0006\u0003\u0002B\tM\u0002b\u0002B\u001by\u0001\u0007!qG\u0001\u0006IQD\u0017n\u001d\t\u0007'N\u0012ID!\u0010\u0011\t\u0005U'1\b\u0003\b\u0003\u007fd$\u0019AAn!\u0011\t)Na\u0010\u0005\u000f\u0005eGH1\u0001\u0002\\\u0006\u0001R-];bYN$S\r\u001f;f]NLwN\\\u000b\u0007\u0005\u000b\u0012\tF!\u0016\u0015\t\t\u001d#1\n\u000b\u0005\u0003c\u0011I\u0005C\u0005\u0002\"u\n\t\u00111\u0001\u0002\u001a!9!QG\u001fA\u0002\t5\u0003CB*4\u0005\u001f\u0012\u0019\u0006\u0005\u0003\u0002V\nECaBA\u0000{\t\u0007\u00111\u001c\t\u0005\u0003+\u0014)\u0006B\u0004\u0002Zv\u0012\r!a7\u0002\rM,\u0017M]2i+\u0019\u0011YFa\u0019\u0003\u0002R!!Q\fB@)\u0011\u0011yF!\u001b\u0011\rM\u001b$\u0011\rB3!\u0011\t)Na\u0019\u0005\u000f\u0005}hH1\u0001\u0002\\B!!q\rB=\u001d\u0011\t)N!\u001b\t\u000f\t-d\bq\u0001\u0003n\u0005\u0011aM\u001d\t\u0007\u0005_\u0012)H!\u0019\u000e\u0005\tE$b\u0001B:\u0001\u00069q-\u001a8fe&\u001c\u0017\u0002\u0002B<\u0005c\u0012Q!S:TKFLA!!7\u0003|%!!Q\u0010B9\u00059I5/\u0013;fe\u0006\u0014G.Z(oG\u0016DqAa\u0001?\u0001\u0004\u0011\t\u0007B\u0004\u0002Zz\u0012\r!a7)\u0017y\u0012\u0019B!\u0007\u0003\u001c\t}!\u0011\u0005"
)
public final class Searching {
   /** @deprecated */
   public static SeqOps search(final Object coll, final IsSeq fr) {
      return Searching$.MODULE$.search(coll, fr);
   }

   public abstract static class SearchResult {
      public abstract int insertionPoint();
   }

   public static class Found extends SearchResult implements Product, Serializable {
      private final int foundIndex;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int foundIndex() {
         return this.foundIndex;
      }

      public int insertionPoint() {
         return this.foundIndex();
      }

      public Found copy(final int foundIndex) {
         return new Found(foundIndex);
      }

      public int copy$default$1() {
         return this.foundIndex();
      }

      public String productPrefix() {
         return "Found";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.foundIndex();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return new AbstractIterator(this) {
            private int c;
            private final int cmax;
            private final Product x$2;

            public boolean hasNext() {
               return this.c < this.cmax;
            }

            public Object next() {
               Object result = this.x$2.productElement(this.c);
               ++this.c;
               return result;
            }

            public {
               this.x$2 = x$2;
               this.c = 0;
               this.cmax = x$2.productArity();
            }
         };
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Found;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "foundIndex";
            default:
               return (String)Statics.ioobe(x$1);
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, this.foundIndex());
         int finalizeHash_length = 1;
         return Statics.avalanche(var1 ^ finalizeHash_length);
      }

      public String toString() {
         return ScalaRunTime$.MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         if (this != x$1) {
            if (x$1 instanceof Found) {
               Found var2 = (Found)x$1;
               if (this.foundIndex() == var2.foundIndex() && var2.canEqual(this)) {
                  return true;
               }
            }

            return false;
         } else {
            return true;
         }
      }

      public Found(final int foundIndex) {
         this.foundIndex = foundIndex;
      }
   }

   public static class Found$ extends AbstractFunction1 implements Serializable {
      public static final Found$ MODULE$ = new Found$();

      public final String toString() {
         return "Found";
      }

      public Found apply(final int foundIndex) {
         return new Found(foundIndex);
      }

      public Option unapply(final Found x$0) {
         return (Option)(x$0 == null ? None$.MODULE$ : new Some(x$0.foundIndex()));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Found$.class);
      }
   }

   public static class InsertionPoint extends SearchResult implements Product, Serializable {
      private final int insertionPoint;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int insertionPoint() {
         return this.insertionPoint;
      }

      public InsertionPoint copy(final int insertionPoint) {
         return new InsertionPoint(insertionPoint);
      }

      public int copy$default$1() {
         return this.insertionPoint();
      }

      public String productPrefix() {
         return "InsertionPoint";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.insertionPoint();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return new AbstractIterator(this) {
            private int c;
            private final int cmax;
            private final Product x$2;

            public boolean hasNext() {
               return this.c < this.cmax;
            }

            public Object next() {
               Object result = this.x$2.productElement(this.c);
               ++this.c;
               return result;
            }

            public {
               this.x$2 = x$2;
               this.c = 0;
               this.cmax = x$2.productArity();
            }
         };
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof InsertionPoint;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "insertionPoint";
            default:
               return (String)Statics.ioobe(x$1);
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, this.insertionPoint());
         int finalizeHash_length = 1;
         return Statics.avalanche(var1 ^ finalizeHash_length);
      }

      public String toString() {
         return ScalaRunTime$.MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         if (this != x$1) {
            if (x$1 instanceof InsertionPoint) {
               InsertionPoint var2 = (InsertionPoint)x$1;
               if (this.insertionPoint() == var2.insertionPoint() && var2.canEqual(this)) {
                  return true;
               }
            }

            return false;
         } else {
            return true;
         }
      }

      public InsertionPoint(final int insertionPoint) {
         this.insertionPoint = insertionPoint;
      }
   }

   public static class InsertionPoint$ extends AbstractFunction1 implements Serializable {
      public static final InsertionPoint$ MODULE$ = new InsertionPoint$();

      public final String toString() {
         return "InsertionPoint";
      }

      public InsertionPoint apply(final int insertionPoint) {
         return new InsertionPoint(insertionPoint);
      }

      public Option unapply(final InsertionPoint x$0) {
         return (Option)(x$0 == null ? None$.MODULE$ : new Some(x$0.insertionPoint()));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(InsertionPoint$.class);
      }
   }

   /** @deprecated */
   public static final class SearchImpl {
      private final SeqOps scala$collection$Searching$SearchImpl$$coll;

      public SeqOps scala$collection$Searching$SearchImpl$$coll() {
         return this.scala$collection$Searching$SearchImpl$$coll;
      }

      public int hashCode() {
         SearchImpl$ var10000 = Searching.SearchImpl$.MODULE$;
         return this.scala$collection$Searching$SearchImpl$$coll().hashCode();
      }

      public boolean equals(final Object x$1) {
         return Searching.SearchImpl$.MODULE$.equals$extension(this.scala$collection$Searching$SearchImpl$$coll(), x$1);
      }

      public SearchImpl(final SeqOps coll) {
         this.scala$collection$Searching$SearchImpl$$coll = coll;
      }
   }

   public static class SearchImpl$ {
      public static final SearchImpl$ MODULE$ = new SearchImpl$();

      public final int hashCode$extension(final SeqOps $this) {
         return $this.hashCode();
      }

      public final boolean equals$extension(final SeqOps $this, final Object x$1) {
         if (x$1 instanceof SearchImpl) {
            SeqOps var3 = x$1 == null ? null : ((SearchImpl)x$1).scala$collection$Searching$SearchImpl$$coll();
            if ($this == null) {
               if (var3 == null) {
                  return true;
               }
            } else if ($this.equals(var3)) {
               return true;
            }
         }

         return false;
      }
   }
}
