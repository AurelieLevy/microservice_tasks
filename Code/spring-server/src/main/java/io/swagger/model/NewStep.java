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
 * NewStep
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-11-14T13:49:00.734Z")

public class NewStep   {
  @JsonProperty("exec_id")
  private String execId = null;

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

  public NewStep execId(String execId) {
    this.execId = execId;
    return this;
  }

   /**
   * Get execId
   * @return execId
  **/
  @ApiModelProperty(value = "")


  public String getExecId() {
    return execId;
  }

  public void setExecId(String execId) {
    this.execId = execId;
  }

  public NewStep name(String name) {
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

  public NewStep context(String context) {
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

  public NewStep status(StatusEnum status) {
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
    NewStep newStep = (NewStep) o;
    return Objects.equals(this.execId, newStep.execId) &&
        Objects.equals(this.name, newStep.name) &&
        Objects.equals(this.context, newStep.context) &&
        Objects.equals(this.status, newStep.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(execId, name, context, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewStep {\n");
    
    sb.append("    execId: ").append(toIndentedString(execId)).append("\n");
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

