package org.bouncycastle.asn1;

import java.io.IOException;

/** @deprecated */
public class DLSequenceParser implements ASN1SequenceParser {
   private ASN1StreamParser _parser;

   DLSequenceParser(ASN1StreamParser var1) {
      this._parser = var1;
   }

   public ASN1Encodable readObject() throws IOException {
      return this._parser.readObject();
   }

   public ASN1Primitive getLoadedObject() throws IOException {
      return DLFactory.createSequence(this._parser.readVector());
   }

   public ASN1Primitive toASN1Primitive() {
      try {
         return this.getLoadedObject();
      } catch (IOException var2) {
         throw new IllegalStateException(var2.getMessage());
      }
   }
}
