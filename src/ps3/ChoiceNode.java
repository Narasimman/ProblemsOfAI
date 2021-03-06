package ps3;

import java.util.ArrayList;
import java.util.List;

public class ChoiceNode implements INode {
  private final Type type;
  private final boolean chance;
  private final int reviewerId;
  private final List<Integer> consultedList = new ArrayList<Integer>();
  private final double prob;
  private final List<Boolean> chances = new ArrayList<Boolean>();
  
  // Non final fields populated during backtracking
  private int utility;
  private List<INode> children = new ArrayList<INode>();
  private INode bestChild;
  
  ChoiceNode(boolean chance, int reviewer, List<Integer> reviewerList, double prob, List<Boolean> chanceList) {
    this.type = Type.CHOICE;
    this.chance = chance;
    this.reviewerId  = reviewer;
    this.consultedList.addAll(reviewerList);
    this.prob = prob;
    this.chances.addAll(chanceList);
    if(reviewer != -1)
      this.chances.add(chance);
  }

  public boolean isChoiceNode() {
    return this.type == Type.CHOICE;
  }
  
  public boolean getChance() {
    return chance;
  }

  public int getReviewerId() {
    return reviewerId;
  }

  @Override
  public Type getType() {
    return type;
  }

  @Override
  public int getUtility() {
    return utility;
  }

  @Override
  public double getProb() {
    return prob;
  }

  @Override
  public List<Integer> getConsultedList() {
    return consultedList;
  }
  
  @Override
  public List<INode> getChildren(List<Reviewer> reviewers) {
    List<Integer> consultedList;

    List<INode> actionNodes = new ArrayList<INode>();
    INode actionNode;

    consultedList = this.getConsultedList();

    
    if(this.getChance()) {
      actionNode = new ChanceNode(INode.Action.PUBLISH, this.getReviewerId(), consultedList, this.prob, this.chances);
    } else {
      actionNode = new OutcomeNode(false, INode.Action.REJECT, consultedList, this.prob);
    }
    actionNodes.add(actionNode);

    for (Reviewer r : reviewers) {
      if(!consultedList.contains(r.getId())) {
        actionNode = new ChanceNode(INode.Action.CONSULT, r.getId(), consultedList, this.prob, this.chances);
        actionNodes.add(actionNode);
      }
    }
    children = actionNodes;
    return actionNodes;
  }
  
  @Override
  public void calculateUtility() {
    int max_util = Integer.MIN_VALUE;
    INode best = null;
    for (INode child : children) {
      if(child.getUtility() > max_util) {
        max_util = child.getUtility();
        best = child;
      }
    }
    this.bestChild = best;
    this.utility = max_util;
  }

  public INode getBestChild() {
    return bestChild;
  }
  
}
