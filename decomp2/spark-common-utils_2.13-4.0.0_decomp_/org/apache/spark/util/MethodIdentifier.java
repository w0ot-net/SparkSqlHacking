package org.apache.spark.util;

import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001df\u0001\u0002\u000f\u001e\t\u001aB\u0001\"\u0010\u0001\u0003\u0016\u0004%\tA\u0010\u0005\t%\u0002\u0011\t\u0012)A\u0005\u007f!A1\u000b\u0001BK\u0002\u0013\u0005A\u000b\u0003\u0005Y\u0001\tE\t\u0015!\u0003V\u0011!I\u0006A!f\u0001\n\u0003!\u0006\u0002\u0003.\u0001\u0005#\u0005\u000b\u0011B+\t\u000bm\u0003A\u0011\u0001/\t\u000f\t\u0004\u0011\u0011!C\u0001G\"9A\u000eAI\u0001\n\u0003i\u0007b\u0002>\u0001#\u0003%\ta\u001f\u0005\t\u007f\u0002\t\n\u0011\"\u0001\u0002\u0002!I\u0011Q\u0001\u0001\u0002\u0002\u0013\u0005\u0013q\u0001\u0005\n\u0003/\u0001\u0011\u0011!C\u0001\u00033A\u0011\"!\t\u0001\u0003\u0003%\t!a\t\t\u0013\u0005%\u0002!!A\u0005B\u0005-\u0002\"CA\u001d\u0001\u0005\u0005I\u0011AA\u001e\u0011%\t)\u0005AA\u0001\n\u0003\n9\u0005C\u0005\u0002L\u0001\t\t\u0011\"\u0011\u0002N!I\u0011q\n\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u000b\u0005\n\u0003'\u0002\u0011\u0011!C!\u0003+:\u0011\"!\u0017\u001e\u0003\u0003EI!a\u0017\u0007\u0011qi\u0012\u0011!E\u0005\u0003;Baa\u0017\f\u0005\u0002\u0005%\u0004\"CA(-\u0005\u0005IQIA)\u0011%\tYGFA\u0001\n\u0003\u000bi\u0007C\u0005\u0002\u0000Y\t\t\u0011\"!\u0002\u0002\"I\u0011Q\u0014\f\u0002\u0002\u0013%\u0011q\u0014\u0002\u0011\u001b\u0016$\bn\u001c3JI\u0016tG/\u001b4jKJT!AH\u0010\u0002\tU$\u0018\u000e\u001c\u0006\u0003A\u0005\nQa\u001d9be.T!AI\u0012\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005!\u0013aA8sO\u000e\u0001QCA\u0014J'\u0011\u0001\u0001FL\u0019\u0011\u0005%bS\"\u0001\u0016\u000b\u0003-\nQa]2bY\u0006L!!\f\u0016\u0003\r\u0005s\u0017PU3g!\tIs&\u0003\u00021U\t9\u0001K]8ek\u000e$\bC\u0001\u001a;\u001d\t\u0019\u0004H\u0004\u00025o5\tQG\u0003\u00027K\u00051AH]8pizJ\u0011aK\u0005\u0003s)\nq\u0001]1dW\u0006<W-\u0003\u0002<y\ta1+\u001a:jC2L'0\u00192mK*\u0011\u0011HK\u0001\u0004G2\u001cX#A \u0011\u0007\u0001#uI\u0004\u0002B\u0005B\u0011AGK\u0005\u0003\u0007*\na\u0001\u0015:fI\u00164\u0017BA#G\u0005\u0015\u0019E.Y:t\u0015\t\u0019%\u0006\u0005\u0002I\u00132\u0001A!\u0002&\u0001\u0005\u0004Y%!\u0001+\u0012\u00051{\u0005CA\u0015N\u0013\tq%FA\u0004O_RD\u0017N\\4\u0011\u0005%\u0002\u0016BA)+\u0005\r\te._\u0001\u0005G2\u001c\b%\u0001\u0003oC6,W#A+\u0011\u0005\u00013\u0016BA,G\u0005\u0019\u0019FO]5oO\u0006)a.Y7fA\u0005!A-Z:d\u0003\u0015!Wm]2!\u0003\u0019a\u0014N\\5u}Q!Ql\u00181b!\rq\u0006aR\u0007\u0002;!)Qh\u0002a\u0001\u007f!)1k\u0002a\u0001+\")\u0011l\u0002a\u0001+\u0006!1m\u001c9z+\t!w\r\u0006\u0003fQ*\\\u0007c\u00010\u0001MB\u0011\u0001j\u001a\u0003\u0006\u0015\"\u0011\ra\u0013\u0005\b{!\u0001\n\u00111\u0001j!\r\u0001EI\u001a\u0005\b'\"\u0001\n\u00111\u0001V\u0011\u001dI\u0006\u0002%AA\u0002U\u000babY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0002osV\tqN\u000b\u0002@a.\n\u0011\u000f\u0005\u0002so6\t1O\u0003\u0002uk\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003m*\n!\"\u00198o_R\fG/[8o\u0013\tA8OA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016$QAS\u0005C\u0002-\u000babY8qs\u0012\"WMZ1vYR$#'\u0006\u0002}}V\tQP\u000b\u0002Va\u0012)!J\u0003b\u0001\u0017\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u001aTc\u0001?\u0002\u0004\u0011)!j\u0003b\u0001\u0017\u0006i\u0001O]8ek\u000e$\bK]3gSb,\"!!\u0003\u0011\t\u0005-\u0011QC\u0007\u0003\u0003\u001bQA!a\u0004\u0002\u0012\u0005!A.\u00198h\u0015\t\t\u0019\"\u0001\u0003kCZ\f\u0017bA,\u0002\u000e\u0005a\u0001O]8ek\u000e$\u0018I]5usV\u0011\u00111\u0004\t\u0004S\u0005u\u0011bAA\u0010U\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0019q*!\n\t\u0013\u0005\u001db\"!AA\u0002\u0005m\u0011a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002.A)\u0011qFA\u001b\u001f6\u0011\u0011\u0011\u0007\u0006\u0004\u0003gQ\u0013AC2pY2,7\r^5p]&!\u0011qGA\u0019\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005u\u00121\t\t\u0004S\u0005}\u0012bAA!U\t9!i\\8mK\u0006t\u0007\u0002CA\u0014!\u0005\u0005\t\u0019A(\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003\u0013\tI\u0005C\u0005\u0002(E\t\t\u00111\u0001\u0002\u001c\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002\u001c\u0005AAo\\*ue&tw\r\u0006\u0002\u0002\n\u00051Q-];bYN$B!!\u0010\u0002X!A\u0011q\u0005\u000b\u0002\u0002\u0003\u0007q*\u0001\tNKRDw\u000eZ%eK:$\u0018NZ5feB\u0011aLF\n\u0005-!\ny\u0006\u0005\u0003\u0002b\u0005\u001dTBAA2\u0015\u0011\t)'!\u0005\u0002\u0005%|\u0017bA\u001e\u0002dQ\u0011\u00111L\u0001\u0006CB\u0004H._\u000b\u0005\u0003_\n)\b\u0006\u0005\u0002r\u0005]\u00141PA?!\u0011q\u0006!a\u001d\u0011\u0007!\u000b)\bB\u0003K3\t\u00071\n\u0003\u0004>3\u0001\u0007\u0011\u0011\u0010\t\u0005\u0001\u0012\u000b\u0019\bC\u0003T3\u0001\u0007Q\u000bC\u0003Z3\u0001\u0007Q+A\u0004v]\u0006\u0004\b\u000f\\=\u0016\t\u0005\r\u0015Q\u0013\u000b\u0005\u0003\u000b\u000b9\nE\u0003*\u0003\u000f\u000bY)C\u0002\u0002\n*\u0012aa\u00149uS>t\u0007cB\u0015\u0002\u000e\u0006EU+V\u0005\u0004\u0003\u001fS#A\u0002+va2,7\u0007\u0005\u0003A\t\u0006M\u0005c\u0001%\u0002\u0016\u0012)!J\u0007b\u0001\u0017\"I\u0011\u0011\u0014\u000e\u0002\u0002\u0003\u0007\u00111T\u0001\u0004q\u0012\u0002\u0004\u0003\u00020\u0001\u0003'\u000bAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!)\u0011\t\u0005-\u00111U\u0005\u0005\u0003K\u000biA\u0001\u0004PE*,7\r\u001e"
)
public class MethodIdentifier implements Product, Serializable {
   private final Class cls;
   private final String name;
   private final String desc;

   public static Option unapply(final MethodIdentifier x$0) {
      return MethodIdentifier$.MODULE$.unapply(x$0);
   }

   public static MethodIdentifier apply(final Class cls, final String name, final String desc) {
      return MethodIdentifier$.MODULE$.apply(cls, name, desc);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Class cls() {
      return this.cls;
   }

   public String name() {
      return this.name;
   }

   public String desc() {
      return this.desc;
   }

   public MethodIdentifier copy(final Class cls, final String name, final String desc) {
      return new MethodIdentifier(cls, name, desc);
   }

   public Class copy$default$1() {
      return this.cls();
   }

   public String copy$default$2() {
      return this.name();
   }

   public String copy$default$3() {
      return this.desc();
   }

   public String productPrefix() {
      return "MethodIdentifier";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.cls();
         }
         case 1 -> {
            return this.name();
         }
         case 2 -> {
            return this.desc();
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
      return x$1 instanceof MethodIdentifier;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "cls";
         }
         case 1 -> {
            return "name";
         }
         case 2 -> {
            return "desc";
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
      boolean var10;
      if (this != x$1) {
         label63: {
            if (x$1 instanceof MethodIdentifier) {
               label56: {
                  MethodIdentifier var4 = (MethodIdentifier)x$1;
                  Class var10000 = this.cls();
                  Class var5 = var4.cls();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label56;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label56;
                  }

                  String var8 = this.name();
                  String var6 = var4.name();
                  if (var8 == null) {
                     if (var6 != null) {
                        break label56;
                     }
                  } else if (!var8.equals(var6)) {
                     break label56;
                  }

                  var8 = this.desc();
                  String var7 = var4.desc();
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

   public MethodIdentifier(final Class cls, final String name, final String desc) {
      this.cls = cls;
      this.name = name;
      this.desc = desc;
      Product.$init$(this);
   }
}
