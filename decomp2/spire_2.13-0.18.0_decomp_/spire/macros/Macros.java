package spire.macros;

import java.io.Serializable;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.SeqFactory;
import scala.collection.SeqOps;
import scala.collection.immutable.List;
import scala.math.BigInt;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Constants;
import scala.reflect.api.Exprs;
import scala.reflect.api.Trees;
import scala.reflect.api.TypeTags;
import scala.reflect.macros.whitebox.Context;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.util.Either;

@ScalaSignature(
   bytes = "\u0006\u0005\ruv!\u0002\u001d:\u0011\u0003qd!\u0002!:\u0011\u0003\t\u0005\"\u0002%\u0002\t\u0003Ie\u0001\u0002&\u0002\u0001.C\u0001bW\u0002\u0003\u0016\u0004%\t\u0001\u0018\u0005\tQ\u000e\u0011\t\u0012)A\u0005;\")\u0001j\u0001C\u0001S\")Qn\u0001C\u0001]\"9qoAA\u0001\n\u0003A\bb\u0002>\u0004#\u0003%\ta\u001f\u0005\n\u0003\u001b\u0019\u0011\u0011!C!\u0003\u001fA\u0011\"a\b\u0004\u0003\u0003%\t!!\t\t\u0013\u0005%2!!A\u0005\u0002\u0005-\u0002\"CA\u001c\u0007\u0005\u0005I\u0011IA\u001d\u0011%\t9eAA\u0001\n\u0003\tI\u0005C\u0005\u0002T\r\t\t\u0011\"\u0011\u0002V!I\u0011\u0011L\u0002\u0002\u0002\u0013\u0005\u00131\f\u0005\n\u0003;\u001a\u0011\u0011!C!\u0003?B\u0011\"!\u0019\u0004\u0003\u0003%\t%a\u0019\b\u0013\u0005\u001d\u0014!!A\t\u0002\u0005%d\u0001\u0003&\u0002\u0003\u0003E\t!a\u001b\t\r!#B\u0011AAB\u0011%\ti\u0006FA\u0001\n\u000b\ny\u0006C\u0005\u0002\u0006R\t\t\u0011\"!\u0002\b\"I\u00111\u0012\u000b\u0002\u0002\u0013\u0005\u0015Q\u0012\u0005\n\u00033#\u0012\u0011!C\u0005\u00037Cq!a)\u0002\t\u0003\t)\u000bC\u0004\u0002>\u0006!\t!a0\t\u000f\u0005%\u0017\u0001\"\u0001\u0002L\"9\u00111^\u0001\u0005\u0002\u00055\bb\u0002B\u0002\u0003\u0011\u0005!Q\u0001\u0005\b\u0005+\tA\u0011\u0001B\f\u0011\u001d\u00119#\u0001C\u0001\u0005SAqA!\u000f\u0002\t\u0003\u0011Y\u0004C\u0004\u0003L\u0005!\tA!\u0014\t\u000f\tu\u0013\u0001\"\u0001\u0003`!9!qM\u0001\u0005\u0002\t%\u0004b\u0002B:\u0003\u0011\u0005!Q\u000f\u0005\b\u0005\u0007\u000bA\u0011\u0001BC\u0011\u001d\u00119*\u0001C\u0001\u00053CqA!*\u0002\t\u0003\u00119\u000bC\u0004\u0003<\u0006!\tA!0\t\u000f\t\u001d\u0017\u0001\"\u0001\u0003J\"9!1[\u0001\u0005\u0002\tU\u0007b\u0002Bp\u0003\u0011\u0005!\u0011\u001d\u0005\b\u0005W\fA\u0011\u0001Bw\u0011\u001d\u001190\u0001C\u0001\u0005sDqaa\u0001\u0002\t\u0003\u0019)\u0001C\u0004\u0004\u0010\u0005!\ta!\u0005\t\u000f\rm\u0011\u0001\"\u0001\u0004\u001e!91qE\u0001\u0005\u0002\r%\u0002bBB\u001a\u0003\u0011\u00051Q\u0007\u0005\b\u0007\u007f\tA\u0011AB!\u0011\u001d\u0019Y%\u0001C\u0001\u0007\u001bBqaa\u0016\u0002\t\u0003\u0019I\u0006C\u0004\u0004\u001a\u0006!\taa'\u0002\r5\u000b7M]8t\u0015\tQ4(\u0001\u0004nC\u000e\u0014xn\u001d\u0006\u0002y\u0005)1\u000f]5sK\u000e\u0001\u0001CA \u0002\u001b\u0005I$AB'bGJ|7o\u0005\u0002\u0002\u0005B\u00111IR\u0007\u0002\t*\tQ)A\u0003tG\u0006d\u0017-\u0003\u0002H\t\n1\u0011I\\=SK\u001a\fa\u0001P5oSRtD#\u0001 \u0003\u00171KG/\u001a:bYV#\u0018\u000e\\\n\u0005\u0007\tcu\n\u0005\u0002D\u001b&\u0011a\n\u0012\u0002\b!J|G-^2u!\t\u0001\u0006L\u0004\u0002R-:\u0011!+V\u0007\u0002'*\u0011A+P\u0001\u0007yI|w\u000e\u001e \n\u0003\u0015K!a\u0016#\u0002\u000fA\f7m[1hK&\u0011\u0011L\u0017\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003/\u0012\u000b\u0011aY\u000b\u0002;B\u0011a,\u001a\b\u0003?\u000et!\u0001\u00192\u000f\u0005I\u000b\u0017\"\u0001\u001f\n\u0005iZ\u0014B\u00013:\u0003\u0019\u0019w.\u001c9bi&\u0011am\u001a\u0002\b\u0007>tG/\u001a=u\u0015\t!\u0017(\u0001\u0002dAQ\u0011!\u000e\u001c\t\u0003W\u000ei\u0011!\u0001\u0005\u00067\u001a\u0001\r!X\u0001\nO\u0016$8\u000b\u001e:j]\u001e,\u0012a\u001c\t\u0003aRt!!\u001d:\u0011\u0005I#\u0015BA:E\u0003\u0019\u0001&/\u001a3fM&\u0011QO\u001e\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005M$\u0015\u0001B2paf$\"A[=\t\u000fmC\u0001\u0013!a\u0001;\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#\u0001?+\u0005uk8&\u0001@\u0011\u0007}\fI!\u0004\u0002\u0002\u0002)!\u00111AA\u0003\u0003%)hn\u00195fG.,GMC\u0002\u0002\b\u0011\u000b!\"\u00198o_R\fG/[8o\u0013\u0011\tY!!\u0001\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0003#\u0001B!a\u0005\u0002\u001e5\u0011\u0011Q\u0003\u0006\u0005\u0003/\tI\"\u0001\u0003mC:<'BAA\u000e\u0003\u0011Q\u0017M^1\n\u0007U\f)\"\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u0002$A\u00191)!\n\n\u0007\u0005\u001dBIA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002.\u0005M\u0002cA\"\u00020%\u0019\u0011\u0011\u0007#\u0003\u0007\u0005s\u0017\u0010C\u0005\u000261\t\t\u00111\u0001\u0002$\u0005\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!a\u000f\u0011\r\u0005u\u00121IA\u0017\u001b\t\tyDC\u0002\u0002B\u0011\u000b!bY8mY\u0016\u001cG/[8o\u0013\u0011\t)%a\u0010\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003\u0017\n\t\u0006E\u0002D\u0003\u001bJ1!a\u0014E\u0005\u001d\u0011un\u001c7fC:D\u0011\"!\u000e\u000f\u0003\u0003\u0005\r!!\f\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003#\t9\u0006C\u0005\u00026=\t\t\u00111\u0001\u0002$\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002$\u0005AAo\\*ue&tw\r\u0006\u0002\u0002\u0012\u00051Q-];bYN$B!a\u0013\u0002f!I\u0011Q\u0007\n\u0002\u0002\u0003\u0007\u0011QF\u0001\f\u0019&$XM]1m+RLG\u000e\u0005\u0002l)M)A#!\u001c\u0002zA1\u0011qNA;;*l!!!\u001d\u000b\u0007\u0005MD)A\u0004sk:$\u0018.\\3\n\t\u0005]\u0014\u0011\u000f\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\f\u0004\u0003BA>\u0003\u0003k!!! \u000b\t\u0005}\u0014\u0011D\u0001\u0003S>L1!WA?)\t\tI'A\u0003baBd\u0017\u0010F\u0002k\u0003\u0013CQaW\fA\u0002u\u000bq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002\u0010\u0006U\u0005\u0003B\"\u0002\u0012vK1!a%E\u0005\u0019y\u0005\u000f^5p]\"A\u0011q\u0013\r\u0002\u0002\u0003\u0007!.A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!(\u0011\t\u0005M\u0011qT\u0005\u0005\u0003C\u000b)B\u0001\u0004PE*,7\r^\u0001\ra\u0006\u00148/Z\"p]R,\u0007\u0010\u001e\u000b\t\u0003O\u000b\u0019,!.\u0002:B1\u0001+!+p\u0003[K1!a+[\u0005\u0019)\u0015\u000e\u001e5feB\u0019\u0001+a,\n\u0007\u0005E&L\u0001\u0004CS\u001eLe\u000e\u001e\u0005\u00067j\u0001\r!\u0018\u0005\b\u0003oS\u0002\u0019AAW\u0003\u0015awn^3s\u0011\u001d\tYL\u0007a\u0001\u0003[\u000bQ!\u001e9qKJ\f1\u0002]1sg\u0016tU/\u001c2feRA\u0011qUAa\u0003\u000b\f9\r\u0003\u0004\u0002Dn\u0001\ra\\\u0001\u0002g\"9\u0011qW\u000eA\u0002\u00055\u0006bBA^7\u0001\u0007\u0011QV\u0001\u0005Ef$X\r\u0006\u0003\u0002N\u0006UGCAAh!\u0019\t\t.a6\u0002f:!\u00111[Ak\u0019\u0001AQa\u0017\u000fA\u0002uKA!!7\u0002\\\n!Q\t\u001f9s\u0013\u0011\ti.a8\u0003\u000f\u0005c\u0017.Y:fg*\u0019!(!9\u000b\u0007\u0005\rH)A\u0004sK\u001adWm\u0019;\u0011\u0007\r\u000b9/C\u0002\u0002j\u0012\u0013AAQ=uK\u0006)QOY=uKR!\u0011q^A{)\t\t\t\u0010\u0005\u0004\u0002t\u0006]\u0017q\u001f\b\u0005\u0003'\f)\u0010C\u0003\\;\u0001\u0007Q\f\u0005\u0003\u0002z\u0006}XBAA~\u0015\r\tipO\u0001\u0005[\u0006$\b.\u0003\u0003\u0003\u0002\u0005m(!B+CsR,\u0017!B:i_J$H\u0003\u0002B\u0004\u0005\u001b!\"A!\u0003\u0011\r\t-\u0011q\u001bB\b\u001d\u0011\t\u0019N!\u0004\t\u000bms\u0002\u0019A/\u0011\u0007\r\u0013\t\"C\u0002\u0003\u0014\u0011\u0013Qa\u00155peR\fa!^:i_J$H\u0003\u0002B\r\u0005?!\"Aa\u0007\u0011\r\tu\u0011q\u001bB\u0011\u001d\u0011\t\u0019Na\b\t\u000bm{\u0002\u0019A/\u0011\t\u0005e(1E\u0005\u0005\u0005K\tYP\u0001\u0004V'\"|'\u000f^\u0001\u0005k&tG\u000f\u0006\u0003\u0003,\tEBC\u0001B\u0017!\u0019\u0011y#a6\u000349!\u00111\u001bB\u0019\u0011\u0015Y\u0006\u00051\u0001^!\u0011\tIP!\u000e\n\t\t]\u00121 \u0002\u0005+&sG/A\u0003vY>tw\r\u0006\u0003\u0003>\t\rCC\u0001B !\u0019\u0011\t%a6\u0003F9!\u00111\u001bB\"\u0011\u0015Y\u0016\u00051\u0001^!\u0011\tIPa\u0012\n\t\t%\u00131 \u0002\u0006+2{gnZ\u0001\te\u0006$\u0018n\u001c8bYR!!q\nB+)\t\u0011\t\u0006\u0005\u0004\u0003T\u0005]'q\u000b\b\u0005\u0003'\u0014)\u0006C\u0003\\E\u0001\u0007Q\f\u0005\u0003\u0002z\ne\u0013\u0002\u0002B.\u0003w\u0014\u0001BU1uS>t\u0017\r\\\u0001\fM>\u0014X.\u0019;XQ>dW\rF\u0003p\u0005C\u0012\u0019\u0007C\u0003\\G\u0001\u0007Q\f\u0003\u0004\u0003f\r\u0002\ra\\\u0001\u0004g\u0016\u0004\u0018!\u00044pe6\fG\u000fR3dS6\fG\u000eF\u0004p\u0005W\u0012iGa\u001c\t\u000bm#\u0003\u0019A/\t\r\t\u0015D\u00051\u0001p\u0011\u0019\u0011\t\b\na\u0001_\u0006\u0019A-Z2\u0002\u0013!\fg\u000e\u001a7f\u0013:$H\u0003\u0003B<\u0005w\u0012iH!!\u0011\r\te\u0014q[A\u0012\u001d\u0011\t\u0019Na\u001f\t\u000bm+\u0003\u0019A/\t\r\t}T\u00051\u0001p\u0003\u0011q\u0017-\\3\t\r\t\u0015T\u00051\u0001p\u0003)A\u0017M\u001c3mK2{gn\u001a\u000b\t\u0005\u000f\u0013YIa%\u0003\u0016B1!\u0011RAl\u0005\u001bsA!a5\u0003\f\")1L\na\u0001;B\u00191Ia$\n\u0007\tEEI\u0001\u0003M_:<\u0007B\u0002B@M\u0001\u0007q\u000e\u0003\u0004\u0003f\u0019\u0002\ra\\\u0001\rQ\u0006tG\r\\3CS\u001eLe\u000e\u001e\u000b\t\u00057\u0013yJ!)\u0003$B1!QTAl\u0003[sA!a5\u0003 \")1l\na\u0001;\"1!qP\u0014A\u0002=DaA!\u001a(\u0001\u0004y\u0017\u0001\u00055b]\u0012dWMQ5h\t\u0016\u001c\u0017.\\1m))\u0011IK!,\u00036\n]&\u0011\u0018\t\u0007\u0005W\u000b9Na,\u000f\t\u0005M'Q\u0016\u0005\u00067\"\u0002\r!\u0018\t\u0004!\nE\u0016b\u0001BZ5\nQ!)[4EK\u000eLW.\u00197\t\r\t}\u0004\u00061\u0001p\u0011\u0019\u0011)\u0007\u000ba\u0001_\"1!\u0011\u000f\u0015A\u0002=\fQa]5J]R$BAa0\u0003FR\u0011!\u0011\u0019\t\u0007\u0005\u0007\f9.a\t\u000f\t\u0005M'Q\u0019\u0005\u00067&\u0002\r!X\u0001\u0007g&duN\\4\u0015\t\t-'\u0011\u001b\u000b\u0003\u0005\u001b\u0004bAa4\u0002X\n5e\u0002BAj\u0005#DQa\u0017\u0016A\u0002u\u000b\u0001b]5CS\u001eLe\u000e\u001e\u000b\u0005\u0005/\u0014i\u000e\u0006\u0002\u0003ZB1!1\\Al\u0003[sA!a5\u0003^\")1l\u000ba\u0001;\u0006a1/\u001b\"jO\u0012+7-[7bYR!!1\u001dBu)\t\u0011)\u000f\u0005\u0004\u0003h\u0006]'q\u0016\b\u0005\u0003'\u0014I\u000fC\u0003\\Y\u0001\u0007Q,A\u0003vg&sG\u000f\u0006\u0003\u0003p\nUHC\u0001By!\u0019\u0011\u00190a6\u0002$9!\u00111\u001bB{\u0011\u0015YV\u00061\u0001^\u0003\u0019)8\u000fT8oOR!!1`B\u0001)\t\u0011i\u0010\u0005\u0004\u0003\u0000\u0006]'Q\u0012\b\u0005\u0003'\u001c\t\u0001C\u0003\\]\u0001\u0007Q,\u0001\u0005vg\nKw-\u00138u)\u0011\u00199a!\u0004\u0015\u0005\r%\u0001CBB\u0006\u0003/\fiK\u0004\u0003\u0002T\u000e5\u0001\"B.0\u0001\u0004i\u0016\u0001D;t\u0005&<G)Z2j[\u0006dG\u0003BB\n\u00073!\"a!\u0006\u0011\r\r]\u0011q\u001bBX\u001d\u0011\t\u0019n!\u0007\t\u000bm\u0003\u0004\u0019A/\u0002\u000b\u0015,\u0018J\u001c;\u0015\t\r}1Q\u0005\u000b\u0003\u0007C\u0001baa\t\u0002X\u0006\rb\u0002BAj\u0007KAQaW\u0019A\u0002u\u000ba!Z;M_:<G\u0003BB\u0016\u0007c!\"a!\f\u0011\r\r=\u0012q\u001bBG\u001d\u0011\t\u0019n!\r\t\u000bm\u0013\u0004\u0019A/\u0002\u0011\u0015,()[4J]R$Baa\u000e\u0004>Q\u00111\u0011\b\t\u0007\u0007w\t9.!,\u000f\t\u0005M7Q\b\u0005\u00067N\u0002\r!X\u0001\rKV\u0014\u0015n\u001a#fG&l\u0017\r\u001c\u000b\u0005\u0007\u0007\u001aI\u0005\u0006\u0002\u0004FA11qIAl\u0005_sA!a5\u0004J!)1\f\u000ea\u0001;\u0006)!/\u00193jqR!1qJB+)\t\u0019\t\u0006\u0005\u0004\u0004T\u0005]\u00171\u0005\b\u0005\u0003'\u001c)\u0006C\u0003\\k\u0001\u0007Q,A\u0003j]R\f5/\u0006\u0003\u0004\\\r%D\u0003BB/\u0007K\"Baa\u0018\u0004\u0000Q!1\u0011MB;!\u0019\u0019\u0019'a6\u0004h9!\u00111[B3\u0011\u0015Yf\u00071\u0001^!\u0011\t\u0019n!\u001b\u0005\u000f\r-dG1\u0001\u0004n\t\t\u0011)\u0005\u0003\u0004p\u00055\u0002cA\"\u0004r%\u001911\u000f#\u0003\u000f9{G\u000f[5oO\"I1q\u000f\u001c\u0002\u0002\u0003\u000f1\u0011P\u0001\u000bKZLG-\u001a8dK\u0012\n\u0004CBB2\u0007w\u001a9'\u0003\u0003\u0004~\u0005m'aC,fC.$\u0016\u0010]3UC\u001eDqa!!7\u0001\u0004\u0019\u0019)\u0001\u0002fmB111MAl\u0007\u000b\u0003baa\"\u0004\u0014\u000e\u001dd\u0002BBE\u0007\u001fs1\u0001YBF\u0013\r\u0019iiO\u0001\bC2<WM\u0019:b\u0013\r96\u0011\u0013\u0006\u0004\u0007\u001b[\u0014\u0002BBK\u0007/\u0013AAU5oO*\u0019qk!%\u0002\u000b\u0011\u0014G.Q:\u0016\t\ru51\u0016\u000b\u0005\u0007?\u001b9\u000b\u0006\u0003\u0004\"\u000eMF\u0003BBR\u0007[\u0003ba!*\u0002X\u000e%f\u0002BAj\u0007OCQaW\u001cA\u0002u\u0003B!a5\u0004,\u0012911N\u001cC\u0002\r5\u0004\"CBXo\u0005\u0005\t9ABY\u0003))g/\u001b3f]\u000e,GE\r\t\u0007\u0007K\u001bYh!+\t\u000f\r\u0005u\u00071\u0001\u00046B11QUAl\u0007o\u0003baa\"\u0004:\u000e%\u0016\u0002BB^\u0007/\u0013QAR5fY\u0012\u0004"
)
public final class Macros {
   public static Exprs.Expr dblAs(final Context c, final Exprs.Expr ev, final TypeTags.WeakTypeTag evidence$2) {
      return Macros$.MODULE$.dblAs(c, ev, evidence$2);
   }

   public static Exprs.Expr intAs(final Context c, final Exprs.Expr ev, final TypeTags.WeakTypeTag evidence$1) {
      return Macros$.MODULE$.intAs(c, ev, evidence$1);
   }

   public static Exprs.Expr radix(final Context c) {
      return Macros$.MODULE$.radix(c);
   }

   public static Exprs.Expr euBigDecimal(final Context c) {
      return Macros$.MODULE$.euBigDecimal(c);
   }

   public static Exprs.Expr euBigInt(final Context c) {
      return Macros$.MODULE$.euBigInt(c);
   }

   public static Exprs.Expr euLong(final Context c) {
      return Macros$.MODULE$.euLong(c);
   }

   public static Exprs.Expr euInt(final Context c) {
      return Macros$.MODULE$.euInt(c);
   }

   public static Exprs.Expr usBigDecimal(final Context c) {
      return Macros$.MODULE$.usBigDecimal(c);
   }

   public static Exprs.Expr usBigInt(final Context c) {
      return Macros$.MODULE$.usBigInt(c);
   }

   public static Exprs.Expr usLong(final Context c) {
      return Macros$.MODULE$.usLong(c);
   }

   public static Exprs.Expr usInt(final Context c) {
      return Macros$.MODULE$.usInt(c);
   }

   public static Exprs.Expr siBigDecimal(final Context c) {
      return Macros$.MODULE$.siBigDecimal(c);
   }

   public static Exprs.Expr siBigInt(final Context c) {
      return Macros$.MODULE$.siBigInt(c);
   }

   public static Exprs.Expr siLong(final Context c) {
      return Macros$.MODULE$.siLong(c);
   }

   public static Exprs.Expr siInt(final Context c) {
      return Macros$.MODULE$.siInt(c);
   }

   public static Exprs.Expr handleBigDecimal(final Context c, final String name, final String sep, final String dec) {
      return Macros$.MODULE$.handleBigDecimal(c, name, sep, dec);
   }

   public static Exprs.Expr handleBigInt(final Context c, final String name, final String sep) {
      return Macros$.MODULE$.handleBigInt(c, name, sep);
   }

   public static Exprs.Expr handleLong(final Context c, final String name, final String sep) {
      return Macros$.MODULE$.handleLong(c, name, sep);
   }

   public static Exprs.Expr handleInt(final Context c, final String name, final String sep) {
      return Macros$.MODULE$.handleInt(c, name, sep);
   }

   public static String formatDecimal(final Context c, final String sep, final String dec) {
      return Macros$.MODULE$.formatDecimal(c, sep, dec);
   }

   public static String formatWhole(final Context c, final String sep) {
      return Macros$.MODULE$.formatWhole(c, sep);
   }

   public static Exprs.Expr rational(final Context c) {
      return Macros$.MODULE$.rational(c);
   }

   public static Exprs.Expr ulong(final Context c) {
      return Macros$.MODULE$.ulong(c);
   }

   public static Exprs.Expr uint(final Context c) {
      return Macros$.MODULE$.uint(c);
   }

   public static Exprs.Expr ushort(final Context c) {
      return Macros$.MODULE$.ushort(c);
   }

   public static Exprs.Expr short(final Context c) {
      return Macros$.MODULE$.short(c);
   }

   public static Exprs.Expr ubyte(final Context c) {
      return Macros$.MODULE$.ubyte(c);
   }

   public static Exprs.Expr byte(final Context c) {
      return Macros$.MODULE$.byte(c);
   }

   public static Either parseNumber(final String s, final BigInt lower, final BigInt upper) {
      return Macros$.MODULE$.parseNumber(s, lower, upper);
   }

   public static Either parseContext(final Context c, final BigInt lower, final BigInt upper) {
      return Macros$.MODULE$.parseContext(c, lower, upper);
   }

   public static class LiteralUtil implements Product, Serializable {
      private final Context c;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Context c() {
         return this.c;
      }

      public String getString() {
         Trees.TreeApi var3 = this.c().prefix().tree();
         if (var3 != null) {
            Option var4 = this.c().universe().ApplyTag().unapply(var3);
            if (!var4.isEmpty()) {
               Trees.ApplyApi var5 = (Trees.ApplyApi)var4.get();
               if (var5 != null) {
                  Option var6 = this.c().universe().Apply().unapply(var5);
                  if (!var6.isEmpty()) {
                     List var7 = (List)((Tuple2)var6.get())._2();
                     if (var7 != null) {
                        SeqOps var8 = .MODULE$.List().unapplySeq(var7);
                        if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var8) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var8)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var8), 1) == 0) {
                           Trees.TreeApi var9 = (Trees.TreeApi)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var8), 0);
                           if (var9 != null) {
                              Option var10 = this.c().universe().ApplyTag().unapply(var9);
                              if (!var10.isEmpty()) {
                                 Trees.ApplyApi var11 = (Trees.ApplyApi)var10.get();
                                 if (var11 != null) {
                                    Option var12 = this.c().universe().Apply().unapply(var11);
                                    if (!var12.isEmpty()) {
                                       List var13 = (List)((Tuple2)var12.get())._2();
                                       if (var13 != null) {
                                          SeqOps var14 = .MODULE$.List().unapplySeq(var13);
                                          if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var14) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var14)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var14), 1) == 0) {
                                             Trees.TreeApi var15 = (Trees.TreeApi)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var14), 0);
                                             if (var15 != null) {
                                                Option var16 = this.c().universe().LiteralTag().unapply(var15);
                                                if (!var16.isEmpty()) {
                                                   Trees.LiteralApi var17 = (Trees.LiteralApi)var16.get();
                                                   if (var17 != null) {
                                                      Option var18 = this.c().universe().Literal().unapply(var17);
                                                      if (!var18.isEmpty()) {
                                                         Constants.ConstantApi var19 = (Constants.ConstantApi)var18.get();
                                                         if (var19 != null) {
                                                            Option var20 = this.c().universe().ConstantTag().unapply(var19);
                                                            if (!var20.isEmpty()) {
                                                               Constants.ConstantApi var21 = (Constants.ConstantApi)var20.get();
                                                               if (var21 != null) {
                                                                  Option var22 = this.c().universe().Constant().unapply(var21);
                                                                  if (!var22.isEmpty()) {
                                                                     Object s = var22.get();
                                                                     if (s instanceof String) {
                                                                        String var24 = (String)s;
                                                                        return var24;
                                                                     }
                                                                  }
                                                               }
                                                            }
                                                         }
                                                      }
                                                   }
                                                }
                                             }
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         throw new MatchError(var3);
      }

      public LiteralUtil copy(final Context c) {
         return new LiteralUtil(c);
      }

      public Context copy$default$1() {
         return this.c();
      }

      public String productPrefix() {
         return "LiteralUtil";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.c();
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof LiteralUtil;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "c";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var7;
         if (this != x$1) {
            label53: {
               boolean var2;
               if (x$1 instanceof LiteralUtil) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label36: {
                     label35: {
                        LiteralUtil var4 = (LiteralUtil)x$1;
                        Context var10000 = this.c();
                        Context var5 = var4.c();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label35;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label35;
                        }

                        if (var4.canEqual(this)) {
                           var7 = true;
                           break label36;
                        }
                     }

                     var7 = false;
                  }

                  if (var7) {
                     break label53;
                  }
               }

               var7 = false;
               return var7;
            }
         }

         var7 = true;
         return var7;
      }

      public LiteralUtil(final Context c) {
         this.c = c;
         Product.$init$(this);
      }
   }

   public static class LiteralUtil$ extends AbstractFunction1 implements Serializable {
      public static final LiteralUtil$ MODULE$ = new LiteralUtil$();

      public final String toString() {
         return "LiteralUtil";
      }

      public LiteralUtil apply(final Context c) {
         return new LiteralUtil(c);
      }

      public Option unapply(final LiteralUtil x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.c()));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(LiteralUtil$.class);
      }
   }
}
