package net.ukr.dreamsicle.exception.handler;

import java.util.Objects;

public class ErrorMessage {

    private final String status;
    private final String errorMessage;

    private ErrorMessage(String status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public static ErrorMessage.ErrorMessageBuilder builder() {
        return new ErrorMessage.ErrorMessageBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorMessage that = (ErrorMessage) o;
        return Objects.equals(status, that.status) &&
                Objects.equals(errorMessage, that.errorMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, errorMessage);
    }

    public String getStatus() {
        return status;
    }


    public String getErrorMessage() {
        return errorMessage;
    }


    public static class ErrorMessageBuilder {
        private String status;
        private String errorMessage;

        public ErrorMessageBuilder() {
        }

        public ErrorMessage.ErrorMessageBuilder status(final String status) {
            this.status = status;
            return this;
        }

        public ErrorMessage.ErrorMessageBuilder errorMessage(final String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        @Override
        public String toString() {
            return "ErrorMessageBuilder{" +
                    "status='" + status + '\'' +
                    ", errorMessage='" + errorMessage + '\'' +
                    '}';
        }

        public ErrorMessage build() {
            return new ErrorMessage(this.status, this.errorMessage);
        }

        public String convertToJson(ErrorMessage errorMessage) {
            return new StringBuilder()
                    .append("{\n")
                    .append("\t\"status\": \"").append(errorMessage.status).append("\",\n")
                    .append("\t\"errorMessage\": \"").append(errorMessage.errorMessage).append("\" \n")
                    .append("}").toString();
        }
    }
}
