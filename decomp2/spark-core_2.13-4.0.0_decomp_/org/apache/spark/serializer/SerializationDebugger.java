package org.apache.spark.serializer;

import java.io.Externalizable;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.ObjectStreamField;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.apache.spark.internal.Logging;
import scala.MatchError;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.immutable.List;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.HashSet;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\r-uAB.]\u0011\u0003qFM\u0002\u0004g9\"\u0005al\u001a\u0005\u0006i\u0006!\tA\u001e\u0005\u0006o\u0006!\t\u0001\u001f\u0005\t\u0003#\tA\u0011\u0001/\u0002\u0014!Q\u0011qH\u0001A\u0002\u0013\u0005A,!\u0011\t\u0015\u0005%\u0013\u00011A\u0005\u0002q\u000bY\u0005\u0003\u0005\u0002X\u0005\u0001\u000b\u0015BA\"\r\u00151\u0017\u0001BA-\u0011\u0019!\b\u0002\"\u0001\u0002\\!I\u0011\u0011\r\u0005C\u0002\u0013%\u00111\r\u0005\t\u0003kB\u0001\u0015!\u0003\u0002f!9\u0011q\u000f\u0005\u0005\u0002\u0005e\u0004bBAB\u0011\u0011%\u0011Q\u0011\u0005\b\u0003GCA\u0011BAS\u0011\u001d\t\t\f\u0003C\u0005\u0003gCq!!2\t\t\u0013\t9\rC\u0004\u0002N\u0006!I!a4\u0007\r\u00055\u0018\u0001BAx\u0011\u0019!(\u0003\"\u0001\u0002x\"I\u00111 \nC\u0002\u0013%\u0011Q \u0005\t\u0005\u000b\u0011\u0002\u0015!\u0003\u0002\u0000\"9!q\u0001\n\u0005\u0002\t%\u0001b\u0002B\u0007%\u0011\u0005#q\u0002\u0005\b\u0005'\u0011B\u0011\tB\u000b\u0011\u001d\u00119B\u0005C!\u00053AqAa\u0006\u0013\t\u0003\u0012)\u0003C\u0004\u0003\u0018I!\tEa\r\t\u000f\tu\"\u0003\"\u0011\u0003\u0016!9!q\b\n\u0005B\t\u0005\u0003b\u0002B'%\u0011\u0005#q\n\u0005\b\u0005+\u0012B\u0011\tB,\u0011\u001d\u0011\tG\u0005C!\u0005GBqAa\u001a\u0013\t\u0003\u0012I\u0007C\u0004\u0003nI!\tEa\u001c\t\u000f\tM$\u0003\"\u0011\u0003v!9!1\u0010\n\u0005B\tu\u0004b\u0002BA%\u0011\u0005#1\u0011\u0005\b\u0005\u000f\u0013B\u0011\tBE\u0011\u001d\u0011)J\u0005C!\u0005/3aAa'\u0002\t\tu\u0005B\u0002;)\t\u0003\u0011)\u000bC\u0004\u0003\u0018!\"\tE!+\u0007\r\t5\u0016\u0001\u0002BX\u0011\u0019!8\u0006\"\u0001\u00038\"I\u00111`\u0016C\u0002\u0013%\u0011Q \u0005\t\u0005\u000bY\u0003\u0015!\u0003\u0002\u0000\"9!qA\u0016\u0005\u0002\t%\u0001b\u0002B^W\u0011\u0005#Q\u0018\u0004\u0007\u0005\u0003\f1Aa1\t\u0015\t-\u0017G!b\u0001\n\u0003\u0011i\r\u0003\u0006\u0003PF\u0012\t\u0011)A\u0005\u0003/Da\u0001^\u0019\u0005\u0002\tE\u0007b\u0002Blc\u0011\u0005!\u0011\u001c\u0005\b\u0005;\fD\u0011AA!\u0011\u001d\u0011y.\rC\u0001\u0003\u0003BqA!92\t\u0003\u0011\u0019\u000fC\u0004\u0003hF\"\tA!;\t\u000f\t-\u0018\u0007\"\u0001\u0003n\"I!q_\u0019\u0002\u0002\u0013\u0005#\u0011 \u0005\n\u0005w\f\u0014\u0011!C!\u0005{<\u0011b!\u0001\u0002\u0003\u0003E\taa\u0001\u0007\u0013\t\u0005\u0017!!A\t\u0002\r\u0015\u0001B\u0002;?\t\u0003\u00199\u0001C\u0004\u0004\ny\")aa\u0003\t\u000f\rEa\b\"\u0002\u0004\u0014!91q\u0003 \u0005\u0006\re\u0001bBB\u000f}\u0011\u00151q\u0004\u0005\b\u0007OqDQAB\u0015\u0011\u001d\u0019iC\u0010C\u0003\u0007_A\u0011b!\u000f?\u0003\u0003%)aa\u000f\t\u0013\r}b(!A\u0005\u0006\r\u0005\u0003\"CB\u0001\u0003\u0005\u0005I1AB%\u0011%\u0019i%\u0001b\u0001\n\u0013\u0019y\u0005\u0003\u0005\u0004\n\u0006\u0001\u000b\u0011BB)\r\u0019\u0019\u0019&\u0001\u0003\u0004V!1Ao\u0013C\u0001\u0007/B\u0011b!\u0017L\u0005\u0004%\taa\u0017\t\u0011\r\u001d4\n)A\u0005\u0007;B\u0011b!\u001bL\u0005\u0004%\taa\u0017\t\u0011\r-4\n)A\u0005\u0007;B\u0011b!\u001cL\u0005\u0004%\taa\u0017\t\u0011\r=4\n)A\u0005\u0007;B\u0011b!\u001dL\u0005\u0004%\taa\u0017\t\u0011\rM4\n)A\u0005\u0007;B\u0011b!\u001eL\u0005\u0004%\taa\u0017\t\u0011\r]4\n)A\u0005\u0007;B\u0011b!\u001fL\u0005\u0004%\taa\u0017\t\u0011\rm4\n)A\u0005\u0007;B\u0011b! L\u0005\u0004%\taa \t\u0011\r\u001d5\n)A\u0005\u0007\u0003\u000bQcU3sS\u0006d\u0017N_1uS>tG)\u001a2vO\u001e,'O\u0003\u0002^=\u0006Q1/\u001a:jC2L'0\u001a:\u000b\u0005}\u0003\u0017!B:qCJ\\'BA1c\u0003\u0019\t\u0007/Y2iK*\t1-A\u0002pe\u001e\u0004\"!Z\u0001\u000e\u0003q\u0013QcU3sS\u0006d\u0017N_1uS>tG)\u001a2vO\u001e,'oE\u0002\u0002Q:\u0004\"!\u001b7\u000e\u0003)T\u0011a[\u0001\u0006g\u000e\fG.Y\u0005\u0003[*\u0014a!\u00118z%\u00164\u0007CA8s\u001b\u0005\u0001(BA9_\u0003!Ig\u000e^3s]\u0006d\u0017BA:q\u0005\u001daunZ4j]\u001e\fa\u0001P5oSRt4\u0001\u0001\u000b\u0002I\u0006\u0001\u0012.\u001c9s_Z,W\t_2faRLwN\u001c\u000b\u0006s\u0006\r\u0011Q\u0002\t\u0003u~l\u0011a\u001f\u0006\u0003yv\f!![8\u000b\u0003y\fAA[1wC&\u0019\u0011\u0011A>\u000319{GoU3sS\u0006d\u0017N_1cY\u0016,\u0005pY3qi&|g\u000eC\u0004\u0002\u0006\r\u0001\r!a\u0002\u0002\u0007=\u0014'\u000eE\u0002j\u0003\u0013I1!a\u0003k\u0005\r\te.\u001f\u0005\u0007\u0003\u001f\u0019\u0001\u0019A=\u0002\u0003\u0015\fAAZ5oIR!\u0011QCA\u001f!\u0019\t9\"a\n\u0002.9!\u0011\u0011DA\u0012\u001d\u0011\tY\"!\t\u000e\u0005\u0005u!bAA\u0010k\u00061AH]8pizJ\u0011a[\u0005\u0004\u0003KQ\u0017a\u00029bG.\fw-Z\u0005\u0005\u0003S\tYC\u0001\u0003MSN$(bAA\u0013UB!\u0011qFA\u001c\u001d\u0011\t\t$a\r\u0011\u0007\u0005m!.C\u0002\u00026)\fa\u0001\u0015:fI\u00164\u0017\u0002BA\u001d\u0003w\u0011aa\u0015;sS:<'bAA\u001bU\"9\u0011Q\u0001\u0003A\u0002\u0005\u001d\u0011aD3oC\ndW\rR3ck\u001e<\u0017N\\4\u0016\u0005\u0005\r\u0003cA5\u0002F%\u0019\u0011q\t6\u0003\u000f\t{w\u000e\\3b]\u0006\u0019RM\\1cY\u0016$UMY;hO&twm\u0018\u0013fcR!\u0011QJA*!\rI\u0017qJ\u0005\u0004\u0003#R'\u0001B+oSRD\u0011\"!\u0016\u0007\u0003\u0003\u0005\r!a\u0011\u0002\u0007a$\u0013'\u0001\tf]\u0006\u0014G.\u001a#fEV<w-\u001b8hAM\u0011\u0001\u0002\u001b\u000b\u0003\u0003;\u00022!a\u0018\t\u001b\u0005\t\u0011a\u0002<jg&$X\rZ\u000b\u0003\u0003K\u0002b!a\u001a\u0002r\u0005\u001dQBAA5\u0015\u0011\tY'!\u001c\u0002\u000f5,H/\u00192mK*\u0019\u0011q\u000e6\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002t\u0005%$a\u0002%bg\"\u001cV\r^\u0001\tm&\u001c\u0018\u000e^3eA\u0005)a/[:jiR1\u0011QCA>\u0003\u007fBq!! \r\u0001\u0004\t9!A\u0001p\u0011\u001d\t\t\t\u0004a\u0001\u0003+\tQa\u001d;bG.\f!B^5tSR\f%O]1z)\u0019\t)\"a\"\u0002\"\"9\u0011QP\u0007A\u0002\u0005%\u0005\u0007BAF\u0003+\u0003R![AG\u0003#K1!a$k\u0005\u0015\t%O]1z!\u0011\t\u0019*!&\r\u0001\u0011a\u0011qSAD\u0003\u0003\u0005\tQ!\u0001\u0002\u001a\n\u0019q\f\n\u001a\u0012\t\u0005m\u0015q\u0001\t\u0004S\u0006u\u0015bAAPU\n9aj\u001c;iS:<\u0007bBAA\u001b\u0001\u0007\u0011QC\u0001\u0014m&\u001c\u0018\u000e^#yi\u0016\u0014h.\u00197ju\u0006\u0014G.\u001a\u000b\u0007\u0003+\t9+a,\t\u000f\u0005ud\u00021\u0001\u0002*B\u0019!0a+\n\u0007\u000556P\u0001\bFqR,'O\\1mSj\f'\r\\3\t\u000f\u0005\u0005e\u00021\u0001\u0002\u0016\u0005\tb/[:jiN+'/[1mSj\f'\r\\3\u0015\r\u0005U\u0011QWAb\u0011\u001d\tih\u0004a\u0001\u0003o\u0003B!!/\u0002@6\u0011\u00111\u0018\u0006\u0004\u0003{k\u0018\u0001\u00027b]\u001eLA!!1\u0002<\n1qJ\u00196fGRDq!!!\u0010\u0001\u0004\t)\"\u0001\u0014wSNLGoU3sS\u0006d\u0017N_1cY\u0016<\u0016\u000e\u001e5Xe&$Xm\u00142kK\u000e$X*\u001a;i_\u0012$b!!\u0006\u0002J\u0006-\u0007bBA?!\u0001\u0007\u0011q\u0017\u0005\b\u0003\u0003\u0003\u0002\u0019AA\u000b\u0003]1\u0017N\u001c3PE*,7\r^!oI\u0012+7o\u0019:jaR|'\u000f\u0006\u0003\u0002R\u0006u\u0007cB5\u0002T\u0006]\u0016q[\u0005\u0004\u0003+T'A\u0002+va2,'\u0007E\u0002{\u00033L1!a7|\u0005Ey%M[3diN#(/Z1n\u00072\f7o\u001d\u0005\b\u0003{\n\u0002\u0019AA\\Q\r\t\u0012\u0011\u001d\t\u0005\u0003G\fI/\u0004\u0002\u0002f*\u0019\u0011q\u001d6\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002l\u0006\u0015(a\u0002;bS2\u0014Xm\u0019\u0002\u0011\u0019&\u001cHo\u00142kK\u000e$x*\u001e;qkR\u001cRAEA\\\u0003c\u00042A_Az\u0013\r\t)p\u001f\u0002\r\u001f\nTWm\u0019;PkR\u0004X\u000f\u001e\u000b\u0003\u0003s\u00042!a\u0018\u0013\u0003\u0019yW\u000f\u001e9viV\u0011\u0011q \t\u0007\u0003O\u0012\t!a\u0002\n\t\t\r\u0011\u0011\u000e\u0002\f\u0003J\u0014\u0018-\u001f\"vM\u001a,'/A\u0004pkR\u0004X\u000f\u001e\u0011\u0002\u0017=,H\u000f];u\u0003J\u0014\u0018-_\u000b\u0003\u0005\u0017\u0001R![AG\u0003\u000f\t1b\u001e:ji\u0016|%M[3diR!\u0011Q\nB\t\u0011\u001d\tih\u0006a\u0001\u0003\u000f\tQA\u001a7vg\"$\"!!\u0014\u0002\u000b]\u0014\u0018\u000e^3\u0015\t\u00055#1\u0004\u0005\b\u0005;I\u0002\u0019\u0001B\u0010\u0003\u0005I\u0007cA5\u0003\"%\u0019!1\u00056\u0003\u0007%sG\u000f\u0006\u0003\u0002N\t\u001d\u0002b\u0002B\u00155\u0001\u0007!1F\u0001\u0006Ef$Xm\u001d\t\u0006S\u00065%Q\u0006\t\u0004S\n=\u0012b\u0001B\u0019U\n!!)\u001f;f)!\tiE!\u000e\u00038\te\u0002b\u0002B\u00157\u0001\u0007!1\u0006\u0005\b\u0005;Y\u0002\u0019\u0001B\u0010\u0011\u001d\u0011Yd\u0007a\u0001\u0005?\t!![\u0019\u0002\u000b\rdwn]3\u0002\u0015]\u0014\u0018\u000e^3GY>\fG\u000f\u0006\u0003\u0002N\t\r\u0003b\u0002B#;\u0001\u0007!qI\u0001\u0002mB\u0019\u0011N!\u0013\n\u0007\t-#NA\u0003GY>\fG/\u0001\u0006xe&$Xm\u00115beN$B!!\u0014\u0003R!9!1\u000b\u0010A\u0002\u00055\u0012!A:\u0002\u0017]\u0014\u0018\u000e^3E_V\u0014G.\u001a\u000b\u0005\u0003\u001b\u0012I\u0006C\u0004\u0003F}\u0001\rAa\u0017\u0011\u0007%\u0014i&C\u0002\u0003`)\u0014a\u0001R8vE2,\u0017\u0001C<sSR,W\u000b\u0016$\u0015\t\u00055#Q\r\u0005\b\u0005'\u0002\u0003\u0019AA\u0017\u0003)9(/\u001b;f'\"|'\u000f\u001e\u000b\u0005\u0003\u001b\u0012Y\u0007C\u0004\u0003\u001e\u0005\u0002\rAa\b\u0002\u0011]\u0014\u0018\u000e^3J]R$B!!\u0014\u0003r!9!Q\u0004\u0012A\u0002\t}\u0011\u0001D<sSR,'i\\8mK\u0006tG\u0003BA'\u0005oBqA!\u001f$\u0001\u0004\t\u0019%A\u0001c\u0003)9(/\u001b;f\u0005f$Xm\u001d\u000b\u0005\u0003\u001b\u0012y\bC\u0004\u0003T\u0011\u0002\r!!\f\u0002\u0013]\u0014\u0018\u000e^3DQ\u0006\u0014H\u0003BA'\u0005\u000bCqA!\b&\u0001\u0004\u0011y\"A\u0005xe&$X\rT8oOR!\u0011Q\nBF\u0011\u001d\u0011iI\na\u0001\u0005\u001f\u000b\u0011\u0001\u001c\t\u0004S\nE\u0015b\u0001BJU\n!Aj\u001c8h\u0003%9(/\u001b;f\u0005f$X\r\u0006\u0003\u0002N\te\u0005b\u0002B\u000fO\u0001\u0007!q\u0004\u0002\u0011\u001dVdGnT;uaV$8\u000b\u001e:fC6\u001c2\u0001\u000bBP!\rQ(\u0011U\u0005\u0004\u0005G[(\u0001D(viB,Ho\u0015;sK\u0006lGC\u0001BT!\r\ty\u0006\u000b\u000b\u0005\u0003\u001b\u0012Y\u000bC\u0004\u0003z)\u0002\rAa\b\u0003-1K7\u000f^(cU\u0016\u001cGoT;uaV$8\u000b\u001e:fC6\u001c2a\u000bBY!\rQ(1W\u0005\u0004\u0005k[(AE(cU\u0016\u001cGoT;uaV$8\u000b\u001e:fC6$\"A!/\u0011\u0007\u0005}3&A\u0007sKBd\u0017mY3PE*,7\r\u001e\u000b\u0005\u0003o\u0013y\fC\u0004\u0002\u0006A\u0002\r!a.\u00031=\u0013'.Z2u'R\u0014X-Y7DY\u0006\u001c8/T3uQ>$7oE\u00022\u0005\u000b\u00042!\u001bBd\u0013\r\u0011IM\u001b\u0002\u0007\u0003:Lh+\u00197\u0002\t\u0011,7oY\u000b\u0003\u0003/\fQ\u0001Z3tG\u0002\"BAa5\u0003VB\u0019\u0011qL\u0019\t\u000f\t-G\u00071\u0001\u0002X\u0006aq-\u001a;TY>$H)Z:dgV\u0011!1\u001c\t\u0006S\u00065\u0015q[\u0001\u0015Q\u0006\u001cxK]5uK>\u0013'.Z2u\u001b\u0016$\bn\u001c3\u0002+!\f7o\u0016:ji\u0016\u0014V\r\u001d7bG\u0016lU\r\u001e5pI\u0006\u0011\u0012N\u001c<pW\u0016<&/\u001b;f%\u0016\u0004H.Y2f)\u0011\t9L!:\t\u000f\u0005\u0015\u0001\b1\u0001\u00028\u0006yq-\u001a;Ok6|%M\u001b$jK2$7/\u0006\u0002\u0003 \u0005\tr-\u001a;PE*4\u0015.\u001a7e-\u0006dW/Z:\u0015\r\u00055#q\u001eBy\u0011\u001d\t)A\u000fa\u0001\u0003oCqAa=;\u0001\u0004\u0011)0A\u0002pkR\u0004R![AG\u0003o\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0005?\ta!Z9vC2\u001cH\u0003BA\"\u0005\u007fD\u0011\"!\u0016=\u0003\u0003\u0005\r!a\u0002\u00021=\u0013'.Z2u'R\u0014X-Y7DY\u0006\u001c8/T3uQ>$7\u000fE\u0002\u0002`y\u001a\"A\u00105\u0015\u0005\r\r\u0011AF4fiNcw\u000e\u001e#fg\u000e\u001cH%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\tm7Q\u0002\u0005\b\u0007\u001f\u0001\u0005\u0019\u0001Bj\u0003\u0015!C\u000f[5t\u0003yA\u0017m],sSR,wJ\u00196fGRlU\r\u001e5pI\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0002D\rU\u0001bBB\b\u0003\u0002\u0007!1[\u0001 Q\u0006\u001cxK]5uKJ+\u0007\u000f\\1dK6+G\u000f[8eI\u0015DH/\u001a8tS>tG\u0003BA\"\u00077Aqaa\u0004C\u0001\u0004\u0011\u0019.\u0001\u000fj]Z|7.Z,sSR,'+\u001a9mC\u000e,G%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\r\u00052Q\u0005\u000b\u0005\u0003o\u001b\u0019\u0003C\u0004\u0002\u0006\r\u0003\r!a.\t\u000f\r=1\t1\u0001\u0003T\u0006Ir-\u001a;Ok6|%M\u001b$jK2$7\u000fJ3yi\u0016t7/[8o)\u0011\u0011yba\u000b\t\u000f\r=A\t1\u0001\u0003T\u0006Yr-\u001a;PE*4\u0015.\u001a7e-\u0006dW/Z:%Kb$XM\\:j_:$Ba!\r\u00048Q1\u0011QJB\u001a\u0007kAq!!\u0002F\u0001\u0004\t9\fC\u0004\u0003t\u0016\u0003\rA!>\t\u000f\r=Q\t1\u0001\u0003T\u0006\u0011\u0002.Y:i\u0007>$W\rJ3yi\u0016t7/[8o)\u0011\u0011Ip!\u0010\t\u000f\r=a\t1\u0001\u0003T\u0006\u0001R-];bYN$S\r\u001f;f]NLwN\u001c\u000b\u0005\u0007\u0007\u001a9\u0005\u0006\u0003\u0002D\r\u0015\u0003\"CA+\u000f\u0006\u0005\t\u0019AA\u0004\u0011\u001d\u0019ya\u0012a\u0001\u0005'$BAa5\u0004L!9!1\u001a%A\u0002\u0005]\u0017a\u0002:fM2,7\r^\u000b\u0003\u0007#\u00022!a\u0018L\u0005my%M[3diN#(/Z1n\u00072\f7o\u001d*fM2,7\r^5p]N\u00111\n\u001b\u000b\u0003\u0007#\n!cR3u\u00072\f7o\u001d#bi\u0006d\u0015-_8viV\u00111Q\f\t\u0005\u0007?\u001a\u0019'\u0004\u0002\u0004b)!1QJA^\u0013\u0011\u0019)g!\u0019\u0003\r5+G\u000f[8e\u0003M9U\r^\"mCN\u001cH)\u0019;b\u0019\u0006Lx.\u001e;!\u0003QA\u0015m],sSR,wJ\u00196fGRlU\r\u001e5pI\u0006)\u0002*Y:Xe&$Xm\u00142kK\u000e$X*\u001a;i_\u0012\u0004\u0013!\u0006%bg^\u0013\u0018\u000e^3SKBd\u0017mY3NKRDw\u000eZ\u0001\u0017\u0011\u0006\u001cxK]5uKJ+\u0007\u000f\\1dK6+G\u000f[8eA\u0005\u0011\u0012J\u001c<pW\u0016<&/\u001b;f%\u0016\u0004H.Y2f\u0003MIeN^8lK^\u0013\u0018\u000e^3SKBd\u0017mY3!\u0003=9U\r\u001e(v[>\u0013'NR5fY\u0012\u001c\u0018\u0001E$fi:+Xn\u00142k\r&,G\u000eZ:!\u0003E9U\r^(cU\u001aKW\r\u001c3WC2,Xm]\u0001\u0013\u000f\u0016$xJ\u00196GS\u0016dGMV1mk\u0016\u001c\b%A\u0005EKN\u001cg)[3mIV\u00111\u0011\u0011\t\u0005\u0007?\u001a\u0019)\u0003\u0003\u0004\u0006\u000e\u0005$!\u0002$jK2$\u0017A\u0003#fg\u000e4\u0015.\u001a7eA\u0005A!/\u001a4mK\u000e$\b\u0005"
)
public final class SerializationDebugger {
   public static ObjectStreamClass ObjectStreamClassMethods(final ObjectStreamClass desc) {
      return SerializationDebugger$.MODULE$.ObjectStreamClassMethods(desc);
   }

   public static NotSerializableException improveException(final Object obj, final NotSerializableException e) {
      return SerializationDebugger$.MODULE$.improveException(obj, e);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return SerializationDebugger$.MODULE$.LogStringContext(sc);
   }

   private static class SerializationDebugger {
      private final HashSet visited = new HashSet();

      private HashSet visited() {
         return this.visited;
      }

      public List visit(final Object o, final List stack) {
         if (o == null) {
            return .MODULE$.List().empty();
         } else if (this.visited().contains(o)) {
            return .MODULE$.List().empty();
         } else {
            this.visited().$plus$eq(o);
            if (o.getClass().isPrimitive()) {
               return .MODULE$.List().empty();
            } else if (o instanceof String) {
               return .MODULE$.List().empty();
            } else if (o.getClass().isArray() && o.getClass().getComponentType().isPrimitive()) {
               return .MODULE$.List().empty();
            } else if (scala.runtime.ScalaRunTime..MODULE$.isArray(o, 1) && o.getClass().isArray() && !o.getClass().getComponentType().isPrimitive()) {
               String var17 = o.getClass().getName();
               String elem = "array (class " + var17 + ", size " + scala.runtime.ScalaRunTime..MODULE$.array_length(o) + ")";
               return this.visitArray(o, stack.$colon$colon(elem));
            } else if (o instanceof Externalizable) {
               Externalizable var8 = (Externalizable)o;
               String var16 = var8.getClass().getName();
               String elem = "externalizable object (class " + var16 + ", " + var8 + ")";
               return this.visitExternalizable(var8, stack.$colon$colon(elem));
            } else if (o instanceof Object && o instanceof Serializable) {
               String var15 = o.getClass().getName();
               String elem = "object (class " + var15 + ", " + o + ")";
               return this.visitSerializable(o, stack.$colon$colon(elem));
            } else {
               String var10000 = o.getClass().getName();
               String var14 = "object not serializable (class: " + var10000 + ", value: " + o + ")";
               return stack.$colon$colon(var14);
            }
         }
      }

      private List visitArray(final Object o, final List stack) {
         for(int i = 0; i < scala.runtime.ScalaRunTime..MODULE$.array_length(o); ++i) {
            Object var10001 = scala.runtime.ScalaRunTime..MODULE$.array_apply(o, i);
            String var5 = "element of array (index: " + i + ")";
            List childStack = this.visit(var10001, stack.$colon$colon(var5));
            if (childStack.nonEmpty()) {
               return childStack;
            }
         }

         return .MODULE$.List().empty();
      }

      private List visitExternalizable(final Externalizable o, final List stack) {
         org.apache.spark.serializer.SerializationDebugger.ListObjectOutput fieldList = new org.apache.spark.serializer.SerializationDebugger.ListObjectOutput();
         o.writeExternal(fieldList);
         Object[] childObjects = fieldList.outputArray();

         for(int i = 0; i < childObjects.length; ++i) {
            List childStack = this.visit(childObjects[i], stack.$colon$colon("writeExternal data"));
            if (childStack.nonEmpty()) {
               return childStack;
            }
         }

         return .MODULE$.List().empty();
      }

      private List visitSerializable(final Object o, final List stack) {
         Tuple2 var5 = SerializationDebugger$.MODULE$.org$apache$spark$serializer$SerializationDebugger$$findObjectAndDescriptor(o);
         if (var5 == null) {
            throw new MatchError(var5);
         } else {
            Object finalObj;
            label59: {
               Object finalObj = var5._1();
               ObjectStreamClass desc = (ObjectStreamClass)var5._2();
               Tuple2 var4 = new Tuple2(finalObj, desc);
               finalObj = var4._1();
               ObjectStreamClass desc = (ObjectStreamClass)var4._2();
               Class var10000 = finalObj.getClass();
               Class var10 = o.getClass();
               if (var10000 == null) {
                  if (var10 != null) {
                     break label59;
                  }
               } else if (!var10000.equals(var10)) {
                  break label59;
               }

               ObjectStreamClass[] slotDescs = org.apache.spark.serializer.SerializationDebugger.ObjectStreamClassMethods$.MODULE$.getSlotDescs$extension(SerializationDebugger$.MODULE$.ObjectStreamClassMethods(desc));

               for(int i = 0; i < slotDescs.length; ++i) {
                  ObjectStreamClass slotDesc = slotDescs[i];
                  if (org.apache.spark.serializer.SerializationDebugger.ObjectStreamClassMethods$.MODULE$.hasWriteObjectMethod$extension(SerializationDebugger$.MODULE$.ObjectStreamClassMethods(slotDesc))) {
                     String elem = "writeObject data (class: " + slotDesc.getName() + ")";
                     List childStack = this.visitSerializableWithWriteObjectMethod(finalObj, stack.$colon$colon(elem));
                     if (childStack.nonEmpty()) {
                        return childStack;
                     }
                  } else {
                     ObjectStreamField[] fields = slotDesc.getFields();
                     Object[] objFieldValues = new Object[org.apache.spark.serializer.SerializationDebugger.ObjectStreamClassMethods$.MODULE$.getNumObjFields$extension(SerializationDebugger$.MODULE$.ObjectStreamClassMethods(slotDesc))];
                     int numPrims = fields.length - objFieldValues.length;
                     org.apache.spark.serializer.SerializationDebugger.ObjectStreamClassMethods$.MODULE$.getObjFieldValues$extension(SerializationDebugger$.MODULE$.ObjectStreamClassMethods(slotDesc), finalObj, objFieldValues);

                     for(int j = 0; j < objFieldValues.length; ++j) {
                        ObjectStreamField fieldDesc = fields[numPrims + j];
                        String var26 = slotDesc.getName();
                        String elem = "field (class: " + var26 + ", name: " + fieldDesc.getName() + ", type: " + fieldDesc.getType() + ")";
                        List childStack = this.visit(objFieldValues[j], stack.$colon$colon(elem));
                        if (childStack.nonEmpty()) {
                           return childStack;
                        }
                     }
                  }
               }

               return .MODULE$.List().empty();
            }

            String var11 = "writeReplace data (class: " + finalObj.getClass().getName() + ")";
            return this.visit(finalObj, stack.$colon$colon(var11));
         }
      }

      private List visitSerializableWithWriteObjectMethod(final Object o, final List stack) {
         org.apache.spark.serializer.SerializationDebugger.ListObjectOutputStream innerObjectsCatcher = new org.apache.spark.serializer.SerializationDebugger.ListObjectOutputStream();
         boolean notSerializableFound = false;

         try {
            innerObjectsCatcher.writeObject(o);
         } catch (IOException var9) {
            notSerializableFound = true;
         }

         if (notSerializableFound) {
            Object[] innerObjects = innerObjectsCatcher.outputArray();

            for(int k = 0; k < innerObjects.length; ++k) {
               List childStack = this.visit(innerObjects[k], stack);
               if (childStack.nonEmpty()) {
                  return childStack;
               }
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            this.visited().$plus$plus$eq(scala.Predef..MODULE$.genericWrapArray(innerObjectsCatcher.outputArray()));
         }

         return .MODULE$.List().empty();
      }

      public SerializationDebugger() {
      }
   }

   private static class ListObjectOutput implements ObjectOutput {
      private final ArrayBuffer output = new ArrayBuffer();

      private ArrayBuffer output() {
         return this.output;
      }

      public Object[] outputArray() {
         return this.output().toArray(scala.reflect.ClassTag..MODULE$.Any());
      }

      public void writeObject(final Object o) {
         this.output().$plus$eq(o);
      }

      public void flush() {
      }

      public void write(final int i) {
      }

      public void write(final byte[] bytes) {
      }

      public void write(final byte[] bytes, final int i, final int i1) {
      }

      public void close() {
      }

      public void writeFloat(final float v) {
      }

      public void writeChars(final String s) {
      }

      public void writeDouble(final double v) {
      }

      public void writeUTF(final String s) {
      }

      public void writeShort(final int i) {
      }

      public void writeInt(final int i) {
      }

      public void writeBoolean(final boolean b) {
      }

      public void writeBytes(final String s) {
      }

      public void writeChar(final int i) {
      }

      public void writeLong(final long l) {
      }

      public void writeByte(final int i) {
      }

      public ListObjectOutput() {
      }
   }

   private static class NullOutputStream extends OutputStream {
      public void write(final int b) {
      }

      public NullOutputStream() {
      }
   }

   private static class ListObjectOutputStream extends ObjectOutputStream {
      private final ArrayBuffer output = new ArrayBuffer();

      private ArrayBuffer output() {
         return this.output;
      }

      public Object[] outputArray() {
         return this.output().toArray(scala.reflect.ClassTag..MODULE$.Any());
      }

      public Object replaceObject(final Object obj) {
         this.output().$plus$eq(obj);
         return obj;
      }

      public ListObjectOutputStream() {
         super(new org.apache.spark.serializer.SerializationDebugger.NullOutputStream());
         this.enableReplaceObject(true);
      }
   }

   public static final class ObjectStreamClassMethods {
      private final ObjectStreamClass desc;

      public ObjectStreamClass desc() {
         return this.desc;
      }

      public ObjectStreamClass[] getSlotDescs() {
         return org.apache.spark.serializer.SerializationDebugger.ObjectStreamClassMethods$.MODULE$.getSlotDescs$extension(this.desc());
      }

      public boolean hasWriteObjectMethod() {
         return org.apache.spark.serializer.SerializationDebugger.ObjectStreamClassMethods$.MODULE$.hasWriteObjectMethod$extension(this.desc());
      }

      public boolean hasWriteReplaceMethod() {
         return org.apache.spark.serializer.SerializationDebugger.ObjectStreamClassMethods$.MODULE$.hasWriteReplaceMethod$extension(this.desc());
      }

      public Object invokeWriteReplace(final Object obj) {
         return org.apache.spark.serializer.SerializationDebugger.ObjectStreamClassMethods$.MODULE$.invokeWriteReplace$extension(this.desc(), obj);
      }

      public int getNumObjFields() {
         return org.apache.spark.serializer.SerializationDebugger.ObjectStreamClassMethods$.MODULE$.getNumObjFields$extension(this.desc());
      }

      public void getObjFieldValues(final Object obj, final Object[] out) {
         org.apache.spark.serializer.SerializationDebugger.ObjectStreamClassMethods$.MODULE$.getObjFieldValues$extension(this.desc(), obj, out);
      }

      public int hashCode() {
         return org.apache.spark.serializer.SerializationDebugger.ObjectStreamClassMethods$.MODULE$.hashCode$extension(this.desc());
      }

      public boolean equals(final Object x$1) {
         return org.apache.spark.serializer.SerializationDebugger.ObjectStreamClassMethods$.MODULE$.equals$extension(this.desc(), x$1);
      }

      public ObjectStreamClassMethods(final ObjectStreamClass desc) {
         this.desc = desc;
      }
   }

   public static class ObjectStreamClassMethods$ {
      public static final org.apache.spark.serializer.SerializationDebugger.ObjectStreamClassMethods$ MODULE$ = new org.apache.spark.serializer.SerializationDebugger.ObjectStreamClassMethods$();

      public final ObjectStreamClass[] getSlotDescs$extension(final ObjectStreamClass $this) {
         return (ObjectStreamClass[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(SerializationDebugger$.MODULE$.org$apache$spark$serializer$SerializationDebugger$$reflect().GetClassDataLayout().invoke($this)), (classDataSlot) -> (ObjectStreamClass)SerializationDebugger$.MODULE$.org$apache$spark$serializer$SerializationDebugger$$reflect().DescField().get(classDataSlot), scala.reflect.ClassTag..MODULE$.apply(ObjectStreamClass.class));
      }

      public final boolean hasWriteObjectMethod$extension(final ObjectStreamClass $this) {
         return BoxesRunTime.unboxToBoolean(SerializationDebugger$.MODULE$.org$apache$spark$serializer$SerializationDebugger$$reflect().HasWriteObjectMethod().invoke($this));
      }

      public final boolean hasWriteReplaceMethod$extension(final ObjectStreamClass $this) {
         return BoxesRunTime.unboxToBoolean(SerializationDebugger$.MODULE$.org$apache$spark$serializer$SerializationDebugger$$reflect().HasWriteReplaceMethod().invoke($this));
      }

      public final Object invokeWriteReplace$extension(final ObjectStreamClass $this, final Object obj) {
         return SerializationDebugger$.MODULE$.org$apache$spark$serializer$SerializationDebugger$$reflect().InvokeWriteReplace().invoke($this, obj);
      }

      public final int getNumObjFields$extension(final ObjectStreamClass $this) {
         return BoxesRunTime.unboxToInt(SerializationDebugger$.MODULE$.org$apache$spark$serializer$SerializationDebugger$$reflect().GetNumObjFields().invoke($this));
      }

      public final void getObjFieldValues$extension(final ObjectStreamClass $this, final Object obj, final Object[] out) {
         SerializationDebugger$.MODULE$.org$apache$spark$serializer$SerializationDebugger$$reflect().GetObjFieldValues().invoke($this, obj, out);
      }

      public final int hashCode$extension(final ObjectStreamClass $this) {
         return $this.hashCode();
      }

      public final boolean equals$extension(final ObjectStreamClass $this, final Object x$1) {
         boolean var10000;
         label41: {
            if (x$1 instanceof org.apache.spark.serializer.SerializationDebugger.ObjectStreamClassMethods) {
               ObjectStreamClass var5 = x$1 == null ? null : ((org.apache.spark.serializer.SerializationDebugger.ObjectStreamClassMethods)x$1).desc();
               if ($this == null) {
                  if (var5 == null) {
                     break label41;
                  }
               } else if ($this.equals(var5)) {
                  break label41;
               }
            }

            var10000 = false;
            return var10000;
         }

         var10000 = true;
         return var10000;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   private static class ObjectStreamClassReflection {
      private final Method GetClassDataLayout;
      private final Method HasWriteObjectMethod;
      private final Method HasWriteReplaceMethod;
      private final Method InvokeWriteReplace;
      private final Method GetNumObjFields;
      private final Method GetObjFieldValues;
      private final Field DescField;

      public Method GetClassDataLayout() {
         return this.GetClassDataLayout;
      }

      public Method HasWriteObjectMethod() {
         return this.HasWriteObjectMethod;
      }

      public Method HasWriteReplaceMethod() {
         return this.HasWriteReplaceMethod;
      }

      public Method InvokeWriteReplace() {
         return this.InvokeWriteReplace;
      }

      public Method GetNumObjFields() {
         return this.GetNumObjFields;
      }

      public Method GetObjFieldValues() {
         return this.GetObjFieldValues;
      }

      public Field DescField() {
         return this.DescField;
      }

      public ObjectStreamClassReflection() {
         Method f = ObjectStreamClass.class.getDeclaredMethod("getClassDataLayout");
         f.setAccessible(true);
         this.GetClassDataLayout = f;
         Method f = ObjectStreamClass.class.getDeclaredMethod("hasWriteObjectMethod");
         f.setAccessible(true);
         this.HasWriteObjectMethod = f;
         Method f = ObjectStreamClass.class.getDeclaredMethod("hasWriteReplaceMethod");
         f.setAccessible(true);
         this.HasWriteReplaceMethod = f;
         Method f = ObjectStreamClass.class.getDeclaredMethod("invokeWriteReplace", Object.class);
         f.setAccessible(true);
         this.InvokeWriteReplace = f;
         Method f = ObjectStreamClass.class.getDeclaredMethod("getNumObjFields");
         f.setAccessible(true);
         this.GetNumObjFields = f;
         Method f = ObjectStreamClass.class.getDeclaredMethod("getObjFieldValues", Object.class, Object[].class);
         f.setAccessible(true);
         this.GetObjFieldValues = f;
         Field f = Class.forName("java.io.ObjectStreamClass$ClassDataSlot").getDeclaredField("desc");
         f.setAccessible(true);
         this.DescField = f;
      }
   }
}
