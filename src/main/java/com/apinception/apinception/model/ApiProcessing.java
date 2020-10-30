package com.apinception.apinception.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Data
@ToString
@Document(collection="API_PROCESSING")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ApiProcessing {
    private int apiId;
    private List<ApiProcessingStep> apiProcessingStepList;
}
