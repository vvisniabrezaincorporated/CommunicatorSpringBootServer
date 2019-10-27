package pl.wnb.communicator.cofiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import pl.wnb.communicator.model.Response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


public class CustomSuccessHandler implements AuthenticationSuccessHandler {



    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        Response res = new Response.Builder()
                .addContentType("application/json")
                .addCharEncoding("UTF-8")
                .addIsError(false)
                .addUserRoles(authentication.getAuthorities().toString())
                .addMsg("Logged in!")
                .addUsername(authentication.getName())
                .addStatus(200)
                .build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(res);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();


    }
}
