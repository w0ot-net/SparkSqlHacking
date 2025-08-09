package org.apache.spark.rdd;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.internal.Logging;
import org.sparkproject.guava.base.Objects;
import scala.Option;
import scala.StringContext;
import scala.collection.IterableOps;
import scala.collection.immutable.Seq;
import scala.package.;
import scala.reflect.ScalaSignature;

@JsonInclude(Include.NON_ABSENT)
@JsonPropertyOrder({"id", "name", "parent"})
@ScalaSignature(
   bytes = "\u0006\u0005\u00055g!B\u000e\u001d\u0001y!\u0003\u0002C\u0016\u0001\u0005\u000b\u0007I\u0011A\u0017\t\u0011e\u0002!\u0011!Q\u0001\n9B\u0001B\u000f\u0001\u0003\u0006\u0004%\ta\u000f\u0005\t\u0003\u0002\u0011\t\u0011)A\u0005y!A!\t\u0001BC\u0002\u0013\u0005Q\u0006\u0003\u0005D\u0001\t\u0005\t\u0015!\u0003/\u0011\u0015!\u0005\u0001\"\u0001F\u0011\u0015I\u0005\u0001\"\u0001.\u0011\u0015Q\u0005\u0001\"\u0001L\u0011\u0015\u0011\u0007\u0001\"\u0011d\u0011\u0015a\u0007\u0001\"\u0011n\u0011\u0015\t\b\u0001\"\u0011s\u000f!\ty\u0001\bE\u0001=\u0005EaaB\u000e\u001d\u0011\u0003q\u00121\u0003\u0005\u0007\t:!\t!!\t\t\u0013\u0005\rbB1A\u0005\n\u0005\u0015\u0002\u0002CA\u001a\u001d\u0001\u0006I!a\n\t\u0013\u0005UbB1A\u0005\n\u0005]\u0002\u0002CA)\u001d\u0001\u0006I!!\u000f\t\u000f\u0005Mc\u0002\"\u0001\u0002V!1\u00111\f\b\u0005\u00025D\u0001\"!\u0018\u000f\t\u0003q\u0012q\f\u0005\u000b\u0003\u001fs\u0011\u0013!C\u0001=\u0005E\u0005\u0002CA/\u001d\u0011\u0005a$!+\t\u0013\u0005\u0005g\"%A\u0005\u0002\u0005\r\u0007\"CAd\u001dE\u0005I\u0011AAe\u0005E\u0011F\tR(qKJ\fG/[8o'\u000e|\u0007/\u001a\u0006\u0003;y\t1A\u001d3e\u0015\ty\u0002%A\u0003ta\u0006\u00148N\u0003\u0002\"E\u00051\u0011\r]1dQ\u0016T\u0011aI\u0001\u0004_J<7C\u0001\u0001&!\t1\u0013&D\u0001(\u0015\u0005A\u0013!B:dC2\f\u0017B\u0001\u0016(\u0005\u0019\te.\u001f*fM\u0006!a.Y7f\u0007\u0001)\u0012A\f\t\u0003_Yr!\u0001\r\u001b\u0011\u0005E:S\"\u0001\u001a\u000b\u0005Mb\u0013A\u0002\u001fs_>$h(\u0003\u00026O\u00051\u0001K]3eK\u001aL!a\u000e\u001d\u0003\rM#(/\u001b8h\u0015\t)t%A\u0003oC6,\u0007%\u0001\u0004qCJ,g\u000e^\u000b\u0002yA\u0019a%P \n\u0005y:#AB(qi&|g\u000e\u0005\u0002A\u00015\tA$A\u0004qCJ,g\u000e\u001e\u0011\u0002\u0005%$\u0017aA5eA\u00051A(\u001b8jiz\"Ba\u0010$H\u0011\")1f\u0002a\u0001]!9!h\u0002I\u0001\u0002\u0004a\u0004b\u0002\"\b!\u0003\u0005\rAL\u0001\u0007i>T5o\u001c8\u0002\u0019\u001d,G/\u00117m'\u000e|\u0007/Z:\u0016\u00031\u00032!\u0014*@\u001d\tq\u0005K\u0004\u00022\u001f&\t\u0001&\u0003\u0002RO\u00059\u0001/Y2lC\u001e,\u0017BA*U\u0005\r\u0019V-\u001d\u0006\u0003#\u001eB#!\u0003,\u0011\u0005]\u0003W\"\u0001-\u000b\u0005eS\u0016AC1o]>$\u0018\r^5p]*\u00111\fX\u0001\bU\u0006\u001c7n]8o\u0015\tif,A\u0005gCN$XM\u001d=nY*\tq,A\u0002d_6L!!\u0019-\u0003\u0015)\u001bxN\\%h]>\u0014X-\u0001\u0004fcV\fGn\u001d\u000b\u0003I\u001e\u0004\"AJ3\n\u0005\u0019<#a\u0002\"p_2,\u0017M\u001c\u0005\u0006Q*\u0001\r![\u0001\u0006_RDWM\u001d\t\u0003M)L!a[\u0014\u0003\u0007\u0005s\u00170\u0001\u0005iCND7i\u001c3f)\u0005q\u0007C\u0001\u0014p\u0013\t\u0001xEA\u0002J]R\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002]!\"\u0001\u0001^<y!\t9V/\u0003\u0002w1\nY!j]8o\u0013:\u001cG.\u001e3f\u0003\u00151\u0018\r\\;fI\u0005I\u0018B\u0001>|\u0003)quJT0B\u0005N+e\n\u0016\u0006\u0003yv\fq!\u00138dYV$WM\u0003\u0002\u007f1\u0006Y!j]8o\u0013:\u001cG.\u001e3fQ\u0019\u0001\u0011\u0011A<\u0002\bA\u0019q+a\u0001\n\u0007\u0005\u0015\u0001LA\tKg>t\u0007K]8qKJ$\u0018p\u0014:eKJdc!!\u0003\u0002\f\u00055\u0011%\u0001\"\"\u0003-\n\u0013AO\u0001\u0012%\u0012#u\n]3sCRLwN\\*d_B,\u0007C\u0001!\u000f'\u0011qQ%!\u0006\u0011\t\u0005]\u0011QD\u0007\u0003\u00033Q1!a\u0007\u001f\u0003!Ig\u000e^3s]\u0006d\u0017\u0002BA\u0010\u00033\u0011q\u0001T8hO&tw\r\u0006\u0002\u0002\u0012\u0005Q!n]8o\u001b\u0006\u0004\b/\u001a:\u0016\u0005\u0005\u001d\u0002\u0003BA\u0015\u0003_i!!a\u000b\u000b\u0007\u00055\",\u0001\u0005eCR\f'-\u001b8e\u0013\u0011\t\t$a\u000b\u0003\u0019=\u0013'.Z2u\u001b\u0006\u0004\b/\u001a:\u0002\u0017)\u001cxN\\'baB,'\u000fI\u0001\rg\u000e|\u0007/Z\"pk:$XM]\u000b\u0003\u0003s\u0001B!a\u000f\u0002N5\u0011\u0011Q\b\u0006\u0005\u0003\u007f\t\t%\u0001\u0004bi>l\u0017n\u0019\u0006\u0005\u0003\u0007\n)%\u0001\u0006d_:\u001cWO\u001d:f]RTA!a\u0012\u0002J\u0005!Q\u000f^5m\u0015\t\tY%\u0001\u0003kCZ\f\u0017\u0002BA(\u0003{\u0011Q\"\u0011;p[&\u001c\u0017J\u001c;fO\u0016\u0014\u0018!D:d_B,7i\\;oi\u0016\u0014\b%\u0001\u0005ge>l'j]8o)\ry\u0014q\u000b\u0005\u0007\u00033\"\u0002\u0019\u0001\u0018\u0002\u0003M\f1B\\3yiN\u001bw\u000e]3JI\u0006Iq/\u001b;i'\u000e|\u0007/Z\u000b\u0005\u0003C\nI\u0007\u0006\u0004\u0002d\u0005}\u00141\u0012\u000b\u0005\u0003K\n)\b\u0005\u0003\u0002h\u0005%D\u0002\u0001\u0003\b\u0003W2\"\u0019AA7\u0005\u0005!\u0016cAA8SB\u0019a%!\u001d\n\u0007\u0005MtEA\u0004O_RD\u0017N\\4\t\u0011\u0005]d\u0003\"a\u0001\u0003s\nAAY8esB)a%a\u001f\u0002f%\u0019\u0011QP\u0014\u0003\u0011q\u0012\u0017P\\1nKzBq!!!\u0017\u0001\u0004\t\u0019)\u0001\u0002tGB!\u0011QQAD\u001b\u0005q\u0012bAAE=\ta1\u000b]1sW\u000e{g\u000e^3yi\"A\u0011Q\u0012\f\u0011\u0002\u0003\u0007A-\u0001\u0007bY2|wOT3ti&tw-A\nxSRD7kY8qK\u0012\"WMZ1vYR$#'\u0006\u0003\u0002\u0014\u0006\u001dVCAAKU\r!\u0017qS\u0016\u0003\u00033\u0003B!a'\u0002$6\u0011\u0011Q\u0014\u0006\u0005\u0003?\u000b\t+A\u0005v]\u000eDWmY6fI*\u0011\u0011lJ\u0005\u0005\u0003K\u000biJA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016$q!a\u001b\u0018\u0005\u0004\ti'\u0006\u0003\u0002,\u0006EFCCAW\u0003o\u000bI,a/\u0002>R!\u0011qVAZ!\u0011\t9'!-\u0005\u000f\u0005-\u0004D1\u0001\u0002n!A\u0011q\u000f\r\u0005\u0002\u0004\t)\fE\u0003'\u0003w\ny\u000bC\u0004\u0002\u0002b\u0001\r!a!\t\u000b-B\u0002\u0019\u0001\u0018\t\r\u00055\u0005\u00041\u0001e\u0011\u0019\ty\f\u0007a\u0001I\u0006a\u0011n\u001a8pe\u0016\u0004\u0016M]3oi\u0006YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uII*\"!!2+\u0007q\n9*A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HeM\u000b\u0003\u0003\u0017T3ALAL\u0001"
)
public class RDDOperationScope {
   private final String name;
   private final Option parent;
   private final String id;

   public static String $lessinit$greater$default$3() {
      return RDDOperationScope$.MODULE$.$lessinit$greater$default$3();
   }

   public static Option $lessinit$greater$default$2() {
      return RDDOperationScope$.MODULE$.$lessinit$greater$default$2();
   }

   public static int nextScopeId() {
      return RDDOperationScope$.MODULE$.nextScopeId();
   }

   public static RDDOperationScope fromJson(final String s) {
      return RDDOperationScope$.MODULE$.fromJson(s);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return RDDOperationScope$.MODULE$.LogStringContext(sc);
   }

   public String name() {
      return this.name;
   }

   public Option parent() {
      return this.parent;
   }

   public String id() {
      return this.id;
   }

   public String toJson() {
      return RDDOperationScope$.MODULE$.org$apache$spark$rdd$RDDOperationScope$$jsonMapper().writeValueAsString(this);
   }

   @JsonIgnore
   public Seq getAllScopes() {
      return (Seq)((IterableOps)this.parent().map((x$1) -> x$1.getAllScopes()).getOrElse(() -> (Seq).MODULE$.Seq().empty())).$plus$plus(new scala.collection.immutable..colon.colon(this, scala.collection.immutable.Nil..MODULE$));
   }

   public boolean equals(final Object other) {
      if (!(other instanceof RDDOperationScope var4)) {
         return false;
      } else {
         boolean var10;
         label48: {
            label42: {
               String var10000 = this.id();
               String var5 = var4.id();
               if (var10000 == null) {
                  if (var5 != null) {
                     break label42;
                  }
               } else if (!var10000.equals(var5)) {
                  break label42;
               }

               var10000 = this.name();
               String var6 = var4.name();
               if (var10000 == null) {
                  if (var6 != null) {
                     break label42;
                  }
               } else if (!var10000.equals(var6)) {
                  break label42;
               }

               Option var9 = this.parent();
               Option var7 = var4.parent();
               if (var9 == null) {
                  if (var7 == null) {
                     break label48;
                  }
               } else if (var9.equals(var7)) {
                  break label48;
               }
            }

            var10 = false;
            return var10;
         }

         var10 = true;
         return var10;
      }
   }

   public int hashCode() {
      return Objects.hashCode(new Object[]{this.id(), this.name(), this.parent()});
   }

   public String toString() {
      return this.toJson();
   }

   public RDDOperationScope(final String name, final Option parent, final String id) {
      this.name = name;
      this.parent = parent;
      this.id = id;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
