package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Step;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Exec
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-11-14T13:49:00.734Z")

public class Exec   {
  @JsonProperty("exec_id")
  private String execId = null;

  @JsonProperty("steps")
  private List<Step> steps = null;

  public Exec execId(String execId) {
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

  public Exec steps(List<Step> steps) {
    this.steps = steps;
    return this;
  }

  public Exec addStepsItem(Step stepsItem) {
    if (this.steps == null) {
      this.steps = new ArrayList<Step>();
    }
    this.steps.add(stepsItem);
    return this;
  }

   /**
   * Get steps
   * @return steps
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Step> getSteps() {
    return steps;
  }

  public void setSteps(List<Step> steps) {
    this.steps = steps;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Exec exec = (Exec) o;
    return Objects.equals(this.execId, exec.execId) &&
        Objects.equals(this.steps, exec.steps);
  }

  @Override
  public int hashCode() {
    return Objects.hash(execId, steps);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Exec {\n");
    
    sb.append("    execId: ").append(toIndentedString(execId)).append("\n");
    sb.append("    steps: ").append(toIndentedString(steps)).append("\n");
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

