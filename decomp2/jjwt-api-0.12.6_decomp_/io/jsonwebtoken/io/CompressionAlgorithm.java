package io.jsonwebtoken.io;

import io.jsonwebtoken.Identifiable;
import java.io.InputStream;
import java.io.OutputStream;

public interface CompressionAlgorithm extends Identifiable {
   OutputStream compress(OutputStream var1);

   InputStream decompress(InputStream var1);
}
