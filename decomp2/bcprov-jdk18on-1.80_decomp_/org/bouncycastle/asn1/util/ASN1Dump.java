package org.bouncycastle.asn1.util;

import org.bouncycastle.asn1.ASN1BMPString;
import org.bouncycastle.asn1.ASN1BitString;
import org.bouncycastle.asn1.ASN1Boolean;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Enumerated;
import org.bouncycastle.asn1.ASN1External;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1GraphicString;
import org.bouncycastle.asn1.ASN1IA5String;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Null;
import org.bouncycastle.asn1.ASN1NumericString;
import org.bouncycastle.asn1.ASN1ObjectDescriptor;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1PrintableString;
import org.bouncycastle.asn1.ASN1RelativeOID;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1T61String;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.ASN1UTCTime;
import org.bouncycastle.asn1.ASN1UTF8String;
import org.bouncycastle.asn1.ASN1Util;
import org.bouncycastle.asn1.ASN1VideotexString;
import org.bouncycastle.asn1.ASN1VisibleString;
import org.bouncycastle.asn1.BEROctetString;
import org.bouncycastle.asn1.BERSequence;
import org.bouncycastle.asn1.BERSet;
import org.bouncycastle.asn1.BERTaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.DLBitString;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;

public class ASN1Dump {
   private static final String TAB = "    ";
   private static final int SAMPLE_SIZE = 32;

   static void _dumpAsString(String var0, boolean var1, ASN1Primitive var2, StringBuffer var3) {
      String var4 = Strings.lineSeparator();
      if (var2 instanceof ASN1Null) {
         var3.append(var0);
         var3.append("NULL");
         var3.append(var4);
      } else if (var2 instanceof ASN1Sequence) {
         var3.append(var0);
         if (var2 instanceof BERSequence) {
            var3.append("BER Sequence");
         } else if (var2 instanceof DERSequence) {
            var3.append("DER Sequence");
         } else {
            var3.append("Sequence");
         }

         var3.append(var4);
         ASN1Sequence var5 = (ASN1Sequence)var2;
         String var6 = var0 + "    ";
         int var7 = 0;

         for(int var8 = var5.size(); var7 < var8; ++var7) {
            _dumpAsString(var6, var1, var5.getObjectAt(var7).toASN1Primitive(), var3);
         }
      } else if (var2 instanceof ASN1Set) {
         var3.append(var0);
         if (var2 instanceof BERSet) {
            var3.append("BER Set");
         } else if (var2 instanceof DERSet) {
            var3.append("DER Set");
         } else {
            var3.append("Set");
         }

         var3.append(var4);
         ASN1Set var9 = (ASN1Set)var2;
         String var16 = var0 + "    ";
         int var20 = 0;

         for(int var22 = var9.size(); var20 < var22; ++var20) {
            _dumpAsString(var16, var1, var9.getObjectAt(var20).toASN1Primitive(), var3);
         }
      } else if (var2 instanceof ASN1TaggedObject) {
         var3.append(var0);
         if (var2 instanceof BERTaggedObject) {
            var3.append("BER Tagged ");
         } else if (var2 instanceof DERTaggedObject) {
            var3.append("DER Tagged ");
         } else {
            var3.append("Tagged ");
         }

         ASN1TaggedObject var10 = (ASN1TaggedObject)var2;
         var3.append(ASN1Util.getTagText(var10));
         if (!var10.isExplicit()) {
            var3.append(" IMPLICIT ");
         }

         var3.append(var4);
         String var17 = var0 + "    ";
         _dumpAsString(var17, var1, var10.getBaseObject().toASN1Primitive(), var3);
      } else if (var2 instanceof ASN1OctetString) {
         ASN1OctetString var11 = (ASN1OctetString)var2;
         if (var2 instanceof BEROctetString) {
            var3.append(var0 + "BER Constructed Octet String[" + var11.getOctets().length + "] ");
         } else {
            var3.append(var0 + "DER Octet String[" + var11.getOctets().length + "] ");
         }

         if (var1) {
            var3.append(dumpBinaryDataAsString(var0, var11.getOctets()));
         } else {
            var3.append(var4);
         }
      } else if (var2 instanceof ASN1ObjectIdentifier) {
         var3.append(var0 + "ObjectIdentifier(" + ((ASN1ObjectIdentifier)var2).getId() + ")" + var4);
      } else if (var2 instanceof ASN1RelativeOID) {
         var3.append(var0 + "RelativeOID(" + ((ASN1RelativeOID)var2).getId() + ")" + var4);
      } else if (var2 instanceof ASN1Boolean) {
         var3.append(var0 + "Boolean(" + ((ASN1Boolean)var2).isTrue() + ")" + var4);
      } else if (var2 instanceof ASN1Integer) {
         var3.append(var0 + "Integer(" + ((ASN1Integer)var2).getValue() + ")" + var4);
      } else if (var2 instanceof ASN1BitString) {
         ASN1BitString var12 = (ASN1BitString)var2;
         byte[] var18 = var12.getBytes();
         int var21 = var12.getPadBits();
         if (var12 instanceof DERBitString) {
            var3.append(var0 + "DER Bit String[" + var18.length + ", " + var21 + "] ");
         } else if (var12 instanceof DLBitString) {
            var3.append(var0 + "DL Bit String[" + var18.length + ", " + var21 + "] ");
         } else {
            var3.append(var0 + "BER Bit String[" + var18.length + ", " + var21 + "] ");
         }

         if (var1) {
            var3.append(dumpBinaryDataAsString(var0, var18));
         } else {
            var3.append(var4);
         }
      } else if (var2 instanceof ASN1IA5String) {
         var3.append(var0 + "IA5String(" + ((ASN1IA5String)var2).getString() + ") " + var4);
      } else if (var2 instanceof ASN1UTF8String) {
         var3.append(var0 + "UTF8String(" + ((ASN1UTF8String)var2).getString() + ") " + var4);
      } else if (var2 instanceof ASN1NumericString) {
         var3.append(var0 + "NumericString(" + ((ASN1NumericString)var2).getString() + ") " + var4);
      } else if (var2 instanceof ASN1PrintableString) {
         var3.append(var0 + "PrintableString(" + ((ASN1PrintableString)var2).getString() + ") " + var4);
      } else if (var2 instanceof ASN1VisibleString) {
         var3.append(var0 + "VisibleString(" + ((ASN1VisibleString)var2).getString() + ") " + var4);
      } else if (var2 instanceof ASN1BMPString) {
         var3.append(var0 + "BMPString(" + ((ASN1BMPString)var2).getString() + ") " + var4);
      } else if (var2 instanceof ASN1T61String) {
         var3.append(var0 + "T61String(" + ((ASN1T61String)var2).getString() + ") " + var4);
      } else if (var2 instanceof ASN1GraphicString) {
         var3.append(var0 + "GraphicString(" + ((ASN1GraphicString)var2).getString() + ") " + var4);
      } else if (var2 instanceof ASN1VideotexString) {
         var3.append(var0 + "VideotexString(" + ((ASN1VideotexString)var2).getString() + ") " + var4);
      } else if (var2 instanceof ASN1UTCTime) {
         var3.append(var0 + "UTCTime(" + ((ASN1UTCTime)var2).getTime() + ") " + var4);
      } else if (var2 instanceof ASN1GeneralizedTime) {
         var3.append(var0 + "GeneralizedTime(" + ((ASN1GeneralizedTime)var2).getTime() + ") " + var4);
      } else if (var2 instanceof ASN1Enumerated) {
         ASN1Enumerated var13 = (ASN1Enumerated)var2;
         var3.append(var0 + "DER Enumerated(" + var13.getValue() + ")" + var4);
      } else if (var2 instanceof ASN1ObjectDescriptor) {
         ASN1ObjectDescriptor var14 = (ASN1ObjectDescriptor)var2;
         var3.append(var0 + "ObjectDescriptor(" + var14.getBaseGraphicString().getString() + ") " + var4);
      } else if (var2 instanceof ASN1External) {
         ASN1External var15 = (ASN1External)var2;
         var3.append(var0 + "External " + var4);
         String var19 = var0 + "    ";
         if (var15.getDirectReference() != null) {
            var3.append(var19 + "Direct Reference: " + var15.getDirectReference().getId() + var4);
         }

         if (var15.getIndirectReference() != null) {
            var3.append(var19 + "Indirect Reference: " + var15.getIndirectReference().toString() + var4);
         }

         if (var15.getDataValueDescriptor() != null) {
            _dumpAsString(var19, var1, var15.getDataValueDescriptor(), var3);
         }

         var3.append(var19 + "Encoding: " + var15.getEncoding() + var4);
         _dumpAsString(var19, var1, var15.getExternalContent(), var3);
      } else {
         var3.append(var0 + var2.toString() + var4);
      }

   }

   public static String dumpAsString(Object var0) {
      return dumpAsString(var0, false);
   }

   public static String dumpAsString(Object var0, boolean var1) {
      ASN1Primitive var2;
      if (var0 instanceof ASN1Primitive) {
         var2 = (ASN1Primitive)var0;
      } else {
         if (!(var0 instanceof ASN1Encodable)) {
            return "unknown object type " + var0.toString();
         }

         var2 = ((ASN1Encodable)var0).toASN1Primitive();
      }

      StringBuffer var3 = new StringBuffer();
      _dumpAsString("", var1, var2, var3);
      return var3.toString();
   }

   private static String dumpBinaryDataAsString(String var0, byte[] var1) {
      String var2 = Strings.lineSeparator();
      StringBuffer var3 = new StringBuffer();
      var0 = var0 + "    ";
      var3.append(var2);

      for(int var4 = 0; var4 < var1.length; var4 += 32) {
         if (var1.length - var4 > 32) {
            var3.append(var0);
            var3.append(Strings.fromByteArray(Hex.encode(var1, var4, 32)));
            var3.append("    ");
            var3.append(calculateAscString(var1, var4, 32));
            var3.append(var2);
         } else {
            var3.append(var0);
            var3.append(Strings.fromByteArray(Hex.encode(var1, var4, var1.length - var4)));

            for(int var5 = var1.length - var4; var5 != 32; ++var5) {
               var3.append("  ");
            }

            var3.append("    ");
            var3.append(calculateAscString(var1, var4, var1.length - var4));
            var3.append(var2);
         }
      }

      return var3.toString();
   }

   private static String calculateAscString(byte[] var0, int var1, int var2) {
      StringBuffer var3 = new StringBuffer();

      for(int var4 = var1; var4 != var1 + var2; ++var4) {
         if (var0[var4] >= 32 && var0[var4] <= 126) {
            var3.append((char)var0[var4]);
         }
      }

      return var3.toString();
   }
}
