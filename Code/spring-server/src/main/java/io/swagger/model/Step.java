package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Step
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-11-14T13:49:00.734Z")

public class Step   {
  @JsonProperty("step_id")
  private String stepId = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("context")
  private String context = null;

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    RUNNING("Running"),
    
    ERROR("Error"),
    
    LOG("Log"),
    
    SUCCESS("Success");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("status")
  private StatusEnum status = null;

  public Step stepId(String stepId) {
    this.stepId = stepId;
    return this;
  }

   /**
   * Get stepId
   * @return stepId
  **/
  @ApiModelProperty(value = "")


  public String getStepId() {
    return stepId;
  }

  public void setStepId(String stepId) {
    this.stepId = stepId;
  }

  public Step name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Step context(String context) {
    this.context = context;
    return this;
  }

   /**
   * Get context
   * @return context
  **/
  @ApiModelProperty(value = "")


  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }

  public Step status(StatusEnum status) {
    this.status = status;
    return this;
  }

   /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(value = "")


  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Step step = (Step) o;
    return Objects.equals(this.stepId, step.stepId) &&
        Objects.equals(this.name, step.name) &&
        Objects.equals(this.context, step.context) &&
        Objects.equals(this.status, step.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(stepId, name, context, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Step {\n");
    
    sb.append("    stepId: ").append(toIndentedString(stepId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    context: ").append(toIndentedString(context)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

