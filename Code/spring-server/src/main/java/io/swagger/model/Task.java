package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Exec;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Task
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-11-14T13:49:00.734Z")

public class Task   {
  @JsonProperty("task_id")
  private String taskId = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("execs")
  private List<Exec> execs = null;

  public Task taskId(String taskId) {
    this.taskId = taskId;
    return this;
  }

   /**
   * Get taskId
   * @return taskId
  **/
  @ApiModelProperty(value = "")


  public String getTaskId() {
    return taskId;
  }

  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }

  public Task name(String name) {
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

  public Task description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(value = "")


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Task execs(List<Exec> execs) {
    this.execs = execs;
    return this;
  }

  public Task addExecsItem(Exec execsItem) {
    if (this.execs == null) {
      this.execs = new ArrayList<Exec>();
    }
    this.execs.add(execsItem);
    return this;
  }

   /**
   * Get execs
   * @return execs
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Exec> getExecs() {
    return execs;
  }

  public void setExecs(List<Exec> execs) {
    this.execs = execs;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Task task = (Task) o;
    return Objects.equals(this.taskId, task.taskId) &&
        Objects.equals(this.name, task.name) &&
        Objects.equals(this.description, task.description) &&
        Objects.equals(this.execs, task.execs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(taskId, name, description, execs);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Task {\n");
    
    sb.append("    taskId: ").append(toIndentedString(taskId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    execs: ").append(toIndentedString(execs)).append("\n");
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

