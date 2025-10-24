import java.util.Scanner;
public class CalculatePostfix {

    Tokenizer tokenizer = new Tokenizer();
    Queue<Object> queue= new Queue<>();
    Stack stack = new Stack();

    public CalculatePostfix(String expression) {
        queue=tokenizer.readTokens(expression);
        Double finalresult=0.03;
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
                Double result=postfixToResult((Double)operands[1],(Double)operands[0],(Character)operator);
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
    }
    public Double postfixToResult(Double a,Double b, Character operator) {
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
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter expression");
        String expression = input.nextLine();
        CalculatePostfix calc = new CalculatePostfix(expression);
    }
}
