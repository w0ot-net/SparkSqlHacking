package org.apache.spark;

import java.io.Serializable;
import java.util.Objects;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005c!\u0002\u000e\u001c\u0001m\t\u0003\u0002\u0003\u0019\u0001\u0005\u000b\u0007I\u0011\u0001\u001a\t\u0011i\u0002!\u0011!Q\u0001\nMB\u0001b\u000f\u0001\u0003\u0006\u0004%\t\u0001\u0010\u0005\t\u001d\u0002\u0011\t\u0011)A\u0005{!Aq\n\u0001BC\u0002\u0013\u0005A\b\u0003\u0005Q\u0001\t\u0005\t\u0015!\u0003>\u0011!\t\u0006A!b\u0001\n\u0003a\u0004\u0002\u0003*\u0001\u0005\u0003\u0005\u000b\u0011B\u001f\t\u000bM\u0003A\u0011\u0001+\t\u000bi\u0003A\u0011I.\t\u000b}\u0003A\u0011\t1\b\r%\\\u0002\u0012A\u000ek\r\u0019Q2\u0004#\u0001\u001cW\")1+\u0004C\u0001Y\"9Q.\u0004b\u0001\n\u0003q\u0007BB8\u000eA\u0003%Q\u000bC\u0003q\u001b\u0011\u0005a\u000eC\u0004r\u001b\u0001\u0007I\u0011\u0001\u001a\t\u000fIl\u0001\u0019!C\u0001g\"1\u00110\u0004Q!\nMBaA_\u0007!\u0002\u0013Y\bBBA\u0002\u001b\u0011\u0005!\u0007C\u0004\u0002\u00065!\t!a\u0002\t\u000f\u0005%R\u0002\"\u0001\u0002,!I\u0011qG\u0007\u0002\u0002\u0013%\u0011\u0011\b\u0002\u000f\u0015>\u0014\u0017I\u001d;jM\u0006\u001cGoU3u\u0015\taR$A\u0003ta\u0006\u00148N\u0003\u0002\u001f?\u00051\u0011\r]1dQ\u0016T\u0011\u0001I\u0001\u0004_J<7c\u0001\u0001#QA\u00111EJ\u0007\u0002I)\tQ%A\u0003tG\u0006d\u0017-\u0003\u0002(I\t1\u0011I\\=SK\u001a\u0004\"!\u000b\u0018\u000e\u0003)R!a\u000b\u0017\u0002\u0005%|'\"A\u0017\u0002\t)\fg/Y\u0005\u0003_)\u0012AbU3sS\u0006d\u0017N_1cY\u0016\fQa\u001d;bi\u0016\u001c\u0001!F\u00014!\r\u0019CGN\u0005\u0003k\u0011\u0012aa\u00149uS>t\u0007CA\u001c9\u001b\u0005Y\u0012BA\u001d\u001c\u0005AQuNY!si&4\u0017m\u0019;Ti\u0006$X-\u0001\u0004ti\u0006$X\rI\u0001\u0005U\u0006\u00148/F\u0001>!\u0011qT\tS&\u000f\u0005}\u001a\u0005C\u0001!%\u001b\u0005\t%B\u0001\"2\u0003\u0019a$o\\8u}%\u0011A\tJ\u0001\u0007!J,G-\u001a4\n\u0005\u0019;%aA'ba*\u0011A\t\n\t\u0003}%K!AS$\u0003\rM#(/\u001b8h!\t\u0019C*\u0003\u0002NI\t!Aj\u001c8h\u0003\u0015Q\u0017M]:!\u0003\u00151\u0017\u000e\\3t\u0003\u00191\u0017\u000e\\3tA\u0005A\u0011M]2iSZ,7/A\u0005be\u000eD\u0017N^3tA\u00051A(\u001b8jiz\"R!\u0016,X1f\u0003\"a\u000e\u0001\t\u000bAJ\u0001\u0019A\u001a\t\u000bmJ\u0001\u0019A\u001f\t\u000b=K\u0001\u0019A\u001f\t\u000bEK\u0001\u0019A\u001f\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012\u0001\u0018\t\u0003GuK!A\u0018\u0013\u0003\u0007%sG/\u0001\u0004fcV\fGn\u001d\u000b\u0003C\u0012\u0004\"a\t2\n\u0005\r$#a\u0002\"p_2,\u0017M\u001c\u0005\u0006K.\u0001\rAZ\u0001\u0004_\nT\u0007CA\u0012h\u0013\tAGEA\u0002B]f\faBS8c\u0003J$\u0018NZ1diN+G\u000f\u0005\u00028\u001bM\u0019QB\t\u0015\u0015\u0003)\f1#Z7qifTuNY!si&4\u0017m\u0019;TKR,\u0012!V\u0001\u0015K6\u0004H/\u001f&pE\u0006\u0013H/\u001b4bGR\u001cV\r\u001e\u0011\u0002+\u0011,g-Y;mi*{'-\u0011:uS\u001a\f7\r^*fi\u0006iA.Y:u'\u0016,gn\u0015;bi\u0016\f\u0011\u0003\\1tiN+WM\\*uCR,w\fJ3r)\t!x\u000f\u0005\u0002$k&\u0011a\u000f\n\u0002\u0005+:LG\u000fC\u0004y'\u0005\u0005\t\u0019A\u001a\u0002\u0007a$\u0013'\u0001\bmCN$8+Z3o'R\fG/\u001a\u0011\u00023\r,(O]3oi\u000ec\u0017.\u001a8u'\u0016\u001c8/[8o'R\fG/\u001a\t\u0004y~\u001cT\"A?\u000b\u0005yd\u0013\u0001\u00027b]\u001eL1!!\u0001~\u0005-!\u0006N]3bI2{7-\u00197\u00025\u001d,GoQ;se\u0016tGOS8c\u0003J$\u0018NZ1diN#\u0018\r^3\u00025]LG\u000f[!di&4XMS8c\u0003J$\u0018NZ1diN#\u0018\r^3\u0016\t\u0005%\u0011\u0011\u0003\u000b\u0005\u0003\u0017\t9\u0003\u0006\u0003\u0002\u000e\u0005u\u0001\u0003BA\b\u0003#a\u0001\u0001B\u0004\u0002\u0014]\u0011\r!!\u0006\u0003\u0003Q\u000b2!a\u0006g!\r\u0019\u0013\u0011D\u0005\u0004\u00037!#a\u0002(pi\"Lgn\u001a\u0005\t\u0003?9B\u00111\u0001\u0002\"\u0005)!\r\\8dWB)1%a\t\u0002\u000e%\u0019\u0011Q\u0005\u0013\u0003\u0011q\u0012\u0017P\\1nKzBQ\u0001M\fA\u0002Y\n!cZ3u\u0003\u000e$\u0018N^3Pe\u0012+g-Y;miR\u0019Q+!\f\t\u000f\u0005=\u0002\u00041\u0001\u00022\u0005\u00111o\u0019\t\u0004o\u0005M\u0012bAA\u001b7\ta1\u000b]1sW\u000e{g\u000e^3yi\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\b\t\u0004y\u0006u\u0012bAA {\n1qJ\u00196fGR\u0004"
)
public class JobArtifactSet implements Serializable {
   private final Option state;
   private final Map jars;
   private final Map files;
   private final Map archives;

   public static JobArtifactSet getActiveOrDefault(final SparkContext sc) {
      return JobArtifactSet$.MODULE$.getActiveOrDefault(sc);
   }

   public static Object withActiveJobArtifactState(final JobArtifactState state, final Function0 block) {
      return JobArtifactSet$.MODULE$.withActiveJobArtifactState(state, block);
   }

   public static Option getCurrentJobArtifactState() {
      return JobArtifactSet$.MODULE$.getCurrentJobArtifactState();
   }

   public static void lastSeenState_$eq(final Option x$1) {
      JobArtifactSet$.MODULE$.lastSeenState_$eq(x$1);
   }

   public static Option lastSeenState() {
      return JobArtifactSet$.MODULE$.lastSeenState();
   }

   public static JobArtifactSet defaultJobArtifactSet() {
      return JobArtifactSet$.MODULE$.defaultJobArtifactSet();
   }

   public static JobArtifactSet emptyJobArtifactSet() {
      return JobArtifactSet$.MODULE$.emptyJobArtifactSet();
   }

   public Option state() {
      return this.state;
   }

   public Map jars() {
      return this.jars;
   }

   public Map files() {
      return this.files;
   }

   public Map archives() {
      return this.archives;
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.state(), this.jars().toSeq(), this.files().toSeq(), this.archives().toSeq()});
   }

   public boolean equals(final Object obj) {
      if (!(obj instanceof JobArtifactSet var4)) {
         throw new MatchError(obj);
      } else {
         boolean var14;
         label64: {
            label58: {
               Class var10000 = this.getClass();
               Class var5 = var4.getClass();
               if (var10000 == null) {
                  if (var5 != null) {
                     break label58;
                  }
               } else if (!var10000.equals(var5)) {
                  break label58;
               }

               Option var10 = this.state();
               Option var6 = var4.state();
               if (var10 == null) {
                  if (var6 != null) {
                     break label58;
                  }
               } else if (!var10.equals(var6)) {
                  break label58;
               }

               Seq var11 = this.jars().toSeq();
               Seq var7 = var4.jars().toSeq();
               if (var11 == null) {
                  if (var7 != null) {
                     break label58;
                  }
               } else if (!var11.equals(var7)) {
                  break label58;
               }

               var11 = this.files().toSeq();
               Seq var8 = var4.files().toSeq();
               if (var11 == null) {
                  if (var8 != null) {
                     break label58;
                  }
               } else if (!var11.equals(var8)) {
                  break label58;
               }

               var11 = this.archives().toSeq();
               Seq var9 = var4.archives().toSeq();
               if (var11 == null) {
                  if (var9 == null) {
                     break label64;
                  }
               } else if (var11.equals(var9)) {
                  break label64;
               }
            }

            var14 = false;
            return var14;
         }

         var14 = true;
         return var14;
      }
   }

   public JobArtifactSet(final Option state, final Map jars, final Map files, final Map archives) {
      this.state = state;
      this.jars = jars;
      this.files = files;
      this.archives = archives;
   }
}
