import java.util.*;
public class IRGenerator implements CCALParserVisitor {

    private static int tmpCount = 1;
    private static int labelCount = 1;

    public Object visit(SimpleNode node, Object data) {
        throw new RuntimeException("Visit SimpleNode");
    }

    public Object visit(Program node, Object data) {
        node.childrenAccept(this, data);
        return data;
    }

    public Object visit(Variable node, Object data) {
        node.childrenAccept(this, data);
        return data;
    }

    public Object visit(Constant node, Object data) {
        node.childrenAccept(this, data);
        return data;
    }

    public Object visit(Function node, Object data) {
        node.childrenAccept(this, data);
        return data;
    }

    public Object visit(Type node, Object data) {
        node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(ParameterList node, Object data) {
        node.childrenAccept(this, data);
        return data;
    }

    public Object visit(NempParamList node, Object data) {
        node.childrenAccept(this, data);
        return data;
    }

    public Object visit(Main node, Object data) {
        node.childrenAccept(this, data);
        return data;
    }

    public Object visit(Statement node, Object data) {
        node.childrenAccept(this, data);
        return data;
    }

    public Object visit(Assign node, Object data) {
        node.childrenAccept(this, data);
        return data;
    }

    public Object visit(Num node, Object data) {
        node.childrenAccept(this, data);
        return data;
    }

    public Object visit(Bool node, Object data) {
        node.childrenAccept(this, data);
        return data;
    }

    public Object visit(Add node, Object data) {
        node.childrenAccept(this, data);
        return data;
    }

    public Object visit(Sub node, Object data) {
        node.childrenAccept(this, data);
        return data;
    }

    public Object visit(IsEqu node, Object data) {
        node.childrenAccept(this, data);
        return data;
    }

    public Object visit(NotEqu node, Object data) {
        node.childrenAccept(this, data);
        return data;
    }

    public Object visit(LessThan node, Object data) {
        node.childrenAccept(this, data);
        return data;
    }

    public Object visit(LessEqu node, Object data) {
        node.childrenAccept(this, data);
        return data;
    }

    public Object visit(GreatThan node, Object data) {
        node.childrenAccept(this, data);
        return data;
    }

    public Object visit(GreatEqu node, Object data) {
        node.childrenAccept(this, data);
        return data;
    }

    public Object visit(LogOr node, Object data) {
        node.childrenAccept(this, data);
        return data;
    }

    public Object visit(LogAnd node, Object data) {
        node.childrenAccept(this, data);
        return data;
    }

    public Object visit(ArgList node, Object data) {
        node.childrenAccept(this, data);
        return data;
    }

    public Object visit(Identifier node, Object data) {
        node.childrenAccept(this, data);
        return data; 
    }
}