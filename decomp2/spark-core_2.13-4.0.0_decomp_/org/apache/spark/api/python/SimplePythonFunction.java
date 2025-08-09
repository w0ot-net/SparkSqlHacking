package org.apache.spark.api.python;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.apache.spark.util.CollectionAccumulator;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u001dc!B\u0015+\u0001:\"\u0004\u0002C(\u0001\u0005+\u0007I\u0011\u0001)\t\u0011]\u0003!\u0011#Q\u0001\nEC\u0001\u0002\u0017\u0001\u0003\u0016\u0004%\t!\u0017\u0005\tU\u0002\u0011\t\u0012)A\u00055\"A1\u000e\u0001BK\u0002\u0013\u0005A\u000e\u0003\u0005q\u0001\tE\t\u0015!\u0003n\u0011!\t\bA!f\u0001\n\u0003\u0011\b\u0002C:\u0001\u0005#\u0005\u000b\u0011\u00022\t\u0011Q\u0004!Q3A\u0005\u0002ID\u0001\"\u001e\u0001\u0003\u0012\u0003\u0006IA\u0019\u0005\tm\u0002\u0011)\u001a!C\u0001o\"I\u0011Q\u0001\u0001\u0003\u0012\u0003\u0006I\u0001\u001f\u0005\u000b\u0003\u000f\u0001!Q3A\u0005\u0002\u0005%\u0001BCA\u0017\u0001\tE\t\u0015!\u0003\u0002\f!9\u0011q\u0006\u0001\u0005\u0002\u0005E\u0002bBA\u0018\u0001\u0011\u0005\u00111\t\u0005\n\u00033\u0002\u0011\u0011!C\u0001\u00037B\u0011\"a\u001b\u0001#\u0003%\t!!\u001c\t\u0013\u0005\r\u0005!%A\u0005\u0002\u0005\u0015\u0005\"CAE\u0001E\u0005I\u0011AAF\u0011%\ty\tAI\u0001\n\u0003\t\t\nC\u0005\u0002\u0016\u0002\t\n\u0011\"\u0001\u0002\u0012\"I\u0011q\u0013\u0001\u0012\u0002\u0013\u0005\u0011\u0011\u0014\u0005\n\u0003;\u0003\u0011\u0013!C\u0001\u0003?C\u0011\"a)\u0001\u0003\u0003%\t%!*\t\u0013\u0005E\u0006!!A\u0005\u0002\u0005M\u0006\"CA^\u0001\u0005\u0005I\u0011AA_\u0011%\tI\rAA\u0001\n\u0003\nY\rC\u0005\u0002Z\u0002\t\t\u0011\"\u0001\u0002\\\"I\u0011Q\u001d\u0001\u0002\u0002\u0013\u0005\u0013q\u001d\u0005\n\u0003W\u0004\u0011\u0011!C!\u0003[D\u0011\"a<\u0001\u0003\u0003%\t%!=\t\u0013\u0005M\b!!A\u0005B\u0005UxACA}U\u0005\u0005\t\u0012\u0001\u0018\u0002|\u001aI\u0011FKA\u0001\u0012\u0003q\u0013Q \u0005\b\u0003_\u0019C\u0011\u0001B\u000b\u0011%\tyoIA\u0001\n\u000b\n\t\u0010C\u0005\u0003\u0018\r\n\t\u0011\"!\u0003\u001a!I!\u0011F\u0012\u0002\u0002\u0013\u0005%1\u0006\u0005\n\u0005{\u0019\u0013\u0011!C\u0005\u0005\u007f\u0011AcU5na2,\u0007+\u001f;i_:4UO\\2uS>t'BA\u0016-\u0003\u0019\u0001\u0018\u0010\u001e5p]*\u0011QFL\u0001\u0004CBL'BA\u00181\u0003\u0015\u0019\b/\u0019:l\u0015\t\t$'\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002g\u0005\u0019qN]4\u0014\u000b\u0001)4h\u0010\"\u0011\u0005YJT\"A\u001c\u000b\u0003a\nQa]2bY\u0006L!AO\u001c\u0003\r\u0005s\u0017PU3g!\taT(D\u0001+\u0013\tq$F\u0001\bQsRDwN\u001c$v]\u000e$\u0018n\u001c8\u0011\u0005Y\u0002\u0015BA!8\u0005\u001d\u0001&o\u001c3vGR\u0004\"a\u0011'\u000f\u0005\u0011SeBA#J\u001b\u00051%BA$I\u0003\u0019a$o\\8u}\r\u0001\u0011\"\u0001\u001d\n\u0005-;\u0014a\u00029bG.\fw-Z\u0005\u0003\u001b:\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!aS\u001c\u0002\u000f\r|W.\\1oIV\t\u0011\u000bE\u0002D%RK!a\u0015(\u0003\u0007M+\u0017\u000f\u0005\u00027+&\u0011ak\u000e\u0002\u0005\u0005f$X-\u0001\u0005d_6l\u0017M\u001c3!\u0003\u001d)gN\u001e,beN,\u0012A\u0017\t\u00057\u0002\u0014'-D\u0001]\u0015\tif,\u0001\u0003vi&d'\"A0\u0002\t)\fg/Y\u0005\u0003Cr\u00131!T1q!\t\u0019wM\u0004\u0002eKB\u0011QiN\u0005\u0003M^\na\u0001\u0015:fI\u00164\u0017B\u00015j\u0005\u0019\u0019FO]5oO*\u0011amN\u0001\tK:4h+\u0019:tA\u0005q\u0001/\u001f;i_:Len\u00197vI\u0016\u001cX#A7\u0011\u0007ms'-\u0003\u0002p9\n!A*[:u\u0003=\u0001\u0018\u0010\u001e5p]&s7\r\\;eKN\u0004\u0013A\u00039zi\"|g.\u0012=fGV\t!-A\u0006qsRDwN\\#yK\u000e\u0004\u0013!\u00039zi\"|gNV3s\u0003)\u0001\u0018\u0010\u001e5p]Z+'\u000fI\u0001\u000eEJ|\u0017\rZ2bgR4\u0016M]:\u0016\u0003a\u00042a\u00178z!\rQXp`\u0007\u0002w*\u0011APL\u0001\nEJ|\u0017\rZ2bgRL!A`>\u0003\u0013\t\u0013x.\u00193dCN$\bc\u0001\u001f\u0002\u0002%\u0019\u00111\u0001\u0016\u0003\u001fAKH\u000f[8o\u0005J|\u0017\rZ2bgR\faB\u0019:pC\u0012\u001c\u0017m\u001d;WCJ\u001c\b%A\u0006bG\u000e,X.\u001e7bi>\u0014XCAA\u0006!\u0011\ti!a\n\u000f\t\u0005=\u00111\u0005\b\u0005\u0003#\t\tC\u0004\u0003\u0002\u0014\u0005}a\u0002BA\u000b\u0003;qA!a\u0006\u0002\u001c9\u0019Q)!\u0007\n\u0003MJ!!\r\u001a\n\u0005=\u0002\u0014BA\u0017/\u0013\tYC&C\u0002\u0002&)\na\u0002U=uQ>tg)\u001e8di&|g.\u0003\u0003\u0002*\u0005-\"!\u0005)zi\"|g.Q2dk6,H.\u0019;pe*\u0019\u0011Q\u0005\u0016\u0002\u0019\u0005\u001c7-^7vY\u0006$xN\u001d\u0011\u0002\rqJg.\u001b;?)A\t\u0019$!\u000e\u00028\u0005e\u00121HA\u001f\u0003\u007f\t\t\u0005\u0005\u0002=\u0001!)qj\u0004a\u0001#\")\u0001l\u0004a\u00015\")1n\u0004a\u0001[\")\u0011o\u0004a\u0001E\")Ao\u0004a\u0001E\")ao\u0004a\u0001q\"9\u0011qA\bA\u0002\u0005-A\u0003EA\u001a\u0003\u000b\ni%a\u0014\u0002R\u0005M\u0013QKA,\u0011\u0019y\u0005\u00031\u0001\u0002HA!a'!\u0013U\u0013\r\tYe\u000e\u0002\u0006\u0003J\u0014\u0018-\u001f\u0005\u00061B\u0001\rA\u0017\u0005\u0006WB\u0001\r!\u001c\u0005\u0006cB\u0001\rA\u0019\u0005\u0006iB\u0001\rA\u0019\u0005\u0006mB\u0001\r\u0001\u001f\u0005\b\u0003\u000f\u0001\u0002\u0019AA\u0006\u0003\u0011\u0019w\u000e]=\u0015!\u0005M\u0012QLA0\u0003C\n\u0019'!\u001a\u0002h\u0005%\u0004bB(\u0012!\u0003\u0005\r!\u0015\u0005\b1F\u0001\n\u00111\u0001[\u0011\u001dY\u0017\u0003%AA\u00025Dq!]\t\u0011\u0002\u0003\u0007!\rC\u0004u#A\u0005\t\u0019\u00012\t\u000fY\f\u0002\u0013!a\u0001q\"I\u0011qA\t\u0011\u0002\u0003\u0007\u00111B\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\tyGK\u0002R\u0003cZ#!a\u001d\u0011\t\u0005U\u0014qP\u0007\u0003\u0003oRA!!\u001f\u0002|\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003{:\u0014AC1o]>$\u0018\r^5p]&!\u0011\u0011QA<\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\t\t9IK\u0002[\u0003c\nabY8qs\u0012\"WMZ1vYR$3'\u0006\u0002\u0002\u000e*\u001aQ.!\u001d\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%iU\u0011\u00111\u0013\u0016\u0004E\u0006E\u0014AD2paf$C-\u001a4bk2$H%N\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00137+\t\tYJK\u0002y\u0003c\nabY8qs\u0012\"WMZ1vYR$s'\u0006\u0002\u0002\"*\"\u00111BA9\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u0011q\u0015\t\u0005\u0003S\u000by+\u0004\u0002\u0002,*\u0019\u0011Q\u00160\u0002\t1\fgnZ\u0005\u0004Q\u0006-\u0016\u0001\u00049s_\u0012,8\r^!sSRLXCAA[!\r1\u0014qW\u0005\u0004\u0003s;$aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BA`\u0003\u000b\u00042ANAa\u0013\r\t\u0019m\u000e\u0002\u0004\u0003:L\b\"CAd7\u0005\u0005\t\u0019AA[\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011Q\u001a\t\u0007\u0003\u001f\f).a0\u000e\u0005\u0005E'bAAjo\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005]\u0017\u0011\u001b\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002^\u0006\r\bc\u0001\u001c\u0002`&\u0019\u0011\u0011]\u001c\u0003\u000f\t{w\u000e\\3b]\"I\u0011qY\u000f\u0002\u0002\u0003\u0007\u0011qX\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0002(\u0006%\b\"CAd=\u0005\u0005\t\u0019AA[\u0003!A\u0017m\u001d5D_\u0012,GCAA[\u0003!!xn\u0015;sS:<GCAAT\u0003\u0019)\u0017/^1mgR!\u0011Q\\A|\u0011%\t9-IA\u0001\u0002\u0004\ty,\u0001\u000bTS6\u0004H.\u001a)zi\"|gNR;oGRLwN\u001c\t\u0003y\r\u001aRaIA\u0000\u0005\u0017\u0001bB!\u0001\u0003\bESVN\u00192y\u0003\u0017\t\u0019$\u0004\u0002\u0003\u0004)\u0019!QA\u001c\u0002\u000fI,h\u000e^5nK&!!\u0011\u0002B\u0002\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gn\u000e\t\u0005\u0005\u001b\u0011\u0019\"\u0004\u0002\u0003\u0010)\u0019!\u0011\u00030\u0002\u0005%|\u0017bA'\u0003\u0010Q\u0011\u00111`\u0001\u0006CB\u0004H.\u001f\u000b\u0011\u0003g\u0011YB!\b\u0003 \t\u0005\"1\u0005B\u0013\u0005OAQa\u0014\u0014A\u0002ECQ\u0001\u0017\u0014A\u0002iCQa\u001b\u0014A\u00025DQ!\u001d\u0014A\u0002\tDQ\u0001\u001e\u0014A\u0002\tDQA\u001e\u0014A\u0002aDq!a\u0002'\u0001\u0004\tY!A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\t5\"\u0011\b\t\u0006m\t=\"1G\u0005\u0004\u0005c9$AB(qi&|g\u000eE\u00067\u0005k\t&,\u001c2cq\u0006-\u0011b\u0001B\u001co\t1A+\u001e9mK^B\u0011Ba\u000f(\u0003\u0003\u0005\r!a\r\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0003BA!\u0011\u0011\u0016B\"\u0013\u0011\u0011)%a+\u0003\r=\u0013'.Z2u\u0001"
)
public class SimplePythonFunction implements PythonFunction, Product, Serializable {
   private final Seq command;
   private final Map envVars;
   private final List pythonIncludes;
   private final String pythonExec;
   private final String pythonVer;
   private final List broadcastVars;
   private final CollectionAccumulator accumulator;

   public static Option unapply(final SimplePythonFunction x$0) {
      return SimplePythonFunction$.MODULE$.unapply(x$0);
   }

   public static SimplePythonFunction apply(final Seq command, final Map envVars, final List pythonIncludes, final String pythonExec, final String pythonVer, final List broadcastVars, final CollectionAccumulator accumulator) {
      return SimplePythonFunction$.MODULE$.apply(command, envVars, pythonIncludes, pythonExec, pythonVer, broadcastVars, accumulator);
   }

   public static Function1 tupled() {
      return SimplePythonFunction$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SimplePythonFunction$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Seq command() {
      return this.command;
   }

   public Map envVars() {
      return this.envVars;
   }

   public List pythonIncludes() {
      return this.pythonIncludes;
   }

   public String pythonExec() {
      return this.pythonExec;
   }

   public String pythonVer() {
      return this.pythonVer;
   }

   public List broadcastVars() {
      return this.broadcastVars;
   }

   public CollectionAccumulator accumulator() {
      return this.accumulator;
   }

   public SimplePythonFunction copy(final Seq command, final Map envVars, final List pythonIncludes, final String pythonExec, final String pythonVer, final List broadcastVars, final CollectionAccumulator accumulator) {
      return new SimplePythonFunction(command, envVars, pythonIncludes, pythonExec, pythonVer, broadcastVars, accumulator);
   }

   public Seq copy$default$1() {
      return this.command();
   }

   public Map copy$default$2() {
      return this.envVars();
   }

   public List copy$default$3() {
      return this.pythonIncludes();
   }

   public String copy$default$4() {
      return this.pythonExec();
   }

   public String copy$default$5() {
      return this.pythonVer();
   }

   public List copy$default$6() {
      return this.broadcastVars();
   }

   public CollectionAccumulator copy$default$7() {
      return this.accumulator();
   }

   public String productPrefix() {
      return "SimplePythonFunction";
   }

   public int productArity() {
      return 7;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.command();
         }
         case 1 -> {
            return this.envVars();
         }
         case 2 -> {
            return this.pythonIncludes();
         }
         case 3 -> {
            return this.pythonExec();
         }
         case 4 -> {
            return this.pythonVer();
         }
         case 5 -> {
            return this.broadcastVars();
         }
         case 6 -> {
            return this.accumulator();
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
      return x$1 instanceof SimplePythonFunction;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "command";
         }
         case 1 -> {
            return "envVars";
         }
         case 2 -> {
            return "pythonIncludes";
         }
         case 3 -> {
            return "pythonExec";
         }
         case 4 -> {
            return "pythonVer";
         }
         case 5 -> {
            return "broadcastVars";
         }
         case 6 -> {
            return "accumulator";
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
      boolean var18;
      if (this != x$1) {
         label95: {
            if (x$1 instanceof SimplePythonFunction) {
               label88: {
                  SimplePythonFunction var4 = (SimplePythonFunction)x$1;
                  Seq var10000 = this.command();
                  Seq var5 = var4.command();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label88;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label88;
                  }

                  Map var12 = this.envVars();
                  Map var6 = var4.envVars();
                  if (var12 == null) {
                     if (var6 != null) {
                        break label88;
                     }
                  } else if (!var12.equals(var6)) {
                     break label88;
                  }

                  List var13 = this.pythonIncludes();
                  List var7 = var4.pythonIncludes();
                  if (var13 == null) {
                     if (var7 != null) {
                        break label88;
                     }
                  } else if (!var13.equals(var7)) {
                     break label88;
                  }

                  String var14 = this.pythonExec();
                  String var8 = var4.pythonExec();
                  if (var14 == null) {
                     if (var8 != null) {
                        break label88;
                     }
                  } else if (!var14.equals(var8)) {
                     break label88;
                  }

                  var14 = this.pythonVer();
                  String var9 = var4.pythonVer();
                  if (var14 == null) {
                     if (var9 != null) {
                        break label88;
                     }
                  } else if (!var14.equals(var9)) {
                     break label88;
                  }

                  List var16 = this.broadcastVars();
                  List var10 = var4.broadcastVars();
                  if (var16 == null) {
                     if (var10 != null) {
                        break label88;
                     }
                  } else if (!var16.equals(var10)) {
                     break label88;
                  }

                  CollectionAccumulator var17 = this.accumulator();
                  CollectionAccumulator var11 = var4.accumulator();
                  if (var17 == null) {
                     if (var11 != null) {
                        break label88;
                     }
                  } else if (!var17.equals(var11)) {
                     break label88;
                  }

                  if (var4.canEqual(this)) {
                     break label95;
                  }
               }
            }

            var18 = false;
            return var18;
         }
      }

      var18 = true;
      return var18;
   }

   public SimplePythonFunction(final Seq command, final Map envVars, final List pythonIncludes, final String pythonExec, final String pythonVer, final List broadcastVars, final CollectionAccumulator accumulator) {
      this.command = command;
      this.envVars = envVars;
      this.pythonIncludes = pythonIncludes;
      this.pythonExec = pythonExec;
      this.pythonVer = pythonVer;
      this.broadcastVars = broadcastVars;
      this.accumulator = accumulator;
      Product.$init$(this);
   }

   public SimplePythonFunction(final byte[] command, final Map envVars, final List pythonIncludes, final String pythonExec, final String pythonVer, final List broadcastVars, final CollectionAccumulator accumulator) {
      this((Seq)org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(command).toImmutableArraySeq(), envVars, pythonIncludes, pythonExec, pythonVer, broadcastVars, accumulator);
   }
}
