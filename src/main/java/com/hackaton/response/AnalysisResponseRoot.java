package com.hackaton.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnalysisResponseRoot<T> {

    @Getter
    private final OperationStatus status;

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String analysisId;

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String error;

    @Getter
    private final int statusCode;

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T data;

    private AnalysisResponseRoot(OperationStatus status, T data, String analysisId, String error) {
        this.status = status;
        this.analysisId = analysisId;
        this.statusCode = status.code;
        this.data = data;
        this.error = error;
    }

    public static  AnalysisResponseRoot success() {
        return new  AnalysisResponseRoot<>(OperationStatus.SUCCESS, null, null, null);
    }

    public static  AnalysisResponseRoot success(String analysisId) {
        return new  AnalysisResponseRoot<>(OperationStatus.SUCCESS, null, analysisId, null);
    }


    public static <T> AnalysisResponseRoot<T> success(String analysisId, T data) {
        return new AnalysisResponseRoot<>(OperationStatus.SUCCESS, data, analysisId, null);
    }

    public static <T> AnalysisResponseRoot<T> error(OperationStatus status, String error) {
        return new AnalysisResponseRoot<>(status, null, null,  error);
    }

    public boolean isSuccessful() {
        return status.isSuccessful();
    }
}
