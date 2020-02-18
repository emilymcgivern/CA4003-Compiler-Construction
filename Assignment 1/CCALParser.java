/* CCALParser.java */
/* Generated By:JavaCC: Do not edit this line. CCALParser.java */
public class CCALParser implements CCALParserConstants {

    public static void main (String args[])
    {

        CCALParser parser;

        if (args.length == 0) {

            System.out.println ("Reading from standard input...");
            parser = new CCALParser(System.in);
        }

              else if (args.length == 1) {
            try {
                parser = new CCALParser(new java.io.FileInputStream(args[0]));
            } catch (java.io.FileNotFoundException e) {
                System.err.println ("File " + args[0] + " not found.");
                return;
            }
        }

        else {
            System.out.println ("CCALParser: Usage is one of:");
            System.out.println ("java CCALParser < inputfile");
            System.out.println ("OR");
            System.out.println ("java CCALParser inputfile");
            return;
        }

        try {

            parser.program();
            System.out.println ("CCAL language parsed successfully");

         }


              catch (ParseException e) {
            System.out.println ("CCAL language parse error:");
            System.out.println (e.getMessage());
        }
    }

//*******************************************************************************************
// Grammar
  static final public 
void program() throws ParseException {
    decl_list();
    function_list();
    main();
    jj_consume_token(0);
  }

  static final public void decl_list() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case VAR:
    case CONST:{
      decl();
      jj_consume_token(SEMICOL);
      decl_list();
      break;
      }
    default:
      jj_la1[0] = jj_gen;

    }
  }

  static final public void decl() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case VAR:{
      var_decl();
      break;
      }
    case CONST:{
      const_decl();
      break;
      }
    default:
      jj_la1[1] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void var_decl() throws ParseException {
    jj_consume_token(VAR);
    jj_consume_token(ID);
    jj_consume_token(COLON);
    type();
  }

  static final public void const_decl() throws ParseException {
    jj_consume_token(CONST);
    jj_consume_token(ID);
    jj_consume_token(COLON);
    type();
    jj_consume_token(EQU);
    expression();
  }

  static final public void function_list() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case INT:
    case BOOL:
    case VOID:{
      function();
      function_list();
      break;
      }
    default:
      jj_la1[2] = jj_gen;

    }
  }

  static final public void function() throws ParseException {
    type();
    jj_consume_token(ID);
    jj_consume_token(LEFT_BR);
    parameter_list();
    jj_consume_token(RIGHT_BR);
    jj_consume_token(LEFT_CURL);
    decl_list();
    statement_block();
    jj_consume_token(RETURN);
    jj_consume_token(LEFT_BR);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case LEFT_BR:
    case MINUS_SIGN:
    case TRUE:
    case FALSE:
    case NUM:
    case ID:{
      expression();
      break;
      }
    default:
      jj_la1[3] = jj_gen;

    }
    jj_consume_token(RIGHT_BR);
    jj_consume_token(SEMICOL);
    jj_consume_token(RIGHT_CURL);
  }

  static final public void type() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case INT:{
      jj_consume_token(INT);
      break;
      }
    case BOOL:{
      jj_consume_token(BOOL);
      break;
      }
    case VOID:{
      jj_consume_token(VOID);
      break;
      }
    default:
      jj_la1[4] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void parameter_list() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case ID:{
      nemp_parameter_list();
      break;
      }
    default:
      jj_la1[5] = jj_gen;

    }
  }

  static final public void nemp_parameter_list() throws ParseException {
    jj_consume_token(ID);
    jj_consume_token(COLON);
    type();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case COMMA:{
      jj_consume_token(COMMA);
      nemp_parameter_list();
      break;
      }
    default:
      jj_la1[6] = jj_gen;

    }
  }

  static final public void main() throws ParseException {
    jj_consume_token(MAIN);
    jj_consume_token(LEFT_CURL);
    decl_list();
    statement_block();
    jj_consume_token(RIGHT_CURL);
  }

  static final public void statement_block() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case LEFT_CURL:
    case IF:
    case WHILE:
    case SKIPP:
    case ID:{
      statement();
      statement_block();
      break;
      }
    default:
      jj_la1[7] = jj_gen;

    }
  }

  static final public void statement() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case ID:{
      jj_consume_token(ID);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case EQU:{
        jj_consume_token(EQU);
        expression();
        jj_consume_token(SEMICOL);
        break;
        }
      case LEFT_BR:{
        jj_consume_token(LEFT_BR);
        arg_list();
        jj_consume_token(RIGHT_BR);
        jj_consume_token(SEMICOL);
        break;
        }
      default:
        jj_la1[8] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
      }
    case LEFT_CURL:{
      jj_consume_token(LEFT_CURL);
      statement_block();
      jj_consume_token(RIGHT_CURL);
      break;
      }
    case IF:{
      jj_consume_token(IF);
      condition();
      jj_consume_token(LEFT_CURL);
      statement_block();
      jj_consume_token(RIGHT_CURL);
      jj_consume_token(ELSE);
      jj_consume_token(LEFT_CURL);
      statement_block();
      jj_consume_token(RIGHT_CURL);
      break;
      }
    case WHILE:{
      jj_consume_token(WHILE);
      condition();
      jj_consume_token(LEFT_CURL);
      statement_block();
      jj_consume_token(RIGHT_CURL);
      break;
      }
    case SKIPP:{
      jj_consume_token(SKIPP);
      jj_consume_token(SEMICOL);
      break;
      }
    default:
      jj_la1[9] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void expression() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case LEFT_BR:{
      jj_consume_token(LEFT_BR);
      expression();
      jj_consume_token(RIGHT_BR);
      expression_nt();
      break;
      }
    case MINUS_SIGN:
    case TRUE:
    case FALSE:
    case NUM:
    case ID:{
      fragment();
      expression_nt();
      break;
      }
    default:
      jj_la1[10] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void expression_nt() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case PLUS_SIGN:
    case MINUS_SIGN:{
      binary_arith_op();
      fragment();
      expression_nt();
      break;
      }
    default:
      jj_la1[11] = jj_gen;

    }
  }

  static final public void binary_arith_op() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case PLUS_SIGN:{
      jj_consume_token(PLUS_SIGN);
      break;
      }
    case MINUS_SIGN:{
      jj_consume_token(MINUS_SIGN);
      break;
      }
    default:
      jj_la1[12] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void fragment() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case MINUS_SIGN:
    case ID:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case MINUS_SIGN:{
        jj_consume_token(MINUS_SIGN);
        break;
        }
      default:
        jj_la1[13] = jj_gen;
        ;
      }
      jj_consume_token(ID);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case LEFT_BR:{
        jj_consume_token(LEFT_BR);
        arg_list();
        jj_consume_token(RIGHT_BR);
        break;
        }
      default:
        jj_la1[14] = jj_gen;
        ;
      }
      break;
      }
    case NUM:{
      jj_consume_token(NUM);
      break;
      }
    case TRUE:{
      jj_consume_token(TRUE);
      break;
      }
    case FALSE:{
      jj_consume_token(FALSE);
      break;
      }
    default:
      jj_la1[15] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void condition() throws ParseException {
    if (jj_2_1(3)) {
      jj_consume_token(LEFT_BR);
      condition();
      jj_consume_token(RIGHT_BR);
      condition_nt();
    } else {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case LOG_NEG:{
        jj_consume_token(LOG_NEG);
        condition();
        condition_nt();
        break;
        }
      case LEFT_BR:
      case MINUS_SIGN:
      case TRUE:
      case FALSE:
      case NUM:
      case ID:{
        expression();
        comp_op();
        expression();
        condition_nt();
        break;
        }
      default:
        jj_la1[16] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

  static final public void condition_nt() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case LOG_OR:
    case LOG_AND:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case LOG_OR:{
        jj_consume_token(LOG_OR);
        break;
        }
      case LOG_AND:{
        jj_consume_token(LOG_AND);
        break;
        }
      default:
        jj_la1[17] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      condition();
      condition_nt();
      break;
      }
    default:
      jj_la1[18] = jj_gen;

    }
  }

  static final public void comp_op() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case IS_EQU:{
      jj_consume_token(IS_EQU);
      break;
      }
    case NOT_EQU:{
      jj_consume_token(NOT_EQU);
      break;
      }
    case LESS_THAN:{
      jj_consume_token(LESS_THAN);
      break;
      }
    case LESS_EQU:{
      jj_consume_token(LESS_EQU);
      break;
      }
    case GREAT_THAN:{
      jj_consume_token(GREAT_THAN);
      break;
      }
    case GREAT_EQU:{
      jj_consume_token(GREAT_EQU);
      break;
      }
    default:
      jj_la1[19] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void arg_list() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case ID:{
      nemp_arg_list();
      break;
      }
    default:
      jj_la1[20] = jj_gen;

    }
  }

  static final public void nemp_arg_list() throws ParseException {
    jj_consume_token(ID);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case COMMA:{
      jj_consume_token(COMMA);
      nemp_arg_list();
      break;
      }
    default:
      jj_la1[21] = jj_gen;

    }
  }

  static private boolean jj_2_1(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  static private boolean jj_3R_12()
 {
    return false;
  }

  static private boolean jj_3R_11()
 {
    if (jj_3R_14()) return true;
    return false;
  }

  static private boolean jj_3R_9()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_11()) {
    jj_scanpos = xsp;
    if (jj_3R_12()) return true;
    }
    return false;
  }

  static private boolean jj_3R_3()
 {
    if (jj_3R_4()) return true;
    if (jj_3R_5()) return true;
    return false;
  }

  static private boolean jj_3R_2()
 {
    if (jj_scan_token(LOG_NEG)) return true;
    if (jj_3R_1()) return true;
    return false;
  }

  static private boolean jj_3_1()
 {
    if (jj_scan_token(LEFT_BR)) return true;
    if (jj_3R_1()) return true;
    return false;
  }

  static private boolean jj_3R_1()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_1()) {
    jj_scanpos = xsp;
    if (jj_3R_2()) {
    jj_scanpos = xsp;
    if (jj_3R_3()) return true;
    }
    }
    return false;
  }

  static private boolean jj_3R_7()
 {
    if (jj_3R_8()) return true;
    if (jj_3R_9()) return true;
    return false;
  }

  static private boolean jj_3R_4()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_6()) {
    jj_scanpos = xsp;
    if (jj_3R_7()) return true;
    }
    return false;
  }

  static private boolean jj_3R_6()
 {
    if (jj_scan_token(LEFT_BR)) return true;
    if (jj_3R_4()) return true;
    return false;
  }

  static private boolean jj_3R_10()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(20)) jj_scanpos = xsp;
    if (jj_scan_token(ID)) return true;
    xsp = jj_scanpos;
    if (jj_3R_13()) jj_scanpos = xsp;
    return false;
  }

  static private boolean jj_3R_8()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_10()) {
    jj_scanpos = xsp;
    if (jj_scan_token(43)) {
    jj_scanpos = xsp;
    if (jj_scan_token(39)) {
    jj_scanpos = xsp;
    if (jj_scan_token(40)) return true;
    }
    }
    }
    return false;
  }

  static private boolean jj_3R_13()
 {
    if (jj_scan_token(LEFT_BR)) return true;
    return false;
  }

  static private boolean jj_3R_5()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(24)) {
    jj_scanpos = xsp;
    if (jj_scan_token(25)) {
    jj_scanpos = xsp;
    if (jj_scan_token(26)) {
    jj_scanpos = xsp;
    if (jj_scan_token(27)) {
    jj_scanpos = xsp;
    if (jj_scan_token(28)) {
    jj_scanpos = xsp;
    if (jj_scan_token(29)) return true;
    }
    }
    }
    }
    }
    return false;
  }

  static private boolean jj_3R_14()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(19)) {
    jj_scanpos = xsp;
    if (jj_scan_token(20)) return true;
    }
    return false;
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public CCALParserTokenManager token_source;
  static JavaCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private Token jj_scanpos, jj_lastpos;
  static private int jj_la;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[22];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0xc0000000,0xc0000000,0x0,0x120000,0x0,0x0,0x800,0x8000,0x24000,0x8000,0x120000,0x180000,0x180000,0x100000,0x20000,0x100000,0x320000,0xc00000,0xc00000,0x3f000000,0x0,0x800,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0xe,0x2980,0xe,0x2000,0x0,0x2620,0x0,0x2620,0x2980,0x0,0x0,0x0,0x0,0x2980,0x2980,0x0,0x0,0x0,0x2000,0x0,};
   }
  static final private JJCalls[] jj_2_rtns = new JJCalls[1];
  static private boolean jj_rescan = false;
  static private int jj_gc = 0;

  /** Constructor with InputStream. */
  public CCALParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public CCALParser(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new JavaCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new CCALParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 22; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 22; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public CCALParser(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new JavaCharStream(stream, 1, 1);
    token_source = new CCALParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 22; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 22; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public CCALParser(CCALParserTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 22; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(CCALParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 22; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  @SuppressWarnings("serial")
  static private final class LookaheadSuccess extends java.lang.Error { }
  static final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  static private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk_f() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;
  static private int[] jj_lasttokens = new int[100];
  static private int jj_endpos;

  static private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      jj_entries_loop: for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.add(jj_expentry);
          break jj_entries_loop;
        }
      }
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[48];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 22; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 48; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

  static private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 1; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  static private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}
