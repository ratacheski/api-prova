package com.ratacheski.apiprova.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
public class ApiError {

    private HttpStatus status;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Field> fields;

    ApiError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public static class Field {
        private String field;
        private String message;

        public Field(String field, String message) {
            super();
            this.field = field;
            this.message = message;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
