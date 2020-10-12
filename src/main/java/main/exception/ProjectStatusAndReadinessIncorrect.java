package main.exception;

public class ProjectStatusAndReadinessIncorrect extends RuntimeException {
    public ProjectStatusAndReadinessIncorrect() {
        super("All requirements completed. Set status to COMPLETED and Readiness to 100");
    }
}
