package org.apache.spark.streaming.api.java;

import java.io.Serializable;
import java.util.Map;
import org.apache.spark.streaming.Time;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\tma!B\u00193\u0001Zr\u0004\u0002C+\u0001\u0005+\u0007I\u0011\u0001,\t\u0011m\u0003!\u0011#Q\u0001\n]C\u0001\u0002\u0018\u0001\u0003\u0016\u0004%\t!\u0018\u0005\tY\u0002\u0011\t\u0012)A\u0005=\"AQ\u000e\u0001BK\u0002\u0013\u0005a\u000e\u0003\u0005s\u0001\tE\t\u0015!\u0003p\u0011!\u0019\bA!f\u0001\n\u0003q\u0007\u0002\u0003;\u0001\u0005#\u0005\u000b\u0011B8\t\u0011U\u0004!Q3A\u0005\u00029D\u0001B\u001e\u0001\u0003\u0012\u0003\u0006Ia\u001c\u0005\to\u0002\u0011)\u001a!C\u0001]\"A\u0001\u0010\u0001B\tB\u0003%q\u000e\u0003\u0005z\u0001\tU\r\u0011\"\u0001o\u0011!Q\bA!E!\u0002\u0013y\u0007\u0002C>\u0001\u0005+\u0007I\u0011\u00018\t\u0011q\u0004!\u0011#Q\u0001\n=D\u0001\" \u0001\u0003\u0016\u0004%\tA\u001c\u0005\t}\u0002\u0011\t\u0012)A\u0005_\"Iq\u0010\u0001BK\u0002\u0013\u0005\u0011\u0011\u0001\u0005\u000b\u0003\u0017\u0001!\u0011#Q\u0001\n\u0005\r\u0001bBA\u0007\u0001\u0011\u0005\u0011q\u0002\u0005\n\u0003O\u0001\u0011\u0011!C\u0001\u0003SA\u0011\"a\u0010\u0001#\u0003%\t!!\u0011\t\u0013\u0005]\u0003!%A\u0005\u0002\u0005e\u0003\"CA/\u0001E\u0005I\u0011AA0\u0011%\t\u0019\u0007AI\u0001\n\u0003\ty\u0006C\u0005\u0002f\u0001\t\n\u0011\"\u0001\u0002`!I\u0011q\r\u0001\u0012\u0002\u0013\u0005\u0011q\f\u0005\n\u0003S\u0002\u0011\u0013!C\u0001\u0003?B\u0011\"a\u001b\u0001#\u0003%\t!a\u0018\t\u0013\u00055\u0004!%A\u0005\u0002\u0005}\u0003\"CA8\u0001E\u0005I\u0011AA9\u0011%\t)\bAA\u0001\n\u0003\n9\bC\u0005\u0002\u0006\u0002\t\t\u0011\"\u0001\u0002\b\"I\u0011\u0011\u0012\u0001\u0002\u0002\u0013\u0005\u00111\u0012\u0005\n\u0003/\u0003\u0011\u0011!C!\u00033C\u0011\"a*\u0001\u0003\u0003%\t!!+\t\u0013\u0005M\u0006!!A\u0005B\u0005U\u0006\"CA]\u0001\u0005\u0005I\u0011IA^\u0011%\ti\fAA\u0001\n\u0003\ny\fC\u0005\u0002B\u0002\t\t\u0011\"\u0011\u0002D\u001eQ\u0011q\u0019\u001a\u0002\u0002#\u0005a'!3\u0007\u0013E\u0012\u0014\u0011!E\u0001m\u0005-\u0007bBA\u0007W\u0011\u0005\u00111\u001d\u0005\n\u0003{[\u0013\u0011!C#\u0003\u007fC\u0011\"!:,\u0003\u0003%\t)a:\t\u0013\u0005u8&!A\u0005\u0002\u0006}\b\"\u0003B\tW\u0005\u0005I\u0011\u0002B\n\u00055Q\u0015M^1CCR\u001c\u0007.\u00138g_*\u00111\u0007N\u0001\u0005U\u00064\u0018M\u0003\u00026m\u0005\u0019\u0011\r]5\u000b\u0005]B\u0014!C:ue\u0016\fW.\u001b8h\u0015\tI$(A\u0003ta\u0006\u00148N\u0003\u0002<y\u00051\u0011\r]1dQ\u0016T\u0011!P\u0001\u0004_J<7\u0003\u0002\u0001@\u000b\"\u0003\"\u0001Q\"\u000e\u0003\u0005S\u0011AQ\u0001\u0006g\u000e\fG.Y\u0005\u0003\t\u0006\u0013a!\u00118z%\u00164\u0007C\u0001!G\u0013\t9\u0015IA\u0004Qe>$Wo\u0019;\u0011\u0005%\u0013fB\u0001&Q\u001d\tYu*D\u0001M\u0015\tie*\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005\u0011\u0015BA)B\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u0015+\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005E\u000b\u0015!\u00032bi\u000eDG+[7f+\u00059\u0006C\u0001-Z\u001b\u00051\u0014B\u0001.7\u0005\u0011!\u0016.\\3\u0002\u0015\t\fGo\u00195US6,\u0007%A\ntiJ,\u0017-\\%e)>Le\u000e];u\u0013:4w.F\u0001_!\u0011y6-\u001a5\u000e\u0003\u0001T!!\u00192\u0002\tU$\u0018\u000e\u001c\u0006\u0002g%\u0011A\r\u0019\u0002\u0004\u001b\u0006\u0004\bC\u0001!g\u0013\t9\u0017IA\u0002J]R\u0004\"!\u001b6\u000e\u0003IJ!a\u001b\u001a\u0003')\u000bg/Y*ue\u0016\fW.\u00138qkRLeNZ8\u0002)M$(/Z1n\u0013\u0012$v.\u00138qkRLeNZ8!\u00039\u0019XOY7jgNLwN\u001c+j[\u0016,\u0012a\u001c\t\u0003\u0001BL!!]!\u0003\t1{gnZ\u0001\u0010gV\u0014W.[:tS>tG+[7fA\u0005\u0019\u0002O]8dKN\u001c\u0018N\\4Ti\u0006\u0014H\u000fV5nK\u0006!\u0002O]8dKN\u001c\u0018N\\4Ti\u0006\u0014H\u000fV5nK\u0002\n\u0011\u0003\u001d:pG\u0016\u001c8/\u001b8h\u000b:$G+[7f\u0003I\u0001(o\\2fgNLgnZ#oIRKW.\u001a\u0011\u0002\u001fM\u001c\u0007.\u001a3vY&tw\rR3mCf\f\u0001c]2iK\u0012,H.\u001b8h\t\u0016d\u0017-\u001f\u0011\u0002\u001fA\u0014xnY3tg&tw\rR3mCf\f\u0001\u0003\u001d:pG\u0016\u001c8/\u001b8h\t\u0016d\u0017-\u001f\u0011\u0002\u0015Q|G/\u00197EK2\f\u00170A\u0006u_R\fG\u000eR3mCf\u0004\u0013A\u00038v[J+7m\u001c:eg\u0006Ya.^7SK\u000e|'\u000fZ:!\u0003QyW\u000f\u001e9vi>\u0003XM]1uS>t\u0017J\u001c4pgV\u0011\u00111\u0001\t\u0006?\u000e,\u0017Q\u0001\t\u0004S\u0006\u001d\u0011bAA\u0005e\t9\"*\u0019<b\u001fV$\b/\u001e;Pa\u0016\u0014\u0018\r^5p]&sgm\\\u0001\u0016_V$\b/\u001e;Pa\u0016\u0014\u0018\r^5p]&sgm\\:!\u0003\u0019a\u0014N\\5u}Q1\u0012\u0011CA\n\u0003+\t9\"!\u0007\u0002\u001c\u0005u\u0011qDA\u0011\u0003G\t)\u0003\u0005\u0002j\u0001!)Q+\u0006a\u0001/\")A,\u0006a\u0001=\")Q.\u0006a\u0001_\")1/\u0006a\u0001_\")Q/\u0006a\u0001_\")q/\u0006a\u0001_\")\u00110\u0006a\u0001_\")10\u0006a\u0001_\")Q0\u0006a\u0001_\"1q0\u0006a\u0001\u0003\u0007\tAaY8qsR1\u0012\u0011CA\u0016\u0003[\ty#!\r\u00024\u0005U\u0012qGA\u001d\u0003w\ti\u0004C\u0004V-A\u0005\t\u0019A,\t\u000fq3\u0002\u0013!a\u0001=\"9QN\u0006I\u0001\u0002\u0004y\u0007bB:\u0017!\u0003\u0005\ra\u001c\u0005\bkZ\u0001\n\u00111\u0001p\u0011\u001d9h\u0003%AA\u0002=Dq!\u001f\f\u0011\u0002\u0003\u0007q\u000eC\u0004|-A\u0005\t\u0019A8\t\u000fu4\u0002\u0013!a\u0001_\"AqP\u0006I\u0001\u0002\u0004\t\u0019!\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\u0005\r#fA,\u0002F-\u0012\u0011q\t\t\u0005\u0003\u0013\n\u0019&\u0004\u0002\u0002L)!\u0011QJA(\u0003%)hn\u00195fG.,GMC\u0002\u0002R\u0005\u000b!\"\u00198o_R\fG/[8o\u0013\u0011\t)&a\u0013\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0005\u0005m#f\u00010\u0002F\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\u001aTCAA1U\ry\u0017QI\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00135\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIU\nabY8qs\u0012\"WMZ1vYR$c'\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001c\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%q\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012J\u0014aD2paf$C-\u001a4bk2$H%\r\u0019\u0016\u0005\u0005M$\u0006BA\u0002\u0003\u000b\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAA=!\u0011\tY(!!\u000e\u0005\u0005u$bAA@E\u0006!A.\u00198h\u0013\u0011\t\u0019)! \u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005)\u0017A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0003\u001b\u000b\u0019\nE\u0002A\u0003\u001fK1!!%B\u0005\r\te.\u001f\u0005\t\u0003+\u001b\u0013\u0011!a\u0001K\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!a'\u0011\r\u0005u\u00151UAG\u001b\t\tyJC\u0002\u0002\"\u0006\u000b!bY8mY\u0016\u001cG/[8o\u0013\u0011\t)+a(\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003W\u000b\t\fE\u0002A\u0003[K1!a,B\u0005\u001d\u0011un\u001c7fC:D\u0011\"!&&\u0003\u0003\u0005\r!!$\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003s\n9\f\u0003\u0005\u0002\u0016\u001a\n\t\u00111\u0001f\u0003!A\u0017m\u001d5D_\u0012,G#A3\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!!\u001f\u0002\r\u0015\fX/\u00197t)\u0011\tY+!2\t\u0013\u0005U\u0015&!AA\u0002\u00055\u0015!\u0004&bm\u0006\u0014\u0015\r^2i\u0013:4w\u000e\u0005\u0002jWM)1&!4\u0002ZB\t\u0012qZAk/z{wn\\8p_>\f\u0019!!\u0005\u000e\u0005\u0005E'bAAj\u0003\u00069!/\u001e8uS6,\u0017\u0002BAl\u0003#\u0014!#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c82aA!\u00111\\Aq\u001b\t\tiNC\u0002\u0002`\n\f!![8\n\u0007M\u000bi\u000e\u0006\u0002\u0002J\u0006)\u0011\r\u001d9msR1\u0012\u0011CAu\u0003W\fi/a<\u0002r\u0006M\u0018Q_A|\u0003s\fY\u0010C\u0003V]\u0001\u0007q\u000bC\u0003]]\u0001\u0007a\fC\u0003n]\u0001\u0007q\u000eC\u0003t]\u0001\u0007q\u000eC\u0003v]\u0001\u0007q\u000eC\u0003x]\u0001\u0007q\u000eC\u0003z]\u0001\u0007q\u000eC\u0003|]\u0001\u0007q\u000eC\u0003~]\u0001\u0007q\u000e\u0003\u0004\u0000]\u0001\u0007\u00111A\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\u0011\tA!\u0004\u0011\u000b\u0001\u0013\u0019Aa\u0002\n\u0007\t\u0015\u0011I\u0001\u0004PaRLwN\u001c\t\u000f\u0001\n%qKX8p_>|wn\\A\u0002\u0013\r\u0011Y!\u0011\u0002\b)V\u0004H.Z\u00191\u0011%\u0011yaLA\u0001\u0002\u0004\t\t\"A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"A!\u0006\u0011\t\u0005m$qC\u0005\u0005\u00053\tiH\u0001\u0004PE*,7\r\u001e"
)
public class JavaBatchInfo implements Product, Serializable {
   private final Time batchTime;
   private final Map streamIdToInputInfo;
   private final long submissionTime;
   private final long processingStartTime;
   private final long processingEndTime;
   private final long schedulingDelay;
   private final long processingDelay;
   private final long totalDelay;
   private final long numRecords;
   private final Map outputOperationInfos;

   public static Option unapply(final JavaBatchInfo x$0) {
      return JavaBatchInfo$.MODULE$.unapply(x$0);
   }

   public static JavaBatchInfo apply(final Time batchTime, final Map streamIdToInputInfo, final long submissionTime, final long processingStartTime, final long processingEndTime, final long schedulingDelay, final long processingDelay, final long totalDelay, final long numRecords, final Map outputOperationInfos) {
      return JavaBatchInfo$.MODULE$.apply(batchTime, streamIdToInputInfo, submissionTime, processingStartTime, processingEndTime, schedulingDelay, processingDelay, totalDelay, numRecords, outputOperationInfos);
   }

   public static Function1 tupled() {
      return JavaBatchInfo$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return JavaBatchInfo$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Time batchTime() {
      return this.batchTime;
   }

   public Map streamIdToInputInfo() {
      return this.streamIdToInputInfo;
   }

   public long submissionTime() {
      return this.submissionTime;
   }

   public long processingStartTime() {
      return this.processingStartTime;
   }

   public long processingEndTime() {
      return this.processingEndTime;
   }

   public long schedulingDelay() {
      return this.schedulingDelay;
   }

   public long processingDelay() {
      return this.processingDelay;
   }

   public long totalDelay() {
      return this.totalDelay;
   }

   public long numRecords() {
      return this.numRecords;
   }

   public Map outputOperationInfos() {
      return this.outputOperationInfos;
   }

   public JavaBatchInfo copy(final Time batchTime, final Map streamIdToInputInfo, final long submissionTime, final long processingStartTime, final long processingEndTime, final long schedulingDelay, final long processingDelay, final long totalDelay, final long numRecords, final Map outputOperationInfos) {
      return new JavaBatchInfo(batchTime, streamIdToInputInfo, submissionTime, processingStartTime, processingEndTime, schedulingDelay, processingDelay, totalDelay, numRecords, outputOperationInfos);
   }

   public Time copy$default$1() {
      return this.batchTime();
   }

   public Map copy$default$10() {
      return this.outputOperationInfos();
   }

   public Map copy$default$2() {
      return this.streamIdToInputInfo();
   }

   public long copy$default$3() {
      return this.submissionTime();
   }

   public long copy$default$4() {
      return this.processingStartTime();
   }

   public long copy$default$5() {
      return this.processingEndTime();
   }

   public long copy$default$6() {
      return this.schedulingDelay();
   }

   public long copy$default$7() {
      return this.processingDelay();
   }

   public long copy$default$8() {
      return this.totalDelay();
   }

   public long copy$default$9() {
      return this.numRecords();
   }

   public String productPrefix() {
      return "JavaBatchInfo";
   }

   public int productArity() {
      return 10;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.batchTime();
         }
         case 1 -> {
            return this.streamIdToInputInfo();
         }
         case 2 -> {
            return BoxesRunTime.boxToLong(this.submissionTime());
         }
         case 3 -> {
            return BoxesRunTime.boxToLong(this.processingStartTime());
         }
         case 4 -> {
            return BoxesRunTime.boxToLong(this.processingEndTime());
         }
         case 5 -> {
            return BoxesRunTime.boxToLong(this.schedulingDelay());
         }
         case 6 -> {
            return BoxesRunTime.boxToLong(this.processingDelay());
         }
         case 7 -> {
            return BoxesRunTime.boxToLong(this.totalDelay());
         }
         case 8 -> {
            return BoxesRunTime.boxToLong(this.numRecords());
         }
         case 9 -> {
            return this.outputOperationInfos();
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
      return x$1 instanceof JavaBatchInfo;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "batchTime";
         }
         case 1 -> {
            return "streamIdToInputInfo";
         }
         case 2 -> {
            return "submissionTime";
         }
         case 3 -> {
            return "processingStartTime";
         }
         case 4 -> {
            return "processingEndTime";
         }
         case 5 -> {
            return "schedulingDelay";
         }
         case 6 -> {
            return "processingDelay";
         }
         case 7 -> {
            return "totalDelay";
         }
         case 8 -> {
            return "numRecords";
         }
         case 9 -> {
            return "outputOperationInfos";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.batchTime()));
      var1 = Statics.mix(var1, Statics.anyHash(this.streamIdToInputInfo()));
      var1 = Statics.mix(var1, Statics.longHash(this.submissionTime()));
      var1 = Statics.mix(var1, Statics.longHash(this.processingStartTime()));
      var1 = Statics.mix(var1, Statics.longHash(this.processingEndTime()));
      var1 = Statics.mix(var1, Statics.longHash(this.schedulingDelay()));
      var1 = Statics.mix(var1, Statics.longHash(this.processingDelay()));
      var1 = Statics.mix(var1, Statics.longHash(this.totalDelay()));
      var1 = Statics.mix(var1, Statics.longHash(this.numRecords()));
      var1 = Statics.mix(var1, Statics.anyHash(this.outputOperationInfos()));
      return Statics.finalizeHash(var1, 10);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10;
      if (this != x$1) {
         label91: {
            if (x$1 instanceof JavaBatchInfo) {
               JavaBatchInfo var4 = (JavaBatchInfo)x$1;
               if (this.submissionTime() == var4.submissionTime() && this.processingStartTime() == var4.processingStartTime() && this.processingEndTime() == var4.processingEndTime() && this.schedulingDelay() == var4.schedulingDelay() && this.processingDelay() == var4.processingDelay() && this.totalDelay() == var4.totalDelay() && this.numRecords() == var4.numRecords()) {
                  label84: {
                     Time var10000 = this.batchTime();
                     Time var5 = var4.batchTime();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label84;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label84;
                     }

                     Map var8 = this.streamIdToInputInfo();
                     Map var6 = var4.streamIdToInputInfo();
                     if (var8 == null) {
                        if (var6 != null) {
                           break label84;
                        }
                     } else if (!var8.equals(var6)) {
                        break label84;
                     }

                     var8 = this.outputOperationInfos();
                     Map var7 = var4.outputOperationInfos();
                     if (var8 == null) {
                        if (var7 != null) {
                           break label84;
                        }
                     } else if (!var8.equals(var7)) {
                        break label84;
                     }

                     if (var4.canEqual(this)) {
                        break label91;
                     }
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

   public JavaBatchInfo(final Time batchTime, final Map streamIdToInputInfo, final long submissionTime, final long processingStartTime, final long processingEndTime, final long schedulingDelay, final long processingDelay, final long totalDelay, final long numRecords, final Map outputOperationInfos) {
      this.batchTime = batchTime;
      this.streamIdToInputInfo = streamIdToInputInfo;
      this.submissionTime = submissionTime;
      this.processingStartTime = processingStartTime;
      this.processingEndTime = processingEndTime;
      this.schedulingDelay = schedulingDelay;
      this.processingDelay = processingDelay;
      this.totalDelay = totalDelay;
      this.numRecords = numRecords;
      this.outputOperationInfos = outputOperationInfos;
      Product.$init$(this);
   }
}
