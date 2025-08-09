package spire.math;

import algebra.ring.Field;
import algebra.ring.GCDRing;
import algebra.ring.Signed;
import cats.kernel.Eq;
import cats.kernel.Order;
import java.math.BigInteger;
import java.math.MathContext;
import scala.Tuple2;
import scala.collection.immutable.Seq;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import spire.algebra.IsReal;
import spire.algebra.NRoot;
import spire.algebra.Trig;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d5s\u0001CA\u0007\u0003\u001fA\t!!\u0007\u0007\u0011\u0005u\u0011q\u0002E\u0001\u0003?Aq!!\f\u0002\t\u0003\ty\u0003C\u0004\u00022\u0005!)!a\r\t\u000f\u0005E\u0012\u0001\"\u0002\u0002@!9\u0011\u0011G\u0001\u0005\u0006\u0005%\u0003bBA\u0019\u0003\u0011\u0015\u00111\u000b\u0005\b\u0003c\tAQAA/\u0011\u001d\t\t$\u0001C\u0003\u0003OBq!!\r\u0002\t\u000b\t\t\bC\u0004\u00024\u0006!)!!.\t\u000f\u0005M\u0016\u0001\"\u0002\u0002:\"9\u00111W\u0001\u0005\u0006\u0005u\u0006bBAZ\u0003\u0011\u0015\u0011\u0011\u001b\u0005\b\u0003O\fA\u0011AAu\u0011\u001d\t90\u0001C\u0001\u0003sDq!!@\u0002\t\u0003\ty\u0010C\u0004\u0003\u0004\u0005!)A!\u0002\t\u000f\t\r\u0011\u0001\"\u0002\u0003\n!9!1A\u0001\u0005\u0006\t5\u0001b\u0002B\u0002\u0003\u0011\u0015!\u0011\u0003\u0005\b\u0005C\tAQ\u0001B\u0012\u0011\u001d\u0011\t#\u0001C\u0003\u0005OAqA!\t\u0002\t\u000b\u0011Y\u0003C\u0004\u0003\"\u0005!)Aa\f\t\u000f\t}\u0012\u0001\"\u0002\u0003B!9!qH\u0001\u0005\u0006\t\u0015\u0003b\u0002B \u0003\u0011\u0015!Q\n\u0005\b\u0005\u007f\tAQ\u0001B)\u0011\u001d\u00119'\u0001C\u0003\u0005SBqAa\u001a\u0002\t\u000b\u0011i\u0007C\u0004\u0003h\u0005!)A!\u001e\t\u000f\t\u001d\u0014\u0001\"\u0001\u0003z!9!qM\u0001\u0005\u0006\t}\u0004b\u0002B4\u0003\u0011\u0015!q\u0012\u0005\b\u0005W\u000bAQ\u0001BW\u0011\u001d\u0011Y+\u0001C\u0003\u0005kCqAa+\u0002\t\u000b\u0011i\fC\u0004\u0003,\u0006!)Aa1\t\u000f\t%\u0017\u0001\"\u0002\u0003L\"9!\u0011Z\u0001\u0005\u0006\tU\u0007b\u0002Be\u0003\u0011\u0015!Q\u001c\u0005\b\u0005\u0013\fAQAB\u0001\u0011\u001d\u0011I-\u0001C\u0003\u0007?Aqa!\u0012\u0002\t\u000b\u00199\u0005C\u0004\u0004F\u0005!)a!\u0014\t\u000f\r\u0015\u0013\u0001\"\u0002\u0004T!911N\u0001\u0005\u0002\r5\u0004bBB6\u0003\u0011\u00051\u0011\u0010\u0005\b\u0007W\nA\u0011ABA\u0011\u001d\u0019Y'\u0001C\u0001\u0007\u0013Cqaa\u001b\u0002\t\u0003\u0019\t\nC\u0004\u0004l\u0005!\ta!'\t\u000f\r=\u0016\u0001\"\u0001\u00042\"91qV\u0001\u0005\u0002\r]\u0006bBBX\u0003\u0011\u00051Q\u0018\u0005\b\u0007_\u000bA\u0011ABb\u0011\u001d\u0019y+\u0001C\u0001\u0007\u0013Dqaa,\u0002\t\u0003\u0019y\rC\u0004\u0004V\u0006!\taa6\t\u000f\rU\u0017\u0001\"\u0001\u0004^\"91Q[\u0001\u0005\u0002\r\r\bbBBk\u0003\u0011\u00051\u0011\u001e\u0005\b\u0007+\fA\u0011ABx\u0011\u001d\u0019).\u0001C\u0001\u0007kDqaa?\u0002\t\u000b\u0019i\u0010C\u0004\u0004|\u0006!)\u0001b\u0001\t\u000f\rm\u0018\u0001\"\u0002\u0005\n!911`\u0001\u0005\u0006\u0011=\u0001bBB~\u0003\u0011\u0015AQ\u0003\u0005\b\u0007w\fAQ\u0001C\u000e\u0011\u001d\u0019Y0\u0001C\u0003\tCAq\u0001b\u000e\u0002\t\u000b!I\u0004C\u0004\u00058\u0005!)\u0001b\u0010\t\u000f\u0011]\u0012\u0001\"\u0002\u0005F!9AqG\u0001\u0005\u0006\u0011-\u0003b\u0002C\u001c\u0003\u0011\u0015A\u0011\u000b\u0005\b\to\tAQ\u0001C,\u0011\u001d!9$\u0001C\u0003\t;Bq\u0001b\u001c\u0002\t\u000b!\t\bC\u0004\u0005p\u0005!)\u0001\"\u001e\t\u000f\u0011=\u0014\u0001\"\u0002\u0005z!9A\u0011R\u0001\u0005\u0006\u0011-\u0005b\u0002CE\u0003\u0011\u0015Aq\u0012\u0005\b\tG\u000bAQ\u0001CS\u0011\u001d!\u0019+\u0001C\u0003\tOCq\u0001b4\u0002\t\u000b!)\u000bC\u0004\u0005P\u0006!)\u0001\"5\t\u000f\u0011\u001d\u0018\u0001\"\u0002\u0005j\"9Q1A\u0001\u0005\u0006\u0015\u0015\u0001bBC\u0010\u0003\u0011\u0015Q\u0011\u0005\u0005\b\u000bw\tAQAC\u001f\u0011\u001d)9&\u0001C\u0003\u000b3Bq!b\u001d\u0002\t\u000b))\bC\u0004\u0006\u0010\u0006!)!\"%\t\u000f\u00155\u0016\u0001\"\u0002\u00060\"9Q\u0011Z\u0001\u0005\u0006\u0015-\u0007bBCs\u0003\u0011\u0015Qq\u001d\u0005\b\r\u0003\tAQ\u0001D\u0002\u0011\u001d19!\u0001C\u0003\r\u0013AqAb\u0002\u0002\t\u000b1\u0019\u0002C\u0004\u0006J\u0006!)A\"\u0007\t\u000f\u0019u\u0011\u0001\"\u0002\u0007 !9a1E\u0001\u0005\u0006\u0019\u0015\u0002b\u0002D\u0012\u0003\u0011\u0015a\u0011\u0006\u0005\b\r[\tAQ\u0001D\u0018\u0011\u001d19$\u0001C\u0003\rsAqA\"\u0010\u0002\t\u000b1y\u0004C\u0004\u0007D\u0005!)A\"\u0012\t\u000f\u0019\r\u0013\u0001\"\u0002\u0007L!9a\u0011K\u0001\u0005\u0006\u0019M\u0003b\u0002D)\u0003\u0011\u0015aq\u000b\u0005\b\r7\nAQ\u0001CS\u0011\u001d1i&\u0001C\u0003\r?BqAb\u0019\u0002\t\u000b1)\u0007C\u0004\u0007d\u0005!)Ab\u001b\t\u000f\u0019E\u0014\u0001\"\u0002\u0007t!9aqO\u0001\u0005\u0006\u0019e\u0004b\u0002D?\u0003\u0011\u0015aq\u0010\u0005\b\r{\nAQ\u0001DB\u0011\u001d19)\u0001C\u0003\r\u0013CqAb-\u0002\t\u00131)\fC\u0004\u0007F\u0006!IAb2\t\u000f\u0019\u0005\u0018\u0001\"\u0003\u0007d\"Ia1_\u0001\u0012\u0002\u0013%aQ\u001f\u0005\b\u000f\u0017\tA\u0011BD\u0007\u0011%99\"\u0001b\u0001\n\u00139I\u0002\u0003\u0005\b\u001c\u0005\u0001\u000b\u0011BA&\u0011\u001d9i\"\u0001C\u0001\u000f?A\u0011bb\f\u0002\t\u0003\t\u0019b\"\r\t\u0013\u001dU\u0012\u0001\"\u0001\u0002\u0014\u001d]\u0002\"CD\u001e\u0003\u0011\u0005\u00111CD\u001f\u0011%9\t%\u0001C\u0001\u0003'9\u0019\u0005C\u0005\bH\u0005!\t!a\u0005\bJ\u00059\u0001/Y2lC\u001e,'\u0002BA\t\u0003'\tA!\\1uQ*\u0011\u0011QC\u0001\u0006gBL'/Z\u0002\u0001!\r\tY\"A\u0007\u0003\u0003\u001f\u0011q\u0001]1dW\u0006<WmE\u0002\u0002\u0003C\u0001B!a\t\u0002*5\u0011\u0011Q\u0005\u0006\u0003\u0003O\tQa]2bY\u0006LA!a\u000b\u0002&\t1\u0011I\\=SK\u001a\fa\u0001P5oSRtDCAA\r\u0003\r\t'm\u001d\u000b\u0005\u0003k\tY\u0004\u0005\u0003\u0002$\u0005]\u0012\u0002BA\u001d\u0003K\u0011AAQ=uK\"9\u0011QH\u0002A\u0002\u0005U\u0012!\u00018\u0015\t\u0005\u0005\u0013q\t\t\u0005\u0003G\t\u0019%\u0003\u0003\u0002F\u0005\u0015\"!B*i_J$\bbBA\u001f\t\u0001\u0007\u0011\u0011\t\u000b\u0005\u0003\u0017\n\t\u0006\u0005\u0003\u0002$\u00055\u0013\u0002BA(\u0003K\u00111!\u00138u\u0011\u001d\ti$\u0002a\u0001\u0003\u0017\"B!!\u0016\u0002\\A!\u00111EA,\u0013\u0011\tI&!\n\u0003\t1{gn\u001a\u0005\b\u0003{1\u0001\u0019AA+)\u0011\ty&!\u001a\u0011\t\u0005\r\u0012\u0011M\u0005\u0005\u0003G\n)CA\u0003GY>\fG\u000fC\u0004\u0002>\u001d\u0001\r!a\u0018\u0015\t\u0005%\u0014q\u000e\t\u0005\u0003G\tY'\u0003\u0003\u0002n\u0005\u0015\"A\u0002#pk\ndW\rC\u0004\u0002>!\u0001\r!!\u001b\u0016\t\u0005M\u00141\u0010\u000b\u0005\u0003k\ny\u000b\u0006\u0003\u0002x\u00055\u0005\u0003BA=\u0003wb\u0001\u0001B\u0004\u0002~%\u0011\r!a \u0003\u0003\u0005\u000bB!!!\u0002\bB!\u00111EAB\u0013\u0011\t))!\n\u0003\u000f9{G\u000f[5oOB!\u00111EAE\u0013\u0011\tY)!\n\u0003\u0007\u0005s\u0017\u0010C\u0004\u0002\u0010&\u0001\u001d!!%\u0002\u0005\u00154\bCBAJ\u0003S\u000b9H\u0004\u0003\u0002\u0016\u0006\u0015f\u0002BAL\u0003CsA!!'\u0002 6\u0011\u00111\u0014\u0006\u0005\u0003;\u000b9\"\u0001\u0004=e>|GOP\u0005\u0003\u0003+IA!a)\u0002\u0014\u00059\u0011\r\\4fEJ\f\u0017\u0002BA\u0007\u0003OSA!a)\u0002\u0014%!\u00111VAW\u0005\u0019\u0019\u0016n\u001a8fI*!\u0011QBAT\u0011\u001d\t\t,\u0003a\u0001\u0003o\n\u0011!Y\u0001\u0005G\u0016LG\u000e\u0006\u0003\u0002`\u0005]\u0006bBA\u001f\u0015\u0001\u0007\u0011q\f\u000b\u0005\u0003S\nY\fC\u0004\u0002>-\u0001\r!!\u001b\u0015\t\u0005}\u0016q\u001a\t\u0005\u0003\u0003\fIM\u0004\u0003\u0002D\u0006\u001dg\u0002BAM\u0003\u000bL!!a\n\n\t\u00055\u0011QE\u0005\u0005\u0003\u0017\fiM\u0001\u0006CS\u001e$UmY5nC2TA!!\u0004\u0002&!9\u0011Q\b\u0007A\u0002\u0005}V\u0003BAj\u00033$B!!6\u0002fR!\u0011q[An!\u0011\tI(!7\u0005\u000f\u0005uTB1\u0001\u0002\u0000!9\u0011qR\u0007A\u0004\u0005u\u0007CBAp\u0003C\f9.\u0004\u0002\u0002(&!\u00111]AT\u0005\u0019I5OU3bY\"9\u0011\u0011W\u0007A\u0002\u0005]\u0017AB2i_>\u001cX\r\u0006\u0004\u0002l\u0006E\u00181\u001f\t\u0005\u0003\u0003\fi/\u0003\u0003\u0002p\u00065'A\u0002\"jO&sG\u000fC\u0004\u0002>9\u0001\r!!\u0016\t\u000f\u0005Uh\u00021\u0001\u0002V\u0005\t1.\u0001\u0003gC\u000e$H\u0003BAv\u0003wDq!!\u0010\u0010\u0001\u0004\t)&A\u0002gS\n$B!a;\u0003\u0002!9\u0011Q\b\tA\u0002\u0005U\u0013!\u00024m_>\u0014H\u0003BA0\u0005\u000fAq!!\u0010\u0012\u0001\u0004\ty\u0006\u0006\u0003\u0002j\t-\u0001bBA\u001f%\u0001\u0007\u0011\u0011\u000e\u000b\u0005\u0003\u007f\u0013y\u0001C\u0004\u0002>M\u0001\r!a0\u0016\t\tM!\u0011\u0004\u000b\u0005\u0005+\u0011y\u0002\u0006\u0003\u0003\u0018\tm\u0001\u0003BA=\u00053!q!! \u0015\u0005\u0004\ty\bC\u0004\u0002\u0010R\u0001\u001dA!\b\u0011\r\u0005}\u0017\u0011\u001dB\f\u0011\u001d\t\t\f\u0006a\u0001\u0005/\tQA]8v]\u0012$B!a\u0018\u0003&!9\u0011\u0011W\u000bA\u0002\u0005}C\u0003BA5\u0005SAq!!-\u0017\u0001\u0004\tI\u0007\u0006\u0003\u0002@\n5\u0002bBAY/\u0001\u0007\u0011qX\u000b\u0005\u0005c\u00119\u0004\u0006\u0003\u00034\tuB\u0003\u0002B\u001b\u0005s\u0001B!!\u001f\u00038\u00119\u0011Q\u0010\rC\u0002\u0005}\u0004bBAH1\u0001\u000f!1\b\t\u0007\u0003?\f\tO!\u000e\t\u000f\u0005E\u0006\u00041\u0001\u00036\u0005\u0019Q\r\u001f9\u0015\t\u0005%$1\t\u0005\b\u0003{I\u0002\u0019AA5)\u0019\tyLa\u0012\u0003J!9\u0011Q\u001f\u000eA\u0002\u0005-\u0003b\u0002B&5\u0001\u0007\u00111J\u0001\naJ,7-[:j_:$B!a0\u0003P!9\u0011Q_\u000eA\u0002\u0005}V\u0003\u0002B*\u00053\"BA!\u0016\u0003fQ!!q\u000bB.!\u0011\tIH!\u0017\u0005\u000f\u0005uDD1\u0001\u0002\u0000!9!Q\f\u000fA\u0004\t}\u0013!\u0001;\u0011\r\u0005}'\u0011\rB,\u0013\u0011\u0011\u0019'a*\u0003\tQ\u0013\u0018n\u001a\u0005\b\u0003cc\u0002\u0019\u0001B,\u0003\rawn\u001a\u000b\u0005\u0003S\u0012Y\u0007C\u0004\u0002>u\u0001\r!!\u001b\u0015\r\u0005%$q\u000eB9\u0011\u001d\tiD\ba\u0001\u0003SBqAa\u001d\u001f\u0001\u0004\tY%\u0001\u0003cCN,G\u0003BA`\u0005oBq!!\u0010 \u0001\u0004\ty\f\u0006\u0004\u0002@\nm$Q\u0010\u0005\b\u0003{\u0001\u0003\u0019AA`\u0011\u001d\u0011\u0019\b\ta\u0001\u0003\u0017*BA!!\u0003\bR!!1\u0011BG)\u0011\u0011)I!#\u0011\t\u0005e$q\u0011\u0003\b\u0003{\n#\u0019AA@\u0011\u001d\u0011i&\ta\u0002\u0005\u0017\u0003b!a8\u0003b\t\u0015\u0005bBAYC\u0001\u0007!QQ\u000b\u0005\u0005#\u00139\n\u0006\u0004\u0003\u0014\n\u001d&\u0011\u0016\u000b\u0007\u0005+\u0013IJa)\u0011\t\u0005e$q\u0013\u0003\b\u0003{\u0012#\u0019AA@\u0011\u001d\u0011YJ\ta\u0002\u0005;\u000b\u0011A\u001a\t\u0007\u0003'\u0013yJ!&\n\t\t\u0005\u0016Q\u0016\u0002\u0006\r&,G\u000e\u001a\u0005\b\u0005;\u0012\u00039\u0001BS!\u0019\tyN!\u0019\u0003\u0016\"9\u0011\u0011\u0017\u0012A\u0002\tU\u0005b\u0002B:E\u0001\u0007\u00111J\u0001\u0004a><HCBA`\u0005_\u0013\t\fC\u0004\u0003t\r\u0002\r!a0\t\u000f\tM6\u00051\u0001\u0002@\u0006AQ\r\u001f9p]\u0016tG\u000f\u0006\u0004\u0002l\n]&\u0011\u0018\u0005\b\u0005g\"\u0003\u0019AAv\u0011\u001d\u0011Y\f\na\u0001\u0003W\f!!\u001a=\u0015\r\u0005U#q\u0018Ba\u0011\u001d\u0011\u0019(\na\u0001\u0003+BqAa-&\u0001\u0004\t)\u0006\u0006\u0004\u0002j\t\u0015'q\u0019\u0005\b\u0005g2\u0003\u0019AA5\u0011\u001d\u0011\u0019L\na\u0001\u0003S\n1aZ2e)\u0019\t)F!4\u0003R\"9!qZ\u0014A\u0002\u0005U\u0013AA0y\u0011\u001d\u0011\u0019n\na\u0001\u0003+\n!aX=\u0015\r\u0005-(q\u001bBm\u0011\u001d\t\t\f\u000ba\u0001\u0003WDqAa7)\u0001\u0004\tY/A\u0001c+\u0011\u0011yN!:\u0015\r\t\u0005(\u0011 B\u007f)\u0019\u0011\u0019Oa:\u0003rB!\u0011\u0011\u0010Bs\t\u001d\ti(\u000bb\u0001\u0003\u007fB\u0011B!;*\u0003\u0003\u0005\u001dAa;\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\u0005\u0004\u0002\u0014\n5(1]\u0005\u0005\u0005_\fiK\u0001\u0002Fc\"9\u0011qR\u0015A\u0004\tM\bCBAJ\u0005k\u0014\u0019/\u0003\u0003\u0003x\u00065&aB$D\tJKgn\u001a\u0005\b\u0005wL\u0003\u0019\u0001Br\u0003\u0005A\bb\u0002B\u0000S\u0001\u0007!1]\u0001\u0002sV!11AB\u0005)\u0011\u0019)a!\u0006\u0015\r\r\u001d11BB\t!\u0011\tIh!\u0003\u0005\u000f\u0005u$F1\u0001\u0002\u0000!I1Q\u0002\u0016\u0002\u0002\u0003\u000f1qB\u0001\u000bKZLG-\u001a8dK\u0012\u0012\u0004CBAJ\u0005[\u001c9\u0001C\u0004\u0002\u0010*\u0002\u001daa\u0005\u0011\r\u0005M%Q_B\u0004\u0011\u001d\u00199B\u000ba\u0001\u00073\t!\u0001_:\u0011\r\u0005\u000571DB\u0004\u0013\u0011\u0019i\"!4\u0003\u0007M+\u0017/\u0006\u0003\u0004\"\r\u001dBCCB\u0012\u0007g\u0019)da\u000e\u0004<Q11QEB\u0015\u0007_\u0001B!!\u001f\u0004(\u00119\u0011QP\u0016C\u0002\u0005}\u0004\"CB\u0016W\u0005\u0005\t9AB\u0017\u0003))g/\u001b3f]\u000e,Ge\r\t\u0007\u0003'\u0013io!\n\t\u000f\u0005=5\u0006q\u0001\u00042A1\u00111\u0013B{\u0007KAqAa?,\u0001\u0004\u0019)\u0003C\u0004\u0003\u0000.\u0002\ra!\n\t\u000f\re2\u00061\u0001\u0004&\u0005\t!\u0010C\u0004\u0004>-\u0002\raa\u0010\u0002\tI,7\u000f\u001e\t\u0007\u0003G\u0019\te!\n\n\t\r\r\u0013Q\u0005\u0002\u000byI,\u0007/Z1uK\u0012t\u0014a\u00017d[R1\u0011QKB%\u0007\u0017BqAa?-\u0001\u0004\t)\u0006C\u0004\u0003\u00002\u0002\r!!\u0016\u0015\r\u0005-8qJB)\u0011\u001d\t\t,\fa\u0001\u0003WDqAa7.\u0001\u0004\tY/\u0006\u0003\u0004V\rmCCBB,\u0007O\u001aI\u0007\u0006\u0004\u0004Z\ru31\r\t\u0005\u0003s\u001aY\u0006B\u0004\u0002~9\u0012\r!a \t\u0013\r}c&!AA\u0004\r\u0005\u0014AC3wS\u0012,gnY3%iA1\u00111\u0013Bw\u00073Bq!a$/\u0001\b\u0019)\u0007\u0005\u0004\u0002\u0014\nU8\u0011\f\u0005\b\u0005wt\u0003\u0019AB-\u0011\u001d\u0011yP\fa\u0001\u00073\n\u0001\"Z9v_Rlw\u000e\u001a\u000b\u0007\u0007_\u001a)ha\u001e\u0011\u0011\u0005\r2\u0011OA\u001b\u0003kIAaa\u001d\u0002&\t1A+\u001e9mKJBq!!-0\u0001\u0004\t)\u0004C\u0004\u0003\\>\u0002\r!!\u000e\u0015\r\rm4QPB@!!\t\u0019c!\u001d\u0002B\u0005\u0005\u0003bBAYa\u0001\u0007\u0011\u0011\t\u0005\b\u00057\u0004\u0004\u0019AA!)\u0019\u0019\u0019i!\"\u0004\bBA\u00111EB9\u0003\u0017\nY\u0005C\u0004\u00022F\u0002\r!a\u0013\t\u000f\tm\u0017\u00071\u0001\u0002LQ111RBG\u0007\u001f\u0003\u0002\"a\t\u0004r\u0005U\u0013Q\u000b\u0005\b\u0003c\u0013\u0004\u0019AA+\u0011\u001d\u0011YN\ra\u0001\u0003+\"baa%\u0004\u0016\u000e]\u0005\u0003CA\u0012\u0007c\nY/a;\t\u000f\u0005E6\u00071\u0001\u0002l\"9!1\\\u001aA\u0002\u0005-HCBBN\u0007W\u001bi\u000b\u0005\u0005\u0002$\rE4QTBO!\u0011\u0019yja*\u000e\u0005\r\u0005&\u0002BA\t\u0007GS!a!*\u0002\t)\fg/Y\u0005\u0005\u0007S\u001b\tK\u0001\u0006CS\u001eLe\u000e^3hKJDq!!-5\u0001\u0004\u0019i\nC\u0004\u0003\\R\u0002\ra!(\u0002\u000b\u0015\fXo\u001c;\u0015\r\u0005U21WB[\u0011\u001d\t\t,\u000ea\u0001\u0003kAqAa76\u0001\u0004\t)\u0004\u0006\u0004\u0002B\re61\u0018\u0005\b\u0003c3\u0004\u0019AA!\u0011\u001d\u0011YN\u000ea\u0001\u0003\u0003\"b!a\u0013\u0004@\u000e\u0005\u0007bBAYo\u0001\u0007\u00111\n\u0005\b\u00057<\u0004\u0019AA&)\u0019\t)f!2\u0004H\"9\u0011\u0011\u0017\u001dA\u0002\u0005U\u0003b\u0002Bnq\u0001\u0007\u0011Q\u000b\u000b\u0007\u0003W\u001cYm!4\t\u000f\u0005E\u0016\b1\u0001\u0002l\"9!1\\\u001dA\u0002\u0005-HCBBO\u0007#\u001c\u0019\u000eC\u0004\u00022j\u0002\ra!(\t\u000f\tm'\b1\u0001\u0004\u001e\u0006!Q-\\8e)\u0019\t)d!7\u0004\\\"9\u0011\u0011W\u001eA\u0002\u0005U\u0002b\u0002Bnw\u0001\u0007\u0011Q\u0007\u000b\u0007\u0003\u0003\u001ayn!9\t\u000f\u0005EF\b1\u0001\u0002B!9!1\u001c\u001fA\u0002\u0005\u0005CCBA&\u0007K\u001c9\u000fC\u0004\u00022v\u0002\r!a\u0013\t\u000f\tmW\b1\u0001\u0002LQ1\u0011QKBv\u0007[Dq!!-?\u0001\u0004\t)\u0006C\u0004\u0003\\z\u0002\r!!\u0016\u0015\r\u0005-8\u0011_Bz\u0011\u001d\t\tl\u0010a\u0001\u0003WDqAa7@\u0001\u0004\tY\u000f\u0006\u0004\u0004\u001e\u000e]8\u0011 \u0005\b\u0003c\u0003\u0005\u0019ABO\u0011\u001d\u0011Y\u000e\u0011a\u0001\u0007;\u000b1!\\5o)\u0019\t)da@\u0005\u0002!9!1`!A\u0002\u0005U\u0002b\u0002B\u0000\u0003\u0002\u0007\u0011Q\u0007\u000b\u0007\u0003\u0003\")\u0001b\u0002\t\u000f\tm(\t1\u0001\u0002B!9!q \"A\u0002\u0005\u0005CCBA&\t\u0017!i\u0001C\u0004\u0003|\u000e\u0003\r!a\u0013\t\u000f\t}8\t1\u0001\u0002LQ1\u0011Q\u000bC\t\t'AqAa?E\u0001\u0004\t)\u0006C\u0004\u0003\u0000\u0012\u0003\r!!\u0016\u0015\r\u0005}Cq\u0003C\r\u0011\u001d\u0011Y0\u0012a\u0001\u0003?BqAa@F\u0001\u0004\ty\u0006\u0006\u0004\u0002j\u0011uAq\u0004\u0005\b\u0005w4\u0005\u0019AA5\u0011\u001d\u0011yP\u0012a\u0001\u0003S*B\u0001b\t\u0005*Q1AQ\u0005C\u001a\tk!B\u0001b\n\u0005,A!\u0011\u0011\u0010C\u0015\t\u001d\tih\u0012b\u0001\u0003\u007fBq!a$H\u0001\b!i\u0003\u0005\u0004\u0002\u0014\u0012=BqE\u0005\u0005\tc\tiKA\u0003Pe\u0012,'\u000fC\u0004\u0003|\u001e\u0003\r\u0001b\n\t\u000f\t}x\t1\u0001\u0005(\u0005\u0019Q.\u0019=\u0015\r\u0005UB1\bC\u001f\u0011\u001d\u0011Y\u0010\u0013a\u0001\u0003kAqAa@I\u0001\u0004\t)\u0004\u0006\u0004\u0002B\u0011\u0005C1\t\u0005\b\u0005wL\u0005\u0019AA!\u0011\u001d\u0011y0\u0013a\u0001\u0003\u0003\"b!a\u0013\u0005H\u0011%\u0003b\u0002B~\u0015\u0002\u0007\u00111\n\u0005\b\u0005\u007fT\u0005\u0019AA&)\u0019\t)\u0006\"\u0014\u0005P!9!1`&A\u0002\u0005U\u0003b\u0002B\u0000\u0017\u0002\u0007\u0011Q\u000b\u000b\u0007\u0003?\"\u0019\u0006\"\u0016\t\u000f\tmH\n1\u0001\u0002`!9!q 'A\u0002\u0005}CCBA5\t3\"Y\u0006C\u0004\u0003|6\u0003\r!!\u001b\t\u000f\t}X\n1\u0001\u0002jU!Aq\fC3)\u0019!\t\u0007b\u001b\u0005nQ!A1\rC4!\u0011\tI\b\"\u001a\u0005\u000f\u0005udJ1\u0001\u0002\u0000!9\u0011q\u0012(A\u0004\u0011%\u0004CBAJ\t_!\u0019\u0007C\u0004\u0003|:\u0003\r\u0001b\u0019\t\u000f\t}h\n1\u0001\u0005d\u000511/[4ok6$B!!\u001b\u0005t!9!1`(A\u0002\u0005%D\u0003BA0\toBqAa?Q\u0001\u0004\ty&\u0006\u0003\u0005|\u0011\u0015E\u0003\u0002C?\t\u000f#B!a\u0013\u0005\u0000!9\u0011qR)A\u0004\u0011\u0005\u0005CBAJ\u0003S#\u0019\t\u0005\u0003\u0002z\u0011\u0015EaBA?#\n\u0007\u0011q\u0010\u0005\b\u0003c\u000b\u0006\u0019\u0001CB\u0003\u0011\u0019\u0018O\u001d;\u0015\t\u0005%DQ\u0012\u0005\b\u0005w\u0014\u0006\u0019AA5+\u0011!\t\nb&\u0015\t\u0011ME\u0011\u0015\u000b\u0005\t+#I\n\u0005\u0003\u0002z\u0011]EaBA?'\n\u0007\u0011q\u0010\u0005\b\u0003\u001f\u001b\u00069\u0001CN!\u0019\ty\u000e\"(\u0005\u0016&!AqTAT\u0005\u0015q%k\\8u\u0011\u001d\t\tl\u0015a\u0001\t+\u000b\u0011!Z\u000b\u0003\u0003S*B\u0001\"+\u0005.R!A1\u0016Cf!\u0011\tI\b\",\u0005\u0017\u0005uT\u000b)A\u0001\u0002\u000b\u0007\u0011q\u0010\u0015\t\t[#\t\fb.\u0005BB!\u00111\u0005CZ\u0013\u0011!),!\n\u0003\u0017M\u0004XmY5bY&TX\rZ\u0019\nG\u0011eF1\u0018C`\t{sA!a\t\u0005<&!AQXA\u0013\u0003\u00151En\\1uc\u001d!\u00131YAc\u0003O\t\u0014b\tCb\t\u000b$I\rb2\u000f\t\u0005\rBQY\u0005\u0005\t\u000f\f)#\u0001\u0004E_V\u0014G.Z\u0019\bI\u0005\r\u0017QYA\u0014\u0011\u001d\ty)\u0016a\u0002\t\u001b\u0004b!a8\u0003b\u0011-\u0016A\u00019j+\u0011!\u0019\u000eb6\u0015\t\u0011UG1\u001d\t\u0005\u0003s\"9\u000eB\u0006\u0002~]\u0003\u000b\u0011!AC\u0002\u0005}\u0004\u0006\u0003Cl\tc#Y\u000eb82\u0013\r\"I\fb/\u0005^\u0012u\u0016g\u0002\u0013\u0002D\u0006\u0015\u0017qE\u0019\nG\u0011\rGQ\u0019Cq\t\u000f\ft\u0001JAb\u0003\u000b\f9\u0003C\u0004\u0002\u0010^\u0003\u001d\u0001\":\u0011\r\u0005}'\u0011\rCk\u0003\r\u0019\u0018N\\\u000b\u0005\tW$\t\u0010\u0006\u0003\u0005n\u0016\u0005A\u0003\u0002Cx\t{\u0004B!!\u001f\u0005r\u0012Y\u0011Q\u0010-!\u0002\u0003\u0005)\u0019AA@Q!!\t\u0010\"-\u0005v\u0012e\u0018'C\u0012\u0005:\u0012mFq\u001fC_c\u001d!\u00131YAc\u0003O\t\u0014b\tCb\t\u000b$Y\u0010b22\u000f\u0011\n\u0019-!2\u0002(!9\u0011q\u0012-A\u0004\u0011}\bCBAp\u0005C\"y\u000fC\u0004\u00022b\u0003\r\u0001b<\u0002\u0007\r|7/\u0006\u0003\u0006\b\u00155A\u0003BC\u0005\u000b;!B!b\u0003\u0006\u001aA!\u0011\u0011PC\u0007\t-\ti(\u0017Q\u0001\u0002\u0003\u0015\r!a )\u0011\u00155A\u0011WC\t\u000b+\t\u0014b\tC]\tw+\u0019\u0002\"02\u000f\u0011\n\u0019-!2\u0002(EJ1\u0005b1\u0005F\u0016]AqY\u0019\bI\u0005\r\u0017QYA\u0014\u0011\u001d\ty)\u0017a\u0002\u000b7\u0001b!a8\u0003b\u0015-\u0001bBAY3\u0002\u0007Q1B\u0001\u0004i\u0006tW\u0003BC\u0012\u000bS!B!\"\n\u0006:Q!QqEC\u001b!\u0011\tI(\"\u000b\u0005\u0017\u0005u$\f)A\u0001\u0002\u000b\u0007\u0011q\u0010\u0015\t\u000bS!\t,\"\f\u00062EJ1\u0005\"/\u0005<\u0016=BQX\u0019\bI\u0005\r\u0017QYA\u0014c%\u0019C1\u0019Cc\u000bg!9-M\u0004%\u0003\u0007\f)-a\n\t\u000f\u0005=%\fq\u0001\u00068A1\u0011q\u001cB1\u000bOAq!!-[\u0001\u0004)9#\u0001\u0003bg&tW\u0003BC \u000b\u000b\"B!\"\u0011\u0006VQ!Q1IC)!\u0011\tI(\"\u0012\u0005\u0017\u0005u4\f)A\u0001\u0002\u000b\u0007\u0011q\u0010\u0015\t\u000b\u000b\"\t,\"\u0013\u0006NEJ1\u0005\"/\u0005<\u0016-CQX\u0019\bI\u0005\r\u0017QYA\u0014c%\u0019C1\u0019Cc\u000b\u001f\"9-M\u0004%\u0003\u0007\f)-a\n\t\u000f\u0005=5\fq\u0001\u0006TA1\u0011q\u001cB1\u000b\u0007Bq!!-\\\u0001\u0004)\u0019%\u0001\u0003bG>\u001cX\u0003BC.\u000bC\"B!\"\u0018\u0006rQ!QqLC7!\u0011\tI(\"\u0019\u0005\u0017\u0005uD\f)A\u0001\u0002\u000b\u0007\u0011q\u0010\u0015\t\u000bC\"\t,\"\u001a\u0006jEJ1\u0005\"/\u0005<\u0016\u001dDQX\u0019\bI\u0005\r\u0017QYA\u0014c%\u0019C1\u0019Cc\u000bW\"9-M\u0004%\u0003\u0007\f)-a\n\t\u000f\u0005=E\fq\u0001\u0006pA1\u0011q\u001cB1\u000b?Bq!!-]\u0001\u0004)y&\u0001\u0003bi\u0006tW\u0003BC<\u000b{\"B!\"\u001f\u0006\u000eR!Q1PCE!\u0011\tI(\" \u0005\u0017\u0005uT\f)A\u0001\u0002\u000b\u0007\u0011q\u0010\u0015\t\u000b{\"\t,\"!\u0006\u0006FJ1\u0005\"/\u0005<\u0016\rEQX\u0019\bI\u0005\r\u0017QYA\u0014c%\u0019C1\u0019Cc\u000b\u000f#9-M\u0004%\u0003\u0007\f)-a\n\t\u000f\u0005=U\fq\u0001\u0006\fB1\u0011q\u001cB1\u000bwBq!!-^\u0001\u0004)Y(A\u0003bi\u0006t''\u0006\u0003\u0006\u0014\u0016eECBCK\u000bS+Y\u000b\u0006\u0003\u0006\u0018\u0016\u0015\u0006\u0003BA=\u000b3#1\"! _A\u0003\u0005\tQ1\u0001\u0002\u0000!BQ\u0011\u0014CY\u000b;+\t+M\u0005$\ts#Y,b(\u0005>F:A%a1\u0002F\u0006\u001d\u0012'C\u0012\u0005D\u0012\u0015W1\u0015Cdc\u001d!\u00131YAc\u0003OAq!a$_\u0001\b)9\u000b\u0005\u0004\u0002`\n\u0005Tq\u0013\u0005\b\u0005\u007ft\u0006\u0019ACL\u0011\u001d\u0011YP\u0018a\u0001\u000b/\u000bAa]5oQV!Q\u0011WC\\)\u0011)\u0019,b2\u0015\t\u0015UV1\u0019\t\u0005\u0003s*9\fB\u0006\u0002~}\u0003\u000b\u0011!AC\u0002\u0005}\u0004\u0006CC\\\tc+Y,b02\u0013\r\"I\fb/\u0006>\u0012u\u0016g\u0002\u0013\u0002D\u0006\u0015\u0017qE\u0019\nG\u0011\rGQYCa\t\u000f\ft\u0001JAb\u0003\u000b\f9\u0003C\u0004\u0002\u0010~\u0003\u001d!\"2\u0011\r\u0005}'\u0011MC[\u0011\u001d\u0011Yp\u0018a\u0001\u000bk\u000bAaY8tQV!QQZCj)\u0011)y-b9\u0015\t\u0015EWq\u001c\t\u0005\u0003s*\u0019\u000eB\u0006\u0002~\u0001\u0004\u000b\u0011!AC\u0002\u0005}\u0004\u0006CCj\tc+9.b72\u0013\r\"I\fb/\u0006Z\u0012u\u0016g\u0002\u0013\u0002D\u0006\u0015\u0017qE\u0019\nG\u0011\rGQYCo\t\u000f\ft\u0001JAb\u0003\u000b\f9\u0003C\u0004\u0002\u0010\u0002\u0004\u001d!\"9\u0011\r\u0005}'\u0011MCi\u0011\u001d\u0011Y\u0010\u0019a\u0001\u000b#\fA\u0001^1oQV!Q\u0011^Cx)\u0011)Y/b@\u0015\t\u00155X1 \t\u0005\u0003s*y\u000fB\u0006\u0002~\u0005\u0004\u000b\u0011!AC\u0002\u0005}\u0004\u0006CCx\tc+\u00190b>2\u0013\r\"I\fb/\u0006v\u0012u\u0016g\u0002\u0013\u0002D\u0006\u0015\u0017qE\u0019\nG\u0011\rGQYC}\t\u000f\ft\u0001JAb\u0003\u000b\f9\u0003C\u0004\u0002\u0010\u0006\u0004\u001d!\"@\u0011\r\u0005}'\u0011MCw\u0011\u001d\u0011Y0\u0019a\u0001\u000b[\fAa\u00192siR!\u0011\u0011\u000eD\u0003\u0011\u001d\u0011YP\u0019a\u0001\u0003S\n\u0001bY8qsNKwM\u001c\u000b\u0007\u0003S2YAb\u0004\t\u000f\u001951\r1\u0001\u0002j\u0005\tQ\u000eC\u0004\u0007\u0012\r\u0004\r!!\u001b\u0002\u0003M$b!a\u0018\u0007\u0016\u0019]\u0001b\u0002D\u0007I\u0002\u0007\u0011q\f\u0005\b\r#!\u0007\u0019AA0)\u0011\tIGb\u0007\t\u000f\tmX\r1\u0001\u0002j\u0005)Q\r\u001f9ncQ!\u0011\u0011\u000eD\u0011\u0011\u001d\u0011YP\u001aa\u0001\u0003S\n1bZ3u\u000bb\u0004xN\\3oiR!\u00111\nD\u0014\u0011\u001d\u0011Yp\u001aa\u0001\u0003S\"B!a\u0013\u0007,!9!1 5A\u0002\u0005}\u0013!D%F\u000b\u0016\u0013X-\\1j]\u0012,'\u000f\u0006\u0004\u0002j\u0019Eb1\u0007\u0005\b\u0005wL\u0007\u0019AA5\u0011\u001d1)$\u001ba\u0001\u0003S\n\u0011\u0001Z\u0001\u0006Y><\u0017\u0007\r\u000b\u0005\u0003S2Y\u0004C\u0004\u0003|*\u0004\r!!\u001b\u0002\u000b1|w-\r9\u0015\t\u0005%d\u0011\t\u0005\b\u0005w\\\u0007\u0019AA5\u0003%qW\r\u001f;BMR,'\u000f\u0006\u0004\u0002j\u0019\u001dc\u0011\n\u0005\b\u0005wd\u0007\u0019AA5\u0011\u001d\u0011y\u0010\u001ca\u0001\u0003S\"b!a\u0018\u0007N\u0019=\u0003b\u0002B~[\u0002\u0007\u0011q\f\u0005\b\u0005\u007fl\u0007\u0019AA0\u0003\u0019qW\r\u001f;VaR!\u0011\u0011\u000eD+\u0011\u001d\u0011YP\u001ca\u0001\u0003S\"B!a\u0018\u0007Z!9!1`8A\u0002\u0005}\u0013A\u0002:b]\u0012|W.\u0001\u0003sS:$H\u0003BA5\rCBqAa?r\u0001\u0004\tI'A\u0003tG\u0006d'\r\u0006\u0004\u0002j\u0019\u001dd\u0011\u000e\u0005\b\rk\u0011\b\u0019AA5\u0011\u001d1\tB\u001da\u0001\u0003\u0017\"b!a\u0018\u0007n\u0019=\u0004b\u0002D\u001bg\u0002\u0007\u0011q\f\u0005\b\r#\u0019\b\u0019AA&\u0003%!x\u000eR3he\u0016,7\u000f\u0006\u0003\u0002j\u0019U\u0004bBAYi\u0002\u0007\u0011\u0011N\u0001\ni>\u0014\u0016\rZ5b]N$B!!\u001b\u0007|!9\u0011\u0011W;A\u0002\u0005%\u0014aA;maR!\u0011\u0011\u000eDA\u0011\u001d\u0011YP\u001ea\u0001\u0003S\"B!!\u001b\u0007\u0006\"9!1`<A\u0002\u0005}\u0013!\u00025za>$X\u0003\u0002DF\r##bA\"$\u00070\u001aEFC\u0003DH\r;3\tK\"*\u0007,B!\u0011\u0011\u0010DI\t-\ti\b\u001fQ\u0001\u0002\u0003\u0015\r!a )\u0011\u0019EE\u0011\u0017DK\r3\u000b\u0014b\tC]\tw39\n\"02\u000f\u0011\n\u0019-!2\u0002(EJ1\u0005b1\u0005F\u001amEqY\u0019\bI\u0005\r\u0017QYA\u0014\u0011\u001d\u0011Y\n\u001fa\u0002\r?\u0003b!a%\u0003 \u001a=\u0005bBA\u001fq\u0002\u000fa1\u0015\t\u0007\u0003?$iJb$\t\u000f\u0019\u001d\u0006\u0010q\u0001\u0007*\u0006\tq\u000e\u0005\u0004\u0002\u0014\u0012=bq\u0012\u0005\b\r#A\b9\u0001DW!\u0019\t\u0019*!+\u0007\u0010\"9!1 =A\u0002\u0019=\u0005b\u0002B\u0000q\u0002\u0007aqR\u0001\nS:$8+Z1sG\"$B!a\u0013\u00078\"9!1T=A\u0002\u0019e\u0006\u0003CA\u0012\rw\u000bYEb0\n\t\u0019u\u0016Q\u0005\u0002\n\rVt7\r^5p]F\u0002B!a\t\u0007B&!a1YA\u0013\u0005\u001d\u0011un\u001c7fC:\fa\u0001Z3d\t&4H\u0003\u0003De\r34YN\"8\u0011\r\u0019-gQ[Av\u001b\t1iM\u0003\u0003\u0007P\u001aE\u0017!C5n[V$\u0018M\u00197f\u0015\u00111\u0019.!\n\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0007X\u001a5'\u0001\u0003'bufd\u0015n\u001d;\t\u000f\tm(\u00101\u0001\u0002l\"9!q >A\u0002\u0005-\bb\u0002Dpu\u0002\u0007\u00111J\u0001\u0002e\u0006AA-[4ji&TX\r\u0006\u0005\u0007f\u001a-hQ\u001eDx!\u0019\t\tMb:\u0002L%!a\u0011^Ag\u0005\u0011a\u0015n\u001d;\t\u000f\tm8\u00101\u0001\u0002l\"9aq\\>A\u0002\u0005-\u0003\"\u0003DywB\u0005\t\u0019\u0001Ds\u0003\u0011\u0001(/\u001a<\u0002%\u0011Lw-\u001b;ju\u0016$C-\u001a4bk2$HeM\u000b\u0003\roTCA\":\u0007z.\u0012a1 \t\u0005\r{<9!\u0004\u0002\u0007\u0000*!q\u0011AD\u0002\u0003%)hn\u00195fG.,GM\u0003\u0003\b\u0006\u0005\u0015\u0012AC1o]>$\u0018\r^5p]&!q\u0011\u0002D\u0000\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000bk:$\u0017nZ5uSj,GCBAv\u000f\u001f9)\u0002C\u0004\b\u0012u\u0004\rab\u0005\u0002\r\u0011Lw-\u001b;t!\u0019\t\tma\u0007\u0002L!9aq\\?A\u0002\u0005-\u0013!\u0002:bI&DXCAA&\u0003\u0019\u0011\u0018\rZ5yA\u0005)aN]8piRA\u0011qXD\u0011\u000fG9)\u0003\u0003\u0005\u00022\u0006\u0005\u0001\u0019AA`\u0011!\t)0!\u0001A\u0002\u0005-\u0003\u0002CD\u0014\u0003\u0003\u0001\ra\"\u000b\u0002\t\r$\b\u0010\u001e\t\u0005\u0007?;Y#\u0003\u0003\b.\r\u0005&aC'bi\"\u001cuN\u001c;fqR\f\u0011\"\u00198z\u0013NTVM]8\u0015\t\u0019}v1\u0007\u0005\t\u0003{\t\u0019\u00011\u0001\u0002\b\u0006Y\u0011M\\=U_\u0012{WO\u00197f)\u0011\tIg\"\u000f\t\u0011\u0005u\u0012Q\u0001a\u0001\u0003\u000f\u000b\u0011\"\u00198z)>duN\\4\u0015\t\u0005Usq\b\u0005\t\u0003{\t9\u00011\u0001\u0002\b\u0006Q\u0011M\\=Jg^Cw\u000e\\3\u0015\t\u0019}vQ\t\u0005\t\u0003{\tI\u00011\u0001\u0002\b\u0006i\u0011M\\=JgZ\u000bG.\u001b3J]R$BAb0\bL!A\u0011QHA\u0006\u0001\u0004\t9\t"
)
public final class package {
   public static BigDecimal nroot(final BigDecimal a, final int k, final MathContext ctxt) {
      return package$.MODULE$.nroot(a, k, ctxt);
   }

   public static Object hypot(final Object x, final Object y, final Field f, final NRoot n, final Order o, final Signed s) {
      return package$.MODULE$.hypot(x, y, f, n, o, s);
   }

   public static double ulp(final float x) {
      return package$.MODULE$.ulp(x);
   }

   public static double ulp(final double x) {
      return package$.MODULE$.ulp(x);
   }

   public static double toRadians(final double a) {
      return package$.MODULE$.toRadians(a);
   }

   public static double toDegrees(final double a) {
      return package$.MODULE$.toDegrees(a);
   }

   public static float scalb(final float d, final int s) {
      return package$.MODULE$.scalb(d, s);
   }

   public static double scalb(final double d, final int s) {
      return package$.MODULE$.scalb(d, s);
   }

   public static double rint(final double x) {
      return package$.MODULE$.rint(x);
   }

   public static double random() {
      return package$.MODULE$.random();
   }

   public static float nextUp(final float x) {
      return package$.MODULE$.nextUp(x);
   }

   public static double nextUp(final double x) {
      return package$.MODULE$.nextUp(x);
   }

   public static float nextAfter(final float x, final float y) {
      return package$.MODULE$.nextAfter(x, y);
   }

   public static double nextAfter(final double x, final double y) {
      return package$.MODULE$.nextAfter(x, y);
   }

   public static double log1p(final double x) {
      return package$.MODULE$.log1p(x);
   }

   public static double log10(final double x) {
      return package$.MODULE$.log10(x);
   }

   public static double IEEEremainder(final double x, final double d) {
      return package$.MODULE$.IEEEremainder(x, d);
   }

   public static int getExponent(final float x) {
      return package$.MODULE$.getExponent(x);
   }

   public static int getExponent(final double x) {
      return package$.MODULE$.getExponent(x);
   }

   public static double expm1(final double x) {
      return package$.MODULE$.expm1(x);
   }

   public static double cosh(final double x) {
      return package$.MODULE$.cosh(x);
   }

   public static float copySign(final float m, final float s) {
      return package$.MODULE$.copySign(m, s);
   }

   public static double copySign(final double m, final double s) {
      return package$.MODULE$.copySign(m, s);
   }

   public static double cbrt(final double x) {
      return package$.MODULE$.cbrt(x);
   }

   public static Object tanh(final Object x, final Trig ev) {
      return package$.MODULE$.tanh(x, ev);
   }

   public static Object cosh(final Object x, final Trig ev) {
      return package$.MODULE$.cosh(x, ev);
   }

   public static Object sinh(final Object x, final Trig ev) {
      return package$.MODULE$.sinh(x, ev);
   }

   public static Object atan2(final Object y, final Object x, final Trig ev) {
      return package$.MODULE$.atan2(y, x, ev);
   }

   public static Object atan(final Object a, final Trig ev) {
      return package$.MODULE$.atan(a, ev);
   }

   public static Object acos(final Object a, final Trig ev) {
      return package$.MODULE$.acos(a, ev);
   }

   public static Object asin(final Object a, final Trig ev) {
      return package$.MODULE$.asin(a, ev);
   }

   public static Object tan(final Object a, final Trig ev) {
      return package$.MODULE$.tan(a, ev);
   }

   public static Object cos(final Object a, final Trig ev) {
      return package$.MODULE$.cos(a, ev);
   }

   public static Object sin(final Object a, final Trig ev) {
      return package$.MODULE$.sin(a, ev);
   }

   public static Object pi(final Trig ev) {
      return package$.MODULE$.pi(ev);
   }

   public static double pi() {
      return package$.MODULE$.pi();
   }

   public static Object e(final Trig ev) {
      return package$.MODULE$.e(ev);
   }

   public static double e() {
      return package$.MODULE$.e();
   }

   public static Object sqrt(final Object a, final NRoot ev) {
      return package$.MODULE$.sqrt(a, ev);
   }

   public static double sqrt(final double x) {
      return package$.MODULE$.sqrt(x);
   }

   public static int signum(final Object a, final Signed ev) {
      return package$.MODULE$.signum(a, ev);
   }

   public static float signum(final float x) {
      return package$.MODULE$.signum(x);
   }

   public static double signum(final double x) {
      return package$.MODULE$.signum(x);
   }

   public static Object max(final Object x, final Object y, final Order ev) {
      return package$.MODULE$.max(x, y, ev);
   }

   public static double max(final double x, final double y) {
      return package$.MODULE$.max(x, y);
   }

   public static float max(final float x, final float y) {
      return package$.MODULE$.max(x, y);
   }

   public static long max(final long x, final long y) {
      return package$.MODULE$.max(x, y);
   }

   public static int max(final int x, final int y) {
      return package$.MODULE$.max(x, y);
   }

   public static short max(final short x, final short y) {
      return package$.MODULE$.max(x, y);
   }

   public static byte max(final byte x, final byte y) {
      return package$.MODULE$.max(x, y);
   }

   public static Object min(final Object x, final Object y, final Order ev) {
      return package$.MODULE$.min(x, y, ev);
   }

   public static double min(final double x, final double y) {
      return package$.MODULE$.min(x, y);
   }

   public static float min(final float x, final float y) {
      return package$.MODULE$.min(x, y);
   }

   public static long min(final long x, final long y) {
      return package$.MODULE$.min(x, y);
   }

   public static int min(final int x, final int y) {
      return package$.MODULE$.min(x, y);
   }

   public static short min(final short x, final short y) {
      return package$.MODULE$.min(x, y);
   }

   public static byte min(final byte x, final byte y) {
      return package$.MODULE$.min(x, y);
   }

   public static BigInteger emod(final BigInteger a, final BigInteger b) {
      return package$.MODULE$.emod(a, b);
   }

   public static BigInt emod(final BigInt a, final BigInt b) {
      return package$.MODULE$.emod(a, b);
   }

   public static long emod(final long a, final long b) {
      return package$.MODULE$.emod(a, b);
   }

   public static int emod(final int a, final int b) {
      return package$.MODULE$.emod(a, b);
   }

   public static short emod(final short a, final short b) {
      return package$.MODULE$.emod(a, b);
   }

   public static byte emod(final byte a, final byte b) {
      return package$.MODULE$.emod(a, b);
   }

   public static BigInteger equot(final BigInteger a, final BigInteger b) {
      return package$.MODULE$.equot(a, b);
   }

   public static BigInt equot(final BigInt a, final BigInt b) {
      return package$.MODULE$.equot(a, b);
   }

   public static long equot(final long a, final long b) {
      return package$.MODULE$.equot(a, b);
   }

   public static int equot(final int a, final int b) {
      return package$.MODULE$.equot(a, b);
   }

   public static short equot(final short a, final short b) {
      return package$.MODULE$.equot(a, b);
   }

   public static byte equot(final byte a, final byte b) {
      return package$.MODULE$.equot(a, b);
   }

   public static Tuple2 equotmod(final BigInteger a, final BigInteger b) {
      return package$.MODULE$.equotmod(a, b);
   }

   public static Tuple2 equotmod(final BigInt a, final BigInt b) {
      return package$.MODULE$.equotmod(a, b);
   }

   public static Tuple2 equotmod(final long a, final long b) {
      return package$.MODULE$.equotmod(a, b);
   }

   public static Tuple2 equotmod(final int a, final int b) {
      return package$.MODULE$.equotmod(a, b);
   }

   public static Tuple2 equotmod(final short a, final short b) {
      return package$.MODULE$.equotmod(a, b);
   }

   public static Tuple2 equotmod(final byte a, final byte b) {
      return package$.MODULE$.equotmod(a, b);
   }

   public static Object lcm(final Object x, final Object y, final Eq evidence$4, final GCDRing ev) {
      return package$.MODULE$.lcm(x, y, evidence$4, ev);
   }

   public static BigInt lcm(final BigInt a, final BigInt b) {
      return package$.MODULE$.lcm(a, b);
   }

   public static long lcm(final long x, final long y) {
      return package$.MODULE$.lcm(x, y);
   }

   public static Object gcd(final Object x, final Object y, final Object z, final Seq rest, final Eq evidence$3, final GCDRing ev) {
      return package$.MODULE$.gcd(x, y, z, rest, evidence$3, ev);
   }

   public static Object gcd(final Seq xs, final Eq evidence$2, final GCDRing ev) {
      return package$.MODULE$.gcd(xs, evidence$2, ev);
   }

   public static Object gcd(final Object x, final Object y, final Eq evidence$1, final GCDRing ev) {
      return package$.MODULE$.gcd(x, y, evidence$1, ev);
   }

   public static BigInt gcd(final BigInt a, final BigInt b) {
      return package$.MODULE$.gcd(a, b);
   }

   public static long gcd(final long _x, final long _y) {
      return package$.MODULE$.gcd(_x, _y);
   }

   public static double pow(final double base, final double exponent) {
      return package$.MODULE$.pow(base, exponent);
   }

   public static long pow(final long base, final long exponent) {
      return package$.MODULE$.pow(base, exponent);
   }

   public static BigInt pow(final BigInt base, final BigInt ex) {
      return package$.MODULE$.pow(base, ex);
   }

   public static BigDecimal pow(final BigDecimal base, final BigDecimal exponent) {
      return package$.MODULE$.pow(base, exponent);
   }

   public static Object log(final Object a, final int base, final Field f, final Trig t) {
      return package$.MODULE$.log(a, base, f, t);
   }

   public static Object log(final Object a, final Trig t) {
      return package$.MODULE$.log(a, t);
   }

   public static BigDecimal log(final BigDecimal n, final int base) {
      return package$.MODULE$.log(n, base);
   }

   public static BigDecimal log(final BigDecimal n) {
      return package$.MODULE$.log(n);
   }

   public static double log(final double n, final int base) {
      return package$.MODULE$.log(n, base);
   }

   public static double log(final double n) {
      return package$.MODULE$.log(n);
   }

   public static Object exp(final Object a, final Trig t) {
      return package$.MODULE$.exp(a, t);
   }

   public static BigDecimal exp(final BigDecimal k) {
      return package$.MODULE$.exp(k);
   }

   public static BigDecimal exp(final int k, final int precision) {
      return package$.MODULE$.exp(k, precision);
   }

   public static double exp(final double n) {
      return package$.MODULE$.exp(n);
   }

   public static Object round(final Object a, final IsReal ev) {
      return package$.MODULE$.round(a, ev);
   }

   public static BigDecimal round(final BigDecimal a) {
      return package$.MODULE$.round(a);
   }

   public static double round(final double a) {
      return package$.MODULE$.round(a);
   }

   public static float round(final float a) {
      return package$.MODULE$.round(a);
   }

   public static Object floor(final Object a, final IsReal ev) {
      return package$.MODULE$.floor(a, ev);
   }

   public static BigDecimal floor(final BigDecimal n) {
      return package$.MODULE$.floor(n);
   }

   public static double floor(final double n) {
      return package$.MODULE$.floor(n);
   }

   public static float floor(final float n) {
      return package$.MODULE$.floor(n);
   }

   public static BigInt fib(final long n) {
      return package$.MODULE$.fib(n);
   }

   public static BigInt fact(final long n) {
      return package$.MODULE$.fact(n);
   }

   public static BigInt choose(final long n, final long k) {
      return package$.MODULE$.choose(n, k);
   }

   public static Object ceil(final Object a, final IsReal ev) {
      return package$.MODULE$.ceil(a, ev);
   }

   public static BigDecimal ceil(final BigDecimal n) {
      return package$.MODULE$.ceil(n);
   }

   public static double ceil(final double n) {
      return package$.MODULE$.ceil(n);
   }

   public static float ceil(final float n) {
      return package$.MODULE$.ceil(n);
   }

   public static Object abs(final Object a, final Signed ev) {
      return package$.MODULE$.abs(a, ev);
   }

   public static double abs(final double n) {
      return package$.MODULE$.abs(n);
   }

   public static float abs(final float n) {
      return package$.MODULE$.abs(n);
   }

   public static long abs(final long n) {
      return package$.MODULE$.abs(n);
   }

   public static int abs(final int n) {
      return package$.MODULE$.abs(n);
   }

   public static short abs(final short n) {
      return package$.MODULE$.abs(n);
   }

   public static byte abs(final byte n) {
      return package$.MODULE$.abs(n);
   }
}
