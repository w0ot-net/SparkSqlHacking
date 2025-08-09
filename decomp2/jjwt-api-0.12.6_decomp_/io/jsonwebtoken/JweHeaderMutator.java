package io.jsonwebtoken;

public interface JweHeaderMutator extends ProtectedHeaderMutator {
   JweHeaderMutator agreementPartyUInfo(byte[] var1);

   JweHeaderMutator agreementPartyUInfo(String var1);

   JweHeaderMutator agreementPartyVInfo(byte[] var1);

   JweHeaderMutator agreementPartyVInfo(String var1);

   JweHeaderMutator pbes2Count(int var1);
}
