package org.apache.spark.sql;

import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=f\u0001B\u000e\u001d\u0001\u0016B\u0001\u0002\u0010\u0001\u0003\u0016\u0004%\t!\u0010\u0005\t\u001b\u0002\u0011\t\u0012)A\u0005}!Aa\n\u0001BK\u0002\u0013\u0005q\n\u0003\u0005W\u0001\tE\t\u0015!\u0003Q\u0011\u00199\u0006\u0001\"\u0001\u001d1\")A\f\u0001C\u0001;\")a\f\u0001C\u0001?\"9Q\u000eAA\u0001\n\u0003q\u0007b\u0002<\u0001#\u0003%\ta\u001e\u0005\n\u0003\u0013\u0001\u0011\u0013!C\u0001\u0003\u0017A\u0011\"a\u0005\u0001\u0003\u0003%\t%!\u0006\t\u0013\u0005\u0015\u0002!!A\u0005\u0002\u0005\u001d\u0002\"CA\u0018\u0001\u0005\u0005I\u0011AA\u0019\u0011%\t9\u0004AA\u0001\n\u0003\nI\u0004C\u0005\u0002H\u0001\t\t\u0011\"\u0001\u0002J!I\u00111\u000b\u0001\u0002\u0002\u0013\u0005\u0013Q\u000b\u0005\n\u00033\u0002\u0011\u0011!C!\u00037B\u0011\"!\u0018\u0001\u0003\u0003%\t%a\u0018\t\u0013\u0005\u0005\u0004!!A\u0005B\u0005\rt!CA49\u0005\u0005\t\u0012AA5\r!YB$!A\t\u0002\u0005-\u0004BB,\u0016\t\u0003\t9\bC\u0005\u0002^U\t\t\u0011\"\u0012\u0002`!I\u0011\u0011P\u000b\u0002\u0002\u0013\u0005\u00151\u0010\u0005\n\u0003\u0017+\u0012\u0011!CA\u0003\u001bC\u0011\"!*\u0016\u0003\u0003%I!a*\u0003\u001d]CWM\u001c(pi6\u000bGo\u00195fI*\u0011QDH\u0001\u0004gFd'BA\u0010!\u0003\u0015\u0019\b/\u0019:l\u0015\t\t#%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002G\u0005\u0019qN]4\u0004\u0001U\u0011a\u0005R\n\u0005\u0001\u001dj\u0003\u0007\u0005\u0002)W5\t\u0011FC\u0001+\u0003\u0015\u00198-\u00197b\u0013\ta\u0013F\u0001\u0004B]f\u0014VM\u001a\t\u0003Q9J!aL\u0015\u0003\u000fA\u0013x\u000eZ;diB\u0011\u0011'\u000f\b\u0003e]r!a\r\u001c\u000e\u0003QR!!\u000e\u0013\u0002\rq\u0012xn\u001c;?\u0013\u0005Q\u0013B\u0001\u001d*\u0003\u001d\u0001\u0018mY6bO\u0016L!AO\u001e\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005aJ\u0013aD7fe\u001e,\u0017J\u001c;p/JLG/\u001a:\u0016\u0003y\u00022a\u0010!C\u001b\u0005a\u0012BA!\u001d\u0005=iUM]4f\u0013:$xn\u0016:ji\u0016\u0014\bCA\"E\u0019\u0001!Q!\u0012\u0001C\u0002\u0019\u0013\u0011\u0001V\t\u0003\u000f*\u0003\"\u0001\u000b%\n\u0005%K#a\u0002(pi\"Lgn\u001a\t\u0003Q-K!\u0001T\u0015\u0003\u0007\u0005s\u00170\u0001\tnKJ<W-\u00138u_^\u0013\u0018\u000e^3sA\u0005I1m\u001c8eSRLwN\\\u000b\u0002!B\u0019\u0001&U*\n\u0005IK#AB(qi&|g\u000e\u0005\u0002@)&\u0011Q\u000b\b\u0002\u0007\u0007>dW/\u001c8\u0002\u0015\r|g\u000eZ5uS>t\u0007%\u0001\u0004=S:LGO\u0010\u000b\u00043j[\u0006cA \u0001\u0005\")A(\u0002a\u0001}!)a*\u0002a\u0001!\u0006I\u0011N\\:feR\fE\u000e\u001c\u000b\u0002}\u00051\u0011N\\:feR$\"A\u00101\t\u000b\u0005<\u0001\u0019\u00012\u0002\u00075\f\u0007\u000f\u0005\u0003dO*\u001cfB\u00013f!\t\u0019\u0014&\u0003\u0002gS\u00051\u0001K]3eK\u001aL!\u0001[5\u0003\u00075\u000b\u0007O\u0003\u0002gSA\u00111m[\u0005\u0003Y&\u0014aa\u0015;sS:<\u0017\u0001B2paf,\"a\u001c:\u0015\u0007A\u001cX\u000fE\u0002@\u0001E\u0004\"a\u0011:\u0005\u000b\u0015C!\u0019\u0001$\t\u000fqB\u0001\u0013!a\u0001iB\u0019q\bQ9\t\u000f9C\u0001\u0013!a\u0001!\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nTc\u0001=\u0002\bU\t\u0011P\u000b\u0002?u.\n1\u0010E\u0002}\u0003\u0007i\u0011! \u0006\u0003}~\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005\u0005\u0011&\u0001\u0006b]:|G/\u0019;j_:L1!!\u0002~\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a\u0003\u0006\u000b&\u0011\rAR\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\u0011\ti!!\u0005\u0016\u0005\u0005=!F\u0001){\t\u0015)%B1\u0001G\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u0011q\u0003\t\u0005\u00033\t\u0019#\u0004\u0002\u0002\u001c)!\u0011QDA\u0010\u0003\u0011a\u0017M\\4\u000b\u0005\u0005\u0005\u0012\u0001\u00026bm\u0006L1\u0001\\A\u000e\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\t\tI\u0003E\u0002)\u0003WI1!!\f*\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\rQ\u00151\u0007\u0005\n\u0003ki\u0011\u0011!a\u0001\u0003S\t1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA\u001e!\u0015\ti$a\u0011K\u001b\t\tyDC\u0002\u0002B%\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\t)%a\u0010\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003\u0017\n\t\u0006E\u0002)\u0003\u001bJ1!a\u0014*\u0005\u001d\u0011un\u001c7fC:D\u0001\"!\u000e\u0010\u0003\u0003\u0005\rAS\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0002\u0018\u0005]\u0003\"CA\u001b!\u0005\u0005\t\u0019AA\u0015\u0003!A\u0017m\u001d5D_\u0012,GCAA\u0015\u0003!!xn\u0015;sS:<GCAA\f\u0003\u0019)\u0017/^1mgR!\u00111JA3\u0011!\t)dEA\u0001\u0002\u0004Q\u0015AD,iK:tu\u000e^'bi\u000eDW\r\u001a\t\u0003\u007fU\u0019B!F\u0014\u0002nA!\u0011qNA;\u001b\t\t\tH\u0003\u0003\u0002t\u0005}\u0011AA5p\u0013\rQ\u0014\u0011\u000f\u000b\u0003\u0003S\nQ!\u00199qYf,B!! \u0002\u0004R1\u0011qPAC\u0003\u0013\u0003Ba\u0010\u0001\u0002\u0002B\u00191)a!\u0005\u000b\u0015C\"\u0019\u0001$\t\rqB\u0002\u0019AAD!\u0011y\u0004)!!\t\u000b9C\u0002\u0019\u0001)\u0002\u000fUt\u0017\r\u001d9msV!\u0011qRAO)\u0011\t\t*a(\u0011\t!\n\u00161\u0013\t\u0007Q\u0005U\u0015\u0011\u0014)\n\u0007\u0005]\u0015F\u0001\u0004UkBdWM\r\t\u0005\u007f\u0001\u000bY\nE\u0002D\u0003;#Q!R\rC\u0002\u0019C\u0011\"!)\u001a\u0003\u0003\u0005\r!a)\u0002\u0007a$\u0003\u0007\u0005\u0003@\u0001\u0005m\u0015\u0001D<sSR,'+\u001a9mC\u000e,GCAAU!\u0011\tI\"a+\n\t\u00055\u00161\u0004\u0002\u0007\u001f\nTWm\u0019;"
)
public class WhenNotMatched implements Product, Serializable {
   private final MergeIntoWriter mergeIntoWriter;
   private final Option condition;

   public static Option unapply(final WhenNotMatched x$0) {
      return WhenNotMatched$.MODULE$.unapply(x$0);
   }

   public static WhenNotMatched apply(final MergeIntoWriter mergeIntoWriter, final Option condition) {
      return WhenNotMatched$.MODULE$.apply(mergeIntoWriter, condition);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public MergeIntoWriter mergeIntoWriter() {
      return this.mergeIntoWriter;
   }

   public Option condition() {
      return this.condition;
   }

   public MergeIntoWriter insertAll() {
      return this.mergeIntoWriter().insertAll(this.condition());
   }

   public MergeIntoWriter insert(final Map map) {
      return this.mergeIntoWriter().insert(this.condition(), map);
   }

   public WhenNotMatched copy(final MergeIntoWriter mergeIntoWriter, final Option condition) {
      return new WhenNotMatched(mergeIntoWriter, condition);
   }

   public MergeIntoWriter copy$default$1() {
      return this.mergeIntoWriter();
   }

   public Option copy$default$2() {
      return this.condition();
   }

   public String productPrefix() {
      return "WhenNotMatched";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.mergeIntoWriter();
         }
         case 1 -> {
            return this.condition();
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
      return x$1 instanceof WhenNotMatched;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "mergeIntoWriter";
         }
         case 1 -> {
            return "condition";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof WhenNotMatched) {
               label48: {
                  WhenNotMatched var4 = (WhenNotMatched)x$1;
                  MergeIntoWriter var10000 = this.mergeIntoWriter();
                  MergeIntoWriter var5 = var4.mergeIntoWriter();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  Option var7 = this.condition();
                  Option var6 = var4.condition();
                  if (var7 == null) {
                     if (var6 != null) {
                        break label48;
                     }
                  } else if (!var7.equals(var6)) {
                     break label48;
                  }

                  if (var4.canEqual(this)) {
                     break label55;
                  }
               }
            }

            var8 = false;
            return var8;
         }
      }

      var8 = true;
      return var8;
   }

   public WhenNotMatched(final MergeIntoWriter mergeIntoWriter, final Option condition) {
      this.mergeIntoWriter = mergeIntoWriter;
      this.condition = condition;
      Product.$init$(this);
   }
}
