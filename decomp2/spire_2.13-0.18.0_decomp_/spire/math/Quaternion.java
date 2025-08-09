package spire.math;

import algebra.ring.CommutativeRing;
import algebra.ring.Field;
import algebra.ring.Signed;
import cats.kernel.Eq;
import cats.kernel.Order;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.math.ScalaNumber;
import scala.math.ScalaNumericAnyConversions;
import scala.math.ScalaNumericConversions;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import spire.algebra.NRoot;
import spire.algebra.Trig;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015\u001dr!\u0002+V\u0011\u0003Qf!\u0002/V\u0011\u0003i\u0006\"B8\u0002\t\u0003\u0001\b\"B9\u0002\t\u0003\u0011\bbBA+\u0003\u0011\u0005Aq\b\u0005\b\u00033\nA\u0011\u0001C,\u0011\u001d!y'\u0001C\u0001\tcBq\u0001\"#\u0002\t\u0003!Y\tC\u0004\u0005$\u0006!\t\u0001\"*\t\u000f\u0011\r\u0016\u0001\"\u0001\u0005D\"IA1U\u0001\u0002\u0002\u0013\u0005E1\u001d\u0005\n\t\u007f\f\u0011\u0011!CA\u000b\u0003A\u0011\"\"\n\u0002\u0003\u0003%I!!-\u0007\tq+&)\u001e\u0005\u000b\u00037i!Q3A\u0005\u0002\u0005u\u0001BCA)\u001b\tE\t\u0015!\u0003\u0002 !I\u0011/\u0004BK\u0002\u0013\u0005\u0011Q\u0004\u0005\u000b\u0003'j!\u0011#Q\u0001\n\u0005}\u0001BCA+\u001b\tU\r\u0011\"\u0001\u0002\u001e!Q\u0011qK\u0007\u0003\u0012\u0003\u0006I!a\b\t\u0015\u0005eSB!f\u0001\n\u0003\ti\u0002\u0003\u0006\u0002\\5\u0011\t\u0012)A\u0005\u0003?Aaa\\\u0007\u0005\u0002\u0005u\u0003bBA5\u001b\u0011\u0005\u00131\u000e\u0005\b\u0003gjA\u0011IA;\u0011\u001d\ti(\u0004C\u0001\u0003\u007fBq!a\"\u000e\t\u0003\nI\tC\u0004\u0002\u00126!\t!a%\t\u000f\u0005mU\u0002\"\u0001\u0002\u001e\"A\u0011QU\u0007!\n\u0013\t9\u000bC\u0004\u000206!\t!!-\t\u000f\u0005}V\u0002\"\u0001\u0002(\"9\u0011\u0011Y\u0007\u0005F\u0005\u001d\u0006bBAb\u001b\u0011\u0005\u0013Q\u0019\u0005\b\u0003\u000flA\u0011IAe\u0011\u001d\ty-\u0004C\u0001\u0003#Dq!a8\u000e\t\u0003\t\t\u000fC\u0004\u0002p6!\t!!=\t\u000f\t=Q\u0002\"\u0001\u0003\u0012!9!QC\u0007\u0005\u0002\t]\u0001b\u0002B\u000e\u001b\u0011\u0005!Q\u0004\u0005\b\u0005OiA\u0011\u0001B\u0015\u0011\u001d\u0011i#\u0004C\u0001\u0005_AqAa\u0012\u000e\t\u0003\u0011I\u0005C\u0004\u0003P5!\tA!\u0015\t\u000f\t\rT\u0002\"\u0001\u0003f!9!QN\u0007\u0005B\t=\u0004b\u0002BA\u001b\u0011\u0005!1\u0011\u0005\b\u0005\u0017kA\u0011\u0001BG\u0011\u001d\u0011\t*\u0004C\u0001\u0005'CqAa'\u000e\t\u0003\u0011i\nC\u0004\u0003&6!\tAa*\t\u000f\t-V\u0002\"\u0001\u0003.\"9!\u0011W\u0007\u0005\u0002\tM\u0006b\u0002B\\\u001b\u0011\u0005!\u0011\u0018\u0005\b\u0005\u0007lA\u0011\u0001Bc\u0011\u001d\u0011I/\u0004C\u0001\u0005WDqA!=\u000e\t\u0003\u0011\u0019\u0010C\u0004\u0003r6!\tAa?\t\u000f\tEX\u0002\"\u0001\u0004\u0004!911B\u0007\u0005\u0002\r5\u0001bBB\u0006\u001b\u0011\u00051Q\u0003\u0005\b\u0007\u0017iA\u0011AB\u000f\u0011\u001d\u0019)#\u0004C\u0001\u0007OAqa!\n\u000e\t\u0003\u0019y\u0003C\u0004\u0004&5!\taa\u000e\t\u000f\r}R\u0002\"\u0001\u0004B!91qH\u0007\u0005\u0002\r%\u0003bBB \u001b\u0011\u00051\u0011\u000b\u0005\b\u00073jA\u0011AB.\u0011\u001d\u0019\u0019'\u0004C\u0001\u0007KBqa!\u001c\u000e\t\u0003\u0019y\u0007C\u0004\u0004\u00026!\taa!\t\u0013\r-U\"!A\u0005\u0002\r5\u0005\"CBU\u001bE\u0005I\u0011ABV\u0011%\u0019y-DI\u0001\n\u0003\u0019\t\u000eC\u0005\u0004`6\t\n\u0011\"\u0001\u0004b\"I1q^\u0007\u0012\u0002\u0013\u00051\u0011\u001f\u0005\n\u0007\u007fl\u0011\u0011!C!\t\u0003A\u0011\u0002b\u0002\u000e\u0003\u0003%\t!a \t\u0013\u0011%Q\"!A\u0005\u0002\u0011-\u0001\"\u0003C\t\u001b\u0005\u0005I\u0011\tC\n\u0011%!\t#DA\u0001\n\u0003!\u0019\u0003C\u0005\u0005(5\t\t\u0011\"\u0011\u0005*\u0005Q\u0011+^1uKJt\u0017n\u001c8\u000b\u0005Y;\u0016\u0001B7bi\"T\u0011\u0001W\u0001\u0006gBL'/Z\u0002\u0001!\tY\u0016!D\u0001V\u0005)\tV/\u0019;fe:LwN\\\n\u0005\u0003y#w\r\u0005\u0002`E6\t\u0001MC\u0001b\u0003\u0015\u00198-\u00197b\u0013\t\u0019\u0007M\u0001\u0004B]f\u0014VM\u001a\t\u00037\u0016L!AZ+\u0003'E+\u0018\r^3s]&|g.\u00138ti\u0006t7-Z:\u0011\u0005!lW\"A5\u000b\u0005)\\\u0017AA5p\u0015\u0005a\u0017\u0001\u00026bm\u0006L!A\\5\u0003\u0019M+'/[1mSj\f'\r\\3\u0002\rqJg.\u001b;?)\u0005Q\u0016!A5\u0016\u0007M$y\u0003F\u0002u\tw\u0001BaW\u0007\u0005.U\u0019a/a\t\u0014\r59Hp`A\u000b!\tA(0D\u0001z\u0015\t1\u0006-\u0003\u0002|s\nY1kY1mC:+XNY3s!\tAX0\u0003\u0002\u007fs\n92kY1mC:+X.\u001a:jG\u000e{gN^3sg&|gn\u001d\t\u0005\u0003\u0003\t\tB\u0004\u0003\u0002\u0004\u00055a\u0002BA\u0003\u0003\u0017i!!a\u0002\u000b\u0007\u0005%\u0011,\u0001\u0004=e>|GOP\u0005\u0002C&\u0019\u0011q\u00021\u0002\u000fA\f7m[1hK&\u0019a.a\u0005\u000b\u0007\u0005=\u0001\rE\u0002`\u0003/I1!!\u0007a\u0005\u001d\u0001&o\u001c3vGR\f\u0011A]\u000b\u0003\u0003?\u0001B!!\t\u0002$1\u0001AaCA\u0013\u001b\u0001\u0006\t\u0011!b\u0001\u0003O\u0011\u0011!Q\t\u0005\u0003S\ty\u0003E\u0002`\u0003WI1!!\fa\u0005\u001dqu\u000e\u001e5j]\u001e\u00042aXA\u0019\u0013\r\t\u0019\u0004\u0019\u0002\u0004\u0003:L\b\u0006CA\u0012\u0003o\ti$a\u0012\u0011\u0007}\u000bI$C\u0002\u0002<\u0001\u00141b\u001d9fG&\fG.\u001b>fIFJ1%a\u0010\u0002B\u0005\u0015\u00131\t\b\u0004?\u0006\u0005\u0013bAA\"A\u0006)a\t\\8biF2A%a\u0001\u0002\f\u0005\f\u0014bIA%\u0003\u0017\ny%!\u0014\u000f\u0007}\u000bY%C\u0002\u0002N\u0001\fa\u0001R8vE2,\u0017G\u0002\u0013\u0002\u0004\u0005-\u0011-\u0001\u0002sA\u0005\u0011\u0011\u000eI\u0001\u0002U\u0006\u0011!\u000eI\u0001\u0002W\u0006\u00111\u000e\t\u000b\u000b\u0003?\n\t'a\u0019\u0002f\u0005\u001d\u0004\u0003B.\u000e\u0003?Aq!a\u0007\u0017\u0001\u0004\ty\u0002\u0003\u0004r-\u0001\u0007\u0011q\u0004\u0005\b\u0003+2\u0002\u0019AA\u0010\u0011\u001d\tIF\u0006a\u0001\u0003?\t\u0011BY=uKZ\u000bG.^3\u0016\u0005\u00055\u0004cA0\u0002p%\u0019\u0011\u0011\u000f1\u0003\t\tKH/Z\u0001\u000bg\"|'\u000f\u001e,bYV,WCAA<!\ry\u0016\u0011P\u0005\u0004\u0003w\u0002'!B*i_J$\u0018\u0001C5oiZ\u000bG.^3\u0016\u0005\u0005\u0005\u0005cA0\u0002\u0004&\u0019\u0011Q\u00111\u0003\u0007%sG/A\u0005m_:<g+\u00197vKV\u0011\u00111\u0012\t\u0004?\u00065\u0015bAAHA\n!Aj\u001c8h\u0003)1Gn\\1u-\u0006dW/Z\u000b\u0003\u0003+\u00032aXAL\u0013\r\tI\n\u0019\u0002\u0006\r2|\u0017\r^\u0001\fI>,(\r\\3WC2,X-\u0006\u0002\u0002 B\u0019q,!)\n\u0007\u0005\r\u0006M\u0001\u0004E_V\u0014G.Z\u0001\fg&dG._%t%\u0016\fG.\u0006\u0002\u0002*B\u0019q,a+\n\u0007\u00055\u0006MA\u0004C_>dW-\u00198\u0002\u0015UtG-\u001a:ms&tw\r\u0006\u0002\u00024B!\u0011QWA^\u001b\t\t9LC\u0002\u0002:.\fA\u0001\\1oO&!\u0011QXA\\\u0005\u0019y%M[3di\u00069\u0011n],i_2,\u0017AC5t-\u0006d\u0017\u000eZ%oi\u0006A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002\u0002\u00061Q-];bYN$B!!+\u0002L\"9\u0011Q\u001a\u0012A\u0002\u0005=\u0012\u0001\u0002;iCR\f\u0011\u0002J3rI\u0015\fH%Z9\u0015\t\u0005%\u00161\u001b\u0005\b\u0003\u001b\u001c\u0003\u0019AAka\u0011\t9.a7\u0011\tmk\u0011\u0011\u001c\t\u0005\u0003C\tY\u000e\u0002\u0007\u0002^\u0006M\u0017\u0011!A\u0001\u0006\u0003\t9CA\u0002`IE\n1\u0002J3rI\t\fgn\u001a\u0013fcR!\u0011\u0011VAr\u0011\u001d\ti\r\na\u0001\u0003K\u0004D!a:\u0002lB!1,DAu!\u0011\t\t#a;\u0005\u0019\u00055\u00181]A\u0001\u0002\u0003\u0015\t!a\n\u0003\u0007}##'\u0001\u0004jgj+'o\u001c\u000b\u0005\u0003S\u000b\u0019\u0010C\u0004\u0002v\u0016\u0002\u001d!a>\u0002\u0003M\u0004b!!?\u0003\n\u0005}a\u0002BA~\u0005\u000bqA!!@\u0003\u00029!\u0011QAA\u0000\u0013\u0005A\u0016b\u0001B\u0002/\u00069\u0011\r\\4fEJ\f\u0017\u0002BA\b\u0005\u000fQ1Aa\u0001X\u0013\u0011\u0011YA!\u0004\u0003\rMKwM\\3e\u0015\u0011\tyAa\u0002\u0002\r%\u001c(+Z1m)\u0011\tIKa\u0005\t\u000f\u0005Uh\u0005q\u0001\u0002x\u00061\u0011n\u001d)ve\u0016$B!!+\u0003\u001a!9\u0011Q_\u0014A\u0004\u0005]\u0018\u0001\u0002:fC2$B!a\u0018\u0003 !9\u0011Q\u001f\u0015A\u0004\t\u0005\u0002CBA}\u0005G\ty\"\u0003\u0003\u0003&\t5!!B\"SS:<\u0017\u0001\u00029ve\u0016$B!a\u0018\u0003,!9\u0011Q_\u0015A\u0004\t\u0005\u0012aA1cgR1\u0011q\u0004B\u0019\u0005wAqAa\r+\u0001\b\u0011)$A\u0001g!\u0019\tIPa\u000e\u0002 %!!\u0011\bB\u0007\u0005\u00151\u0015.\u001a7e\u0011\u001d\u0011iD\u000ba\u0002\u0005\u007f\t\u0011A\u001c\t\u0007\u0005\u0003\u0012\u0019%a\b\u000e\u0005\t\u001d\u0011\u0002\u0002B#\u0005\u000f\u0011QA\u0014*p_R\fq\u0001];sK\u0006\u00137\u000f\u0006\u0004\u0002 \t-#Q\n\u0005\b\u0005gY\u00039\u0001B\u001b\u0011\u001d\u0011id\u000ba\u0002\u0005\u007f\t1!Z9w)\u0011\u0011\u0019Fa\u0018\u0015\t\u0005%&Q\u000b\u0005\b\u0005/b\u00039\u0001B-\u0003\u0005y\u0007CBA}\u00057\ny\"\u0003\u0003\u0003^\t5!AA#r\u0011\u001d\u0011\t\u0007\fa\u0001\u0003?\n1A\u001d5t\u0003\u0011qW-\u001d<\u0015\t\t\u001d$1\u000e\u000b\u0005\u0003S\u0013I\u0007C\u0004\u0003X5\u0002\u001dA!\u0017\t\u000f\t\u0005T\u00061\u0001\u0002`\u0005AAo\\*ue&tw\r\u0006\u0002\u0003rA!!1\u000fB>\u001d\u0011\u0011)Ha\u001e\u0011\u0007\u0005\u0015\u0001-C\u0002\u0003z\u0001\fa\u0001\u0015:fI\u00164\u0017\u0002\u0002B?\u0005\u007f\u0012aa\u0015;sS:<'b\u0001B=A\u0006IAo\\\"p[BdW\r_\u000b\u0003\u0005\u000b\u0003Ra\u0017BD\u0003?I1A!#V\u0005\u001d\u0019u.\u001c9mKb\faa]5h]VlG\u0003BAA\u0005\u001fCq!!>1\u0001\b\t90\u0001\trk\u0006$XM\u001d8j_:\u001c\u0016n\u001a8v[RA\u0011q\fBK\u0005/\u0013I\nC\u0004\u00034E\u0002\u001dA!\u000e\t\u000f\tu\u0012\u0007q\u0001\u0003@!9\u0011Q_\u0019A\u0004\u0005]\u0018A\u00039ve\u0016\u001c\u0016n\u001a8v[RA\u0011q\fBP\u0005C\u0013\u0019\u000bC\u0004\u00034I\u0002\u001dA!\u000e\t\u000f\tu\"\u0007q\u0001\u0003@!9\u0011Q\u001f\u001aA\u0004\u0005]\u0018\u0001D;oCJLx\fJ7j]V\u001cH\u0003BA0\u0005SCq!!>4\u0001\b\u0011\t#A\u0005d_:TWoZ1uKR!\u0011q\fBX\u0011\u001d\t)\u0010\u000ea\u0002\u0005C\t!B]3dSB\u0014xnY1m)\u0011\tyF!.\t\u000f\tMR\u0007q\u0001\u00036\u0005!1/\u001d:u)!\tyFa/\u0003>\n\u0005\u0007b\u0002B\u001am\u0001\u000f!Q\u0007\u0005\b\u0005\u007f3\u00049\u0001B \u0003\tq'\u000fC\u0004\u0002vZ\u0002\u001d!a>\u0002\u000b9\u0014xn\u001c;\u0015\t\t\u001d'Q\u001d\u000b\r\u0003?\u0012IMa3\u0003N\n]'1\u001c\u0005\b\u0005g9\u00049\u0001B\u001b\u0011\u001d\u0011yl\u000ea\u0002\u0005\u007fAqAa48\u0001\b\u0011\t.\u0001\u0002peB1\u0011\u0011 Bj\u0003?IAA!6\u0003\u000e\t)qJ\u001d3fe\"9!\u0011\\\u001cA\u0004\u0005]\u0018AA:j\u0011\u001d\u0011in\u000ea\u0002\u0005?\f!\u0001\u001e:\u0011\r\t\u0005#\u0011]A\u0010\u0013\u0011\u0011\u0019Oa\u0002\u0003\tQ\u0013\u0018n\u001a\u0005\b\u0005O<\u0004\u0019AAA\u0003\u0005i\u0017\u0001B;oSR$b!a\u0018\u0003n\n=\bb\u0002B\u001aq\u0001\u000f!Q\u0007\u0005\b\u0005{A\u00049\u0001B \u0003\u0015!\u0003\u000f\\;t)\u0011\u0011)P!?\u0015\t\u0005}#q\u001f\u0005\b\u0003kL\u00049\u0001B\u0011\u0011\u001d\u0011\t'\u000fa\u0001\u0003?!BA!@\u0004\u0002Q!\u0011q\fB\u0000\u0011\u001d\t)P\u000fa\u0002\u0005CAqA!\u0019;\u0001\u0004\u0011)\t\u0006\u0003\u0004\u0006\r%A\u0003BA0\u0007\u000fAq!!><\u0001\b\u0011\t\u0003C\u0004\u0003bm\u0002\r!a\u0018\u0002\r\u0011j\u0017N\\;t)\u0011\u0019yaa\u0005\u0015\t\u0005}3\u0011\u0003\u0005\b\u0003kd\u00049\u0001B\u0011\u0011\u001d\u0011\t\u0007\u0010a\u0001\u0003?!Baa\u0006\u0004\u001cQ!\u0011qLB\r\u0011\u001d\t)0\u0010a\u0002\u0005CAqA!\u0019>\u0001\u0004\u0011)\t\u0006\u0003\u0004 \r\rB\u0003BA0\u0007CAq!!>?\u0001\b\u0011\t\u0003C\u0004\u0003by\u0002\r!a\u0018\u0002\r\u0011\"\u0018.\\3t)\u0011\u0019Ic!\f\u0015\t\u0005}31\u0006\u0005\b\u0003k|\u00049\u0001B\u0011\u0011\u001d\u0011\tg\u0010a\u0001\u0003?!Ba!\r\u00046Q!\u0011qLB\u001a\u0011\u001d\t)\u0010\u0011a\u0002\u0005CAqA!\u0019A\u0001\u0004\u0011)\t\u0006\u0003\u0004:\ruB\u0003BA0\u0007wAq!!>B\u0001\b\u0011\t\u0003C\u0004\u0003b\u0005\u0003\r!a\u0018\u0002\t\u0011\"\u0017N\u001e\u000b\u0005\u0007\u0007\u001a9\u0005\u0006\u0003\u0002`\r\u0015\u0003b\u0002B\u001a\u0005\u0002\u000f!Q\u0007\u0005\b\u0005C\u0012\u0005\u0019AA\u0010)\u0011\u0019Yea\u0014\u0015\t\u0005}3Q\n\u0005\b\u0005g\u0019\u00059\u0001B\u001b\u0011\u001d\u0011\tg\u0011a\u0001\u0005\u000b#Baa\u0015\u0004XQ!\u0011qLB+\u0011\u001d\u0011\u0019\u0004\u0012a\u0002\u0005kAqA!\u0019E\u0001\u0004\ty&A\u0002q_^$Ba!\u0018\u0004bQ!\u0011qLB0\u0011\u001d\t)0\u0012a\u0002\u0005CAq!!\u0017F\u0001\u0004\t\t)\u0001\u0007%i&lWm\u001d\u0013uS6,7\u000f\u0006\u0003\u0004h\r-D\u0003BA0\u0007SBq!!>G\u0001\b\u0011\t\u0003C\u0004\u0002Z\u0019\u0003\r!!!\u0002\t\u0019\u0004xn\u001e\u000b\u0005\u0007c\u001ai\b\u0006\u0007\u0002`\rM4QOB<\u0007s\u001aY\bC\u0004\u00034\u001d\u0003\u001dA!\u000e\t\u000f\t}v\tq\u0001\u0003@!9!qZ$A\u0004\tE\u0007b\u0002Bm\u000f\u0002\u000f\u0011q\u001f\u0005\b\u0005;<\u00059\u0001Bp\u0011\u001d\u0019yh\u0012a\u0001\u0003?\t!a\u001b\u0019\u0002\u0007\u0011|G\u000f\u0006\u0003\u0004\u0006\u000e%E\u0003BA\u0010\u0007\u000fCqAa\rI\u0001\b\u0011)\u0004C\u0004\u0003b!\u0003\r!a\u0018\u0002\t\r|\u0007/_\u000b\u0005\u0007\u001f\u001b)\n\u0006\u0006\u0004\u0012\u000e\u000561UBS\u0007O\u0003BaW\u0007\u0004\u0014B!\u0011\u0011EBK\t-\t)#\u0013Q\u0001\u0002\u0003\u0015\r!a\n)\u0011\rU\u0015qGBM\u0007;\u000b\u0014bIA \u0003\u0003\u001aY*a\u00112\r\u0011\n\u0019!a\u0003bc%\u0019\u0013\u0011JA&\u0007?\u000bi%\r\u0004%\u0003\u0007\tY!\u0019\u0005\n\u00037I\u0005\u0013!a\u0001\u0007'C\u0001\"]%\u0011\u0002\u0003\u000711\u0013\u0005\n\u0003+J\u0005\u0013!a\u0001\u0007'C\u0011\"!\u0017J!\u0003\u0005\raa%\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU!1QVBb+\t\u0019yK\u000b\u0003\u0002 \rE6FABZ!\u0011\u0019)la0\u000e\u0005\r]&\u0002BB]\u0007w\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\ru\u0006-\u0001\u0006b]:|G/\u0019;j_:LAa!1\u00048\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0005\u0017\u0005\u0015\"\n)A\u0001\u0002\u000b\u0007\u0011q\u0005\u0015\t\u0007\u0007\f9da2\u0004LFJ1%a\u0010\u0002B\r%\u00171I\u0019\u0007I\u0005\r\u00111B12\u0013\r\nI%a\u0013\u0004N\u00065\u0013G\u0002\u0013\u0002\u0004\u0005-\u0011-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\t\r561\u001b\u0003\f\u0003KY\u0005\u0015!A\u0001\u0006\u0004\t9\u0003\u000b\u0005\u0004T\u0006]2q[Bnc%\u0019\u0013qHA!\u00073\f\u0019%\r\u0004%\u0003\u0007\tY!Y\u0019\nG\u0005%\u00131JBo\u0003\u001b\nd\u0001JA\u0002\u0003\u0017\t\u0017AD2paf$C-\u001a4bk2$HeM\u000b\u0005\u0007[\u001b\u0019\u000fB\u0006\u0002&1\u0003\u000b\u0011!AC\u0002\u0005\u001d\u0002\u0006CBr\u0003o\u00199oa;2\u0013\r\ny$!\u0011\u0004j\u0006\r\u0013G\u0002\u0013\u0002\u0004\u0005-\u0011-M\u0005$\u0003\u0013\nYe!<\u0002NE2A%a\u0001\u0002\f\u0005\fabY8qs\u0012\"WMZ1vYR$C'\u0006\u0003\u0004.\u000eMHaCA\u0013\u001b\u0002\u0006\t\u0011!b\u0001\u0003OA\u0003ba=\u00028\r]81`\u0019\nG\u0005}\u0012\u0011IB}\u0003\u0007\nd\u0001JA\u0002\u0003\u0017\t\u0017'C\u0012\u0002J\u0005-3Q`A'c\u0019!\u00131AA\u0006C\u0006i\u0001O]8ek\u000e$\bK]3gSb,\"\u0001b\u0001\u0011\t\u0005UFQA\u0005\u0005\u0005{\n9,\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005=BQ\u0002\u0005\n\t\u001f\u0001\u0016\u0011!a\u0001\u0003\u0003\u000b1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XC\u0001C\u000b!\u0019!9\u0002\"\b\u000205\u0011A\u0011\u0004\u0006\u0004\t7\u0001\u0017AC2pY2,7\r^5p]&!Aq\u0004C\r\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005%FQ\u0005\u0005\n\t\u001f\u0011\u0016\u0011!a\u0001\u0003_\t!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!A1\u0001C\u0016\u0011%!yaUA\u0001\u0002\u0004\t\t\t\u0005\u0003\u0002\"\u0011=BaCA\u0013\u0007\u0001\u0006\t\u0011!b\u0001\u0003OA\u0003\u0002b\f\u00028\u0011MBqG\u0019\nG\u0005}\u0012\u0011\tC\u001b\u0003\u0007\nd\u0001JA\u0002\u0003\u0017\t\u0017'C\u0012\u0002J\u0005-C\u0011HA'c\u0019!\u00131AA\u0006C\"9!1G\u0002A\u0004\u0011u\u0002CBA}\u0005G!i#\u0006\u0003\u0005B\u0011\u001dC\u0003\u0002C\"\t'\u0002BaW\u0007\u0005FA!\u0011\u0011\u0005C$\t-\t)\u0003\u0002Q\u0001\u0002\u0003\u0015\r!a\n)\u0011\u0011\u001d\u0013q\u0007C&\t\u001f\n\u0014bIA \u0003\u0003\"i%a\u00112\r\u0011\n\u0019!a\u0003bc%\u0019\u0013\u0011JA&\t#\ni%\r\u0004%\u0003\u0007\tY!\u0019\u0005\b\u0005g!\u00019\u0001C+!\u0019\tIPa\t\u0005FU!A\u0011\fC0)\u0011!Y\u0006b\u001b\u0011\tmkAQ\f\t\u0005\u0003C!y\u0006B\u0006\u0002&\u0015\u0001\u000b\u0011!AC\u0002\u0005\u001d\u0002\u0006\u0003C0\u0003o!\u0019\u0007b\u001a2\u0013\r\ny$!\u0011\u0005f\u0005\r\u0013G\u0002\u0013\u0002\u0004\u0005-\u0011-M\u0005$\u0003\u0013\nY\u0005\"\u001b\u0002NE2A%a\u0001\u0002\f\u0005DqAa\r\u0006\u0001\b!i\u0007\u0005\u0004\u0002z\n\rBQL\u0001\u0005u\u0016\u0014x.\u0006\u0003\u0005t\u0011eD\u0003\u0002C;\t\u000b\u0003BaW\u0007\u0005xA!\u0011\u0011\u0005C=\t-\t)C\u0002Q\u0001\u0002\u0003\u0015\r!a\n)\u0011\u0011e\u0014q\u0007C?\t\u0003\u000b\u0014bIA \u0003\u0003\"y(a\u00112\r\u0011\n\u0019!a\u0003bc%\u0019\u0013\u0011JA&\t\u0007\u000bi%\r\u0004%\u0003\u0007\tY!\u0019\u0005\b\u0005g1\u00019\u0001CD!\u0019\tIPa\t\u0005x\u0005\u0019qN\\3\u0016\t\u00115E1\u0013\u000b\u0005\t\u001f#y\n\u0005\u0003\\\u001b\u0011E\u0005\u0003BA\u0011\t'#1\"!\n\bA\u0003\u0005\tQ1\u0001\u0002(!BA1SA\u001c\t/#Y*M\u0005$\u0003\u007f\t\t\u0005\"'\u0002DE2A%a\u0001\u0002\f\u0005\f\u0014bIA%\u0003\u0017\"i*!\u00142\r\u0011\n\u0019!a\u0003b\u0011\u001d\u0011\u0019d\u0002a\u0002\tC\u0003b!!?\u0003$\u0011E\u0015!B1qa2LX\u0003\u0002CT\t_#B\u0001\"+\u0005@R!A1\u0016C^!\u0011YV\u0002\",\u0011\t\u0005\u0005Bq\u0016\u0003\f\u0003KA\u0001\u0015!A\u0001\u0006\u0004\t9\u0003\u000b\u0005\u00050\u0006]B1\u0017C\\c%\u0019\u0013qHA!\tk\u000b\u0019%\r\u0004%\u0003\u0007\tY!Y\u0019\nG\u0005%\u00131\nC]\u0003\u001b\nd\u0001JA\u0002\u0003\u0017\t\u0007b\u0002B\u001a\u0011\u0001\u000fAQ\u0018\t\u0007\u0003s\u0014\u0019\u0003\",\t\u000f\u0011\u0005\u0007\u00021\u0001\u0005.\u0006\t\u0011-\u0006\u0003\u0005F\u00125G\u0003\u0002Cd\t;$B\u0001\"3\u0005ZB!1,\u0004Cf!\u0011\t\t\u0003\"4\u0005\u0017\u0005\u0015\u0012\u0002)A\u0001\u0002\u000b\u0007\u0011q\u0005\u0015\t\t\u001b\f9\u0004\"5\u0005VFJ1%a\u0010\u0002B\u0011M\u00171I\u0019\u0007I\u0005\r\u00111B12\u0013\r\nI%a\u0013\u0005X\u00065\u0013G\u0002\u0013\u0002\u0004\u0005-\u0011\rC\u0004\u00034%\u0001\u001d\u0001b7\u0011\r\u0005e(1\u0005Cf\u0011\u001d!y.\u0003a\u0001\tC\f\u0011a\u0019\t\u00067\n\u001dE1Z\u000b\u0005\tK$Y\u000f\u0006\u0006\u0005h\u0012]H\u0011 C~\t{\u0004BaW\u0007\u0005jB!\u0011\u0011\u0005Cv\t-\t)C\u0003Q\u0001\u0002\u0003\u0015\r!a\n)\u0011\u0011-\u0018q\u0007Cx\tg\f\u0014bIA \u0003\u0003\"\t0a\u00112\r\u0011\n\u0019!a\u0003bc%\u0019\u0013\u0011JA&\tk\fi%\r\u0004%\u0003\u0007\tY!\u0019\u0005\b\u00037Q\u0001\u0019\u0001Cu\u0011\u0019\t(\u00021\u0001\u0005j\"9\u0011Q\u000b\u0006A\u0002\u0011%\bbBA-\u0015\u0001\u0007A\u0011^\u0001\bk:\f\u0007\u000f\u001d7z+\u0011)\u0019!b\u0005\u0015\t\u0015\u0015Qq\u0004\t\u0006?\u0016\u001dQ1B\u0005\u0004\u000b\u0013\u0001'AB(qi&|g\u000eE\u0006`\u000b\u001b)\t\"\"\u0005\u0006\u0012\u0015E\u0011bAC\bA\n1A+\u001e9mKR\u0002B!!\t\u0006\u0014\u0011Y\u0011QE\u0006!\u0002\u0003\u0005)\u0019AA\u0014Q!)\u0019\"a\u000e\u0006\u0018\u0015m\u0011'C\u0012\u0002@\u0005\u0005S\u0011DA\"c\u0019!\u00131AA\u0006CFJ1%!\u0013\u0002L\u0015u\u0011QJ\u0019\u0007I\u0005\r\u00111B1\t\u0013\u0015\u00052\"!AA\u0002\u0015\r\u0012a\u0001=%aA!1,DC\t\u000319(/\u001b;f%\u0016\u0004H.Y2f\u0001"
)
public class Quaternion extends ScalaNumber implements ScalaNumericConversions, Product {
   public final Object r;
   public final Object i;
   public final Object j;
   public final Object k;

   public static Option unapply(final Quaternion x$0) {
      return Quaternion$.MODULE$.unapply(x$0);
   }

   public static Quaternion apply(final Object r, final Object i, final Object j, final Object k) {
      return Quaternion$.MODULE$.apply(r, i, j, k);
   }

   public static Quaternion apply(final Complex c, final CommutativeRing f) {
      return Quaternion$.MODULE$.apply(c, f);
   }

   public static Quaternion apply(final Object a, final CommutativeRing f) {
      return Quaternion$.MODULE$.apply(a, f);
   }

   public static Quaternion one(final CommutativeRing f) {
      return Quaternion$.MODULE$.one(f);
   }

   public static Quaternion zero(final CommutativeRing f) {
      return Quaternion$.MODULE$.zero(f);
   }

   public static QuaternionOverRichField QuaternionOverRichField(final Field f0, final NRoot n0, final Order o0, final Signed s0, final Trig t0) {
      return Quaternion$.MODULE$.QuaternionOverRichField(f0, n0, o0, s0, t0);
   }

   public static QuaternionOverField QuaternionOverField(final Field f0, final Order o0, final Signed s0) {
      return Quaternion$.MODULE$.QuaternionOverField(f0, o0, s0);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public char toChar() {
      return ScalaNumericAnyConversions.toChar$(this);
   }

   public byte toByte() {
      return ScalaNumericAnyConversions.toByte$(this);
   }

   public short toShort() {
      return ScalaNumericAnyConversions.toShort$(this);
   }

   public int toInt() {
      return ScalaNumericAnyConversions.toInt$(this);
   }

   public long toLong() {
      return ScalaNumericAnyConversions.toLong$(this);
   }

   public float toFloat() {
      return ScalaNumericAnyConversions.toFloat$(this);
   }

   public double toDouble() {
      return ScalaNumericAnyConversions.toDouble$(this);
   }

   public boolean isValidByte() {
      return ScalaNumericAnyConversions.isValidByte$(this);
   }

   public boolean isValidShort() {
      return ScalaNumericAnyConversions.isValidShort$(this);
   }

   public boolean isValidChar() {
      return ScalaNumericAnyConversions.isValidChar$(this);
   }

   public int unifiedPrimitiveHashcode() {
      return ScalaNumericAnyConversions.unifiedPrimitiveHashcode$(this);
   }

   public boolean unifiedPrimitiveEquals(final Object x) {
      return ScalaNumericAnyConversions.unifiedPrimitiveEquals$(this, x);
   }

   public Object r() {
      return this.r;
   }

   public Object i() {
      return this.i;
   }

   public Object j() {
      return this.j;
   }

   public Object k() {
      return this.k;
   }

   public byte byteValue() {
      return (byte)((int)this.longValue());
   }

   public short shortValue() {
      return (short)((int)this.longValue());
   }

   public int intValue() {
      return (int)this.longValue();
   }

   public long longValue() {
      return package$.MODULE$.anyToLong(this.r());
   }

   public float floatValue() {
      return (float)this.doubleValue();
   }

   public double doubleValue() {
      return package$.MODULE$.anyToDouble(this.r());
   }

   private boolean sillyIsReal() {
      return package$.MODULE$.anyIsZero(this.i()) && package$.MODULE$.anyIsZero(this.j()) && package$.MODULE$.anyIsZero(this.k());
   }

   public Object underlying() {
      return this;
   }

   public boolean isWhole() {
      return this.sillyIsReal() && package$.MODULE$.anyIsWhole(this.r());
   }

   public final boolean isValidInt() {
      return this.sillyIsReal() && package$.MODULE$.anyIsValidInt(this.r());
   }

   public int hashCode() {
      return this.sillyIsReal() ? Statics.anyHash(this.r()) : 19 * Statics.anyHash(this.r()) + 41 * Statics.anyHash(this.i()) + 13 * Statics.anyHash(this.j()) + 77 * Statics.anyHash(this.k()) + 97;
   }

   public boolean equals(final Object that) {
      boolean var2;
      if (that instanceof Quaternion) {
         Quaternion var4 = (Quaternion)that;
         var2 = this.$eq$eq$eq(var4);
      } else if (that instanceof Complex) {
         Complex var5 = (Complex)that;
         var2 = BoxesRunTime.equals(this.r(), var5.real()) && BoxesRunTime.equals(this.i(), var5.imag()) && package$.MODULE$.anyIsZero(this.j()) && package$.MODULE$.anyIsZero(this.k());
      } else {
         var2 = this.sillyIsReal() && BoxesRunTime.equals(this.r(), that);
      }

      return var2;
   }

   public boolean $eq$eq$eq(final Quaternion that) {
      return BoxesRunTime.equals(this.r(), that.r()) && BoxesRunTime.equals(this.i(), that.i()) && BoxesRunTime.equals(this.j(), that.j()) && BoxesRunTime.equals(this.k(), that.k());
   }

   public boolean $eq$bang$eq(final Quaternion that) {
      return !this.$eq$eq$eq(that);
   }

   public boolean isZero(final Signed s) {
      return s.isSignZero(this.r()) && s.isSignZero(this.i()) && s.isSignZero(this.j()) && s.isSignZero(this.k());
   }

   public boolean isReal(final Signed s) {
      return s.isSignZero(this.i()) && s.isSignZero(this.j()) && s.isSignZero(this.k());
   }

   public boolean isPure(final Signed s) {
      return s.isSignZero(this.r());
   }

   public Quaternion real(final CommutativeRing s) {
      return Quaternion$.MODULE$.apply(this.r(), s);
   }

   public Quaternion pure(final CommutativeRing s) {
      return new Quaternion(s.zero(), this.i(), this.j(), this.k());
   }

   public Object abs(final Field f, final NRoot n) {
      return n.sqrt(f.plus(f.plus(f.plus(f.pow(this.r(), 2), f.pow(this.i(), 2)), f.pow(this.j(), 2)), f.pow(this.k(), 2)));
   }

   public Object pureAbs(final Field f, final NRoot n) {
      return n.sqrt(f.plus(f.plus(f.pow(this.i(), 2), f.pow(this.j(), 2)), f.pow(this.k(), 2)));
   }

   public boolean eqv(final Quaternion rhs, final Eq o) {
      return o.eqv(this.r(), rhs.r()) && o.eqv(this.i(), rhs.i()) && o.eqv(this.j(), rhs.j()) && o.eqv(this.k(), rhs.k());
   }

   public boolean neqv(final Quaternion rhs, final Eq o) {
      return o.neqv(this.r(), rhs.r()) && o.neqv(this.i(), rhs.i()) && o.neqv(this.j(), rhs.j()) && o.neqv(this.k(), rhs.k());
   }

   public String toString() {
      return (new StringBuilder(14)).append("(").append(this.r()).append(" + ").append(this.i()).append("i + ").append(this.j()).append("j + ").append(this.k()).append("k)").toString();
   }

   public Complex toComplex() {
      return new Complex(this.r(), this.i());
   }

   public int signum(final Signed s) {
      int var2 = s.signum(this.r());
      int var10000;
      switch (var2) {
         case 0:
            int var3 = s.signum(this.i());
            switch (var3) {
               case 0:
                  int var4 = s.signum(this.j());
                  switch (var4) {
                     case 0:
                        var10000 = s.signum(this.k());
                        return var10000;
                     default:
                        var10000 = var4;
                        return var10000;
                  }
               default:
                  var10000 = var3;
                  return var10000;
            }
         default:
            var10000 = var2;
            return var10000;
      }
   }

   public Quaternion quaternionSignum(final Field f, final NRoot n, final Signed s) {
      return this.isZero(s) ? this : this.$div(this.abs(f, n), f);
   }

   public Quaternion pureSignum(final Field f, final NRoot n, final Signed s) {
      return this.isReal(s) ? Quaternion$.MODULE$.zero(f) : this.pure(f).$div(this.pureAbs(f, n), f);
   }

   public Quaternion unary_$minus(final CommutativeRing s) {
      return new Quaternion(s.negate(this.r()), s.negate(this.i()), s.negate(this.j()), s.negate(this.k()));
   }

   public Quaternion conjugate(final CommutativeRing s) {
      return new Quaternion(this.r(), s.negate(this.i()), s.negate(this.j()), s.negate(this.k()));
   }

   public Quaternion reciprocal(final Field f) {
      return this.conjugate(f).$div(f.plus(f.plus(f.plus(f.pow(this.r(), 2), f.pow(this.i(), 2)), f.pow(this.j(), 2)), f.pow(this.k(), 2)), f);
   }

   public Quaternion sqrt(final Field f, final NRoot nr, final Signed s) {
      Quaternion var10000;
      if (!this.isReal(s)) {
         Object n = nr.sqrt(f.plus(this.r(), this.abs(f, nr)));
         var10000 = (new Quaternion(n, f.div(this.i(), n), f.div(this.j(), n), f.div(this.k(), n))).$div(nr.sqrt(f.fromInt(2)), f);
      } else {
         var10000 = s.signum(this.r()) >= 0 ? Quaternion$.MODULE$.apply((Object)nr.sqrt(this.r()), f) : new Quaternion(f.zero(), nr.sqrt(s.abs(this.r())), f.zero(), f.zero());
      }

      return var10000;
   }

   public Quaternion nroot(final int m, final Field f, final NRoot nr, final Order or, final Signed si, final Trig tr) {
      if (m <= 0) {
         throw new IllegalArgumentException((new StringBuilder(14)).append("illegal root: ").append(m).toString());
      } else {
         Quaternion var10000;
         if (m == 1) {
            var10000 = this;
         } else if (!this.isReal(si)) {
            Object s = this.pureAbs(f, nr);
            Object n = this.abs(f, nr);
            Object t = package$.MODULE$.acos(f.div(this.r(), n), tr);
            Quaternion v = new Quaternion(f.zero(), f.div(this.i(), s), f.div(this.j(), s), f.div(this.k(), s));
            Quaternion e = si.signum(package$.MODULE$.sin(t, tr)) >= 0 ? v : v.unary_$minus(f);
            Object tm = f.div(t, f.fromInt(m));
            var10000 = e.$times((Object)package$.MODULE$.sin(tm, tr), f).$plus((Object)package$.MODULE$.cos(tm, tr), f).$times((Object)nr.nroot(n, m), f);
         } else {
            var10000 = si.signum(this.r()) >= 0 ? Quaternion$.MODULE$.apply((Object)nr.nroot(this.r(), m), f) : Quaternion$.MODULE$.apply((Complex)Complex$.MODULE$.apply(this.r(), (CommutativeRing)f).nroot(m, f, nr, or, si, tr), f);
         }

         return var10000;
      }
   }

   public Quaternion unit(final Field f, final NRoot n) {
      return (new Quaternion(f.pow(this.r(), 2), f.pow(this.i(), 2), f.pow(this.j(), 2), f.pow(this.k(), 2))).$div(this.abs(f, n), f);
   }

   public Quaternion $plus(final Object rhs, final CommutativeRing s) {
      return new Quaternion(s.plus(this.r(), rhs), this.i(), this.j(), this.k());
   }

   public Quaternion $plus(final Complex rhs, final CommutativeRing s) {
      return new Quaternion(s.plus(this.r(), rhs.real()), s.plus(this.i(), rhs.imag()), this.j(), this.k());
   }

   public Quaternion $plus(final Quaternion rhs, final CommutativeRing s) {
      return new Quaternion(s.plus(this.r(), rhs.r()), s.plus(this.i(), rhs.i()), s.plus(this.j(), rhs.j()), s.plus(this.k(), rhs.k()));
   }

   public Quaternion $minus(final Object rhs, final CommutativeRing s) {
      return new Quaternion(s.minus(this.r(), rhs), this.i(), this.j(), this.k());
   }

   public Quaternion $minus(final Complex rhs, final CommutativeRing s) {
      return new Quaternion(s.minus(this.r(), rhs.real()), s.minus(this.i(), rhs.imag()), this.j(), this.k());
   }

   public Quaternion $minus(final Quaternion rhs, final CommutativeRing s) {
      return new Quaternion(s.minus(this.r(), rhs.r()), s.minus(this.i(), rhs.i()), s.minus(this.j(), rhs.j()), s.minus(this.k(), rhs.k()));
   }

   public Quaternion $times(final Object rhs, final CommutativeRing s) {
      return new Quaternion(s.times(this.r(), rhs), s.times(this.i(), rhs), s.times(this.j(), rhs), s.times(this.k(), rhs));
   }

   public Quaternion $times(final Complex rhs, final CommutativeRing s) {
      return new Quaternion(s.minus(s.times(this.r(), rhs.real()), s.times(this.i(), rhs.imag())), s.plus(s.times(this.r(), rhs.imag()), s.times(this.i(), rhs.real())), s.plus(s.times(this.j(), rhs.real()), s.times(this.k(), rhs.imag())), s.plus(s.times(this.j(), rhs.imag()), s.times(this.k(), rhs.real())));
   }

   public Quaternion $times(final Quaternion rhs, final CommutativeRing s) {
      return new Quaternion(s.minus(s.minus(s.minus(s.times(this.r(), rhs.r()), s.times(this.i(), rhs.i())), s.times(this.j(), rhs.j())), s.times(this.k(), rhs.k())), s.minus(s.plus(s.plus(s.times(this.r(), rhs.i()), s.times(this.i(), rhs.r())), s.times(this.j(), rhs.k())), s.times(this.k(), rhs.j())), s.plus(s.plus(s.minus(s.times(this.r(), rhs.j()), s.times(this.i(), rhs.k())), s.times(this.j(), rhs.r())), s.times(this.k(), rhs.i())), s.plus(s.minus(s.plus(s.times(this.r(), rhs.k()), s.times(this.i(), rhs.j())), s.times(this.j(), rhs.i())), s.times(this.k(), rhs.r())));
   }

   public Quaternion $div(final Object rhs, final Field f) {
      return new Quaternion(f.div(this.r(), rhs), f.div(this.i(), rhs), f.div(this.j(), rhs), f.div(this.k(), rhs));
   }

   public Quaternion $div(final Complex rhs, final Field f) {
      return this.$times((Quaternion)Quaternion$.MODULE$.apply((Complex)rhs, f).reciprocal(f), f);
   }

   public Quaternion $div(final Quaternion rhs, final Field f) {
      return this.$times((Quaternion)rhs.reciprocal(f), f);
   }

   public Quaternion pow(final int k, final CommutativeRing s) {
      if (k >= 0) {
         return this.loop$1(Quaternion$.MODULE$.one(s), this, k, s);
      } else {
         throw new IllegalArgumentException((new StringBuilder(18)).append("illegal exponent: ").append(k).toString());
      }
   }

   public Quaternion $times$times(final int k, final CommutativeRing s) {
      return this.pow(k, s);
   }

   public Quaternion fpow(final Object k0, final Field f, final NRoot nr, final Order or, final Signed si, final Trig tr) {
      Quaternion var10000;
      if (si.signum(k0) < 0) {
         var10000 = Quaternion$.MODULE$.zero(f);
      } else if (BoxesRunTime.equals(k0, f.zero())) {
         var10000 = Quaternion$.MODULE$.one(f);
      } else if (BoxesRunTime.equals(k0, f.one())) {
         var10000 = this;
      } else if (!this.isReal(si)) {
         Object s = nr.sqrt(f.plus(f.plus(f.pow(this.i(), 2), f.pow(this.j(), 2)), f.pow(this.k(), 2)));
         Quaternion v = new Quaternion(f.zero(), f.div(this.i(), s), f.div(this.j(), s), f.div(this.k(), s));
         Object n = this.abs(f, nr);
         Object t = package$.MODULE$.acos(f.div(this.r(), n), tr);
         var10000 = Quaternion$.MODULE$.apply((Object)package$.MODULE$.cos(f.times(t, k0), tr), f).$plus((Quaternion)v.$times((Object)package$.MODULE$.sin(f.times(t, k0), tr), f), f).$times((Object)nr.fpow(n, k0), f);
      } else {
         var10000 = si.signum(this.r()) >= 0 ? Quaternion$.MODULE$.apply((Object)nr.fpow(this.r(), k0), f) : Quaternion$.MODULE$.apply((Complex)Complex$.MODULE$.apply(this.r(), (CommutativeRing)f).pow(Complex$.MODULE$.apply(k0, (CommutativeRing)f), f, nr, or, si, tr), f);
      }

      return var10000;
   }

   public Object dot(final Quaternion rhs, final Field f) {
      return f.div(this.conjugate(f).$times((Quaternion)rhs, f).$plus((Quaternion)rhs.conjugate(f).$times((Quaternion)this, f), f).r(), f.fromInt(2));
   }

   public Quaternion copy(final Object r, final Object i, final Object j, final Object k) {
      return new Quaternion(r, i, j, k);
   }

   public Object copy$default$1() {
      return this.r();
   }

   public Object copy$default$2() {
      return this.i();
   }

   public Object copy$default$3() {
      return this.j();
   }

   public Object copy$default$4() {
      return this.k();
   }

   public String productPrefix() {
      return "Quaternion";
   }

   public int productArity() {
      return 4;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.r();
            break;
         case 1:
            var10000 = this.i();
            break;
         case 2:
            var10000 = this.j();
            break;
         case 3:
            var10000 = this.k();
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof Quaternion;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "r";
            break;
         case 1:
            var10000 = "i";
            break;
         case 2:
            var10000 = "j";
            break;
         case 3:
            var10000 = "k";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public double r$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.r());
   }

   public float r$mcF$sp() {
      return BoxesRunTime.unboxToFloat(this.r());
   }

   public double i$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.i());
   }

   public float i$mcF$sp() {
      return BoxesRunTime.unboxToFloat(this.i());
   }

   public double j$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.j());
   }

   public float j$mcF$sp() {
      return BoxesRunTime.unboxToFloat(this.j());
   }

   public double k$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.k());
   }

   public float k$mcF$sp() {
      return BoxesRunTime.unboxToFloat(this.k());
   }

   public boolean isZero$mcD$sp(final Signed s) {
      return this.isZero(s);
   }

   public boolean isZero$mcF$sp(final Signed s) {
      return this.isZero(s);
   }

   public boolean isReal$mcD$sp(final Signed s) {
      return this.isReal(s);
   }

   public boolean isReal$mcF$sp(final Signed s) {
      return this.isReal(s);
   }

   public boolean isPure$mcD$sp(final Signed s) {
      return this.isPure(s);
   }

   public boolean isPure$mcF$sp(final Signed s) {
      return this.isPure(s);
   }

   public Quaternion real$mcD$sp(final CommutativeRing s) {
      return this.real(s);
   }

   public Quaternion real$mcF$sp(final CommutativeRing s) {
      return this.real(s);
   }

   public Quaternion pure$mcD$sp(final CommutativeRing s) {
      return this.pure(s);
   }

   public Quaternion pure$mcF$sp(final CommutativeRing s) {
      return this.pure(s);
   }

   public double abs$mcD$sp(final Field f, final NRoot n) {
      return BoxesRunTime.unboxToDouble(this.abs(f, n));
   }

   public float abs$mcF$sp(final Field f, final NRoot n) {
      return BoxesRunTime.unboxToFloat(this.abs(f, n));
   }

   public double pureAbs$mcD$sp(final Field f, final NRoot n) {
      return BoxesRunTime.unboxToDouble(this.pureAbs(f, n));
   }

   public float pureAbs$mcF$sp(final Field f, final NRoot n) {
      return BoxesRunTime.unboxToFloat(this.pureAbs(f, n));
   }

   public boolean eqv$mcD$sp(final Quaternion rhs, final Eq o) {
      return this.eqv(rhs, o);
   }

   public boolean eqv$mcF$sp(final Quaternion rhs, final Eq o) {
      return this.eqv(rhs, o);
   }

   public boolean neqv$mcD$sp(final Quaternion rhs, final Eq o) {
      return this.neqv(rhs, o);
   }

   public boolean neqv$mcF$sp(final Quaternion rhs, final Eq o) {
      return this.neqv(rhs, o);
   }

   public Complex toComplex$mcD$sp() {
      return this.toComplex();
   }

   public Complex toComplex$mcF$sp() {
      return this.toComplex();
   }

   public int signum$mcD$sp(final Signed s) {
      return this.signum(s);
   }

   public int signum$mcF$sp(final Signed s) {
      return this.signum(s);
   }

   public Quaternion quaternionSignum$mcD$sp(final Field f, final NRoot n, final Signed s) {
      return this.quaternionSignum(f, n, s);
   }

   public Quaternion quaternionSignum$mcF$sp(final Field f, final NRoot n, final Signed s) {
      return this.quaternionSignum(f, n, s);
   }

   public Quaternion pureSignum$mcD$sp(final Field f, final NRoot n, final Signed s) {
      return this.pureSignum(f, n, s);
   }

   public Quaternion pureSignum$mcF$sp(final Field f, final NRoot n, final Signed s) {
      return this.pureSignum(f, n, s);
   }

   public Quaternion unary_$minus$mcD$sp(final CommutativeRing s) {
      return this.unary_$minus(s);
   }

   public Quaternion unary_$minus$mcF$sp(final CommutativeRing s) {
      return this.unary_$minus(s);
   }

   public Quaternion conjugate$mcD$sp(final CommutativeRing s) {
      return this.conjugate(s);
   }

   public Quaternion conjugate$mcF$sp(final CommutativeRing s) {
      return this.conjugate(s);
   }

   public Quaternion reciprocal$mcD$sp(final Field f) {
      return this.reciprocal(f);
   }

   public Quaternion reciprocal$mcF$sp(final Field f) {
      return this.reciprocal(f);
   }

   public Quaternion sqrt$mcD$sp(final Field f, final NRoot nr, final Signed s) {
      return this.sqrt(f, nr, s);
   }

   public Quaternion sqrt$mcF$sp(final Field f, final NRoot nr, final Signed s) {
      return this.sqrt(f, nr, s);
   }

   public Quaternion nroot$mcD$sp(final int m, final Field f, final NRoot nr, final Order or, final Signed si, final Trig tr) {
      return this.nroot(m, f, nr, or, si, tr);
   }

   public Quaternion nroot$mcF$sp(final int m, final Field f, final NRoot nr, final Order or, final Signed si, final Trig tr) {
      return this.nroot(m, f, nr, or, si, tr);
   }

   public Quaternion unit$mcD$sp(final Field f, final NRoot n) {
      return this.unit(f, n);
   }

   public Quaternion unit$mcF$sp(final Field f, final NRoot n) {
      return this.unit(f, n);
   }

   public Quaternion $plus$mcD$sp(final double rhs, final CommutativeRing s) {
      return this.$plus((Object)BoxesRunTime.boxToDouble(rhs), s);
   }

   public Quaternion $plus$mcF$sp(final float rhs, final CommutativeRing s) {
      return this.$plus((Object)BoxesRunTime.boxToFloat(rhs), s);
   }

   public Quaternion $plus$mcD$sp(final Complex rhs, final CommutativeRing s) {
      return this.$plus(rhs, s);
   }

   public Quaternion $plus$mcF$sp(final Complex rhs, final CommutativeRing s) {
      return this.$plus(rhs, s);
   }

   public Quaternion $plus$mcD$sp(final Quaternion rhs, final CommutativeRing s) {
      return this.$plus(rhs, s);
   }

   public Quaternion $plus$mcF$sp(final Quaternion rhs, final CommutativeRing s) {
      return this.$plus(rhs, s);
   }

   public Quaternion $minus$mcD$sp(final double rhs, final CommutativeRing s) {
      return this.$minus((Object)BoxesRunTime.boxToDouble(rhs), s);
   }

   public Quaternion $minus$mcF$sp(final float rhs, final CommutativeRing s) {
      return this.$minus((Object)BoxesRunTime.boxToFloat(rhs), s);
   }

   public Quaternion $minus$mcD$sp(final Complex rhs, final CommutativeRing s) {
      return this.$minus(rhs, s);
   }

   public Quaternion $minus$mcF$sp(final Complex rhs, final CommutativeRing s) {
      return this.$minus(rhs, s);
   }

   public Quaternion $minus$mcD$sp(final Quaternion rhs, final CommutativeRing s) {
      return this.$minus(rhs, s);
   }

   public Quaternion $minus$mcF$sp(final Quaternion rhs, final CommutativeRing s) {
      return this.$minus(rhs, s);
   }

   public Quaternion $times$mcD$sp(final double rhs, final CommutativeRing s) {
      return this.$times((Object)BoxesRunTime.boxToDouble(rhs), s);
   }

   public Quaternion $times$mcF$sp(final float rhs, final CommutativeRing s) {
      return this.$times((Object)BoxesRunTime.boxToFloat(rhs), s);
   }

   public Quaternion $times$mcD$sp(final Complex rhs, final CommutativeRing s) {
      return this.$times(rhs, s);
   }

   public Quaternion $times$mcF$sp(final Complex rhs, final CommutativeRing s) {
      return this.$times(rhs, s);
   }

   public Quaternion $times$mcD$sp(final Quaternion rhs, final CommutativeRing s) {
      return this.$times(rhs, s);
   }

   public Quaternion $times$mcF$sp(final Quaternion rhs, final CommutativeRing s) {
      return this.$times(rhs, s);
   }

   public Quaternion $div$mcD$sp(final double rhs, final Field f) {
      return this.$div((Object)BoxesRunTime.boxToDouble(rhs), f);
   }

   public Quaternion $div$mcF$sp(final float rhs, final Field f) {
      return this.$div((Object)BoxesRunTime.boxToFloat(rhs), f);
   }

   public Quaternion $div$mcD$sp(final Complex rhs, final Field f) {
      return this.$div(rhs, f);
   }

   public Quaternion $div$mcF$sp(final Complex rhs, final Field f) {
      return this.$div(rhs, f);
   }

   public Quaternion $div$mcD$sp(final Quaternion rhs, final Field f) {
      return this.$div(rhs, f);
   }

   public Quaternion $div$mcF$sp(final Quaternion rhs, final Field f) {
      return this.$div(rhs, f);
   }

   public Quaternion pow$mcD$sp(final int k, final CommutativeRing s) {
      return this.pow(k, s);
   }

   public Quaternion pow$mcF$sp(final int k, final CommutativeRing s) {
      return this.pow(k, s);
   }

   public Quaternion $times$times$mcD$sp(final int k, final CommutativeRing s) {
      return this.$times$times(k, s);
   }

   public Quaternion $times$times$mcF$sp(final int k, final CommutativeRing s) {
      return this.$times$times(k, s);
   }

   public Quaternion fpow$mcD$sp(final double k0, final Field f, final NRoot nr, final Order or, final Signed si, final Trig tr) {
      return this.fpow(BoxesRunTime.boxToDouble(k0), f, nr, or, si, tr);
   }

   public Quaternion fpow$mcF$sp(final float k0, final Field f, final NRoot nr, final Order or, final Signed si, final Trig tr) {
      return this.fpow(BoxesRunTime.boxToFloat(k0), f, nr, or, si, tr);
   }

   public double dot$mcD$sp(final Quaternion rhs, final Field f) {
      return BoxesRunTime.unboxToDouble(this.dot(rhs, f));
   }

   public float dot$mcF$sp(final Quaternion rhs, final Field f) {
      return BoxesRunTime.unboxToFloat(this.dot(rhs, f));
   }

   public Quaternion copy$mDc$sp(final double r, final double i, final double j, final double k) {
      return new Quaternion$mcD$sp(r, i, j, k);
   }

   public Quaternion copy$mFc$sp(final float r, final float i, final float j, final float k) {
      return new Quaternion$mcF$sp(r, i, j, k);
   }

   public double copy$default$1$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.copy$default$1());
   }

   public float copy$default$1$mcF$sp() {
      return BoxesRunTime.unboxToFloat(this.copy$default$1());
   }

   public double copy$default$2$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.copy$default$2());
   }

   public float copy$default$2$mcF$sp() {
      return BoxesRunTime.unboxToFloat(this.copy$default$2());
   }

   public double copy$default$3$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.copy$default$3());
   }

   public float copy$default$3$mcF$sp() {
      return BoxesRunTime.unboxToFloat(this.copy$default$3());
   }

   public double copy$default$4$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.copy$default$4());
   }

   public float copy$default$4$mcF$sp() {
      return BoxesRunTime.unboxToFloat(this.copy$default$4());
   }

   public boolean specInstance$() {
      return false;
   }

   private final Quaternion loop$1(final Quaternion p, final Quaternion b, final int e, final CommutativeRing s$1) {
      while(e != 0) {
         if ((e & 1) == 1) {
            Quaternion var10000 = p.$times(b, s$1);
            Quaternion var5 = b.$times(b, s$1);
            e >>>= 1;
            b = var5;
            p = var10000;
         } else {
            Quaternion var10001 = b.$times(b, s$1);
            e >>>= 1;
            b = var10001;
            p = p;
         }
      }

      return p;
   }

   public Quaternion(final Object r, final Object i, final Object j, final Object k) {
      this.r = r;
      this.i = i;
      this.j = j;
      this.k = k;
      ScalaNumericAnyConversions.$init$(this);
      Product.$init$(this);
   }
}
