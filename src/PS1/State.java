package PS1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class State {
  private int taskId;
  private int depth;
  private boolean visited;
  private String sequence;
  
  public State(int id, int depth) {
    this.taskId = id;
    this.visited = false;
    this.depth = depth;
  }
  
  public boolean isVisited() {
    return visited;
  }
  
  public int getTaskId() {
    return taskId;
  }
  
  public void setSequence(String s) {
    this.sequence = s;
  }
  
  public String getSequence() {
    return this.sequence;
  }
  
  public int getDepth() {
    return this.depth;
  }
  
  /**
   * Checks whether a state is valid or not by checking
   * if it has crossed the deadline
   * @param state
   * @return
   */
  boolean isValidState(List<Task> list, Task goalTask) {    
    Map<String, Integer> map = this.computeCumulateValues(list);  

    if(map.get("time") <= goalTask.getTime()) { 
      return true;
    }
    return false;
  }

  Map<String, Integer> computeCumulateValues(List<Task> taskList) {
    Map<String, Integer> map = new HashMap<String, Integer>();
    int currentValue = 0;
    int currentDeadline = 0;

    if(this.getTaskId() > -1) {      
      String[] s = this.getSequence().split("");      
      for(int i = 1; i < s.length; i++) {
        Task t = taskList.get(Integer.parseInt(s[i]));
        currentValue += t.getValue();
        currentDeadline += t.getTime();
      }
    }
    map.put("value", currentValue);
    map.put("time", currentDeadline);
    return map;    
  }
  
  @Override
  public String toString() {
    return "" + this.sequence;
  }
}
