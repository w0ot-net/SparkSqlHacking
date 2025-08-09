package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}c!B\r\u001b\u0001j\u0011\u0003\u0002C\u001f\u0001\u0005+\u0007I\u0011\u0001 \t\u0011\u001d\u0003!\u0011#Q\u0001\n}B\u0001\u0002\u0013\u0001\u0003\u0016\u0004%\tA\u0010\u0005\t\u0013\u0002\u0011\t\u0012)A\u0005\u007f!)!\n\u0001C\u0001\u0017\"9q\nAA\u0001\n\u0003\u0001\u0006bB*\u0001#\u0003%\t\u0001\u0016\u0005\b?\u0002\t\n\u0011\"\u0001U\u0011\u001d\u0001\u0007!!A\u0005B\u0005Dq!\u001b\u0001\u0002\u0002\u0013\u0005!\u000eC\u0004o\u0001\u0005\u0005I\u0011A8\t\u000fU\u0004\u0011\u0011!C!m\"9Q\u0010AA\u0001\n\u0003q\b\"CA\u0004\u0001\u0005\u0005I\u0011IA\u0005\u0011%\ti\u0001AA\u0001\n\u0003\ny\u0001C\u0005\u0002\u0012\u0001\t\t\u0011\"\u0011\u0002\u0014!I\u0011Q\u0003\u0001\u0002\u0002\u0013\u0005\u0013qC\u0004\u000b\u00037Q\u0012\u0011!E\u00015\u0005ua!C\r\u001b\u0003\u0003E\tAGA\u0010\u0011\u0019Q5\u0003\"\u0001\u00028!I\u0011\u0011C\n\u0002\u0002\u0013\u0015\u00131\u0003\u0005\n\u0003s\u0019\u0012\u0011!CA\u0003wA\u0011\"!\u0011\u0014\u0003\u0003%\t)a\u0011\t\u0013\u0005U3#!A\u0005\n\u0005]#!D#yK\u000e,Ho\u001c:BI\u0012,GM\u0003\u0002\u001c9\u0005I1o\u00195fIVdWM\u001d\u0006\u0003;y\tQa\u001d9be.T!a\b\u0011\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\t\u0013aA8sON)\u0001aI\u0015.aA\u0011AeJ\u0007\u0002K)\ta%A\u0003tG\u0006d\u0017-\u0003\u0002)K\t1\u0011I\\=SK\u001a\u0004\"AK\u0016\u000e\u0003iI!\u0001\f\u000e\u0003#\u0011\u000buiU2iK\u0012,H.\u001a:Fm\u0016tG\u000f\u0005\u0002%]%\u0011q&\n\u0002\b!J|G-^2u!\t\t$H\u0004\u00023q9\u00111gN\u0007\u0002i)\u0011QGN\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\ta%\u0003\u0002:K\u00059\u0001/Y2lC\u001e,\u0017BA\u001e=\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tIT%\u0001\u0004fq\u0016\u001c\u0017\nZ\u000b\u0002\u007fA\u0011\u0001\t\u0012\b\u0003\u0003\n\u0003\"aM\u0013\n\u0005\r+\u0013A\u0002)sK\u0012,g-\u0003\u0002F\r\n11\u000b\u001e:j]\u001eT!aQ\u0013\u0002\u000f\u0015DXmY%eA\u0005!\u0001n\\:u\u0003\u0015Awn\u001d;!\u0003\u0019a\u0014N\\5u}Q\u0019A*\u0014(\u0011\u0005)\u0002\u0001\"B\u001f\u0006\u0001\u0004y\u0004\"\u0002%\u0006\u0001\u0004y\u0014\u0001B2paf$2\u0001T)S\u0011\u001did\u0001%AA\u0002}Bq\u0001\u0013\u0004\u0011\u0002\u0003\u0007q(\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003US#a\u0010,,\u0003]\u0003\"\u0001W/\u000e\u0003eS!AW.\u0002\u0013Ut7\r[3dW\u0016$'B\u0001/&\u0003)\tgN\\8uCRLwN\\\u0005\u0003=f\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#\u00012\u0011\u0005\rDW\"\u00013\u000b\u0005\u00154\u0017\u0001\u00027b]\u001eT\u0011aZ\u0001\u0005U\u00064\u0018-\u0003\u0002FI\u0006a\u0001O]8ek\u000e$\u0018I]5usV\t1\u000e\u0005\u0002%Y&\u0011Q.\n\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003aN\u0004\"\u0001J9\n\u0005I,#aA!os\"9AoCA\u0001\u0002\u0004Y\u0017a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001x!\rA8\u0010]\u0007\u0002s*\u0011!0J\u0001\u000bG>dG.Z2uS>t\u0017B\u0001?z\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\u0007}\f)\u0001E\u0002%\u0003\u0003I1!a\u0001&\u0005\u001d\u0011un\u001c7fC:Dq\u0001^\u0007\u0002\u0002\u0003\u0007\u0001/\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,Gc\u00012\u0002\f!9AODA\u0001\u0002\u0004Y\u0017\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003-\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002E\u00061Q-];bYN$2a`A\r\u0011\u001d!\u0018#!AA\u0002A\fQ\"\u0012=fGV$xN]!eI\u0016$\u0007C\u0001\u0016\u0014'\u0015\u0019\u0012\u0011EA\u0017!\u001d\t\u0019#!\u000b@\u007f1k!!!\n\u000b\u0007\u0005\u001dR%A\u0004sk:$\u0018.\\3\n\t\u0005-\u0012Q\u0005\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u0014\u0004\u0003BA\u0018\u0003ki!!!\r\u000b\u0007\u0005Mb-\u0001\u0002j_&\u00191(!\r\u0015\u0005\u0005u\u0011!B1qa2LH#\u0002'\u0002>\u0005}\u0002\"B\u001f\u0017\u0001\u0004y\u0004\"\u0002%\u0017\u0001\u0004y\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u0003\u000b\n\t\u0006E\u0003%\u0003\u000f\nY%C\u0002\u0002J\u0015\u0012aa\u00149uS>t\u0007#\u0002\u0013\u0002N}z\u0014bAA(K\t1A+\u001e9mKJB\u0001\"a\u0015\u0018\u0003\u0003\u0005\r\u0001T\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA-!\r\u0019\u00171L\u0005\u0004\u0003;\"'AB(cU\u0016\u001cG\u000f"
)
public class ExecutorAdded implements DAGSchedulerEvent, Product, Serializable {
   private final String execId;
   private final String host;

   public static Option unapply(final ExecutorAdded x$0) {
      return ExecutorAdded$.MODULE$.unapply(x$0);
   }

   public static ExecutorAdded apply(final String execId, final String host) {
      return ExecutorAdded$.MODULE$.apply(execId, host);
   }

   public static Function1 tupled() {
      return ExecutorAdded$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ExecutorAdded$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String execId() {
      return this.execId;
   }

   public String host() {
      return this.host;
   }

   public ExecutorAdded copy(final String execId, final String host) {
      return new ExecutorAdded(execId, host);
   }

   public String copy$default$1() {
      return this.execId();
   }

   public String copy$default$2() {
      return this.host();
   }

   public String productPrefix() {
      return "ExecutorAdded";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.execId();
         }
         case 1 -> {
            return this.host();
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
      return x$1 instanceof ExecutorAdded;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "execId";
         }
         case 1 -> {
            return "host";
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
            if (x$1 instanceof ExecutorAdded) {
               label48: {
                  ExecutorAdded var4 = (ExecutorAdded)x$1;
                  String var10000 = this.execId();
                  String var5 = var4.execId();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  var10000 = this.host();
                  String var6 = var4.host();
                  if (var10000 == null) {
                     if (var6 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var6)) {
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

   public ExecutorAdded(final String execId, final String host) {
      this.execId = execId;
      this.host = host;
      Product.$init$(this);
   }
}
