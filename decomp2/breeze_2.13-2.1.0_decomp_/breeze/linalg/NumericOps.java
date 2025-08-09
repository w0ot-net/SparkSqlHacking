package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.operators.HasOps$;
import breeze.storage.Zero;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011mba\u0002\u00180!\u0003\r\t\u0001\u000e\u0005\u0006\u0017\u0002!\t\u0001\u0014\u0005\u0006!\u0002!)!\u0015\u0005\u0006o\u0002!)\u0001\u001f\u0005\b\u0003\u0017\u0001AQAA\u0007\u0011\u001d\ti\u0002\u0001C\u0003\u0003?Aq!!\u000e\u0001\t\u000b\t9\u0004C\u0004\u0002H\u0001!)!!\u0013\t\u000f\u0005e\u0003\u0001\"\u0002\u0002\\!9\u0011\u0011\u000f\u0001\u0005\u0006\u0005M\u0004bBAE\u0001\u0011\u0015\u00111\u0012\u0005\b\u00037\u0003AQAAO\u0011\u001d\ti\u000b\u0001C\u0003\u0003_Cq!!2\u0001\t\u000b\t9\rC\u0004\u0002^\u0002!)!a8\t\u000f\u0005=\b\u0001\"\u0002\u0002r\"9!1\u0002\u0001\u0005\u0006\t5\u0001b\u0002B\u0014\u0001\u0011\u0015!\u0011\u0006\u0005\b\u0005\u0007\u0002AQ\u0001B#\u0011\u001d\u0011y\u0006\u0001C\u0003\u0005CBqAa\u001e\u0001\t\u000b\u0011I\bC\u0004\u0003\u0010\u0002!)A!%\t\u000f\t\u001d\u0006\u0001\"\u0002\u0003*\"9!\u0011\u0018\u0001\u0005\u0006\tm\u0006b\u0002Bf\u0001\u0011\u0015!QZ\u0004\b\u0005;|\u0003\u0012\u0001Bp\r\u0019qs\u0006#\u0001\u0003b\"9!1\u001d\u000e\u0005\u0002\t\u0015xa\u0002Bt5!\u0005!\u0011\u001e\u0004\b\u0005[T\u0002\u0012\u0001Bx\u0011\u001d\u0011\u0019/\bC\u0001\u0007\u00033aaa!\u001e\u0003\r\u0015\u0005BCBI?\t\u0005\t\u0015!\u0003\u0004\f\"9!1]\u0010\u0005\u0002\rM\u0005bBBN?\u0011\u00051Q\u0014\u0005\n\u0007?k\u0012\u0011!C\u0002\u0007CCqaa,\u001e\t\u0007\u0019\t\fC\u0004\u0004Dv!\u0019a!2\t\u000f\r]W\u0004b\u0001\u0004Z\"91q^\u000f\u0005\u0004\rE\bb\u0002C\u0004;\u0011\rA\u0011\u0002\u0004\n\u0005gT\u0002\u0013aA\u0011\u0005kDQaS\u0015\u0005\u00021CqAa>*\t\u0007\u0011I\u0010C\u0004\u0004.%\"\u0019aa\f\t\u000f\u0011}!\u0004b\u0001\u0005\"\tQa*^7fe&\u001cw\n]:\u000b\u0005A\n\u0014A\u00027j]\u0006dwMC\u00013\u0003\u0019\u0011'/Z3{K\u000e\u0001QCA\u001bC'\r\u0001a\u0007\u0010\t\u0003oij\u0011\u0001\u000f\u0006\u0002s\u0005)1oY1mC&\u00111\b\u000f\u0002\u0007\u0003:L(+\u001a4\u0011\u0007ur\u0004)D\u00010\u0013\tytFA\nJ[6,H/\u00192mK:+X.\u001a:jG>\u00038\u000f\u0005\u0002B\u00052\u0001AAB\"\u0001\t\u000b\u0007AI\u0001\u0003UQ&\u001c\u0018CA#I!\t9d)\u0003\u0002Hq\t9aj\u001c;iS:<\u0007CA\u001cJ\u0013\tQ\u0005HA\u0002B]f\fa\u0001J5oSR$C#A'\u0011\u0005]r\u0015BA(9\u0005\u0011)f.\u001b;\u0002\u000b\u0011\u0002H.^:\u0016\tI\u000bX/\u0016\u000b\u0003'N$\"\u0001V,\u0011\u0005\u0005+F!\u0002,\u0003\u0005\u0004!%\u0001\u0002+iCRDQ\u0001\u0017\u0002A\u0004e\u000b!a\u001c9\u0011\u000bi\u0003g\r\u001d+\u000f\u0005msV\"\u0001/\u000b\u0005u{\u0013!C8qKJ\fGo\u001c:t\u0013\tyF,A\u0003Pa\u0006#G-\u0003\u0002bE\n)\u0011*\u001c9me%\u00111\r\u001a\u0002\u0006+\u001a+hn\u0019\u0006\u0003KF\nqaZ3oKJL7M\u000b\u0002AO.\n\u0001\u000e\u0005\u0002j]6\t!N\u0003\u0002lY\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003[b\n!\"\u00198o_R\fG/[8o\u0013\ty'NA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\u0004\"!Q9\u0005\u000bI\u0014!\u0019\u0001#\u0003\u0003\tCQ\u0001\u001e\u0002A\u0002A\f\u0011A\u0019\u0003\u0006m\n\u0011\r\u0001\u0012\u0002\u0002\u0007\u0006IAeY8m_:$S-]\u000b\u0004s\u0006\u001dAc\u0001>\u0002\nQ\u0011\u0001i\u001f\u0005\u00061\u000e\u0001\u001d\u0001 \t\u0007{\u0006\u0005a-!\u0002\u000f\u0005ms\u0018BA@]\u0003\u0015y\u0005oU3u\u0013\r\t\u0019A\u0019\u0002\r\u0013:\u0004F.Y2f\u00136\u0004HN\r\t\u0004\u0003\u0006\u001dA!\u0002:\u0004\u0005\u0004!\u0005B\u0002;\u0004\u0001\u0004\t)!\u0001\b%G>dwN\u001c\u0013qYV\u001cH%Z9\u0016\t\u0005=\u0011\u0011\u0004\u000b\u0005\u0003#\tY\u0002F\u0002A\u0003'Aa\u0001\u0017\u0003A\u0004\u0005U\u0001C\u0002.\u0002\u0002\u0019\f9\u0002E\u0002B\u00033!QA\u001d\u0003C\u0002\u0011Ca\u0001\u001e\u0003A\u0002\u0005]\u0011a\u0004\u0013d_2|g\u000e\n;j[\u0016\u001cH%Z9\u0016\t\u0005\u0005\u0012\u0011\u0007\u000b\u0005\u0003G\t\u0019\u0004F\u0002A\u0003KAa\u0001W\u0003A\u0004\u0005\u001d\u0002cBA\u0015\u0003\u00031\u0017q\u0006\b\u00047\u0006-\u0012bAA\u00179\u0006Yq\n]'vYN\u001b\u0017\r\\1s!\r\t\u0015\u0011\u0007\u0003\u0006e\u0016\u0011\r\u0001\u0012\u0005\u0007i\u0016\u0001\r!a\f\u0002\u0011\u0011\u0002H.^:%KF,B!!\u000f\u0002DQ!\u00111HA#)\r\u0001\u0015Q\b\u0005\u00071\u001a\u0001\u001d!a\u0010\u0011\ri\u000b\tAZA!!\r\t\u00151\t\u0003\u0006e\u001a\u0011\r\u0001\u0012\u0005\u0007i\u001a\u0001\r!!\u0011\u0002\u0013\u0011\"\u0018.\\3tI\u0015\fX\u0003BA&\u0003+\"B!!\u0014\u0002XQ\u0019\u0001)a\u0014\t\ra;\u00019AA)!\u001d\tI#!\u0001g\u0003'\u00022!QA+\t\u0015\u0011xA1\u0001E\u0011\u0019!x\u00011\u0001\u0002T\u0005yAeY8m_:$S.\u001b8vg\u0012*\u0017/\u0006\u0003\u0002^\u00055D\u0003BA0\u0003_\"2\u0001QA1\u0011\u0019A\u0006\u0002q\u0001\u0002dA9\u0011QMA\u0001M\u0006-dbA.\u0002h%\u0019\u0011\u0011\u000e/\u0002\u000b=\u00038+\u001e2\u0011\u0007\u0005\u000bi\u0007B\u0003s\u0011\t\u0007A\t\u0003\u0004u\u0011\u0001\u0007\u00111N\u0001\u0012I\r|Gn\u001c8%a\u0016\u00148-\u001a8uI\u0015\fX\u0003BA;\u0003\u000b#B!a\u001e\u0002\bR\u0019\u0001)!\u001f\t\raK\u00019AA>!\u001d\ti(!\u0001g\u0003\u0007s1aWA@\u0013\r\t\t\tX\u0001\u0006\u001fBlu\u000e\u001a\t\u0004\u0003\u0006\u0015E!\u0002:\n\u0005\u0004!\u0005B\u0002;\n\u0001\u0004\t\u0019)A\u0006%a\u0016\u00148-\u001a8uI\u0015\fX\u0003BAG\u0003/#B!a$\u0002\u001aR\u0019\u0001)!%\t\raS\u00019AAJ!\u001d\ti(!\u0001g\u0003+\u00032!QAL\t\u0015\u0011(B1\u0001E\u0011\u0019!(\u00021\u0001\u0002\u0016\u0006IA%\\5okN$S-]\u000b\u0005\u0003?\u000bI\u000b\u0006\u0003\u0002\"\u0006-Fc\u0001!\u0002$\"1\u0001l\u0003a\u0002\u0003K\u0003r!!\u001a\u0002\u0002\u0019\f9\u000bE\u0002B\u0003S#QA]\u0006C\u0002\u0011Ca\u0001^\u0006A\u0002\u0005\u001d\u0016!\u0004\u0013d_2|g\u000e\n3jm\u0012*\u0017/\u0006\u0003\u00022\u0006\u0005G\u0003BAZ\u0003\u0007$2\u0001QA[\u0011\u0019AF\u0002q\u0001\u00028B9\u0011\u0011XA\u0001M\u0006}fbA.\u0002<&\u0019\u0011Q\u0018/\u0002\u000b=\u0003H)\u001b<\u0011\u0007\u0005\u000b\t\rB\u0003s\u0019\t\u0007A\t\u0003\u0004u\u0019\u0001\u0007\u0011qX\u0001\rI\r|Gn\u001c8%kB$S-]\u000b\u0005\u0003\u0013\fI\u000e\u0006\u0003\u0002L\u0006mGc\u0001!\u0002N\"1\u0001,\u0004a\u0002\u0003\u001f\u0004r!!5\u0002\u0002\u0019\f9ND\u0002\\\u0003'L1!!6]\u0003\u0015y\u0005\u000fU8x!\r\t\u0015\u0011\u001c\u0003\u0006e6\u0011\r\u0001\u0012\u0005\u0007i6\u0001\r!a6\u0002\u000f\u0011\"\u0017N\u001e\u0013fcV!\u0011\u0011]Av)\u0011\t\u0019/!<\u0015\u0007\u0001\u000b)\u000f\u0003\u0004Y\u001d\u0001\u000f\u0011q\u001d\t\b\u0003s\u000b\tAZAu!\r\t\u00151\u001e\u0003\u0006e:\u0011\r\u0001\u0012\u0005\u0007i:\u0001\r!!;\u0002!\u0011bWm]:%G>dwN\u001c\u0013mKN\u001cXCBAz\u0005\u000f\tI\u0010\u0006\u0003\u0002v\n%A\u0003BA|\u0003w\u00042!QA}\t\u00151vB1\u0001E\u0011\u0019Av\u0002q\u0001\u0002~BA\u0011q 1g\u0005\u000b\t9PD\u0002\\\u0005\u0003I1Aa\u0001]\u0003\u0011y\u0005\u000f\u0014+\u0011\u0007\u0005\u00139\u0001B\u0003s\u001f\t\u0007A\t\u0003\u0004u\u001f\u0001\u0007!QA\u0001\u000fI1,7o\u001d\u0013d_2|g\u000eJ3r+\u0019\u0011yAa\t\u0003\u0016Q!!\u0011\u0003B\u0013)\u0011\u0011\u0019Ba\u0006\u0011\u0007\u0005\u0013)\u0002B\u0003W!\t\u0007A\t\u0003\u0004Y!\u0001\u000f!\u0011\u0004\t\t\u00057\u0001gM!\t\u0003\u00149\u00191L!\b\n\u0007\t}A,A\u0003Pa2#V\tE\u0002B\u0005G!QA\u001d\tC\u0002\u0011Ca\u0001\u001e\tA\u0002\t\u0005\u0012A\u0006\u0013he\u0016\fG/\u001a:%G>dwN\u001c\u0013he\u0016\fG/\u001a:\u0016\r\t-\"q\bB\u0019)\u0011\u0011iC!\u0011\u0015\t\t=\"1\u0007\t\u0004\u0003\nEB!\u0002,\u0012\u0005\u0004!\u0005B\u0002-\u0012\u0001\b\u0011)\u0004\u0005\u0005\u00038\u00014'Q\bB\u0018\u001d\rY&\u0011H\u0005\u0004\u0005wa\u0016\u0001B(q\u000fR\u00032!\u0011B \t\u0015\u0011\u0018C1\u0001E\u0011\u0019!\u0018\u00031\u0001\u0003>\u0005\tBe\u001a:fCR,'\u000fJ2pY>tG%Z9\u0016\r\t\u001d#1\fB')\u0011\u0011IE!\u0018\u0015\t\t-#q\n\t\u0004\u0003\n5C!\u0002,\u0013\u0005\u0004!\u0005B\u0002-\u0013\u0001\b\u0011\t\u0006\u0005\u0005\u0003T\u00014'\u0011\fB&\u001d\rY&QK\u0005\u0004\u0005/b\u0016!B(q\u000fR+\u0005cA!\u0003\\\u0011)!O\u0005b\u0001\t\"1AO\u0005a\u0001\u00053\nQ\u0002J2pY>tG%Y7qI\u0015\fX\u0003\u0002B2\u0005g\"BA!\u001a\u0003vQ\u0019\u0001Ia\u001a\t\ra\u001b\u00029\u0001B5!\u001d\u0011Y'!\u0001g\u0005cr1a\u0017B7\u0013\r\u0011y\u0007X\u0001\u0006\u001fB\fe\u000e\u001a\t\u0004\u0003\nMD!\u0002:\u0014\u0005\u0004!\u0005B\u0002;\u0014\u0001\u0004\u0011\t(A\u0007%G>dwN\u001c\u0013cCJ$S-]\u000b\u0005\u0005w\u0012Y\t\u0006\u0003\u0003~\t5Ec\u0001!\u0003\u0000!1\u0001\f\u0006a\u0002\u0005\u0003\u0003rAa!\u0002\u0002\u0019\u0014IID\u0002\\\u0005\u000bK1Aa\"]\u0003\u0011y\u0005o\u0014:\u0011\u0007\u0005\u0013Y\tB\u0003s)\t\u0007A\t\u0003\u0004u)\u0001\u0007!\u0011R\u0001\u0010I\r|Gn\u001c8%kB$S\u000f\u001d\u0013fcV!!1\u0013BR)\u0011\u0011)J!*\u0015\u0007\u0001\u00139\n\u0003\u0004Y+\u0001\u000f!\u0011\u0014\t\b\u00057\u000b\tA\u001aBQ\u001d\rY&QT\u0005\u0004\u0005?c\u0016!B(q1>\u0014\bcA!\u0003$\u0012)!/\u0006b\u0001\t\"1A/\u0006a\u0001\u0005C\u000bq\u0001J1na\u0012*\u0017/\u0006\u0003\u0003,\nUF\u0003\u0002BW\u0005o#2\u0001\u0011BX\u0011\u0019Af\u0003q\u0001\u00032B9!1NA\u0001M\nM\u0006cA!\u00036\u0012)!O\u0006b\u0001\t\"1AO\u0006a\u0001\u0005g\u000bq\u0001\n2be\u0012*\u0017/\u0006\u0003\u0003>\n\u001dG\u0003\u0002B`\u0005\u0013$2\u0001\u0011Ba\u0011\u0019Av\u0003q\u0001\u0003DB9!1QA\u0001M\n\u0015\u0007cA!\u0003H\u0012)!o\u0006b\u0001\t\"1Ao\u0006a\u0001\u0005\u000b\f\u0011\u0002J;qIU\u0004H%Z9\u0016\t\t='\u0011\u001c\u000b\u0005\u0005#\u0014Y\u000eF\u0002A\u0005'Da\u0001\u0017\rA\u0004\tU\u0007c\u0002BN\u0003\u00031'q\u001b\t\u0004\u0003\neG!\u0002:\u0019\u0005\u0004!\u0005B\u0002;\u0019\u0001\u0004\u00119.\u0001\u0006Ok6,'/[2PaN\u0004\"!\u0010\u000e\u0014\u0005i1\u0014A\u0002\u001fj]&$h\b\u0006\u0002\u0003`\u00061\u0011I\u001d:bsN\u00042Aa;\u001e\u001b\u0005Q\"AB!se\u0006L8o\u0005\u0003\u001em\tE\bc\u0001BvS\t\t\u0012I\u001d:bsNdun\u001e)sS>\u0014\u0018\u000e^=\u0014\u0005%2\u0014A\u00062j]\u0006\u0014\u00180\u00169eCR,w\n\u001d$s_6$ek\u00149\u0016\u0011\tm8\u0011DB\u0010\u0007\u001b!BA!@\u0004$AQ!q`B\u0004\u0007\u0017\u0019\tb!\b\u000f\t\r\u000511A\u0007\u0002I&\u00191Q\u00013\u0002\u000bU3UO\\2\n\t\u0005\r1\u0011\u0002\u0006\u0004\u0007\u000b!\u0007cA!\u0004\u000e\u001111qB\u0016C\u0002\u0011\u0013!a\u00149\u0011\u000b]\u001a\u0019ba\u0006\n\u0007\rU\u0001HA\u0003BeJ\f\u0017\u0010E\u0002B\u00073!aaa\u0007,\u0005\u0004!%!\u0001,\u0011\u0007\u0005\u001by\u0002\u0002\u0004\u0004\"-\u0012\r\u0001\u0012\u0002\u0006\u001fRDWM\u001d\u0005\u00071.\u0002\u001da!\n\u0011\u0015\t}8qAB\u0006\u0007O\u0019i\u0002E\u0003>\u0007S\u00199\"C\u0002\u0004,=\u00121\u0002R3og\u00164Vm\u0019;pe\u0006\u0001\"-\u001b8bef|\u0005O\u0012:p[\u00123v\n]\u000b\u000b\u0007c\u0019Ie!\u0014\u0004<\rMC\u0003CB\u001a\u0007/\u001ayfa\u001c\u0011\u0019\t}8QGB\u001d\u0007\u000b\u001aYea\u0014\n\t\r]2\u0011\u0002\u0002\u0007+&k\u0007\u000f\u001c\u001a\u0011\u0007\u0005\u001bY\u0004B\u0004\u0004\u00101\u0012\ra!\u0010\u0012\u0007\u0015\u001by\u0004E\u0002\\\u0007\u0003J1aa\u0011]\u0005\u0019y\u0005\u000fV=qKB)qga\u0005\u0004HA\u0019\u0011i!\u0013\u0005\r\rmAF1\u0001E!\r\t5Q\n\u0003\u0007\u0007Ca#\u0019\u0001#\u0011\u000b]\u001a\u0019b!\u0015\u0011\u0007\u0005\u001b\u0019\u0006\u0002\u0004\u0004V1\u0012\r\u0001\u0012\u0002\u0002+\"1\u0001\f\fa\u0002\u00073\u0002BBa@\u00046\re21LB&\u0007;\u0002R!PB\u0015\u0007\u000f\u0002R!PB\u0015\u0007#Bqa!\u0019-\u0001\b\u0019\u0019'A\u0002nC:\u0004ba!\u001a\u0004l\rESBAB4\u0015\r\u0019I\u0007O\u0001\be\u00164G.Z2u\u0013\u0011\u0019iga\u001a\u0003\u0011\rc\u0017m]:UC\u001eDqa!\u001d-\u0001\b\u0019\u0019(\u0001\u0003{KJ|\u0007CBB;\u0007w\u001a\t&\u0004\u0002\u0004x)\u00191\u0011P\u0019\u0002\u000fM$xN]1hK&!1QPB<\u0005\u0011QVM]8*\u0005%jBC\u0001Bu\u0005E\t%O]1z\u0013NtU/\\3sS\u000e|\u0005o]\u000b\u0005\u0007\u000f\u001byi\u0005\u0003 m\r%\u0005\u0003B\u001f\u0001\u0007\u0017\u0003RaNB\n\u0007\u001b\u00032!QBH\t\u0019\u0019Yb\bb\u0001\t\u0006\u0019\u0011M\u001d:\u0015\t\rU5\u0011\u0014\t\u0006\u0007/{2QR\u0007\u0002;!91\u0011S\u0011A\u0002\r-\u0015\u0001\u0002:faJ,\"aa#\u0002#\u0005\u0013(/Y=Jg:+X.\u001a:jG>\u00038/\u0006\u0003\u0004$\u000e%F\u0003BBS\u0007W\u0003Raa& \u0007O\u00032!QBU\t\u0019\u0019Yb\tb\u0001\t\"91\u0011S\u0012A\u0002\r5\u0006#B\u001c\u0004\u0014\r\u001d\u0016\u0001\u00062j]\u0006\u0014\u0018p\u00149Ge>lGIV(qe\u0005#G-\u0006\u0003\u00044\u000emF\u0003BB[\u0007{\u0003\u0002B\u00171\u00048\u000e]6q\u0017\t\u0006o\rM1\u0011\u0018\t\u0004\u0003\u000emFABB\u000eI\t\u0007A\t\u0003\u0004YI\u0001\u000f1q\u0018\t\t5\u0002\u001c\tm!1\u0004BB)Qh!\u000b\u0004:\u0006A\"-\u001b8bef|\u0005/\u00113e\rJ|W\u000e\u0012,V\u001fB\fE\r\u001a\u001a\u0016\t\r\u001d7q\u001a\u000b\u0005\u0007\u0013\u001c\t\u000e\u0005\u0005[A\u000e-7QZBf!\u0015941CBg!\r\t5q\u001a\u0003\u0007\u00077)#\u0019\u0001#\t\ra+\u00039ABj!!Q\u0006m!6\u0004N\u000eU\u0007#B\u001f\u0004*\r5\u0017!\u00052j]\u0006\u0014\u0018p\u00149Ge>lGIV(qeU111\\Bt\u0007C$Ba!8\u0004jBa!q`B\u001b\u0007?\u001c\u0019oa9\u0004dB\u0019\u0011i!9\u0005\u000f\r=aE1\u0001\u0004>A)qga\u0005\u0004fB\u0019\u0011ia:\u0005\r\rmaE1\u0001E\u0011\u0019Af\u0005q\u0001\u0004lBa!q`B\u001b\u0007?\u001cio!<\u0004nB)Qh!\u000b\u0004f\u0006A\"-\u001b8bef,\u0006\u000fZ1uK>\u0003hI]8n\tZ#ek\u00149\u0016\r\rM8q`B})\u0011\u0019)\u0010\"\u0001\u0011\u0015\t}8qAB|\u0007w\u001cY\u0010E\u0002B\u0007s$qaa\u0004(\u0005\u0004\u0019i\u0004E\u00038\u0007'\u0019i\u0010E\u0002B\u0007\u007f$aaa\u0007(\u0005\u0004!\u0005B\u0002-(\u0001\b!\u0019\u0001\u0005\u0006\u0003\u0000\u000e\u001d1q\u001fC\u0003\t\u000b\u0001R!PB\u0015\u0007{\f!CY5oCJLx\n\u001d$s_6$e+V(qeU1A1\u0002C\f\t#!B\u0001\"\u0004\u0005\u001aAa!q`B\u001b\t\u001f!\u0019\u0002\"\u0006\u0005\u0014A\u0019\u0011\t\"\u0005\u0005\u000f\r=\u0001F1\u0001\u0004>A)qga\u0005\u0005\u0016A\u0019\u0011\tb\u0006\u0005\r\rm\u0001F1\u0001E\u0011\u0019A\u0006\u0006q\u0001\u0005\u001cAa!q`B\u001b\t\u001f!i\u0002\"\u0006\u0005\u001eA)Qh!\u000b\u0005\u0016\u00059\"-\u001b8bef,\u0006\u000fZ1uK>\u0003hI]8n\tZ3v\n]\u000b\t\tG!y\u0003\"\u000b\u00054Q!AQ\u0005C\u001b!)\u0011ypa\u0002\u0005(\u0011-B\u0011\u0007\t\u0004\u0003\u0012%BABB\b[\t\u0007A\tE\u00038\u0007'!i\u0003E\u0002B\t_!aaa\u0007.\u0005\u0004!\u0005cA!\u00054\u001111QK\u0017C\u0002\u0011Ca\u0001W\u0017A\u0004\u0011]\u0002C\u0003B\u0000\u0007\u000f!9\u0003\"\u000f\u00052A)Qh!\u000b\u0005.\u0001"
)
public interface NumericOps extends ImmutableNumericOps {
   static UFunc.InPlaceImpl2 binaryUpdateOpFromDVVOp(final UFunc.InPlaceImpl2 op) {
      return NumericOps$.MODULE$.binaryUpdateOpFromDVVOp(op);
   }

   // $FF: synthetic method
   static Object $plus$(final NumericOps $this, final Object b, final UFunc.UImpl2 op) {
      return $this.$plus(b, op);
   }

   default Object $plus(final Object b, final UFunc.UImpl2 op) {
      return op.apply(this.repr(), b);
   }

   // $FF: synthetic method
   static Object $colon$eq$(final NumericOps $this, final Object b, final UFunc.InPlaceImpl2 op) {
      return $this.$colon$eq(b, op);
   }

   default Object $colon$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      op.apply(this.repr(), b);
      return this.repr();
   }

   // $FF: synthetic method
   static Object $colon$plus$eq$(final NumericOps $this, final Object b, final UFunc.InPlaceImpl2 op) {
      return $this.$colon$plus$eq(b, op);
   }

   default Object $colon$plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      op.apply(this.repr(), b);
      return this.repr();
   }

   // $FF: synthetic method
   static Object $colon$times$eq$(final NumericOps $this, final Object b, final UFunc.InPlaceImpl2 op) {
      return $this.$colon$times$eq(b, op);
   }

   default Object $colon$times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      op.apply(this.repr(), b);
      return this.repr();
   }

   // $FF: synthetic method
   static Object $plus$eq$(final NumericOps $this, final Object b, final UFunc.InPlaceImpl2 op) {
      return $this.$plus$eq(b, op);
   }

   default Object $plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return this.$colon$plus$eq(b, op);
   }

   // $FF: synthetic method
   static Object $times$eq$(final NumericOps $this, final Object b, final UFunc.InPlaceImpl2 op) {
      return $this.$times$eq(b, op);
   }

   default Object $times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return this.$colon$times$eq(b, op);
   }

   // $FF: synthetic method
   static Object $colon$minus$eq$(final NumericOps $this, final Object b, final UFunc.InPlaceImpl2 op) {
      return $this.$colon$minus$eq(b, op);
   }

   default Object $colon$minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      op.apply(this.repr(), b);
      return this.repr();
   }

   // $FF: synthetic method
   static Object $colon$percent$eq$(final NumericOps $this, final Object b, final UFunc.InPlaceImpl2 op) {
      return $this.$colon$percent$eq(b, op);
   }

   default Object $colon$percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      op.apply(this.repr(), b);
      return this.repr();
   }

   // $FF: synthetic method
   static Object $percent$eq$(final NumericOps $this, final Object b, final UFunc.InPlaceImpl2 op) {
      return $this.$percent$eq(b, op);
   }

   default Object $percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return this.$colon$percent$eq(b, op);
   }

   // $FF: synthetic method
   static Object $minus$eq$(final NumericOps $this, final Object b, final UFunc.InPlaceImpl2 op) {
      return $this.$minus$eq(b, op);
   }

   default Object $minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return this.$colon$minus$eq(b, op);
   }

   // $FF: synthetic method
   static Object $colon$div$eq$(final NumericOps $this, final Object b, final UFunc.InPlaceImpl2 op) {
      return $this.$colon$div$eq(b, op);
   }

   default Object $colon$div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      op.apply(this.repr(), b);
      return this.repr();
   }

   // $FF: synthetic method
   static Object $colon$up$eq$(final NumericOps $this, final Object b, final UFunc.InPlaceImpl2 op) {
      return $this.$colon$up$eq(b, op);
   }

   default Object $colon$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      op.apply(this.repr(), b);
      return this.repr();
   }

   // $FF: synthetic method
   static Object $div$eq$(final NumericOps $this, final Object b, final UFunc.InPlaceImpl2 op) {
      return $this.$div$eq(b, op);
   }

   default Object $div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return this.$colon$div$eq(b, op);
   }

   // $FF: synthetic method
   static Object $less$colon$less$(final NumericOps $this, final Object b, final UFunc.UImpl2 op) {
      return $this.$less$colon$less(b, op);
   }

   default Object $less$colon$less(final Object b, final UFunc.UImpl2 op) {
      return op.apply(this.repr(), b);
   }

   // $FF: synthetic method
   static Object $less$colon$eq$(final NumericOps $this, final Object b, final UFunc.UImpl2 op) {
      return $this.$less$colon$eq(b, op);
   }

   default Object $less$colon$eq(final Object b, final UFunc.UImpl2 op) {
      return op.apply(this.repr(), b);
   }

   // $FF: synthetic method
   static Object $greater$colon$greater$(final NumericOps $this, final Object b, final UFunc.UImpl2 op) {
      return $this.$greater$colon$greater(b, op);
   }

   default Object $greater$colon$greater(final Object b, final UFunc.UImpl2 op) {
      return op.apply(this.repr(), b);
   }

   // $FF: synthetic method
   static Object $greater$colon$eq$(final NumericOps $this, final Object b, final UFunc.UImpl2 op) {
      return $this.$greater$colon$eq(b, op);
   }

   default Object $greater$colon$eq(final Object b, final UFunc.UImpl2 op) {
      return op.apply(this.repr(), b);
   }

   // $FF: synthetic method
   static Object $colon$amp$eq$(final NumericOps $this, final Object b, final UFunc.InPlaceImpl2 op) {
      return $this.$colon$amp$eq(b, op);
   }

   default Object $colon$amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      op.apply(this.repr(), b);
      return this.repr();
   }

   // $FF: synthetic method
   static Object $colon$bar$eq$(final NumericOps $this, final Object b, final UFunc.InPlaceImpl2 op) {
      return $this.$colon$bar$eq(b, op);
   }

   default Object $colon$bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      op.apply(this.repr(), b);
      return this.repr();
   }

   // $FF: synthetic method
   static Object $colon$up$up$eq$(final NumericOps $this, final Object b, final UFunc.InPlaceImpl2 op) {
      return $this.$colon$up$up$eq(b, op);
   }

   default Object $colon$up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      op.apply(this.repr(), b);
      return this.repr();
   }

   // $FF: synthetic method
   static Object $amp$eq$(final NumericOps $this, final Object b, final UFunc.InPlaceImpl2 op) {
      return $this.$amp$eq(b, op);
   }

   default Object $amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      op.apply(this.repr(), b);
      return this.repr();
   }

   // $FF: synthetic method
   static Object $bar$eq$(final NumericOps $this, final Object b, final UFunc.InPlaceImpl2 op) {
      return $this.$bar$eq(b, op);
   }

   default Object $bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      op.apply(this.repr(), b);
      return this.repr();
   }

   // $FF: synthetic method
   static Object $up$up$eq$(final NumericOps $this, final Object b, final UFunc.InPlaceImpl2 op) {
      return $this.$up$up$eq(b, op);
   }

   default Object $up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      op.apply(this.repr(), b);
      return this.repr();
   }

   static void $init$(final NumericOps $this) {
   }

   public static class Arrays$ implements ArraysLowPriority {
      public static final Arrays$ MODULE$ = new Arrays$();

      static {
         NumericOps.ArraysLowPriority.$init$(MODULE$);
      }

      public UFunc.InPlaceImpl2 binaryUpdateOpFromDVOp(final UFunc.InPlaceImpl2 op) {
         return NumericOps.ArraysLowPriority.super.binaryUpdateOpFromDVOp(op);
      }

      public UFunc.UImpl2 binaryOpFromDVOp(final UFunc.UImpl2 op, final ClassTag man, final Zero zero) {
         return NumericOps.ArraysLowPriority.super.binaryOpFromDVOp(op, man, zero);
      }

      public NumericOps$Arrays$ArrayIsNumericOps ArrayIsNumericOps(final Object arr) {
         return new NumericOps$Arrays$ArrayIsNumericOps(arr);
      }

      public UFunc.UImpl2 binaryOpFromDVOp2Add(final UFunc.UImpl2 op) {
         return new UFunc.UImpl2(op) {
            private final UFunc.UImpl2 op$1;

            public double apply$mcDDD$sp(final double v, final double v2) {
               return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
            }

            public float apply$mcDDF$sp(final double v, final double v2) {
               return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
            }

            public int apply$mcDDI$sp(final double v, final double v2) {
               return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
            }

            public double apply$mcDFD$sp(final double v, final float v2) {
               return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
            }

            public float apply$mcDFF$sp(final double v, final float v2) {
               return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
            }

            public int apply$mcDFI$sp(final double v, final float v2) {
               return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
            }

            public double apply$mcDID$sp(final double v, final int v2) {
               return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
            }

            public float apply$mcDIF$sp(final double v, final int v2) {
               return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
            }

            public int apply$mcDII$sp(final double v, final int v2) {
               return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
            }

            public double apply$mcFDD$sp(final float v, final double v2) {
               return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
            }

            public float apply$mcFDF$sp(final float v, final double v2) {
               return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
            }

            public int apply$mcFDI$sp(final float v, final double v2) {
               return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
            }

            public double apply$mcFFD$sp(final float v, final float v2) {
               return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
            }

            public float apply$mcFFF$sp(final float v, final float v2) {
               return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
            }

            public int apply$mcFFI$sp(final float v, final float v2) {
               return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
            }

            public double apply$mcFID$sp(final float v, final int v2) {
               return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
            }

            public float apply$mcFIF$sp(final float v, final int v2) {
               return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
            }

            public int apply$mcFII$sp(final float v, final int v2) {
               return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
            }

            public double apply$mcIDD$sp(final int v, final double v2) {
               return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
            }

            public float apply$mcIDF$sp(final int v, final double v2) {
               return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
            }

            public int apply$mcIDI$sp(final int v, final double v2) {
               return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
            }

            public double apply$mcIFD$sp(final int v, final float v2) {
               return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
            }

            public float apply$mcIFF$sp(final int v, final float v2) {
               return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
            }

            public int apply$mcIFI$sp(final int v, final float v2) {
               return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
            }

            public double apply$mcIID$sp(final int v, final int v2) {
               return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
            }

            public float apply$mcIIF$sp(final int v, final int v2) {
               return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
            }

            public int apply$mcIII$sp(final int v, final int v2) {
               return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
            }

            public Object apply(final Object a, final Object b) {
               DenseVector r = (DenseVector)this.op$1.apply(DenseVector$.MODULE$.apply(a), DenseVector$.MODULE$.apply(b));
               return r.offset() == 0 && r.stride() == 1 ? r.data() : r.copy().data();
            }

            public {
               this.op$1 = op$1;
            }
         };
      }

      public UFunc.UImpl2 binaryOpAddFromDVUOpAdd2(final UFunc.UImpl2 op) {
         return new UFunc.UImpl2(op) {
            private final UFunc.UImpl2 op$2;

            public double apply$mcDDD$sp(final double v, final double v2) {
               return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
            }

            public float apply$mcDDF$sp(final double v, final double v2) {
               return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
            }

            public int apply$mcDDI$sp(final double v, final double v2) {
               return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
            }

            public double apply$mcDFD$sp(final double v, final float v2) {
               return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
            }

            public float apply$mcDFF$sp(final double v, final float v2) {
               return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
            }

            public int apply$mcDFI$sp(final double v, final float v2) {
               return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
            }

            public double apply$mcDID$sp(final double v, final int v2) {
               return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
            }

            public float apply$mcDIF$sp(final double v, final int v2) {
               return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
            }

            public int apply$mcDII$sp(final double v, final int v2) {
               return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
            }

            public double apply$mcFDD$sp(final float v, final double v2) {
               return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
            }

            public float apply$mcFDF$sp(final float v, final double v2) {
               return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
            }

            public int apply$mcFDI$sp(final float v, final double v2) {
               return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
            }

            public double apply$mcFFD$sp(final float v, final float v2) {
               return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
            }

            public float apply$mcFFF$sp(final float v, final float v2) {
               return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
            }

            public int apply$mcFFI$sp(final float v, final float v2) {
               return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
            }

            public double apply$mcFID$sp(final float v, final int v2) {
               return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
            }

            public float apply$mcFIF$sp(final float v, final int v2) {
               return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
            }

            public int apply$mcFII$sp(final float v, final int v2) {
               return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
            }

            public double apply$mcIDD$sp(final int v, final double v2) {
               return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
            }

            public float apply$mcIDF$sp(final int v, final double v2) {
               return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
            }

            public int apply$mcIDI$sp(final int v, final double v2) {
               return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
            }

            public double apply$mcIFD$sp(final int v, final float v2) {
               return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
            }

            public float apply$mcIFF$sp(final int v, final float v2) {
               return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
            }

            public int apply$mcIFI$sp(final int v, final float v2) {
               return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
            }

            public double apply$mcIID$sp(final int v, final int v2) {
               return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
            }

            public float apply$mcIIF$sp(final int v, final int v2) {
               return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
            }

            public int apply$mcIII$sp(final int v, final int v2) {
               return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
            }

            public Object apply(final Object a, final Object b) {
               DenseVector r = (DenseVector)this.op$2.apply(DenseVector$.MODULE$.apply(a), b);
               return r.offset() == 0 && r.stride() == 1 ? r.data() : r.copy().data();
            }

            public {
               this.op$2 = op$2;
            }
         };
      }

      public UFunc.UImpl2 binaryOpFromDVOp2(final UFunc.UImpl2 op) {
         return new UFunc.UImpl2(op) {
            private final UFunc.UImpl2 op$3;

            public double apply$mcDDD$sp(final double v, final double v2) {
               return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
            }

            public float apply$mcDDF$sp(final double v, final double v2) {
               return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
            }

            public int apply$mcDDI$sp(final double v, final double v2) {
               return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
            }

            public double apply$mcDFD$sp(final double v, final float v2) {
               return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
            }

            public float apply$mcDFF$sp(final double v, final float v2) {
               return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
            }

            public int apply$mcDFI$sp(final double v, final float v2) {
               return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
            }

            public double apply$mcDID$sp(final double v, final int v2) {
               return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
            }

            public float apply$mcDIF$sp(final double v, final int v2) {
               return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
            }

            public int apply$mcDII$sp(final double v, final int v2) {
               return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
            }

            public double apply$mcFDD$sp(final float v, final double v2) {
               return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
            }

            public float apply$mcFDF$sp(final float v, final double v2) {
               return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
            }

            public int apply$mcFDI$sp(final float v, final double v2) {
               return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
            }

            public double apply$mcFFD$sp(final float v, final float v2) {
               return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
            }

            public float apply$mcFFF$sp(final float v, final float v2) {
               return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
            }

            public int apply$mcFFI$sp(final float v, final float v2) {
               return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
            }

            public double apply$mcFID$sp(final float v, final int v2) {
               return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
            }

            public float apply$mcFIF$sp(final float v, final int v2) {
               return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
            }

            public int apply$mcFII$sp(final float v, final int v2) {
               return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
            }

            public double apply$mcIDD$sp(final int v, final double v2) {
               return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
            }

            public float apply$mcIDF$sp(final int v, final double v2) {
               return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
            }

            public int apply$mcIDI$sp(final int v, final double v2) {
               return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
            }

            public double apply$mcIFD$sp(final int v, final float v2) {
               return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
            }

            public float apply$mcIFF$sp(final int v, final float v2) {
               return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
            }

            public int apply$mcIFI$sp(final int v, final float v2) {
               return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
            }

            public double apply$mcIID$sp(final int v, final int v2) {
               return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
            }

            public float apply$mcIIF$sp(final int v, final int v2) {
               return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
            }

            public int apply$mcIII$sp(final int v, final int v2) {
               return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
            }

            public Object apply(final Object a, final Object b) {
               DenseVector r = (DenseVector)this.op$3.apply(DenseVector$.MODULE$.apply(a), DenseVector$.MODULE$.apply(b));
               return r.offset() == 0 && r.stride() == 1 ? r.data() : r.copy().data();
            }

            public {
               this.op$3 = op$3;
            }
         };
      }

      public UFunc.InPlaceImpl2 binaryUpdateOpFromDVDVOp(final UFunc.InPlaceImpl2 op) {
         return new UFunc.InPlaceImpl2(op) {
            private final UFunc.InPlaceImpl2 op$4;

            public void apply$mcD$sp(final Object v, final double v2) {
               UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
            }

            public void apply$mcF$sp(final Object v, final float v2) {
               UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
            }

            public void apply$mcI$sp(final Object v, final int v2) {
               UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
            }

            public void apply(final Object a, final Object b) {
               this.op$4.apply(DenseVector$.MODULE$.apply(a), DenseVector$.MODULE$.apply(b));
            }

            public {
               this.op$4 = op$4;
            }
         };
      }

      public UFunc.UImpl2 binaryOpFromDVUOp2(final UFunc.UImpl2 op) {
         return new UFunc.UImpl2(op) {
            private final UFunc.UImpl2 op$5;

            public double apply$mcDDD$sp(final double v, final double v2) {
               return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
            }

            public float apply$mcDDF$sp(final double v, final double v2) {
               return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
            }

            public int apply$mcDDI$sp(final double v, final double v2) {
               return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
            }

            public double apply$mcDFD$sp(final double v, final float v2) {
               return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
            }

            public float apply$mcDFF$sp(final double v, final float v2) {
               return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
            }

            public int apply$mcDFI$sp(final double v, final float v2) {
               return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
            }

            public double apply$mcDID$sp(final double v, final int v2) {
               return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
            }

            public float apply$mcDIF$sp(final double v, final int v2) {
               return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
            }

            public int apply$mcDII$sp(final double v, final int v2) {
               return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
            }

            public double apply$mcFDD$sp(final float v, final double v2) {
               return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
            }

            public float apply$mcFDF$sp(final float v, final double v2) {
               return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
            }

            public int apply$mcFDI$sp(final float v, final double v2) {
               return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
            }

            public double apply$mcFFD$sp(final float v, final float v2) {
               return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
            }

            public float apply$mcFFF$sp(final float v, final float v2) {
               return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
            }

            public int apply$mcFFI$sp(final float v, final float v2) {
               return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
            }

            public double apply$mcFID$sp(final float v, final int v2) {
               return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
            }

            public float apply$mcFIF$sp(final float v, final int v2) {
               return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
            }

            public int apply$mcFII$sp(final float v, final int v2) {
               return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
            }

            public double apply$mcIDD$sp(final int v, final double v2) {
               return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
            }

            public float apply$mcIDF$sp(final int v, final double v2) {
               return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
            }

            public int apply$mcIDI$sp(final int v, final double v2) {
               return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
            }

            public double apply$mcIFD$sp(final int v, final float v2) {
               return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
            }

            public float apply$mcIFF$sp(final int v, final float v2) {
               return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
            }

            public int apply$mcIFI$sp(final int v, final float v2) {
               return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
            }

            public double apply$mcIID$sp(final int v, final int v2) {
               return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
            }

            public float apply$mcIIF$sp(final int v, final int v2) {
               return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
            }

            public int apply$mcIII$sp(final int v, final int v2) {
               return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
            }

            public Object apply(final Object a, final Object b) {
               DenseVector r = (DenseVector)this.op$5.apply(DenseVector$.MODULE$.apply(a), b);
               return r.offset() == 0 && r.stride() == 1 ? r.data() : r.copy().data();
            }

            public {
               this.op$5 = op$5;
            }
         };
      }
   }

   public interface ArraysLowPriority {
      default UFunc.InPlaceImpl2 binaryUpdateOpFromDVOp(final UFunc.InPlaceImpl2 op) {
         return new UFunc.InPlaceImpl2(op) {
            private final UFunc.InPlaceImpl2 op$6;

            public void apply$mcD$sp(final Object v, final double v2) {
               UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
            }

            public void apply$mcF$sp(final Object v, final float v2) {
               UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
            }

            public void apply$mcI$sp(final Object v, final int v2) {
               UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
            }

            public void apply(final Object a, final Object b) {
               this.op$6.apply(DenseVector$.MODULE$.apply(a), b);
            }

            public {
               this.op$6 = op$6;
            }
         };
      }

      default UFunc.UImpl2 binaryOpFromDVOp(final UFunc.UImpl2 op, final ClassTag man, final Zero zero) {
         return new UFunc.UImpl2(op, man, zero) {
            private final UFunc.UImpl2 op$7;
            private final ClassTag man$1;
            private final Zero zero$1;

            public double apply$mcDDD$sp(final double v, final double v2) {
               return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
            }

            public float apply$mcDDF$sp(final double v, final double v2) {
               return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
            }

            public int apply$mcDDI$sp(final double v, final double v2) {
               return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
            }

            public double apply$mcDFD$sp(final double v, final float v2) {
               return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
            }

            public float apply$mcDFF$sp(final double v, final float v2) {
               return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
            }

            public int apply$mcDFI$sp(final double v, final float v2) {
               return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
            }

            public double apply$mcDID$sp(final double v, final int v2) {
               return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
            }

            public float apply$mcDIF$sp(final double v, final int v2) {
               return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
            }

            public int apply$mcDII$sp(final double v, final int v2) {
               return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
            }

            public double apply$mcFDD$sp(final float v, final double v2) {
               return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
            }

            public float apply$mcFDF$sp(final float v, final double v2) {
               return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
            }

            public int apply$mcFDI$sp(final float v, final double v2) {
               return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
            }

            public double apply$mcFFD$sp(final float v, final float v2) {
               return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
            }

            public float apply$mcFFF$sp(final float v, final float v2) {
               return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
            }

            public int apply$mcFFI$sp(final float v, final float v2) {
               return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
            }

            public double apply$mcFID$sp(final float v, final int v2) {
               return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
            }

            public float apply$mcFIF$sp(final float v, final int v2) {
               return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
            }

            public int apply$mcFII$sp(final float v, final int v2) {
               return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
            }

            public double apply$mcIDD$sp(final int v, final double v2) {
               return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
            }

            public float apply$mcIDF$sp(final int v, final double v2) {
               return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
            }

            public int apply$mcIDI$sp(final int v, final double v2) {
               return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
            }

            public double apply$mcIFD$sp(final int v, final float v2) {
               return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
            }

            public float apply$mcIFF$sp(final int v, final float v2) {
               return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
            }

            public int apply$mcIFI$sp(final int v, final float v2) {
               return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
            }

            public double apply$mcIID$sp(final int v, final int v2) {
               return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
            }

            public float apply$mcIIF$sp(final int v, final int v2) {
               return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
            }

            public int apply$mcIII$sp(final int v, final int v2) {
               return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
            }

            public Object apply(final Object a, final Object b) {
               DenseVector r = (DenseVector)this.op$7.apply(DenseVector$.MODULE$.apply(a), b);
               Object var10000;
               if (r.offset() == 0 && r.stride() == 1) {
                  var10000 = r.data();
               } else {
                  DenseVector z = DenseVector$.MODULE$.zeros(r.length(), this.man$1, this.zero$1);
                  z.$colon$eq(r, HasOps$.MODULE$.impl_OpSet_InPlace_DV_DV());
                  var10000 = z.data();
               }

               return var10000;
            }

            public {
               this.op$7 = op$7;
               this.man$1 = man$1;
               this.zero$1 = zero$1;
            }
         };
      }

      static void $init$(final ArraysLowPriority $this) {
      }
   }
}
