package batfish.representation;

import java.io.Serializable;
import java.util.Set;

public class PolicyMapClause implements Serializable {

   private static final long serialVersionUID = 1L;

   private String _mapName;
   private Set<PolicyMapMatchLine> _matchList;
   private Set<PolicyMapSetLine> _setList;
   private PolicyMapAction _type;

   public PolicyMapClause(PolicyMapAction action, String name,
         Set<PolicyMapMatchLine> mlist, Set<PolicyMapSetLine> slist) {
      _type = action;
      _mapName = name;
      _matchList = mlist;
      _setList = slist;
   }

   public PolicyMapAction getAction() {
      return _type;
   }

   public String getMapName() {
      return _mapName;
   }

   public Set<PolicyMapMatchLine> getMatchLines() {
      return _matchList;
   }

   public Set<PolicyMapSetLine> getSetLines() {
      return _setList;
   }

   public boolean sameParseTree(PolicyMapClause clause, String prefix) {
      boolean res = (_mapName.equals(clause._mapName))
            && (_type == clause._type);
      boolean finalRes = res;

      if (res == false) {
         System.out.println("PolicyMapClause " + prefix);
         finalRes = false;
      }

      if (_matchList.size() != clause._matchList.size()) {
         System.out.println("PolicyMapClause:MatchList:Size " + prefix);
         finalRes = false;
      }
      else {
         Object[] lhs = _matchList.toArray();
         Object[] rhs = clause._matchList.toArray();
         for (int i = 0; i < _matchList.size(); i++) {
            res = ((PolicyMapMatchLine) lhs[i]).sameParseTree(
                  ((PolicyMapMatchLine) rhs[i]), "PolicyMapClause:MatchList "
                        + prefix);
            if (res == false) {
               finalRes = false;
            }
         }
      }

      if (_setList.size() != clause._setList.size()) {
         System.out.println("PolicyMapClause:SetList:Size " + prefix);
         finalRes = false;
      }
      else {
         Object[] lhs = _setList.toArray();
         Object[] rhs = clause._setList.toArray();
         for (int i = 0; i < _setList.size(); i++) {
            res = res
                  && ((PolicyMapSetLine) lhs[i]).sameParseTree(
                        ((PolicyMapSetLine) rhs[i]), "PolicyMapClause:SetList "
                              + prefix);
            if (res == false) {
               finalRes = false;
            }
         }
      }

      return finalRes;
   }

}
