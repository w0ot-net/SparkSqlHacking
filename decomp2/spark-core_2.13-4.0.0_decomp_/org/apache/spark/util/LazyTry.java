package org.apache.spark.util;

import java.io.Serializable;
import scala.Function0;
import scala.reflect.ScalaSignature;
import scala.util.Try;

@ScalaSignature(
   bytes = "\u0006\u0005\u00014QAC\u0006\u0001\u001bMA\u0001\u0002\u000b\u0001\u0003\u0002\u0013\u0006I!\u000b\u0005\u0006o\u0001!\t\u0001\u000f\u0005\ty\u0001A)\u0019!C\u0005{!)1\t\u0001C\u0001\t\u001e1Qi\u0003E\u0001\u001b\u00193aAC\u0006\t\u000259\u0005\"B\u001c\u0007\t\u0003y\u0005\"\u0002)\u0007\t\u0003\t\u0006b\u0002-\u0007\u0003\u0003%I!\u0017\u0002\b\u0019\u0006T\u0018\u0010\u0016:z\u0015\taQ\"\u0001\u0003vi&d'B\u0001\b\u0010\u0003\u0015\u0019\b/\u0019:l\u0015\t\u0001\u0012#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002%\u0005\u0019qN]4\u0016\u0005Qq3c\u0001\u0001\u00167A\u0011a#G\u0007\u0002/)\t\u0001$A\u0003tG\u0006d\u0017-\u0003\u0002\u001b/\t1\u0011I\\=SK\u001a\u0004\"\u0001H\u0013\u000f\u0005u\u0019cB\u0001\u0010#\u001b\u0005y\"B\u0001\u0011\"\u0003\u0019a$o\\8u}\r\u0001\u0011\"\u0001\r\n\u0005\u0011:\u0012a\u00029bG.\fw-Z\u0005\u0003M\u001d\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001J\f\u0002\u0015%t\u0017\u000e^5bY&TX\rE\u0002\u0017U1J!aK\f\u0003\u0011q\u0012\u0017P\\1nKz\u0002\"!\f\u0018\r\u0001\u0011)q\u0006\u0001b\u0001a\t\tA+\u0005\u00022iA\u0011aCM\u0005\u0003g]\u0011qAT8uQ&tw\r\u0005\u0002\u0017k%\u0011ag\u0006\u0002\u0004\u0003:L\u0018A\u0002\u001fj]&$h\b\u0006\u0002:wA\u0019!\b\u0001\u0017\u000e\u0003-Aa\u0001\u000b\u0002\u0005\u0002\u0004I\u0013\u0001\u0002;ssR+\u0012A\u0010\t\u0004\u007f\u0005cS\"\u0001!\u000b\u000519\u0012B\u0001\"A\u0005\r!&/_\u0001\u0004O\u0016$X#\u0001\u0017\u0002\u000f1\u000b'0\u001f+ssB\u0011!HB\n\u0004\rUA\u0005CA%O\u001b\u0005Q%BA&M\u0003\tIwNC\u0001N\u0003\u0011Q\u0017M^1\n\u0005\u0019RE#\u0001$\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\u0005I+FCA*W!\rQ\u0004\u0001\u0016\t\u0003[U#Qa\f\u0005C\u0002ABa\u0001\u000b\u0005\u0005\u0002\u00049\u0006c\u0001\f+)\u0006aqO]5uKJ+\u0007\u000f\\1dKR\t!\f\u0005\u0002\\=6\tAL\u0003\u0002^\u0019\u0006!A.\u00198h\u0013\tyFL\u0001\u0004PE*,7\r\u001e"
)
public class LazyTry implements Serializable {
   private Try tryT;
   private Function0 initialize;
   private volatile boolean bitmap$0;

   public static LazyTry apply(final Function0 initialize) {
      return LazyTry$.MODULE$.apply(initialize);
   }

   private Try tryT$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.tryT = Utils$.MODULE$.doTryWithCallerStacktrace(this.initialize);
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      this.initialize = null;
      return this.tryT;
   }

   private Try tryT() {
      return !this.bitmap$0 ? this.tryT$lzycompute() : this.tryT;
   }

   public Object get() {
      return Utils$.MODULE$.getTryWithCallerStacktrace(this.tryT());
   }

   public LazyTry(final Function0 initialize) {
      this.initialize = initialize;
      super();
   }
}
