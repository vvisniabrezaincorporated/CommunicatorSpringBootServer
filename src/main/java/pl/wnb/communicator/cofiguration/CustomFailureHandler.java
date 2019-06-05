package pl.wnb.communicator.cofiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import pl.wnb.communicator.model.Response;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class CustomFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception)
            throws IOException, ServletException {
        response.setStatus(401);
        Response res = new Response.Builder()
                .addContentType("application/json")
                .addCharEncoding("UTF-8")
                .addIsError(false)
                .addMsg(exception.getMessage())
                .addStatus(401)
                .build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(res);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();

    }
}
