package ps2;

import java.util.ArrayList;
import java.util.List;

public class PropositionSet {
  
  private List<Clause> clauses = new ArrayList<Clause>();
  
  /**
   * Returns boolean if the clause is empty
   * @return
   */
  public boolean containsEmptyClause() {
    for (Clause clause : clauses) {
      if (clause.isEmpty()) {
        return true;
      }
    }
    return false;
  }
  
  public boolean isEmpty() {
    return clauses.isEmpty();
  }
  
  /**
   * Returns the first singleton clause identified
   * @return
   */
  public Literal findSingletonClause() {
    for (Clause clause : clauses) {
      if (clause.isSingleton()) {
        return clause.getLiterals().get(0);
      }
    }
    return null;
  }
  
  void addClause(Clause clause) {
    this.clauses.add(clause);
  }
  
  List<Clause> getClauses() {
    return this.clauses;
  }
}
