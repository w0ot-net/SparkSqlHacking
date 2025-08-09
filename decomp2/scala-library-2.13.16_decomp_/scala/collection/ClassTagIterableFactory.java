package scala.collection;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.collection.immutable.NumericRange;
import scala.collection.immutable.NumericRange$;
import scala.collection.mutable.Builder;
import scala.math.Integral;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\rEgaB\u0011#!\u0003\r\ta\n\u0005\u0006\u000b\u0002!\tA\u0012\u0005\u0007\u0015\u0002\u0001K1B&\t\u000bY\u0003A\u0011A,\t\u000bY\u0003A\u0011\u00018\t\u000by\u0004A\u0011A@\t\ry\u0004A\u0011AA!\u0011\u0019q\b\u0001\"\u0001\u0002h!1a\u0010\u0001C\u0001\u0003#Cq!a0\u0001\t\u0003\t\t\rC\u0004\u0002@\u0002!\t!a:\t\u000f\u0005}\u0006\u0001\"\u0001\u0003\u0010!9\u0011q\u0018\u0001\u0005\u0002\tmra\u0002B6E!\u0005!Q\u000e\u0004\u0007C\tB\tAa\u001c\t\u000f\t\u0005e\u0002\"\u0001\u0003\u0004\u001a1!Q\u0011\b\u0001\u0005\u000fC!Ba)\u0011\u0005\u0003\u0005\u000b\u0011\u0002BS\u0011\u001d\u0011\t\t\u0005C\u0001\u0005O3aAa/\u000f\u0001\tu\u0006B\u0003BR'\t\u0005\t\u0015!\u0003\u0003R\"9!\u0011Q\n\u0005\u0002\tM\u0007b\u0002Bm'\u0011\u0005!1\u001c\u0005\b\u0005K\u001cB\u0011\u0001Bt\u0011\u001d\u0011Yp\u0005C\u0001\u0005{Dqaa\u0005\u0014\t\u0003\u001a)\u0002C\u0004\u0004*M!\tea\u000b\t\u000f\r\u00153\u0003\"\u0011\u0004H!1ak\u0005C!\u0007[BaAV\n\u0005B\r\r\u0005B\u0002@\u0014\t\u0003\u001aI\nC\u0004\u0002@N!\te!,\t\u0013\r\u0005g\"!A\u0005\n\r\r'aF\"mCN\u001cH+Y4Ji\u0016\u0014\u0018M\u00197f\r\u0006\u001cGo\u001c:z\u0015\t\u0019C%\u0001\u0006d_2dWm\u0019;j_:T\u0011!J\u0001\u0006g\u000e\fG.Y\u0002\u0001+\tA3gE\u0002\u0001S5\u0002\"AK\u0016\u000e\u0003\u0011J!\u0001\f\u0013\u0003\r\u0005s\u0017PU3g!\u0011qs&M \u000e\u0003\tJ!\u0001\r\u0012\u0003/\u00153\u0018\u000eZ3oG\u0016LE/\u001a:bE2,g)Y2u_JL\bC\u0001\u001a4\u0019\u0001!a\u0001\u000e\u0001\u0005\u0006\u0004)$AA\"D+\t1T(\u0005\u00028uA\u0011!\u0006O\u0005\u0003s\u0011\u0012qAT8uQ&tw\r\u0005\u0002+w%\u0011A\b\n\u0002\u0004\u0003:LH!\u0002 4\u0005\u00041$!B0%IM\u0002\u0004C\u0001!D\u001b\u0005\t%B\u0001\"%\u0003\u001d\u0011XM\u001a7fGRL!\u0001R!\u0003\u0011\rc\u0017m]:UC\u001e\fa\u0001J5oSR$C#A$\u0011\u0005)B\u0015BA%%\u0005\u0011)f.\u001b;\u0002\u0015\r\u001c7\t\\1tgR\u000bw-\u0006\u0002M!V\tQ\nE\u0002A\u0007:\u00032AM\u001aP!\t\u0011\u0004\u000bB\u0003R\u0005\t\u0007aGA\u0001YQ\t\u00111\u000b\u0005\u0002+)&\u0011Q\u000b\n\u0002\u0007S:d\u0017N\\3\u0002\u000bI\fgnZ3\u0016\u0005acFcA-kYR\u0019!LX4\u0011\u0007I\u001a4\f\u0005\u000239\u0012)Ql\u0001b\u0001m\t\t\u0011\tC\u0004`\u0007\u0005\u0005\t9\u00011\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$#G\r\t\u0004C\u0012\\fB\u0001\u0016c\u0013\t\u0019G%A\u0004qC\u000e\\\u0017mZ3\n\u0005\u00154'\u0001C%oi\u0016<'/\u00197\u000b\u0005\r$\u0003b\u00025\u0004\u0003\u0003\u0005\u001d![\u0001\fKZLG-\u001a8dK\u0012\u00124\u0007E\u0002A\u0007nCQa[\u0002A\u0002m\u000bQa\u001d;beRDQ!\\\u0002A\u0002m\u000b1!\u001a8e+\ty7\u000f\u0006\u0003qundHcA9uoB\u0019!g\r:\u0011\u0005I\u001aH!B/\u0005\u0005\u00041\u0004bB;\u0005\u0003\u0003\u0005\u001dA^\u0001\fKZLG-\u001a8dK\u0012\u0012D\u0007E\u0002bIJDq\u0001\u001f\u0003\u0002\u0002\u0003\u000f\u00110A\u0006fm&$WM\\2fII*\u0004c\u0001!De\")1\u000e\u0002a\u0001e\")Q\u000e\u0002a\u0001e\")Q\u0010\u0002a\u0001e\u0006!1\u000f^3q\u0003\u00111\u0017\u000e\u001c7\u0016\t\u0005\u0005\u0011q\u0002\u000b\u0007\u0003\u0007\t\u0019$!\u0010\u0015\t\u0005\u0015\u0011\u0011\u0006\u000b\u0005\u0003\u000f\t\u0019\u0003\u0005\u00033g\u0005%!\u0006BA\u0006\u0003#\u0001BAM\u001a\u0002\u000eA\u0019!'a\u0004\u0005\u000bu+!\u0019\u0001\u001c,\u0005\u0005M\u0001\u0003BA\u000b\u0003?i!!a\u0006\u000b\t\u0005e\u00111D\u0001\nk:\u001c\u0007.Z2lK\u0012T1!!\b%\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003C\t9BA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016D\u0011\"!\n\u0006\u0003\u0003\u0005\u001d!a\n\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$#G\u000e\t\u0005\u0001\u000e\u000bi\u0001\u0003\u0005\u0002,\u0015!\t\u0019AA\u0017\u0003\u0011)G.Z7\u0011\u000b)\ny#!\u0004\n\u0007\u0005EBE\u0001\u0005=Eft\u0017-\\3?\u0011\u001d\t)$\u0002a\u0001\u0003o\t!A\\\u0019\u0011\u0007)\nI$C\u0002\u0002<\u0011\u00121!\u00138u\u0011\u001d\ty$\u0002a\u0001\u0003o\t!A\u001c\u001a\u0016\t\u0005\r\u00131\u000b\u000b\t\u0003\u000b\ny&!\u0019\u0002dQ!\u0011qIA.)\u0011\tI%!\u0016\u0011\tI\u001a\u00141\n\u0016\u0005\u0003\u001b\n\t\u0002\u0005\u00033g\u0005=\u0003\u0003\u0002\u001a4\u0003#\u00022AMA*\t\u0015ifA1\u00017\u0011%\t9FBA\u0001\u0002\b\tI&A\u0006fm&$WM\\2fII:\u0004\u0003\u0002!D\u0003#B\u0001\"a\u000b\u0007\t\u0003\u0007\u0011Q\f\t\u0006U\u0005=\u0012\u0011\u000b\u0005\b\u0003k1\u0001\u0019AA\u001c\u0011\u001d\tyD\u0002a\u0001\u0003oAq!!\u001a\u0007\u0001\u0004\t9$\u0001\u0002ogU!\u0011\u0011NA>))\tY'a\"\u0002\n\u0006-\u0015Q\u0012\u000b\u0005\u0003[\n\u0019\t\u0006\u0003\u0002p\u0005u\u0004\u0003\u0002\u001a4\u0003cRC!a\u001d\u0002\u0012A!!gMA;!\u0011\u00114'a\u001e\u0011\tI\u001a\u0014\u0011\u0010\t\u0004e\u0005mD!B/\b\u0005\u00041\u0004\"CA@\u000f\u0005\u0005\t9AAA\u0003-)g/\u001b3f]\u000e,GE\r\u001d\u0011\t\u0001\u001b\u0015\u0011\u0010\u0005\t\u0003W9A\u00111\u0001\u0002\u0006B)!&a\f\u0002z!9\u0011QG\u0004A\u0002\u0005]\u0002bBA \u000f\u0001\u0007\u0011q\u0007\u0005\b\u0003K:\u0001\u0019AA\u001c\u0011\u001d\tyi\u0002a\u0001\u0003o\t!A\u001c\u001b\u0016\t\u0005M\u0015q\u0015\u000b\r\u0003+\u000b\u0019,!.\u00028\u0006e\u00161\u0018\u000b\u0005\u0003/\u000by\u000b\u0006\u0003\u0002\u001a\u0006%\u0006\u0003\u0002\u001a4\u00037SC!!(\u0002\u0012A!!gMAP!\u0011\u00114'!)\u0011\tI\u001a\u00141\u0015\t\u0005eM\n)\u000bE\u00023\u0003O#Q!\u0018\u0005C\u0002YB\u0011\"a+\t\u0003\u0003\u0005\u001d!!,\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$#'\u000f\t\u0005\u0001\u000e\u000b)\u000b\u0003\u0005\u0002,!!\t\u0019AAY!\u0015Q\u0013qFAS\u0011\u001d\t)\u0004\u0003a\u0001\u0003oAq!a\u0010\t\u0001\u0004\t9\u0004C\u0004\u0002f!\u0001\r!a\u000e\t\u000f\u0005=\u0005\u00021\u0001\u00028!9\u0011Q\u0018\u0005A\u0002\u0005]\u0012A\u000186\u0003!!\u0018MY;mCR,W\u0003BAb\u0003#$b!!2\u0002d\u0006\u0015H\u0003BAd\u00033$B!!3\u0002TB!!gMAfU\u0011\ti-!\u0005\u0011\tI\u001a\u0014q\u001a\t\u0004e\u0005EG!B/\n\u0005\u00041\u0004\"CAk\u0013\u0005\u0005\t9AAl\u0003-)g/\u001b3f]\u000e,Ge\r\u0019\u0011\t\u0001\u001b\u0015q\u001a\u0005\b\u00037L\u0001\u0019AAo\u0003\u00051\u0007#\u0003\u0016\u0002`\u0006]\u0012qGAh\u0013\r\t\t\u000f\n\u0002\n\rVt7\r^5p]JBq!!\u000e\n\u0001\u0004\t9\u0004C\u0004\u0002@%\u0001\r!a\u000e\u0016\t\u0005%\u0018\u0011 \u000b\t\u0003W\u0014IAa\u0003\u0003\u000eQ!\u0011Q\u001eB\u0001)\u0011\ty/a?\u0011\tI\u001a\u0014\u0011\u001f\u0016\u0005\u0003g\f\t\u0002\u0005\u00033g\u0005U\b\u0003\u0002\u001a4\u0003o\u00042AMA}\t\u0015i&B1\u00017\u0011%\tiPCA\u0001\u0002\b\ty0A\u0006fm&$WM\\2fIM\n\u0004\u0003\u0002!D\u0003oDq!a7\u000b\u0001\u0004\u0011\u0019\u0001E\u0006+\u0005\u000b\t9$a\u000e\u00028\u0005]\u0018b\u0001B\u0004I\tIa)\u001e8di&|gn\r\u0005\b\u0003kQ\u0001\u0019AA\u001c\u0011\u001d\tyD\u0003a\u0001\u0003oAq!!\u001a\u000b\u0001\u0004\t9$\u0006\u0003\u0003\u0012\t\rBC\u0003B\n\u0005g\u0011)Da\u000e\u0003:Q!!Q\u0003B\u0016)\u0011\u00119B!\n\u0011\tI\u001a$\u0011\u0004\u0016\u0005\u00057\t\t\u0002\u0005\u00033g\tu\u0001\u0003\u0002\u001a4\u0005?\u0001BAM\u001a\u0003\"A\u0019!Ga\t\u0005\u000bu[!\u0019\u0001\u001c\t\u0013\t\u001d2\"!AA\u0004\t%\u0012aC3wS\u0012,gnY3%gI\u0002B\u0001Q\"\u0003\"!9\u00111\\\u0006A\u0002\t5\u0002#\u0004\u0016\u00030\u0005]\u0012qGA\u001c\u0003o\u0011\t#C\u0002\u00032\u0011\u0012\u0011BR;oGRLwN\u001c\u001b\t\u000f\u0005U2\u00021\u0001\u00028!9\u0011qH\u0006A\u0002\u0005]\u0002bBA3\u0017\u0001\u0007\u0011q\u0007\u0005\b\u0003\u001f[\u0001\u0019AA\u001c+\u0011\u0011iD!\u0015\u0015\u0019\t}\"\u0011\rB2\u0005K\u00129G!\u001b\u0015\t\t\u0005#\u0011\f\u000b\u0005\u0005\u0007\u0012\u0019\u0006\u0005\u00033g\t\u0015#\u0006\u0002B$\u0003#\u0001BAM\u001a\u0003JA!!g\rB&!\u0011\u00114G!\u0014\u0011\tI\u001a$q\n\t\u0004e\tEC!B/\r\u0005\u00041\u0004\"\u0003B+\u0019\u0005\u0005\t9\u0001B,\u0003-)g/\u001b3f]\u000e,GeM\u001a\u0011\t\u0001\u001b%q\n\u0005\b\u00037d\u0001\u0019\u0001B.!=Q#QLA\u001c\u0003o\t9$a\u000e\u00028\t=\u0013b\u0001B0I\tIa)\u001e8di&|g.\u000e\u0005\b\u0003ka\u0001\u0019AA\u001c\u0011\u001d\ty\u0004\u0004a\u0001\u0003oAq!!\u001a\r\u0001\u0004\t9\u0004C\u0004\u0002\u00102\u0001\r!a\u000e\t\u000f\u0005uF\u00021\u0001\u00028\u000592\t\\1tgR\u000bw-\u0013;fe\u0006\u0014G.\u001a$bGR|'/\u001f\t\u0003]9\u0019BAD\u0015\u0003rA!!1\u000fB?\u001b\t\u0011)H\u0003\u0003\u0003x\te\u0014AA5p\u0015\t\u0011Y(\u0001\u0003kCZ\f\u0017\u0002\u0002B@\u0005k\u0012AbU3sS\u0006d\u0017N_1cY\u0016\fa\u0001P5oSRtDC\u0001B7\u0005!!U\r\\3hCR,W\u0003\u0002BE\u00053\u001bR\u0001\u0005BF\u0005C\u0003rA!$\u0003\u0014\n]uHD\u0002/\u0005\u001fK1A!%#\u0003])e/\u001b3f]\u000e,\u0017\n^3sC\ndWMR1di>\u0014\u00180\u0003\u0003\u0003\u0006\nU%b\u0001BIEA\u0019!G!'\u0005\rQ\u0002\"\u0019\u0001BN+\r1$Q\u0014\u0003\b\u0005?\u0013IJ1\u00017\u0005\u0015yF\u0005J\u001a2!\u0011q\u0003Aa&\u0002\u0011\u0011,G.Z4bi\u0016\u0004RAL\u0018\u0003\u0018~\"BA!+\u0003.B)!1\u0016\t\u0003\u00186\ta\u0002C\u0004\u0003$J\u0001\rA!*)\u000fA\u0011\tLa.\u0003:B\u0019!Fa-\n\u0007\tUFE\u0001\tTKJL\u0017\r\u001c,feNLwN\\+J\t\u0006)a/\u00197vKz\t1AA\nB]fLE/\u001a:bE2,G)\u001a7fO\u0006$X-\u0006\u0003\u0003@\n%7\u0003B\n*\u0005\u0003\u0004RA\fBb\u0005\u000fL1A!2#\u0005=IE/\u001a:bE2,g)Y2u_JL\bc\u0001\u001a\u0003J\u00121Ag\u0005b\u0001\u0005\u0017,2A\u000eBg\t\u001d\u0011yM!3C\u0002Y\u0012Qa\u0018\u0013%gI\u0002BA\f\u0001\u0003HR!!Q\u001bBl!\u0015\u0011Yk\u0005Bd\u0011\u001d\u0011\u0019+\u0006a\u0001\u0005#\fQ!Z7qif,BA!8\u0003dV\u0011!q\u001c\t\u0006e\t%'\u0011\u001d\t\u0004e\t\rH!B/\u0017\u0005\u00041\u0014\u0001\u00024s_6,BA!;\u0003pR!!1\u001eBy!\u0015\u0011$\u0011\u001aBw!\r\u0011$q\u001e\u0003\u0006;^\u0011\rA\u000e\u0005\b\u0005g<\u0002\u0019\u0001B{\u0003\tIG\u000fE\u0003/\u0005o\u0014i/C\u0002\u0003z\n\u0012A\"\u0013;fe\u0006\u0014G.Z(oG\u0016\f!B\\3x\u0005VLG\u000eZ3s+\u0011\u0011ypa\u0004\u0016\u0005\r\u0005\u0001\u0003CB\u0002\u0007\u0013\u0019ia!\u0005\u000e\u0005\r\u0015!bAB\u0004E\u00059Q.\u001e;bE2,\u0017\u0002BB\u0006\u0007\u000b\u0011qAQ;jY\u0012,'\u000fE\u00023\u0007\u001f!Q!\u0018\rC\u0002Y\u0002RA\rBe\u0007\u001b\tQ!\u00199qYf,Baa\u0006\u0004\u001eQ!1\u0011DB\u0010!\u0015\u0011$\u0011ZB\u000e!\r\u00114Q\u0004\u0003\u0006;f\u0011\rA\u000e\u0005\b\u0007CI\u0002\u0019AB\u0012\u0003\u0015)G.Z7t!\u0015Q3QEB\u000e\u0013\r\u00199\u0003\n\u0002\u000byI,\u0007/Z1uK\u0012t\u0014aB5uKJ\fG/Z\u000b\u0005\u0007[\u0019)\u0004\u0006\u0004\u00040\r}2\u0011\t\u000b\u0005\u0007c\u00199\u0004E\u00033\u0005\u0013\u001c\u0019\u0004E\u00023\u0007k!Q!\u0018\u000eC\u0002YBq!a7\u001b\u0001\u0004\u0019I\u0004E\u0004+\u0007w\u0019\u0019da\r\n\u0007\ruBEA\u0005Gk:\u001cG/[8oc!11N\u0007a\u0001\u0007gAqaa\u0011\u001b\u0001\u0004\t9$A\u0002mK:\fa!\u001e8g_2$WCBB%\u0007#\u001aI\u0006\u0006\u0003\u0004L\r%D\u0003BB'\u0007'\u0002RA\rBe\u0007\u001f\u00022AMB)\t\u0015i6D1\u00017\u0011\u001d\tYn\u0007a\u0001\u0007+\u0002rAKB\u001e\u0007/\u001ai\u0006E\u00023\u00073\"aaa\u0017\u001c\u0005\u00041$!A*\u0011\u000b)\u001ayfa\u0019\n\u0007\r\u0005DE\u0001\u0004PaRLwN\u001c\t\bU\r\u00154qJB,\u0013\r\u00199\u0007\n\u0002\u0007)V\u0004H.\u001a\u001a\t\u000f\r-4\u00041\u0001\u0004X\u0005!\u0011N\\5u+\u0011\u0019yga\u001e\u0015\r\rE4qPBA)\u0011\u0019\u0019h!\u001f\u0011\u000bI\u0012Im!\u001e\u0011\u0007I\u001a9\bB\u0003^9\t\u0007a\u0007C\u0004\u0004|q\u0001\u001da! \u0002\u0003%\u0004B!\u00193\u0004v!11\u000e\ba\u0001\u0007kBa!\u001c\u000fA\u0002\rUT\u0003BBC\u0007\u001b#\u0002ba\"\u0004\u0014\u000eU5q\u0013\u000b\u0005\u0007\u0013\u001by\tE\u00033\u0005\u0013\u001cY\tE\u00023\u0007\u001b#Q!X\u000fC\u0002YBqaa\u001f\u001e\u0001\b\u0019\t\n\u0005\u0003bI\u000e-\u0005BB6\u001e\u0001\u0004\u0019Y\t\u0003\u0004n;\u0001\u000711\u0012\u0005\u0007{v\u0001\raa#\u0016\t\rm51\u0015\u000b\u0005\u0007;\u001bI\u000b\u0006\u0003\u0004 \u000e\u0015\u0006#\u0002\u001a\u0003J\u000e\u0005\u0006c\u0001\u001a\u0004$\u0012)QL\bb\u0001m!A\u00111\u0006\u0010\u0005\u0002\u0004\u00199\u000bE\u0003+\u0003_\u0019\t\u000bC\u0004\u0004,z\u0001\r!a\u000e\u0002\u00039,Baa,\u00048R!1\u0011WB_)\u0011\u0019\u0019l!/\u0011\u000bI\u0012Im!.\u0011\u0007I\u001a9\fB\u0003^?\t\u0007a\u0007C\u0004\u0002\\~\u0001\raa/\u0011\u000f)\u001aY$a\u000e\u00046\"911V\u0010A\u0002\u0005]\u0002fB\n\u00032\n]&\u0011X\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0007\u000b\u0004Baa2\u0004N6\u00111\u0011\u001a\u0006\u0005\u0007\u0017\u0014I(\u0001\u0003mC:<\u0017\u0002BBh\u0007\u0013\u0014aa\u00142kK\u000e$\b"
)
public interface ClassTagIterableFactory extends EvidenceIterableFactory {
   private ClassTag ccClassTag() {
      return ClassTag$.MODULE$.AnyRef();
   }

   default Object range(final Object start, final Object end, final Integral evidence$22, final ClassTag evidence$23) {
      NumericRange$ var10001 = NumericRange$.MODULE$;
      Object apply_step = evidence$22.one();
      NumericRange.Exclusive var7 = new NumericRange.Exclusive(start, end, apply_step, evidence$22);
      apply_step = null;
      return this.from(var7, evidence$23);
   }

   default Object range(final Object start, final Object end, final Object step, final Integral evidence$24, final ClassTag evidence$25) {
      NumericRange$ var10001 = NumericRange$.MODULE$;
      return this.from(new NumericRange.Exclusive(start, end, step, evidence$24), evidence$25);
   }

   default Object fill(final int n1, final int n2, final Function0 elem, final ClassTag evidence$26) {
      return this.fill(n1, () -> this.fill(n2, elem, evidence$26), ClassTag$.MODULE$.AnyRef());
   }

   default Object fill(final int n1, final int n2, final int n3, final Function0 elem, final ClassTag evidence$27) {
      return this.fill(n1, () -> this.fill(n2, n3, elem, evidence$27), ClassTag$.MODULE$.AnyRef());
   }

   default Object fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem, final ClassTag evidence$28) {
      return this.fill(n1, () -> this.fill(n2, n3, n4, elem, evidence$28), ClassTag$.MODULE$.AnyRef());
   }

   default Object fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem, final ClassTag evidence$29) {
      return this.fill(n1, () -> this.fill(n2, n3, n4, n5, elem, evidence$29), ClassTag$.MODULE$.AnyRef());
   }

   default Object tabulate(final int n1, final int n2, final Function2 f, final ClassTag evidence$30) {
      return this.tabulate(n1, (i1) -> $anonfun$tabulate$9(this, n2, f, evidence$30, BoxesRunTime.unboxToInt(i1)), ClassTag$.MODULE$.AnyRef());
   }

   default Object tabulate(final int n1, final int n2, final int n3, final Function3 f, final ClassTag evidence$31) {
      return this.tabulate(n1, (i1) -> $anonfun$tabulate$11(this, n2, n3, f, evidence$31, BoxesRunTime.unboxToInt(i1)), ClassTag$.MODULE$.AnyRef());
   }

   default Object tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f, final ClassTag evidence$32) {
      return this.tabulate(n1, (i1) -> $anonfun$tabulate$13(this, n2, n3, n4, f, evidence$32, BoxesRunTime.unboxToInt(i1)), ClassTag$.MODULE$.AnyRef());
   }

   default Object tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f, final ClassTag evidence$33) {
      return this.tabulate(n1, (i1) -> $anonfun$tabulate$15(this, n2, n3, n4, n5, f, evidence$33, BoxesRunTime.unboxToInt(i1)), ClassTag$.MODULE$.AnyRef());
   }

   // $FF: synthetic method
   static Object $anonfun$tabulate$10(final Function2 f$5, final int i1$5, final int x$15) {
      return f$5.apply(BoxesRunTime.boxToInteger(i1$5), BoxesRunTime.boxToInteger(x$15));
   }

   // $FF: synthetic method
   static Object $anonfun$tabulate$9(final ClassTagIterableFactory $this, final int n2$13, final Function2 f$5, final ClassTag evidence$30$1, final int i1) {
      return $this.tabulate(n2$13, (x$15) -> $anonfun$tabulate$10(f$5, i1, BoxesRunTime.unboxToInt(x$15)), evidence$30$1);
   }

   // $FF: synthetic method
   static Object $anonfun$tabulate$12(final Function3 f$6, final int i1$6, final int x$16, final int x$17) {
      return f$6.apply(BoxesRunTime.boxToInteger(i1$6), BoxesRunTime.boxToInteger(x$16), BoxesRunTime.boxToInteger(x$17));
   }

   // $FF: synthetic method
   static Object $anonfun$tabulate$11(final ClassTagIterableFactory $this, final int n2$14, final int n3$10, final Function3 f$6, final ClassTag evidence$31$1, final int i1) {
      return $this.tabulate(n2$14, n3$10, (x$16, x$17) -> $anonfun$tabulate$12(f$6, i1, BoxesRunTime.unboxToInt(x$16), BoxesRunTime.unboxToInt(x$17)), evidence$31$1);
   }

   // $FF: synthetic method
   static Object $anonfun$tabulate$14(final Function4 f$7, final int i1$7, final int x$18, final int x$19, final int x$20) {
      return f$7.apply(BoxesRunTime.boxToInteger(i1$7), BoxesRunTime.boxToInteger(x$18), BoxesRunTime.boxToInteger(x$19), BoxesRunTime.boxToInteger(x$20));
   }

   // $FF: synthetic method
   static Object $anonfun$tabulate$13(final ClassTagIterableFactory $this, final int n2$15, final int n3$11, final int n4$7, final Function4 f$7, final ClassTag evidence$32$1, final int i1) {
      return $this.tabulate(n2$15, n3$11, n4$7, (x$18, x$19, x$20) -> $anonfun$tabulate$14(f$7, i1, BoxesRunTime.unboxToInt(x$18), BoxesRunTime.unboxToInt(x$19), BoxesRunTime.unboxToInt(x$20)), evidence$32$1);
   }

   // $FF: synthetic method
   static Object $anonfun$tabulate$16(final Function5 f$8, final int i1$8, final int x$21, final int x$22, final int x$23, final int x$24) {
      return f$8.apply(BoxesRunTime.boxToInteger(i1$8), BoxesRunTime.boxToInteger(x$21), BoxesRunTime.boxToInteger(x$22), BoxesRunTime.boxToInteger(x$23), BoxesRunTime.boxToInteger(x$24));
   }

   // $FF: synthetic method
   static Object $anonfun$tabulate$15(final ClassTagIterableFactory $this, final int n2$16, final int n3$12, final int n4$8, final int n5$4, final Function5 f$8, final ClassTag evidence$33$1, final int i1) {
      return $this.tabulate(n2$16, n3$12, n4$8, n5$4, (x$21, x$22, x$23, x$24) -> $anonfun$tabulate$16(f$8, i1, BoxesRunTime.unboxToInt(x$21), BoxesRunTime.unboxToInt(x$22), BoxesRunTime.unboxToInt(x$23), BoxesRunTime.unboxToInt(x$24)), evidence$33$1);
   }

   static void $init$(final ClassTagIterableFactory $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class Delegate extends EvidenceIterableFactory.Delegate implements ClassTagIterableFactory {
      private static final long serialVersionUID = 3L;

      public Object range(final Object start, final Object end, final Integral evidence$22, final ClassTag evidence$23) {
         return ClassTagIterableFactory.super.range(start, end, evidence$22, evidence$23);
      }

      public Object range(final Object start, final Object end, final Object step, final Integral evidence$24, final ClassTag evidence$25) {
         return ClassTagIterableFactory.super.range(start, end, step, evidence$24, evidence$25);
      }

      public Object fill(final int n1, final int n2, final Function0 elem, final ClassTag evidence$26) {
         return ClassTagIterableFactory.super.fill(n1, n2, elem, evidence$26);
      }

      public Object fill(final int n1, final int n2, final int n3, final Function0 elem, final ClassTag evidence$27) {
         return ClassTagIterableFactory.super.fill(n1, n2, n3, elem, evidence$27);
      }

      public Object fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem, final ClassTag evidence$28) {
         return ClassTagIterableFactory.super.fill(n1, n2, n3, n4, elem, evidence$28);
      }

      public Object fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem, final ClassTag evidence$29) {
         return ClassTagIterableFactory.super.fill(n1, n2, n3, n4, n5, elem, evidence$29);
      }

      public Object tabulate(final int n1, final int n2, final Function2 f, final ClassTag evidence$30) {
         return ClassTagIterableFactory.super.tabulate(n1, n2, f, evidence$30);
      }

      public Object tabulate(final int n1, final int n2, final int n3, final Function3 f, final ClassTag evidence$31) {
         return ClassTagIterableFactory.super.tabulate(n1, n2, n3, f, evidence$31);
      }

      public Object tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f, final ClassTag evidence$32) {
         return ClassTagIterableFactory.super.tabulate(n1, n2, n3, n4, f, evidence$32);
      }

      public Object tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f, final ClassTag evidence$33) {
         return ClassTagIterableFactory.super.tabulate(n1, n2, n3, n4, n5, f, evidence$33);
      }

      public Delegate(final EvidenceIterableFactory delegate) {
         super(delegate);
      }
   }

   public static class AnyIterableDelegate implements IterableFactory {
      private static final long serialVersionUID = 3L;
      private final ClassTagIterableFactory delegate;

      public Object fill(final int n1, final int n2, final Function0 elem) {
         return IterableFactory.fill$(this, n1, n2, elem);
      }

      public Object fill(final int n1, final int n2, final int n3, final Function0 elem) {
         return IterableFactory.fill$(this, n1, n2, n3, elem);
      }

      public Object fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem) {
         return IterableFactory.fill$(this, n1, n2, n3, n4, elem);
      }

      public Object fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem) {
         return IterableFactory.fill$(this, n1, n2, n3, n4, n5, elem);
      }

      public Object tabulate(final int n1, final int n2, final Function2 f) {
         return IterableFactory.tabulate$(this, n1, n2, f);
      }

      public Object tabulate(final int n1, final int n2, final int n3, final Function3 f) {
         return IterableFactory.tabulate$(this, n1, n2, n3, f);
      }

      public Object tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f) {
         return IterableFactory.tabulate$(this, n1, n2, n3, n4, f);
      }

      public Object tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f) {
         return IterableFactory.tabulate$(this, n1, n2, n3, n4, n5, f);
      }

      public Object concat(final scala.collection.immutable.Seq xss) {
         return IterableFactory.concat$(this, xss);
      }

      public Factory iterableFactory() {
         return IterableFactory.iterableFactory$(this);
      }

      public Object empty() {
         return this.delegate.empty(ClassTag$.MODULE$.Any());
      }

      public Object from(final IterableOnce it) {
         return this.delegate.from(it, ClassTag$.MODULE$.Any());
      }

      public Builder newBuilder() {
         return this.delegate.newBuilder(ClassTag$.MODULE$.Any());
      }

      public Object apply(final scala.collection.immutable.Seq elems) {
         return this.delegate.apply(elems, ClassTag$.MODULE$.Any());
      }

      public Object iterate(final Object start, final int len, final Function1 f) {
         return this.delegate.iterate(start, len, f, ClassTag$.MODULE$.Any());
      }

      public Object unfold(final Object init, final Function1 f) {
         return this.delegate.unfold(init, f, ClassTag$.MODULE$.Any());
      }

      public Object range(final Object start, final Object end, final Integral i) {
         return this.delegate.range(start, end, i, ClassTag$.MODULE$.Any());
      }

      public Object range(final Object start, final Object end, final Object step, final Integral i) {
         return this.delegate.range(start, end, step, i, ClassTag$.MODULE$.Any());
      }

      public Object fill(final int n, final Function0 elem) {
         return this.delegate.fill(n, elem, ClassTag$.MODULE$.Any());
      }

      public Object tabulate(final int n, final Function1 f) {
         return this.delegate.tabulate(n, f, ClassTag$.MODULE$.Any());
      }

      public AnyIterableDelegate(final ClassTagIterableFactory delegate) {
         this.delegate = delegate;
      }
   }
}
