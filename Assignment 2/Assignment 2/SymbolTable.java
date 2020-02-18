import java.util.*;

public class SymbolTable extends Object {
    private Hashtable<String, LinkedList<String>> st;
    private Hashtable<String, String> types;
    private Hashtable<String, String> desc;

	SymbolTable() {
		st = new Hashtable<>();
		types = new Hashtable<>();
		desc = new Hashtable<>();
		st.put("global", new LinkedList<>());
	}

	public void insert(String id, String val, String type, String scope) {
		LinkedList<String> scopeList = st.get(scope);
		if (scopeList == null) {
			scopeList = new LinkedList<>();
			scopeList.add(id);
			st.put(scope, scopeList);
		}

		else {
			scopeList.addFirst(id);
		}

		types.put(id + scope, type);
		desc.put(id + scope, val);
	}

    public void printST() {
    	String scope;
    	Enumeration e = st.keys();
    	while (e.hasMoreElements()) {
        	scope = (String) e.nextElement();
        	System.out.println("\n" + "Scope: " + scope + "\n");
        	LinkedList<String> scopeList = st.get(scope);
        	for (String id : scopeList) {
            	String type = types.get(id + scope);
            	String val = desc.get(id + scope);
            	System.out.print(id + ":" + type + "(" + val + ")" + "\n");
        	}
    	}
	}

	public String getDesc(String id, String scope) {
		return desc.get(id + scope);
	}

    public String checkDeclaration(String id, String scope) {
        LinkedList<String> scopeList = st.get(scope);
        LinkedList<String> globalScopeList = st.get("global");
        if (scopeList != null) {
            for (int i = 0; i < scopeList.size(); i++) {
                if ((scopeList.get(i)).equals(id)) {
                    return types.get(id + scope);
                }
            }
        }
        if( globalScopeList != null){
            for (int i = 0; i < globalScopeList.size(); i++) {
                if ((globalScopeList.get(i)).equals(id)) {
                    return types.get(id + "global");
                }
            }
        }

        return types.get(id + scope);
    }

   public void duplicateChecker(){
        Set<String>keys = st.keySet();
        for(String key : keys) {
            LinkedList<String> keyList = st.get(key);
            String topList = keyList.pop();
            if(keyList.contains(topList)){
                System.out.println("Fail: Identifier declared more that once in scope");

            }
        }
    }

    public ArrayList<String> getFunctionList(){
    	LinkedList<String> globalScopeList = st.get("global");
    	ArrayList<String> functions = new ArrayList<String>();
    	for (int i = 0; i < globalScopeList.size(); i++) {
    		String checkDesc = desc.get(globalScopeList.get(i)+ "global");
			if (checkDesc.equals("function")) {
					functions.add(globalScopeList.get(i));
			}
    	}

    	return functions;
    }

    public boolean checkFunction(String id) {
    	LinkedList<String> globalScopeList = st.get("global");
    	for (int i = 0; i < globalScopeList.size(); i++) {
    		String checkDesc = desc.get(globalScopeList.get(i)+ "global");
			if (checkDesc.equals("function") && globalScopeList.get(i).equals(id)) {
					return true;
			}
    	}
    	return false;
    }
}
