package io.jsonwebtoken;

/** @deprecated */
@Deprecated
public interface CompressionCodecResolver {
   CompressionCodec resolveCompressionCodec(Header var1) throws CompressionException;
}
