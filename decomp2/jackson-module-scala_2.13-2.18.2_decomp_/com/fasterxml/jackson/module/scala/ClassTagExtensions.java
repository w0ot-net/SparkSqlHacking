package com.fasterxml.jackson.module.scala;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r=v!\u0002\u0015*\u0011\u0003!d!\u0002\u001c*\u0011\u00039\u0004\"B\u001f\u0002\t\u0003q\u0004\"B \u0002\t\u0003\u0001\u0005BB \u0002\t\u0003\u0019yI\u0002\u0004\u0004\u0018\u0006\u00111\u0011\u0014\u0005\n\u00077+!\u0011!Q\u0001\n\u0011Cq!P\u0003\u0005\u0002\u0005\u0019iJ\u0002\u0004\u0004&\u0006\u00111q\u0015\u0005\n\u00077C!\u0011!Q\u0001\n]Cq!\u0010\u0005\u0005\u0002\u0005\u0019IKB\u00057SA\u0005\u0019\u0011A'\u0004\b\")aj\u0003C\u0001\u001f\")1k\u0003C\u0003)\")ap\u0003C\u0003\u007f\"9\u0011qF\u0006\u0005\u0002\u0005E\u0002bBA%\u0017\u0011\u0005\u00111\n\u0005\b\u0003WZA\u0011AA7\u0011\u001d\t)i\u0003C\u0001\u0003\u000fCq!!\u0013\f\t\u0003\t\t\u000bC\u0004\u0002J-!\t!!2\t\u000f\u0005%3\u0002\"\u0001\u0002d\"9\u0011\u0011J\u0006\u0005\u0002\u0005u\bbBA%\u0017\u0011\u0005!Q\u0003\u0005\b\u0003\u0013ZA\u0011\u0001B\u0017\u0011\u001d\tIe\u0003C\u0001\u0005\u0017BqAa\u001b\f\t\u0003\u0011i\u0007C\u0004\u0003l-!\tAa!\t\u000f\t-4\u0002\"\u0001\u0003\u0018\"9!1N\u0006\u0005\u0002\t-\u0006b\u0002B6\u0017\u0011\u0005!q\u0018\u0005\b\u0005WZA\u0011\u0001Bj\u0011\u001d\u0011Yg\u0003C\u0001\u0005ODqAa@\f\t\u0013\u0019\t\u0001C\u0004\u0004\u001a-!\taa\u0007\t\u000f\r=2\u0002\"\u0001\u00042!91qH\u0006\u0005\u0002\r\u0005\u0003bBB(\u0017\u0011\u00051\u0011\u000b\u0005\b\u0007?ZA\u0011AB1\u0011\u001d\u0019)h\u0003C\u0005\u0007o\n!c\u00117bgN$\u0016mZ#yi\u0016t7/[8og*\u0011!fK\u0001\u0006g\u000e\fG.\u0019\u0006\u0003Y5\na!\\8ek2,'B\u0001\u00180\u0003\u001dQ\u0017mY6t_:T!\u0001M\u0019\u0002\u0013\u0019\f7\u000f^3sq6d'\"\u0001\u001a\u0002\u0007\r|Wn\u0001\u0001\u0011\u0005U\nQ\"A\u0015\u0003%\rc\u0017m]:UC\u001e,\u0005\u0010^3og&|gn]\n\u0003\u0003a\u0002\"!O\u001e\u000e\u0003iR\u0011AK\u0005\u0003yi\u0012a!\u00118z%\u00164\u0017A\u0002\u001fj]&$h\bF\u00015\u00031!3m\u001c7p]\u0012\u001aw\u000e\\8o)\r\t51\u0012\n\u0004\u0005\u0012ce\u0001B\"\u0002\u0001\u0005\u0013A\u0002\u0010:fM&tW-\\3oiz\u0002\"!\u0012&\u000e\u0003\u0019S!a\u0012%\u0002\t)\u001cxN\u001c\u0006\u0003\u00136\n\u0001\u0002Z1uC\nLg\u000eZ\u0005\u0003\u0017\u001a\u0013!BS:p]6\u000b\u0007\u000f]3s!\t)4b\u0005\u0002\fq\u00051A%\u001b8ji\u0012\"\u0012\u0001\u0015\t\u0003sEK!A\u0015\u001e\u0003\tUs\u0017\u000e^\u0001\tC\u0012$W*\u001b=j]V\u0019Q+\u001a:\u0015\u0003Y#2aV.o!\tA\u0016,D\u0001I\u0013\tQ\u0006J\u0001\u0007PE*,7\r^'baB,'\u000fC\u0004]\u001b\u0005\u0005\t9A/\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007E\u0002_C\u000el\u0011a\u0018\u0006\u0003Aj\nqA]3gY\u0016\u001cG/\u0003\u0002c?\nA1\t\\1tgR\u000bw\r\u0005\u0002eK2\u0001A!\u00024\u000e\u0005\u00049'A\u0002+be\u001e,G/\u0005\u0002iWB\u0011\u0011([\u0005\u0003Uj\u0012qAT8uQ&tw\r\u0005\u0002:Y&\u0011QN\u000f\u0002\u0004\u0003:L\bbB8\u000e\u0003\u0003\u0005\u001d\u0001]\u0001\u000bKZLG-\u001a8dK\u0012\u0012\u0004c\u00010bcB\u0011AM\u001d\u0003\u0006g6\u0011\ra\u001a\u0002\f\u001b&D\u0018N\\*pkJ\u001cW\r\u000b\u0004\u000ekbL8\u0010 \t\u0003sYL!a\u001e\u001e\u0003\u0015\u0011,\u0007O]3dCR,G-A\u0004nKN\u001c\u0018mZ3\"\u0003i\fq\b\u001e5jg\u0002\u001aX\u000f\u001d9peR\u0004\u0013N\u001c\u0011kC\u000e\\7o\u001c8.I\u0006$\u0018MY5oI\u0002J7\u000fI7pm&tw\r\t;pAQDW\rI'baB,'OQ;jY\u0012,'/A\u0003tS:\u001cW-I\u0001~\u0003\u0019\u0011d&\r\u001a/e\u0005\tb-\u001b8e\u001b&D\u0018J\\\"mCN\u001chi\u001c:\u0016\t\u0005\u0005\u0011\u0011\u0006\u000b\u0005\u0003\u0007\t\t\u0003\r\u0003\u0002\u0006\u0005u\u0001CBA\u0004\u0003+\tYB\u0004\u0003\u0002\n\u0005E\u0001cAA\u0006u5\u0011\u0011Q\u0002\u0006\u0004\u0003\u001f\u0019\u0014A\u0002\u001fs_>$h(C\u0002\u0002\u0014i\na\u0001\u0015:fI\u00164\u0017\u0002BA\f\u00033\u0011Qa\u00117bgNT1!a\u0005;!\r!\u0017Q\u0004\u0003\u000b\u0003?q\u0011\u0011!A\u0001\u0006\u00039'aA0%c!I\u00111\u0005\b\u0002\u0002\u0003\u000f\u0011QE\u0001\u000bKZLG-\u001a8dK\u0012\u001a\u0004\u0003\u00020b\u0003O\u00012\u0001ZA\u0015\t\u0019\tYC\u0004b\u0001O\n\tA\u000b\u000b\u0004\u000fkbL8\u0010`\u0001\u000eG>t7\u000f\u001e:vGR$\u0016\u0010]3\u0016\t\u0005M\u0012q\t\u000b\u0005\u0003k\tY\u0004E\u0002Y\u0003oI1!!\u000fI\u0005!Q\u0015M^1UsB,\u0007\"CA\u001f\u001f\u0005\u0005\t9AA \u0003))g/\u001b3f]\u000e,G\u0005\u000e\t\u0006k\u0005\u0005\u0013QI\u0005\u0004\u0003\u0007J#\u0001\u0004&bm\u0006$\u0016\u0010]3bE2,\u0007c\u00013\u0002H\u00111\u00111F\bC\u0002\u001d\f\u0011B]3bIZ\u000bG.^3\u0016\t\u00055\u00131\u000b\u000b\u0005\u0003\u001f\nY\u0006\u0006\u0003\u0002R\u0005U\u0003c\u00013\u0002T\u00111\u00111\u0006\tC\u0002\u001dD\u0011\"a\u0016\u0011\u0003\u0003\u0005\u001d!!\u0017\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$S\u0007E\u00036\u0003\u0003\n\t\u0006C\u0004\u0002^A\u0001\r!a\u0018\u0002\u0005)\u0004\b\u0003BA1\u0003Oj!!a\u0019\u000b\u0007\u0005\u0015T&\u0001\u0003d_J,\u0017\u0002BA5\u0003G\u0012!BS:p]B\u000b'o]3s\u0003)\u0011X-\u00193WC2,Xm]\u000b\u0005\u0003_\nY\b\u0006\u0003\u0002r\u0005\rE\u0003BA:\u0003{\u0002R\u0001WA;\u0003sJ1!a\u001eI\u0005=i\u0015\r\u001d9j]\u001eLE/\u001a:bi>\u0014\bc\u00013\u0002|\u00111\u00111F\tC\u0002\u001dD\u0011\"a \u0012\u0003\u0003\u0005\u001d!!!\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$c\u0007E\u00036\u0003\u0003\nI\bC\u0004\u0002^E\u0001\r!a\u0018\u0002\u0017Q\u0014X-\u001a+p-\u0006dW/Z\u000b\u0005\u0003\u0013\u000by\t\u0006\u0003\u0002\f\u0006]E\u0003BAG\u0003#\u00032\u0001ZAH\t\u0019\tYC\u0005b\u0001O\"I\u00111\u0013\n\u0002\u0002\u0003\u000f\u0011QS\u0001\u000bKZLG-\u001a8dK\u0012:\u0004#B\u001b\u0002B\u00055\u0005bBAM%\u0001\u0007\u00111T\u0001\u0002]B!\u0011\u0011MAO\u0013\u0011\ty*a\u0019\u0003\u0011Q\u0013X-\u001a(pI\u0016,B!a)\u0002*R!\u0011QUAY)\u0011\t9+a+\u0011\u0007\u0011\fI\u000b\u0002\u0004\u0002,M\u0011\ra\u001a\u0005\n\u0003[\u001b\u0012\u0011!a\u0002\u0003_\u000b!\"\u001a<jI\u0016t7-\u001a\u00139!\u0015)\u0014\u0011IAT\u0011\u001d\t\u0019l\u0005a\u0001\u0003k\u000b1a\u001d:d!\u0011\t9,!1\u000e\u0005\u0005e&\u0002BA^\u0003{\u000b!![8\u000b\u0005\u0005}\u0016\u0001\u00026bm\u0006LA!a1\u0002:\n!a)\u001b7f+\u0011\t9-!4\u0015\t\u0005%\u0017Q\u001b\u000b\u0005\u0003\u0017\fy\rE\u0002e\u0003\u001b$a!a\u000b\u0015\u0005\u00049\u0007\"CAi)\u0005\u0005\t9AAj\u0003))g/\u001b3f]\u000e,G%\u000f\t\u0006k\u0005\u0005\u00131\u001a\u0005\b\u0003g#\u0002\u0019AAl!\u0011\tI.a8\u000e\u0005\u0005m'\u0002BAo\u0003{\u000b1A\\3u\u0013\u0011\t\t/a7\u0003\u0007U\u0013F*\u0006\u0003\u0002f\u0006-H\u0003BAt\u0003g$B!!;\u0002nB\u0019A-a;\u0005\r\u0005-RC1\u0001h\u0011%\ty/FA\u0001\u0002\b\t\t0A\u0006fm&$WM\\2fIE\u0002\u0004#B\u001b\u0002B\u0005%\bbBA{+\u0001\u0007\u0011q_\u0001\bG>tG/\u001a8u!\u0011\t9!!?\n\t\u0005m\u0018\u0011\u0004\u0002\u0007'R\u0014\u0018N\\4\u0016\t\u0005}(Q\u0001\u000b\u0005\u0005\u0003\u0011i\u0001\u0006\u0003\u0003\u0004\t\u001d\u0001c\u00013\u0003\u0006\u00111\u00111\u0006\fC\u0002\u001dD\u0011B!\u0003\u0017\u0003\u0003\u0005\u001dAa\u0003\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013'\r\t\u0006k\u0005\u0005#1\u0001\u0005\b\u0003g3\u0002\u0019\u0001B\b!\u0011\t9L!\u0005\n\t\tM\u0011\u0011\u0018\u0002\u0007%\u0016\fG-\u001a:\u0016\t\t]!Q\u0004\u000b\u0005\u00053\u0011)\u0003\u0006\u0003\u0003\u001c\t}\u0001c\u00013\u0003\u001e\u00111\u00111F\fC\u0002\u001dD\u0011B!\t\u0018\u0003\u0003\u0005\u001dAa\t\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013G\r\t\u0006k\u0005\u0005#1\u0004\u0005\b\u0003g;\u0002\u0019\u0001B\u0014!\u0011\t9L!\u000b\n\t\t-\u0012\u0011\u0018\u0002\f\u0013:\u0004X\u000f^*ue\u0016\fW.\u0006\u0003\u00030\tUB\u0003\u0002B\u0019\u0005{!BAa\r\u00038A\u0019AM!\u000e\u0005\r\u0005-\u0002D1\u0001h\u0011%\u0011I\u0004GA\u0001\u0002\b\u0011Y$A\u0006fm&$WM\\2fIE\u001a\u0004#B\u001b\u0002B\tM\u0002bBAZ1\u0001\u0007!q\b\t\u0006s\t\u0005#QI\u0005\u0004\u0005\u0007R$!B!se\u0006L\bcA\u001d\u0003H%\u0019!\u0011\n\u001e\u0003\t\tKH/Z\u000b\u0005\u0005\u001b\u0012\u0019\u0006\u0006\u0005\u0003P\tm#Q\fB4)\u0011\u0011\tF!\u0016\u0011\u0007\u0011\u0014\u0019\u0006\u0002\u0004\u0002,e\u0011\ra\u001a\u0005\n\u0005/J\u0012\u0011!a\u0002\u00053\n1\"\u001a<jI\u0016t7-\u001a\u00132iA)Q'!\u0011\u0003R!9\u00111W\rA\u0002\t}\u0002b\u0002B03\u0001\u0007!\u0011M\u0001\u0007_\u001a47/\u001a;\u0011\u0007e\u0012\u0019'C\u0002\u0003fi\u00121!\u00138u\u0011\u001d\u0011I'\u0007a\u0001\u0005C\n1\u0001\\3o\u0003-)\b\u000fZ1uKZ\u000bG.^3\u0016\t\t=$Q\u000f\u000b\u0007\u0005c\u0012iH!!\u0015\t\tM$q\u000f\t\u0004I\nUDABA\u00165\t\u0007q\rC\u0005\u0003zi\t\t\u0011q\u0001\u0003|\u0005YQM^5eK:\u001cW\rJ\u00196!\u0015)\u0014\u0011\tB:\u0011\u001d\u0011yH\u0007a\u0001\u0005g\nQB^1mk\u0016$v.\u00169eCR,\u0007bBAZ5\u0001\u0007\u0011QW\u000b\u0005\u0005\u000b\u0013Y\t\u0006\u0004\u0003\b\nM%Q\u0013\u000b\u0005\u0005\u0013\u0013i\tE\u0002e\u0005\u0017#a!a\u000b\u001c\u0005\u00049\u0007\"\u0003BH7\u0005\u0005\t9\u0001BI\u0003-)g/\u001b3f]\u000e,G%\r\u001c\u0011\u000bU\n\tE!#\t\u000f\t}4\u00041\u0001\u0003\n\"9\u00111W\u000eA\u0002\u0005]W\u0003\u0002BM\u0005?#bAa'\u0003(\n%F\u0003\u0002BO\u0005C\u00032\u0001\u001aBP\t\u0019\tY\u0003\bb\u0001O\"I!1\u0015\u000f\u0002\u0002\u0003\u000f!QU\u0001\fKZLG-\u001a8dK\u0012\nt\u0007E\u00036\u0003\u0003\u0012i\nC\u0004\u0003\u0000q\u0001\rA!(\t\u000f\u0005UH\u00041\u0001\u0002xV!!Q\u0016BZ)\u0019\u0011yKa/\u0003>R!!\u0011\u0017B[!\r!'1\u0017\u0003\u0007\u0003Wi\"\u0019A4\t\u0013\t]V$!AA\u0004\te\u0016aC3wS\u0012,gnY3%ca\u0002R!NA!\u0005cCqAa \u001e\u0001\u0004\u0011\t\fC\u0004\u00024v\u0001\rAa\u0004\u0016\t\t\u0005'q\u0019\u000b\u0007\u0005\u0007\u0014yM!5\u0015\t\t\u0015'\u0011\u001a\t\u0004I\n\u001dGABA\u0016=\t\u0007q\rC\u0005\u0003Lz\t\t\u0011q\u0001\u0003N\u0006YQM^5eK:\u001cW\rJ\u0019:!\u0015)\u0014\u0011\tBc\u0011\u001d\u0011yH\ba\u0001\u0005\u000bDq!a-\u001f\u0001\u0004\u00119#\u0006\u0003\u0003V\nmGC\u0002Bl\u0005G\u0014)\u000f\u0006\u0003\u0003Z\nu\u0007c\u00013\u0003\\\u00121\u00111F\u0010C\u0002\u001dD\u0011Ba8 \u0003\u0003\u0005\u001dA!9\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$#\u0007\r\t\u0006k\u0005\u0005#\u0011\u001c\u0005\b\u0005\u007fz\u0002\u0019\u0001Bm\u0011\u001d\t\u0019l\ba\u0001\u0005\u007f)BA!;\u0003pRQ!1\u001eB|\u0005s\u0014YP!@\u0015\t\t5(\u0011\u001f\t\u0004I\n=HABA\u0016A\t\u0007q\rC\u0005\u0003t\u0002\n\t\u0011q\u0001\u0003v\u0006YQM^5eK:\u001cW\r\n\u001a2!\u0015)\u0014\u0011\tBw\u0011\u001d\u0011y\b\ta\u0001\u0005[Dq!a-!\u0001\u0004\u0011y\u0004C\u0004\u0003`\u0001\u0002\rA!\u0019\t\u000f\t%\u0004\u00051\u0001\u0003b\u0005yqN\u00196fGR\u0014V-\u00193fe\u001a{'/\u0006\u0003\u0004\u0004\rUA\u0003BB\u0003\u0007/!Baa\u0002\u0004\u000eA\u0019\u0001l!\u0003\n\u0007\r-\u0001J\u0001\u0007PE*,7\r\u001e*fC\u0012,'\u000fC\u0005\u0004\u0010\u0005\n\t\u0011q\u0001\u0004\u0012\u0005YQM^5eK:\u001cW\r\n\u001a3!\u0015)\u0014\u0011IB\n!\r!7Q\u0003\u0003\u0007\u0003W\t#\u0019A4\t\u000f\t}\u0014\u00051\u0001\u0004\u0014\u0005qqO]5uKJ<\u0016\u000e\u001e5WS\u0016<X\u0003BB\u000f\u0007[!Baa\b\u0004&A\u0019\u0001l!\t\n\u0007\r\r\u0002J\u0001\u0007PE*,7\r^,sSR,'\u000fC\u0005\u0004(\t\n\t\u0011q\u0001\u0004*\u0005YQM^5eK:\u001cW\r\n\u001a4!\u0011q\u0016ma\u000b\u0011\u0007\u0011\u001ci\u0003\u0002\u0004\u0002,\t\u0012\raZ\u0001\noJLG/\u001a:G_J,Baa\r\u0004>Q!1qDB\u001b\u0011%\u00199dIA\u0001\u0002\b\u0019I$A\u0006fm&$WM\\2fII\"\u0004#B\u001b\u0002B\rm\u0002c\u00013\u0004>\u00111\u00111F\u0012C\u0002\u001d\f\u0011B]3bI\u0016\u0014hi\u001c:\u0016\t\r\r3Q\n\u000b\u0005\u0007\u000f\u0019)\u0005C\u0005\u0004H\u0011\n\t\u0011q\u0001\u0004J\u0005YQM^5eK:\u001cW\r\n\u001a6!\u0015)\u0014\u0011IB&!\r!7Q\n\u0003\u0007\u0003W!#\u0019A4\u0002\u001dI,\u0017\rZ3s/&$\bNV5foV!11KB/)\u0011\u00199a!\u0016\t\u0013\r]S%!AA\u0004\re\u0013aC3wS\u0012,gnY3%eY\u0002BAX1\u0004\\A\u0019Am!\u0018\u0005\r\u0005-RE1\u0001h\u00031\u0019wN\u001c<feR4\u0016\r\\;f+\u0011\u0019\u0019g!\u001b\u0015\t\r\u00154\u0011\u000f\u000b\u0005\u0007O\u001aY\u0007E\u0002e\u0007S\"a!a\u000b'\u0005\u00049\u0007\"CB7M\u0005\u0005\t9AB8\u0003-)g/\u001b3f]\u000e,GEM\u001c\u0011\u000bU\n\tea\u001a\t\r\rMd\u00051\u0001l\u0003%1'o\\7WC2,X-\u0001\u0005dY\u0006\u001c8OR8s+\u0011\u0019Iha \u0015\t\rm4\u0011\u0011\t\u0007\u0003\u000f\t)b! \u0011\u0007\u0011\u001cy\b\u0002\u0004\u0002,\u001d\u0012\ra\u001a\u0005\n\u0007\u0007;\u0013\u0011!a\u0002\u0007\u000b\u000b1\"\u001a<jI\u0016t7-\u001a\u00133qA!a,YB?%\u0011\u0019I\tT,\u0007\u000b\r\u0003\u0001aa\"\t\r\r55\u00011\u0001E\u0003\u0005yG\u0003BBI\u0007+\u0013Baa%X\u0019\u001a)1)\u0001\u0001\u0004\u0012\"11Q\u0012\u0003A\u0002]\u0013Q!T5yS:\u001c2!\u0002#M\u0003\u0019i\u0017\r\u001d9feR!1qTBR!\r\u0019\t+B\u0007\u0002\u0003!111T\u0004A\u0002\u0011\u0013\u0011c\u00142kK\u000e$X*\u00199qKJl\u0015\u000e_5o'\rAq\u000b\u0014\u000b\u0005\u0007W\u001bi\u000bE\u0002\u0004\"\"Aaaa'\u000b\u0001\u00049\u0006"
)
public interface ClassTagExtensions {
   static ObjectMapper $colon$colon(final ObjectMapper o) {
      return ClassTagExtensions$.MODULE$.$colon$colon(o);
   }

   static JsonMapper $colon$colon(final JsonMapper o) {
      return ClassTagExtensions$.MODULE$.$colon$colon(o);
   }

   /** @deprecated */
   default ObjectMapper addMixin(final ClassTag evidence$1, final ClassTag evidence$2) {
      return ((ObjectMapper)this).addMixIn(this.classFor(evidence$1), this.classFor(evidence$2));
   }

   /** @deprecated */
   default Class findMixInClassFor(final ClassTag evidence$3) {
      return ((ObjectMapper)this).findMixInClassFor(this.classFor(evidence$3));
   }

   default JavaType constructType(final JavaTypeable evidence$4) {
      return ((JavaTypeable).MODULE$.implicitly(evidence$4)).asJavaType(((ObjectMapper)this).getTypeFactory());
   }

   default Object readValue(final JsonParser jp, final JavaTypeable evidence$5) {
      return ((ObjectMapper)this).readValue(jp, this.constructType(evidence$5));
   }

   default MappingIterator readValues(final JsonParser jp, final JavaTypeable evidence$6) {
      return ((ObjectMapper)this).readValues(jp, this.constructType(evidence$6));
   }

   default Object treeToValue(final TreeNode n, final JavaTypeable evidence$7) {
      return ((ObjectMapper)this).treeToValue(n, this.constructType(evidence$7));
   }

   default Object readValue(final File src, final JavaTypeable evidence$8) {
      return ((ObjectMapper)this).readValue(src, this.constructType(evidence$8));
   }

   default Object readValue(final URL src, final JavaTypeable evidence$9) {
      return ((ObjectMapper)this).readValue(src, this.constructType(evidence$9));
   }

   default Object readValue(final String content, final JavaTypeable evidence$10) {
      return ((ObjectMapper)this).readValue(content, this.constructType(evidence$10));
   }

   default Object readValue(final Reader src, final JavaTypeable evidence$11) {
      return ((ObjectMapper)this).readValue(src, this.constructType(evidence$11));
   }

   default Object readValue(final InputStream src, final JavaTypeable evidence$12) {
      return ((ObjectMapper)this).readValue(src, this.constructType(evidence$12));
   }

   default Object readValue(final byte[] src, final JavaTypeable evidence$13) {
      return ((ObjectMapper)this).readValue(src, this.constructType(evidence$13));
   }

   default Object readValue(final byte[] src, final int offset, final int len, final JavaTypeable evidence$14) {
      return ((ObjectMapper)this).readValue(src, offset, len, this.constructType(evidence$14));
   }

   default Object updateValue(final Object valueToUpdate, final File src, final JavaTypeable evidence$15) {
      return this.objectReaderFor(valueToUpdate, evidence$15).readValue(src);
   }

   default Object updateValue(final Object valueToUpdate, final URL src, final JavaTypeable evidence$16) {
      return this.objectReaderFor(valueToUpdate, evidence$16).readValue(src);
   }

   default Object updateValue(final Object valueToUpdate, final String content, final JavaTypeable evidence$17) {
      return this.objectReaderFor(valueToUpdate, evidence$17).readValue(content);
   }

   default Object updateValue(final Object valueToUpdate, final Reader src, final JavaTypeable evidence$18) {
      return this.objectReaderFor(valueToUpdate, evidence$18).readValue(src);
   }

   default Object updateValue(final Object valueToUpdate, final InputStream src, final JavaTypeable evidence$19) {
      return this.objectReaderFor(valueToUpdate, evidence$19).readValue(src);
   }

   default Object updateValue(final Object valueToUpdate, final byte[] src, final JavaTypeable evidence$20) {
      return this.objectReaderFor(valueToUpdate, evidence$20).readValue(src);
   }

   default Object updateValue(final Object valueToUpdate, final byte[] src, final int offset, final int len, final JavaTypeable evidence$21) {
      return this.objectReaderFor(valueToUpdate, evidence$21).readValue(src, offset, len);
   }

   private ObjectReader objectReaderFor(final Object valueToUpdate, final JavaTypeable evidence$22) {
      return ((ObjectMapper)this).readerForUpdating(valueToUpdate).forType(this.constructType(evidence$22));
   }

   default ObjectWriter writerWithView(final ClassTag evidence$23) {
      return ((ObjectMapper)this).writerWithView(this.classFor(evidence$23));
   }

   default ObjectWriter writerFor(final JavaTypeable evidence$24) {
      return ((ObjectMapper)this).writerFor(this.constructType(evidence$24));
   }

   default ObjectReader readerFor(final JavaTypeable evidence$25) {
      return ((ObjectMapper)this).readerFor(this.constructType(evidence$25));
   }

   default ObjectReader readerWithView(final ClassTag evidence$26) {
      return ((ObjectMapper)this).readerWithView(this.classFor(evidence$26));
   }

   default Object convertValue(final Object fromValue, final JavaTypeable evidence$27) {
      return ((ObjectMapper)this).convertValue(fromValue, this.constructType(evidence$27));
   }

   private Class classFor(final ClassTag evidence$28) {
      return ((ClassTag).MODULE$.implicitly(evidence$28)).runtimeClass();
   }

   static void $init$(final ClassTagExtensions $this) {
   }

   public static final class Mixin extends JsonMapper implements ClassTagExtensions {
      /** @deprecated */
      public final ObjectMapper addMixin(final ClassTag evidence$1, final ClassTag evidence$2) {
         return ClassTagExtensions.super.addMixin(evidence$1, evidence$2);
      }

      /** @deprecated */
      public final Class findMixInClassFor(final ClassTag evidence$3) {
         return ClassTagExtensions.super.findMixInClassFor(evidence$3);
      }

      public JavaType constructType(final JavaTypeable evidence$4) {
         return ClassTagExtensions.super.constructType(evidence$4);
      }

      public Object readValue(final JsonParser jp, final JavaTypeable evidence$5) {
         return ClassTagExtensions.super.readValue(jp, evidence$5);
      }

      public MappingIterator readValues(final JsonParser jp, final JavaTypeable evidence$6) {
         return ClassTagExtensions.super.readValues(jp, evidence$6);
      }

      public Object treeToValue(final TreeNode n, final JavaTypeable evidence$7) {
         return ClassTagExtensions.super.treeToValue(n, evidence$7);
      }

      public Object readValue(final File src, final JavaTypeable evidence$8) {
         return ClassTagExtensions.super.readValue(src, evidence$8);
      }

      public Object readValue(final URL src, final JavaTypeable evidence$9) {
         return ClassTagExtensions.super.readValue(src, evidence$9);
      }

      public Object readValue(final String content, final JavaTypeable evidence$10) {
         return ClassTagExtensions.super.readValue(content, evidence$10);
      }

      public Object readValue(final Reader src, final JavaTypeable evidence$11) {
         return ClassTagExtensions.super.readValue(src, evidence$11);
      }

      public Object readValue(final InputStream src, final JavaTypeable evidence$12) {
         return ClassTagExtensions.super.readValue(src, evidence$12);
      }

      public Object readValue(final byte[] src, final JavaTypeable evidence$13) {
         return ClassTagExtensions.super.readValue(src, evidence$13);
      }

      public Object readValue(final byte[] src, final int offset, final int len, final JavaTypeable evidence$14) {
         return ClassTagExtensions.super.readValue(src, offset, len, evidence$14);
      }

      public Object updateValue(final Object valueToUpdate, final File src, final JavaTypeable evidence$15) {
         return ClassTagExtensions.super.updateValue(valueToUpdate, src, evidence$15);
      }

      public Object updateValue(final Object valueToUpdate, final URL src, final JavaTypeable evidence$16) {
         return ClassTagExtensions.super.updateValue(valueToUpdate, src, evidence$16);
      }

      public Object updateValue(final Object valueToUpdate, final String content, final JavaTypeable evidence$17) {
         return ClassTagExtensions.super.updateValue(valueToUpdate, content, evidence$17);
      }

      public Object updateValue(final Object valueToUpdate, final Reader src, final JavaTypeable evidence$18) {
         return ClassTagExtensions.super.updateValue(valueToUpdate, src, evidence$18);
      }

      public Object updateValue(final Object valueToUpdate, final InputStream src, final JavaTypeable evidence$19) {
         return ClassTagExtensions.super.updateValue(valueToUpdate, src, evidence$19);
      }

      public Object updateValue(final Object valueToUpdate, final byte[] src, final JavaTypeable evidence$20) {
         return ClassTagExtensions.super.updateValue(valueToUpdate, src, evidence$20);
      }

      public Object updateValue(final Object valueToUpdate, final byte[] src, final int offset, final int len, final JavaTypeable evidence$21) {
         return ClassTagExtensions.super.updateValue(valueToUpdate, src, offset, len, evidence$21);
      }

      public ObjectWriter writerWithView(final ClassTag evidence$23) {
         return ClassTagExtensions.super.writerWithView(evidence$23);
      }

      public ObjectWriter writerFor(final JavaTypeable evidence$24) {
         return ClassTagExtensions.super.writerFor(evidence$24);
      }

      public ObjectReader readerFor(final JavaTypeable evidence$25) {
         return ClassTagExtensions.super.readerFor(evidence$25);
      }

      public ObjectReader readerWithView(final ClassTag evidence$26) {
         return ClassTagExtensions.super.readerWithView(evidence$26);
      }

      public Object convertValue(final Object fromValue, final JavaTypeable evidence$27) {
         return ClassTagExtensions.super.convertValue(fromValue, evidence$27);
      }

      public Mixin(final JsonMapper mapper) {
         super(mapper);
         ClassTagExtensions.$init$(this);
      }
   }

   public static final class ObjectMapperMixin extends ObjectMapper implements ClassTagExtensions {
      /** @deprecated */
      public final ObjectMapper addMixin(final ClassTag evidence$1, final ClassTag evidence$2) {
         return ClassTagExtensions.super.addMixin(evidence$1, evidence$2);
      }

      /** @deprecated */
      public final Class findMixInClassFor(final ClassTag evidence$3) {
         return ClassTagExtensions.super.findMixInClassFor(evidence$3);
      }

      public JavaType constructType(final JavaTypeable evidence$4) {
         return ClassTagExtensions.super.constructType(evidence$4);
      }

      public Object readValue(final JsonParser jp, final JavaTypeable evidence$5) {
         return ClassTagExtensions.super.readValue(jp, evidence$5);
      }

      public MappingIterator readValues(final JsonParser jp, final JavaTypeable evidence$6) {
         return ClassTagExtensions.super.readValues(jp, evidence$6);
      }

      public Object treeToValue(final TreeNode n, final JavaTypeable evidence$7) {
         return ClassTagExtensions.super.treeToValue(n, evidence$7);
      }

      public Object readValue(final File src, final JavaTypeable evidence$8) {
         return ClassTagExtensions.super.readValue(src, evidence$8);
      }

      public Object readValue(final URL src, final JavaTypeable evidence$9) {
         return ClassTagExtensions.super.readValue(src, evidence$9);
      }

      public Object readValue(final String content, final JavaTypeable evidence$10) {
         return ClassTagExtensions.super.readValue(content, evidence$10);
      }

      public Object readValue(final Reader src, final JavaTypeable evidence$11) {
         return ClassTagExtensions.super.readValue(src, evidence$11);
      }

      public Object readValue(final InputStream src, final JavaTypeable evidence$12) {
         return ClassTagExtensions.super.readValue(src, evidence$12);
      }

      public Object readValue(final byte[] src, final JavaTypeable evidence$13) {
         return ClassTagExtensions.super.readValue(src, evidence$13);
      }

      public Object readValue(final byte[] src, final int offset, final int len, final JavaTypeable evidence$14) {
         return ClassTagExtensions.super.readValue(src, offset, len, evidence$14);
      }

      public Object updateValue(final Object valueToUpdate, final File src, final JavaTypeable evidence$15) {
         return ClassTagExtensions.super.updateValue(valueToUpdate, src, evidence$15);
      }

      public Object updateValue(final Object valueToUpdate, final URL src, final JavaTypeable evidence$16) {
         return ClassTagExtensions.super.updateValue(valueToUpdate, src, evidence$16);
      }

      public Object updateValue(final Object valueToUpdate, final String content, final JavaTypeable evidence$17) {
         return ClassTagExtensions.super.updateValue(valueToUpdate, content, evidence$17);
      }

      public Object updateValue(final Object valueToUpdate, final Reader src, final JavaTypeable evidence$18) {
         return ClassTagExtensions.super.updateValue(valueToUpdate, src, evidence$18);
      }

      public Object updateValue(final Object valueToUpdate, final InputStream src, final JavaTypeable evidence$19) {
         return ClassTagExtensions.super.updateValue(valueToUpdate, src, evidence$19);
      }

      public Object updateValue(final Object valueToUpdate, final byte[] src, final JavaTypeable evidence$20) {
         return ClassTagExtensions.super.updateValue(valueToUpdate, src, evidence$20);
      }

      public Object updateValue(final Object valueToUpdate, final byte[] src, final int offset, final int len, final JavaTypeable evidence$21) {
         return ClassTagExtensions.super.updateValue(valueToUpdate, src, offset, len, evidence$21);
      }

      public ObjectWriter writerWithView(final ClassTag evidence$23) {
         return ClassTagExtensions.super.writerWithView(evidence$23);
      }

      public ObjectWriter writerFor(final JavaTypeable evidence$24) {
         return ClassTagExtensions.super.writerFor(evidence$24);
      }

      public ObjectReader readerFor(final JavaTypeable evidence$25) {
         return ClassTagExtensions.super.readerFor(evidence$25);
      }

      public ObjectReader readerWithView(final ClassTag evidence$26) {
         return ClassTagExtensions.super.readerWithView(evidence$26);
      }

      public Object convertValue(final Object fromValue, final JavaTypeable evidence$27) {
         return ClassTagExtensions.super.convertValue(fromValue, evidence$27);
      }

      public ObjectMapperMixin(final ObjectMapper mapper) {
         super(mapper);
         ClassTagExtensions.$init$(this);
      }
   }
}
