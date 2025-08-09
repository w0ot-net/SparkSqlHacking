package org.apache.spark.scheduler;

import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.spark.annotation.DeveloperApi;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rc\u0001\u0002\u000b\u0016\u0001yA\u0001\"\n\u0001\u0003\u0006\u0004%\tA\n\u0005\to\u0001\u0011\t\u0011)A\u0005O!Aq\b\u0001BC\u0002\u0013\u0005\u0001\t\u0003\u0005E\u0001\t\u0005\t\u0015!\u0003B\u0011!)\u0005A!b\u0001\n\u0003\u0001\u0005\u0002\u0003$\u0001\u0005\u0003\u0005\u000b\u0011B!\t\u0011\u001d\u0003!Q1A\u0005\u0002!C\u0001\u0002\u0014\u0001\u0003\u0002\u0003\u0006I!\u0013\u0005\t\u001b\u0002\u0011)\u0019!C\u0001\u001d\"Aq\n\u0001B\u0001B\u0003%A\bC\u0003Q\u0001\u0011\u0005\u0011\u000bC\u0003^\u0001\u0011\u0005c\fC\u0003`\u0001\u0011\u0005\u0003\rC\u0003e\u0001\u0011\u0005SmB\u0003s+!\u00051OB\u0003\u0015+!\u0005A\u000fC\u0003Q!\u0011\u0005Q\u000fC\u0003w!\u0011\u0005q\u000f\u0003\u0004w!\u0011\u0005\u0011Q\u0005\u0002\n'Bd\u0017\u000e^%oM>T!AF\f\u0002\u0013M\u001c\u0007.\u001a3vY\u0016\u0014(B\u0001\r\u001a\u0003\u0015\u0019\b/\u0019:l\u0015\tQ2$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00029\u0005\u0019qN]4\u0004\u0001M\u0011\u0001a\b\t\u0003A\rj\u0011!\t\u0006\u0002E\u0005)1oY1mC&\u0011A%\t\u0002\u0007\u0003:L(+\u001a4\u0002!%t\u0007/\u001e;G_Jl\u0017\r^\"mCjTX#A\u00141\u0005!*\u0004cA\u00151g9\u0011!F\f\t\u0003W\u0005j\u0011\u0001\f\u0006\u0003[u\ta\u0001\u0010:p_Rt\u0014BA\u0018\"\u0003\u0019\u0001&/\u001a3fM&\u0011\u0011G\r\u0002\u0006\u00072\f7o\u001d\u0006\u0003_\u0005\u0002\"\u0001N\u001b\r\u0001\u0011IaGAA\u0001\u0002\u0003\u0015\t\u0001\u000f\u0002\u0004?\u0012\n\u0014!E5oaV$hi\u001c:nCR\u001cE.\u0019>{AE\u0011\u0011\b\u0010\t\u0003AiJ!aO\u0011\u0003\u000f9{G\u000f[5oOB\u0011\u0001%P\u0005\u0003}\u0005\u00121!\u00118z\u00031Awn\u001d;M_\u000e\fG/[8o+\u0005\t\u0005CA\u0015C\u0013\t\u0019%G\u0001\u0004TiJLgnZ\u0001\u000eQ>\u001cH\u000fT8dCRLwN\u001c\u0011\u0002\tA\fG\u000f[\u0001\u0006a\u0006$\b\u000eI\u0001\u0007Y\u0016tw\r\u001e5\u0016\u0003%\u0003\"\u0001\t&\n\u0005-\u000b#\u0001\u0002'p]\u001e\fq\u0001\\3oORD\u0007%A\bv]\u0012,'\u000f\\=j]\u001e\u001c\u0006\u000f\\5u+\u0005a\u0014\u0001E;oI\u0016\u0014H._5oON\u0003H.\u001b;!\u0003\u0019a\u0014N\\5u}Q1!\u000bV-[7r\u0003\"a\u0015\u0001\u000e\u0003UAQ!J\u0006A\u0002U\u0003$A\u0016-\u0011\u0007%\u0002t\u000b\u0005\u000251\u0012Ia\u0007VA\u0001\u0002\u0003\u0015\t\u0001\u000f\u0005\u0006\u007f-\u0001\r!\u0011\u0005\u0006\u000b.\u0001\r!\u0011\u0005\u0006\u000f.\u0001\r!\u0013\u0005\u0006\u001b.\u0001\r\u0001P\u0001\ti>\u001cFO]5oOR\t\u0011)\u0001\u0005iCND7i\u001c3f)\u0005\t\u0007C\u0001\u0011c\u0013\t\u0019\u0017EA\u0002J]R\fa!Z9vC2\u001cHC\u00014j!\t\u0001s-\u0003\u0002iC\t9!i\\8mK\u0006t\u0007\"\u00026\u000f\u0001\u0004a\u0014!B8uQ\u0016\u0014\bF\u0001\u0001m!\ti\u0007/D\u0001o\u0015\tyw#\u0001\u0006b]:|G/\u0019;j_:L!!\u001d8\u0003\u0019\u0011+g/\u001a7pa\u0016\u0014\u0018\t]5\u0002\u0013M\u0003H.\u001b;J]\u001a|\u0007CA*\u0011'\t\u0001r\u0004F\u0001t\u0003-!xn\u00159mSRLeNZ8\u0015\u000fa\f\u0019!a\u0004\u0002\u0012A\u0019\u0011P *\u000f\u0005idhBA\u0016|\u0013\u0005\u0011\u0013BA?\"\u0003\u001d\u0001\u0018mY6bO\u0016L1a`A\u0001\u0005\r\u0019V-\u001d\u0006\u0003{\u0006Ba!\n\nA\u0002\u0005\u0015\u0001\u0007BA\u0004\u0003\u0017\u0001B!\u000b\u0019\u0002\nA\u0019A'a\u0003\u0005\u0017\u00055\u00111AA\u0001\u0002\u0003\u0015\t\u0001\u000f\u0002\u0004?\u0012\u0012\u0004\"B#\u0013\u0001\u0004\t\u0005bBA\n%\u0001\u0007\u0011QC\u0001\f[\u0006\u0004(/\u001a3Ta2LG\u000f\u0005\u0003\u0002\u0018\u0005\u0005RBAA\r\u0015\u0011\tY\"!\b\u0002\r5\f\u0007O]3e\u0015\r\ty\"G\u0001\u0007Q\u0006$wn\u001c9\n\t\u0005\r\u0012\u0011\u0004\u0002\u000b\u0013:\u0004X\u000f^*qY&$Hc\u0002=\u0002(\u0005M\u0012Q\u0007\u0005\u0007KM\u0001\r!!\u000b1\t\u0005-\u0012q\u0006\t\u0005SA\ni\u0003E\u00025\u0003_!1\"!\r\u0002(\u0005\u0005\t\u0011!B\u0001q\t\u0019q\fJ\u001a\t\u000b\u0015\u001b\u0002\u0019A!\t\u000f\u0005]2\u00031\u0001\u0002:\u0005qQ.\u00199sK\u0012,8-Z*qY&$\b\u0003BA\u001e\u0003\u0003j!!!\u0010\u000b\t\u0005}\u0012QD\u0001\n[\u0006\u0004(/\u001a3vG\u0016LA!a\t\u0002>\u0001"
)
public class SplitInfo {
   private final Class inputFormatClazz;
   private final String hostLocation;
   private final String path;
   private final long length;
   private final Object underlyingSplit;

   public static Seq toSplitInfo(final Class inputFormatClazz, final String path, final InputSplit mapreduceSplit) {
      return SplitInfo$.MODULE$.toSplitInfo(inputFormatClazz, path, mapreduceSplit);
   }

   public static Seq toSplitInfo(final Class inputFormatClazz, final String path, final org.apache.hadoop.mapred.InputSplit mapredSplit) {
      return SplitInfo$.MODULE$.toSplitInfo(inputFormatClazz, path, mapredSplit);
   }

   public Class inputFormatClazz() {
      return this.inputFormatClazz;
   }

   public String hostLocation() {
      return this.hostLocation;
   }

   public String path() {
      return this.path;
   }

   public long length() {
      return this.length;
   }

   public Object underlyingSplit() {
      return this.underlyingSplit;
   }

   public String toString() {
      String var10000 = super.toString();
      return "SplitInfo " + var10000 + " .. inputFormatClazz " + this.inputFormatClazz() + ", hostLocation : " + this.hostLocation() + ", path : " + this.path() + ", length : " + this.length() + ", underlyingSplit " + this.underlyingSplit();
   }

   public int hashCode() {
      int hashCode = this.inputFormatClazz().hashCode();
      hashCode = hashCode * 31 + this.hostLocation().hashCode();
      hashCode = hashCode * 31 + this.path().hashCode();
      hashCode = hashCode * 31 + (int)(this.length() & 2147483647L);
      return hashCode;
   }

   public boolean equals(final Object other) {
      if (!(other instanceof SplitInfo var4)) {
         return false;
      } else {
         boolean var10;
         label47: {
            String var10000 = this.hostLocation();
            String var5 = var4.hostLocation();
            if (var10000 == null) {
               if (var5 != null) {
                  break label47;
               }
            } else if (!var10000.equals(var5)) {
               break label47;
            }

            Class var8 = this.inputFormatClazz();
            Class var6 = var4.inputFormatClazz();
            if (var8 == null) {
               if (var6 != null) {
                  break label47;
               }
            } else if (!var8.equals(var6)) {
               break label47;
            }

            String var9 = this.path();
            String var7 = var4.path();
            if (var9 == null) {
               if (var7 != null) {
                  break label47;
               }
            } else if (!var9.equals(var7)) {
               break label47;
            }

            if (this.length() == var4.length() && BoxesRunTime.equals(this.underlyingSplit(), var4.underlyingSplit())) {
               var10 = true;
               return var10;
            }
         }

         var10 = false;
         return var10;
      }
   }

   public SplitInfo(final Class inputFormatClazz, final String hostLocation, final String path, final long length, final Object underlyingSplit) {
      this.inputFormatClazz = inputFormatClazz;
      this.hostLocation = hostLocation;
      this.path = path;
      this.length = length;
      this.underlyingSplit = underlyingSplit;
   }
}
