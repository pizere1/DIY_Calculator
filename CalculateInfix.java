
public class CalculateInfix {
    Queue<Object> inputqueue;
    Queue<Object> outputqueue=new Queue<>();
    Stack<Object> operators =new Stack<>();
    Tokenizer tokenizer=new Tokenizer();
    CalculatePostfix calculatepostfix=new CalculatePostfix();

    public Double infixToPostfix(String expression) {
        inputqueue=tokenizer.readTokens(expression);
        while(!inputqueue.isEmpty()){
            if(inputqueue.peek()instanceof Double){
                outputqueue.add(inputqueue.remove());
            } else if(isanoperator(inputqueue.peek())){
                while(!operators.isEmpty()&& (getprecedence(operators.peek())<(getprecedence(inputqueue.peek())))){
                    outputqueue.add(operators.pop());
                }
                operators.push(inputqueue.remove());
            }else if((Character)inputqueue.peek()=='('){
                operators.push(inputqueue.remove());
            }else if((Character)inputqueue.peek()==')'){
                while((Character)operators.peek()!='('){
                    outputqueue.add(operators.pop());
                }
                if((Character)operators.peek()!='('){
                    operators.pop();
                }else{//if there's no ( found
                    throw new IllegalArgumentException();
                }
            }else{
                throw new IllegalArgumentException();
            }

        }
        while(!operators.isEmpty()){
            if(((Character)operators.peek()!='(')||(Character)operators.peek()!=')'){
                throw new IllegalArgumentException();//mismatched
            }else if(isanoperator((Character)operators.peek())){
                outputqueue.add(operators.pop());
            }else{//Not an operator
                throw new IllegalArgumentException();
            }
        }
        Double value=calculatepostfix.postfixToResult(outputqueue);
        return value;
    }
    public boolean isanoperator(Object o){
        boolean flag=false;
        if(((Character)o=='+')||((Character)o=='-')){
            flag=true;
        }else if(((Character)o=='*')||((Character)o=='/')){
            flag=true;
        }else if(((Character)o=='^')||((Character)o=='%')){
            flag=true;
        }else{
            flag=false;
        }
        return flag;
    }
    public int getprecedence(Object obj){
        int precedence=0;
        if((Character)obj=='+'){
            precedence=5;
        }else if((Character)obj=='-'){
            precedence=6;
        }else if((Character)obj=='*'){
            precedence=2;
        }else if((Character)obj=='/'){
            precedence=3;
        }else if((Character)obj=='^'){
            precedence=1;
        }else if((Character)obj=='%'){
            precedence=4;
        }else{
            throw new IllegalArgumentException("Invalid input");
        }
        return precedence;
    }
}
