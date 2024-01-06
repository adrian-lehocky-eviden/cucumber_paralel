package StepDefinitions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class StepDefs {


    @Given("I")
    public void i() throws InterruptedException {
        System.out.println("I");
        Thread.sleep(5000);

    }

    @Given("A")
    public void a() throws InterruptedException {
        System.out.println("A");
        Thread.sleep(10000);
    }

    @Given("B")
    public void b() throws InterruptedException {
        System.out.println("B");
        Thread.sleep(15000);
    }

    @When ("O")

    public void o() throws InterruptedException {
        System.out.println("close after");
        Thread.sleep(15000);
    }

    @Given("Y")
    public void y() throws InterruptedException {
        System.out.println("close afterwards");
        Thread.sleep(15000);
    }

    @Given("X")
    public void x() throws InterruptedException {
        System.out.println("close aftertowards");
        Thread.sleep(15000);
    }
}