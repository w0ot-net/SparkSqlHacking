package org.apache.spark.ui;

import java.lang.invoke.SerializedLambda;
import scala.Predef;
import scala.collection.StringOps;
import scala.collection.StringOps.;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005y3a!\u0004\b\u0002\u0002A1\u0002\u0002C\u000f\u0001\u0005\u0003\u0005\u000b\u0011B\u0010\t\u0011\r\u0002!Q1A\u0005\u0002\u0011B\u0001\u0002\r\u0001\u0003\u0002\u0003\u0006I!\n\u0005\u0006c\u0001!\tA\r\u0005\bm\u0001\u0011\r\u0011\"\u00018\u0011\u0019\u0019\u0005\u0001)A\u0005q!9A\t\u0001b\u0001\n\u0003!\u0003BB#\u0001A\u0003%Q\u0005C\u0003G\u0001\u0011\u0005q\tC\u0003N\u0001\u0011\u0005a\nC\u0003Y\u0001\u0011\u0005A\u0005C\u0003Z\u0001\u0011\u0005!L\u0001\u0005XK\n,\u0016\nV1c\u0015\ty\u0001#\u0001\u0002vS*\u0011\u0011CE\u0001\u0006gB\f'o\u001b\u0006\u0003'Q\ta!\u00199bG\",'\"A\u000b\u0002\u0007=\u0014xm\u0005\u0002\u0001/A\u0011\u0001dG\u0007\u00023)\t!$A\u0003tG\u0006d\u0017-\u0003\u0002\u001d3\t1\u0011I\\=SK\u001a\fa\u0001]1sK:$8\u0001\u0001\t\u0003A\u0005j\u0011AD\u0005\u0003E9\u0011QaV3c+&\u000ba\u0001\u001d:fM&DX#A\u0013\u0011\u0005\u0019jcBA\u0014,!\tA\u0013$D\u0001*\u0015\tQc$\u0001\u0004=e>|GOP\u0005\u0003Ye\ta\u0001\u0015:fI\u00164\u0017B\u0001\u00180\u0005\u0019\u0019FO]5oO*\u0011A&G\u0001\baJ,g-\u001b=!\u0003\u0019a\u0014N\\5u}Q\u00191\u0007N\u001b\u0011\u0005\u0001\u0002\u0001\"B\u000f\u0005\u0001\u0004y\u0002\"B\u0012\u0005\u0001\u0004)\u0013!\u00029bO\u0016\u001cX#\u0001\u001d\u0011\u0007er\u0004)D\u0001;\u0015\tYD(A\u0004nkR\f'\r\\3\u000b\u0005uJ\u0012AC2pY2,7\r^5p]&\u0011qH\u000f\u0002\f\u0003J\u0014\u0018-\u001f\"vM\u001a,'\u000f\u0005\u0002!\u0003&\u0011!I\u0004\u0002\n/\u0016\u0014W+\u0013)bO\u0016\fa\u0001]1hKN\u0004\u0013\u0001\u00028b[\u0016\fQA\\1nK\u0002\n!\"\u0019;uC\u000eD\u0007+Y4f)\tA5\n\u0005\u0002\u0019\u0013&\u0011!*\u0007\u0002\u0005+:LG\u000fC\u0003M\u0013\u0001\u0007\u0001)\u0001\u0003qC\u001e,\u0017A\u00035fC\u0012,'\u000fV1cgV\tq\nE\u0002Q+Nr!!U*\u000f\u0005!\u0012\u0016\"\u0001\u000e\n\u0005QK\u0012a\u00029bG.\fw-Z\u0005\u0003-^\u00131aU3r\u0015\t!\u0016$\u0001\u0005cCN,\u0007+\u0019;i\u00031!\u0017n\u001d9mCf|%\u000fZ3s+\u0005Y\u0006C\u0001\r]\u0013\ti\u0016DA\u0002J]R\u0004"
)
public abstract class WebUITab {
   private final WebUI parent;
   private final String prefix;
   private final ArrayBuffer pages;
   private final String name;

   public String prefix() {
      return this.prefix;
   }

   public ArrayBuffer pages() {
      return this.pages;
   }

   public String name() {
      return this.name;
   }

   public void attachPage(final WebUIPage page) {
      StringOps var10001 = .MODULE$;
      Predef var10002 = scala.Predef..MODULE$;
      String var10003 = this.prefix();
      page.prefix_$eq(var10001.stripSuffix$extension(var10002.augmentString(var10003 + "/" + page.prefix()), "/"));
      this.pages().$plus$eq(page);
   }

   public Seq headerTabs() {
      return (Seq)this.parent.getTabs().sortBy((x$9) -> BoxesRunTime.boxToInteger($anonfun$headerTabs$1(x$9)), scala.math.Ordering.Int..MODULE$);
   }

   public String basePath() {
      return this.parent.getBasePath();
   }

   public int displayOrder() {
      return Integer.MIN_VALUE;
   }

   // $FF: synthetic method
   public static final int $anonfun$headerTabs$1(final WebUITab x$9) {
      return x$9.displayOrder();
   }

   public WebUITab(final WebUI parent, final String prefix) {
      this.parent = parent;
      this.prefix = prefix;
      this.pages = (ArrayBuffer)scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      this.name = .MODULE$.capitalize$extension(scala.Predef..MODULE$.augmentString(prefix));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
