package org.apache.spark;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015e\u0001B\u0010!\t\u001eB\u0001\"\u0010\u0001\u0003\u0016\u0004%\tA\u0010\u0005\t\u000f\u0002\u0011\t\u0012)A\u0005\u007f!A\u0001\n\u0001BK\u0002\u0013\u0005a\b\u0003\u0005J\u0001\tE\t\u0015!\u0003@\u0011!Q\u0005A!f\u0001\n\u0003q\u0004\u0002C&\u0001\u0005#\u0005\u000b\u0011B \t\u00111\u0003!Q3A\u0005\u00025C\u0001\"\u0015\u0001\u0003\u0012\u0003\u0006IA\u0014\u0005\u0006%\u0002!\ta\u0015\u0005\b5\u0002\t\t\u0011\"\u0001\\\u0011\u001d\u0001\u0007!%A\u0005\u0002\u0005Dq\u0001\u001c\u0001\u0012\u0002\u0013\u0005\u0011\rC\u0004n\u0001E\u0005I\u0011A1\t\u000f9\u0004\u0011\u0013!C\u0001_\"9\u0011\u000fAA\u0001\n\u0003\u0012\bb\u0002>\u0001\u0003\u0003%\ta\u001f\u0005\t\u007f\u0002\t\t\u0011\"\u0001\u0002\u0002!I\u0011Q\u0002\u0001\u0002\u0002\u0013\u0005\u0013q\u0002\u0005\n\u0003;\u0001\u0011\u0011!C\u0001\u0003?A\u0011\"!\u000b\u0001\u0003\u0003%\t%a\u000b\t\u0013\u0005=\u0002!!A\u0005B\u0005E\u0002\"CA\u001a\u0001\u0005\u0005I\u0011IA\u001b\u0011%\t9\u0004AA\u0001\n\u0003\nIdB\u0005\u0002>\u0001\n\t\u0011#\u0003\u0002@\u0019Aq\u0004IA\u0001\u0012\u0013\t\t\u0005\u0003\u0004S3\u0011\u0005\u0011\u0011\f\u0005\n\u0003gI\u0012\u0011!C#\u0003kA\u0011\"a\u0017\u001a\u0003\u0003%\t)!\u0018\t\u0013\u0005\u001d\u0014$!A\u0005\u0002\u0006%\u0004\"CA>3\u0005\u0005I\u0011BA?\u00059)%O]8s'R\fG/Z%oM>T!!\t\u0012\u0002\u000bM\u0004\u0018M]6\u000b\u0005\r\"\u0013AB1qC\u000eDWMC\u0001&\u0003\ry'oZ\u0002\u0001'\u0011\u0001\u0001FL\u0019\u0011\u0005%bS\"\u0001\u0016\u000b\u0003-\nQa]2bY\u0006L!!\f\u0016\u0003\r\u0005s\u0017PU3g!\tIs&\u0003\u00021U\t9\u0001K]8ek\u000e$\bC\u0001\u001a;\u001d\t\u0019\u0004H\u0004\u00025o5\tQG\u0003\u00027M\u00051AH]8pizJ\u0011aK\u0005\u0003s)\nq\u0001]1dW\u0006<W-\u0003\u0002<y\ta1+\u001a:jC2L'0\u00192mK*\u0011\u0011HK\u0001\fI\u0016\u001c8M]5qi&|g.F\u0001@!\t\u0001EI\u0004\u0002B\u0005B\u0011AGK\u0005\u0003\u0007*\na\u0001\u0015:fI\u00164\u0017BA#G\u0005\u0019\u0019FO]5oO*\u00111IK\u0001\rI\u0016\u001c8M]5qi&|g\u000eI\u0001\u0007_JLw-\u001b8\u0002\u000f=\u0014\u0018nZ5oA\u0005A1\u000f^1oI\u0006\u0014H-A\u0005ti\u0006tG-\u0019:eA\u00051Qo]3e\u0005f,\u0012A\u0014\t\u0004e={\u0014B\u0001)=\u0005\u0011a\u0015n\u001d;\u0002\u000fU\u001cX\r\u001a\"zA\u00051A(\u001b8jiz\"R\u0001\u0016,X1f\u0003\"!\u0016\u0001\u000e\u0003\u0001BQ!P\u0005A\u0002}BQ\u0001S\u0005A\u0002}BQAS\u0005A\u0002}BQ\u0001T\u0005A\u00029\u000bAaY8qsR)A\u000bX/_?\"9QH\u0003I\u0001\u0002\u0004y\u0004b\u0002%\u000b!\u0003\u0005\ra\u0010\u0005\b\u0015*\u0001\n\u00111\u0001@\u0011\u001da%\u0002%AA\u00029\u000babY8qs\u0012\"WMZ1vYR$\u0013'F\u0001cU\ty4mK\u0001e!\t)'.D\u0001g\u0015\t9\u0007.A\u0005v]\u000eDWmY6fI*\u0011\u0011NK\u0001\u000bC:tw\u000e^1uS>t\u0017BA6g\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIM\nabY8qs\u0012\"WMZ1vYR$C'F\u0001qU\tq5-A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002gB\u0011A/_\u0007\u0002k*\u0011ao^\u0001\u0005Y\u0006twMC\u0001y\u0003\u0011Q\u0017M^1\n\u0005\u0015+\u0018\u0001\u00049s_\u0012,8\r^!sSRLX#\u0001?\u0011\u0005%j\u0018B\u0001@+\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\t\u0019!!\u0003\u0011\u0007%\n)!C\u0002\u0002\b)\u00121!\u00118z\u0011!\tY!EA\u0001\u0002\u0004a\u0018a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002\u0012A1\u00111CA\r\u0003\u0007i!!!\u0006\u000b\u0007\u0005]!&\u0001\u0006d_2dWm\u0019;j_:LA!a\u0007\u0002\u0016\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\t\t#a\n\u0011\u0007%\n\u0019#C\u0002\u0002&)\u0012qAQ8pY\u0016\fg\u000eC\u0005\u0002\fM\t\t\u00111\u0001\u0002\u0004\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\r\u0019\u0018Q\u0006\u0005\t\u0003\u0017!\u0012\u0011!a\u0001y\u0006A\u0001.Y:i\u0007>$W\rF\u0001}\u0003!!xn\u0015;sS:<G#A:\u0002\r\u0015\fX/\u00197t)\u0011\t\t#a\u000f\t\u0013\u0005-q#!AA\u0002\u0005\r\u0011AD#se>\u00148\u000b^1uK&sgm\u001c\t\u0003+f\u0019R!GA\"\u0003\u001f\u0002\u0012\"!\u0012\u0002L}ztH\u0014+\u000e\u0005\u0005\u001d#bAA%U\u00059!/\u001e8uS6,\u0017\u0002BA'\u0003\u000f\u0012\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c85!\u0011\t\t&a\u0016\u000e\u0005\u0005M#bAA+o\u0006\u0011\u0011n\\\u0005\u0004w\u0005MCCAA \u0003\u0015\t\u0007\u000f\u001d7z)%!\u0016qLA1\u0003G\n)\u0007C\u0003>9\u0001\u0007q\bC\u0003I9\u0001\u0007q\bC\u0003K9\u0001\u0007q\bC\u0003M9\u0001\u0007a*A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005-\u0014q\u000f\t\u0006S\u00055\u0014\u0011O\u0005\u0004\u0003_R#AB(qi&|g\u000eE\u0004*\u0003gzth\u0010(\n\u0007\u0005U$F\u0001\u0004UkBdW\r\u000e\u0005\t\u0003sj\u0012\u0011!a\u0001)\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005}\u0004c\u0001;\u0002\u0002&\u0019\u00111Q;\u0003\r=\u0013'.Z2u\u0001"
)
public class ErrorStateInfo implements Product, Serializable {
   private final String description;
   private final String origin;
   private final String standard;
   private final List usedBy;

   public static Option unapply(final ErrorStateInfo x$0) {
      return ErrorStateInfo$.MODULE$.unapply(x$0);
   }

   public static ErrorStateInfo apply(final String description, final String origin, final String standard, final List usedBy) {
      return ErrorStateInfo$.MODULE$.apply(description, origin, standard, usedBy);
   }

   public static Function1 tupled() {
      return ErrorStateInfo$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ErrorStateInfo$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String description() {
      return this.description;
   }

   public String origin() {
      return this.origin;
   }

   public String standard() {
      return this.standard;
   }

   public List usedBy() {
      return this.usedBy;
   }

   public ErrorStateInfo copy(final String description, final String origin, final String standard, final List usedBy) {
      return new ErrorStateInfo(description, origin, standard, usedBy);
   }

   public String copy$default$1() {
      return this.description();
   }

   public String copy$default$2() {
      return this.origin();
   }

   public String copy$default$3() {
      return this.standard();
   }

   public List copy$default$4() {
      return this.usedBy();
   }

   public String productPrefix() {
      return "ErrorStateInfo";
   }

   public int productArity() {
      return 4;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.description();
         }
         case 1 -> {
            return this.origin();
         }
         case 2 -> {
            return this.standard();
         }
         case 3 -> {
            return this.usedBy();
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
      return x$1 instanceof ErrorStateInfo;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "description";
         }
         case 1 -> {
            return "origin";
         }
         case 2 -> {
            return "standard";
         }
         case 3 -> {
            return "usedBy";
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
      boolean var12;
      if (this != x$1) {
         label71: {
            if (x$1 instanceof ErrorStateInfo) {
               label64: {
                  ErrorStateInfo var4 = (ErrorStateInfo)x$1;
                  String var10000 = this.description();
                  String var5 = var4.description();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label64;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label64;
                  }

                  var10000 = this.origin();
                  String var6 = var4.origin();
                  if (var10000 == null) {
                     if (var6 != null) {
                        break label64;
                     }
                  } else if (!var10000.equals(var6)) {
                     break label64;
                  }

                  var10000 = this.standard();
                  String var7 = var4.standard();
                  if (var10000 == null) {
                     if (var7 != null) {
                        break label64;
                     }
                  } else if (!var10000.equals(var7)) {
                     break label64;
                  }

                  List var11 = this.usedBy();
                  List var8 = var4.usedBy();
                  if (var11 == null) {
                     if (var8 != null) {
                        break label64;
                     }
                  } else if (!var11.equals(var8)) {
                     break label64;
                  }

                  if (var4.canEqual(this)) {
                     break label71;
                  }
               }
            }

            var12 = false;
            return var12;
         }
      }

      var12 = true;
      return var12;
   }

   public ErrorStateInfo(final String description, final String origin, final String standard, final List usedBy) {
      this.description = description;
      this.origin = origin;
      this.standard = standard;
      this.usedBy = usedBy;
      Product.$init$(this);
   }
}
