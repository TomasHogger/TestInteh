package main.exception;

public class RequirementStatusAndReadinessIncorrect extends RuntimeException {
    public RequirementStatusAndReadinessIncorrect() {
        super("All task completed. Set status to COMPLETED and Readiness to 100");
    }
}
