
// all enum constants are created automattically when the enum class is loaded.

enum Color{
    RED, GREEN, BLUE;

    private Color(){
        
        System.out.println("Constructor called for: " + this);
    }

    public void display(){
        
        System.out.println("Color is: " + this);
    }
}

public class Test{
    
    public static void main(String[] args){
        
        Color c1 = Color.RED;
        c1.display();
    }
}

// Output:
// Constructor called for: RED
// Constructor called for: GREEN
// Constructor called for: BLUE
// Color is: RED