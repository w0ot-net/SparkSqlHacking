package org.apache.spark.sql.execution.streaming;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import org.apache.spark.sql.streaming.Trigger;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.concurrent.duration.Duration;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%e\u0001B\r\u001b\u0001\u001eB\u0001b\u0010\u0001\u0003\u0016\u0004%\t\u0001\u0011\u0005\t\t\u0002\u0011\t\u0012)A\u0005\u0003\")Q\t\u0001C\u0001\r\"9!\nAA\u0001\n\u0003Y\u0005bB'\u0001#\u0003%\tA\u0014\u0005\b3\u0002\t\t\u0011\"\u0011[\u0011\u001d\u0019\u0007!!A\u0005\u0002\u0011Dq\u0001\u001b\u0001\u0002\u0002\u0013\u0005\u0011\u000eC\u0004p\u0001\u0005\u0005I\u0011\t9\t\u000f]\u0004\u0011\u0011!C\u0001q\"9Q\u0010AA\u0001\n\u0003r\b\"CA\u0001\u0001\u0005\u0005I\u0011IA\u0002\u0011%\t)\u0001AA\u0001\n\u0003\n9\u0001C\u0005\u0002\n\u0001\t\t\u0011\"\u0011\u0002\f\u001d9\u0011q\u0002\u000e\t\u0002\u0005EaAB\r\u001b\u0011\u0003\t\u0019\u0002\u0003\u0004F!\u0011\u0005\u0011Q\u0005\u0005\b\u0003O\u0001B\u0011AA\u0015\u0011\u001d\t9\u0003\u0005C\u0001\u0003{Aq!!\u0015\u0011\t\u0003\t\u0019\u0006C\u0004\u0002RA!\t!a\u0016\t\u0013\u0005\u001d\u0002#!A\u0005\u0002\u00065\u0004\"CA9!\u0005\u0005I\u0011QA:\u0011%\ty\bEA\u0001\n\u0013\t\tIA\u000bQe>\u001cWm]:j]\u001e$\u0016.\\3Ue&<w-\u001a:\u000b\u0005ma\u0012!C:ue\u0016\fW.\u001b8h\u0015\tib$A\u0005fq\u0016\u001cW\u000f^5p]*\u0011q\u0004I\u0001\u0004gFd'BA\u0011#\u0003\u0015\u0019\b/\u0019:l\u0015\t\u0019C%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002K\u0005\u0019qN]4\u0004\u0001M!\u0001\u0001K\u00174!\tI3&D\u0001+\u0015\tYb$\u0003\u0002-U\t9AK]5hO\u0016\u0014\bC\u0001\u00182\u001b\u0005y#\"\u0001\u0019\u0002\u000bM\u001c\u0017\r\\1\n\u0005Iz#a\u0002)s_\u0012,8\r\u001e\t\u0003iqr!!\u000e\u001e\u000f\u0005YJT\"A\u001c\u000b\u0005a2\u0013A\u0002\u001fs_>$h(C\u00011\u0013\tYt&A\u0004qC\u000e\\\u0017mZ3\n\u0005ur$\u0001D*fe&\fG.\u001b>bE2,'BA\u001e0\u0003)Ig\u000e^3sm\u0006dWj]\u000b\u0002\u0003B\u0011aFQ\u0005\u0003\u0007>\u0012A\u0001T8oO\u0006Y\u0011N\u001c;feZ\fG.T:!\u0003\u0019a\u0014N\\5u}Q\u0011q)\u0013\t\u0003\u0011\u0002i\u0011A\u0007\u0005\u0006\u007f\r\u0001\r!Q\u0001\u0005G>\u0004\u0018\u0010\u0006\u0002H\u0019\"9q\b\u0002I\u0001\u0002\u0004\t\u0015AD2paf$C-\u001a4bk2$H%M\u000b\u0002\u001f*\u0012\u0011\tU\u0016\u0002#B\u0011!kV\u0007\u0002'*\u0011A+V\u0001\nk:\u001c\u0007.Z2lK\u0012T!AV\u0018\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002Y'\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005Y\u0006C\u0001/b\u001b\u0005i&B\u00010`\u0003\u0011a\u0017M\\4\u000b\u0003\u0001\fAA[1wC&\u0011!-\u0018\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003\u0015\u0004\"A\f4\n\u0005\u001d|#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HC\u00016n!\tq3.\u0003\u0002m_\t\u0019\u0011I\\=\t\u000f9D\u0011\u0011!a\u0001K\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012!\u001d\t\u0004eVTW\"A:\u000b\u0005Q|\u0013AC2pY2,7\r^5p]&\u0011ao\u001d\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0002zyB\u0011aF_\u0005\u0003w>\u0012qAQ8pY\u0016\fg\u000eC\u0004o\u0015\u0005\u0005\t\u0019\u00016\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u00037~DqA\\\u0006\u0002\u0002\u0003\u0007Q-\u0001\u0005iCND7i\u001c3f)\u0005)\u0017\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003m\u000ba!Z9vC2\u001cHcA=\u0002\u000e!9aNDA\u0001\u0002\u0004Q\u0017!\u0006)s_\u000e,7o]5oORKW.\u001a+sS\u001e<WM\u001d\t\u0003\u0011B\u0019R\u0001EA\u000b\u00037\u00012ALA\f\u0013\r\tIb\f\u0002\u0007\u0003:L(+\u001a4\u0011\t\u0005u\u00111E\u0007\u0003\u0003?Q1!!\t`\u0003\tIw.C\u0002>\u0003?!\"!!\u0005\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0007\u001d\u000bY\u0003C\u0004\u0002.I\u0001\r!a\f\u0002\u0011%tG/\u001a:wC2\u0004B!!\r\u0002:9!\u00111GA\u001b!\t1t&C\u0002\u00028=\na\u0001\u0015:fI\u00164\u0017b\u00012\u0002<)\u0019\u0011qG\u0018\u0015\u0007\u001d\u000by\u0004C\u0004\u0002.M\u0001\r!!\u0011\u0011\t\u0005\r\u0013QJ\u0007\u0003\u0003\u000bRA!a\u0012\u0002J\u0005AA-\u001e:bi&|gNC\u0002\u0002L=\n!bY8oGV\u0014(/\u001a8u\u0013\u0011\ty%!\u0012\u0003\u0011\u0011+(/\u0019;j_:\faa\u0019:fCR,GcA$\u0002V!9\u0011Q\u0006\u000bA\u0002\u0005=B#B$\u0002Z\u0005m\u0003BBA\u0017+\u0001\u0007\u0011\tC\u0004\u0002^U\u0001\r!a\u0018\u0002\tUt\u0017\u000e\u001e\t\u0005\u0003C\nI'\u0004\u0002\u0002d)!\u00111JA3\u0015\r\t9gX\u0001\u0005kRLG.\u0003\u0003\u0002l\u0005\r$\u0001\u0003+j[\u0016,f.\u001b;\u0015\u0007\u001d\u000by\u0007C\u0003@-\u0001\u0007\u0011)A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005U\u00141\u0010\t\u0005]\u0005]\u0014)C\u0002\u0002z=\u0012aa\u00149uS>t\u0007\u0002CA?/\u0005\u0005\t\u0019A$\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002\u0004B\u0019A,!\"\n\u0007\u0005\u001dUL\u0001\u0004PE*,7\r\u001e"
)
public class ProcessingTimeTrigger extends Trigger implements Product, Serializable {
   private final long intervalMs;

   public static Option unapply(final ProcessingTimeTrigger x$0) {
      return ProcessingTimeTrigger$.MODULE$.unapply(x$0);
   }

   public static ProcessingTimeTrigger apply(final long intervalMs) {
      return ProcessingTimeTrigger$.MODULE$.apply(intervalMs);
   }

   public static ProcessingTimeTrigger create(final long interval, final TimeUnit unit) {
      return ProcessingTimeTrigger$.MODULE$.create(interval, unit);
   }

   public static ProcessingTimeTrigger create(final String interval) {
      return ProcessingTimeTrigger$.MODULE$.create(interval);
   }

   public static ProcessingTimeTrigger apply(final Duration interval) {
      return ProcessingTimeTrigger$.MODULE$.apply(interval);
   }

   public static ProcessingTimeTrigger apply(final String interval) {
      return ProcessingTimeTrigger$.MODULE$.apply(interval);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public long intervalMs() {
      return this.intervalMs;
   }

   public ProcessingTimeTrigger copy(final long intervalMs) {
      return new ProcessingTimeTrigger(intervalMs);
   }

   public long copy$default$1() {
      return this.intervalMs();
   }

   public String productPrefix() {
      return "ProcessingTimeTrigger";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToLong(this.intervalMs());
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
      return x$1 instanceof ProcessingTimeTrigger;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "intervalMs";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.longHash(this.intervalMs()));
      return Statics.finalizeHash(var1, 1);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label36: {
            if (x$1 instanceof ProcessingTimeTrigger) {
               ProcessingTimeTrigger var4 = (ProcessingTimeTrigger)x$1;
               if (this.intervalMs() == var4.intervalMs() && var4.canEqual(this)) {
                  break label36;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public ProcessingTimeTrigger(final long intervalMs) {
      this.intervalMs = intervalMs;
      Product.$init$(this);
      Triggers$.MODULE$.validate(intervalMs);
   }
}
