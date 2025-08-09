package spire.math;

import algebra.ring.CommutativeRig;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r\u001dt!B-[\u0011\u0003yf!B1[\u0011\u0003\u0011\u0007\"\u00027\u0002\t\u0003i\u0007\"\u00028\u0002\t\u000by\u0007B\u00028\u0002\t\u000b\u0011Y\u0002C\u0005\u0003\"\u0005\u0011\r\u0011\"\u0002\u00020\"9!1E\u0001!\u0002\u001b\u0001\b\"\u0003B\u0014\u0003\t\u0007IQAAX\u0011\u001d\u0011I#\u0001Q\u0001\u000eADqA!\f\u0002\t\u000b\u0011y\u0003C\u0004\u00036\u0005!)Aa\u000e\t\u000f\tm\u0012\u0001\"\u0002\u0003>!9!\u0011I\u0001\u0005\u0006\t\r\u0003b\u0002B$\u0003\u0011\u0015!\u0011\n\u0005\b\u0005\u001b\nAQ\u0001B(\u0011\u001d\u0011\u0019&\u0001C\u0003\u0005+BqA!\u0017\u0002\t\u000b\u0011Y\u0006C\u0004\u0003`\u0005!)A!\u0019\t\u000f\t\u0015\u0014\u0001\"\u0002\u0003h!9!1N\u0001\u0005\u0006\t5\u0004b\u0002B9\u0003\u0011\u0015!1\u000f\u0005\b\u0005o\nAQ\u0001B=\u0011\u001d\u0011i(\u0001C\u0003\u0005\u007fBqAa!\u0002\t\u000b\u0011)\tC\u0004\u0003\u000e\u0006!)Aa$\t\u000f\t]\u0015\u0001\"\u0002\u0003\u001a\"9!\u0011U\u0001\u0005\u0006\t\r\u0006b\u0002BV\u0003\u0011\u0015!Q\u0016\u0005\b\u0005k\u000bAQ\u0001B\\\u0011\u001d\u0011y,\u0001C\u0003\u0005\u0003DqA!3\u0002\t\u000b\u0011Y\rC\u0004\u0003T\u0006!)A!6\t\u000f\te\u0017\u0001\"\u0002\u0003\\\"9!1]\u0001\u0005\u0006\t\u0015\bb\u0002Bw\u0003\u0011\u0015!q\u001e\u0005\b\u0005o\fAQ\u0001B}\u0011\u001d\u0019\t!\u0001C\u0003\u0007\u0007Aqaa\u0003\u0002\t\u000b\u0019i\u0001C\u0004\u0004\u0012\u0005!)aa\u0005\t\u000f\rm\u0011\u0001\"\u0002\u0004\u001e!91QE\u0001\u0005\u0006\r\u001d\u0002bBB\u0018\u0003\u0011\u00151\u0011\u0007\u0005\b\u0007s\tAQAB\u001e\u0011\u001d\u0019\u0019%\u0001C\u0003\u0007\u000bBqa!\u0014\u0002\t\u000b\u0019y\u0005C\u0005\u0004X\u0005\t\t\u0011\"\u0002\u0004Z!I1QL\u0001\u0002\u0002\u0013\u00151q\f\u0004\u0005Cj\u0013\u0011\u000f\u0003\u0005v_\t\u0015\r\u0011\"\u0001w\u0011!QxF!A!\u0002\u00139\b\"\u000270\t\u0003Y\b\"B?0\t\u0003q\bbBA\u0003_\u0011\u0005\u0011q\u0001\u0005\b\u0003\u001fyC\u0011AA\t\u0011\u0019\tIb\fC\u0001m\"9\u00111D\u0018\u0005\u0002\u0005u\u0001bBA\u0013_\u0011\u0005\u0011q\u0005\u0005\b\u0003_yC\u0011AA\u0019\u0011\u001d\tId\fC\u0001\u0003wAq!!\u00160\t\u0003\t9\u0006C\u0004\u0002`=\"\t!a\u0016\t\u000f\u0005\u0005t\u0006\"\u0001\u0002X!9\u00111M\u0018\u0005\u0002\u0005]\u0003bBA3_\u0011\u0005\u0011q\u000b\u0005\b\u0003OzC\u0011IA5\u0011\u001d\tYh\fC\u0001\u0003{Bq!a!0\t\u0003\t)\tC\u0004\u0002\n>\"\t!a#\t\u000f\u0005=u\u0006\"\u0001\u0002\u0012\"9\u0011QS\u0018\u0005\u0002\u0005]\u0005bBAN_\u0011\u0005\u0011Q\u0014\u0005\b\u0003C{C\u0011AAR\u0011\u001d\t9k\fC\u0001\u0003SCq!!,0\t\u0003\ty\u000bC\u0004\u00022>\"\t!a-\t\u000f\u0005]v\u0006\"\u0001\u0002:\"9\u0011QX\u0018\u0005\u0002\u0005}\u0006bBAb_\u0011\u0005\u0011Q\u0019\u0005\b\u0003\u0013|C\u0011AAf\u0011\u001d\tym\fC\u0001\u0003_Cq!!50\t\u0003\t\u0019\u000eC\u0004\u0002Z>\"\t!a7\t\u000f\u0005}w\u0006\"\u0001\u0002b\"9\u0011Q]\u0018\u0005\u0002\u0005\u001d\bbBAv_\u0011\u0005\u0011Q\u001e\u0005\b\u0003c|C\u0011AAz\u0011\u001d\t9p\fC\u0001\u0003sD\u0011\"!@0\u0003\u0003%\t%a@\t\u0013\t\u0005q&!A\u0005B\t\r\u0011\u0001B+J]RT!a\u0017/\u0002\t5\fG\u000f\u001b\u0006\u0002;\u0006)1\u000f]5sK\u000e\u0001\u0001C\u00011\u0002\u001b\u0005Q&\u0001B+J]R\u001c2!A2j!\t!w-D\u0001f\u0015\u00051\u0017!B:dC2\f\u0017B\u00015f\u0005\u0019\te.\u001f*fMB\u0011\u0001M[\u0005\u0003Wj\u0013Q\"V%oi&s7\u000f^1oG\u0016\u001c\u0018A\u0002\u001fj]&$h\bF\u0001`\u0003\u0015\t\u0007\u000f\u001d7z)\r\u0001(q\u0002\t\u0003A>\u001a\"a\f:\u0011\u0005\u0011\u001c\u0018B\u0001;f\u0005\u0019\te.\u001f,bY\u000611/[4oK\u0012,\u0012a\u001e\t\u0003IbL!!_3\u0003\u0007%sG/A\u0004tS\u001etW\r\u001a\u0011\u0015\u0005Ad\b\"B;3\u0001\u00049\u0018A\u0002;p\u0005f$X-F\u0001\u0000!\r!\u0017\u0011A\u0005\u0004\u0003\u0007)'\u0001\u0002\"zi\u0016\fa\u0001^8DQ\u0006\u0014XCAA\u0005!\r!\u00171B\u0005\u0004\u0003\u001b)'\u0001B\"iCJ\fq\u0001^8TQ>\u0014H/\u0006\u0002\u0002\u0014A\u0019A-!\u0006\n\u0007\u0005]QMA\u0003TQ>\u0014H/A\u0003u_&sG/\u0001\u0004u_2{gnZ\u000b\u0003\u0003?\u00012\u0001ZA\u0011\u0013\r\t\u0019#\u001a\u0002\u0005\u0019>tw-A\u0004u_\u001acw.\u0019;\u0016\u0005\u0005%\u0002c\u00013\u0002,%\u0019\u0011QF3\u0003\u000b\u0019cw.\u0019;\u0002\u0011Q|Gi\\;cY\u0016,\"!a\r\u0011\u0007\u0011\f)$C\u0002\u00028\u0015\u0014a\u0001R8vE2,\u0017\u0001\u0003;p\u0005&<\u0017J\u001c;\u0016\u0005\u0005u\u0002\u0003BA \u0003\u001frA!!\u0011\u0002L9!\u00111IA%\u001b\t\t)EC\u0002\u0002Hy\u000ba\u0001\u0010:p_Rt\u0014\"\u00014\n\u0007\u00055S-A\u0004qC\u000e\\\u0017mZ3\n\t\u0005E\u00131\u000b\u0002\u0007\u0005&<\u0017J\u001c;\u000b\u0007\u00055S-A\u0006jgZ\u000bG.\u001b3CsR,WCAA-!\r!\u00171L\u0005\u0004\u0003;*'a\u0002\"p_2,\u0017M\\\u0001\rSN4\u0016\r\\5e'\"|'\u000f^\u0001\fSN4\u0016\r\\5e\u0007\"\f'/\u0001\u0006jgZ\u000bG.\u001b3J]R\f1\"[:WC2LG\rT8oO\u0006AAo\\*ue&tw\r\u0006\u0002\u0002lA!\u0011QNA;\u001d\u0011\ty'!\u001d\u0011\u0007\u0005\rS-C\u0002\u0002t\u0015\fa\u0001\u0015:fI\u00164\u0017\u0002BA<\u0003s\u0012aa\u0015;sS:<'bAA:K\u00061A%Z9%KF$B!!\u0017\u0002\u0000!1\u0011\u0011Q!A\u0002A\fA\u0001\u001e5bi\u0006AAEY1oO\u0012*\u0017\u000f\u0006\u0003\u0002Z\u0005\u001d\u0005BBAA\u0005\u0002\u0007\u0001/A\u0005%KF$S-\u001d\u0013fcR!\u0011\u0011LAG\u0011\u0019\t\ti\u0011a\u0001a\u0006YA%Z9%E\u0006tw\rJ3r)\u0011\tI&a%\t\r\u0005\u0005E\t1\u0001q\u0003!!C.Z:tI\u0015\fH\u0003BA-\u00033Ca!!!F\u0001\u0004\u0001\u0018!\u0002\u0013mKN\u001cH\u0003BA-\u0003?Ca!!!G\u0001\u0004\u0001\u0018a\u0003\u0013he\u0016\fG/\u001a:%KF$B!!\u0017\u0002&\"1\u0011\u0011Q$A\u0002A\f\u0001\u0002J4sK\u0006$XM\u001d\u000b\u0005\u00033\nY\u000b\u0003\u0004\u0002\u0002\"\u0003\r\u0001]\u0001\rk:\f'/_0%[&tWo]\u000b\u0002a\u0006)A\u0005\u001d7vgR\u0019\u0001/!.\t\r\u0005\u0005%\n1\u0001q\u0003\u0019!S.\u001b8vgR\u0019\u0001/a/\t\r\u0005\u00055\n1\u0001q\u0003\u0019!C/[7fgR\u0019\u0001/!1\t\r\u0005\u0005E\n1\u0001q\u0003\u0011!C-\u001b<\u0015\u0007A\f9\r\u0003\u0004\u0002\u00026\u0003\r\u0001]\u0001\tIA,'oY3oiR\u0019\u0001/!4\t\r\u0005\u0005e\n1\u0001q\u00031)h.\u0019:z?\u0012\"\u0018\u000e\u001c3f\u0003)!C.Z:tI1,7o\u001d\u000b\u0004a\u0006U\u0007BBAl!\u0002\u0007q/A\u0003tQ&4G/\u0001\t%OJ,\u0017\r^3sI\u001d\u0014X-\u0019;feR\u0019\u0001/!8\t\r\u0005]\u0017\u000b1\u0001x\u0003a!sM]3bi\u0016\u0014He\u001a:fCR,'\u000fJ4sK\u0006$XM\u001d\u000b\u0004a\u0006\r\bBBAl%\u0002\u0007q/\u0001\u0003%C6\u0004Hc\u00019\u0002j\"1\u0011\u0011Q*A\u0002A\fA\u0001\n2beR\u0019\u0001/a<\t\r\u0005\u0005E\u000b1\u0001q\u0003\r!S\u000f\u001d\u000b\u0004a\u0006U\bBBAA+\u0002\u0007\u0001/\u0001\u0007%i&lWm\u001d\u0013uS6,7\u000fF\u0002q\u0003wDa!!!W\u0001\u0004\u0001\u0018\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003]\fa!Z9vC2\u001cH\u0003BA-\u0005\u000bA\u0011Ba\u0002Y\u0003\u0003\u0005\rA!\u0003\u0002\u0007a$\u0013\u0007E\u0002e\u0005\u0017I1A!\u0004f\u0005\r\te.\u001f\u0005\u0007\u0005#\u0019\u0001\u0019A<\u0002\u00039D3a\u0001B\u000b!\r!'qC\u0005\u0004\u00053)'AB5oY&tW\rF\u0002q\u0005;AqA!\u0005\u0005\u0001\u0004\ty\u0002K\u0002\u0005\u0005+\t\u0001\"T5o-\u0006dW/Z\u0001\n\u001b&tg+\u00197vK\u0002B3A\u0002B\u000b\u0003!i\u0015\r\u001f,bYV,\u0017!C'bqZ\u000bG.^3!Q\rA!QC\u0001\u0011i>\u0014\u0015\u0010^3%Kb$XM\\:j_:$2a B\u0019\u0011\u0019\u0011\u0019$\u0003a\u0001a\u0006)A\u0005\u001e5jg\u0006\u0001Bo\\\"iCJ$S\r\u001f;f]NLwN\u001c\u000b\u0005\u0003\u0013\u0011I\u0004\u0003\u0004\u00034)\u0001\r\u0001]\u0001\u0012i>\u001c\u0006n\u001c:uI\u0015DH/\u001a8tS>tG\u0003BA\n\u0005\u007fAaAa\r\f\u0001\u0004\u0001\u0018a\u0004;p\u0013:$H%\u001a=uK:\u001c\u0018n\u001c8\u0015\u0007]\u0014)\u0005\u0003\u0004\u000341\u0001\r\u0001]\u0001\u0011i>duN\\4%Kb$XM\\:j_:$B!a\b\u0003L!1!1G\u0007A\u0002A\f\u0011\u0003^8GY>\fG\u000fJ3yi\u0016t7/[8o)\u0011\tIC!\u0015\t\r\tMb\u00021\u0001q\u0003I!x\u000eR8vE2,G%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\u0005M\"q\u000b\u0005\u0007\u0005gy\u0001\u0019\u00019\u0002%Q|')[4J]R$S\r\u001f;f]NLwN\u001c\u000b\u0005\u0003{\u0011i\u0006\u0003\u0004\u00034A\u0001\r\u0001]\u0001\u0016SN4\u0016\r\\5e\u0005f$X\rJ3yi\u0016t7/[8o)\u0011\tIFa\u0019\t\r\tM\u0012\u00031\u0001q\u0003YI7OV1mS\u0012\u001c\u0006n\u001c:uI\u0015DH/\u001a8tS>tG\u0003BA-\u0005SBaAa\r\u0013\u0001\u0004\u0001\u0018!F5t-\u0006d\u0017\u000eZ\"iCJ$S\r\u001f;f]NLwN\u001c\u000b\u0005\u00033\u0012y\u0007\u0003\u0004\u00034M\u0001\r\u0001]\u0001\u0015SN4\u0016\r\\5e\u0013:$H%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\u0005e#Q\u000f\u0005\u0007\u0005g!\u0002\u0019\u00019\u0002+%\u001ch+\u00197jI2{gn\u001a\u0013fqR,gn]5p]R!\u0011\u0011\fB>\u0011\u0019\u0011\u0019$\u0006a\u0001a\u0006\u0011Bo\\*ue&tw\rJ3yi\u0016t7/[8o)\u0011\tIG!!\t\r\tMb\u00031\u0001q\u0003A!S-\u001d\u0013fc\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0003\b\n-E\u0003BA-\u0005\u0013Ca!!!\u0018\u0001\u0004\u0001\bB\u0002B\u001a/\u0001\u0007\u0001/\u0001\n%E\u0006tw\rJ3rI\u0015DH/\u001a8tS>tG\u0003\u0002BI\u0005+#B!!\u0017\u0003\u0014\"1\u0011\u0011\u0011\rA\u0002ADaAa\r\u0019\u0001\u0004\u0001\u0018a\u0005\u0013fc\u0012*\u0017\u000fJ3rI\u0015DH/\u001a8tS>tG\u0003\u0002BN\u0005?#B!!\u0017\u0003\u001e\"1\u0011\u0011Q\rA\u0002ADaAa\r\u001a\u0001\u0004\u0001\u0018!\u0006\u0013fc\u0012\u0012\u0017M\\4%KF$S\r\u001f;f]NLwN\u001c\u000b\u0005\u0005K\u0013I\u000b\u0006\u0003\u0002Z\t\u001d\u0006BBAA5\u0001\u0007\u0001\u000f\u0003\u0004\u00034i\u0001\r\u0001]\u0001\u0013I1,7o\u001d\u0013fc\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u00030\nMF\u0003BA-\u0005cCa!!!\u001c\u0001\u0004\u0001\bB\u0002B\u001a7\u0001\u0007\u0001/A\b%Y\u0016\u001c8\u000fJ3yi\u0016t7/[8o)\u0011\u0011IL!0\u0015\t\u0005e#1\u0018\u0005\u0007\u0003\u0003c\u0002\u0019\u00019\t\r\tMB\u00041\u0001q\u0003U!sM]3bi\u0016\u0014H%Z9%Kb$XM\\:j_:$BAa1\u0003HR!\u0011\u0011\fBc\u0011\u0019\t\t)\ba\u0001a\"1!1G\u000fA\u0002A\f!\u0003J4sK\u0006$XM\u001d\u0013fqR,gn]5p]R!!Q\u001aBi)\u0011\tIFa4\t\r\u0005\u0005e\u00041\u0001q\u0011\u0019\u0011\u0019D\ba\u0001a\u00061RO\\1ss~#S.\u001b8vg\u0012*\u0007\u0010^3og&|g\u000eF\u0002q\u0005/DaAa\r \u0001\u0004\u0001\u0018a\u0004\u0013qYV\u001cH%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\tu'\u0011\u001d\u000b\u0004a\n}\u0007BBAAA\u0001\u0007\u0001\u000f\u0003\u0004\u00034\u0001\u0002\r\u0001]\u0001\u0011I5Lg.^:%Kb$XM\\:j_:$BAa:\u0003lR\u0019\u0001O!;\t\r\u0005\u0005\u0015\u00051\u0001q\u0011\u0019\u0011\u0019$\ta\u0001a\u0006\u0001B\u0005^5nKN$S\r\u001f;f]NLwN\u001c\u000b\u0005\u0005c\u0014)\u0010F\u0002q\u0005gDa!!!#\u0001\u0004\u0001\bB\u0002B\u001aE\u0001\u0007\u0001/\u0001\b%I&4H%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\tm(q \u000b\u0004a\nu\bBBAAG\u0001\u0007\u0001\u000f\u0003\u0004\u00034\r\u0002\r\u0001]\u0001\u0013IA,'oY3oi\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0004\u0006\r%Ac\u00019\u0004\b!1\u0011\u0011\u0011\u0013A\u0002ADaAa\r%\u0001\u0004\u0001\u0018AF;oCJLx\f\n;jY\u0012,G%\u001a=uK:\u001c\u0018n\u001c8\u0015\u0007A\u001cy\u0001\u0003\u0004\u00034\u0015\u0002\r\u0001]\u0001\u0015I1,7o\u001d\u0013mKN\u001cH%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\rU1\u0011\u0004\u000b\u0004a\u000e]\u0001BBAlM\u0001\u0007q\u000f\u0003\u0004\u00034\u0019\u0002\r\u0001]\u0001\u001bI\u001d\u0014X-\u0019;fe\u0012:'/Z1uKJ$S\r\u001f;f]NLwN\u001c\u000b\u0005\u0007?\u0019\u0019\u0003F\u0002q\u0007CAa!a6(\u0001\u00049\bB\u0002B\u001aO\u0001\u0007\u0001/\u0001\u0012%OJ,\u0017\r^3sI\u001d\u0014X-\u0019;fe\u0012:'/Z1uKJ$S\r\u001f;f]NLwN\u001c\u000b\u0005\u0007S\u0019i\u0003F\u0002q\u0007WAa!a6)\u0001\u00049\bB\u0002B\u001aQ\u0001\u0007\u0001/\u0001\b%C6\u0004H%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\rM2q\u0007\u000b\u0004a\u000eU\u0002BBAAS\u0001\u0007\u0001\u000f\u0003\u0004\u00034%\u0002\r\u0001]\u0001\u000fI\t\f'\u000fJ3yi\u0016t7/[8o)\u0011\u0019id!\u0011\u0015\u0007A\u001cy\u0004\u0003\u0004\u0002\u0002*\u0002\r\u0001\u001d\u0005\u0007\u0005gQ\u0003\u0019\u00019\u0002\u001b\u0011*\b\u000fJ3yi\u0016t7/[8o)\u0011\u00199ea\u0013\u0015\u0007A\u001cI\u0005\u0003\u0004\u0002\u0002.\u0002\r\u0001\u001d\u0005\u0007\u0005gY\u0003\u0019\u00019\u0002-\u0011\"\u0018.\\3tIQLW.Z:%Kb$XM\\:j_:$Ba!\u0015\u0004VQ\u0019\u0001oa\u0015\t\r\u0005\u0005E\u00061\u0001q\u0011\u0019\u0011\u0019\u0004\fa\u0001a\u0006\u0011\u0002.Y:i\u0007>$W\rJ3yi\u0016t7/[8o)\u0011\typa\u0017\t\r\tMR\u00061\u0001q\u0003A)\u0017/^1mg\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0004b\r\u0015D\u0003BA-\u0007GB\u0011Ba\u0002/\u0003\u0003\u0005\rA!\u0003\t\r\tMb\u00061\u0001q\u0001"
)
public final class UInt {
   private final int signed;

   public static boolean equals$extension(final int $this, final Object x$1) {
      return UInt$.MODULE$.equals$extension($this, x$1);
   }

   public static int hashCode$extension(final int $this) {
      return UInt$.MODULE$.hashCode$extension($this);
   }

   public static int $times$times$extension(final int $this, final int that) {
      return UInt$.MODULE$.$times$times$extension($this, that);
   }

   public static int $up$extension(final int $this, final int that) {
      return UInt$.MODULE$.$up$extension($this, that);
   }

   public static int $bar$extension(final int $this, final int that) {
      return UInt$.MODULE$.$bar$extension($this, that);
   }

   public static int $amp$extension(final int $this, final int that) {
      return UInt$.MODULE$.$amp$extension($this, that);
   }

   public static int $greater$greater$greater$extension(final int $this, final int shift) {
      return UInt$.MODULE$.$greater$greater$greater$extension($this, shift);
   }

   public static int $greater$greater$extension(final int $this, final int shift) {
      return UInt$.MODULE$.$greater$greater$extension($this, shift);
   }

   public static int $less$less$extension(final int $this, final int shift) {
      return UInt$.MODULE$.$less$less$extension($this, shift);
   }

   public static int unary_$tilde$extension(final int $this) {
      return UInt$.MODULE$.unary_$tilde$extension($this);
   }

   public static int $percent$extension(final int $this, final int that) {
      return UInt$.MODULE$.$percent$extension($this, that);
   }

   public static int $div$extension(final int $this, final int that) {
      return UInt$.MODULE$.$div$extension($this, that);
   }

   public static int $times$extension(final int $this, final int that) {
      return UInt$.MODULE$.$times$extension($this, that);
   }

   public static int $minus$extension(final int $this, final int that) {
      return UInt$.MODULE$.$minus$extension($this, that);
   }

   public static int $plus$extension(final int $this, final int that) {
      return UInt$.MODULE$.$plus$extension($this, that);
   }

   public static int unary_$minus$extension(final int $this) {
      return UInt$.MODULE$.unary_$minus$extension($this);
   }

   public static boolean $greater$extension(final int $this, final int that) {
      return UInt$.MODULE$.$greater$extension($this, that);
   }

   public static boolean $greater$eq$extension(final int $this, final int that) {
      return UInt$.MODULE$.$greater$eq$extension($this, that);
   }

   public static boolean $less$extension(final int $this, final int that) {
      return UInt$.MODULE$.$less$extension($this, that);
   }

   public static boolean $less$eq$extension(final int $this, final int that) {
      return UInt$.MODULE$.$less$eq$extension($this, that);
   }

   public static boolean $eq$bang$eq$extension(final int $this, final int that) {
      return UInt$.MODULE$.$eq$bang$eq$extension($this, that);
   }

   public static boolean $eq$eq$eq$extension(final int $this, final int that) {
      return UInt$.MODULE$.$eq$eq$eq$extension($this, that);
   }

   public static boolean $bang$eq$extension(final int $this, final int that) {
      return UInt$.MODULE$.$bang$eq$extension($this, that);
   }

   public static boolean $eq$eq$extension(final int $this, final int that) {
      return UInt$.MODULE$.$eq$eq$extension($this, that);
   }

   public static String toString$extension(final int $this) {
      return UInt$.MODULE$.toString$extension($this);
   }

   public static boolean isValidLong$extension(final int $this) {
      return UInt$.MODULE$.isValidLong$extension($this);
   }

   public static boolean isValidInt$extension(final int $this) {
      return UInt$.MODULE$.isValidInt$extension($this);
   }

   public static boolean isValidChar$extension(final int $this) {
      return UInt$.MODULE$.isValidChar$extension($this);
   }

   public static boolean isValidShort$extension(final int $this) {
      return UInt$.MODULE$.isValidShort$extension($this);
   }

   public static boolean isValidByte$extension(final int $this) {
      return UInt$.MODULE$.isValidByte$extension($this);
   }

   public static BigInt toBigInt$extension(final int $this) {
      return UInt$.MODULE$.toBigInt$extension($this);
   }

   public static double toDouble$extension(final int $this) {
      return UInt$.MODULE$.toDouble$extension($this);
   }

   public static float toFloat$extension(final int $this) {
      return UInt$.MODULE$.toFloat$extension($this);
   }

   public static long toLong$extension(final int $this) {
      return UInt$.MODULE$.toLong$extension($this);
   }

   public static int toInt$extension(final int $this) {
      return UInt$.MODULE$.toInt$extension($this);
   }

   public static short toShort$extension(final int $this) {
      return UInt$.MODULE$.toShort$extension($this);
   }

   public static char toChar$extension(final int $this) {
      return UInt$.MODULE$.toChar$extension($this);
   }

   public static byte toByte$extension(final int $this) {
      return UInt$.MODULE$.toByte$extension($this);
   }

   public static int MaxValue() {
      return UInt$.MODULE$.MaxValue();
   }

   public static int MinValue() {
      return UInt$.MODULE$.MinValue();
   }

   public static int apply(final long n) {
      return UInt$.MODULE$.apply(n);
   }

   public static int apply(final int n) {
      return UInt$.MODULE$.apply(n);
   }

   public static NumberTag UIntTag() {
      return UInt$.MODULE$.UIntTag();
   }

   public static BitString UIntBitString() {
      return UInt$.MODULE$.UIntBitString();
   }

   public static CommutativeRig UIntAlgebra() {
      return UInt$.MODULE$.UIntAlgebra();
   }

   public int signed() {
      return this.signed;
   }

   public byte toByte() {
      return UInt$.MODULE$.toByte$extension(this.signed());
   }

   public char toChar() {
      return UInt$.MODULE$.toChar$extension(this.signed());
   }

   public short toShort() {
      return UInt$.MODULE$.toShort$extension(this.signed());
   }

   public int toInt() {
      return UInt$.MODULE$.toInt$extension(this.signed());
   }

   public long toLong() {
      return UInt$.MODULE$.toLong$extension(this.signed());
   }

   public float toFloat() {
      return UInt$.MODULE$.toFloat$extension(this.signed());
   }

   public double toDouble() {
      return UInt$.MODULE$.toDouble$extension(this.signed());
   }

   public BigInt toBigInt() {
      return UInt$.MODULE$.toBigInt$extension(this.signed());
   }

   public boolean isValidByte() {
      return UInt$.MODULE$.isValidByte$extension(this.signed());
   }

   public boolean isValidShort() {
      return UInt$.MODULE$.isValidShort$extension(this.signed());
   }

   public boolean isValidChar() {
      return UInt$.MODULE$.isValidChar$extension(this.signed());
   }

   public boolean isValidInt() {
      return UInt$.MODULE$.isValidInt$extension(this.signed());
   }

   public boolean isValidLong() {
      return UInt$.MODULE$.isValidLong$extension(this.signed());
   }

   public String toString() {
      return UInt$.MODULE$.toString$extension(this.signed());
   }

   public boolean $eq$eq(final int that) {
      return UInt$.MODULE$.$eq$eq$extension(this.signed(), that);
   }

   public boolean $bang$eq(final int that) {
      return UInt$.MODULE$.$bang$eq$extension(this.signed(), that);
   }

   public boolean $eq$eq$eq(final int that) {
      return UInt$.MODULE$.$eq$eq$eq$extension(this.signed(), that);
   }

   public boolean $eq$bang$eq(final int that) {
      return UInt$.MODULE$.$eq$bang$eq$extension(this.signed(), that);
   }

   public boolean $less$eq(final int that) {
      return UInt$.MODULE$.$less$eq$extension(this.signed(), that);
   }

   public boolean $less(final int that) {
      return UInt$.MODULE$.$less$extension(this.signed(), that);
   }

   public boolean $greater$eq(final int that) {
      return UInt$.MODULE$.$greater$eq$extension(this.signed(), that);
   }

   public boolean $greater(final int that) {
      return UInt$.MODULE$.$greater$extension(this.signed(), that);
   }

   public int unary_$minus() {
      return UInt$.MODULE$.unary_$minus$extension(this.signed());
   }

   public int $plus(final int that) {
      return UInt$.MODULE$.$plus$extension(this.signed(), that);
   }

   public int $minus(final int that) {
      return UInt$.MODULE$.$minus$extension(this.signed(), that);
   }

   public int $times(final int that) {
      return UInt$.MODULE$.$times$extension(this.signed(), that);
   }

   public int $div(final int that) {
      return UInt$.MODULE$.$div$extension(this.signed(), that);
   }

   public int $percent(final int that) {
      return UInt$.MODULE$.$percent$extension(this.signed(), that);
   }

   public int unary_$tilde() {
      return UInt$.MODULE$.unary_$tilde$extension(this.signed());
   }

   public int $less$less(final int shift) {
      return UInt$.MODULE$.$less$less$extension(this.signed(), shift);
   }

   public int $greater$greater(final int shift) {
      return UInt$.MODULE$.$greater$greater$extension(this.signed(), shift);
   }

   public int $greater$greater$greater(final int shift) {
      return UInt$.MODULE$.$greater$greater$greater$extension(this.signed(), shift);
   }

   public int $amp(final int that) {
      return UInt$.MODULE$.$amp$extension(this.signed(), that);
   }

   public int $bar(final int that) {
      return UInt$.MODULE$.$bar$extension(this.signed(), that);
   }

   public int $up(final int that) {
      return UInt$.MODULE$.$up$extension(this.signed(), that);
   }

   public int $times$times(final int that) {
      return UInt$.MODULE$.$times$times$extension(this.signed(), that);
   }

   public int hashCode() {
      return UInt$.MODULE$.hashCode$extension(this.signed());
   }

   public boolean equals(final Object x$1) {
      return UInt$.MODULE$.equals$extension(this.signed(), x$1);
   }

   public UInt(final int signed) {
      this.signed = signed;
   }
}
