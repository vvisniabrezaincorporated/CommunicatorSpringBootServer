package pl.wnb.communicator.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Response {

    private String date;
    private Integer status;
    private String contentType;
    private String characterEncoding;
    private String msg;
    private boolean isError;
    private String username;
    private String userRoles;

    private Response(Builder builder) {
        this.date = builder.date;
        this.status = builder.status;
        this.contentType = builder.contentType;
        this.characterEncoding = builder.characterEncoding;
        this.msg = builder.msg;
        this.isError = builder.isError;
        this.username = builder.username;
        this.userRoles = builder.userRoles;
    }

    public String getDate() {
        return date;
    }

    public Integer getStatus() {
        return status;
    }

    public String getContentType() {
        return contentType;
    }

    public String getCharacterEncoding() {
        return characterEncoding;
    }

    public String getMsg() {
        return msg;
    }

    public boolean isError() {
        return isError;
    }

    public String getUsername() {
        return username;
    }

    public String getUserRoles() {
        return userRoles;
    }

    public static class Builder {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();
        String currentDateString = dateFormat.format(currentDate);

        private String date = currentDateString;
        private Integer status;
        private String contentType;
        private String characterEncoding;
        private String msg;
        private boolean isError;
        private String username;
        private String userRoles;

        public Builder addDate(String date){
            this.date = date;
            return this;
        }
        public Builder addStatus(Integer status){
            this.status = status;
            return this;
        }
        public Builder addContentType(String contentType){
            this.contentType = contentType;
            return this;
        }
        public Builder addCharEncoding(String characterEncoding){
            this.characterEncoding = characterEncoding;
            return this;
        }
        public Builder addMsg(String msg){
            this.msg = msg;
            return this;
        }
        public Builder addIsError(boolean isError){
            this.isError = isError;
            return this;
        }
        public Builder addUsername(String username){
            this.username = username;
            return this;
        }
        public Builder addUserRoles(String userRoles){
            this.userRoles = userRoles;
            return this;
        }
        public Response build(){
            return new Response(this);
        }
    }

}
