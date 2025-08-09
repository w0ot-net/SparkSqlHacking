package org.apache.spark.deploy.history;

import java.io.Serializable;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.spark.ui.SparkUI;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%e!B\u000f\u001f\u0001zA\u0003\u0002C \u0001\u0005+\u0007I\u0011\u0001!\t\u0011\u0019\u0003!\u0011#Q\u0001\n\u0005CQa\u0012\u0001\u0005\u0002!Cq\u0001\u0014\u0001C\u0002\u0013\u0005Q\n\u0003\u0004[\u0001\u0001\u0006IA\u0014\u0005\b7\u0002\u0001\r\u0011\"\u0003]\u0011\u001d\u0001\u0007\u00011A\u0005\n\u0005Daa\u001a\u0001!B\u0013i\u0006\"\u00027\u0001\t\u0003a\u0006\"B7\u0001\t\u0003q\u0007bB8\u0001\u0003\u0003%\t\u0001\u001d\u0005\be\u0002\t\n\u0011\"\u0001t\u0011\u001dq\b!!A\u0005B}D\u0011\"!\u0004\u0001\u0003\u0003%\t!a\u0004\t\u0013\u0005]\u0001!!A\u0005\u0002\u0005e\u0001\"CA\u0012\u0001\u0005\u0005I\u0011IA\u0013\u0011%\t\u0019\u0004AA\u0001\n\u0003\t)\u0004C\u0005\u0002:\u0001\t\t\u0011\"\u0011\u0002<!I\u0011q\b\u0001\u0002\u0002\u0013\u0005\u0013\u0011\t\u0005\n\u0003\u0007\u0002\u0011\u0011!C!\u0003\u000bB\u0011\"a\u0012\u0001\u0003\u0003%\t%!\u0013\b\u0015\u00055c$!A\t\u0002y\tyEB\u0005\u001e=\u0005\u0005\t\u0012\u0001\u0010\u0002R!1qi\u0006C\u0001\u0003SB\u0011\"a\u0011\u0018\u0003\u0003%)%!\u0012\t\u0013\u0005-t#!A\u0005\u0002\u00065\u0004\"CA9/\u0005\u0005I\u0011QA:\u0011%\tyhFA\u0001\n\u0013\t\tIA\u0006M_\u0006$W\rZ!qaVK%BA\u0010!\u0003\u001dA\u0017n\u001d;pefT!!\t\u0012\u0002\r\u0011,\u0007\u000f\\8z\u0015\t\u0019C%A\u0003ta\u0006\u00148N\u0003\u0002&M\u00051\u0011\r]1dQ\u0016T\u0011aJ\u0001\u0004_J<7\u0003\u0002\u0001*_I\u0002\"AK\u0017\u000e\u0003-R\u0011\u0001L\u0001\u0006g\u000e\fG.Y\u0005\u0003]-\u0012a!\u00118z%\u00164\u0007C\u0001\u00161\u0013\t\t4FA\u0004Qe>$Wo\u0019;\u0011\u0005MbdB\u0001\u001b;\u001d\t)\u0014(D\u00017\u0015\t9\u0004(\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005a\u0013BA\u001e,\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u0010 \u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005mZ\u0013AA;j+\u0005\t\u0005C\u0001\"E\u001b\u0005\u0019%BA #\u0013\t)5IA\u0004Ta\u0006\u00148.V%\u0002\u0007UL\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0003\u0013.\u0003\"A\u0013\u0001\u000e\u0003yAQaP\u0002A\u0002\u0005\u000bA\u0001\\8dWV\ta\n\u0005\u0002P16\t\u0001K\u0003\u0002R%\u0006)An\\2lg*\u00111\u000bV\u0001\u000bG>t7-\u001e:sK:$(BA+W\u0003\u0011)H/\u001b7\u000b\u0003]\u000bAA[1wC&\u0011\u0011\f\u0015\u0002\u0017%\u0016,g\u000e\u001e:b]R\u0014V-\u00193Xe&$X\rT8dW\u0006)An\\2lA\u00051qL^1mS\u0012,\u0012!\u0018\t\u0003UyK!aX\u0016\u0003\u000f\t{w\u000e\\3b]\u0006QqL^1mS\u0012|F%Z9\u0015\u0005\t,\u0007C\u0001\u0016d\u0013\t!7F\u0001\u0003V]&$\bb\u00024\b\u0003\u0003\u0005\r!X\u0001\u0004q\u0012\n\u0014aB0wC2LG\r\t\u0015\u0003\u0011%\u0004\"A\u000b6\n\u0005-\\#\u0001\u0003<pY\u0006$\u0018\u000e\\3\u0002\u000bY\fG.\u001b3\u0002\u0015%tg/\u00197jI\u0006$X\rF\u0001c\u0003\u0011\u0019w\u000e]=\u0015\u0005%\u000b\bbB \f!\u0003\u0005\r!Q\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005!(FA!vW\u00051\bCA<}\u001b\u0005A(BA={\u0003%)hn\u00195fG.,GM\u0003\u0002|W\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005uD(!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006i\u0001O]8ek\u000e$\bK]3gSb,\"!!\u0001\u0011\t\u0005\r\u0011\u0011B\u0007\u0003\u0003\u000bQ1!a\u0002W\u0003\u0011a\u0017M\\4\n\t\u0005-\u0011Q\u0001\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0005\u0005E\u0001c\u0001\u0016\u0002\u0014%\u0019\u0011QC\u0016\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005m\u0011\u0011\u0005\t\u0004U\u0005u\u0011bAA\u0010W\t\u0019\u0011I\\=\t\u0011\u0019|\u0011\u0011!a\u0001\u0003#\tq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003O\u0001b!!\u000b\u00020\u0005mQBAA\u0016\u0015\r\ticK\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA\u0019\u0003W\u0011\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR\u0019Q,a\u000e\t\u0011\u0019\f\u0012\u0011!a\u0001\u00037\t!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!\u0011\u0011AA\u001f\u0011!1'#!AA\u0002\u0005E\u0011\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u0005E\u0011\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005\u0005\u0011AB3rk\u0006d7\u000fF\u0002^\u0003\u0017B\u0001BZ\u000b\u0002\u0002\u0003\u0007\u00111D\u0001\f\u0019>\fG-\u001a3BaB,\u0016\n\u0005\u0002K/M)q#a\u0015\u0002`A1\u0011QKA.\u0003&k!!a\u0016\u000b\u0007\u0005e3&A\u0004sk:$\u0018.\\3\n\t\u0005u\u0013q\u000b\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\f\u0004\u0003BA1\u0003Oj!!a\u0019\u000b\u0007\u0005\u0015d+\u0001\u0002j_&\u0019Q(a\u0019\u0015\u0005\u0005=\u0013!B1qa2LHcA%\u0002p!)qH\u0007a\u0001\u0003\u00069QO\\1qa2LH\u0003BA;\u0003w\u0002BAKA<\u0003&\u0019\u0011\u0011P\u0016\u0003\r=\u0003H/[8o\u0011!\tihGA\u0001\u0002\u0004I\u0015a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\u0011\t\u0005\u0003\u0007\t))\u0003\u0003\u0002\b\u0006\u0015!AB(cU\u0016\u001cG\u000f"
)
public class LoadedAppUI implements Product, Serializable {
   private final SparkUI ui;
   private final ReentrantReadWriteLock lock;
   private volatile boolean _valid;

   public static Option unapply(final LoadedAppUI x$0) {
      return LoadedAppUI$.MODULE$.unapply(x$0);
   }

   public static LoadedAppUI apply(final SparkUI ui) {
      return LoadedAppUI$.MODULE$.apply(ui);
   }

   public static Function1 andThen(final Function1 g) {
      return LoadedAppUI$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return LoadedAppUI$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public SparkUI ui() {
      return this.ui;
   }

   public ReentrantReadWriteLock lock() {
      return this.lock;
   }

   private boolean _valid() {
      return this._valid;
   }

   private void _valid_$eq(final boolean x$1) {
      this._valid = x$1;
   }

   public boolean valid() {
      return this._valid();
   }

   public void invalidate() {
      this.lock().writeLock().lock();

      try {
         this._valid_$eq(false);
      } finally {
         this.lock().writeLock().unlock();
      }

   }

   public LoadedAppUI copy(final SparkUI ui) {
      return new LoadedAppUI(ui);
   }

   public SparkUI copy$default$1() {
      return this.ui();
   }

   public String productPrefix() {
      return "LoadedAppUI";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.ui();
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
      return x$1 instanceof LoadedAppUI;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "ui";
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
            if (x$1 instanceof LoadedAppUI) {
               label40: {
                  LoadedAppUI var4 = (LoadedAppUI)x$1;
                  SparkUI var10000 = this.ui();
                  SparkUI var5 = var4.ui();
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

   public LoadedAppUI(final SparkUI ui) {
      this.ui = ui;
      Product.$init$(this);
      this.lock = new ReentrantReadWriteLock();
      this._valid = true;
   }
}
