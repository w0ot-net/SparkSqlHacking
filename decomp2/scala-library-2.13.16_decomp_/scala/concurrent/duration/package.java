package scala.concurrent.duration;

import java.util.concurrent.TimeUnit;
import scala.Tuple2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r\u0005t!B:u\u0011\u0003Yh!B?u\u0011\u0003q\bbBA\u0004\u0003\u0011\u0005\u0011\u0011B\u0004\b\u0003\u0017\t\u0001\u0012AA\u0007\r\u001d\t\t\"\u0001E\u0001\u0003'Aq!a\u0002\u0005\t\u0003\t)bB\u0004\u0002\u0018\u0005A\t!!\u0007\u0007\u000f\u0005m\u0011\u0001#\u0001\u0002\u001e!9\u0011qA\u0004\u0005\u0002\u0005}QABA\u0011\u0003\u0001\t\u0019\u0003C\u0005\u00024\u0005\u0011\r\u0011\"\u0002\u00026!A\u0011\u0011I\u0001!\u0002\u001b\t9\u0004C\u0005\u0002D\u0005\u0011\r\u0011\"\u0002\u0002F!A\u0011QJ\u0001!\u0002\u001b\t9\u0005C\u0005\u0002P\u0005\u0011\r\u0011\"\u0002\u0002R!A\u0011\u0011L\u0001!\u0002\u001b\t\u0019\u0006C\u0005\u0002\\\u0005\u0011\r\u0011\"\u0002\u0002^!A\u0011QM\u0001!\u0002\u001b\ty\u0006C\u0005\u0002h\u0005\u0011\r\u0011\"\u0002\u0002j!A\u0011\u0011O\u0001!\u0002\u001b\tY\u0007C\u0005\u0002t\u0005\u0011\r\u0011\"\u0002\u0002v!A\u0011QP\u0001!\u0002\u001b\t9\bC\u0005\u0002\u0000\u0005\u0011\r\u0011\"\u0002\u0002\u0002\"A\u0011\u0011R\u0001!\u0002\u001b\t\u0019\tC\u0004\u0002\f\u0006!\u0019!!$\t\u000f\u0005\u001d\u0016\u0001b\u0001\u0002*\"9\u00111X\u0001\u0005\u0004\u0005ufABAb\u0003\r\t)\r\u0003\b\u0002Tn!\t\u0011!B\u0003\u0006\u0004%I!!6\t\u0017\u0005]7D!B\u0001B\u0003%\u0011q\u0014\u0005\b\u0003\u000fYB\u0011AAm\u0011\u001d\t\to\u0007C)\u0003GD\u0011\"!;\u001c\u0003\u0003%\t%a;\t\u0013\u000558$!A\u0005B\u0005=x!\u0003B\u0001\u0003\u0005\u0005\t\u0012\u0001B\u0002\r%\t\u0019-AA\u0001\u0012\u0003\u0011)\u0001C\u0004\u0002\b\r\"\tAa\u0002\t\u000f\t%1\u0005\"\u0002\u0003\f!I!QC\u0012\u0002\u0002\u0013\u0015!q\u0003\u0005\n\u00057\u0019\u0013\u0011!C\u0003\u0005;A\u0011B!\u0001\u0002\u0003\u0003%9A!\n\u0007\r\t%\u0012a\u0001B\u0016\u00119\u0011i#\u000bC\u0001\u0002\u000b\u0015)\u0019!C\u0005\u0005_A1B!\r*\u0005\u000b\u0005\t\u0015!\u0003\u00026\"9\u0011qA\u0015\u0005\u0002\tM\u0002bBAqS\u0011E#\u0011\b\u0005\n\u0003SL\u0013\u0011!C!\u0003WD\u0011\"!<*\u0003\u0003%\tE!\u0010\b\u0013\t\u0005\u0013!!A\t\u0002\t\rc!\u0003B\u0015\u0003\u0005\u0005\t\u0012\u0001B#\u0011\u001d\t9!\rC\u0001\u0005\u000fBqA!\u00032\t\u000b\u0011I\u0005C\u0005\u0003\u0016E\n\t\u0011\"\u0002\u0003R!I!1D\u0019\u0002\u0002\u0013\u0015!Q\u000b\u0005\n\u0005\u0003\n\u0011\u0011!C\u0004\u0005;2aA!\u0019\u0002\u0007\t\r\u0004B\u0004B3o\u0011\u0005\tQ!BC\u0002\u0013%!q\r\u0005\f\u0005_:$Q!A!\u0002\u0013\u0011I\u0007C\u0004\u0002\b]\"\tA!\u001d\t\u000f\u0005\u0005x\u0007\"\u0015\u0003x!I\u0011\u0011^\u001c\u0002\u0002\u0013\u0005\u00131\u001e\u0005\n\u0003[<\u0014\u0011!C!\u0005w:\u0011Ba \u0002\u0003\u0003E\tA!!\u0007\u0013\t\u0005\u0014!!A\t\u0002\t\r\u0005bBA\u0004\u007f\u0011\u0005!Q\u0011\u0005\b\u0005\u0013yDQ\u0001BD\u0011%\u0011)bPA\u0001\n\u000b\u0011y\tC\u0005\u0003\u001c}\n\t\u0011\"\u0002\u0003\u0014\"I!qP\u0001\u0002\u0002\u0013\u001d!1\u0014\u0004\u0007\u0005?\u000b1A!)\t\u001d\t\rV\t\"A\u0001\u0006\u000b\u0015\r\u0011\"\u0003\u0002V\"Y!QU#\u0003\u0006\u0003\u0005\u000b\u0011BAP\u0011\u001d\t9!\u0012C\u0001\u0005OCqAa,F\t\u0003\u0011\t\fC\u0004\u00030\u0016#\tA!.\t\u0013\u0005%X)!A\u0005B\u0005-\b\"CAw\u000b\u0006\u0005I\u0011\tB]\u000f%\u0011i,AA\u0001\u0012\u0003\u0011yLB\u0005\u0003 \u0006\t\t\u0011#\u0001\u0003B\"9\u0011q\u0001(\u0005\u0002\t\r\u0007b\u0002Bc\u001d\u0012\u0015!q\u0019\u0005\b\u0005\u000btEQ\u0001Bh\u0011%\u0011)BTA\u0001\n\u000b\u00119\u000eC\u0005\u0003\u001c9\u000b\t\u0011\"\u0002\u0003\\\"I!QX\u0001\u0002\u0002\u0013\u001d!1\u001d\u0004\u0007\u0005O\f1A!;\t\u001d\t-X\u000b\"A\u0001\u0006\u000b\u0015\r\u0011\"\u0003\u00030!Y!Q^+\u0003\u0006\u0003\u0005\u000b\u0011BA[\u0011\u001d\t9!\u0016C\u0001\u0005_DqAa,V\t\u0003\u0011)\u0010C\u0004\u00030V#\tA!?\t\u0013\u0005%X+!A\u0005B\u0005-\b\"CAw+\u0006\u0005I\u0011\tB\u007f\u000f%\u0019\t!AA\u0001\u0012\u0003\u0019\u0019AB\u0005\u0003h\u0006\t\t\u0011#\u0001\u0004\u0006!9\u0011q\u00010\u0005\u0002\r\u001d\u0001b\u0002Bc=\u0012\u00151\u0011\u0002\u0005\b\u0005\u000btFQAB\t\u0011%\u0011)BXA\u0001\n\u000b\u0019I\u0002C\u0005\u0003\u001cy\u000b\t\u0011\"\u0002\u0004\u001e!I1\u0011A\u0001\u0002\u0002\u0013\u001d1Q\u0005\u0004\u0007\u0007S\t1aa\u000b\t\u001d\r5R\r\"A\u0001\u0006\u000b\u0015\r\u0011\"\u0003\u0003h!Y1qF3\u0003\u0006\u0003\u0005\u000b\u0011\u0002B5\u0011\u001d\t9!\u001aC\u0001\u0007cAqAa,f\t\u0003\u0019I\u0004C\u0005\u0002j\u0016\f\t\u0011\"\u0011\u0002l\"I\u0011Q^3\u0002\u0002\u0013\u00053QH\u0004\n\u0007\u0003\n\u0011\u0011!E\u0001\u0007\u00072\u0011b!\u000b\u0002\u0003\u0003E\ta!\u0012\t\u000f\u0005\u001dQ\u000e\"\u0001\u0004H!9!QY7\u0005\u0006\r%\u0003\"\u0003B\u000b[\u0006\u0005IQAB)\u0011%\u0011Y\"\\A\u0001\n\u000b\u0019)\u0006C\u0005\u0004B\u0005\t\t\u0011b\u0002\u0004^\u00059\u0001/Y2lC\u001e,'BA;w\u0003!!WO]1uS>t'BA<y\u0003)\u0019wN\\2veJ,g\u000e\u001e\u0006\u0002s\u0006)1oY1mC\u000e\u0001\u0001C\u0001?\u0002\u001b\u0005!(a\u00029bG.\fw-Z\n\u0003\u0003}\u0004B!!\u0001\u0002\u00045\t\u00010C\u0002\u0002\u0006a\u0014a!\u00118z%\u00164\u0017A\u0002\u001fj]&$h\bF\u0001|\u0003\u0011\u0019\b/\u00198\u0011\u0007\u0005=A!D\u0001\u0002\u0005\u0011\u0019\b/\u00198\u0014\u0005\u0011yHCAA\u0007\u0003\u001d1'o\\7O_^\u00042!a\u0004\b\u0005\u001d1'o\\7O_^\u001c\"aB@\u0015\u0005\u0005e!\u0001\u0003+j[\u0016,f.\u001b;\u0011\t\u0005\u0015\u0012\u0011G\u0007\u0003\u0003OQ1a^A\u0015\u0015\u0011\tY#!\f\u0002\tU$\u0018\u000e\u001c\u0006\u0003\u0003_\tAA[1wC&!\u0011\u0011EA\u0014\u0003\u0011!\u0015)W*\u0016\u0005\u0005]rBAA\u001dI\t\tY$\u0003\u0003\u00024\u0005u\"\u0002BA \u0003O\t\u0001\u0002V5nKVs\u0017\u000e^\u0001\u0006\t\u0006K6\u000bI\u0001\u0006\u0011>+&kU\u000b\u0003\u0003\u000fz!!!\u0013%\u0005\u0005-\u0013\u0002BA\"\u0003{\ta\u0001S(V%N\u0003\u0013\u0001D'J\u0007J{5+R\"P\u001d\u0012\u001bVCAA*\u001f\t\t)\u0006\n\u0002\u0002X%!\u0011qJA\u001f\u00035i\u0015j\u0011*P'\u0016\u001buJ\u0014#TA\u0005aQ*\u0013'M\u0013N+5i\u0014(E'V\u0011\u0011qL\b\u0003\u0003C\"#!a\u0019\n\t\u0005m\u0013QH\u0001\u000e\u001b&cE*S*F\u0007>sEi\u0015\u0011\u0002\u000f5Ke*\u0016+F'V\u0011\u00111N\b\u0003\u0003[\"#!a\u001c\n\t\u0005\u001d\u0014QH\u0001\t\u001b&sU\u000bV#TA\u0005Ya*\u0011(P'\u0016\u001buJ\u0014#T+\t\t9h\u0004\u0002\u0002z\u0011\u0012\u00111P\u0005\u0005\u0003g\ni$\u0001\u0007O\u0003:{5+R\"P\u001d\u0012\u001b\u0006%A\u0004T\u000b\u000e{e\nR*\u0016\u0005\u0005\ruBAACI\t\t9)\u0003\u0003\u0002\u0000\u0005u\u0012\u0001C*F\u0007>sEi\u0015\u0011\u0002#A\f\u0017N]%oiR{G)\u001e:bi&|g\u000e\u0006\u0003\u0002\u0010\u0006U\u0005c\u0001?\u0002\u0012&\u0019\u00111\u0013;\u0003\u0011\u0011+(/\u0019;j_:Dq!a&\u0019\u0001\u0004\tI*A\u0001q!!\t\t!a'\u0002 \u0006\u0015\u0016bAAOq\n1A+\u001e9mKJ\u0002B!!\u0001\u0002\"&\u0019\u00111\u0015=\u0003\u0007%sG\u000fE\u0002\u0002\u0010%\t!\u0003]1je2{gn\u001a+p\tV\u0014\u0018\r^5p]R!\u00111VAY!\ra\u0018QV\u0005\u0004\u0003_#(A\u0004$j]&$X\rR;sCRLwN\u001c\u0005\b\u0003/K\u0002\u0019AAZ!!\t\t!a'\u00026\u0006\u0015\u0006\u0003BA\u0001\u0003oK1!!/y\u0005\u0011auN\\4\u0002\u001d\u0011,(/\u0019;j_:$v\u000eU1jeR!\u00111WA`\u0011\u001d\t\tM\u0007a\u0001\u0003\u001f\u000b\u0011\u0001\u001a\u0002\f\tV\u0014\u0018\r^5p]&sGoE\u0003\u001c\u0003\u000f\fi\r\u0005\u0003\u0002\u0002\u0005%\u0017bAAfq\n1\u0011I\\=WC2\u00042\u0001`Ah\u0013\r\t\t\u000e\u001e\u0002\u0014\tV\u0014\u0018\r^5p]\u000e{gN^3sg&|gn]\u0001)g\u000e\fG.\u0019\u0013d_:\u001cWO\u001d:f]R$C-\u001e:bi&|g\u000e\n#ve\u0006$\u0018n\u001c8J]R$CE\\\u000b\u0003\u0003?\u000b\u0011f]2bY\u0006$3m\u001c8dkJ\u0014XM\u001c;%IV\u0014\u0018\r^5p]\u0012\"UO]1uS>t\u0017J\u001c;%I9\u0004C\u0003BAn\u0003;\u00042!a\u0004\u001c\u0011\u001d\tyN\ba\u0001\u0003?\u000b\u0011A\\\u0001\u000bIV\u0014\u0018\r^5p]&sG\u0003BAV\u0003KDq!a: \u0001\u0004\t)+\u0001\u0003v]&$\u0018\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u0005}\u0015AB3rk\u0006d7\u000f\u0006\u0003\u0002r\u0006]\b\u0003BA\u0001\u0003gL1!!>y\u0005\u001d\u0011un\u001c7fC:D\u0011\"!?\"\u0003\u0003\u0005\r!a?\u0002\u0007a$\u0013\u0007\u0005\u0003\u0002\u0002\u0005u\u0018bAA\u0000q\n\u0019\u0011I\\=\u0002\u0017\u0011+(/\u0019;j_:Le\u000e\u001e\t\u0004\u0003\u001f\u00193CA\u0012\u0000)\t\u0011\u0019!\u0001\u000bekJ\fG/[8o\u0013:$S\r\u001f;f]NLwN\u001c\u000b\u0005\u0005\u001b\u0011\t\u0002\u0006\u0003\u0002,\n=\u0001bBAtK\u0001\u0007\u0011Q\u0015\u0005\b\u0005')\u0003\u0019AAn\u0003\u0015!C\u000f[5t\u0003IA\u0017m\u001d5D_\u0012,G%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\u0005-(\u0011\u0004\u0005\b\u0005'1\u0003\u0019AAn\u0003A)\u0017/^1mg\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0003 \t\rB\u0003BAy\u0005CA\u0011\"!?(\u0003\u0003\u0005\r!a?\t\u000f\tMq\u00051\u0001\u0002\\R!\u00111\u001cB\u0014\u0011\u001d\ty\u000e\u000ba\u0001\u0003?\u0013A\u0002R;sCRLwN\u001c'p]\u001e\u001cR!KAd\u0003\u001b\f\u0011f]2bY\u0006$3m\u001c8dkJ\u0014XM\u001c;%IV\u0014\u0018\r^5p]\u0012\"UO]1uS>tGj\u001c8hI\u0011rWCAA[\u0003)\u001a8-\u00197bI\r|gnY;se\u0016tG\u000f\n3ve\u0006$\u0018n\u001c8%\tV\u0014\u0018\r^5p]2{gn\u001a\u0013%]\u0002\"BA!\u000e\u00038A\u0019\u0011qB\u0015\t\u000f\u0005}G\u00061\u0001\u00026R!\u00111\u0016B\u001e\u0011\u001d\t9/\fa\u0001\u0003K#B!!=\u0003@!I\u0011\u0011`\u0018\u0002\u0002\u0003\u0007\u00111`\u0001\r\tV\u0014\u0018\r^5p]2{gn\u001a\t\u0004\u0003\u001f\t4CA\u0019\u0000)\t\u0011\u0019\u0005\u0006\u0003\u0003L\t=C\u0003BAV\u0005\u001bBq!a:4\u0001\u0004\t)\u000bC\u0004\u0003\u0014M\u0002\rA!\u000e\u0015\t\u0005-(1\u000b\u0005\b\u0005'!\u0004\u0019\u0001B\u001b)\u0011\u00119Fa\u0017\u0015\t\u0005E(\u0011\f\u0005\n\u0003s,\u0014\u0011!a\u0001\u0003wDqAa\u00056\u0001\u0004\u0011)\u0004\u0006\u0003\u00036\t}\u0003bBApm\u0001\u0007\u0011Q\u0017\u0002\u000f\tV\u0014\u0018\r^5p]\u0012{WO\u00197f'\u00159\u0014qYAg\u0003-\u001a8-\u00197bI\r|gnY;se\u0016tG\u000f\n3ve\u0006$\u0018n\u001c8%\tV\u0014\u0018\r^5p]\u0012{WO\u00197fI\u0011\"WC\u0001B5!\u0011\t\tAa\u001b\n\u0007\t5\u0004P\u0001\u0004E_V\u0014G.Z\u0001-g\u000e\fG.\u0019\u0013d_:\u001cWO\u001d:f]R$C-\u001e:bi&|g\u000e\n#ve\u0006$\u0018n\u001c8E_V\u0014G.\u001a\u0013%I\u0002\"BAa\u001d\u0003vA\u0019\u0011qB\u001c\t\u000f\u0005\u0005'\b1\u0001\u0003jQ!\u00111\u0016B=\u0011\u001d\t9o\u000fa\u0001\u0003K#B!!=\u0003~!I\u0011\u0011`\u001f\u0002\u0002\u0003\u0007\u00111`\u0001\u000f\tV\u0014\u0018\r^5p]\u0012{WO\u00197f!\r\tyaP\n\u0003\u007f}$\"A!!\u0015\t\t%%Q\u0012\u000b\u0005\u0003W\u0013Y\tC\u0004\u0002h\u0006\u0003\r!!*\t\u000f\tM\u0011\t1\u0001\u0003tQ!\u00111\u001eBI\u0011\u001d\u0011\u0019B\u0011a\u0001\u0005g\"BA!&\u0003\u001aR!\u0011\u0011\u001fBL\u0011%\tIpQA\u0001\u0002\u0004\tY\u0010C\u0004\u0003\u0014\r\u0003\rAa\u001d\u0015\t\tM$Q\u0014\u0005\b\u0003\u0003$\u0005\u0019\u0001B5\u0005\u001dIe\u000e^'vYR\u001c2!RAd\u0003\u0011\u001a8-\u00197bI\r|gnY;se\u0016tG\u000f\n3ve\u0006$\u0018n\u001c8%\u0013:$X*\u001e7uI\u0011J\u0017!J:dC2\fGeY8oGV\u0014(/\u001a8uI\u0011,(/\u0019;j_:$\u0013J\u001c;Nk2$H\u0005J5!)\u0011\u0011IKa+\u0011\u0007\u0005=Q\tC\u0004\u0003.\"\u0003\r!a(\u0002\u0003%\fa\u0001\n;j[\u0016\u001cH\u0003BAH\u0005gCq!!1J\u0001\u0004\ty\t\u0006\u0003\u0002,\n]\u0006bBAa\u0015\u0002\u0007\u00111\u0016\u000b\u0005\u0003c\u0014Y\fC\u0005\u0002z2\u000b\t\u00111\u0001\u0002|\u00069\u0011J\u001c;Nk2$\bcAA\b\u001dN\u0011aj \u000b\u0003\u0005\u007f\u000b\u0001\u0003\n;j[\u0016\u001cH%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\t%'Q\u001a\u000b\u0005\u0003\u001f\u0013Y\rC\u0004\u0002BB\u0003\r!a$\t\u000f\tM\u0001\u000b1\u0001\u0003*R!!\u0011\u001bBk)\u0011\tYKa5\t\u000f\u0005\u0005\u0017\u000b1\u0001\u0002,\"9!1C)A\u0002\t%F\u0003BAv\u00053DqAa\u0005S\u0001\u0004\u0011I\u000b\u0006\u0003\u0003^\n\u0005H\u0003BAy\u0005?D\u0011\"!?T\u0003\u0003\u0005\r!a?\t\u000f\tM1\u000b1\u0001\u0003*R!!\u0011\u0016Bs\u0011\u001d\u0011i\u000b\u0016a\u0001\u0003?\u0013\u0001\u0002T8oO6+H\u000e^\n\u0004+\u0006\u001d\u0017!J:dC2\fGeY8oGV\u0014(/\u001a8uI\u0011,(/\u0019;j_:$Cj\u001c8h\u001bVdG\u000f\n\u0013j\u0003\u0019\u001a8-\u00197bI\r|gnY;se\u0016tG\u000f\n3ve\u0006$\u0018n\u001c8%\u0019>tw-T;mi\u0012\"\u0013\u000e\t\u000b\u0005\u0005c\u0014\u0019\u0010E\u0002\u0002\u0010UCqA!,Y\u0001\u0004\t)\f\u0006\u0003\u0002\u0010\n]\bbBAa3\u0002\u0007\u0011q\u0012\u000b\u0005\u0003W\u0013Y\u0010C\u0004\u0002Bj\u0003\r!a+\u0015\t\u0005E(q \u0005\n\u0003sd\u0016\u0011!a\u0001\u0003w\f\u0001\u0002T8oO6+H\u000e\u001e\t\u0004\u0003\u001fq6C\u00010\u0000)\t\u0019\u0019\u0001\u0006\u0003\u0004\f\r=A\u0003BAH\u0007\u001bAq!!1a\u0001\u0004\ty\tC\u0004\u0003\u0014\u0001\u0004\rA!=\u0015\t\rM1q\u0003\u000b\u0005\u0003W\u001b)\u0002C\u0004\u0002B\u0006\u0004\r!a+\t\u000f\tM\u0011\r1\u0001\u0003rR!\u00111^B\u000e\u0011\u001d\u0011\u0019B\u0019a\u0001\u0005c$Baa\b\u0004$Q!\u0011\u0011_B\u0011\u0011%\tIpYA\u0001\u0002\u0004\tY\u0010C\u0004\u0003\u0014\r\u0004\rA!=\u0015\t\tE8q\u0005\u0005\b\u0005[#\u0007\u0019AA[\u0005)!u.\u001e2mK6+H\u000e^\n\u0004K\u0006\u001d\u0017aJ:dC2\fGeY8oGV\u0014(/\u001a8uI\u0011,(/\u0019;j_:$Ci\\;cY\u0016lU\u000f\u001c;%I\u0019\f\u0001f]2bY\u0006$3m\u001c8dkJ\u0014XM\u001c;%IV\u0014\u0018\r^5p]\u0012\"u.\u001e2mK6+H\u000e\u001e\u0013%M\u0002\"Baa\r\u00046A\u0019\u0011qB3\t\u000f\r]\u0002\u000e1\u0001\u0003j\u0005\ta\r\u0006\u0003\u0002\u0010\u000em\u0002bBAaS\u0002\u0007\u0011q\u0012\u000b\u0005\u0003c\u001cy\u0004C\u0005\u0002z.\f\t\u00111\u0001\u0002|\u0006QAi\\;cY\u0016lU\u000f\u001c;\u0011\u0007\u0005=Qn\u0005\u0002n\u007fR\u001111\t\u000b\u0005\u0007\u0017\u001ay\u0005\u0006\u0003\u0002\u0010\u000e5\u0003bBAa_\u0002\u0007\u0011q\u0012\u0005\b\u0005'y\u0007\u0019AB\u001a)\u0011\tYoa\u0015\t\u000f\tM\u0001\u000f1\u0001\u00044Q!1qKB.)\u0011\t\tp!\u0017\t\u0013\u0005e\u0018/!AA\u0002\u0005m\bb\u0002B\nc\u0002\u000711\u0007\u000b\u0005\u0007g\u0019y\u0006C\u0004\u00048I\u0004\rA!\u001b"
)
public final class package {
   public static double DoubleMult(final double f) {
      return package$.MODULE$.DoubleMult(f);
   }

   public static long LongMult(final long i) {
      return package$.MODULE$.LongMult(i);
   }

   public static int IntMult(final int i) {
      return package$.MODULE$.IntMult(i);
   }

   public static double DurationDouble(final double d) {
      return package$.MODULE$.DurationDouble(d);
   }

   public static long DurationLong(final long n) {
      return package$.MODULE$.DurationLong(n);
   }

   public static int DurationInt(final int n) {
      return package$.MODULE$.DurationInt(n);
   }

   public static Tuple2 durationToPair(final Duration d) {
      return package$.MODULE$.durationToPair(d);
   }

   public static FiniteDuration pairLongToDuration(final Tuple2 p) {
      return package$.MODULE$.pairLongToDuration(p);
   }

   public static Duration pairIntToDuration(final Tuple2 p) {
      return package$.MODULE$.pairIntToDuration(p);
   }

   public static TimeUnit SECONDS() {
      return package$.MODULE$.SECONDS();
   }

   public static TimeUnit NANOSECONDS() {
      return package$.MODULE$.NANOSECONDS();
   }

   public static TimeUnit MINUTES() {
      return package$.MODULE$.MINUTES();
   }

   public static TimeUnit MILLISECONDS() {
      return package$.MODULE$.MILLISECONDS();
   }

   public static TimeUnit MICROSECONDS() {
      return package$.MODULE$.MICROSECONDS();
   }

   public static TimeUnit HOURS() {
      return package$.MODULE$.HOURS();
   }

   public static TimeUnit DAYS() {
      return package$.MODULE$.DAYS();
   }

   public static class span$ {
      public static final span$ MODULE$ = new span$();
   }

   public static class fromNow$ {
      public static final fromNow$ MODULE$ = new fromNow$();
   }

   public static final class DurationInt implements DurationConversions {
      private final int scala$concurrent$duration$DurationInt$$n;

      public FiniteDuration nanoseconds() {
         return DurationConversions.nanoseconds$(this);
      }

      public FiniteDuration nanos() {
         return DurationConversions.nanos$(this);
      }

      public FiniteDuration nanosecond() {
         return DurationConversions.nanosecond$(this);
      }

      public FiniteDuration nano() {
         return DurationConversions.nano$(this);
      }

      public FiniteDuration microseconds() {
         return DurationConversions.microseconds$(this);
      }

      public FiniteDuration micros() {
         return DurationConversions.micros$(this);
      }

      public FiniteDuration microsecond() {
         return DurationConversions.microsecond$(this);
      }

      public FiniteDuration micro() {
         return DurationConversions.micro$(this);
      }

      public FiniteDuration milliseconds() {
         return DurationConversions.milliseconds$(this);
      }

      public FiniteDuration millis() {
         return DurationConversions.millis$(this);
      }

      public FiniteDuration millisecond() {
         return DurationConversions.millisecond$(this);
      }

      public FiniteDuration milli() {
         return DurationConversions.milli$(this);
      }

      public FiniteDuration seconds() {
         return DurationConversions.seconds$(this);
      }

      public FiniteDuration second() {
         return DurationConversions.second$(this);
      }

      public FiniteDuration minutes() {
         return DurationConversions.minutes$(this);
      }

      public FiniteDuration minute() {
         return DurationConversions.minute$(this);
      }

      public FiniteDuration hours() {
         return DurationConversions.hours$(this);
      }

      public FiniteDuration hour() {
         return DurationConversions.hour$(this);
      }

      public FiniteDuration days() {
         return DurationConversions.days$(this);
      }

      public FiniteDuration day() {
         return DurationConversions.day$(this);
      }

      public Object nanoseconds(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.nanoseconds$(this, c, ev);
      }

      public Object nanos(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.nanos$(this, c, ev);
      }

      public Object nanosecond(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.nanosecond$(this, c, ev);
      }

      public Object nano(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.nano$(this, c, ev);
      }

      public Object microseconds(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.microseconds$(this, c, ev);
      }

      public Object micros(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.micros$(this, c, ev);
      }

      public Object microsecond(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.microsecond$(this, c, ev);
      }

      public Object micro(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.micro$(this, c, ev);
      }

      public Object milliseconds(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.milliseconds$(this, c, ev);
      }

      public Object millis(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.millis$(this, c, ev);
      }

      public Object millisecond(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.millisecond$(this, c, ev);
      }

      public Object milli(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.milli$(this, c, ev);
      }

      public Object seconds(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.seconds$(this, c, ev);
      }

      public Object second(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.second$(this, c, ev);
      }

      public Object minutes(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.minutes$(this, c, ev);
      }

      public Object minute(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.minute$(this, c, ev);
      }

      public Object hours(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.hours$(this, c, ev);
      }

      public Object hour(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.hour$(this, c, ev);
      }

      public Object days(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.days$(this, c, ev);
      }

      public Object day(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.day$(this, c, ev);
      }

      public int scala$concurrent$duration$DurationInt$$n() {
         return this.scala$concurrent$duration$DurationInt$$n;
      }

      public FiniteDuration durationIn(final TimeUnit unit) {
         DurationInt$ var10000 = package.DurationInt$.MODULE$;
         int durationIn$extension_$this = this.scala$concurrent$duration$DurationInt$$n();
         Duration$ var5 = Duration$.MODULE$;
         long durationIn$extension_apply_length = (long)durationIn$extension_$this;
         return new FiniteDuration(durationIn$extension_apply_length, unit);
      }

      public int hashCode() {
         DurationInt$ var10000 = package.DurationInt$.MODULE$;
         return Integer.hashCode(this.scala$concurrent$duration$DurationInt$$n());
      }

      public boolean equals(final Object x$1) {
         return package.DurationInt$.MODULE$.equals$extension(this.scala$concurrent$duration$DurationInt$$n(), x$1);
      }

      public DurationInt(final int n) {
         this.scala$concurrent$duration$DurationInt$$n = n;
      }
   }

   public static class DurationInt$ {
      public static final DurationInt$ MODULE$ = new DurationInt$();

      public final FiniteDuration durationIn$extension(final int $this, final TimeUnit unit) {
         Duration$ var10000 = Duration$.MODULE$;
         long apply_length = (long)$this;
         return new FiniteDuration(apply_length, unit);
      }

      public final int hashCode$extension(final int $this) {
         return Integer.hashCode($this);
      }

      public final boolean equals$extension(final int $this, final Object x$1) {
         if (x$1 instanceof DurationInt) {
            int var3 = ((DurationInt)x$1).scala$concurrent$duration$DurationInt$$n();
            if ($this == var3) {
               return true;
            }
         }

         return false;
      }
   }

   public static final class DurationLong implements DurationConversions {
      private final long scala$concurrent$duration$DurationLong$$n;

      public FiniteDuration nanoseconds() {
         return DurationConversions.nanoseconds$(this);
      }

      public FiniteDuration nanos() {
         return DurationConversions.nanos$(this);
      }

      public FiniteDuration nanosecond() {
         return DurationConversions.nanosecond$(this);
      }

      public FiniteDuration nano() {
         return DurationConversions.nano$(this);
      }

      public FiniteDuration microseconds() {
         return DurationConversions.microseconds$(this);
      }

      public FiniteDuration micros() {
         return DurationConversions.micros$(this);
      }

      public FiniteDuration microsecond() {
         return DurationConversions.microsecond$(this);
      }

      public FiniteDuration micro() {
         return DurationConversions.micro$(this);
      }

      public FiniteDuration milliseconds() {
         return DurationConversions.milliseconds$(this);
      }

      public FiniteDuration millis() {
         return DurationConversions.millis$(this);
      }

      public FiniteDuration millisecond() {
         return DurationConversions.millisecond$(this);
      }

      public FiniteDuration milli() {
         return DurationConversions.milli$(this);
      }

      public FiniteDuration seconds() {
         return DurationConversions.seconds$(this);
      }

      public FiniteDuration second() {
         return DurationConversions.second$(this);
      }

      public FiniteDuration minutes() {
         return DurationConversions.minutes$(this);
      }

      public FiniteDuration minute() {
         return DurationConversions.minute$(this);
      }

      public FiniteDuration hours() {
         return DurationConversions.hours$(this);
      }

      public FiniteDuration hour() {
         return DurationConversions.hour$(this);
      }

      public FiniteDuration days() {
         return DurationConversions.days$(this);
      }

      public FiniteDuration day() {
         return DurationConversions.day$(this);
      }

      public Object nanoseconds(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.nanoseconds$(this, c, ev);
      }

      public Object nanos(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.nanos$(this, c, ev);
      }

      public Object nanosecond(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.nanosecond$(this, c, ev);
      }

      public Object nano(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.nano$(this, c, ev);
      }

      public Object microseconds(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.microseconds$(this, c, ev);
      }

      public Object micros(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.micros$(this, c, ev);
      }

      public Object microsecond(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.microsecond$(this, c, ev);
      }

      public Object micro(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.micro$(this, c, ev);
      }

      public Object milliseconds(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.milliseconds$(this, c, ev);
      }

      public Object millis(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.millis$(this, c, ev);
      }

      public Object millisecond(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.millisecond$(this, c, ev);
      }

      public Object milli(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.milli$(this, c, ev);
      }

      public Object seconds(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.seconds$(this, c, ev);
      }

      public Object second(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.second$(this, c, ev);
      }

      public Object minutes(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.minutes$(this, c, ev);
      }

      public Object minute(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.minute$(this, c, ev);
      }

      public Object hours(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.hours$(this, c, ev);
      }

      public Object hour(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.hour$(this, c, ev);
      }

      public Object days(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.days$(this, c, ev);
      }

      public Object day(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.day$(this, c, ev);
      }

      public long scala$concurrent$duration$DurationLong$$n() {
         return this.scala$concurrent$duration$DurationLong$$n;
      }

      public FiniteDuration durationIn(final TimeUnit unit) {
         DurationLong$ var10000 = package.DurationLong$.MODULE$;
         long durationIn$extension_$this = this.scala$concurrent$duration$DurationLong$$n();
         Duration$ var4 = Duration$.MODULE$;
         return new FiniteDuration(durationIn$extension_$this, unit);
      }

      public int hashCode() {
         DurationLong$ var10000 = package.DurationLong$.MODULE$;
         return Long.hashCode(this.scala$concurrent$duration$DurationLong$$n());
      }

      public boolean equals(final Object x$1) {
         return package.DurationLong$.MODULE$.equals$extension(this.scala$concurrent$duration$DurationLong$$n(), x$1);
      }

      public DurationLong(final long n) {
         this.scala$concurrent$duration$DurationLong$$n = n;
      }
   }

   public static class DurationLong$ {
      public static final DurationLong$ MODULE$ = new DurationLong$();

      public final FiniteDuration durationIn$extension(final long $this, final TimeUnit unit) {
         Duration$ var10000 = Duration$.MODULE$;
         return new FiniteDuration($this, unit);
      }

      public final int hashCode$extension(final long $this) {
         return Long.hashCode($this);
      }

      public final boolean equals$extension(final long $this, final Object x$1) {
         if (x$1 instanceof DurationLong) {
            long var4 = ((DurationLong)x$1).scala$concurrent$duration$DurationLong$$n();
            if ($this == var4) {
               return true;
            }
         }

         return false;
      }
   }

   public static final class DurationDouble implements DurationConversions {
      private final double scala$concurrent$duration$DurationDouble$$d;

      public FiniteDuration nanoseconds() {
         return DurationConversions.nanoseconds$(this);
      }

      public FiniteDuration nanos() {
         return DurationConversions.nanos$(this);
      }

      public FiniteDuration nanosecond() {
         return DurationConversions.nanosecond$(this);
      }

      public FiniteDuration nano() {
         return DurationConversions.nano$(this);
      }

      public FiniteDuration microseconds() {
         return DurationConversions.microseconds$(this);
      }

      public FiniteDuration micros() {
         return DurationConversions.micros$(this);
      }

      public FiniteDuration microsecond() {
         return DurationConversions.microsecond$(this);
      }

      public FiniteDuration micro() {
         return DurationConversions.micro$(this);
      }

      public FiniteDuration milliseconds() {
         return DurationConversions.milliseconds$(this);
      }

      public FiniteDuration millis() {
         return DurationConversions.millis$(this);
      }

      public FiniteDuration millisecond() {
         return DurationConversions.millisecond$(this);
      }

      public FiniteDuration milli() {
         return DurationConversions.milli$(this);
      }

      public FiniteDuration seconds() {
         return DurationConversions.seconds$(this);
      }

      public FiniteDuration second() {
         return DurationConversions.second$(this);
      }

      public FiniteDuration minutes() {
         return DurationConversions.minutes$(this);
      }

      public FiniteDuration minute() {
         return DurationConversions.minute$(this);
      }

      public FiniteDuration hours() {
         return DurationConversions.hours$(this);
      }

      public FiniteDuration hour() {
         return DurationConversions.hour$(this);
      }

      public FiniteDuration days() {
         return DurationConversions.days$(this);
      }

      public FiniteDuration day() {
         return DurationConversions.day$(this);
      }

      public Object nanoseconds(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.nanoseconds$(this, c, ev);
      }

      public Object nanos(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.nanos$(this, c, ev);
      }

      public Object nanosecond(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.nanosecond$(this, c, ev);
      }

      public Object nano(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.nano$(this, c, ev);
      }

      public Object microseconds(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.microseconds$(this, c, ev);
      }

      public Object micros(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.micros$(this, c, ev);
      }

      public Object microsecond(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.microsecond$(this, c, ev);
      }

      public Object micro(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.micro$(this, c, ev);
      }

      public Object milliseconds(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.milliseconds$(this, c, ev);
      }

      public Object millis(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.millis$(this, c, ev);
      }

      public Object millisecond(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.millisecond$(this, c, ev);
      }

      public Object milli(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.milli$(this, c, ev);
      }

      public Object seconds(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.seconds$(this, c, ev);
      }

      public Object second(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.second$(this, c, ev);
      }

      public Object minutes(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.minutes$(this, c, ev);
      }

      public Object minute(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.minute$(this, c, ev);
      }

      public Object hours(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.hours$(this, c, ev);
      }

      public Object hour(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.hour$(this, c, ev);
      }

      public Object days(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.days$(this, c, ev);
      }

      public Object day(final Object c, final DurationConversions.Classifier ev) {
         return DurationConversions.day$(this, c, ev);
      }

      public double scala$concurrent$duration$DurationDouble$$d() {
         return this.scala$concurrent$duration$DurationDouble$$d;
      }

      public FiniteDuration durationIn(final TimeUnit unit) {
         return package.DurationDouble$.MODULE$.durationIn$extension(this.scala$concurrent$duration$DurationDouble$$d(), unit);
      }

      public int hashCode() {
         DurationDouble$ var10000 = package.DurationDouble$.MODULE$;
         return Double.hashCode(this.scala$concurrent$duration$DurationDouble$$d());
      }

      public boolean equals(final Object x$1) {
         return package.DurationDouble$.MODULE$.equals$extension(this.scala$concurrent$duration$DurationDouble$$d(), x$1);
      }

      public DurationDouble(final double d) {
         this.scala$concurrent$duration$DurationDouble$$d = d;
      }
   }

   public static class DurationDouble$ {
      public static final DurationDouble$ MODULE$ = new DurationDouble$();

      public final FiniteDuration durationIn$extension(final double $this, final TimeUnit unit) {
         Duration var4 = Duration$.MODULE$.apply($this, unit);
         if (var4 instanceof FiniteDuration) {
            return (FiniteDuration)var4;
         } else {
            throw new IllegalArgumentException((new StringBuilder(31)).append("Duration DSL not applicable to ").append($this).toString());
         }
      }

      public final int hashCode$extension(final double $this) {
         return Double.hashCode($this);
      }

      public final boolean equals$extension(final double $this, final Object x$1) {
         if (x$1 instanceof DurationDouble) {
            double var4 = ((DurationDouble)x$1).scala$concurrent$duration$DurationDouble$$d();
            if ($this == var4) {
               return true;
            }
         }

         return false;
      }
   }

   public static final class IntMult {
      private final int scala$concurrent$duration$IntMult$$i;

      public int scala$concurrent$duration$IntMult$$i() {
         return this.scala$concurrent$duration$IntMult$$i;
      }

      public Duration $times(final Duration d) {
         IntMult$ var10000 = package.IntMult$.MODULE$;
         int $times$extension_$this = this.scala$concurrent$duration$IntMult$$i();
         return d.$times((double)$times$extension_$this);
      }

      public FiniteDuration $times(final FiniteDuration d) {
         IntMult$ var10000 = package.IntMult$.MODULE$;
         int $times$extension_$this = this.scala$concurrent$duration$IntMult$$i();
         return d.$times((long)$times$extension_$this);
      }

      public int hashCode() {
         IntMult$ var10000 = package.IntMult$.MODULE$;
         return Integer.hashCode(this.scala$concurrent$duration$IntMult$$i());
      }

      public boolean equals(final Object x$1) {
         return package.IntMult$.MODULE$.equals$extension(this.scala$concurrent$duration$IntMult$$i(), x$1);
      }

      public IntMult(final int i) {
         this.scala$concurrent$duration$IntMult$$i = i;
      }
   }

   public static class IntMult$ {
      public static final IntMult$ MODULE$ = new IntMult$();

      public final Duration $times$extension(final int $this, final Duration d) {
         return d.$times((double)$this);
      }

      public final FiniteDuration $times$extension(final int $this, final FiniteDuration d) {
         return d.$times((long)$this);
      }

      public final int hashCode$extension(final int $this) {
         return Integer.hashCode($this);
      }

      public final boolean equals$extension(final int $this, final Object x$1) {
         if (x$1 instanceof IntMult) {
            int var3 = ((IntMult)x$1).scala$concurrent$duration$IntMult$$i();
            if ($this == var3) {
               return true;
            }
         }

         return false;
      }
   }

   public static final class LongMult {
      private final long scala$concurrent$duration$LongMult$$i;

      public long scala$concurrent$duration$LongMult$$i() {
         return this.scala$concurrent$duration$LongMult$$i;
      }

      public Duration $times(final Duration d) {
         LongMult$ var10000 = package.LongMult$.MODULE$;
         long $times$extension_$this = this.scala$concurrent$duration$LongMult$$i();
         return d.$times((double)$times$extension_$this);
      }

      public FiniteDuration $times(final FiniteDuration d) {
         LongMult$ var10000 = package.LongMult$.MODULE$;
         long $times$extension_$this = this.scala$concurrent$duration$LongMult$$i();
         return d.$times($times$extension_$this);
      }

      public int hashCode() {
         LongMult$ var10000 = package.LongMult$.MODULE$;
         return Long.hashCode(this.scala$concurrent$duration$LongMult$$i());
      }

      public boolean equals(final Object x$1) {
         return package.LongMult$.MODULE$.equals$extension(this.scala$concurrent$duration$LongMult$$i(), x$1);
      }

      public LongMult(final long i) {
         this.scala$concurrent$duration$LongMult$$i = i;
      }
   }

   public static class LongMult$ {
      public static final LongMult$ MODULE$ = new LongMult$();

      public final Duration $times$extension(final long $this, final Duration d) {
         return d.$times((double)$this);
      }

      public final FiniteDuration $times$extension(final long $this, final FiniteDuration d) {
         return d.$times($this);
      }

      public final int hashCode$extension(final long $this) {
         return Long.hashCode($this);
      }

      public final boolean equals$extension(final long $this, final Object x$1) {
         if (x$1 instanceof LongMult) {
            long var4 = ((LongMult)x$1).scala$concurrent$duration$LongMult$$i();
            if ($this == var4) {
               return true;
            }
         }

         return false;
      }
   }

   public static final class DoubleMult {
      private final double scala$concurrent$duration$DoubleMult$$f;

      public double scala$concurrent$duration$DoubleMult$$f() {
         return this.scala$concurrent$duration$DoubleMult$$f;
      }

      public Duration $times(final Duration d) {
         DoubleMult$ var10000 = package.DoubleMult$.MODULE$;
         double $times$extension_$this = this.scala$concurrent$duration$DoubleMult$$f();
         return d.$times($times$extension_$this);
      }

      public int hashCode() {
         DoubleMult$ var10000 = package.DoubleMult$.MODULE$;
         return Double.hashCode(this.scala$concurrent$duration$DoubleMult$$f());
      }

      public boolean equals(final Object x$1) {
         return package.DoubleMult$.MODULE$.equals$extension(this.scala$concurrent$duration$DoubleMult$$f(), x$1);
      }

      public DoubleMult(final double f) {
         this.scala$concurrent$duration$DoubleMult$$f = f;
      }
   }

   public static class DoubleMult$ {
      public static final DoubleMult$ MODULE$ = new DoubleMult$();

      public final Duration $times$extension(final double $this, final Duration d) {
         return d.$times($this);
      }

      public final int hashCode$extension(final double $this) {
         return Double.hashCode($this);
      }

      public final boolean equals$extension(final double $this, final Object x$1) {
         if (x$1 instanceof DoubleMult) {
            double var4 = ((DoubleMult)x$1).scala$concurrent$duration$DoubleMult$$f();
            if ($this == var4) {
               return true;
            }
         }

         return false;
      }
   }
}
