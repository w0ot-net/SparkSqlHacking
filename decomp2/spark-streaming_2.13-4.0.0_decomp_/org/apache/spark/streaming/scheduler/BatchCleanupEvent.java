package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-c!\u0002\f\u0018\u0001f\t\u0003\u0002\u0003\u001f\u0001\u0005+\u0007I\u0011A\u001f\t\u0011\u0015\u0003!\u0011#Q\u0001\nyBQA\u0012\u0001\u0005\u0002\u001dCqA\u0013\u0001\u0002\u0002\u0013\u00051\nC\u0004N\u0001E\u0005I\u0011\u0001(\t\u000fe\u0003\u0011\u0011!C!5\"91\rAA\u0001\n\u0003!\u0007b\u00025\u0001\u0003\u0003%\t!\u001b\u0005\b_\u0002\t\t\u0011\"\u0011q\u0011\u001d9\b!!A\u0005\u0002aDq! \u0001\u0002\u0002\u0013\u0005c\u0010C\u0005\u0002\u0002\u0001\t\t\u0011\"\u0011\u0002\u0004!I\u0011Q\u0001\u0001\u0002\u0002\u0013\u0005\u0013q\u0001\u0005\n\u0003\u0013\u0001\u0011\u0011!C!\u0003\u00179!\"a\u0004\u0018\u0003\u0003E\t!GA\t\r%1r#!A\t\u0002e\t\u0019\u0002\u0003\u0004G!\u0011\u0005\u00111\u0006\u0005\n\u0003\u000b\u0001\u0012\u0011!C#\u0003\u000fA\u0011\"!\f\u0011\u0003\u0003%\t)a\f\t\u0013\u0005M\u0002#!A\u0005\u0002\u0006U\u0002\"CA!!\u0005\u0005I\u0011BA\"\u0005E\u0011\u0015\r^2i\u00072,\u0017M\\;q\u000bZ,g\u000e\u001e\u0006\u00031e\t\u0011b]2iK\u0012,H.\u001a:\u000b\u0005iY\u0012!C:ue\u0016\fW.\u001b8h\u0015\taR$A\u0003ta\u0006\u00148N\u0003\u0002\u001f?\u00051\u0011\r]1dQ\u0016T\u0011\u0001I\u0001\u0004_J<7#\u0002\u0001#Q1z\u0003CA\u0012'\u001b\u0005!#\"A\u0013\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u001d\"#AB!osJ+g\r\u0005\u0002*U5\tq#\u0003\u0002,/\ta\"+Z2fSZ,GM\u00117pG.$&/Y2lKJdunZ#wK:$\bCA\u0012.\u0013\tqCEA\u0004Qe>$Wo\u0019;\u0011\u0005AJdBA\u00198\u001d\t\u0011d'D\u00014\u0015\t!T'\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005)\u0013B\u0001\u001d%\u0003\u001d\u0001\u0018mY6bO\u0016L!AO\u001e\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005a\"\u0013!\u0002;j[\u0016\u001cX#\u0001 \u0011\u0007Az\u0014)\u0003\u0002Aw\t\u00191+Z9\u0011\u0005\t\u001bU\"A\r\n\u0005\u0011K\"\u0001\u0002+j[\u0016\fa\u0001^5nKN\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002I\u0013B\u0011\u0011\u0006\u0001\u0005\u0006y\r\u0001\rAP\u0001\u0005G>\u0004\u0018\u0010\u0006\u0002I\u0019\"9A\b\u0002I\u0001\u0002\u0004q\u0014AD2paf$C-\u001a4bk2$H%M\u000b\u0002\u001f*\u0012a\bU\u0016\u0002#B\u0011!kV\u0007\u0002'*\u0011A+V\u0001\nk:\u001c\u0007.Z2lK\u0012T!A\u0016\u0013\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002Y'\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005Y\u0006C\u0001/b\u001b\u0005i&B\u00010`\u0003\u0011a\u0017M\\4\u000b\u0003\u0001\fAA[1wC&\u0011!-\u0018\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003\u0015\u0004\"a\t4\n\u0005\u001d$#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HC\u00016n!\t\u00193.\u0003\u0002mI\t\u0019\u0011I\\=\t\u000f9D\u0011\u0011!a\u0001K\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012!\u001d\t\u0004eVTW\"A:\u000b\u0005Q$\u0013AC2pY2,7\r^5p]&\u0011ao\u001d\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0002zyB\u00111E_\u0005\u0003w\u0012\u0012qAQ8pY\u0016\fg\u000eC\u0004o\u0015\u0005\u0005\t\u0019\u00016\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u00037~DqA\\\u0006\u0002\u0002\u0003\u0007Q-\u0001\u0005iCND7i\u001c3f)\u0005)\u0017\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003m\u000ba!Z9vC2\u001cHcA=\u0002\u000e!9aNDA\u0001\u0002\u0004Q\u0017!\u0005\"bi\u000eD7\t\\3b]V\u0004XI^3oiB\u0011\u0011\u0006E\n\u0006!\u0005U\u0011\u0011\u0005\t\u0007\u0003/\tiB\u0010%\u000e\u0005\u0005e!bAA\u000eI\u00059!/\u001e8uS6,\u0017\u0002BA\u0010\u00033\u0011\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c82!\u0011\t\u0019#!\u000b\u000e\u0005\u0005\u0015\"bAA\u0014?\u0006\u0011\u0011n\\\u0005\u0004u\u0005\u0015BCAA\t\u0003\u0015\t\u0007\u000f\u001d7z)\rA\u0015\u0011\u0007\u0005\u0006yM\u0001\rAP\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t9$!\u0010\u0011\t\r\nIDP\u0005\u0004\u0003w!#AB(qi&|g\u000e\u0003\u0005\u0002@Q\t\t\u00111\u0001I\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003\u000b\u00022\u0001XA$\u0013\r\tI%\u0018\u0002\u0007\u001f\nTWm\u0019;"
)
public class BatchCleanupEvent implements ReceivedBlockTrackerLogEvent, Product, Serializable {
   private final Seq times;

   public static Option unapply(final BatchCleanupEvent x$0) {
      return BatchCleanupEvent$.MODULE$.unapply(x$0);
   }

   public static BatchCleanupEvent apply(final Seq times) {
      return BatchCleanupEvent$.MODULE$.apply(times);
   }

   public static Function1 andThen(final Function1 g) {
      return BatchCleanupEvent$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return BatchCleanupEvent$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Seq times() {
      return this.times;
   }

   public BatchCleanupEvent copy(final Seq times) {
      return new BatchCleanupEvent(times);
   }

   public Seq copy$default$1() {
      return this.times();
   }

   public String productPrefix() {
      return "BatchCleanupEvent";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.times();
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
      return x$1 instanceof BatchCleanupEvent;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "times";
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
      boolean var6;
      if (this != x$1) {
         label47: {
            if (x$1 instanceof BatchCleanupEvent) {
               label40: {
                  BatchCleanupEvent var4 = (BatchCleanupEvent)x$1;
                  Seq var10000 = this.times();
                  Seq var5 = var4.times();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label40;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label40;
                  }

                  if (var4.canEqual(this)) {
                     break label47;
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

   public BatchCleanupEvent(final Seq times) {
      this.times = times;
      Product.$init$(this);
   }
}
