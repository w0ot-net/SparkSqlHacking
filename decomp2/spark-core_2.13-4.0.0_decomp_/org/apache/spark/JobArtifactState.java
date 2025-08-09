package org.apache.spark;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005uc!B\r\u001b\u0001j\u0001\u0003\u0002C\u001c\u0001\u0005+\u0007I\u0011\u0001\u001d\t\u0011\u0005\u0003!\u0011#Q\u0001\neB\u0001B\u0011\u0001\u0003\u0016\u0004%\ta\u0011\u0005\t\u000f\u0002\u0011\t\u0012)A\u0005\t\")\u0001\n\u0001C\u0001\u0013\"9a\nAA\u0001\n\u0003y\u0005b\u0002*\u0001#\u0003%\ta\u0015\u0005\b=\u0002\t\n\u0011\"\u0001`\u0011\u001d\t\u0007!!A\u0005B\tDqA\u001b\u0001\u0002\u0002\u0013\u00051\u000eC\u0004p\u0001\u0005\u0005I\u0011\u00019\t\u000fY\u0004\u0011\u0011!C!o\"9a\u0010AA\u0001\n\u0003y\b\"CA\u0005\u0001\u0005\u0005I\u0011IA\u0006\u0011%\ty\u0001AA\u0001\n\u0003\n\t\u0002C\u0005\u0002\u0014\u0001\t\t\u0011\"\u0011\u0002\u0016!I\u0011q\u0003\u0001\u0002\u0002\u0013\u0005\u0013\u0011D\u0004\u000b\u0003;Q\u0012\u0011!E\u00015\u0005}a!C\r\u001b\u0003\u0003E\tAGA\u0011\u0011\u0019A5\u0003\"\u0001\u0002:!I\u00111C\n\u0002\u0002\u0013\u0015\u0013Q\u0003\u0005\n\u0003w\u0019\u0012\u0011!CA\u0003{A\u0011\"a\u0011\u0014\u0003\u0003%\t)!\u0012\t\u0013\u0005M3#!A\u0005\n\u0005U#\u0001\u0005&pE\u0006\u0013H/\u001b4bGR\u001cF/\u0019;f\u0015\tYB$A\u0003ta\u0006\u00148N\u0003\u0002\u001e=\u00051\u0011\r]1dQ\u0016T\u0011aH\u0001\u0004_J<7\u0003\u0002\u0001\"O)\u0002\"AI\u0013\u000e\u0003\rR\u0011\u0001J\u0001\u0006g\u000e\fG.Y\u0005\u0003M\r\u0012a!\u00118z%\u00164\u0007C\u0001\u0012)\u0013\tI3EA\u0004Qe>$Wo\u0019;\u0011\u0005-\"dB\u0001\u00173\u001d\ti\u0013'D\u0001/\u0015\ty\u0003'\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005!\u0013BA\u001a$\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u000e\u001c\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005M\u001a\u0013\u0001B;vS\u0012,\u0012!\u000f\t\u0003uyr!a\u000f\u001f\u0011\u00055\u001a\u0013BA\u001f$\u0003\u0019\u0001&/\u001a3fM&\u0011q\b\u0011\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005u\u001a\u0013!B;vS\u0012\u0004\u0013a\u0004:fa2\u001cE.Y:t\t&\u0014XK]5\u0016\u0003\u0011\u00032AI#:\u0013\t15E\u0001\u0004PaRLwN\\\u0001\u0011e\u0016\u0004Hn\u00117bgN$\u0015N]+sS\u0002\na\u0001P5oSRtDc\u0001&M\u001bB\u00111\nA\u0007\u00025!)q'\u0002a\u0001s!)!)\u0002a\u0001\t\u0006!1m\u001c9z)\rQ\u0005+\u0015\u0005\bo\u0019\u0001\n\u00111\u0001:\u0011\u001d\u0011e\u0001%AA\u0002\u0011\u000babY8qs\u0012\"WMZ1vYR$\u0013'F\u0001UU\tITkK\u0001W!\t9F,D\u0001Y\u0015\tI&,A\u0005v]\u000eDWmY6fI*\u00111lI\u0001\u000bC:tw\u000e^1uS>t\u0017BA/Y\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\u0005\u0001'F\u0001#V\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\t1\r\u0005\u0002eS6\tQM\u0003\u0002gO\u0006!A.\u00198h\u0015\u0005A\u0017\u0001\u00026bm\u0006L!aP3\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u00031\u0004\"AI7\n\u00059\u001c#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HCA9u!\t\u0011#/\u0003\u0002tG\t\u0019\u0011I\\=\t\u000fU\\\u0011\u0011!a\u0001Y\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012\u0001\u001f\t\u0004sr\fX\"\u0001>\u000b\u0005m\u001c\u0013AC2pY2,7\r^5p]&\u0011QP\u001f\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002\u0002\u0005\u001d\u0001c\u0001\u0012\u0002\u0004%\u0019\u0011QA\u0012\u0003\u000f\t{w\u000e\\3b]\"9Q/DA\u0001\u0002\u0004\t\u0018A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$2aYA\u0007\u0011\u001d)h\"!AA\u00021\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002Y\u0006AAo\\*ue&tw\rF\u0001d\u0003\u0019)\u0017/^1mgR!\u0011\u0011AA\u000e\u0011\u001d)\u0018#!AA\u0002E\f\u0001CS8c\u0003J$\u0018NZ1diN#\u0018\r^3\u0011\u0005-\u001b2#B\n\u0002$\u0005=\u0002cBA\u0013\u0003WIDIS\u0007\u0003\u0003OQ1!!\u000b$\u0003\u001d\u0011XO\u001c;j[\u0016LA!!\f\u0002(\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\u001c\u001a\u0011\t\u0005E\u0012qG\u0007\u0003\u0003gQ1!!\u000eh\u0003\tIw.C\u00026\u0003g!\"!a\b\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000b)\u000by$!\u0011\t\u000b]2\u0002\u0019A\u001d\t\u000b\t3\u0002\u0019\u0001#\u0002\u000fUt\u0017\r\u001d9msR!\u0011qIA(!\u0011\u0011S)!\u0013\u0011\u000b\t\nY%\u000f#\n\u0007\u000553E\u0001\u0004UkBdWM\r\u0005\t\u0003#:\u0012\u0011!a\u0001\u0015\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005]\u0003c\u00013\u0002Z%\u0019\u00111L3\u0003\r=\u0013'.Z2u\u0001"
)
public class JobArtifactState implements Product, Serializable {
   private final String uuid;
   private final Option replClassDirUri;

   public static Option unapply(final JobArtifactState x$0) {
      return JobArtifactState$.MODULE$.unapply(x$0);
   }

   public static JobArtifactState apply(final String uuid, final Option replClassDirUri) {
      return JobArtifactState$.MODULE$.apply(uuid, replClassDirUri);
   }

   public static Function1 tupled() {
      return JobArtifactState$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return JobArtifactState$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String uuid() {
      return this.uuid;
   }

   public Option replClassDirUri() {
      return this.replClassDirUri;
   }

   public JobArtifactState copy(final String uuid, final Option replClassDirUri) {
      return new JobArtifactState(uuid, replClassDirUri);
   }

   public String copy$default$1() {
      return this.uuid();
   }

   public Option copy$default$2() {
      return this.replClassDirUri();
   }

   public String productPrefix() {
      return "JobArtifactState";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.uuid();
         }
         case 1 -> {
            return this.replClassDirUri();
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
      return x$1 instanceof JobArtifactState;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "uuid";
         }
         case 1 -> {
            return "replClassDirUri";
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
            if (x$1 instanceof JobArtifactState) {
               label48: {
                  JobArtifactState var4 = (JobArtifactState)x$1;
                  String var10000 = this.uuid();
                  String var5 = var4.uuid();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  Option var7 = this.replClassDirUri();
                  Option var6 = var4.replClassDirUri();
                  if (var7 == null) {
                     if (var6 != null) {
                        break label48;
                     }
                  } else if (!var7.equals(var6)) {
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

   public JobArtifactState(final String uuid, final Option replClassDirUri) {
      this.uuid = uuid;
      this.replClassDirUri = replClassDirUri;
      Product.$init$(this);
   }
}
