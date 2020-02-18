import java.util.*;

public class SemanticChecks implements CCALParserVisitor {

    private static String scope = "global";
    private static SymbolTable st;
    private static ArrayList<String> calledFunctions = new ArrayList<String>();

    //Semantic checks specified in assignment

    //Is every identifier declared within scope before its is used?
    private static boolean declaredWithinScope = true;
    // Is no identifier declared more than once in the same scope?
    private static boolean noDupsinScope = true;
    //Is the left-hand side of an assignment a variable of the correct type?
    private static boolean correctType = true;
    //Are the arguments of an arithmetic operator the integer variables or integer constants?
    private static boolean correctArgsArith = true;
    //Are the arguments of a boolean operator boolean variables or boolean constants?
    private static boolean correctArgsBool = true;
    //Is there a function for every invoked identifier?
    private static boolean functionForIdentifier = true;
    //Does every function call have the correct number of arguments?
    private static boolean correctNumArgs = true;
    //Is every variable both written to and read from?
    private static boolean writtenAndRead = true;
    //Is every function called?
    private static boolean allFunctionsCalled = true;

    public Object visit(SimpleNode node, Object data) {
        throw new RuntimeException("Visit SimpleNode");
    }

    public Object visit(Program node, Object data) {
        st = (SymbolTable) data;
        node.childrenAccept(this, data);
        st.duplicateChecker();

        if (declaredWithinScope) {
            System.out.println("Pass: All identifiers declared within scope before use!");
        }

        if (noDupsinScope) {
             System.out.println("Pass: No identifier declared more than once in the same scope!");
        }

        if (correctType) {
             System.out.println("Pass: Variable assignment is of the correct type!");
        }

        ArrayList<String> functions = st.getFunctionList();
        for(int i = 0; i < functions.size(); i++) {
            if(!calledFunctions.contains(functions.get(i))) {
                allFunctionsCalled = false;
                System.out.println("Fail: Some functions are never invoked");
            }
        }

        if (allFunctionsCalled) {
             System.out.println("Pass: Every function is called!");
        }

        if (correctArgsArith) {
             System.out.println("Pass: Arithmetic Arguments are Correct!");
        }

        if (correctArgsBool) {
            System.out.println("Pass: Boolean Arguments are Correct!");
        }



        return DataType.Program;
    }

    public Object visit(Variable node, Object data) {
        node.childrenAccept(this, data);
        return DataType.Variable;
    }

    public Object visit(Constant node, Object data) {
        node.childrenAccept(this, data);
        return DataType.Constant;
    }

    public Object visit(Function node, Object data) {
        SimpleNode id = (SimpleNode) node.jjtGetChild(1);
        DataType type = (DataType) node.jjtGetChild(0).jjtAccept(this, data);
    

        String functionName = (String) id.value;
        if(st.checkFunction(functionName)) {
            calledFunctions.add(functionName);
        }
        node.childrenAccept(this, data);
        return DataType.Function;
    }

    public Object visit(Type node, Object data) {
        String val = (String) node.value;

        if (val.equals("boolean")) {
            return DataType.Bool;
        }

        if (val.equals("integer")) {
            return DataType.Num;
        }

        return DataType.Unknown;
    }
    
    public Object visit(ParameterList node, Object data) {
        node.childrenAccept(this, data);
        return DataType.ParameterList;
    }

    public Object visit(NempParamList node, Object data) {
        node.childrenAccept(this, data);
        return DataType.ParameterList;
    }

    public Object visit(Main node, Object data) {
        scope = "main";
        node.childrenAccept(this, data);
        return DataType.Main;
    }

    public Object visit(Statement node, Object data) {
        node.childrenAccept(this, data);
        return DataType.Statement;
    }

    public Object visit(Assign node, Object data) {
        DataType child1DataType = (DataType) node.jjtGetChild(0).jjtAccept(this, data);
        DataType child2DataType = (DataType) node.jjtGetChild(1).jjtAccept(this, data);
      
        if (child1DataType == child2DataType) {
            return DataType.Assign;
        }

        else {
            correctType = false;
            System.out.println("Fail: Variable assignment is not the correct type!");
        }

        node.childrenAccept(this, data);
        return DataType.Unknown;
    }

    public Object visit(Num node, Object data) {
        return DataType.Num;
    }

    public Object visit(Bool node, Object data) {
        return DataType.Bool;
    }

    public Object visit(Add node, Object data) {
        DataType child1DataType = (DataType) node.jjtGetChild(0).jjtAccept(this, data);
        DataType child2DataType = (DataType) node.jjtGetChild(1).jjtAccept(this, data);

        if ((child1DataType == DataType.Num) && (child2DataType == DataType.Num)) {
            return DataType.Bool;
        }

        if ((child1DataType != DataType.Num) | (child2DataType != DataType.Num)) {
            correctArgsArith = false;
            System.out.println("Fail: Cannot assign " + child1DataType + " to " + child2DataType);
        }

        return DataType.Unknown;
    }

    public Object visit(Sub node, Object data) {
        DataType child1DataType = (DataType) node.jjtGetChild(0).jjtAccept(this, data);
        DataType child2DataType = (DataType) node.jjtGetChild(1).jjtAccept(this, data);
        if ((child1DataType == DataType.Num) && (child2DataType == DataType.Num)) {
            return DataType.Num;
        }

        if ((child1DataType != DataType.Num) | (child2DataType != DataType.Num)) {
            correctArgsArith = false;
            System.out.println("Fail: Cannot assign " + child1DataType + " to " + child2DataType);
        }

        return DataType.Unknown;
    }

    public Object visit(IsEqu node, Object data) {
        DataType child1DataType = (DataType) node.jjtGetChild(0).jjtAccept(this, data);
        DataType child2DataType = (DataType) node.jjtGetChild(1).jjtAccept(this, data);
        if (child1DataType == child2DataType) {
            return child1DataType;
        }
        return DataType.Unknown;
    }

    public Object visit(NotEqu node, Object data) {
        DataType child1DataType = (DataType) node.jjtGetChild(0).jjtAccept(this, data);
        DataType child2DataType = (DataType) node.jjtGetChild(1).jjtAccept(this, data);
        if (child1DataType == child2DataType) {
            return child1DataType;
        }
        return DataType.Unknown;
    }

    public Object visit(LessThan node, Object data) {
        DataType child1DataType = (DataType) node.jjtGetChild(0).jjtAccept(this, data);
        DataType child2DataType = (DataType) node.jjtGetChild(1).jjtAccept(this, data);
        if ((child1DataType == DataType.Num) && (child2DataType == DataType.Num)) {
            return DataType.Bool;
        }

        if ((child1DataType != DataType.Bool) | (child2DataType != DataType.Bool)) {
            correctArgsBool = false;
            System.out.println("Fail: Cannot assign " + child1DataType + " to " + child2DataType);
        }

        return DataType.Unknown;
    }

    public Object visit(LessEqu node, Object data) {
        DataType child1DataType = (DataType) node.jjtGetChild(0).jjtAccept(this, data);
        DataType child2DataType = (DataType) node.jjtGetChild(1).jjtAccept(this, data);
        if ((child1DataType == DataType.Num) && (child2DataType == DataType.Num)) {
            return DataType.Bool;
        }

        if ((child1DataType != DataType.Bool) | (child2DataType != DataType.Bool)) {
            correctArgsBool = false;
            System.out.println("Fail: Cannot assign " + child1DataType + " to " + child2DataType);
        }

        return DataType.Unknown;
    }

    public Object visit(GreatThan node, Object data) {
        DataType child1DataType = (DataType) node.jjtGetChild(0).jjtAccept(this, data);
        DataType child2DataType = (DataType) node.jjtGetChild(1).jjtAccept(this, data);
        if ((child1DataType == DataType.Num) && (child2DataType == DataType.Num)) {
            return DataType.Bool;
        }

        if ((child1DataType != DataType.Bool) | (child2DataType != DataType.Bool)) {
            correctArgsBool = false;
            System.out.println("Fail: Cannot assign " + child1DataType + " to " + child2DataType);
        }

        return DataType.Unknown;
    }

    public Object visit(GreatEqu node, Object data) {
        DataType child1DataType = (DataType) node.jjtGetChild(0).jjtAccept(this, data);
        DataType child2DataType = (DataType) node.jjtGetChild(1).jjtAccept(this, data);
        if ((child1DataType == DataType.Num) && (child2DataType == DataType.Num)) {
            return DataType.Bool;
        }

        if ((child1DataType != DataType.Bool) | (child2DataType != DataType.Bool)) {
            correctArgsBool = false;
            System.out.println("Fail: Cannot assign " + child1DataType + " to " + child2DataType);
        }

        return DataType.Unknown;
    }

    public Object visit(LogOr node, Object data) {
        DataType child1DataType = (DataType) node.jjtGetChild(0).jjtAccept(this, data);
        DataType child2DataType = (DataType) node.jjtGetChild(1).jjtAccept(this, data);
        if ((child1DataType == DataType.Bool) && (child2DataType == DataType.Bool)) {
            return DataType.Bool;
        }

        if ((child1DataType != DataType.Bool) | (child2DataType != DataType.Bool)) {
            correctArgsBool = false;
            System.out.println("Fail: Cannot assign " + child1DataType + " to " + child2DataType);
        }

        return DataType.Unknown;
    }

    public Object visit(LogAnd node, Object data) {
        DataType child1DataType = (DataType) node.jjtGetChild(0).jjtAccept(this, data);
        DataType child2DataType = (DataType) node.jjtGetChild(1).jjtAccept(this, data);
        if ((child1DataType == DataType.Bool) && (child2DataType == DataType.Bool)) {
            return DataType.Bool;
        }

        if ((child1DataType != DataType.Bool) | (child2DataType != DataType.Bool)) {
            correctArgsBool = false;
            System.out.println("Fail: Cannot assign " + child1DataType + " to " + child2DataType);
        }

        return DataType.Unknown;
    }

    public Object visit(ArgList node, Object data) {
        node.childrenAccept(this, data);
        return DataType.Unknown;
    }

    public Object visit(Identifier node, Object data) {
        SimpleNode parent = (SimpleNode) node.jjtGetParent();
        String value = (String) node.jjtGetValue();
        String nodeValue = (String) node.value;

        if(parent.toString() == "Function"){
            String id = (String)node.jjtGetValue();
            scope = id;
            return DataType.Function;
        }

        else if(parent.toString() == "Variable"){
            return DataType.Variable;
        }

        else if(parent.toString() == "Constant"){
            return DataType.Constant;
        }

        else if(st.checkDeclaration(value, scope).equals("integer")) {
          return DataType.Num;
        }

        else if(st.checkDeclaration(value, scope).equals("boolean")) {
          return DataType.Bool;
        }

        else if(st.checkDeclaration(value, scope).equals("")) {
            declaredWithinScope = false;
            System.out.println("Fail: Not all identifiers are declared within scope");
        }
        
        return DataType.Unknown; 
    }

}
