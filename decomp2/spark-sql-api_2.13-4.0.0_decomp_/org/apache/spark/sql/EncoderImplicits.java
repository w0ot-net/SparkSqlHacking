package org.apache.spark.sql;

import java.io.Serializable;
import org.apache.spark.sql.catalyst.ScalaReflection$;
import org.apache.spark.sql.catalyst.encoders.AgnosticEncoder;
import org.apache.spark.sql.catalyst.encoders.AgnosticEncoders;
import org.apache.spark.sql.catalyst.encoders.AgnosticEncoders$;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.api.TypeTags;
import scala.reflect.package.;

@ScalaSignature(
   bytes = "\u0006\u0005\r5ga\u0002\u001a4!\u0003\r\t\u0001\u0010\u0005\u0006'\u0002!\t\u0001\u0016\u0005\u00061\u0002!\u0019!\u0017\u0005\u0006A\u0002!\u0019!\u0019\u0005\u0006M\u0002!\u0019a\u001a\u0005\u0006Y\u0002!\u0019!\u001c\u0005\u0006e\u0002!\u0019a\u001d\u0005\u0006q\u0002!\u0019!\u001f\u0005\u0006}\u0002!\u0019a \u0005\b\u0003\u0013\u0001A1AA\u0006\u0011\u001d\ty\u0002\u0001C\u0002\u0003CAq!!\u000e\u0001\t\u0007\t9\u0004C\u0004\u0002D\u0001!\u0019!!\u0012\t\u000f\u0005M\u0003\u0001b\u0001\u0002V!9\u0011Q\r\u0001\u0005\u0004\u0005\u001d\u0004bBA9\u0001\u0011\r\u00111\u000f\u0005\b\u0003{\u0002A1AA@\u0011\u001d\tI\t\u0001C\u0002\u0003\u0017Cq!!&\u0001\t\u0007\t9\nC\u0004\u0002\"\u0002!\u0019!a)\t\u000f\t\r\u0001\u0001b\u0001\u0003\u0006!9!q\u0002\u0001\u0005\u0004\tE\u0001b\u0002B\r\u0001\u0011\r!1\u0004\u0005\b\u0005G\u0001A1\u0001B\u0013\u0011\u001d\u0011i\u0003\u0001C\u0002\u0005_AqAa\u000e\u0001\t\u0007\u0011I\u0004C\u0004\u0003B\u0001!\u0019Aa\u0011\t\u000f\t-\u0003\u0001\"\u0003\u0003N!I!1\u000f\u0001C\u0002\u0013\u0005!Q\u000f\u0005\n\u0005\u001f\u0003!\u0019!C\u0001\u0005#C\u0011B!'\u0001\u0005\u0004%\tAa'\t\u0013\t\r\u0006A1A\u0005\u0002\t\u0015\u0006\"\u0003BW\u0001\t\u0007I\u0011\u0001BX\u0011%\u00119\f\u0001b\u0001\n\u0003\u0011I\fC\u0005\u0003B\u0002\u0011\r\u0011\"\u0001\u0003D\"I!1\u001a\u0001C\u0002\u0013\u0005!Q\u001a\u0005\b\u0005+\u0004A\u0011\u0001Bl\u0011\u001d\u0011\u0019\u0010\u0001C\u0002\u0005kDqaa\u0005\u0001\t\u0007\u0019)\u0002C\u0004\u0004B\u0001!\u0019aa\u0011\t\u000f\r\r\u0004\u0001\"\u0003\u0004f!I1\u0011\u0010\u0001C\u0002\u0013\r11\u0010\u0005\n\u0007\u0003\u0003!\u0019!C\u0002\u0007\u0007C\u0011b!#\u0001\u0005\u0004%\u0019aa#\t\u0013\rE\u0005A1A\u0005\u0004\rM\u0005\"CBM\u0001\t\u0007I1ABN\u0011%\u0019\t\u000b\u0001b\u0001\n\u0007\u0019\u0019\u000bC\u0005\u0004*\u0002\u0011\r\u0011b\u0001\u0004,\"I1\u0011\u0017\u0001C\u0002\u0013\r11\u0017\u0005\b\u0007s\u0003A1AB^\u0005A)enY8eKJLU\u000e\u001d7jG&$8O\u0003\u00025k\u0005\u00191/\u001d7\u000b\u0005Y:\u0014!B:qCJ\\'B\u0001\u001d:\u0003\u0019\t\u0007/Y2iK*\t!(A\u0002pe\u001e\u001c\u0001a\u0005\u0003\u0001{\r;\u0005C\u0001 B\u001b\u0005y$\"\u0001!\u0002\u000bM\u001c\u0017\r\\1\n\u0005\t{$AB!osJ+g\r\u0005\u0002E\u000b6\t1'\u0003\u0002Gg\t9Bj\\<Qe&|'/\u001b;z'Fc\u0015*\u001c9mS\u000eLGo\u001d\t\u0003\u0011Bs!!\u0013(\u000f\u0005)kU\"A&\u000b\u00051[\u0014A\u0002\u001fs_>$h(C\u0001A\u0013\tyu(A\u0004qC\u000e\\\u0017mZ3\n\u0005E\u0013&\u0001D*fe&\fG.\u001b>bE2,'BA(@\u0003\u0019!\u0013N\\5uIQ\tQ\u000b\u0005\u0002?-&\u0011qk\u0010\u0002\u0005+:LG/A\u0007oK^Le\u000e^#oG>$WM]\u000b\u00025B\u0019AiW/\n\u0005q\u001b$aB#oG>$WM\u001d\t\u0003}yK!aX \u0003\u0007%sG/\u0001\boK^duN\\4F]\u000e|G-\u001a:\u0016\u0003\t\u00042\u0001R.d!\tqD-\u0003\u0002f\u007f\t!Aj\u001c8h\u0003AqWm\u001e#pk\ndW-\u00128d_\u0012,'/F\u0001i!\r!5,\u001b\t\u0003})L!a[ \u0003\r\u0011{WO\u00197f\u0003=qWm\u001e$m_\u0006$XI\\2pI\u0016\u0014X#\u00018\u0011\u0007\u0011[v\u000e\u0005\u0002?a&\u0011\u0011o\u0010\u0002\u0006\r2|\u0017\r^\u0001\u000f]\u0016<()\u001f;f\u000b:\u001cw\u000eZ3s+\u0005!\bc\u0001#\\kB\u0011aH^\u0005\u0003o~\u0012AAQ=uK\u0006ya.Z<TQ>\u0014H/\u00128d_\u0012,'/F\u0001{!\r!5l\u001f\t\u0003}qL!!` \u0003\u000bMCwN\u001d;\u0002#9,wOQ8pY\u0016\fg.\u00128d_\u0012,'/\u0006\u0002\u0002\u0002A!AiWA\u0002!\rq\u0014QA\u0005\u0004\u0003\u000fy$a\u0002\"p_2,\u0017M\\\u0001\u0011]\u0016<8\u000b\u001e:j]\u001e,enY8eKJ,\"!!\u0004\u0011\t\u0011[\u0016q\u0002\t\u0005\u0003#\tIB\u0004\u0003\u0002\u0014\u0005U\u0001C\u0001&@\u0013\r\t9bP\u0001\u0007!J,G-\u001a4\n\t\u0005m\u0011Q\u0004\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\u0005]q(A\u000boK^T\u0015M^1EK\u000eLW.\u00197F]\u000e|G-\u001a:\u0016\u0005\u0005\r\u0002\u0003\u0002#\\\u0003K\u0001B!a\n\u000225\u0011\u0011\u0011\u0006\u0006\u0005\u0003W\ti#\u0001\u0003nCRD'BAA\u0018\u0003\u0011Q\u0017M^1\n\t\u0005M\u0012\u0011\u0006\u0002\u000b\u0005&<G)Z2j[\u0006d\u0017A\u00068foN\u001b\u0017\r\\1EK\u000eLW.\u00197F]\u000e|G-\u001a:\u0016\u0005\u0005e\u0002\u0003\u0002#\\\u0003w\u0001B!!\u0010\u0002B5\u0011\u0011q\b\u0006\u0004\u0003Wy\u0014\u0002BA\u001a\u0003\u007f\taB\\3x\t\u0006$X-\u00128d_\u0012,'/\u0006\u0002\u0002HA!AiWA%!\u0011\tY%a\u0014\u000e\u0005\u00055#b\u0001\u001b\u0002.%!\u0011\u0011KA'\u0005\u0011!\u0015\r^3\u0002'9,w\u000fT8dC2$\u0015\r^3F]\u000e|G-\u001a:\u0016\u0005\u0005]\u0003\u0003\u0002#\\\u00033\u0002B!a\u0017\u0002b5\u0011\u0011Q\f\u0006\u0005\u0003?\ni#\u0001\u0003uS6,\u0017\u0002BA2\u0003;\u0012\u0011\u0002T8dC2$\u0015\r^3\u0002/9,w\u000fT8dC2$\u0015\r^3US6,WI\\2pI\u0016\u0014XCAA5!\u0011!5,a\u001b\u0011\t\u0005m\u0013QN\u0005\u0005\u0003_\niFA\u0007M_\u000e\fG\u000eR1uKRKW.Z\u0001\u0014]\u0016<H+[7f'R\fW\u000e]#oG>$WM]\u000b\u0003\u0003k\u0002B\u0001R.\u0002xA!\u00111JA=\u0013\u0011\tY(!\u0014\u0003\u0013QKW.Z:uC6\u0004\u0018!\u00058fo&s7\u000f^1oi\u0016s7m\u001c3feV\u0011\u0011\u0011\u0011\t\u0005\tn\u000b\u0019\t\u0005\u0003\u0002\\\u0005\u0015\u0015\u0002BAD\u0003;\u0012q!\u00138ti\u0006tG/\u0001\noK^$UO]1uS>tWI\\2pI\u0016\u0014XCAAG!\u0011!5,a$\u0011\t\u0005m\u0013\u0011S\u0005\u0005\u0003'\u000biF\u0001\u0005EkJ\fG/[8o\u0003AqWm\u001e)fe&|G-\u00128d_\u0012,'/\u0006\u0002\u0002\u001aB!AiWAN!\u0011\tY&!(\n\t\u0005}\u0015Q\f\u0002\u0007!\u0016\u0014\u0018n\u001c3\u0002%9,wOS1wC\u0016sW/\\#oG>$WM]\u000b\u0005\u0003K\u000bi\u000b\u0006\u0003\u0002(\u0006U\u0007\u0003\u0002#\\\u0003S\u0003B!a+\u0002.2\u0001AaBAX'\t\u0007\u0011\u0011\u0017\u0002\u0002\u0003F!\u00111WA]!\rq\u0014QW\u0005\u0004\u0003o{$a\u0002(pi\"Lgn\u001a\u0019\u0005\u0003w\u000bI\r\u0005\u0004\u0002>\u0006\r\u0017qY\u0007\u0003\u0003\u007fSA!!1\u0002.\u0005!A.\u00198h\u0013\u0011\t)-a0\u0003\t\u0015sW/\u001c\t\u0005\u0003W\u000bI\r\u0002\u0007\u0002L\u00065\u0016\u0011!A\u0001\u0006\u0003\tiMA\u0002`IE\nB!a-\u0002PB\u0019a(!5\n\u0007\u0005MwHA\u0002B]fD\u0011\"a6\u0014\u0003\u0003\u0005\u001d!!7\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$3\u0007\u0005\u0004\u0002\\\u0006]\u0018\u0011\u0016\b\u0005\u0003;\f\tP\u0004\u0003\u0002`\u00065h\u0002BAq\u0003Ot1!SAr\u0013\r\t)oP\u0001\be\u00164G.Z2u\u0013\u0011\tI/a;\u0002\u000fI,h\u000e^5nK*\u0019\u0011Q] \n\u0007=\u000byO\u0003\u0003\u0002j\u0006-\u0018\u0002BAz\u0003k\f\u0001\"\u001e8jm\u0016\u00148/\u001a\u0006\u0004\u001f\u0006=\u0018\u0002BA}\u0003w\u0014q\u0001V=qKR\u000bw-\u0003\u0003\u0002~\u0006}(\u0001\u0003+za\u0016$\u0016mZ:\u000b\t\t\u0005\u00111^\u0001\u0004CBL\u0017A\u00058fo\n{\u00070\u001a3J]R,enY8eKJ,\"Aa\u0002\u0011\t\u0011[&\u0011\u0002\t\u0005\u0003{\u0013Y!\u0003\u0003\u0003\u000e\u0005}&aB%oi\u0016<WM]\u0001\u0014]\u0016<(i\u001c=fI2{gnZ#oG>$WM]\u000b\u0003\u0005'\u0001B\u0001R.\u0003\u0016A!\u0011Q\u0018B\f\u0013\r)\u0017qX\u0001\u0016]\u0016<(i\u001c=fI\u0012{WO\u00197f\u000b:\u001cw\u000eZ3s+\t\u0011i\u0002\u0005\u0003E7\n}\u0001\u0003BA_\u0005CI1a[A`\u0003QqWm\u001e\"pq\u0016$g\t\\8bi\u0016s7m\u001c3feV\u0011!q\u0005\t\u0005\tn\u0013I\u0003\u0005\u0003\u0002>\n-\u0012bA9\u0002@\u0006\u0019b.Z<C_b,GMQ=uK\u0016s7m\u001c3feV\u0011!\u0011\u0007\t\u0005\tn\u0013\u0019\u0004\u0005\u0003\u0002>\nU\u0012bA<\u0002@\u0006!b.Z<C_b,Gm\u00155peR,enY8eKJ,\"Aa\u000f\u0011\t\u0011[&Q\b\t\u0005\u0003{\u0013y$C\u0002~\u0003\u007f\u000baC\\3x\u0005>DX\r\u001a\"p_2,\u0017M\\#oG>$WM]\u000b\u0003\u0005\u000b\u0002B\u0001R.\u0003HA!\u0011Q\u0018B%\u0013\u0011\t9!a0\u0002\u001b9,woU3r\u000b:\u001cw\u000eZ3s+\u0011\u0011yE!\u001b\u0015\t\tE#Q\u000e\t\u0007\u0005'\u0012iF!\u0019\u000e\u0005\tU#\u0002\u0002B,\u00053\n\u0001\"\u001a8d_\u0012,'o\u001d\u0006\u0004\u00057\u001a\u0014\u0001C2bi\u0006d\u0017p\u001d;\n\t\t}#Q\u000b\u0002\u0010\u0003\u001etwn\u001d;jG\u0016s7m\u001c3feB)\u0001Ja\u0019\u0003h%\u0019!Q\r*\u0003\u0007M+\u0017\u000f\u0005\u0003\u0002,\n%Da\u0002B67\t\u0007\u0011Q\u001a\u0002\u0002\u000b\"9!qN\u000eA\u0002\tE\u0014AD3mK6,g\u000e^#oG>$WM\u001d\t\u0007\u0005'\u0012iFa\u001a\u0002!9,w/\u00138u'\u0016\fXI\\2pI\u0016\u0014XC\u0001B<!\u0011!5L!\u001f\u0011\t!\u0013\u0019'\u0018\u0015\f9\tu$1\u0011BC\u0005\u0013\u0013Y\tE\u0002?\u0005\u007fJ1A!!@\u0005)!W\r\u001d:fG\u0006$X\rZ\u0001\b[\u0016\u001c8/Y4fC\t\u00119)\u0001\u0010Vg\u0016\u0004c.Z<TKF,XM\\2f\u000b:\u001cw\u000eZ3sA%t7\u000f^3bI\u0006)1/\u001b8dK\u0006\u0012!QR\u0001\u0006e9\u0012d\u0006M\u0001\u0012]\u0016<Hj\u001c8h'\u0016\fXI\\2pI\u0016\u0014XC\u0001BJ!\u0011!5L!&\u0011\t!\u0013\u0019g\u0019\u0015\f;\tu$1\u0011BC\u0005\u0013\u0013Y)A\noK^$u.\u001e2mKN+\u0017/\u00128d_\u0012,'/\u0006\u0002\u0003\u001eB!Ai\u0017BP!\u0011A%1M5)\u0017y\u0011iHa!\u0003\u0006\n%%1R\u0001\u0013]\u0016<h\t\\8biN+\u0017/\u00128d_\u0012,'/\u0006\u0002\u0003(B!Ai\u0017BU!\u0011A%1M8)\u0017}\u0011iHa!\u0003\u0006\n%%1R\u0001\u0012]\u0016<()\u001f;f'\u0016\fXI\\2pI\u0016\u0014XC\u0001BY!\u0011!5La-\u0011\t!\u0013\u0019'\u001e\u0015\fA\tu$1\u0011BC\u0005\u0013\u0013Y)\u0001\noK^\u001c\u0006n\u001c:u'\u0016\fXI\\2pI\u0016\u0014XC\u0001B^!\u0011!5L!0\u0011\t!\u0013\u0019g\u001f\u0015\fC\tu$1\u0011BC\u0005\u0013\u0013Y)\u0001\u000boK^\u0014un\u001c7fC:\u001cV-]#oG>$WM]\u000b\u0003\u0005\u000b\u0004B\u0001R.\u0003HB)\u0001Ja\u0019\u0002\u0004!Z!E! \u0003\u0004\n\u0015%\u0011\u0012BF\u0003MqWm^*ue&twmU3r\u000b:\u001cw\u000eZ3s+\t\u0011y\r\u0005\u0003E7\nE\u0007#\u0002%\u0003d\u0005=\u0001fC\u0012\u0003~\t\r%Q\u0011BE\u0005\u0017\u000bAC\\3x!J|G-^2u'\u0016\fXI\\2pI\u0016\u0014X\u0003\u0002Bm\u0005C$BAa7\u0003lB!Ai\u0017Bo!\u0015A%1\rBp!\u0011\tYK!9\u0005\u000f\u0005=FE1\u0001\u0003dF!\u00111\u0017Bs!\rq$q]\u0005\u0004\u0005S|$a\u0002)s_\u0012,8\r\u001e\u0005\n\u0005[$\u0013\u0011!a\u0002\u0005_\f!\"\u001a<jI\u0016t7-\u001a\u00135!\u0019\tY.a>\u0003`\"ZAE! \u0003\u0004\n\u0015%\u0011\u0012BF\u0003IqWm^*fcV,gnY3F]\u000e|G-\u001a:\u0016\t\t](Q \u000b\u0005\u0005s\u001ci\u0001\u0005\u0003E7\nm\b\u0003BAV\u0005{$qAa@&\u0005\u0004\u0019\tAA\u0001U#\u0011\t\u0019la\u00011\t\r\u00151\u0011\u0002\t\u0006\u0011\n\r4q\u0001\t\u0005\u0003W\u001bI\u0001\u0002\u0007\u0004\f\tu\u0018\u0011!A\u0001\u0006\u0003\tiMA\u0002`IIB\u0011ba\u0004&\u0003\u0003\u0005\u001da!\u0005\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$S\u0007\u0005\u0004\u0002\\\u0006](1`\u0001\u000e]\u0016<X*\u00199F]\u000e|G-\u001a:\u0016\t\r]1Q\u0004\u000b\u0005\u00073\u0019Y\u0004\u0005\u0003E7\u000em\u0001\u0003BAV\u0007;!qAa@'\u0005\u0004\u0019y\"\u0005\u0003\u00024\u000e\u0005\u0002GBB\u0012\u0007c\u00199\u0004\u0005\u0005\u0004&\r-2qFB\u001b\u001b\t\u00199CC\u0002\u0004*}\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\u0019ica\n\u0003\u00075\u000b\u0007\u000f\u0005\u0003\u0002,\u000eEB\u0001DB\u001a\u0007;\t\t\u0011!A\u0003\u0002\u00055'aA0%gA!\u00111VB\u001c\t1\u0019Id!\b\u0002\u0002\u0003\u0005)\u0011AAg\u0005\ryF\u0005\u000e\u0005\n\u0007{1\u0013\u0011!a\u0002\u0007\u007f\t!\"\u001a<jI\u0016t7-\u001a\u00137!\u0019\tY.a>\u0004\u001c\u0005ia.Z<TKR,enY8eKJ,Ba!\u0012\u0004LQ!1qIB/!\u0011!5l!\u0013\u0011\t\u0005-61\n\u0003\b\u0005\u007f<#\u0019AB'#\u0011\t\u0019la\u00141\t\rE3\u0011\f\t\u0007\u0003#\u0019\u0019fa\u0016\n\t\rU\u0013Q\u0004\u0002\u0004'\u0016$\b\u0003BAV\u00073\"Aba\u0017\u0004L\u0005\u0005\t\u0011!B\u0001\u0003\u001b\u00141a\u0018\u00136\u0011%\u0019yfJA\u0001\u0002\b\u0019\t'\u0001\u0006fm&$WM\\2fI]\u0002b!a7\u0002x\u000e%\u0013a\u00048fo\u0006\u0013(/Y=F]\u000e|G-\u001a:\u0016\t\r\u001d41\u000f\u000b\u0005\u0007S\u001a)\b\u0005\u0004\u0003T\tu31\u000e\t\u0006}\r54\u0011O\u0005\u0004\u0007_z$!B!se\u0006L\b\u0003BAV\u0007g\"qAa\u001b)\u0005\u0004\ti\rC\u0004\u0003p!\u0002\raa\u001e\u0011\r\tM#QLB9\u0003IqWm^%oi\u0006\u0013(/Y=F]\u000e|G-\u001a:\u0016\u0005\ru\u0004\u0003\u0002#\\\u0007\u007f\u0002BAPB7;\u0006\u0019b.Z<M_:<\u0017I\u001d:bs\u0016s7m\u001c3feV\u00111Q\u0011\t\u0005\tn\u001b9\t\u0005\u0003?\u0007[\u001a\u0017!\u00068fo\u0012{WO\u00197f\u0003J\u0014\u0018-_#oG>$WM]\u000b\u0003\u0007\u001b\u0003B\u0001R.\u0004\u0010B!ah!\u001cj\u0003QqWm\u001e$m_\u0006$\u0018I\u001d:bs\u0016s7m\u001c3feV\u00111Q\u0013\t\u0005\tn\u001b9\n\u0005\u0003?\u0007[z\u0017a\u00058fo\nKH/Z!se\u0006LXI\\2pI\u0016\u0014XCABO!\u0011!5la(\u0011\ty\u001ai'^\u0001\u0015]\u0016<8\u000b[8si\u0006\u0013(/Y=F]\u000e|G-\u001a:\u0016\u0005\r\u0015\u0006\u0003\u0002#\\\u0007O\u0003BAPB7w\u00061b.Z<C_>dW-\u00198BeJ\f\u00170\u00128d_\u0012,'/\u0006\u0002\u0004.B!AiWBX!\u0015q4QNA\u0002\u0003UqWm^*ue&tw-\u0011:sCf,enY8eKJ,\"a!.\u0011\t\u0011[6q\u0017\t\u0006}\r5\u0014qB\u0001\u0017]\u0016<\bK]8ek\u000e$\u0018I\u001d:bs\u0016s7m\u001c3feV!1QXBc)\u0011\u0019yla2\u0011\t\u0011[6\u0011\u0019\t\u0006}\r541\u0019\t\u0005\u0003W\u001b)\rB\u0004\u00020F\u0012\rAa9\t\u0013\r%\u0017'!AA\u0004\r-\u0017AC3wS\u0012,gnY3%qA1\u00111\\A|\u0007\u0007\u0004"
)
public interface EncoderImplicits extends LowPrioritySQLImplicits, Serializable {
   void org$apache$spark$sql$EncoderImplicits$_setter_$newIntSeqEncoder_$eq(final Encoder x$1);

   void org$apache$spark$sql$EncoderImplicits$_setter_$newLongSeqEncoder_$eq(final Encoder x$1);

   void org$apache$spark$sql$EncoderImplicits$_setter_$newDoubleSeqEncoder_$eq(final Encoder x$1);

   void org$apache$spark$sql$EncoderImplicits$_setter_$newFloatSeqEncoder_$eq(final Encoder x$1);

   void org$apache$spark$sql$EncoderImplicits$_setter_$newByteSeqEncoder_$eq(final Encoder x$1);

   void org$apache$spark$sql$EncoderImplicits$_setter_$newShortSeqEncoder_$eq(final Encoder x$1);

   void org$apache$spark$sql$EncoderImplicits$_setter_$newBooleanSeqEncoder_$eq(final Encoder x$1);

   void org$apache$spark$sql$EncoderImplicits$_setter_$newStringSeqEncoder_$eq(final Encoder x$1);

   void org$apache$spark$sql$EncoderImplicits$_setter_$newIntArrayEncoder_$eq(final Encoder x$1);

   void org$apache$spark$sql$EncoderImplicits$_setter_$newLongArrayEncoder_$eq(final Encoder x$1);

   void org$apache$spark$sql$EncoderImplicits$_setter_$newDoubleArrayEncoder_$eq(final Encoder x$1);

   void org$apache$spark$sql$EncoderImplicits$_setter_$newFloatArrayEncoder_$eq(final Encoder x$1);

   void org$apache$spark$sql$EncoderImplicits$_setter_$newByteArrayEncoder_$eq(final Encoder x$1);

   void org$apache$spark$sql$EncoderImplicits$_setter_$newShortArrayEncoder_$eq(final Encoder x$1);

   void org$apache$spark$sql$EncoderImplicits$_setter_$newBooleanArrayEncoder_$eq(final Encoder x$1);

   void org$apache$spark$sql$EncoderImplicits$_setter_$newStringArrayEncoder_$eq(final Encoder x$1);

   // $FF: synthetic method
   static Encoder newIntEncoder$(final EncoderImplicits $this) {
      return $this.newIntEncoder();
   }

   default Encoder newIntEncoder() {
      return Encoders$.MODULE$.scalaInt();
   }

   // $FF: synthetic method
   static Encoder newLongEncoder$(final EncoderImplicits $this) {
      return $this.newLongEncoder();
   }

   default Encoder newLongEncoder() {
      return Encoders$.MODULE$.scalaLong();
   }

   // $FF: synthetic method
   static Encoder newDoubleEncoder$(final EncoderImplicits $this) {
      return $this.newDoubleEncoder();
   }

   default Encoder newDoubleEncoder() {
      return Encoders$.MODULE$.scalaDouble();
   }

   // $FF: synthetic method
   static Encoder newFloatEncoder$(final EncoderImplicits $this) {
      return $this.newFloatEncoder();
   }

   default Encoder newFloatEncoder() {
      return Encoders$.MODULE$.scalaFloat();
   }

   // $FF: synthetic method
   static Encoder newByteEncoder$(final EncoderImplicits $this) {
      return $this.newByteEncoder();
   }

   default Encoder newByteEncoder() {
      return Encoders$.MODULE$.scalaByte();
   }

   // $FF: synthetic method
   static Encoder newShortEncoder$(final EncoderImplicits $this) {
      return $this.newShortEncoder();
   }

   default Encoder newShortEncoder() {
      return Encoders$.MODULE$.scalaShort();
   }

   // $FF: synthetic method
   static Encoder newBooleanEncoder$(final EncoderImplicits $this) {
      return $this.newBooleanEncoder();
   }

   default Encoder newBooleanEncoder() {
      return Encoders$.MODULE$.scalaBoolean();
   }

   // $FF: synthetic method
   static Encoder newStringEncoder$(final EncoderImplicits $this) {
      return $this.newStringEncoder();
   }

   default Encoder newStringEncoder() {
      return Encoders$.MODULE$.STRING();
   }

   // $FF: synthetic method
   static Encoder newJavaDecimalEncoder$(final EncoderImplicits $this) {
      return $this.newJavaDecimalEncoder();
   }

   default Encoder newJavaDecimalEncoder() {
      return Encoders$.MODULE$.DECIMAL();
   }

   // $FF: synthetic method
   static Encoder newScalaDecimalEncoder$(final EncoderImplicits $this) {
      return $this.newScalaDecimalEncoder();
   }

   default Encoder newScalaDecimalEncoder() {
      return AgnosticEncoders$.MODULE$.DEFAULT_SCALA_DECIMAL_ENCODER();
   }

   // $FF: synthetic method
   static Encoder newDateEncoder$(final EncoderImplicits $this) {
      return $this.newDateEncoder();
   }

   default Encoder newDateEncoder() {
      return Encoders$.MODULE$.DATE();
   }

   // $FF: synthetic method
   static Encoder newLocalDateEncoder$(final EncoderImplicits $this) {
      return $this.newLocalDateEncoder();
   }

   default Encoder newLocalDateEncoder() {
      return Encoders$.MODULE$.LOCALDATE();
   }

   // $FF: synthetic method
   static Encoder newLocalDateTimeEncoder$(final EncoderImplicits $this) {
      return $this.newLocalDateTimeEncoder();
   }

   default Encoder newLocalDateTimeEncoder() {
      return Encoders$.MODULE$.LOCALDATETIME();
   }

   // $FF: synthetic method
   static Encoder newTimeStampEncoder$(final EncoderImplicits $this) {
      return $this.newTimeStampEncoder();
   }

   default Encoder newTimeStampEncoder() {
      return Encoders$.MODULE$.TIMESTAMP();
   }

   // $FF: synthetic method
   static Encoder newInstantEncoder$(final EncoderImplicits $this) {
      return $this.newInstantEncoder();
   }

   default Encoder newInstantEncoder() {
      return Encoders$.MODULE$.INSTANT();
   }

   // $FF: synthetic method
   static Encoder newDurationEncoder$(final EncoderImplicits $this) {
      return $this.newDurationEncoder();
   }

   default Encoder newDurationEncoder() {
      return Encoders$.MODULE$.DURATION();
   }

   // $FF: synthetic method
   static Encoder newPeriodEncoder$(final EncoderImplicits $this) {
      return $this.newPeriodEncoder();
   }

   default Encoder newPeriodEncoder() {
      return Encoders$.MODULE$.PERIOD();
   }

   // $FF: synthetic method
   static Encoder newJavaEnumEncoder$(final EncoderImplicits $this, final TypeTags.TypeTag evidence$3) {
      return $this.newJavaEnumEncoder(evidence$3);
   }

   default Encoder newJavaEnumEncoder(final TypeTags.TypeTag evidence$3) {
      return ScalaReflection$.MODULE$.encoderFor(evidence$3);
   }

   // $FF: synthetic method
   static Encoder newBoxedIntEncoder$(final EncoderImplicits $this) {
      return $this.newBoxedIntEncoder();
   }

   default Encoder newBoxedIntEncoder() {
      return Encoders$.MODULE$.INT();
   }

   // $FF: synthetic method
   static Encoder newBoxedLongEncoder$(final EncoderImplicits $this) {
      return $this.newBoxedLongEncoder();
   }

   default Encoder newBoxedLongEncoder() {
      return Encoders$.MODULE$.LONG();
   }

   // $FF: synthetic method
   static Encoder newBoxedDoubleEncoder$(final EncoderImplicits $this) {
      return $this.newBoxedDoubleEncoder();
   }

   default Encoder newBoxedDoubleEncoder() {
      return Encoders$.MODULE$.DOUBLE();
   }

   // $FF: synthetic method
   static Encoder newBoxedFloatEncoder$(final EncoderImplicits $this) {
      return $this.newBoxedFloatEncoder();
   }

   default Encoder newBoxedFloatEncoder() {
      return Encoders$.MODULE$.FLOAT();
   }

   // $FF: synthetic method
   static Encoder newBoxedByteEncoder$(final EncoderImplicits $this) {
      return $this.newBoxedByteEncoder();
   }

   default Encoder newBoxedByteEncoder() {
      return Encoders$.MODULE$.BYTE();
   }

   // $FF: synthetic method
   static Encoder newBoxedShortEncoder$(final EncoderImplicits $this) {
      return $this.newBoxedShortEncoder();
   }

   default Encoder newBoxedShortEncoder() {
      return Encoders$.MODULE$.SHORT();
   }

   // $FF: synthetic method
   static Encoder newBoxedBooleanEncoder$(final EncoderImplicits $this) {
      return $this.newBoxedBooleanEncoder();
   }

   default Encoder newBoxedBooleanEncoder() {
      return Encoders$.MODULE$.BOOLEAN();
   }

   private AgnosticEncoder newSeqEncoder(final AgnosticEncoder elementEncoder) {
      return new AgnosticEncoders.IterableEncoder(.MODULE$.classTag(scala.reflect.ClassTag..MODULE$.apply(Seq.class)), elementEncoder, elementEncoder.nullable(), elementEncoder.lenientSerialization());
   }

   /** @deprecated */
   Encoder newIntSeqEncoder();

   /** @deprecated */
   Encoder newLongSeqEncoder();

   /** @deprecated */
   Encoder newDoubleSeqEncoder();

   /** @deprecated */
   Encoder newFloatSeqEncoder();

   /** @deprecated */
   Encoder newByteSeqEncoder();

   /** @deprecated */
   Encoder newShortSeqEncoder();

   /** @deprecated */
   Encoder newBooleanSeqEncoder();

   /** @deprecated */
   Encoder newStringSeqEncoder();

   // $FF: synthetic method
   static Encoder newProductSeqEncoder$(final EncoderImplicits $this, final TypeTags.TypeTag evidence$4) {
      return $this.newProductSeqEncoder(evidence$4);
   }

   /** @deprecated */
   default Encoder newProductSeqEncoder(final TypeTags.TypeTag evidence$4) {
      return this.newSeqEncoder(ScalaReflection$.MODULE$.encoderFor(evidence$4));
   }

   // $FF: synthetic method
   static Encoder newSequenceEncoder$(final EncoderImplicits $this, final TypeTags.TypeTag evidence$5) {
      return $this.newSequenceEncoder(evidence$5);
   }

   default Encoder newSequenceEncoder(final TypeTags.TypeTag evidence$5) {
      return ScalaReflection$.MODULE$.encoderFor(evidence$5);
   }

   // $FF: synthetic method
   static Encoder newMapEncoder$(final EncoderImplicits $this, final TypeTags.TypeTag evidence$6) {
      return $this.newMapEncoder(evidence$6);
   }

   default Encoder newMapEncoder(final TypeTags.TypeTag evidence$6) {
      return ScalaReflection$.MODULE$.encoderFor(evidence$6);
   }

   // $FF: synthetic method
   static Encoder newSetEncoder$(final EncoderImplicits $this, final TypeTags.TypeTag evidence$7) {
      return $this.newSetEncoder(evidence$7);
   }

   default Encoder newSetEncoder(final TypeTags.TypeTag evidence$7) {
      return ScalaReflection$.MODULE$.encoderFor(evidence$7);
   }

   private AgnosticEncoder newArrayEncoder(final AgnosticEncoder elementEncoder) {
      return new AgnosticEncoders.ArrayEncoder(elementEncoder, elementEncoder.nullable());
   }

   Encoder newIntArrayEncoder();

   Encoder newLongArrayEncoder();

   Encoder newDoubleArrayEncoder();

   Encoder newFloatArrayEncoder();

   Encoder newByteArrayEncoder();

   Encoder newShortArrayEncoder();

   Encoder newBooleanArrayEncoder();

   Encoder newStringArrayEncoder();

   // $FF: synthetic method
   static Encoder newProductArrayEncoder$(final EncoderImplicits $this, final TypeTags.TypeTag evidence$8) {
      return $this.newProductArrayEncoder(evidence$8);
   }

   default Encoder newProductArrayEncoder(final TypeTags.TypeTag evidence$8) {
      return this.newArrayEncoder(ScalaReflection$.MODULE$.encoderFor(evidence$8));
   }

   static void $init$(final EncoderImplicits $this) {
      $this.org$apache$spark$sql$EncoderImplicits$_setter_$newIntSeqEncoder_$eq($this.newSeqEncoder(AgnosticEncoders.PrimitiveIntEncoder$.MODULE$));
      $this.org$apache$spark$sql$EncoderImplicits$_setter_$newLongSeqEncoder_$eq($this.newSeqEncoder(AgnosticEncoders.PrimitiveLongEncoder$.MODULE$));
      $this.org$apache$spark$sql$EncoderImplicits$_setter_$newDoubleSeqEncoder_$eq($this.newSeqEncoder(AgnosticEncoders.PrimitiveDoubleEncoder$.MODULE$));
      $this.org$apache$spark$sql$EncoderImplicits$_setter_$newFloatSeqEncoder_$eq($this.newSeqEncoder(AgnosticEncoders.PrimitiveFloatEncoder$.MODULE$));
      $this.org$apache$spark$sql$EncoderImplicits$_setter_$newByteSeqEncoder_$eq($this.newSeqEncoder(AgnosticEncoders.PrimitiveByteEncoder$.MODULE$));
      $this.org$apache$spark$sql$EncoderImplicits$_setter_$newShortSeqEncoder_$eq($this.newSeqEncoder(AgnosticEncoders.PrimitiveShortEncoder$.MODULE$));
      $this.org$apache$spark$sql$EncoderImplicits$_setter_$newBooleanSeqEncoder_$eq($this.newSeqEncoder(AgnosticEncoders.PrimitiveBooleanEncoder$.MODULE$));
      $this.org$apache$spark$sql$EncoderImplicits$_setter_$newStringSeqEncoder_$eq($this.newSeqEncoder(AgnosticEncoders.StringEncoder$.MODULE$));
      $this.org$apache$spark$sql$EncoderImplicits$_setter_$newIntArrayEncoder_$eq($this.newArrayEncoder(AgnosticEncoders.PrimitiveIntEncoder$.MODULE$));
      $this.org$apache$spark$sql$EncoderImplicits$_setter_$newLongArrayEncoder_$eq($this.newArrayEncoder(AgnosticEncoders.PrimitiveLongEncoder$.MODULE$));
      $this.org$apache$spark$sql$EncoderImplicits$_setter_$newDoubleArrayEncoder_$eq($this.newArrayEncoder(AgnosticEncoders.PrimitiveDoubleEncoder$.MODULE$));
      $this.org$apache$spark$sql$EncoderImplicits$_setter_$newFloatArrayEncoder_$eq($this.newArrayEncoder(AgnosticEncoders.PrimitiveFloatEncoder$.MODULE$));
      $this.org$apache$spark$sql$EncoderImplicits$_setter_$newByteArrayEncoder_$eq(Encoders$.MODULE$.BINARY());
      $this.org$apache$spark$sql$EncoderImplicits$_setter_$newShortArrayEncoder_$eq($this.newArrayEncoder(AgnosticEncoders.PrimitiveShortEncoder$.MODULE$));
      $this.org$apache$spark$sql$EncoderImplicits$_setter_$newBooleanArrayEncoder_$eq($this.newArrayEncoder(AgnosticEncoders.PrimitiveBooleanEncoder$.MODULE$));
      $this.org$apache$spark$sql$EncoderImplicits$_setter_$newStringArrayEncoder_$eq($this.newArrayEncoder(AgnosticEncoders.StringEncoder$.MODULE$));
   }
}
