public class CalculatePostfix {

    Tokenizer tokenizer = new Tokenizer();
    Queue<Object> queue;
    Stack stack = new Stack();

    public CalculatePostfix(String expression) {
        queue=tokenizer.readTokens(expression);
        postfixToResult(queue);

    }
    public Double postfixToResult(Queue<Object>queue){
        Double finalresult=0.0;
        while(!queue.isEmpty()){
            if(queue.peek() instanceof Double) {
                stack.push((Double)(queue.remove()));
            }else if(queue.peek() instanceof Character) {
                Object operator=queue.remove();
                Double [] operands=new Double[2];
                for(int i=0;i<operands.length;i++) {
                    if(stack.isEmpty()){
                        throw new IllegalArgumentException();
                    } else{
                        operands[i]=(Double)stack.pop();
                    }
                }
                Double result=postfixToResult1((Double)operands[1],(Double)operands[0],(Character)operator);
                stack.push((result));
            }
        }
        if(queue.isEmpty()){
            finalresult=(Double)stack.pop();
            if(!stack.isEmpty()){
                throw new IllegalArgumentException();
            }
        }
       System.out.println(finalresult);
        return(finalresult);
    }
    public Double postfixToResult1(Double a,Double b, Character operator) {
        Double result;
        if (operator == '+') {
            result = a + b;
        } else if (operator == '-') {
            result = a - b;
        } else if (operator == '*') {
            result = a * b;
        } else if (operator == '/') {
            result = a / b;
        } else if (operator == '%') {
            result = a % b;
        } else if (operator == '^') {
            result = Math.pow(a, b);
        }
        else{
            result=null;
        }
        return result;
    }
}
