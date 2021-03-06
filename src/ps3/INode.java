package ps3;

import java.util.List;

public interface INode {
  
  public enum Type {
    CHANCE,
    CHOICE,
    OUTCOME
  };
  
  public enum Action {
    PUBLISH,
    REJECT,
    CONSULT
  };
  
  public Type getType();
  public int getUtility();
  public List<Integer> getConsultedList();
  public List<INode> getChildren(List<Reviewer> reviewers);
  public void calculateUtility();
  public double getProb();
}
