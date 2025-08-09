package org.apache.spark.util;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.internal.Logging;
import org.apache.xbean.asm9.Handle;
import scala.Option;
import scala.StringContext;
import scala.collection.mutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=tA\u0002\t\u0012\u0011\u0003\u0019\u0012D\u0002\u0004\u001c#!\u00051\u0003\b\u0005\u0006S\u0005!\ta\u000b\u0005\bY\u0005\u0011\r\u0011\"\u0001.\u0011\u00191\u0014\u0001)A\u0005]!9q'\u0001b\u0001\n\u0003i\u0003B\u0002\u001d\u0002A\u0003%a\u0006C\u0004:\u0003\t\u0007I\u0011A\u0017\t\ri\n\u0001\u0015!\u0003/\u0011\u0015Y\u0014\u0001\"\u0001=\u0011\u0015A\u0015\u0001\"\u0001J\u0011\u0015y\u0015\u0001\"\u0001Q\u0011\u0015\u0019\u0016\u0001\"\u0001U\u0011\u0015y\u0016\u0001\"\u0001a\u0011\u0015y\u0017\u0001\"\u0001q\u0011\u0015q\u0018\u0001\"\u0001\u0000\u0003]Ie\u000eZ=mC6\u0014G-Y*dC2\f7\t\\8tkJ,7O\u0003\u0002\u0013'\u0005!Q\u000f^5m\u0015\t!R#A\u0003ta\u0006\u00148N\u0003\u0002\u0017/\u00051\u0011\r]1dQ\u0016T\u0011\u0001G\u0001\u0004_J<\u0007C\u0001\u000e\u0002\u001b\u0005\t\"aF%oIfd\u0017-\u001c2eCN\u001b\u0017\r\\1DY>\u001cXO]3t'\r\tQd\t\t\u0003=\u0005j\u0011a\b\u0006\u0002A\u0005)1oY1mC&\u0011!e\b\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\u0011:S\"A\u0013\u000b\u0005\u0019\u001a\u0012\u0001C5oi\u0016\u0014h.\u00197\n\u0005!*#a\u0002'pO\u001eLgnZ\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\t\u0011$\u0001\u000eMC6\u0014G-Y'fi\u00064\u0017m\u0019;pef\u001cE.Y:t\u001d\u0006lW-F\u0001/!\tyC'D\u00011\u0015\t\t$'\u0001\u0003mC:<'\"A\u001a\u0002\t)\fg/Y\u0005\u0003kA\u0012aa\u0015;sS:<\u0017a\u0007'b[\n$\u0017-T3uC\u001a\f7\r^8ss\u000ec\u0017m]:OC6,\u0007%A\u000eMC6\u0014G-Y'fi\u00064\u0017m\u0019;peflU\r\u001e5pI:\u000bW.Z\u0001\u001d\u0019\u0006l'\rZ1NKR\fg-Y2u_JLX*\u001a;i_\u0012t\u0015-\\3!\u0003ma\u0015-\u001c2eC6+G/\u00194bGR|'/_'fi\"|G\rR3tG\u0006aB*Y7cI\u0006lU\r^1gC\u000e$xN]=NKRDw\u000e\u001a#fg\u000e\u0004\u0013!F4fiN+'/[1mSj\fG/[8o!J|\u00070\u001f\u000b\u0003{\u0019\u00032A\b A\u0013\tytD\u0001\u0004PaRLwN\u001c\t\u0003\u0003\u0012k\u0011A\u0011\u0006\u0003\u0007B\na!\u001b8w_.,\u0017BA#C\u0005A\u0019VM]5bY&TX\r\u001a'b[\n$\u0017\rC\u0003H\u0013\u0001\u0007Q$\u0001\u0007nCf\u0014Wm\u00117pgV\u0014X-\u0001\rjg&sG-\u001f7b[\n$\u0017mU2bY\u0006\u001cEn\\:ve\u0016$\"AS'\u0011\u0005yY\u0015B\u0001' \u0005\u001d\u0011un\u001c7fC:DQA\u0014\u0006A\u0002\u0001\u000b1\u0002\\1nE\u0012\f\u0007K]8ys\u00069\u0011N\\:qK\u000e$HC\u0001!R\u0011\u0015\u00116\u00021\u0001\u001e\u0003\u001d\u0019Gn\\:ve\u0016\f1#[:MC6\u0014G-Y'fi\u00064\u0017m\u0019;pef$\"AS+\t\u000bYc\u0001\u0019A,\u0002\u0013\t\u001cX\u000eS1oI2,\u0007C\u0001-^\u001b\u0005I&B\u0001.\\\u0003\u0011\t7/\\\u001d\u000b\u0005q+\u0012!\u0002=cK\u0006t\u0017B\u00010Z\u0005\u0019A\u0015M\u001c3mK\u0006Q\u0012n\u001d'b[\n$\u0017MQ8es\u000e\u000b\u0007\u000f^;sS:<w*\u001e;feR\u0019!*Y2\t\u000b\tl\u0001\u0019A,\u0002\r!\fg\u000e\u001a7f\u0011\u0015!W\u00021\u0001f\u0003EywO\\3s\u0013:$XM\u001d8bY:\u000bW.\u001a\t\u0003M6t!aZ6\u0011\u0005!|R\"A5\u000b\u0005)T\u0013A\u0002\u001fs_>$h(\u0003\u0002m?\u00051\u0001K]3eK\u001aL!!\u000e8\u000b\u00051|\u0012AH5t\u0013:tWM]\"mCN\u001c8\t^8s\u0007\u0006\u0004H/\u001e:j]\u001e|U\u000f^3s)\u0019Q\u0015O\u001e={y\")!O\u0004a\u0001g\u0006\u0011q\u000e\u001d\t\u0003=QL!!^\u0010\u0003\u0007%sG\u000fC\u0003x\u001d\u0001\u0007Q-A\u0003po:,'\u000fC\u0003z\u001d\u0001\u0007Q-\u0001\u0003oC6,\u0007\"B>\u000f\u0001\u0004)\u0017\u0001\u00023fg\u000eDQ! \bA\u0002\u0015\f!cY1mY\u0016\u0014\u0018J\u001c;fe:\fGNT1nK\u0006\u0011b-\u001b8e\u0003\u000e\u001cWm]:fI\u001aKW\r\u001c3t)9\t\t!a\u0002\u0002\n\u0005M\u00111JA.\u0003W\u00022AHA\u0002\u0013\r\t)a\b\u0002\u0005+:LG\u000fC\u0003O\u001f\u0001\u0007\u0001\tC\u0004\u0002\f=\u0001\r!!\u0004\u0002#1\fWN\u00193b\u00072\f7o\u001d'pC\u0012,'\u000fE\u00020\u0003\u001fI1!!\u00051\u0005-\u0019E.Y:t\u0019>\fG-\u001a:\t\u000f\u0005Uq\u00021\u0001\u0002\u0018\u0005q\u0011mY2fgN,GMR5fY\u0012\u001c\b\u0003CA\r\u0003G\t9#!\u0012\u000e\u0005\u0005m!\u0002BA\u000f\u0003?\tq!\\;uC\ndWMC\u0002\u0002\"}\t!bY8mY\u0016\u001cG/[8o\u0013\u0011\t)#a\u0007\u0003\u00075\u000b\u0007\u000f\r\u0003\u0002*\u0005M\u0002#\u00024\u0002,\u0005=\u0012bAA\u0017]\n)1\t\\1tgB!\u0011\u0011GA\u001a\u0019\u0001!A\"!\u000e\u0002\u0014\u0005\u0005\t\u0011!B\u0001\u0003o\u0011Aa\u0018\u00133oE!\u0011\u0011HA !\rq\u00121H\u0005\u0004\u0003{y\"a\u0002(pi\"Lgn\u001a\t\u0004=\u0005\u0005\u0013bAA\"?\t\u0019\u0011I\\=\u0011\u000b\u0005e\u0011qI3\n\t\u0005%\u00131\u0004\u0002\u0004'\u0016$\bbBA'\u001f\u0001\u0007\u0011qJ\u0001\u0015C\u000e\u001cWm]:fI\u0006kWnQ7e\r&,G\u000eZ:\u0011\u0011\u0005e\u00111EA)\u0003\u000b\u0002D!a\u0015\u0002XA)a-a\u000b\u0002VA!\u0011\u0011GA,\t1\tI&a\u0013\u0002\u0002\u0003\u0005)\u0011AA\u001c\u0005\u0011yFE\r\u001d\t\u000f\u0005us\u00021\u0001\u0002`\u0005y\u0011-\\7D[\u0012Len\u001d;b]\u000e,7\u000fE\u0004\u0002\u001a\u0005\r\u0012\u0011M\u000f1\t\u0005\r\u0014q\r\t\u0006M\u0006-\u0012Q\r\t\u0005\u0003c\t9\u0007\u0002\u0007\u0002j\u0005m\u0013\u0011!A\u0001\u0006\u0003\t9D\u0001\u0003`IIJ\u0004BBA7\u001f\u0001\u0007!*\u0001\tgS:$GK]1og&$\u0018N^3ms\u0002"
)
public final class IndylambdaScalaClosures {
   public static void findAccessedFields(final SerializedLambda lambdaProxy, final ClassLoader lambdaClassLoader, final Map accessedFields, final Map accessedAmmCmdFields, final Map ammCmdInstances, final boolean findTransitively) {
      IndylambdaScalaClosures$.MODULE$.findAccessedFields(lambdaProxy, lambdaClassLoader, accessedFields, accessedAmmCmdFields, ammCmdInstances, findTransitively);
   }

   public static boolean isInnerClassCtorCapturingOuter(final int op, final String owner, final String name, final String desc, final String callerInternalName) {
      return IndylambdaScalaClosures$.MODULE$.isInnerClassCtorCapturingOuter(op, owner, name, desc, callerInternalName);
   }

   public static boolean isLambdaBodyCapturingOuter(final Handle handle, final String ownerInternalName) {
      return IndylambdaScalaClosures$.MODULE$.isLambdaBodyCapturingOuter(handle, ownerInternalName);
   }

   public static boolean isLambdaMetafactory(final Handle bsmHandle) {
      return IndylambdaScalaClosures$.MODULE$.isLambdaMetafactory(bsmHandle);
   }

   public static SerializedLambda inspect(final Object closure) {
      return IndylambdaScalaClosures$.MODULE$.inspect(closure);
   }

   public static boolean isIndylambdaScalaClosure(final SerializedLambda lambdaProxy) {
      return IndylambdaScalaClosures$.MODULE$.isIndylambdaScalaClosure(lambdaProxy);
   }

   public static Option getSerializationProxy(final Object maybeClosure) {
      return IndylambdaScalaClosures$.MODULE$.getSerializationProxy(maybeClosure);
   }

   public static String LambdaMetafactoryMethodDesc() {
      return IndylambdaScalaClosures$.MODULE$.LambdaMetafactoryMethodDesc();
   }

   public static String LambdaMetafactoryMethodName() {
      return IndylambdaScalaClosures$.MODULE$.LambdaMetafactoryMethodName();
   }

   public static String LambdaMetafactoryClassName() {
      return IndylambdaScalaClosures$.MODULE$.LambdaMetafactoryClassName();
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return IndylambdaScalaClosures$.MODULE$.LogStringContext(sc);
   }
}
