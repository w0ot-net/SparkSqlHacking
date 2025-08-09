package jakarta.ws.rs.ext;

import jakarta.ws.rs.core.Response;

public interface ExceptionMapper {
   Response toResponse(Throwable var1);
}
