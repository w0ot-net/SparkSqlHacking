package org.apache.spark.streaming.api.java;

import java.io.Serializable;
import java.util.Map;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015f!B\u0010!\u0001\u0012b\u0003\u0002C\"\u0001\u0005+\u0007I\u0011\u0001#\t\u0011!\u0003!\u0011#Q\u0001\n\u0015C\u0001\"\u0013\u0001\u0003\u0016\u0004%\tA\u0013\u0005\t\u001d\u0002\u0011\t\u0012)A\u0005\u0017\"Aq\n\u0001BK\u0002\u0013\u0005\u0001\u000b\u0003\u0005d\u0001\tE\t\u0015!\u0003R\u0011!!\u0007A!f\u0001\n\u0003)\u0007\u0002\u00034\u0001\u0005#\u0005\u000b\u0011\u0002-\t\u000b\u001d\u0004A\u0011\u00015\t\u000f=\u0004\u0011\u0011!C\u0001a\"9Q\u000fAI\u0001\n\u00031\b\"CA\u0002\u0001E\u0005I\u0011AA\u0003\u0011%\tI\u0001AI\u0001\n\u0003\tY\u0001C\u0005\u0002\u0010\u0001\t\n\u0011\"\u0001\u0002\u0012!I\u0011Q\u0003\u0001\u0002\u0002\u0013\u0005\u0013q\u0003\u0005\t\u0003G\u0001\u0011\u0011!C\u0001\t\"I\u0011Q\u0005\u0001\u0002\u0002\u0013\u0005\u0011q\u0005\u0005\n\u0003[\u0001\u0011\u0011!C!\u0003_A\u0011\"!\u0010\u0001\u0003\u0003%\t!a\u0010\t\u0013\u0005%\u0003!!A\u0005B\u0005-\u0003\"CA(\u0001\u0005\u0005I\u0011IA)\u0011%\t\u0019\u0006AA\u0001\n\u0003\n)\u0006C\u0005\u0002X\u0001\t\t\u0011\"\u0011\u0002Z\u001dQ\u0011Q\f\u0011\u0002\u0002#\u0005A%a\u0018\u0007\u0013}\u0001\u0013\u0011!E\u0001I\u0005\u0005\u0004BB4\u001a\t\u0003\tI\bC\u0005\u0002Te\t\t\u0011\"\u0012\u0002V!I\u00111P\r\u0002\u0002\u0013\u0005\u0015Q\u0010\u0005\n\u0003\u000fK\u0012\u0011!CA\u0003\u0013C\u0011\"a'\u001a\u0003\u0003%I!!(\u0003')\u000bg/Y*ue\u0016\fW.\u00138qkRLeNZ8\u000b\u0005\u0005\u0012\u0013\u0001\u00026bm\u0006T!a\t\u0013\u0002\u0007\u0005\u0004\u0018N\u0003\u0002&M\u0005I1\u000f\u001e:fC6Lgn\u001a\u0006\u0003O!\nQa\u001d9be.T!!\u000b\u0016\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005Y\u0013aA8sON!\u0001!L\u001a7!\tq\u0013'D\u00010\u0015\u0005\u0001\u0014!B:dC2\f\u0017B\u0001\u001a0\u0005\u0019\te.\u001f*fMB\u0011a\u0006N\u0005\u0003k=\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00028\u0001:\u0011\u0001H\u0010\b\u0003suj\u0011A\u000f\u0006\u0003wq\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002a%\u0011qhL\u0001\ba\u0006\u001c7.Y4f\u0013\t\t%I\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002@_\u0005i\u0011N\u001c9viN#(/Z1n\u0013\u0012,\u0012!\u0012\t\u0003]\u0019K!aR\u0018\u0003\u0007%sG/\u0001\bj]B,Ho\u0015;sK\u0006l\u0017\n\u001a\u0011\u0002\u00159,XNU3d_J$7/F\u0001L!\tqC*\u0003\u0002N_\t!Aj\u001c8h\u0003-qW/\u001c*fG>\u0014Hm\u001d\u0011\u0002\u00115,G/\u00193bi\u0006,\u0012!\u0015\t\u0005%ZC\u0006-D\u0001T\u0015\t!V+\u0001\u0003vi&d'\"A\u0011\n\u0005]\u001b&aA'baB\u0011\u0011,\u0018\b\u00035n\u0003\"!O\u0018\n\u0005q{\u0013A\u0002)sK\u0012,g-\u0003\u0002_?\n11\u000b\u001e:j]\u001eT!\u0001X\u0018\u0011\u00059\n\u0017B\u000120\u0005\r\te._\u0001\n[\u0016$\u0018\rZ1uC\u0002\n1#\\3uC\u0012\fG/\u0019#fg\u000e\u0014\u0018\u000e\u001d;j_:,\u0012\u0001W\u0001\u0015[\u0016$\u0018\rZ1uC\u0012+7o\u0019:jaRLwN\u001c\u0011\u0002\rqJg.\u001b;?)\u0015I7\u000e\\7o!\tQ\u0007!D\u0001!\u0011\u0015\u0019\u0015\u00021\u0001F\u0011\u0015I\u0015\u00021\u0001L\u0011\u0015y\u0015\u00021\u0001R\u0011\u0015!\u0017\u00021\u0001Y\u0003\u0011\u0019w\u000e]=\u0015\u000b%\f(o\u001d;\t\u000f\rS\u0001\u0013!a\u0001\u000b\"9\u0011J\u0003I\u0001\u0002\u0004Y\u0005bB(\u000b!\u0003\u0005\r!\u0015\u0005\bI*\u0001\n\u00111\u0001Y\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012a\u001e\u0016\u0003\u000bb\\\u0013!\u001f\t\u0003u~l\u0011a\u001f\u0006\u0003yv\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005y|\u0013AC1o]>$\u0018\r^5p]&\u0019\u0011\u0011A>\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0005\u0005\u001d!FA&y\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIM*\"!!\u0004+\u0005EC\u0018AD2paf$C-\u001a4bk2$H\u0005N\u000b\u0003\u0003'Q#\u0001\u0017=\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\tI\u0002\u0005\u0003\u0002\u001c\u0005\u0005RBAA\u000f\u0015\r\ty\"V\u0001\u0005Y\u0006tw-C\u0002_\u0003;\tA\u0002\u001d:pIV\u001cG/\u0011:jif\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000fF\u0002a\u0003SA\u0001\"a\u000b\u0012\u0003\u0003\u0005\r!R\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005E\u0002#BA\u001a\u0003s\u0001WBAA\u001b\u0015\r\t9dL\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA\u001e\u0003k\u0011\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u0011\u0011IA$!\rq\u00131I\u0005\u0004\u0003\u000bz#a\u0002\"p_2,\u0017M\u001c\u0005\t\u0003W\u0019\u0012\u0011!a\u0001A\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\tI\"!\u0014\t\u0011\u0005-B#!AA\u0002\u0015\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002\u000b\u0006AAo\\*ue&tw\r\u0006\u0002\u0002\u001a\u00051Q-];bYN$B!!\u0011\u0002\\!A\u00111F\f\u0002\u0002\u0003\u0007\u0001-A\nKCZ\f7\u000b\u001e:fC6Le\u000e];u\u0013:4w\u000e\u0005\u0002k3M)\u0011$a\u0019\u0002pAI\u0011QMA6\u000b.\u000b\u0006,[\u0007\u0003\u0003OR1!!\u001b0\u0003\u001d\u0011XO\u001c;j[\u0016LA!!\u001c\u0002h\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\u001c\u001b\u0011\t\u0005E\u0014qO\u0007\u0003\u0003gR1!!\u001eV\u0003\tIw.C\u0002B\u0003g\"\"!a\u0018\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0013%\fy(!!\u0002\u0004\u0006\u0015\u0005\"B\"\u001d\u0001\u0004)\u0005\"B%\u001d\u0001\u0004Y\u0005\"B(\u001d\u0001\u0004\t\u0006\"\u00023\u001d\u0001\u0004A\u0016aB;oCB\u0004H.\u001f\u000b\u0005\u0003\u0017\u000b9\nE\u0003/\u0003\u001b\u000b\t*C\u0002\u0002\u0010>\u0012aa\u00149uS>t\u0007c\u0002\u0018\u0002\u0014\u0016[\u0015\u000bW\u0005\u0004\u0003+{#A\u0002+va2,G\u0007\u0003\u0005\u0002\u001av\t\t\u00111\u0001j\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003?\u0003B!a\u0007\u0002\"&!\u00111UA\u000f\u0005\u0019y%M[3di\u0002"
)
public class JavaStreamInputInfo implements Product, Serializable {
   private final int inputStreamId;
   private final long numRecords;
   private final Map metadata;
   private final String metadataDescription;

   public static Option unapply(final JavaStreamInputInfo x$0) {
      return JavaStreamInputInfo$.MODULE$.unapply(x$0);
   }

   public static JavaStreamInputInfo apply(final int inputStreamId, final long numRecords, final Map metadata, final String metadataDescription) {
      return JavaStreamInputInfo$.MODULE$.apply(inputStreamId, numRecords, metadata, metadataDescription);
   }

   public static Function1 tupled() {
      return JavaStreamInputInfo$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return JavaStreamInputInfo$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int inputStreamId() {
      return this.inputStreamId;
   }

   public long numRecords() {
      return this.numRecords;
   }

   public Map metadata() {
      return this.metadata;
   }

   public String metadataDescription() {
      return this.metadataDescription;
   }

   public JavaStreamInputInfo copy(final int inputStreamId, final long numRecords, final Map metadata, final String metadataDescription) {
      return new JavaStreamInputInfo(inputStreamId, numRecords, metadata, metadataDescription);
   }

   public int copy$default$1() {
      return this.inputStreamId();
   }

   public long copy$default$2() {
      return this.numRecords();
   }

   public Map copy$default$3() {
      return this.metadata();
   }

   public String copy$default$4() {
      return this.metadataDescription();
   }

   public String productPrefix() {
      return "JavaStreamInputInfo";
   }

   public int productArity() {
      return 4;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.inputStreamId());
         }
         case 1 -> {
            return BoxesRunTime.boxToLong(this.numRecords());
         }
         case 2 -> {
            return this.metadata();
         }
         case 3 -> {
            return this.metadataDescription();
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
      return x$1 instanceof JavaStreamInputInfo;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "inputStreamId";
         }
         case 1 -> {
            return "numRecords";
         }
         case 2 -> {
            return "metadata";
         }
         case 3 -> {
            return "metadataDescription";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.inputStreamId());
      var1 = Statics.mix(var1, Statics.longHash(this.numRecords()));
      var1 = Statics.mix(var1, Statics.anyHash(this.metadata()));
      var1 = Statics.mix(var1, Statics.anyHash(this.metadataDescription()));
      return Statics.finalizeHash(var1, 4);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label63: {
            if (x$1 instanceof JavaStreamInputInfo) {
               JavaStreamInputInfo var4 = (JavaStreamInputInfo)x$1;
               if (this.inputStreamId() == var4.inputStreamId() && this.numRecords() == var4.numRecords()) {
                  label56: {
                     Map var10000 = this.metadata();
                     Map var5 = var4.metadata();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label56;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label56;
                     }

                     String var7 = this.metadataDescription();
                     String var6 = var4.metadataDescription();
                     if (var7 == null) {
                        if (var6 != null) {
                           break label56;
                        }
                     } else if (!var7.equals(var6)) {
                        break label56;
                     }

                     if (var4.canEqual(this)) {
                        break label63;
                     }
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

   public JavaStreamInputInfo(final int inputStreamId, final long numRecords, final Map metadata, final String metadataDescription) {
      this.inputStreamId = inputStreamId;
      this.numRecords = numRecords;
      this.metadata = metadata;
      this.metadataDescription = metadataDescription;
      Product.$init$(this);
   }
}
