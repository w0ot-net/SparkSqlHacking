package jakarta.servlet.http;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.ServletOutputStream;
import java.io.IOException;

public interface WebConnection extends AutoCloseable {
   ServletInputStream getInputStream() throws IOException;

   ServletOutputStream getOutputStream() throws IOException;
}
